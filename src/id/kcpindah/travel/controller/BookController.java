package id.kcpindah.travel.controller;

import com.jfoenix.controls.JFXTextField;

import id.kcpindah.travel.dao.MySQLBookingDAO;
import id.kcpindah.travel.model.Booking;
import id.kcpindah.travel.model.ScheduleProperty;
import id.kcpindah.travel.model.UserAccount;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;

/**
 * Created by SpookyBastard on 6/11/2017.
 */
public class BookController implements Initializable{
    @FXML
    JFXTextField username;

    @FXML
    private TextField address;

    @FXML
    JFXTextField travel;

    @FXML
    JFXTextField destination;

    @FXML
    JFXTextField schedule;

    private UserAccount userActive = new UserAccount();

    private ScheduleProperty userSchedule = new ScheduleProperty();

    public void setUserActive(UserAccount userActive) {
        this.userActive = userActive;
    }

    public void setUserSchedule(ScheduleProperty userSchedule) {
        this.userSchedule = userSchedule;
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

    }

    @FXML
    public void bookOrder() throws Exception {
        String user = userActive.getUsername();
        String alamat = address.getText();
        String travel = userSchedule.getTravelName();
        String destination = userSchedule.getTravelDestination();
        Time time = userSchedule.getTravelSchedule();
        Booking booking = new Booking(user,alamat,travel,destination,time);
        MySQLBookingDAO mySQLBookingDAO = new MySQLBookingDAO();
        mySQLBookingDAO.addBooking(booking);
        
    }

}
