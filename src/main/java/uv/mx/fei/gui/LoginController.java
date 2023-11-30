package uv.mx.fei.gui;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import uv.mx.fei.logic.ProfessorDAO;
import uv.mx.fei.logic.UsersDAO;
import uv.mx.fei.logic.domain.SessionDetails;


import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField textFieldUser;
    @FXML
    private PasswordField textFieldPassword;

    public static final String USER_ADMINISTRATOR = "Administrador";
    public static final String USER_ADMIN = "Administrativo";
    public static final String USER_PROFESSOR = "Profesor";

    @FXML
    private void onActionButtonContinue() throws IOException {
        UsersDAO userDAO = new UsersDAO();
        try {
            continueLogin(userDAO.isUserValid(textFieldUser.getText(), textFieldPassword.getText()));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    private void continueLogin(boolean validation) throws SQLException, IOException {
        if (validation) {
            redirectToWindow();
        } else {
            AlertPopUpGenerator.showMissingFilesMessage();
        }
    }

    static SessionDetails sessionDetails;
    private void redirectToWindow() throws SQLException, IOException {
        UsersDAO userDAO = new UsersDAO();
        String userType = userDAO.getAccessAccountTypeByUsername(textFieldUser.getText());
        String username = textFieldUser.getText();

        switch (userType) {
            case USER_ADMIN -> MainApp.changeView("GUI_ACTUALIZAR_FIRMA.fxml");
            case USER_PROFESSOR -> {
                ProfessorDAO professorDAO = new ProfessorDAO();
                int professorId = professorDAO.getProfessorIdByUsername(username);
                sessionDetails = SessionDetails.getInstance(username, userType, professorId);
                MainApp.changeView("generatecertificates-view.fxml");
            }
            case USER_ADMINISTRATOR -> MainApp.changeView("usermanagement-view.fxml");
        }
    }
}
