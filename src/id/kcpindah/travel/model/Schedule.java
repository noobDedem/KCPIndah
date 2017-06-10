package id.kcpindah.travel.model;

import java.sql.Time;

/**
 * Created by SpookyBastard on 6/10/2017.
 */
public class Schedule {
    private String travelName;
    private String travelDestination;
    private Time travelSchedule;

    public Schedule() {}
    public Schedule(String travelName, String travelDestination, Time travelSchedule) {
        this.travelName = travelName;
        this.travelDestination = travelDestination;
        this.travelSchedule = travelSchedule;
    }

    public String getTravelName() {
        return travelName;
    }

    public void setTravelName(String travelName) {
        this.travelName = travelName;
    }

    public String getTravelDestination() {
        return travelDestination;
    }

    public void setTravelSchedule(Time travelSchedule) {
        this.travelSchedule = travelSchedule;
    }

    public Time getTravelSchedule() {
        return travelSchedule;
    }

    public void setTravelDestination(String travelDestination) {
        this.travelDestination = travelDestination;
    }
}
