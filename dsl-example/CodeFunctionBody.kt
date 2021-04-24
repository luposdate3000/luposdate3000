package lupos.codegen

open class CodeFunctionBody() : CodeStatementGroup() {
    var returnType: CodeType? = null
    val parameterContainer = CodeParameterContainer()
    fun parameter(init: CodeParameterContainer.() -> Unit) {
        parameterContainer.init()
    }

    fun statementUse(name: String, init: CodeParameterContainer.() -> Unit, onEvent: CodeFunctionBody.(CodeReturnEvent) -> Unit) {
        val params = CodeParameterContainer()
        params.init()
        var found = false
        loop@ for (segment in registeredCodeSegments) {
            if (segment.name == name) {
                var flag = segment.parameterContainer.parameters.size == params.parameters.size
                var i = 0
                while (flag && i < segment.parameterContainer.parameters.size) {
                    flag = segment.parameterContainer.parameters[i] == params.parameters[i]
                    i++
                }
                if (flag) {
                    found = true
                    segment.generate(this, params, onEvent)
                    break@loop
                }
            }
        }
        if (!found) {
            throw Exception("function not found")
        }
    }

    override fun prepareImports(parentFile: CodeFile) {
        super.prepareImports(parentFile)
        returnType?.addImport(parentFile)
        parameterContainer.prepareImports(parentFile)
    }

    fun statementReturn(): CodeReturnValue {
        val r = CodeReturnValue()
        returnType = codeTypes("Unit")
        statements.add(r)
        return r
    }

    fun statementReturn(event: CodeReturnEvent): CodeReturnValue {
        val ass = CodeReturnValue(CodeVarRef(event.name.name, event.type))
        returnType = event.type
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
