<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List,Partage.* " %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bonjour</title>
</head>
<body>

<% session.setAttribute("pageCourante","/DocsFavoris.jsp");%>
<%String dossierCourant = new String(); %>
<%utilisateur u = (utilisateur) session.getAttribute("client"); %>

<% int i = 1; List<Projet> list = null; %>
		<%if (request.getAttribute("Rep") != null){ %>
			<%Projet p = Drive.getInstance().getDocumentById(Integer.parseInt((String)request.getAttribute("idProjet"))); %>
			<%list = Drive.getInstance().getDocumentInside(p);%>
			<%System.out.println("rep2"); %>
		<%}else{%>
  			<%list = Drive.getInstance().getTousLesDocumentsFavoris(u); %>
		<%} %>
		<% dossierCourant = list.get(0).getUrlServeur(); %>
		<%for (Projet p : list) {%>
			<div onclick="afficherForm(<%=p.getId()%>)">
				<p><%=i++%></p><br>
				<p><%=p.getNom()%></p><br>
				<p><%=p.getId()%></p>
				<% if (p.toString().equals("Repertoire")){ %>
					<form id=<%=p.getId() %> method="get" action="choixActionProjet">
					<input type="hidden" name="idProjet" value=<%=p.getId() %>>
					<button type="submit" name="OuvrirRep" value="OuvrirRepertoire">OuvrirRepertoire</button><br>
					</form>
				<%}else {%>
					<form style="visibility:hidden" id=<%=p.getId() %> method="get" action="choixActionProjet">
						<input type="hidden" name="UrlServeur" value=<%=p.getUrlServeur() %>/>
						<button type="submit" name="Ouvrir" value="ouvrirDoc">Ouvrir</button><br>
						<button type="submit" name="Partager" value="PartagerDoc" onclick="afficherInputpartage()">Partager</button><br>
						<button type="submit" name="Supprimer" value="SupprimerDoc">Supprimer</button><br>
						<button type="submit" name="Telecharger" value="TelechargerDoc">Telecharger</button><br>
					</form>
					
					<form style="visibility:hidden" name="formPartage" method="get" action ="choixActionProjet" >
						<input style="visibility:hidden" type="text" name="partegeInput" value="mailPartage" placeholder="Entrez un mail avec qui partager">
						<button type="submit" name="Partager" value="Partager">Partager</button>>
					</form>
					
				<%} %>
			</div>
		<%
		} 
		%>
			
</body>
<script>
function afficherForm(id){
	document.getElementById(id).style.visibility = 'visible';
}
function afficherInputPartage(){
	document.getElementByName("formPartage").style.visibility = 'visible';
}
</script>
</html>