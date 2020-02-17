package lupos.s00misc
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.*
import kotlin.coroutines.*
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED

object CoroutinesHelper {

    val channelType=2
    fun run(action: suspend CoroutineScope.() -> Unit){
	println("prepare on ${Thread.currentThread().name}")
	GlobalScope.launch{
		println("launched on ${Thread.currentThread().name}")
		action()
		println("finished on ${Thread.currentThread().name}")
	}
	println("finish on ${Thread.currentThread().name}")
    }

    inline fun runBlock(crossinline action: suspend CoroutineScope.() -> Unit) = runBlocking {
println("blocking start on ${Thread.currentThread().name}")
	action()
println("blocking finished on ${Thread.currentThread().name}")
    }

}

