package uv.mx.fei.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import uv.mx.fei.logic.UsersDAO;
import uv.mx.fei.logic.domain.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UserManagementController {

    @FXML
    private TableView<User> tableViewUsers;

    @FXML
    private void initialize() {
        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setMinWidth(50);
        idColumn.setMaxWidth(70);
        TableColumn<User, String> usernameColumn = new TableColumn<>("Usuario");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        TableColumn<User, String> userTypeColumn = new TableColumn<>("Tipo de usuario");
        userTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableViewUsers.getColumns().addAll(
                Arrays.asList(idColumn, usernameColumn, userTypeColumn));
        fillTableViewAccessAccounts();
    }

    @FXML
    private void fillTableViewAccessAccounts() {
        UsersDAO userDAO = new UsersDAO();
        tableViewUsers.getItems().clear();
        try {
            tableViewUsers.getItems().addAll(userDAO.getUserList());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @FXML
    private void changeToUpdateSign(ActionEvent event) throws IOException {
        MainApp.changeView("updatesignature-view.fxml");
    }

    @FXML
    private void changeToRegisterUser(ActionEvent event) throws IOException {
        MainApp.changeView("adduser-view.fxml");
    }

    @FXML
    private void changeToModifyUser(ActionEvent event) throws IOException {
        if (isItemSelected()) {
            try{
                int id = tableViewUsers.getSelectionModel().getSelectedItem().getId();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("modifyuser-view.fxml"));
                Parent root = loader.load();
                ModifyUserController modifyController = loader.getController();
                modifyController.setUser(new UsersDAO().getUserById(id));

                Scene scene = new Scene(root);
                Stage currentStage = (Stage) scene.getWindow();
                currentStage.setScene(new Scene(root));
            }catch(SQLException exception){
                AlertPopUpGenerator.showConnectionErrorMessage();
            }
        } else {
            AlertPopUpGenerator.showCustomMessage(Alert.AlertType.WARNING, "", "Debes seleccionar el usuario que deseas modificar");
        }
    }

    @FXML
    private void actionDeleteUser(ActionEvent event) {
        if (isItemSelected()) {
            int id = tableViewUsers.getSelectionModel().getSelectedItem().getId();
            if (isSelectedUserAdmin()) {
                AlertPopUpGenerator.showCustomMessage(Alert.AlertType.WARNING, "",
                        "No se pueden eliminar usuarios administrador");
            } else {
                deleteUser(id);
                tableViewUsers.getItems().clear();
                fillTableViewAccessAccounts();
            }
        } else {
            AlertPopUpGenerator.showCustomMessage(Alert.AlertType.WARNING, "", "Debes seleccionar el usuario que deseas eliminar");
        }
    }

    private void deleteUser(int id) {
        UsersDAO userDAO = new UsersDAO();
        if(confirmedDelete(tableViewUsers.getSelectionModel().getSelectedItem().getUsername())) {
            try {
                userDAO.deleteUserById(id);
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    private boolean confirmedDelete(String name) {
        return AlertPopUpGenerator.showConfirmationMessage("", "¿Está seguro que desea eliminar al usuario " + name + "?");
    }

    private boolean isItemSelected() {
        return tableViewUsers.getSelectionModel().getSelectedItem() != null;
    }

    private boolean isSelectedUserAdmin() {
        return tableViewUsers
                .getSelectionModel()
                .getSelectedItem()
                .getType().equals(LoginController.USER_ADMINISTRATOR);
    }

    public boolean confirmedLogOut() {
        return AlertPopUpGenerator.showConfirmationMessage(
                "Cerrar sesión", "¿Estás seguro que deseas cerrar sesión?");
    }

    @FXML
    private void logOut(ActionEvent event) throws IOException {
        if (confirmedLogOut()) {
            MainApp.changeView("login-view.fxml");
        }
    }

}
