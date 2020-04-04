  // creer les variables connexion
  let login = document.forms["connexionForm"]["login"];
  let	password = document.forms["connexionForm"]["password"];

  let errorLogin = document.getElementById("errorLogin");
  let errorPassword = document.getElementById("errorPassword");
  
  // creer les events d'ecoute
  login.addEventListener("blur", loginVerifie, true);
  password.addEventListener("blur", passwordVerifie, true);

  //verifier si les champs sont bon avant d'envoyer
  function ValidateConnexion(){
    if(login.value == ""){
      errorLogin.textContent = "Il faut mettre une adresse mail";
      errorLogin.style.color = "red";
      login.focus();
      return false;
    }
    if(password.value == ""){
      errorPassword.textContent = "Il faut mettre un mot de passe";
      errorPassword.style.color = "red";
      password.focus();
      return false;
    }
  }

  // si les champs sont bon, alors c'est ok
  function loginVerifie(){
    if(login.value != ""){
      errorLogin.innerHTML = "";
      return true;
    }
  }
  function passwordVerifie(){
    if(password.value != ""){
        errorPassword.innerHTML = "";
        return true;
    }
  }