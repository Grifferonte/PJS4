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
	<button type="submit" name="docsPublics" value="DocsPublics">Liste de tous mes documents</button><br>
	<button type="submit" name="docsFavoris" value="DocsFavoris"> Mes documents favoris</button><br>
	<button type="submit" name="docsPartages" value="DocsPartages">Partages avec moi</button><br>
	<button type="submit" name="docsArchives" value="DocsArchives">Documents Archives</button><br>
	</form>
	
<p>Choisissez un document à télécharger sur votre serveur</p>
<form method ="get" action="choixAction" name ="upload" value="upload">
  <input type="file" id="myFile" name="filename" value="fichierUp">
  <input type="submit">
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