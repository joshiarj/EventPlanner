<%@include file="shared/header.jsp" %>
<h2 align="center">New User Registration</h2>
<c:if test="${param.success!=null}">
    <div style="color:green">
        <span class="glyphicon glyphicon-ok-sign"></span> User successfully registered!
    </div>
</c:if>
<c:if test="${param.emerror!=null}">
    <div style="color:red">
        <span class="glyphicon glyphicon-info-sign"></span> Email already exists! Please try again.
    </div>
</c:if>
<c:if test="${param.unerror!=null}">
    <div style="color:red">
        <span class="glyphicon glyphicon-info-sign"></span> Username already exists! Please try again.
    </div>
</c:if>
<c:if test="${param.pwderror!=null}">
    <div style="color:red">
        <span class="glyphicon glyphicon-info-sign"></span> Passwords don't match! Please try again.
    </div>
</c:if>
<form method="post" action="">
    <div class="form-group">
        <label>Username:</label>
        <input type="text" class="form-control" name="username" required="required"/>
    </div>
    <div class="form-group">
        <label>Email:</label>
        <input type="email" class="form-control" name="email" required="required"/>
    </div>
    <div class="form-group">
        <label>Sex:</label>
        <select name="sex" class="form-control" required="required">
            <option value="">------</option>
            <option value="M">Male</option>
            <option value="F">Female</option>
        </select>
    </div>
    <div class="form-group">
        <label>Password:</label>
        <input type="password" class="form-control" name="password" required="required"/>
    </div>
    <div class="form-group">
        <label>Retype password to confirm:</label>
        <input type="password" class="form-control" name="retypepassword" required="required"/>
    </div>
<!--    <div class="form-group">
        <label>Profile Picture (Optional):</label>
        <input type="file" accept="image/*" class="form-control" name="profilepicture" />
    </div>-->
    <button type="submit" class="btn btn-success">
        <span class="glyphicon glyphicon-plus"></span> Sign Up
    </button>
    <button type="button" class="btn btn-primary" value="Reset Form" onClick="this.form.reset()">
        <span class="glyphicon glyphicon-erase"></span> Reset form
    </button>
</form>
<br>
<div>
    Already a member? Log in <a href="${SITE_URL}/login">here</a>!
</div>
<%@include file="shared/footer.jsp" %>