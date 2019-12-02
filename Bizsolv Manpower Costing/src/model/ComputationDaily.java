package model;

public class ComputationDaily extends Computation {

    private int daysOfIncentiveLeave;
    private float allowance, subTotal, adminCost;

    public ComputationDaily(float basicSalary, int workingDays, int daysOfIncentiveLeave, float allowance, float adminCost) {
        super(basicSalary, workingDays);
        this.allowance = allowance;
        this.daysOfIncentiveLeave = daysOfIncentiveLeave;
        this.subTotal = this.getBasicSalary() + this.getMonthBonus() + getNumOfDayIncentive() + this.allowance;
        this.adminCost = adminCost;
    }

    public float getNumOfDayIncentive() {
        return (this.daysOfIncentiveLeave * this.getBasicSalary())/12;
    }

    public float getEquivalentMonthlyCost() {
        return (this.subTotal * this.getWorkingDays())/12;
    }

    public float getEffectiveMonthlyRate() {
        return (this.getBasicSalary() * this.getWorkingDays())/12;
    }

    public float getAllowance() {
        return allowance;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public float getAdminCost() { return adminCost; }
}