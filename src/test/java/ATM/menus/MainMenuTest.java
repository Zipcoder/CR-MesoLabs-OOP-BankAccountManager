package ATM.menus;

import ATM.ATM;
import ATM.User;
import ATM.Exceptions.ClosedAccountException;
import ATM.Exceptions.FrozenAccountException;
import ATM.Exceptions.InsufficientFundsException;
import ATM.accounts.Account;
import ATM.accounts.Checking;
import ATM.accounts.Savings;
import ATM.services.AccountServices;
import ATM.services.TransactionServices;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class MainMenuTest {

    private ATM atm;
    private AccountServices accountServices;
    private TransactionServices transactionServices;
    private MainMenu menu;

    @Before
    public void setUp() throws Exception {
        atm = new ATM("testuserDB.csv", "testaccountDB.csv", "testtransactionDB.csv");
        accountServices = atm.getAccountServices();
        transactionServices = atm.getTransactionServices();
        menu = new MainMenu(atm);
    }

    @After
    public void tearDown() throws Exception {
        atm.getUserDB().clear();
        atm.getAccountDB().clear();
        atm.getTransactionDB().clear();
    }

    @Test
    public void addAccountOptionsTest1() throws FrozenAccountException, ClosedAccountException, InsufficientFundsException {
        User user = new User("Jim","Dandy","1234",23,123);
        atm.setCurrentUser(user);
        Account account1 = new Checking(500.00,23,12, Account.Status.CLOSED);
        accountServices.saveAccountToDB(account1);
        Account account2 = new Checking(600.00,23,15, Account.Status.OPEN);
        accountServices.saveAccountToDB(account2);
        Account account3 = new Savings(700.00,23,16, .10, Account.Status.OPEN);
        accountServices.saveAccountToDB(account3);
        Account account4 = new Checking(800.00,23,19, Account.Status.OFAC);
        accountServices.saveAccountToDB(account4);
        Account account5 = new Checking(900.00,23,22, Account.Status.OPEN);
        accountServices.saveAccountToDB(account5);
        Account account6 = new Checking(10900.00,67,21, Account.Status.OPEN);
        accountServices.saveAccountToDB(account6);

        ArrayList<String> result = menu.addAccountOptions(new ArrayList<String>(Arrays.asList("zarp")));
        String expected = "Checking #12 ($500.00)";
        String actual = result.get(1);

        Assert.assertEquals(expected,actual);

        expected = "Savings #16 ($700.00)";
        actual = result.get(3);

        Assert.assertEquals(expected,actual);

        expected = "Checking #19 (FROZEN)";
        actual = result.get(4);

        Assert.assertEquals(expected,actual);

    }

    @Test
    public void getNameTest() throws ClosedAccountException, FrozenAccountException {
        User user = new User("Jim","Dandy","1234",23,123);
        Account account1 = new Checking(500.00,23,12, Account.Status.OPEN);
        accountServices.saveAccountToDB(account1);

        Assert.assertEquals("Main Menu", menu.getName());
    }
}