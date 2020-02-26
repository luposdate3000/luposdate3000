package lupos.s00misc
import lupos.s00misc.EOperatorID

import kotlin.coroutines.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex


typealias CoroutinesHelperMutex = Mutex

object CoroutinesHelper {
    val channelType = 2
    inline fun run(crossinline action: suspend CoroutineScope.() -> Unit) = GlobalScope.launch {
        action()
    }

    inline fun runBlock(crossinline action: suspend CoroutineScope.() -> Unit) = runBlocking {
        action()
    }

    inline fun createLock() = Mutex()

    inline fun runBlockWithLock(lock: CoroutinesHelperMutex, crossinline action: () -> Unit) = runBlocking {
        try {
            lock.lock()
            action()
        } finally {
            lock.unlock()
        }
    }

}

