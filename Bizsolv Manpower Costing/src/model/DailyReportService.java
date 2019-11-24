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
    private final String NO_ABSENCE = "no_absence";
    private final String DAYS_RENDERED = "total_days_rendered";
    private final String LATE_HOURS = "late_hours";
    private final String LATE_RATE = "late_rate";
    private final String LATE_DEDUCTION = "late_deduction";
    private final String UNDERTIME_HOURS = "undertime_hours";
    private final String UNDERTIME_RATE = "undertime_rate";
    private final String UNDERTIME_DEDUCTION = "undertime_deduction";
    private final String TOTAL_DEDUCTION = "total_deduction";
    private final String NIGHT_DIFF_HOURS = "night_diff_hours";
    private final String NIGHT_DIFF_RATE = "night_diff_rate";
    private final String NIGHT_DIFF_EARNING = "night_diff_earning";
    private final String OVERTIME_REGDAY_HOURS = "ot_regday_hours";
    private final String OVERTIME_REGDAY_RATE = "ot_regday_rate";
    private final String OVERTIME_REGDAY_EARNING = "ot_regday_earning";
    private final String RD_SH_HOURS = "rd_or_sh_hours";
    private final String RD_SH_RATE = "rd_or_sh_rate";
    private final String RD_SH_EARNING = "rd_or_sh_earning";
    private final String SHRD_HOURS = "sh_and_rd_hours";
    private final String SHRD_RATE = "sh_and_rd_rate";
    private final String SHRD_EARNING = "sh_and_rd_earning";
    private final String LH_HOURS = "lh_hours";
    private final String LH_RATE = "lh_rate";
    private final String LH_EARNING = "lh_earning";
    private final String LHRD_HOURS = "lh_and_rd_hours";
    private final String LHRD_RATE = "lh_and_rd_rate";
    private final String LHRD_EARNING = "lh_and_rd_earning";
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
        String query = "INSERT INTO monthly_report VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        try {
            DailyReport temp = getLast();
            statement.setInt(1, temp.getIdreport() + 1);
            statement.setString(2,employee.getFirstname() + " " + employee.getLastname());
            statement.setFloat(3, m.getBasicRate());
            statement.setFloat(4, m.getnWorkingDays());
            statement.setInt(5, m.getnAbscence());
            statement.setFloat(6, m.gettDaysReported());
            statement.setFloat(7, m.getLateHours());
            statement.setFloat(8, m.getLateRate());
            statement.setFloat(9, m.getLateDeduction());
            statement.setFloat(10, m.getUndertimeHours());
            statement.setFloat(11, m.getUndertimeRate());
            statement.setFloat(12, m.getUndertimeDeduction());
            statement.setFloat(13, m.getTotalDeduction());
            statement.setFloat(14, m.getNight_Diff_Hours());
            statement.setFloat(15, m.getNight_Diff_Rate());
            statement.setFloat(16, m.getNight_Diff_Earning());
            statement.setFloat(17, m.getOt_RegDay_Hours());
            statement.setFloat(18, m.getOt_RegDay_Rate());
            statement.setFloat(19, m.getOt_RegDay_Earning());
            statement.setFloat(20, m.getRd_sh_Hours());
            statement.setFloat(21, m.getRd_sh_Rate());
            statement.setFloat(22, m.getRd_sh_Earning());
            statement.setFloat(23, m.getShrd_Hours());
            statement.setFloat(24, m.getShrd_Rate());
            statement.setFloat(25, m.getShrd_Earning());
            statement.setFloat(26, m.getLh_Hours());
            statement.setFloat(27, m.getLh_Rate());
            statement.setFloat(28, m.getLh_Earning());
            statement.setFloat(29, m.getLhrd_Hours());
            statement.setFloat(30, m.getLhrd_Rate());
            statement.setFloat(31, m.getLhrd_Earning());
            statement.setFloat(32, m.getGrossPay());
            statement.setFloat(33, m.getStatutory_sss());
            statement.setFloat(34, m.getStatutory_pagibig());
            statement.setFloat(35, m.getStatutory_philhealth());
            statement.setFloat(36, m.getStatutory_escola());
            statement.setFloat(37, m.getTotalStatutory());
            statement.setFloat(38, m.getThirteenth_month());
            statement.setFloat(39, m.getIncentive());
            statement.setFloat(40, m.getTotal());
            statement.setFloat(41, m.getAdmin_fee());
            statement.setFloat(42, m.getNet_payroll());
            temp = getEmployeeLastVersions(m.getEmployeename());
            statement.setInt(46, temp.getVersion() + 1);

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
                mr.setnAbscence(rs.getInt(NO_ABSENCE));
                mr.settDaysReported(rs.getFloat(DAYS_RENDERED));
                mr.setLateHours(rs.getFloat(LATE_HOURS));
                mr.setLateRate(rs.getFloat(LATE_RATE));
                mr.setLateDeduction(rs.getFloat(LATE_DEDUCTION));
                mr.setUndertimeHours(rs.getFloat(UNDERTIME_HOURS));
                mr.setUndertimeRate(rs.getFloat(UNDERTIME_RATE));
                mr.setUndertimeDeduction(rs.getFloat(UNDERTIME_DEDUCTION));
                mr.setTotalDeduction(rs.getFloat(TOTAL_DEDUCTION));
                mr.setNight_Diff_Hours(rs.getFloat(NIGHT_DIFF_HOURS));
                mr.setNight_Diff_Rate(rs.getFloat(NIGHT_DIFF_RATE));
                mr.setNight_Diff_Earning(rs.getFloat(NIGHT_DIFF_EARNING));
                mr.setOt_RegDay_Hours(rs.getFloat(OVERTIME_REGDAY_HOURS));
                mr.setOt_RegDay_Rate(rs.getFloat(OVERTIME_REGDAY_RATE));
                mr.setOt_RegDay_Earning(rs.getFloat(OVERTIME_REGDAY_EARNING));
                mr.setRd_sh_Hours(rs.getFloat(RD_SH_HOURS));
                mr.setRd_sh_Rate(rs.getFloat(RD_SH_RATE));
                mr.setRd_sh_Earning(rs.getFloat(RD_SH_EARNING));
                mr.setShrd_Hours(rs.getFloat(SHRD_HOURS));
                mr.setShrd_Rate(rs.getFloat(SHRD_RATE));
                mr.setShrd_Earning(rs.getFloat(SHRD_EARNING));
                mr.setLh_Hours(rs.getFloat(LH_HOURS));
                mr.setLh_Rate(rs.getFloat(LH_RATE));
                mr.setLh_Earning(rs.getFloat(LH_EARNING));
                mr.setLh_Hours(rs.getFloat(LHRD_HOURS));
                mr.setLh_Rate(rs.getFloat(LHRD_RATE));
                mr.setLh_Earning(rs.getFloat(LHRD_EARNING));
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
                mr.setnAbscence(rs.getInt(NO_ABSENCE));
                mr.settDaysReported(rs.getFloat(DAYS_RENDERED));
                mr.setLateHours(rs.getFloat(LATE_HOURS));
                mr.setLateRate(rs.getFloat(LATE_RATE));
                mr.setLateDeduction(rs.getFloat(LATE_DEDUCTION));
                mr.setUndertimeHours(rs.getFloat(UNDERTIME_HOURS));
                mr.setUndertimeRate(rs.getFloat(UNDERTIME_RATE));
                mr.setUndertimeDeduction(rs.getFloat(UNDERTIME_DEDUCTION));
                mr.setTotalDeduction(rs.getFloat(TOTAL_DEDUCTION));
                mr.setNight_Diff_Hours(rs.getFloat(NIGHT_DIFF_HOURS));
                mr.setNight_Diff_Rate(rs.getFloat(NIGHT_DIFF_RATE));
                mr.setNight_Diff_Earning(rs.getFloat(NIGHT_DIFF_EARNING));
                mr.setOt_RegDay_Hours(rs.getFloat(OVERTIME_REGDAY_HOURS));
                mr.setOt_RegDay_Rate(rs.getFloat(OVERTIME_REGDAY_RATE));
                mr.setOt_RegDay_Earning(rs.getFloat(OVERTIME_REGDAY_EARNING));
                mr.setRd_sh_Hours(rs.getFloat(RD_SH_HOURS));
                mr.setRd_sh_Rate(rs.getFloat(RD_SH_RATE));
                mr.setRd_sh_Earning(rs.getFloat(RD_SH_EARNING));
                mr.setShrd_Hours(rs.getFloat(SHRD_HOURS));
                mr.setShrd_Rate(rs.getFloat(SHRD_RATE));
                mr.setShrd_Earning(rs.getFloat(SHRD_EARNING));
                mr.setLh_Hours(rs.getFloat(LH_HOURS));
                mr.setLh_Rate(rs.getFloat(LH_RATE));
                mr.setLh_Earning(rs.getFloat(LH_EARNING));
                mr.setLh_Hours(rs.getFloat(LHRD_HOURS));
                mr.setLh_Rate(rs.getFloat(LHRD_RATE));
                mr.setLh_Earning(rs.getFloat(LHRD_EARNING));
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
                mr.setnAbscence(rs.getInt(NO_ABSENCE));
                mr.settDaysReported(rs.getFloat(DAYS_RENDERED));
                mr.setLateHours(rs.getFloat(LATE_HOURS));
                mr.setLateRate(rs.getFloat(LATE_RATE));
                mr.setLateDeduction(rs.getFloat(LATE_DEDUCTION));
                mr.setUndertimeHours(rs.getFloat(UNDERTIME_HOURS));
                mr.setUndertimeRate(rs.getFloat(UNDERTIME_RATE));
                mr.setUndertimeDeduction(rs.getFloat(UNDERTIME_DEDUCTION));
                mr.setTotalDeduction(rs.getFloat(TOTAL_DEDUCTION));
                mr.setNight_Diff_Hours(rs.getFloat(NIGHT_DIFF_HOURS));
                mr.setNight_Diff_Rate(rs.getFloat(NIGHT_DIFF_RATE));
                mr.setNight_Diff_Earning(rs.getFloat(NIGHT_DIFF_EARNING));
                mr.setOt_RegDay_Hours(rs.getFloat(OVERTIME_REGDAY_HOURS));
                mr.setOt_RegDay_Rate(rs.getFloat(OVERTIME_REGDAY_RATE));
                mr.setOt_RegDay_Earning(rs.getFloat(OVERTIME_REGDAY_EARNING));
                mr.setRd_sh_Hours(rs.getFloat(RD_SH_HOURS));
                mr.setRd_sh_Rate(rs.getFloat(RD_SH_RATE));
                mr.setRd_sh_Earning(rs.getFloat(RD_SH_EARNING));
                mr.setShrd_Hours(rs.getFloat(SHRD_HOURS));
                mr.setShrd_Rate(rs.getFloat(SHRD_RATE));
                mr.setShrd_Earning(rs.getFloat(SHRD_EARNING));
                mr.setLh_Hours(rs.getFloat(LH_HOURS));
                mr.setLh_Rate(rs.getFloat(LH_RATE));
                mr.setLh_Earning(rs.getFloat(LH_EARNING));
                mr.setLh_Hours(rs.getFloat(LHRD_HOURS));
                mr.setLh_Rate(rs.getFloat(LHRD_RATE));
                mr.setLh_Earning(rs.getFloat(LHRD_EARNING));
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
                + NO_WORKING_DAYS + " = ?, " + NO_ABSENCE + " = ?, " + DAYS_RENDERED + " = ?, "
                + LATE_HOURS + " = ?, "  + LATE_RATE + " = ?, "  + LATE_DEDUCTION + " = ?, "
                + UNDERTIME_HOURS + " = ?, "  + UNDERTIME_RATE + " = ?, "  + UNDERTIME_DEDUCTION + " = ?, "
                + TOTAL_DEDUCTION + " = ?, "
                + NIGHT_DIFF_HOURS + " = ?, "  + NIGHT_DIFF_RATE + " = ?, "  + NIGHT_DIFF_EARNING + " = ?, "
                + OVERTIME_REGDAY_HOURS + " = ?, "  + OVERTIME_REGDAY_RATE + " = ?, "  + OVERTIME_REGDAY_EARNING + " = ?, "
                + RD_SH_HOURS + " = ?, "  + RD_SH_RATE + " = ?, "  + RD_SH_EARNING + " = ?, "
                + SHRD_HOURS + " = ?, "  + SHRD_RATE + " = ?, "  + SHRD_EARNING + " = ?, "
                + LH_HOURS + " = ?, "  + LH_RATE + " = ?, "  + LH_EARNING + " = ?, "
                + LHRD_HOURS + " = ?, " + LHRD_RATE + " = ?, " + LH_EARNING + " = ?, "
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
            statement.setInt(4, m.getnAbscence());
            statement.setFloat(5, m.gettDaysReported());
            statement.setFloat(6, m.getLateHours());
            statement.setFloat( 7, m.getLateRate());
            statement.setFloat(8, m.getLateDeduction());
            statement.setFloat(9, m.getUndertimeHours());
            statement.setFloat(10, m.getUndertimeRate());
            statement.setFloat(11, m.getUndertimeDeduction());
            statement.setFloat(12, m.getTotalDeduction());
            statement.setFloat(13, m.getNight_Diff_Hours());
            statement.setFloat(14, m.getNight_Diff_Rate());
            statement.setFloat(15, m.getNight_Diff_Earning());
            statement.setFloat(16, m.getOt_RegDay_Hours());
            statement.setFloat(17, m.getOt_RegDay_Rate());
            statement.setFloat(18, m.getOt_RegDay_Earning());
            statement.setFloat(19, m.getRd_sh_Hours());
            statement.setFloat(20, m.getRd_sh_Rate());
            statement.setFloat(21, m.getRd_sh_Earning());
            statement.setFloat(22, m.getShrd_Hours());
            statement.setFloat(23, m.getShrd_Rate());
            statement.setFloat(24, m.getShrd_Earning());
            statement.setFloat(25, m.getLh_Hours());
            statement.setFloat(26, m.getLh_Rate());
            statement.setFloat(27, m.getLh_Earning());
            statement.setFloat(28, m.getLhrd_Hours());
            statement.setFloat(29, m.getLhrd_Rate());
            statement.setFloat(30, m.getLhrd_Earning());
            statement.setFloat(31, m.getGrossPay());
            statement.setFloat(32, m.getStatutory_sss());
            statement.setFloat(33, m.getStatutory_pagibig());
            statement.setFloat(34, m.getStatutory_philhealth());
            statement.setFloat(35, m.getStatutory_escola());
            statement.setFloat(36, m.getTotalStatutory());
            statement.setFloat(37, m.getThirteenth_month());
            statement.setFloat(38, m.getIncentive());
            statement.setFloat(39, m.getTotal());
            statement.setFloat(40, m.getAdmin_fee());
            statement.setFloat(41, m.getNet_payroll());
            statement.setInt(42, m.getVersion());
            statement.setInt(43, m.getIdreport());

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
