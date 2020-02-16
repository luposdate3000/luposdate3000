package lupos.s00misc

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking


object CoroutinesHelper {

    inline fun run(crossinline action: suspend CoroutineScope.() -> Unit) {
        runBlocking {
            action()
        }
    }

}
