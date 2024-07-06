package pt.ipp.isep.dei.esoft.project.application.session;

import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The type Application session.
 */
public class ApplicationSession {
    private AuthenticationRepository authenticationRepository=null;
    private static final String CONFIGURATION_FILENAME = "config.properties";
    private static final String COMPANY_DESIGNATION = "Company.Designation";
    private static final String SYSTEM_BACKUP = "System.Backup";

    private static final String EMAIL_SYSTEM = "filter.term.gmail";

    /**
     * Instantiates a new Application session.
     */
    public ApplicationSession() {
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        Properties props = getProperties();
    }

    /**
     * Get current session user session.
     *
     * @return the user session
     */
    public UserSession getCurrentSession(){
        pt.isep.lei.esoft.auth.UserSession userSession = this.authenticationRepository.getCurrentUserSession();
        return new UserSession(userSession);
    }

    /**
     * Gets system backup.
     *
     * @return the system backup
     */
    public static String getSystemBackup() {
        return SYSTEM_BACKUP;
    }

    /**
     * Gets email system.
     *
     * @return the email system
     */
    public static String getEmailSystem() {
        return EMAIL_SYSTEM;
    }

    /**
     * Gets properties.
     *
     * @return the properties
     */
    public Properties getProperties()
    {
        Properties props = new Properties();

        // Add default properties and values
        props.setProperty(COMPANY_DESIGNATION, "Real Estate USA");
        props.setProperty(SYSTEM_BACKUP, "true");
        props.setProperty(EMAIL_SYSTEM, "\"@gmail.com\"");


        // Read configured values
        try
        {
            InputStream in = new FileInputStream(CONFIGURATION_FILENAME);
            props.load(in);
            in.close();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        return props;
    }

    // Extracted from https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static ApplicationSession singleton = null;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ApplicationSession getInstance()
    {
        if(singleton == null)
        {
            synchronized(ApplicationSession.class)
            {
                singleton = new ApplicationSession();
            }
        }
        return singleton;
    }


}
