/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.repositories;

/**
 *
 * @author fenrrir
 */
public class ComponentProfileRepositoryManager extends RepositoryManager implements IComponentProfileRepositoryManager{

    public ComponentProfileRepositoryManager(IComponentProfileRepositoryManager concreteComponent) {
        super(concreteComponent);
    }

}
