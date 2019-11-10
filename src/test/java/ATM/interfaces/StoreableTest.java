package ATM.interfaces;

import ATM.User;
import ATM.accounts.Account;
import ATM.accounts.Checking;
import ATM.accounts.Investment;
import ATM.accounts.Savings;
import org.junit.Assert;
import org.junit.Test;

public class StoreableTest {

    @Test
    public void toStringArrayUser() {
        User user = new User("Jim","Brown","goolybib", 12, 12343);

        String[] actual = user.toStringArray();
        String[] expected = new String[] {
                "12",
                "Brown",
                "Jim",
                "12343",
                "goolybib"
        };

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void toStringArrayAccountChecking() {
        Account account = new Checking(12.23, 23, 3432, Account.Status.CLOSED, Checking.Overdraft.OFF);

        String[] actual = account.toStringArray();
        String[] expected = new String[] {
                "3432",
                "23",
                "12.23",
                "Checking",
                "",
                "CLOSED"
        };

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void toStringArrayAccountSavings() {
        Account account = new Savings(12.23, 23, 3432, 0.05, Account.Status.OFAC);

        String[] actual = account.toStringArray();
        String[] expected = new String[] {
                "3432",
                "23",
                "12.23",
                "Savings",
                "0.05",
                "OFAC"
        };

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void toStringArrayAccountInvestment() {
        Account account = new Investment(12.23, 23, 3432, 0.2, Account.Status.OPEN);

        String[] actual = account.toStringArray();
        String[] expected = new String[] {
                "3432",
                "23",
                "12.23",
                "Investment",
                "0.2",
                "OPEN"
        };

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void saveToDB() {
    }
}