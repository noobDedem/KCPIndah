package id.kcpindah.travel.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Time;

/**
 * Created by SpookyBastard on 6/10/2017.
 */
public class ScheduleProperty {
    private StringProperty travelName;
    private StringProperty travelDestination;
    private SimpleObjectProperty<Time> travelSchedule;

    public ScheduleProperty(){}

    public ScheduleProperty (String travelName, String travelDestination, Time travelSchedule) {
        this.travelName = new SimpleStringProperty(travelName);
        this.travelDestination = new SimpleStringProperty(travelDestination);
        this.travelSchedule = new SimpleObjectProperty<>(travelSchedule);
    }

    public StringProperty getTravelNameProperty() {
        return travelName;
    }

    public StringProperty getTravelDestinationProperty() {
        return travelDestination;
    }

    public SimpleObjectProperty<Time> getTravelScheduleProperty() {
        return travelSchedule;
    }

    public void setTravelDestination(String travelDestination) {
        this.travelDestination.set(travelDestination);
    }

    public Time getTravelSchedule() {
        return travelSchedule.get();
    }

    public void setTravelSchedule(Time travelSchedule) {
        this.travelSchedule.set(travelSchedule);
    }

    public String getTravelDestination() {
        return travelDestination.get();
    }

    public void setTravelName(String travelName) {
        this.travelName.set(travelName);
    }

    public String getTravelName() {
        return travelName.get();
    }
}
