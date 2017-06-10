package id.kcpindah.travel.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by SpookyBastard on 6/3/2017.
 */
public class Booking {
    private String username;
    private String address;
    private String travelName;
    private String destination;
    private Time bookingTime;
    
    

    public Booking(){}
    public Booking(String username, String address, String travelName, String destination, Time bookingTime){
    	this.username=username;
    	this.address=address;
    	this.travelName=travelName;
    	this.destination=destination;
    	this.bookingTime=bookingTime;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

	public Time getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(Time bookingTime) {
		this.bookingTime = bookingTime;
	}
}
