package com.bazaarvoice.portal.controller

import com.bazaarvoice.portal.web.TopNav
import portal.web.nav.NavDescriptor

class HomeController {

    static allowedMethods = [index: 'GET']

    static navigation = [
            new NavDescriptor('index', TopNav.HOME)
    ]

    def index() {}
}
