package bank;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.event.ActionEvent;
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
        submit.setOnAction(this::submitHandler);
    }


    private void submitHandler(ActionEvent event) {
        try {
            HttpResponse<String> response = Unirest.post("http://www.mocky.io/v2/5185415ba171ea3a00704eed")
//                    .queryString("name", "Mark")
//                    .field("last", "Polo")
                    .asString();
            name.appendText(response.getBody());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
