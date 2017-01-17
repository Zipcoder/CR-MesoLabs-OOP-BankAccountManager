package reynoldstitko.gillian;

/**
 * Created by gillianreynolds-titko on 1/17/17.
 */
public class BankManager {

    private enum AccountType { CHECKING, SAVINGS, INVESTMENT}
    private AccountType accountType;
    private static long accountNumber = 12345678L;
    private CustomerAccount account = new CustomerAccount();

    //Create a constructor; include account number, account type and account name
    BankManager(AccountType accountType, String customerName){
        accountNumber = accountNumber + 1; //Set the account number
        this.accountType = accountType;
        account.setAccountHolderName(customerName);
    }

    public void openAccount(){

    }

    public void closeAccount(){
        //Account balance must be zero before it can be closed
    }

}
