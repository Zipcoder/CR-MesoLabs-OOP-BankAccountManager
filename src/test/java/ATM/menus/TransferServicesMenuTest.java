package ATM.menus;

import ATM.ATM;
import ATM.Exceptions.InsufficientFundsException;
import ATM.User;
import ATM.Exceptions.ClosedAccountException;
import ATM.Exceptions.FrozenAccountException;
import ATM.accounts.Account;
import ATM.accounts.Checking;
import ATM.accounts.Savings;
import ATM.services.AccountServices;
import ATM.services.TransactionServices;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class TransferServicesMenuTest {

    private ATM atm;
    private AccountServices accountServices;
    private TransactionServices transactionServices;
    private TransferServicesMenu menu;

    @Before
    public void setUp() throws Exception {
        atm = new ATM("testuserDB.csv", "testaccountDB.csv", "testtransactionDB.csv");
        accountServices = atm.getAccountServices();
        transactionServices = atm.getTransactionServices();
    }

    @After
    public void tearDown() throws Exception {
        atm.getUserDB().clear();
        atm.getAccountDB().clear();
        atm.getTransactionDB().clear();
    }

    @Test
    public void getHeaderTest() throws ClosedAccountException, FrozenAccountException {
        User user = new User("Jim","Dandy","1234",23,123);
        Account account1 = new Checking(500.00,23,12, Account.Status.OPEN);
        accountServices.saveAccountToDB(account1);
        Account account2 = new Savings(600.00,23,15, .01, Account.Status.OPEN);
        accountServices.saveAccountToDB(account2);

        menu = new TransferServicesMenu(atm, account1, accountServices.getAccountsForUser(user));

        String expected = "Transfer from: Checking Account #12  Balance: ($500.00)";
        String actual = menu.getHeader();
    }

    @Test
    public void getHeaderTest2() throws ClosedAccountException, FrozenAccountException {
        User user = new User("Jim","Dandy","1234",23,123);
        Account account1 = new Checking(500.00,23,12, Account.Status.OPEN);
        accountServices.saveAccountToDB(account1);
        Account account2 = new Savings(600.00,23,15, .01, Account.Status.OPEN);
        accountServices.saveAccountToDB(account2);

        menu = new TransferServicesMenu(atm, account1, accountServices.getAccountsForUser(user));

        String expected = "Transfer from: Savings Account #15  Balance: ($600.00)";
        String actual = menu.getHeader();
    }

    @Test (expected = ClosedAccountException.class)
    public void constructorCrashTest() throws ClosedAccountException, FrozenAccountException {
        User user = new User("Jim","Dandy","1234",23,123);
        Account account1 = new Checking(500.00,23,12, Account.Status.CLOSED);
        accountServices.saveAccountToDB(account1);
        Account account2 = new Savings(600.00,23,15, .01, Account.Status.OPEN);
        accountServices.saveAccountToDB(account2);

        menu = new TransferServicesMenu(atm, account1, accountServices.getAccountsForUser(user));
    }

    @Test (expected = FrozenAccountException.class)
    public void constructorCrashTest2() throws ClosedAccountException, FrozenAccountException {
        User user = new User("Jim","Dandy","1234",23,123);
        Account account1 = new Checking(500.00,23,12, Account.Status.CLOSED);
        accountServices.saveAccountToDB(account1);
        Account account2 = new Savings(600.00,23,15, .01, Account.Status.OFAC);
        accountServices.saveAccountToDB(account2);

        menu = new TransferServicesMenu(atm, account2, accountServices.getAccountsForUser(user));
    }

    @Test
    public void addAccountOptionsTest() {
    }

    @Test
    public void accountTransferTest1() throws FrozenAccountException, ClosedAccountException, InsufficientFundsException {
        User user = new User("Jim","Dandy","1234",23,123);
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

        menu = new TransferServicesMenu(atm, account2, accountServices.getAccountsForUser(user));

        String expected = "Checking #12 ($500.00)";
        String actual = menu.addAccountOptions(new ArrayList<String>()).get(0);

        Assert.assertEquals(expected,actual);

        expected = "Savings #16 ($700.00)";
        actual = menu.addAccountOptions(new ArrayList<String>()).get(1);


    }

    @Test
    public void getDestinationAccountsTest() throws ClosedAccountException, FrozenAccountException {
        User user = new User("Jim","Dandy","1234",23,123);
        Account account1 = new Checking(500.00,23,12, Account.Status.OPEN);
        accountServices.saveAccountToDB(account1);
        Account account2 = new Checking(600.00,23,15, Account.Status.OPEN);
        accountServices.saveAccountToDB(account2);
        Account account3 = new Savings(700.00,23,16, .10, Account.Status.OPEN);
        accountServices.saveAccountToDB(account3);
        Account account4 = new Checking(800.00,23,19, Account.Status.OPEN);
        accountServices.saveAccountToDB(account4);
        Account account5 = new Checking(900.00,23,22, Account.Status.OPEN);
        accountServices.saveAccountToDB(account5);

        menu = new TransferServicesMenu(atm, account2, accountServices.getAccountsForUser(user));

        ArrayList<Account> expected = new ArrayList<Account>(Arrays.asList(account1,account3,account4,account5));
        ArrayList<Account> actual = menu.getDestinationAccounts();
        for (int i = 0; i < actual.size(); i++) {
            Assert.assertTrue(expected.get(i).equals(actual.get(i)));
        }
    }

    @Test
    public void getNameTest() throws ClosedAccountException, FrozenAccountException {
        User user = new User("Jim","Dandy","1234",23,123);
        Account account1 = new Checking(500.00,23,12, Account.Status.OPEN);
        accountServices.saveAccountToDB(account1);

        menu = new TransferServicesMenu(atm, account1, accountServices.getAccountsForUser(user));

        Assert.assertEquals("Transfer Menu", menu.getName());
    }
}