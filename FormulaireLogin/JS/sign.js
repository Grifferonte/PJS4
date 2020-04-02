  // creer les variables inscription
  let pseudo = document.forms["inscriptionForm"]["pseudo"];
  let email = document.forms["inscriptionForm"]["email"];
  let mdp = document.forms["inscriptionForm"]["mdp"];
  let confirmationMdp = document.forms["inscriptionForm"]["confirmationMdp"];

  let errorPseudo = document.getElementById("errorPseudo");
  let errorEmail = document.getElementById("errorEmail");
  let errorMdp = document.getElementById("errorMdp");
  let errorConfirmationMdp = document.getElementById("errorConfirmationMdp");

  // creer les events d'ecoute
  pseudo.addEventListener("blur", pseudoVerifie, true);
  email.addEventListener("blur", emailVerifie, true);
  mdp.addEventListener("blur", mdpVerifie, true);
  confirmationMdp.addEventListener("blur", confirmationMdpVerifie, true);

  //verifier si les champs sont bon avant d'envoyer
  function Validate(){
      if (pseudo.value == ""){
          errorPseudo.textContent = "Il faut mettre un pseudo";
          errorPseudo.style.color = "red";
          pseudo.focus();
          return false;
      }
      if (email.value == ""){
          errorEmail.textContent = "Il faut mettre une adresse mail";
          errorEmail.style.color = "red";
          email.focus();
          return false;
      }
      if (mdp.value == ""){
          errorMdp.textContent = "Il faut mettre un mot de passe";
          errorMdp.style.color = "red";
          mdp.focus();
          return false;
      }
      if(mdp.value != confirmationMdp.value){
          errorConfirmationMdp.innerHTML = "Les deux mot de passe ne correspondent pas"
          errorConfirmationMdp.style.color = "red";
          return false;
      }
      
  }

  // si les champs sont bon, alors c'est ok
  function pseudoVerifie(){
      if(pseudo.value != ""){
          errorPseudo.innerHTML = "";
          return true;
      }
  }
  function emailVerifie(){
      if(email.value != ""){
          errorEmail.innerHTML = "";
          return true;
      }
  }
  function mdpVerifie(){
      if(mdp.value != ""){
          errorMdp.innerHTML = "";
          return true;
      }
  }
  function confirmationMdpVerifie(){
      if(confirmationMdp.value != ""){
        errorConfirmationMdp.innerHTML = "";
        return true;
      }
  }