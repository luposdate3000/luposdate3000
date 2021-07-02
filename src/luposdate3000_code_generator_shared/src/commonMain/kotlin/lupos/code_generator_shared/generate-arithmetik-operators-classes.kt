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
package lupos.code_generator_shared

import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.shared.ETripleComponentType
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.ValueBoolean
import lupos.shared.ValueDecimal
import lupos.shared.ValueInteger
import lupos.shared.ValueIri
import lupos.shared.ValueStringBase
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.IOPBase

public enum class OperatorType(public val str: String) {
    BuildInCall("BuildInCall"),
    FunctionCall("FunctionCall"),
    Basic(""),
}

public enum class EParamRepresentation {
    ID, // represented as dictionary key
    BYTEARRAYWRAPPER, // represented as ByteArrayWrapper
    INSTANTIATED, // represented as the parsed Type itself
}
public typealias GenerateFunc = (
    String, // indention
    Array<String>, // inputNames
    String, // outputName
    String, // prefix (for intermediates)
    MutableSet<String>, // imports
    StringBuilder, // target
    MutableSet<String>, // globalVariables
    (String, ETripleComponentType) -> Unit, // onResult(indention,resultType)
) -> Unit

public typealias GenerateFuncOther = (
    String, // indention
    String, // outputName
    String, // prefix (for intermediates)
    MutableSet<String>, // imports
    StringBuilder, // target
    MutableSet<String>, // globalVariables
    (String, ETripleComponentType) -> Unit, // onResult(indention,resultType)
) -> Unit

public typealias GenerateFuncOtherInstantiated = (
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
    imports.add("lupos.shared.inline.DictionaryHelper")
    target.appendLine("${indention}DictionaryHelper.errorToByteArray($outputName)")
    onResult(indention, ETripleComponentTypeExt.ERROR)
}
public val generateIDError: GenerateFuncOther = { indention, outputName, _, imports, target, _, onResult ->
    imports.add("lupos.shared.dictionary.DictionaryExt")
    target.appendLine("$indention$outputName = DictionaryValueHelper.errorValue")
    onResult(indention, ETripleComponentTypeExt.ERROR)
}
public val generateInstantiatedFalse: GenerateFuncOtherInstantiated = { indention, outputName, _, _, target, _ ->
    target.appendLine("$indention$outputName = false")
    ETripleComponentTypeExt.BOOLEAN
}
public val generateByteArrayWrapperFalse: GenerateFuncOther = { indention, outputName, _, imports, target, _, onResult ->
    imports.add("lupos.shared.inline.DictionaryHelper")
    target.appendLine("${indention}DictionaryHelper.booleanToByteArray($outputName, false)")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}
public val generateIDFalse: GenerateFuncOther = { indention, outputName, _, imports, target, _, onResult ->
    imports.add("lupos.shared.dictionary.DictionaryExt")
    target.appendLine("$indention$outputName = DictionaryExt.booleanFalseValue")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}
public val generateInstantiatedTrue: GenerateFuncOtherInstantiated = { indention, outputName, _, _, target, _ ->
    target.appendLine("$indention$outputName = true")
    ETripleComponentTypeExt.BOOLEAN
}
public val generateByteArrayWrapperTrue: GenerateFuncOther = { indention, outputName, _, imports, target, _, onResult ->
    imports.add("lupos.shared.inline.DictionaryHelper")
    target.appendLine("${indention}DictionaryHelper.booleanToByteArray($outputName, true)")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}
public val generateIDTrue: GenerateFuncOther = { indention, outputName, _, imports, target, _, onResult ->
    imports.add("lupos.shared.dictionary.DictionaryExt")
    target.appendLine("$indention$outputName = DictionaryExt.booleanTrueValue")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}
public val generateInstantiatedError2: GenerateFunc = { indention, _, _, _, _, _, _, onResult ->
    onResult(indention, ETripleComponentTypeExt.ERROR)
}

public val generateByteArrayWrapperError2: GenerateFunc = { indention, _, outputName, _, imports, target, globalVariables, onResult ->
    imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
    imports.add("lupos.shared.inline.DictionaryHelper")
    globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
    target.appendLine("${indention}DictionaryHelper.errorToByteArray($outputName)")
    onResult(indention, ETripleComponentTypeExt.ERROR)
}
public val generateInstantiatedTrue2: GenerateFunc = { indention, _, outputName, _, _, target, _, onResult ->
    target.appendLine("$indention$outputName = true")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}

public val generateByteArrayWrapperTrue2: GenerateFunc = { indention, _, outputName, _, imports, target, globalVariables, onResult ->
    imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
    imports.add("lupos.shared.inline.DictionaryHelper")
    globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
    target.appendLine("${indention}DictionaryHelper.booleanToByteArray($outputName, true)")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}
public val generateInstantiatedFalse2: GenerateFunc = { indention, _, outputName, _, _, target, _, onResult ->
    target.appendLine("$indention$outputName = false")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}

public val generateByteArrayWrapperFalse2: GenerateFunc = { indention, _, outputName, _, imports, target, globalVariables, onResult ->
    imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
    imports.add("lupos.shared.inline.DictionaryHelper")
    globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
    target.appendLine("${indention}DictionaryHelper.booleanToByteArray($outputName, false)")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}

public fun generateByteArrayWrapperString(str: String): GenerateFunc = { indention, _, outputName, _, imports, target, globalVariables, onResult ->
    imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
    imports.add("lupos.shared.inline.DictionaryHelper")
    globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
    target.appendLine("${indention}DictionaryHelper.stringToByteArray($outputName, \"$str\")")
    onResult(indention, ETripleComponentTypeExt.STRING)
}

public fun generateInstantiatedString(str: String): GenerateFunc = { indention, _, outputName, _, _, target, _, onResult ->
    target.appendLine("$indention$outputName = \"$str\"")
    onResult(indention, ETripleComponentTypeExt.STRING)
}

public fun generateMethod(child: IOPBase, indention: String, inputNames: Array<String>, outputName: String, prefix: String, imports: MutableSet<String>, target: StringBuilder, globalVariables: MutableSet<String>, confType: MutableList<MutableSet<ETripleComponentType>>) {
    val tmpBuf = ByteArrayWrapper()

    val confirmedTypes = mutableListOf<MutableSet<ETripleComponentType>>()
    for (c in child.getChildren()) {
        generateMethod(c, indention, inputNames, outputName, prefix, imports, target, globalVariables, confirmedTypes)
    }

    when (child) {
        is AOPVariable -> {
            // Muss in einen Datentyp gecastet werden, um Operationen wie ?pages+5 < 50 im Filter durchführen zu können
            // Hier klappt .toInt() am Ende ranhängen, sollte aber dynamisch erkannt werden; Anhängig von der Konstanten zuvor machen?
            target.appendLine("                        query.getDictionary().getValue(buffer, row${child.name})")
            target.appendLine("                        val child${child.uuid} = DictionaryHelper.byteArrayToValueDefinition(buffer)")
            confType.add(Array(ETripleComponentTypeExt.values_size) { it }.toMutableSet())
        }
        is AOPConstant -> {
            val dict = child.getQuery().getDictionary()
            dict.getValue(tmpBuf, child.getValue())
            val type = DictionaryHelper.byteArrayToType(tmpBuf)
            confType.add(mutableSetOf(type))
            when (val value = DictionaryHelper.byteArrayToValueDefinition(tmpBuf)) {
                is ValueBoolean -> {
                    target.appendLine("        val child${child.uuid} = ${value.value}")
                }
                is ValueInteger -> {
                    target.appendLine("        val child${child.uuid} = BigInteger.fromInt(${value.value})")
                }
                is ValueDecimal -> {
                    target.appendLine("        val child${child.uuid} = MyBigDecimal.fromBigDecimal(${value.value})")
                }
                is ValueStringBase -> {
                    // Erkennt noch nicht, dass es ein String ist?
                    // Abfragen wie equals/!equals werden noch als == und != übersetzt
                    target.appendLine(
                        "        val child${child.uuid} = \"${
                        value.valueToString()!!.replace("\"", "")
                        }\""
                    )
                }
                is ValueIri -> {
                    throw Exception("to do")
                }
                else -> {
                    throw Exception(value.toString())
                }
            }
        }
        else -> {
            confType.add(Array(ETripleComponentTypeExt.values_size) { it }.toMutableSet())
            val operatorName = getOperatorName(child.getClassname())
            val operators = mutableListOf<MyOperator>()
            OperatorBuilder.build(operators)
            val operator = operators.filter { it -> it.name == operatorName }.first()
            val inputNaming = child.getChildren().map { it -> "child${it.getUUID()}" }
            val map = operator.generateMap(indention, EParamRepresentation.ID, inputNaming.toTypedArray(), "res", "tmp", imports, target, globalVariables, confirmedTypes)
            val maxElement = map.maxByOrNull { it -> it.value.size }
            for (m in map) {
                // println(m.key);
            }
            println(map.size)
            println(maxElement!!.value.size)
            // map.map { println(it.key to  it.value.map{it.map { it }}) }
            val builder = StringBuilder()
            generateOptimizedWhenStructure(indention, map, builder, map.get(map.keys.first())!!.first().size, 0, inputNaming)
            println(builder.toString())
        }
    }
}

private fun generateOptimizedWhenStructure(indention: String, map: MutableMap<String, MutableSet<Array<ETripleComponentType>>>, builder: StringBuilder, maxDepth: Int, currentDepth: Int, inputNames: List<String>) {
    if (currentDepth > maxDepth || map.isEmpty()) {
        return
    }
    if (map.size == 1) {
        builder.append(map.keys.first())
        return
    }
    var first = true
    val m = mutableMapOf<String, MutableSet<ETripleComponentType>>()
    for (currenType in 0 until ETripleComponentTypeExt.values_size) {
        val newMap = mutableMapOf<String, MutableSet<Array<ETripleComponentType>>>()
        var sameMap = true
        for ((k, v) in map) {
            val values = mutableSetOf<Array<ETripleComponentType>>()
            for (types in v) {
                if (currenType == types[currentDepth]) {
                    values.add(types)
                } else {
                    sameMap = false
                }
            }
            if (values.size > 0) {
                newMap[k] = values
            }
        }
        if (newMap.isNotEmpty()) {
            // newMap.map { println(it.key to  it.value.map{it.map { it }}) }
            val temBuilder = StringBuilder()
            generateOptimizedWhenStructure(indention, newMap, temBuilder, maxDepth, currentDepth + 1, inputNames)
            val tempString = temBuilder.toString()
            val tempType = m[tempString]
            if (tempType != null) {
                tempType.add(currenType)
            } else {
                m[tempString] = mutableSetOf(currenType)
            }
        }
    }
    if (m.size == 1) {
        builder.append(m.keys.first())
    } else {
        builder.appendLine(" when (DictionaryHelper.byteArrayToType(${inputNames[currentDepth]})) {")
        for ((k, v) in m) {
            builder.appendLine(v.joinToString { "ETripleComponentTypeExt.${ETripleComponentTypeExt.names[it]}" } + " -> {")
            builder.append(k)
            builder.appendLine("}")
        }
        builder.appendLine("}")
    }
}

private fun getOperatorName(aopClassname: String): String {
    return aopClassname.replace("AOPBuildInCall", "").replace("AOPFunctionCall", "").replace("AOP", "")
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
                imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
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
        val currentStatements = mutableMapOf<String, String>() // content -> keys
        fun closeWhenStatements(first: Int, max: Int) {
            while (openWhenStatements > first) {
                if (openWhenStatements == max - 1) {
                    for ((k, v) in currentStatements) {
                        target.appendLine("${localindention.substring(4)}$v -> {")
                        target.append(k)
                        target.appendLine("${localindention.substring(4)}}")
                    }
                    currentStatements.clear()
                } else {
                    target.appendLine("${localindention.substring(4)}}")
                }
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

        var variableCount = -1
        for (implementation in implementations) {
            variableCount = implementation.childrenTypes.size
            fun createWhenStatements(first: Int, last: Int) {
                closeWhenStatements(first, variableCount)
                for (i in first until last) {
                    if (openWhenStatements < i) {
                        target.appendLine("${localindention}when (${typeNames[i]}) {")
                        openWhenStatements++
                        localindention += "        "
                    } else {
                        if (i < implementation.childrenTypes.size - 1) {
                            target.appendLine("${localindention.substring(4)}}")
                        }
                    }
                    imports.add("lupos.shared.ETripleComponentTypeExt")
                    lastOperatorTypes[i] = implementation.childrenTypes[i]
                    if (i < implementation.childrenTypes.size - 1) {
                        target.appendLine("${localindention.substring(4)}ETripleComponentTypeExt.${ETripleComponentTypeExt.names[implementation.childrenTypes[i]]} -> {")
                    }
                }
            }

            val generateByteArrayWrapper = implementation.generateByteArrayWrapper
            var commonOperatorTypes = 0
            while (lastOperatorTypes[commonOperatorTypes] == implementation.childrenTypes[commonOperatorTypes] && commonOperatorTypes < implementation.childrenTypes.size) {
                commonOperatorTypes++
            }
            createWhenStatements(commonOperatorTypes, implementation.childrenTypes.size)
            val localtarget = StringBuilder()
            if (generateByteArrayWrapper != null) {
                generateByteArrayWrapper(localindention, myInputNames, myOutputName, "${prefix}_${prefix_counter++}", imports, localtarget, globalVariables) { indention2, _ ->
                    if (representation == EParamRepresentation.ID) {
                        localtarget.appendLine("$indention2$outputName = query.getDictionary().createValue($myOutputName)")
                    }
                }
            } else {
                var myInputInstances = Array(inputNames.size) { "${prefix}_${prefix_counter++}" }
                var myOutputInstance = "${prefix}_${prefix_counter++}"
                for (i in 0 until inputNames.size) {
                    val converter = getRepresentationConversionFunction(implementation.childrenTypes[i], EParamRepresentation.BYTEARRAYWRAPPER, EParamRepresentation.INSTANTIATED)
                    converter.generate(localindention, myInputNames[i], myInputInstances[i], imports, localtarget, globalVariables)
                }
                implementation.generateInstantiated(localindention, myInputInstances, myOutputInstance, "${prefix}_${prefix_counter++}", imports, localtarget, globalVariables) { indention2, resultType ->
                    if (resultType == ETripleComponentTypeExt.BLANK_NODE) {
                        localtarget.appendLine("$indention2$outputName = $myOutputInstance")
                    } else {
                        val converter = getRepresentationConversionFunction(resultType, EParamRepresentation.INSTANTIATED, EParamRepresentation.BYTEARRAYWRAPPER)
                        converter.generate(indention2, myOutputInstance, myOutputName, imports, localtarget, globalVariables)
                        if (representation == EParamRepresentation.ID) {
                            localtarget.appendLine("$indention2$outputName = query.getDictionary().createValue($myOutputName)")
                        }
                    }
                }
            }
            val localtargetS = localtarget.toString()
            val tmp = currentStatements[localtargetS]
            if (tmp == null) {
                currentStatements[localtargetS] = "ETripleComponentTypeExt." + ETripleComponentTypeExt.names[implementation.childrenTypes[implementation.childrenTypes.size - 1]]
            } else {
                currentStatements[localtargetS] = tmp + ", ETripleComponentTypeExt." + ETripleComponentTypeExt.names[implementation.childrenTypes[implementation.childrenTypes.size - 1]]
            }
        }
        closeWhenStatements(-1, variableCount)
    }

    public fun generateMap(indention: String, representation: EParamRepresentation, inputNames: Array<String>, outputName: String, prefix: String, imports: MutableSet<String>, target_param: StringBuilder, globalVariables: MutableSet<String>, confirmedTypes: MutableList<MutableSet<ETripleComponentType>>): MutableMap<String, MutableSet<Array<ETripleComponentType>>> {
        var outputMap = mutableMapOf<String, MutableSet<Array<ETripleComponentType>>>()
        var myInputNames = Array<String>(inputNames.size) { inputNames[it] }
        var prefix_counter = 0
        globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
        var typeNames = Array<String>(inputNames.size) { "${prefix}_${prefix_counter++}" }
        imports.add("lupos.shared.ETripleComponentType")
        for (i in 0 until inputNames.size) {
            target_param.appendLine("${indention}val ${typeNames[i]}: ETripleComponentType = DictionaryHelper.byteArrayToType(${myInputNames[i]})")
        }
        var myOutputName = outputName
        implementations.sort()
        var localindention = indention
        var lastOperatorTypes = Array(implementations[0].childrenTypes.size) { -1 }

        val target = StringBuilder()
        for (implementation in implementations) {
            val generateByteArrayWrapper = implementation.generateByteArrayWrapper
            var commonOperatorTypes = 0
            while (lastOperatorTypes[commonOperatorTypes] == implementation.childrenTypes[commonOperatorTypes] && commonOperatorTypes < implementation.childrenTypes.size) {
                commonOperatorTypes++
            }
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

            var possibleImplementation = true
            for ((index, type) in implementation.childrenTypes.withIndex()) {
                if (!confirmedTypes[index].contains(type)) {
                    possibleImplementation = false
                    break
                }
            }
            if (possibleImplementation) {
                if (outputMap[target.toString()] != null) {
                    val oldValues = outputMap[target.toString()]
                    oldValues!!.add(implementation.childrenTypes)
                    outputMap[target.toString()] = oldValues
                } else {
                    outputMap[target.toString()] = mutableSetOf(implementation.childrenTypes)
                }
            }

            target.clear()
        }

        return outputMap
    }

    public fun generateAopMethod(): StringBuilder {
        var method = StringBuilder()
        var imports2 = mutableSetOf<String>()
        var target = StringBuilder()

        var globalVariables = mutableSetOf<String>()
        generate("            ", EParamRepresentation.ID, Array(implementations[0].childrenTypes.size) { "childIn$it" }, "res", "tmp", imports2, target, globalVariables)
        imports2.addAll(imports)

        method.appendLine("    override fun evaluateID(row: IteratorBundle): () -> Int {")

        for (v in globalVariables) {
            if (!v.contains(" res: ")) {
                method.appendLine("        $v")
            }
        }

        method.appendLine("}")
        return method
    }

    public fun generateAOP(): StringBuilder {
        var clazz = StringBuilder()
        clazz.appendLine("package lupos.operator.arithmetik.generated")
        clazz.appendLine("")
        var imports2 = mutableSetOf<String>()
        imports2.addAll(imports)
        var target = StringBuilder()
        var globalVariables = mutableSetOf<String>()
        generate("            ", EParamRepresentation.ID, Array(implementations[0].childrenTypes.size) { "childIn$it" }, "res", "tmp", imports2, target, globalVariables)
        imports2.add("lupos.operator.arithmetik.AOPBase")
        imports2.add("lupos.shared.IQuery")
        imports2.add("lupos.shared.operator.iterator.IteratorBundle")
        imports2.add("lupos.shared.operator.IOPBase")
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
    internal var list = mutableListOf<MyOperatorPart>()
    internal var generateInstantiatedOther: GenerateFunc? = null
    internal var generateByteArrayWrapperOther: GenerateFunc? = null

    public fun build(): Array<MyOperatorPart> {
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

    public fun setOther(generateInstantiated: GenerateFunc, generateByteArrayWrapper: GenerateFunc? = null): MyOperatorPartFactory {
        generateInstantiatedOther = generateInstantiated
        generateByteArrayWrapperOther = generateByteArrayWrapper
        return this
    }

    public fun add(part: MyOperatorPart): MyOperatorPartFactory {
        list.add(part)
        return this
    }

    public fun addForEachChildrenType(childrenTypes: Array<Array<ETripleComponentType>>, generateInstantiated: GenerateFunc, generateByteArrayWrapper: GenerateFunc? = null): MyOperatorPartFactory {
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

    public fun addNumericBinaryOperatorStub(operator: String): MyOperatorPartFactory {
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.INTEGER),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                    imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == BigInteger.ZERO) {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: BigDecimal = ${inputNames[0]} $operator BigDecimal.fromBigInteger(${inputNames[1]})")
                        onResult(indention + "    ", ETripleComponentTypeExt.DECIMAL)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    val $outputName: BigDecimal = ${inputNames[0]} $operator BigDecimal.fromBigInteger(${inputNames[1]})")
                        onResult(indention + "    ", ETripleComponentTypeExt.DECIMAL)
                    }
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == BigInteger.ZERO) {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}.doubleValue()")
                        onResult(indention + "    ", ETripleComponentTypeExt.FLOAT)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}.doubleValue()")
                        onResult(indention + "    ", ETripleComponentTypeExt.FLOAT)
                    }
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.INTEGER),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == BigInteger.ZERO) {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}.doubleValue()")
                        onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}.doubleValue()")
                        onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                    }
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.DECIMAL),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == BigDecimal.ZERO) {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: BigDecimal = BigDecimal.fromBigInteger(${inputNames[0]}) $operator ${inputNames[1]}")
                        onResult(indention + "    ", ETripleComponentTypeExt.DECIMAL)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    val $outputName: BigDecimal = BigDecimal.fromBigInteger(${inputNames[0]}) $operator ${inputNames[1]}")
                        onResult(indention + "    ", ETripleComponentTypeExt.DECIMAL)
                    }
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DECIMAL),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == BigDecimal.ZERO) {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: BigDecimal = ${inputNames[0]} $operator ${inputNames[1]}")
                        onResult(indention + "    ", ETripleComponentTypeExt.DECIMAL)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    val $outputName: BigDecimal = ${inputNames[0]} $operator ${inputNames[1]}")
                        onResult(indention + "    ", ETripleComponentTypeExt.DECIMAL)
                    }
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.DECIMAL),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == BigDecimal.ZERO) {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}.doubleValue()")
                        onResult(indention + "    ", ETripleComponentTypeExt.FLOAT)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}.doubleValue()")
                        onResult(indention + "    ", ETripleComponentTypeExt.FLOAT)
                    }
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.DECIMAL),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, onResult ->
                    imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == BigDecimal.ZERO) {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}.doubleValue()")
                        onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}.doubleValue()")
                        onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                    }
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.FLOAT),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]}.doubleValue() $operator ${inputNames[1]}")
                        onResult(indention + "    ", ETripleComponentTypeExt.FLOAT)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]}.doubleValue() $operator ${inputNames[1]}")
                        onResult(indention + "    ", ETripleComponentTypeExt.FLOAT)
                    }
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.FLOAT),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]}.doubleValue() $operator ${inputNames[1]}")
                        onResult(indention + "    ", ETripleComponentTypeExt.FLOAT)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]}.doubleValue() $operator ${inputNames[1]}")
                        onResult(indention + "    ", ETripleComponentTypeExt.FLOAT)
                    }
                },
            )
        )

        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.FLOAT),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}")
                        onResult(indention + "    ", ETripleComponentTypeExt.FLOAT)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}")
                        onResult(indention + "    ", ETripleComponentTypeExt.FLOAT)
                    }
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.FLOAT),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}")
                        onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}")
                        onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                    }
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.DOUBLE),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]}.doubleValue() $operator ${inputNames[1]}")
                        onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]}.doubleValue() $operator ${inputNames[1]}")
                        onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                    }
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]}.doubleValue() $operator ${inputNames[1]}")
                        onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]}.doubleValue() $operator ${inputNames[1]}")
                        onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                    }
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.DOUBLE),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}")
                        onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}")
                        onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                    }
                },
            )
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.DOUBLE),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                        onResult(indention + "    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}")
                        onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    val $outputName: Double = ${inputNames[0]} $operator ${inputNames[1]}")
                        onResult(indention + "    ", ETripleComponentTypeExt.DOUBLE)
                    }
                },
            )
        )
        return this
    }
}

public fun getRepresentationConversionFunction(type: ETripleComponentType, inputRepresentation: EParamRepresentation, outputRepresentation: EParamRepresentation): MyRepresentationConversionFunction {
    for (converter in converters) {
        if (converter.type == type && converter.inputRepresentation == inputRepresentation && converter.outputRepresentation == outputRepresentation) {
            return converter
        }
    }
    throw Exception("not found ${ETripleComponentTypeExt.names[type]} $inputRepresentation $outputRepresentation")
}

public val converters: List<MyRepresentationConversionFunction> = listOf(
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.INTEGER,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.shared.inline.DictionaryHelper")
            imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
            target.appendLine("${indention}val $outputName: BigInteger = DictionaryHelper.byteArrayToInteger_I($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.INTEGER,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
            imports.add("lupos.shared.inline.DictionaryHelper")
            globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.integerToByteArray($outputName, $inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.DECIMAL,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.shared.inline.DictionaryHelper")
            imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
            target.appendLine("${indention}val $outputName: BigDecimal = DictionaryHelper.byteArrayToDecimal_I($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.DECIMAL,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
            imports.add("lupos.shared.inline.DictionaryHelper")
            globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.decimalToByteArray($outputName, $inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.DOUBLE,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.shared.inline.DictionaryHelper")
            target.appendLine("${indention}val $outputName: Double = DictionaryHelper.byteArrayToDouble_I($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.DOUBLE,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
            imports.add("lupos.shared.inline.DictionaryHelper")
            globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.doubleToByteArray($outputName, $inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.FLOAT,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.shared.inline.DictionaryHelper")
            target.appendLine("${indention}val $outputName: Double = DictionaryHelper.byteArrayToFloat_I($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.STRING_TYPED,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.shared.inline.DictionaryHelper")
            target.appendLine("${indention}val ${outputName}_content: String = DictionaryHelper.byteArrayToTyped_Content($inputName)")
            target.appendLine("${indention}val ${outputName}_type: String = DictionaryHelper.byteArrayToTyped_Type($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.STRING_LANG,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.shared.inline.DictionaryHelper")
            target.appendLine("${indention}val ${outputName}_content: String = DictionaryHelper.byteArrayToLang_Content($inputName)")
            target.appendLine("${indention}val ${outputName}_lang: String = DictionaryHelper.byteArrayToLang_Lang($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.IRI,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.shared.inline.DictionaryHelper")
            target.appendLine("${indention}val $outputName: String = DictionaryHelper.byteArrayToIri($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.STRING,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.shared.inline.DictionaryHelper")
            target.appendLine("${indention}val $outputName: String = DictionaryHelper.byteArrayToString($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.BLANK_NODE,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.shared.inline.DictionaryHelper")
            target.appendLine("${indention}val $outputName: Int = DictionaryHelper.byteArrayToBnode_I($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.BOOLEAN,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.shared.inline.DictionaryHelper")
            target.appendLine("${indention}val $outputName: Boolean = DictionaryHelper.byteArrayToBoolean($inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.BLANK_NODE,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
            imports.add("lupos.shared.inline.DictionaryHelper")
            globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.bnodeToByteArray($outputName, $inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.STRING,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
            imports.add("lupos.shared.inline.DictionaryHelper")
            globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.stringToByteArray($outputName, $inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.STRING_LANG,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
            imports.add("lupos.shared.inline.DictionaryHelper")
            globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.langToByteArray($outputName, ${inputName}_content, ${inputName}_lang)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.STRING_TYPED,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
            imports.add("lupos.shared.inline.DictionaryHelper")
            globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.typedToByteArray($outputName, ${inputName}_content, ${inputName}_type)")
        }
    ),

    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.IRI,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
            imports.add("lupos.shared.inline.DictionaryHelper")
            globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.iriToByteArray($outputName, $inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.FLOAT,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
            imports.add("lupos.shared.inline.DictionaryHelper")
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
            imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
            imports.add("lupos.shared.inline.DictionaryHelper")
            globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.booleanToByteArray($outputName, $inputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.ERROR,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, _, outputName, imports, target, globalVariables ->
            imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
            imports.add("lupos.shared.inline.DictionaryHelper")
            globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.errorToByteArray($outputName)")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.DATE_TIME,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _ ->
            imports.add("lupos.shared.inline.DictionaryHelper")
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
