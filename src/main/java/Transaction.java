import java.util.Date;

public class Transaction implements Storeable {

    private Double amount;
    private Date timeStamp;
    private Integer accountID;
    private String description;
    private Boolean isCredit;
    // 0: credit/debit 1: accountID 2: amount (signed) 3: timeStamp 4: description
    public Transaction(Double amount, Date timeStamp, Integer accountID, String description, Boolean isCredit) {
        this.amount = amount;
        this.timeStamp = timeStamp;
        this.accountID = accountID;
        this.description = description;
        this.isCredit = isCredit;
    }

    public String print() {
        return amount + " " + description + timeStamp;
    }


    @Override
    public String[] toStringArray() {
        String type;
        if (isCredit) {
            type = "credit";
        } else {
            type = "debit";
        }

        String[] result = new String[] {
                type,
                this.accountID.toString(),
                String.format("%.2f",this.amount),
                this.timeStamp.toString(),
                this.description
        };
        return result;
    }
}
