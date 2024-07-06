package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.Properties;


/**
 * The SerializationController class manages ...
 */
public class SerializationController {


    /**
     * Serialize code.
     *
     * @param caminho the caminho
     * @param backup  the backup
     */
    public void serializeCode(String caminho, String backup){


        ClientRepository clientRepository = Repositories.getInstance().getClientRepository();
        StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();
        EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();
        PropertyRepository propertyRepository = Repositories.getInstance().getPropertyRepository();
        AnnouncementRepository announcementRepository = Repositories.getInstance().getAnnouncementRepository();
        AnnouncementRequestRepository announcementRequestRepository = Repositories.getInstance().getAnnouncementRequestRepository();
        PurchaseOrderRepository purchaseOrderRepository = Repositories.getInstance().getPurchaseOrderRepository();
        VisitRequestRepository visitRequestRepository = Repositories.getInstance().getVisitRequestRepository();



        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            System.out.println("\u001B[34mSaving the information...\u001B[0m");

            try {
                FileOutputStream fileOut = new FileOutputStream(caminho);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);

                serializeObjects(storeRepository.getStores(),out);
                serializeObjects(employeeRepository.getEmployees(),out);
                serializeObjects(clientRepository.getClients(),out);
                serializeObjects(propertyRepository.getLands(),out);
                serializeObjects(propertyRepository.getHouses(),out);
                serializeObjects(propertyRepository.getApartments(),out);
                serializeObjects(propertyRepository.getInhabitableProperties(),out);
                serializeObjects(propertyRepository.getProperties(),out);
                serializeObjects(announcementRequestRepository.getAnnouncementRequests(),out);
//                serializeObjects(announcementRepository.getSaleAnnouncements(),out);
//                serializeObjects(announcementRepository.getRentAnnouncements(),out);
                serializeObjects(announcementRepository.getAnnouncements(),out);
                serializeObjects(purchaseOrderRepository.getAllOrders(),out);
                serializeObjects(visitRequestRepository.getVisitRequests(), out);

                out.close();
                fileOut.close();
                System.out.println();
                System.out.println("\u001B[32mInformation successfully saved.\u001B[0m");


                ApplicationSession appSession = ApplicationSession.getInstance();
                Properties props = appSession.getProperties();
                String sysBak = props.getProperty(ApplicationSession.getSystemBackup());
                if (sysBak.compareTo("true") == 0) {

                    FileInputStream serialized = new FileInputStream(caminho);
                    File serBackupFile = new File(backup);
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
                    System.out.println("\u001B[32mBackup successfully created.\u001B[0m");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

            readSerializedObjects(caminho);



    }

    /**
     * Serialize objects.
     *
     * @param <T>     the type parameter
     * @param objects the objects
     * @param out     the out
     * @throws IOException the io exception
     */
    public <T> void serializeObjects(List<T> objects, ObjectOutputStream out) throws IOException {
        for (T obj : objects) {
            out.writeObject(obj);
        }
    }

    /**
     * Read serialized objects.
     *
     * @param caminhoArquivo the caminho arquivo
     */
    public static void readSerializedObjects(String caminhoArquivo) {
        try {
            FileInputStream fileIn = new FileInputStream(caminhoArquivo);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            while (true) {
                try {
                    Object object1 = in.readObject();

                    addInRepository(object1);

//                    System.out.println("Objeto lido: \n" + object1.toString());
                } catch (EOFException e) {
                    break;
                }
            }

            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addInRepository(Object object) {

        Repositories repositories = Repositories.getInstance();
//        System.out.println(object.getClass());
        if (object instanceof Store) {
            repositories.getStoreRepository().add((Store) object);
        } else if (object instanceof Employee) {
            repositories.getEmployeeRepository().add((Employee) object);
        } else if (object instanceof Client) {
            repositories.getClientRepository().add((Client) object);
        } else if (object instanceof Property) {
            repositories.getPropertyRepository().add((Property) object);
        } else if (object instanceof House) {
            repositories.getPropertyRepository().add((House) object);
        }  else if (object instanceof Apartment) {
            repositories.getPropertyRepository().add((Apartment) object);
        }  else if (object instanceof Land) {
            repositories.getPropertyRepository().add((Land) object);
//        } else if (object instanceof SaleAnnouncement) {
//            repositories.getAnnouncementRepository().add((SaleAnnouncement) object);
//        } else if (object instanceof RentAnnouncement) {
//            repositories.getAnnouncementRepository().add((RentAnnouncement) object);
        }  else if (object instanceof Announcement) {
            repositories.getAnnouncementRepository().add((Announcement) object);
        }else if (object instanceof AnnouncementRequest) {
            repositories.getAnnouncementRequestRepository().add((AnnouncementRequest) object);
        }else if (object instanceof PurchaseOrder) {
            repositories.getPurchaseOrderRepository().addOrder((PurchaseOrder) object);
        }else if (object instanceof Inhabitable) {
            //do nothing
        }else if (object instanceof VisitRequest) {
            repositories.getVisitRequestRepository().add(((VisitRequest) object).toDTO());
        }else {
            System.out.println("obj é de um tipo desconhecido - " + object.getClass());
        }
    }


}
