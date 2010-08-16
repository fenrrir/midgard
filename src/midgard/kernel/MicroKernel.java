/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.kernel;

import midgard.naming.INameService;

/**
 *
 * @author fenrrir
 */
public class MicroKernel {

    private static MicroKernel instance = null;

    private MicroKernel(){
    }

    public INameService getNameService(){
        return null;
    }

    public ClassLoader getClassLoader(){
        return ClassLoader.getInstance();
    }

    public static MicroKernel getInstance(){
        if (instance == null)
            instance = new MicroKernel();
        return instance;
    }

}
