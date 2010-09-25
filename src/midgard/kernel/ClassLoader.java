/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.kernel;

import midgard.componentmodel.Component;

/**
 *
 * @author fenrrir
 */
public class ClassLoader extends Component{

    private static ClassLoader instance = null;

    private ClassLoader(){
    }

    public static ClassLoader getInstance(){
        if (instance == null)
            instance = new ClassLoader();
        return instance;
    }

    public static Class getClassByName(String name) throws ClassNotFoundException{
        return Class.forName(name);
    }

    public static Object newInstanceOf(Class cls) throws IllegalAccessException,
                                                  InstantiationException{
        return cls.newInstance();
    }

    public static Object newInstanceOf(String name) throws IllegalAccessException,
                                                    InstantiationException,
                                                    ClassNotFoundException{
        Class cls = getClassByName(name);
        return cls.newInstance();
    }

    public static boolean isInterface(String name){
        try {
            Class cls = getClassByName(name);
            return cls.isInterface();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
