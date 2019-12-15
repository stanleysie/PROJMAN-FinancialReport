package model;

public class ComputationMonthly extends Computation {

    private double allowance, subTotal, adminCost, daysOfIncentiveLeave;

    public ComputationMonthly(double basicSalary, int workingDays, double daysOfIncentiveLeave, double allowance, double adminCost) {
        super(basicSalary, workingDays);
        this.setBasicSalary(basicSalary);
        this.allowance = allowance;
        this.daysOfIncentiveLeave = daysOfIncentiveLeave;
        this.subTotal = this.getBasicSalary() + this.getMonthBonus() + getNumOfDayIncentive() + this.allowance;
        this.adminCost = adminCost;
    }

    public double getNumOfDayIncentive() {
        return (daysOfIncentiveLeave * 12)/this.getWorkingDays();
    }

    public double getEquivalentMonthlyCost() {
        return (this.getBasicSalary() + this.getMonthBonus() + getNumOfDayIncentive() + this.allowance);
    }

    public double getEffectiveMonthlyRate() {
        return this.getBasicSalary();
    }

    public double getAllowance() {
        return allowance;
    }

    public double getSubTotal() { return subTotal; }

    public double getAdminCost() { return adminCost; }
}
