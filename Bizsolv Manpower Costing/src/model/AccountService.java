package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountService {
    private JDBCConnectionPool pool;

    private final String ID_ACCOUNT = "idaccount";
    private final String FIRSTNAME = "firstname";
    private final String LASTNAME = "lastname";
    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String ACCOUNTTYPE = "accounttype";

    public AccountService() {
        pool = new JDBCConnectionPool();
    }

    public boolean add(Account a) throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();
        String query = "INSERT INTO province VALUE (?, ?, ?, ?, ?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        try {
            Account temp = getLast();
            statement.setInt(1, temp.getAccountid() + 1);
            statement.setString(2, a.getFirstname());
            statement.setString(3, a.getLastname());
            statement.setString(4, a.getUsername());
            statement.setString(5, a.getPassword());
            statement.setString(6, a.getAccounttype());

            boolean added = statement.execute();

            return added;
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        // Return the connection
        pool.checkIn(connection);
        return false;
    }

    public ObservableList<Account> getAll() throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();

        ObservableList <Account> accounts = FXCollections.observableArrayList();
        String query ="SELECT * FROM account";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Account a = new Account();
                a.setAccountid(rs.getInt(ID_ACCOUNT));
                a.setPassword(rs.getString(FIRSTNAME));
                a.setLastname(rs.getString(LASTNAME));
                a.setUsername(rs.getString(USERNAME));
                a.setPassword(rs.getString(PASSWORD));
                a.setAccounttype(rs.getString(ACCOUNTTYPE));
                accounts.add(a);
            }
            return accounts;
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        pool.checkIn(connection);
        return null;
    }

    public Account getLast() throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();
        Account a = new Account();

        String query ="SELECT * FROM account ORDER BY " + ID_ACCOUNT
                + " DESC  LIMIT 1";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                a.setAccountid(rs.getInt(ID_ACCOUNT));
                a.setPassword(rs.getString(FIRSTNAME));
                a.setLastname(rs.getString(LASTNAME));
                a.setUsername(rs.getString(USERNAME));
                a.setPassword(rs.getString(PASSWORD));
                a.setAccounttype(rs.getString(ACCOUNTTYPE));;
            }
            return a;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        pool.checkIn(connection);
        return a;
    }

    public Account getAccountByUsername(String username) throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();
        Account a = new Account();

        String query ="SELECT * FROM account WHERE " + USERNAME + " = '" + username
                + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                a.setAccountid(rs.getInt(ID_ACCOUNT));
                a.setPassword(rs.getString(FIRSTNAME));
                a.setLastname(rs.getString(LASTNAME));
                a.setUsername(rs.getString(USERNAME));
                a.setPassword(rs.getString(PASSWORD));
                a.setAccounttype(rs.getString(ACCOUNTTYPE));;
            }
            return a;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        pool.checkIn(connection);
        return a;
    }

    public boolean delete(String username) throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();
        String query = "DELETE FROM account WHERE " + USERNAME + " = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            statement.setString(1, username);

            boolean deleted  = statement.execute();
            return deleted;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        pool.checkIn(connection);
        return false;
    }


    public boolean update(String username, Account a) throws SQLException {
        //UPDATE
        // Get a connection:
        Connection connection = pool.checkOut();

        String query = "UPDATE account SET "
                + FIRSTNAME +" = ?, "
                + LASTNAME +" = ?, "
                + PASSWORD +" = ?, "
                + "WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            statement.setString(1, a.getFirstname());
            statement.setString(2, a.getLastname());
            statement.setString(3, a.getPassword());
            statement.setString(4, a.getUsername());

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        pool.checkIn(connection);
        return false;
    }


}
