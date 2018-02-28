package gdg.seoul.kotlin

import io.vertx.core.Handler
import io.vertx.reactivex.core.Vertx
import io.vertx.reactivex.ext.web.RoutingContext

interface RouteSpec {
    fun requestHandler(vertx: Vertx): Handler<RoutingContext>
}