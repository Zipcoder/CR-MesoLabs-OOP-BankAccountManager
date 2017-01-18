package wolfe.michael.AccessControlLab.wolfe.michael.AccessControlLabTest;

/**
 * Created by michaelwolfe on 1/17/17.
 */
public class CheckingAccount extends BankAccount {
    public CheckingAccount(String actNumber, AccountTypes type, String actName, AccountStatus state){
        super(actNumber, AccountTypes.CHECKING, actName,  AccountStatus.OPEN, OverdraftProtection.NO);

    }
}
