package wolfe.michael.AccessControlLab.wolfe.michael.AccessControlLabTest;

/**
 * Created by michaelwolfe on 1/17/17.
 */
public class BankAccount {

    public AccountTypes type;
    public OverdraftProtection odp;
    public AccountStatus status;
    private String accountNumber; //  CANT BE CHANGED
    private double accountBalance;
    private String accountHolderName;
    private double interestRate;
    //transaction log array list updated with a string builder

    public BankAccount(){

    }

    public BankAccount(String actNumber, AccountTypes actType, String actHolderName, AccountStatus state, OverdraftProtection prot){
        accountNumber = actNumber;
        type = actType;
        accountHolderName = actHolderName;
        status = state;
        odp = prot;
        //prompt for name and account type
        //auto generate account number
    }

    public void setAccountBalance(double d){
        this.accountBalance = d;
    }

    public double getAccountBalance(){
        return this.accountBalance;
    }

    public void setAccountHolderName(String s){
        this.accountHolderName = s;
    }

    public String getAccountHolderName(){
        return this.accountHolderName;
    }

    public void setAccountStatus(AccountStatus state){
        status = state;
    }

    public AccountStatus getAccountStatus(){
        return this.status;
    }

    public void setOverdraftProtection(OverdraftProtection type){
        odp = type;
    }

    public OverdraftProtection getOverdraftProtection(){
        return this.odp;
    }

    public void setInterestRate(double d){
        this.interestRate = d;
    }

    public double getInterestRate(){
        return this.interestRate;
    }

    public String balanceInquiry(String accountNumber){

        //if account is frozen -- NO
        return null;
    }

    public void creditAccount(double amount, String accountNumber){
        //if account is frozen -- NO
    }

    public void debitAccount(double ammount, String accountNumber){
        //if account is frozen -- NO

    }

    public void toggleFreezeOnAccount(String accountNumber){

    }

    public void transferFundsFromAccountToAccount(double amount, String accountOneNumber, String accountTwoNumber){
        //if account is frozen -- NO

    }

    public void openAccount(){
        //call constructor
        //prompt user for name and account type
        //generate account number
    }

    public void closeAccount(){

    }

    public void createNewAccount(){

    }

}
