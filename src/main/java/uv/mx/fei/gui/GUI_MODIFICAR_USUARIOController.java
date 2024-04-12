package uv.mx.fei.gui;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import uv.mx.fei.logic.UsersDAO;
import uv.mx.fei.logic.domain.User;

public class GUI_MODIFICAR_USUARIOController{

    @FXML
    private TextField staffNumberTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private DatePicker joinDatePicker;
    @FXML
    private DatePicker expirationDatePicker;
    @FXML
    private DatePicker birthDatePicker;
    @FXML 
    private ComboBox<String> userTypeComboBox;
    @FXML
    private ComboBox<String> academicDegreeComboBox;

    @FXML
    public void initialize() {
        academicDegreeComboBox.getItems().addAll("Licenciatura", "Maestría", "Doctorado");
        userTypeComboBox.getItems().addAll("Administrador", "Profesor");
    }    
    
    @FXML
    private void saveChangesButtonClick(ActionEvent event) throws IOException {
        if(!areFieldsBlank()){
            if(areDatesValid()){
                User user = new User();
            
                user.setStaffNumber(Integer.parseInt(staffNumberTextField.getText()));
                user.setName(nameTextField.getText());
                user.setJoinDate(Date.valueOf(joinDatePicker.getValue()));
                user.setExpirationDate(Date.valueOf(expirationDatePicker.getValue()));
                user.setBirthDate(Date.valueOf(birthDatePicker.getValue()));
                user.setType(userTypeComboBox.getValue());
                user.setAcademicDegree(academicDegreeComboBox.getValue());

                try{
                    UsersDAO userDAO = new UsersDAO();
                    
                    if(userDAO.modifyUser(user) > 0){
                        AlertPopUpGenerator.showCustomMessage(Alert.AlertType.INFORMATION, "Modificación exitosa", "Los datos del usuario han sido modificados exitosamente");
                        
                        MainApp.changeView("usermanagement-view.fxml");
                    }
                }catch(SQLException exception){
                    AlertPopUpGenerator.showCustomMessage(Alert.AlertType.ERROR, "Error", "Error de conexión con la base de datosHubo un error al conectar con la base de datos, por favor inténtelo de nuevo más tarde");
                }
            }else{
                AlertPopUpGenerator.showCustomMessage(Alert.AlertType.ERROR, "Datos inválidos", "No se puede guardar los cambios. Favor de seleccionar fechas válidas");
            }
        }else{
            AlertPopUpGenerator.showCustomMessage(Alert.AlertType.WARNING, "Datos inválidos", "No se pueden guardar los cambios. Favor de llenar todos los campos");
        }
    }

    @FXML
    private void cancelChangesButtonClick(ActionEvent event) throws IOException {
        boolean result = AlertPopUpGenerator.showConfirmationMessage("Cancelar cambios", "¿Está seguro de que realmente desea cancelar la operación? Los cambios no guardados se perderán");
        
        if(result){
            MainApp.changeView("usermanagement-view.fxml");
        }
    }
    
    public void setUser(User user){
        staffNumberTextField.setText(String.valueOf(user.getStaffNumber()));
        nameTextField.setText(user.getName());
        joinDatePicker.setValue(user.getJoinDate().toLocalDate());
        expirationDatePicker.setValue(user.getExpirationDate().toLocalDate());
        birthDatePicker.setValue(user.getBirthDate().toLocalDate());
        academicDegreeComboBox.setValue(user.getAcademicDegree());
    }
    
    public boolean areFieldsBlank(){
        boolean result = false;
        
        if(staffNumberTextField.getText().isBlank() || nameTextField.getText().isBlank()){
            result = false;
        }
        
        if(userTypeComboBox.getValue() == null || academicDegreeComboBox.getValue() == null){
            result = false;
        }
        
        if(joinDatePicker.getValue() == null || expirationDatePicker.getValue() == null || birthDatePicker.getValue() != null){
            result = false;
        }
        
        return result;
    }
    
    public boolean areDatesValid(){
        boolean result = true;
        
        //Join date cannot go after expiration date
        if(joinDatePicker.getValue().compareTo(expirationDatePicker.getValue()) > 0){
            result = false;
        }
        
        //Join or expiration date cannot go before birthDate
        if(birthDatePicker.getValue().compareTo(joinDatePicker.getValue()) > 0 || birthDatePicker.getValue().compareTo(expirationDatePicker.getValue()) > 0){
            result = false;
        }
        
        //None of these dates can be in the future
        if(birthDatePicker.getValue().compareTo(LocalDate.now()) > 0 || joinDatePicker.getValue().compareTo(LocalDate.now()) > 0 || expirationDatePicker.getValue().compareTo(LocalDate.now()) > 0){
            result = false;
        }
        
        return result;
    }
}
