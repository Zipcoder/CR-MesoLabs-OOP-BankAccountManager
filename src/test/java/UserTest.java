import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void genCardNumTest() {

        System.out.println("Testing Card Number Gen");

        for (int i = 0; i < 10; i++) {
            Integer cardNum = User.genCardNum();

            System.out.println(cardNum);
            Assert.assertEquals(8,  cardNum.toString().length());
        }
    }
}