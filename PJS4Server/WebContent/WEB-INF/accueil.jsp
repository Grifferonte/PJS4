<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*, Partage.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tempest</title>
    <link rel="icon" type="image/png" href="../images/LogoProjet.png"/>
    <link rel = stylesheet type="text/css" href="accueil.css">
	<link rel = stylesheet type="text/css" href="menu.css">
    <link rel = stylesheet type="text/css" href="header.css">
    <script src="https://kit.fontawesome.com/5836357430.js"></script>


</head>



<body>


	<% session.setAttribute("pageCourante","/accueil.jsp");%>
	<%String dossierCourant = new String(); %>
	<%utilisateur u = (utilisateur) session.getAttribute("client"); %>
	<%List<Projet> listFavoris = Drive.getInstance().getTousLesDocumentsFavoris(u); %>
	<%session.setAttribute("visibilite", "tous"); %>
	<% int i = 1; List<Projet> list = null; %>
		<%if (request.getAttribute("Rep") != null){ %>
			<%int idProjet = (Integer) request.getAttribute("idProjet"); %>
			<%list = Drive.getInstance().getDocumentInside(Drive.getInstance().getDocumentById(idProjet),(String) session.getAttribute("visibilite")); %>
			<%System.out.println("rep2"); %>
		<%}else{%>
  			<%list = Drive.getInstance().getTousLesDocumentsPrives(u); %>
		<%} %>
		<% dossierCourant = list.get(0).getUrlServeur(); %>
    <div id="bloc">

    <nav>
        <ul>
            <li>
                <a href="accueil.html" id= "lienLogo"> <img src="LogoProjet.png" alt="Logo" >  </a>
            </li>

            <li><form method="get" action="choixActionPage">
            	<input type="hidden" name="choix" value="Ajout">
                <button type="submit"><i class="far fa-plus-square"></i></button>
                </form>
            </li>
            <li>
				<form method="get" action="choixActionPage">
				<input type="hidden" name="choix" value="Accueil">
                <i type="submit" class="far fa-folder"></i>
                </form>
            </li>
            <li>
				<form method="get" action="choixAccueil">
				<input type="hidden" name="choix" value="Recherche">
                <button type="submit"><i class="fas fa-search"></i></button>
                </form>
            </li>
            <li >
				<form method="get" action="choixAccueil">
				<input type="hidden" name="choix" value="Partages">
                <button type="submit" value="envoyer"></button>
                </form>
            </li>
            <li>
				<form method="get" action="choixAccueil">
				<input type="hidden" name="choix" value="Publics">
                <button type="submit"><i class="fas fa-globe-asia"></i></button>
                </form>
            </li>
            <li >
				<form method="get" action="choixAccueil">
				<input type="hidden" name="choix" value="Suivi">
                <button type="submit"><i class="far fa-star"></i></button>
                </form>
				
            </li>
            <li ><form method="get" action="choixAccueil">
           	    <input type="hidden" name="choix" value="Contacts">
                <button type="submit"><i class="far fa-paper-plane"></i></button>
                </form>
            </li>
            <li ><form method="get" action="choixAccueil">
            	<input type="hidden" name="choix" value="Archives">
                <button type="submit"><i class="far fa-trash-alt"></i></button>
                </form>
            </li>
        </ul>
    </nav>

    <div id="bloc1">
            <header>
                <div id="blocBarre">
                </div>
                <div id="param">
                    <div class="user">
                        <i class="fas fa-user"> </i>
                        <div class="menuParam">
                            <a class="sousMenuParam1" href="../FormulaireLogin/Login.html"><i class="fas fa-sign-out-alt"></i></a>
                            <form method="get" action="choixAccueil">
				            	<input type="hidden" name="choix" value="Parametres">
				                <button type="submit"><i class="fas fa-cogs"></i></button>
				            </form>
                        </div>
                    </div>



                </div>

            </header>
        <div id="bloc2">
            <div id ="blocFavoris">
            <h4>Favoris</h4>

            <div id="favoris">
	            <div id = "dossier">
	            	<h4>Dossiers</h4>
					<%for (Projet p : listFavoris) {%>
						<div id="SuiteProjet">
						<% if (p.toString().equals("Repertoire")){ %>
							<figure id="1f">
								<i class="fas fa-folder"></i>
							</figure>
							<br>
							<div onclick="afficherForm(<%=p.getId()%>)">
								<p><%=p.getNom()%></p><br>
							</div>
						<%}else {%>
							<figure id="2f">
								<i class="fas fa-file"></i>
							</figure>
							<br>
							<div onclick="afficherForm(<%=p.getId()%>)">
								<p><%=p.getNom()%></p><br>
							</div>
						<%} %>
						</div>
						<%
						} 
						%>
				</div>
			</div>
            <div id="dossier">
					<%for (Projet p : list) {%>
					<% if (p.toString().equals("Repertoire")){ %>
					<div class="suiteProjets">
						<figure id="1f">
							<i class="fas fa-folder"></i>
						</figure>
						<div onclick="afficherForm(<%=p.getId()%>)">
							<p><%=p.getNom()%></p><br>
						</div>
					</div>
					<%}else {%>
					<div class="suiteProjets">
						<figure id="2f">
							<i class="fas fa-file"></i>
						</figure>
						<div onclick="afficherForm(<%=p.getId()%>)">
							<p><%=p.getNom()%></p><br>
						</div>
					</div>
					<%} %>
					<%
					} 
					%>
		        </div>
			</div>
        </div>
       </div>
   </div>
</body>
<script>
function afficherForm(id){
	document.getElementById(id).style.visibility = 'visible';
}
function disableButton(){
	document.getElementById("btnListUsr").disabled = true;
	document.getElementById("btnListUsr").innerHTML += " (<br> vous n'etes pas administrateur <br>, cette  fonctionnalit� n'est pas <br> accessible pour vous) ";
	
}
<%if (!((utilisateur)session.getAttribute("client")).isAdmin()){ %>
	disableButton();
<%}%>
</script>