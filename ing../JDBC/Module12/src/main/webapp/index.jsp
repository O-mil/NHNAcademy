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
                    int pageNo = 1;
                    int pageSize = 20;  //한 페이지에 출력할 행의 개수
                    String pageParam = request.getParameter("pageNo");
                    DataSource dataSource = DbUtils.getDataSource();
                    int startPage = 0;  //limit의 시작값

                    PreparedStatement statement = null;
                    ResultSet result = null;

                    if (pageParam != null && !pageParam.isEmpty()) {
                        pageNo = Integer.parseInt(pageParam);
                    }


                    try (Connection connection = dataSource.getConnection()){

                        // 전체 데이터 개수 조회
                        Statement countPstmt = connection.createStatement();
                        String countSql = "SELECT COUNT(*) FROM Members";
                        ResultSet countRs = countPstmt.executeQuery(countSql);
                        countRs.next();
                        int totalCount = countRs.getInt(1);

                        // 페이지 개수 계산
                        int pageCount = (int) Math.ceil((double) totalCount / pageSize);

                        // ----------
                        String SQL = "SELECT * FROM Members limit ?, ?";
                        int startRow = (pageNo - 1) * pageSize;
                        statement = connection.prepareStatement(SQL);
                        statement.setInt(1, startRow);
                        statement.setInt(2, pageSize);
                        result = statement.executeQuery();

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