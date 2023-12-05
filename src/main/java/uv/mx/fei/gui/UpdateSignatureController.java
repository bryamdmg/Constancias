package uv.mx.fei.gui;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import uv.mx.fei.logic.SignatureDAO;

public class UpdateSignatureController{

    @FXML
    private PasswordField oldSignatureField;
    @FXML
    private PasswordField newSignatureField;
    @FXML
    private PasswordField confirmSignatureField;
    @FXML
    private PasswordField adminPasswordField;

    @FXML
    public void initialize() {
        
    }

    @FXML
    private void cancelButtonClick(ActionEvent event) throws IOException{
        boolean result = AlertPopUpGenerator.showConfirmationMessage("Cancelar cambios", "¿Está seguro de que desea cancelar la modificación del usuario?");
        
        if(result){
            MainApp.changeView("usermanagement-view.fxml");
        }
    }

    @FXML
    private void saveChangesButtonClick(ActionEvent event) throws IOException{
        SignatureDAO signatureDAO = new SignatureDAO();
        
        if(areFieldsBlank()){
            if(doSignaturesMatch()){
                if(isCurrentSignatureCorrect()){
                    try{
                        if(signatureDAO.updateSignature(newSignatureField.getText()) > 0){
                            MainApp.changeView("usermanagement-view.fxml");
                            AlertPopUpGenerator.showCustomMessage(Alert.AlertType.INFORMATION, "Operación exitosa", "La firma electrónica ha sido actualizada exitosamente");
                        }
                    }catch(SQLException exception){
                        AlertPopUpGenerator.showConnectionErrorMessage();
                    }
                }else{
                    AlertPopUpGenerator.showCustomMessage(Alert.AlertType.WARNING, "No se puede actualizar la firma", "La firma actual no coincide");
                }
            }else{
                AlertPopUpGenerator.showCustomMessage(Alert.AlertType.WARNING, "No se puede actualizar la firma", "Las firmas nuevas no coinciden");
            }
        }else{
            AlertPopUpGenerator.showCustomMessage(Alert.AlertType.WARNING, "No se puede actualizar la firma", "Faltan campos por llenar");
        }
    }
    
    public boolean isCurrentSignatureCorrect(){
        boolean result = false;
        SignatureDAO signatureDAO = new SignatureDAO();
        
        try{
            String oldSignature = signatureDAO.getSignature();
            
            if(newSignatureField.getText().contentEquals(oldSignature)){
                result = true;
            }
        }catch(SQLException exception){
            AlertPopUpGenerator.showConnectionErrorMessage();
        }
        
        return result;
    }
    
    public boolean areFieldsBlank(){
        boolean result = false;
        
        if(oldSignatureField.getText().isBlank() || newSignatureField.getText().isBlank()){
            result = true;
        }
        
        if(confirmSignatureField.getText().isBlank() || adminPasswordField.getText().isBlank()){
            result = true;
        }
        
        return result;
    }
    
    public boolean doSignaturesMatch(){
        return newSignatureField.getText().contentEquals(confirmSignatureField.getText());
    }
}
