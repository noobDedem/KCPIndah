package id.kcpindah.travel.dao;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by SpookyBastard on 6/10/2017.
 */
public class MySQLTravelDAO implements TravelDAO {
    /**
     * Called to Initialize travel data
     */
    @Override
    public void insertData() throws Exception {
        MySQLConnection mySQLConnection = new MySQLConnection();
        DataTravel dataTravel = new DataTravel();
        Connection connection = mySQLConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(dataTravel.getDataTravel());
        connection.close();
    }
}
