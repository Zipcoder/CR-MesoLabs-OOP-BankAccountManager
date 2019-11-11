package ATM;

import ATM.menus.MainMenu;
import ATM.menus.UserMenu;
import ATM.services.AccountServices;
import ATM.services.TransactionServices;
import ATM.services.UserServices;

import java.io.IOException;

public class ATM {

    private User currentUser;

    private DB userDB;          // 0: ID 1: Last Name 2: First Name 3: cardNum 4: PW
    private DB transactionDB;   // 0: credit/debit 1: accountID 2: amount (signed) 3: timeStamp 4: description
    private DB accountDB;       // 0: accountID 1: ownerID 2: balance 3: type 4: risk/interest/null (type-dependent)

    private UserServices userServices;
    private TransactionServices transactionServices;
    private AccountServices accountServices;


    public ATM(String userDBName, String accountDBName, String transactionDBName) {
        this.currentUser = null;
        try {
            this.userDB = new DB(userDBName, 5);
            this.transactionDB = new DB(transactionDBName, 5);
            this.accountDB = new DB(accountDBName, 6);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.userServices = new UserServices(userDB, this);
        this.transactionServices = new TransactionServices(transactionDB, this);
        this.accountServices = new AccountServices(accountDB, this);
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public DB getUserDB() {
        return this.userDB;
    }

    public DB getTransactionDB() {
        return this.transactionDB;
    }

    public DB getAccountDB() {
        return this.accountDB;
    }

    public UserServices getUserServices() {
        return userServices;
    }

    public TransactionServices getTransactionServices() {
        return transactionServices;
    }

    public AccountServices getAccountServices() {
        return accountServices;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    // no test - requires input
    public void serviceLoop() {
        this.transactionServices.linkServices();
        this.accountServices.linkServices();
        //this.userServices.linkServices();

        new UserMenu(this).displayMenu();

        new MainMenu(this).displayMenu();

        logOut();

        serviceLoop();
    }

    public void logOut() {
        this.currentUser = null;
    }
}