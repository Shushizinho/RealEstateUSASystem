package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImportLegacySystemControllerTest {

    private ImportLegacySystemController importLegacySystemController;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        importLegacySystemController = new ImportLegacySystemController();
    }


    @Test
    @DisplayName("Should return false when the file has invalid headers")
    void importFileWhenInvalidHeaders() {
        File file = new File("\\src\\main\\resources\\legacyRealStateTestFailing.csv");
        boolean result = importLegacySystemController.importFile(file);
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return false when the file has invalid property data")
    void importFileWhenInvalidPropertyData() {
        File file = new File("\\src\\main\\resources\\legacyRealStateTestFailing.csv");
        boolean result = importLegacySystemController.importFile(file);
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return false when the file has invalid client data")
    void importFileWhenInvalidClientData() {
        File file = new File("\\src\\main\\resources\\legacyRealStateTestFailing.csv");
        boolean result = importLegacySystemController.importFile(file);
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return false when the file has invalid store data")
    void importFileWhenInvalidStoreData() {
        File file = new File("\\src\\main\\resources\\testesFiles\\legacyRealStateTestFailing.csv");
        boolean result = importLegacySystemController.importFile(file);
        assertFalse(result);
    }

//    @Test
    @DisplayName("Should import the file successfully when all data is valid")
    void importFileWhenAllDataIsValid() {
        File file = new File("/test/resources/testesFiles/legacyRealStateTestFailing.csv");
        boolean result = importLegacySystemController.importFile(file);
        assertTrue(result);
    }

}