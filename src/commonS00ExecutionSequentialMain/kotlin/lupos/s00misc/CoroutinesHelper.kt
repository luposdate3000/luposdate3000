package lupos.s00misc

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import lupos.s04logicalOperators.Query

typealias CoroutinesHelperMutex = Int

object CoroutinesHelper {
    @JvmField
    val channelType = UNLIMITED

    inline fun run(crossinline action: suspend CoroutineScope.() -> Unit) = runBlocking {
        action()
    }

    inline fun runBlock(crossinline action: suspend CoroutineScope.() -> Unit) = runBlocking {
        action()
    }

    inline fun createLock() = 0
    inline fun runBlockWithLock(lock: CoroutinesHelperMutex, crossinline action: () -> Unit) = runBlocking {
        action()
    }
}
