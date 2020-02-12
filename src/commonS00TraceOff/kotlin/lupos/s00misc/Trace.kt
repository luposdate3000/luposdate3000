package lupos.s00misc

object Trace {
    inline fun trace(name: () -> String, action: () -> Any): Any {
        return action()
    }

    inline fun start(obj: Any) {
    }

    inline fun start(name: String) {
    }

    inline fun stop(obj: Any) {
    }

    inline fun stop(name: String) {
    }

    inline fun print() {
    }
}
