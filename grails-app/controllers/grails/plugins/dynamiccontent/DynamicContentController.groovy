package grails.plugins.dynamiccontent

import org.springframework.dao.DataIntegrityViolationException

/**
 * DynamicContent related controller.
 * 
 * @author odangreaux
 */
class DynamicContentController {
	DynamicContentService dynamicContentService
	
	// security interceptor declaration
	def beforeInterceptor = [action: this.&checkSecurity, except: 'login']
	
	// default action
	def index() {
		redirect(action: "admin", params: params)
	}
	
	/**
	 * Security check private action executed as an interceptor before each action
	 */
	private checkSecurity() {
		// if dynamiccontent basic security is enabled
		if(grailsApplication.mergedConfig.grails.plugins.dynamiccontent.security.enabled) {
			// checks rights is session
			def adminRightsSessionFlag = grailsApplication.mergedConfig.grails.plugins.dynamiccontent.security.adminRightsSessionFlag as String
			if (!session[adminRightsSessionFlag]) {
				// if admin rigths haven't been granted
				redirect(action: 'login')
				return false
			}
		}		
	}
	
	def login(String password) {
		if(password) {
			def securityPassword = grailsApplication.mergedConfig.grails.plugins.dynamiccontent.security.password as String
			if(password != securityPassword) {
				flash.errorMessage = g.message(code:"grails.plugins.dynamiccontent.login.invalidPassword.message")
			}
			else {
				// grants admin rights
				def adminRightsSessionFlag = grailsApplication.mergedConfig.grails.plugins.dynamiccontent.security.adminRightsSessionFlag as String
				session[adminRightsSessionFlag] = true
				
				// redirects to the default action
				redirect(action: "index")
				return false
			}			
		}
	}
	
	def admin() {
		
	}

    def enableEdition() {
		def editionModeSessionFlag = grailsApplication.mergedConfig.grails.plugins.dynamiccontent.editionModeSessionFlag as String
		session[editionModeSessionFlag] = true
		redirect(action:"admin")
	}
	
	def disableEdition() {
		def editionModeSessionFlag = grailsApplication.mergedConfig.grails.plugins.dynamiccontent.editionModeSessionFlag as String
		session[editionModeSessionFlag] = false
		redirect(action:"admin")
	}
	
	def disableAdministration() {
		def adminRightsSessionFlag = grailsApplication.mergedConfig.grails.plugins.dynamiccontent.security.adminRightsSessionFlag as String
		session[adminRightsSessionFlag] = false
				
		// redirects to the default action
		redirect(action: "index")
	}
	
	def contentChanged(String key, String content) {
		dynamicContentService.createOrUpdateDynamicContent(key, content)
		render "OK"
	}
	
	

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[dynamicContentInstanceList: DynamicContent.list(params), dynamicContentInstanceTotal: DynamicContent.count()]
	}

	def create() {
		[dynamicContentInstance: new DynamicContent(params)]
	}

	def save() {
		def dynamicContentInstance = new DynamicContent(params)
		if (!dynamicContentInstance.save(flush: true)) {
			render(view: "create", model: [dynamicContentInstance: dynamicContentInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'dynamicContent.label', default: 'DynamicContent'), dynamicContentInstance.id])
		redirect(action: "show", id: dynamicContentInstance.id)
	}

	def show(Long id) {
		def dynamicContentInstance = DynamicContent.get(id)
		if (!dynamicContentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'dynamicContent.label', default: 'DynamicContent'), id])
			redirect(action: "list")
			return
		}

		[dynamicContentInstance: dynamicContentInstance]
	}

	def edit(Long id) {
		def dynamicContentInstance = DynamicContent.get(id)
		if (!dynamicContentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'dynamicContent.label', default: 'DynamicContent'), id])
			redirect(action: "list")
			return
		}

		[dynamicContentInstance: dynamicContentInstance]
	}

	def update(Long id, Long version) {
		def dynamicContentInstance = DynamicContent.get(id)
		if (!dynamicContentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'dynamicContent.label', default: 'DynamicContent'), id])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (dynamicContentInstance.version > version) {
				dynamicContentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						  [message(code: 'dynamicContent.label', default: 'DynamicContent')] as Object[],
						  "Another user has updated this DynamicContent while you were editing")
				render(view: "edit", model: [dynamicContentInstance: dynamicContentInstance])
				return
			}
		}

		dynamicContentInstance.properties = params

		if (!dynamicContentInstance.save(flush: true)) {
			render(view: "edit", model: [dynamicContentInstance: dynamicContentInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'dynamicContent.label', default: 'DynamicContent'), dynamicContentInstance.id])
		redirect(action: "show", id: dynamicContentInstance.id)
	}

	def delete(Long id) {
		def dynamicContentInstance = DynamicContent.get(id)
		if (!dynamicContentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'dynamicContent.label', default: 'DynamicContent'), id])
			redirect(action: "list")
			return
		}

		try {
			dynamicContentInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'dynamicContent.label', default: 'DynamicContent'), id])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'dynamicContent.label', default: 'DynamicContent'), id])
			redirect(action: "show", id: id)
		}
	}
}
