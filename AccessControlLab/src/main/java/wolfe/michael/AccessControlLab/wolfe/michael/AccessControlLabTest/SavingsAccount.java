package wolfe.michael.AccessControlLab.wolfe.michael.AccessControlLabTest;

/**
 * Created by michaelwolfe on 1/17/17.
 */
public class SavingsAccount extends BankAccount{
    public SavingsAccount(String actNumber, String actName, AccountStatus state, OverdraftProtection prot){
        super(actNumber, AccountTypes.SAVINGS, actName, state, prot);

    }

}
