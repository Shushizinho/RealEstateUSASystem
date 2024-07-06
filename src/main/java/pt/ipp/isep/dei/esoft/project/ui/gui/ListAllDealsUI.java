package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pt.ipp.isep.dei.esoft.project.application.controller.GetDealsListController;
import pt.ipp.isep.dei.esoft.project.domain.Property;

import java.io.IOException;
import java.util.List;


/**
 * The type Analyse deals ui.
 */
public class ListAllDealsUI extends Application  implements Runnable  {

    /**
     * The Controller.
     */
    public GetDealsListController controller = new GetDealsListController();
    private final double MINIMUM_WINDOW_WIDTH = 400.0;
    private final double MINIMUM_WINDOW_HEIGHT = 300.0;

    /**
     * The Border pane.
     */
    @FXML
    public BorderPane borderPane ;


    @FXML
    private static Stage stageLocal;
    private static boolean isLaunched = false;


    /**
     * The Anchor pane.
     */
    @FXML
    public AnchorPane anchorPane;

    /**
     * The Ascendent radio.
     */
    @FXML
    public RadioButton ascendentRadio;

    /**
     * The Bubble radio.
     */
    @FXML
    public RadioButton bubbleRadio;

    /**
     * The Buttonbar.
     */
    @FXML
    public ButtonBar buttonbar;

    /**
     * The Stage button.
     */
    @FXML
    public Button stageButton;

    /**
     * The Confirm button.
     */
    @FXML
    public Button confirmButton;

    /**
     * The Descendent radio.
     */
    @FXML
    public RadioButton descendentRadio;

    /**
     * The Intersetion sort.
     */
    @FXML
    public RadioButton intersetionSort;

    /**
     * The Mode type.
     */
    @FXML
    public ToggleGroup modeType;

    /**
     * The Sort type.
     */
    @FXML
    public ToggleGroup sortType;
    private boolean verifyShow;
    /**
     * The Close button.
     */
    public Button closeButton;

    private AnchorPane showList;
    private AnchorPane showMain;

    private boolean stageSecond;

    /**
     * The Text area.
     */
    @FXML
    public TextArea textArea;

    /**
     * The Title linear.
     */
    @FXML
    public Label titleLinear;


    /**
     * Stage trade.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    public void stageTrade(ActionEvent event) throws IOException {


        if (sortType.getSelectedToggle() != null && modeType.getSelectedToggle() != null) {

            if(!stageSecond){
                AnchorPane view = FXMLLoader.load(getClass().getResource("/fxml/ListAllDeals_Show.fxml"));
                showList = view;
                borderPane.setCenter(view);

                textArea = controller.lookupElementById(showList,"textArea");

                //Type: 1 - Ascending <> 2 - Descending
                //Order: 1 - Bubble Sort <> 2 - Intersetion Sort

                int type = 0;
                int order = 0;
                String typeString;
                String orderString;

                if (verifyShow){
                    ascendentRadio =  controller.lookupElementById(showMain, "ascendentRadio");
                    descendentRadio =  controller.lookupElementById(showMain, "descendentRadio");
                    bubbleRadio =  controller.lookupElementById(showMain, "bubbleRadio");
                    intersetionSort =  controller.lookupElementById(showMain, "intersetionSort");
                }

                if(ascendentRadio.isSelected()) {
                    type = 1;
                    typeString = "Ascendent";
                }
                else {
                    type = 2;
                    typeString = "Descendent";

                }

                if(bubbleRadio.isSelected()){
                    order = 1;
                    orderString = "Bubble sort";

                }
                else{
                    order = 2;
                    orderString = "Intersetion sort";
                }

                titleLinear = controller.lookupElementById(showList,"titleLinear");

                titleLinear.setText(typeString + " - " + orderString);

                List<Property> propertyList = controller.getDealsOrder();
                String display = "";



                List<Property> properties = controller.getPropertySorted(propertyList,controller.sortDeals(propertyList, order)) ;

                if (type == 1){
                    for (int i = 0; i < properties.size(); i++) {
                        display += i+1 + ". " +  properties.get(i).toString() + "\n";
                    }
                }else {

                    for (int k = properties.size() -1; k >= 0 ; k--) {
                        display += k+1 + ". " +  properties.get(k).toString() + "\n";
                    }
                }


                textArea.setText(display);

                stageButton.setText("Cancel");
                stageSecond = true;

            }
            else{

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListAllDeals.fxml"));
                BorderPane view2 = loader.load();
                showMain = (AnchorPane) view2.lookup("#anchorPaneMain");

                borderPane.setCenter(showMain);
                stageButton.setText("Confirm");
                verifyShow = true;
                stageSecond = false;

            }
        }


    }

    /**
     * Gets select.
     *
     * @param event the event
     */
    @FXML
    public void getSelect(ActionEvent event) {

        if (sortType.getSelectedToggle() != null && modeType.getSelectedToggle() != null) {
            stageButton.setDisable(false);


        }
        else {
            stageButton.setDisable(true);
        }


    }

    /**
     * Chnange mode.
     *
     * @param event the event
     */
    @FXML
    public void chnangeMode(MouseEvent event) {

        textArea.setCursor(Cursor.DEFAULT);

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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListAllDeals.fxml"));
        Parent root = loader.load();



        try{
            Image icon = new Image("icon.png");
            stage.getIcons().add(icon);

        }
        catch(Exception e){ System.out.println("'icon.png'  missing in the resources folder");}

        Scene scene = new Scene(root);
        String css = this.getClass().getResource("/styles/Styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("List All Deals");
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

        borderPane = (BorderPane) root;

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
