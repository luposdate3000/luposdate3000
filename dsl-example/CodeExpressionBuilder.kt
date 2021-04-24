package lupos.codegen

class CodeExpressionBuilder() {
    fun expVal(type: CodeType, value: String): ACodeExpression {
        return CodeVal(type, value)
    }

    fun expVar(v: CodeVariableDefinition): ACodeExpression {
        return CodeVarRef(v.name.name, v.getType())
    }

    fun expVar(name: String, type: CodeType): ACodeExpression {
        return CodeVarRef(name, type)
    }

    fun expDiv(a: CodeExpressionBuilder.() -> ACodeExpression, b: CodeExpressionBuilder.() -> ACodeExpression): ACodeExpression {
        val ax = CodeExpressionBuilder().a()
        val bx = CodeExpressionBuilder().b()
        return CodePrimitive(ax.resultType, "/", ax, bx)
    }

    fun expEq(a: CodeExpressionBuilder.() -> ACodeExpression, b: CodeExpressionBuilder.() -> ACodeExpression): ACodeExpression {
        val ax = CodeExpressionBuilder().a()
        val bx = CodeExpressionBuilder().b()
        return CodePrimitive(codeTypes("Boolean"), "==", ax, bx)
    }
}
