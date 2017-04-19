<%@include file="shared/header.jsp" %>
<h3>${loggedInUser.userName}'s Inbox</h3>
<c:set var="msgId" value="0"/>
<div>
    <table class="table table-striped">
        <tr>
            <th>#</th>
            <th></th>
            <th>From</th>
            <th>Subject</th>
            <th>Received on:</th>
            <th>Action</th>
        </tr>
        <c:forEach var="msg" items="${allMessages}">
            <c:if test="${msg.receiver.userName==loggedInUser.userName}">
                <tr>
                    <td>${msgId+1}</td>
                    <td>
                        <c:if test="${(msg.readStatus)==true}"><span class="glyphicon glyphicon-eye-open"></span></c:if>
                        <c:if test="${(msg.readStatus)==false}"><span class="glyphicon glyphicon-eye-close"></span></c:if>
                    </td>
                    <td><a href="${SITE_URL}/users?viewid=${msg.sender.id}">${msg.sender.userName}</a></td>
                    <td>${msg.subject}</td>
                    <td>${msg.receivedDateTime}</td>
                    <td>
                        <a  href="${SITE_URL}/inbox?viewmsgid=${msg.id}">
                            <span class="glyphicon glyphicon-eye-open"></span> View</a> | 
                        <a href="${SITE_URL}/inbox?replytomsgid=${msg.id}">
                            <span class="glyphicon glyphicon-share-alt"></span> Reply</a> | 
                        <a href="${SITE_URL}/inbox?deletemsgid=${msg.id}" style="color:red">
                            <span class="glyphicon glyphicon-trash"></span> Delete</a>
                    </td>
                </tr>
                <c:if test="${param.viewmsgid==msg.id}">
                    <tr>
                        <td></td>
                        <td></td>
                        <td><b>Message:</b></td>
                        <td colspan="3">${msg.message}</td>
                    </tr>
                    <tr><td></td><td></td><td></td><td></td><td></td><td></td></tr>
                </c:if>
                <c:set var="msgId" value="${msgId+1}"/>
            </c:if>
        </c:forEach>
    </table>
</div>
<%@include file="shared/footer.jsp" %>