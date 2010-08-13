/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.repositories;

/**
 *
 * @author fenrrir
 */
public class ComponentRepositoryManager extends RepositoryManager implements IComponentRepositoryManager{

    public ComponentRepositoryManager(IComponentRepositoryManager concreteComponent) {
        super(concreteComponent);
    }
}
