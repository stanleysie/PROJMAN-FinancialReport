package model;

public interface Report {
    abstract public double getBasicRate();
    abstract public int getnWorkingDays();
    abstract public double getequivalentMonthlyCost();
    abstract public double geteffectiveMonthlyRate();
    abstract public double getStatutory_sss();
    abstract public double getStatutory_pagibig();
    abstract public double getStatutory_philhealth();
    abstract public double getStatutory_escola();
    abstract public double getTotalStatutory();
    abstract public double getThirteenth_month();
    abstract public double getIncentive();
    abstract public double getTotal();
    abstract public double getadmin_cost();
    abstract public double getcontractCost();
    abstract public String getVersion();
    abstract public double getAllowance();
    abstract public String getType();
    abstract public String getCreator();
    abstract public String getOtherName();
    abstract public double getOtherValue();
}
