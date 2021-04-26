package lupos.codegen

class CodeConstantDefinition(var name: CodeName, var expression: ACodeExpression) : ACodeStatement(), ICodeVariableOrConstantDefinition {
    override fun copyInto(target: CodeFunctionBody, onEvent: CodeFunctionBody.(CodeReturnEvent) -> Unit, mapName: (String) -> String) {
        val exp = expression
        target.statementVal(mapName(name.name)) { use(exp.copy(mapName)) }
    }

    override fun prepareImports(parentFile: CodeFile) {
        expression.resultType.addImport(parentFile)
    }

    override fun generate(indention: String, out: StringBuilder) {
        out.appendLine("$indention${generate()}")
    }

    fun generate(): String {
        return "val ${name.generate()} : ${expression.resultType.generate()} = ${expression.generate()}"
    }
}
