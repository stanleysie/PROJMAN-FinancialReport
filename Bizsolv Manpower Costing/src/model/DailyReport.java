package model;

public class DailyReport implements Report {
    int idreport;
    String employeename, version;
    float basicRate, nWorkingDays, equivalentMonthlyCost, effectiveMonthlyRate, statutory_sss,
            statutory_pagibig, statutory_philhealth,statutory_escola, totalStatutory, thirteenth_month,
            incentive, totalLaborCost, admin_cost, contractCost, allowance;

    public DailyReport() {
    }

    public int getIdreport() {
        return idreport;
    }

    public void setIdreport(int idreport) {
        this.idreport = idreport;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public float getBasicRate() {
        return basicRate;
    }

    public void setBasicRate(float basicRate) {
        this.basicRate = basicRate;
    }

    public float getnWorkingDays() {
        return nWorkingDays;
    }

    public void setnWorkingDays(float nWorkingDays) {
        this.nWorkingDays = nWorkingDays;
    }

    public float getequivalentMonthlyCost() {
        return equivalentMonthlyCost;
    }

    public void setequivalentMonthlyCost(float equivalentMonthlyCost) {
        this.equivalentMonthlyCost = equivalentMonthlyCost;
    }

    public float geteffectiveMonthlyRate() {
        return effectiveMonthlyRate;
    }

    public void seteffectiveMonthlyRate(float effectiveMonthlyRate) {
        this.effectiveMonthlyRate = effectiveMonthlyRate;
    }

    public float getStatutory_sss() {
        return statutory_sss;
    }

    public void setStatutory_sss(float statutory_sss) {
        this.statutory_sss = statutory_sss;
    }

    public float getStatutory_pagibig() {
        return statutory_pagibig;
    }

    public void setStatutory_pagibig(float statutory_pagibig) {
        this.statutory_pagibig = statutory_pagibig;
    }

    public float getStatutory_philhealth() {
        return statutory_philhealth;
    }

    public void setStatutory_philhealth(float statutory_philhealth) {
        this.statutory_philhealth = statutory_philhealth;
    }

    public float getStatutory_escola() {
        return statutory_escola;
    }

    public void setStatutory_escola(float statutory_escola) {
        this.statutory_escola = statutory_escola;
    }

    public float getTotalStatutory() {
        return totalStatutory;
    }

    public void setTotalStatutory(float totalStatutory) {
        this.totalStatutory = totalStatutory;
    }

    public float getThirteenth_month() {
        return thirteenth_month;
    }

    public void setThirteenth_month(float thirteenth_month) {
        this.thirteenth_month = thirteenth_month;
    }

    public float getIncentive() {
        return incentive;
    }

    public void setIncentive(float incentive) {
        this.incentive = incentive;
    }

    public float getTotal() { return totalLaborCost; }

    public void setTotal(float total) {
        this.totalLaborCost = total;
    }

    public float getadmin_cost() {
        return admin_cost;
    }

    public void setadmin_cost(float admin_cost) {
        this.admin_cost = admin_cost;
    }

    public float getcontractCost() {
        return contractCost;
    }

    public void setcontractCost(float contractCost) {
        this.contractCost = contractCost;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public float getAllowance() {
        return allowance;
    }

    public void setAllowance(float allowance) {
        this.allowance = allowance;
    }
}
