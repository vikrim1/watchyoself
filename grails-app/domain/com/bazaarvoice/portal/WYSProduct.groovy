package com.bazaarvoice.portal

class WYSProduct {

    static constraints = {
        productId nullable: true
        starRating nullable: true
        reviewHighlight nullable: true
        miloProductInfo nullable: true
    }

    String name
    String productId
    String starRating
    String reviewHighlight
    MLProduct miloProductInfo
}
