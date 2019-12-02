package model;

public class DailyReport {
    int idreport, version;
    String employeename;
    float basicRate, nWorkingDays, tDaysReported, grossPay, statutory_sss,
            statutory_pagibig, statutory_philhealth,statutory_escola, totalStatutory, thirteenth_month,
            incentive, total, admin_fee, net_payroll;

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

    public float gettDaysReported() {
        return tDaysReported;
    }

    public void settDaysReported(float tDaysReported) {
        this.tDaysReported = tDaysReported;
    }

    public float getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(float grossPay) {
        this.grossPay = grossPay;
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getAdmin_fee() {
        return admin_fee;
    }

    public void setAdmin_fee(float admin_fee) {
        this.admin_fee = admin_fee;
    }

    public float getNet_payroll() {
        return net_payroll;
    }

    public void setNet_payroll(float net_payroll) {
        this.net_payroll = net_payroll;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
