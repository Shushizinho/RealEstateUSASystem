package pt.ipp.isep.dei.esoft.project.domain;

/**
 * The type Table view data.
 */
public class TableViewData {

    private String variation;
    private String sum;
    private String degrees;
    private String mean;
    private String statistics;

    public TableViewData(String variation, String sum, String degrees, String mean, String statistics) {
        this.variation = variation;
        this.sum = sum;
        this.degrees = degrees;
        this.mean = mean;
        this.statistics = statistics;
    }

    public String getVariation() {
        return variation;
    }

    public void setVariation(String variation) {
        this.variation = variation;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getDegrees() {
        return degrees;
    }

    public void setDegrees(String degrees) {
        this.degrees = degrees;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getStatistics() {
        return statistics;
    }

    public void setStatistics(String statistics) {
        this.statistics = statistics;
    }
}
