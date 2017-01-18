package reynoldstitko.gillian;

/**
 * Created by gillianreynolds-titko on 1/17/17.
 */
public class BankManager {

    //private enum AccountType { CHECKING, SAVINGS, INVESTMENT}
    private AccountType accountType;
    public static Double getAccountNumber() {

        return accountNumber;
    }

    //BankManager(){};

    private static Double accountNumber = 12345678d;
    private CustomerAccount account = new CustomerAccount();

    //Create a constructor; include account number, account type and account name
    public BankManager(AccountType accountType, String customerName){
        accountNumber = accountNumber + 1; //Set the account number
        this.accountType = accountType;
        account.setAccountHolderName(customerName);
    }

    public void openAccount(AccountType accountType, String customerName){
        BankManager account = new BankManager(accountType, customerName);
    }

    public void closeAccount(Double accountNumber){
        //Account balance must be zero before it can be closed
        if(account.getAccountBalance() == 0.0){
            accountNumber = 0.0;
        }
    }

}
