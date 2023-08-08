package org.example;

import java.util.ArrayList;
import java.util.List;

public class SimpleBankAccount {

    private final String customerName;
    private int accountBalance;
    private final String id;
    private final List<String> transactions;
    private final double interestRate = 0.04;

    public SimpleBankAccount(String customerName, int accountBalance) {
        this.customerName = customerName;
        this.accountBalance = accountBalance;
        this.id = "A000001"; //TODO: make this random
        transactions = new ArrayList<String>();
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAccountId() {
        return id;
    }

    public void makeADeposit(int amount) {
        String deposit = "+";
        if(amount >= 0) {
            accountBalance += amount;
            deposit += Integer.toString(amount);
            transactions.add(deposit);
        } else {
            throw  new IllegalArgumentException("Amount cannot be less than 0 ");
        }

    }

    public void makeAWithdrawal(int amount) {
        String withdrawal = "-";
        if(accountBalance >= amount) {
            accountBalance -= amount;
            withdrawal += Integer.toString(amount);
            transactions.add(withdrawal);
        } else {
            throw new IllegalArgumentException("Cannot make a transaction. Balance low. Current balance is: "
                    + accountBalance);
        }
    }

    public String getPreviousTransaction() {
        return this.transactions.get(transactions.size()-1);
    }

    public double calculateInterest() {
        double rate = accountBalance * interestRate * 1;
        return rate;
    }
}
