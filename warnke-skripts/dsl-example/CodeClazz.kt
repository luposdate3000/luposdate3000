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

class CodeClazz(var name: CodeName) : ACodeBase() {
    val functions = mutableListOf<CodeFunction>()
    override fun prepareImports(parentFile: CodeFile) {
        for (function in functions) {
            function.prepareImports(parentFile)
        }
    }

    fun function(name: String, init: CodeFunction.() -> Unit): CodeFunction {
        val func = CodeFunction(null, CodeName(name))
        func.parentFunction = func
        func.init()
        functions.add(func)
        return func
    }

    fun generate(indention: String, out: StringBuilder) {
        out.appendLine("${indention}class ${name.generate()} {")
        for (function in functions) {
            function.generate(indention + "  ", out)
            out.appendLine()
        }
        out.appendLine("$indention}")
    }
}
