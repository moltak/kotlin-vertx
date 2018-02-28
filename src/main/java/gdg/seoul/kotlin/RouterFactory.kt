package gdg.seoul.kotlin

import io.vertx.reactivex.core.Vertx
import io.vertx.reactivex.ext.web.Router

interface RouterFactory {
    fun getInstance(vertx: Vertx, routeSpec: RouteSpec): Router
}