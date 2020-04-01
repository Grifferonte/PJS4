<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%if (request.getAttribute("erreur") != null) {%>
	<%=request.getAttribute("erreur") %>
<%} %>
<form method="get" action="inscription?mail=mailUt&pseudo=pseudoUt&mdp=mdpUt&confirmMdp=confirmMdpUt">
	<input type="email" name="mailUt" placeholder="entrez votre mail">mail</input>
	<input type="text" name="pseudoUt" placeholder="entrez votre pseudo">pseudo</input>
	<input type="password" name="mdpUt" placeholder="entrez votre mot de passe">mot de passe</input>
	<input type="password" name="confirmMdpUt" placeholder="confirmez votre mot de passe">mot de passe confirme</input>
	<button type="submit" value="Valider"></button>
</form>

</body>
</html>