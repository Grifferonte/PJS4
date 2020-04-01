<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List,Partage.* " %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<% session.setAttribute("pageCourante","/DocsArchives.jsp");%>
<%utilisateur u = (utilisateur) session.getAttribute("client"); %>

<% int i = 1;
   List<Projet> list = Drive.getInstance().getTousLesDocumentsArchives(u); %>
 
			<%
				for (Projet p : list) {
			%>
			<tr>
				<td><%=i++%></td>
				<td><%=p.getNom()%></td>
				<td><%=p.getId()%></td>
			</tr><br>
			<%
				}
			%>



</body>
</html>