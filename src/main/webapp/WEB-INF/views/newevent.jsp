<%@include file="shared/header.jsp" %>
<h2 align="center">Register New Event</h2>
<c:if test="${param.success!=null}">
    <div style="color:green">
        <h3>Event successfully added!</h3>
    </div>
</c:if>
<form method="post" action="">
    <div class="form-group">
        <label>Title:</label>
        <input type="text" class="form-control" name="title" required="required"/>
    </div>
    <div class="form-group">
        <label>Description:</label>
        <textarea name="description" class="form-control" required="required"></textarea>
    </div>
    <div class="form-group">
        <label>Venue:</label>
        <input type="text" class="form-control" name="venue" required="required"/>
    </div>
    <div class="form-group">
        <label>Start Date:</label>
        <input type="date" class="form-control" name="startdate" required="required"/>
    </div>
    <div class="form-group">
        <label>End Date:</label>
        <input type="date" class="form-control" name="enddate" required="required"/>
    </div>
    <div class="form-group">
        <label>Upload Invite List File:</label>
        <input type="file" class="form-control" name="invitelistfile" required="required"/>
    </div>
    <!--    <div class="form-group">
            <label>Invite:</label>
            <input type="checkbox" name="invitestatus" required="required"/> 
        </div>-->
    <button type="submit" class="btn btn-success">Save</button>
</form>
<%@include file="shared/footer.jsp" %>