package battin.preston.AccessControl;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by prestonbattin on 1/17/17.
 */
public class AccountTest {

    @Test
    public void AccountCreationTest() {

        Checking test = new Checking("Checking", "Sally");
        Checking test2 = new Checking("Checking", "Steve");
        int expected = 1;
        int actual = test2.checkingAccountNumber;
        Assert.assertEquals(expected, actual);
        Assert.assertEquals("Sally", test.holdersName);

    }

    @Test
    public void CheckingBalanceTest(){
        Checking test = new Checking("Checking", "SAlly");
        double expected = 0;
        double actual = test.balance;
        Assert.assertEquals(expected,actual,0);
    }

    @Test
    public void TestCreditAccount(){
        Checking test = new Checking("Checking", "Sally");
        test.creditAccount(100.0);
        double expected = 100;
        double actual = test.balance;
       Assert.assertEquals(expected, actual,0);
       Assert.assertEquals(expected, test.getDeposits().get(0),0);
    }

    @Test
    public void TestDebitAccount(){
        Checking test = new Checking("Checking", "Foo");
        test.debitAccount(100);
        double expected = -100;
        double actual = test.balance;
        Assert.assertEquals(expected,actual,0);
        Assert.assertEquals(expected, test.getWithdrawls().get(0),0);

    }
}
