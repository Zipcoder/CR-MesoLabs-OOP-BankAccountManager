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
        Double expected = null;
        Double actual = bankAccount.getBalance();
        Assert.assertEquals(expected, actual, delta);
    }

    @Test
    public void updateBalanceAccountClosedTest() {
        bankAccount = new BankAccount(BankAccountType.SAVINGS, 100.0,
                "John", 10.0, BankAccountStatus.OPEN,
                OverdraftProtection.ENABLED);
        Double expected = 100.0;
        bankAccount.updateBalanceWithCreditOrDebit(5.0);
        Double actual = bankAccount.getBalance();
    }

    @Test
    public void updateBalancePositiveTest() {
        bankAccount = new BankAccount(BankAccountType.SAVINGS, 100.0,
                "John", 10.0, BankAccountStatus.OPEN,
                OverdraftProtection.ENABLED);
        Double expected = 200.0;
        bankAccount.updateBalanceWithCreditOrDebit(100.0);
        Double actual = bankAccount.getBalance();
    }

}
