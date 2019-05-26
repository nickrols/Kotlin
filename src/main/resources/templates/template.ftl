<#macro page title>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">


    <link rel="stylesheet" type="text/css" href="/static/bootstrap/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/static/bootstrap/bootstrap.min.css">

</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light text-light bg-dark">
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                  <li class="nav-item active">
                    <a class="nav-link text-light" href="/">Accueil <span class="sr-only">(current)</span></a>
                  </li>
                <#if session??>
                  <li class="nav-item">
                    <a class="nav-link text-light" href="/article/post">Ajouter article</a>
                  </li>
                  </ul>
                      <span class="navbar-text text-success">
                           ${session}
                          </span>
                    <a class="nav-link text-light" href="/logout">Déconnexion</a>
                <#else>
                    </ul>
                      <a class="nav-link text-light" href="/login">Connexion</a>
            </div>
        </#if>
    </nav>
</header>
<section>
<#nested/>
</section>
<!-- footer section -->
</body></html>
</#macro>