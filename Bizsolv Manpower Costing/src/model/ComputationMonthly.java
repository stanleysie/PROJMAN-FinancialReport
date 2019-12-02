package model;

public class ComputationMonthly extends Computation {

    private int daysOfIncentiveLeave;
    private float allowance, subTotal, adminCost;

    public ComputationMonthly(float basicSalary, int workingDays, int daysOfIncentiveLeave, float allowance, float adminCost) {
        super(basicSalary, workingDays);
        this.setBasicSalary(basicSalary);
        this.allowance = allowance;
        this.daysOfIncentiveLeave = daysOfIncentiveLeave;
        this.subTotal = this.getBasicSalary() + this.getMonthBonus() + getNumOfDayIncentive() + this.allowance;
        this.adminCost = adminCost;
    }

    public float getNumOfDayIncentive() {
        return (daysOfIncentiveLeave * 12)/this.getWorkingDays();
    }

    public float getEquivalentMonthlyCost() {
        return (this.getBasicSalary() + this.getMonthBonus() + getNumOfDayIncentive() + this.allowance);
    }

    public float getEffectiveMonthlyRate() {
        return this.getBasicSalary();
    }

    public float getAllowance() {
        return allowance;
    }

    public float getSubTotal() { return subTotal; }

    public float getAdminCost() { return adminCost; }
}
