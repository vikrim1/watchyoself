package com.bazaarvoice.portal

class ProductController {

    static allowedMethods = [index: 'GET']

    def index() {}

    def addToWishList(){
        String productName = params.id
        WishList wishList = new WishList()
        wishList.productName = productName
        wishList.save(flush: true, failOnError: true)
    }

    def isNearby(){
        String location = params.id
        // call service to call milo

        //return null or nearby locations + product + price
    }
}
