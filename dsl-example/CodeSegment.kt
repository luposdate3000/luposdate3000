package lupos.codegen

class CodeSegment(val name: String) : CodeStatementGroup() {
    val parameterContainer = CodeParameterContainer()

    fun parameter(init: CodeParameterContainer.() -> Unit) {
        parameterContainer.init()
    }

    fun generate(target: CodeFunctionBody, params: CodeParameterContainer, onEvent: CodeFunctionBody.(CodeReturnEvent) -> Unit) {
        copyInto(
            target, onEvent,
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

    override fun prepareImports(parentFile: CodeFile) {
        throw Exception("dont call this")
    }
}
