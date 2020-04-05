<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List,Partage.* " %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<head>
    <meta charset="UTF-8">
    <title>Tempest</title>
    <link rel="icon" type="image/png" href="LogoProjet.png"/>
    <link rel = stylesheet type="text/css" href="accueil.css">
    <link rel = stylesheet type="text/css" href="menu.css">
    <link rel = stylesheet type="text/css" href="header.css">
    <link rel = stylesheet type="text/css" href="search.css">
    <script src="https://kit.fontawesome.com/5836357430.js"></script>
</head>

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
                <div id = "blocChek">
                    <h4>Suivre</h4>
                    <input type="checkbox" name="">
                </div>
                <h1>Nom personne</h1>
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
            <div id = "blocDossier">
                <h4>Recherche</h4>
                <div id="dossier">
				<% if (request.getAttribute("motsClefs") != null){%>
					<%List<Projet> listDocsSearch = Drive.getInstance().getDocumentsBySearch((String)request.getAttribute("motsClefs")); %>
					<%List<utilisateur> listUsersSearch = Drive.getInstance().getUsersBySearch((String)request.getAttribute("motsClefs")); %>
					<%for (Projet projet : listDocsSearch) {%>
						<p><%=projet.getNom() %></p>
					<%} %>
					<%for (utilisateur u : listUsersSearch) {%>
						<i class="fas fa-user"> </i>
						<%System.out.println(u.getPseudo()); %>
						<p><%=u.getPseudo() %></p>
					<%} %>
				<%} %>
                </div></div>
        </div>
    </div>
</div>



</body>
</html>
