
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="../error.jsp"%>
<%@ page import="java.util.*,com.wsy.product"%>
<jsp:useBean id="a" class="com.wsy.StringTrans" scope="page" />
<jsp:useBean id="sql" class="com.wsy.selectsql" scope="page" />
<%@ page import="org.apache.commons.fileupload.*,java.io.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>保存上传文件</title>
		<link href="../CSS/style.css" rel="stylesheet" type="text/css">

	</head>
	<%

DiskFileUpload fu=new DiskFileUpload();//设置允许用户上传文件大小，单位：字节
//fu.setSizeMax(10000000);//设置最多只允许在内存中存储的数据，单位：字节
//fu.setSizeThreshold(4096);
//fu.setRepositoryPath("upload/");
List fileitems=fu.parseRequest(request);//开始读取上传信息
			String name2=null;
%>
	<body leftmargin="0" topmargin="40" marginwidth="0" marginheight="0">
		<div align="center">
			<p class="whitezi">
				文件列表：
			</p>
			<p>
				&nbsp;
			</p>
			<table width="322" height="84" border=1 cellpadding=1 cellspacing=1
				bordercolor="#CCCCCC" bgcolor="#FFFFFF">
				<tr bgcolor="#FFFFFF">
					<td class="lunzi">
						文件名
					</td>
					<td class="lunzi">
						大小
					</td>
				</tr>
				<%
					Iterator iter = fileitems.iterator();
					while (iter.hasNext()) {
						FileItem item = (FileItem) iter.next();
						//忽略其他不是文件域的所有表单信息
						if (!item.isFormField()) {

							String name1 = item.getName();

							long size = item.getSize();
							name1 = name1.replace(':', '-');
							name1 = name1.replace('\\', '-');
							String name[] = name1.split("-");
							//if(name==null||name.equals("")&&size==0)
							//continue;
							name2 = name[name.length - 1];
							request.setCharacterEncoding("UTF-8");
							System.out.println(name2);
							
				%>

				<tr bgcolor="#FFFFFF">
					<td class="lunzi">
						<%=name2%>
					</td>
					<td class="lunzi">
						<%=item.getSize()%>
					</td>
				</tr>
				<%
							File path = new File("D:\\Upload");
							if (!path.isDirectory()) {
						path.mkdir();
							}

							item.write(new File(path + "\\" + name2));
						}

						//取出文本域的内容
						//if(item.isFormField()){
						//String name=item.getFieldName();
						//String value=item.getString();
						//System.out.println(name);
						//System.out.println(value);

						//Map map=new HashMap();
						//map.put(name,value);
						//String[] curParam = (String[]) map.get(item.getFieldName());
						//if (curParam == null) {
						// simple form field
						//map.put(item.getFieldName(), new String[] { value });
						//}
						//for(int i=0;i<curParam.length;i++){
						//System.out.println(curParam[i]);
						//}

						//Collection c=map.values();
						//Iterator it=c.iterator();
						//while(it.hasNext()){
						//String name2=(String)it.next();

						//}

						//}
						System.out.print(request.getParameter("name") != null);
						System.out.print(request.getParameter("category") != null);
						System.out.println(request.getParameter("ms") != null);
						if ((request.getParameter("name") != null)
						&& (request.getParameter("category") != null)
						&& (request.getParameter("ms") != null)) {
							String productname = request.getParameter("name").trim();
							String category = request.getParameter("category").trim();
							String ms = request.getParameter("ms").trim();
							System.out.println(a.tranC(ms));
							System.out.println(a.tranC(productname));
							System.out.println(a.tranC(category));
							int i = sql
							.InsertBusiness(a.tranC(category), a.tranC(productname), a.tranC(ms), name2);

							if (i == 1) {
				%>
				<script language="JavaScript">
 				window.alert("添加成功");
 				window.close();
 			</script>
				<%
						}
						}
					}
				%>
			</table>
		</div>
	</body>
</html>
