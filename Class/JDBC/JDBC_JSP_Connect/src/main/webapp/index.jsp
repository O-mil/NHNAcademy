<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Passenger List</title>
    </head>
    <body>
        <h1>Passenger List</h1>
    <table border="1">
        <thead>
            <tr>
                <th>PassengerNo</th>
                <th>PassengerName</th>
                <th>Grade</th>
                <th>Age</th>
            </tr>
        </thead>
        <tbody>
<%
    final String driverName = "com.mysql.cj.jdbc.Driver";
    final String databaseURL = "jdbc:mysql://localhost/module06";

    final String userName = "root";
    final String password = "1234";

    Connection connection = null;
    Statement statement = null;
    ResultSet result = null;

        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(databaseURL, userName, password);
            statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM Passenger";
            result = statement.executeQuery(sqlQuery);

            while (result.next()) { %>

                <tr>
                    <td><%=result.getInt(1)%></td>
                    <td><%=result.getString(2)%></td>
                    <td><%=result.getInt(3)%></td>
                    <td><%=result.getInt(4)%></td>
                </tr>
<%
            }
            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
%>
        </tbody>
    </table>
    </body>
</html>