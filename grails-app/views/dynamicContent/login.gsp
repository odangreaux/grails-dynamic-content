<%@ page import="grails.plugins.dynamiccontent.DynamicContent"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName" value="${message(code: 'dynamicContent.label', default: 'DynamicContent')}" />
<title><g:message code="grails.plugins.dynamiccontent.login.title" /></title>
</head>
<body>
	<div class="content">
		<h1>
			<g:message code="grails.plugins.dynamiccontent.login.title" />
		</h1>
		<g:if test="${flash.errorMessage}">
			<div class="errors">
				<ul>
					<li>${flash.errorMessage}</li>
				</ul>
			</div>
		</g:if>
		<fieldset>
			<g:form method="post" action="login">
				<fieldset>
					<div class="fieldcontain">
						<label for="password"><g:message code="grails.plugins.dynamiccontent.login.password.label" /></label>
						<g:passwordField name="password" />
						<g:submitButton name="${g.message(code:'grails.plugins.dynamiccontent.login.login.action.label')}"/>
					</div>
				</fieldset>
			</g:form>
		</fieldset>
	</div>
</body>
</html>
