package ATM.services;

import ATM.ATM;
import ATM.DB;
import ATM.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class UserServicesTest {

    private ATM atm;
    private UserServices userServices;

    @Before
    public void setUp() throws Exception {
        atm = new ATM("testuserDB.csv", "testaccountDB.csv", "testtransactionDB.csv");
        userServices = atm.getUserServices();
    }

    @Test
    public void getMaxUserNumber() {


            userServices.clearUserDB();

            int actual = userServices.getMaxUserNumber();
            int expected = 0;

            Assert.assertEquals(actual,expected);

            User user1 = new User("Jim","Brown","goolybib", 12, 12343);
            userServices.saveUserToDB(user1);
            User user2 = new User("Ji123m","Bro23wn","gool321ybib", 122, 1234313);
            userServices.saveUserToDB(user2);
            User user3 = new User("Jane","Himne","gasdsdool321ybib", 32, 313);
            userServices.saveUserToDB(user3);

            actual = userServices.getMaxUserNumber();
            expected = 122;

            Assert.assertEquals(actual,expected);

            User user4 = new User("Jane","Himne","gasdsdool321ybib", 29, 313);
            userServices.saveUserToDB(user4);

            actual = userServices.getMaxUserNumber();
            expected = 122;

            Assert.assertEquals(actual,expected);

            User user5 = new User("Jane","Himne","gasdsdool321ybib", 199, 313);
            userServices.saveUserToDB(user5);

            actual = userServices.getMaxUserNumber();
            expected = 199;

            Assert.assertEquals(actual,expected);
        }



    @Test
    public void getUserRowByID() {

            userServices.clearUserDB();

            User user1 = new User("Jim","Brown","goolybib", 12, 12343);
            userServices.saveUserToDB(user1);
            User user2 = new User("Ji123m","Bro23wn","gool321ybib", 122, 1234313);
            userServices.saveUserToDB(user2);
            User user3 = new User("Jane","Himne","gasdsdool321ybib", 32, 313);
            userServices.saveUserToDB(user3);

            int actual = userServices.getUserRowByID(122);
            int expected = 1;

            Assert.assertEquals(actual,expected);

            actual = userServices.getUserRowByID(12);
            expected = 0;

            Assert.assertEquals(actual,expected);

            actual = userServices.getUserRowByID(32);
            expected = 2;

            Assert.assertEquals(actual,expected);

            actual = userServices.getUserRowByID(323232);
            expected = -1;

            Assert.assertEquals(actual,expected);
        }



    @Test
    public void getUserInfoByID() {

            userServices.clearUserDB();

            User user1 = new User("Jim","Brown","goolybib", 12, 12343);
            userServices.saveUserToDB(user1);
            User user2 = new User("Ji123m","Bro23wn","gool321ybib", 122, 1234313);
            userServices.saveUserToDB(user2);
            User user3 = new User("Jane","Himne","gasdsdool321ybib", 32, 313);
            userServices.saveUserToDB(user3);

            String[] actual = userServices.getUserInfoByID(122);
            String[] expected = user2.toStringArray();

            Assert.assertEquals(actual,expected);
        }


    @Test
    public void getUserInfoByCardNum() {

            userServices.clearUserDB();
            User user1 = new User("Jim","Brown","goolybib", 12, 12343);
            userServices.saveUserToDB(user1);
            User user2 = new User("Ji123m","Bro23wn","gool321ybib", 122, 1234313);
            userServices.saveUserToDB(user2);
            User user3 = new User("Jane","Himne","gasdsdool321ybib", 32, 313);
            userServices.saveUserToDB(user3);

            String[] actual = userServices.getUserInfoByCardNum(1234313);
            String[] expected = user2.toStringArray();

            Assert.assertEquals(actual,expected);

            actual = userServices.getUserInfoByCardNum(313);
            expected = user3.toStringArray();

            Assert.assertEquals(actual,expected);
        }



    @Test
    public void saveUserToDB() {

            userServices.clearUserDB();

            User user1 = new User("Jim","Brown","goolybib", 12, 12343);
            userServices.saveUserToDB(user1);
            User user2 = new User("Ji123m","Bro23wn","gool321ybib", 122, 1234313);
            userServices.saveUserToDB(user2);
            User user3 = new User("Jane","Himne","gasdsdool321ybib", 32, 313);
            userServices.saveUserToDB(user3);

            String[] actual = userServices.getUserInfoByID(122);
            String[] expected = user2.toStringArray();

            Assert.assertEquals(actual,expected);

            actual = userServices.getUserInfoByID(12);
            expected = user1.toStringArray();

            Assert.assertEquals(actual,expected);

            int actual2 = userServices.getUserDBLength();
            int expected2 = 3;

            Assert.assertEquals(actual,expected);

            User user4 = new User("Ji123m","Bro23wn","gool321ysdasdbib", 12, 1234313);
            userServices.saveUserToDB(user4);

            actual2 =userServices.getUserDBLength();
            expected2 = 3;

            Assert.assertEquals(actual,expected);

            actual = userServices.getUserInfoByID(12);
            expected = user4.toStringArray();

            Assert.assertEquals(actual,expected);

            expected = user1.toStringArray();

            Assert.assertNotEquals(actual,expected);
        }

    }

