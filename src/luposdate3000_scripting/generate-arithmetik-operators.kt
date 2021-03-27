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
private enum class OperatorType {
    BuildInCall,
    Function,
}

private enum class EParamRepresentation {
    ID, // represented as dictionary key
    BYTEARRAYWRAPPER, // represented as ByteArrayWrapper
    INSTANTIATED, // represented as the parsed Type itself
}

internal class MyOperator(
    private val name: String,
    private val type: OperatorType,
    private val implementations: Array<MyOperatorPart>,
) {
    internal fun generate(representation: EParamRepresentation, inputNames: Array<String>, outputName: String, prefix: String, imports: MutableSet<String>, target: StringBuilder) {
        if (representation == INSTANTIATED) {
            throw Exception("there is no need to combine functions here")
        }
        var myInputNames = Array<String>(inputNames.size) { inputNames[it] }
        var prefix_counter = 0
        if (representation == EParamRepresentation.ID) {
            for (i in 0 until inputNames.size) {
                myInputNames[i] = "${prefix}_${prefix_counter++}"
                imports.add("lupos.s00misc.ByteArrayWrapper")
                target.appendln("val ${myInputNames[i]} = ByteArrayWrapper()")
                target.appendln("query.getDictionary().getValue(${myInputNames[i]}, ${inputNames[i]})")
            }
        }
        var typeNames = Array<String>(inputNames.size) { "${prefix}_${prefix_counter++}" }
        for (i in 0 until inputNames.size) {
            target.appendln("val ${typeNames[i]} = DictionaryHelper.byteArrayToType(${myInputNames[i]}")
        }
        var myOutputName = "${prefix}_${prefix_counter++}"
        var first = true
        for (implementation in implementations) {
            var cond = ""
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
                cond += "${typeNames[i]} == ETripleComponentType.${ETripleComponentType.names[implementation.childrenTypes[i]]}"
            }
            cond += ") {"
            target.appendln(cond)
            var myInputInstances = Array(inputNames.size) { "${prefix}_${prefix_counter++}" }
            var myOutputInstance = "${prefix}_${prefix_counter++}"
            for (i in 0 until inputNames.size) {
                val converter = getRepresentationConversionFunction(implementation.childrenTypes[i], EParamRepresentation.BYTEARRAYWRAPPER, EParamRepresentation.INSTANTIATED)
                converter.generate(myInputNames[i], myInputInstances[i], imports, target)
            }
            implementation.generate(myInputInstances, myOutputInstance, "${prefix}_${prefix_counter++}", imports, target)
            val converter = getRepresentationConversionFunction(implementation.resultType, EParamRepresentation.INSTANTIATED, EParamRepresentation.BYTEARRAYWRAPPER)
            converter.generate(myOutputInstance, myOutputName, imports, target)
        }
        target.appendln("}")
        if (representation == EParamRepresentation.ID) {
            target.appendln("val $outputName = query.getDictionary().createValue($myOutputName)")
        }
    }
}

internal class MyOperatorPart(
    private val childrenTypes: Array<ETripleComponentType>,
    private val resultType: ETripleComponentType,
    private val generate: (
        Array<String>, // inputNames
        String, // outputName
        String, // prefix (for intermediates)
        MutableSet<String>, // imports
        StringBuilder, // target
    ) -> Unit
)

internal class MyRepresentationConversionFunction(
    type: ETripleComponentType,
    inputRepresentation: EParamRepresentation,
    outputRepresentation: EParamRepresentation,
    generate: (
        String, // inputName
        String, // outputName
        MutableSet<String>, // imports
        StringBuilder, // target
    ) -> Unit
)

val operators = listOf(
    MyOperator(
        name = "ABS",
        type = OperatorType.BuildInCall,
        implementations = arrayOf(
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                resultType = ETripleComponentTypeExt.INTEGER,
                generate = { inputNames, outputName, prefix, imports, target ->
                    target.appendln("val $outputName = ${inputNames[0]}.abs()")
                },
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                resultType = ETripleComponentTypeExt.DECIMAL,
                generate = { inputNames, outputName, prefix, imports, target ->
                    target.appendln("val $outputName = ${inputNames[0]}.abs()")
                },
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                resultType = ETripleComponentTypeExt.DOUBLE,
                generate = { inputNames, outputName, prefix, imports, target ->
                    target.appendln("val $outputName = abs(${inputNames[0]})")
                },
            ),
            MyOperatorPart(
                childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                resultType = ETripleComponentTypeExt.FLOAT,
                generate = { inputNames, outputName, prefix, imports, target ->
                    target.appendln("val $outputName = abs(${inputNames[0]})")
                },
            ),
        ),
    ),
)
val converters = listOf(
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.INTEGER,
        inputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        outputRepresentation = EParamRepresentation.INSTANTIATED,
        generate = { inputName, outputName, imports, target ->
            imports.add("lupos.dictionary.DictionaryHelper")
            target.appendln("val $outputName = MyBigInteger(DictionaryHelper.byteArrayToInteger($inputName))")
        }
    ),
    MyRepresentationConversionFunction(
        type = ETripleComponentTypeExt.INTEGER,
        inputRepresentation = EParamRepresentation.INSTANTIATED,
        outputRepresentation = EParamRepresentation.BYTEARRAYWRAPPER,
        generate = { inputName, outputName, imports, target ->
            imports.add("lupos.s00misc.ByteArrayWrapper")
            imports.add("lupos.dictionary.DictionaryHelper")
            target.appendln("val $outputName = ByteArrayWrapper()")
            target.appendln("DictionaryHelper.integerToByteArray($outputName, $inputName.toString())")
        }
    ),
)

private fun getRepresentationConversionFunction(type: ETripleComponentType, inputRepresentation: EParamRepresentation, outputRepresentation: EParamRepresentation): MyRepresentationConversionFunction {
    for (converter in converters) {
        if (converter.type == type && converter.inputRepresentation == inputRepresentation && converter.outputRepresentation == outputRepresentation) {
            return converter
        }
    }
    throw Exception("not found $type $inputRepresentation $outputRepresentation")
}
