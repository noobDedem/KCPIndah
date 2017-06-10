package id.kcpindah.travel.controller;

import id.kcpindah.travel.dao.DAOManager;
import id.kcpindah.travel.dao.MySQLConnection;
import id.kcpindah.travel.dao.MySQLScheduleDAO;
import id.kcpindah.travel.dao.MySQLTravelDAO;
import id.kcpindah.travel.model.Schedule;
import id.kcpindah.travel.model.UserAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by SpookyBastard on 6/1/2017.
 */
public class MainController implements Initializable{
	@FXML
    private JFXComboBox<String> travelInput;

    @FXML
    private JFXComboBox<String> destinationInput;

    @FXML
    private JFXComboBox<Time> timeInput;

    @FXML
    private JFXButton clearButton;

    @FXML
    private JFXButton searchButton;

    @FXML
    private JFXButton myAccountButton;

    @FXML
    private JFXButton bookButton;

    @FXML
    private TableView<Schedule> tableViewSchedule;

    @FXML
    private TableColumn columnName, columnDestination, columnSchedule;
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
        ArrayList<Schedule> listSchedule = new ArrayList<>();
        ObservableList<String> travelName = FXCollections.observableArrayList();
        ObservableList<String> travelDestination = FXCollections.observableArrayList();
        ObservableList<Time> travelTime = FXCollections.observableArrayList();

        MySQLConnection mySQLConnection = new MySQLConnection();
        MySQLScheduleDAO mySQLScheduleDAO = new MySQLScheduleDAO();
        MySQLTravelDAO mySQLTravelDAO = new MySQLTravelDAO();
        try {
            mySQLConnection.createDatabase();
            mySQLTravelDAO.insertData();
            mySQLScheduleDAO.insertData();
            mySQLScheduleDAO.getName(travelName);
            mySQLScheduleDAO.getDestination(travelDestination);
            mySQLScheduleDAO.getTime(travelTime);
            for(String name : travelName){
                travelInput.getItems().add(name);
            }
            for(String destination : travelDestination){
                destinationInput.getItems().add(destination);
            }
            for(Time time : travelTime){
                timeInput.getItems().add(time);
            }
            listSchedule = mySQLScheduleDAO.getSchedule();
            ObservableList<Schedule> tableSchedule = FXCollections.observableArrayList(listSchedule);
            columnName.setCellValueFactory(new PropertyValueFactory<Schedule, String>("travelName"));
            columnDestination.setCellValueFactory(new PropertyValueFactory<Schedule, String>("travelDestination"));
            columnSchedule.setCellValueFactory(new PropertyValueFactory<Schedule, Time>("travelSchedule"));
            tableViewSchedule.setItems(tableSchedule);



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
