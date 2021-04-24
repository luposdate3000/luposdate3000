package lupos.codegen

abstract class ACodeStatement() : ACodeBase() {
    abstract fun generate(indention: String, out: StringBuilder)
    abstract fun copyInto(target: CodeFunctionBody, onEvent: CodeFunctionBody.(CodeReturnEvent) -> Unit, mapName: (String) -> String)
}
