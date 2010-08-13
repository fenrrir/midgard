/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.repositories;

/**
 *
 * @author fenrrir
 */
public class AdaptationProfileRepositoryManager extends RepositoryManager implements IAdaptationProfileRepositoryManager{

    public AdaptationProfileRepositoryManager(IAdaptationProfileRepositoryManager concreteComponent) {
        super(concreteComponent);
    }
}
