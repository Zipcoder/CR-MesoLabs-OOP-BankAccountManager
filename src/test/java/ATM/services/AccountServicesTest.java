package ATM.services;

import ATM.ATM;
import ATM.Exceptions.BalanceRemainingException;
import ATM.Exceptions.ClosedAccountException;
import ATM.Exceptions.FrozenAccountException;
import ATM.Exceptions.InsufficientFundsException;
import ATM.User;
import ATM.accounts.Account;
import ATM.accounts.Checking;
import ATM.accounts.Investment;
import ATM.accounts.Savings;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class AccountServicesTest {
    private ATM atm;
    private AccountServices accountServices;
    private UserServices userServices;

    @Before
    public void setUp() throws Exception {
        atm = new ATM("testuserDB.csv", "testaccountDB.csv", "testtransactionDB.csv");
        accountServices = atm.getAccountServices();
        userServices = atm.getUserServices();
        accountServices.linkServices();
    }

    @After
    public void tearDown() throws Exception {
        atm.getUserDB().clear();
        atm.getAccountDB().clear();
        atm.getTransactionDB().clear();
    }


    @Test
    public void getMaxAccountNumberTest() {
        accountServices.clearAccountDB();

        int actual = accountServices.getMaxAccountNumber();
        int expected = 0;

        Assert.assertEquals(actual, expected);

        Account account1 = new Checking(1532.34, 23, 2123, Account.Status.valueOf("OPEN"), Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account1);

        actual = accountServices.getMaxAccountNumber();
        expected = 2123;

        Assert.assertEquals(actual, expected);

        Account account2 = new Savings(120.43, 12, 33, 0.01, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account2);

        actual = accountServices.getMaxAccountNumber();
        expected = 2123;

        Assert.assertEquals(actual, expected);

        Account account3 = new Investment(234023.23, 42, 48, 0.06, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account3);
        Account account4 = new Checking(1532.34, 42, 5423, Account.Status.valueOf("OPEN"), Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account4);
        Account account5 = new Savings(120.43, 98, 333223, 0.01, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account5);
        Account account6 = new Investment(234023.23, 42, 9948, 0.06, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account6);
        Account account7 = new Checking(1532.34, 23, 515, Account.Status.valueOf("OPEN"), Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account7);
        Account account8 = new Savings(120.43, 12, 749, 0.01, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account8);
        Account account9 = new Investment(234023.23, 42, 904, 0.06, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account9);

        actual = accountServices.getMaxAccountNumber();
        expected = 333223;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getAccountInfoByID() {
        accountServices.clearAccountDB();

        Account account1 = new Checking(1532.34, 23, 1232123, Account.Status.valueOf("OPEN"), Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account1);
        Account account2 = new Savings(120.43, 12, 333223, 0.01, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account2);
        Account account3 = new Investment(234023.23, 42, 9948, 0.06, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account3);

        String[] actual = accountServices.getAccountInfoByID(333223);
        String[] expected = account2.toStringArray();

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getAccountRowByID() {
        Account account1 = new Checking(1532.34, 23, 1232123, Account.Status.valueOf("OPEN"), Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account1);
        Account account2 = new Savings(120.43, 12, 333223, 0.01, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account2);
        Account account3 = new Investment(234023.23, 42, 9948, 0.06, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account3);

        int actual = accountServices.getAccountRowByID(333223);
        int expected = 1;

        Assert.assertEquals(expected, actual);

        actual = accountServices.getAccountRowByID(1232123);
        expected = 0;

        Assert.assertEquals(expected, actual);

        actual = accountServices.getAccountRowByID(9948);
        expected = 2;

        Assert.assertEquals(expected, actual);

        actual = accountServices.getAccountRowByID(99323248);
        expected = -1;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAccountIDsByUserTest() {
        accountServices.clearAccountDB();
        userServices.clearUserDB();


        User user1 = new User("Jim", "Brown", "goolybib", 98, 12343);
        userServices.saveUserToDB(user1);
        User user2 = new User("Ji123m", "Bro23wn", "gool321ybib", 42, 1234313);
        userServices.saveUserToDB(user2);
        User user3 = new User("Jane", "Himne", "gasdsdool321ybib", 33, 313);
        userServices.saveUserToDB(user3);


        Account account1 = new Checking(1532.34, 23, 1232123, Account.Status.valueOf("OPEN"), Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account1);
        Account account2 = new Savings(120.43, 12, 33, 0.01, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account2);
        Account account3 = new Investment(234023.23, 42, 48, 0.06, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account3);
        Account account4 = new Checking(1532.34, 42, 5423, Account.Status.valueOf("OPEN"), Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account4);
        Account account5 = new Savings(120.43, 98, 333223, 0.01, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account5);
        Account account6 = new Investment(234023.23, 42, 9948, 0.06, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account6);
        Account account7 = new Checking(1532.34, 23, 515, Account.Status.valueOf("OPEN"), Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account7);
        Account account8 = new Savings(120.43, 12, 749, 0.01, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account8);
        Account account9 = new Investment(234023.23, 42, 904, 0.06, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account9);

        int[] rows = accountServices.getAccountRowsByUser(user1);
        String[] accountInfo;
        int[] accts = {333223};
        for (int i = 0; i < rows.length; i++) {
            accountInfo = accountServices.getAccountInfoByRow(rows[i]);
            Assert.assertEquals("user1", (int) user1.getUserID(), (int) Integer.parseInt(accountInfo[1]));
            Assert.assertEquals("user1", (int) accts[i], (int) Integer.parseInt(accountInfo[0]));
        }

        int[] rows2 = accountServices.getAccountRowsByUser(user2);
        String[] accountInfo2;
        int[] accts2 = {48, 5423, 9948, 904};
        for (int i = 0; i < rows2.length; i++) {
            accountInfo2 = accountServices.getAccountInfoByRow(rows2[i]);
            Assert.assertEquals("user2", (int) user2.getUserID(), (int) Integer.parseInt(accountInfo2[1]));
            Assert.assertEquals("user2", (int) accts2[i], (int) Integer.parseInt(accountInfo2[0]));
        }

        int[] rows3 = accountServices.getAccountRowsByUser(user3);
        String[] accountInfo3;
        int[] accts3 = {};
        for (int i = 0; i < rows3.length; i++) {
            accountInfo3 = accountServices.getAccountInfoByRow(rows3[i]);
            Assert.assertEquals("user3", (int) user3.getUserID(), (int) Integer.parseInt(accountInfo3[1]));
            Assert.assertEquals("user3", (int) accts3[i], (int) Integer.parseInt(accountInfo3[0]));
        }
    }

    @Test
    public void getAccountsForUserTest() {
        accountServices.clearAccountDB();
        userServices.clearUserDB();

        User user1 = new User("Jim", "Brown", "goolybib", 98, 12343);
        userServices.saveUserToDB(user1);
        User user2 = new User("Ji123m", "Bro23wn", "gool321ybib", 42, 1234313);
        userServices.saveUserToDB(user2);
        User user3 = new User("Jane", "Himne", "gasdsdool321ybib", 33, 313);
        userServices.saveUserToDB(user3);

        Account account1 = new Checking(1532.34, 23, 1232123, Account.Status.valueOf("OPEN"), Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account1);
        Account account2 = new Savings(120.43, 12, 33, 0.01, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account2);
        Account account3 = new Investment(234023.23, 42, 48, 0.06, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account3);
        Account account4 = new Checking(1532.34, 42, 5423, Account.Status.valueOf("OPEN"), Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account4);
        Account account5 = new Savings(120.43, 98, 333223, 0.01, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account5);
        Account account6 = new Investment(234023.23, 42, 9948, 0.06, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account6);
        Account account7 = new Checking(1532.34, 23, 515, Account.Status.valueOf("OPEN"), Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account7);
        Account account8 = new Savings(120.43, 12, 749, 0.01, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account8);
        Account account9 = new Investment(234023.23, 42, 904, 0.06, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account9);

        ArrayList<Account> actual = accountServices.getAccountsForUser(user1);

        Assert.assertEquals("user1", (int) 1, (int) actual.size());
        Assert.assertTrue("user1.1", Arrays.equals(account5.toStringArray(), actual.get(0).toStringArray()));

        actual = accountServices.getAccountsForUser(user2);

        Assert.assertEquals("user2", (int) 4, (int) actual.size());
        Assert.assertTrue("user2.1", Arrays.equals(account6.toStringArray(), actual.get(2).toStringArray()));
        Assert.assertTrue("user2.3", Arrays.equals(account3.toStringArray(), actual.get(0).toStringArray()));
    }

    @Test
    public void saveAccountToDBTest() {
        accountServices.clearAccountDB();

        Account account1 = new Checking(1532.34, 23, 1232123, Account.Status.valueOf("OPEN"), Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account1);
        Account account2 = new Savings(120.43, 12, 749, 0.01, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account2);
        Account account3 = new Investment(234023.23, 42, 48, 0.06, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account3);
        Account account4 = new Checking(1532.34, 42, 5423, Account.Status.valueOf("OPEN"), Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account4);


        String[] actual = accountServices.getAccountInfoByID(48);
        String[] expected = account3.toStringArray();

        Assert.assertEquals(actual, expected);

        actual = accountServices.getAccountInfoByID(1232123);
        expected = account1.toStringArray();

        Assert.assertEquals(actual, expected);

        int actual2 = accountServices.getAccountDBLength();
        int expected2 = 4;

        Assert.assertEquals(actual, expected);

        Account account10 = new Savings(9990.43, 12, 749, 0.01, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account10);

        actual2 = accountServices.getAccountDBLength();
        expected2 = 4;

        Assert.assertEquals(actual, expected);

        actual = accountServices.getAccountInfoByID(749);
        expected = account10.toStringArray();

        Assert.assertEquals(actual, expected);

    }

    @Test(expected = InsufficientFundsException.class)
    public void insufficientFundWithdrawTest() throws InsufficientFundsException, FrozenAccountException, ClosedAccountException {

        accountServices.clearAccountDB();
        userServices.clearUserDB();
        User user1 = new User("Jim", "Brown", "goolybib", 98, 12343);
        userServices.saveUserToDB(user1);
        Account account1 = new Savings(1532.34, 23, 1232123, .01, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account1);
        accountServices.attemptAccountWithdrawal(account1, 1600);
    }

    @Test(expected = InsufficientFundsException.class)
    public void insufficientFundWithdrawTest2() throws InsufficientFundsException, FrozenAccountException, ClosedAccountException {

        accountServices.clearAccountDB();
        userServices.clearUserDB();
        User user1 = new User("Jim", "Brown", "goolybib", 98, 12343);
        userServices.saveUserToDB(user1);
        Account account1 = new Investment(1532.34, 23, 1232123, .02, Account.Status.valueOf("OPEN"));
        accountServices.saveAccountToDB(account1);
        accountServices.attemptAccountWithdrawal(account1, 1600);
    }

    @Test(expected = InsufficientFundsException.class)
    public void insufficientFundWithdrawTest3() throws InsufficientFundsException, FrozenAccountException, ClosedAccountException {

        accountServices.clearAccountDB();
        userServices.clearUserDB();
        User user1 = new User("Jim", "Brown", "goolybib", 98, 12343);
        userServices.saveUserToDB(user1);
        Account account1 = new Checking(1532.34, 23, 1232123, Account.Status.valueOf("OPEN"), Checking.Overdraft.ON);
        accountServices.saveAccountToDB(account1);
        accountServices.attemptAccountWithdrawal(account1, 1600);
    }

    @Test
    public void insufficientFundWithdrawTest4() throws InsufficientFundsException, FrozenAccountException, ClosedAccountException {

        accountServices.clearAccountDB();
        userServices.clearUserDB();
        User user1 = new User("Jim", "Brown", "goolybib", 98, 12343);
        userServices.saveUserToDB(user1);
        Account account1 = new Checking(1500.00, 23, 1232123, Account.Status.valueOf("OPEN"), Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account1);
        accountServices.attemptAccountWithdrawal(account1, 1600);
        Assert.assertEquals(-100.0, account1.getBalance(), .01);
    }

    @Test
    public void overdraftAutoTransferTest() throws InsufficientFundsException, FrozenAccountException, ClosedAccountException {

        accountServices.clearAccountDB();
        userServices.clearUserDB();
        User user1 = new User("Jim", "Brown", "goolybib", 98, 12343);
        atm.setCurrentUser(user1);
        userServices.saveUserToDB(user1);
        Account account1 = new Checking(1500.00, 98, 1232123, Account.Status.OPEN, Checking.Overdraft.AUTO);
        Account account2 = new Checking(2500.00, 98, 1232124, Account.Status.OPEN, Checking.Overdraft.OFF);

        accountServices.saveAccountToDB(account1);
        accountServices.saveAccountToDB(account2);

        accountServices.attemptAccountWithdrawal(account1, 1600);
        Assert.assertEquals(0.0, account1.getBalance(), .01);
        String[] account2Info = accountServices.getAccountInfoByID(1232124);
        Assert.assertEquals(2400.0, Double.parseDouble(account2Info[2]), .01);
    }


    @Test(expected = FrozenAccountException.class)
    public void withdrawFrozenAcctException() throws InsufficientFundsException, FrozenAccountException, ClosedAccountException {

        accountServices.clearAccountDB();
        userServices.clearUserDB();
        User user1 = new User("Jim", "Brown", "goolybib", 98, 12343);
        userServices.saveUserToDB(user1);
        Account account1 = new Checking(1532.34, 23, 1232123, Account.Status.valueOf("OFAC"), Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account1);
        accountServices.attemptAccountWithdrawal(account1, 1500);

    }


    @Test(expected = ClosedAccountException.class)
    public void withdrawClosedAcctTest() throws InsufficientFundsException, FrozenAccountException, ClosedAccountException {

        accountServices.clearAccountDB();
        userServices.clearUserDB();
        User user1 = new User("Jim", "Brown", "goolybib", 98, 12343);
        userServices.saveUserToDB(user1);
        Account account1 = new Checking(1532.34, 23, 1232123, Account.Status.valueOf("CLOSED"), Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account1);
        accountServices.attemptAccountWithdrawal(account1, 1500);
    }

    @Test
    public void accountWithdrawTest() throws FrozenAccountException, ClosedAccountException, InsufficientFundsException {
        accountServices.clearAccountDB();
        userServices.clearUserDB();
        User user1 = new User("Jim", "Brown", "goolybib", 98, 12343);
        userServices.saveUserToDB(user1);
        Account account1 = new Checking(1532.34, 23, 1232123, Account.Status.valueOf("OPEN"), Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account1);
        accountServices.attemptAccountWithdrawal(account1, 32.34);
        Account retrieved = accountServices.getAccountByInfo(accountServices.getAccountInfoByID(1232123));
        double actual = retrieved.getBalance();
        double expected = 1500.00;

        Assert.assertEquals(actual, expected, .01);


    }

    @Test(expected = ClosedAccountException.class)
    public void closedAccountDepositTest() throws FrozenAccountException, ClosedAccountException, InsufficientFundsException {
        accountServices.clearAccountDB();
        userServices.clearUserDB();
        User user1 = new User("Jim", "Brown", "goolybib", 98, 12343);
        userServices.saveUserToDB(user1);
        Account account1 = new Checking(1532.34, 23, 1232123, Account.Status.valueOf("CLOSED"), Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account1);
        accountServices.accountDeposit(account1, 50.00);
        double actual = account1.getBalance();
        double expected = 1582.34;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void accountDepositTest() throws FrozenAccountException, ClosedAccountException {
        accountServices.clearAccountDB();
        userServices.clearUserDB();
        User user1 = new User("Jim", "Brown", "goolybib", 98, 12343);
        userServices.saveUserToDB(user1);
        Account account1 = new Checking(1532.34, 23, 1232123, Account.Status.valueOf("OPEN"), Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account1);
        accountServices.accountDeposit(account1, 50.00);
        double actual = account1.getBalance();
        double expected = 1582.34;
        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void closeAccountTest() throws BalanceRemainingException, FrozenAccountException, ClosedAccountException {
        accountServices.clearAccountDB();
        userServices.clearUserDB();
        User user1 = new User("Jim", "Brown", "goolybib", 98, 12343);
        userServices.saveUserToDB(user1);
        Account account1 = new Checking(0.00, 23, 1232123, Account.Status.valueOf("OPEN"), Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account1);
        accountServices.closeAccount(account1);
        Account.Status actual = account1.getAcctStatus();
        Account.Status expected = Account.Status.CLOSED;
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = BalanceRemainingException.class)
    public void closeAccountWithBalanceTest() throws BalanceRemainingException, FrozenAccountException, ClosedAccountException {
        accountServices.clearAccountDB();
        userServices.clearUserDB();
        User user1 = new User("Jim", "Brown", "goolybib", 98, 12343);
        userServices.saveUserToDB(user1);
        Account account1 = new Checking(10.00, 23, 1232123, Account.Status.OPEN, Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account1);
        accountServices.closeAccount(account1);
    }

    @Test(expected = FrozenAccountException.class)
    public void closeFrozenAccountTest() throws BalanceRemainingException, FrozenAccountException, ClosedAccountException {
        accountServices.clearAccountDB();
        userServices.clearUserDB();
        User user1 = new User("Jim", "Brown", "goolybib", 98, 12343);
        userServices.saveUserToDB(user1);
        Account account1 = new Checking(0.00, 23, 1232123, Account.Status.OFAC, Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account1);
        accountServices.closeAccount(account1);
    }

    @Test(expected = ClosedAccountException.class)
    public void alreadyClosedAccountTest() throws BalanceRemainingException, FrozenAccountException, ClosedAccountException {
        accountServices.clearAccountDB();
        userServices.clearUserDB();
        User user1 = new User("Jim", "Brown", "goolybib", 98, 12343);
        userServices.saveUserToDB(user1);
        Account account1 = new Checking(0.00, 23, 1232123, Account.Status.CLOSED, Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account1);
        accountServices.closeAccount(account1);
    }

    @Test
    public void getAccountDBLengthTest() {
        accountServices.clearAccountDB();

        Account account1 = new Checking(1532.34, 23, 1232123, Account.Status.OPEN, Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account1);
        Account account2 = new Savings(120.43, 12, 749, 0.01, Account.Status.OPEN);
        accountServices.saveAccountToDB(account2);
        Account account3 = new Investment(234023.23, 42, 48, 0.06, Account.Status.OPEN);
        accountServices.saveAccountToDB(account3);
        Account account4 = new Checking(1532.34, 42, 5423, Account.Status.OPEN, Checking.Overdraft.OFF);
        accountServices.saveAccountToDB(account4);
        int actual = accountServices.getAccountDBLength();
        int expected = 4;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getNewRateTest() {
        Random random = new Random();
        accountServices.clearAccountDB();
        Savings account2 = new Savings(120.43, 12, 749, 0.01, Account.Status.OPEN);
        accountServices.saveAccountToDB(account2);
        Double actual = account2.getInterestRate();
        Double expected = accountServices.getNewRate(random, account2);
        Assert.assertEquals(actual, expected, .1);
    }

    @Test
    public void setNewRateTest() {
        accountServices.clearAccountDB();
        Savings account2 = new Savings(120.43, 12, 749, 0.01, Account.Status.OPEN);
        accountServices.saveAccountToDB(account2);
        account2.setInterestRate(0.03);
        Double actual = account2.getInterestRate();
        Double expected = 0.03;
        Assert.assertEquals(actual, expected, 0);
    }

    @Test
    public void calcInterestTest(){
        accountServices.clearAccountDB();
        Savings account2 = new Savings(200.00, 12, 749, 0.01, Account.Status.OPEN);
        accountServices.saveAccountToDB(account2);
        accountServices.calcInterest(account2);
        Double actual = account2.getBalance();
        Double expected = 200.02;
        Assert.assertEquals(actual, expected, 0);
    }
}




    //    @Test
//    public void clearAccountDB() {
//        ATM.DB accountDB = null;
//        try {
//            accountDB = new ATM.DB("accounts.csv", 5);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        accountDB.clear();
//    }
