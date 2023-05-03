<%--
  Created by IntelliJ IDEA.
  User: kimhwajeong
  Date: 2023/05/03
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.nhnacademy.dbutils.DbUtils" %>
<html>
  <head>
    <title>Flight Info </title>
  </head>
  <body>
    <table border="1">
      <thead>
        <tr>
          <th>FlightNo</th>
          <th>AircraftNo</th>
          <th>Deparetures</th>
          <th>Arrival</th>
          <th>Price</th>
          <th>FlightDate</th>
        </tr>
      </thead>
      <tbody>
        <%
          DataSource dataSource = DbUtils.getDataSource();

          PreparedStatement statement = null;
          ResultSet result = null;

          try (Connection connection = dataSource.getConnection()){
            String sqlQuery = "SELECT * FROM Flight WHERE FlightNo = ?";
            statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, request.getParameter("id"));
            result = statement.executeQuery();

            while (result.next()) { %>

        <tr>
          <td><%=result.getInt(1)%></td>
          <td><%=result.getInt(2)%></td>
          <td><%=result.getString(3)%></td>
          <td><%=result.getString(4)%></td>
          <td><%=result.getInt(5)%></td>
          <td><%=String.valueOf(result.getDate(6)) + String.valueOf(result.getTime(6))%></td>
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