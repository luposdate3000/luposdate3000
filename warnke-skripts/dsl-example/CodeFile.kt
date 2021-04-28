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

class CodeFile(var name: String, var pkg: String) : ACodeBase() {
    val imports = mutableSetOf<String>()
    val clazzes = mutableListOf<CodeClazz>()
    override fun prepareImports(parentFile: CodeFile) {
        for (clazz in clazzes) {
            clazz.prepareImports(parentFile)
        }
    }

    fun clazz(name: String, init: CodeClazz.() -> Unit): CodeClazz {
        val c = CodeClazz(CodeName(name))
        c.init()
        clazzes.add(c)
        return c
    }

    fun generate(indention: String, out: StringBuilder) {
        for (clazz in clazzes) {
            clazz.generate(indention + "", out)
            out.appendLine()
        }
    }

    override fun toString(): String {
        val out = StringBuilder()
        prepareImports(this)
        out.appendLine("package $pkg")
        out.appendLine()
        for (i in imports) {
            out.appendLine("import $i")
        }
        out.appendLine()
        generate("", out)
        return out.toString()
    }
}
