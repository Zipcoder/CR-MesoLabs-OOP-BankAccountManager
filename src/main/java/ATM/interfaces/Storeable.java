package ATM.interfaces;

import ATM.accounts.Account;
import ATM.accounts.Investment;
import ATM.accounts.Savings;

public interface Storeable {

    default String[] toStringArray() {
        if (this instanceof Account) {

            String acctType;
            String typeSpecificProperty;
            if (this instanceof Investment) {
                acctType = "accounts.Investment";
                typeSpecificProperty = ((Investment) this).getRisk().toString();
            } else if (this instanceof Savings) {
                acctType = "accounts.Savings";
                typeSpecificProperty = ((Savings) this).getInterestRate().toString();
            } else {
                acctType = "accounts.Checking";
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
