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
@file:Import("generate-arithmetik-operators.kt")
@file:Import("../luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/ETripleComponentType.kt")
@file:Import("../luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/ETripleComponentTypeExt.kt")

for (operator in operators) {
    println("${operator.name} --------- ")
    var imports = mutableSetOf<String>()
    var target = StringBuilder()
    operator.generate(EParamRepresentation.ID, imports, target)
    for (i in imports) {
        println("import $i")
    }
    print(target.toString())
}
