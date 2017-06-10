package id.kcpindah.travel.dao;

import javafx.collections.ObservableList;

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
	void getTime(ObservableList<String> travelTime) throws Exception;
}
