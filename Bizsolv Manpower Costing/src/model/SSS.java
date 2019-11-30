package model;

public class SSS {
    private double minRange, maxRange, monthlySalaryCredit, ER, EE, total, EC;

    public SSS(double minRange, double maxRange, double monthlySalaryCredit, double ER, double EE, double total, double EC) {
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.monthlySalaryCredit = monthlySalaryCredit;
        this.ER = ER;
        this.EE = EE;
        this.total = total;
        this.EC = EC;
    }

    public double getMinRange() {
        return minRange;
    }

    public double getMaxRange() {
        return maxRange;
    }

    public double getMonthlySalaryCredit() {
        return monthlySalaryCredit;
    }

    public double getER() {
        return ER;
    }

    public double getEE() {
        return EE;
    }

    public double getTotal() {
        return total;
    }

    public double getEC() {
        return EC;
    }
}
