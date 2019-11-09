package ATM.services;

import ATM.ATM;
import ATM.DB;
import ATM.Transaction;
import ATM.User;
import ATM.accounts.Account;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TransactionServices {

    private ATM atm;
    private DB transactionDB;   // 0: credit/debit 1: accountID 2: amount (signed) 3: timeStamp 4: description
    private AccountServices accountServices;

    public TransactionServices(DB transactionDB, ATM atm) {
        this.transactionDB = transactionDB;
        this.atm = atm;
        this.accountServices = atm.getAccountServices();
    }

    public DB getTransactionDB() {
        return this.transactionDB;
    }



    public int[] getTransactionRowsByUser (User user) {
        int[] accountRows =  this.accountServices.getAccountRowsByUser(user);
        ArrayList<Integer> accountNums = new ArrayList<>();
        for (int row : accountRows) {
            accountNums.add(Integer.parseInt(this.accountServices.getAccountInfoByRow(row)[0]));
        }

        ArrayList<Integer> rows = new ArrayList<>();
//        int [] recordRowNums = null;
//        for (int accountNum : accountNums) {
//            recordRowNums = this.transactionDB.findPartialRowMultiple(new String[]{Integer.toString(accountNum)}, new int[]{1});
//
//        }
        ArrayList<String[]> transData = transactionDB.readAllRows();

        for (int i = 0; i < transData.size(); i++) {
            for (int acctNum : accountNums) {
                if ((int) Integer.parseInt(transData.get(i)[1]) == acctNum) {
                    rows.add(i);
                }
            }
        }

        int[] results = new int[rows.size()];
        for (int i = 0; i < rows.size(); i++) {
            results[i] = rows.get(i);
        }

        return results;
    }

    public int[] getTransactionRowsByAccount (Account account) {
        return this.transactionDB.findPartialRowMultiple(new String[]{Integer.toString(account.getAcctNum())}, new int[]{1});
    }

    // get string array representation of one transaction
    public String[] getTransactionInfoByRow (int rowNum) {
        return this.transactionDB.readRow(rowNum);
    }

    public ArrayList<Transaction> getTransactionsForUser(User user) {
        return getTransactionsForRows(getTransactionRowsByUser(user));
    }

    public ArrayList<Transaction> getTransactionsForAccount(Account account) {
        return getTransactionsForRows(getTransactionRowsByAccount(account));
    }

    public ArrayList<Transaction> getTransactionsForRows(int[] rows) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        String[] info = new String[5];
        for (int row : rows) {
            info = getTransactionInfoByRow(row);
            try {
                transactions.add(new Transaction(
                        Double.parseDouble(info[2]),
                        new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(info[3]),
                        Integer.parseInt(info[1]),
                        info[4],
                        info[0].equals("credit")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return transactions;
    }



    public void savePendingTransactionsToDB(ArrayList<Transaction> pendingTransactions) {
        for (Transaction transaction : pendingTransactions) {
            this.transactionDB.addRow(transaction.toStringArray());
        }
    }

    public void saveTransactionToDB(Transaction transaction) {
        this.transactionDB.addRow(transaction.toStringArray());
    }

}
