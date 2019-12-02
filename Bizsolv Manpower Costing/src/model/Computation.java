package model;

public abstract class Computation {

    private int workingDays;
    private float basicSalary, totalGovernmentalCost, totalLaborCost, bizsolvAdminCost, contractCost;

    public Computation(float basicSalary, int workingDays) {
        this.basicSalary = basicSalary;
        this.workingDays = workingDays;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public float getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(float basicSalary) {
        this.basicSalary = basicSalary;
    }

    public float getMonthBonus() {
        return basicSalary/12;
    }

    public void setGovernmentalCost(float value) { this.totalGovernmentalCost = value; }

    public float getTotalGovernmentalCost() { return totalGovernmentalCost; }

    public void setTotalLaborCost(float value) { this.totalLaborCost = value; }

    public float getTotalLaborCost() { return this.totalLaborCost; }

    public void setBizsolvAdminCost(float value) { this.bizsolvAdminCost = value; }

    public float getBizsolvAdminCost() { return this.bizsolvAdminCost; }

    public void setContractCost(float value) { this.contractCost = value; }

    public float getContractCost() { return this.contractCost; }

    abstract public float getNumOfDayIncentive();
    abstract public float getEquivalentMonthlyCost();
    abstract public float getEffectiveMonthlyRate();
    abstract public float getAllowance();
    abstract public float getSubTotal();
    abstract public float getAdminCost();
}
