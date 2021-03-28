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
@file:Import("../luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/ETripleComponentType.kt")
@file:Import("../luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/ETripleComponentTypeExt.kt")

import lupos.s00misc.ETripleComponentType
import lupos.s00misc.ETripleComponentTypeExt
import java.io.File

public enum class OperatorType {
    BuildInCall,
    Function,
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
    imports.add("lupos.s00misc.ByteArrayWrapper")
    imports.add("lupos.dictionary.DictionaryHelper")
    globalVariables.add("val $outputName = ByteArrayWrapper()")
    target.appendLine("${indention}DictionaryHelper.errorToByteArray($outputName)")
    onResult(indention, ETripleComponentTypeExt.ERROR)
}
public val generateInstantiatedTrue2: GenerateFunc = { indention, _, outputName, _, _, target, _, onResult ->
    target.appendLine("$indention$outputName = true")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}

public val generateByteArrayWrapperTrue2: GenerateFunc = { indention, _, outputName, _, imports, target, globalVariables, onResult ->
    imports.add("lupos.s00misc.ByteArrayWrapper")
    imports.add("lupos.dictionary.DictionaryHelper")
    globalVariables.add("val $outputName = ByteArrayWrapper()")
    target.appendLine("${indention}DictionaryHelper.booleanToByteArray($outputName, true)")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}
public val generateInstantiatedFalse2: GenerateFunc = { indention, _, outputName, _, _, target, _, onResult ->
    target.appendLine("$indention$outputName = false")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}

public val generateByteArrayWrapperFalse2: GenerateFunc = { indention, _, outputName, _, imports, target, globalVariables, onResult ->
    imports.add("lupos.s00misc.ByteArrayWrapper")
    imports.add("lupos.dictionary.DictionaryHelper")
    globalVariables.add("val $outputName = ByteArrayWrapper()")
    target.appendLine("${indention}DictionaryHelper.booleanToByteArray($outputName, false)")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}

fun generateByteArrayWrapperString(str: String): GenerateFunc = { indention, _, outputName, _, imports, target, globalVariables, onResult ->
    imports.add("lupos.s00misc.ByteArrayWrapper")
    imports.add("lupos.dictionary.DictionaryHelper")
    globalVariables.add("val $outputName = ByteArrayWrapper()")
    target.appendLine("${indention}DictionaryHelper.stringToByteArray($outputName, \"$str\")")
    onResult(indention, ETripleComponentTypeExt.STRING)
}

fun generateInstantiatedString(str: String): GenerateFunc = { indention, _, outputName, _, _, target, _, onResult ->
    target.appendLine("$indention$outputName = \"$str\"")
    onResult(indention, ETripleComponentTypeExt.STRING)
}

public class MyOperator(
    public val name: String,
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
            globalVariables.add("var $outputName : Int")
            for (i in 0 until inputNames.size) {
                myInputNames[i] = "${prefix}_${prefix_counter++}"
                imports.add("lupos.s00misc.ByteArrayWrapper")
                globalVariables.add("val ${myInputNames[i]} = ByteArrayWrapper()")
                target.appendLine("${indention}query.getDictionary().getValue(${myInputNames[i]}, ${inputNames[i]})")
            }
        } else {
            globalVariables.add("val $outputName = ByteArrayWrapper()")
        }
        var typeNames = Array<String>(inputNames.size) { "${prefix}_${prefix_counter++}" }
        for (i in 0 until inputNames.size) {
            target.appendLine("${indention}val ${typeNames[i]} = DictionaryHelper.byteArrayToType(${myInputNames[i]})")
        }
        var myOutputName = outputName
        if (representation == EParamRepresentation.ID) {
            myOutputName = "${prefix}_${prefix_counter++}"
        }
        var first = true
        implementations.sort()
        for (implementation in implementations) {
            val generateByteArrayWrapper = implementation.generateByteArrayWrapper
            var cond: String
            if (first) {
                cond = "if ("
            } else {
                cond = "} else if ("
            }
            first = false
            var firstCond = true
            for (i in 0 until inputNames.size) {
                if (firstCond) {
                } else {
                    cond += " && "
                }
                firstCond = false
                imports.add("lupos.s00misc.ETripleComponentTypeExt")
                cond += "${typeNames[i]} == ETripleComponentTypeExt.${ETripleComponentTypeExt.names[implementation.childrenTypes[i]]}"
            }
            cond += ") {"
            target.appendLine(indention + cond)
            if (generateByteArrayWrapper != null) {
                generateByteArrayWrapper(indention + "    ", myInputNames, myOutputName, "${prefix}_${prefix_counter++}", imports, target, globalVariables) { _, _ ->
                }
            } else {
                var myInputInstances = Array(inputNames.size) { "${prefix}_${prefix_counter++}" }
                var myOutputInstance = "${prefix}_${prefix_counter++}"
                for (i in 0 until inputNames.size) {
                    val converter = getRepresentationConversionFunction(implementation.childrenTypes[i], EParamRepresentation.BYTEARRAYWRAPPER, EParamRepresentation.INSTANTIATED)
                    converter.generate(indention + "    ", myInputNames[i], myInputInstances[i], imports, target, globalVariables)
                }
                implementation.generateInstantiated(indention + "    ", myInputInstances, myOutputInstance, "${prefix}_${prefix_counter++}", imports, target, globalVariables) { indention2, resultType ->
                    val converter = getRepresentationConversionFunction(resultType, EParamRepresentation.INSTANTIATED, EParamRepresentation.BYTEARRAYWRAPPER)
                    converter.generate(indention2, myOutputInstance, myOutputName, imports, target, globalVariables)
                }
            }
            if (representation == EParamRepresentation.ID) {
                target.appendLine("$indention    $outputName = query.getDictionary().createValue($myOutputName)")
            }
        }
        target.appendLine("$indention} else {")
        if (representation == EParamRepresentation.ID) {
            generateIDOther(indention + "    ", outputName, "${prefix}_${prefix_counter++}", imports, target, globalVariables) { _, _ ->
            }
        } else {
            generateByteArrayWrapperOther(indention + "    ", outputName, "${prefix}_${prefix_counter++}", imports, target, globalVariables) { _, _ ->
            }
        }
        target.appendLine("$indention}")
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
        imports2.add("lupos.s00misc.EOperatorIDExt")
        for (i in imports2.toList().sorted()) {
            clazz.appendLine("import $i")
        }
        clazz.appendLine("")
        var line0 = ""
        line0 += ("public class AOP$type$name public constructor(")
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
            line2 += "children[$i].toSparql()"
            line3 += " && children[$i] == other.children[$i]"
            line4 += ", children[$i].cloneOP() as AOPBase"
        }
        for (i in 0 until additionalParametersDefinition.size) {
            line0 += " ${additionalParametersDefinition[i]},"
            line4 += ", ${additionalParametersName[i]}"
        }
        line0 += (") : AOPBase(")
        line0 += ("query, ")
        line0 += ("EOperatorIDExt.AOP$type${name}ID, ")
        line0 += ("\"AOP$type${name}\", ")
        line0 += ("arrayOf($line)) {")
        clazz.appendLine(line0)
        clazz.appendLine("    override fun toSparql(): String = \"$name(\${$line2})\"")
        clazz.appendLine("    override fun equals(other: Any?): Boolean = other is AOP$type$name$line3")
        clazz.appendLine("    override fun cloneOP(): IOPBase = AOP$type$name(query$line4)")
        clazz.appendLine("    override fun evaluateID(row: IteratorBundle): () -> Int {")
        for (v in globalVariables) {
            if (!v.contains(" res ")) {
                clazz.appendLine("        $v")
            }
        }
        for (i in 0 until implementations[0].childrenTypes.size) {
            clazz.appendLine("        val child$i = (children[$i] as AOPBase).evaluateID(row)")
        }
        clazz.appendLine("        return {")
        clazz.appendLine("            var res: Int")
        for (i in 0 until implementations[0].childrenTypes.size) {
            clazz.appendLine("            val childIn$i = child$i()")
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
    public val generateByteArrayWrapper: GenerateFunc?,
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

public val operators = listOf(
    MyOperator(
        name = "ABS",
        type = OperatorType.BuildInCall,
        implementations = arrayOf(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    target.appendLine("${indention}val $outputName = ${inputNames[0]}.abs()")
                    onResult(indention, ETripleComponentTypeExt.INTEGER)
                },
                generateByteArrayWrapper = null,
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    target.appendLine("${indention}val $outputName = ${inputNames[0]}.abs()")
                    onResult(indention, ETripleComponentTypeExt.DECIMAL)
                },
                generateByteArrayWrapper = null,
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("kotlin.math.abs")
                    target.appendLine("${indention}val $outputName = abs(${inputNames[0]})")
                    onResult(indention, ETripleComponentTypeExt.DOUBLE)
                },
                generateByteArrayWrapper = null,
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("kotlin.math.abs")
                    target.appendLine("${indention}val $outputName = abs(${inputNames[0]})")
                    onResult(indention, ETripleComponentTypeExt.FLOAT)
                },
                generateByteArrayWrapper = null,
            ),
        ),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    ),
    MyOperator(
        name = "ROUND",
        type = OperatorType.BuildInCall,
        implementations = arrayOf(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    target.appendLine("${indention}val $outputName = ${inputNames[0]}")
                    onResult(indention, ETripleComponentTypeExt.INTEGER)
                },
                generateByteArrayWrapper = { indention, inputNames, outputName, _, imports, target, globalVariables, onResult ->
                    imports.add("lupos.s00misc.ByteArrayWrapper")
                    globalVariables.add("val $outputName = ByteArrayWrapper()")
                    target.appendLine("${indention}${inputNames[0]}.copyInto($outputName)")
                    onResult(indention, ETripleComponentTypeExt.INTEGER)
                }
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    target.appendLine("${indention}val $outputName = ${inputNames[0]}.round()")
                    onResult(indention, ETripleComponentTypeExt.DECIMAL)
                },
                generateByteArrayWrapper = null,
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("kotlin.math.roundToInt")
                    target.appendLine("${indention}val $outputName = ${inputNames[0]}.roundToInt().toDouble()")
                    onResult(indention, ETripleComponentTypeExt.DOUBLE)
                },
                generateByteArrayWrapper = null,
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("kotlin.math.roundToInt")
                    target.appendLine("${indention}val $outputName = ${inputNames[0]}.roundToInt().toDouble()")
                    onResult(indention, ETripleComponentTypeExt.FLOAT)
                },
                generateByteArrayWrapper = null,
            ),
        ),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    ),
    MyOperator(
        name = "FLOOR",
        type = OperatorType.BuildInCall,
        implementations = arrayOf(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    target.appendLine("${indention}val $outputName = ${inputNames[0]}")
                    onResult(indention, ETripleComponentTypeExt.INTEGER)
                },
                generateByteArrayWrapper = { indention, inputNames, outputName, _, imports, target, globalVariables, onResult ->
                    imports.add("lupos.s00misc.ByteArrayWrapper")
                    globalVariables.add("val $outputName = ByteArrayWrapper()")
                    target.appendLine("${indention}${inputNames[0]}.copyInto($outputName)")
                    onResult(indention, ETripleComponentTypeExt.INTEGER)
                }
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    target.appendLine("${indention}val $outputName = ${inputNames[0]}.floor()")
                    onResult(indention, ETripleComponentTypeExt.DECIMAL)
                },
                generateByteArrayWrapper = null,
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("kotlin.math.floor")
                    target.appendLine("${indention}val $outputName = floor(${inputNames[0]})")
                    onResult(indention, ETripleComponentTypeExt.DOUBLE)
                },
                generateByteArrayWrapper = null,
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("kotlin.math.floor")
                    target.appendLine("${indention}val $outputName = floor(${inputNames[0]})")
                    onResult(indention, ETripleComponentTypeExt.FLOAT)
                },
                generateByteArrayWrapper = null,
            ),
        ),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    ),
    MyOperator(
        name = "CEIL",
        type = OperatorType.BuildInCall,
        implementations = arrayOf(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    target.appendLine("${indention}val $outputName = ${inputNames[0]}")
                    onResult(indention, ETripleComponentTypeExt.INTEGER)
                },
                generateByteArrayWrapper = { indention, inputNames, outputName, _, imports, target, globalVariables, onResult ->
                    imports.add("lupos.s00misc.ByteArrayWrapper")
                    globalVariables.add("val $outputName = ByteArrayWrapper()")
                    target.appendLine("${indention}${inputNames[0]}.copyInto($outputName)")
                    onResult(indention, ETripleComponentTypeExt.INTEGER)
                }
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    target.appendLine("${indention}val $outputName = ${inputNames[0]}.ceil()")
                    onResult(indention, ETripleComponentTypeExt.DECIMAL)
                },
                generateByteArrayWrapper = null,
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("kotlin.math.ceil")
                    target.appendLine("${indention}val $outputName = ceil(${inputNames[0]})")
                    onResult(indention, ETripleComponentTypeExt.DOUBLE)
                },
                generateByteArrayWrapper = null,
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("kotlin.math.ceil")
                    target.appendLine("${indention}val $outputName = ceil(${inputNames[0]})")
                    onResult(indention, ETripleComponentTypeExt.FLOAT)
                },
                generateByteArrayWrapper = null,
            ),
        ),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    ),
    MyOperator(
        name = "BOUND",
        type = OperatorType.BuildInCall,
        implementations = arrayOf(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.ERROR),
                generateInstantiated = generateInstantiatedFalse2,
                generateByteArrayWrapper = generateByteArrayWrapperFalse2,
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.UNDEF),
                generateInstantiated = generateInstantiatedFalse2,
                generateByteArrayWrapper = generateByteArrayWrapperFalse2,
            ),
        ),
        generateInstantiatedOther = generateInstantiatedTrue,
        generateIDOther = generateIDTrue,
        generateByteArrayWrapperOther = generateByteArrayWrapperTrue,
    ),
    MyOperator(
        name = "STRLEN",
        type = OperatorType.BuildInCall,
        implementations = arrayOf(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("lupos.s00misc.MyBigInteger")
                    target.appendLine("${indention}val $outputName = MyBigInteger(${inputNames[0]}.length)")
                    onResult(indention, ETripleComponentTypeExt.INTEGER)
                },
                generateByteArrayWrapper = null,
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("lupos.s00misc.MyBigInteger")
                    target.appendLine("${indention}val $outputName = MyBigInteger(${inputNames[0]}_content.length)")
                    onResult(indention, ETripleComponentTypeExt.INTEGER)
                },
                generateByteArrayWrapper = null,
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("lupos.s00misc.MyBigInteger")
                    target.appendLine("${indention}val $outputName = MyBigInteger(${inputNames[0]}_content.length)")
                    onResult(indention, ETripleComponentTypeExt.INTEGER)
                },
                generateByteArrayWrapper = null,
            ),
        ),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    ),
    MyOperator(
        name = "IsIri",
        type = OperatorType.BuildInCall,
        implementations = arrayOf(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.ERROR),
                generateInstantiated = generateInstantiatedError2,
                generateByteArrayWrapper = generateByteArrayWrapperError2,
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.UNDEF),
                generateInstantiated = generateInstantiatedError2,
                generateByteArrayWrapper = generateByteArrayWrapperError2,
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.IRI),
                generateInstantiated = generateInstantiatedTrue2,
                generateByteArrayWrapper = generateByteArrayWrapperTrue2,
            ),
        ),
        generateInstantiatedOther = generateInstantiatedFalse,
        generateIDOther = generateIDFalse,
        generateByteArrayWrapperOther = generateByteArrayWrapperFalse,
    ),
    MyOperator(
        name = "IsLITERAL",
        type = OperatorType.BuildInCall,
        implementations = arrayOf(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.ERROR),
                generateInstantiated = generateInstantiatedError2,
                generateByteArrayWrapper = generateByteArrayWrapperError2,
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.UNDEF),
                generateInstantiated = generateInstantiatedError2,
                generateByteArrayWrapper = generateByteArrayWrapperError2,
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.IRI),
                generateInstantiated = generateInstantiatedFalse2,
                generateByteArrayWrapper = generateByteArrayWrapperFalse2,
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.BLANK_NODE),
                generateInstantiated = generateInstantiatedFalse2,
                generateByteArrayWrapper = generateByteArrayWrapperFalse2,
            ),
        ),
        generateInstantiatedOther = generateInstantiatedTrue,
        generateIDOther = generateIDTrue,
        generateByteArrayWrapperOther = generateByteArrayWrapperTrue,
    ),
    MyOperator(
        name = "IsNUMERIC",
        type = OperatorType.BuildInCall,
        implementations = arrayOf(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.ERROR),
                generateInstantiated = generateInstantiatedError2,
                generateByteArrayWrapper = generateByteArrayWrapperError2,
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.UNDEF),
                generateInstantiated = generateInstantiatedError2,
                generateByteArrayWrapper = generateByteArrayWrapperError2,
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                generateInstantiated = generateInstantiatedTrue2,
                generateByteArrayWrapper = generateByteArrayWrapperTrue2,
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                generateInstantiated = generateInstantiatedTrue2,
                generateByteArrayWrapper = generateByteArrayWrapperTrue2,
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                generateInstantiated = generateInstantiatedTrue2,
                generateByteArrayWrapper = generateByteArrayWrapperTrue2,
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                generateInstantiated = generateInstantiatedTrue2,
                generateByteArrayWrapper = generateByteArrayWrapperTrue2,
            ),
        ),
        generateInstantiatedOther = generateInstantiatedFalse,
        generateIDOther = generateIDFalse,
        generateByteArrayWrapperOther = generateByteArrayWrapperFalse,
    ),
    MyOperator(
        name = "DATATYPE",
        type = OperatorType.BuildInCall,
        implementations = arrayOf(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.BOOLEAN),
                generateInstantiated = generateInstantiatedString("http://www.w3.org/2001/XMLSchema#boolean"),
                generateByteArrayWrapper = generateByteArrayWrapperString("http://www.w3.org/2001/XMLSchema#boolean"),
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                generateInstantiated = generateInstantiatedString("http://www.w3.org/2001/XMLSchema#integer"),
                generateByteArrayWrapper = generateByteArrayWrapperString("http://www.w3.org/2001/XMLSchema#integer"),
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                generateInstantiated = generateInstantiatedString("http://www.w3.org/2001/XMLSchema#double"),
                generateByteArrayWrapper = generateByteArrayWrapperString("http://www.w3.org/2001/XMLSchema#double"),
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                generateInstantiated = generateInstantiatedString("http://www.w3.org/2001/XMLSchema#decimal"),
                generateByteArrayWrapper = generateByteArrayWrapperString("http://www.w3.org/2001/XMLSchema#decimal"),
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                generateInstantiated = generateInstantiatedString("http://www.w3.org/2001/XMLSchema#float"),
                generateByteArrayWrapper = generateByteArrayWrapperString("http://www.w3.org/2001/XMLSchema#float"),
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                generateInstantiated = generateInstantiatedString("http://www.w3.org/2001/XMLSchema#string"),
                generateByteArrayWrapper = generateByteArrayWrapperString("http://www.w3.org/2001/XMLSchema#string"),
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                generateInstantiated = generateInstantiatedString("http://www.w3.org/1999/02/22-rdf-syntax-ns#langString"),
                generateByteArrayWrapper = generateByteArrayWrapperString("http://www.w3.org/1999/02/22-rdf-syntax-ns#langString"),
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                generateInstantiated = generateInstantiatedString("http://www.w3.org/2001/XMLSchema#dateTime"),
                generateByteArrayWrapper = generateByteArrayWrapperString("http://www.w3.org/2001/XMLSchema#dateTime"),
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    target.appendLine("${indention}val $outputName = ${inputNames[0]}_type")
                    onResult(indention, ETripleComponentTypeExt.STRING)
                },
                generateByteArrayWrapper = null
            ),

        ),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    ),
    MyOperator(
        name = "IRI",
        type = OperatorType.BuildInCall,
        additionalParametersDefinition = arrayOf("@JvmField public var prefix: String"),
        imports = mutableSetOf("kotlin.jvm.JvmField"),
        additionalParametersName = arrayOf("prefix"),
        implementations = arrayOf(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.IRI),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    target.appendLine("${indention}val $outputName = ${inputNames[0]}")
                    onResult(indention, ETripleComponentTypeExt.IRI)
                },
                generateByteArrayWrapper = { indention, inputNames, outputName, _, imports, target, globalVariables, onResult ->
                    imports.add("lupos.s00misc.ByteArrayWrapper")
                    globalVariables.add("val $outputName = ByteArrayWrapper()")
                    target.appendLine("${indention}${inputNames[0]}.copyInto($outputName)")
                    onResult(indention, ETripleComponentTypeExt.IRI)
                }
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    target.appendLine("${indention}val $outputName = if (prefix.length > 0 && !prefix.endsWith('/')) {")
                    target.appendLine("$indention    \"\$prefix/\$${inputNames[0]}\"")
                    target.appendLine("$indention} else {")
                    target.appendLine("$indention    \"\$prefix\$${inputNames[0]}\"")
                    target.appendLine("$indention}")
                    onResult(indention, ETripleComponentTypeExt.IRI)
                },
                generateByteArrayWrapper = null,
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    target.appendLine("${indention}if (${inputNames[0]}_type == \"http://www.w3.org/2001/XMLSchema#string\") {")
                    target.appendLine("$indention    val $outputName = if (prefix.length > 0 && !prefix.endsWith('/')) {")
                    target.appendLine("$indention        \"\$prefix/\$${inputNames[0]}_content\"")
                    target.appendLine("$indention    } else {")
                    target.appendLine("$indention        \"\$prefix\$${inputNames[0]}_content\"")
                    target.appendLine("$indention    }")
                    onResult("$indention    ", ETripleComponentTypeExt.IRI)
                    target.appendLine("$indention} else {")
                    onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                    target.appendLine("$indention}")
                },
                generateByteArrayWrapper = null,
            ),
        ),
        generateInstantiatedOther = generateInstantiatedError,
        generateIDOther = generateIDError,
        generateByteArrayWrapperOther = generateByteArrayWrapperError,
    ),
)

public val converters = listOf(
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.INTEGER,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.dictionary.DictionaryHelper")
            imports.add("lupos.s00misc.MyBigInteger")
            target.appendLine("${indention}val $outputName = MyBigInteger(DictionaryHelper.byteArrayToInteger($inputName))")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.INTEGER,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.s00misc.ByteArrayWrapper")
            imports.add("lupos.dictionary.DictionaryHelper")
            globalVariables.add("val $outputName = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.integerToByteArray($outputName, $inputName.toString())")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.DECIMAL,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.dictionary.DictionaryHelper")
            imports.add("lupos.s00misc.MyBigDecimal")
            target.appendLine("${indention}val $outputName = MyBigDecimal(DictionaryHelper.byteArrayToDecimal($inputName))")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.DECIMAL,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.s00misc.ByteArrayWrapper")
            imports.add("lupos.dictionary.DictionaryHelper")
            globalVariables.add("val $outputName = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.decimalToByteArray($outputName, $inputName.toString())")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.DOUBLE,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.dictionary.DictionaryHelper")
            target.appendLine("${indention}val $outputName = DictionaryHelper.byteArrayToDouble($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.DOUBLE,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.s00misc.ByteArrayWrapper")
            imports.add("lupos.dictionary.DictionaryHelper")
            globalVariables.add("val $outputName = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.doubleToByteArray($outputName, $inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.FLOAT,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.dictionary.DictionaryHelper")
            target.appendLine("${indention}val $outputName = DictionaryHelper.byteArrayToFloat($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.STRING_TYPED,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.dictionary.DictionaryHelper")
            target.appendLine("${indention}val ${outputName}_content = DictionaryHelper.byteArrayToTyped_Content($inputName)")
            target.appendLine("${indention}val ${outputName}_type = DictionaryHelper.byteArrayToTyped_Type($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.STRING_LANG,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.dictionary.DictionaryHelper")
            target.appendLine("${indention}val ${outputName}_content = DictionaryHelper.byteArrayToLang_Content($inputName)")
            target.appendLine("${indention}val ${outputName}_lang = DictionaryHelper.byteArrayToLang_Lang($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.STRING,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.dictionary.DictionaryHelper")
            target.appendLine("${indention}val $outputName = DictionaryHelper.byteArrayToString($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.STRING,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.s00misc.ByteArrayWrapper")
            imports.add("lupos.dictionary.DictionaryHelper")
            globalVariables.add("val $outputName = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.stringToByteArray($outputName, $inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.IRI,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.s00misc.ByteArrayWrapper")
            imports.add("lupos.dictionary.DictionaryHelper")
            globalVariables.add("val $outputName = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.iriToByteArray($outputName, $inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.FLOAT,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.s00misc.ByteArrayWrapper")
            imports.add("lupos.dictionary.DictionaryHelper")
            globalVariables.add("val $outputName = ByteArrayWrapper()")
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
            imports.add("lupos.s00misc.ByteArrayWrapper")
            imports.add("lupos.dictionary.DictionaryHelper")
            globalVariables.add("val $outputName = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.booleanToByteArray($outputName, $inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.ERROR,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, _, outputName, imports, target, globalVariables ->
            imports.add("lupos.s00misc.ByteArrayWrapper")
            imports.add("lupos.dictionary.DictionaryHelper")
            globalVariables.add("val $outputName = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.errorToByteArray($outputName)")
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
    File("src/luposdate3000_operator_arithmetik/src/commonMain/kotlin/lupos/s04arithmetikOperators/generated/AOP${operator.type}${operator.name}.kt").printWriter().use { out ->
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
