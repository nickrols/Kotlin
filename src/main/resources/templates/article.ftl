<#import "template.ftl" as layout />

<@layout.page title="Welcome">
<div class="container">
    <div class="row">
        <div class="col-2"></div>
        <div class="col-6">
            <h1>Article n°${article.id}</h1>
            <p>${article.text}</p>
            <h2>Commentaires :</h2>
            <div class="commentaires">
            <#list article.comments as comment>

                <div class="border border-dark">
                            <p>${comment.text}</p>
                            <#if session??>
                            <form method="POST" action="/article/${article.id}/comment/${comment.id}">
                                <input class="delete btn btn-danger" type="submit" value="Supprimer" />
                            </form>
                    </#if>
                </div>
            </#list>

            </div>

            <form method="POST" action="/article/${article.id}">
            <div class="form-group">
                <label for="Textarea1">Ecrire un commentaire</label>
                <textarea class="form-control" id="Textarea1" name="comment"></textarea><br />
                <input class="btn btn-primary" type="submit" value="Poster un commentaire">
                </div>
            </form>
        </div>
        <div class="col-2"></div>
    </div>
</div>

</@layout.page>
