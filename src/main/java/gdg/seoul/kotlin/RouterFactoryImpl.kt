package gdg.seoul.kotlin

import io.vertx.reactivex.core.Vertx
import io.vertx.reactivex.ext.web.Router

class RouterFactoryImpl : RouterFactory {
    override fun getInstance(
            vertx: Vertx,
            routeSpec: RouteSpec
    ): Router {
        return Router.router(vertx).apply {
            listOf(routeSpec).forEach { routeSpec ->
                get("/user/:name").handler(routeSpec.requestHandler(vertx))
            }
        }
    }
}