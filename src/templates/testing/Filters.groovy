@artifact.package@

import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 *
 * NOTE: do not use setUp/tearDown or @Before/@After
 *     They are not guaranteed to run after Grails Test Mixin setUp/tearDown, and we often need them to.
 *     See http://grails.1312388.n4.nabble.com/Test-mixins-and-Before-td4329905.html for details
 */
@Mock(@artifact.testclass@)
class @artifact.name@ {

    void testSomething() {
        fail "Implement me"
    }
}
