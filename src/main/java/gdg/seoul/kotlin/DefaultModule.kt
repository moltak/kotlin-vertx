package gdg.seoul.kotlin

import com.google.inject.AbstractModule
import com.google.inject.Provides
import io.vertx.reactivex.core.Vertx
import io.vertx.reactivex.core.http.HttpServer
import javax.inject.Singleton

class DefaultModule : AbstractModule() {
    override fun configure() {}

    @Provides @Singleton
    fun provideRouterFactory(): RouterFactory = RouterFactoryImpl()

    @Provides @Singleton
    fun provideRouteSpec(): RouteSpec = GetUserRouteSpec()

    @Provides @Singleton
    fun provideHttpServerFactory(
            routerFactory: RouterFactory,
            routeSpec: RouteSpec
    ): HttpServerFactory {
        return object : HttpServerFactory {
            override fun getInstance(vertx: Vertx): HttpServer {
                val router = routerFactory.getInstance(vertx, routeSpec)
                return vertx.createHttpServer().requestHandler(router::accept)
            }
        }
    }
}