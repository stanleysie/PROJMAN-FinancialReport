package model;

public class SSS {
    private float minRange, maxRange, monthlySalaryCredit, ER, EE, total, EC;

    public SSS(float minRange, float maxRange, float monthlySalaryCredit, float ER, float EE, float total, float EC) {
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.monthlySalaryCredit = monthlySalaryCredit;
        this.ER = ER;
        this.EE = EE;
        this.total = total;
        this.EC = EC;
    }

    public float getMinRange() {
        return minRange;
    }

    public float getMaxRange() {
        return maxRange;
    }

    public float getMonthlySalaryCredit() {
        return monthlySalaryCredit;
    }

    public float getER() {
        return ER;
    }

    public float getEE() {
        return EE;
    }

    public float getTotal() {
        return total;
    }

    public float getEC() {
        return EC;
    }
}
