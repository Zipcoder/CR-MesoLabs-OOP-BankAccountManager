package ibikunle.tolani;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by tolaniibikunle on 1/17/17.
 */
public class BankAccountTESTS {
    @Test
    public static void setBankAccountNumber(){
        double actual = BankAccount.setBankAccountNumber();
        double expected = 1;
        assertEquals("Expected 1",actual,expected);
    }
}
