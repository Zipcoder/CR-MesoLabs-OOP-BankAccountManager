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
        AccountTypes expected = testAccount.setAccountType(AccountTypes.CHECKING);
        AccountTypes actual = testAccount.getAccountType();
        Assert.assertEquals(expected, actual);

    }

}
