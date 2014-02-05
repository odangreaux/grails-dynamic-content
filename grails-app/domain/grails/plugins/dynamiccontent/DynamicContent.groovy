package grails.plugins.dynamiccontent

/**
 * A dynamic content
 * 
 * @author odangreaux
 */
class DynamicContent {
	// the key identifying the content
	String key
	
	// the content
	String content
	
	// last update date of the content
	Date lastUpdated

    static constraints = {
		key nullable: false, unique :true
		content nullable: true
		lastUpdated nullable: true
    }
	
	static mapping = {
		version false
		content type: "text"
	}
}
