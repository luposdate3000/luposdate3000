package lupos.codegen

class CodeFile(var name: String, var pkg: String) : ACodeBase() {
    val imports = mutableSetOf<String>()
    val clazzes = mutableListOf<CodeClazz>()
    override fun prepareImports(parentFile: CodeFile) {
        for (clazz in clazzes) {
            clazz.prepareImports(parentFile)
        }
    }

    fun clazz(name: String, init: CodeClazz.() -> Unit): CodeClazz {
        val c = CodeClazz(CodeName(name))
        c.init()
        clazzes.add(c)
        return c
    }

    fun generate(indention: String, out: StringBuilder) {
        for (clazz in clazzes) {
            clazz.generate(indention + "", out)
            out.appendLine()
        }
    }

    override fun toString(): String {
        val out = StringBuilder()
        prepareImports(this)
        out.appendLine("package $pkg")
        out.appendLine()
        for (i in imports) {
            out.appendLine("import $i")
        }
        out.appendLine()
        generate("", out)
        return out.toString()
    }
}
