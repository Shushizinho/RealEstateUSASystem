package pt.ipp.isep.dei.esoft.project.service;

import org.apache.commons.math.MathException;
import org.apache.commons.math.distribution.TDistribution;
import org.apache.commons.math.distribution.TDistributionImpl;
import org.apache.commons.math.stat.regression.SimpleRegression;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Regression analysis.
 */
public class RegressionAnalysis  {

    private TDistribution distribution;

    private SimpleRegression simpleRegression;

    private double tc;

    private double n;

    private List<String> tableAnova = new ArrayList<>();


    /**
     * Initializes a RegressionAnalysis object with the provided SimpleRegression instance.
     * The degrees of freedom (n) for the t-distribution are set based on the number of data points in the SimpleRegression.
     *
     * @param s The SimpleRegression instance containing the regression analysis results.
     */
    public RegressionAnalysis(SimpleRegression s){
        this.simpleRegression = s;
        this.distribution = new TDistributionImpl((s.getN() -2) );
        n = s.getN();
    }

    /**
     * Calculates the critical value (tc) for the t-distribution with the given significance level (alfa).
     *
     * @param alfa The significance level.
     * @return The critical value for the t-distribution.
     */
    public double getTc (double alfa){
        try {
             this.tc  =distribution.inverseCumulativeProbability(1.0 - (alfa/2.0) );
        } catch (MathException e) {
            throw new RuntimeException(e);
        }
        return this.tc;
    }

    /**
     * Calculates the confidence intervals for the regression parameters (a and b) based on the given confidence level (IC).
     *
     * @param IC The confidence level.
     * @return The confidence intervals for the regression parameters (a and b).
     */
    public String calcularIC (double IC){
        List<Double> intervaloConfiança = new ArrayList<>();
        double alfa = (100- IC)/100;

        double funcion = simpleRegression.getInterceptStdErr();
        double f;
        try {
           f = simpleRegression.getSlopeConfidenceInterval(alfa);
        } catch (MathException e) {
            throw new RuntimeException(e);
        }

       return ("Confidance Interval for a = [" + fourDecimalCase((getA() - (getTc(alfa)* funcion)) )+ ", "
                +fourDecimalCase ((getA() + (getTc(alfa)* funcion)) )+ "]" + "\nConfidance Interval for b = ["+
               fourDecimalCase( (getB() - f) )+ ", " + fourDecimalCase(getB() + f) + "]");

    }

    /**
     * Retrieves the slope (b) of the regression line.
     *
     * @return The slope of the regression line.
     */
    public double getB(){
         return simpleRegression.getSlope();
    }

    /**
     * Retrieves the intercept (a) of the regression line.
     *
     * @return The intercept of the regression line.
     */
    public double getA(){
        return simpleRegression.getIntercept();
    }

    /**
     * Retrieves the intercept (a) of the regression line as a string formatted with four decimal places.
     *
     * @return The intercept of the regression line as a formatted string.
     */
    public String getAString(){
        return fourDecimalCase(getA());
    }

    /**
     * Retrieves the slope (b) of the regression line as a string formatted with four decimal places.
     *
     * @return The slope of the regression line as a formatted string.
     */
    public String getBString(){
        return fourDecimalCase(getB());
    }

    /**
     * Predicts the price based on the regression line for the given value.
     *
     * @param value The value for which the price prediction is calculated.
     * @return The predicted price.
     */
    public String getPricePrediceted(double value){
        return ("The price prediction is " + fourDecimalCase(simpleRegression.predict(value)) + "$.");
    }

    /**
     * Formats a Double value to a string representation with four decimal places.
     *
     * @param flota The Double value to be formatted.
     * @return The formatted string representation of the value.
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
     * Retrieves the equation of the regression line in the form "y = a + bx".
     *
     * @return The equation of the regression line.
     */
    public String getEquation(){
        return ("y ="+ fourDecimalCase(getA()) + "+" + fourDecimalCase(getB()) + "x");
    }

    /**
     * Retrieves the correlation coefficient (R) of the regression analysis.
     *
     * @return The correlation coefficient.
     */
    public String getR(){
        return ("Correlation coefficient = " +fourDecimalCase(simpleRegression.getR()));
    }

    /**
     * Retrieves the determination coefficient (R^2) of the regression analysis.
     *
     * @return The determination coefficient.
     */
    public String getR2(){
        return ("Determination coefficient = " + fourDecimalCase(simpleRegression.getRSquare()));
    }

    /**
     * Performs a hypothesis test for the regression parameters (a and b) with the given null hypotheses (a0 and b0).
     * Calculates the t-values for the null hypotheses and determines whether to reject or accept them.
     *
     * @param a0 The null hypothesis for the intercept (a).
     * @param b0 The null hypothesis for the slope (b).
     * @return The results of the hypothesis test.
     */
    public String testeHipotese( double a0, double b0){
        double Ta = (getA() - a0)/simpleRegression.getInterceptStdErr();
        double tb = (getB() - b0)/simpleRegression.getInterceptStdErr();
        String finalMesage;
        String symbol = "\u2260";
        String mesageA = "H0 : a = " + a0 + " v.s H1: a "+ symbol +" "+ a0;
        if (Ta > this.tc){
            mesageA = mesageA +  "\nThe hypothesis H0 is rejected, because " + fourDecimalCase(Ta) + " > "+ fourDecimalCase(this.tc);
        }else {
            mesageA =  mesageA + "\nThe hypothesis H1 is rejected, because "+ fourDecimalCase(Ta) + " < "+ fourDecimalCase(this.tc);
        }
        String mesageB = "\nH0 : b = " + b0 + " v.s H1: b "+ symbol +" " + b0;
        if (tb > this.tc){
            mesageB  = mesageB + "\nThe hypothesis H0 is rejected, because " + fourDecimalCase(tb) + " > "+ fourDecimalCase(this.tc) ;
        }else {
            mesageB = mesageB + "\nThe hypothesis H1 is rejected, because "  + fourDecimalCase(tb) + " < "+ fourDecimalCase(this.tc) ;
        }
        finalMesage = mesageA + mesageB;
        return finalMesage;
    }

    /**
     * Retrieves the ANOVA table for the regression analysis.
     *
     * @return The ANOVA table as a list of strings.
     */
    public List<String> getTableAnova(){

        double SR = simpleRegression.getRegressionSumSquares();
        tableAnova.add(fourDecimalCase(SR));
        double SE = simpleRegression.getSumSquaredErrors();
        tableAnova.add(fourDecimalCase(SE));
        double ST = simpleRegression.getTotalSumSquares();
        tableAnova.add(fourDecimalCase(ST));
        double Gliberdade = 1d;
        tableAnova.add(fourDecimalCase(Gliberdade));
        double GliberdadeErro = (simpleRegression.getN() - 2);
        tableAnova.add(fourDecimalCase(GliberdadeErro));
        double GliberdadeTotal = (simpleRegression.getN() - 1);
        tableAnova.add(fourDecimalCase(GliberdadeTotal));
        double MSR = SR;
        tableAnova.add(fourDecimalCase(MSR));
        double MSE = SE/(n-2);
        tableAnova.add(fourDecimalCase(MSE));
        double F = MSR/MSE;
        tableAnova.add(fourDecimalCase(F));

        return tableAnova;
    }

    /**
     * Determines the significance of the regression based on the F-test from the ANOVA table.
     *
     * @return A message indicating whether the regression is significant or not.
     */
    public String tableAnovaSignificancia(){

        double FSignificancia;
        double SR = simpleRegression.getRegressionSumSquares();
        double SE = simpleRegression.getSumSquaredErrors();
        double MSR = SR;
        tableAnova.add(fourDecimalCase(MSR));
        double MSE = SE/(n-2);
        double F = MSR/MSE;

        try{
            FSignificancia = simpleRegression.getSignificance();
        } catch (MathException e) {
            throw new RuntimeException(e);
        }

        if (F > FSignificancia) {
            return ("The regression is significant.");
        }else {
            return ("The regression isn't significant.");
        }
    }

    /**
     * Retrieves the significance level (p-value) from the F-test in the ANOVA table.
     *
     * @return The significance level as a formatted string.
     */
    public String FSignificancia(){
        double FSignificancia;

        try{
            FSignificancia = simpleRegression.getSignificance();
        } catch (MathException e) {
            throw new RuntimeException(e);
        }

        return fourDecimalCase(FSignificancia);
    }
}
