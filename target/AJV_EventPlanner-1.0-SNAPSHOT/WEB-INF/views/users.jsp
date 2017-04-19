<%@include file="shared/header.jsp" %>
<c:if test="${param.viewid!=null}">
    <h3>Viewing ${userFromURL.userName}'s profile!</h3>
    <div>
        <table class="table">
            <tr>
                <td><label>Username:</label></td>
                <td>${userFromURL.userName}</td>
            </tr>
            <tr>
                <td><label>Email:</label></td>
                <td>${userFromURL.email} [<a href="${SITE_URL}/users?msgtoid=${userFromURL.id}">Send Message</a>]</td>
            </tr>
            <tr>
                <td><label>Sex:</label></td>
                <td>${userFromURL.sex}</td>
            </tr>
            <tr>
                <td><label>Member Since:</label></td>
                <td>${userFromURL.joinedDate}</td>
            </tr>
            <tr>
                <td><label>Active:</label></td>
                <td>${(userFromURL.status)?"Yes":"No"}</td>
            </tr>
        </table>
    </div>
</c:if>

<c:if test="${param.msgtoid!=null}">
    <h3>Send Message to: ${userFromURL.userName}</h3>
    <div>
        <form method="post" action="">
            <div class="form-group">
                <label>Subject:</label>
                <input type="text" class="form-control" name="subject" required="required" placeholder="Enter Message Subject" />
            </div>
            <div class="form-group">
                <label>Message:</label>
                <textarea class="form-control" name="message" required="required" placeholder="Enter Message"></textarea>
            </div>
            <button type="submit" class="btn btn-success">Send Message</button>
        </form>
    </div>
</c:if>

<%@include file="shared/footer.jsp" %>