package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import pt.ipp.isep.dei.esoft.project.application.controller.ListBookingRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.VisitRequestDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * The type List booking requests ui.
 */
public class ListBookingRequestsUI extends Application  implements Runnable  {

    private ListBookingRequestController controller = new ListBookingRequestController();
    private final double MINIMUM_WINDOW_WIDTH = 400.0;
    private final double MINIMUM_WINDOW_HEIGHT = 300.0;

    /**
     * The Date picker.
     */
    @FXML
    public DatePicker datePicker;


    /**
     * The My list view.
     */
    @FXML
    public ListView<VisitRequestDTO> myListView;

    /**
     * The My label.
     */
    @FXML
    public Label myLabel;

    /**
     * The Date picker 1.
     */
    @FXML
    public DatePicker datePicker1;

    /**
     * The My label 1.
     */
    @FXML
    public Label myLabel1;

    /**
     * The View.
     */
    @FXML
     public Button view;

    /**
     * The Border pane.
     */
    @FXML
    public BorderPane borderPane;

//    Agent agent =  controller.getAgentFromSession();

    /**
     * The Date begin.
     */
    DateTime dateBegin;
    /**
     * The Date end.
     */
    DateTime dateEnd ;

    /**
     * Gets date.
     *
     * @param event the event
     */
    @FXML
    public void getDate(ActionEvent event) {
        dateBegin=   controller.getDate(datePicker,myLabel);
    }

    /**
     * Gets date end.
     *
     * @param event the event
     */
    @FXML
    public void getDateEnd(ActionEvent event) {
        dateEnd=  controller.getDateEnd(datePicker1,myLabel1);
    }

    /**
     * The Address.
     */
    Address address = new Address("teste","teste","teste","teste");

    /**
     * The List.
     */
    List<Role> list = new ArrayList<>();

    /**
     * The Store.
     */
    Store store =  new Store(12,"aaa","aaa", address, 123213213);

    /**
     * The Employee.
     */
    Employee employee = new Employee("Agent", "agent@this.app", 2, "111-11-0439",address ,0000002,store, list );

    /**
     * The Agent.
     */
    Agent agent = new Agent(employee);


    /**
     * Btn view.
     *
     * @param event the event
     * @throws IOException the io exception
     */
// List<VisitRequestDTO> visitRequestDTOS = controller.getVisitRequestsByAgent(agent.getEmail(),dateBegin,dateEnd);
    @FXML
    public void btnView(ActionEvent event) throws IOException {

        AnchorPane view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/VisitRequestsList.fxml")));
        borderPane.setCenter(view);
        borderPane.setCenter(view);

       // myListView.getItems().addAll(visitRequestDTOS);





    }

    /**
     * Display visit requests.
     */
//    @FXML
    public void displayVisitRequests(){
//        int index = 1;
//
//        for(VisitRequestDTO visitRequestDTO : visitRequestDTOS){
//            System.out.println("[" + index + "] " + visitRequestDTO.toString());
//                System.out.println("------------------");
//                index++;
//
//
//        }
//
//
//
    }






    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListBookingRequests.fxml"));
        Parent root = loader.load();


        try{
            Image icon = new Image("icon.png");
            stage.getIcons().add(icon);

        }
        catch(Exception e){
            System.out.println("'icon.png'  missing in the resources folder");
        }

//        stage.initStyle(StageStyle.TRANSPARENT);

        Scene scene = new Scene(root);
        String css = this.getClass().getResource("/styles/Styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("BookingRequests");
        stage.setScene(scene);

        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);

        stage.sizeToScene();
        stage.setResizable(false);


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

}
