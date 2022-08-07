module com.fitnext.registrationapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.java;
    requires java.desktop;

    opens com.fitnext.registrationapp to javafx.fxml;
    exports com.fitnext.registrationapp;
}