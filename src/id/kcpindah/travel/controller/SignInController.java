package id.kcpindah.travel.controller;

import com.jfoenix.controls.*;
import id.kcpindah.travel.dao.DAOManager;
import id.kcpindah.travel.dao.MySQLConnection;
import id.kcpindah.travel.dao.MySQLScheduleDAO;
import id.kcpindah.travel.dao.MySQLTravelDAO;
import id.kcpindah.travel.dao.MySQLUserDAO;
import id.kcpindah.travel.model.UserAccount;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

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
    	MySQLConnection mySQLConnection = new MySQLConnection();
    	MySQLTravelDAO mySQLTravelDAO = new MySQLTravelDAO();
    	MySQLScheduleDAO mySQLScheduleDAO = new MySQLScheduleDAO();
    	try {
			mySQLConnection.createDatabase();
			mySQLConnection.createTable(mySQLConnection.getQueryUser());
			mySQLConnection.createTable(mySQLConnection.getQueryTravel());
			mySQLConnection.createTable(mySQLConnection.getQueryJadwal());
			mySQLConnection.createTable(mySQLConnection.getQueryBooking());
			mySQLTravelDAO.insertData();
            mySQLScheduleDAO.insertData();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
                MainController mainController = fxmlLoader.getController();
                mainController.setUserActive(userAccount);
                mainStage.setScene(new Scene(mainRoot));
                mainStage.getIcons().add(new Image("/id/kcpindah/travel/TravelLogo.png"));
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
            RegisterController registerController = fxmlLoader.getController();
            registerController.setUserAccount(userAccount);
            registerStage.setScene(new Scene(rootRegisterForm, 640, 480));
            registerStage.getIcons().add(new Image("/id/kcpindah/travel/TravelLogo.png"));
            registerStage.setTitle("Register Mock UserAccount");
            registerStage.setResizable(false);
            registerStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
