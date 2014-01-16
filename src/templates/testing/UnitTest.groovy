@artifact.package@import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 *
 * NOTE: do not use setUp/tearDown or @Before/@After
 *     They are not guaranteed to run after Grails Test Mixin setUp/tearDown, and we often need them to.
 *     See http://grails.1312388.n4.nabble.com/Test-mixins-and-Before-td4329905.html for details
 */
@TestMixin(GrailsUnitTestMixin)
class @artifact.name@ {

    void setUp() {
        // Setup logic here
    }

    void tearDown() {
        // Tear down logic here
    }

    void testSomething() {
        fail "Implement me"
    }
}
