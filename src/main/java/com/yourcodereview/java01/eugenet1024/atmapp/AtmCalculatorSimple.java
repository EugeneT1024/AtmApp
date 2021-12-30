package com.yourcodereview.java01.eugenet1024.atmapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class AtmCalculatorSimple implements AtmCalculator {

    private static final String ERROR_CASH_AMOUNT_IS_LESS_THAN_DENOMINATIONS =
        "Cash amount is less than smallest provided denomination!";
    private static final String BAD_INPUT_CASH_AMOUNT_MUST_BE_0_BUT_PROVIDED =
        "Bad input - cash amount must be > 0, but provided: ";
    private static final String BAD_INPUT_DENOMINATIONS_OF_BANKNOTES_IS_NULL =
        "Bad input - denominationsOfBanknotes is null";
    private static final String BAD_INPUT_LIST_OF_DENOMINATIONS_OF_BANKNOTES_IS_EMPTY =
        "Bad input - list of denominationsOfBanknotes is empty";

    private final Logger logger = LogManager.getLogger();

    @Override
    public CalculationResult calculate(int cashAmount, List<Integer> denominationsOfBanknotes) {
        validateInput(cashAmount, denominationsOfBanknotes);

        Collections.sort(denominationsOfBanknotes, Collections.reverseOrder());

        return calculateResult(cashAmount, denominationsOfBanknotes);
    }

    private void validateInput(int cashAmount, List<Integer> denominationsOfBanknotes) {
        if (cashAmount < 1) {
            String message = BAD_INPUT_CASH_AMOUNT_MUST_BE_0_BUT_PROVIDED + cashAmount;
            logger.error(message);
            throw new IllegalArgumentException(message);
        }

        if (denominationsOfBanknotes == null) {
            logger.error(BAD_INPUT_DENOMINATIONS_OF_BANKNOTES_IS_NULL);
            throw new IllegalArgumentException(BAD_INPUT_DENOMINATIONS_OF_BANKNOTES_IS_NULL);
        }

        if (denominationsOfBanknotes.isEmpty()) {
            logger.error(BAD_INPUT_LIST_OF_DENOMINATIONS_OF_BANKNOTES_IS_EMPTY);
            throw new IllegalArgumentException(BAD_INPUT_LIST_OF_DENOMINATIONS_OF_BANKNOTES_IS_EMPTY);
        }
    }

    private CalculationResult calculateResult(int cashAmount, List<Integer> denominationsOfBanknotes) {
        CalculationResult result = new CalculationResult(cashAmount, denominationsOfBanknotes);

        if (cashAmount < denominationsOfBanknotes.get(denominationsOfBanknotes.size() - 1)) {
            result.setErrorMessage(ERROR_CASH_AMOUNT_IS_LESS_THAN_DENOMINATIONS);
            return result;
        }

        int sum = 0;
        int reminder = cashAmount;
        for (int denomination : denominationsOfBanknotes) {
            int countOfDenomination = 0;
            while (reminder >= denomination) {
                sum += denomination;
                reminder -= denomination;
                countOfDenomination++;
            }

            if (countOfDenomination > 0) {
                result.addCalculationForBanknote(denomination, countOfDenomination);
            }
        }
        result.setSumOfCalculatedBanknotes(sum);
        result.setRemainderOfCash(reminder);
        return result;
    }
}
