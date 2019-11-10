package ATM.accounts;

import ATM.ATM;
import ATM.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
        account = new Checking(12.00, 44, 123, Account.Status.OPEN, Checking.Overdraft.TRUE);
        Assert.assertEquals(Checking.Overdraft.TRUE, account.getOverdraft());
    }

    @Test
    public void setOverdraft() {
        account = new Checking(12.00, 44, 123, Account.Status.OPEN, Checking.Overdraft.TRUE);
        Assert.assertEquals(Checking.Overdraft.TRUE, account.getOverdraft());
        account.setOverdraft(Checking.Overdraft.FALSE);
        Assert.assertEquals(Checking.Overdraft.FALSE, account.getOverdraft());
    }
}