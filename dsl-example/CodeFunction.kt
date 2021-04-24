package lupos.codegen

class CodeFunction(parentFunction: CodeFunction?, var name: CodeName) : CodeFunctionBody(parentFunction) {
    var returnType_: CodeType = codeTypes("Unit")
    var returnTypeSet = false
    fun setReturnType(type: CodeType) {
        if (returnTypeSet) {
            if (returnType_ != type) {
                throw Exception("incompatible types")
            }
        } else {
            returnTypeSet = true
            returnType_ = type
        }
    }

    override fun generate(indention: String, out: StringBuilder) {
        out.appendLine("${indention}fun ${name.generate()}(${parameterContainer.generate()}) : ${returnType_.generate()} {")
        super.generate(indention, out)
        out.appendLine("$indention}")
    }

    override fun prepareImports(parentFile: CodeFile) {
        super.prepareImports(parentFile)
        returnType_.addImport(parentFile)
    }
}
