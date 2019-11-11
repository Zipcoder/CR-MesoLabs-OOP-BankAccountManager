package ATM.accounts;

public class Checking extends Account {

    private Overdraft overdraft;

    public enum Overdraft {
        ON,
        OFF,
        AUTO
    }

    public Checking(Double balance, Integer ownerID, Integer acctNum, Status acctStatus, Overdraft overdraft){
        super(balance, ownerID, acctNum, acctStatus);
        this.overdraft = overdraft;
    }

    public Overdraft getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(Overdraft overdraft) {
        this.overdraft = overdraft;
    }
}
