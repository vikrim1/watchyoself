package com.bazaarvoice.portal.auth
import com.bazaarvoice.portal.Role
import com.bazaarvoice.portal.User
import com.bazaarvoice.portal.UserRole
import com.bazaarvoice.portalsdk.client.UserEntity
import grails.test.mixin.Mock
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import portal.auth.PortalUser

@TestMixin(GrailsUnitTestMixin)
@Mock([User, Role, UserRole])
class UserProviderTest {

    UserProvider userProvider

    void init() {
        userProvider = new UserProvider()
    }

    void testCreateUser() {
        init()
        def entity = new UserEntity("testClient", "testClient/testUserName")
        entity.displayName = "Test Display Name"
        PortalUser user = userProvider.getUser(entity)
        assertEquals "Should have correct display name", entity.displayName, user.displayName
        assertEquals "Should have correct username", "testUserName", user.username
    }

    /**
     * Regression test for a uniqueness constraint issue we were running into trying to
     * get two different users¥
     */
    void testCreateMultipleUsers() {
        init()
        def entityOne = new UserEntity("testClient", "testClient/testUserOne")
        entityOne.displayName = "Test Display Name One"

        def entityTwo = new UserEntity("testClient", "testClient/testUserTwo")
        entityTwo.displayName = "Test Display Name Two"

        userProvider.getUser(entityOne)
        def user = userProvider.getUser(entityTwo)

        assertEquals "Should have correct display name", entityTwo.displayName, user.displayName
        assertEquals "Should have correct username", "testUserTwo", user.username
    }
}
