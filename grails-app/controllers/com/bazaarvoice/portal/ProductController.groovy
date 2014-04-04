package com.bazaarvoice.portal

import groovy.json.JsonBuilder

class ProductController {

    static allowedMethods = [index: 'GET']

    MiloService miloService

    def index() {
        render (status: 200)
    }

    def addToWishList() {
        String productQuery = params.name
        String lat = params.lat
        String lng = params.lng

        miloService.setLastLat(lat)
        miloService.setLastLong(lng)

        WishList wishList = WishList.findOrSaveById(1)
        WYSProductQuery query = new WYSProductQuery(query: productQuery).save()
        wishList.addToQueries(query)
        wishList.save(flush: true, failOnError: true)

        for (WYSProductQuery q : wishList.queries) {
            if (!q.miloProductsInfo) {
                def miloResponse = miloService.getProductData(q.query, lat as float, lng as float)

                List<MLProduct> result = new ArrayList<MLProduct>()
                for (def productJson : (miloResponse.response.products as List).take(10)) {
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
                        ).save()
                        offers.add(o)
                    }

                    MLProduct p = new MLProduct(
                            name: productJson.name,
                            rtpalUrl: productJson.rtpal_url,
                            productId: productJson.product_id,
                            offers: offers
                    ).save()
                    result.add(p)
                }
                q.miloProductsInfo = result
                q.save()
            }
        }

        render (status: 200)
        return
    }

    def searchWishList() {
        String lat = params.lat
        String lng = params.lng
        int radius = 1
        miloService.setLastLat(lat)
        miloService.setLastLong(lng)

        WishList wishList = WishList.findById(1)
        if (!wishList) {
            render(status: 404, text: "No wishlist found")
            return
        }

        for (WYSProductQuery query : wishList.queries) {
            for (MLProduct product : query.miloProductsInfo) {
                for (MLOffer offer : product.offers) {
                    def miloResponse =
                        miloService.getProductAvailability(offer.offerId, lat as float, lng as float, radius)
                    if (miloResponse && (miloResponse.response.availabilities as List).size() > 0) {
                        HashMap responseJson = miloResponse.response.availabilities[0].results[0]
                        if (responseJson == null) continue
                        responseJson.put("product_name", product.name)
                        responseJson.put("product_id", product.id)
                        response.setContentType('application/json')
                        response.outputStream << new JsonBuilder(responseJson).toString()
                        return
                    }
                }
            }
        }

        response.setContentType('application/json')
        response.outputStream << new JsonBuilder([:]).toString()
        return
    }

    def lastLocation(){
        def lastLat = miloService.getLastLat()
        def lastLong = miloService.getLastLong()
        def a = [url: "https://www.google.com/maps/embed/v1/search?key=AIzaSyAY1KUoh2rYogfCZFm_CF78nqk2fKk7oA0&q=store&center=${lastLat},${lastLong}&zoom=16"]
        response.setContentType('application/json')
        response.outputStream << new JsonBuilder(a).toString()
    }

    def map(){
    }
}
