package wolfe.michael.AccessControlLab.wolfe.michael.AccessControlLabTest;

/**
 * Created by michaelwolfe on 1/17/17.
 */
public class InvestmentAccount extends BankAccount {
    public InvestmentAccount(String actNumber, AccountTypes type, String actName, AccountStatus state){
        super(actNumber, AccountTypes.INVESTMENT, actName,  AccountStatus.OPEN, OverdraftProtection.NO);

    }
}
