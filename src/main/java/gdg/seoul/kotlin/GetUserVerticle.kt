package gdg.seoul.kotlin

import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.vertx.core.Future
import io.vertx.reactivex.core.eventbus.EventBus
import java.util.concurrent.TimeUnit

class GetUserVerticle : GuiceAbstractVerticle() {
    override fun start(startFuture: Future<Void>) {
        super.start()

        vertx.eventBus().consumer<String>("GET_USER") { message ->
            Single.just("Hello")
                    .subscribeBy(
                            onSuccess = { data ->
                                message.reply("$data ${message.body()}")
                            },
                            onError = {
                                message.reply("error")
                            }
                    )
        }

        startFuture.complete()
    }
}