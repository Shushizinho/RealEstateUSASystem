package pt.ipp.isep.dei.esoft.project.domain;



public interface EmailFilter {
    public void createEmail(String sender, String recipient, String subject, String message) ;
}
