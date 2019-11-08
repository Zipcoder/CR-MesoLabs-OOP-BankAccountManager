public class Savings extends Account{

    public Double interestRate;

    public Savings(Double balance, Integer ownerID, Integer acctNum, Double interestRate) {
        super(balance, ownerID, acctNum);
        this.interestRate = interestRate;
    }

    public Double getInterestRate() {
        return this.interestRate;
    }


}
