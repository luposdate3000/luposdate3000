package lupos.codegen

@CodeDSLMarker
class CodeParameterContainer {
    val parameters = mutableListOf<CodeParamDefinition>()
    fun generate(): String {
        return parameters.map { it.generate() }.joinToString()
    }

    fun add(v: CodeVariableDefinition): CodeParamDefinition {
        val param = CodeParamDefinition(v.name)
        param.type_ = v.getType()
        parameters.add(param)
        return param
    }

    fun add(name: String, type: CodeType): CodeParamDefinition {
        val param = CodeParamDefinition(CodeName(name))
        param.type_ = type
        parameters.add(param)
        return param
    }

    fun add(name: String, type: CodeType, value: String): CodeParamDefinition {
        val param = CodeParamDefinition(CodeName(name))
        param.expression = CodeVal(type, value)
        parameters.add(param)
        return param
    }

    fun prepareImports(parentFile: CodeFile) {
        for (parameter in parameters) {
            parameter.prepareImports(parentFile)
        }
    }
}
