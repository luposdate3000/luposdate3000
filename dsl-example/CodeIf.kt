package lupos.codegen

class CodeIf(var cond: ACodeExpression, var a: CodeFunctionBody, var b: CodeFunctionBody) : ACodeStatement() {
    override fun copyInto(target: CodeStatementGroup, mapName: (String) -> String) {
        val a2 = CodeFunctionBody()
        val b2 = CodeFunctionBody()
        val c2 = cond.copy(mapName)
        a.copyInto(a2, mapName)
        b.copyInto(b2, mapName)
        target.statements.add(CodeIf(c2, a2, b2))
    }

    override fun prepareImports(parentFile: CodeFile) {
        cond.prepareImports(parentFile)
        a.prepareImports(parentFile)
        b.prepareImports(parentFile)
    }

    override fun generate(indention: String, out: StringBuilder) {
        out.appendLine("${indention}if (${cond.generate()}) {")
        a.generate(indention + "  ", out)
        out.appendLine("$indention}else {")
        b.generate(indention + "  ", out)
        out.appendLine("$indention")
    }
}
