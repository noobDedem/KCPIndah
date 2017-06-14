package id.kcpindah.travel.controller;

import id.kcpindah.travel.dao.DAOManager;
import id.kcpindah.travel.dao.MySQLConnection;
import id.kcpindah.travel.dao.MySQLScheduleDAO;
import id.kcpindah.travel.dao.MySQLTravelDAO;
import id.kcpindah.travel.model.Schedule;
import id.kcpindah.travel.model.ScheduleProperty;
import id.kcpindah.travel.model.UserAccount;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
    private TableView<ScheduleProperty> tableViewSchedule;

    @FXML
    private TableColumn<ScheduleProperty, String> columnName;

    @FXML
    private TableColumn<ScheduleProperty, String> columnDestination;

    @FXML
    private TableColumn<ScheduleProperty, Time> columnSchedule;

    private UserAccount userActive = new UserAccount();

    private ScheduleProperty userSchedule = new ScheduleProperty();

    private ObservableList<ScheduleProperty> dataSchedule = FXCollections.observableArrayList();

    public MainController() throws Exception {
        MySQLScheduleDAO ms = new MySQLScheduleDAO();
        ArrayList<ScheduleProperty> ls = ms.getSchedule();
        dataSchedule.addAll(ls);
    }

    public void setUserActive(UserAccount userActive) {
        this.userActive = userActive;
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
        ArrayList<ScheduleProperty> listSchedule;
        ObservableList<String> travelName = FXCollections.observableArrayList();
        ObservableList<String> travelDestination = FXCollections.observableArrayList();
        ObservableList<Time> travelTime = FXCollections.observableArrayList();

        MySQLScheduleDAO mySQLScheduleDAO = new MySQLScheduleDAO();
        MySQLTravelDAO mySQLTravelDAO = new MySQLTravelDAO();
        try {
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
//            listSchedule = mySQLScheduleDAO.getSchedule();
//            ObservableList<ScheduleProperty> tableSchedule = FXCollections.observableArrayList(listSchedule);
//            columnName.setCellValueFactory(new PropertyValueFactory<ScheduleProperty, String>("travelName"));
//            columnDestination.setCellValueFactory(new PropertyValueFactory<ScheduleProperty, String>("travelDestination"));
//            columnSchedule.setCellValueFactory(new PropertyValueFactory<ScheduleProperty, Time>("travelSchedule"));
//            tableViewSchedule.setItems(tableSchedule);
            columnName.setCellValueFactory(cellData -> cellData.getValue().getTravelNameProperty());
            columnDestination.setCellValueFactory(cellData -> cellData.getValue().getTravelDestinationProperty());
            columnSchedule.setCellValueFactory(cellData -> cellData.getValue().getTravelScheduleProperty());

            FilteredList<ScheduleProperty> filteredList = new FilteredList<>(dataSchedule, p -> true);

            travelInput.valueProperty().addListener((observable, oldValue, newValue) -> filteredList.setPredicate(scheduleProperty -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                if (scheduleProperty.getTravelName().contains(newValue)) {
                    return true;
                }

                return false;
            }));

            destinationInput.valueProperty().addListener((observable, oldValue, newValue) -> filteredList.setPredicate(scheduleProperty -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                if (scheduleProperty.getTravelDestination().contains(newValue)) {
                    return true;
                }

                return false;
            }));

            timeInput.valueProperty().addListener((observable, oldValue, newValue) -> filteredList.setPredicate(scheduleProperty -> {
                if (newValue == null) {
                    return true;
                }
                if (scheduleProperty.getTravelSchedule().equals(newValue)) {
                    return true;
                }

                return false;
            }));

            SortedList<ScheduleProperty> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(tableViewSchedule.comparatorProperty());
            tableViewSchedule.setItems(sortedList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bookAction() throws Exception{
        userSchedule = tableViewSchedule.getSelectionModel().getSelectedItem();
        if (userSchedule == null) {
            System.out.println("Error");
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/BookingForm.fxml"));
            Parent bookingRoot = fxmlLoader.load();
            Stage bookingStage = new Stage();
            BookController bookController = fxmlLoader.getController();
            bookController.setUserActive(userActive);
            bookController.setUserSchedule(userSchedule);
            bookController.username.setText(userActive.getName());
            bookController.travel.setText(userSchedule.getTravelName());
            bookController.destination.setText(userSchedule.getTravelDestination());
            bookController.schedule.setText(String.valueOf(userSchedule.getTravelSchedule()));
            bookingStage.setScene(new Scene(bookingRoot, 169, 300));
            bookingStage.setTitle("Confirm Order");
            bookingStage.getIcons().add(new Image("/id/kcpindah/travel/TravelLogo.png"));
            bookingStage.setResizable(false);
            bookingStage.show();
        }

    }

    @FXML
    void clearAction(ActionEvent event) {
        System.out.println(userActive.getUsername());
    }

    @FXML
    void myAccountAction(ActionEvent event) {

    }

    @FXML
    void searchAction(ActionEvent event) {

    }
}
