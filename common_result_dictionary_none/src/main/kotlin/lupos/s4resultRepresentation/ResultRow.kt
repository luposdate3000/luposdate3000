package lupos.s4resultRepresentation

actual inline class ResultRow(val values: Any) {
    actual operator fun set(name: Variable, value: Value) {
        (values as MutableMap<Variable, Value>)[name] = value
    }

    actual operator fun get(name: Variable): Value {
        return (values as MutableMap<Variable, Value>)[name]!!
    }

    override fun toString(): String {
        return values.toString()
    }
}