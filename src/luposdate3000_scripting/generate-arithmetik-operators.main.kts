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

public enum class OperatorType {
    BuildInCall,
    Function,
}

public enum class EParamRepresentation {
    ID, // represented as dictionary key
    BYTEARRAYWRAPPER, // represented as ByteArrayWrapper
    INSTANTIATED, // represented as the parsed Type itself
}

public class MyOperator(
    public val name: String,
    public val type: OperatorType,
    public val implementations: Array<MyOperatorPart>,
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
        for (implementation in implementations) {
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
                cond += "${typeNames[i]} == ETripleComponentTypeExt.${ETripleComponentTypeExt.names[implementation.childrenTypes[i]]}"
            }
            cond += ") {"
            target.appendLine(indention + cond)
            var myInputInstances = Array(inputNames.size) { "${prefix}_${prefix_counter++}" }
            var myOutputInstance = "${prefix}_${prefix_counter++}"
            for (i in 0 until inputNames.size) {
                val converter = getRepresentationConversionFunction(implementation.childrenTypes[i], EParamRepresentation.BYTEARRAYWRAPPER, EParamRepresentation.INSTANTIATED)
                converter.generate(indention + "    ", myInputNames[i], myInputInstances[i], imports, target, globalVariables)
            }
            implementation.generate(indention + "    ", myInputInstances, myOutputInstance, "${prefix}_${prefix_counter++}", imports, target, globalVariables)
            val converter = getRepresentationConversionFunction(implementation.resultType, EParamRepresentation.INSTANTIATED, EParamRepresentation.BYTEARRAYWRAPPER)
            converter.generate(indention + "    ", myOutputInstance, myOutputName, imports, target, globalVariables)
            if (representation == EParamRepresentation.ID) {
                target.appendLine("$indention    $outputName = query.getDictionary().createValue($myOutputName)")
            }
        }
        target.appendLine("$indention} else {")
        if (representation == EParamRepresentation.ID) {
            imports.add("lupos.dictionary.DictionaryExt")
            target.appendLine("$indention    $outputName = DictionaryExt.errorValue")
        } else {
            imports.add("lupos.dictionary.DictionaryHelper")
            target.appendLine("$indention    DictionaryHelper.errorToByteArray($outputName)")
        }
        target.appendLine("$indention}")
    }

    public fun generateAOP(): StringBuilder {
        var clazz = StringBuilder()
        when (implementations[0].childrenTypes.size) {
            0 -> clazz.appendLine("package lupos.s04arithmetikOperators.noinput")
            1 -> clazz.appendLine("package lupos.s04arithmetikOperators.singleinput")
            else -> clazz.appendLine("package lupos.s04arithmetikOperators.multiinput")
        }
        var imports = mutableSetOf<String>()
        var target = StringBuilder()
        var globalVariables = mutableSetOf<String>()
        generate("            ", EParamRepresentation.ID, Array(implementations[0].childrenTypes.size) { "childIn$it" }, "res", "tmp", imports, target, globalVariables)
        imports.add("lupos.s04arithmetikOperators.AOPBase")
        imports.add("lupos.s04logicalOperators.IQuery")
        imports.add("lupos.s04logicalOperators.iterator.IteratorBundle")
        imports.add("lupos.s04logicalOperators.IOPBase")
        imports.add("lupos.s00misc.EOperatorIDExt")
        for (i in imports) {
            clazz.appendLine("import $i")
        }
        var line0 = ""
        line0 += ("public class AOP$type$name public constructor(")
        line0 += ("query: IQuery, ")
        var line = ""
        var line2 = ""
        var line3 = ""
        var line4 = ""
        for (i in 0 until implementations[0].childrenTypes.size) {
            line0 += ("child$i: AOPBase, ")
            line += "child$i, "
            if (i > 0) {
                line2 += ", "
            }
            line2 += "children[$i].toSparql()"
            line3 += " && children[$i] == other.children[$i]"
            line4 += ", children[$i].cloneOP() as AOPBase"
        }
        line0 += (") : AOPBase(")
        line0 += ("query, ")
        line0 += ("EOperatorIDExt.AOP$type${name}ID, ")
        line0 += ("\"AOP$type${name}\", ")
        line0 += ("arrayOf($line)) {")
        clazz.appendLine(line0)
        clazz.appendLine("    override fun toSparql(): String = \"$name(\${$line2})\"")
        clazz.appendLine("    override fun equals(other: Any?): Boolean = other is AOP$type$name$line3")
        clazz.appendLine("    override fun cloneOP(): IOPBase = AOP$name(query$line4)")
        clazz.appendLine("    override fun evaluateID(row: IteratorBundle): () -> Int {")
        for (v in globalVariables) {
            if (!v.contains(" res ")) {
                clazz.appendLine("        $v")
            }
        }
        for (i in 0 until implementations[0].childrenTypes.size) {
            clazz.appendLine("        val child$i = (children[$i] as AOPBase).evaluate(row)")
        }
        clazz.appendLine("        return {")
        clazz.appendLine("            var res:Int")
        for (i in 0 until implementations[0].childrenTypes.size) {
            clazz.appendLine("            val childIn$i = child$i()")
        }
        clazz.append(target.toString())
        clazz.appendLine("            res")
        clazz.appendLine("        }")
        clazz.appendLine("    }")
        clazz.appendLine("}")
        return clazz
    }
}

public class MyOperatorPart(
    public val childrenTypes: Array<ETripleComponentType>,
    public val resultType: ETripleComponentType,
    public val generate: (
        String, // indention
        Array<String>, // inputNames
        String, // outputName
        String, // prefix (for intermediates)
        MutableSet<String>, // imports
        StringBuilder, // target
        MutableSet<String>, // globalVariables
    ) -> Unit
)

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
                resultType = ETripleComponentTypeExt.INTEGER,
                generate = { indention, inputNames, outputName, prefix, imports, target, globalVariables ->
                    target.appendLine("${indention}val $outputName = ${inputNames[0]}.abs()")
                },
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                resultType = ETripleComponentTypeExt.DECIMAL,
                generate = { indention, inputNames, outputName, prefix, imports, target, globalVariables ->
                    target.appendLine("${indention}val $outputName = ${inputNames[0]}.abs()")
                },
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                resultType = ETripleComponentTypeExt.DOUBLE,
                generate = { indention, inputNames, outputName, prefix, imports, target, globalVariables ->
                    target.appendLine("${indention}val $outputName = abs(${inputNames[0]})")
                },
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                resultType = ETripleComponentTypeExt.FLOAT,
                generate = { indention, inputNames, outputName, prefix, imports, target, globalVariables ->
                    target.appendLine("${indention}val $outputName = abs(${inputNames[0]})")
                },
            ),
        ),
    ),
)
public val converters = listOf(
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.INTEGER,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
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
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
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
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
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
            target.appendLine("${indention}DictionaryHelper.doubleToByteArray($outputName, $inputName.toString())")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.FLOAT,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { indention, inputName, outputName, imports, target, globalVariables ->
            imports.add("lupos.dictionary.DictionaryHelper")
            target.appendLine("${indention}val $outputName = DictionaryHelper.byteArrayToFloat($inputName)")
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
            target.appendLine("${indention}DictionaryHelper.floatToByteArray($outputName, $inputName.toString())")
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
    val foldername: String
    when (implementations[0].childrenTypes.size) {
        0 -> foldername = "noinput"
        1 -> foldername = "singleinput"
        else -> foldername = "multiinput"
    }
    File("src/luposdate3000_operator_arithmetik/src/commonMain/kotlin/lupos/s04arithmetikOperators/$foldername/AOP${operator.type}${operator.name}.kt").printWriter { out ->
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
