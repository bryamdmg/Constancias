package uv.mx.fei.gui;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField textFieldUser;
    @FXML
    private PasswordField textFieldPassword;

    public static final String USER_ADMIN = "Administrador";
    public static final String USER_STUDENT = "Estudiante";
    public static final String USER_PROFESSOR = "Profesor";
    public static final String USER_REPRESENTATIVE = "RepresentanteCA";

    @FXML
    private void onActionButtonContinue() throws IOException {
        MainApp.changeView("GUI_MODIFICAR_USUARIO.fxml");
    }

    private void continueLogin(boolean isLoginValid) throws SQLException, IOException {

    }

    private void redirectToWindow() throws SQLException, IOException {

    }
}