package uv.mx.fei.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import uv.mx.fei.logic.domain.TransferProfessor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CertificatesController {

    @FXML
    TableView<File> tableViewCertificates;

    public void initialize() {
        if (getCertificatesDirectory().exists()) {

            fillTableViewCertificates();
        }
    }

    private File getCertificatesDirectory() {
        File directory = new File(System.getProperty("user.home")
                +"/IdeaProjects/Constancias/certificates/"
                + TransferProfessor.getId());
        return directory;
    }

    private void fillTableViewCertificates() {
        TableColumn<File, String> nameColumn = new TableColumn<>("Certificado");

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableViewCertificates.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableViewCertificates.getColumns().clear();
        tableViewCertificates.getColumns().addAll(nameColumn);

        File folder = new File(getCertificatesDirectory().getAbsolutePath());
        File[] files = folder.listFiles();

        ObservableList<File> fileList = FXCollections.observableArrayList(files);

        tableViewCertificates.getItems().clear();
        tableViewCertificates.setItems(fileList);
    }

    @FXML
    private void visualizeFile() throws IOException {
        if (tableViewCertificates.getSelectionModel().getSelectedItem() != null) {
            String filePath = tableViewCertificates.getSelectionModel().getSelectedItem().getAbsolutePath();
            switch (getOperativeSystem()) {
                case "windows":
                    Runtime.getRuntime().exec("cmd /c start \"\" \"" + filePath + "\"");
                    break;
                case "mac os x":
                    Runtime.getRuntime().exec("open \"" + filePath + "\"");
                    break;
                case "linux":
                    ProcessBuilder processBuilder = new ProcessBuilder("xdg-open", filePath);
                    processBuilder.start();
                    break;
                default:
                    AlertPopUpGenerator.showCustomMessage(Alert.AlertType.ERROR,
                            "Error", "No se encontró el sistema operativo");
                    break;
            }
        } else {
            AlertPopUpGenerator.showCustomMessage(Alert.AlertType.WARNING,
                    "", "Selecciona un archivo para visualizar");
        }
    }

    @FXML
    private void downloadFile() {
        if (tableViewCertificates.getSelectionModel().getSelectedItem() != null) {
            try {
                copyFile(tableViewCertificates.getSelectionModel().getSelectedItem());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            AlertPopUpGenerator.showCustomMessage(Alert.AlertType.WARNING,
                    "", "Selecciona un archivo para descargar");
        }
    }

    private void copyFile(File file) throws IOException {
        File fileToSave = new File(System.getProperty("user.home")+"/Downloads/"+file.getName());
        if (fileToSave.exists()) {
            if (confirmedCopyFile(file.getName())) {
                fileToSave.delete();
                Files.copy(file.toPath(), fileToSave.toPath());
                AlertPopUpGenerator.showConfirmationMessage("Constancia generada", "Revisa la carpeta de descargas");
            }
        } else {
            Files.copy(file.toPath(), fileToSave.toPath());
            AlertPopUpGenerator.showConfirmationMessage("Constancia generada", "Revisa la carpeta de descargas");
        }
    }

    public boolean confirmedCopyFile(String fileName) {
        return AlertPopUpGenerator.showConfirmationMessage("Archivo existente","El archivo con el nombre "+fileName+" ya existe, ¿Deseas sobreescribirlo?");
    }

    private String getOperativeSystem() {
        String operativeSystem = System.getProperty("os.name").toLowerCase();
        if (operativeSystem.contains("win")) {
            operativeSystem = "windows";
        }
        return operativeSystem;
    }

    private boolean confirmedReturn() {
        return AlertPopUpGenerator.showConfirmationMessage(
                "Salir", "¿Estás seguro que deseas resgresar a la lista de profesores?");
    }

    @FXML
    private void returnToProfessors() throws IOException {
        if (confirmedReturn()) {
            MainApp.changeView("selectprofessor-view.fxml");
        }
    }
}
