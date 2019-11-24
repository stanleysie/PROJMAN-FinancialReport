package model;

public abstract class Computation {

    private int workingDays;
    private double basicSalary;

    public Computation(double basicSalary, int workingDays) {
        this.basicSalary = basicSalary;
        this.workingDays = workingDays;
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
    abstract public double getAdminCost();

}
