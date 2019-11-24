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
}
