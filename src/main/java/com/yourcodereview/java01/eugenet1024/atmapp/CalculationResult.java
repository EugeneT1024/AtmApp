package com.yourcodereview.java01.eugenet1024.atmapp;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculationResult {

    private final int cashAmount;
    private final List<Integer> denominationsOfBanknotes;

    private Map<Integer, Integer> calculatedBanknotes;
    private int sumOfCalculatedBanknotes;
    private int remainderOfCash;
    private String errorMessage;

    public CalculationResult(int cashAmount, List<Integer> denominationsOfBanknotes) {
        this.cashAmount = cashAmount;
        this.denominationsOfBanknotes = Collections.unmodifiableList(denominationsOfBanknotes);
    }

    public int getCashAmount() {
        return cashAmount;
    }

    public List<Integer> getDenominationsOfBanknotes() {
        return denominationsOfBanknotes;
    }

    public Map<Integer, Integer> getCalculatedBanknotes() {
        return calculatedBanknotes;
    }

    public void setCalculatedBanknotes(Map<Integer, Integer> calculatedBanknotes) {
        this.calculatedBanknotes = calculatedBanknotes;
    }

    public void addCalculationForBanknote(int denomination, int qty) {
        if (calculatedBanknotes == null) {
            calculatedBanknotes = new HashMap<>();
        }
        calculatedBanknotes.put(denomination, qty);
    }

    public int getSumOfCalculatedBanknotes() {
        return sumOfCalculatedBanknotes;
    }

    public void setSumOfCalculatedBanknotes(int sumOfCalculatedBanknotes) {
        this.sumOfCalculatedBanknotes = sumOfCalculatedBanknotes;
    }

    public int getRemainderOfCash() {
        return remainderOfCash;
    }

    public void setRemainderOfCash(int remainderOfCash) {
        this.remainderOfCash = remainderOfCash;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
