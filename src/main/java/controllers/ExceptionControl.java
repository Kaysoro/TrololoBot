package controllers;

import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * Created by kaysoro on 27/09/2018.
 */
public class ExceptionControl {

    public static void throwException(String title, Exception e){
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        });
    }
}