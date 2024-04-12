package uv.mx.fei.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import uv.mx.fei.logic.ProfessorDAO;
import uv.mx.fei.logic.domain.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenerateCertificatesController {
    @FXML
    TextField textFieldServedAs;
    @FXML
    TextField textFieldDegreeName;
    @FXML
    TextField textFieldStudents;
    @FXML
    TextField textFieldWorkTitle;
    @FXML
    TextField textFieldModality;
    @FXML
    DatePicker datePickerPresentation;
    @FXML
    TextArea textAreaResult;
    @FXML
    TextField textFieldDirectorName;

    @FXML
    TextField textFieldProject;
    @FXML
    TextField textFieldDuration;
    @FXML
    TextField textFieldPlace;
    @FXML
    TextArea textAreaInvolved;
    @FXML
    TextArea textAreaImpact;
    @FXML
    TextField textFieldDirectorProject;

    @FXML
    TextArea textAreaAxis;
    @FXML
    TextArea textAreaProgram;
    @FXML
    TextArea textAreaObjectives;
    @FXML
    TextArea textAreaActions;
    @FXML
    TextArea textAreaGoals;
    @FXML
    TextField textFieldDirectorPLADEA;

    @FXML
    TextArea textAreaPeriod;
    @FXML
    TextArea textAreaProgramImple;
    @FXML
    TextArea textAreaEE;
    @FXML
    TextArea textAreaBSC;
    @FXML
    TextArea textAreaHSM;
    @FXML
    TextField textFieldDirectorImple;

    @FXML
    TableView<File> tableViewCertificates;

    public void initialize() {
        if (getCertificatesDirectory().exists()) {

            fillTableViewCertificates();
        }
    }

    @FXML
    private void generateJuryCertificate() {
        List<JuryInfo> infos = new ArrayList<>();

        JuryInfo info = new JuryInfo();

        info.setStudents(textFieldStudents.getText());
        info.setWorkTitle(textFieldWorkTitle.getText());
        info.setModality(textFieldModality.getText());
        info.setPresentationDate(datePickerPresentation.getValue().toString());
        info.setResult(textAreaResult.getText());

        infos.add(info);
        ProfessorDAO professorDAO = new ProfessorDAO();
        User professor = null;
        try {
            professor = professorDAO.getProfessorIdNameByPersonalNum(SessionDetails
                    .getInstance().getId());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        TemplateJury templateJury = new TemplateJury(professor.getName(), textFieldServedAs.getText(),
                textFieldDegreeName.getText(), infos, textFieldDirectorName.getText(), getFileName(professor)
                +"/" +professor.getName() + "ConstanciaJurado" + java.time.LocalDate.now());

        templateJury.createCertificated();
    }

    @FXML
    private void generateProjectCertificate() {
        ProfessorDAO professorDAO = new ProfessorDAO();
        User professor = null;
        try {
            professor = professorDAO.getProfessorIdNameByPersonalNum(SessionDetails
                    .getInstance().getId());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        TemplateProject templateProject = new TemplateProject(professor.getName(), textFieldProject.getText(),
                textFieldDuration.getText(), textFieldPlace.getText(), textAreaInvolved.getText(),
                textAreaImpact.getText(), textFieldDirectorProject.getText(), getFileName(professor)
                +"/" +professor.getName() + "ConstanciaProyecto" + java.time.LocalDate.now());

        templateProject.createCertificated();
    }

    @FXML
    private void generatePLADEACertificate() {
        ProfessorDAO professorDAO = new ProfessorDAO();
        User professor = null;
        try {
            professor = professorDAO.getProfessorIdNameByPersonalNum(SessionDetails
                    .getInstance().getId());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        TemplatePLADEA templatePLADEA = new TemplatePLADEA(professor.getName(), textAreaAxis.getText(),
                textAreaProgram.getText(), textAreaObjectives.getText(), textAreaActions.getText(),
                textAreaGoals.getText(), textFieldDirectorPLADEA.getText(), getFileName(professor)
                +"/" +professor.getName() + "ConstanciaPLADEA" + java.time.LocalDate.now());

        templatePLADEA.createCertificated();
    }

    @FXML
    private void generateImpleCertificate() {
        ProfessorDAO professorDAO = new ProfessorDAO();
        User professor = null;
        try {
            professor = professorDAO.getProfessorIdNameByPersonalNum(SessionDetails
                    .getInstance().getId());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        TemplateImplementation templateImple = new TemplateImplementation(professor.getName(), textAreaPeriod.getText(),
                textAreaProgramImple.getText(), textAreaEE.getText(), textAreaBSC.getText(),
                textAreaHSM.getText(), textFieldDirectorImple.getText(), getFileName(professor)
                +"/" +professor.getName() + "ConstanciaImplementación" + java.time.LocalDate.now());

        templateImple.createCertificated();
    }

    private File getCertificatesDirectory() {
        ProfessorDAO professorDAO = new ProfessorDAO();
        User professor = null;
        try {
            professor = professorDAO.getProfessorIdNameByPersonalNum(SessionDetails
                    .getInstance().getId());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        File directory = new File(System.getProperty("user.home")
                +"/IdeaProjects/Constancias/certificates/"
                +professor.getId());
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

    private String getFileName(User user) {
        File path = new File(System.getProperty("user.home")
                +"/IdeaProjects/Constancias/certificates/"
                +user.getId());
        if (!path.exists()) {
            path.mkdirs();
        }
        return path.getAbsolutePath().toString();
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
