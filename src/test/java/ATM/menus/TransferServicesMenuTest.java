package ATM.menus;

import ATM.ATM;
import ATM.Exceptions.ClosedAccountException;
import ATM.Exceptions.FrozenAccountException;
import ATM.accounts.Account;
import ATM.accounts.Checking;
import ATM.services.AccountServices;
import ATM.services.TransactionServices;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    public void getHeaderTest() {

    }

    @Test
    public void addAccountOptionsTest() {
    }

    @Test
    public void getDestinationAccounts() {
    }

    @Test
    public void getNameTest() throws ClosedAccountException, FrozenAccountException {
        Account account1 = new Checking(500.00,23,1232123, Account.Status.OPEN);
        accountServices.saveAccountToDB(account1);
        menu = new TransferServicesMenu(atm, account1);

        Assert.assertEquals("Transfer Menu", menu.getName());
    }
}