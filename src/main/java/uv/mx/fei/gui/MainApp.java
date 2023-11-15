package uv.mx.fei.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private static Scene scene;

    public static void main(String[] args) {
        launch();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(fxml));
        return fxmlLoader.load();
    }

    public static void changeView(String url) throws IOException {
        Stage currentStage = (Stage) scene.getWindow();
        configureStage(currentStage, 1024, 640);
        MainApp.setRoot(url);
    }

    private static void configureStage(Stage stage, int width, int height) {
        stage.setWidth(width);
        stage.setHeight(height);
        stage.centerOnScreen();
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login-view.fxml"), 1024, 640);
        stage.setTitle("Gestión de Proyecto Guiado y Experiencia Recepcional");
        stage.setScene(scene);
        stage.show();
    }
}
