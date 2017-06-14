package id.kcpindah.travel.controller;

import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

import id.kcpindah.travel.dao.MySQLBookingDAO;
import id.kcpindah.travel.model.Booking;
import id.kcpindah.travel.model.ScheduleProperty;
import id.kcpindah.travel.model.UserAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MyAccountController implements Initializable{
	
	@FXML
    private TableView<Booking> tableAccount;

	@FXML
    private TableColumn<Booking, String> columnNama;

	@FXML
    TableColumn<Booking, Time> columnTime;

	@FXML
    TableColumn<Booking, String> columnTujuan;

	@FXML
    TableColumn<Booking, String> columnAlamat;


	private UserAccount userActive = new UserAccount();
	
	private ScheduleProperty userSchedule = new ScheduleProperty();
	
	private ObservableList<Booking> dataSchedule = FXCollections.observableArrayList();

	public MyAccountController() {


    }
	public void setUserActive(UserAccount userActive) {
        this.userActive = userActive;
    }
	
	public void setUserSchedule(ScheduleProperty userSchedule) {
        this.userSchedule = userSchedule;
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
        columnNama.setCellValueFactory(new PropertyValueFactory<>("travelName"));
        columnTime.setCellValueFactory(new PropertyValueFactory<>("bookingTime"));
        columnTujuan.setCellValueFactory(new PropertyValueFactory<>("destination"));
        columnAlamat.setCellValueFactory(new PropertyValueFactory<>("address"));

	}

	@FXML
    public void showData() throws Exception {
        MySQLBookingDAO mySQLBookingDAO = new MySQLBookingDAO();
        ArrayList<Booking> bookList;
        bookList = mySQLBookingDAO.getBooking(userActive);
        dataSchedule.addAll(bookList);
        tableAccount.setItems(dataSchedule);
        for (Booking bo:bookList) {
            System.out.println(bo.getUsername());
        }
    }
	
}
