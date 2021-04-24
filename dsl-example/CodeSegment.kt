package lupos.codegen

class CodeSegment(val name: String) : CodeStatementGroup() {
    val parameterContainer = CodeParameterContainer()
    fun statementEvent(name: String, type: CodeType): CodeReturnEvent {
        val r = CodeReturnEvent(CodeName(name), type)
        statements.add(r)
        return r
    }

    fun statementEvent(v: CodeVariableDefinition): CodeReturnEvent {
        val r = CodeReturnEvent(v.name, v.getType())
        statements.add(r)
        return r
    }

    fun parameter(init: CodeParameterContainer.() -> Unit) {
        parameterContainer.init()
    }

    fun generate(target: CodeStatementGroup, params: CodeParameterContainer, onEvent: (CodeReturnEvent) -> Unit) {
        for (statement in statements) {
            if (statement is CodeReturnEvent) {
                onEvent(statement)
            } else {
                statement.copyInto(
                    target,
                    { name ->
                        var res = name
                        for (i in 0 until parameterContainer.parameters.size) {
                            if (parameterContainer.parameters[i].name.name == name) {
                                res = params.parameters[i].name.name
                            }
                        }
                        res
                    }
                )
            }
        }
    }

    override fun prepareImports(parentFile: CodeFile) {
        throw Exception("dont call this")
    }
}
