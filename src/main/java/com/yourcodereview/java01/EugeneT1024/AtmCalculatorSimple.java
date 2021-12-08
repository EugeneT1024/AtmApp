package com.yourcodereview.java01.EugeneT1024;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class AtmCalculatorSimple implements AtmCalculator {

    public static final String ERROR_CASH_AMOUNT_IS_LESS_THAN_DENOMINATIONS =
        "Cash amount is less than smallest provided denomination!";

    private final Logger logger = LogManager.getLogger();

    @Override
    public CalculationResult calculate(int cashAmount, List<Integer> denominationsOfBanknotes) {
        validateInput(cashAmount, denominationsOfBanknotes);

        Collections.sort(denominationsOfBanknotes, Collections.reverseOrder());
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

    private void validateInput(int cashAmount, List<Integer> denominationsOfBanknotes) {
        if (cashAmount < 1) {
            logger.error("Bad input - cash amount must be > 0, but provided: " + cashAmount);
            throw new IllegalArgumentException();
        }

        if (denominationsOfBanknotes == null) {
            logger.error("Bad input - denominationsOfBanknotes is null");
            throw new IllegalArgumentException();
        }

        if (denominationsOfBanknotes.isEmpty()) {
            logger.error("Bad input - denominationsOfBanknotes is null");
            throw new IllegalArgumentException();
        }

    }

}
