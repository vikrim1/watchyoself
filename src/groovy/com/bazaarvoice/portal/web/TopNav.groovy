package com.bazaarvoice.portal.web

import portal.web.nav.NavGroup

class TopNav {
    static HOME = new NavGroup('nav.top.title.home', 0, 'home', 'index')
    static ADMIN = new NavGroup('nav.top.title.settings', 100, 'settings', 'app')
}
