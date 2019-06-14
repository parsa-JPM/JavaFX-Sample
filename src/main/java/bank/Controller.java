package bank;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

	@FXML
	private Button submit;

	@FXML
	private TextField name;

	@FXML
	public void initialize() {
		submit.setOnAction((actionEvent -> name.appendText("hello")));
	}

}
