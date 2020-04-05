<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Login</title>
    <link rel="icon" type="image/png" href="LogoProjet.png" />
    <link rel="stylesheet" href="Login.css">
    <script type="text/javascript" src="login.js"></script>
  </head>
  <body>
	  <form method="get" action="co?m=mail&mdp=motDePasse" class="login-form" name="connexionForm">
        <h1> Login </h1>

        <div class="login-txt">
          <input type="text" placeholder="Email" name="mail" autocomplete="off">
        </div>
        <div id="errorLogin"></div>

        <div class="login-txt">
          <input type="password" placeholder="Mot de passe" name="motDePasse" autocomplete="off">
        </div>
        <div id="errorPassword"></div>
        <br>

        <input type="submit" class="login-btn" value="Se connecter">
      </form>
	  <div class="bottom-text">
		<form method="get" action="inscription">
		<input type="hidden" name="param" value="InscEnCours"/>
		<button type="submit" value="Pas encore inscrit ? Créez vous un compte !">S'inscrire</button>
		</form>
      </div>

  </body>
</html>
    