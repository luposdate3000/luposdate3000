package lupos.codegen

abstract class ACodeStatement() : ACodeBase() {
    abstract fun generate(indention: String, out: StringBuilder)
    abstract fun copyInto(target: CodeStatementGroup, onEvent: (CodeReturnEvent) -> Unit, mapName: (String) -> String)
}
