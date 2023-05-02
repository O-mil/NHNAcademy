import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FirstConnection {
    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String databaseURL = "jdbc:mysql://localhost/module06";
    private static final String userName = "root";
    private static final String password = "1234";

    Connection connection = null;
    Statement statement = null;
    ResultSet result = null;

    public void connect() {

        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(databaseURL, userName, password);
            statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM Passenger";
            result = statement.executeQuery(sqlQuery);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
