package lupos.codegen

class CodeVarRef(var name: String, var type: CodeType) : ACodeExpression(type) {
    override fun copy(mapName: (String) -> String): ACodeExpression {
        return CodeVarRef(mapName(name), type)
    }

    override fun generate(): String {
        return name
    }

    override fun prepareImports(parentFile: CodeFile) {
        resultType.addImport(parentFile)
    }
}
