package pt.ipp.isep.dei.esoft.project.service;

import org.apache.commons.math.MathException;
import org.apache.commons.math.distribution.FDistributionImpl;
import org.apache.commons.math.distribution.TDistribution;
import org.apache.commons.math.distribution.TDistributionImpl;
import org.apache.commons.math.stat.regression.OLSMultipleLinearRegression;
import pt.ipp.isep.dei.esoft.project.application.controller.GetDealsListController;
import pt.ipp.isep.dei.esoft.project.domain.PurchaseOrder;
import pt.ipp.isep.dei.esoft.project.repository.PropertyRepository;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Multiple regression analysis.
 */
public class MultipleRegressionAnalysis {
    private OLSMultipleLinearRegression multipleLinearRegression = new OLSMultipleLinearRegression();

    /**
     * The K.
     */
    double k;

    /**
     * The N.
     */
    int n;

    private final FDistributionImpl Fdistribution;

    private final TDistribution distribution;

    /**
     * The Betas.
     */
    double [] betas;

    /**
     * The Xtx.
     */
    double [][] xtx;

    /**
     * The Fa.
     */
    double Fa;
    private final List<String> tableAnova = new ArrayList<>();
    private final GetDealsListController controller = new GetDealsListController();

    /**
     * Initializes a MultipleRegressionAnalysis object and performs a multiple linear regression analysis.
     * The analysis is performed based on the given dependent variable (y) and independent variables (x).
     * The regression model parameters (betas) and the variance of the regression parameters (xtx) are calculated.
     * The degrees of freedom (k and n) for the F-distribution and t-distribution are set accordingly.
     */
    public MultipleRegressionAnalysis(){
        double[] y = getY();
        double[][] x = getX();
        multipleLinearRegression.newSampleData(y, x);
        this.k= 5;
        this.n = x.length;
        this.Fdistribution =new FDistributionImpl(k,n - (k +1));
        this.distribution  = new TDistributionImpl(n - (k +1) );
        this.betas = multipleLinearRegression.estimateRegressionParameters();
        this.xtx  = multipleLinearRegression.estimateRegressionParametersVariance();

    }

    /**
     * This method saves the value of all announcement's listed price.
     *
     * @return array with all announcement's listed price.
     */
    public double[] getY(){
        List<PurchaseOrder> p = controller.getDeals();
        List<Double> listY = new ArrayList<>();
        for (PurchaseOrder order: p) {
           listY.add(order.getAnnouncement().getListedPrice());
        }

        double[] y = new double[listY.size()];
        int i = 0;
        for (double value: listY){
            y[i] = value;
            i++;
        }
        
        return y;        
    }

    /**
     * This method saves in a matrix the value of all announcement's area, distance to center, property, number of bathrooms, number of bedrooms and number parking spaces.
     *
     * @return matrix with value of all announcement's area, distance to center, property, number of bathrooms, number of bedrooms and number parking spaces.
     */
    public double[][] getX(){
        List<PurchaseOrder> p = controller.getDeals();
        List<List<Double>> listX = new ArrayList<>();
        List<Double> linhas = new ArrayList<>();
        int j = 0;

            for (PurchaseOrder order : p) {
                linhas = new ArrayList<>();
                linhas.add(order.getAnnouncement().getProperty().getArea());
                linhas.add(order.getAnnouncement().getProperty().getDistanceToCentre());
                if (PropertyRepository.getInhabitableByProperty(order.getAnnouncement().getProperty()) != null)
                    linhas.add((double) Objects.requireNonNull(PropertyRepository.getInhabitableByProperty(order.getAnnouncement().getProperty())).getBedroomNumber());
                if (PropertyRepository.getInhabitableByProperty(order.getAnnouncement().getProperty()) != null) {
                    linhas.add((double) Objects.requireNonNull(PropertyRepository.getInhabitableByProperty(order.getAnnouncement().getProperty())).getBathroomNumber());
                }
                if (PropertyRepository.getInhabitableByProperty(order.getAnnouncement().getProperty()) != null) {
                    linhas.add((double) Objects.requireNonNull(PropertyRepository.getInhabitableByProperty(order.getAnnouncement().getProperty())).getParkingSpaceNumber());
                }
                listX.add(linhas);


        }

        double[][] x = new double[listX.size()][listX.get(0).size()];

        for (int l = 0; l < listX.size(); l++) {
            for (int i = 0; i < listX.get(0).size() ; i++){
                x[l][i] = listX.get(l).get(i);
            }
        }

        return x;
    }

    /**
     * Formats a floating-point number with four decimal places as a string.
     *
     * @param flota The floating-point number to be formatted.
     * @return The formatted string representation of the number.
     */
    public String fourDecimalCase(Double flota){
        DecimalFormat decimalFormat = new DecimalFormat("#.0000");
        if(flota<0 && flota > -1){
            return decimalFormat.format(flota).replace("-", "-0");

        } else if( flota > 0 && flota <1){
            return "0" + decimalFormat.format(flota);
        }
        return decimalFormat.format(flota);
    }

    /**
     * Returns the regression equation for least squares.
     *
     * @return The regression equation in the form: y = <beta0> + <beta1>X1 + <beta2>X2 + <beta3>X3 + <beta4>X4 + <beta5>X5,         where <beta0>, <beta1>, <beta2>, <beta3>, <beta4>, and <beta5> are formatted with four decimal places.
     */
    public String getEquation(){
        return ("The regression equation for least squares: \ny=" + fourDecimalCase(betas[0]) + " + " +  fourDecimalCase(betas[1]) + "X1" + " + " +  fourDecimalCase(betas[2]) + "X2" + " + \n" +  fourDecimalCase(betas[3])
                + "X3" + " + " +  fourDecimalCase(betas[4]) + "X4" + " + " + fourDecimalCase(betas[5]) + "X5");
    }

    /**
     * Calculates the predicted price based on the given input variables.
     *
     * @param area             The area value.
     * @param distanceToCenter The distance to the center value.
     * @param bedroomNumber    The number of bedrooms value.
     * @param bathroomNumber   The number of bathrooms value.
     * @param parkingSpace     The number of parking spaces value.
     * @return The price prediction for the given input variables, formatted with four decimal places.
     */
    public String getPricePrediceted(double area, double distanceToCenter, double bedroomNumber, double bathroomNumber,
                                     double parkingSpace){

        double prediction = ((betas[0]) + ((betas[1]*area) + (betas[2]*distanceToCenter) + (betas[3]*bedroomNumber)
                + (betas[4]*bathroomNumber) + (betas[5]*parkingSpace)));


        return ("The price prediction for area = " + area + " \n, distance to center = " + distanceToCenter +
                ", number of bedrooms = " + bedroomNumber + "\n , number of bathrooms = " + bathroomNumber
                + " , parking spaces =" + parkingSpace + "\n is " + fourDecimalCase(prediction) + "$.");

    }

    /**
     * Returns the determination coefficient (R^2) of the multiple linear regression model.
     *
     * @return The determination coefficient, formatted with four decimal places.
     */
    public String getR2(){
        return ("\nDetermination coefficient = " + fourDecimalCase( multipleLinearRegression.calculateRSquared()) );
    }

    /**
     * Returns the adjusted determination coefficient (R^2 adjusted) of the multiple linear regression model.
     *
     * @return The adjusted determination coefficient, formatted with four decimal places.
     */
    public String getR2Adjusted(){
        return  ("\nDetermination coefficient adjusted = " + fourDecimalCase(multipleLinearRegression.calculateAdjustedRSquared() ));
    }

    /**
     * Returns the critical value for the F-distribution with the specified significance level (alfa).
     *
     * @param alfa The significance level.
     * @return The critical value for the F-distribution, formatted with four decimal places.
     */
    public double getFa (double alfa){
        try {
            this.Fa  = Fdistribution.inverseCumulativeProbability(1.0 -alfa);
        } catch (MathException e) {
            throw new RuntimeException(e);
        }
        return this.Fa;
    }

    /**
     * Returns the critical value for the t-distribution with the specified significance level (alfa).
     *
     * @param alfa The significance level.
     * @return The critical value for the t-distribution, formatted with four decimal places.
     */
    public double getTc (double alfa){
        try {
            this.Fa  = distribution.inverseCumulativeProbability(1.0 - (alfa/2.0));
        } catch (MathException e) {
            throw new RuntimeException(e);
        }
        return this.Fa;
    }

    /**
     * Returns a message indicating the result of the ANOVA table justification.
     *
     * @param IC The confidence interval.
     * @return The message explaining the ANOVA table justification.
     */
    public String getTabelaAnovaJustifiacao(double IC){
        double SQE = multipleLinearRegression.calculateResidualSumOfSquares();
        double SQT = multipleLinearRegression.calculateTotalSumOfSquares();
        double SQR = SQT - SQE;
        double MQR = SQR/k;
        double glE = n - (k - 1);
        double MQE = SQE/glE;
        double f0 = MQR/MQE;

        double alfa = (100- IC)/100;
        String symbol = "\u2260";
        String mesage = "H0 : β1 = β2 = β3 = β4 = β5 = 0 v.s H1: β1 = β2 = β3 = β4 = β5 "+ symbol +" 0";
        if (f0 > getFa(alfa)){
            mesage = mesage +  "\nThe hypothesis H0 is rejected, which allows us to conclude that there is \n at least one independent variable that contributes significantly to explaining" +
                    " \nthe variation of the dependent variable. \nThe linear regression model presented significant" ;
        }else {
            mesage =  mesage + "\nThe hypothesis H0 isn't rejected, which allows us to conclude \n that there is a bad adjustment " +
                    " in the presented linear regression model. \nThe linear regression model presented isn't significant";
        }
        return mesage;
   }

    /**
     * Returns the critical value for the F-distribution as a formatted string with four decimal places.
     *
     * @param IC The confidence interval.
     * @return The critical value for the F-distribution as a formatted string.
     */
    public String getFaToString(double IC){
        double SQE = multipleLinearRegression.calculateResidualSumOfSquares();
        double SQT = multipleLinearRegression.calculateTotalSumOfSquares();
        double SQR = SQT - SQE;
        double MQR = SQR/k;
        double glE = n - (k - 1);
        double MQE = SQE/glE;
        double F = MQR/MQE;


       double alfa = (100- IC)/100;

       return fourDecimalCase(getFa(alfa));
   }


    /**
     * Returns the ANOVA table as a list of formatted strings.
     *
     * @return The ANOVA table.
     */
    public List<String> getTableAnova(){
        double SQE = multipleLinearRegression.calculateResidualSumOfSquares();
        double SQT = multipleLinearRegression.calculateTotalSumOfSquares();
        double SQR = SQT - SQE;
        tableAnova.add(fourDecimalCase(SQR));
        tableAnova.add(fourDecimalCase(SQE));
        tableAnova.add(fourDecimalCase(SQT));
        double glR = k;
        tableAnova.add(fourDecimalCase(glR));
        double glE = n - (k - 1);
        tableAnova.add(fourDecimalCase(glE));
        double glTotal = n -1;
        tableAnova.add(fourDecimalCase(glTotal));
        double MQR = SQR/k;
        tableAnova.add(fourDecimalCase(MQR));
        double MQE = SQE/glE;
        tableAnova.add(fourDecimalCase(MQE));
        double F = MQR/MQE;
        tableAnova.add(fourDecimalCase(F));

        return tableAnova;
    }

    /**
     * Calculates the confidence interval for the specified beta value.
     *
     * @param IC   The confidence interval.
     * @param beta The beta value.
     * @return The confidence interval for the beta value as a formatted string.
     */
    public String calcularIC (double IC , int beta){

       double alfa = (100- IC)/100;

       double tc = getTc(alfa);

        double SQE = multipleLinearRegression.calculateResidualSumOfSquares();
        double s2 = SQE / (n - (k + 1));

        double befaConfidance = tc * Math.sqrt((s2 * xtx[beta][beta]));

        double interval1 = beta - befaConfidance;

        double interval2 = beta + befaConfidance;

        return ("\nConfidance Interval for β"+ beta + " = [" + fourDecimalCase(interval1) + ", "
                + fourDecimalCase(interval2) + "]");

    }

    /**
     * Performs a hypothesis test for the specified beta value.
     *
     * @param IC   The confidence interval.
     * @param beta The beta value.
     * @return The result of the hypothesis test as a message.
     */
    public String testeHipotese (double IC , int beta){
        double alfa = (100- IC)/100;

        double tc = getTc(alfa);

        double x0 =(betas[beta])/( Math.sqrt( multipleLinearRegression.estimateErrorVariance() * xtx[beta][beta] ));
        String symbol = "\u2260";
        String mesage = "H0 : β"+ beta + " = 0 v.s H1: β"+ beta +" "+ symbol +" 0";
        if (x0 > tc){
            mesage = mesage + " \nWe reject H0 because " + fourDecimalCase(x0) + " > " + fourDecimalCase(tc);
        }else {
            mesage =  mesage + " \nWe reject H1 because " + fourDecimalCase(x0) + " < " + fourDecimalCase(tc);
        }

        return mesage;
    }
}
