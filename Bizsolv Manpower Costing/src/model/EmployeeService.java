package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeService {
    private JDBCConnectionPool pool;

    private final String ID_EMPLOYEE = "idempoloyee";
    private final String EMPLOYEE_FIRSTNAME = "employeefirstname";
    private final String EMPLOYEE_LASTNAME = "employeelastname";
    private final String PROVINCE = "province";
    private final String ADDRESS = "address";

    public EmployeeService() {
        pool = new JDBCConnectionPool();
    }

    public boolean add(Employee emp) throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();
        String query = "INSERT INTO employee VALUE (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        try {
            Employee temp = getEmployeeLast();
            statement.setInt(1, temp.getIdemployee() + 1);
            statement.setString(2, emp.getFirstname());
            statement.setString(3, emp.getLastname());
            statement.setString(4, emp.getProvince());
            statement.setString(5, emp.getAddress());

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

    public ObservableList<Employee> getAll() throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();

        ObservableList <Employee> employess = FXCollections.observableArrayList();
        String query ="SELECT * FROM employee";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Employee a = new Employee();
                a.setIdemployee(rs.getInt(ID_EMPLOYEE));
                a.setFirstname(rs.getString(EMPLOYEE_FIRSTNAME));
                a.setLastname(rs.getString(EMPLOYEE_LASTNAME));
                a.setProvince(rs.getString(PROVINCE));
                a.setAddress(rs.getString(ADDRESS));
                employess.add(a);
            }
            return employess;
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        pool.checkIn(connection);
        return null;
    }

    public Employee getEmployeeLast() throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();
        Employee emp = new Employee();

        String query ="SELECT * FROM employee ORDER BY " + ID_EMPLOYEE
                + " DESC  LIMIT 1";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                emp.setIdemployee(rs.getInt(ID_EMPLOYEE));
                emp.setFirstname(rs.getString(EMPLOYEE_FIRSTNAME));
                emp.setLastname(rs.getString(EMPLOYEE_LASTNAME));
                emp.setProvince(rs.getString(PROVINCE));
                emp.setAddress(rs.getString(ADDRESS));
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

    public Employee getEmployeeBYFirstAndLastName(String firstname, String lastname) throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();
        Employee emp = new Employee();

        String query ="SELECT * FROM employee WHERE " + EMPLOYEE_FIRSTNAME + " = '" + firstname
                + "' AND " + EMPLOYEE_LASTNAME + " = '" + lastname + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                emp.setIdemployee(rs.getInt(ID_EMPLOYEE));
                emp.setFirstname(rs.getString(EMPLOYEE_FIRSTNAME));
                emp.setLastname(rs.getString(EMPLOYEE_LASTNAME));
                emp.setProvince(rs.getString(PROVINCE));
                emp.setAddress(rs.getString(ADDRESS));
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

    public boolean delete(int idemployee) throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();
        String query = "DELETE FROM employee WHERE idemployee = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            statement.setInt(1, idemployee);

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
    
    public boolean update(int idemployee, Employee emp) throws SQLException {
        //UPDATE
        // Get a connection:
        Connection connection = pool.checkOut();

        String query = "UPDATE employee SET "
                + EMPLOYEE_FIRSTNAME +" = ?, "
                + EMPLOYEE_LASTNAME +" = ?, "
                + PROVINCE +" = ?, "
                + ADDRESS +" = ? "
                + "WHERE username= ?";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            statement.setString(1, emp.getFirstname());
            statement.setString(2, emp.getLastname());
            statement.setString(3, emp.getProvince());
            statement.setString(4, emp.getAddress());
            statement.setInt(5, idemployee);

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
