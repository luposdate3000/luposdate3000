package lupos.codegen

class CodeClazz(var name: CodeName) : ACodeBase() {
    val functions = mutableListOf<CodeFunction>()
    override fun prepareImports(parentFile: CodeFile) {
        for (function in functions) {
            function.prepareImports(parentFile)
        }
    }

    fun function(name: String, init: CodeFunction.() -> Unit): CodeFunction {
        val func = CodeFunction(CodeName(name))
        func.init()
        functions.add(func)
        return func
    }

    fun generate(indention: String, out: StringBuilder) {
        out.appendLine("${indention}class ${name.generate()} {")
        for (function in functions) {
            function.generate(indention + "  ", out)
            out.appendLine()
        }
        out.appendLine("$indention}")
    }
}
