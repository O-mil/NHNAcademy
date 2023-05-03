import java.sql.*;

public class FirstConnection {
    // private static final String driverName = "com.mysql.cj.jdbc.Driver";
    // private static final String databaseURL = "jdbc:mysql://localhost/module06";
    private static final String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String databaseURL = "jdbc:sqlserver://localhost;database=modul06;integrateSecurity=trye";

    private static final String userName = "sa";
    private static final String password = "kogpsd";

    Connection connection = null;
    Statement statement = null;
    ResultSet result = null;

    public void connect() {

        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(databaseURL, userName, password);
            statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM Module06";
            result = statement.executeQuery(sqlQuery);

            while (result.next()) {
                System.out.print(result.getInt(1) + " ");
                System.out.print(result.getString(2) + " ");
                System.out.print(result.getInt(3) + " ");
                System.out.println(result.getInt(4));
            }
            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FirstConnection connection = new FirstConnection();
        connection.connect();
    }
}