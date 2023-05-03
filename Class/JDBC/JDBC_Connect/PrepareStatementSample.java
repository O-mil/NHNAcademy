import java.sql.*;

public class PrepareStatementSample {

    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String databaseURL = "jdbc:mysql://localhost/module06";

    public static void loadDriver(String driverName) {
        try {
            Class.forName(driverName);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        loadDriver(driverName);

        Connection myConnection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            myConnection = DriverManager.getConnection(databaseURL, "root", "1234");
            String sqlQuery = "SELECT PassengerName, Grade, Age FROM Passenger";
            statement = myConnection.prepareStatement(sqlQuery);
            ((PreparedStatement) statement).setInt(1, 2);

            result = statement.executeQuery(sqlQuery);

            for (; result.next();) {
                System.out.println(result.getString(1) + " ");
                System.out.println(result.getString(2) + " ");
                System.out.println(result.getString(3));

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
