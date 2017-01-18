package wolfe.michael.AccessControlLab.wolfe.michael.AccessControlLabTest;

/**
 * Created by michaelwolfe on 1/17/17.
 */
public class SavingsAccount extends BankAccount{
    public SavingsAccount(String actNumber, AccountTypes type, String actName, AccountStatus state, OverdraftProtection opd){
        super(actNumber, AccountTypes.SAVINGS, actName, AccountStatus.OPEN, OverdraftProtection.NO);

    }

}
