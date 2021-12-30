package com.yourcodereview.java01.eugenet1024.atmapp;

import java.util.List;

public interface AtmCalculator {

    CalculationResult calculate(int cashAmount, List<Integer> denominationsOfBanknotes);
}
