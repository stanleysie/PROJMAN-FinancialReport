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

    public DailyReportService() {
        pool = new JDBCConnectionPool();
    }

    public boolean add(DailyReport m, Employee employee) throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();
        String query = "INSERT INTO daily_report VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        try {
            DailyReport temp = getLast();
            m.setIdreport(temp.getIdreport() + 1);
            statement.setInt(1, m.getIdreport());
            statement.setString(2,employee.getLastname() + ", " + employee.getFirstname());
            statement.setFloat(3, m.getBasicRate());
            statement.setFloat(4, m.getnWorkingDays());
            statement.setFloat(5, m.getequivalentMonthlyCost());
            statement.setFloat(6, m.geteffectiveMonthlyRate());
            statement.setFloat(7, m.getStatutory_sss());
            statement.setFloat(8, m.getStatutory_pagibig());
            statement.setFloat(9, m.getStatutory_philhealth());
            statement.setFloat(10, m.getStatutory_escola());
            statement.setFloat(11, m.getTotalStatutory());
            statement.setFloat(12, m.getThirteenth_month());
            statement.setFloat(13, m.getIncentive());
            statement.setFloat(14, m.getTotal());
            statement.setFloat(15, m.getadmin_cost());
            statement.setFloat(16, m.getcontractCost());
            temp = getEmployeeLastVersions(m.getEmployeename());
            if(temp != null) {
                statement.setString(17, temp.getVersion() + 1);
            } else {
                statement.setString(17, m.getVersion());
            }

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
                mr.setequivalentMonthlyCost(rs.getFloat(EQUIVALENT_MONTHLY_COST));
                mr.seteffectiveMonthlyRate(rs.getFloat(EFFECTIVE_MONTHLY_RATE));
                mr.setStatutory_sss(rs.getFloat(STATUTORY_SSS));
                mr.setStatutory_pagibig(rs.getFloat(STATUTORY_PAGIBIG));
                mr.setStatutory_philhealth(rs.getFloat(STATUTORY_PHILHEALTH));
                mr.setStatutory_escola(rs.getFloat(STATUTORY_ECOLA));
                mr.setTotalStatutory(rs.getFloat(TOTAL_STATUTORY));
                mr.setThirteenth_month(rs.getFloat(THIRTHEENTH_MONTH_PAY));
                mr.setIncentive(rs.getFloat(INCENTIVE));
                mr.setTotal(rs.getFloat(TOTAL_LABOR_COST));
                mr.setadmin_cost(rs.getFloat(ADMIN_COST));
                mr.setcontractCost(rs.getFloat(CONTRACT_COST));
                mr.setVersion(rs.getString(VERSION));

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

        String query ="SELECT * FROM daily_report WHERE employeename = '" + employeename + "' ORDER BY " + ID_REPORT
                + " DESC  LIMIT 1";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            while (rs.next()){

                mr.setIdreport(rs.getInt(ID_REPORT));
                mr.setEmployeename(rs.getString(EMPLOYEE_NAME));
                mr.setBasicRate(rs.getFloat(BASIC_RATE));
                mr.setnWorkingDays(rs.getFloat(NO_WORKING_DAYS));
                mr.setequivalentMonthlyCost(rs.getFloat(EQUIVALENT_MONTHLY_COST));
                mr.seteffectiveMonthlyRate(rs.getFloat(EFFECTIVE_MONTHLY_RATE));
                mr.setStatutory_sss(rs.getFloat(STATUTORY_SSS));
                mr.setStatutory_pagibig(rs.getFloat(STATUTORY_PAGIBIG));
                mr.setStatutory_philhealth(rs.getFloat(STATUTORY_PHILHEALTH));
                mr.setStatutory_escola(rs.getFloat(STATUTORY_ECOLA));
                mr.setTotalStatutory(rs.getFloat(TOTAL_STATUTORY));
                mr.setThirteenth_month(rs.getFloat(THIRTHEENTH_MONTH_PAY));
                mr.setIncentive(rs.getFloat(INCENTIVE));
                mr.setTotal(rs.getFloat(TOTAL_LABOR_COST));
                mr.setadmin_cost(rs.getFloat(ADMIN_COST));
                mr.setcontractCost(rs.getFloat(CONTRACT_COST));
                mr.setVersion(rs.getString(VERSION));

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
                mr.setBasicRate(rs.getFloat(BASIC_RATE));
                mr.setnWorkingDays(rs.getFloat(NO_WORKING_DAYS));
                mr.setequivalentMonthlyCost(rs.getFloat(EQUIVALENT_MONTHLY_COST));
                mr.seteffectiveMonthlyRate(rs.getFloat(EFFECTIVE_MONTHLY_RATE));
                mr.setStatutory_sss(rs.getFloat(STATUTORY_SSS));
                mr.setStatutory_pagibig(rs.getFloat(STATUTORY_PAGIBIG));
                mr.setStatutory_philhealth(rs.getFloat(STATUTORY_PHILHEALTH));
                mr.setStatutory_escola(rs.getFloat(STATUTORY_ECOLA));
                mr.setTotalStatutory(rs.getFloat(TOTAL_STATUTORY));
                mr.setThirteenth_month(rs.getFloat(THIRTHEENTH_MONTH_PAY));
                mr.setIncentive(rs.getFloat(INCENTIVE));
                mr.setTotal(rs.getFloat(TOTAL_LABOR_COST));
                mr.setadmin_cost(rs.getFloat(ADMIN_COST));
                mr.setcontractCost(rs.getFloat(CONTRACT_COST));
                mr.setVersion(rs.getString(VERSION));

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


        String query = "UPDATE daily_report SET "
                + EMPLOYEE_NAME + " = ?, "  + BASIC_RATE + " = ?, "
                + NO_WORKING_DAYS + " = ?, " + EQUIVALENT_MONTHLY_COST + " = ?, "
                + EFFECTIVE_MONTHLY_RATE + " = ?, "
                + STATUTORY_SSS + " = ?, " + STATUTORY_PAGIBIG + " = ?, "
                + STATUTORY_PHILHEALTH + " = ?, " + STATUTORY_ECOLA + " = ?, "
                + TOTAL_STATUTORY + " = ?, "
                + THIRTHEENTH_MONTH_PAY + " = ?, "
                + INCENTIVE + " = ?, "
                + TOTAL_LABOR_COST + " = ?, "
                + ADMIN_COST + " = ?, "
                + CONTRACT_COST + " = ?, "
                + VERSION + " = ? "
                + "WHERE "+ ID_REPORT + " = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            statement.setString(1, m.getEmployeename());
            statement.setFloat(2, m.getBasicRate());
            statement.setFloat(3, m.getnWorkingDays());
            statement.setFloat(4, m.getequivalentMonthlyCost());
            statement.setFloat(5, m.geteffectiveMonthlyRate());
            statement.setFloat(6, m.getStatutory_sss());
            statement.setFloat(7, m.getStatutory_pagibig());
            statement.setFloat(8, m.getStatutory_philhealth());
            statement.setFloat(9, m.getStatutory_escola());
            statement.setFloat(10, m.getTotalStatutory());
            statement.setFloat(11, m.getThirteenth_month());
            statement.setFloat(12, m.getIncentive());
            statement.setFloat(13, m.getTotal());
            statement.setFloat(14, m.getadmin_cost());
            statement.setFloat(15, m.getcontractCost());
            statement.setString(16, m.getVersion());
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
