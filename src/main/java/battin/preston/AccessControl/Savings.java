package battin.preston.AccessControl;

/**
 * Created by prestonbattin on 1/17/17.
 */
public class Savings extends Account {




    public Savings(String type, String name){
        super(type, name);
        setRate();
        setStatus("Open");
        setOverDraftProtection("On");
    }



    protected int getSavingAccountNumber() {

        return this.getSavingAccountNumber();
    }

}



