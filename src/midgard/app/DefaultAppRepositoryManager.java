/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midgard.app;

import java.util.Enumeration;
import java.util.Vector;
import midgard.componentmodel.Component;
import midgard.componentmodel.IComponent;
import midgard.components.IComponentManager;
import midgard.config.IDefaultConfig;
import midgard.naming.DNS;
import midgard.sensors.accelerometer.AccelerometerThresholds;
import midgard.sensors.light.ThresholdChangedLightData;
import midgard.sensors.temperature.ThresholdChangedTemperatureData;
import midgard.utils.FileUtils;
import midgard.utils.JSONUtils;
import midgard.web.json.JSONArray;
import midgard.web.json.JSONException;
import midgard.web.json.JSONObject;

/**
 *
 * @author fenrrir
 */
public class DefaultAppRepositoryManager extends Component implements IAppRepositoryManager {

    private IComponentManager componentManager = null;
    private IDefaultConfig defaultConfig = null;
    private final String REPO = "/apps.json";
    private Vector names, appLabels,
            sensors, services,
            tasks, customEvents,
            adaptationProfiles, webApplications;
    private JSONObject json = null;
    private long sleepTime;

    public String[] getRequiredInterfaces() {
        return new String[]{
                    IComponentManager.class.getName(),
                    IDefaultConfig.class.getName()
                };
    }

    public void initialize() {
        super.initialize();
        componentManager = (IComponentManager) getConnectedComponents().get(IComponentManager.class.getName());

        defaultConfig = (IDefaultConfig) getConnectedComponents().get(IDefaultConfig.class.getName());

    }

    public IApp getApp(String name) {
        return (IApp) get(name);
    }

    public Vector getInstalledAppNames() {
        return names;
    }

    public IThreadedApp getThreadedApp(String name) {
        return (IThreadedApp) get(name);
    }

    public void clear() {
        names.removeAllElements();
        services.removeAllElements();
        sensors.removeAllElements();
        tasks.removeAllElements();
        customEvents.removeAllElements();
        services = null;
        sensors = null;
        names = null;
        json = null;
        defaultConfig = null;
    }

    public void close() {
        clear();
    }

    public IComponent get(String name) {
        return (IComponent) componentManager.resolveComponent(name);
    }

    public Vector list() {
        Vector v1, v2;
        Enumeration e;
        v1 = componentManager.getComponentsFromInterface(DNS.IAPP);
        v2 = componentManager.getComponentsFromInterface(DNS.ITHREADEDAPP);

        e = v2.elements();
        while (e.hasMoreElements()) {
            v1.addElement(e.nextElement());
        }

        return v1;

    }

    public void open() {
        JSONObject apps;
        String name, classname;
        String sensorinterface, sensorclass;

        if (json == null) {
            try {

                names = new Vector();
                sensors = new Vector();
                appLabels = new Vector();

                json = new JSONObject(FileUtils.readFile(REPO));

                apps = json.getJSONObject("apps");
                Enumeration e = apps.keys();
                while (e.hasMoreElements()) {
                    name = (String) e.nextElement();
                    appLabels.addElement(name);
                    classname = apps.getJSONObject(name).getString("class");
                    names.addElement(classname);

                    try {
                        JSONArray sensorsArray = apps.getJSONObject(name).getJSONArray("sensors");
                        Vector vSensorsArray = JSONUtils.JSONArrayToVectorString(sensorsArray);
                        for (int i = 0; i < vSensorsArray.size(); i++) {
                            String element = (String) vSensorsArray.elementAt(i);
                            if (!sensors.contains(element)) {
                                sensors.addElement(e);
                            }
                        }
                    } catch (JSONException ex) {
                        //System.err.println("Configuracao da app " + name + "nao contem sensores");
                    }

                }

                services = loadVectorDataFrom("services");
                tasks = loadVectorDataFrom("tasks");
                customEvents = loadVectorDataFrom("customEvents");
                adaptationProfiles = loadVectorDataFrom("adaptationProfiles");
                webApplications = loadVectorDataFrom("webApplications");

                try {
                    sleepTime = json.getLong("sleep");
                } catch (JSONException ex) {
                    sleepTime = defaultConfig.getSleepTime();
                    //System.err.println("Configuracao das apps nao contem tempo de sleep");
                }



            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
    }

    private Vector loadVectorDataFrom(String name) {
        Vector v;
        try {
            v = JSONUtils.JSONArrayToVectorString(json.getJSONArray(name));
        } catch (JSONException ex) {
            v = new Vector();
            //System.err.println("Configuracao das apps nao contem " + name);
        }
        return v;
    }

    public Vector listAppLabels() {
        return appLabels;
    }

    public Vector getSensorsForAppLabel(String name) {
        try {
            return JSONUtils.JSONArrayToVectorString(
                    json.getJSONObject("apps").getJSONObject(name).getJSONArray("sensors"));
        } catch (JSONException ex) {
            return new Vector();
        }

    }

    public IApp getAppFromLabel(String name) {
        try {
            String cls = json.getJSONObject("apps").getJSONObject(name).getString("class");
            return getApp(cls);
        } catch (JSONException ex) {
            return null;
        }
    }

    public Vector listSensors() {
        return sensors;
    }

    public Vector listServices() {
        return services;
    }

    public long getSleepTime() {
        return sleepTime;
    }

    public void destroy() {
        super.destroy();
        close();
    }

    public void pause() {
        super.pause();
        close();
    }

    public void resume() {
        super.resume();
        open();
    }

    public ThresholdChangedLightData getLightThreshold() {
        int max;
        int min;
        try {
            JSONObject lightConf = json.getJSONObject("lightThreshold");
            max = lightConf.getInt("max");
            min = lightConf.getInt("min");
        } catch (JSONException ex) {
            return null;
        }
        return new ThresholdChangedLightData(min, max);

    }

    public ThresholdChangedTemperatureData getTemperatureThreshold() {
        double max;
        double min;
        try {
            JSONObject lightConf = json.getJSONObject("temperatureThreshold");
            max = lightConf.getDouble("max");
            min = lightConf.getDouble("min");
        } catch (JSONException ex) {
            return null;
        }
        return new ThresholdChangedTemperatureData(min, max);
    }

    public AccelerometerThresholds getAccelerometerThreshold() {
        double xmax, xmin, ymax, ymin, zmax, zmin;
        boolean relative;
        try {
            JSONObject axisX, axisY, axisZ;

            relative = json.getJSONObject("accelerometerThreshold").getBoolean("relative");
            axisX = json.getJSONObject("accelerometerThreshold").getJSONObject("x");
            axisY = json.getJSONObject("accelerometerThreshold").getJSONObject("y");
            axisZ = json.getJSONObject("accelerometerThreshold").getJSONObject("z");

            xmax = axisX.getDouble("max");
            xmin = axisX.getDouble("min");

            ymax = axisY.getDouble("max");
            ymin = axisY.getDouble("min");

            zmax = axisZ.getDouble("max");
            zmin = axisZ.getDouble("min");
        } catch (JSONException ex) {
            return null;
        }
        return new AccelerometerThresholds(xmin, xmax,
                ymin, ymax,
                zmin, zmax,
                relative);
    }

    public Vector listTasks() {
        return tasks;
    }

    public Vector listCustomEvents() {
        return customEvents;
    }

    public Vector listAdaptationProfiles() {
        return adaptationProfiles;
    }

    public Vector listWebApplications() {
        return webApplications;
    }
}
