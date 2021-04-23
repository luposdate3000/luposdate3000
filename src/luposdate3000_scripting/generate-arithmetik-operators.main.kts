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
@file:Import("../luposdate3000_shared/src/commonMain/kotlin/lupos/shared/ETripleComponentType.kt")
@file:Import("../luposdate3000_shared/src/commonMain/kotlin/lupos/shared/ETripleComponentTypeExt.kt")

import lupos.shared.ETripleComponentType
import lupos.shared.ETripleComponentTypeExt
import java.io.File

public enum class OperatorType(val str: String) {
    BuildInCall("BuildInCall"),
    FunctionCall("FunctionCall"),
    Basic(""),
}

public enum class EParamRepresentation {
    ID, // represented as dictionary key
    BYTEARRAYWRAPPER, // represented as ByteArrayWrapper
    INSTANTIATED, // represented as the parsed Type itself
}
typealias GenerateFunc = (
    String, // indention
    Array<String>, // inputNames
    String, // outputName
    String, // prefix (for intermediates)
    MutableSet<String>, // imports
    StringBuilder, // target
    MutableSet<String>, // globalVariables
    (String, ETripleComponentType) -> Unit, // onResult(indention,resultType)
) -> Unit

typealias GenerateFuncOther = (
    String, // indention
    String, // outputName
    String, // prefix (for intermediates)
    MutableSet<String>, // imports
    StringBuilder, // target
    MutableSet<String>, // globalVariables
    (String, ETripleComponentType) -> Unit, // onResult(indention,resultType)
) -> Unit

typealias GenerateFuncOtherInstantiated = (
    String, // indention
    String, // outputName
    String, // prefix (for intermediates)
    MutableSet<String>, // imports
    StringBuilder, // target
    MutableSet<String>, // globalVariables
) -> ETripleComponentType

public val generateInstantiatedError: GenerateFuncOtherInstantiated = { _, _, _, _, _, _ ->
    ETripleComponentTypeExt.ERROR
}
public val generateByteArrayWrapperError: GenerateFuncOther = { indention, outputName, _, imports, target, _, onResult ->
    imports.add("lupos.dictionary.DictionaryHelper")
    target.appendLine("${indention}DictionaryHelper.errorToByteArray($outputName)")
    onResult(indention, ETripleComponentTypeExt.ERROR)
}
public val generateIDError: GenerateFuncOther = { indention, outputName, _, imports, target, _, onResult ->
    imports.add("lupos.dictionary.DictionaryExt")
    target.appendLine("$indention$outputName = DictionaryExt.errorValue")
    onResult(indention, ETripleComponentTypeExt.ERROR)
}
public val generateInstantiatedFalse: GenerateFuncOtherInstantiated = { indention, outputName, _, _, target, _ ->
    target.appendLine("$indention$outputName = false")
    ETripleComponentTypeExt.BOOLEAN
}
public val generateByteArrayWrapperFalse: GenerateFuncOther = { indention, outputName, _, imports, target, _, onResult ->
    imports.add("lupos.dictionary.DictionaryHelper")
    target.appendLine("${indention}DictionaryHelper.booleanToByteArray($outputName, false)")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}
public val generateIDFalse: GenerateFuncOther = { indention, outputName, _, imports, target, _, onResult ->
    imports.add("lupos.dictionary.DictionaryExt")
    target.appendLine("$indention$outputName = DictionaryExt.booleanFalseValue")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}
public val generateInstantiatedTrue: GenerateFuncOtherInstantiated = { indention, outputName, _, _, target, _ ->
    target.appendLine("$indention$outputName = true")
    ETripleComponentTypeExt.BOOLEAN
}
public val generateByteArrayWrapperTrue: GenerateFuncOther = { indention, outputName, _, imports, target, _, onResult ->
    imports.add("lupos.dictionary.DictionaryHelper")
    target.appendLine("${indention}DictionaryHelper.booleanToByteArray($outputName, true)")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}
public val generateIDTrue: GenerateFuncOther = { indention, outputName, _, imports, target, _, onResult ->
    imports.add("lupos.dictionary.DictionaryExt")
    target.appendLine("$indention$outputName = DictionaryExt.booleanTrueValue")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}
public val generateInstantiatedError2: GenerateFunc = { indention, _, _, _, _, _, _, onResult ->
    onResult(indention, ETripleComponentTypeExt.ERROR)
}
public val generateByteArrayWrapperError2: GenerateFunc = { indention, _, outputName, _, imports, target, globalVariables, onResult ->
    imports.add("lupos.shared.ByteArrayWrapper")
    imports.add("lupos.dictionary.DictionaryHelper")
    globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
    target.appendLine("${indention}DictionaryHelper.errorToByteArray($outputName)")
    onResult(indention, ETripleComponentTypeExt.ERROR)
}
public val generateInstantiatedTrue2: GenerateFunc = { indention, _, outputName, _, _, target, _, onResult ->
    target.appendLine("$indention$outputName = true")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}

public val generateByteArrayWrapperTrue2: GenerateFunc = { indention, _, outputName, _, imports, target, globalVariables, onResult ->
    imports.add("lupos.shared.ByteArrayWrapper")
    imports.add("lupos.dictionary.DictionaryHelper")
    globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
    target.appendLine("${indention}DictionaryHelper.booleanToByteArray($outputName, true)")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}
public val generateInstantiatedFalse2: GenerateFunc = { indention, _, outputName, _, _, target, _, onResult ->
    target.appendLine("$indention$outputName = false")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}

public val generateByteArrayWrapperFalse2: GenerateFunc = { indention, _, outputName, _, imports, target, globalVariables, onResult ->
    imports.add("lupos.shared.ByteArrayWrapper")
    imports.add("lupos.dictionary.DictionaryHelper")
    globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
    target.appendLine("${indention}DictionaryHelper.booleanToByteArray($outputName, false)")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}

fun generateByteArrayWrapperString(str: String): GenerateFunc = { indention, _, outputName, _, imports, target, globalVariables, onResult ->
    imports.add("lupos.shared.ByteArrayWrapper")
    imports.add("lupos.dictionary.DictionaryHelper")
    globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
    target.appendLine("${indention}DictionaryHelper.stringToByteArray($outputName, \"$str\")")
    onResult(indention, ETripleComponentTypeExt.STRING)
}

fun generateInstantiatedString(str: String): GenerateFunc = { indention, _, outputName, _, _, target, _, onResult ->
    target.appendLine("$indention$outputName = \"$str\"")
    onResult(indention, ETripleComponentTypeExt.STRING)
}

public class MyOperator(
    public val name: String,
    public val functionname: String = name,
    public val type: OperatorType,
    public val imports: MutableSet<String> = mutableSetOf(),
    public val additionalParametersName: Array<String> = arrayOf(),
    public val additionalParametersDefinition: Array<String> = arrayOf(),
    public val implementations: Array<MyOperatorPart>,
    public val generateInstantiatedOther: GenerateFuncOtherInstantiated,
    public val generateIDOther: GenerateFuncOther,
    public val generateByteArrayWrapperOther: GenerateFuncOther,
) {
    public fun generate(indention: String, representation: EParamRepresentation, imports: MutableSet<String>, target: StringBuilder, globalVariables: MutableSet<String>) {
        generate(indention, representation, Array(implementations[0].childrenTypes.size) { "children[$it]" }, "res", "tmp", imports, target, globalVariables)
    }

    public fun generate(indention: String, representation: EParamRepresentation, inputNames: Array<String>, outputName: String, prefix: String, imports: MutableSet<String>, target: StringBuilder, globalVariables: MutableSet<String>) {
        if (representation == EParamRepresentation.INSTANTIATED) {
            throw Exception("there is no need to combine functions here")
        }
        var myInputNames = Array<String>(inputNames.size) { inputNames[it] }
        var prefix_counter = 0
        if (representation == EParamRepresentation.ID) {
            globalVariables.add("var $outputName: Int")
            for (i in 0 until inputNames.size) {
                myInputNames[i] = "${prefix}_${prefix_counter++}"
                imports.add("lupos.shared.ByteArrayWrapper")
                globalVariables.add("val ${myInputNames[i]}: ByteArrayWrapper = ByteArrayWrapper()")
                target.appendLine("${indention}query.getDictionary().getValue(${myInputNames[i]}, ${inputNames[i]})")
            }
        } else {
            globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
        }
        var typeNames = Array<String>(inputNames.size) { "${prefix}_${prefix_counter++}" }
        imports.add("lupos.shared.ETripleComponentType")
        for (i in 0 until inputNames.size) {
            target.appendLine("${indention}val ${typeNames[i]}: ETripleComponentType = DictionaryHelper.byteArrayToType(${myInputNames[i]})")
        }
        var myOutputName = outputName
        if (representation == EParamRepresentation.ID) {
            myOutputName = "${prefix}_${prefix_counter++}"
        }
        implementations.sort()
        var lastOperatorTypes = Array(implementations[0].childrenTypes.size) { -1 }
        var openWhenStatements = -1
        var localindention = indention
        fun closeWhenStatements(first: Int) {
            while (openWhenStatements > first) {
                target.appendLine("${localindention.substring(4)}}")
                target.appendLine("${localindention.substring(4)}else -> {")
                if (representation == EParamRepresentation.ID) {
                    generateIDOther(localindention, outputName, "${prefix}_${prefix_counter++}", imports, target, globalVariables) { _, _ ->
                    }
                } else {
                    generateByteArrayWrapperOther(localindention, outputName, "${prefix}_${prefix_counter++}", imports, target, globalVariables) { _, _ ->
                    }
                }
                target.appendLine("${localindention.substring(4)}}")
                localindention = localindention.substring(8)
                target.appendLine("$localindention}")
                openWhenStatements--
            }
        }
        for (implementation in implementations) {
            fun createWhenStatements(first: Int, last: Int) {
                closeWhenStatements(first)
                for (i in first until last) {
                    if (openWhenStatements < i) {
                        target.appendLine("${localindention}when (${typeNames[i]}) {")
                        openWhenStatements++
                        localindention += "        "
                    } else {
                        target.appendLine("${localindention.substring(4)}}")
                    }
                    imports.add("lupos.shared.ETripleComponentTypeExt")
                    lastOperatorTypes[i] = implementation.childrenTypes[i]
                    target.appendLine("${localindention.substring(4)}ETripleComponentTypeExt.${ETripleComponentTypeExt.names[implementation.childrenTypes[i]]} -> {")
                }
            }

            val generateByteArrayWrapper = implementation.generateByteArrayWrapper
            var commonOperatorTypes = 0
            while (lastOperatorTypes[commonOperatorTypes] == implementation.childrenTypes[commonOperatorTypes] && commonOperatorTypes < implementation.childrenTypes.size) {
                commonOperatorTypes++
            }
            createWhenStatements(commonOperatorTypes, implementation.childrenTypes.size)
            if (generateByteArrayWrapper != null) {
                generateByteArrayWrapper(localindention, myInputNames, myOutputName, "${prefix}_${prefix_counter++}", imports, target, globalVariables) { indention2, _ ->
                    if (representation == EParamRepresentation.ID) {
                        target.appendLine("$indention2$outputName = query.getDictionary().createValue($myOutputName)")
                    }
                }
            } else {
                var myInputInstances = Array(inputNames.size) { "${prefix}_${prefix_counter++}" }
                var myOutputInstance = "${prefix}_${prefix_counter++}"
                for (i in 0 until inputNames.size) {
                    val converter = getRepresentationConversionFunction(implementation.childrenTypes[i], EParamRepresentation.BYTEARRAYWRAPPER, EParamRepresentation.INSTANTIATED)
                    converter.generate(localindention, myInputNames[i], myInputInstances[i], imports, target, globalVariables)
                }
                implementation.generateInstantiated(localindention, myInputInstances, myOutputInstance, "${prefix}_${prefix_counter++}", imports, target, globalVariables) { indention2, resultType ->
                    if (resultType == ETripleComponentTypeExt.BLANK_NODE) {
                        target.appendLine("$indention2$outputName = $myOutputInstance")
                    } else {
                        val converter = getRepresentationConversionFunction(resultType, EParamRepresentation.INSTANTIATED, EParamRepresentation.BYTEARRAYWRAPPER)
                        converter.generate(indention2, myOutputInstance, myOutputName, imports, target, globalVariables)
                        if (representation == EParamRepresentation.ID) {
                            target.appendLine("$indention2$outputName = query.getDictionary().createValue($myOutputName)")
                        }
                    }
                }
            }
        }
        closeWhenStatements(-1)
    }

    public fun generateAOP(): StringBuilder {
        var clazz = StringBuilder()
        clazz.appendLine("package lupos.s04arithmetikOperators.generated")
        clazz.appendLine("")
        var imports2 = mutableSetOf<String>()
        imports2.addAll(imports)
        var target = StringBuilder()
        var globalVariables = mutableSetOf<String>()
        generate("            ", EParamRepresentation.ID, Array(implementations[0].childrenTypes.size) { "childIn$it" }, "res", "tmp", imports2, target, globalVariables)
        imports2.add("lupos.s04arithmetikOperators.AOPBase")
        imports2.add("lupos.s04logicalOperators.IQuery")
        imports2.add("lupos.s04logicalOperators.iterator.IteratorBundle")
        imports2.add("lupos.s04logicalOperators.IOPBase")
        imports2.add("lupos.shared.EOperatorIDExt")
        for (i in imports2.toList().sorted()) {
            clazz.appendLine("import $i")
        }
        clazz.appendLine("")
        var line0 = ""
        line0 += ("public class AOP${type.str}$name public constructor(")
        line0 += ("query: IQuery, ")
        var line = ""
        var line2 = ""
        var line3 = ""
        var line4 = ""
        for (i in 0 until implementations[0].childrenTypes.size) {
            if (i > 0) {
                line2 += ", "
                line += " "
                line0 += " "
            }
            line += "child$i,"
            line0 += "child$i: AOPBase,"
            line2 += "\${children[$i].toSparql()}"
            line3 += " && children[$i] == other.children[$i]"
            line4 += ", children[$i].cloneOP() as AOPBase"
        }
        for (i in 0 until additionalParametersDefinition.size) {
            line0 += " ${additionalParametersDefinition[i]},"
            line4 += ", ${additionalParametersName[i]}"
        }
        line0 += (") : AOPBase(")
        line0 += ("query, ")
        line0 += ("EOperatorIDExt.AOP${type.str}${name}ID, ")
        line0 += ("\"AOP${type.str}${name}\", ")
        line0 += ("arrayOf($line)) {")
        clazz.appendLine(line0)
        clazz.appendLine("    override fun toSparql(): String = \"$functionname($line2)\"")
        clazz.appendLine("    override fun equals(other: Any?): Boolean = other is AOP${type.str}$name$line3")
        clazz.appendLine("    override fun cloneOP(): IOPBase = AOP${type.str}$name(query$line4)")
        clazz.appendLine("    override fun evaluateID(row: IteratorBundle): () -> Int {")
        for (v in globalVariables) {
            if (!v.contains(" res: ")) {
                clazz.appendLine("        $v")
            }
        }
        for (i in 0 until implementations[0].childrenTypes.size) {
            clazz.appendLine("        val child$i: () -> Int = (children[$i] as AOPBase).evaluateID(row)")
        }
        clazz.appendLine("        return {")
        clazz.appendLine("            var res: Int")
        for (i in 0 until implementations[0].childrenTypes.size) {
            clazz.appendLine("            val childIn$i: Int = child$i()")
        }
        clazz.append(target.toString())
        clazz.appendLine("            res")
        clazz.appendLine("        }")
        clazz.appendLine("    }")
        clazz.append("}")
        return clazz
    }
}

public class MyOperatorPart(
    public val childrenTypes: Array<ETripleComponentType>,
    public val generateInstantiated: GenerateFunc,
    public val generateByteArrayWrapper: GenerateFunc? = null,
) : Comparable<MyOperatorPart> {
    public override fun compareTo(other: MyOperatorPart): Int {
        var res = 0
        var i = 0
        while (i < childrenTypes.size && res == 0) {
            res = childrenTypes[i] - other.childrenTypes[i]
            i++
        }
        return res
    }
}

public class MyRepresentationConversionFunction(
    public val type: ETripleComponentType,
    public val inputRepresentation: EParamRepresentation,
    public val outputRepresentation: EParamRepresentation,
    public val generate: (
        String, // indention
        String, // inputName
        String, // outputName
        MutableSet<String>, // imports
        StringBuilder, // target
        MutableSet<String>, // globalVariables
    ) -> Unit
)

public class MyOperatorPartFactory() {
    var list = mutableListOf<MyOperatorPart>()
    var generateInstantiatedOther: GenerateFunc? = null
    var generateByteArrayWrapperOther: GenerateFunc? = null

    fun build(): Array<MyOperatorPart> {
        if (generateInstantiatedOther != null) {
            val paramsCount = list[0].childrenTypes.size
            when (paramsCount) {
                1 -> {
                    for (i in 0 until ETripleComponentTypeExt.values_size) {
                        var found = false
                        for (c in list) {
                            if (c.childrenTypes[0] == i) {
                                found = true
                                break
                            }
                        }
                        if (!found) {
                            list.add(
                                MyOperatorPart(
                                    childrenTypes = arrayOf(i),
                                    generateInstantiated = generateInstantiatedOther!!,
                                    generateByteArrayWrapper = generateByteArrayWrapperOther,
                                )
                            )
                        }
                    }
                }
                2 -> {
                    for (i in 0 until ETripleComponentTypeExt.values_size) {
                        for (j in 0 until ETripleComponentTypeExt.values_size) {
                            var found = false
                            for (c in list) {
                                if (c.childrenTypes[0] == i && c.childrenTypes[1] == j) {
                                    found = true
                                    break
                                }
                            }
                            if (!found) {
                                list.add(
                                    MyOperatorPart(
                                        childrenTypes = arrayOf(i, j),
                                        generateInstantiated = generateInstantiatedOther!!,
                                        generateByteArrayWrapper = generateByteArrayWrapperOther,
                                    )
                                )
                            }
                        }
                    }
                }
                3 -> {
                    for (i in 0 until ETripleComponentTypeExt.values_size) {
                        for (j in 0 until ETripleComponentTypeExt.values_size) {
                            for (k in 0 until ETripleComponentTypeExt.values_size) {
                                var found = false
                                for (c in list) {
                                    if (c.childrenTypes[0] == i && c.childrenTypes[1] == j && c.childrenTypes[2] == k) {
                                        found = true
                                        break
                                    }
                                }
                                if (!found) {
                                    list.add(
                                        MyOperatorPart(
                                            childrenTypes = arrayOf(i, j, k),
                                            generateInstantiated = generateInstantiatedOther!!,
                                            generateByteArrayWrapper = generateByteArrayWrapperOther,
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
                else -> {
                    throw Exception("not implemented $paramsCount")
                }
            }
        }
        return list.toTypedArray()
    }

    fun setOther(generateInstantiated: GenerateFunc, generateByteArrayWrapper: GenerateFunc? = null): MyOperatorPartFactory {
        generateInstantiatedOther = generateInstantiated
        generateByteArrayWrapperOther = generateByteArrayWrapper
        return this
    }

    fun add(part: MyOperatorPart): MyOperatorPartFactory {
        list.add(part)
        return this
    }

    fun addForEachChildrenType(childrenTypes: Array<Array<ETripleComponentType>>, generateInstantiated: GenerateFunc, generateByteArrayWrapper: GenerateFunc? = null): MyOperatorPartFactory {
        for (p in childrenTypes) {
            list.add(
                MyOperatorPart(
                    childrenTypes = p,
                    generateInstantiated = generateInstantiated,
                    generateByteArrayWrapper = generateByteArrayWrapper,
                )
            )
        }
        return this
    }

    fun addNumericBinaryOperatorStub(operator: String): MyOperatorPartFactory {
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.INTEGER),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                    imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                    target.appendLine("${indention}if (${inputNames[1]} == BigInteger.ZERO) {")
                    onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                    target.appendLine("$indention} else {")
                    target.appendLine("$indention    val $outputName: BigDecimal = ${inputNames[0]} $operator BigDecimal.fromBigInteger(${inputNames[1]})")
                    onResult(indention + "    ", ETripleComponentTypeExt.DECIMAL)
                    target.appendLine("$indention}")
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                    target.appendLine("${indention}if (${inputNames[1]} == BigInteger.ZERO) {")
                    onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                    target.appendLine("$indention} else {")
                    target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}.doubleValue()")
                    onResult(indention + "    ", ETripleComponentTypeExt.FLOAT)
                    target.appendLine("$indention}")
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.INTEGER),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                    target.appendLine("${indention}if (${inputNames[1]} == BigInteger.ZERO) {")
                    onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                    target.appendLine("$indention} else {")
                    target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}.doubleValue()")
                    onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                    target.appendLine("$indention}")
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.DECIMAL),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                    target.appendLine("${indention}if (${inputNames[1]} == BigDecimal.ZERO) {")
                    onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                    target.appendLine("$indention} else {")
                    target.appendLine("$indention    val $outputName: BigDecimal = BigDecimal.fromBigInteger(${inputNames[0]}) $operator ${inputNames[1]}")
                    onResult(indention + "    ", ETripleComponentTypeExt.DECIMAL)
                    target.appendLine("$indention}")
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DECIMAL),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                    target.appendLine("${indention}if (${inputNames[1]} == BigDecimal.ZERO) {")
                    onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                    target.appendLine("$indention} else {")
                    target.appendLine("$indention    val $outputName: BigDecimal = ${inputNames[0]} $operator ${inputNames[1]}")
                    onResult(indention + "    ", ETripleComponentTypeExt.DECIMAL)
                    target.appendLine("$indention}")
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.DECIMAL),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                    target.appendLine("${indention}if (${inputNames[1]} == BigDecimal.ZERO) {")
                    onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                    target.appendLine("$indention} else {")
                    target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}.doubleValue()")
                    onResult(indention + "    ", ETripleComponentTypeExt.FLOAT)
                    target.appendLine("$indention}")
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.DECIMAL),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                    target.appendLine("${indention}if (${inputNames[1]} == BigDecimal.ZERO) {")
                    onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                    target.appendLine("$indention} else {")
                    target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}.doubleValue()")
                    onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                    target.appendLine("$indention}")
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.FLOAT),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                    onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                    target.appendLine("$indention} else {")
                    target.appendLine("$indention    val $outputName: Double = ${inputNames[0]}.doubleValue() $operator ${inputNames[1]}")
                    onResult(indention + "    ", ETripleComponentTypeExt.FLOAT)
                    target.appendLine("$indention}")
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.FLOAT),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                    onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                    target.appendLine("$indention} else {")
                    target.appendLine("$indention    val $outputName: Double = ${inputNames[0]}.doubleValue() $operator ${inputNames[1]}")
                    onResult(indention + "    ", ETripleComponentTypeExt.FLOAT)
                    target.appendLine("$indention}")
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.FLOAT),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                    onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                    target.appendLine("$indention} else {")
                    target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}")
                    onResult(indention + "    ", ETripleComponentTypeExt.FLOAT)
                    target.appendLine("$indention}")
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.FLOAT),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                    onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                    target.appendLine("$indention} else {")
                    target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}")
                    onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                    target.appendLine("$indention}")
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.DOUBLE),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                    onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                    target.appendLine("$indention} else {")
                    target.appendLine("$indention    val $outputName: Double = ${inputNames[0]}.doubleValue() $operator ${inputNames[1]}")
                    onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                    target.appendLine("$indention}")
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                    onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                    target.appendLine("$indention} else {")
                    target.appendLine("$indention    val $outputName: Double = ${inputNames[0]}.doubleValue() $operator ${inputNames[1]}")
                    onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                    target.appendLine("$indention}")
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.DOUBLE),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                    onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                    target.appendLine("$indention} else {")
                    target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}")
                    onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                    target.appendLine("$indention}")
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.DOUBLE),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                    onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                    target.appendLine("$indention} else {")
                    target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}")
                    onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                    target.appendLine("$indention}")
                },
            )
        )
        return this
    }
}

public val operators = mutableListOf<MyOperator>()
operators.add(
    MyOperator(
        name = "ABS",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: BigInteger = ${inputNames[0]}.abs()")
                        onResult(indention, ETripleComponentTypeExt.INTEGER)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                        target.appendLine("${indention}val $outputName: BigDecimal = ${inputNames[0]}.abs()")
                        onResult(indention, ETripleComponentTypeExt.DECIMAL)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("kotlin.math.abs")
                        target.appendLine("${indention}val $outputName: Double = abs(${inputNames[0]})")
                        onResult(indention, ETripleComponentTypeExt.DOUBLE)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("kotlin.math.abs")
                        target.appendLine("${indention}val $outputName: Double = abs(${inputNames[0]})")
                        onResult(indention, ETripleComponentTypeExt.FLOAT)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "ROUND",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: BigInteger = ${inputNames[0]}")
                        onResult(indention, ETripleComponentTypeExt.INTEGER)
                    },
                    generateByteArrayWrapper = { indention, inputNames, outputName, _, imports, target, globalVariables, onResult ->
                        imports.add("lupos.shared.ByteArrayWrapper")
                        globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
                        target.appendLine("${indention}${inputNames[0]}.copyInto($outputName)")
                        onResult(indention, ETripleComponentTypeExt.INTEGER)
                    }
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                        imports.add("com.ionspin.kotlin.bignum.decimal.RoundingMode")
                        target.appendLine("${indention}val $outputName: BigDecimal = ${inputNames[0]}.roundToDigitPositionAfterDecimalPoint(0, RoundingMode.ROUND_HALF_AWAY_FROM_ZERO)")
                        onResult(indention, ETripleComponentTypeExt.DECIMAL)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("kotlin.math.roundToInt")
                        target.appendLine("${indention}val $outputName: Double = ${inputNames[0]}.roundToInt().toDouble()")
                        onResult(indention, ETripleComponentTypeExt.DOUBLE)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("kotlin.math.roundToInt")
                        target.appendLine("${indention}val $outputName: Double = ${inputNames[0]}.roundToInt().toDouble()")
                        onResult(indention, ETripleComponentTypeExt.FLOAT)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "FLOOR",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: BigInteger = ${inputNames[0]}")
                        onResult(indention, ETripleComponentTypeExt.INTEGER)
                    },
                    generateByteArrayWrapper = { indention, inputNames, outputName, _, imports, target, globalVariables, onResult ->
                        imports.add("lupos.shared.ByteArrayWrapper")
                        globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
                        target.appendLine("${indention}${inputNames[0]}.copyInto($outputName)")
                        onResult(indention, ETripleComponentTypeExt.INTEGER)
                    }
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                        target.appendLine("${indention}val $outputName: BigDecimal = ${inputNames[0]}.floor()")
                        onResult(indention, ETripleComponentTypeExt.DECIMAL)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("kotlin.math.floor")
                        target.appendLine("${indention}val $outputName: Double = floor(${inputNames[0]})")
                        onResult(indention, ETripleComponentTypeExt.DOUBLE)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("kotlin.math.floor")
                        target.appendLine("${indention}val $outputName: Double = floor(${inputNames[0]})")
                        onResult(indention, ETripleComponentTypeExt.FLOAT)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "CEIL",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: BigInteger = ${inputNames[0]}")
                        onResult(indention, ETripleComponentTypeExt.INTEGER)
                    },
                    generateByteArrayWrapper = { indention, inputNames, outputName, _, imports, target, globalVariables, onResult ->
                        imports.add("lupos.shared.ByteArrayWrapper")
                        globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
                        target.appendLine("${indention}${inputNames[0]}.copyInto($outputName)")
                        onResult(indention, ETripleComponentTypeExt.INTEGER)
                    }
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                        target.appendLine("${indention}val $outputName: BigDecimal = ${inputNames[0]}.ceil()")
                        onResult(indention, ETripleComponentTypeExt.DECIMAL)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("kotlin.math.ceil")
                        target.appendLine("${indention}val $outputName: Double = ceil(${inputNames[0]})")
                        onResult(indention, ETripleComponentTypeExt.DOUBLE)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("kotlin.math.ceil")
                        target.appendLine("${indention}val $outputName: Double = ceil(${inputNames[0]})")
                        onResult(indention, ETripleComponentTypeExt.FLOAT)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "BOUND",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .addForEachChildrenType(
                arrayOf(arrayOf(ETripleComponentTypeExt.ERROR), arrayOf(ETripleComponentTypeExt.UNDEF)),
                generateInstantiated = generateInstantiatedFalse2,
                generateByteArrayWrapper = generateByteArrayWrapperFalse2,
            ).setOther(generateInstantiatedTrue2, generateByteArrayWrapperTrue2, ).build(),
        generateInstantiatedOther = generateInstantiatedTrue,
        generateIDOther = generateIDTrue,
        generateByteArrayWrapperOther = generateByteArrayWrapperTrue,
    )
)
operators.add(
    MyOperator(
        name = "STRLEN",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: BigInteger = BigInteger(${inputNames[0]}.length)")
                        onResult(indention, ETripleComponentTypeExt.INTEGER)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: BigInteger = BigInteger(${inputNames[0]}_content.length)")
                        onResult(indention, ETripleComponentTypeExt.INTEGER)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: BigInteger = BigInteger(${inputNames[0]}_content.length)")
                        onResult(indention, ETripleComponentTypeExt.INTEGER)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "LCASE",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]}.toLowerCase()")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        target.appendLine("${indention}val ${outputName}_content: String = ${inputNames[0]}_content.toLowerCase()")
                        target.appendLine("${indention}val ${outputName}_type: String = ${inputNames[0]}_type")
                        onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        target.appendLine("${indention}val ${outputName}_content: String = ${inputNames[0]}_content.toLowerCase()")
                        target.appendLine("${indention}val ${outputName}_lang: String = ${inputNames[0]}_lang")
                        onResult(indention, ETripleComponentTypeExt.STRING_LANG)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "LANG",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .addForEachChildrenType(
                arrayOf(
                    arrayOf(ETripleComponentTypeExt.STRING),
                    arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                    arrayOf(ETripleComponentTypeExt.INTEGER),
                    arrayOf(ETripleComponentTypeExt.DECIMAL),
                    arrayOf(ETripleComponentTypeExt.DOUBLE),
                    arrayOf(ETripleComponentTypeExt.FLOAT),
                    arrayOf(ETripleComponentTypeExt.BOOLEAN),
                    arrayOf(ETripleComponentTypeExt.DATE_TIME)
                ),
                generateInstantiated = { indention, _, outputName, _, imports, target, _, onResult ->
                    imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                    target.appendLine("${indention}val $outputName: String = \"\"")
                    onResult(indention, ETripleComponentTypeExt.STRING)
                },
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]}_lang")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "STR",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]}")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.IRI),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]}")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]}_content")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]}_content")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]}.toString()")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]}.toString()")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]}.toString()")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]}.toString()")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.BOOLEAN),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]}.toString()")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]}_typed_content")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "String",
        functionname = "<http://www.w3.org/2001/XMLSchema#string>",
        type = OperatorType.FunctionCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val ${outputName}_content: String = ${inputNames[0]}")
                        target.appendLine("${indention}val ${outputName}_type: String = \"http://www.w3.org/2001/XMLSchema#string\"")
                        onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val ${outputName}_content: String = ${inputNames[0]}_content")
                        target.appendLine("${indention}val ${outputName}_type: String = \"http://www.w3.org/2001/XMLSchema#string\"")
                        onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val ${outputName}_content: String = ${inputNames[0]}_content")
                        target.appendLine("${indention}val ${outputName}_type: String = \"http://www.w3.org/2001/XMLSchema#string\"")
                        onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val ${outputName}_content: String = ${inputNames[0]}.toString()")
                        target.appendLine("${indention}val ${outputName}_type: String = \"http://www.w3.org/2001/XMLSchema#string\"")
                        onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val ${outputName}_content: String = ${inputNames[0]}.toString()")
                        target.appendLine("${indention}val ${outputName}_type: String = \"http://www.w3.org/2001/XMLSchema#string\"")
                        onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val ${outputName}_content: String = ${inputNames[0]}.toString()")
                        target.appendLine("${indention}val ${outputName}_type: String = \"http://www.w3.org/2001/XMLSchema#string\"")
                        onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val ${outputName}_content: String = ${inputNames[0]}.toString()")
                        target.appendLine("${indention}val ${outputName}_type: String = \"http://www.w3.org/2001/XMLSchema#string\"")
                        onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.BOOLEAN),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val ${outputName}_content: String = ${inputNames[0]}.toString()")
                        target.appendLine("${indention}val ${outputName}_type: String = \"http://www.w3.org/2001/XMLSchema#string\"")
                        onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val ${outputName}_content: String = ${inputNames[0]}_typed_content")
                        target.appendLine("${indention}val ${outputName}_type: String = \"http://www.w3.org/2001/XMLSchema#string\"")
                        onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "UCASE",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]}.toUpperCase()")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        target.appendLine("${indention}val ${outputName}_content: String = ${inputNames[0]}_content.toUpperCase()")
                        target.appendLine("${indention}val ${outputName}_type: String = ${inputNames[0]}_type")
                        onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        target.appendLine("${indention}val ${outputName}_content: String = ${inputNames[0]}_content.toUpperCase()")
                        target.appendLine("${indention}val ${outputName}_lang: String = ${inputNames[0]}_lang")
                        onResult(indention, ETripleComponentTypeExt.STRING_LANG)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "Double",
        functionname = "<http://www.w3.org/2001/XMLSchema#double>",
        type = OperatorType.FunctionCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: Double = ${inputNames[0]}")
                        onResult(indention, ETripleComponentTypeExt.DOUBLE)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Double = ${inputNames[0]}")
                        onResult(indention, ETripleComponentTypeExt.DOUBLE)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Double = ${inputNames[0]}.doubleValue()")
                        onResult(indention, ETripleComponentTypeExt.DOUBLE)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Double = ${inputNames[0]}.doubleValue()")
                        onResult(indention, ETripleComponentTypeExt.DOUBLE)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.BOOLEAN),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Double = if (${inputNames[0]}) {")
                        target.appendLine("$indention    1.0")
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    0.0")
                        target.appendLine("$indention}")
                        onResult(indention, ETripleComponentTypeExt.DOUBLE)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}try {")
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]}.toDouble()")
                        onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                        target.appendLine("$indention} catch (e: Throwable) {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention}")
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}try {")
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]}_content.toDouble()")
                        onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                        target.appendLine("$indention} catch (e: Throwable) {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention}")
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}try {")
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]}_content.toDouble()")
                        onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                        target.appendLine("$indention} catch (e: Throwable) {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention}")
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "Float",
        functionname = "<http://www.w3.org/2001/XMLSchema#float>",
        type = OperatorType.FunctionCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: Double = ${inputNames[0]}")
                        onResult(indention, ETripleComponentTypeExt.FLOAT)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Double = ${inputNames[0]}")
                        onResult(indention, ETripleComponentTypeExt.FLOAT)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Double = ${inputNames[0]}.doubleValue()")
                        onResult(indention, ETripleComponentTypeExt.FLOAT)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Double = ${inputNames[0]}.doubleValue()")
                        onResult(indention, ETripleComponentTypeExt.FLOAT)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.BOOLEAN),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Double = if (${inputNames[0]}) {")
                        target.appendLine("$indention    1.0")
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    0.0")
                        target.appendLine("$indention}")
                        onResult(indention, ETripleComponentTypeExt.FLOAT)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}try {")
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]}.toDouble()")
                        onResult(indention + "    ", ETripleComponentTypeExt.FLOAT)
                        target.appendLine("$indention} catch (e: Throwable) {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention}")
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}try {")
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]}_content.toDouble()")
                        onResult(indention + "    ", ETripleComponentTypeExt.FLOAT)
                        target.appendLine("$indention} catch (e: Throwable) {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention}")
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}try {")
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]}_content.toDouble()")
                        onResult(indention + "    ", ETripleComponentTypeExt.FLOAT)
                        target.appendLine("$indention} catch (e: Throwable) {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention}")
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "DAY",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: BigInteger = ${inputNames[0]}_day")
                        onResult(indention, ETripleComponentTypeExt.INTEGER)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "MONTH",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: BigInteger = ${inputNames[0]}_month")
                        onResult(indention, ETripleComponentTypeExt.INTEGER)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "HOURS",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: BigInteger = ${inputNames[0]}_hours")
                        onResult(indention, ETripleComponentTypeExt.INTEGER)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "MINUTES",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: BigInteger = ${inputNames[0]}_minutes")
                        onResult(indention, ETripleComponentTypeExt.INTEGER)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "YEAR",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                        target.appendLine("${indention}val $outputName: BigInteger = ${inputNames[0]}_year")
                        onResult(indention, ETripleComponentTypeExt.INTEGER)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "SECONDS",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                        target.appendLine("${indention}val $outputName: BigDecimal = ${inputNames[0]}_seconds")
                        onResult(indention, ETripleComponentTypeExt.DECIMAL)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "TZ",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]}_tz")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "TIMEZONE",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]}_timezone")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "STRDT",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.IRI),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val ${outputName}_content: String = ${inputNames[0]}")
                        target.appendLine("${indention}val ${outputName}_type: String = ${inputNames[1]}")
                        onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "STRLANG",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val ${outputName}_content: String = ${inputNames[0]}")
                        target.appendLine("${indention}val ${outputName}_lang: String = ${inputNames[1]}")
                        onResult(indention, ETripleComponentTypeExt.STRING_LANG)
                    },
                ),
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "LANGMATCHES",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Boolean = ${inputNames[0]} == ${inputNames[1]}")
                        onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                    },
                ),
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "STRAFTER",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, onResult ->
                        target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}.indexOf(${inputNames[1]})")
                        target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                        target.appendLine("$indention    val $outputName: String = ${inputNames[0]}.substring(${prefix}_idx + ${inputNames[1]}.length, ${inputNames[0]}.length)")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: String = \"\"")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING)
                        target.appendLine("$indention}")
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG),
                    generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, onResult ->
                        target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}.indexOf(${inputNames[1]}_content)")
                        target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                        target.appendLine("$indention    val $outputName: String = ${inputNames[0]}.substring(${prefix}_idx + ${inputNames[1]}_content.length, ${inputNames[0]}.length)")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: String = \"\"")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING)
                        target.appendLine("$indention}")
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_TYPED),
                    generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, onResult ->
                        target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}.indexOf(${inputNames[1]}_content)")
                        target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                        target.appendLine("$indention    val $outputName: String = ${inputNames[0]}.substring(${prefix}_idx + ${inputNames[1]}_content.length, ${inputNames[0]}.length)")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: String = \"\"")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING)
                        target.appendLine("$indention}")
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, onResult ->
                        target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]})")
                        target.appendLine("${indention}val ${outputName}_lang: String = ${inputNames[0]}_lang")
                        target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                        target.appendLine("$indention    val ${outputName}_content: String = ${inputNames[0]}_content.substring(${prefix}_idx + ${inputNames[1]}.length, ${inputNames[0]}_content.length)")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_LANG)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val ${outputName}_content: String = \"\"")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_LANG)
                        target.appendLine("$indention}")
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_LANG),
                    generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, onResult ->
                        target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]}_content)")
                        target.appendLine("${indention}val ${outputName}_lang: String = ${inputNames[0]}_lang")
                        target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                        target.appendLine("$indention    val ${outputName}_content: String = ${inputNames[0]}_content.substring(${prefix}_idx + ${inputNames[1]}_content.length, ${inputNames[0]}_content.length)")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_LANG)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val ${outputName}_content: String = \"\"")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_LANG)
                        target.appendLine("$indention}")
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED),
                    generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, onResult ->
                        target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]}_content)")
                        target.appendLine("${indention}val ${outputName}_lang: String = ${inputNames[0]}_lang")
                        target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                        target.appendLine("$indention    val ${outputName}_content: String = ${inputNames[0]}_content.substring(${prefix}_idx + ${inputNames[1]}_content.length, ${inputNames[0]}_content.length)")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_LANG)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val ${outputName}_content: String = \"\"")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_LANG)
                        target.appendLine("$indention}")
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, onResult ->
                        target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]})")
                        target.appendLine("${indention}val ${outputName}_type: String = ${inputNames[0]}_type")
                        target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                        target.appendLine("$indention    val ${outputName}_content: String = ${inputNames[0]}_content.substring(${prefix}_idx + ${inputNames[1]}.length, ${inputNames[0]}_content.length)")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_TYPED)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val ${outputName}_content: String = \"\"")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_TYPED)
                        target.appendLine("$indention}")
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING_LANG),
                    generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, onResult ->
                        target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]}_content)")
                        target.appendLine("${indention}val ${outputName}_type: String = ${inputNames[0]}_type")
                        target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                        target.appendLine("$indention    val ${outputName}_content: String = ${inputNames[0]}_content.substring(${prefix}_idx + ${inputNames[1]}_content.length, ${inputNames[0]}_content.length)")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_TYPED)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val ${outputName}_content: String = \"\"")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_TYPED)
                        target.appendLine("$indention}")
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING_TYPED),
                    generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, onResult ->
                        target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]}_content)")
                        target.appendLine("${indention}val ${outputName}_type: String = ${inputNames[0]}_type")
                        target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                        target.appendLine("$indention    val ${outputName}_content: String = ${inputNames[0]}_content.substring(${prefix}_idx + ${inputNames[1]}_content.length, ${inputNames[0]}_content.length)")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_TYPED)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val ${outputName}_content: String = \"\"")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_TYPED)
                        target.appendLine("$indention}")
                    },
                ),
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "STRBEFORE",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, onResult ->
                        target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}.indexOf(${inputNames[1]})")
                        target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                        target.appendLine("$indention    val $outputName: String = ${inputNames[0]}.substring(0, ${prefix}_idx)")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: String = \"\"")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING)
                        target.appendLine("$indention}")
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG),
                    generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, onResult ->
                        target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}.indexOf(${inputNames[1]}_content)")
                        target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                        target.appendLine("$indention    val $outputName: String = ${inputNames[0]}.substring(0, ${prefix}_idx)")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: String = \"\"")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING)
                        target.appendLine("$indention}")
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_TYPED),
                    generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, onResult ->
                        target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}.indexOf(${inputNames[1]}_content)")
                        target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                        target.appendLine("$indention    val $outputName: String = ${inputNames[0]}.substring(0, ${prefix}_idx)")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: String = \"\"")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING)
                        target.appendLine("$indention}")
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, onResult ->
                        target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]})")
                        target.appendLine("${indention}val ${outputName}_lang: String = ${inputNames[0]}_lang")
                        target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                        target.appendLine("$indention    val ${outputName}_content: String = ${inputNames[0]}_content.substring(0, ${prefix}_idx)")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_LANG)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val ${outputName}_content: String = \"\"")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_LANG)
                        target.appendLine("$indention}")
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_LANG),
                    generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, onResult ->
                        target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]}_content)")
                        target.appendLine("${indention}val ${outputName}_lang: String = ${inputNames[0]}_lang")
                        target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                        target.appendLine("$indention    val ${outputName}_content: String = ${inputNames[0]}_content.substring(0, ${prefix}_idx)")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_LANG)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val ${outputName}_content: String = \"\"")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_LANG)
                        target.appendLine("$indention}")
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED),
                    generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, onResult ->
                        target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]}_content)")
                        target.appendLine("${indention}val ${outputName}_lang: String = ${inputNames[0]}_lang")
                        target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                        target.appendLine("$indention    val ${outputName}_content: String = ${inputNames[0]}_content.substring(0, ${prefix}_idx)")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_LANG)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val ${outputName}_content: String = \"\"")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_LANG)
                        target.appendLine("$indention}")
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, onResult ->
                        target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]})")
                        target.appendLine("${indention}val ${outputName}_type: String = ${inputNames[0]}_type")
                        target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                        target.appendLine("$indention    val ${outputName}_content: String = ${inputNames[0]}_content.substring(0, ${prefix}_idx)")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_TYPED)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val ${outputName}_content: String = \"\"")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_TYPED)
                        target.appendLine("$indention}")
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING_LANG),
                    generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, onResult ->
                        target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]}_content)")
                        target.appendLine("${indention}val ${outputName}_type: String = ${inputNames[0]}_type")
                        target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                        target.appendLine("$indention    val ${outputName}_content: String = ${inputNames[0]}_content.substring(0, ${prefix}_idx)")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_TYPED)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val ${outputName}_content: String = \"\"")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_TYPED)
                        target.appendLine("$indention}")
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING_TYPED),
                    generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, onResult ->
                        target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]}_content)")
                        target.appendLine("${indention}val ${outputName}_type: String = ${inputNames[0]}_type")
                        target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                        target.appendLine("$indention    val ${outputName}_content: String = ${inputNames[0]}_content.substring(0, ${prefix}_idx)")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_TYPED)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val ${outputName}_content: String = \"\"")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_TYPED)
                        target.appendLine("$indention}")
                    },
                ),
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "STRENDS",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Boolean = ${inputNames[0]}.endsWith(${inputNames[1]})")
                        onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Boolean = ${inputNames[0]}_content.endsWith(${inputNames[1]})")
                        onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Boolean = ${inputNames[0]}_content.endsWith(${inputNames[1]})")
                        onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                    },
                ),
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "STRSTARTS",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Boolean = ${inputNames[0]}.startsWith(${inputNames[1]})")
                        onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Boolean = ${inputNames[0]}_content.startsWith(${inputNames[1]})")
                        onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Boolean = ${inputNames[0]}_content.startsWith(${inputNames[1]})")
                        onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "CONTAINS",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Boolean = ${inputNames[0]}.contains(${inputNames[1]})")
                        onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Boolean = ${inputNames[0]}_content.contains(${inputNames[1]})")
                        onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Boolean = ${inputNames[0]}_content.contains(${inputNames[1]})")
                        onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "CONCAT",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]} + ${inputNames[1]}")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]} + ${inputNames[1]}_content")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]}_content + ${inputNames[1]}")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_TYPED),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]} + ${inputNames[1]}_content")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]}_content + ${inputNames[1]}")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]}_content + ${inputNames[1]}_content")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING_LANG),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]}_content + ${inputNames[1]}_content")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_LANG),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}if (${inputNames[0]}_lang == ${inputNames[1]}_lang) {")
                        target.appendLine("$indention    val ${outputName}_content: String = ${inputNames[0]}_content + ${inputNames[1]}_content")
                        target.appendLine("$indention    val ${outputName}_lang: String = ${inputNames[0]}_lang")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_LANG)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: String = ${inputNames[0]}_content + ${inputNames[1]}_content")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING)
                        target.appendLine("$indention}")
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING_TYPED),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}if (${inputNames[0]}_type == \"http://www.w3.org/2001/XMLSchema#string\" && ${inputNames[1]}_type == \"http://www.w3.org/2001/XMLSchema#string\") {")
                        target.appendLine("$indention    val ${outputName}_content: String = ${inputNames[0]}_content + ${inputNames[1]}_content")
                        target.appendLine("$indention    val ${outputName}_type: String = \"http://www.w3.org/2001/XMLSchema#string\"")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING_TYPED)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: String = ${inputNames[0]}_content + ${inputNames[1]}_content")
                        onResult(indention + "    ", ETripleComponentTypeExt.STRING)
                        target.appendLine("$indention}")
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "Not",
        type = OperatorType.Basic,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.BOOLEAN),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Boolean = !${inputNames[0]}")
                        onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "And",
        type = OperatorType.Basic,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.BOOLEAN),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Boolean = ${inputNames[0]} && ${inputNames[1]}")
                        onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.ERROR),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}if (${inputNames[0]}) {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: Boolean = false")
                        onResult(indention + "    ", ETripleComponentTypeExt.BOOLEAN)
                        target.appendLine("$indention}")
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.BOOLEAN),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}if (${inputNames[1]}) {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: Boolean = false")
                        onResult(indention + "    ", ETripleComponentTypeExt.BOOLEAN)
                        target.appendLine("$indention}")
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "Or",
        type = OperatorType.Basic,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.BOOLEAN),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Boolean = ${inputNames[0]} || ${inputNames[1]}")
                        onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.ERROR),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}if (${inputNames[0]}) {")
                        target.appendLine("$indention    val $outputName: Boolean = true")
                        onResult(indention + "    ", ETripleComponentTypeExt.BOOLEAN)
                        target.appendLine("$indention} else {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention}")
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.BOOLEAN),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}if (${inputNames[1]}) {")
                        target.appendLine("$indention    val $outputName: Boolean = true")
                        onResult(indention + "    ", ETripleComponentTypeExt.BOOLEAN)
                        target.appendLine("$indention} else {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention}")
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "MD5",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("lupos.shared.Crypto")
                        target.appendLine("${indention}val $outputName: String = Crypto.md5(${inputNames[0]})")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("lupos.shared.Crypto")
                        target.appendLine("${indention}val $outputName: String = Crypto.md5(${inputNames[0]}_content)")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("lupos.shared.Crypto")
                        target.appendLine("${indention}val $outputName: String = Crypto.md5(${inputNames[0]}_content)")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "SHA1",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("lupos.shared.Crypto")
                        target.appendLine("${indention}val $outputName: String = Crypto.sha1(${inputNames[0]})")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("lupos.shared.Crypto")
                        target.appendLine("${indention}val $outputName: String = Crypto.sha1(${inputNames[0]}_content)")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("lupos.shared.Crypto")
                        target.appendLine("${indention}val $outputName: String = Crypto.sha1(${inputNames[0]}_content)")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "SHA256",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("lupos.shared.Crypto")
                        target.appendLine("${indention}val $outputName: String = Crypto.sha256(${inputNames[0]})")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("lupos.shared.Crypto")
                        target.appendLine("${indention}val $outputName: String = Crypto.sha256(${inputNames[0]}_content)")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        imports.add("lupos.shared.Crypto")
                        target.appendLine("${indention}val $outputName: String = Crypto.sha256(${inputNames[0]}_content)")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "IsIri",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.ERROR),
                    generateInstantiated = generateInstantiatedError2,
                    generateByteArrayWrapper = generateByteArrayWrapperError2,
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.UNDEF),
                    generateInstantiated = generateInstantiatedError2,
                    generateByteArrayWrapper = generateByteArrayWrapperError2,
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.IRI),
                    generateInstantiated = generateInstantiatedTrue2,
                    generateByteArrayWrapper = generateByteArrayWrapperTrue2,
                )
            ).setOther(generateInstantiatedFalse2, generateByteArrayWrapperFalse2, ).build(),
        generateInstantiatedOther = generateInstantiatedFalse,
        generateIDOther = generateIDFalse,
        generateByteArrayWrapperOther = generateByteArrayWrapperFalse,
    )
)
operators.add(
    MyOperator(
        name = "IsLITERAL",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.ERROR),
                    generateInstantiated = generateInstantiatedError2,
                    generateByteArrayWrapper = generateByteArrayWrapperError2,
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.UNDEF),
                    generateInstantiated = generateInstantiatedError2,
                    generateByteArrayWrapper = generateByteArrayWrapperError2,
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.IRI),
                    generateInstantiated = generateInstantiatedFalse2,
                    generateByteArrayWrapper = generateByteArrayWrapperFalse2,
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.BLANK_NODE),
                    generateInstantiated = generateInstantiatedFalse2,
                    generateByteArrayWrapper = generateByteArrayWrapperFalse2,
                )
            ).setOther(generateInstantiatedTrue2, generateByteArrayWrapperTrue2, ).build(),
        generateInstantiatedOther = generateInstantiatedTrue,
        generateIDOther = generateIDTrue,
        generateByteArrayWrapperOther = generateByteArrayWrapperTrue,
    )
)
operators.add(
    MyOperator(
        name = "BNODE1",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.ERROR),
                    generateInstantiated = { indention, _, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Int = query.getDictionary().createNewBNode(\"ERROR_ERROR\")")
                        onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.UNDEF),
                    generateInstantiated = { indention, _, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Int = query.getDictionary().createNewBNode(\"UNDEF_UNDEF\")")
                        onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.BLANK_NODE),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Int = query.getDictionary().createNewBNode(\"BLANK_NODE_\${${inputNames[0]}}\")")
                        onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.BOOLEAN),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Int = query.getDictionary().createNewBNode(\"BOOLEAN_\${${inputNames[0]}}\")")
                        onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Int = query.getDictionary().createNewBNode(\"DECIMAL_\${${inputNames[0]}.toString()}\")")
                        onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Int = query.getDictionary().createNewBNode(\"DOUBLE_\${${inputNames[0]}.toString()}\")")
                        onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Int = query.getDictionary().createNewBNode(\"FLOAT_\${${inputNames[0]}.toString()}\")")
                        onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Int = query.getDictionary().createNewBNode(\"INTEGER_\${${inputNames[0]}.toString()}\")")
                        onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.IRI),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Int = query.getDictionary().createNewBNode(\"IRI_\${${inputNames[0]}.toString()}\")")
                        onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Int = query.getDictionary().createNewBNode(\"STRING_\${${inputNames[0]}.toString()}\")")
                        onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Int = query.getDictionary().createNewBNode(\"STRING_LANG_\${${inputNames[0]}_content.toString()}_\${${inputNames[0]}_lang.toString()}\")")
                        onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: Int = query.getDictionary().createNewBNode(\"STRING_TYPED_\${${inputNames[0]}_content.toString()}_\${${inputNames[0]}_type.toString()}\")")
                        onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "IsNUMERIC",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .addForEachChildrenType(
                arrayOf(
                    arrayOf(ETripleComponentTypeExt.ERROR),
                    arrayOf(ETripleComponentTypeExt.UNDEF),
                ),
                generateInstantiated = generateInstantiatedError2,
                generateByteArrayWrapper = generateByteArrayWrapperError2,
            )
            .addForEachChildrenType(
                arrayOf(
                    arrayOf(ETripleComponentTypeExt.INTEGER),
                    arrayOf(ETripleComponentTypeExt.DECIMAL),
                    arrayOf(ETripleComponentTypeExt.DOUBLE),
                    arrayOf(ETripleComponentTypeExt.FLOAT),
                ),
                generateInstantiated = generateInstantiatedTrue2,
                generateByteArrayWrapper = generateByteArrayWrapperTrue2,
            ).setOther(generateInstantiatedFalse2, generateByteArrayWrapperFalse2, ).build(),
        generateInstantiatedOther = generateInstantiatedFalse,
        generateIDOther = generateIDFalse,
        generateByteArrayWrapperOther = generateByteArrayWrapperFalse,
    )
)
operators.add(
    MyOperator(
        name = "DATATYPE",
        type = OperatorType.BuildInCall,
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.BOOLEAN),
                    generateInstantiated = generateInstantiatedString("http://www.w3.org/2001/XMLSchema#boolean"),
                    generateByteArrayWrapper = generateByteArrayWrapperString("http://www.w3.org/2001/XMLSchema#boolean"),
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                    generateInstantiated = generateInstantiatedString("http://www.w3.org/2001/XMLSchema#integer"),
                    generateByteArrayWrapper = generateByteArrayWrapperString("http://www.w3.org/2001/XMLSchema#integer"),
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                    generateInstantiated = generateInstantiatedString("http://www.w3.org/2001/XMLSchema#double"),
                    generateByteArrayWrapper = generateByteArrayWrapperString("http://www.w3.org/2001/XMLSchema#double"),
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                    generateInstantiated = generateInstantiatedString("http://www.w3.org/2001/XMLSchema#decimal"),
                    generateByteArrayWrapper = generateByteArrayWrapperString("http://www.w3.org/2001/XMLSchema#decimal"),
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                    generateInstantiated = generateInstantiatedString("http://www.w3.org/2001/XMLSchema#float"),
                    generateByteArrayWrapper = generateByteArrayWrapperString("http://www.w3.org/2001/XMLSchema#float"),
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                    generateInstantiated = generateInstantiatedString("http://www.w3.org/2001/XMLSchema#string"),
                    generateByteArrayWrapper = generateByteArrayWrapperString("http://www.w3.org/2001/XMLSchema#string"),
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                    generateInstantiated = generateInstantiatedString("http://www.w3.org/1999/02/22-rdf-syntax-ns#langString"),
                    generateByteArrayWrapper = generateByteArrayWrapperString("http://www.w3.org/1999/02/22-rdf-syntax-ns#langString"),
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                    generateInstantiated = generateInstantiatedString("http://www.w3.org/2001/XMLSchema#dateTime"),
                    generateByteArrayWrapper = generateByteArrayWrapperString("http://www.w3.org/2001/XMLSchema#dateTime"),
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]}_type")
                        onResult(indention, ETripleComponentTypeExt.STRING)
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "IRI",
        type = OperatorType.BuildInCall,
        additionalParametersDefinition = arrayOf("@JvmField public var prefix: String"),
        imports = mutableSetOf("kotlin.jvm.JvmField"),
        additionalParametersName = arrayOf("prefix"),
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.IRI),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]}")
                        onResult(indention, ETripleComponentTypeExt.IRI)
                    },
                    generateByteArrayWrapper = { indention, inputNames, outputName, _, imports, target, globalVariables, onResult ->
                        imports.add("lupos.shared.ByteArrayWrapper")
                        globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
                        target.appendLine("${indention}${inputNames[0]}.copyInto($outputName)")
                        onResult(indention, ETripleComponentTypeExt.IRI)
                    }
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: String = if (prefix.length > 0 && !prefix.endsWith('/')) {")
                        target.appendLine("$indention    \"\$prefix/\$${inputNames[0]}\"")
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    \"\$prefix\$${inputNames[0]}\"")
                        target.appendLine("$indention}")
                        onResult(indention, ETripleComponentTypeExt.IRI)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}if (${inputNames[0]}_type == \"http://www.w3.org/2001/XMLSchema#string\") {")
                        target.appendLine("$indention    val $outputName: String = if (prefix.length > 0 && !prefix.endsWith('/')) {")
                        target.appendLine("$indention        \"\$prefix/\$${inputNames[0]}_content\"")
                        target.appendLine("$indention    } else {")
                        target.appendLine("$indention        \"\$prefix\$${inputNames[0]}_content\"")
                        target.appendLine("$indention    }")
                        onResult("$indention    ", ETripleComponentTypeExt.IRI)
                        target.appendLine("$indention} else {")
                        onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention}")
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "URI",
        type = OperatorType.BuildInCall,
        additionalParametersDefinition = arrayOf("@JvmField public var prefix: String"),
        imports = mutableSetOf("kotlin.jvm.JvmField"),
        additionalParametersName = arrayOf("prefix"),
        implementations = MyOperatorPartFactory()
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.IRI),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: String = ${inputNames[0]}")
                        onResult(indention, ETripleComponentTypeExt.IRI)
                    },
                    generateByteArrayWrapper = { indention, inputNames, outputName, _, imports, target, globalVariables, onResult ->
                        imports.add("lupos.shared.ByteArrayWrapper")
                        globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
                        target.appendLine("${indention}${inputNames[0]}.copyInto($outputName)")
                        onResult(indention, ETripleComponentTypeExt.IRI)
                    }
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}val $outputName: String = if (prefix.length > 0 && !prefix.endsWith('/')) {")
                        target.appendLine("$indention    \"\$prefix/\$${inputNames[0]}\"")
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    \"\$prefix\$${inputNames[0]}\"")
                        target.appendLine("$indention}")
                        onResult(indention, ETripleComponentTypeExt.IRI)
                    },
                )
            )
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                    generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                        target.appendLine("${indention}if (${inputNames[0]}_type == \"http://www.w3.org/2001/XMLSchema#string\") {")
                        target.appendLine("$indention    val $outputName: String = if (prefix.length > 0 && !prefix.endsWith('/')) {")
                        target.appendLine("$indention        \"\$prefix/\$${inputNames[0]}_content\"")
                        target.appendLine("$indention    } else {")
                        target.appendLine("$indention        \"\$prefix\$${inputNames[0]}_content\"")
                        target.appendLine("$indention    }")
                        onResult("$indention    ", ETripleComponentTypeExt.IRI)
                        target.appendLine("$indention} else {")
                        onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention}")
                    },
                )
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "Division",
        type = OperatorType.Basic,
        additionalParametersName = arrayOf("prefix"),
        implementations = MyOperatorPartFactory()
            .addNumericBinaryOperatorStub("/")
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.INTEGER),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        target.appendLine("${indention}if (${inputNames[1]} == BigInteger.ZERO) {")
                        imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: BigDecimal = BigDecimal.fromBigInteger(${inputNames[0]}) / BigDecimal.fromBigInteger(${inputNames[1]})")
                        onResult(indention + "    ", ETripleComponentTypeExt.DECIMAL)
                        target.appendLine("$indention}")
                    },
                ),
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "Addition",
        type = OperatorType.Basic,
        additionalParametersName = arrayOf("prefix"),
        implementations = MyOperatorPartFactory()
            .addNumericBinaryOperatorStub("+")
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.INTEGER),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        target.appendLine("${indention}if (${inputNames[1]} == BigInteger.ZERO) {")
                        imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: BigInteger = ${inputNames[0]} + ${inputNames[1]}")
                        onResult(indention + "    ", ETripleComponentTypeExt.INTEGER)
                        target.appendLine("$indention}")
                    },
                ),
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "Subtraction",
        type = OperatorType.Basic,
        additionalParametersName = arrayOf("prefix"),
        implementations =
        MyOperatorPartFactory()
            .addNumericBinaryOperatorStub("-")
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.INTEGER),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        target.appendLine("${indention}if (${inputNames[1]} == BigInteger.ZERO) {")
                        imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: BigInteger = ${inputNames[0]} - ${inputNames[1]}")
                        onResult(indention + "    ", ETripleComponentTypeExt.INTEGER)
                        target.appendLine("$indention}")
                    },
                ),
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)
operators.add(
    MyOperator(
        name = "Multiplication",
        type = OperatorType.Basic,
        additionalParametersName = arrayOf("prefix"),
        implementations = MyOperatorPartFactory()
            .addNumericBinaryOperatorStub("*")
            .add(
                MyOperatorPart(
                    childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.INTEGER),
                    generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                        target.appendLine("${indention}if (${inputNames[1]} == BigInteger.ZERO) {")
                        imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: BigInteger = ${inputNames[0]} * ${inputNames[1]}")
                        onResult(indention + "    ", ETripleComponentTypeExt.INTEGER)
                        target.appendLine("$indention}")
                    },
                ),
            ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2, ).build(),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    )
)

public val converters = listOf(
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.INTEGER,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.dictionary.DictionaryHelper")
            imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
            target.appendLine("${indention}val $outputName: BigInteger = DictionaryHelper.byteArrayToInteger_I($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.INTEGER,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.shared.ByteArrayWrapper")
            imports.add("lupos.dictionary.DictionaryHelper")
            globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.integerToByteArray($outputName, $inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.DECIMAL,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.dictionary.DictionaryHelper")
            imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
            target.appendLine("${indention}val $outputName: BigDecimal = DictionaryHelper.byteArrayToDecimal_I($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.DECIMAL,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.shared.ByteArrayWrapper")
            imports.add("lupos.dictionary.DictionaryHelper")
            globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.decimalToByteArray($outputName, $inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.DOUBLE,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.dictionary.DictionaryHelper")
            target.appendLine("${indention}val $outputName: Double = DictionaryHelper.byteArrayToDouble_I($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.DOUBLE,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.shared.ByteArrayWrapper")
            imports.add("lupos.dictionary.DictionaryHelper")
            globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.doubleToByteArray($outputName, $inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.FLOAT,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.dictionary.DictionaryHelper")
            target.appendLine("${indention}val $outputName: Double = DictionaryHelper.byteArrayToFloat_I($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.STRING_TYPED,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.dictionary.DictionaryHelper")
            target.appendLine("${indention}val ${outputName}_content: String = DictionaryHelper.byteArrayToTyped_Content($inputName)")
            target.appendLine("${indention}val ${outputName}_type: String = DictionaryHelper.byteArrayToTyped_Type($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.STRING_LANG,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.dictionary.DictionaryHelper")
            target.appendLine("${indention}val ${outputName}_content: String = DictionaryHelper.byteArrayToLang_Content($inputName)")
            target.appendLine("${indention}val ${outputName}_lang: String = DictionaryHelper.byteArrayToLang_Lang($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.IRI,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.dictionary.DictionaryHelper")
            target.appendLine("${indention}val $outputName: String = DictionaryHelper.byteArrayToIri($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.STRING,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.dictionary.DictionaryHelper")
            target.appendLine("${indention}val $outputName: String = DictionaryHelper.byteArrayToString($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.BLANK_NODE,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.dictionary.DictionaryHelper")
            target.appendLine("${indention}val $outputName: Int = DictionaryHelper.byteArrayToBnode_I($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.BOOLEAN,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.dictionary.DictionaryHelper")
            target.appendLine("${indention}val $outputName: Boolean = DictionaryHelper.byteArrayToBoolean($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.BLANK_NODE,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.shared.ByteArrayWrapper")
            imports.add("lupos.dictionary.DictionaryHelper")
            globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.bnodeToByteArray($outputName, $inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.STRING,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.shared.ByteArrayWrapper")
            imports.add("lupos.dictionary.DictionaryHelper")
            globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.stringToByteArray($outputName, $inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.STRING_LANG,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.shared.ByteArrayWrapper")
            imports.add("lupos.dictionary.DictionaryHelper")
            globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.langToByteArray($outputName, ${inputName}_content, ${inputName}_lang)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.STRING_TYPED,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.shared.ByteArrayWrapper")
            imports.add("lupos.dictionary.DictionaryHelper")
            globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.langToByteArray($outputName, ${inputName}_content, ${inputName}_type)")
        }
    ),

    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.IRI,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.shared.ByteArrayWrapper")
            imports.add("lupos.dictionary.DictionaryHelper")
            globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.iriToByteArray($outputName, $inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.FLOAT,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.shared.ByteArrayWrapper")
            imports.add("lupos.dictionary.DictionaryHelper")
            globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.floatToByteArray($outputName, $inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.ERROR,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { _, _, _, _, _, _ ->
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.UNDEF,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { _, _, _, _, _, _ ->
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.BOOLEAN,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.shared.ByteArrayWrapper")
            imports.add("lupos.dictionary.DictionaryHelper")
            globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.booleanToByteArray($outputName, $inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.ERROR,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, _, outputName, imports, target, globalVariables ->
            imports.add("lupos.shared.ByteArrayWrapper")
            imports.add("lupos.dictionary.DictionaryHelper")
            globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.errorToByteArray($outputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.DATE_TIME,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.dictionary.DictionaryHelper")
            imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
            imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
            target.appendLine("${indention}val ${outputName}_typed_content: String = DictionaryHelper.byteArrayToDateTimeAsTyped_Content($inputName)")
            target.appendLine("${indention}val ${outputName}_year: BigInteger = DictionaryHelper.byteArrayToDateTime_Year($inputName)")
            target.appendLine("${indention}val ${outputName}_month: BigInteger = DictionaryHelper.byteArrayToDateTime_Month($inputName)")
            target.appendLine("${indention}val ${outputName}_day: BigInteger = DictionaryHelper.byteArrayToDateTime_Day($inputName)")
            target.appendLine("${indention}val ${outputName}_hours: BigInteger = DictionaryHelper.byteArrayToDateTime_Hours($inputName)")
            target.appendLine("${indention}val ${outputName}_minutes: BigInteger = DictionaryHelper.byteArrayToDateTime_Minutes($inputName)")
            target.appendLine("${indention}val ${outputName}_seconds: BigDecimal = DictionaryHelper.byteArrayToDateTime_Seconds($inputName)")
            target.appendLine("${indention}val ${outputName}_tz: String = DictionaryHelper.byteArrayToDateTime_TZ($inputName)")
            target.appendLine("${indention}val ${outputName}_timezone: String = DictionaryHelper.byteArrayToDateTime_TimeZone($inputName)")
        }
    ),
)

fun getRepresentationConversionFunction(type: ETripleComponentType, inputRepresentation: EParamRepresentation, outputRepresentation: EParamRepresentation): MyRepresentationConversionFunction {
    for (converter in converters) {
        if (converter.type == type && converter.inputRepresentation == inputRepresentation && converter.outputRepresentation == outputRepresentation) {
            return converter
        }
    }
    throw Exception("not found ${ETripleComponentTypeExt.names[type]} $inputRepresentation $outputRepresentation")
}
for (operator in operators) {
    File("src/luposdate3000_operator_arithmetik/src/commonMain/kotlin/lupos/s04arithmetikOperators/generated/AOP${operator.type.str}${operator.name}.kt").printWriter().use { out ->
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
