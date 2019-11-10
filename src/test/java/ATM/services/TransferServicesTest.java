package ATM.services;

import ATM.ATM;
import ATM.Exceptions.ClosedAccountException;
import ATM.Exceptions.FrozenAccountException;
import ATM.Exceptions.InsufficientFundsException;
import ATM.User;
import ATM.accounts.Account;
import ATM.accounts.Checking;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TransferServicesTest {

    private ATM atm;
    private AccountServices accountServices;
    private TransactionServices transactionServices;

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

    @Test(expected = ClosedAccountException.class)
    public void accountTransferExceptionTest1() throws FrozenAccountException, ClosedAccountException, InsufficientFundsException {
        accountServices.clearAccountDB();

        Account account1 = new Checking(500.00,23,1232123, Account.Status.OPEN, Checking.Overdraft.FALSE);
        accountServices.saveAccountToDB(account1);
        Account account2 = new Checking(2000.00,23,1232123, Account.Status.CLOSED, Checking.Overdraft.FALSE);
        accountServices.saveAccountToDB(account1);

        TransferServices transferServices = new TransferServices(atm, account1);
        transferServices.executeTransfer(account1, account2, 200.00);
    }

    @Test(expected = FrozenAccountException.class)
    public void accountTransferExceptionTest2() throws FrozenAccountException, ClosedAccountException, InsufficientFundsException {
        accountServices.clearAccountDB();

        Account account1 = new Checking(1532.34,23,1232123, Account.Status.OPEN, Checking.Overdraft.FALSE);
        accountServices.saveAccountToDB(account1);
        Account account2 = new Checking(1532.34,23,1232123, Account.Status.OFAC, Checking.Overdraft.FALSE);
        accountServices.saveAccountToDB(account1);

        TransferServices transferServices = new TransferServices(atm, account1);
        transferServices.executeTransfer(account1, account2, 1203.00);
    }

    @Test(expected = InsufficientFundsException.class)
    public void accountTransferExceptionTest3() throws FrozenAccountException, ClosedAccountException, InsufficientFundsException {
        accountServices.clearAccountDB();

        Account account1 = new Checking(1532.34,23,1232123, Account.Status.OPEN, Checking.Overdraft.FALSE);
        accountServices.saveAccountToDB(account1);
        Account account2 = new Checking(1532.34,23,1232123, Account.Status.CLOSED, Checking.Overdraft.FALSE);
        accountServices.saveAccountToDB(account1);

        TransferServices transferServices = new TransferServices(atm, account1);
        transferServices.executeTransfer(account1, account2, 2203.00);
    }

    @Test
    public void accountTransferTest1() throws FrozenAccountException, ClosedAccountException, InsufficientFundsException {
        accountServices.clearAccountDB();

        Account account1 = new Checking(500.00,23,1232123, Account.Status.OPEN, Checking.Overdraft.FALSE);
        accountServices.saveAccountToDB(account1);
        Account account2 = new Checking(2000.00,23,1232123, Account.Status.OPEN, Checking.Overdraft.FALSE);
        accountServices.saveAccountToDB(account1);

        TransferServices transferServices = new TransferServices(atm, account1);
        transferServices.executeTransfer(account1, account2, 200.00);

        Assert.assertEquals(300.00, account1.getBalance(), .01);
        Assert.assertEquals(2200.00, account2.getBalance(), .01);
    }

    @Test
    public void accountTransferTest2() throws FrozenAccountException, ClosedAccountException, InsufficientFundsException {
        accountServices.clearAccountDB();

        Account account1 = new Checking(500.00,23,1232123, Account.Status.OPEN, Checking.Overdraft.FALSE);
        accountServices.saveAccountToDB(account1);
        Account account2 = new Checking(2000.00,23,1232123, Account.Status.OPEN, Checking.Overdraft.FALSE);
        accountServices.saveAccountToDB(account1);

        TransferServices transferServices = new TransferServices(atm, account1);
        transferServices.executeTransfer(account1, account2, 500.00);

        Assert.assertEquals(0.00, account1.getBalance(), .01);
        Assert.assertEquals(2500.00, account2.getBalance(), .01);
    }

    @Test
    public void accountTransferTest3() throws FrozenAccountException, ClosedAccountException, InsufficientFundsException {
        accountServices.clearAccountDB();

        Account account1 = new Checking(500.00,23,1232123, Account.Status.OPEN, Checking.Overdraft.FALSE);
        accountServices.saveAccountToDB(account1);
        Account account2 = new Checking(2000.00,23,1232124, Account.Status.OPEN, Checking.Overdraft.FALSE);
        accountServices.saveAccountToDB(account1);

        Assert.assertEquals(0,transactionServices.getTransactionRowsByAccount(account1).length);
        Assert.assertEquals(0,transactionServices.getTransactionRowsByAccount(account2).length);

        TransferServices transferServices = new TransferServices(atm, account1);
        transferServices.executeTransfer(account1, account2, 200.00);

        Assert.assertEquals(300.00, account1.getBalance(), .01);
        Assert.assertEquals(2200.00, account2.getBalance(), .01);
        Assert.assertEquals(1,transactionServices.getTransactionRowsByAccount(account1).length);
        Assert.assertEquals(1,transactionServices.getTransactionRowsByAccount(account2).length);

    }

    @Test
    public void accountTransferTest4() throws FrozenAccountException, ClosedAccountException, InsufficientFundsException {
        accountServices.clearAccountDB();

        Account account1 = new Checking(500.00,23,1232123, Account.Status.OPEN, Checking.Overdraft.FALSE);
        accountServices.saveAccountToDB(account1);
        Account account2 = new Checking(2000.00,23,1232124, Account.Status.OPEN, Checking.Overdraft.FALSE);
        accountServices.saveAccountToDB(account1);

        TransferServices transferServices = new TransferServices(atm, account1);
        transferServices.executeTransfer(account1, account2, 200.00);

        String[] array1 = transactionServices.getTransactionsForAccount(account1).get(0).toStringArray();
        Assert.assertEquals("debit",array1[0]);
        Assert.assertEquals("1232123",array1[1]);
        Assert.assertEquals("-200.00",array1[2]);
        Assert.assertEquals("Transfer to account 1232124",array1[4]);
        String[] array2 = transactionServices.getTransactionsForAccount(account2).get(0).toStringArray();
        Assert.assertEquals("credit",array2[0]);
        Assert.assertEquals("1232124",array2[1]);
        Assert.assertEquals("200.00",array2[2]);
        Assert.assertEquals("Transfer from account 1232123",array2[4]);
    }

}
