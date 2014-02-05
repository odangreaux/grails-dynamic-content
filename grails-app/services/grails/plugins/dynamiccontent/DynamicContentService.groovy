package grails.plugins.dynamiccontent

/**
 * DynamicContent related services
 * 
 * @author odangreaux
 */
class DynamicContentService {
    /**
     * Creates or updates a DynamicContent
     * 
     * @param key The key identifying the DynamicContent
     * @param content The content to be saved
     */
	def createOrUpdateDynamicContent(String key, String content) {
		DynamicContent dynamicContent
		
		// search for the dynamic content
		dynamicContent = DynamicContent.findByKey(key)
		
		// if the dynamic content doesn't exists, creates it
		if(!dynamicContent) {
			dynamicContent = new DynamicContent(key:key, content:content)
		}
		else {
			// sets content
			dynamicContent.content = content
		}
		
		// saves the DynamicContent
		dynamicContent.save(failOnError: true, flush: true)
    }
}
