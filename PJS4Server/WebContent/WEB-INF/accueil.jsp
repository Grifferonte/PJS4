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
		<% session.setAttribute("client", u) ;%>
		<% session.setAttribute("pseudo", u.getLogin()) ;%>
		<p>Bienvenue <%=((utilisateur)session.getAttribute("pseudo"))%></p><br>
	<%} %>
	<p>Que voulez vous faire</p><br>
	<form method="get" action="choixAccueil">
	<button type="submit" name="tousMesDocuments" value="liste des documents">Liste de tous mes documents</button><br>
	<button type="submit" name="CreerProjet" value="Projet"> Creer un projet</button><br>
	<button type="submit" name="PartagesAvecMoi" value="Partage">Partages avec moi</button><br>
	<button type="submit" name="ListUsers" value="Utilisateurs" id="btnListUsr">liste de tous les utilisateurs</button><br>
	</form>
	<form method="get" action="EntreeServlet" >
	<button type="submit" value="revenir a la page d'accueil">se deconnecter</button>
	</form>
		
</body>
<script>
	function disableButton(){
		document.getElementById("btnListUsr").disabled = true;
		document.getElementById("btnListUsr").innerHTML += " (<br> vous n'etes pas administrateur <br>, cette  fonctionnalit� n'est pas <br> accessible pour vous) ";
		
	}
	<%if (!((utilisateur)session.getAttribute("client")).isAdmin()){ %>
		disableButton();
	<%}%>
</script>
		
</body>
</html>