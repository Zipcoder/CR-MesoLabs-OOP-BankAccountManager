public interface Storeable {

    default String[] toStringArray() {
        if (this instanceof Account) {

            String acctType;
            String typeSpecificProperty;
            if (this instanceof Investment) {
                acctType = "Investment";
                typeSpecificProperty = ((Investment) this).risk.toString();
            } else if (this instanceof Savings) {
                acctType = "Savings";
                typeSpecificProperty = ((Savings) this).interestRate.toString();
            } else {
                acctType = "Checking";
                typeSpecificProperty = "";
            }

            String[] result = new String[] {
                    ((Account) this).acctNum.toString(),
                    ((Account) this).ownerID.toString(),
                    ((Account) this).balance.toString(),
                    acctType,
                    typeSpecificProperty
            };

            return result;

        } else {
            return null;
        }
    }

}
