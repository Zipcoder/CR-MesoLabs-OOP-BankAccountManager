package squier.john.accessControl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by johnsquier on 1/17/17.
 */
public class BankAccountTest {

    BankAccount bankAccount;
    double delta = 0.00001;

    @Test
    public void getBalanceAccountUnfrozenTest() {
        bankAccount = new BankAccount(BankAccountType.SAVINGS, 100.0,
                    "John", 10.0, BankAccountStatus.OPEN,
                    OverdraftProtection.ENABLED);

        Double expected = 100.0;
        Double actual = bankAccount.getBalance();
        Assert.assertEquals(expected, actual, delta);
    }

    @Test
    public void getBalanceAccountFrozenTest() {
        bankAccount = new BankAccount(BankAccountType.SAVINGS, 100.0,
                "John", 10.0, BankAccountStatus.OFAC_FROZEN,
                OverdraftProtection.ENABLED);

        // using null to indicate frozen acct status
        Double actual = bankAccount.getBalance();
        Assert.assertNull(actual);
    }

    @Test
    public void getAccountHoldersNameTest() {
        bankAccount = new BankAccount(BankAccountType.SAVINGS, 100.0,
                "John", 10.0, BankAccountStatus.OFAC_FROZEN,
                OverdraftProtection.ENABLED);

        String expected = "John";
        String actual = bankAccount.getAccountHoldersName();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setAccountHoldersNameOpenTest() {
        bankAccount = new BankAccount(BankAccountType.SAVINGS, 100.0,
                "John", 10.0, BankAccountStatus.OPEN,
                OverdraftProtection.ENABLED);

        String expected = "Bob";
        bankAccount.setAccountHoldersName("Bob");
        String actual = bankAccount.getAccountHoldersName();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setAccountHoldersNameClosedTest() {
        bankAccount = new BankAccount(BankAccountType.SAVINGS, 100.0,
                "John", 10.0, BankAccountStatus.CLOSED,
                OverdraftProtection.ENABLED);

        String expected = "John";
        bankAccount.setAccountHoldersName("Bob");
        String actual = bankAccount.getAccountHoldersName();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateBalanceAccountClosedTest() {
        bankAccount = new BankAccount(BankAccountType.SAVINGS, 100.0,
                "John", 10.0, BankAccountStatus.CLOSED,
                OverdraftProtection.ENABLED);

        Double expected = 100.0;
        bankAccount.updateBalanceWithCreditOrDebit(5.0);
        Double actual = bankAccount.getBalance();
        Assert.assertEquals(expected, actual, delta);
    }

    @Test
    public void updateBalancePositiveTest() {
        bankAccount = new BankAccount(BankAccountType.SAVINGS, 100.0,
                "John", 10.0, BankAccountStatus.OPEN,
                OverdraftProtection.ENABLED);

        Double expected = 200.0;
        bankAccount.updateBalanceWithCreditOrDebit(100.0);
        Double actual = bankAccount.getBalance();
        Assert.assertEquals(expected, actual, delta);
    }

    @Test
    public void updateBalanceNegativeTest() {
        bankAccount = new BankAccount(BankAccountType.SAVINGS, 100.0,
                "John", 10.0, BankAccountStatus.OPEN,
                OverdraftProtection.ENABLED);

        Double expected = 50.0;
        bankAccount.updateBalanceWithCreditOrDebit(-50.0);
        Double actual = bankAccount.getBalance();
        Assert.assertEquals(expected, actual, delta);
    }

    @Test
    public void transferBalanceFromTestBothEndAboveZeroTest() {
        BankAccount transferFrom = new BankAccount(BankAccountType.SAVINGS, 100.0,
                "John", 10.0, BankAccountStatus.OPEN,
                OverdraftProtection.ENABLED);
        BankAccount transferTo = new BankAccount(BankAccountType.CHECKING, 100.0,
                "John", 10.0, BankAccountStatus.OPEN,
                OverdraftProtection.ENABLED);

        transferTo.transferBalanceFrom(transferFrom, 50.0);

        Double[] expected = {150.0, 50.0};
        Double[] actual = {transferTo.getBalance(), transferFrom.getBalance()};
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void transferBalanceToTestBothEndAboveZeroTest() {
        BankAccount transferFrom = new BankAccount(BankAccountType.SAVINGS, 100.0,
                "John", 10.0, BankAccountStatus.OPEN,
                OverdraftProtection.ENABLED);
        BankAccount transferTo = new BankAccount(BankAccountType.CHECKING, 100.0,
                "John", 10.0, BankAccountStatus.OPEN,
                OverdraftProtection.ENABLED);

        transferFrom.transferBalanceTo(transferTo, 50.0);

        Double[] expected = {150.0, 50.0};
        Double[] actual = {transferTo.getBalance(), transferFrom.getBalance()};
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void transferBalanceFromTestNotEnoughInFromTest() {
        BankAccount transferFrom = new BankAccount(BankAccountType.SAVINGS, 100.0,
                "John", 10.0, BankAccountStatus.OPEN,
                OverdraftProtection.ENABLED);
        BankAccount transferTo = new BankAccount(BankAccountType.CHECKING, 100.0,
                "John", 10.0, BankAccountStatus.OPEN,
                OverdraftProtection.ENABLED);

        transferTo.transferBalanceFrom(transferFrom, 500.0);

        Double[] expected = {100.0, 100.0};
        Double[] actual = {transferTo.getBalance(), transferFrom.getBalance()};
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void transferBalanceToTestNotEnoughInFromTest() {
        BankAccount transferFrom = new BankAccount(BankAccountType.SAVINGS, 100.0,
                "John", 10.0, BankAccountStatus.OPEN,
                OverdraftProtection.ENABLED);
        BankAccount transferTo = new BankAccount(BankAccountType.CHECKING, 100.0,
                "John", 10.0, BankAccountStatus.OPEN,
                OverdraftProtection.ENABLED);

        transferFrom.transferBalanceTo(transferTo, 500.0);

        Double[] expected = {100.0, 100.0};
        Double[] actual = {transferTo.getBalance(), transferFrom.getBalance()};
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void overdrawWithProtectionOnTest() {
        bankAccount = new BankAccount(BankAccountType.SAVINGS, 100.0,
                "John", 10.0, BankAccountStatus.OPEN,
                OverdraftProtection.ENABLED);

        Double expected = 100.0;
        bankAccount.updateBalanceWithCreditOrDebit(-500.0);
        Double actual = bankAccount.getBalance();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void overdrawWithProtectOffTest() {
        bankAccount = new BankAccount(BankAccountType.SAVINGS, 100.0,
                "John", 10.0, BankAccountStatus.OPEN,
                OverdraftProtection.DISABLED);

        Double expected = -400.0;
        bankAccount.updateBalanceWithCreditOrDebit(-500.0);
        Double actual = bankAccount.getBalance();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getBankAccountStatusTest() {
        bankAccount = new BankAccount(BankAccountType.SAVINGS, 100.0,
                "John", 10.0, BankAccountStatus.OPEN,
                OverdraftProtection.ENABLED);

        BankAccountStatus expected = BankAccountStatus.OPEN;
        BankAccountStatus actual = bankAccount.getAccountStatus();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setBankAccountStatusClosedFromOpen() {
        bankAccount = new BankAccount(BankAccountType.SAVINGS, 0.0,
                "John", 10.0, BankAccountStatus.OPEN,
                OverdraftProtection.ENABLED);

        BankAccountStatus expected = BankAccountStatus.CLOSED;
        bankAccount.setAccountStatus(BankAccountStatus.CLOSED);
        BankAccountStatus actual = bankAccount.getAccountStatus();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setBankAccountStatusClosedFromFrozen() {
        bankAccount = new BankAccount(BankAccountType.SAVINGS, 0.0,
                "John", 10.0, BankAccountStatus.OFAC_FROZEN,
                OverdraftProtection.ENABLED);

        BankAccountStatus expected = BankAccountStatus.CLOSED;
        bankAccount.setAccountStatus(BankAccountStatus.CLOSED);
        BankAccountStatus actual = bankAccount.getAccountStatus();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setBankAccountStatusOpenFromFrozen() {
        bankAccount = new BankAccount(BankAccountType.SAVINGS, 0.0,
                "John", 10.0, BankAccountStatus.OFAC_FROZEN,
                OverdraftProtection.ENABLED);

        BankAccountStatus expected = BankAccountStatus.OPEN;
        bankAccount.setAccountStatus(BankAccountStatus.OPEN);
        BankAccountStatus actual = bankAccount.getAccountStatus();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setBankAccountStatusOpenFromClosed() {
        // needs to fail
        bankAccount = new BankAccount(BankAccountType.SAVINGS, 0.0,
                "John", 10.0, BankAccountStatus.CLOSED,
                OverdraftProtection.ENABLED);

        BankAccountStatus expected = BankAccountStatus.CLOSED;
        bankAccount.setAccountStatus(BankAccountStatus.OPEN);
        BankAccountStatus actual = bankAccount.getAccountStatus();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setBankAccountStatusFrozenFromOpen() {
        bankAccount = new BankAccount(BankAccountType.SAVINGS, 0.0,
                "John", 10.0, BankAccountStatus.OPEN,
                OverdraftProtection.ENABLED);

        BankAccountStatus expected = BankAccountStatus.OFAC_FROZEN;
        bankAccount.setAccountStatus(BankAccountStatus.OFAC_FROZEN);
        BankAccountStatus actual = bankAccount.getAccountStatus();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setBankAccountStatusFrozenFromClosed() {
        bankAccount = new BankAccount(BankAccountType.SAVINGS, 0.0,
                "John", 10.0, BankAccountStatus.CLOSED,
                OverdraftProtection.ENABLED);

        BankAccountStatus expected = BankAccountStatus.CLOSED;
        bankAccount.setAccountStatus(BankAccountStatus.OFAC_FROZEN);
        BankAccountStatus actual = bankAccount.getAccountStatus();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setBankAccountStatusClosedWithZeroBalance() {
        bankAccount = new BankAccount(BankAccountType.SAVINGS, 0.0,
                "John", 10.0, BankAccountStatus.OPEN,
                OverdraftProtection.ENABLED);

        BankAccountStatus expected = BankAccountStatus.CLOSED;
        bankAccount.setAccountStatus(BankAccountStatus.CLOSED);
        BankAccountStatus actual = bankAccount.getAccountStatus();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setBankAccountStatusClosedWithNonZeroBalance() {
        bankAccount = new BankAccount(BankAccountType.SAVINGS, 10.0,
                "John", 10.0, BankAccountStatus.OPEN,
                OverdraftProtection.ENABLED);

        BankAccountStatus expected = BankAccountStatus.OPEN;
        bankAccount.setAccountStatus(BankAccountStatus.CLOSED);
        BankAccountStatus actual = bankAccount.getAccountStatus();
        Assert.assertEquals(expected, actual);
    }
}
