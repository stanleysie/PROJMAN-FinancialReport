package model;

public class Province {
    private String provincename;
    private float salarymin, salarymax;

    public Province() {}

    public Province(String name, float min, float max) {
        this.provincename = name;
        this.salarymin = min;
        this.salarymax = max;
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
        return salarymax;
    }

    public void setSalarymax(float salarymax) {
        this.salarymax = salarymax;
    }

    @Override
    public boolean equals(Object obj) {
        Province p = (Province) obj;
        return p.getProvincename().equalsIgnoreCase(this.provincename);
    }
}
