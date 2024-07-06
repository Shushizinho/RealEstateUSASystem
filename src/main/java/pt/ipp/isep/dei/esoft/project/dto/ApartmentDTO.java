package pt.ipp.isep.dei.esoft.project.dto;

import java.util.List;

/**
 * The type Apartment dto.
 */
public class ApartmentDTO {


    private InhabitableDTO inhabitable;


    /**
     * Instantiates a new Apartment dto.
     *
     * @param price              the price
     * @param area               the area
     * @param address            the address
     * @param distanceToCentre   the distance to centre
     * @param photographs        the photographs
     * @param bedroomNumber      the bedroom number
     * @param bathroomNumber     the bathroom number
     * @param parkingSpaceNumber the parking space number
     * @param equipmentList      the equipment list
     * @param propertyType       the property type
     * @param store              the store
     * @param client             the client
     * @param dateFormatted      the date formatted
     */
    public ApartmentDTO(double price, double area, AddressDTO address, double distanceToCentre, List<PhotographDTO> photographs,int bedroomNumber, int bathroomNumber, int parkingSpaceNumber, List<String> equipmentList,
                     PropertyTypeDTO propertyType, StoreDTO store, ClientDTO client, DateTimeDTO dateFormatted) {
        this.inhabitable= new InhabitableDTO(price, area, address, distanceToCentre, photographs, bedroomNumber, bathroomNumber, parkingSpaceNumber, equipmentList, propertyType, store, client, dateFormatted);
    }

    /**
     * Instantiates a new Apartment dto.
     *
     * @param inhabitable the inhabitable
     */
    public ApartmentDTO(InhabitableDTO inhabitable) {
        this.inhabitable = inhabitable;
    }

    /**
     * Gets inhabitable.
     *
     * @return the inhabitable
     */
    public InhabitableDTO getInhabitable() {
        return inhabitable;
    }





}
