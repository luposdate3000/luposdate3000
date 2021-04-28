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
