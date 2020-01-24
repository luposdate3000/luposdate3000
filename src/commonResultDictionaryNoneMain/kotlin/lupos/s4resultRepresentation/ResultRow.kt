package lupos.s4resultRepresentation

inline class ResultRow(val values: Any) {
    operator fun set(name: Variable, value: Value) {
        (values as MutableMap<Variable, Value>)[name] = value
    }

    operator fun get(name: Variable): Value {
        return (values as MutableMap<Variable, Value>)[name]!!
    }

    override fun toString(): String {
        return values.toString()
    }
}
