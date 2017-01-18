package battin.preston.AccessControl;

/**
 * Created by prestonbattin on 1/17/17.
 */
public class Checking extends Account {


    public Checking(String type, String name){
        super(type, name);
        setRate();
        setStatus("Open");
        setOverDraftProtection("On");
    }

    public int getCheckingAccountNumber() {

        return this.getCheckingAccountNumber();
    }

}
