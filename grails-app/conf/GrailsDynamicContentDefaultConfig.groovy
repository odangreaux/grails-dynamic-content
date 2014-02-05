grails {
	plugins {
		dynamiccontent {
			createNonexistentContent = true
			editionModeSessionFlag = "DYNAMICCONTENT_EDITION_ENABLE"
			defaultSurroundingTag = "div"
			security {
				enabled = true
				password = "A4212CACFFE05252A528A15CA170D6201D205254F3318D0EE7E84F3575D5E3AC"
				adminRightsSessionFlag = "DYNAMICCONTENT_ADMIN_RIGHTS"
			}
		}
	}
}
