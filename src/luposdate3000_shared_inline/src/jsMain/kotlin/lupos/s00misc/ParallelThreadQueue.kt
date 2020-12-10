package lupos.s00misc

internal actual class ParallelThreadQueue<T> {
    var queue = mutableListOf<T>()
    var terminalValue: T

    actual constructor(terminationValue: T) {
        terminalValue = terminationValue
    }

    actual inline fun send(value: T) {
        queue.add(value)
    }

    actual inline fun close() {
        queue.clear()
    }

    actual inline fun receive(): T {
        if (queue.size > 0) {
            return queue.removeAt(0)
        }
        return terminalValue
    }
}
