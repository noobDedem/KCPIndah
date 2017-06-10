package id.kcpindah.travel.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

import id.kcpindah.travel.dao.MySQLConnection;
import javafx.collections.ObservableList;

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
    
    @Override
	public void getName(ObservableList<String> travelName) throws Exception {
		String query = "SELECT namatravel FROM jadwal GROUP BY namatravel";
		MySQLConnection mySQLConnection = new MySQLConnection();
		Connection conn = mySQLConnection.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while(rs.next()){
			String namaTravel = rs.getString("namatravel");
			travelName.add(namaTravel);
		}
	}

	@Override
	public void getDestination(ObservableList<String> travelDestination) throws Exception {
		String query = "SELECT tujuan FROM jadwal GROUP BY tujuan";
		MySQLConnection mySQLConnection = new MySQLConnection();
		Connection conn = mySQLConnection.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while(rs.next()){
			String destinasiTravel = rs.getString("tujuan");
			travelDestination.add(destinasiTravel);
		}
		
	}

	@Override
	public void getTime(ObservableList<Time> travelTime) throws Exception {
		String query = "SELECT jam FROM jadwal GROUP BY jam";
		MySQLConnection mySQLConnection = new MySQLConnection();
		Connection conn = mySQLConnection.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while(rs.next()){
			Time jamTravel = rs.getTime("jam");
			travelTime.add(jamTravel);
		}
		
	}
}
