package lupos.codegen

class CodeReturnEvent(val name: CodeName, val type: CodeType) : ACodeStatement() {
    override fun copyInto(target: CodeStatementGroup, onEvent: (CodeReturnEvent) -> Unit, mapName: (String) -> String) {
        throw Exception("dont call this")
    }

    override fun prepareImports(parentFile: CodeFile) {
        throw Exception("dont call this")
    }

    override fun generate(indention: String, out: StringBuilder) {
        throw Exception("dont call this")
    }
}
