<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="context" value="${pageContext.servletContext.contextPath}" />
<meta charset="UTF-8">
<title>Decode Vin Service</title>
<link rel="stylesheet" href="${context}/css/semantic.min.css">
<script src="${context}/js/jquery-3.4.1.min.js"></script>
<script src="${context}/js/semantic.min.js"></script>
<script src="${context}/js/decodevin.js"></script>
</head>
<body>
	<div class="ui hidden divider"></div>
	<div class="ui container">
		<div class="ui form">
			<div class="inline field">
				<label>Decode VIN:</label>
				<div class="ui icon input">
					<input id="vin-holder" type="text" placeholder="Search...">
					<i id="decode-vin-btn" class="inverted circular search link icon"></i>
				</div>
			</div>
		</div>
		<div class="ui hidden divider"></div>
		<div id="message-holder" class="ui bottom warning message" style="display: none;">
			<i class="icon help"></i> <span id="decodevin-error-code"></span>; <span id="decodevin-error-text"></span>
		</div>
		<div class="ui hidden divider"></div>
		<div class="ui blue segment">
			<div id="results-list" class="ui list">
				<%-- dynamically populated --%>
			</div>
		</div>
	</div>

</body>
</html>