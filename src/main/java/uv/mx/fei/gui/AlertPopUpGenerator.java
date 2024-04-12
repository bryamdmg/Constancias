package uv.mx.fei.gui;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertPopUpGenerator {

    public static void showConnectionErrorMessage() {
        Alert errorMessage = new Alert(Alert.AlertType.ERROR);
        errorMessage.setHeaderText("Error de conexión");
        errorMessage.setContentText("Hubo un error al conectarse al servidor, inténtelo más tarde");

        errorMessage.showAndWait();
    }

    public static void showMissingFilesMessage() {
        Alert errorMessage = new Alert(Alert.AlertType.ERROR);
        errorMessage.setHeaderText("Error");
        errorMessage.setContentText("Fallo al cargar la ventana");

        errorMessage.showAndWait();
    }

    public static void showCustomMessage(Alert.AlertType AlertType, String header, String content) {
        Alert customMessage = new Alert(AlertType);
        customMessage.setHeaderText(header);
        customMessage.setContentText(content);

        customMessage.showAndWait();
    }
    
    public static boolean showConfirmationMessage(String header, String content) {
        Alert customMessage = new Alert(Alert.AlertType.CONFIRMATION);
        customMessage.setHeaderText(header);
        customMessage.setContentText(content);
        
        Optional<ButtonType> choice = customMessage.showAndWait();
        return choice.isPresent() && choice.get() == ButtonType.OK;
    }
}
