package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.math.stat.regression.SimpleRegression;
import pt.ipp.isep.dei.esoft.project.application.controller.AnalyseDealsController;
import pt.ipp.isep.dei.esoft.project.domain.TableViewData;
import pt.ipp.isep.dei.esoft.project.service.MultipleRegressionAnalysis;
import pt.ipp.isep.dei.esoft.project.service.RegressionAnalysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * The type Analyse deals ui.
 */
public class AnalyseDealsUI extends Application  implements Runnable  {

    /**
     * The Controller.
     */
    public AnalyseDealsController controller = new AnalyseDealsController();
    private final double MINIMUM_WINDOW_WIDTH = 400.0;
    private final double MINIMUM_WINDOW_HEIGHT = 300.0;

    /**
     * The Border pane.
     */
    @FXML
    public BorderPane borderPane ;


    private boolean verifyShow;

    /**
     * The Anchor pane.
     */
    @FXML
    public AnchorPane anchorPane;

    /**
     * The Valuesmulti.
     */
    @FXML
    public AnchorPane valuesmulti;
    /**
     * The Valuessimple.
     */
    @FXML
    public AnchorPane valuessimple;
    /**
     * The Optionssimple.
     */
    @FXML
    public AnchorPane optionssimple;

    /**
     * The Cancel button.
     */
    @FXML
    public Button cancelButton;

    /**
     * The Confirm button.
     */
    @FXML
    public Button confirmButton;

    /**
     * The Multiple button.
     */
    @FXML
    public Button multipleButton;

    /**
     * The Simple button.
     */
    @FXML
    public Button simpleButton;

    /**
     * The Close button.
     */
    @FXML
    public Button closeButton;


    /**
     * The Buttonbar.
     */
    @FXML
    public ButtonBar buttonbar;


    /**
     * The Input value 1.
     */
    @FXML
    public TextField inputValue1;

    /**
     * The Input value 2.
     */
    @FXML
    public TextField inputValue2;

    /**
     * The Input value 3.
     */
    @FXML
    public TextField inputValue3;

    /**
     * The Input value 4.
     */
    @FXML
    public TextField inputValue4;

    /**
     * The Input value 5.
     */
    @FXML
    public TextField inputValue5;
    /**
     * The Input value ic.
     */
    @FXML
    public TextField inputValueIC;
    /**
     * The Input value beta.
     */
    @FXML
    public TextField inputValueBeta;

    private int positonScene=0;

    /**
     * The Values.
     */
    @FXML
    public ToggleGroup Values;

    /**
     * The Radio 1.
     */
    @FXML
    public RadioButton radio1;

    /**
     * The Radio 2.
     */
    @FXML
    public RadioButton radio2;

    /**
     * The Radio 3.
     */
    @FXML
    public RadioButton radio3;

    /**
     * The Radio 4.
     */
    @FXML
    public RadioButton radio4;

    /**
     * The Radio 5.
     */
    @FXML
    public RadioButton radio5;


    /**
     * The Input value a.
     */
    @FXML
    public TextField inputValueA;

    /**
     * The Input value b.
     */
    @FXML
    public TextField inputValueB;

    /**
     * The Input value ics.
     */
    @FXML
    public TextField inputValueICS;

    /**
     * The Input value x.
     */
    @FXML
    public TextField inputValueX;

    /**
     * The Predict variavel.
     */
    @FXML
    public Label predictVariavel;

    /**
     * The Type of output.
     */
    @FXML
    public ToggleGroup TypeOfOutput;

    /**
     * The Radio ci.
     */
    @FXML
    public RadioButton radioCI;

    /**
     * The Radioanova.
     */
    @FXML
    public RadioButton radioanova;

    /**
     * The Radiohypo.
     */
    @FXML
    public RadioButton radiohypo;

    /**
     * The Radioinfo.
     */
    @FXML
    public RadioButton radioinfo;

    /**
     * The Radiopredict.
     */
    @FXML
    public RadioButton radiopredict;
    private int simpleValueSelected;

    /**
     * The Statistics column.
     */
    @FXML
    public TableColumn<TableViewData, String> statisticsColumn;

    /**
     * The Sum column.
     */
    @FXML
    public TableColumn<TableViewData, String> sumColumn;

    /**
     * The Table view.
     */
    @FXML
    public TableView<TableViewData> tableView;

    /**
     * The Variant column.
     */
    @FXML
    public TableColumn<TableViewData, String> variantColumn;


    /**
     * The Mean column.
     */
    @FXML
    public TableColumn<TableViewData, String> meanColumn;

    /**
     * The Degrees column.
     */
    @FXML
    public TableColumn<TableViewData, String> degreesColumn;


    /**
     * The Info label.
     */
    @FXML
    public Label infoLabel;

    @FXML
    private static Stage stageLocal;
    private static boolean isLaunched = false;

    /**
     * Gets view atributes.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void getViewAtributes() throws IOException {
        buttonbar.setVisible(true);
        AnchorPane view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/AnalyseDeals_ValuesMultiple.fxml")));
        valuesmulti = view;
        borderPane.setCenter(view);
        positonScene=3;


    }

    /**
     * Gets view atributes simple.
     *
     * @param id the id
     * @param i  the
     * @throws IOException the io exception
     */
    @FXML
    public void getViewAtributesSimple(String id, int i) throws IOException {
        buttonbar.setVisible(true);
        AnchorPane view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/AnalyseDeals_ValuesSimple.fxml")));
        valuessimple = view;
        borderPane.setCenter(view);
        predictVariavel = controller.lookupElementById(valuessimple,"predictVariavel");

        predictVariavel.setText(id);
        simpleValueSelected = i;

        positonScene=2;


    }

    /**
     * Gets view options.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void getViewOptions() throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("/fxml/AnalyseDeals_Options.fxml"));
        optionssimple = view;
        borderPane.setCenter(view);
        buttonbar.setVisible(true);
        positonScene=1;

    }

    /**
     * Gets view main.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void getViewMain() throws IOException {

        buttonbar.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AnalyseDeals.fxml"));
        BorderPane view = loader.load();
        AnchorPane anchorPane = (AnchorPane) view.lookup("#anchorPaneMain");

        // Configurar os event handlers do botão novamente
        Button button = (Button) anchorPane.lookup("#simpleButton");
        button.setOnAction(event -> {
            try {
                getViewOptions();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Configurar os event handlers do botão novamente
        Button button2 = (Button) anchorPane.lookup("#multipleButton");
        button2.setOnAction(event -> {
            try {
                getViewAtributes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        borderPane.setCenter(anchorPane);
        positonScene = 0;

    }

    /**
     * Confirm validation.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void confirmValidation() throws IOException {

        if (positonScene == 1){
            radio1 = controller.lookupElementById(optionssimple,"area");
            radio2 = controller.lookupElementById(optionssimple,"center");
            radio3 = controller.lookupElementById(optionssimple,"bedroom");
            radio4 = controller.lookupElementById(optionssimple,"bathroom");
            radio5 = controller.lookupElementById(optionssimple,"parking");
            if (radio1.isSelected()) {
                getViewAtributesSimple(radio1.getText(), 1);
            } else if (radio2.isSelected()) {
                getViewAtributesSimple(radio2.getText(), 2);
            } else if (radio3.isSelected()) {
                getViewAtributesSimple(radio3.getText(), 3);
            } else if (radio4.isSelected()) {
                getViewAtributesSimple(radio4.getText(), 4);
            } else if (radio5.isSelected()) {
                getViewAtributesSimple(radio5.getText(), 5);
            }
        }

        else if (positonScene == 2 ){

            //Inputs of over views
            putAllSimple();


            List<String> value = controller.checkValuesToCalculateSimple(inputValueX,inputValueICS,inputValueA,inputValueB,predictVariavel.getText());

            if(value.size() == 4){
                tableView = controller.lookupElementById(valuessimple,"tableView");



                List<Double> valueInt = new ArrayList<>();
                for (String string: value) {
                    valueInt.add(Double.parseDouble(string));
                }


                SimpleRegression simpleRegression = null;
                if (simpleValueSelected == 1 ){
                    simpleRegression = controller.getSimpleRegressionByArea();
                }
                else if (simpleValueSelected == 2 ){
                    simpleRegression = controller.getSimpleRegressionByDistanceToCenter();
                }
                else if (simpleValueSelected == 3 ){
                    simpleRegression = controller.getSimpleRegressionByNumberOfBedrooms();
                }
                else if (simpleValueSelected == 4 ){
                    simpleRegression = controller.getSimpleRegressionByNumberOfBathrooms();
                }
                else if (simpleValueSelected == 5 ){
                    simpleRegression = controller.getSimpleRegressionByNumberOfParkingSpaces();
                }

                RegressionAnalysis regressionAnalysis = new RegressionAnalysis(simpleRegression);

                tableView.setVisible(false);
                infoLabel.setVisible(true);

                String predict = regressionAnalysis.getPricePrediceted(valueInt.get(0));
                String ic = regressionAnalysis.calcularIC(valueInt.get(1));
                String a = regressionAnalysis.getAString();
                String b = regressionAnalysis.getBString();

                if (radioinfo.isSelected()) infoLabel.setText("a = " + a + "\nb = " + b + "\n" + regressionAnalysis.getEquation() + "\n" + regressionAnalysis.getR() + "\n" + regressionAnalysis.getR2());
                else if (radiopredict.isSelected()) infoLabel.setText(predict);
                else if (radioCI.isSelected()) infoLabel.setText(ic);
                else if (radiohypo.isSelected()) infoLabel.setText(regressionAnalysis.testeHipotese(valueInt.get(2),valueInt.get(3)));
                else if (radioanova.isSelected()) {

                     List<String> tableAnova = regressionAnalysis.getTableAnova();

                    TableColumn<TableViewData, ?> variationColumn = tableView.getColumns().get(0);;
                    TableColumn<TableViewData, ?> degreesColumn = tableView.getColumns().get(1);;
                    TableColumn<TableViewData, ?> sumColumn = tableView.getColumns().get(2);;
                    TableColumn<TableViewData, ?> meanColumn = tableView.getColumns().get(3);;
                    TableColumn<TableViewData, ?> statisticsColumn = tableView.getColumns().get(4);

                    variationColumn.setCellValueFactory(new PropertyValueFactory<>("variation"));
                    sumColumn.setCellValueFactory(new PropertyValueFactory<>("sum"));
                    degreesColumn.setCellValueFactory(new PropertyValueFactory<>("degrees"));
                    meanColumn.setCellValueFactory(new PropertyValueFactory<>("mean"));
                    statisticsColumn.setCellValueFactory(new PropertyValueFactory<>("statistics"));

                    TableViewData tableViewData1 = new TableViewData("Regression",tableAnova.get(0), tableAnova.get(3),tableAnova.get(5),tableAnova.get(7));
                    TableViewData tableViewData2 = new TableViewData("Error",tableAnova.get(1),tableAnova.get(4),tableAnova.get(6),"");
                    TableViewData tableViewData3 = new TableViewData("Total",tableAnova.get(2),tableAnova.get(5), "","");

                    tableView.setVisible(true);
                    ObservableList<TableViewData> details = FXCollections.observableArrayList();
                    details.add(tableViewData1);
                    details.add(tableViewData2);
                    details.add(tableViewData3);
                    tableView.setItems(details);
                    infoLabel.setText(regressionAnalysis.tableAnovaSignificancia());


                }

            }




        }


        else if(positonScene==3){
            //If you want to fetch the values of other controls in other fxml, you need to save the anchor and match the variable
            putAllMultiples();





            List<String> value = controller.checkValuesToCalculateMultiple(inputValue1,inputValue2,inputValue3,inputValue4,inputValue5,inputValueBeta,inputValueIC);

            tableView = controller.lookupElementById(valuesmulti,"tableView");

            MultipleRegressionAnalysis multipleRegressionAnalysis = new MultipleRegressionAnalysis();



            List<Double> valueInt = new ArrayList<>();
            for (String string: value) {
                valueInt.add(Double.parseDouble(string));
            }


            tableView.setVisible(false);
            infoLabel.setVisible(true);

            if (radioinfo.isSelected()){
                infoLabel.setStyle("-fx-font-size: 14px;");
                infoLabel.setText(multipleRegressionAnalysis.getEquation() + multipleRegressionAnalysis.getR2() + multipleRegressionAnalysis.getR2Adjusted());
            }
            else if (radiopredict.isSelected()) {
                infoLabel.setStyle("-fx-font-size: 16px;");
                infoLabel.setText(multipleRegressionAnalysis.getPricePrediceted(valueInt.get(0),valueInt.get(1),valueInt.get(2),valueInt.get(3),valueInt.get(4)));
            }
            else if (radioCI.isSelected()) {
                infoLabel.setStyle("-fx-font-size: 16px;");
                infoLabel.setText(multipleRegressionAnalysis.calcularIC(valueInt.get(5),(int) Math.round(valueInt.get(6))));
            }
            else if (radiohypo.isSelected()){
                infoLabel.setStyle("-fx-font-size: 16px;");
                infoLabel.setText(multipleRegressionAnalysis.testeHipotese(valueInt.get(5),(int) Math.round(valueInt.get(6))));
            }
            else if (radioanova.isSelected()) {

                infoLabel.setStyle("-fx-font-size: 14px;");
                List<String> tableAnova =multipleRegressionAnalysis.getTableAnova();

                TableColumn<TableViewData, ?> variationColumn = tableView.getColumns().get(0);;
                TableColumn<TableViewData, ?> degreesColumn = tableView.getColumns().get(1);;
                TableColumn<TableViewData, ?> sumColumn = tableView.getColumns().get(2);;
                TableColumn<TableViewData, ?> meanColumn = tableView.getColumns().get(3);;
                TableColumn<TableViewData, ?> statisticsColumn = tableView.getColumns().get(4);

                variationColumn.setCellValueFactory(new PropertyValueFactory<>("variation"));
                sumColumn.setCellValueFactory(new PropertyValueFactory<>("sum"));
                degreesColumn.setCellValueFactory(new PropertyValueFactory<>("degrees"));
                meanColumn.setCellValueFactory(new PropertyValueFactory<>("mean"));
                statisticsColumn.setCellValueFactory(new PropertyValueFactory<>("statistics"));

                TableViewData tableViewData1 = new TableViewData("Regression",tableAnova.get(0), tableAnova.get(3),tableAnova.get(5),tableAnova.get(7));
                TableViewData tableViewData2 = new TableViewData("Error",tableAnova.get(1),tableAnova.get(4),tableAnova.get(6),"");
                TableViewData tableViewData3 = new TableViewData("Total",tableAnova.get(2),tableAnova.get(5), "","");

                tableView.setVisible(true);
                ObservableList<TableViewData> details = FXCollections.observableArrayList();
                details.add(tableViewData1);
                details.add(tableViewData2);
                details.add(tableViewData3);
                tableView.setItems(details);
                infoLabel.setText(multipleRegressionAnalysis.getTabelaAnovaJustifiacao(valueInt.get(5)));


            }

        }

    }

    private void putAllMultiples() {
        inputValue1 = controller.lookupElementById(valuesmulti,"inputValue1");
        inputValue2 = controller.lookupElementById(valuesmulti,"inputValue2");
        inputValue3 = controller.lookupElementById(valuesmulti,"inputValue3");
        inputValue4 = controller.lookupElementById(valuesmulti,"inputValue4");
        inputValue5 = controller.lookupElementById(valuesmulti,"inputValue5");
        inputValueIC = controller.lookupElementById(valuesmulti,"inputValueIC");
        inputValueBeta = controller.lookupElementById(valuesmulti,"inputValueBeta");

        radioinfo = controller.lookupElementById(valuesmulti,"info");
        radiopredict = controller.lookupElementById(valuesmulti,"prediction");
        radioCI = controller.lookupElementById(valuesmulti,"cofInterv");
        radiohypo = controller.lookupElementById(valuesmulti,"hypothesisTest");
        radioanova = controller.lookupElementById(valuesmulti,"anovaTable");

        infoLabel = controller.lookupElementById(valuesmulti,"infoLabel");

    }

    private void putAllSimple() {
        inputValueX = controller.lookupElementById(valuessimple,"inputValueX");
        inputValueICS = controller.lookupElementById(valuessimple,"inputValueICS");
        inputValueA = controller.lookupElementById(valuessimple,"inputValueA");
        inputValueB = controller.lookupElementById(valuessimple,"inputValueB");

        radioinfo = controller.lookupElementById(valuessimple,"info");
        radiopredict = controller.lookupElementById(valuessimple,"prediction");
        radioCI = controller.lookupElementById(valuessimple,"cofInterv");
        radiohypo = controller.lookupElementById(valuessimple,"hypothesisTest");
        radioanova = controller.lookupElementById(valuessimple,"anovaTable");
        infoLabel = controller.lookupElementById(valuessimple,"infoLabel");
    }


    /**
     * Gets out red.
     *
     * @param event the event
     */
    @FXML
    public void getOutRed(MouseEvent event) {
        controller.getOutRed(event);
    }


    /**
     * Hide stage.
     *
     * @param event the event
     */
    @FXML
    public void hideStage(ActionEvent event) {

//        stageLocal.hide();
    }




    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AnalyseDeals.fxml"));
        Parent root = loader.load();



        try{
            Image icon = new Image("icon.png");
            stage.getIcons().add(icon);

        }
        catch(Exception e){ System.out.println("'icon.png'  missing in the resources folder");}

        Scene scene = new Scene(root);
        String css = this.getClass().getResource("/styles/Styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("AnalyseDeals");
        stage.setScene(scene);

        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);

        stage.sizeToScene();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.setOnShown(event2 -> {
            verifyShow = true;
            closeButton = controller.lookupElementById(root,"closeButton");

            closeButton.setOnAction(event -> stage.hide());
        });

        stageLocal = stage;

        stage.toFront();
        stage.show();



    }


    @Override
    public void run() {


        Platform.setImplicitExit(false);
        new JFXPanel();
        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                start(stage);
            } catch (Exception ignore){
            }
        });

    }



    private static Stage getPrimaryStage() {
        return stageLocal ;
    }

}
