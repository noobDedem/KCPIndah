package id.kcpindah.travel.dao;

import id.kcpindah.travel.model.Booking;
import id.kcpindah.travel.model.UserAccount;

import java.util.ArrayList;

/**
 * Created by SpookyBastard on 6/3/2017.
 */
public interface BookingDAO {
    void addBooking(Booking booking) throws Exception;
    ArrayList<Booking> getBooking(UserAccount userActive) throws Exception;
}
