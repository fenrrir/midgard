/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.repositories;

import midgard.adaptation.IAdaptationProfile;

/**
 *
 * @author fenrrir
 */
public interface IAdaptationProfileRepositoryManager extends IRepositoryManager{
    public IAdaptationProfile getProfile(String name);
    public void freeProfile(String name);

}
