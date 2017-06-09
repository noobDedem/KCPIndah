package id.kcpindah.travel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by SpookyBastard on 6/3/2017.
 */
public class MySQLConnection {
    Connection connection = null;
    Statement statement;
    private String queryUser = "CREATE TABLE IF NOT EXIST User("
            + "nama VARCHAR(20) NOT NULL, "
            + "username VARCHAR(20) NOT NULL PRIMARY KEY, "
            + "password VARCHAR(20) NOT NULL, "
            + "tlp VARCHAR(20) NOT NULL "
            + ");";
    private String queryTravel = "CREATE TABLE IF NOT EXIST travel("
            + "namatravel VARCHAR(20) NOT NULL PRIMARY KEY, "
            + "alamat VARCHAR(50) NOT NULL, "
            + "tlp VARCHAR(20) NOT NULL "
            + ");";
    private String queryJadwal = "CREATE TABLE IF NOT EXIST jadwal("
            + "namatravel VARCHAR(20) NOT NULL, "
            + "jam time NOT NULL, "
            + "tujuan VARCHAR(20) NOT NULL, "
            + "PRIMARY KEY(jam, tujuan), "
            + "FOREIGN KEY(namatravel) REFERENCES travel(namatravel) "
            + ");";
    private String queryBooking = "CREATE TABLE IF NOT EXIST booking("
            + "username VARCHAR(20) NOT NULL, "
            + "namatravel VARCHAR(20) NOT NULL, "
            + "tgl date NOT NULL, "
            + "jam time NOT NULL, "
            + "tujuan VARCHAR(20) NOT NULL, "
            + "alamat VARCHAR(50) NOT NULL, "
            + "FOREIGN KEY(username) REFERENCES user(username), "
            + "FOREIGN KEY(namatravel) REFERENCES travel(namatravel), "
            + "FOREIGN KEY(jam, tujuan) REFERENCES Jadwal(jam, tujuan) "
            + ");";


    public Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Travel", "root", "kiki810kiki");
        return connection;
    }

    public void createDatabase() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=kiki810kiki");
        statement = connection.createStatement();
        statement.executeUpdate("CREATE DATABASE IF NOT EXISTS Travel");
    }

    public void createTable(String query) throws SQLException {
        try {
            this.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            statement.close();
        }
    }

    public String getQueryBooking() {
        return queryBooking;
    }

    public String getQueryJadwal() {
        return queryJadwal;
    }

    public String getQueryTravel() {
        return queryTravel;
    }

    public String getQueryUser() {
        return queryUser;
    }
}
