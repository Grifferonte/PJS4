// creer les variables inscription
let prenom = document.forms["inscriptionForm"]["prenom"];
let nom = document.forms["inscriptionForm"]["nom"];
let utilisateur = document.forms["inscriptionForm"]["utilisateur"];
let mdp = document.forms["inscriptionForm"]["mdp"];
let confirmationMdp = document.forms["inscriptionForm"]["confirmationMdp"];

let errorPrenom = document.getElementById("errorPrenom");
let errorNom = document.getElementById("errorNom");
let errorUtilisateur = document.getElementById("errorUtilisateur");
let errorMdp = document.getElementById("errorMdp");


// creer les variables connexion
let login = document.forms["connexionForm"]["login"];
let	password = document.forms["connexionForm"]["password"];

let errorLogin = document.getElementById("errorLogin");
let errorPassword = document.getElementById("errorPassword");


// creer les events d'ecoute
prenom.addEventListener("blur", prenomVerifie, true);
nom.addEventListener("blur", nomVerifie, true);
utilisateur.addEventListener("blur", utilisateurVerifie, true);
mdp.addEventListener("blur", mdpVerifie, true);
confirmationMdp.addEventListener("blur", confirmationMdpVerifie, true);

login.addEventListener("blur", loginVerifie, true);
password.addEventListener("blur", passwordVerifie, true);

//verifier si les champs sont bon avant d'envoyer
function Validate(){
    if(prenom.value == ""){
        prenom.style.border = "2px solid red";
        errorPrenom.textContent = "Il faut mettre un Prenom";
        prenom.focus();
        return false;
    }
    if (nom.value == ""){
        nom.style.border = "2px solid red";
        errorNom.textContent = "Il faut mettre un Nom";
        nom.focus();
        return false;
    }
    if (utilisateur.value == ""){
        utilisateur.style.border = "2px solid red";
        errorUtilisateur.textContent = "Il faut mettre un nom d'utilisateur";
        utilisateur.focus();
        return false;
    }
    if (mdp.value == ""){
        mdp.style.border = "2px solid red";
        errorMdp.textContent = "Il faut mettre un mot de passe";
        mdp.focus();
        return false;
    }
    if(mdp.value != confirmationMdp.value){
        mdp.style.border = "2px solid red";
        confirmationMdp.style.border = "2px solid red";
        errorMdp.innerHTML = "Les deux mot de passe ne correspondent pas"
        return false;
    }
    
}

function ValidateConnexion(){
    if(login.value == ""){
        login.style.border = "2px solid red";
        errorLogin.textContent = "Il faut mettre un nom d'utilisateur";
        login.focus();
        return false;
    }
    if(password.value == ""){
        password.style.border = "2px solid red";
        errorPassword.textContent = "Il faut mettre un mot de passe";
        password.focus();
        return false;
    }
}


// si les champs sont bon, alors c'est ok
function prenomVerifie(){
    if(prenom.value != ""){
        prenom.style.border = "none";
        prenom.style.borderBottom = "1px solid";
        errorPrenom.innerHTML = "";
        return true;
    }
}
function nomVerifie(){
    if(nom.value != ""){
        nom.style.border = "none";
        nom.style.borderBottom = "1px solid";
        errorNom.innerHTML = "";
        return true;
    }
}
function utilisateurVerifie(){
    if(utilisateur.value != ""){
        utilisateur.style.border = "none";
        utilisateur.style.borderBottom = "1px solid";
        errorUtilisateur.innerHTML = "";
        return true;
    }
}
function mdpVerifie(){
    if(mdp.value != ""){
        mdp.style.border = "none";
        mdp.style.borderBottom = "1px solid";
        errorMdp.innerHTML = "";
        return true;
    }
}
function confirmationMdpVerifie(){
    if(confirmationMdp.value != ""){
        confirmationMdp.style.border = "none";
        confirmationMdp.style.borderBottom = "1px solid"
        return true;
    }
}

function loginVerifie(){
    if(login.value != ""){
        login.style.border = "none";
        login.style.borderBottom = "1px solid";
        errorLogin.innerHTML = "";
        return true;
    }
}
function passwordVerifie(){
    if(password.value != ""){
        password.style.border = "none";
        password.style.borderBottom = "1px solid";
        errorPassword.innerHTML = "";
        return true;
    }
}