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

open class CodeFunctionBody(parentFunction: CodeFunction?) : CodeStatementGroup(parentFunction) {
    val parameterContainer = CodeParameterContainer()
    fun parameter(init: CodeParameterContainer.() -> Unit) {
        parameterContainer.init()
    }

    fun statementUse(name: String, init: CodeParameterContainer.() -> Unit, onEvent: CodeFunctionBody.(CodeReturnEvent) -> Unit) {
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
                    segment.generate(this, params, onEvent)
                    break@loop
                }
            }
        }
        if (!found) {
            throw Exception("function not found")
        }
    }

    override fun prepareImports(parentFile: CodeFile) {
        super.prepareImports(parentFile)
        parameterContainer.prepareImports(parentFile)
    }

    fun statementReturn(): CodeReturnValue {
        val r = CodeReturnValue()
        parentFunction!!.setReturnType(codeTypes("Unit"))
        statements.add(r)
        return r
    }

    fun statementReturn(event: CodeReturnEvent): CodeReturnValue {
        val ass = CodeReturnValue(CodeVarRef(event.name.name, event.type))
        parentFunction!!.setReturnType(event.type)
        statements.add(ass)
        return ass
    }

    fun statementReturn(init: CodeExpressionBuilder.() -> ACodeExpression): CodeReturnValue {
        val res = CodeExpressionBuilder().init()
        val r = CodeReturnValue(res)
        parentFunction!!.setReturnType(res.resultType)
        statements.add(r)
        return r
    }
}
