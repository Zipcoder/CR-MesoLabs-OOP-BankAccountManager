import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.junit.Assert.*;
@RunWith(JUnitParamsRunner.class)
public class ConsoleTest {

    @Test
    public void print() {
    }

    @Test
    public void println() {
    }

    @Test
    public void menuOptionsTest() {
        String[] options = new String[] {"Live", "Die", "Repeat"};
        String header = "Account Creation Menu";
        options = new String[] {"Live", "Die", "Repeat", "Bump", "Set", "Spike", "Towel"};
        //Console.getInput(options);
        //Console.getInput(header, options);
    }

    @Test
    public void getInput() {
    }

    @Test
    public void getCurrency() {
    }

    @Test
    @Parameters({"1,true","2.3,false","-3,false","0,true","203,true","sad1,false","1223.231,false","3.33,true","2.3412,false"})
    public void currencyCheckTest(String input, Boolean valid) {
        Assert.assertTrue(valid == Console.currencyCheck(input));
    }

    @Test
    @Parameters({"1,true","2.3,false","-3,false","0,true","203,true","sad1,false","1223.231,false","3.33,false","2.3412,false"})
    public void integerCheckTest(String input, Boolean valid) {
        Assert.assertTrue(valid == Console.integerCheck(input));
    }

}
