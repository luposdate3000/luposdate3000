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

@CodeDSLMarker
class CodeExpressionBuilder() {
    fun use(res: ACodeExpression): ACodeExpression {
        return res
    }

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
