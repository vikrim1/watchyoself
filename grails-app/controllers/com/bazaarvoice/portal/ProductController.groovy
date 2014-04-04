package com.bazaarvoice.portal

class ProductController {

    static allowedMethods = [index: 'GET']

    MiloService miloService

    def index() {}

    def addToWishList(){
        String productName = params.id
        String lat = params.lat
        String lng = params.lng

        WishList wishList = WishList.findOrCreateById(1)
        WYSProduct product = new WYSProduct(name: productName)
        wishList.products.add(product)
        wishList.save(flush: true, failOnError: true)

        for (WYSProduct p : wishList.products) {
            if (!p.miloProductInfo) {
                def miloResponse = miloService.getProductData(p.name, lat as float, lng as float)
                // leo todo parse response
                p.miloProductInfo = new MLProduct()
            }
        }
    }

    def isNearby(){
        String lat = params.lat
        String lng = params.lng
        int radius = 5

        WishList wishList = WishList.findById(1)

        for (WYSProduct product : wishList.products) {
            def miloResponse =
                miloService.getProductAvailability(product.miloProductInfo.offers, lat as float, lng as float, radius)

        }

        //return null or nearby locations + product + price
    }
}
