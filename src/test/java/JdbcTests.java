import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;

class JdbcTests {

    @Test
    void selectTest() {
        String connectionString = "jdbc:sqlserver://vault.local:1433;databaseName=Northwind;user=sa;password=Banana!2";
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            Assertions.assertTrue(connection.isValid(10));
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT TOP 10 * FROM Products");
            ResultSet resultSet = preparedStatement.executeQuery();
            int counter = 0;
            while (resultSet.next()) {
                String row = resultSet.getString("ProductID") + ":" + resultSet.getString("ProductName");
                System.out.println(row);
                counter++;
            }
            Assertions.assertEquals(10, counter);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Assertions.fail("Failed to connect to database");
        }
    }

    @Test
    void dataSourceTest() {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setUser("sa");
        dataSource.setPassword("Banana!2");
        dataSource.setDatabaseName("Northwind");
        dataSource.setServerName("vault.local");
        dataSource.setPortNumber(1433);

        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) as cnt FROM Products");
            final ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            final int count = resultSet.getInt("cnt");
            System.out.println("Count is " + count);
            Assertions.assertEquals(77, count);
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
