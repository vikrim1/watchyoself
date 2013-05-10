package com.bazaarvoice.portal.controller

import com.bazaarvoice.portal.web.TopNav
import portal.web.nav.LeftNavContributor
import portal.web.nav.NavDescriptor

class SettingsController {

    static allowedMethods = [app: 'GET', user: 'GET']

    static navigation = [
            new NavDescriptor('app', TopNav.ADMIN, new LeftNavContributor('nav.left.title.settings.app', 0)),
            new NavDescriptor('user', TopNav.ADMIN, new LeftNavContributor('nav.left.title.settings.user', 10))
    ]

    def app() {}

    def user() {}
}
