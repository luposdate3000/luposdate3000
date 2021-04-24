package lupos.codegen

class CodeVariableDefinition(var name: CodeName) : ACodeStatement(), ICodeVariableOrConstantDefinition {
    override fun copyInto(target: CodeFunctionBody, onEvent: CodeFunctionBody.(CodeReturnEvent) -> Unit, mapName: (String) -> String) {
        throw Exception("dont call this")
    }

    var expression: ACodeExpression? = null
    var type_: CodeType? = null
    fun getType(): CodeType {
        if (expression != null) {
            return expression!!.resultType
        } else {
            return type_!!
        }
    }

    override fun prepareImports(parentFile: CodeFile) {
        getType().addImport(parentFile)
    }

    override fun generate(indention: String, out: StringBuilder) {
        out.appendLine("$indention${generate()}")
    }

    fun generate(): String {
        if (expression != null) {
            return "var ${name.generate()} : ${expression!!.resultType.generate()} = ${expression!!.generate()}"
        } else {
            return "var ${name.generate()} : ${type_!!.generate()}"
        }
    }
}
