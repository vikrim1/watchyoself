package com.bazaarvoice.portal

class MLProduct {

    static constraints = {
    }

    static hasMany = [offers: MLOffer]

    int productId
    String name
    String rtpalUrl
}
