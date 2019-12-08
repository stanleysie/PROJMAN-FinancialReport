package model;

public interface Report {
    abstract public float getBasicRate();
    abstract public float getnWorkingDays();
    abstract public float getequivalentMonthlyCost();
    abstract public float geteffectiveMonthlyRate();
    abstract public float getStatutory_sss();
    abstract public float getStatutory_pagibig();
    abstract public float getStatutory_philhealth();
    abstract public float getStatutory_escola();
    abstract public float getTotalStatutory();
    abstract public float getThirteenth_month();
    abstract public float getIncentive();
    abstract public float getTotal();
    abstract public float getadmin_cost();
    abstract public float getcontractCost();
    abstract public String getVersion();
    abstract public float getAllowance();
    abstract public String getType();
    abstract public String getCreator();
}
