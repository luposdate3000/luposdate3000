package lupos.s0data

expect class VariableName {
    constructor(name: String)
}

expect class ResultRow {
    constructor()

    fun setVariable(name: VariableName, value: String)
    fun getVariable(name: VariableName): String?
}