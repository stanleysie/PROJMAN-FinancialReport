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
    private final String EQUIVALENT_MONTHLY_COST = "equivalent_monthly_cost";
    private final String EFFECTIVE_MONTHLY_RATE = "effective_monthly_rate";
    private final String STATUTORY_SSS = "statutory_sss";
    private final String STATUTORY_PAGIBIG = "statutory_pagibig";
    private final String STATUTORY_PHILHEALTH = "statutory_philhealth";
    private final String STATUTORY_ECOLA = "statutory_ecola";
    private final String TOTAL_STATUTORY = "total_statutory";
    private final String THIRTHEENTH_MONTH_PAY = "13th_month";
    private final String INCENTIVE = "incentive";
    private final String TOTAL_LABOR_COST = "total_labor_cost";
    private final String ADMIN_COST = "admin_cost";
    private final String CONTRACT_COST = "contract_cost";
    private final String VERSION = "version";
    private final String ALLOWANCE = "allowance";
    private final String CREATOR = "creator";
    private final String OTHER_NAME = "other_name";
    private final String OTHER_VALUE = "other_value";

    public DailyReportService() {
        pool = new JDBCConnectionPool();
    }

    public boolean add(DailyReport m, Employee employee) throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();
        String query = "INSERT INTO daily_report VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        try {
            DailyReport temp = getLast();
            m.setIdreport(temp.getIdreport() + 1);
            statement.setInt(1, m.getIdreport());
            statement.setString(2,employee.getLastname() + ", " + employee.getFirstname());
            statement.setDouble(3, m.getBasicRate());
            statement.setInt(4, m.getnWorkingDays());
            statement.setDouble(5, m.getequivalentMonthlyCost());
            statement.setDouble(6, m.geteffectiveMonthlyRate());
            statement.setDouble(7, m.getStatutory_sss());
            statement.setDouble(8, m.getStatutory_pagibig());
            statement.setDouble(9, m.getStatutory_philhealth());
            statement.setDouble(10, m.getStatutory_escola());
            statement.setDouble(11, m.getTotalStatutory());
            statement.setDouble(12, m.getThirteenth_month());
            statement.setDouble(13, m.getIncentive());
            statement.setDouble(14, m.getTotal());
            statement.setDouble(15, m.getadmin_cost());
            statement.setDouble(16, m.getcontractCost());
            statement.setString(17, m.getVersion());
            statement.setDouble(18, m.getAllowance());
            statement.setString(19, m.getCreator());
            statement.setString(20, m.getOtherName());
            statement.setDouble(21, m.getOtherValue());

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
                mr.setBasicRate(rs.getDouble(BASIC_RATE));
                mr.setnWorkingDays(rs.getInt(NO_WORKING_DAYS));
                mr.setequivalentMonthlyCost(rs.getDouble(EQUIVALENT_MONTHLY_COST));
                mr.seteffectiveMonthlyRate(rs.getDouble(EFFECTIVE_MONTHLY_RATE));
                mr.setStatutory_sss(rs.getDouble(STATUTORY_SSS));
                mr.setStatutory_pagibig(rs.getDouble(STATUTORY_PAGIBIG));
                mr.setStatutory_philhealth(rs.getDouble(STATUTORY_PHILHEALTH));
                mr.setStatutory_escola(rs.getDouble(STATUTORY_ECOLA));
                mr.setTotalStatutory(rs.getDouble(TOTAL_STATUTORY));
                mr.setThirteenth_month(rs.getDouble(THIRTHEENTH_MONTH_PAY));
                mr.setIncentive(rs.getDouble(INCENTIVE));
                mr.setTotal(rs.getDouble(TOTAL_LABOR_COST));
                mr.setadmin_cost(rs.getDouble(ADMIN_COST));
                mr.setcontractCost(rs.getDouble(CONTRACT_COST));
                mr.setVersion(rs.getString(VERSION));
                mr.setAllowance(rs.getDouble(ALLOWANCE));
                mr.setCreator(rs.getString(CREATOR));
                mr.setOtherName(rs.getString(OTHER_NAME));
                mr.setOtherValue(rs.getDouble(OTHER_VALUE));

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

    public int getEmployeeCount() throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();
        int total = 0;

        String query ="SELECT count(idreport) AS total FROM daily_report";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                total = rs.getInt("total");
            }
            return total;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        pool.checkIn(connection);
        return total;
    }

    public DailyReport getEmployeeDailyReport(String version) throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();
        DailyReport mr = null;

        String query ="SELECT * FROM daily_report WHERE version = '" + version + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                mr = new DailyReport();
                mr.setIdreport(rs.getInt(ID_REPORT));
                mr.setEmployeename(rs.getString(EMPLOYEE_NAME));
                mr.setBasicRate(rs.getDouble(BASIC_RATE));
                mr.setnWorkingDays(rs.getInt(NO_WORKING_DAYS));
                mr.setequivalentMonthlyCost(rs.getDouble(EQUIVALENT_MONTHLY_COST));
                mr.seteffectiveMonthlyRate(rs.getDouble(EFFECTIVE_MONTHLY_RATE));
                mr.setStatutory_sss(rs.getDouble(STATUTORY_SSS));
                mr.setStatutory_pagibig(rs.getDouble(STATUTORY_PAGIBIG));
                mr.setStatutory_philhealth(rs.getDouble(STATUTORY_PHILHEALTH));
                mr.setStatutory_escola(rs.getDouble(STATUTORY_ECOLA));
                mr.setTotalStatutory(rs.getDouble(TOTAL_STATUTORY));
                mr.setThirteenth_month(rs.getDouble(THIRTHEENTH_MONTH_PAY));
                mr.setIncentive(rs.getDouble(INCENTIVE));
                mr.setTotal(rs.getDouble(TOTAL_LABOR_COST));
                mr.setadmin_cost(rs.getDouble(ADMIN_COST));
                mr.setcontractCost(rs.getDouble(CONTRACT_COST));
                mr.setVersion(rs.getString(VERSION));
                mr.setAllowance(rs.getDouble(ALLOWANCE));
                mr.setCreator(rs.getString(CREATOR));
                mr.setOtherName(rs.getString(OTHER_NAME));
                mr.setOtherValue(rs.getDouble(OTHER_VALUE));
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

        String query ="SELECT * FROM daily_report ORDER BY " + ID_REPORT
                + " DESC  LIMIT 1";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            while (rs.next()){

                mr.setIdreport(rs.getInt(ID_REPORT));
                mr.setEmployeename(rs.getString(EMPLOYEE_NAME));
                mr.setBasicRate(rs.getDouble(BASIC_RATE));
                mr.setnWorkingDays(rs.getInt(NO_WORKING_DAYS));
                mr.setequivalentMonthlyCost(rs.getDouble(EQUIVALENT_MONTHLY_COST));
                mr.seteffectiveMonthlyRate(rs.getDouble(EFFECTIVE_MONTHLY_RATE));
                mr.setStatutory_sss(rs.getDouble(STATUTORY_SSS));
                mr.setStatutory_pagibig(rs.getDouble(STATUTORY_PAGIBIG));
                mr.setStatutory_philhealth(rs.getDouble(STATUTORY_PHILHEALTH));
                mr.setStatutory_escola(rs.getDouble(STATUTORY_ECOLA));
                mr.setTotalStatutory(rs.getDouble(TOTAL_STATUTORY));
                mr.setThirteenth_month(rs.getDouble(THIRTHEENTH_MONTH_PAY));
                mr.setIncentive(rs.getDouble(INCENTIVE));
                mr.setTotal(rs.getDouble(TOTAL_LABOR_COST));
                mr.setadmin_cost(rs.getDouble(ADMIN_COST));
                mr.setcontractCost(rs.getDouble(CONTRACT_COST));
                mr.setVersion(rs.getString(VERSION));
                mr.setAllowance(rs.getDouble(ALLOWANCE));
                mr.setCreator(rs.getString(CREATOR));
                mr.setOtherName(rs.getString(OTHER_NAME));
                mr.setOtherValue(rs.getDouble(OTHER_VALUE));
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
}
