package lupos.codegen

class CodeParamDefinition(var name: CodeName) : ACodeStatement() {
    override fun copyInto(target: CodeStatementGroup, mapName: (String) -> String) {
        throw Exception("dont call this")
    }

    override fun equals(other: Any?): Boolean {
        return other is CodeParamDefinition && other.getType() == getType()
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
        type_?.addImport(parentFile)
    }

    override fun generate(indention: String, out: StringBuilder) {
        throw Exception("dont call this")
    }

    fun generate(): String {
        if (expression != null) {
            return "${name.generate()} : ${expression!!.resultType.generate()} = ${expression!!.generate()}"
        } else {
            return "${name.generate()} : ${type_!!.generate()}"
        }
    }
}
