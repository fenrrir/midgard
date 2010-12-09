/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.repositories;

import midgard.adaptation.IAdaptationProfile;
import midgard.componentmodel.IComponent;

/**
 *
 * @author fenrrir
 */
public class AdaptationProfileRepositoryManager extends RepositoryManager implements IAdaptationProfileRepositoryManager{
    private IAdaptationProfileRepositoryManager concreteComponent;

    public void setConcreteComponent(IComponent concreteComponent){
        super.setConcreteComponent(concreteComponent);
        this.concreteComponent = (IAdaptationProfileRepositoryManager) concreteComponent;
    }

    public IAdaptationProfile getProfile(String name) {
        return concreteComponent.getProfile(name);
    }

    public void freeProfile(String name) {
        concreteComponent.freeProfile(name);
    }

    


}
