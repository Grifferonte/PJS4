<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="get" action="co?m=mail&mdp=motDePasse">
	<input type="text" name="mail" placeholder="entrez votre mail">mail</input>
	<input type="password" name="motDePasse" placeholder="entrez votre mot de passe">mot de passe</input>
	<button type="submit" value="soumettre"></button>
</form>

<form method="get" action="inscription">
	<input type="hidden" name="param" value="InscEnCours"/>
	<button type="submit" value="Pas encore inscrit ? Créez vous un compte !"></button>
</form>

</body>
</html>