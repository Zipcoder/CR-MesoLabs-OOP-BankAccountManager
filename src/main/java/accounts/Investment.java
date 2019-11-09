package accounts;

import interfaces.Storeable;

public class Investment extends Account{

    private Double risk;

    public Investment(Double balance, Integer ownerID, Integer acctNum, Double risk) {
        super(balance, ownerID, acctNum);
        this.risk = risk;

    }
    public Double getRisk() {
        return risk;
    }

    public void setRisk(Double risk) {
        this.risk = risk;
    }



}
