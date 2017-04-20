<%@include file="shared/header.jsp" %>
<c:set var="msgId" value="0"/>
<c:set var="currentMsg" value="${msgFromURL}"/>

<c:if test="${param.msgdeleted!=null}">
    <div style="color:green">
        <span class="glyphicon glyphicon-ok-sign"></span> Message deleted!
    </div>
</c:if>

<c:if test="${param.replytomsgid!=null && loggedInUser.userName==msgFromURL.receiver.userName}">
    <br>
    <label>Reply to:</label> ${msgFromURL.sender.userName}
    <form method="post" action="">
        <div class="form-group">
            <label>Subject:</label>
            <input type="text" name="subject" value="Re: ${msgFromURL.subject}" class="form-control" required="required"/>
        </div>
        <div class="form-group">
            <label>Message</label>
            <textarea name="message" class="form-control" rows="7" required="required">


***********************************************************************
On ${msgFromURL.receivedDateTime}, ${msgFromURL.sender.userName} wrote:
***********************************************************************
Subject: ${msgFromURL.subject}
Message:
${fn:replace(msgFromURL.message,"<br/>","")}</textarea>
        </div>
        <button type="submit" class="btn btn-success">
            <span class="glyphicon glyphicon-send"></span> Reply
        </button>
        <a href="${SITE_URL}/inbox?all=1" class="btn btn-link"> Cancel</a>
    </form>
    <br><br>
</c:if>

<h3>${loggedInUser.userName}'s Inbox</h3>
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
                        <c:if test="${(msg.readStatus)==true}">
                            <span class="glyphicon glyphicon-eye-open" title="Read" style="color:green"></span>
                        </c:if>
                        <c:if test="${(msg.readStatus)==false}">
                            <span class="glyphicon glyphicon-eye-close" title="Unread" style="color:red"></span>
                        </c:if>
                    </td>
                    <td><a href="${SITE_URL}/users?viewid=${msg.sender.id}">${msg.sender.userName}</a></td>
                    <td>${msg.subject}</td>
                    <td>${msg.receivedDateTime}</td>
                    <td>
                        <form method="post" action="${SITE_URL}/inbox?viewmsgid=${msg.id}">
                            <button type="submit" class="btn btn-link">
                                <span class="glyphicon glyphicon-eye-open"></span> View message
                            </button>
                        </form>
                    </td>
                </tr>
                <c:if test="${param.viewmsgid==msg.id}">
                    <tr>
                        <td></td>
                        <td></td>
                        <td><b>Message:</b></td>
                        <td colspan="2">${msg.message}</td>
                        <td>
                            <a href="${SITE_URL}/inbox?replytomsgid=${msg.id}" class="btn btn-link">
                                <span class="glyphicon glyphicon-share-alt"></span> Reply
                            </a>
                            <form method="post" action="${SITE_URL}/inbox?deletemsgid=${msg.id}">
                                <button type="submit" class="btn btn-link" style="color:red;">
                                    <span class="glyphicon glyphicon-trash"></span> Delete
                                </button>
                            </form>
                            <form method="post" action="${SITE_URL}/inbox?unreadmsgid=${msg.id}">
                                <button type="submit" class="btn btn-link" style="color: grey;">
                                    <span class="glyphicon glyphicon-eye-close"></span> Mark as unread
                                </button>
                            </form>
                        </td>
                    </tr>
                    <tr><td></td><td></td><td></td><td></td><td></td><td></td></tr>
                </c:if>
                <c:set var="msgId" value="${msgId+1}"/>
            </c:if>
        </c:forEach>
    </table>
</div>
<%@include file="shared/footer.jsp" %>