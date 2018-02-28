package gdg.seoul.kotlin

import io.reactivex.rxkotlin.subscribeBy
import io.vertx.core.Handler
import io.vertx.reactivex.core.Vertx
import io.vertx.reactivex.ext.web.RoutingContext

class GetUserRouteSpec : RouteSpec {
    override fun requestHandler(vertx: Vertx): Handler<RoutingContext> {
        return Handler { routingContext ->

            val name = routingContext.pathParam("name")

            vertx.eventBus().rxSend<String>("GET_USER", name)
                    .subscribeBy(
                            onSuccess = { message ->
                                routingContext.response().apply {
                                    statusCode = 200
                                }.end(message.body())

                            },
                            onError = {
                                routingContext.fail(500)
                            }
                    )
        }
    }
}