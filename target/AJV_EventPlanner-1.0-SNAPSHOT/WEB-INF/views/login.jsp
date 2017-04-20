<%@include file="shared/header.jsp" %>
<h2>Log In</h2>
<c:if test="${param.error!=null}">
    <div style="color:red">
        <span class="glyphicon glyphicon-info-sign"></span> Invalid Username/Password! Please try again.
    </div>
</c:if>
<c:if test="${param.notactivated!=null}">
    <div style="color:red">
        <span class="glyphicon glyphicon-info-sign"></span> Sorry! Your account isn't activated yet.
    </div>
</c:if>
<form method="post" action="">
    <div class="form-group">
        <label>Username:</label>
        <input type="text" class="form-control" name="username" required="required"/>
    </div>
    <div class="form-group">
        <label>Password:</label>
        <input type="password" class="form-control" name="password" required="required"/>
        <div align="right">
            <a href="${SITE_URL}/forgotpwd"> Forgot Your Password?</a>
        </div>
    </div>
    <button type="submit" class="btn btn-success">
        <span class="glyphicon glyphicon-user"></span> Log In
    </button>
</form>
<br>
<div>
    First visit? Become a member. Sign up <a href="${SITE_URL}/signup">here</a>!
</div>
<%@include file="shared/footer.jsp" %>