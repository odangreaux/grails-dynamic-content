package grails.plugins.dynamiccontent

class DynamicContentTagLib {
	static namespace = "dc"
	
	def grailsApplication
	
	/**
	 * Renders a dynamic content.
	 * 
	 * @attr key REQUIRED The key identifiying the content
	 * @attr tag The HTML tag to render to surround the content. DIV if not specified.
	 */
	def content = { attrs, body ->
		def specialTags = ["tag"]
		def dynamicContentContent = ""
		def contenteditable = false
		def tag = ""
		
		def editionModeSessionFlag = grailsApplication.mergedConfig.grails.plugins.dynamiccontent.editionModeSessionFlag as String
		
		// search for the DynamicContent with the specified key
		DynamicContent dynamicContent = DynamicContent.findByKey(attrs.key)
		
		// if found, populate the content
		if(dynamicContent) {
			dynamicContentContent = dynamicContent.content
		}
		
		if(attrs.tag) {
			tag = attrs.tag
		}
		else {
			tag = grailsApplication.mergedConfig.grails.plugins.dynamiccontent.defaultSurroundingTag as String
		}
		
		// set edition mode
		if(session[editionModeSessionFlag]) {
			contenteditable = true
		}
		
		// render HTML
		out << "<${tag} "
		
		// attributes copy
		attrs.each {
			attr, value ->
			if(!specialTags.contains(attr.toLowerCase())) {
				out << /${attr}="${value}" /
			}	
		}
		
		out << /contenteditable="${contenteditable}" /
		out << '>'
		out << dynamicContentContent
		out << "</${tag}>"
	}
	
	/**
	 * Renders the body content only if dynamic content edition is enabled
	 */
	def ifContentEditionEnabled = { attrs, body ->
		def editionModeSessionFlag = grailsApplication.mergedConfig.grails.plugins.dynamiccontent.editionModeSessionFlag as String
		if(session[editionModeSessionFlag]) {
			out << body()
		}
	}
	
	/**
	 * Renders the body content only if dynamic content edition is deisabled
	 */
	def ifContentEditionDisabled = { attrs, body ->
		def editionModeSessionFlag = grailsApplication.mergedConfig.grails.plugins.dynamiccontent.editionModeSessionFlag as String
		if(!session[editionModeSessionFlag]) {
			out << body()
		}
	}
	
	/**
	 * Renders the body only if dynamic content basic security is enabled and if the current session has admin rights
	 */
	def ifHasAdminRights = { attrs, body ->
		def securityEnabled = grailsApplication.mergedConfig.grails.plugins.dynamiccontent.security.enabled
		if(securityEnabled) {
			def adminRightsSessionFlag = grailsApplication.mergedConfig.grails.plugins.dynamiccontent.security.adminRightsSessionFlag as String
			if(session[adminRightsSessionFlag]) {
				out << body()
			}
		}
	}
}
