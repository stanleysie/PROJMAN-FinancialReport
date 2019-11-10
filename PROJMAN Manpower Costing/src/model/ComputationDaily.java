package model;

public class ComputationDaily extends Computation {

    private int daysOfIncentiveLeave;
    private double allowance, subTotal;

    public ComputationDaily(String location, int workingDays, int daysOfIncentiveLeave, double allowance) {
        super(location, workingDays);
        this.allowance = allowance;
        this.daysOfIncentiveLeave = daysOfIncentiveLeave;
        this.subTotal = this.getBasicSalary() + this.getMonthBonus() + getNumOfDayIncentive() + this.allowance;
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
}