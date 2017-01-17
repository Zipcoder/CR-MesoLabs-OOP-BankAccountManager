package crame.randall.AccessControlLab;

import crame.randall.AccessControlLab.AccountTypes;
import crame.randall.AccessControlLab.BankAccount;
import org.junit.*;

/**
 * Created by randallcrame on 1/17/17.
 */
public class BankAccountTest {
BankAccount testAccount = new BankAccount();

    @Test
    public void getAccountTypesTest(){
        testAccount.setAccountType(AccountTypes.CHECKING);
        AccountTypes expected = AccountTypes.CHECKING;
        AccountTypes actual = testAccount.getAccountType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAccountNumberTest(){
        testAccount.setAccountNumber(360000000);
        long expected = 360000000;
        long actual = testAccount.getAccountNumber();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void createAccountNumberTest(){
        BankAccount secondTestAccount = new BankAccount();
        long expected = 360000004;
        long actual = secondTestAccount.getAccountNumber();
        Assert.assertEquals(expected,actual);
    }

}
