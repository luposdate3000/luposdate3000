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
@file:DependsOn("com.ionspin.kotlin:bignum:0.3.1")
@file:Import("../luposdate3000_code_generator_shared/src/jvmMain/kotlin/lupos/code_generator_shared/generate-arithmetik-operators-classes.kt")
@file:Import("../luposdate3000_code_generator_shared/src/jvmMain/kotlin/lupos/code_generator_shared/generate-arithmetik-operators.kt")
@file:Import("../luposdate3000_operator_arithmetik/src/commonMain/kotlin/lupos/operator/arithmetik/noinput/AOPConstant.kt")
@file:Import("../luposdate3000_operator_arithmetik/src/commonMain/kotlin/lupos/operator/arithmetik/noinput/AOPVariable.kt")
@file:Import("../luposdate3000_shared/src/commonMain/kotlin/lupos/shared/ETripleComponentTypeExt.kt")
@file:Import("../luposdate3000_shared/src/commonMain/kotlin/lupos/shared/ETripleComponentType.kt")
@file:Import("../luposdate3000_shared/src/commonMain/kotlin/lupos/shared/dynamicArray/ByteArrayWrapper.kt")
@file:Import("../luposdate3000_shared/src/commonMain/kotlin/lupos/shared/operator/IOPBase.kt")
@file:Import("../luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared_inline/DictionaryHelper.kt")
@file:Import("../luposdate3000_shared/src/commonMain/kotlin/lupos/shared/ValueDefinitions.kt")
@file:Import("../luposdate3000_shared/src/commonMain/kotlin/lupos/shared/XMLElement.kt")
@file:Import("../luposdate3000_shared/src/commonMain/kotlin/lupos/shared/dictionary/DictionaryExt.kt")
@file:Import("../luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared_inline/dynamicArray/ByteArrayWrapperExt.kt")
@file:Import("../luposdate3000_shared_inline/src/jvmMain/kotlin/lupos/shared_inline/ByteArrayHelper.kt")
@file:Import("../luposdate3000_shared_inline/src/commonMain/kotlin/lupos/shared_inline/ByteArrayHelper.kt")
@file:CompilerOptions("-Xmulti-platform")

import lupos.code_generator_shared.MyOperator
import lupos.code_generator_shared.OperatorBuilder
import java.io.File

public val operators = mutableListOf<MyOperator>()

OperatorBuilder.build(operators)
for (operator in operators) {
    File("src/luposdate3000_operator_arithmetik/src/commonMain/kotlin/lupos/operator/arithmetik/generated/AOP${operator.type.str}${operator.name}.kt").printWriter().use { out ->
        out.println("/*")
        out.println(" * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).")
        out.println(" * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck")
        out.println(" *")
        out.println(" * This program is free software: you can redistribute it and/or modify")
        out.println(" * it under the terms of the GNU General Public License as published by")
        out.println(" * the Free Software Foundation, version 3.")
        out.println(" *")
        out.println(" * This program is distributed in the hope that it will be useful, but")
        out.println(" * WITHOUT ANY WARRANTY; without even the implied warranty of")
        out.println(" * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU")
        out.println(" * General Public License for more details.")
        out.println(" *")
        out.println(" * You should have received a copy of the GNU General Public License")
        out.println(" * along with this program. If not, see <http://www.gnu.org/licenses/>.")
        out.println(" */")
        out.println(operator.generateAOP().toString())
    }
}
