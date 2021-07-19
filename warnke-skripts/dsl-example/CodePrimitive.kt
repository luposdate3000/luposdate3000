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

class CodePrimitive(resultType: CodeType, var symbol: String, var a: ACodeExpression, var b: ACodeExpression) : ACodeExpression(resultType) {
    override fun copy(mapName: (String) -> String): ACodeExpression {
        return CodePrimitive(resultType, symbol, a.copy(mapName), b.copy(mapName))
    }

    override fun generate(): String {
        return "${a.generate()} $symbol ${b.generate()}"
    }

    override fun prepareImports(parentFile: CodeFile) {
        a.prepareImports(parentFile)
        b.prepareImports(parentFile)
        resultType.addImport(parentFile)
    }
}
