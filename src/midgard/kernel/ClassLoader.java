/*
* Copyright (C) 2011 Rodrigo Pinheiro Marques de Araujo
*
* This program is free software; you can redistribute it and/or modify it under
* the terms of the GNU General Public License as published by the Free Software
* Foundation; either version 2 of the License, or (at your option) any later
* version.
*
* This program is distributed in the hope that it will be useful, but WITHOUT
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
* FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
* details.
*
* You should have received a copy of the GNU General Public License along with
* this program; if not, write to the Free Software Foundation, Inc., 51
* Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
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
