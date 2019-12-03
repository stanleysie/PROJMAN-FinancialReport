package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.effect.Effect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonthlyReportService {
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

    public MonthlyReportService() {
        pool = new JDBCConnectionPool();
    }

    public boolean add(MonthlyReport m, Employee employee) throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();
        String query = "INSERT INTO monthly_report VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        try {
            MonthlyReport temp = getLast();
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
            if(temp.getVersion() != null) {
                statement.setString(17, temp.getVersion() + "-1");
            } else {
                statement.setString(17, m.getVersion());
            }
            statement.setFloat(18, m.getAllowance());

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

    public ObservableList<MonthlyReport> getEmployeeVersions(String employeename) throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();
        ObservableList<MonthlyReport> monthlyReports = FXCollections.observableArrayList();

        String query ="SELECT * FROM monthly_report WHERE employeename = '" + employeename + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                MonthlyReport mr = new MonthlyReport();

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
                mr.setAllowance(rs.getFloat(ALLOWANCE));

                monthlyReports.add(mr);
            }
            return monthlyReports;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null)  connection.close();
        }
        pool.checkIn(connection);
        return monthlyReports;
    }

    public MonthlyReport getEmployeeMontlyReport(String version) throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();
        MonthlyReport mr = null;

        String query ="SELECT * FROM monthly_report WHERE version = '" + version + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        try {

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                mr = new MonthlyReport();
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
                mr.setAllowance(rs.getFloat(ALLOWANCE));

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

    public MonthlyReport getLast() throws SQLException {
        // Get a connection:
        Connection connection = pool.checkOut();
        MonthlyReport mr = new MonthlyReport();

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
                mr.setAllowance(rs.getFloat(ALLOWANCE));

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
//    public boolean update(String employeename, MonthlyReport m) throws SQLException {
//        //UPDATE
//        // Get a connection:
//        Connection connection = pool.checkOut();
//
//
//        String query = "UPDATE monthly_report SET "
//                + EMPLOYEE_NAME + " = ?, "  + BASIC_RATE + " = ?, "
//                + NO_WORKING_DAYS + " = ?, "  + EQUIVALENT_MONTHLY_COST + " = ?, "
//                + EFFECTIVE_MONTHLY_RATE + " = ?, "
//                + STATUTORY_SSS + " = ?, " + STATUTORY_PAGIBIG + " = ?, "
//                + STATUTORY_PHILHEALTH + " = ?, " + STATUTORY_ECOLA + " = ?, "
//                + TOTAL_STATUTORY + " = ?, "
//                + THIRTHEENTH_MONTH_PAY + " = ?, "
//                + INCENTIVE + " = ?, "
//                + TOTAL_LABOR_COST + " = ?, "
//                + ADMIN_COST + " = ?, "
//                + CONTRACT_COST + " = ?, "
//                + VERSION + " = ? "
//                + "WHERE "+ ID_REPORT + " = ?";
//        PreparedStatement statement = connection.prepareStatement(query);
//        try {
//            MonthlyReport temp = getLast();
//            statement.setString(1, m.getEmployeename());
//            statement.setFloat(3, m.getBasicRate());
//            statement.setFloat(4, m.getnWorkingDays());
//            statement.setFloat(5, m.getequivalentMonthlyCost());
//            statement.setFloat(6, m.geteffectiveMonthlyRate());
//            statement.setFloat(7, m.getStatutory_sss());
//            statement.setFloat(8, m.getStatutory_pagibig());
//            statement.setFloat(9, m.getStatutory_philhealth());
//            statement.setFloat(10, m.getStatutory_escola());
//            statement.setFloat(11, m.getTotalStatutory());
//            statement.setFloat(12, m.getThirteenth_month());
//            statement.setFloat(13, m.getIncentive());
//            statement.setFloat(14, m.getTotal());
//            statement.setFloat(15, m.getadmin_cost());
//            statement.setFloat(16, m.getcontractCost());
//            statement.setString(17, temp.getVersion() + "-1");
//            statement.setInt(18, m.getIdreport());
//
//            statement.executeUpdate();
//
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if(statement != null) statement.close();
//            if(connection != null)  connection.close();
//        }
//        pool.checkIn(connection);
//        return false;
//    }
}
