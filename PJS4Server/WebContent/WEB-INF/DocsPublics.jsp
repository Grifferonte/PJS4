<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List" %>
<%@page import="Partage.Projet " %>
<%@page import="java.util.List" %>
<%@page import="Partage.utilisateur" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% session.setAttribute("pageCourante","/DocsPublics.jsp");%>

<%utilisateur u = (utilisateur) session.getAttribute("client"); %>

<% int i = 1; List<Projet> list = null; %>

   <%if (session.getAttribute("ListDocsInside") != null ){%>
	  <%  list = (List<Projet>) session.getAttribute("ListDocsInside");%>
   <% }else{ %>
  		<%list = (List<Projet>) session.getAttribute("ListeDocsFavoris"); %>
	<% } %>
		<%for (Projet p : list) {%>
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
			<form method="get" action="choixActionPage">
			<button type="submit" name="DossierParent" value="RevenirDossierPrent">Revenir au dossierParent</button><br>
			</form>
			
</body>
<script>
function afficherForm(id){
	document.getElementById(id).style.visibility = 'visible';
}
</script>
</html>