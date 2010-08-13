/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.repositories;

/**
 *
 * @author fenrrir
 */
public class AdapterProfileRepositoryManager extends RepositoryManager implements IAdapterProfileRepositoryManager{

    public AdapterProfileRepositoryManager(IAdapterProfileRepositoryManager concreteComponent) {
        super(concreteComponent);
    }
}
