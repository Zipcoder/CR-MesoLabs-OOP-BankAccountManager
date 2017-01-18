package wolfe.michael.AccessControlLabTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import wolfe.michael.AccessControlLab.wolfe.michael.AccessControlLabTest.*;

import static wolfe.michael.AccessControlLab.wolfe.michael.AccessControlLabTest.AccountStatus.FROZEN;

/**
 * Created by michaelwolfe on 1/17/17.
 */
public class BankAccountTest {

    BankAccount ba;
    BankAccount.Transactions transactions;
    SavingsAccount sa;
    BankAccount.CheckingAccount ca;
    InvestmentAccount ia;
    @Before
    public void setUp(){

        sa = new SavingsAccount("001", "Mike", AccountStatus.OPEN, OverdraftProtection.NO);
        sa.setAccountBalance(100);
        sa.setInterestRate(1);
        ca = new BankAccount.CheckingAccount("002", "Mike", AccountStatus.OPEN, OverdraftProtection.NO);
        ca.setAccountBalance(50);
        ca.setInterestRate(2);
        ia = new InvestmentAccount("003", "Mike", AccountStatus.OPEN, OverdraftProtection.NO);
        ia.setAccountBalance(50);
        ia.setInterestRate(2);

    }

    @Test
    public void setAndGetAccountHolderNameTest(){
        ba.setAccountHolderName("Mike");
        String expected = "Mike";
        String actual = ba.getAccountHolderName();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAccountStatusTest(){
        AccountStatus expected = AccountStatus.OPEN;
        AccountStatus actual = sa.getAccountStatus();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setAccountStatusTest(){
        AccountStatus expected = FROZEN;
        sa.setAccountStatus(FROZEN);
        AccountStatus actual = sa.getAccountStatus();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setOverdraftProtectionTest(){
        OverdraftProtection expected = OverdraftProtection.NO;
        OverdraftProtection actual = sa.getOverdraftProtection();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getOverdraftProtectionTest(){
        sa.setOverdraftProtection(OverdraftProtection.AUTOXFER);
        OverdraftProtection expected = OverdraftProtection.AUTOXFER;
        OverdraftProtection actual = sa.getOverdraftProtection();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setAndGetInterestRateTest(){
        double expected = 0.0;
        sa.setInterestRate(0.0);
        double actual = sa.getInterestRate();
        Assert.assertEquals(expected, actual, .000001);
    }

    @Test
    public void getAccountBalanceTest(){
        String expected = Double.toString(100);
        String actual = Double.toString(sa.getAccountBalance());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void creditAccountTest(){
        sa.creditAccount(50.0, "001");
        double expected = 150.0;
        double actual = sa.getAccountBalance();
        Assert.assertEquals(expected, actual, .000001);
    }

    @Test
    public void debitAccountTest(){
        sa.debitAccount(50.0, "001");
        double expected = 50.0;
        double actual = sa.getAccountBalance();
        Assert.assertEquals(expected, actual, .000001);
    }

    @Test
    public void transferFundsFromAccountToAccountTest(){
        boolean expected = true;
        boolean actual = true;//some code
    }



}
