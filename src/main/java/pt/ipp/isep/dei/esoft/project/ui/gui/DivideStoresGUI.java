package pt.ipp.isep.dei.esoft.project.ui.gui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pt.ipp.isep.dei.esoft.project.application.controller.DivideStoresController;
import pt.ipp.isep.dei.esoft.project.dto.StoreDTO;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Divide stores gui.
 */
public class DivideStoresGUI extends Application  implements Runnable {
    private static List<StoreDTO> items;
    private static List<CheckBox> checkboxes;
    private final int MAX_CHECKBOXES_PER_HBOX = 5;
    private final double VERTICAL_OFFSET = 30.0; // Vertical offset between HBoxes
    private final double CHECKBOX_WIDTH = 175.0; // Custom width for checkboxes
    private final double ANCHOR_PANE_MAX_HEIGHT = 300.0;

    private final double MINIMUM_WINDOW_WIDTH = 400.0;
    private final double MINIMUM_WINDOW_HEIGHT = 300.0;
    private final DivideStoresController controller = new DivideStoresController();

    /**
     * Instantiates a new Divide stores gui.
     */
    public DivideStoresGUI() {
    }

    private DivideStoresController getController() {
        return controller;
    }

    /**
     * The Content box.
     */
    @FXML
    public VBox contentBox;
    private int positonScene=0;
    @FXML
    public static Label resultsTitle;
    /**
     * The Anchor pane.
     */
    @FXML
    public AnchorPane anchorPane;
    public static AnchorPane anchorPaneTrue;
    /**
     * The Results pane.
     */
    @FXML
    public static AnchorPane resultsPane;

    /**
     * The Border pane.
     */
    @FXML
    public BorderPane borderPane;

    /**
     * The Buttonbar.
     */
    @FXML
    public ButtonBar buttonbar;

    /**
     * The Results field.
     */
    @FXML
    public static TextArea resultsField;

    /**
     * The Confirm/GoBack button.
     */
    @FXML
    public static Button cancelButton;

    /**
     * The Close button.
     */
    @FXML
    public Button closeButton;


    @Override
    public void start(Stage stage) throws Exception {
        items = getController().getStores();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DivideStores.fxml"));
        Parent root = loader.load();

        try{
            Image icon = new Image("icon.png");
            stage.getIcons().add(icon);

        }
        catch(Exception e){ System.out.println("'icon.png'  missing in the resources folder");}


        contentBox = (VBox) loader.getNamespace().get("contentBox");
        cancelButton = (Button) loader.getNamespace().get("cancelButton");
        contentBox.setMaxHeight(ANCHOR_PANE_MAX_HEIGHT);
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("/styles/Styles.css").toExternalForm();

        stage.setTitle("DivideStores");
        stage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);

        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);

        stage.setResizable(false);

        // Retrieve the ArrayList or populate it with data

        // ... add more items as needed

        HBox currentHBox = new HBox(); // Create the first HBox container
        currentHBox.setSpacing(10);

        int counter = 0; // Keep track of the number of checkboxes in the current HBox
        checkboxes = new ArrayList<>();
        // Create checkboxes based on the ArrayList size
        for (StoreDTO item : items) {
            CheckBox checkBox = new CheckBox(item.getDesignation());
//            CheckBox checkBox = new CheckBox(item.getDesignation() + "[ID:" + item.getId() + "]");
            checkBox.setPrefWidth(CHECKBOX_WIDTH); // Set the custom width for checkboxes

            if (counter % MAX_CHECKBOXES_PER_HBOX == 0) {
                currentHBox = new HBox(); // Create a new HBox for the next set of checkboxes
                currentHBox.setSpacing(10);
                contentBox.getChildren().add(currentHBox); // Add the current HBox to the container
//                AnchorPane.setTopAnchor(currentHBox, counter / MAX_CHECKBOXES_PER_HBOX * VERTICAL_OFFSET); // Adjust the top anchor for the HBox
            }
            currentHBox.getChildren().add(checkBox);
            checkboxes.add(checkBox);
            counter++;
        }
//        contentBox.setPrefHeight((counter/MAX_CHECKBOXES_PER_HBOX)*VERTICAL_OFFSET);
        // Add contentBox to the root element of the FXML file

        stage.show();
        for (CheckBox checkBox : checkboxes) {
            checkBox.setOnAction(event -> handleButtonSelect());
        }

        borderPane = (BorderPane) loader.getNamespace().get("borderPane");
        anchorPaneTrue = (AnchorPane) borderPane.getCenter();
    }

    public <T extends Node> T lookupElementById(Parent parent, String id) {
        return (T) parent.lookup("#" + id);
    }



    private void handleButtonSelect() {
        if (getSelectedCheckboxes().size() > 1 && getSelectedCheckboxes().size() < 31) cancelButton.setDisable(false);
        else cancelButton.setDisable(true);
    }

    /**
     * Gets results view.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void getResultsView() throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("/fxml/DivideStores_results.fxml"));
        TextArea textA = (TextArea) view.getChildren().get(1);
        Label label = (Label) view.getChildren().get(0);
        resultsPane = view;
        borderPane.setCenter(view);
        positonScene=1;
        resultsField = textA;
        resultsTitle = label;
        cancelButton.setText("Go Back");
        Platform.runLater(() -> {
            // Run your desired function here
            try {
                getResults();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Gets results.
     */

    public void getResults() throws IOException {
        List<StoreDTO> selectedCheckboxes = getSelectedCheckboxes();
        List<Integer> numberPropertiesList =  getController().getPropertyNumberInEach(selectedCheckboxes);
        String results = getController().divideStores(selectedCheckboxes,numberPropertiesList);
        resultsField.setText(results);
        resultsTitle.setText("Results:");
    }

    /**
     * Gets selected checkboxes.
     *
     * @return the selected checkboxes
     */
    public List<StoreDTO> getSelectedCheckboxes() {
        List<StoreDTO> selectedCheckboxes = new ArrayList<>();
        for (CheckBox checkbox: checkboxes) {
            if (checkbox.isSelected()) selectedCheckboxes.add(items.get(checkboxes.indexOf(checkbox)));
        }
//        System.out.println("Selected Checkboxes: " + selectedCheckboxes);
        return selectedCheckboxes;
    }

    /**
     * Button click.
     *
     * @throws IOException the io exception
     */
    public void buttonClick() throws IOException {
        if (positonScene == 0) getResultsView();
        else closeResultsView();
    }

    private void closeResultsView() throws IOException {
        AnchorPane view = anchorPaneTrue;
        resultsPane.setVisible(false);
        borderPane.setCenter(view);
        positonScene=0;
        cancelButton.setText("Confirm");
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
//        launch();
    }
}
