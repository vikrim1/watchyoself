package com.bazaarvoice.portal.auth

import com.bazaarvoice.portal.Role
import com.bazaarvoice.portal.User
import com.bazaarvoice.portal.UserRole
import com.bazaarvoice.portalsdk.client.UserEntity
import portal.auth.PortalUser
import portal.auth.PortalUserProvider

class UserProvider implements PortalUserProvider {
    @Override
    PortalUser getUser(UserEntity userEntity) {
        String[] clientNameUsernamePair = userEntity.getUserId().split("/")
        def username = clientNameUsernamePair[1]
        User user = User.findByUsername(username)

        if (!user) {
            // PORTAL-GRAILS TODO modify this to create reasonable default users for your app
            User.withTransaction {
                def userRole
                if (userEntity.isInternalUser()) {
                    userRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
                } else {
                    userRole = new Role(authority: 'ROLE_USER').save(flush: true)
                }
                // PORTAL-GRAILS TODO associate this user with the client in userEntity
                user = new User([username: username, fullName: userEntity.getDisplayName(), enabled: true, password: 'unused'])
                user.save(flush: true)
                UserRole.create(user, userRole)
            }
        }

        return user
    }
}
