package grails.plugins.dynamiccontent

/**
 * Controller for serving ckeditor resources
 * 
 * @author odangreaux
 */
class CkeditorController {

    /**
     * Renders ckeditor resources
     */
	def resources() {
		// extracts the requested resource from the URL
		def basePath = g.createLink(action:"base")
		basePath = basePath.replace("/base", "/resources")
		def resourcePath = "/ckeditor" + request.forwardURI.replace(basePath, "")
		
		// gets the requested resource from the classpath
		def inputStream = getClass().getResourceAsStream(resourcePath)
		
		// sets the content-type depending on the resource extension
		if(resourcePath.endsWith(".js")) {
			response.setContentType("application/javascript")
		}
		else if(resourcePath.endsWith(".css")) {
			response.setContentType("text/css")
		}
		
		// writes the responses
		response.outputStream << inputStream
		
		return	
	}
}
