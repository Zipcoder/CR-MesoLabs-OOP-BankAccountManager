package ATM.accounts;

import ATM.ATM;
import ATM.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CheckingTest {

    private ATM atm;
    private User user;
    private Checking account;

    @Before
    public void setUp() throws Exception {
        atm = new ATM("testuserDB.csv", "testaccountDB.csv", "testtransactionDB.csv");
        user = new User("","","12",44, 123);
    }

    @Test
    public void getOverdraft() {
        account = new Checking(12.00, 44, 123, Account.Status.OPEN, Checking.Overdraft.ON);
        Assert.assertEquals(Checking.Overdraft.ON, account.getOverdraft());
    }

    @Test
    public void setOverdraft() {
        account = new Checking(12.00, 44, 123, Account.Status.OPEN, Checking.Overdraft.ON);
        Assert.assertEquals(Checking.Overdraft.ON, account.getOverdraft());
        account.setOverdraft(Checking.Overdraft.OFF);
        Assert.assertEquals(Checking.Overdraft.OFF, account.getOverdraft());
    }
}