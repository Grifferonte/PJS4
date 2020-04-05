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

<% session.setAttribute("pageCourante","/DocsPublics.jsp");%>
<%String dossierCourant = new String(); %>
<%utilisateur u = (utilisateur) session.getAttribute("client"); %>
<%session.setAttribute("visibilite", "public"); %>
<%List<Projet> listFavoris = Drive.getInstance().getTousLesDocumentsFavoris(u);%>
<% int i = 1; List<Projet> list = null; %>
		<%if (request.getAttribute("Rep") != null){ %>
			<%int idProjet = (Integer) request.getAttribute("idProjet"); %>
			<%list = Drive.getInstance().getDocumentInside(Drive.getInstance().getDocumentById(idProjet),(String) session.getAttribute("visibilite")); %>
			<%System.out.println("rep2"); %>
		<%}else{%>
  			<%list = Drive.getInstance().getTousLesDocumentsPublics(u); %>
		<%} %>
		<% dossierCourant = list.get(0).getUrlServeur(); %>

<body>



<div id="bloc">

    <nav>
        <ul>
            <li>
                <a href="accueil.html" id= "lienLogo"> <img src="LogoProjet.png" alt="Logo" >  </a>
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
        	<h4>Favoris</h4>
            	<div id ="blocFav">
					<%for (Projet p : listFavoris) {%>
					<div class="SuiteProjetFav">
						<%=i++%>
							<% if (p.toString().equals("Repertoire")){ %>
								<figure id="1f">
									<i class="fas fa-folder"></i>
								</figure>
								<div onclick="afficherForm(<%=p.getId()%>)">
									<p><%=p.getNom()%></p><br>
								</div>
								<form style="visibility:hidden" id=<%=p.getId() %> method="get" action="choixActionProjet">
								<input type="text" name="idProjetPere" value=<%=Drive.getInstance().getRepertoirePere(list.get(0)).getId()%>>
								<input type="text" name="idProjet" value=<%=p.getId()%>>
								<input type="hidden" name="UrlServeur" value=<%=dossierCourant %>>
								</form>
							<%}else {%>
								<figure id="2f">
									<i class="fas fa-file"></i>
								</figure>
								<div onclick="afficherForm(<%=p.getId()%>)">
									<p><%=p.getNom()%></p><br>
								</div>
								<form style="visibility:hidden" id=<%=p.getId() %> method="get" action="choixActionProjet">
								<input type="text" name="idProjetPere" value=<%=Drive.getInstance().getRepertoirePere(list.get(0)).getId()%>>
								<input type="text" name="idProjet" value=<%=p.getId()%>>
								<input type="hidden" name="UrlServeur" value=<%=dossierCourant %>>
								</form>
							<%} %>
						</div>
					<%
					} 
					%>
            </div>
            <div id = "blocProjets">
                <h4>Dossiers Publiques</h4>
				<%for (Projet p : list) {%>
				<div class="SuiteProjet">
					<%=i++%>
						<% if (p.toString().equals("Repertoire")){ %>
							<figure id="1f">
								<i class="fas fa-folder"></i>
							</figure>
							<div onclick="afficherForm(<%=p.getId()%>)">
								<p><%=p.getNom()%></p><br>
							</div>
							<form style="visibility:hidden" id=<%=p.getId() %> method="get" action="choixActionProjet">
							<input type="text" name="idProjetPere" value=<%=Drive.getInstance().getRepertoirePere(list.get(0)).getId()%>>
							<input type="text" name="idProjet" value=<%=p.getId()%>>
							<input type="hidden" name="UrlServeur" value=<%=dossierCourant %>>
							</form>
						<%}else {%>
							<figure id="2f">
								<i class="fas fa-folder"></i>
							</figure>
							<div onclick="afficherForm(<%=p.getId()%>)">
								<p><%=p.getNom()%></p><br>
							</div>
							<form style="visibility:hidden" id=<%=p.getId() %> method="get" action="choixActionProjet">
							<input type="text" name="idProjetPere" value=<%=Drive.getInstance().getRepertoirePere(list.get(0)).getId()%>>
							<input type="text" name="idProjet" value=<%=p.getId()%>>
							<input type="hidden" name="UrlServeur" value=<%=dossierCourant %>>
							</form>
						<%} %>
					</div>
				<%}%>
            </div>
        </div>
    </div>
</div>
</body>
<script>
function afficherForm(id){
	document.getElementById(id).style.visibility = 'visible';
}
</script>
</html>