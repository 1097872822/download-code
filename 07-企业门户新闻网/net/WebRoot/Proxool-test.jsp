<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
  <title>Proxool-test.jsp</title>
</head>
<body>

<h2>使用 Proxool Connection Pool</h2>

<%
	Connection con = null;	
	Statement stmt = null;		
	ResultSet rs = null;

		
	try	
	{				
		con = DriverManager.getConnection("proxool.net");		
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);	
		String query = "SELECT * FROM tb_usertable";	
		rs = stmt.executeQuery(query);	
	
		while(rs.next()) {	
			String name = rs.getString(2);	
			String password = rs.getString(3);
%>	
			姓名为：<%= name %><br>
			密码为：<%=password %>
<%			
		}
		
		stmt.close();
		con.close();
	}	
	catch(SQLException sqle)
	{
		out.println("sqle="+sqle);	
	}
	finally
	{
		try {
			if(con != null)
			{
				con.close();
			}		
		}
		catch(SQLException sqle)
		{
			out.println("sqle="+sqle);	
		}
	}	
		
%>

</body>
</html>