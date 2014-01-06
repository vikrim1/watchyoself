package com.bazaarvoice.portal

import portal.auth.PortalUser

class User implements PortalUser {

	String username
	String password
    String fullName
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

    @Override
    String getDisplayName() {
        return fullName == null ? username : fullName
    }

    static constraints = {
		username blank: false, unique: true
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}

    String getPassword() {
        return 'unused'
    }

    void setPassword(String ignored) {
        // no-op.  We don't manage passwords.  They are handled by the Portal SSO.
    }

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}
}
