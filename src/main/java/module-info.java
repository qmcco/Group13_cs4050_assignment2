module assignment.birds {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires javafx.media;


/*
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
*/
    opens Group13_cs4050_assignment2.songs to javafx.fxml;
    exports Group13_cs4050_assignment2.songs;
}