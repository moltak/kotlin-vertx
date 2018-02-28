package gdg.seoul.kotlin

import io.reactivex.Completable
import io.reactivex.rxkotlin.subscribeBy
import io.vertx.core.DeploymentOptions

@Suppress("unused")
class LauncherVerticle : GuiceAbstractVerticle() {
    override fun start() {
        super.start()

        val listenVerticle = vertx.rxDeployVerticle(
                ListenVerticle::class.java.name,
                DeploymentOptions()).toCompletable()

        val getUserVerticle = vertx.rxDeployVerticle(
                GetUserVerticle::class.java.name,
                DeploymentOptions()).toCompletable()

        Completable.merge(listOf(
                listenVerticle,
                getUserVerticle)
        ).subscribeBy(
                onComplete = {
                    println("LauncherVerticle was completed")
                },
                onError = {
                    it.printStackTrace()
                }
        )
    }
}