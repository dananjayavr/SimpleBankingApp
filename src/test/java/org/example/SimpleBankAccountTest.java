package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleBankAccountTest {
    SimpleBankAccount simpleBankAccount;
    @BeforeEach
    void setUp() {
        simpleBankAccount = new SimpleBankAccount("Tim Statler",0);
    }

    @Test
    void canCheckBalance() {
        assertEquals(0, simpleBankAccount.getAccountBalance());
    }

    @Test
    void canGetCustomerName() {
        assertEquals("Tim Statler", simpleBankAccount.getCustomerName());
    }

    @Test
    void canGetAccountId() {
        assertEquals("A000001", simpleBankAccount.getAccountId());
    }

    @Test
    void canMakeADeposit() {
        simpleBankAccount.makeADeposit(100);
        assertEquals(100, simpleBankAccount.getAccountBalance());
    }

    @Test
    void depositZeroDoesNotMakeWholeAccountBalanceZero() {
        simpleBankAccount.makeADeposit(100);
        simpleBankAccount.makeADeposit(0);
        assertEquals(100, simpleBankAccount.getAccountBalance());
    }

    @Test
    void canMakeAWithdrawal() {
        simpleBankAccount.makeADeposit(50);
        simpleBankAccount.makeAWithdrawal(50);
        assertEquals(0, simpleBankAccount.getAccountBalance());
    }

    @Test
    void cannotMakeAWithdrawalWithoutSufficientBalanceInAccount() {
        assertThrows(IllegalArgumentException.class,() -> {
            simpleBankAccount.makeAWithdrawal(1000);
        });
    }

    @Test
    void cannotMakeADepositWithNegativeAmount() {
        assertThrows(IllegalArgumentException.class,() -> {
            simpleBankAccount.makeADeposit(-100);
        });
    }

    @Test
    void canViewPreviousTransactionIfDeposit() {
        simpleBankAccount.makeADeposit(150);
        assertEquals("+150", simpleBankAccount.getPreviousTransaction());
    }

    @Test
    void canViewPreviousTransactionIfWithdrawal() {
        simpleBankAccount.makeADeposit(45);
        simpleBankAccount.makeAWithdrawal(45);
        assertEquals("-45", simpleBankAccount.getPreviousTransaction());
    }

    @Test
    void canCalculateInterestRate() {
        simpleBankAccount.makeADeposit(1000);
        assertEquals(40.0, simpleBankAccount.calculateInterest());
    }

    @Test
    void canCalculateInterestRateIfBalanceIsZero() {
        simpleBankAccount.makeADeposit(500);
        simpleBankAccount.makeAWithdrawal(500);
        assertEquals(0.0, simpleBankAccount.calculateInterest());
    }

    @Test
    void depositAndWithdrawSequentuallyIsCorrectlyImplemented() {
        simpleBankAccount.makeADeposit(1500);
        simpleBankAccount.makeAWithdrawal(500);

        assertEquals(1000, simpleBankAccount.getAccountBalance());
    }

}