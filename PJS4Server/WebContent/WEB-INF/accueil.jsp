<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Partage.utilisateur" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%if (session.getAttribute("client") != null) {%>
		<p>Bienvenue <%=((utilisateur)session.getAttribute("client")).getLogin()%></p><br>
	<%} 
	else{%>
		<%utilisateur u = (utilisateur) request.getAttribute("User"); %>
		<%String log = (String) request.getAttribute("login"); %>
		<%String mdp = (String) request.getAttribute("mdp"); %>
		<% session.setAttribute("client", u) ;%>
		<% session.setAttribute("logclient", log) ;%>
		<% session.setAttribute("Mdpclient", mdp) ;%>
		<p>Bienvenue <%=((utilisateur)session.getAttribute("client")).getLogin()%></p><br>
	<%} %>
	<p>Que voulez vous faire</p><br>
	<form method="get" action="http://localhost:8080/JavaEEProjet/choixAccServlet?choixClient=choix">
	<button type="submit" name="tousMesDocuments" value="liste des documents">Liste de tous mes documents</button><br>
	<button type="submit" name="Cr�erProjet" value="Projet"> Cr�er un projet</button><br>
	<button type="submit" name="Partag�sAvecMoi" value="Partage">Partag�s avec moi</button><br>
	<button type="submit" name="ListUsers" value="Utilisateurs" id="btnListUsr">liste de tous les utilisateurs</button><br>
	</form>
	<form method="get" action="http://localhost:8080/JavaEEProjet/EntreeServlet" >
	<button type="submit" value="revenir � la page d'accueil">se d�connecter</button>
	</form>
		
</body>
<script>
	function disableButton(){
		document.getElementById("btnListUsr").disabled = true;
		document.getElementById("btnListUsr").innerHTML += " (<br> vous n'�tes pas administrateur <br>, cette  fonctionnalit� n'est pas <br> accessible pour vous) ";
		
	}
	<%if (!((utilisateur)session.getAttribute("client")).isAdmin()){ %>
		disableButton();
	<%}%>
</script>
		
</body>
</html>