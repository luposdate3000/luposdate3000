package lupos.codegen

@CodeDSLMarker
class CodeExpressionBuilder() {
    fun expVal(type: CodeType, value: String): CodeVal {
        return CodeVal(type, value)
    }

    fun expRef(v: CodeVariableDefinition): CodeVarRef {
        return CodeVarRef(v.name.name, v.getType())
    }

    fun expRef(v: CodeConstantDefinition): CodeVarRef {
        return CodeVarRef(v.name.name, v.expression.resultType)
    }

    fun expRef(name: String, type: CodeType): CodeVarRef {
        return CodeVarRef(name, type)
    }

    fun expDiv(a: CodeExpressionBuilder.() -> ACodeExpression, b: CodeExpressionBuilder.() -> ACodeExpression): CodePrimitive {
        val ax = CodeExpressionBuilder().a()
        val bx = CodeExpressionBuilder().b()
        return CodePrimitive(ax.resultType, "/", ax, bx)
    }

    fun expEq(a: CodeExpressionBuilder.() -> ACodeExpression, b: CodeExpressionBuilder.() -> ACodeExpression): CodePrimitive {
        val ax = CodeExpressionBuilder().a()
        val bx = CodeExpressionBuilder().b()
        return CodePrimitive(codeTypes("Boolean"), "==", ax, bx)
    }
}
