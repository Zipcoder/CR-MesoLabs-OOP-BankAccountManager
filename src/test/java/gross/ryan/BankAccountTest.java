package gross.ryan;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by ryangross on 1/17/17.
 */
public class BankAccountTest {
    BankAccount ryansAcct;
    BankAccount fakeAcct;
    BankAccount closedAcct;

    @Before
    public void setUp() {
        ryansAcct = new BankAccount("Ryan Gross", "checking", 5000);
        ryansAcct.setType("checking");
        ryansAcct.debitMoney(50);

        fakeAcct = new BankAccount("Fake", "savings", 50000);
        fakeAcct.setStatus("frozen");

        closedAcct = new BankAccount("Meh", "savings", 10000);
        closedAcct.setStatus("closed");

    }

    @Test
    public void balanceInquiryTest() {
        Assert.assertEquals("Current Balance: 50.0", ryansAcct.balanceInquiry());
        Assert.assertEquals("Account is closed.", closedAcct.balanceInquiry());
        Assert.assertEquals("Error: Please call 1-877-383-4802 for more information.", fakeAcct.balanceInquiry());
    }

    @Test
    public void debitMoneyTest() {
        Assert.assertTrue(ryansAcct.debitMoney(50));
        Assert.assertFalse(fakeAcct.debitMoney(10));
        Assert.assertFalse(closedAcct.debitMoney(10));
    }

    @Test
     public void checkOverDraftTest() {
        Assert.assertTrue(ryansAcct.overDraftProtect());
        ryansAcct.setOverDraft("no");
        Assert.assertFalse(ryansAcct.overDraftProtect());
        ryansAcct.setOverDraft("yes");
        Assert.assertFalse(ryansAcct.overDraftProtect());
    }

    @Test
    public void creditMoney() {
        Assert.assertTrue(ryansAcct.creditMoney(50));
        Assert.assertFalse(ryansAcct.creditMoney(50));
        Assert.assertFalse(fakeAcct.creditMoney(50));
    }
    @Test
    public void closeAccount() {
        Assert.assertFalse(ryansAcct.closeAccount());
        ryansAcct.creditMoney(50);
        Assert.assertTrue(ryansAcct.closeAccount());
        Assert.assertFalse(fakeAcct.closeAccount());
        Assert.assertFalse(closedAcct.closeAccount());
    }

    @Test
    public void reOpenAccount() {
        Assert.assertTrue(fakeAcct.reOpenAccount());
        Assert.assertFalse(closedAcct.reOpenAccount());
    }
}
