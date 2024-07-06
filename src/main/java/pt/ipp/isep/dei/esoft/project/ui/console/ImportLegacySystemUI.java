package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ImportLegacySystemController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Create Task UI (console). This option is only available for administrators for demonstration purposes.
 */
public class ImportLegacySystemUI implements Runnable {

    private final ImportLegacySystemController controller = new ImportLegacySystemController();

    /**
     * Instantiates a new Import legacy system ui.
     *
     * @throws FileNotFoundException the file not found exception
     */
    public ImportLegacySystemUI() throws FileNotFoundException {
    }

    private ImportLegacySystemController getController() {
        return controller;
    }

    public void run() {

        System.out.println("\u001B[36m#=======Choose the file to import into the system=======#\u001B[0m");

        requestFile();
    }

    private void requestFile() {
        //display the task categories as a menu with number options to select
        String folderPath = "src\\main\\resources\\";; // The folder path
        File folder = new File(folderPath); // Create a new File object with the folder path
        File[] files = folder.listFiles();
        int answer=0;
        File[] filecsv = displayFiles(files);

        System.out.println("\n\u001B[0m0. Cancel\u001B[0m");

        if (filecsv != null){
            if (filecsv.length>0){
                while(answer < 1 || answer > filecsv.length){
                    Scanner input = new Scanner(System.in);
                    System.out.print("\u001B[35m\nSelect a file: \u001B[0m");

                    try{
                        answer = input.nextInt();

                        if (answer == 0) break;

                    }
                    catch (Exception e){
                        System.out.println("\u001B[31mMust be an Integer cannot be a String\u001B[0m");
                    }
                }

                if(answer!=0){

                    File file = filecsv[answer-1];
                    System.out.println("\u001B[34m... \u001B[0m");

                    boolean bool = controller.importFile(file);
                    if(bool){
                        System.out.println("\u001B[32mSuccessfully imported \u001B[0m");
                    }
                    else{
                        System.out.println("\u001B[31mThe file got a error, you can not import this file - " + file.getName() + "\u001B[0m");
                    }

                }

            }
        }
    }

    private File[] displayFiles(File[] files) {
         // Get an array of File objects representing the files in the folder
        int i=1,answer=0;
        if (files != null) {
            File[] filecsv = filecsv = new File[files.length];
            for (File file : files) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".csv")) {
                    System.out.println("\u001B[0m" + i +". " + file.getName() + "\u001B[0m" );
                    filecsv[i-1] = file;
                    i++;
                }
            }
            return filecsv;

        }

        if(files == null || i==1) {
            System.out.println("\u001B[31mDoes not existe any CSV file or is not in the directory 'resources'.\u001B[0m");

        }
        return null;
    }


}
