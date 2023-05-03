<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.nhnacademy.dbutils.DbUtils" %>
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
        DataSource dataSource = DbUtils.getDataSource();

        Statement statement = null;
        ResultSet result = null;

        try (Connection connection = dataSource.getConnection()){
            statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM Flight";
            result = statement.executeQuery(sqlQuery);

            while (result.next()) { %>

    <tr>
        <td><a href="reservation.jsp?id=<%=result.getInt(1)%>"><%=result.getInt(1)%></a></td>
        <td><%=result.getInt(2)%></td>
        <td><%=result.getString(3)%></td>
        <td><%=result.getString(4)%></td>
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