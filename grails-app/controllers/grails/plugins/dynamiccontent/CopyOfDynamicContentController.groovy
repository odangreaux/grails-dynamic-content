package grails.plugins.dynamiccontent

/**
 * DynamicContent related controller.
 * 
 * @author odangreaux
 */
class CopyOfDynamicContentController {
	DynamicContentService dynamicContentService

    def enableEdition() {
		def editionModeSessionFlag = grailsApplication.mergedConfig.grails.plugins.dynamiccontent.editionModeSessionFlag as String
		session[editionModeSessionFlag] = true
		render "DynamicContent edition was enabled"
	}
	
	def disableEdition() {
		def editionModeSessionFlag = grailsApplication.mergedConfig.grails.plugins.dynamiccontent.editionModeSessionFlag as String
		session[editionModeSessionFlag] = false
		render "DynamicContent edition was disabled"
	}
	
	def contentChanged(String key, String content) {
		dynamicContentService.createOrUpdateDynamicContent(key, content)
		render "OK"
	}
}
