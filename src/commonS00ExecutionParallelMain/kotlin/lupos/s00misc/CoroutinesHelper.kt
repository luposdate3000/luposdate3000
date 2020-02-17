package lupos.s00misc
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.*
import kotlin.coroutines.*
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.sync.Mutex

object CoroutinesHelper {
    val channelType=2
    fun run(action: suspend CoroutineScope.() -> Unit){
	GlobalScope.launch{
		action()
	}
    }

    inline fun runBlock(crossinline action: suspend CoroutineScope.() -> Unit) = runBlocking {
	action()
    }

inline fun runBlockWithLock(lock:Mutex,crossinline action: () -> Unit)  =	runBlocking {
		try{
			lock.lock()
			action()
		}finally{
			lock.unlock()
		}
}

}

