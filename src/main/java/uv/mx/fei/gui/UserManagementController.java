package uv.mx.fei.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import uv.mx.fei.logic.UsersDAO;
import uv.mx.fei.logic.domain.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

public class UserManagementController {

    @FXML
    private TableView<User> tableViewUsers;
    private static String username;
    private static String userType;

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
            tableViewUsers.getItems().addAll(userDAO.getUsersList());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public boolean confirmedLogOut() {
        return AlertPopUpGenerator.showConfirmationMessage(
                "Cerrar sesión", "¿Estás seguro que deseas cerrar sesión?");
    }

    @FXML
    private void logOut() throws IOException {
        if (confirmedLogOut()) {
            MainApp.changeView("login-view.fxml");
        }
    }

}
