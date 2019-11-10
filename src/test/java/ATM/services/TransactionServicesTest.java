package ATM.services;

import ATM.ATM;
import ATM.DB;
import ATM.Transaction;
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
import java.util.Date;

public class TransactionServicesTest {

    private ATM atm;
    private TransactionServices transactionsServices;
    UserServices userServices;
    AccountServices accountServices;

    @Before
    public void setUp() throws Exception {
        atm = new ATM("testuserDB.csv", "testaccountDB.csv", "testtransactionDB.csv");
        transactionsServices = atm.getTransactionServices();
        userServices =atm.getUserServices();
        accountServices = atm.getAccountServices();
        transactionsServices.linkServices();
    }

    @After
    public void tearDown() throws Exception {
        atm.getUserDB().clear();
        atm.getAccountDB().clear();
        atm.getTransactionDB().clear();
    }
    @Test
    public void getTransactionDB() {
        DB foundDB = atm.getTransactionDB();
        String fileName = foundDB.getFileName();
        Assert.assertEquals("testtransactionDB.csv",fileName);
    }
    @Test
    public void savePendingTransactionsTest() {
        transactionsServices.clearTransactionsDB();
        Assert.assertEquals((int)0, (int)transactionsServices.getTransactionDB().length());

        Transaction trans1 = new Transaction(123.42, new Date(2014, 1, 6, 11,23, 40 ),23,"Opened account", true);
        Transaction trans2 = new Transaction(-23.57, new Date(2015, 2, 7, 10,23, 3 ),12,"Withdrawal", false);
        ArrayList<Transaction> pendingTransactions = new ArrayList<Transaction>();
        pendingTransactions.add(trans1);
        pendingTransactions.add(trans2);

        transactionsServices.savePendingTransactionsToDB(pendingTransactions);

        Assert.assertEquals((int)2, (int)transactionsServices.getTransactionDB().length());

        transactionsServices.clearTransactionsDB();
        Assert.assertEquals((int)0, (int)transactionsServices.getTransactionDB().length());

    }
    @Test
    public void saveTransactionTest(){
        transactionsServices.clearTransactionsDB();
        Assert.assertEquals((int)0, (int)transactionsServices.getTransactionDB().length());

        Transaction trans1 = new Transaction(123.42, new Date(2014, 1, 6, 11,23, 40 ),23,"Opened account", true);
        Transaction trans2 = new Transaction(-23.57, new Date(2015, 2, 7, 10,23, 3 ),12,"Withdrawal", false);

        transactionsServices.saveTransactionToDB(trans1);
        transactionsServices.saveTransactionToDB(trans2);

        Assert.assertEquals((int)2, (int)transactionsServices.getTransactionDB().length());
    }

    @Test
    public void getTransactionRowsByUserTest(){
        transactionsServices.clearTransactionsDB();
        userServices.clearUserDB();
        Assert.assertEquals((int)0, (int)transactionsServices.getTransactionDB().length());

        User user1 = new User("Jim", "Brown", "goolybib", 12, 12343);
        userServices.saveUserToDB(user1);
        User user2 = new User("Ji123m", "Bro23wn", "gool321ybib", 35, 1234313);
        userServices.saveUserToDB(user2);
        User user3 = new User("Jane", "Himne", "gasdsdool321ybib", 41, 313);
        userServices.saveUserToDB(user3);

        Account account1 = new Checking(1532.34, 23, 1232123, Account.Status.OPEN, Checking.Overdraft.FALSE);
        accountServices.saveAccountToDB(account1);
        Account account2 = new Savings(10000.00, 12, 749, 0.01, Account.Status.OPEN);
        accountServices.saveAccountToDB(account2);
        Account account3 = new Investment(234023.23, 41, 41, 0.06, Account.Status.OPEN);
        accountServices.saveAccountToDB(account3);

        Transaction trans1 = new Transaction(123.42, new Date(2014, 1, 6, 11,23, 40 ),123123,"Opened account", true);
        transactionsServices.saveTransactionToDB(trans1);
        Transaction trans2 = new Transaction(-23.57, new Date(2015, 2, 7, 10,23, 3 ),12,"Withdrawal", false);
        transactionsServices.saveTransactionToDB(trans2);
        Transaction trans3 = new Transaction(200.42, new Date(2014, 1, 8, 5,20, 40 ),35,"Opened account", true);
        transactionsServices.saveTransactionToDB(trans3);
        Transaction trans4 = new Transaction(-40.57, new Date(2015, 2, 9, 6,39, 3 ),41,"Withdrawal", false);
        transactionsServices.saveTransactionToDB(trans4);
        Transaction trans5 = new Transaction(-30.57, new Date(2015, 2, 9, 7,40, 13 ),41,"Withdrawal", false);
        transactionsServices.saveTransactionToDB(trans5);

        int[] actual = transactionsServices.getTransactionRowsByUser(user3);
        int[] expected = new int[]{3,4};
        Assert.assertArrayEquals(expected,actual);
    }



    @Test
    public void getTransactionRowsByAccountTest(){
        transactionsServices.clearTransactionsDB();
        userServices.clearUserDB();
        Assert.assertEquals((int)0, (int)transactionsServices.getTransactionDB().length());

        User user1 = new User("Jim", "Brown", "goolybib", 12, 12343);
        userServices.saveUserToDB(user1);
        User user2 = new User("Ji123m", "Bro23wn", "gool321ybib", 35, 1234313);
        userServices.saveUserToDB(user2);
        User user3 = new User("Jane", "Himne", "gasdsdool321ybib", 41, 313);
        userServices.saveUserToDB(user3);

        Account account1 = new Checking(1532.34, 23, 1232123, Account.Status.OPEN, Checking.Overdraft.FALSE);
        accountServices.saveAccountToDB(account1);
        Account account2 = new Savings(10000.00, 12, 749, 0.01, Account.Status.OPEN);
        accountServices.saveAccountToDB(account2);
        Account account3 = new Investment(234023.23, 41, 41, 0.06, Account.Status.OPEN);
        accountServices.saveAccountToDB(account3);

        Transaction trans1 = new Transaction(123.42, new Date(2014, 1, 6, 11,23, 40 ),123123,"Opened account", true);
        transactionsServices.saveTransactionToDB(trans1);
        Transaction trans2 = new Transaction(-23.57, new Date(2015, 2, 7, 10,23, 3 ),12,"Withdrawal", false);
        transactionsServices.saveTransactionToDB(trans2);
        Transaction trans3 = new Transaction(200.42, new Date(2014, 1, 8, 5,20, 40 ),35,"Opened account", true);
        transactionsServices.saveTransactionToDB(trans3);
        Transaction trans4 = new Transaction(-40.57, new Date(2015, 2, 9, 6,39, 3 ),41,"Withdrawal", false);
        transactionsServices.saveTransactionToDB(trans4);
        Transaction trans5 = new Transaction(-30.57, new Date(2015, 2, 9, 7,40, 13 ),41,"Withdrawal", false);
        transactionsServices.saveTransactionToDB(trans5);

        int[]actual = transactionsServices.getTransactionRowsByAccount(account1);
        int[]expected = new int[0];

        Assert.assertArrayEquals(expected, actual);

    }
    @Test
    public void getTransactionInfoByRowTest(){
            transactionsServices.clearTransactionsDB();
            userServices.clearUserDB();
            Assert.assertEquals((int)0, (int)transactionsServices.getTransactionDB().length());

            User user1 = new User("Jim", "Brown", "goolybib", 12, 12343);
            userServices.saveUserToDB(user1);
            User user2 = new User("Ji123m", "Bro23wn", "gool321ybib", 35, 1234313);
            userServices.saveUserToDB(user2);
            User user3 = new User("Jane", "Himne", "gasdsdool321ybib", 41, 313);
            userServices.saveUserToDB(user3);

            Account account1 = new Checking(1532.34, 23, 1232123, Account.Status.OPEN, Checking.Overdraft.FALSE);
            accountServices.saveAccountToDB(account1);
            Account account2 = new Savings(10000.00, 12, 749, 0.01, Account.Status.OPEN);
            accountServices.saveAccountToDB(account2);
            Account account3 = new Investment(234023.23, 41, 41, 0.06, Account.Status.OPEN);
            accountServices.saveAccountToDB(account3);

            Transaction trans1 = new Transaction(123.42, new Date(2014, 1, 6, 11,23, 40 ),123123,"Opened account", true);
            transactionsServices.saveTransactionToDB(trans1);
            Transaction trans2 = new Transaction(-23.57, new Date(2015, 2, 7, 10,23, 3 ),12,"Withdrawal", false);
            transactionsServices.saveTransactionToDB(trans2);
            Transaction trans3 = new Transaction(200.42, new Date(2014, 1, 8, 5,20, 40 ),35,"Opened account", true);
            transactionsServices.saveTransactionToDB(trans3);
            Transaction trans4 = new Transaction(-40.57, new Date(2015, 2, 9, 6,39, 3 ),41,"Withdrawal", false);
            transactionsServices.saveTransactionToDB(trans4);
            Transaction trans5 = new Transaction(-30.57, new Date(2015, 2, 9, 7,40, 13 ),41,"Withdrawal", false);
            transactionsServices.saveTransactionToDB(trans5);

            String[]expected = transactionsServices.getTransactionInfoByRow(0);
            String[]actual = new String[]{"credit","123123","123.42","Fri Feb 06 11:23:40 EST 3914","Opened account"};

            Assert.assertArrayEquals(expected, actual);

    }


    //    @Test
//    public void clearTransactionDB() {
//        ATM.DB transactionDB = null;
//        try {
//            transactionDB = new ATM.DB("transactions.csv", 5);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        transactionDB.clear();
//    }
    //    @Test
//    public void transactionHistoryShowTest() {
//        ATM.DB transactionDB = new ATM.DB("transactions.csv");
//
//
//        atm.showTransactions();
//    }
//    @Test
//    public void savePendingTransactionsTest() {
//        transactionsServices.clearTransactionsDB();
//        Assert.assertEquals((int)0, (int)transactionDB.length());
//
//        Transaction trans1 = new Transaction(123.42, new Date(2014, 1, 6, 11,23, 40 ),23,"Opened account", true);
//        Transaction trans2 = new Transaction(-23.57, new Date(2015, 2, 7, 10,23, 3 ),12,"Withdrawal", false);
//        ArrayList<Transaction> pendingTransactions = new ArrayList<Transaction>();
//        pendingTransactions.add(trans1);
//        pendingTransactions.add(trans2);
//
//        transactionsServices.savePendingTransactionsToDB(pendingTransactions);
//
//        Assert.assertEquals((int)2, (int)transactionDB.length());
//
//        transactionsServices.clearTransactionsDB();
//        Assert.assertEquals((int)0, (int)transactionDB.length());
//
//    }
}
