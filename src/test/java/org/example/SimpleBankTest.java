package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleBankTest {
    SimpleBank simpleBank;
    @BeforeEach
    void setUp() {
        simpleBank = new SimpleBank("Tim Statler",0);
    }

    @Test
    void canCheckBalance() {
        assertEquals(0,simpleBank.getAccountBalance());
    }

    @Test
    void canGetCustomerName() {
        assertEquals("Tim Statler",simpleBank.getCustomerName());
    }

    @Test
    void canGetAccountId() {
        assertEquals("A000001",simpleBank.getAccountId());
    }

    @Test
    void canMakeADeposit() {
        simpleBank.makeADeposit(100);
        assertEquals(100,simpleBank.getAccountBalance());
    }

    @Test
    void depositZeroDoesNotMakeWholeAccountBalanceZero() {
        simpleBank.makeADeposit(100);
        simpleBank.makeADeposit(0);
        assertEquals(100,simpleBank.getAccountBalance());
    }

    @Test
    void canMakeAWithdrawal() {
        simpleBank.makeADeposit(50);
        simpleBank.makeAWithdrawal(50);
        assertEquals(0,simpleBank.getAccountBalance());
    }

    @Test
    void cannotMakeAWithdrawalWithoutSufficientBalanceInAccount() {
        assertThrows(IllegalArgumentException.class,() -> {
            simpleBank.makeAWithdrawal(1000);
        });
    }

    @Test
    void cannotMakeADepositWithNegativeAmount() {
        assertThrows(IllegalArgumentException.class,() -> {
            simpleBank.makeADeposit(-100);
        });
    }

    @Test
    void canViewPreviousTransactionIfDeposit() {
        simpleBank.makeADeposit(150);
        assertEquals("+150",simpleBank.getPreviousTransaction());
    }

    @Test
    void canViewPreviousTransactionIfWithdrawal() {
        simpleBank.makeADeposit(45);
        simpleBank.makeAWithdrawal(45);
        assertEquals("-45",simpleBank.getPreviousTransaction());
    }

    @Test
    void canCalculateInterestRate() {
        simpleBank.makeADeposit(1000);
        assertEquals(40.0,simpleBank.calculateInterest());
    }

    @Test
    void canCalculateInterestRateIfBalanceIsZero() {
        simpleBank.makeADeposit(500);
        simpleBank.makeAWithdrawal(500);
        assertEquals(0.0,simpleBank.calculateInterest());
    }

    @Test
    void depositAndWithdrawSequentuallyIsCorrectlyImplemented() {
        simpleBank.makeADeposit(1500);
        simpleBank.makeAWithdrawal(500);

        assertEquals(1000,simpleBank.getAccountBalance());
    }

}