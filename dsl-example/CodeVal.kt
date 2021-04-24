package lupos.codegen

class CodeVal(type: CodeType, var value: String) : ACodeExpression(type) {
    override fun copy(mapName: (String) -> String): ACodeExpression {
        return CodeVal(resultType, value)
    }

    override fun prepareImports(parentFile: CodeFile) {
        resultType.addImport(parentFile)
    }

    override fun generate(): String {
        return value
    }
}
