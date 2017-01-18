package wolfe.michael.AccessControlLab.wolfe.michael.AccessControlLabTest;

/**
 * Created by michaelwolfe on 1/17/17.
 */
public class InvestmentAccount extends BankAccount {
    public InvestmentAccount(String actNumber, String actName, AccountStatus state, OverdraftProtection prot){
        super(actNumber, AccountTypes.INVESTMENT, actName,  state, prot);
    }
}
