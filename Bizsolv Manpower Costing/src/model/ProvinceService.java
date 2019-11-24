package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvinceService {
    private JDBCConnectionPool pool;

    private final String ID_PROVINCE = "idProvince";
    private final String PROVINCE_NAME = "provincename";
    private final String SALARY_MINIMUM= "salarymin";
    private final String SALARY_MAXIMUM = "salarymax";

    public boolean add(Province p) throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();
        String query = "INSERT INTO province VALUE (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        try {
            Province temp = getLast();
            statement.setInt(1, temp.getIdprovince() + 1);
            statement.setString(2, p.getProvincename());
            statement.setFloat(3, p.getSalarymin());
            statement.setFloat(4, p.getSalarymax());

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

    public ObservableList<Province> getAll() throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();

        ObservableList <Province> provinces = FXCollections.observableArrayList();
        String query ="SELECT * FROM province";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Province a = new Province();
                a.setIdprovince(rs.getInt(ID_PROVINCE));
                a.setProvincename(rs.getString(PROVINCE_NAME));
                a.setSalarymin(rs.getFloat(SALARY_MINIMUM));
                a.setSalarymax(rs.getFloat(SALARY_MAXIMUM));
                provinces.add(a);
            }
            return provinces;
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        pool.checkIn(connection);
        return null;
    }

    public Province getLast() throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();
        Province emp = new Province();

        String query ="SELECT * FROM province ORDER BY " + ID_PROVINCE
                + " DESC  LIMIT 1";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                emp.setIdprovince(rs.getInt(ID_PROVINCE));
                emp.setProvincename(rs.getString(PROVINCE_NAME));
                emp.setSalarymin(rs.getFloat(SALARY_MINIMUM));
                emp.setSalarymax(rs.getFloat(SALARY_MAXIMUM));
            }
            return emp;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        pool.checkIn(connection);
        return emp;
    }

    public Province getProvinceByName(String provincename) throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();
        Province emp = new Province();

        String query ="SELECT * FROM province WHERE " + PROVINCE_NAME + " = '" + provincename
                + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                emp.setIdprovince(rs.getInt(ID_PROVINCE));
                emp.setProvincename(rs.getString(PROVINCE_NAME));
                emp.setSalarymin(rs.getFloat(SALARY_MINIMUM));
                emp.setSalarymax(rs.getFloat(SALARY_MAXIMUM));
            }
            return emp;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        pool.checkIn(connection);
        return emp;
    }


    public boolean update(String provincename, Province p) throws SQLException {
        //UPDATE
        // Get a connection:
        Connection connection = pool.checkOut();

        String query = "UPDATE province SET "
                + SALARY_MINIMUM +" = ?, "
                + SALARY_MAXIMUM +" = ? "
                + "WHERE username= ?";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            statement.setFloat(1, p.getSalarymin());
            statement.setFloat(2, p.getSalarymax());
            statement.setString(3, p.getProvincename());

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
