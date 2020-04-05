	// creer les variables
	let pseudo = document.forms["pseudoForm"]["pseudo"];
	let	mdp = document.forms["mdpForm"]["mdp"];
	let confirmationMdp = document.forms["mdpForm"]["validationMdp"];
	
	// creer les events d'ecoute
	pseudo.addEventListener("blur", pseudoVerifie, true);
	mdp.addEventListener("blur", mdpVerifie, true);
	confirmationMdp.addEventListener("blur", confirmationMdpVerifie, true);

	//verifier si les champs sont bon avant d'envoyer
	function ValidatePseudo(){
		if(pseudo.value == ""){
			pseudo.style.border = "1px solid red";
			pseudo.style.borderRadius = "20px";
			pseudo.focus();
			return false;
		}
	}
	function ValidateMdp(){
		if(mdp.value == ""){
			mdp.style.border = "1px solid red";
			mdp.style.borderRadius = "20px";
			mdp.focus();
			return false;
		}
		if(confirmationMdp.value == ""){
			confirmationMdp.style.border = "1px solid red";
			confirmationMdp.style.borderRadius = "20px";
			confirmationMdp.focus();
			return false;
		}
		if(mdp.value != confirmationMdp.value){
			confirmationMdp.style.border = "1px solid red";
			confirmationMdp.style.borderRadius = "20px";
			confirmationMdp.focus();
			return false;
		}
	}

	// si les champs sont bon, alors c'est ok
	function pseudoVerifie(){
		if(pseudo.value != ""){
			pseudo.style.border = "none";
			return true;
		}
	}
	function mdpVerifie(){
		if(mdp.value != ""){
			mdp.style.border = "none";
			return true;
		}
	}
	function confirmationMdpVerifie(){
		if(confirmationMdp.value != ""){
			confirmationMdp.style.border = "none";
			return true;
		}
	}