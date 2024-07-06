package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pt.ipp.isep.dei.esoft.project.application.controller.ReplyToAgentAboutVisitRequestController;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The type Analyse deals ui.
 */
public class ReplyVisitRequestUI extends Application  implements Runnable  {

    /**
     * The Controller.
     */
    public ReplyToAgentAboutVisitRequestController controller = new ReplyToAgentAboutVisitRequestController();
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
     * The Accept radio.
     */
    @FXML
    public RadioButton acceptRadio;

    /**
     * The Decline radio.
     */
    @FXML
    public RadioButton declineRadio;

    /**
     * The Mode type.
     */
    @FXML
    public ToggleGroup modeType;

    /**
     * The Close button.
     */
    @FXML
    public Button closeButton;

    /**
     * The Text area.
     */
    @FXML
    public TextArea textArea;

    /**
     * The Option typed.
     */
    @FXML
    public TextField optionTyped;
    /**
     * The Title linear.
     */
    @FXML
    public Label titleLinear;
    /**
     * The Text reason.
     */
    @FXML
    public TextArea textReason;

    @FXML
    private Text textVisit;

    private AnchorPane showList;
    private AnchorPane showMain;


    private static List<VisitRequest> visitRequests;


    private boolean stageSecond;

    private static int requestNumber;



    private boolean verifyShow;

    private static int sizeVisitRequest;


    /**
     * Stage trade.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    public void stageTrade(ActionEvent event) throws IOException {


        //TODO: fazer a validação do numero para a quantidade de request, ou seja, se tiver so 10 n permitir o numero 12, ou se ja n tiver nenhuma, n permitir o numero 0

        try{



                if (verifyShow) optionTyped =  controller.lookupElementById(showMain, "optionTyped");


                requestNumber = Integer.parseInt(optionTyped.getText());

            if(requestNumber>0 && requestNumber<=sizeVisitRequest ){

                if(!stageSecond){
                    AnchorPane view = FXMLLoader.load(getClass().getResource("/fxml/ReplyVisitRequest_Answer.fxml"));
                    showList = view;
                    borderPane.setCenter(view);

                    textArea = controller.lookupElementById(showList,"textArea");
                    textVisit = controller.lookupElementById(showList,"textVisit");
                    confirmButton.setVisible(true);


                    stageButton.setText("Cancel");
                    stageSecond = true;

                    textVisit.setText(visitRequests.get(requestNumber-1).toString() + "\n");

                }

                else{

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ReplyVisitRequest.fxml"));
                    BorderPane view2 = loader.load();
                    showMain = (AnchorPane) view2.lookup("#anchorPaneMain");

                    borderPane.setCenter(showMain);
                    stageButton.setText("Confirm");
                    verifyShow = true;
                    stageSecond = false;
                    confirmButton.setVisible(false);
                    listInformation();
                }

            }
        }
        catch (Exception ignored){};


    }


    /**
     * Send answer.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    public void sendAnswer(ActionEvent event) throws IOException {

        acceptRadio = controller.lookupElementById(showList,"acceptRadio");
        declineRadio = controller.lookupElementById(showList,"declineRadio");
        titleLinear = controller.lookupElementById(showList,"titleLinear");
        textReason = controller.lookupElementById(showList,"textReason");

        String reason = textReason.getText();

        int answer;
        if(declineRadio.isSelected()) answer=2;
        else answer = 1;

        String answerString;
        if(declineRadio.isSelected()) answerString=declineRadio.getText();
        else answerString = acceptRadio.getText();


        VisitRequest visitRequest = visitRequests.get(requestNumber-1);
        controller.sendEmailToAgent(visitRequest);

        controller.setVisitRequestClientReply(visitRequest, answerString);




//        System.out.println(requestNumber);


        if((answer == 2 && haveCharacters(reason)) || answer == 1 ){

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ReplyVisitRequest.fxml"));
            BorderPane view2 = loader.load();
            showMain = (AnchorPane) view2.lookup("#anchorPaneMain");

            borderPane.setCenter(showMain);
            stageButton.setText("Confirm");
            verifyShow = true;
            stageSecond = false;
            confirmButton.setVisible(false);



            Alert alert = new Alert(Alert.AlertType.ERROR);
            ImageView icon = new ImageView("confirm.png");

            // The standard Alert icon size is 48x48, so let's resize our icon to match
            icon.setFitHeight(50);
            icon.setFitWidth(50);

            // Set our new ImageView as the alert's icon
            alert.getDialogPane().setGraphic(icon);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("The answer to the visit request was sent successfully.");

            if (answer == 2) alert.setContentText( "Reason: " + reason + "\n" + visitRequests.get(requestNumber-1).toString());
            else alert.setContentText(visitRequests.get(requestNumber-1).toString());

            alert.showAndWait();
        }
        else{

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Alert Dialog");
            alert.setHeaderText("Should fill the reason of decline");
            alert.showAndWait();

        }

    }

    /**
     * List information.
     */
    public void listInformation(){

        visitRequests = controller.getVisitRequest();
        sizeVisitRequest = visitRequests.size();
        String display = "";

        for (int i = 0; i < visitRequests.size(); i++) {
            display += i+1 + ". " +  visitRequests.get(i).toStringComplete() + "\n";

        }

        if(verifyShow) textArea = controller.lookupElementById(showMain,"textArea");

        textArea.setText(display);


    }


    /**
     * Have characters boolean.
     *
     * @param texto the texto
     * @return the boolean
     */
    public static boolean haveCharacters(String texto) {
        Pattern pattern = Pattern.compile("\\S"); // Expressão regular para encontrar qualquer caractere não espaço em branco
        Matcher matcher = pattern.matcher(texto);
        return matcher.find();
    }

    /**
     * Select option.
     *
     * @param event the event
     */
    @FXML
    public void selectOption(ActionEvent event) {




        if(declineRadio.isSelected()){
            titleLinear.setVisible(true);
            textReason.setVisible(true);

        }
        else{
            titleLinear.setVisible(false);
            textReason.setVisible(false);
        }


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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ReplyVisitRequest.fxml"));
        Parent root = loader.load();



        try{
            Image icon = new Image("icon.png");
            stage.getIcons().add(icon);

        }
        catch(Exception e){ System.out.println("'icon.png'  missing in the resources folder");}

        Scene scene = new Scene(root);
        String css = this.getClass().getResource("/styles/Styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("Reply to accept visit requests");
        stage.setScene(scene);

        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);

        stage.sizeToScene();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.setOnShown(event2 -> {

            textArea = controller.lookupElementById(root,"textArea");
            closeButton = controller.lookupElementById(root,"closeButton");

            closeButton.setOnAction(event -> stage.hide());
            listInformation();

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
