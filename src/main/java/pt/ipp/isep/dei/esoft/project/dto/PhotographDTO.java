package pt.ipp.isep.dei.esoft.project.dto;

/**
 * The type Photograph dto.
 */
public class PhotographDTO  {

    private String uri;

    /**
     * Instantiates a new Photograph dto.
     */
    public PhotographDTO() {
        this.uri = "default.png";
    }

    /**
     * Instantiates a new Photograph dto.
     *
     * @param uri the uri
     */
    public PhotographDTO(String uri) {
        this.uri = uri;
    }

    /**
     * Gets uri.
     *
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * Sets uri.
     *
     * @param uri the uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return uri;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PhotographDTO)) {
            return false;
        }
        return uri.equals(((PhotographDTO) o).getUri());
    }
}
