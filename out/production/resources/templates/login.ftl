<#import "template.ftl" as layout />

<@layout.page title="Welcome">
<#if error??>
<p style="color:red;">${error}</p>
</#if>
<div class="container">
    <div class="row">
        <div class="col"></div>
        <div class="col">
            <form action="/login" method="POST">
                <div class="form-group">
                    <input name="username" type="text" placeholder="Username"/>
                </div>
                <div class="form-group">
                    <input name="password" type="password" placeholder="Password"/>
                 </div>

                <input class="btn btn-success" type="submit" value="Connexion" />
            </form>
        </div>
        <div class="col"></div>
</div>

</@layout.page>
