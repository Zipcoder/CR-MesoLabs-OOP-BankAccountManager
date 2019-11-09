package ATM.accounts;

public class Investment extends Account{

    private Double risk;

    public Investment(Double balance, Integer ownerID, Integer acctNum, Double risk, Status acctStatus) {
        super(balance, ownerID, acctNum, acctStatus);
        this.risk = risk;

    }
    public Double getRisk() {
        return risk;
    }

    public void setRisk(Double risk) {
        this.risk = risk;
    }



}
