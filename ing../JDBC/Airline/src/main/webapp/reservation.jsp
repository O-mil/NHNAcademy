<%--
  Created by IntelliJ IDEA.
  User: kimhwajeong
  Date: 2023/05/03
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.nhnacademy.dbutils.DbUtils" %>
<html>
    <head>
        <title>Reservation</title>
    </head>
    <body>
    <table border="1">
        <thead>
            <tr>
                <th>PassengerNo</th>
                <th>FlightNo</th>
                <th>ReserveDate</th>
            </tr>
        </thead>
        <tbody>
            <%
                DataSource dataSource = DbUtils.getDataSource();

                PreparedStatement statement = null;
                ResultSet result = null;

                try (Connection connection = dataSource.getConnection()){
                    String sqlQuery = "SELECT * FROM Reservation WHERE passengerNo = ?";
                    statement = connection.prepareStatement(sqlQuery);
                    statement.setString(1, request.getParameter("id"));
                    result = statement.executeQuery();

                    while (result.next()) { %>

            <tr>
                <td><a href="reservation.jsp?id=<%=result.getInt(1)%>"><%=result.getInt(1)%></a></td>
                <td><%=result.getInt(2)%></td>
                <td><%=String.valueOf(result.getDate(3))%></td>
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
