package ATM.services;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import sun.jvmstat.perfdata.monitor.PerfStringVariableMonitor;

import static org.junit.Assert.*;

public class UserServicesTest {

    private ATM atm;

    @Before
    public void setUp() throws Exception {
        atm = new ATM("testuserDB.csv", "testaccountDB.csv", "testtransactionDB.csv");
    }

    @Test
    public void getMaxUserNumber() {

            DB userDB = atm.getUserDB();
            userDB.clear();

            int actual = atm.getMaxUserNumber();
            int expected = 0;

            Assert.assertEquals(actual,expected);

            User user1 = new User("Jim","Brown","goolybib", 12, 12343);
            atm.saveUserToDB(user1);
            User user2 = new User("Ji123m","Bro23wn","gool321ybib", 122, 1234313);
            atm.saveUserToDB(user2);
            User user3 = new User("Jane","Himne","gasdsdool321ybib", 32, 313);
            atm.saveUserToDB(user3);

            actual = atm.getMaxUserNumber();
            expected = 122;

            Assert.assertEquals(actual,expected);

            User user4 = new User("Jane","Himne","gasdsdool321ybib", 29, 313);
            atm.saveUserToDB(user4);

            actual = atm.getMaxUserNumber();
            expected = 122;

            Assert.assertEquals(actual,expected);

            User user5 = new User("Jane","Himne","gasdsdool321ybib", 199, 313);
            atm.saveUserToDB(user5);

            actual = atm.getMaxUserNumber();
            expected = 199;

            Assert.assertEquals(actual,expected);
        }



    @Test
    public void getUserRowByID() {

            DB userDB = atm.getUserDB();
            userDB.clear();

            User user1 = new User("Jim","Brown","goolybib", 12, 12343);
            userDB.addRow(user1.toStringArray());
            User user2 = new User("Ji123m","Bro23wn","gool321ybib", 122, 1234313);
            userDB.addRow(user2.toStringArray());
            User user3 = new User("Jane","Himne","gasdsdool321ybib", 32, 313);
            userDB.addRow(user3.toStringArray());

            int actual = atm.getUserRowByID(122);
            int expected = 1;

            Assert.assertEquals(actual,expected);

            actual = atm.getUserRowByID(12);
            expected = 0;

            Assert.assertEquals(actual,expected);

            actual = atm.getUserRowByID(32);
            expected = 2;

            Assert.assertEquals(actual,expected);

            actual = atm.getUserRowByID(323232);
            expected = -1;

            Assert.assertEquals(actual,expected);
        }



    @Test
    public void getUserInfoByID() {

            DB userDB = atm.getUserDB();
            userDB.clear();

            User user1 = new User("Jim","Brown","goolybib", 12, 12343);
            userDB.addRow(user1.toStringArray());
            User user2 = new User("Ji123m","Bro23wn","gool321ybib", 122, 1234313);
            userDB.addRow(user2.toStringArray());
            User user3 = new User("Jane","Himne","gasdsdool321ybib", 32, 313);
            userDB.addRow(user3.toStringArray());

            String[] actual = atm.getUserInfoByID(122);
            String[] expected = user2.toStringArray();

            Assert.assertEquals(actual,expected);
        }


    @Test
    public void getUserInfoByCardNum() {

            DB userDB = atm.getUserDB();
            userDB.clear();

            User user1 = new User("Jim","Brown","goolybib", 12, 12343);
            userDB.addRow(user1.toStringArray());
            User user2 = new User("Ji123m","Bro23wn","gool321ybib", 122, 1234313);
            userDB.addRow(user2.toStringArray());
            User user3 = new User("Jane","Himne","gasdsdool321ybib", 32, 313);
            userDB.addRow(user3.toStringArray());

            String[] actual = atm.getUserInfoByCardNum(1234313);
            String[] expected = user2.toStringArray();

            Assert.assertEquals(actual,expected);

            actual = atm.getUserInfoByCardNum(313);
            expected = user3.toStringArray();

            Assert.assertEquals(actual,expected);
        }



    @Test
    public void saveUserToDB() {

            DB userDB = atm.getUserDB();
            userDB.clear();

            User user1 = new User("Jim","Brown","goolybib", 12, 12343);
            atm.saveUserToDB(user1);
            User user2 = new User("Ji123m","Bro23wn","gool321ybib", 122, 1234313);
            atm.saveUserToDB(user2);
            User user3 = new User("Jane","Himne","gasdsdool321ybib", 32, 313);
            atm.saveUserToDB(user3);

            String[] actual = atm.getUserInfoByID(122);
            String[] expected = user2.toStringArray();

            Assert.assertEquals(actual,expected);

            actual = atm.getUserInfoByID(12);
            expected = user1.toStringArray();

            Assert.assertEquals(actual,expected);

            int actual2 = userDB.length();
            int expected2 = 3;

            Assert.assertEquals(actual,expected);

            User user4 = new User("Ji123m","Bro23wn","gool321ysdasdbib", 12, 1234313);
            atm.saveUserToDB(user4);

            actual2 = userDB.length();
            expected2 = 3;

            Assert.assertEquals(actual,expected);

            actual = atm.getUserInfoByID(12);
            expected = user4.toStringArray();

            Assert.assertEquals(actual,expected);

            expected = user1.toStringArray();

            Assert.assertNotEquals(actual,expected);
        }

    }

