package views.screen;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GiveBackResultHandler extends BaseScreenHandler {
	private String result;
	private String message;

	public GiveBackResultHandler(Stage stage, String screenPath, String result, String message) throws IOException {
		super(stage, screenPath);
		paymentResult.setText(result);
		messageResult.setText(message);
	}

	@FXML
	private Label pageTitle;

	@FXML
	private Label paymentResult;

	@FXML
	private Button okButton;
	
	@FXML
	private Label messageResult;

	@FXML
	void confirmPayment(MouseEvent event) throws IOException {
		homeScreenHandler.show();
	}
}
