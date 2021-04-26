package lupos.codegen

abstract class ACodeExpression(var resultType: CodeType) : ACodeBase() {
    abstract fun generate(): String
    abstract fun copy(mapName: (String) -> String): ACodeExpression
}
