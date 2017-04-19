<%@include file="shared/header.jsp" %>
<center><h1>Zeppelin Event Planners</h1><br></center>
<a href="${SITE_URL}/events?viewall">View all events</a>
<h3>Current & Upcoming Events:</h3>
<table width="100%" class="table">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Organizer</th>
        <th>Venue</th>
        <th>From</th>
        <th>To</th>
        <th>Action</th>
    </tr>
    <c:forEach var="event" items="${requestScope.allevents}">
        <tr>
            <td>${event.id}</td>
            <td><a href="${SITE_URL}/events?viewid=${event.id}">${event.title}</a></td>
            <td><a href="${SITE_URL}/userprofile?id=${event.organizer.id}">${event.organizer.userName}</a></td>
            <td>${event.venue}</td>
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
<%@include file="shared/footer.jsp" %>