package gdg.seoul.kotlin

import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.vertx.core.Future
import io.vertx.reactivex.core.http.HttpServer
import javax.inject.Inject

class ListenVerticle : GuiceAbstractVerticle() {
    @Inject private lateinit var httpServerFactory: HttpServerFactory

    private lateinit var httpServer: HttpServer
    private lateinit var disposable: Disposable

    override fun start(startFuture: Future<Void>) {
        super.start()

        httpServer = httpServerFactory.getInstance(vertx)
        disposable = httpServer.rxListen(3003).subscribeBy(
                onSuccess = { result ->
                    println("Server is listening on port [${result.actualPort()}]")

                    startFuture.complete()
                },
                onError = {
                    it.printStackTrace()

                    startFuture.fail(it)
                }
        )
    }

    override fun stop(stopFuture: Future<Void>) {
        httpServer.rxClose().subscribeBy(
                onComplete = {
                    disposable.dispose()

                    stopFuture.complete()
                },
                onError = {
                    it.printStackTrace()

                    stopFuture.fail(it)
                }
        )
    }
}