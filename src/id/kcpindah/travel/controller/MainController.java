package id.kcpindah.travel.controller;

import id.kcpindah.travel.dao.DAOManager;
import id.kcpindah.travel.dao.MySQLConnection;
import id.kcpindah.travel.dao.MySQLScheduleDAO;
import id.kcpindah.travel.dao.MySQLTravelDAO;
import id.kcpindah.travel.model.UserAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

/**
 * Created by SpookyBastard on 6/1/2017.
 */
public class MainController implements Initializable{
	@FXML
    private JFXComboBox<?> travelInput;

    @FXML
    private JFXComboBox<?> destinationInput;

    @FXML
    private JFXComboBox<?> timeInput;

    @FXML
    private JFXButton clearButton;

    @FXML
    private JFXButton searchButton;

    @FXML
    private JFXButton myAccountButton;

    @FXML
    private JFXButton bookButton;
	
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
        MySQLScheduleDAO mySQLScheduleDAO = new MySQLScheduleDAO();
        MySQLTravelDAO mySQLTravelDAO = new MySQLTravelDAO();
        try {
            mySQLConnection.createDatabase();
            mySQLTravelDAO.insertData();
            mySQLScheduleDAO.insertData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bookAction(ActionEvent event) {
    	UserAccount userAccount = new UserAccount("Rizqi Hadi Prawira", "doublesinlove", "kiki810kiki", "08996394828");
        DAOManager manager = new DAOManager();
    }

    @FXML
    void clearAction(ActionEvent event) {

    }

    @FXML
    void myAccountAction(ActionEvent event) {

    }

    @FXML
    void searchAction(ActionEvent event) {

    }
}
