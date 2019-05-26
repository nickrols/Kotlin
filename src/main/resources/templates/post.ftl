<#import "template.ftl" as layout />

<@layout.page title="Welcome">
<div class="conatiner">
    <div class="row">
        <div class="col-2"></div>
        <div class="col-6">
            <h1>Rédiger un article :</h1>
            <form method="POST" action="/article/post">
                <label for=articleTitle">Titre</label>
                <input id="articleTitle" type="text" name="articleTitle">
                <br/>
                <label for="Textarea1">Description</label>
                <textarea class="form-control" id="Textarea1" name="article"></textarea><br />
                <input class="btn btn-primary" type="submit" value="Poster l'article">
            </form>
        </div>
        <div class="col-2"></div>
    </div
</div>

</@layout.page>
