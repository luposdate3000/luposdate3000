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

class CodeParamDefinition(var name: CodeName) : ACodeStatement() {
    override fun copyInto(target: CodeFunctionBody, onEvent: CodeFunctionBody.(CodeReturnEvent) -> Unit, mapName: (String) -> String) {
        throw Exception("dont call this")
    }

    override fun equals(other: Any?): Boolean {
        return other is CodeParamDefinition && other.getType() == getType()
    }

    var expression: ACodeExpression? = null
    var type_: CodeType? = null
    fun getType(): CodeType {
        if (expression != null) {
            return expression!!.resultType
        } else {
            return type_!!
        }
    }

    override fun prepareImports(parentFile: CodeFile) {
        type_?.addImport(parentFile)
    }

    override fun generate(indention: String, out: StringBuilder) {
        throw Exception("dont call this")
    }

    fun generate(): String {
        if (expression != null) {
            return "${name.generate()} : ${expression!!.resultType.generate()} = ${expression!!.generate()}"
        } else {
            return "${name.generate()} : ${type_!!.generate()}"
        }
    }
}
