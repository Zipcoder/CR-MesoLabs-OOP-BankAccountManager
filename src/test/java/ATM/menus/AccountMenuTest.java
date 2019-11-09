package ATM.menus;

import ATM.ATM;
import ATM.Exceptions.FrozenAccountException;
import ATM.accounts.Account;
import ATM.accounts.Checking;
import ATM.accounts.Investment;
import ATM.accounts.Savings;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountMenuTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getName() throws FrozenAccountException {
        Account account = new Checking(123.45, 123, 9675, Account.Status.OPEN);
        AccountMenu acctMenu = new AccountMenu(new ATM("users.csv", "accounts.csv", "transactions.csv"), account);
        Assert.assertEquals("Account Menu", acctMenu.getName());

    }

    @Test
    public void getHeaderTest() throws FrozenAccountException{
        Account account = new Checking(123.45, 123, 9675, Account.Status.OPEN);
        AccountMenu acctMenu = new AccountMenu(new ATM("users.csv", "accounts.csv", "transactions.csv"), account);
        String actual = acctMenu.getHeader();
        String expected = "Checking Account #9675  Balance: $123.45";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getHeaderTest2() throws FrozenAccountException{
        Account account = new Savings(123.45, 123, 9675, .03, Account.Status.OPEN);
        AccountMenu acctMenu = new AccountMenu(new ATM("users.csv", "accounts.csv", "transactions.csv"), account);
        String actual = acctMenu.getHeader();
        String expected = "Savings Account #9675  Balance: $123.45  Interest Rate: 0.03%";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getHeaderTest3() throws FrozenAccountException{
        Account account = new Investment(123.45, 123, 9675, .08, Account.Status.OPEN);
        AccountMenu acctMenu = new AccountMenu(new ATM("users.csv", "accounts.csv", "transactions.csv"), account);
        String actual = acctMenu.getHeader();
        String expected = "Investment Account #9675  Balance: $123.45  Risk: 8/10";
        Assert.assertEquals(expected, actual);
    }
}