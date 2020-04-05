<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="fr">
<head>
	<title>Contact</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" type="image/png" href="LogoProjet.png">
	<link rel="stylesheet" type="text/css" href="main.css">
	<script type="text/javascript" src="contact.js"></script>
</head>
<body>


	<div class="container-contact">
		<div class="wrap-contact">
			<form class="contact-form validate-form" action="envoyerEmail" method="POST" onsubmit="return Validate()" name="contactForm">
				<span class="contact-form-title">
					Contactez nous !													
				</span>
				<div class="wrap-input validate-input">
					<input class="input" type="email" name="email" placeholder="Email" autocomplete="off">
					<span class="focus-input"></span>
				</div>
				<div class="wrap-input validate-input">
					<input class="input" type="text" name="objet" placeholder="Objet" autocomplete="off">
					<span class="focus-input"></span>
				</div>
				<div class="wrap-input validate-input">
					<textarea class="input" name="message" placeholder="Votre message"></textarea>
					<span class="focus-input"></span>
				</div>
				<div class="container-contact-form-btn">
					<button class="contact-form-btn">
						Envoyer
					</button>
				</div>
			</form>
			<form id="retour" action="../html/accueil.html">
				<div class="container-contact-form-btn">
				<button class="contact-form-btn">
					Retour
				</button>
				</div>
			</form>
			<div class="contact-more">
				Notre assistance technique est à votre disposition : <span class="contact-more-highlight"> 06-51-34-76-07</span>
			</div>
		</div>
	</div>
</body>
</html>
    