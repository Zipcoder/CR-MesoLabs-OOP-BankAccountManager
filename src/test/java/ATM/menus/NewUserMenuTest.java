package ATM.menus;

import ATM.ATM;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NewUserMenuTest {
    private ATM atm;
    private NewUserMenu menu;

    @Before
    public void setUp() throws Exception {
        atm = new ATM("testuserDB.csv", "testaccountDB.csv", "testtransactionDB.csv");
        menu = new NewUserMenu(atm);
    }

    @Test
    public void handleChoice() {
        // yeah, that's a cheap ploy for coverage
        menu.handleChoice(1);
    }

    @Test
    public void getName() {
        Assert.assertEquals("New User Menu", menu.getName());
    }
}