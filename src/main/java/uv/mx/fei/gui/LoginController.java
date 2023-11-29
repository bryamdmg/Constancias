package uv.mx.fei.gui;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
            continueLogin(userDAO.getUserValidation(textFieldUser.getText(), textFieldPassword.getText()));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    private void continueLogin(int validation) throws SQLException, IOException {
        if (validation == 1) {
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
            case USER_ADMIN -> MainApp.changeView("usermanagement-view.fxml");
            case USER_PROFESSOR -> MainApp.changeView("-view.fxml");
            case USER_ADMINISTRATOR -> MainApp.changeView("-view.fxml");
        }
    }
}
