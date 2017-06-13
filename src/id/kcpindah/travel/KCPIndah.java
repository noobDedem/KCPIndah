package id.kcpindah.travel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class KCPIndah extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/SignInForm.fxml"));
        primaryStage.setTitle("KCP Indah");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.getIcons().add(new Image("/id/kcpindah/travel/TravelLogo.png"));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
