package lupos.s00misc

object SanityCheck{
inline fun helper(action:()->Unit){}
inline fun<T> helperInit(action:()->T):T?=null
inline fun check(value:()->Boolean){}
inline fun checkFalse(value:()->Boolean){}
inline fun<T> checkEQ(a:()->T,b:()->T){}
inline fun<T> checkNEQ(a:()->T,b:()->T){}
inline fun<T> checkNULL(value:()->T){}
inline fun<T> checkNNULL(value:()->T){}
inline fun checkUnreachable():Nothing=throw Exception("this should be unreachable")
}
