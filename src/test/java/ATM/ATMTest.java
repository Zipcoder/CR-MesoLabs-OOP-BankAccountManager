package ATM;

import ATM.ATM;
import ATM.accounts.Account;
import ATM.accounts.Checking;
import ATM.accounts.Investment;
import ATM.accounts.Savings;
import ATM.services.AccountServices;
import ATM.services.TransactionServices;
import ATM.services.UserServices;
import junitparams.JUnitParamsRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.Arrays;

@RunWith(JUnitParamsRunner.class)
public class ATMTest {

    private ATM atm;
    private UserServices userServices;
    private TransactionServices transactionServices;
    private AccountServices accountServices;

    @Before
    public void setUp() throws Exception {
        atm = new ATM ("testuserDB.csv", "testaccountDB.csv", "testtransactionDB.csv");
        transactionServices = atm.getTransactionServices();
        userServices = atm.getUserServices();
        accountServices = atm.getAccountServices();
    }

    @After
    public void tearDown() throws Exception {
        atm.getUserDB().clear();
        atm.getAccountDB().clear();
        atm.getTransactionDB().clear();
    }

    @Test
    public void getUserDB() {

        DB foundDB = atm.getUserDB();
        String fileName = foundDB.getFileName();
        Assert.assertEquals("testuserDB.csv",fileName);
    }

    @Test
    public void getTransactionDB() {

        DB foundDB = atm.getTransactionDB();
        String fileName = foundDB.getFileName();
        Assert.assertEquals("testtransactionDB.csv",fileName);
    }

    @Test
    public void getAccountDB() {

        DB foundDB = atm.getAccountDB();
        String fileName = foundDB.getFileName();
        Assert.assertEquals("testaccountDB.csv",fileName);
    }



    @Test
    public void setCurrentUser() {

        User foundUser = atm.getCurrentUser();
        Assert.assertEquals(foundUser, null);

        User user = new User("Jim","Brown","goolybib", 12, 12343);
        atm.setCurrentUser(user);

        foundUser = atm.getCurrentUser();
        Assert.assertEquals(foundUser, user);
    }

    @Test
    public void logOutTest() {
        User foundUser = atm.getCurrentUser();
        Assert.assertEquals(foundUser, null);

        User user = new User("Jim","Brown","goolybib", 12, 12343);
        atm.setCurrentUser(user);

        foundUser = atm.getCurrentUser();
        Assert.assertEquals(user, foundUser);

        atm.logOut();

        foundUser = atm.getCurrentUser();
        Assert.assertEquals(null, foundUser);

    }


    // convenience methods for dev environment to clear the DBs - only called from the IDE manually
//    @Test
//    public void clearUserDB() {
//        DB userDB = null;
//        try {
//            userDB = new DB("users.csv", 5);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        userDB.clear();
//    }
//
//    @Test
//    public void clearAccountDB() {
//        DB accountDB = null;
//        try {
//            accountDB = new DB("accounts.csv", 5);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        accountDB.clear();
//    }
//
//    @Test
//    public void clearTransactionDB() {
//        DB transactionDB = null;
//        try {
//            transactionDB = new DB("transactions.csv", 5);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        transactionDB.clear();
//    }

}