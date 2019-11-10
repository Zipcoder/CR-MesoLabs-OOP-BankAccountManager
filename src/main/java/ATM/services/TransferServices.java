package ATM.services;


import ATM.Exceptions.ClosedAccountException;
import ATM.Exceptions.FrozenAccountException;
import ATM.Exceptions.InsufficientFundsException;
import ATM.User;
import ATM.Transaction;
import ATM.ATM;
import ATM.accounts.Account;

import java.util.ArrayList;
import java.util.Date;

public class TransferServices {


//    private ATM.DB userDB;          // 0: ID 1: Last Name 2: First Name 3: cardNum 4: PW
//    private ATM.DB transactionDB;   // 0: credit/debit 1: accountID 2: amount (signed) 3: timeStamp 4: description
//    private ATM.DB accountDB;       // 0: accountID 1: ownerID 2: balance 3: type 4: risk/interest/null (type-dependent)

    //Instance Fields
    Account account;
    private User currentUser;
    private String acctStatus;
    private ATM atm;
    private Integer acctTransferTo;
    private AccountServices accountServices;
    private TransactionServices transactionServices;

    //Constructor
    public TransferServices(ATM atm, Account account) {
        this.atm = atm;
        this.currentUser = this.atm.getCurrentUser();
        this.account = account;
        this.transactionServices = this.atm.getTransactionServices();
        this.accountServices = this.atm.getAccountServices();
    }


    public boolean executeTransfer(Account sourceAccount, Account targetAccount, double amountToDeposit) throws InsufficientFundsException, FrozenAccountException, ClosedAccountException {

        checkTransferExceptions(sourceAccount, targetAccount, amountToDeposit);

        targetAccount.balance = targetAccount.balance + amountToDeposit;
        sourceAccount.balance = sourceAccount.balance - amountToDeposit;
        accountServices.saveAccountToDB(targetAccount);
        accountServices.saveAccountToDB(sourceAccount);

        transactionServices.saveTransactionToDB(new Transaction(amountToDeposit * -1, new Date(), sourceAccount.getAcctNum(),String.format("Transfer to account %d", targetAccount.getAcctNum()), false ));
        transactionServices.saveTransactionToDB(new Transaction(amountToDeposit, new Date(), targetAccount.getAcctNum(),String.format("Transfer from account %d", sourceAccount.getAcctNum()), true ));

        return true;
    }

    public void checkTransferExceptions(Account sourceAccount, Account targetAccount, double amountToDeposit) throws InsufficientFundsException, ClosedAccountException, FrozenAccountException {
        if (amountToDeposit > sourceAccount.balance) {
            throw new InsufficientFundsException();
        }
        else if (targetAccount.getAcctStatus() == Account.Status.OFAC) {
            throw new FrozenAccountException();
        }
        else if (targetAccount.getAcctStatus() == Account.Status.CLOSED) {
            throw new ClosedAccountException();
        }
    }
}











