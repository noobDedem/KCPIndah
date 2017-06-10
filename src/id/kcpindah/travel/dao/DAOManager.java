package id.kcpindah.travel.dao;

/**
 * Created by SpookyBastard on 6/3/2017.
 */
public class DAOManager {
    private UserAccountDAO userAccountDAO;
    private BookingDAO bookingDAO;

    public void setUserAccountDAO(UserAccountDAO userAccountDAO) {
        this.userAccountDAO = userAccountDAO;
    }

    public void setBookingDAO(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    public UserAccountDAO getUserAccountDAO() {
        return userAccountDAO;
    }

    public BookingDAO getBookingDAO() {
        return bookingDAO;
    }
}
