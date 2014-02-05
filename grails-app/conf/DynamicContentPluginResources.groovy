modules = {
    "grails-dynamic-content" {
		dependsOn 'jquery'
		resource url:[plugin: 'grails-dynamic-content', dir:'js', file:'ckeditor_basepath.js.gsp']
		resource id:'ckeditor', url:[plugin: 'grails-dynamic-content', dir:'js', file:'ckeditor.js']
		resource url:[plugin: 'grails-dynamic-content', dir:'js', file:'grails-dynamic-content.js.gsp']
		
    }
}