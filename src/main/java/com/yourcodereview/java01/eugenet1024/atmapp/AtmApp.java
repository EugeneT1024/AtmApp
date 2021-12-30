package com.yourcodereview.java01.eugenet1024.atmapp;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AtmApp {

    public static final String CASH_MUST_BE_POSITIVE_INTEGER_NUMBER =
        "Incorrect format. Amount of cash must be positive integer number";
    public static final String PROVIDE_DENOMINATIONS_FROM_LIST_MENTIONED_ABOVE =
        "Incorrect format. Please provide denominations from list mentioned above";

    private static final Set<Integer> AVAILABLE_BANKNOTES = new HashSet<>() {{
        add(1);
        add(2);
        add(5);
        add(10);
        add(20);
        add(50);
        add(100);
        add(200);
        add(500);
        add(1000);
    }};
    public static final String LIST_OF_AVAILABLE_BANKNOTES =
        AVAILABLE_BANKNOTES.stream().sorted().map(String::valueOf).collect(Collectors.joining(", "));

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Please enter the required amount of cash: ");
            int amountOfCash = Integer.parseInt((scanner.nextLine()).trim());
            if (amountOfCash <= 0) {
                throw new InputMismatchException(CASH_MUST_BE_POSITIVE_INTEGER_NUMBER);
            }

            System.out.println("ATM can provide next denominations: " + LIST_OF_AVAILABLE_BANKNOTES);
            System.out.print("Please enter the required denominations of banknotes, separated by comma: ");
            String userInput = scanner.nextLine();
            List<Integer> denominationsOfBanknotes = Stream.of(userInput.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

            checkDenominations(denominationsOfBanknotes);

            AtmCalculator atmCalculator = new AtmCalculatorSimple();
            CalculationResult result = atmCalculator.calculate(amountOfCash, denominationsOfBanknotes);

            System.out.println(createResultAnswer(result));

        } catch (InputMismatchException e) {
            System.out.println(CASH_MUST_BE_POSITIVE_INTEGER_NUMBER);
        } catch (NumberFormatException e) {
            System.out.println(PROVIDE_DENOMINATIONS_FROM_LIST_MENTIONED_ABOVE);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkDenominations(List<Integer> denominationsOfBanknotes) {
        if (denominationsOfBanknotes == null || !AVAILABLE_BANKNOTES.containsAll(denominationsOfBanknotes)) {
            throw new NumberFormatException(PROVIDE_DENOMINATIONS_FROM_LIST_MENTIONED_ABOVE);
        }
    }

    private static String createResultAnswer(CalculationResult result) {
        if (result.getErrorMessage() != null) {
            return result.getErrorMessage();
        }

        StringBuilder builder = new StringBuilder();

        if (result.getRemainderOfCash() != 0) {
            builder
                .append("Based on given denominations it is possible to get only ")
                .append(result.getSumOfCalculatedBanknotes())
                .append(" of cash")
                .append(" with next denominations:\n");
        } else {
            builder
                .append("You can get ")
                .append(result.getCashAmount())
                .append(" of cash")
                .append(" with next denominations:\n");
        }

        for (int denomination : result.getDenominationsOfBanknotes()) {
            if (result.getCalculatedBanknotes().containsKey(denomination)) {
                builder
                    .append(denomination)
                    .append(" : ")
                    .append(result.getCalculatedBanknotes().get(denomination))
                    .append(" pcs\n");
            }
        }

        return builder.toString();
    }
}
