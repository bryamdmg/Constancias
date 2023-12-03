package uv.mx.fei.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import uv.mx.fei.logic.ProfessorDAO;
import uv.mx.fei.logic.domain.TransferProfessor;
import uv.mx.fei.logic.domain.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

public class SelectProfessorController {

    @FXML
    private TableView<User> tableViewProfessors;

    @FXML
    private void initialize() {
        TableColumn<User, Integer> idColumn = new TableColumn<>("Número de personal");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("staffNumber"));
        TableColumn<User, String> usernameColumn = new TableColumn<>("Nombre");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableViewProfessors.getColumns().addAll(
                Arrays.asList(idColumn, usernameColumn));
        fillTableViewProfessors();
    }

    @FXML
    private void fillTableViewProfessors() {
        ProfessorDAO professorDAO = new ProfessorDAO();
        tableViewProfessors.getItems().clear();
        try {
            tableViewProfessors.getItems().addAll(professorDAO.getProfessorsList());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @FXML
    private void selectProfessor() throws IOException{
        User professor = tableViewProfessors.getSelectionModel().getSelectedItem();
        if (professor != null) {
            TransferProfessor.setId(professor.getId());
            TransferProfessor.setName(professor.getName());
            MainApp.changeView("login-view.fxml");
        } else {
            AlertPopUpGenerator.showCustomMessage(Alert.AlertType.WARNING,
                    "", "Selecciona un profesor para ver sus constancias");
        }
    }

    private boolean confirmedLogOut() {
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
