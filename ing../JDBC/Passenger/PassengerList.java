import java.sql.*;
import java.util.*;

public class PassengerList {
    List<Passenger> passengerlist = new ArrayList<>();

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

    public List<Passenger> getData() {
        loadDriver(driverName);

        Connection myConnection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            myConnection = DriverManager.getConnection(databaseURL, "root", "1234");
            String sqlQuery = "SELECT PassengerNo, PassengerName, Grade, Age FROM Passenger";
            statement = myConnection.prepareStatement(sqlQuery);
            // ((PreparedStatement) statement).setInt(1, 2);

            result = statement.executeQuery(sqlQuery);

            while(result.next()) {
                this.passengerlist.add(new Passenger(
                    result.getInt(1),
                    result.getString(2),
                    result.getInt(3),
                    result.getInt(4)
                ));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                myConnection.close();
                statement.close();
                result.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return passengerlist;
    }
}
