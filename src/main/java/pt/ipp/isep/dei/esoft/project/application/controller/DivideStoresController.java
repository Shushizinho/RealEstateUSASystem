package pt.ipp.isep.dei.esoft.project.application.controller;

import org.apache.commons.lang3.NotImplementedException;
import pt.ipp.isep.dei.esoft.project.domain.AnnouncementRequest;
import pt.ipp.isep.dei.esoft.project.domain.Pair;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.dto.AnnouncementRequestDTO;
import pt.ipp.isep.dei.esoft.project.dto.StoreDTO;
import pt.ipp.isep.dei.esoft.project.mapper.AnnouncementRequestMapper;
import pt.ipp.isep.dei.esoft.project.mapper.StoreMapper;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.service.DivideStoresAlgorithm;

import java.util.List;

/**
 * The type Divide stores controller.
 */
public class DivideStoresController {
    private DivideStoresAlgorithm bruteForce;
    private StoreRepository storeRepository = null;

    /**
     * Instantiates a new Divide stores controller.
     */
    public DivideStoresController() {
        getStoreRepository();
    }

    private StoreRepository getStoreRepository() {
        if (storeRepository== null) {
            Repositories repositories = Repositories.getInstance();

            storeRepository = repositories.getStoreRepository();
        }
        return storeRepository;
    }

    /**
     * Gets store i ds list.
     *
     * @param selectedStoresList the selected stores list
     * @return the store i ds list
     */
    public List<Integer> getStoreIDsList(List<StoreDTO> selectedStoresList) {
        return getStoreRepository().getStoreIDsList(selectedStoresList);
    }

    /**
     * Get store list.
     *
     * @return the list
     */
    public  List<Store> getStore(){
        return getStoreRepository().getStore();
    }

    /**
     * Gets property number in each.
     *
     * @param selectedStoresList the selected stores list
     * @return the property number in each
     */
    public List<Integer> getPropertyNumberInEach(List<StoreDTO> selectedStoresList) {
        return getStoreRepository().getPropertyNumberInEach(selectedStoresList);
//        throw new NotImplementedException("");
    }

    /**
     * Divide stores pair.
     *
     * @param selectedStoresList   the selected stores list
     * @param numberPropertiesList the number properties list
     * @return the pair
     */
    public Pair<List<Pair<Integer, Integer>>, List<Pair<Integer, Integer>>> divideStoresConsole(List<StoreDTO> selectedStoresList, List<Integer> numberPropertiesList){
        Integer[] selectedStoresIDArray = getStoreIDsList(selectedStoresList).toArray(new Integer[selectedStoresList.size()]);
        Integer[] numberPropertiesArray = numberPropertiesList.toArray(new Integer[numberPropertiesList.size()]);
        return bruteForce.divideStoresConsole(selectedStoresIDArray, numberPropertiesArray);
    }

    /**
     * Divide stores string.
     *
     * @param selectedStoresList   the selected stores list
     * @param numberPropertiesList the number properties list
     * @return the string
     */
    public String divideStores(List<StoreDTO> selectedStoresList, List<Integer> numberPropertiesList){
        Integer[] selectedStoresIDArray = getStoreIDsList(selectedStoresList).toArray(new Integer[selectedStoresList.size()]);
        Integer[] numberPropertiesArray = numberPropertiesList.toArray(new Integer[numberPropertiesList.size()]);
        return bruteForce.divideStores(selectedStoresIDArray, numberPropertiesArray);
    }

    /**
     * Get stores list.
     *
     * @return the list
     */
    public List<StoreDTO> getStores(){

        List <Store> stores= getStore();

        return StoreMapper.getStoresDTO(stores);

    }




}
