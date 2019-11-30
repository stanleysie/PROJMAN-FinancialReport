package model;

import controller.SSSReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class Master {
    private EmployeeService employeeService;
    private ProvinceService provinceService;
    private Employee currentEmployee;
    private SSSReader sssReader;

    // data needed for generating new pdf
    private String fileName, fileDestination, version, fileTime;

    private ObservableList<Employee> employees;
    private ObservableList<Province> provinces;
    private ObservableList<SSS> sss;

    public Master() {
        this.employeeService = new EmployeeService();
        this.provinceService = new ProvinceService();
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

    public void setFileDestination(String dest) { this.fileDestination = dest; }

    public String getFileDestination() { return this.fileDestination; }

    public String getFileName() { return fileName; }

    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getVersion() { return version; }

    public void setVersion(String version) { this.version = version; }

    public String getFileTime() { return fileTime; }

    public void setFileTime(String fileTime) { this.fileTime = fileTime; }
}
