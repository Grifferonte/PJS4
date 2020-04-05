<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="fr">
<head>
	<title>Parametre</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="icon" type="image/png" href="LogoProjet.png" />
	<link rel="stylesheet" type="text/css" href="main.css">
	<script src="https://kit.fontawesome.com/5836357430.js"></script>
	<script type="text/javascript" src="parametre.js"></script>

</head>
<body>


	<div class="container-param">


		<div class="wrap-param">
			
			<span class="form-title">
				ParamĂ¨tres utilisateur													
			</span>

			<div class="information-user">
				<div class="information-icone">
					<i class="far fa-id-card"></i>
				</div>
				<div class="information-text">
					Adresse mail :
					<br>
					Pseudonyme :
					<br>
					Mot de passe :
					<br>
					<br>
				</div>
			</div>

			<span class="form-title-pseudo">
				Modifier votre pseudonyme :
			</span>
			<form action="" method="POST" name="pseudoForm" onsubmit="return ValidatePseudo()">
				<div class="wrap-input">
					<input class="input" type="text" name="pseudo" placeholder="Nouveau pseudonyme" autocomplete="off">
					<span class="focus-input"></span>
					<button class="validation-button">
						Valider
					</button>
				</div>
			</form>
			<span class="form-title-mdp">
				Modifier votre mot de passe :
			</span>
			<form action="" method="POST" name="mdpForm" onsubmit="return ValidateMdp()">
				<div class="wrap-input">
					<input class="input" type="password" name="mdp" placeholder="Nouveau mot de passe" autocomplete="off">
					<span class="focus-input"></span>
				</div>
			
				<div class="wrap-input">
					<input class="input" type="password" name="validationMdp" placeholder="Confirmation mot de passe" autocomplete="off">
					<span class="focus-input"></span>
					<button class="validation-button">
						Valider
					</button>
				</div>
			</form>

			<form action="../html/accueil.html">
				<div class="container-form-button">
					<button class="form-button">
						Retour
					</button>
				</div>
			</form>
		</div>
	</div>




</body>
</html>
    