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
	<%List<Projet> listDocsSearch = Drive.getInstance().getDocumentsBySearch((String)request.getAttribute("motsClefs")); %>
 	<%for (Projet projet : listDocsSearch) {%>
 		<p><%=projet.getNom() %></p>
 	<%} %>
</body>
</html>