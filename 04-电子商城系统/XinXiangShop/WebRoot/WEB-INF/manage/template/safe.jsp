<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%if (session.getAttribute("manager")==null){
	out.println("<script language='javascript'>alert('µÇÂ¼Ê§°Ü!');window.location.href='loginM.lzw';</script>");
}%>
