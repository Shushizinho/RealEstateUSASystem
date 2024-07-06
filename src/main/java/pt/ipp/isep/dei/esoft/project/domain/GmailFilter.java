package pt.ipp.isep.dei.esoft.project.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The type Gmail filter.
 */
public class GmailFilter implements EmailFilter {

    private String filterTerm;

    /**
     * Instantiates a new Gmail filter.
     *
     * @param filter the filter
     */
    public GmailFilter(String filter) {
        this.filterTerm = filter;
    }

    @Override
    public void createEmail(String sender, String recipient, String subject, String message) {

//        ApplicationSession appSession = ApplicationSession.getInstance();
//        Properties props = appSession.getProperties();
//        if (props.getProperty(ApplicationSession.getEmailSystem()).compareTo("\"@gmail.com\"") == 0) {

            String fileName = "Gmail_Service.txt";
            String fileContent = "From: " + sender + "\n" + "Recipient: " + recipient + "\n" + "Subject: " + subject + "\n\n" + message;

            String directory = "src\\main\\resources\\passwords\\";

            try {
                File file = new File(directory, fileName);

                FileWriter writer;
                if (file.exists()) {
                    writer = new FileWriter(file, true);
                } else {
                    writer = new FileWriter(file);
                }

                writer.append("\n\n");
                writer.write(fileContent);
                writer.close();
                System.out.println("=====================");
                System.out.println("E-mail was sent successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred while creating the e-mail.");
                e.printStackTrace();
            }
//        }
    }

}
