package com.bazaarvoice.portal

class WYSProductQuery {

    static constraints = {
        productId nullable: true
        starRating nullable: true
        reviewHighlight nullable: true
    }

    static hasMany = [miloProductsInfo: MLProduct]

    String query
    String productId
    String starRating
    String reviewHighlight
}
