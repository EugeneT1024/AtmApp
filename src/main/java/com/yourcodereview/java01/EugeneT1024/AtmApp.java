package com.yourcodereview.java01.EugeneT1024;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AtmApp {

    public static final Set<Integer> availableBanknotes = new HashSet<>(){{
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

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Please enter the required amount of cash: ");
            int amountOfCash = Integer.parseInt((scanner.nextLine()).trim());
            if (amountOfCash <= 0) {
                throw new InputMismatchException();
            }

            System.out.println("ATM can provide next denominations: 1, 2, 5, 10, 20, 50, 100, 200, 500, 1000");
            System.out.print("Please enter the required denominations of banknotes, separated by comma: ");
            String userInput = scanner.nextLine();
            List<Integer> denominationsOfBanknotes = Stream.of(userInput.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

            checkDenominations(denominationsOfBanknotes);

            AtmCalculator atmCalculator = new AtmCalculatorSimple();
            CalculationResult result = atmCalculator.calculate(amountOfCash, denominationsOfBanknotes);

            System.out.println(result.toString());

        } catch (InputMismatchException e) {
            System.out.println("Incorrect format. Amount of cash must be positive integer number");
        } catch (NumberFormatException e) {
            System.out.println("Incorrect format. Please provide denominations from list mentioned above");
        } catch (IllegalArgumentException e) {
            System.out.println("Exit");
        }
    }

    private static void checkDenominations(List<Integer> denominationsOfBanknotes) {
        if (denominationsOfBanknotes == null || !availableBanknotes.containsAll(denominationsOfBanknotes)) {
            throw new NumberFormatException();
        }
    }
}
