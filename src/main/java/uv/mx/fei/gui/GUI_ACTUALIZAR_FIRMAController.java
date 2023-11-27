package uv.mx.fei.gui;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;

public class GUI_ACTUALIZAR_FIRMAController implements Initializable {

    @FXML
    private PasswordField oldSignatureField;
    @FXML
    private PasswordField newSignatureField;
    @FXML
    private PasswordField confirmSignatureField;
    @FXML
    private PasswordField adminPasswordField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void cancelButtonClick(ActionEvent event) {
        Alert customMessage = new Alert(Alert.AlertType.WARNING);
        customMessage.setHeaderText("Cancelar cambios");
        customMessage.setContentText("¿Está seguro de que desea cancelar la modificación del usuario?");
        
        
    }

    @FXML
    private void saveChangesButtonClick(ActionEvent event) {
    }
    
}