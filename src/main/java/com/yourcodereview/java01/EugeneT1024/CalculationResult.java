package com.yourcodereview.java01.EugeneT1024;

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

    @Override
    public String toString() {
        if (errorMessage != null) {
            return errorMessage;
        }

        StringBuilder builder = new StringBuilder();

        if (remainderOfCash != 0) {
            builder
                .append("Based on given denominations it is possible to get only ")
                .append(sumOfCalculatedBanknotes)
                .append(" of cash")
                .append(" with next denominations:\n");
        } else {
            builder
                .append("You can get ")
                .append(cashAmount)
                .append(" of cash")
                .append(" with next denominations:\n");
        }

        for (int denomination : denominationsOfBanknotes) {
            if (calculatedBanknotes.containsKey(denomination)) {
                builder
                    .append(denomination)
                    .append(" : ")
                    .append(calculatedBanknotes.get(denomination))
                    .append(" pcs\n");
            }
        }

        return builder.toString();
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
