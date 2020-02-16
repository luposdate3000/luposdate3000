package lupos.s00misc

import kotlinx.coroutines.*


object CoroutinesHelper {

    inline fun run(crossinline action: suspend CoroutineScope.() -> Unit) {
        runBlocking {
            action()
        }
    }

}
