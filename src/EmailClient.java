import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EmailClient extends Application {
    private TextField tfMailFrom = new TextField();
    private TextField tfRcptTo = new TextField();
    private TextField tfData = new TextField();
    private Button btSend = new Button("Send");

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create UI
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(new Label("Mail From:"), 0, 0);
        gridPane.add(tfMailFrom, 1, 0);
        gridPane.add(new Label("Send To:"), 0, 1);
        gridPane.add(tfRcptTo, 1, 1);
        gridPane.add(new Label("Data:"), 0, 2);
        gridPane.add(tfData, 1, 2);
        gridPane.add(btSend, 1, 5);

        // Set properties for UI
        gridPane.setAlignment(Pos.CENTER);
        tfMailFrom.setAlignment(Pos.BOTTOM_RIGHT);
        tfRcptTo.setAlignment(Pos.BOTTOM_RIGHT);
        tfData.setAlignment(Pos.BOTTOM_RIGHT);
        GridPane.setHalignment(btSend, HPos.RIGHT);

        // Process events
        btSend.setOnAction(e -> {
            try {
                createEmail();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

    /*
    btCalculate.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e) {
        calculateLoanPayment();
      }
    });

    */


        // Create a scene and place it in the stage
        Scene scene = new Scene(gridPane, 400, 250);
        primaryStage.setTitle("Email Client"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    private void createEmail() throws Exception {
        // Get values from text fields
        String mailFrom = tfMailFrom.getText();
        String rcptTo = tfRcptTo.getText();
        String data = tfData.getText();

        // Create a loan object. Loan defined in Listing 10.2
        Email email = new Email(mailFrom, rcptTo, data);

        EmailSender sender = new EmailSender(email);
        sender.sendEmail();
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}