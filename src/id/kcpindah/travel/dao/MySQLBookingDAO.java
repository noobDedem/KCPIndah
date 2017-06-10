package id.kcpindah.travel.dao;

import id.kcpindah.travel.model.Booking;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

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
}
