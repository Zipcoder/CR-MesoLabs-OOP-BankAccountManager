import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class TransactionServicesTest {

    private  ATM atm;

    @Before
    public void setUp() throws Exception {
        atm = new ATM ("testuserDB.csv", "testaccountDB.csv", "testtransactionDB.csv");
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
    //    @Test
//    public void transactionHistoryShowTest() {
//        DB transactionDB = new DB("transactions.csv");
//
//
//        atm.showTransactions();
//    }
    @Test
    public void savePendingTransactionsTest() {
        DB transactionDB = atm.getTransactionDB();
        transactionDB.clear();
        Assert.assertEquals((int)0, (int)transactionDB.length());

        Transaction trans1 = new Transaction(123.42, new Date(2014, 1, 6, 11,23, 40 ),23,"Opened account", true);
        Transaction trans2 = new Transaction(-23.57, new Date(2015, 2, 7, 10,23, 3 ),12,"Withdrawal", false);
        ArrayList<Transaction> pendingTransactions = new ArrayList<Transaction>();
        pendingTransactions.add(trans1);
        pendingTransactions.add(trans2);

        atm.savePendingTransactionsToDB(pendingTransactions);

        Assert.assertEquals((int)2, (int)transactionDB.length());

        transactionDB.clear();
        Assert.assertEquals((int)0, (int)transactionDB.length());

    }
}
