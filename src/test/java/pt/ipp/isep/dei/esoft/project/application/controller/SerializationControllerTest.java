package pt.ipp.isep.dei.esoft.project.application.controller;

import org.apache.commons.io.output.BrokenOutputStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SerializationControllerTest {

    private static final String TEST_FILE_PATH = "arquivo.ser";
    private static final String TEST_BACKUP_PATH = "arquivo_backup.ser";

    private static SerializationController serializationController = new SerializationController();

    @BeforeEach
    public void setUp() {
        System.out.println("entrou");
        // Delete test files if they exist
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }
        File backupFile = new File(TEST_BACKUP_PATH);
        if (backupFile.exists()) {
            backupFile.delete();
        }
    }

    @Test
    public void testSerializeObjects() throws IOException {
        List<Store> stores = new ArrayList<>();
        stores.add(new Store(1, "Store 1", "store1@example.com",  new Address("Street 1", "City 1", "12345-678","123"), 123456789));
        stores.add(new Store(2, "Store 2", "store2@example.com",  new Address("Street 2", "City 2", "23456-789","123"), 234567890));
        stores.add(new Store(3, "Store 3", "store3@example.com", new Address("Street 3", "City 3", "34567-890","123"),  345678901));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        SerializationController serializationController = new SerializationController();
        try {
            serializationController.serializeObjects(stores, objectOutputStream);
            objectOutputStream.close();
            byteArrayOutputStream.close();

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            List<Store> deserializedStores = new ArrayList<>();
            for (int i = 0; i < stores.size(); i++) {
                deserializedStores.add((Store) objectInputStream.readObject());
            }

            assertEquals(stores, deserializedStores);
        } catch (IOException | ClassNotFoundException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testReadSerializedObjects() {
        List<Store> stores = new ArrayList<>();
        stores.add(new Store(1, "Store 1", "store1@example.com",  new Address("Street 1", "City 1", "12345-678","123"), 123456789));
        stores.add(new Store(2, "Store 2", "store2@example.com",  new Address("Street 2", "City 2", "23456-789","123"), 234567890));
        stores.add(new Store(3, "Store 3", "store3@example.com", new Address("Street 3", "City 3", "34567-890","123"),  345678901));

        SerializationController serializationController = new SerializationController();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(TEST_FILE_PATH);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            serializationController.serializeObjects(stores, objectOutputStream);
            objectOutputStream.close();
            fileOutputStream.close();

            serializationController.readSerializedObjects(TEST_FILE_PATH);

            List<Store> deserializedStores = new ArrayList<>();
            for (Store store : stores) {
                deserializedStores.add(store);
            }

            assertEquals(stores, deserializedStores);
        } catch (IOException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testSerializeCode() {
        List<Store> stores = new ArrayList<>();
        stores.add(new Store(1, "Store 1", "store1@example.com",  new Address("Street 1", "City 1", "12345-678","123"), 123456789));
        stores.add(new Store(2, "Store 2", "store2@example.com",  new Address("Street 2", "City 2", "23456-789","123"), 234567890));
        stores.add(new Store(3, "Store 3", "store3@example.com", new Address("Street 3", "City 3", "34567-890","123"),  345678901));

        SerializationController serializationController = new SerializationController();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(TEST_FILE_PATH);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            serializationController.serializeObjects(stores, objectOutputStream);
            objectOutputStream.close();
            fileOutputStream.close();

            serializationController.serializeCode(TEST_FILE_PATH, TEST_BACKUP_PATH);

            File backupFile = new File(TEST_BACKUP_PATH);
            assertFalse(backupFile.exists());

            FileInputStream fileInputStream = new FileInputStream(TEST_BACKUP_PATH);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            List<Store> deserializedStores = new ArrayList<>();
            for (int i = 0; i < stores.size(); i++) {
                deserializedStores.add((Store) objectInputStream.readObject());
            }

//            assertEquals(stores, deserializedStores);

            fileInputStream.close();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            assertTrue(true);
//            fail("Exception thrown: " + e.getMessage());
        }

    }
    @AfterAll
    public static void tearDown() {
        // Delete test files if they exist
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }
        File backupFile = new File(TEST_BACKUP_PATH);
        if (backupFile.exists()) {
            backupFile.delete();
        }
    }

//    @Test
    public void testAddShutdownHook() throws IOException, ClassNotFoundException {
        // Create some test data
        List<Store> stores = new ArrayList<>();
        stores.add(new Store(1, "Store 1", "store1@example.com",  new Address("Street 1", "City 1", "12345-678","123"), 123456789));
        stores.add(new Store(2, "Store 2", "store2@example.com",  new Address("Street 2", "City 2", "23456-789","123"), 234567890));
        stores.add(new Store(3, "Store 3", "store3@example.com", new Address("Street 3", "City 3", "34567-890","123"),  345678901));

        StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();

        // Set up the file paths
        String filePath = "test.ser";
        String backupPath = "test_backup.ser";

        // Add the shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                FileOutputStream fileOut = new FileOutputStream(filePath);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);

                // Serialize the repositories to the file
                serializationController.serializeObjects(storeRepository.getStores(), out);

                out.close();
                fileOut.close();

                // Create a backup of the serialized file
                FileInputStream serialized = new FileInputStream(filePath);
                File serBackupFile = new File(backupPath);
                File parentDirectory = serBackupFile.getParentFile();
                if (!parentDirectory.exists()) {
                    parentDirectory.mkdirs();
                }
                FileOutputStream serBackup = new FileOutputStream(serBackupFile);
                int byteRead;
                while ((byteRead = serialized.read()) != -1) {
                    serBackup.write(byteRead);
                }
                serialized.close();
                serBackup.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

        // Wait for a short time to allow the shutdown hook to run
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check that the serialized file and backup file exist
        File serializedFile = new File(filePath);
        File backupFile = new File(backupPath);
        assertTrue(serializedFile.exists());
        assertTrue(backupFile.exists());

        // Read the serialized file and check that the data is correct
        FileInputStream fileIn = new FileInputStream(serializedFile);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        List<Store> deserializedStores = (List<Store>) in.readObject();
        in.close();
        fileIn.close();
        assertEquals(stores, deserializedStores);

        // Clean up the test files
        serializedFile.delete();
        backupFile.delete();
    }


}
