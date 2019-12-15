package model;

public abstract class Computation {

    private int workingDays;
    private double basicSalary, totalGovernmentalCost, totalLaborCost, bizsolvAdminCost, contractCost;

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

    public void setGovernmentalCost(double value) { this.totalGovernmentalCost = value; }

    public double getTotalGovernmentalCost() { return totalGovernmentalCost; }

    public void setTotalLaborCost(double value) { this.totalLaborCost = value; }

    public double getTotalLaborCost() { return this.totalLaborCost; }

    public void setBizsolvAdminCost(double value) { this.bizsolvAdminCost = value; }

    public double getBizsolvAdminCost() { return this.bizsolvAdminCost; }

    public void setContractCost(double value) { this.contractCost = value; }

    public double getContractCost() { return this.contractCost; }

    abstract public double getNumOfDayIncentive();
    abstract public double getEquivalentMonthlyCost();
    abstract public double getEffectiveMonthlyRate();
    abstract public double getAllowance();
    abstract public double getSubTotal();
    abstract public double getAdminCost();
}
