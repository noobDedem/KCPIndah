package id.kcpindah.travel.dao;

import id.kcpindah.travel.model.Schedule;
import id.kcpindah.travel.model.ScheduleProperty;
import javafx.collections.ObservableList;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by SpookyBastard on 6/10/2017.
 */
public interface ScheduleDAO {
    /**
     * Initialize data into jadwal table
     * */
    void insertData() throws Exception;
    void getName(ObservableList<String> travelName) throws Exception;
	void getDestination(ObservableList<String> travelDestination) throws Exception;
	void getTime(ObservableList<Time> travelTime) throws Exception;
	ArrayList<ScheduleProperty> getSchedule() throws Exception;
}
