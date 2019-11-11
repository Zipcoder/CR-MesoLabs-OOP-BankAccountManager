package ATM.menus;

import ATM.ATM;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserMenuTest {

    @Test
    public void getName() {
        ATM atm = new ATM("testuserDB.csv", "testaccountDB.csv", "testtransactionDB.csv");
        Assert.assertEquals("User Menu", new UserMenu(atm).getName());
    }
}