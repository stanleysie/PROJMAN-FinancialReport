package model;

public class MonthlyReport implements Report {
    int idreport, nWorkingDays;
    String employeename, version, type, creator, otherName;
    double basicRate, equivalentMonthlyCost, effectiveMonthlyRate, statutory_sss,
            statutory_pagibig, statutory_philhealth,statutory_escola, totalStatutory, thirteenth_month,
            incentive, totalLaborCost, admin_cost, contractCost, allowance, otherValue;

    public MonthlyReport() {
        this.type = "Monthly";
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

    public double getBasicRate() {
        return basicRate;
    }

    public void setBasicRate(double basicRate) {
        this.basicRate = basicRate;
    }

    public int getnWorkingDays() {
        return nWorkingDays;
    }

    public void setnWorkingDays(int nWorkingDays) {
        this.nWorkingDays = nWorkingDays;
    }

    public double getequivalentMonthlyCost() {
        return equivalentMonthlyCost;
    }

    public void setequivalentMonthlyCost(double equivalentMonthlyCost) {
        this.equivalentMonthlyCost = equivalentMonthlyCost;
    }

    public double geteffectiveMonthlyRate() {
        return effectiveMonthlyRate;
    }

    public void seteffectiveMonthlyRate(double effectiveMonthlyRate) {
        this.effectiveMonthlyRate = effectiveMonthlyRate;
    }

    public double getStatutory_sss() {
        return statutory_sss;
    }

    public void setStatutory_sss(double statutory_sss) {
        this.statutory_sss = statutory_sss;
    }

    public double getStatutory_pagibig() {
        return statutory_pagibig;
    }

    public void setStatutory_pagibig(double statutory_pagibig) {
        this.statutory_pagibig = statutory_pagibig;
    }

    public double getStatutory_philhealth() {
        return statutory_philhealth;
    }

    public void setStatutory_philhealth(double statutory_philhealth) {
        this.statutory_philhealth = statutory_philhealth;
    }

    public double getStatutory_escola() {
        return statutory_escola;
    }

    public void setStatutory_escola(double statutory_escola) {
        this.statutory_escola = statutory_escola;
    }

    public double getTotalStatutory() {
        return totalStatutory;
    }

    public void setTotalStatutory(double totalStatutory) {
        this.totalStatutory = totalStatutory;
    }

    public double getThirteenth_month() {
        return thirteenth_month;
    }

    public void setThirteenth_month(double thirteenth_month) {
        this.thirteenth_month = thirteenth_month;
    }

    public double getIncentive() {
        return incentive;
    }

    public void setIncentive(double incentive) {
        this.incentive = incentive;
    }

    public double getTotal() {
        return totalLaborCost;
    }

    public void setTotal(double total) {
        this.totalLaborCost = total;
    }

    public double getadmin_cost() {
        return admin_cost;
    }

    public void setadmin_cost(double admin_cost) {
        this.admin_cost = admin_cost;
    }

    public double getcontractCost() {
        return contractCost;
    }

    public void setcontractCost(double contractCost) {
        this.contractCost = contractCost;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getOtherName() { return otherName; }

    public void setOtherName(String otherName) { this.otherName = otherName; }

    public double getOtherValue() { return otherValue; }

    public void setOtherValue(double otherValue) { this.otherValue = otherValue; }
}


