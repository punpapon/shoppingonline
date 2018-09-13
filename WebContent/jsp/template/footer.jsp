<%@ page import="util.database.ConnectionProvider"%>
<br/>
Copyright &#169; 2013 Project. All rights reserved. Project version 0.0.0<br/>
Tue Oct 15 09:00:00 ICT 2013 <%="Active: " + (ConnectionProvider.NUM_ACTIVE + " of " + ConnectionProvider.MAX_ACTIVE)%><%=" Idel: " + (ConnectionProvider.NUM_IDLE + " of " + ConnectionProvider.MAX_IDLE)%>
<br/>
<br/>	