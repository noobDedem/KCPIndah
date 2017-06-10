package id.kcpindah.travel.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by SpookyBastard on 6/3/2017.
 */
public class Booking {
    private String username;
    private String travelName;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private String destination;
    private String address;

    public Booking(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTravelName(String travelName) {
        this.travelName = travelName;
    }

    public String getTravelName() {
        return travelName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public LocalTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBookingTime(LocalTime bookingTime) {
        this.bookingTime = bookingTime;
    }
}
