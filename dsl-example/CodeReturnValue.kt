package lupos.codegen

class CodeReturnValue(val expression: ACodeExpression? = null) : ACodeStatement() {
    override fun copyInto(target: CodeFunctionBody, onEvent: CodeFunctionBody.(CodeReturnEvent) -> Unit, mapName: (String) -> String) {
        target.statements.add(CodeReturnValue(expression?.copy(mapName)))
    }

    override fun prepareImports(parentFile: CodeFile) {
        expression?.prepareImports(parentFile)
    }

    override fun generate(indention: String, out: StringBuilder) {
        if (expression != null) {
            out.appendLine("${indention}return ${expression.generate()}")
        } else {
            out.appendLine("${indention}return")
        }
    }
}
