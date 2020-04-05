<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Sign in</title>
    <link rel="icon" type="image/png" href="LogoProjet.png" />
    <link rel="stylesheet" href="Login.css">
    <script type="text/javascript" src="sign.js"></script>
  </head>
  <body>

	  <form method="get" action="inscription?mail=mailUt&pseudo=pseudoUt&mdp=mdpUt&confirmMdp=confirmMdpUt" class="sign-form" name="inscriptionForm">
        <h1> Sign in </h1>


        <div class="login-txt">
          <input type="text" name="pseudoUt" placeholder="entrez votre pseudo">pseudo
        </div>
        <div id="errorPseudo"></div>

        <div class="login-txt">
          <input type="email" name="mailUt" placeholder="entrez votre mail">mail
        </div>
        <div id="errorEmail"></div>

        <div class="login-txt">
          <input type="password" name="mdpUt" placeholder="entrez votre mot de passe">mot de passe
        </div>
        <div id="errorMdp"></div>
			<input type="password" name="confirmMdpUt" placeholder="confirmez votre mot de passe">mot de passe confirme
        <div class="login-txt">
          
        </div>
        <div id="errorConfirmationMdp"></div>
        <br>

        <button type="submit" class="login-btn" value="S'inscrire"></button>


      </form>

  </body>
</html>
    