<%@include file="shared/header.jsp" %>
<c:set var="eventToEdit" value="${currentEvent}"/>
<c:if test="${param.editid!=null && currentEvent.organizer.userName==loggedIn.userName}">
    <h3>Edit Event: ${eventToEdit.title}</h3>
    <c:if test="${param.success!=null}">
        <div style="color:green">
            <h3>Event successfully edited!</h3>
        </div>
    </c:if>
    <form method="post" action="">
        <div class="form-group">
            <label>New Title:</label>
            <input type="text" class="form-control" name="title" value="${eventToEdit.title}" required="required"/>
        </div>
        <div class="form-group">
            <label>New Description:</label>
            <textarea name="description" class="form-control" required="required">${eventToEdit.description}</textarea>
        </div>
        <div class="form-group">
            <label>New Venue:</label>
            <input type="text" class="form-control" name="venue" value="${eventToEdit.venue}" required="required"/>
        </div>
        <div class="form-group">
            <label>New Start Date:</label>
            <input type="date" class="form-control" name="startdate" value="${eventToEdit.startDate}" required="required"/>
        </div>
        <div class="form-group">
            <label>New End Date:</label>
            <input type="date" class="form-control" name="enddate" value="${eventToEdit.endDate}" required="required"/>
        </div>
        <!--    <div class="form-group">
                <label>Invite:</label>
                <input type="checkbox" name="invitestatus" required="required"/> 
            </div>-->
        <button type="submit" class="btn btn-success">Save</button>
    </form>
    <br>
    <a href="${SITE_URL}/events?deleteid=${eventToEdit.id}">Delete</a> this event instead.
</c:if>
<c:if test="${param.deleteid!=null && currentEvent.organizer.userName==loggedIn.userName}">
    <h3>Delete Event: ${currentEvent.title}</h3>
    <table class="table">
        <tr>
            <td><label>Title:</label></td>
            <td>${eventToEdit.title}</td>
        </tr>
        <tr>
            <td><label>Description:</label></td>
            <td>${eventToEdit.description}</td>
        </tr>
        <tr>
            <td><label>Venue:</label></td>
            <td>${eventToEdit.venue}</td>
        </tr>
        <tr>
            <td><label>Start Date:</label></td>
            <td>${eventToEdit.startDate}</td>
        </tr>
        <tr>
            <td><label>End Date:</label></td>
            <td>${eventToEdit.endDate}</td>
        </tr>
    </table>
    <form method="post" action="">
        <button type="submit" class="btn btn-danger">
            <span class="glyphicon glyphicon-trash"></span> Delete
        </button>
    </form>
    <br>
    <a href="${SITE_URL}/events?editid=${eventToEdit.id}">Edit</a> this event instead.
</c:if>
<c:if test="${param.viewid!=null}">
    <h3>Event Details: ${currentEvent.title}</h3>
    <table class="table">
        <tr>
            <td><label>Title:</label></td>
            <td>${currentEvent.title}</td>
        </tr>
        <tr>
            <td><label>Description:</label></td>
            <td>${currentEvent.description}</td>
        </tr>
        <tr>
            <td><label>Venue:</label></td>
            <td>${currentEvent.venue}</td>
        </tr>
        <tr>
            <td><label>Start Date:</label></td>
            <td>${currentEvent.startDate}</td>
        </tr>
        <tr>
            <td><label>End Date:</label></td>
            <td>${currentEvent.endDate}</td>
        </tr>
        <tr>
            <td><label>Added By:</label></td>
            <td>${currentEvent.organizer.userName}</td>
        </tr>
    </table>
    <c:if test="${not empty loggedIn && currentEvent.organizer.userName==loggedIn.userName}">
        <a href="${SITE_URL}/events?editid=${currentEvent.id}" class="btn btn-link">
            <span class="glyphicon glyphicon-pencil"></span> Edit this event
        </a><br>
        <a style="color:red" href="${SITE_URL}/events?deleteid=${currentEvent.id}" class="btn btn-link">
            <span class="glyphicon glyphicon-trash"></span> Delete this event
        </a>
    </c:if>
</c:if>
<c:if test="${(param.deleteid!=null || param.editid!=null) && currentEvent.organizer.userName!=loggedIn.userName}">
    <br>
    <div style="color:red">
        <span class="glyphicon glyphicon-info-sign"></span> You are NOT authorized to edit or delete this event!
    </div>
</c:if>
<%--<c:if test="${param.viewall==null}">--%>
<!--All Events:-->
<%--</c:if>--%>
<%@include file="shared/footer.jsp" %>