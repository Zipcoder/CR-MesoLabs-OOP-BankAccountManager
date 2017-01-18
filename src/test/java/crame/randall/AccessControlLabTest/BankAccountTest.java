package crame.randall.AccessControlLabTest;

import crame.randall.AccessControlLab.AccountStatus;
import crame.randall.AccessControlLab.AccountTypes;
import crame.randall.AccessControlLab.BankAccount;
import org.junit.*;

/**
 * Created by randallcrame on 1/17/17.
 */
public class BankAccountTest {
    BankAccount testAccount = new BankAccount(AccountTypes.CHECKING);
    BankAccount secondTestAccount = new BankAccount(AccountTypes.CHECKING);

    @Test
    public void getAccountTypesTest(){
        AccountTypes expected = AccountTypes.CHECKING;
        AccountTypes actual = testAccount.getAccountType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void createAccountNumberTest(){
        long expected = 360000004;
        long actual = secondTestAccount.getAccountNumber();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getAccountBalanceTest(){
        double expected = 0.0;
        double  actual = testAccount.getAccountBalance();
        Assert.assertEquals(expected,actual, 0.0);
    }

    @Test
    public void getAccountHolderNameTest(){
        String expected = "Enter Name";
        String actual = testAccount.getAccountHoldersName();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAccountInterestRateTest(){
        double expected = 0.0;
        double actual = testAccount.getAccountInterestRate();
        Assert.assertEquals(expected, actual, 0.0);
    }

    @Test
    public void getAccountStatusTest(){
        AccountStatus expected = AccountStatus.OPEN;
        AccountStatus actual = testAccount.getAccountStatus();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void balanceInquiryTest(){
        String expected = "0.0";
        String actual = testAccount.balanceInquiry();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void requestDebitAccount(){
        String expected = "Unable to Complete Request...";
        String actual = testAccount.requestDebitAccount(10000);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void requestCreditAccount(){
        String expected = "Credit Complete";
        String actual = testAccount.requestCreditAccount(20);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void requestTransferToAccountTest(){
        String expected = "Transfer Complete";
        String actual = testAccount.requestTransferToAccount(secondTestAccount, 25);
        System.out.println(testAccount.getAccountBalance());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void requestTransferFromAccountTest(){
        String expected = "Transfer Complete";
        String actual = testAccount.requestTransferFromAccount(secondTestAccount, 25);
        System.out.println(testAccount.getAccountBalance());
        Assert.assertEquals(expected, actual);
    }
}
