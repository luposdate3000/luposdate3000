package lupos.s06resultRepresentation

import lupos.s06resultRepresentation.ResultSet
import lupos.s06resultRepresentation.Value
import lupos.s06resultRepresentation.Variable


class ResultRow() {
    val values = mutableMapOf<Variable, Value>()
    operator fun set(name: Variable, value: Value) {
        values[name] = value
    }

    operator fun get(name: Variable): Value {
        return values[name]!!
    }

    override fun toString(): String {
        return values.toString()
    }
}
