package demoTest;

import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by LuoYJ on 2019/1/11.
 */
public class MyTest {

    @Test
    public void test01(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/springboot?useSSL=false", "root", "root");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02(){
        BigDecimal d1 = new BigDecimal(146);
        BigDecimal d2 = new BigDecimal(3);
        BigDecimal divide = d1.divide(d2, 1, BigDecimal.ROUND_HALF_UP);
        System.out.println(divide.doubleValue());
    }
}
