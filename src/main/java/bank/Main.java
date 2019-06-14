package bank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {
    private static String UI = "fxml/UI.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(findResource(UI));
        Scene frame = new Scene(root);
        frame.getStylesheets().add(findResource("style.css").toExternalForm());
        primaryStage.setTitle("Submit service");
        primaryStage.setResizable(false);
        primaryStage.setScene(frame);
        primaryStage.centerOnScreen();
        primaryStage.setHeight(410);
        primaryStage.show();

    }

    /**
     * find resources shortcut
     *
     * @param name name of resource
     * @return URL
     */
    private URL findResource(String name) {
        return getClass().getClassLoader().getResource(name);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
