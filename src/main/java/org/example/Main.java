package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        SimpleBank simpleBank = new SimpleBank("Tim FromTheOffice", 0);
        Scanner scanner = new Scanner(System.in);

        char choice;

        System.out.println("===================");
        System.out.println("Banking Application");
        System.out.println("===================");

        System.out.println("Welcome, " + simpleBank.getCustomerName());
        System.out.println("Your ID is: " + simpleBank.getAccountId());
        System.out.println();
        showMenu();

        do {

            choice = getUserChoice(scanner).charAt(0);


            if (choice == 'A') {
                System.out.println("Current account balance: " + simpleBank.getAccountBalance());
            } else if (choice == 'B') {
                int depositAmount;

                System.out.println("Enter amount to deposit: ");
                depositAmount = getAmount(scanner);
                try {
                    simpleBank.makeADeposit(depositAmount);
                    System.out.println("Amount " + depositAmount + " successfully deposited to account.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Cannot deposit " + depositAmount);
                    System.out.println("Enter a non-negative amount.");
                }

            } else if (choice == 'C') {
                int withdrawalAmount;

                System.out.println("Enter amount to withdraw: ");
                withdrawalAmount = getAmount(scanner);

                try {
                    simpleBank.makeAWithdrawal(withdrawalAmount);
                    System.out.println("Amount " + withdrawalAmount + " has successfully been withdrawn");
                } catch (IllegalArgumentException e) {
                    System.out.println("Cannot withdraw " + withdrawalAmount);
                    System.out.println("Current balance is: " + simpleBank.getAccountBalance());
                }
            } else if (choice == 'D') {
                System.out.println("Previous transaction was: " + simpleBank.getPreviousTransaction());
            } else if (choice == 'E') {
                System.out.println("Interest based on current account balance: " + simpleBank.calculateInterest());
            } else if (choice == '?') {
                showMenu();
            } else if (choice == 'F') {
                System.out.println("Thank you for using Simple Bank console.");
            } else {
                System.out.println("Unknown option. Enter '?' for the help menu.");
            }

        } while (choice != 'F');

        scanner.close();

    }

    private static int getAmount(Scanner scanner) {
        String temp;
        int amount;
        temp = scanner.nextLine();
        try {
            amount = Integer.parseInt(temp);
            return amount;
        } catch (NumberFormatException e) {
            System.out.println("Invalid value.");
        }
        return 0;
    }

    private static String getUserChoice(Scanner scanner) throws IOException {
        String choice;
        System.out.println();
        System.out.print(">>> ");
        choice = scanner.nextLine();

        return choice.strip();
    }

    private static void showMenu() {
        System.out.println("What would you like to do: ");
        System.out.println();

        System.out.println("A. Check your balance");
        System.out.println("B. Make a deposit");
        System.out.println("C. Make a withdrawal");
        System.out.println("D. View previous transaction");
        System.out.println("E. Calculate interest");
        System.out.println("F. Exit.");
        System.out.println("?. Show Help.");
    }
}