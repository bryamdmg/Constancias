package uv.mx.fei.gui;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import uv.mx.fei.logic.ProfessorDAO;
import uv.mx.fei.logic.domain.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    private String getFileName(User user) {
        File path = new File(System.getProperty("user.home")
                +"/IdeaProjects/Constancias/certificates/"
                +user.getId());
        if (!path.exists()) {
            path.mkdirs();
        }
        return path.getAbsolutePath().toString();
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
