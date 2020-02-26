package lupos.s00misc
import lupos.s00misc.EOperatorID

object Trace {
    inline fun <T> trace(name: () -> String, action: () -> T): T {
        return action()
    }

    fun start(obj: Any) {
    }

    fun start(name: String) {
    }

    fun stop(obj: Any) {
    }

    fun stop(name: String) {
    }

    fun print() {
    }
}
