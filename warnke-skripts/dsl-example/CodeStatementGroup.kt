/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.codegen

abstract class CodeStatementGroup(var parentFunction: CodeFunction?) : ACodeBase() {
    val statements = mutableListOf<ACodeStatement>()
    fun copyInto(target: CodeFunctionBody, onEvent: CodeFunctionBody.(CodeReturnEvent) -> Unit, mapName: (String) -> String) {
        for (statement in statements) {
            if (statement is CodeReturnEvent) {
                target.onEvent(statement)
            } else {
                statement.copyInto(target, onEvent, mapName)
            }
        }
    }

    fun statementEvent(name: String, type: CodeType): CodeReturnEvent {
        val r = CodeReturnEvent(CodeName(name), type)
        statements.add(r)
        return r
    }

    fun statementEvent(v: CodeVariableDefinition): CodeReturnEvent {
        val r = CodeReturnEvent(v.name, v.getType())
        statements.add(r)
        return r
    }

    override fun prepareImports(parentFile: CodeFile) {
        for (statement in statements) {
            statement.prepareImports(parentFile)
        }
    }

    fun statementIf(cond: CodeExpressionBuilder.() -> ACodeExpression, a: CodeFunctionBody.() -> Unit, b: CodeFunctionBody.() -> Unit): CodeIf {
        val statA = CodeFunctionBody(parentFunction)
        statA.a()
        val statB = CodeFunctionBody(parentFunction)
        statB.b()
        val stat = CodeIf(CodeExpressionBuilder().cond(), statA, statB)
        statements.add(stat)
        return stat
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
