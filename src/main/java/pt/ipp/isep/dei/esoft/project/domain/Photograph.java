package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Calendar;

public class Photograph implements Serializable {

    private final String uri;



    public Photograph() {
        this.uri = "default.png";
    }



    public Photograph(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    /**
     * This method displays the photo's uri.
     * @return String
     */
    @Override
    public String toString() {
        return getUri();
    }


    /**
     * This method cheks is the given object is equal to this Date object.
      * @param o
     * @return boolean
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Photograph)) {
            return false;
        }
        return ( getUri().equals(((Photograph) o).getUri()));
    }


}
