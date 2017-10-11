package ro.tirzuman.ioana.fx;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DesktopApplication extends Application {
	private Button submit;
	private TextField urlTextField;
	private TextField nrOfRequestsTextField;
	private TextField requestTypeTextField;
	private static final ExecutorService executor = Executors.newCachedThreadPool();

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Creating a GridPane container
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5); 
		grid.setHgap(5);

		// Defining the url text field
		urlTextField = new TextField();
		urlTextField.setText("http://localhost:8080/java-l1/pair");
		urlTextField.setPrefColumnCount(10);
		urlTextField.getText();
		GridPane.setConstraints(urlTextField, 0, 0);
		grid.getChildren().add(urlTextField);

		// Defining the number of times text field
		nrOfRequestsTextField = new TextField();
		nrOfRequestsTextField.setText("1");
		GridPane.setConstraints(nrOfRequestsTextField, 0, 1);
		grid.getChildren().add(nrOfRequestsTextField);

		// Defining the request type text field
		requestTypeTextField = new TextField();
		requestTypeTextField.setPrefColumnCount(15);
		requestTypeTextField.setText("GET");
		GridPane.setConstraints(requestTypeTextField, 0, 2);
		grid.getChildren().add(requestTypeTextField);

		// Defining the Submit button
		submit = new Button("Submit");
		submit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				String url = urlTextField.getText();
				String nrOfRequests = nrOfRequestsTextField.getText();
				String requestType = requestTypeTextField.getText();
				try {
					callWebApp(url, nrOfRequests, requestType);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridPane.setConstraints(submit, 1, 0);
		grid.getChildren().add(submit);

		Scene scene = new Scene(grid);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void callWebApp(String url, String nrOfRequests, String requestType) throws Exception {
		if (url == null || url.isEmpty()) {
			return;
		}
		if (nrOfRequests == null || nrOfRequests.isEmpty()) {
			return;
		}
		if (requestType == null || requestType.isEmpty()) {
			return;
		}
		int nr = Integer.parseInt(nrOfRequests);
		for (int i = 0; i < nr; i++) {
			executor.execute(new Sender(url, requestType));
		}
	}
}
