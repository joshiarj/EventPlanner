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
    <br><a href="${SITE_URL}/events?viewall=1">Go back to all events</a><br>
    <h3>Delete Event: ${currentEvent.title}</h3>
    <table class="table">
        <tr>
            <td><label>Event ID:</label></td>
            <td>${currentEvent.id}</td>
        </tr>
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
    <br><a href="${SITE_URL}/events?viewall=1">Go back to all events</a><br>
    <h3>Event Details: ${currentEvent.title}</h3>
    <table class="table">
        <tr>
            <td><label>Event ID:</label></td>
            <td>${currentEvent.id}</td>
        </tr>
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
    <c:if test="${not empty loggedIn && currentEvent.organizer.userName!=loggedIn.userName}">
        <a href="${SITE_URL}/users?msgtoid=${currentEvent.organizer.id}" class="btn btn-link">
            <span class="glyphicon glyphicon-envelope"></span> Contact organizer
        </a><br>
    </c:if>
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

<c:if test="${param.viewall!=null}">
    <h3 align="center">All Events:</h3>
    <table width="100%" class="table table-striped table-hover">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Organizer</th>
            <th>Venue</th>
            <th>From</th>
            <th>To</th>
            <th>Action</th>
        </tr>
        <c:forEach var="event" items="${allEvents}">
            <tr>
                <td>${event.id}</td>
                <td><a href="${SITE_URL}/events?viewid=${event.id}">${event.title}</a></td>
                <td>
                    <a href="${SITE_URL}/users?viewid=${event.organizer.id}">${event.organizer.userName}</a>
                    <c:if test="${event.organizer.userName!=loggedIn.userName}"> | 
                        <a href="${SITE_URL}/users?msgtoid=${event.organizer.id}">
                            <span class="glyphicon glyphicon-envelope"></span>
                        </a>
                    </c:if>
                </td>
                <td>
                    <a href="https://www.google.com.np/maps/place/${event.venue}" target="_blank">${event.venue}</a>
                </td>
                <td>${event.startDate}</td>
                <td>${event.endDate}</td>
                <td>
                    <c:if test="${not empty loggedIn && event.organizer.userName==loggedIn.userName}">
                        <a href="${SITE_URL}/events?editid=${event.id}">
                            <span class="glyphicon glyphicon-pencil"></span> Edit
                        </a>| 
                        <a style="color:red" href="${SITE_URL}/events?deleteid=${event.id}">
                            <span class="glyphicon glyphicon-trash"></span> Delete
                        </a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${not empty loggedIn}">
        <div>
            <a href="${SITE_URL}/newevent" class="btn btn-primary">
                <span class="glyphicon glyphicon-plus"></span> Add New Event
            </a>
        </div>
    </c:if>
</c:if>

<%@include file="shared/footer.jsp" %>