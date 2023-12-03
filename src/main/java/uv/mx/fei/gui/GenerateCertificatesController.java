package uv.mx.fei.gui;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import uv.mx.fei.logic.ProfessorDAO;
import uv.mx.fei.logic.domain.JuryInfo;
import uv.mx.fei.logic.domain.SessionDetails;
import uv.mx.fei.logic.domain.TemplateJury;
import uv.mx.fei.logic.domain.User;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
