<<<<<<< HEAD
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
>>>>>>> origin/pre-release
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

<<<<<<< HEAD
<<<<<<<< HEAD:src/main/java/uv/mx/fei/gui/AddUserController.java
public class AddUserController{
========
public class ModifyUserController{
>>>>>>>> origin/pre-release:src/main/java/uv/mx/fei/gui/ModifyUserController.java
=======
<<<<<<<< HEAD:src/main/java/uv/mx/fei/gui/ModifyUserController.java
public class ModifyUserController{
========
public class AddUserController{
>>>>>>>> origin/pre-release:src/main/java/uv/mx/fei/gui/AddUserController.java
>>>>>>> origin/pre-release
    @FXML
    private TextField usernameTextField;
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
<<<<<<< HEAD
<<<<<<<< HEAD:src/main/java/uv/mx/fei/gui/AddUserController.java
    @FXML
    private ComboBox<String> userTypeComboBox;
    @FXML
    private ComboBox<String> academicDegreeComboBox;
    
    public void initialize(){
        academicDegreeComboBox.getItems().addAll("Licenciatura", "Maestría", "Doctorado");
        userTypeComboBox.getItems().addAll("Administrador", "Administrativo", "Profesor");
    }
    
========
=======
>>>>>>> origin/pre-release
    @FXML
    private ComboBox<String> academicDegreeComboBox;
    @FXML
    private ComboBox<String> userTypeComboBox;
    
<<<<<<< HEAD
    @FXML
    public void initialize() {
=======
<<<<<<<< HEAD:src/main/java/uv/mx/fei/gui/ModifyUserController.java
    @FXML
    public void initialize() {
========
    public void initialize(){
>>>>>>>> origin/pre-release:src/main/java/uv/mx/fei/gui/AddUserController.java
>>>>>>> origin/pre-release
        academicDegreeComboBox.getItems().addAll("Licenciatura", "Maestría", "Doctorado");
        userTypeComboBox.getItems().addAll("Administrador", "Profesor","Administrativo");
    }

<<<<<<< HEAD
>>>>>>>> origin/pre-release:src/main/java/uv/mx/fei/gui/ModifyUserController.java
=======
>>>>>>> origin/pre-release
    @FXML
    private void saveChangesButtonClick(ActionEvent event) throws IOException{
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
                user.setUsername(usernameTextField.getText());

                try{
                    UsersDAO userDAO = new UsersDAO();
                    
<<<<<<< HEAD
<<<<<<<< HEAD:src/main/java/uv/mx/fei/gui/AddUserController.java
                    if(userDAO.addUser(user) > 0){
========
                    if(userDAO.modifyUser(user) > 0){
>>>>>>>> origin/pre-release:src/main/java/uv/mx/fei/gui/ModifyUserController.java
=======
                    if(userDAO.modifyUser(user) > 0){
>>>>>>> origin/pre-release
                        AlertPopUpGenerator.showCustomMessage(Alert.AlertType.INFORMATION, "Operación exitosa", "El nuevo usuario ha sido agregado exitosamente");
                        
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
    private void cancelChangesButtonClick(ActionEvent event) throws IOException{
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
        userTypeComboBox.setValue(user.getType());
        academicDegreeComboBox.setValue(user.getAcademicDegree());
    }
    
    public boolean areFieldsBlank(){
        boolean result = false;
        
        if(staffNumberTextField.getText().isBlank() || nameTextField.getText().isBlank() || usernameTextField.getText().isBlank()){
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
<<<<<<< HEAD
        if(joinDatePicker.getValue().compareTo(expirationDatePicker.getValue()) < 0){
=======
        if(joinDatePicker.getValue().compareTo(expirationDatePicker.getValue()) > 0){
>>>>>>> origin/pre-release
            result = false;
        }
        
        //Join or expiration date cannot go before birthDate
<<<<<<< HEAD
        if(birthDatePicker.getValue().compareTo(joinDatePicker.getValue())< 0 || birthDatePicker.getValue().compareTo(expirationDatePicker.getValue()) < 0){
=======
        if(birthDatePicker.getValue().compareTo(joinDatePicker.getValue()) > 0 || birthDatePicker.getValue().compareTo(expirationDatePicker.getValue()) > 0){
>>>>>>> origin/pre-release
            result = false;
        }
        
        //None of these dates can be in the future
<<<<<<< HEAD
        if(birthDatePicker.getValue().compareTo(LocalDate.now()) < 0 || joinDatePicker.getValue().compareTo(LocalDate.now()) < 0 || expirationDatePicker.getValue().compareTo(LocalDate.now()) < 0){
=======
        if(birthDatePicker.getValue().compareTo(LocalDate.now()) > 0 || joinDatePicker.getValue().compareTo(LocalDate.now()) > 0 || expirationDatePicker.getValue().compareTo(LocalDate.now()) > 0){
>>>>>>> origin/pre-release
            result = false;
        }
        
        return result;
    }
}
