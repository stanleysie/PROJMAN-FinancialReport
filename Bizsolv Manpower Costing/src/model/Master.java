package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class Master {
    private EmployeeService employeeService;
    private ProvinceService provinceService;
    private Employee currentEmployee;

    // data needed for generating new pdf
    private String fileName, name, address, rateType, location;
    private double basicSalary, adminCost, allowance;
    private int workingDays, incentiveLeave;

    private ObservableList<Employee> employees;
    private ObservableList<Province> provinces;

    public Master() {
        this.employeeService = new EmployeeService();
        this.provinceService = new ProvinceService();
        employees = FXCollections.observableArrayList();
        provinces = FXCollections.observableArrayList();
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getAdminCost() {
        return adminCost;
    }

    public void setAdminCost(double adminCost) {
        this.adminCost = adminCost;
    }

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public int getIncentiveLeave() {
        return incentiveLeave;
    }

    public void setIncentiveLeave(int incentiveLeave) {
        this.incentiveLeave = incentiveLeave;
    }
}
