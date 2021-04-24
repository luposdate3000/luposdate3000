package lupos.codegen

class CodeFunction(var name: CodeName) : CodeFunctionBody() {
    override fun generate(indention: String, out: StringBuilder) {
        if (returnType == null) {
            returnType = codeTypes("Unit")
        }
        out.appendLine("${indention}fun ${name.generate()}(${parameterContainer.generate()}) : ${returnType!!.generate()} {")
        super.generate(indention, out)
        out.appendLine("$indention}")
    }
}
