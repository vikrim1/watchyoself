package views.settings

import grails.test.mixin.TestMixin
import grails.test.mixin.web.GroovyPageUnitTestMixin

@TestMixin(GroovyPageUnitTestMixin)
class SettingsViewTests {
    def testApp() {
        assertTrue "App view should render a-okay", render(view: 'settings/app').contains("Application Settings")
    }

    def testUser() {
        assertTrue "User view should render dandy", render(view: 'settings/user').contains("User Settings")
    }
}
