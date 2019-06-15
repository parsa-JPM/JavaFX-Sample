package bank;

import bank.async.CallApi;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * controller of UI.fxml
 */
public class Controller {

    @FXML
    private Button submit;

    @FXML
    public TextField name;

    @FXML
    public TextField family;

    @FXML
    public TextField email;

    @FXML
    public ComboBox service;

    @FXML
    public Label resultLabel;

    private boolean isFormFilled = false;
    private final String TEAL = "#009688";

    /**
     * register events
     */
    @FXML
    public void initialize() {
        submit.setOnAction(this::submitHandler);
    }


    /**
     * click handler for submit button
     *
     * @param event
     */
    private void submitHandler(ActionEvent event) {
        checkFormData();
        sendData();
    }


    /**
     * check form data that all of them be filled
     */
    private void checkFormData() {
        boolean unFilled = false;
        if (name.getText().equals("")) {
            name.setStyle("-fx-border-color: red");
            unFilled = true;
        }

        if (family.getText().equals("")) {
            family.setStyle("-fx-border-color: red");
            unFilled = true;
        }

        if (email.getText().equals("") || !email.getText().contains("@")) {
            email.setStyle("-fx-border-color: red");
            unFilled = true;
        }

        if (service.getValue() == null) {
            service.setStyle("-fx-border-color: red");
            unFilled = true;
        }


        /*
          if all of them be filled then we can send they to the server
          and rollback style of components
         */
        if (!unFilled) {
            isFormFilled = true;
            name.setStyle("-fx-border-color:" + TEAL);
            family.setStyle("-fx-border-color:" + TEAL);
            email.setStyle("-fx-border-color:" + TEAL);
            service.setStyle("-fx-border-color:" + TEAL);
        }

    }

    /**
     * send form data to the server
     */
    private void sendData() {
        // cancel sending if data didn't prepare
        if (!isFormFilled)
            return;

        new CallApi(this).execute();
    }
}
