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
class CodeParameterContainer {
    val parameters = mutableListOf<CodeParamDefinition>()
    fun generate(): String {
        return parameters.map { it.generate() }.joinToString()
    }

    fun add(v: CodeVariableDefinition): CodeParamDefinition {
        val param = CodeParamDefinition(v.name)
        param.type_ = v.getType()
        parameters.add(param)
        return param
    }

    fun add(name: String, type: CodeType): CodeParamDefinition {
        val param = CodeParamDefinition(CodeName(name))
        param.type_ = type
        parameters.add(param)
        return param
    }

    fun add(name: String, type: CodeType, value: String): CodeParamDefinition {
        val param = CodeParamDefinition(CodeName(name))
        param.expression = CodeVal(type, value)
        parameters.add(param)
        return param
    }

    fun prepareImports(parentFile: CodeFile) {
        for (parameter in parameters) {
            parameter.prepareImports(parentFile)
        }
    }
}
