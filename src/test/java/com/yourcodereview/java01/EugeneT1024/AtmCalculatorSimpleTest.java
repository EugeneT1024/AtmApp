package com.yourcodereview.java01.EugeneT1024;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AtmCalculatorSimpleTest {

    private final AtmCalculator calculator = new AtmCalculatorSimple();

    @Test
    void shouldThrowExceptionWhenCashAmountIsLessThanOne() {
        List<Integer> denominations = new ArrayList<>() {{
            add(100);
        }};
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(0, denominations));
    }

    @Test
    void shouldThrowExceptionWhenDenominationsOfBanknotesIsNull() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(10, null));
    }

    @Test
    void shouldThrowExceptionWhenDenominationsOfBanknotesIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(20, new ArrayList<>()));
    }

    @Test
    void shouldCalculateWithErrorIfAmountOfCashIsLessThanMinimumDenomination() {
        int cashAmount = 100;
        List<Integer> denominations = new ArrayList<>() {{
            add(200);
            add(1000);
            add(5000);
        }};

        CalculationResult expected = new CalculationResult(cashAmount, denominations);
        expected.setErrorMessage(AtmCalculatorSimple.ERROR_CASH_AMOUNT_IS_LESS_THAN_DENOMINATIONS);

        CalculationResult actual = calculator.calculate(cashAmount, denominations);

        assertEquals(expected.getErrorMessage(), actual.getErrorMessage());
    }

    @Test
    void shouldCalculateWithReminderIfDenominationsQtyIsNotSufficient() {
        int cashAmount = 999;
        List<Integer> denominations = new ArrayList<>() {{
            add(500);
            add(200);
            add(50);
            add(20);
        }};

        Map<Integer, Integer> calculatedBanknotes = new HashMap<>() {{
            put(500, 1);
            put(200, 2);
            put(50, 1);
            put(20, 2);
        }};

        CalculationResult expected = new CalculationResult(cashAmount, denominations);
        expected.setCalculatedBanknotes(calculatedBanknotes);
        expected.setSumOfCalculatedBanknotes(990);
        expected.setRemainderOfCash(9);

        CalculationResult actual = calculator.calculate(cashAmount, denominations);

        assertEquals(expected.getCalculatedBanknotes(), actual.getCalculatedBanknotes());
        assertEquals(expected.getSumOfCalculatedBanknotes(), actual.getSumOfCalculatedBanknotes());
        assertEquals(expected.getRemainderOfCash(), actual.getRemainderOfCash());
    }

    @Test
    void shouldCalculate() {
        int cashAmount = 4567;
        List<Integer> denominations = new ArrayList<>() {{
            add(1000);
            add(100);
            add(20);
            add(5);
            add(2);
        }};

        Map<Integer, Integer> calculatedBanknotes = new HashMap<>() {{
            put(1000, 4);
            put(100, 5);
            put(20, 3);
            put(5, 1);
            put(2, 1);
        }};

        CalculationResult expected = new CalculationResult(cashAmount, denominations);
        expected.setCalculatedBanknotes(calculatedBanknotes);

        CalculationResult actual = calculator.calculate(cashAmount, denominations);

        assertEquals(expected.getCalculatedBanknotes(), actual.getCalculatedBanknotes());
    }

}