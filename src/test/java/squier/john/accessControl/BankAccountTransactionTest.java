package squier.john.accessControl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by johnsquier on 1/17/17.
 */
public class BankAccountTransactionTest {

    BankAccountTransaction bankAccountTransaction;

    @Before
    public void setup() {
        bankAccountTransaction = new BankAccountTransaction(TransactionType.DEPOSIT, 100.0,
                                                            BankAccountStatus.OPEN, "John");
    }

    @Test
    public void getTransactionTypeTest() {
        TransactionType expected = TransactionType.DEPOSIT;
        TransactionType actual = bankAccountTransaction.getTransactionType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTransactionAmountTest() {
        Double expected = 100.0;
        Double actual = bankAccountTransaction.getTransactionAmount();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getBankAccountStatusTest() {
        BankAccountStatus expected = BankAccountStatus.OPEN;
        BankAccountStatus actual = bankAccountTransaction.getBankAccountStatus();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getNameTest() {
        String expected = "John";
        String actual = bankAccountTransaction.getName();
        Assert.assertEquals(expected, actual);
    }
}
