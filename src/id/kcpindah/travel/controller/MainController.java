package id.kcpindah.travel.controller;

import com.jfoenix.controls.JFXTextField;
import id.kcpindah.travel.dao.MySQLScheduleDAO;
import id.kcpindah.travel.dao.MySQLTravelDAO;
import id.kcpindah.travel.model.ScheduleProperty;
import id.kcpindah.travel.model.UserAccount;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Created by SpookyBastard on 6/1/2017.
 */
public class MainController implements Initializable{
	@FXML
    private JFXTextField travelInput;

    @FXML
    private JFXTextField destinationInput;

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

    void setUserActive(UserAccount userActive) {
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
        ObservableList<String> travelName = FXCollections.observableArrayList();
        ObservableList<String> travelDestination = FXCollections.observableArrayList();
        ObservableList<Time> travelTime = FXCollections.observableArrayList();

        MySQLScheduleDAO mySQLScheduleDAO = new MySQLScheduleDAO();
        try {
            mySQLScheduleDAO.getName(travelName);
            mySQLScheduleDAO.getDestination(travelDestination);
            mySQLScheduleDAO.getTime(travelTime);

            columnName.setCellValueFactory(cellData -> cellData.getValue().getTravelNameProperty());
            columnDestination.setCellValueFactory(cellData -> cellData.getValue().getTravelDestinationProperty());
            columnSchedule.setCellValueFactory(cellData -> cellData.getValue().getTravelScheduleProperty());

            FilteredList<ScheduleProperty> filteredList = new FilteredList<>(dataSchedule, p -> true);


            filteredList.predicateProperty().bind(Bindings.createObjectBinding(() ->
            scheduleProperty -> scheduleProperty.getTravelName().contains(travelInput.getText())
            && scheduleProperty.getTravelDestination().contains(destinationInput.getText()),
                    travelInput.textProperty(), destinationInput.textProperty()));


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
    void clearAction() {
        travelInput.setText("");
        destinationInput.setText("");
    }

    @FXML
    void myAccountAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/MyAccountForm.fxml"));
        Parent accountRoot = fxmlLoader.load();
        Stage accountStage = new Stage();
        MyAccountController myAccountController = fxmlLoader.getController();
        myAccountController.setUserActive(userActive);
        myAccountController.setUserSchedule(userSchedule);
        accountStage.setScene(new Scene(accountRoot, 480, 276));
        accountStage.setTitle("My Account");
        accountStage.getIcons().add(new Image("/id/kcpindah/travel/TravelLogo.png"));
        accountStage.setResizable(false);
        accountStage.show();
    }

    @FXML
    void searchAction() {

    }
}
