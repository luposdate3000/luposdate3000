package lupos.s00misc

import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking


object CoroutinesHelper {

    val channelType = UNLIMITED
    inline fun run(crossinline action: suspend CoroutineScope.() -> Unit) {
        runBlocking {
            action()
        }
    }

}
