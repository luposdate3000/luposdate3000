package lupos.s4resultRepresentation
import lupos.s06resultRepresentation.ResultSet

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
