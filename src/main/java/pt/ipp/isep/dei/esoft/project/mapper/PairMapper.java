package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Pair;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.dto.AddressDTO;
import pt.ipp.isep.dei.esoft.project.dto.PairDTO;
import pt.ipp.isep.dei.esoft.project.dto.StoreDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Pair mapper.
 */
public class PairMapper {
    /**
     * To dto pair dto.
     *
     * @param pair the pair
     * @return the pair dto
     */
    public static PairDTO toDTO(Pair pair) {

        return new PairDTO(pair.getLeft(), pair.getRight());
    }


    /**
     * To entity pair.
     *
     * @param pair the pair
     * @return the pair
     */
    public static Pair toEntity(PairDTO pair) {
        return new Pair(pair.getLeft(), pair.getRight());
    }

}
