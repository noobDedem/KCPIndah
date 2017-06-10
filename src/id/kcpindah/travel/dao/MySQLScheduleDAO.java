package id.kcpindah.travel.dao;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by SpookyBastard on 6/10/2017.
 */
public class MySQLScheduleDAO implements ScheduleDAO {
    /**
     * Initialize data into jadwal table
     */
    @Override
    public void insertData() throws Exception {
        DataSchedule dataSchedule = new DataSchedule();
        MySQLConnection mySQLConnection = new MySQLConnection();
        Connection connection = mySQLConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(dataSchedule.getDataTravel());
        connection.close();
    }
}
