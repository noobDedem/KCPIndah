package id.kcpindah.travel.dao;

import id.kcpindah.travel.model.Booking;
import id.kcpindah.travel.model.UserAccount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Created by SpookyBastard on 6/3/2017.
 */

public class MySQLBookingDAO implements BookingDAO {
    @Override
    public void addBooking(Booking booking) throws Exception {
        String username = booking.getUsername();
        String address = booking.getAddress();
        String travelName = booking.getTravelName();
        String destination = booking.getDestination();
        Time bookingTime = booking.getBookingTime();
        
        

        String addQuery = "INSERT INTO booking(username, namatravel, jam, tujuan, alamat) " +
                "VALUES('"+username+"', '"+travelName+"', '"+bookingTime+"', '"+destination+"', '"+address+"')";
        MySQLConnection mySQLConnection = new MySQLConnection();
        Connection connection = mySQLConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(addQuery);
        connection.close();
    }

    @Override
    public ArrayList<Booking> getBooking(UserAccount userActive) throws Exception {
        ArrayList<Booking> bookList = new ArrayList<>();
        MySQLConnection mySQLConnection = new MySQLConnection();
        Connection connection = mySQLConnection.getConnection();
        String query = "select namatravel, jam, tujuan, alamat from booking where username='"+userActive.getUsername()+"'";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()){
            String travelName = rs.getString("namatravel");
            Time travelSchedule = rs.getTime("jam");
            String travelDestination = rs.getString("tujuan");
            String userAddress = rs.getString("alamat");
            bookList.add(new Booking(userActive.getUsername(), userAddress, travelName, travelDestination, travelSchedule));
        }
        return bookList;
    }
}
