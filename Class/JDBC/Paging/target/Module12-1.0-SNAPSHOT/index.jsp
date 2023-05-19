<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.nhnacademy.module12.DbUtils" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Module12 List</title>
    </head>
    <body>
        <h1>Module12 List</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>City</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int pageNo;
                    int pageSize = 20;  //한 페이지에 출력할 행의 개수
                    String pageParam = request.getParameter("page");
                    DataSource dataSource = DbUtils.getDataSource();
                    int startPage = 0;  //limit의 시작값


                    if (pageParam != null) {
                        pageNo = Integer.parseInt(pageParam);
                    } else {
                        pageNo = 1;
                    }


                    String countSql = "SELECT COUNT(*) FROM Members";

                    try (Connection connection = dataSource.getConnection()){

                        // 전체 데이터 개수 조회
                        Statement countPstmt = connection.createStatement();
                        ResultSet countRs = countPstmt.executeQuery(countSql);
                        countRs.next();
                        int totalCount = countRs.getInt(1);

                        // ----------
                        String SQL = "SELECT * FROM Members limit ?, ?";
                        PreparedStatement statement = connection.prepareStatement(SQL);
                        int startRow = (pageNo - 1) * pageSize;
                        statement.setInt(1, startRow);
                        statement.setInt(2, pageSize);
                        ResultSet result = statement.executeQuery();

                        // 페이지 개수 계산
                        int pageCount = totalCount / pageSize;

                        while (result.next()) { %>

                <tr>
                    <td><%=result.getInt(1)%></td>
                    <td><%=result.getString(2)%></td>
                    <td><%=result.getString(3)%></td>
                </tr>
                <%
                        }
                        result.close();
                        statement.close();
                        connection.close();

                        for (int i = 1; i <= pageCount; i++) { %>
                            <a href="?page=<%=i%>"><%=i%></a>
                       <%}

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                %>
            </tbody>
        </table>
    </body>
</html>