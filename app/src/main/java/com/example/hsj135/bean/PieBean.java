package com.example.hsj135.bean;

public class PieBean {
    private String salaries;
    private int percentage;

    public PieBean(String salaries, int percentage) {
        this.salaries = salaries;
        this.percentage = percentage;
    }

    public String getSalaries() {
        return salaries;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setSalaries(String salaries) {
        this.salaries = salaries;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
