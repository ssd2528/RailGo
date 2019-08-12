<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<body>
	<form id="signup" action="/signup" method="POST">
		<%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> --%>
		<input type="hidden" name="email" type="text" value="${email}"/>
		<input type="hidden" name="name" type="text" value="${name}"/>
		<input type="hidden" name="pwd" type="text" value="${pwd}"/>
		<input type="hidden" name="gender" type="text" value="${gender}"/>
		<input type="hidden" name="status" type="text" value="${status}"/>
	</form>
	
	<form id="signin" action="/signin" target="parent" method="POST">
		<%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> --%>
		<input type="hidden" name="email" type="text" value="${email}"/>
		<input type="hidden" name="pwd" type="text" value="${pwd}"/>
	</form>
	

	<script src="<c:url value='../resources/jquery-3.4.1.min.js'/>"></script>
	<script>
		$(document).ready(function() {
			if('${request}' == 'signup') {
				$("form#signup").submit();
			} else if('${request}' == 'signin') {
				$('form#signin').submit();
				self.close();
				window.opener.location.href='../';
			}
		});
	</script>
</body>
</html>