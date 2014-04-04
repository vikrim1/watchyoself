package com.bazaarvoice.portal

class WYSProductQuery {

    static constraints = {
        productId nullable: true
        starRating nullable: true
        reviewHighlight nullable: true
    }

    String query
    String productId
    String starRating
    String reviewHighlight
    List<MLProduct> miloProductsInfo = new ArrayList<MLProduct>()
}
