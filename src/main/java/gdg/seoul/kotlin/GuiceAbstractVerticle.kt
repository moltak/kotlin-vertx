package gdg.seoul.kotlin

import com.google.inject.Guice
import com.google.inject.Module
import io.vertx.core.Future
import io.vertx.reactivex.core.AbstractVerticle

abstract class GuiceAbstractVerticle : AbstractVerticle() {
    override fun start() {
        super.start()

        val modules = listOf<Module>(DefaultModule())
        Guice.createInjector(modules).injectMembers(this)
    }
}