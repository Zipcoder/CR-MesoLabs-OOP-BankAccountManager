package battin.preston.AccessControl;

/**
 * Created by prestonbattin on 1/17/17.
 */
public class Main {

    public static void main(String[] args) {

Checking test = new Checking("Checking", "foo");

        test.creditAccount(4);

        Savings test1 = new Savings("Savings", "bar");
        test1.creditAccount(4545);

        test.creditAccount(6);
    }
}
