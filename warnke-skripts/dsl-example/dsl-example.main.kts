#!/usr/bin/env kotlin
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
@file:Import("ACodeBase.kt")
@file:Import("ACodeExpression.kt")
@file:Import("ACodeStatement.kt")
@file:Import("CodeAssignment.kt")
@file:Import("CodeClazz.kt")
@file:Import("CodeConstantDefinition.kt")
@file:Import("CodeDSLMarker.kt")
@file:Import("CodeExpressionBuilder.kt")
@file:Import("CodeFile.kt")
@file:Import("CodeFunctionBody.kt")
@file:Import("CodeFunction.kt")
@file:Import("CodeIf.kt")
@file:Import("CodeName.kt")
@file:Import("CodeParamDefinition.kt")
@file:Import("CodeParameterContainer.kt")
@file:Import("CodePrimitive.kt")
@file:Import("CodeReturnEvent.kt")
@file:Import("CodeReturnValue.kt")
@file:Import("CodeSegment.kt")
@file:Import("CodeStatementGroup.kt")
@file:Import("CodeType.kt")
@file:Import("CodeVal.kt")
@file:Import("CodeVariableDefinition.kt")
@file:Import("CodeVarRef.kt")
@file:Import("entryPoint.kt")
@file:Import("ICodeVariableOrConstantDefinition.kt")
@file:Import("registeredCodeSegments.kt")
@file:Import("registeredTypes.kt")

import lupos.codegen.codeFile
import lupos.codegen.codeTypes
import lupos.codegen.codeVar

codeFile("myfilename", "generatedPackage") {
    clazz("myclazzname") {
        function("x") {
            parameter {
                add("a", codeTypes("Double"), "0.0")
                add("b", codeTypes("Double"))
            }
            statementVar("y", codeTypes("Int"))
            statementVar("z", codeTypes("ByteArrayWrapper"))
            statementVal("x") { expVal(codeTypes("Int"), "4") }
            statementAssign("y") { expVal(codeTypes("Int"), "5") }
            statementReturn { expVal(codeTypes("Double"), "8") }
        }
        function("y") {
            val a = codeVar("x", codeTypes("Double"))
            val b = codeVar("y", codeTypes("Double"))
            statementVal(a) { expVal(codeTypes("Double"), "4") }
            statementVal(b) { expVal(codeTypes("Double"), "5") }
            statementUse(
                "/",
                {
                    add(a)
                    add(b)
                },
                { event ->
                    when (event.type) {
                        codeTypes("Double") -> statementReturn(event)
                        else -> statementReturn { expVal(codeTypes("Double"), "6") }
                    }
                }
            )
        }
    }
}
