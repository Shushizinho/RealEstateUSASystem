package pt.ipp.isep.dei.esoft.project.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonDTOTest {

    @Test
    @DisplayName("Should get and set name correctly")
    void getAndSetName() {
        PersonDTO person = new PersonDTO("john.doe@example.com", "John Doe", 123456, 1234567890, "123456789", new AddressDTO("123 Main St", "Anytown", "CA", "12345"));

        String expectedName = "Jane Doe";
        person.setName(expectedName);

        String actualName = person.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    @DisplayName("Should get and set email correctly")
    void getAndSetEmail() {
        String email = "john.doe@example.com";
        PersonDTO person = new PersonDTO(email, "John Doe", 123456, 1234567890, "123456789", new AddressDTO("123 Main St", "Anytown", "CA", "12345"));

        assertEquals(email, person.getEmail());

        String newEmail = "jane.doe@example.com";
        person.setEmail(newEmail);

        assertEquals(newEmail, person.getEmail());
    }

    @Test
    @DisplayName("Should get and set passportNumber correctly")
    void getAndSetPassportNumber() {// Create a new PersonDTO object
        AddressDTO address = new AddressDTO("123 Main St", "Anytown", "CA", "12345");
        PersonDTO person = new PersonDTO("johndoe@example.com", "John Doe", 123456789, 1234567890, "123-45-6789", address);

        // Test getPassportNumber method
        assertEquals(123456789, person.getPassportNumber());

        // Test setPassportNumber method
        person.setPassportNumber(987654321);
        assertEquals(987654321, person.getPassportNumber());
    }

    @Test
    @DisplayName("Should create a PersonDTO with the given parameters")
    void createPersonDTOWithParameters() {
        String email = "john.doe@example.com";
        String name = "John Doe";
        Integer passportNumber = 123456789;
        long phoneNumber = 1234567890;
        String taxNumber = "123456789";
        AddressDTO address = new AddressDTO("123 Main St", "Anytown", "CA", "12345");

        PersonDTO personDTO = new PersonDTO(email, name, passportNumber, phoneNumber, taxNumber, address);

        assertEquals(email, personDTO.getEmail());
        assertEquals(name, personDTO.getName());
        assertEquals(passportNumber, personDTO.getPassportNumber());
        assertEquals(phoneNumber, personDTO.getPhoneNumber());
        assertEquals(taxNumber, personDTO.getTaxNumber());
        assertEquals(address, personDTO.getAddress());
    }

    @Test
    @DisplayName("Should get and set phoneNumber correctly")
    void getAndSetPhoneNumber() {// Create a mock AddressDTO object
        AddressDTO address = new AddressDTO("123 Main St", "Anytown", "CA", "12345");

        // Create a PersonDTO object with mock AddressDTO
        PersonDTO person = new PersonDTO("test@example.com", "John Doe", 123456, 1234567890, "ABC123", address);

        // Test getPhoneNumber method
        assertEquals(1234567890, person.getPhoneNumber());

        // Test setPhoneNumber method
        person.setPhoneNumber(9876543210L);
        assertEquals(9876543210L, person.getPhoneNumber());
    }

}