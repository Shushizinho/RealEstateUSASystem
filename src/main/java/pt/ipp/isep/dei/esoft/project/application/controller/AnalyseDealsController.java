package pt.ipp.isep.dei.esoft.project.application.controller;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.apache.commons.math.stat.regression.SimpleRegression;
import pt.ipp.isep.dei.esoft.project.repository.PurchaseOrderRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Analyse deals controller.
 */
public class AnalyseDealsController {

    /**
     * The Purchase order repository.
     */
    final PurchaseOrderRepository purchaseOrderRepository = Repositories.getInstance().getPurchaseOrderRepository();


    /**
     * Check values to calculate multiple list.
     *
     * @param inputValue1    the input value 1
     * @param inputValue2    the input value 2
     * @param inputValue3    the input value 3
     * @param inputValue4    the input value 4
     * @param inputValue5    the input value 5
     * @param inputValueBeta the input value beta
     * @param inputValueIC   the input value ic
     * @return the list
     */
    public List<String> checkValuesToCalculateMultiple(TextField inputValue1, TextField inputValue2, TextField inputValue3, TextField inputValue4, TextField inputValue5, TextField inputValueBeta, TextField inputValueIC){

        List<String> values = new ArrayList<>();
        String stringWarning = "";
        try{
            if (Integer.parseInt(inputValue1.getText()) < 0){
                inputValue1.setStyle("-fx-text-fill: red; -fx-font-weight: bold;-fx-background-color: #d792a7");
                stringWarning+= "Area must be positive\n";

            }
                else values.add(inputValue1.getText());

            if (Integer.parseInt(inputValue2.getText()) < 0){
                inputValue2.setStyle("-fx-text-fill: red; -fx-font-weight: bold;-fx-background-color: #d792a7");
                stringWarning+= "Distance Center can't be negative\n";

            }
            else values.add(inputValue2.getText());

            if (Integer.parseInt(inputValue3.getText()) < 0){
                inputValue3.setStyle("-fx-text-fill: red; -fx-font-weight: bold;-fx-background-color: #d792a7");
                stringWarning+= "Bedroom Nº can't be negative\n";
            }
            else values.add(inputValue3.getText());

            if (Integer.parseInt(inputValue4.getText()) < 0){
                inputValue4.setStyle("-fx-text-fill: red; -fx-font-weight: bold;-fx-background-color: #d792a7");
                stringWarning+= "Bathroom Nº can't be negative\n";

            }
            else values.add(inputValue4.getText());

            if (Integer.parseInt(inputValue5.getText()) < 0) {
                inputValue5.setStyle("-fx-text-fill: red; -fx-font-weight: bold;-fx-background-color: #d792a7");
                stringWarning+= "Parking Space Nº can't be negative\n";

            }
            else values.add(inputValue5.getText());

            if (Integer.parseInt(inputValueIC.getText()) < 0 || Integer.parseInt(inputValueIC.getText()) > 100 ){
                inputValueIC.setStyle("-fx-text-fill: red; -fx-font-weight: bold;-fx-background-color: #d792a7");
                stringWarning+= "CI needs to be between 0 and 100 \n";

            }
            else values.add(inputValueIC.getText());

            if (Integer.parseInt(inputValueBeta.getText()) < 0 || Integer.parseInt(inputValueBeta.getText()) > 5) {
                inputValueBeta.setStyle("-fx-text-fill: red; -fx-font-weight: bold;-fx-background-color: #d792a7");
                stringWarning+= "Beta needs to be between 0 and 5 \n";

            }
            else values.add(inputValueBeta.getText());

            if(values.size() != 7){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert Dialog");
                alert.setContentText(stringWarning);
                alert.setHeaderText("Validation Error");
                alert.showAndWait();
            }

        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert Dialog");
            alert.setHeaderText("Should fill all variables correctly");
            /*if(stringWarning.compareTo("") ! = 0){
                alert.setContentText("Also\n" + stringWarning);
            }*/
            alert.showAndWait();
        }


        return values;

    }

    /**
     * Check values to calculate simple list.
     *
     * @param inputValue1 the input value 1
     * @param inputValue2 the input value 2
     * @param inputValue3 the input value 3
     * @param inputValue4 the input value 4
     * @param text        the text
     * @return the list
     */
    public List<String> checkValuesToCalculateSimple(TextField inputValue1, TextField inputValue2, TextField inputValue3, TextField inputValue4, String text){

        List<String> values = new ArrayList<>();
        String stringWarning = "";
        try{
            if (Integer.parseInt(inputValue1.getText()) < 0){
                inputValue1.setStyle("-fx-text-fill: red; -fx-font-weight: bold;-fx-background-color: #d792a7");
                stringWarning+= text + " must be positive\n";

            }
            else values.add(inputValue1.getText());

            if (Integer.parseInt(inputValue2.getText()) < 0 || Integer.parseInt(inputValue2.getText()) > 100){
                inputValue2.setStyle("-fx-text-fill: red; -fx-font-weight: bold;-fx-background-color: #d792a7");
                stringWarning+= "CI needs to be between 0 and 100 \n";

            }
            else values.add(inputValue2.getText());

            if (Integer.parseInt(inputValue3.getText()) < 0){
                inputValue3.setStyle("-fx-text-fill: red; -fx-font-weight: bold;-fx-background-color: #d792a7");
                stringWarning+= "A can't be negative\n";
            }
            else values.add(inputValue3.getText());

            if (Integer.parseInt(inputValue4.getText()) < 0){
                inputValue4.setStyle("-fx-text-fill: red; -fx-font-weight: bold;-fx-background-color: #d792a7");
                stringWarning+= "B can't be negative\n";

            }
            else values.add(inputValue4.getText());

            if(values.size() != 4){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert Dialog");
                alert.setContentText(stringWarning);
                alert.setHeaderText("Validation Error");
                alert.showAndWait();
            }

        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert Dialog");
            alert.setHeaderText("Should fill all variables correctly");
            /*if(stringWarning.compareTo("") != 0){
                alert.setContentText("Also\n" + stringWarning);
            }*/
            alert.showAndWait();
        }


        return values;

    }


    /**
     * Lookup element by id t.
     *
     * @param <T>    the type parameter
     * @param parent the parent
     * @param id     the id
     * @return the t
     */
    public <T extends Node> T lookupElementById(Parent parent, String id) {
        return (T) parent.lookup("#" + id);
    }

    /**
     * Gets out red.
     *
     * @param event the event
     */
    public void getOutRed(MouseEvent event) {
        // Código para ser executado quando qualquer botão for clicado
        TextField clickedTextField = (TextField) event.getSource();
        clickedTextField.setStyle("-fx-text-fill: black; -fx-font-weight: normal;-fx-background-color: white");
    }


    /**
     * Gets simple regression by area.
     *
     * @return the simple regression by area
     */
    public SimpleRegression getSimpleRegressionByArea() {
        return purchaseOrderRepository.getSimpleRegressionByArea();
    }

    /**
     * Gets simple regression by distance to center.
     *
     * @return the simple regression by distance to center
     */
    public SimpleRegression getSimpleRegressionByDistanceToCenter() {
        return purchaseOrderRepository.getSimpleRegressionByDistanceToCenter();

    }

    /**
     * Gets simple regression by number of bedrooms.
     *
     * @return the simple regression by number of bedrooms
     */
    public SimpleRegression getSimpleRegressionByNumberOfBedrooms() {
        return purchaseOrderRepository.getSimpleRegressionByNumberOfBedrooms();
    }

    /**
     * Gets simple regression by number of bathrooms.
     *
     * @return the simple regression by number of bathrooms
     */
    public SimpleRegression getSimpleRegressionByNumberOfBathrooms() {
        return purchaseOrderRepository.getSimpleRegressionByNumberOfBathrooms();
    }

    /**
     * Gets simple regression by number of parking spaces.
     *
     * @return the simple regression by number of parking spaces
     */
    public SimpleRegression getSimpleRegressionByNumberOfParkingSpaces() {
        return purchaseOrderRepository.getSimpleRegressionByNumberOfParkingSpaces();
    }
}
