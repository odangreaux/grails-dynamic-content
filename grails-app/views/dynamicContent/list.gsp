
<%@ page import="grails.plugins.dynamiccontent.DynamicContent" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'dynamicContent.label', default: 'DynamicContent')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-dynamicContent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link action="admin"><g:message code="grails.plugins.dynamiccontent.admin.link.label" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-dynamicContent" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="key" title="${message(code: 'dynamicContent.key.label', default: 'Key')}" />
					
						<g:sortableColumn property="content" title="${message(code: 'dynamicContent.content.label', default: 'Content')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'dynamicContent.lastUpdated.label', default: 'Last Updated')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${dynamicContentInstanceList}" status="i" var="dynamicContentInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${dynamicContentInstance.id}">${fieldValue(bean: dynamicContentInstance, field: "key")}</g:link></td>
					
						<td>${fieldValue(bean: dynamicContentInstance, field: "content")}</td>
					
						<td><g:formatDate date="${dynamicContentInstance.lastUpdated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${dynamicContentInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
