package lupos.codegen

open class CodeFunctionBody() : CodeStatementGroup() {
    var returnType: CodeType? = null
    val parameterContainer = CodeParameterContainer()
    fun parameter(init: CodeParameterContainer.() -> Unit) {
        parameterContainer.init()
    }

    override fun prepareImports(parentFile: CodeFile) {
        super.prepareImports(parentFile)
        returnType?.addImport(parentFile)
        parameterContainer.prepareImports(parentFile)
    }

    fun statementReturn(): CodeReturnValue {
        val r = CodeReturnValue()
        statements.add(r)
        return r
    }

    fun statementReturn(event: CodeReturnEvent): CodeReturnValue {
        val ass = CodeReturnValue(CodeVarRef(event.name.name, event.type))
        statements.add(ass)
        return ass
    }

    fun statementReturn(init: CodeExpressionBuilder.() -> ACodeExpression): CodeReturnValue {
        val res = CodeExpressionBuilder().init()
        val r = CodeReturnValue(res)
        returnType = res.resultType
        statements.add(r)
        return r
    }
}
