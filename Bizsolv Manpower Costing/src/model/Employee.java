package model;

public class Employee {
    private int idemployee, status;
    private String firstname, lastname, province, address, name, statusLabel;

    public Employee() {
    }

    public Employee(String firstname, String lastname, String province, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.province = province;
        this.address = address;
        this.status = 1;
        this.name = this.lastname + ", " + this.firstname;
    }

    public int getIdemployee() {
        return idemployee;
    }

    public void setIdemployee(int idemployee) {
        this.idemployee = idemployee;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setName(String name) { this.name = name; }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        if(status == 0) {
            setStatusLabel("Freeze");
        } else {
            setStatusLabel("Active");
        }
    }

    public String getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(String statusLabel) {
        this.statusLabel = statusLabel;
    }
}
