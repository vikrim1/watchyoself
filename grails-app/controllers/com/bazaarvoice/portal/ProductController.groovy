package com.bazaarvoice.portal

import groovy.json.JsonBuilder

class ProductController {

    static allowedMethods = [index: 'GET']

    MiloService miloService

    def index() {}

    def addToWishList(){
        String productQuery = params.name
        String lat = params.lat
        String lng = params.lng

        WishList wishList = WishList.findOrSaveById(1)
        WYSProductQuery query = new WYSProductQuery(query: productQuery)
        wishList.queries.add(query)
        wishList.save(flush: true, failOnError: true)

        for (WYSProductQuery q : wishList.queries) {
            if (!q.miloProductsInfo) {
                def miloResponse = miloService.getProductData(q.query, lat as float, lng as float)

                List<MLProduct> result = new ArrayList<MLProduct>()
                for (def productJson : miloResponse.response.products) {
                    List<MLOffer> offers = new ArrayList<MLOffer>()
                    for (def offer : productJson.offers) {
                        MLOffer o = new MLOffer(
                                offerId: offer.offer_id,
                                merchantId: offer.merchant_id,
                                merchantName: offer.merchant_name,
                                price: offer.price as int,
                                prettyPrice: offer.pretty_price,
                                salePrice: offer.sale_price as int,
                                currentPrice: offer.current_price as int,
                                prettyCurrentPrice: offer.pretty_current_price
                        )
                        offers.add(o)
                    }


                    MLProduct p = new MLProduct(
                            name: productJson.name,
                            rtpalUrl: productJson.rtpal_url,
                            productId: productJson.product_id,
                            offers: offers
                    )
                    result.add(p)
                }
                q.miloProductsInfo = result
                q.save()
            }
        }

        render(status: 200)
    }

    def isNearby(){
        String lat = params.lat
        String lng = params.lng
        int radius = 5

        WishList wishList = WishList.findById(1)

        for (WYSProductQuery query : wishList.queries) {
            for (MLProduct product : query.miloProductsInfo) {
                def miloResponse =
                    miloService.getProductAvailability(product.offers, lat as float, lng as float, radius)
                if (miloResponse) {
                    response.setContentType('application/json')
                    response.outputStream << new JsonBuilder(miloResponse).toString()
                    return
                }
            }
        }
    }
}
