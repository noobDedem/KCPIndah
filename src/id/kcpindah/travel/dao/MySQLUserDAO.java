package id.kcpindah.travel.dao;

import id.kcpindah.travel.model.UserAccount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

/**
 * Created by SpookyBastard on 6/3/2017.
 */
public class MySQLUserDAO implements UserAccountDAO {
    @Override
    public void saveUserAccount(UserAccount userAccount) throws Exception {
        String name = userAccount.getName();
        String username = userAccount.getUsername();
        String password = userAccount.getPassword();
        String phoneNumber = userAccount.getPhoneNumber();

        String insertQuery = "INSERT INTO User(nama, username, password, tlp) VAlUES('"+name+"', '"+username+"', '"+password+"', '"+phoneNumber+"')";
        MySQLConnection mySQLConnection = new MySQLConnection();
        Connection connection = mySQLConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(insertQuery);
        connection.close();
    }

    @Override
    public UserAccount getUserAccount(String username) throws Exception {
        String getQuery = "SELECT user.nama, user.username, user.password, user.tlp FROM user WHERE username = '"+username+"'";
        MySQLConnection mySQLConnection = new MySQLConnection();
        Connection connection = mySQLConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getQuery);
        resultSet.next();
        String name = resultSet.getString("user.nama");
        String user = resultSet.getString("user.username");
        String pass = resultSet.getString("user.password");
        String phone = resultSet.getString("user.tlp");
        UserAccount userAccount = new UserAccount(name, user, pass, phone);
        connection.close();
        return userAccount;
    }
}
