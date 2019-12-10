package model;

import controller.ProvinceReader;
import controller.SSSReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class Master {
    private AccountService accountService;
    private EmployeeService employeeService;
    private DailyReportService dailyReportService;
    private MonthlyReportService monthlyReportService;
    private Account currentAccount;
    private Employee currentEmployee;
    private Report currentReport;
    private SSSReader sssReader;
    private ProvinceReader provinceReader;

    // data needed for generating new pdf
    private String fileName, fileDestination, version, fileTime;

    private ObservableList<Employee> employees;
    private ObservableList<Province> provinces;
    private ObservableList<SSS> sss;

    public Master() {
        this.accountService = new AccountService();
        this.employeeService = new EmployeeService();
        this.dailyReportService = new DailyReportService();
        this.monthlyReportService = new MonthlyReportService();
        this.sssReader = new SSSReader(this);
        this.provinceReader = new ProvinceReader(this);
        employees = FXCollections.observableArrayList();
        provinces = FXCollections.observableArrayList();
        sss = FXCollections.observableArrayList();
        this.sssReader.readSSS();
        this.provinceReader.readProvince();
        setup();
    }

    public ObservableList<SSS> getSSS() {
        return sss;
    }

    public ObservableList<Employee> getAllEmployees() {
        updateEmployeeList();
        return employees;
    }

    public ObservableList<String> getAllEmployeesName() {
        ObservableList<Employee> emp = FXCollections.observableArrayList();
        try {
            emp = employeeService.getUnfreezeEmployees();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> names = FXCollections.observableArrayList();
        for(int i = 0; i < emp.size(); i++) {
            names.add(emp.get(i).getName());
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

    public void updateEmployee(Employee employee) {
        try {
            employeeService.update(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Province> getAllProvinces() {
        return provinces;
    }

    public ObservableList<String> getAllProvincesNames() {
        provinceReader.readProvince();
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

    public int getNumberOfDailyReport() {
        try {
            return dailyReportService.getEmployeeCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getNumberOfMonthlyReport() {
        try {
            return monthlyReportService.getEmployeeCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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

    public void updateEmployeeList() {
        try {
            employees = employeeService.getUnfreezeEmployees();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account getAccountByUsername(String username) {
        Account a = null;
        try {
            a = accountService.getAccountByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    public void addAccount(Account a) {
        try {
            accountService.add(a);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setup() {
        Account account = null;
        try {
            account = accountService.getAccountByUsername("admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(account == null) {
            Account a = new Account();
            a.setAccounttype("admin");
            a.setUsername("admin");
            a.setPassword("admin");
            try {
                accountService.add(a);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Account getCurrentAccount() { return currentAccount; }

    public void setCurrentAccount(Account currentAccount) { this.currentAccount = currentAccount; }

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
