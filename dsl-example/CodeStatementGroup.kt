package lupos.codegen

abstract class CodeStatementGroup() : ACodeBase() {
    val statements = mutableListOf<ACodeStatement>()
    fun copyInto(target: CodeStatementGroup, mapName: (String) -> String) {
        for (statement in statements) {
            statement.copyInto(target, mapName)
        }
    }

    override fun prepareImports(parentFile: CodeFile) {
        for (statement in statements) {
            statement.prepareImports(parentFile)
        }
    }

    fun statementIf(cond: CodeExpressionBuilder.() -> ACodeExpression, a: CodeFunctionBody.() -> Unit, b: CodeFunctionBody.() -> Unit): CodeIf {
        val statA = CodeFunctionBody()
        statA.a()
        val statB = CodeFunctionBody()
        statB.b()
        val stat = CodeIf(CodeExpressionBuilder().cond(), statA, statB)
        statements.add(stat)
        return stat
    }

    fun statementUse(name: String, init: CodeParameterContainer.() -> Unit, onEvent: (CodeReturnEvent) -> Unit) {
        val params = CodeParameterContainer()
        params.init()
        var found = false
        loop@ for (segment in registeredCodeSegments) {
            if (segment.name == name) {
                var flag = segment.parameterContainer.parameters.size == params.parameters.size
                var i = 0
                while (flag && i < segment.parameterContainer.parameters.size) {
                    flag = segment.parameterContainer.parameters[i] == params.parameters[i]
                    i++
                }
                if (flag) {
                    found = true
                    segment.generate(this, params) { it ->
                        onEvent(it)
                    }
                    break@loop
                }
            }
        }
        if (!found) {
            throw Exception("function not found")
        }
    }

    fun statementAssign(name: String, init: CodeExpressionBuilder.() -> ACodeExpression): CodeAssignment {
        val expression = CodeExpressionBuilder().init()
        val ass = CodeAssignment(CodeName(name), expression)
        statements.add(ass)
        return ass
    }

    fun statementAssign(v: CodeVariableDefinition, event: CodeReturnEvent): CodeAssignment {
        if (event.type != v.getType()) {
            throw Exception("incompatible types")
        }
        val ass = CodeAssignment(v.name, CodeVarRef(event.name.name, event.type))
        statements.add(ass)
        return ass
    }

    fun statementAssign(v: CodeVariableDefinition, init: CodeExpressionBuilder.() -> ACodeExpression): CodeAssignment {
        val expression = CodeExpressionBuilder().init()
        if (expression.resultType != v.getType()) {
            throw Exception("incompatible types")
        }
        val ass = CodeAssignment(v.name, expression)
        statements.add(ass)
        return ass
    }

    fun statementVar(v: CodeVariableDefinition): CodeVariableDefinition {
        statements.add(v)
        return v
    }

    fun statementVar(name: String, type: CodeType): CodeVariableDefinition {
        val v = CodeVariableDefinition(CodeName(name))
        v.type_ = type
        statements.add(v)
        return v
    }

    fun statementVal(v: CodeVariableDefinition, init: CodeExpressionBuilder.() -> ACodeExpression): CodeConstantDefinition {
        val exp = CodeExpressionBuilder().init()
        if (v.getType() != exp.resultType) {
            throw Exception("incompatible types")
        }
        val v2 = CodeConstantDefinition(v.name, exp)
        statements.add(v2)
        return v2
    }

    fun statementVal(name: String, init: CodeExpressionBuilder.() -> ACodeExpression): CodeConstantDefinition {
        val v = CodeConstantDefinition(CodeName(name), CodeExpressionBuilder().init())
        statements.add(v)
        return v
    }

    open fun generate(indention: String, out: StringBuilder) {
        for (statement in statements) {
            statement.generate(indention + "  ", out)
        }
    }
}
