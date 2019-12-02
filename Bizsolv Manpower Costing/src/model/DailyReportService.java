package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DailyReportService {
    private JDBCConnectionPool pool;

    private final String ID_REPORT = "idreport";
    private final String EMPLOYEE_NAME = "employeename";
    private final String BASIC_RATE = "basic_rate";
    private final String NO_WORKING_DAYS = "no_working_days";
    private final String DAYS_RENDERED = "total_days_rendered";
    private final String GROSS_PAY = "gross_pay";
    private final String STATUTORY_SSS = "statutory_sss";
    private final String STATUTORY_PAGIBIG = "statutory_pagibig";
    private final String STATUTORY_PHILHEALTH = "statutory_philhealth";
    private final String STATUTORY_ESCOLA = "statutory_escola";
    private final String TOTAL_STATUTORY = "total_statutory";
    private final String THIRTHEENTH_MONTH_PAY = "13th_month";
    private final String INCENTIVE = "incentive";
    private final String TOTAL = "total";
    private final String ADMIN_FEE = "admin_fee";
    private final String NET_PAYROLL = "net_payroll";
    private final String VERSION = "version";

    public DailyReportService() {
        pool = new JDBCConnectionPool();
    }

    public boolean add(DailyReport m, Employee employee) throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();
        String query = "INSERT INTO monthly_report VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        try {
            DailyReport temp = getLast();
            statement.setInt(1, temp.getIdreport() + 1);
            statement.setString(2,employee.getFirstname() + " " + employee.getLastname());
            statement.setFloat(3, m.getBasicRate());
            statement.setFloat(4, m.getnWorkingDays());
            statement.setFloat(5, m.gettDaysReported());
            statement.setFloat(6, m.getGrossPay());
            statement.setFloat(7, m.getStatutory_sss());
            statement.setFloat(8, m.getStatutory_pagibig());
            statement.setFloat(9, m.getStatutory_philhealth());
            statement.setFloat(10, m.getStatutory_escola());
            statement.setFloat(11, m.getTotalStatutory());
            statement.setFloat(12, m.getThirteenth_month());
            statement.setFloat(13, m.getIncentive());
            statement.setFloat(14, m.getTotal());
            statement.setFloat(15, m.getAdmin_fee());
            statement.setFloat(16, m.getNet_payroll());
            temp = getEmployeeLastVersions(m.getEmployeename());
            statement.setInt(17, temp.getVersion() + 1);

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

    public ObservableList<DailyReport> getEmployeeVersions(String employeename) throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();
        ObservableList<DailyReport> dailyReports = FXCollections.observableArrayList();

        String query ="SELECT * FROM daily_report WHERE employeename = '" + employeename + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                DailyReport mr = new DailyReport();

                mr.setIdreport(rs.getInt(ID_REPORT));
                mr.setEmployeename(rs.getString(EMPLOYEE_NAME));
                mr.setBasicRate(rs.getFloat(BASIC_RATE));
                mr.setnWorkingDays(rs.getFloat(NO_WORKING_DAYS));
                mr.setGrossPay(rs.getFloat(GROSS_PAY));
                mr.setStatutory_sss(rs.getFloat(STATUTORY_SSS));
                mr.setStatutory_pagibig(rs.getFloat(STATUTORY_PAGIBIG));
                mr.setStatutory_philhealth(rs.getFloat(STATUTORY_PHILHEALTH));
                mr.setStatutory_escola(rs.getFloat(STATUTORY_ESCOLA));
                mr.setTotalStatutory(rs.getFloat(TOTAL_STATUTORY));
                mr.setThirteenth_month(rs.getFloat(THIRTHEENTH_MONTH_PAY));
                mr.setIncentive(rs.getFloat(INCENTIVE));
                mr.setTotal(rs.getFloat(TOTAL));
                mr.setAdmin_fee(rs.getFloat(ADMIN_FEE));
                mr.setNet_payroll(rs.getFloat(NET_PAYROLL));
                mr.setVersion(rs.getInt(VERSION));

                dailyReports.add(mr);
            }
            return dailyReports;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        pool.checkIn(connection);
        return dailyReports;
    }

    public DailyReport getEmployeeLastVersions(String employeename) throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();
        DailyReport mr = new DailyReport();

        String query ="SELECT * FROM dailyly_report WHERE employeename = '" + employeename + "' ORDER BY " + ID_REPORT
                + " DESC  LIMIT 1";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            while (rs.next()){

                mr.setIdreport(rs.getInt(ID_REPORT));
                mr.setEmployeename(rs.getString(EMPLOYEE_NAME));
                mr.setBasicRate(rs.getFloat(BASIC_RATE));
                mr.setnWorkingDays(rs.getFloat(NO_WORKING_DAYS));
                mr.setGrossPay(rs.getFloat(GROSS_PAY));
                mr.setStatutory_sss(rs.getFloat(STATUTORY_SSS));
                mr.setStatutory_pagibig(rs.getFloat(STATUTORY_PAGIBIG));
                mr.setStatutory_philhealth(rs.getFloat(STATUTORY_PHILHEALTH));
                mr.setStatutory_escola(rs.getFloat(STATUTORY_ESCOLA));
                mr.setTotalStatutory(rs.getFloat(TOTAL_STATUTORY));
                mr.setThirteenth_month(rs.getFloat(THIRTHEENTH_MONTH_PAY));
                mr.setIncentive(rs.getFloat(INCENTIVE));
                mr.setTotal(rs.getFloat(TOTAL));
                mr.setAdmin_fee(rs.getFloat(ADMIN_FEE));
                mr.setNet_payroll(rs.getFloat(NET_PAYROLL));
                mr.setVersion(rs.getInt(VERSION));

            }
            return mr;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        pool.checkIn(connection);
        return mr;
    }

    public DailyReport getLast() throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();
        DailyReport mr = new DailyReport();

        String query ="SELECT * FROM monthly_report ORDER BY " + ID_REPORT
                + " DESC  LIMIT 1";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            while (rs.next()){

                mr.setIdreport(rs.getInt(ID_REPORT));
                mr.setEmployeename(rs.getString(EMPLOYEE_NAME));
                mr.setBasicRate(rs.getFloat(BASIC_RATE));
                mr.setnWorkingDays(rs.getFloat(NO_WORKING_DAYS));
                mr.settDaysReported(rs.getFloat(DAYS_RENDERED));
                mr.setGrossPay(rs.getFloat(GROSS_PAY));
                mr.setStatutory_sss(rs.getFloat(STATUTORY_SSS));
                mr.setStatutory_pagibig(rs.getFloat(STATUTORY_PAGIBIG));
                mr.setStatutory_philhealth(rs.getFloat(STATUTORY_PHILHEALTH));
                mr.setStatutory_escola(rs.getFloat(STATUTORY_ESCOLA));
                mr.setTotalStatutory(rs.getFloat(TOTAL_STATUTORY));
                mr.setThirteenth_month(rs.getFloat(THIRTHEENTH_MONTH_PAY));
                mr.setIncentive(rs.getFloat(INCENTIVE));
                mr.setTotal(rs.getFloat(TOTAL));
                mr.setAdmin_fee(rs.getFloat(ADMIN_FEE));
                mr.setNet_payroll(rs.getFloat(NET_PAYROLL));
                mr.setVersion(rs.getInt(VERSION));

            }
            return mr;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        pool.checkIn(connection);
        return mr;
    }

    //pass the username of the account that wants to be change and an account class with COMPLETE information including the updates
    public boolean update(String employeename, DailyReport m) throws SQLException {
        //UPDATE
        // Get a connection:
        Connection connection = pool.checkOut();


        String query = "UPDATE monthly_report SET "
                + EMPLOYEE_NAME + " = ?, "  + BASIC_RATE + " = ?, "
                + NO_WORKING_DAYS + " = ?, " + DAYS_RENDERED + " = ?, "
                + GROSS_PAY + " = ?, "
                + STATUTORY_SSS + " = ?, " + STATUTORY_PAGIBIG + " = ?, "
                + STATUTORY_PHILHEALTH + " = ?, " + STATUTORY_ESCOLA + " = ?, "
                + TOTAL_STATUTORY + " = ?, "
                + THIRTHEENTH_MONTH_PAY + " = ?, "
                + INCENTIVE + " = ?, "
                + TOTAL + " = ?, "
                + ADMIN_FEE + " = ?, "
                + NET_PAYROLL + " = ?, "
                + VERSION + " = ? "
                + "WHERE "+ ID_REPORT + " = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            statement.setString(1, m.getEmployeename());
            statement.setFloat(2, m.getBasicRate());
            statement.setFloat(3, m.getnWorkingDays());
            statement.setFloat(4, m.gettDaysReported());
            statement.setFloat(5, m.getGrossPay());
            statement.setFloat(6, m.getStatutory_sss());
            statement.setFloat(7, m.getStatutory_pagibig());
            statement.setFloat(8, m.getStatutory_philhealth());
            statement.setFloat(9, m.getStatutory_escola());
            statement.setFloat(10, m.getTotalStatutory());
            statement.setFloat(11, m.getThirteenth_month());
            statement.setFloat(12, m.getIncentive());
            statement.setFloat(13, m.getTotal());
            statement.setFloat(14, m.getAdmin_fee());
            statement.setFloat(15, m.getNet_payroll());
            statement.setInt(16, m.getVersion());
            statement.setInt(17, m.getIdreport());

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
