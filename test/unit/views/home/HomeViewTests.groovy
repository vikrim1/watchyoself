package views.home

import grails.test.mixin.TestMixin
import grails.test.mixin.web.GroovyPageUnitTestMixin

@TestMixin(GroovyPageUnitTestMixin)
class HomeViewTests {

    def testIndex() {
        assertTrue "Index view should render contently", render(view: 'home/index').contains("Placeholder")
    }
}
