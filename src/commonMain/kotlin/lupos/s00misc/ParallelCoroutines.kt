package lupos.s00misc
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
object ParallelCoroutines {
	inline fun <T>runBlocking (crossinline action:suspend()->T):T{
return		kotlinx.coroutines.runBlocking{
			action()
		}
	}
	inline fun launch(crossinline action:suspend()->Unit):ParallelJob{
return             GlobalScope.launch(Dispatchers.Default){
                        action()
                }
        }
inline suspend fun delay(milliseconds:Int){
delay(milliseconds)
}
}
