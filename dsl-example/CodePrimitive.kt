package lupos.codegen

class CodePrimitive(resultType: CodeType, var symbol: String, var a: ACodeExpression, var b: ACodeExpression) : ACodeExpression(resultType) {
    override fun copy(mapName: (String) -> String): ACodeExpression {
        return CodePrimitive(resultType, symbol, a.copy(mapName), b.copy(mapName))
    }

    override fun generate(): String {
        return "${a.generate()} $symbol ${b.generate()}"
    }

    override fun prepareImports(parentFile: CodeFile) {
        a.prepareImports(parentFile)
        b.prepareImports(parentFile)
        resultType.addImport(parentFile)
    }
}
