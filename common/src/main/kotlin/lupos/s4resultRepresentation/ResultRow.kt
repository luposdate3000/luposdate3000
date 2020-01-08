package lupos.s4resultRepresentation

expect inline class ResultRow(val values: Any) {
    operator fun set(name: Variable, value: Value)
    operator fun get(name: Variable): Value
}
