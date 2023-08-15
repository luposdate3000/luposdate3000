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

import lupos.shared.*
import lupos.shared.inline.DictionaryHelper

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
    (String, EVariablePlaceholder) -> String, // valPrefix
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

private val EVariablePlaceholderNames = Array(EVariablePlaceholderExt.values_size) {
    when (it) {

        EVariablePlaceholderExt.Boolean -> "Boolean"
        EVariablePlaceholderExt.DateTime_day -> "BigInteger"
        EVariablePlaceholderExt.DateTime_hours -> "BigInteger"
        EVariablePlaceholderExt.DateTime_minutes -> "BigInteger"
        EVariablePlaceholderExt.DateTime_month -> "BigInteger"
        EVariablePlaceholderExt.DateTime_seconds -> "BigDecimal"
        EVariablePlaceholderExt.DateTime_timezone -> "String"
        EVariablePlaceholderExt.DateTime_typed_content -> "String"
        EVariablePlaceholderExt.DateTime_tz -> "String"
        EVariablePlaceholderExt.DateTime_year -> "BigInteger"
        EVariablePlaceholderExt.Decimal -> "BigDecimal"
        EVariablePlaceholderExt.Double -> "Double"
        EVariablePlaceholderExt.Empty -> ""
        EVariablePlaceholderExt.Integer -> "BigInteger"
        EVariablePlaceholderExt.Iri -> "String"
        EVariablePlaceholderExt.String_content -> "String"
        EVariablePlaceholderExt.String_lang -> "String"
        EVariablePlaceholderExt.String_type -> "String"
        EVariablePlaceholderExt.Blank_Node -> "DictionaryValueType"
        EVariablePlaceholderExt.ByteArrayWrapper -> "ByteArrayWrapper"
        EVariablePlaceholderExt.DictionaryID -> "ID"
        else -> TODO("Unknown EVariablePlaceholder $it")
    }
}

public fun getDefaultValue(s: String): String {
    var r = ""
    r = when (s) {
        "Boolean" -> "false"
        "BigInteger" -> "BigInteger(0)"
        "BigDecimal" -> "BigDecimal.ZERO"
        "String" -> "\"\""
        "Double" -> "0.0"
        "ByteArrayWrapper" -> "ByteArrayWrapper()"
        "Int" -> "0"
        else -> TODO("Unknown getDefaultValue  $s")
    }
    return r
}

private val ETripleComponentTypeToEVariablePlaceholder = Array(ETripleComponentTypeExt.values_size) {
    when (it) {
        ETripleComponentTypeExt.BLANK_NODE -> EVariablePlaceholderExt.Blank_Node
        ETripleComponentTypeExt.BOOLEAN -> EVariablePlaceholderExt.Boolean
        ETripleComponentTypeExt.DATE_TIME -> EVariablePlaceholderExt.DateTime_typed_content
        ETripleComponentTypeExt.DECIMAL -> EVariablePlaceholderExt.Decimal
        ETripleComponentTypeExt.DOUBLE -> EVariablePlaceholderExt.Double
        ETripleComponentTypeExt.UNDEF -> EVariablePlaceholderExt.Empty
        ETripleComponentTypeExt.IRI -> EVariablePlaceholderExt.Iri
        ETripleComponentTypeExt.INTEGER -> EVariablePlaceholderExt.Integer
        ETripleComponentTypeExt.STRING -> EVariablePlaceholderExt.String_content
        ETripleComponentTypeExt.ERROR -> EVariablePlaceholderExt.ByteArrayWrapper
        ETripleComponentTypeExt.STRING_LANG -> EVariablePlaceholderExt.String_lang
        ETripleComponentTypeExt.STRING_TYPED -> EVariablePlaceholderExt.String_type
        ETripleComponentTypeExt.FLOAT -> EVariablePlaceholderExt.Double
        ETripleComponentTypeExt._BYTEARRAYWRAPPER -> EVariablePlaceholderExt.ByteArrayWrapper
        ETripleComponentTypeExt._DICTIONARYID -> EVariablePlaceholderExt.DictionaryID
        else -> TODO("Unknown ETripleComponentType -> EVariablePlaceholder $it")
    }
}

public val generateInstantiatedError: GenerateFuncOtherInstantiated = { _, _, _, _, _, _ ->
    ETripleComponentTypeExt.ERROR
}
public val generateByteArrayWrapperError: GenerateFuncOther = { indention, outputName, _, imports, target, _, onResult ->
    imports.add("lupos.shared.inline.DictionaryHelper")
    target.appendLine("${indention}DictionaryHelper.errorToByteArray(${suffixNames(outputName, EVariablePlaceholderExt.ByteArrayWrapper)})")
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
public val generateInstantiatedError2: GenerateFunc = { indention, _, _, _, _, _, _, _, onResult ->
    onResult(indention, ETripleComponentTypeExt.ERROR)
}

public val generateByteArrayWrapperError2: GenerateFunc = { indention, _, outputName, _, imports, target, globalVariables, valPrefix, onResult ->
    imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
    imports.add("lupos.shared.inline.DictionaryHelper")
    globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
    target.appendLine("${indention}DictionaryHelper.errorToByteArray(${suffixNames(outputName, EVariablePlaceholderExt.ByteArrayWrapper)})")
    onResult(indention, ETripleComponentTypeExt.ERROR)
}
public val generateInstantiatedTrue2: GenerateFunc = { indention, _, outputName, _, _, target, _, _, onResult ->
    target.appendLine("$indention$outputName = true")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}

public val generateByteArrayWrapperTrue2: GenerateFunc = { indention, _, outputName, _, imports, target, globalVariables, _, onResult ->
    imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
    imports.add("lupos.shared.inline.DictionaryHelper")
    globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
    target.appendLine("${indention}DictionaryHelper.booleanToByteArray($outputName, true)")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}
public val generateInstantiatedFalse2: GenerateFunc = { indention, _, outputName, _, _, target, _, _, onResult ->
    target.appendLine("$indention$outputName = false")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}

public val generateByteArrayWrapperFalse2: GenerateFunc = { indention, _, outputName, _, imports, target, globalVariables, _, onResult ->
    imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
    imports.add("lupos.shared.inline.DictionaryHelper")
    globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
    target.appendLine("${indention}DictionaryHelper.booleanToByteArray($outputName, false)")
    onResult(indention, ETripleComponentTypeExt.BOOLEAN)
}

public fun generateByteArrayWrapperString(str: String): GenerateFunc = { indention, _, outputName, _, imports, target, globalVariables, _, onResult ->
    imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
    imports.add("lupos.shared.inline.DictionaryHelper")
    globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
    target.appendLine("${indention}DictionaryHelper.stringToByteArray($outputName, \"$str\")")
    onResult(indention, ETripleComponentTypeExt.STRING)
}

public fun generateInstantiatedString(str: String): GenerateFunc = { indention, _, outputName, _, _, target, _, _, onResult ->
    target.appendLine("$indention$outputName = \"$str\"")
    onResult(indention, ETripleComponentTypeExt.STRING)
}

public fun prefixVal_yes(name: String, type: EVariablePlaceholder): String {
    return "val ${name}${EVariablePlaceholderExt.names[type]} : ${EVariablePlaceholderNames[type]}"
}

public fun prefixVal_no(name: String, type: EVariablePlaceholder): String {
    if (type == EVariablePlaceholderExt.ByteArrayWrapper) {
        return "val ${name}${EVariablePlaceholderExt.names[type]} : ${EVariablePlaceholderNames[type]}"
    }
    return "${name}${EVariablePlaceholderExt.names[type]}"
}

public fun suffixNames(name: String, type: EVariablePlaceholder): String {
    return "${name}${EVariablePlaceholderExt.names[type]}"
}

public fun generateMethod(
    child: CodeGenClassHolder,
    indention: String,
    inputNames: Array<String>,
    outputName: String,
    prefix: String,
    imports: MutableSet<String>,
    target: StringBuilder,
    globalVariables: MutableSet<String>,
    confType: MutableList<MutableSet<ETripleComponentType>>,
    isChild: Boolean,
    builder: StringBuilder,
    variables: MutableMap<String, String>,
    toBoolean: Boolean,
) {
    imports.add("lupos.shared.ETripleComponentTypeExt")
    imports.add("lupos.shared.ETripleComponentType")
    val confirmedTypes = mutableListOf<MutableSet<ETripleComponentType>>()

    for (c in child.getChildren()) {
        generateMethod(c, indention, inputNames, outputName, prefix, imports, target, globalVariables, confirmedTypes, true, builder, variables, toBoolean)
    }
    when (child.getClassname()) {
        "AOPVariable" -> {
            // Muss in einen Datentyp gecastet werden, um Operationen wie ?pages+5 < 50 im Filter durchführen zu können
            // Hier klappt .toInt() am Ende ranhängen, sollte aber dynamisch erkannt werden; Anhängig von der Konstanten zuvor machen?
            builder.appendLine("query.getDictionary().getValue(buffer, row${child.getName()})")
            builder.appendLine("val child${child.getUUID()} = buffer")
            confType.add(Array(ETripleComponentTypeExt.values_size) { it }.toMutableSet())
            builder.appendLine("var child${child.getUUID()}_type = DictionaryHelper.byteArrayToType(buffer)")

            for (i in 0 until EVariablePlaceholderExt.values_size) {
                variables["child${child.getUUID()}${EVariablePlaceholderExt.names[i]}"] = EVariablePlaceholderNames[i]
            }
            builder.appendLine(" when (child${child.getUUID()}_type) {")

            for (i in 0 until ETripleComponentTypeExt.values_size) {
                if (i != ETripleComponentTypeExt._DICTIONARYID && i != ETripleComponentTypeExt._BYTEARRAYWRAPPER) {
                    builder.appendLine("ETripleComponentTypeExt.${ETripleComponentTypeExt.names[i]}  -> {")

                    val converter = getRepresentationConversionFunction(i, EParamRepresentation.BYTEARRAYWRAPPER, EParamRepresentation.INSTANTIATED)
                    converter.generate("", "child${child.getUUID()}", "child${child.getUUID()}", imports, builder, globalVariables, ::prefixVal_no)

                    builder.appendLine("}")
                }
            }
            builder.appendLine("}")
        }
        "AOPConstant" -> {
            val tmpBuf2 = child.getValue()
            val type = DictionaryHelper.byteArrayToType(tmpBuf2)
            builder.appendLine("var child${child.getUUID()}_type : ETripleComponentType = ETripleComponentTypeExt.${ETripleComponentTypeExt.names[type]}")
            confType.add(mutableSetOf(type))
            when (type) {
                ETripleComponentTypeExt.BOOLEAN -> builder.appendLine("        val child${child.getUUID()}Boolean = ${DictionaryHelper.byteArrayToBoolean(tmpBuf2)}")
                ETripleComponentTypeExt.INTEGER -> {
                    builder.appendLine("        val child${child.getUUID()}Integer = BigInteger.fromInt(${DictionaryHelper.byteArrayToInteger_S(tmpBuf2)})")
                }
                ETripleComponentTypeExt.DECIMAL -> {
                    builder.appendLine("        val child${child.getUUID()}Decimal = MyBigDecimal.fromBigDecimal(${DictionaryHelper.byteArrayToDecimal_S(tmpBuf2)})")
                }
                else -> {
                    TODO()
                }
            }
        }
        else -> {
            for (i in 0 until EVariablePlaceholderExt.values_size) {
                variables["child${child.getUUID()}${EVariablePlaceholderExt.names[i]}"] = EVariablePlaceholderNames[i]
            }
            val outputName2 = if (isChild) {
                "child${child.getUUID()}"
            } else {
                outputName
            }
            builder.appendLine("var ${outputName2}_type : ETripleComponentType")
            val operatorName = getOperatorName(child.getClassname())
            val operators = mutableListOf<MyOperator>()
            println("OP NAME $operatorName")
            OperatorBuilder.build(operators)
            val operator = operators.filter { it.name == operatorName }.first()
            val inputNaming = child.getChildren().map { "child${it.getUUID()}" }
            val types = mutableSetOf<ETripleComponentType>()
            // val representation = if(isChild) EParamRepresentation.INSTANTIATED else EParamRepresentation.ID;
            val representation = EParamRepresentation.INSTANTIATED
            println(representation)

            val map = operator.generateMap(indention, representation, inputNaming.toTypedArray(), outputName2, "", imports, target, globalVariables, confirmedTypes, types)
            confType.add(types)
            val maxElement = map.maxByOrNull { it.value.size }

            println(map.size)
            println(maxElement!!.value.size)
            // map.map { println("MARKING" +it.key to  it.value.map{it.map { it }}) }
            generateOptimizedWhenStructure(indention, map, builder, map[map.keys.first()]!!.first().size, 0, inputNaming)
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
        builder.appendLine(" when (${inputNames[currentDepth]}_type) {")
        for ((k, v) in m) {
            builder.appendLine(v.joinToString { "ETripleComponentTypeExt.${ETripleComponentTypeExt.names[it]}" } + " -> {")
            builder.append(k)
            builder.appendLine("}")
        }
        builder.appendLine("else -> throw Exception(\"Unknown ETripleComponentType\")")
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

    private fun generate(indention: String, representation: EParamRepresentation, inputNames: Array<String>, outputName: String, prefix: String, imports: MutableSet<String>, target: StringBuilder, globalVariables: MutableSet<String>) {
        println("generating operator $name")
        if (representation == EParamRepresentation.INSTANTIATED) {
            throw Exception("there is no need to combine functions here")
        }
        val myInputNames = Array(inputNames.size) { inputNames[it] }
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
        val typeNames = Array(inputNames.size) { "${prefix}_${prefix_counter++}" }
        imports.add("lupos.shared.ETripleComponentType")
        for (i in 0 until inputNames.size) {
            target.appendLine("${indention}val ${typeNames[i]}: ETripleComponentType = DictionaryHelper.byteArrayToType(${myInputNames[i]})")
        }
        var myOutputName = outputName

        if (representation == EParamRepresentation.ID) {
            myOutputName = "${prefix}_${prefix_counter++}"
        }
        implementations.sort()
        val lastOperatorTypes = Array(implementations[0].childrenTypes.size) { -1 }
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

        val prefix_reset = prefix_counter
        for (implementation in implementations) {
            prefix_counter = prefix_reset
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
                generateByteArrayWrapper(localindention, myInputNames, myOutputName, "${prefix}_${prefix_counter++}", imports, target, globalVariables, ::prefixVal_yes) { indention2, _ ->
                    if (representation == EParamRepresentation.ID) {
                        target.appendLine("$indention2$outputName = query.getDictionary().createValue($myOutputName)")
                    }
                }
            } else {
                val myInputInstances = Array(inputNames.size) { "${prefix}_${prefix_counter++}" }
                val myOutputInstance = "${prefix}_${prefix_counter++}"
                for (i in 0 until inputNames.size) {
                    val converter = getRepresentationConversionFunction(implementation.childrenTypes[i], EParamRepresentation.BYTEARRAYWRAPPER, EParamRepresentation.INSTANTIATED)
                    converter.generate(localindention, myInputNames[i], myInputInstances[i], imports, target, globalVariables, ::prefixVal_yes)
                }

                implementation.generateInstantiated(localindention, myInputInstances, myOutputInstance, "${prefix}_${prefix_counter++}", imports, target, globalVariables, ::prefixVal_yes) { indention2, resultType ->
                    if (resultType == ETripleComponentTypeExt.BLANK_NODE) {
                        target.appendLine("$indention2$outputName = $myOutputInstance")
                    } else {
                        val converter = getRepresentationConversionFunction(resultType, EParamRepresentation.INSTANTIATED, EParamRepresentation.BYTEARRAYWRAPPER)
                        converter.generate(indention2, myOutputInstance, myOutputName, imports, target, globalVariables, ::prefixVal_yes)
                        if (representation == EParamRepresentation.ID) {
                            target.appendLine("$indention2$outputName = query.getDictionary().createValue($myOutputName)")
                        }
                    }
                }
            }
        }
        closeWhenStatements(-1)
    }

    public fun generateMap(indention: String, representation: EParamRepresentation, inputNames: Array<String>, outputName: String, prefix: String, imports: MutableSet<String>, target_param: StringBuilder, globalVariables: MutableSet<String>, confirmedTypes: MutableList<MutableSet<ETripleComponentType>>, outputType: MutableSet<ETripleComponentType>): MutableMap<String, MutableSet<Array<ETripleComponentType>>> {
        val outputMap = mutableMapOf<String, MutableSet<Array<ETripleComponentType>>>()
        val myInputNames = Array(inputNames.size) { inputNames[it] }
        var prefix_counter = 0
        globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
        val typeNames = Array(inputNames.size) { "${prefix}_${prefix_counter++}" }
        imports.add("lupos.shared.ETripleComponentType")
        for (i in 0 until inputNames.size) {
            target_param.appendLine("${indention}val ${typeNames[i]}: ETripleComponentType = DictionaryHelper.byteArrayToType(${myInputNames[i]})")
        }
        implementations.sort()
        val lastOperatorTypes = Array(implementations[0].childrenTypes.size) { -1 }

        val target = StringBuilder()
        for (implementation in implementations) {
            var possibleImplementation = true
            for ((index, type) in implementation.childrenTypes.withIndex()) {
                if (!confirmedTypes[index].contains(type)) {
                    possibleImplementation = false
                    break
                }
            }

            if (possibleImplementation) {
                val generateByteArrayWrapper = implementation.generateByteArrayWrapper
                var commonOperatorTypes = 0
                while (lastOperatorTypes[commonOperatorTypes] == implementation.childrenTypes[commonOperatorTypes] && commonOperatorTypes < implementation.childrenTypes.size) {
                    commonOperatorTypes++
                }
                if (generateByteArrayWrapper != null) {
                    generateByteArrayWrapper(
                        indention,
                        myInputNames,
                        outputName,
                        "${prefix}_${prefix_counter++}",
                        imports,
                        target,
                        globalVariables,
                        ::prefixVal_no,
                    ) { indention2, resultType ->
                        outputType.add(resultType)
                        if (representation == EParamRepresentation.ID) {
                            target.appendLine("$indention2$outputName = query.getDictionary().createValue($outputName)")
                        } else if (representation == EParamRepresentation.INSTANTIATED) {
                            // target.appendLine("$indention2$outputName  = query.getDictionary().createValue($myOutputName)")
                            target.appendLine("${outputName}_type = ETripleComponentTypeExt.ERROR")
                        }
                    }
                } else {
                    val myInputInstances = Array(inputNames.size) { "${prefix}_${prefix_counter++}" }
                    val myOutputInstance = "${prefix}_${prefix_counter++}"
                    /**for (i in 0 until inputNames.size) {
                     val converter = getRepresentationConversionFunction(implementation.childrenTypes[i], EParamRepresentation.BYTEARRAYWRAPPER, EParamRepresentation.INSTANTIATED)
                     converter.generate(localindention, myInputNames[i], myInputInstances[i], imports, target, globalVariables)
                     }**/
                    for (i in inputNames.indices) {
                        // myInputInstances[i] = suffixNames(inputNames[i], ETripleComponentTypeToEVariablePlaceholder[implementation.childrenTypes[i]])
                        myInputInstances[i] = inputNames[i]
                    }
                    implementation.generateInstantiated(
                        indention,
                        myInputInstances,
                        outputName,
                        "${prefix}_${prefix_counter++}",
                        imports,
                        target,
                        globalVariables,
                        ::prefixVal_no,
                    ) { indention2, resultType ->
                        outputType.add(resultType)
                        if (resultType == ETripleComponentTypeExt.BLANK_NODE) {
                            target.appendLine("$indention2$outputName = $myOutputInstance")
                            target.appendLine("${outputName}_type = ETripleComponentTypeExt.BLANK_NODE")
                        } else {
                            if (representation == EParamRepresentation.INSTANTIATED) {
                                target.appendLine("${outputName}_type = ETripleComponentTypeExt.${ETripleComponentTypeExt.names[resultType]}")
                            } else {
                                val converter = getRepresentationConversionFunction(
                                    resultType,
                                    EParamRepresentation.INSTANTIATED,
                                    EParamRepresentation.BYTEARRAYWRAPPER,
                                )
                                converter.generate(
                                    indention2,
                                    myOutputInstance,
                                    "tmp_$outputName",
                                    imports,
                                    target,
                                    globalVariables,
                                    ::prefixVal_no,
                                )
                                if (representation == EParamRepresentation.ID) {
                                    target.appendLine("$indention2$outputName = query.getDictionary().createValue(tmp_$outputName)")
                                }
                            }
                        }
                    }
                }
            }

            if (target.toString() != "") {
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
        val method = StringBuilder()
        val imports2 = mutableSetOf<String>()
        val target = StringBuilder()

        val globalVariables = mutableSetOf<String>()
        generate("            ", EParamRepresentation.ID, Array(implementations[0].childrenTypes.size) { "childIn$it" }, "res", "tmp", imports2, target, globalVariables)
        imports2.addAll(imports)

        method.appendLine("    override fun evaluateID(row: IteratorBundle): () -> DictionaryValueType {")

        for (v in globalVariables) {
            if (!v.contains(" res: ")) {
                method.appendLine("        $v")
            }
        }

        method.appendLine("}")
        return method
    }

    public fun generateAOP(): StringBuilder {
        val clazz = StringBuilder()
        clazz.appendLine("package lupos.operator.arithmetik.generated")
        clazz.appendLine("")
        val imports2 = mutableSetOf<String>()
        imports2.addAll(imports)
        val target = StringBuilder()
        val globalVariables = mutableSetOf<String>()
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
        clazz.appendLine("    override fun evaluateID(row: IteratorBundle): () -> DictionaryValueType {")
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
    override fun compareTo(other: MyOperatorPart): Int {
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
        MutableSet<String>, // globalVariables,
        (String, EVariablePlaceholder) -> String, // valPrefix
    ) -> Unit,
)

public class MyOperatorPartFactory {
    internal var list = mutableListOf<MyOperatorPart>()
    private var generateInstantiatedOther: GenerateFunc? = null
    private var generateByteArrayWrapperOther: GenerateFunc? = null

    public fun build(): Array<MyOperatorPart> {
        if (generateInstantiatedOther != null) {
            when (val paramsCount = list[0].childrenTypes.size) {
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
                                ),
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
                                    ),
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
                                        ),
                                    )
                                }
                            }
                        }
                    }
                }
                else -> {
                    TODO("not implemented $paramsCount")
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
                ),
            )
        }
        return this
    }

    public fun addNumericBinaryOperatorStub(operator: String): MyOperatorPartFactory {
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.INTEGER),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                    imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                    imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == BigInteger.ZERO) {")
                        onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Decimal)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Decimal)} $operator BigDecimal.fromBigInteger(${suffixNames(inputNames[1], EVariablePlaceholderExt.Integer)})")
                        onResult("$indention    ", ETripleComponentTypeExt.DECIMAL)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Decimal)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Decimal)} $operator BigDecimal.fromBigInteger(${suffixNames(inputNames[1], EVariablePlaceholderExt.Integer)})")
                        onResult("$indention    ", ETripleComponentTypeExt.DECIMAL)
                    }
                },
            ),
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                    imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == BigInteger.ZERO) {")
                        onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)} $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Integer)}.doubleValue()")
                        onResult("$indention    ", ETripleComponentTypeExt.FLOAT)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)} $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Integer)}.doubleValue()")
                        onResult("$indention    ", ETripleComponentTypeExt.FLOAT)
                    }
                },
            ),
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.INTEGER),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                    imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == BigInteger.ZERO) {")
                        onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)} $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Integer)}.doubleValue()")
                        onResult("$indention    ", ETripleComponentTypeExt.DOUBLE)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)} $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Integer)}.doubleValue()")
                        onResult("$indention    ", ETripleComponentTypeExt.DOUBLE)
                    }
                },
            ),
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.DECIMAL),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                    imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == BigDecimal.ZERO) {")
                        onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Decimal)} = BigDecimal.fromBigInteger(${suffixNames(inputNames[0], EVariablePlaceholderExt.Integer)}) $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Decimal)}")
                        onResult("$indention    ", ETripleComponentTypeExt.DECIMAL)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Decimal)} = BigDecimal.fromBigInteger(${suffixNames(inputNames[0], EVariablePlaceholderExt.Integer)}) $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Decimal)}")
                        onResult("$indention    ", ETripleComponentTypeExt.DECIMAL)
                    }
                },
            ),
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DECIMAL),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                    imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == BigDecimal.ZERO) {")
                        onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Decimal)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Decimal)} $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Decimal)}")
                        onResult("$indention    ", ETripleComponentTypeExt.DECIMAL)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Decimal)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Decimal)} $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Decimal)}")
                        onResult("$indention    ", ETripleComponentTypeExt.DECIMAL)
                    }
                },
            ),
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.DECIMAL),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                    imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == BigDecimal.ZERO) {")
                        onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)} $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Decimal)}.doubleValue()")
                        onResult("$indention    ", ETripleComponentTypeExt.FLOAT)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)} $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Decimal)}.doubleValue()")
                        onResult("$indention    ", ETripleComponentTypeExt.FLOAT)
                    }
                },
            ),
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.DECIMAL),
                generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                    imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == BigDecimal.ZERO) {")
                        onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)} $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Decimal)}.doubleValue()")
                        onResult("$indention    ", ETripleComponentTypeExt.DOUBLE)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)} $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Decimal)}.doubleValue()")
                        onResult("$indention    ", ETripleComponentTypeExt.DOUBLE)
                    }
                },
            ),
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.FLOAT),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                        onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Integer)}.doubleValue() $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Double)}")
                        onResult("$indention    ", ETripleComponentTypeExt.FLOAT)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Integer)}.doubleValue() $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Double)}")
                        onResult("$indention    ", ETripleComponentTypeExt.FLOAT)
                    }
                },
            ),
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.FLOAT),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                        onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Decimal)}.doubleValue() $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Double)}")
                        onResult("$indention    ", ETripleComponentTypeExt.FLOAT)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Decimal)}.doubleValue() $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Double)}")
                        onResult("$indention    ", ETripleComponentTypeExt.FLOAT)
                    }
                },
            ),
        )

        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.FLOAT),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                        onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)} $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Double)}")
                        onResult("$indention    ", ETripleComponentTypeExt.FLOAT)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)} $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Double)}")
                        onResult("$indention    ", ETripleComponentTypeExt.FLOAT)
                    }
                },
            ),
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.FLOAT),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                        onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)} $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Double)}")
                        onResult("$indention    ", ETripleComponentTypeExt.DOUBLE)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)} $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Double)}")
                        onResult("$indention    ", ETripleComponentTypeExt.DOUBLE)
                    }
                },
            ),
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.DOUBLE),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                        onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Integer)}.doubleValue() $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Double)}")
                        onResult("$indention    ", ETripleComponentTypeExt.DOUBLE)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Integer)}.doubleValue() $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Double)}")
                        onResult("$indention    ", ETripleComponentTypeExt.DOUBLE)
                    }
                },
            ),
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                        onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Decimal)}.doubleValue() $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Double)}")
                        onResult("$indention    ", ETripleComponentTypeExt.DOUBLE)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Decimal)}.doubleValue() $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Double)}")
                        onResult("$indention    ", ETripleComponentTypeExt.DOUBLE)
                    }
                },
            ),
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.DOUBLE),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                        onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)} $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Double)}")
                        onResult("$indention    ", ETripleComponentTypeExt.DOUBLE)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)} $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Double)}")
                        onResult("$indention    ", ETripleComponentTypeExt.DOUBLE)
                    }
                },
            ),
        )
        list.add(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.DOUBLE),
                generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                    if (operator == "/") {
                        target.appendLine("${indention}if (${inputNames[1]} == 0.0) {")
                        onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                        target.appendLine("$indention} else {")
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)} $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Double)}")
                        onResult("$indention    ", ETripleComponentTypeExt.DOUBLE)
                        target.appendLine("$indention}")
                    } else {
                        target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)} $operator ${suffixNames(inputNames[1], EVariablePlaceholderExt.Double)}")
                        onResult("$indention    ", ETripleComponentTypeExt.DOUBLE)
                    }
                },
            ),
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
        generate = { indention, inputName, outputName, imports, target, _, valPrefix ->
            imports.add("lupos.shared.inline.DictionaryHelper")
            imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
            target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Integer)} = DictionaryHelper.byteArrayToInteger_I($inputName)")
        },
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.INTEGER,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables, valPrefix ->
            imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
            imports.add("lupos.shared.inline.DictionaryHelper")
            globalVariables.add("${valPrefix(outputName, EVariablePlaceholderExt.ByteArrayWrapper)} = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.integerToByteArray($outputName, $inputName)")
        },
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.DECIMAL,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _, valPrefix ->
            imports.add("lupos.shared.inline.DictionaryHelper")
            imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
            target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Decimal)} = DictionaryHelper.byteArrayToDecimal_I($inputName)")
        },
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.DECIMAL,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables, valPrefix ->
            imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
            imports.add("lupos.shared.inline.DictionaryHelper")
            globalVariables.add("${valPrefix(outputName, EVariablePlaceholderExt.ByteArrayWrapper)} = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.decimalToByteArray($outputName, $inputName)")
        },
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.DOUBLE,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _, valPrefix ->
            imports.add("lupos.shared.inline.DictionaryHelper")
            target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Double)} = DictionaryHelper.byteArrayToDouble_I($inputName)")
        },
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.DOUBLE,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables, valPrefix ->
            imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
            imports.add("lupos.shared.inline.DictionaryHelper")
            globalVariables.add("${valPrefix(outputName, EVariablePlaceholderExt.ByteArrayWrapper)} = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.doubleToByteArray($outputName, $inputName)")
        },
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.FLOAT,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _, valPrefix ->
            imports.add("lupos.shared.inline.DictionaryHelper")
            target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Double)} = DictionaryHelper.byteArrayToFloat_I($inputName)")
        },
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.STRING_TYPED,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _, valPrefix ->
            imports.add("lupos.shared.inline.DictionaryHelper")
            target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)}= DictionaryHelper.byteArrayToTyped_Content($inputName)")
            target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_type)} = DictionaryHelper.byteArrayToTyped_Type($inputName)")
        },
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.STRING_LANG,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _, valPrefix ->
            imports.add("lupos.shared.inline.DictionaryHelper")
            target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = DictionaryHelper.byteArrayToLang_Content($inputName)")
            target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_lang)} = DictionaryHelper.byteArrayToLang_Lang($inputName)")
        },
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.IRI,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _, valPrefix ->
            imports.add("lupos.shared.inline.DictionaryHelper")
            target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Iri)} = DictionaryHelper.byteArrayToIri($inputName)")
        },
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.STRING,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _, valPrefix ->
            imports.add("lupos.shared.inline.DictionaryHelper")
            target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = DictionaryHelper.byteArrayToString($inputName)")
        },
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.BLANK_NODE,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _, valPrefix ->
            imports.add("lupos.shared.inline.DictionaryHelper")
            target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Blank_Node)} = DictionaryHelper.byteArrayToBnode_I($inputName)")
        },
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.BOOLEAN,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _, valPrefix ->
            imports.add("lupos.shared.inline.DictionaryHelper")
            target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Boolean)} = DictionaryHelper.byteArrayToBoolean($inputName)")
        },
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.BLANK_NODE,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables, valPrefix ->
            imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
            imports.add("lupos.shared.inline.DictionaryHelper")
            globalVariables.add("${valPrefix(outputName, EVariablePlaceholderExt.ByteArrayWrapper)} = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.bnodeToByteArray($outputName, $inputName)")
        },
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.STRING,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables, valPrefix ->
            imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
            imports.add("lupos.shared.inline.DictionaryHelper")
            globalVariables.add("${valPrefix(outputName, EVariablePlaceholderExt.ByteArrayWrapper)} = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.stringToByteArray($outputName, $inputName)")
        },
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.STRING_LANG,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables, valPrefix ->
            imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
            imports.add("lupos.shared.inline.DictionaryHelper")
            globalVariables.add("${valPrefix(outputName, EVariablePlaceholderExt.ByteArrayWrapper)} = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.langToByteArray($outputName, ${inputName}_content, ${inputName}_lang)")
        },
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.STRING_TYPED,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables, valPrefix ->
            imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
            imports.add("lupos.shared.inline.DictionaryHelper")
            globalVariables.add("${valPrefix(outputName, EVariablePlaceholderExt.ByteArrayWrapper)} = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.typedToByteArray($outputName, ${inputName}_content, ${inputName}_type)")
        },
    ),

    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.IRI,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables, valPrefix ->
            imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
            imports.add("lupos.shared.inline.DictionaryHelper")
            globalVariables.add("${valPrefix(outputName, EVariablePlaceholderExt.ByteArrayWrapper)} = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.iriToByteArray($outputName, $inputName)")
        },
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.FLOAT,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables, valPrefix ->
            imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
            imports.add("lupos.shared.inline.DictionaryHelper")
            globalVariables.add("${valPrefix(outputName, EVariablePlaceholderExt.ByteArrayWrapper)} = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.floatToByteArray($outputName, $inputName)")
        },
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.ERROR,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { _, _, _, _, _, _, _ ->
        },
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.UNDEF,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { _, _, _, _, _, _, _ ->
        },
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.BOOLEAN,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, inputName, outputName, imports, target, globalVariables, valPrefix ->
            imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
            imports.add("lupos.shared.inline.DictionaryHelper")
            globalVariables.add("${valPrefix(outputName, EVariablePlaceholderExt.ByteArrayWrapper)} = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.booleanToByteArray($outputName, $inputName)")
        },
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.ERROR,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { indention, _, outputName, imports, target, globalVariables, valPrefix ->
            imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
            imports.add("lupos.shared.inline.DictionaryHelper")
            globalVariables.add("${valPrefix(outputName, EVariablePlaceholderExt.ByteArrayWrapper)} = ByteArrayWrapper()")
            target.appendLine("${indention}DictionaryHelper.errorToByteArray($outputName)")
        },
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.DATE_TIME,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, _, valPrefix ->
            imports.add("lupos.shared.inline.DictionaryHelper")
            imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
            imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
            target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.DateTime_typed_content)} = DictionaryHelper.byteArrayToDateTimeAsTyped_Content($inputName)")
            target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.DateTime_year)} = DictionaryHelper.byteArrayToDateTime_Year($inputName)")
            target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.DateTime_month)} = DictionaryHelper.byteArrayToDateTime_Month($inputName)")
            target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.DateTime_day)} = DictionaryHelper.byteArrayToDateTime_Day($inputName)")
            target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.DateTime_hours)} = DictionaryHelper.byteArrayToDateTime_Hours($inputName)")
            target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.DateTime_minutes)} = DictionaryHelper.byteArrayToDateTime_Minutes($inputName)")
            target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.DateTime_seconds)}= DictionaryHelper.byteArrayToDateTime_Seconds($inputName)")
            target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.DateTime_tz)} = DictionaryHelper.byteArrayToDateTime_TZ($inputName)")
            target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.DateTime_timezone)} = DictionaryHelper.byteArrayToDateTime_TimeZone($inputName)")
        },
    ),
)
