package model;

public class Province {

    private int idprovince;
    private String provincename;
    private float salarymin, Salarymax;

    public Province() {
    }

    public int getIdprovince() {
        return idprovince;
    }

    public void setIdprovince(int idprovince) {
        this.idprovince = idprovince;
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename;
    }

    public float getSalarymin() {
        return salarymin;
    }

    public void setSalarymin(float salarymin) {
        this.salarymin = salarymin;
    }

    public float getSalarymax() {
        return Salarymax;
    }

    public void setSalarymax(float Salarymax) {
        this.Salarymax = Salarymax;
    }
}
