<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.List,Partage.* " %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tempest</title>
    <link rel="icon" type="image/png" href="LogoProjet.png" />
    <link rel = stylesheet type="text/css" href="accueil.css" >
    <link rel = stylesheet type="text/css" href="menu.css" >
    <link rel = stylesheet type="text/css" href="header.css" >
    <script src="https://kit.fontawesome.com/5836357430.js"></script>


</head>


<% session.setAttribute("pageCourante","/ListDocsPartages.jsp");%>
<%String dossierCourant = new String(); %>
<%utilisateur u = (utilisateur) session.getAttribute("client"); %>
<%session.setAttribute("visibilite", "tous"); %>
<%List<Projet> listFavoris = Drive.getInstance().getTousLesDocumentsFavoris(u);%>
<% int i = 1; List<Projet> list = null; %>
		<%if (request.getAttribute("Rep") != null){ %>
			<%int idProjet = (Integer) request.getAttribute("idProjet"); %>
			<%list = Drive.getInstance().getDocumentInside(Drive.getInstance().getDocumentById(idProjet),(String) session.getAttribute("visibilite")); %>
			<%System.out.println("rep2"); %>
		<%}else{%>
  			<%list = Drive.getInstance().getDocsPartages(u); %>
		<%} %>
		<% dossierCourant = list.get(0).getUrlServeur(); %>
<body>



<div id="bloc">

    <nav>
        <ul>
            <li>
                <a href="accueil.html" id= "lienLogo"> <img src="../images/LogoProjet.png" alt="Logo" >  </a>
            </li>

            <li><form method="get" action="choixActionPage?choix=Accueil">
                <button type="submit"><i class="far fa-plus-square"></i></button>
                </form>
            </li>
            <li>
				<form method="get" action="choixActionPage?choix=Accueil">
                <button type="submit"><i class="far fa-folder"></i></button>
                </form>
            </li>
            <li>
				<form method="get" action="choixAccueil?choix=Recherche">
                <button type="submit"><i class="fas fa-search"></i></button>
                </form>
            </li>
            <li >
				<form method="get" action="choixAccueil?choix=Partages">
                <button type="submit"><i class="fas fa-users"></i></button>
                </form>
            </li>
            <li>
				<form method="get" action="choixAccueil?choix=Publics">
                <button type="submit"><i class="fas fa-globe-asia"></i></button>
                </form>
            </li>
            <li >
				<form method="get" action="choixAccueil?choix=Suivi">
                <button type="submit"><i class="far fa-star"></i></button>
                </form>
				
            </li>
            <li ><form method="get" action="choixAccueil?choix=Contacts">
                <button type="submit"><i class="far fa-paper-plane"></i></button>
                </form>
            </li>
            <li ><form method="get" action="choixAccueil?choix=Archives">
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
                        <a class="sousMenuParam1" href=""><i class="fas fa-sign-out-alt"></i></a>
                        <a class="sousMenuParam2" href=""><i class="fas fa-cogs"></i></a>
                    </div>
                </div>



            </div>

        </header>
        <div id="bloc2">
            <div id ="blocFavoris">
                <h4>Favoris</h4>
				<%for (Projet p : listFavoris) {%>
			<%=i++%>
			<div onclick="afficherForm(<%=p.getId()%>)">
				<p><%=p.getNom()%></p><br>
			</div>
			
			<div onclick="afficherForm(<%=i%>)">Renommer</div><br>
			<div onclick="afficherForm(<%=i+1%>)">Changer Visibilité</div><br>
			<div onclick="afficherForm(<%=i+2%>)">Partager</div><br>
			
			<form style="visibility:hidden" id=<%=i+2%> method="get" action ="choixActionProjet" >
				<input type="hidden" name="idProjetPere" value=<%=Drive.getInstance().getRepertoirePere(list.get(0)).getId()%>>
				<input type="hidden" type="text" name="Partager" value="Partager">
				<input type="text" name="mailPartage" placeholder="Entrez un mail avec qui partager">
				<input type="hidden" name="idProjet" value=<%=p.getId()%>>
				<input type="hidden" name="UrlServeur" value=<%=dossierCourant %>>
				<button type="submit" name="Partager" value="Partager">soumettre</button>
			</form>
			
			<form style="visibility:hidden" id = <%=i %> method="get" action ="choixActionProjet" >
				<input type="hidden" name="idProjetPere" value=<%=Drive.getInstance().getRepertoirePere(list.get(0)).getId()%>>
				<input type="hidden" type="text" name="Renommer" value="RenommerDoc" placeholder="Entrez le nouveau nom du document">
				<input type="text" name="NewName" placeholder="Entrez le nouveau nom du document">
				<input type="hidden" name="UrlServeur" value=<%=dossierCourant %>>
				<input type="text" name="idProjet" value=<%=p.getId()%>>
				<button type="submit" name="Rename" value="Renommer">soumettre</button>
			</form>
			
			<form style="visibility:hidden" id=<%=i+1%> method="get" action ="choixActionProjet" >
				<input type="hidden" name="idProjetPere" value=<%=Drive.getInstance().getRepertoirePere(list.get(0)).getId()%>>
				<input type="hidden" type="text" name="ChangerVisibilite" value="ChangerVisibiliteDoc" placeholder="">
				<input type="text" name="NewVisibility" placeholder="Entrez le nouveau nom du document">
				<input type="hidden" name="UrlServeur" value=<%=dossierCourant %>>
				<input type="text" name="idProjet" value=<%=p.getId()%>>
				<button type="submit" name="Partager" value="Partager">soumettre</button>
			</form>
				<% if (p.toString().equals("Repertoire")){ %>
					<figure id="1d">
						<i class="fas fa-folder"></i>
					</figure>
					<form style="visibility:hidden" id=<%=p.getId() %> method="get" action="choixActionProjet">
					<input type="text" name="idProjetPere" value=<%=Drive.getInstance().getRepertoirePere(list.get(0)).getId()%>>
					<input type="text" name="idProjet" value=<%=p.getId()%>>
					<button type="submit" name="OuvrirRep" value="OuvrirRepertoire">OuvrirRepertoire</button><br>
					<input type="hidden" name="UrlServeur" value=<%=dossierCourant %>>
					<button type="submit" name="Supprimer" value="SupprimerDoc">Supprimer</button><br>
					</form>
				<%}else {%>
					<figure>
						<i class="fas fa-file"></i>
					</figure>
					<form style="visibility:hidden" id=<%=p.getId() %> method="get" action="choixActionProjet">
					<input type="text" name="idProjetPere" value=<%=Drive.getInstance().getRepertoirePere(list.get(0)).getId()%>>
					<input type="text" name="idProjet" value=<%=p.getId()%>>
					<input type="hidden" name="UrlServeur" value=<%=dossierCourant %>>
					<button type="submit" name="Ouvrir" value="ouvrirDoc">Ouvrir</button><br>
					<button type="submit" name="Supprimer" value="SupprimerDoc">Supprimer</button><br>
					<button type="submit" name="Telecharger" value="TelechargerDoc">Telecharger</button><br>
					</form>
			<%}%>
		
		<%
		} 
		%>
        </div>
            <div id = "blocDossier">
                <h4>Dossiers PartagĂ©s</h4>
				<%for (Projet p : list) {%>
					<%=i++%>
					<div onclick="afficherForm(<%=p.getId()%>)">
						<p><%=p.getNom()%></p><br>
					</div>
					
					<div onclick="afficherForm(<%=i%>)">Renommer</div><br>
					<div onclick="afficherForm(<%=i+1%>)">Changer Visibilité</div><br>
					<div onclick="afficherForm(<%=i+2%>)">Partager</div><br>
					
					<form style="visibility:hidden" id=<%=i+2%> method="get" action ="choixActionProjet" >
						<input type="hidden" name="idProjetPere" value=<%=Drive.getInstance().getRepertoirePere(list.get(0)).getId()%>>
						<input type="hidden" type="text" name="Partager" value="Partager">
						<input type="text" name="mailPartage" placeholder="Entrez un mail avec qui partager">
						<input type="hidden" name="idProjet" value=<%=p.getId()%>>
						<input type="hidden" name="UrlServeur" value=<%=dossierCourant %>>
						<button type="submit" name="Partager" value="Partager">soumettre</button>
					</form>
					
					<form style="visibility:hidden" id = <%=i %> method="get" action ="choixActionProjet" >
						<input type="hidden" name="idProjetPere" value=<%=Drive.getInstance().getRepertoirePere(list.get(0)).getId()%>>
						<input type="hidden" type="text" name="Renommer" value="RenommerDoc" placeholder="Entrez le nouveau nom du document">
						<input type="text" name="NewName" placeholder="Entrez le nouveau nom du document">
						<input type="hidden" name="UrlServeur" value=<%=dossierCourant %>>
						<input type="text" name="idProjet" value=<%=p.getId()%>>
						<button type="submit" name="Rename" value="Renommer">soumettre</button>
					</form>
					
					<form style="visibility:hidden" id=<%=i+1%> method="get" action ="choixActionProjet" >
						<input type="hidden" name="idProjetPere" value=<%=Drive.getInstance().getRepertoirePere(list.get(0)).getId()%>>
						<input type="hidden" type="text" name="ChangerVisibilite" value="ChangerVisibiliteDoc" placeholder="">
						<input type="text" name="NewVisibility" placeholder="Entrez le nouveau nom du document">
						<input type="hidden" name="UrlServeur" value=<%=dossierCourant %>>
						<input type="text" name="idProjet" value=<%=p.getId()%>>
						<button type="submit" name="Partager" value="Partager">soumettre</button>
					</form>
						<% if (p.toString().equals("Repertoire")){ %>
							<figure id="1d">
								<i class="fas fa-folder"></i>
							</figure>
							<form style="visibility:hidden" id=<%=p.getId() %> method="get" action="choixActionProjet">
							<input type="text" name="idProjetPere" value=<%=Drive.getInstance().getRepertoirePere(list.get(0)).getId()%>>
							<input type="text" name="idProjet" value=<%=p.getId()%>>
							<button type="submit" name="OuvrirRep" value="OuvrirRepertoire">OuvrirRepertoire</button><br>
							<input type="hidden" name="UrlServeur" value=<%=dossierCourant %>>
							<button type="submit" name="Supprimer" value="SupprimerDoc">Supprimer</button><br>
							</form>
						<%}else {%>
							<figure>
								<i class="fas fa-file"></i>
							</figure>
							<form style="visibility:hidden" id=<%=p.getId() %> method="get" action="choixActionProjet">
							<input type="text" name="idProjetPere" value=<%=Drive.getInstance().getRepertoirePere(list.get(0)).getId()%>>
							<input type="text" name="idProjet" value=<%=p.getId()%>>
							<input type="hidden" name="UrlServeur" value=<%=dossierCourant %>>
							<button type="submit" name="Ouvrir" value="ouvrirDoc">Ouvrir</button><br>
							<button type="submit" name="Supprimer" value="SupprimerDoc">Supprimer</button><br>
							<button type="submit" name="Telecharger" value="TelechargerDoc">Telecharger</button><br>
							</form>
						<%} %>
				<%
				} 
				%>
                <div id="dossier">
                    <figure id="1d">
                        <i class="fas fa-folder"></i>
                        <figcaption>Dossier</figcaption>
                    </figure>
                    <figure>
                        <i class="fas fa-file"></i>
                        <figcaption>Fichier</figcaption>
                    </figure>

                </div></div>
        </div>
    </div>
</div>

		
		
	<form method="get" action="ChoixActionPage">
		<input type="hidden" name="idProjetPere" value=<%=Drive.getInstance().getRepertoirePere(list.get(0)).getId() %>>
		<input type="hidden" name="UrlServeur" value=<%=dossierCourant %>>
		<input type="hidden" name="Rep" value="ajouterRep"/>
		<input type="text" name="directory"/>Ajouter un répertoire
		<input type="radio" name="privOuPubl" value="0"> Publique
		<input type="radio" name="privOuOubl" value="1"> Prive
		<button type="submit"></button>
	</form>
	<form method="get" action="ChoixActionPage">
		<input type="hidden" name="idProjetPere" value=<%=Drive.getInstance().getRepertoirePere(list.get(0)).getId() %>>
		<input type="hidden" name="UrlServeur" value=<%=dossierCourant %>>
		<input type="text" name="nomFichier" placeholder="entrez le nom du fichier"/>
		<input type="hidden" name="ajouterFichier"/>
		<input type="text" name="cheminFichier" placeholder="copiez-coller le chemin de votre fichier"/>
		<input type="radio" name="privOuPubl" value="0"> Publique
		<input type="radio" name="privOuPubl" value="1"> Prive
		<button type="submit"></button>
	</form>
			
</body>
<script>
function afficherForm(id){
	document.getElementById(id).style.visibility = 'visible';
}
</script>
</html>