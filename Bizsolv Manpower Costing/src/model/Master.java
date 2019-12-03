package model;

import controller.SSSReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class Master {
    private EmployeeService employeeService;
    private ProvinceService provinceService;
    private DailyReportService dailyReportService;
    private MonthlyReportService monthlyReportService;
    private Employee currentEmployee;
    private Report currentReport;
    private SSSReader sssReader;

    // data needed for generating new pdf
    private String fileName, fileDestination, version, fileTime;

    private ObservableList<Employee> employees;
    private ObservableList<Province> provinces;
    private ObservableList<SSS> sss;

    public Master() {
        this.employeeService = new EmployeeService();
        this.provinceService = new ProvinceService();
        this.dailyReportService = new DailyReportService();
        this.monthlyReportService = new MonthlyReportService();
        this.sssReader = new SSSReader(this);
        employees = FXCollections.observableArrayList();
        provinces = FXCollections.observableArrayList();
        sss = FXCollections.observableArrayList();
        this.sssReader.readSSS();
    }

    public ObservableList<SSS> getSSS() {
        return sss;
    }

    public ObservableList<Employee> getAllEmployees() {
        updateEmployeeList();
        return employees;
    }

    public ObservableList<String> getAllEmployeesName() {
        updateEmployeeList();
        ObservableList<String> names = FXCollections.observableArrayList();
        for(int i = 0; i < employees.size(); i++) {
            names.add(employees.get(i).getName());
        }
        return names;
    }

    public void addEmployee(Employee employee) {
        try {
            employeeService.add(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Province> getAllProvinces() {
        updateProvinceList();
        return provinces;
    }

    public ObservableList<String> getAllProvincesNames() {
        updateProvinceList();
        ObservableList<String> names = FXCollections.observableArrayList();
        for(int i = 0; i < provinces.size(); i++) {
            names.add(provinces.get(i).getProvincename());
        }
        return names;
    }

    public ObservableList<Report> getEmployeesVersion(String name) {
        ObservableList<DailyReport> daily = getEmployeeDailyVersion(name);
        ObservableList<MonthlyReport> monthly = getEmployeeMonthlyVersion(name);
        ObservableList<Report> versions = FXCollections.observableArrayList();
        for(int i = 0; i < daily.size(); i++) {
            versions.add(daily.get(i));
        }
        for(int i = 0; i < monthly.size(); i++) {
            versions.add(monthly.get(i));
        }
        return versions;
    }

    public ObservableList<DailyReport> getEmployeeDailyVersion(String name) {
        try {
            return dailyReportService.getEmployeeVersions(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<MonthlyReport> getEmployeeMonthlyVersion(String name) {
        try {
            return monthlyReportService.getEmployeeVersions(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addDailyReport(DailyReport daily) {
        try {
            dailyReportService.add(daily, currentEmployee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMonthlyReport(MonthlyReport monthly) {
        try {
            monthlyReportService.add(monthly, currentEmployee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getReport(String version) {
        Report daily = null, monthly = null;
        try {
            daily = dailyReportService.getEmployeeDailyReport(version);
            monthly = monthlyReportService.getEmployeeMontlyReport(version);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(daily != null) {
            setVersion(((DailyReport) daily).getVersion());
            setFileName(((DailyReport) daily).getEmployeename().replace(", ", "_"));
            currentReport = daily;
        } else if(monthly != null) {
            setVersion(((MonthlyReport) monthly).getVersion());
            setFileName(((MonthlyReport) monthly).getEmployeename().replace(", ", "_"));
            currentReport = monthly;
        }
    }

    public void updateProvinceList() {
        try {
            provinces = provinceService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployeeList() {
        try {
            employees = employeeService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Employee getCurrentEmployee() {
        return currentEmployee;
    }

    public void setCurrentEmployee(Employee currentEmployee) {
        this.currentEmployee = currentEmployee;
    }

    public Report getCurrentReport() { return this.currentReport; }

    public void setFileDestination(String dest) { this.fileDestination = dest; }

    public String getFileDestination() { return this.fileDestination; }

    public String getFileName() { return fileName; }

    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getVersion() { return version; }

    public void setVersion(String version) { this.version = version; }

    public String getFileTime() { return fileTime; }

    public void setFileTime(String fileTime) { this.fileTime = fileTime; }
}
