module uv.mx.fei.constancias {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.base;

    opens uv.mx.fei.gui to javafx.fxml;
    exports uv.mx.fei.gui;
}