package parameters;

import org.testng.annotations.Test;
import utilities.SqlConnector;

import java.sql.ResultSet;

public class TestSqlParameters {
    @Test
    public void testDatabaseValues() throws Exception {
        ResultSet resultSet = SqlConnector.readDate("select * from monthly_mortgage");
        while (resultSet.next()) {
            System.out.println("The home price is: " + resultSet.getString("homevalue"));
            System.out.println("The down payment is: " + resultSet.getString("downpayment"));
        }
    }
}
