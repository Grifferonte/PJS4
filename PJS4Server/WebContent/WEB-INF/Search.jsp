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
	<form style="visibility:hidden" name="formPartage" method="get" action ="choixActionPage" >
		<input type="hidden" name="recherche"/>
		<input type="text" name="motsClefs" placeholder="Entrez un mot-clef">
		<button type="submit" name="Envoyer" value="Envoyer">Partager</button>>
	</form>
	<%if (request.getAttribute("DocsPublicsSuiviUt") != null) {%>
		<%int idPseudoSuivi = (Integer) request.getAttribute("idPseudoSuivi");%>
		<%utilisateur UtSuivi = Drive.getInstance().getUserById(idPseudoSuivi); %>
		<% List<Projet> listPublics = Drive.getInstance().getTousLesDocumentsPublics(UtSuivi);%>
		<%for (Projet p : listPublics){ %>
			<p><%=p.getNom() %></p>
		<%} %>
	<%} %>
	<% if (request.getAttribute("motsclefs") != null){%>
		<%List<Projet> listDocsSearch = Drive.getInstance().getDocumentsBySearch((String)request.getAttribute("motsClefs")); %>
	 	<%List<utilisateur> listUsersSearch = Drive.getInstance().getUsersBySearch((String)request.getAttribute("motsClefs")); %>
	 	<%for (Projet projet : listDocsSearch) {%>
	 		<p><%=projet.getNom() %></p>
 		<%} %>
 		<%for (utilisateur ut : listUsersSearch) {%>
 			<form method="get" action="choixActionPage">
		 		<input type="text" name="pseudoUtSuivi" value=<%=ut.getId() %>><%=ut.getPseudo() %></p>
		 		<button type="submit" name="DocsPublicsSuiviUt"></button>
	 			<button type="submit" name="suivre"></button>
 			</form>
 		<%} %>
 	<%} %>
 	<%if (request.getAttribute("MessageConfirmation") != null){ %>
 		<%System.out.println(request.getAttribute("MessageConfirmation")); %>
 	<%} %>
</body>
</html>
