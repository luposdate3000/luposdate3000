package lupos.codegen

class CodeAssignment(var name: CodeName, var expression: ACodeExpression) : ACodeStatement() {
    override fun copyInto(target: CodeFunctionBody, onEvent: CodeFunctionBody.(CodeReturnEvent) -> Unit, mapName: (String) -> String) {
        val exp = expression
        target.statementAssign(mapName(name.name)) { use(exp.copy(mapName)) }
    }

    override fun prepareImports(parentFile: CodeFile) {
        expression.prepareImports(parentFile)
    }

    override fun generate(indention: String, out: StringBuilder) {
        out.appendLine("$indention${name.generate()} = ${expression.generate()}")
    }
}
