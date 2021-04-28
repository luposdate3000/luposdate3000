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

fun codeTypes(type: String): CodeType {
    return registeredTypes[type]!!
}

fun codeFile(name: String, pkg: String, init: CodeFile.() -> Unit): CodeFile {
    val file = CodeFile(name, pkg)
    file.init()
    return file
}

fun codeSegment(name: String, init: CodeSegment.() -> Unit): CodeSegment {
    val seg = CodeSegment(null, name)
    seg.init()
    return seg
}

fun codeVar(name: String, type: CodeType): CodeVariableDefinition {
    val v = CodeVariableDefinition(CodeName(name))
    v.type_ = type
    return v
}

fun codeVal(name: String, init: CodeExpressionBuilder.() -> ACodeExpression): CodeConstantDefinition {
    val v = CodeConstantDefinition(CodeName(name), CodeExpressionBuilder().init())
    return v
}
