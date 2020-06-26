package lupos.s00misc

import kotlin.jvm.JvmField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import lupos.s00misc.Coverage
import lupos.s00misc.Lock

object CoroutinesHelperParallel {
    @JvmField
    val channelType = 2
    /*inline*/ fun run(/*crossinline*/ action: suspend CoroutineScope.() -> Unit) = GlobalScope.launch {
        action()
    }

    /*inline*/ fun runBlock(/*crossinline*/ action: suspend CoroutineScope.() -> Unit) = runBlocking {
        action()
    }

    /*inline*/ fun createLock() = Lock()
}
