<%@ page import="grails.plugins.dynamiccontent.DynamicContent"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName" value="${message(code: 'dynamicContent.label', default: 'DynamicContent')}" />
<title><g:message code="grails.plugins.dynamiccontent.admin.title" /></title>
</head>
<body>
	<a href="#admin-dynamicContent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label" /></a></li>
			<li><g:link class="list" action="list">
					<g:message code="default.list.label" args="[entityName]" />
				</g:link></li>
			<li><g:link class="create" action="create">
					<g:message code="default.new.label" args="[entityName]" />
				</g:link></li>
		</ul>
	</div>

	<div class="content">
		<h1>
			<g:message code="grails.plugins.dynamiccontent.admin.title" />
		</h1>
		<fieldset>
			<dc:ifHasAdminRights>
				<g:message code="grails.plugins.dynamiccontent.admin.hasAdminRights.message" />
				<g:link action="disableAdministration">
					<g:message code="grails.plugins.dynamiccontent.admin.disableAdministration.action.label" />
				</g:link>
				<br/><br/>
			</dc:ifHasAdminRights>
			
			<dc:ifContentEditionEnabled>
				<g:message code="grails.plugins.dynamiccontent.admin.enabled.message" />
				<g:link action="disableEdition">
					<g:message code="grails.plugins.dynamiccontent.admin.disableEdition.action.label" />
				</g:link>
			</dc:ifContentEditionEnabled>
			<dc:ifContentEditionDisabled>
				<g:message code="grails.plugins.dynamiccontent.admin.disabled.message" />
				<g:link action="enableEdition">
					<g:message code="grails.plugins.dynamiccontent.admin.enableEdition.action.label" />
				</g:link>
			</dc:ifContentEditionDisabled>
		</fieldset>
	</div>
</body>
</html>
