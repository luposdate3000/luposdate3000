package lupos.codegen

class CodeIf(var cond: ACodeExpression, var a: CodeFunctionBody, var b: CodeFunctionBody) : ACodeStatement() {
    override fun copyInto(target: CodeFunctionBody, onEvent: CodeFunctionBody.(CodeReturnEvent) -> Unit, mapName: (String) -> String) {
        val c = cond.copy(mapName)
        val s = target.statementIf({ use(c) }, {}, {})
        a.copyInto(s.a, onEvent, mapName)
        b.copyInto(s.b, onEvent, mapName)
    }

    override fun prepareImports(parentFile: CodeFile) {
        cond.prepareImports(parentFile)
        a.prepareImports(parentFile)
        b.prepareImports(parentFile)
    }

    override fun generate(indention: String, out: StringBuilder) {
        out.appendLine("${indention}if (${cond.generate()}) {")
        a.generate(indention, out)
        out.appendLine("$indention} else {")
        b.generate(indention, out)
        out.appendLine("$indention}")
    }
}
