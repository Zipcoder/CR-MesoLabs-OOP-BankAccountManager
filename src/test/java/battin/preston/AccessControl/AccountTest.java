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
        int actual = test2.getCheckingAccountNumber();
        Assert.assertEquals(expected, actual);
        Assert.assertEquals("Sally", test.getHoldersName());

    }

    @Test
    public void CheckingBalanceTest(){
        Checking test = new Checking("Checking", "SAlly");
        double expected = 0;
        double actual = test.getBalance();
        Assert.assertEquals(expected,actual,0);
    }

    @Test
    public void TestCreditAccount(){
        Checking test = new Checking("Checking", "Sally");
        test.creditAccount(100.0);
        double expected = 100;
        double actual = test.getBalance();
       Assert.assertEquals(expected, actual,0);
       Assert.assertEquals(expected, test.getDeposits().get(0),0);
    }

    @Test
    public void TestDebitAccount() {
        Checking test = new Checking("Checking", "Foo");
        test.debitAccount(100);
        double expected = -100;
        double actual = test.getBalance();
        Assert.assertEquals(expected, actual, 0);
        Assert.assertEquals(expected, test.getWithdrawls().get(0), 0);
    }

        @Test
        public void TestSetAccountHoldersName(){
            Checking test = new Checking("Checking", "Foo");
            test.setAccountHoldersName("Bar");
            String expected = "Bar";
            String actual = test.getHoldersName();
            Assert.assertEquals(expected, actual);
        }

        @Test
        public void TestTansfers(){
            Checking test = new Checking("Checking", "Foo");
            Savings testFoo = new Savings("Savings", "Foo");
            test.creditAccount(5000);
            test.makeTransfer(testFoo, 600);
            double expected = 600;
            double actual = testFoo.getBalance();
            Assert.assertEquals(expected,actual,0);
            System.out.println(test.getTransfers());
            System.out.println(testFoo.getTransfers());
        }


        @Test
        public void TestInterestRates(){
            Checking test = new Checking("Checking", "Foo");
            double expected = .05;
            double actual = test.getRate();
            Assert.assertEquals(expected,actual,0);
            test.setInterestRates(100);
            Assert.assertEquals(100, test.getRate(),0);

        }

        @Test
        public void TestSetName(){
            Checking test = new Checking("Checking", "Foo");
            test.setAccountHoldersName("Kevin");
            String expected = "Kevin";
            String actual = test.getHoldersName();
            Assert.assertEquals(expected,actual);

        }


        @Test
        public void TestSetStatus(){
            Checking test = new Checking("Checking", "Foo");
            String expected = "Open";
            String actual = test.getStatus();
            Assert.assertEquals(expected,actual);
            Assert.assertEquals(expected, test.getStatuses().get(0));
        }


     @Test
    public void TestOverDraftProtection(){
         Checking test = new Checking("Checking", "Foo");
         String expected = "On";
         String actaul = test.getOverDraftProtection();
         Assert.assertEquals(expected, actaul );
     }

    }

