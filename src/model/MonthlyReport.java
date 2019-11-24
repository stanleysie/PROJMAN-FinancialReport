package model;

public class MonthlyReport {
    int idreport, nAbscence, version;
    String employeename;
    float mRate, basicRate, nWorkingDays, tDaysReported, absent_Rate, absent_Deduction, lateHours, lateRate,
            lateDeduction, undertimeHours, undertimeRate, undertimeDeduction, totalDeduction,
            night_Diff_Hours, night_Diff_Rate, night_Diff_Earning, ot_RegDay_Hours, ot_RegDay_Rate,
            ot_RegDay_Earning, rd_sh_Hours, rd_sh_Rate, rd_sh_Earning, shrd_Hours, shrd_Rate, shrd_Earning,
            lh_Hours, lh_Rate, lh_Earning, lhrd_Hours, lhrd_Rate, lhrd_Earning, grossPay, statutory_sss,
            statutory_pagibig, statutory_philhealth,statutory_escola, totalStatutory, thirteenth_month,
            incentive, total, admin_fee, net_payroll;

    public MonthlyReport() {
    }

    public int getIdreport() {
        return idreport;
    }

    public void setIdreport(int idreport) {
        this.idreport = idreport;
    }

    public int getnAbscence() {
        return nAbscence;
    }

    public void setnAbscence(int nAbscence) {
        this.nAbscence = nAbscence;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public float getmRate() {
        return mRate;
    }

    public void setmRate(float mRate) {
        this.mRate = mRate;
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

    public float getAbsent_Rate() {
        return absent_Rate;
    }

    public void setAbsent_Rate(float absent_Rate) {
        this.absent_Rate = absent_Rate;
    }

    public float getAbsent_Deduction() {
        return absent_Deduction;
    }

    public void setAbsent_Deduction(float absent_Deduction) {
        this.absent_Deduction = absent_Deduction;
    }

    public float getLateHours() {
        return lateHours;
    }

    public void setLateHours(float lateHours) {
        this.lateHours = lateHours;
    }

    public float getLateRate() {
        return lateRate;
    }

    public void setLateRate(float lateRate) {
        this.lateRate = lateRate;
    }

    public float getLateDeduction() {
        return lateDeduction;
    }

    public void setLateDeduction(float lateDeduction) {
        this.lateDeduction = lateDeduction;
    }

    public float getUndertimeHours() {
        return undertimeHours;
    }

    public void setUndertimeHours(float undertimeHours) {
        this.undertimeHours = undertimeHours;
    }

    public float getUndertimeRate() {
        return undertimeRate;
    }

    public void setUndertimeRate(float undertimeRate) {
        this.undertimeRate = undertimeRate;
    }

    public float getUndertimeDeduction() {
        return undertimeDeduction;
    }

    public void setUndertimeDeduction(float undertimeDeduction) {
        this.undertimeDeduction = undertimeDeduction;
    }

    public float getTotalDeduction() {
        return totalDeduction;
    }

    public void setTotalDeduction(float totalDeduction) {
        this.totalDeduction = totalDeduction;
    }

    public float getNight_Diff_Hours() {
        return night_Diff_Hours;
    }

    public void setNight_Diff_Hours(float night_Diff_Hours) {
        this.night_Diff_Hours = night_Diff_Hours;
    }

    public float getNight_Diff_Rate() {
        return night_Diff_Rate;
    }

    public void setNight_Diff_Rate(float night_Diff_Rate) {
        this.night_Diff_Rate = night_Diff_Rate;
    }

    public float getNight_Diff_Earning() {
        return night_Diff_Earning;
    }

    public void setNight_Diff_Earning(float night_Diff_Earning) {
        this.night_Diff_Earning = night_Diff_Earning;
    }

    public float getOt_RegDay_Hours() {
        return ot_RegDay_Hours;
    }

    public void setOt_RegDay_Hours(float ot_RegDay_Hours) {
        this.ot_RegDay_Hours = ot_RegDay_Hours;
    }

    public float getOt_RegDay_Rate() {
        return ot_RegDay_Rate;
    }

    public void setOt_RegDay_Rate(float ot_RegDay_Rate) {
        this.ot_RegDay_Rate = ot_RegDay_Rate;
    }

    public float getOt_RegDay_Earning() {
        return ot_RegDay_Earning;
    }

    public void setOt_RegDay_Earning(float ot_RegDay_Earning) {
        this.ot_RegDay_Earning = ot_RegDay_Earning;
    }

    public float getRd_sh_Hours() {
        return rd_sh_Hours;
    }

    public void setRd_sh_Hours(float rd_sh_Hours) {
        this.rd_sh_Hours = rd_sh_Hours;
    }

    public float getRd_sh_Rate() {
        return rd_sh_Rate;
    }

    public void setRd_sh_Rate(float rd_sh_Rate) {
        this.rd_sh_Rate = rd_sh_Rate;
    }

    public float getRd_sh_Earning() {
        return rd_sh_Earning;
    }

    public void setRd_sh_Earning(float rd_sh_Earning) {
        this.rd_sh_Earning = rd_sh_Earning;
    }

    public float getShrd_Hours() {
        return shrd_Hours;
    }

    public void setShrd_Hours(float shrd_Hours) {
        this.shrd_Hours = shrd_Hours;
    }

    public float getShrd_Rate() {
        return shrd_Rate;
    }

    public void setShrd_Rate(float shrd_Rate) {
        this.shrd_Rate = shrd_Rate;
    }

    public float getShrd_Earning() {
        return shrd_Earning;
    }

    public void setShrd_Earning(float shrd_Earning) {
        this.shrd_Earning = shrd_Earning;
    }

    public float getLh_Hours() {
        return lh_Hours;
    }

    public void setLh_Hours(float lh_Hours) {
        this.lh_Hours = lh_Hours;
    }

    public float getLh_Rate() {
        return lh_Rate;
    }

    public void setLh_Rate(float lh_Rate) {
        this.lh_Rate = lh_Rate;
    }

    public float getLh_Earning() {
        return lh_Earning;
    }

    public void setLh_Earning(float lh_Earning) {
        this.lh_Earning = lh_Earning;
    }

    public float getLhrd_Hours() {
        return lhrd_Hours;
    }

    public void setLhrd_Hours(float lhrd_Hours) {
        this.lhrd_Hours = lhrd_Hours;
    }

    public float getLhrd_Rate() {
        return lhrd_Rate;
    }

    public void setLhrd_Rate(float lhrd_Rate) {
        this.lhrd_Rate = lhrd_Rate;
    }

    public float getLhrd_Earning() {
        return lhrd_Earning;
    }

    public void setLhrd_Earning(float lhrd_Earning) {
        this.lhrd_Earning = lhrd_Earning;
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


