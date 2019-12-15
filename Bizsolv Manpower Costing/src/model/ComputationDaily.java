package model;

public class ComputationDaily extends Computation {

    private double allowance, subTotal, adminCost,daysOfIncentiveLeave;

    public ComputationDaily(double basicSalary, int workingDays, double daysOfIncentiveLeave, double allowance, double adminCost) {
        super(basicSalary, workingDays);
        this.allowance = allowance;
        this.daysOfIncentiveLeave = daysOfIncentiveLeave;
        this.subTotal = this.getBasicSalary() + this.getMonthBonus() + getNumOfDayIncentive() + this.allowance;
        this.adminCost = adminCost;
    }

    public double getNumOfDayIncentive() {
        return (this.daysOfIncentiveLeave * this.getBasicSalary())/12;
    }

    public double getEquivalentMonthlyCost() {
        return (this.subTotal * this.getWorkingDays())/12;
    }

    public double getEffectiveMonthlyRate() {
        return (this.getBasicSalary() * this.getWorkingDays())/12;
    }

    public double getAllowance() {
        return allowance;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getAdminCost() { return adminCost; }
}