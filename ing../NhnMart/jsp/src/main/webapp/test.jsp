<%--
  Created by IntelliJ IDEA.
  User: kimhwajeong
  Date: 2023/04/04
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<hr>
  <%
      for (int i = 1; i < 10; i++) {
  %>
            <h1><%=i%>단</h1>
  <%
          for (int j = 1; j < 10; j++) {
  %>
        <p>
            <%=i%> x <%=j%> = <%=i*j%>
        </p>
  <%
          }
          System.out.println();
      }
  %>
<hr/>
</body>
</html>
