package id.kcpindah.travel.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import id.kcpindah.travel.dao.DAOManager;
import id.kcpindah.travel.dao.MySQLUserDAO;
import id.kcpindah.travel.model.UserAccount;
import id.kcpindah.travel.view.SuccessDialog;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

/**
 * Created by SpookyBastard on 6/1/2017.
 */

public class RegisterController {
    /* Class Attributes */
    @FXML
    private StackPane stackPane;

    @FXML
    private JFXTextField nameInput;

    @FXML
    private JFXTextField usernameInput;

    @FXML
    private JFXPasswordField passwordInput;

    @FXML
    private JFXTextField phoneInput;

    private UserAccount userAccount = new UserAccount();

    private DAOManager manager = new DAOManager();

    void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
    /**
     * Called to store new UserAccount info.
     * */
    @FXML
    public void signUpNewAccount() {
        // Get Input
        String name     = nameInput.getText();
        String user     = usernameInput.getText();
        String password = passwordInput.getText();
        String phone    = phoneInput.getText();

        userAccount.setName(name);
        userAccount.setUsername(user);
        userAccount.setPassword(password);
        userAccount.setPhoneNumber(phone);
        // TODO : Prevent User for inputting blank input
        // TODO : Validating Password
        // Password must be a combination pf number and character

        // TODO : Storing Data
        // Store input data to database
        manager.setUserAccountDAO(new MySQLUserDAO());
        manager.setUserAccountDAO(new MySQLUserDAO());
        try {
            manager.getUserAccountDAO().saveUserAccount(userAccount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO : Show a success dialog
        // Make sure data has been stored
        SuccessDialog successDialog = new SuccessDialog();
        successDialog.showDialog(stackPane);

    }
}
