package gdg.seoul.kotlin

import io.vertx.reactivex.core.Vertx
import io.vertx.reactivex.core.http.HttpServer

interface HttpServerFactory {
    fun getInstance(vertx: Vertx): HttpServer
}