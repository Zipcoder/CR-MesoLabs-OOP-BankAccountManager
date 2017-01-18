package wolfe.michael.AccessControlLabTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import wolfe.michael.AccessControlLab.wolfe.michael.AccessControlLabTest.*;

/**
 * Created by michaelwolfe on 1/17/17.
 */
public class BankAccountTest {

    BankAccount ba;
    @Before
    public void setUp(){
        ba = new BankAccount();
        SavingsAccount sa = new SavingsAccount("001", AccountTypes.SAVINGS, "Mike", AccountStatus.OPEN, OverdraftProtection.NO);
        sa.setAccountBalance(100);
        sa.setInterestRate(1);
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
        //ba.setAccountStatus(AccountStatus.OPEN);
        AccountStatus expected = AccountStatus.OPEN;
        AccountStatus actual = ba.getAccountStatus();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setOverdraftProtectionTest(){

    }

    @Test
    public void balanceInquiryTest(){

    }

    @Test
    public void creditAccountTest(){

    }

    @Test
    public void debitAccountTest(){

    }

}
