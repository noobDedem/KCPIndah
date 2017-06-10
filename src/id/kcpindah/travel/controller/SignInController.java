package id.kcpindah.travel.controller;

import com.jfoenix.controls.*;
import id.kcpindah.travel.dao.DAOManager;
import id.kcpindah.travel.dao.MySQLUserDAO;
import id.kcpindah.travel.model.UserAccount;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable{
    /* Class attributes */
    @FXML
    private StackPane signInStack;

    @FXML
    private JFXTextField usernameInput;

    @FXML
    private JFXPasswordField passwordInput;

    @FXML
    private JFXButton signInButton;

    private UserAccount userAccount = new UserAccount();

    private DAOManager manager = new DAOManager();
    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void signIn() {
        String username = usernameInput.getText();
        manager.setUserAccountDAO(new MySQLUserDAO());
        try {
           userAccount = manager.getUserAccountDAO().getUserAccount(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String password = passwordInput.getText();
        if (username.equals(userAccount.getUsername()) && password.equals(userAccount.getPassword())) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/MainForm.fxml"));
                Parent mainRoot = fxmlLoader.load();
                Stage mainStage = new Stage();
                mainStage.setScene(new Scene(mainRoot));
                mainStage.setTitle("Travel Ticketing");
                mainStage.setResizable(false);
                mainStage.show();
                signInButton.getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (!username.equals(userAccount.getUsername()) || !password.equals(userAccount.getPassword())){
            JFXDialogLayout dialogLayout = new JFXDialogLayout();
            dialogLayout.setHeading(new Text("ERROR"));
            dialogLayout.setBody(new Text("Username or Password might be wrong."));
            JFXDialog errorDialog = new JFXDialog();
            errorDialog.setContent(dialogLayout);
            errorDialog.show(signInStack);
        }

    }

    @FXML
    public void signUp() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/RegisterForm.fxml"));
            Parent rootRegisterForm = fxmlLoader.load();
            Stage registerStage = new Stage();
            registerStage.setScene(new Scene(rootRegisterForm, 640, 480));
            registerStage.setTitle("Register Mock UserAccount");
            registerStage.setResizable(false);
            registerStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
