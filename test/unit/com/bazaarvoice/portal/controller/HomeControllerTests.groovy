package com.bazaarvoice.portal.controller
import grails.test.mixin.TestFor

@TestFor(HomeController)
class HomeControllerTests {

    void testIndex() {
        controller.index()
        assertEquals "Should respond happily", 200, response.status
    }
}
