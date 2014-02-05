import org.grails.plugin.resource.mapper.MapperPhase
import org.codehaus.groovy.grails.web.mapping.LinkGenerator

class DynamicContentPluginResourceMapper {
	LinkGenerator grailsLinkGenerator
	
	def phase = MapperPhase.GENERATION
	
	static defaultIncludes = [ '**/ckeditor.js' ]

	def map(resource, config) {
		if(resource.module.name == "grails-dynamic-content" && resource.id == "ckeditor") {
			resource.processedFile << getClass().getResourceAsStream("/ckeditor/ckeditor.js")
			resource.updateActualUrlFromProcessedFile()
		}
	}
}