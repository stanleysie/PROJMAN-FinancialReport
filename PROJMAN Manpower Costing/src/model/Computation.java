package model;

public abstract class Computation {

    private String location;
    private int workingDays;
    private double basicSalary;

    public Computation(String location, int workingDays) {
        this.location = location;
        if(this.location.equalsIgnoreCase("ncr")) {
            setBasicSalary(537);
        } else if(this.location.equalsIgnoreCase("provincial")) {
            setBasicSalary(600);
        }
        this.workingDays = workingDays;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getMonthBonus() {
        return basicSalary/12;
    }

    abstract public double getNumOfDayIncentive();
    abstract public double getEquivalentMonthlyCost();
    abstract public double getEffectiveMonthlyRate();
    abstract public double getAllowance();
    abstract public double getSubTotal();

}
