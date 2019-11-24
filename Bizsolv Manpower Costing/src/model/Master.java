package model;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public class Master {
    private EmployeeService employeeService;
    private Employee currentEmployee;
    private String fileName;

    public Master() {
        this.employeeService = new EmployeeService();
    }

    public ObservableList<Employee> getAllEmployees() {
        try {
            return employeeService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addEmployee(Employee employee) {
        try {
            employeeService.add(employee);
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
}
