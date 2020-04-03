<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.List,Partage.* " %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form style="visibility:hidden" name="formPartage" method="get" action ="choixActionPage" >
		<input type="hidden" name="rechercheDocs"/>
		<input type="text" name="motsClefs" value="mailPartage" placeholder="Entrez un mot-clef">
		<button type="submit" name="Envoyer" value="Envoyer">Partager</button>>
	</form>
<% session.setAttribute("pageCourante","/DocsFavoris.jsp");%>
<%String dossierCourant = new String(); %>
<%utilisateur u = (utilisateur) session.getAttribute("client"); %>

<% int i = 1; List<Projet> list = null; %>
		<%if (request.getAttribute("Rep") != null){ %>
			<%int idProjet = (Integer) request.getAttribute("idProjet"); %>
			<%list = Drive.getInstance().getDocumentInside(Drive.getInstance().getDocumentById(idProjet)); %>
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
					<input type="text" name="idProjetPere" value=<%=Drive.getInstance().getRepertoirePere(list.get(0)).getId()%>>
					<input type="text" name="idProjet" value=<%=p.getId()%>>
					<button type="submit" name="OuvrirRep" value="OuvrirRepertoire">OuvrirRepertoire</button><br>
					<button type="submit" name="Partager" value="PartagerDoc">Partager</button><br>
					<button type="submit" name="Supprimer" value="SupprimerDoc">Supprimer</button><br>
					</form>
				<%}else {%>
					<form style="visibility:hidden" id=<%=p.getId() %> method="get" action="choixActionProjet">
					<input type="text" name="idProjetPere" value=<%=Drive.getInstance().getRepertoirePere(list.get(0)).getId()%>>
					<input type="text" name="idProjet" value=<%=p.getId()%>>
					<button type="submit" name="Ouvrir" value="ouvrirDoc">Ouvrir</button><br>
					<button type="submit" name="Partager" value="PartagerDoc">Partager</button><br>
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
	<form method="get" action="ChoixPage">
		<input type="hidden" name="idProjetPere" value=<%=Drive.getInstance().getRepertoirePere(list.get(0)).getId() %>>
		<input type="hidden" name="UrlServeur" value=<%=dossierCourant %>>
		<input type="hidden" name="Rep" value="ajouterRep"/>
		<input type="text" name="directory"/>Ajouter un répertoire
		<input type="radio" name="privOuPubl" value="0"> Publique
		<input type="radio" name="privOuOubl" value="1"> Prive
		<button type="submit"></button>
	</form>
	<form method="get" action="ChoixPage">
		<input type="hidden" name="idProjetPere" value=<%=Drive.getInstance().getRepertoirePere(list.get(0)).getId() %>>
		<input type="hidden" name="UrlServeur" value=<%=dossierCourant %>>
		<input type="text" name="nomFichier" placeholder="entrez le nom du fichier"/>
		<input type="hidden" name="Fichier" value="ajouterFichier"/>
		<input type="text" name="cheminFichier" placeholder="copiez-coller le chemin de votre fichier"/>
		<input type="radio" name="privOuPubl" value="0"> Publique
		<input type="radio" name="privOuPubl" value="1"> Prive
		<button type="submit"></button>
	</form>
			
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