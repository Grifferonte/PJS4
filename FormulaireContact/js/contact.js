	// creer les variables
	let email = document.forms["contactForm"]["email"];
	let	objet = document.forms["contactForm"]["objet"];
	let message = document.forms["contactForm"]["message"];
	
	// creer les events d'ecoute
	email.addEventListener("blur", emailVerifie, true);
	objet.addEventListener("blur", objetVerifie, true);
	message.addEventListener("blur", messageVerifie, true);
	
	//verifier si les champs sont bon avant d'envoyer
	function Validate(){
		if(email.value == ""){
			email.style.border = "1px solid red";
			email.style.borderRadius = "20px";
			email.focus();
			return false;
		}
		if(objet.value == ""){
			objet.style.border = "1px solid red";
			objet.style.borderRadius = "20px";
			objet.focus();
			return false;
		}
		if(message.value == ""){
			message.style.border = "1px solid red";
			message.style.borderRadius = "20px";
			message.focus();
			return false;
		}
	}

	// si les champs sont bon, alors c'est ok
	function emailVerifie(){
		if(email.value != ""){
			email.style.border = "none";
			return true;
		}
	}
	function objetVerifie(){
		if(objet.value != ""){
			objet.style.border = "none";
			return true;
		}
	}
	function messageVerifie(){
		if(message.value != ""){
			message.style.border = "none";
			return true;
		}
	}