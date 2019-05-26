<#import "template.ftl" as layout />

<@layout.page title="Welcome">

<div class="container">
    <div class="row">
        <div class="col-2"></div>
         <div class="col-6">
            <h1>Articles :</h1>
            <#list articles as article>
                <p><a href="/article/${article.id}">Article n°${article.id} -  ${article.title}</a></p>
                 <#if session??>
                    <form method="POST" action="/article/delete/${article.id}">
                        <input class="delete btn btn-danger" type="submit" value="Supprimer" />
                    </form>
                 </#if>
            </#list>
         </div>
         <div class="col-2"></div>
    </div>
</div>

</@layout.page>
