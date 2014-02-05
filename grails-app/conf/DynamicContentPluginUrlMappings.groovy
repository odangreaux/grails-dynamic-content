class DynamicContentPluginUrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		"/ckeditor/resources/$param1?/$param2?/$param3?/$param4?/$param5?"(controller: "ckeditor", action: "resources")

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
