<%@ page import="grails.plugins.dynamiccontent.DynamicContent" %>



<div class="fieldcontain ${hasErrors(bean: dynamicContentInstance, field: 'key', 'error')} ">
	<label for="key">
		<g:message code="dynamicContent.key.label" default="Key" />
		
	</label>
	<g:textField name="key" value="${dynamicContentInstance?.key}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dynamicContentInstance, field: 'content', 'error')} ">
	<label for="content">
		<g:message code="dynamicContent.content.label" default="Content" />
		
	</label>
	<g:textField name="content" value="${dynamicContentInstance?.content}"/>
</div>

