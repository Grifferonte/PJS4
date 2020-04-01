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

<% session.setAttribute("pageCourante","/DocsFavoris.jsp");%>

<%utilisateur u = (utilisateur) session.getAttribute("client"); %>

<% int i = 1;
   List<Projet> list = Drive.getInstance().getTousLesDocumentsFavoris(u); %>
 
			<%
				for (Projet p : list) {
					%>
					<div onclick="afficherForm(<%=p.getId()%>)">
						<p><%=i++%></p><br>
						<p><%=p.getNom()%></p><br>
						<p><%=p.getId()%></p>
						<form style="visibility:hidden" id=<%=p.getId() %> method="get" action="choixAction">
						<input type="hidden" name="UrlServeur" value=<%=p.getUrlServeur() %>/>
						<button type="submit" name="Ouvrir" value="ouvrirDoc">Ouvrir</button><br>
						<button type="submit" name="Partager" value="PartagerDoc">Partager</button><br>
						<button type="submit" name="Supprimer" value="SupprimerDoc">Supprimer</button><br>
						<button type="submit" name="Telecharger" value="TelechargerDoc">Telecharger</button><br>
						</form>
					</div>
					<%
				}
			%>

</body>

<script>
function afficherForm(id){
	document.getElementById(id).style.visibility = 'visible';
}
</script>
</html>