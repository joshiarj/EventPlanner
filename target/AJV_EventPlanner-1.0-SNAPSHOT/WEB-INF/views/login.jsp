<%@include file="shared/header.jsp" %>
<h2>Log In</h2>
<c:if test="${param.error!=null}">
    <div style="color:red">
        <span class="glyphicon glyphicon-info-sign"></span> Invalid Username/Password! Please try again.
    </div>
</c:if>
<c:if test="${param.notactivated!=null}">
    <div style="color:red">
        <span class="glyphicon glyphicon-info-sign"></span> Your account is not activated yet!
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
<%@include file="shared/footer.jsp" %>