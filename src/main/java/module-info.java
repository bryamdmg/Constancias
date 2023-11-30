module uv.mx.fei.constancias {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.base;
    requires kernel;
    requires layout;
    requires io;
    requires itextpdf;

    opens uv.mx.fei.logic.domain to javafx.fxml;
    opens uv.mx.fei.gui to javafx.fxml;
    exports uv.mx.fei.gui;
    exports uv.mx.fei.logic.domain;
}