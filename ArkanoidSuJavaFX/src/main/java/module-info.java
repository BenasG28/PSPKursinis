module com.example.arkanoidsujavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.datatransfer;
    requires java.desktop;
    requires lombok;


    opens com.example.arkanoidsujavafx to javafx.fxml;
    exports com.example.arkanoidsujavafx;
}