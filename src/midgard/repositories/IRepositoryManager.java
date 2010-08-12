/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.repositories;

import midgard.componentmodel.IComponent;

/**
 *
 * @author fenrrir
 */
public interface IRepositoryManager extends IComponent {
    void open(); //clash
    void clear();
    void close();

}
