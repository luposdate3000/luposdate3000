package lupos.s00misc

object Trace {
    inline fun trace(name: () -> String, action: () -> Any): Any {
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
