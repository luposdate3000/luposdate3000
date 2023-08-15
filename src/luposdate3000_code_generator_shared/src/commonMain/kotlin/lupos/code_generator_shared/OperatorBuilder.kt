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

import lupos.shared.ETripleComponentTypeExt

public object OperatorBuilder {
    public fun build(operators: MutableList<MyOperator>) {
        operators.add(
            MyOperator(
                name = "ABS",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Integer)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Integer)}.abs()")
                                onResult(indention, ETripleComponentTypeExt.INTEGER)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Decimal)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Decimal)}.abs()")
                                onResult(indention, ETripleComponentTypeExt.DECIMAL)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("kotlin.math.abs")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Double)} = abs(${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)})")
                                onResult(indention, ETripleComponentTypeExt.DOUBLE)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("kotlin.math.abs")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Double)} = abs(${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)})")
                                onResult(indention, ETripleComponentTypeExt.FLOAT)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "ROUND",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Integer)} = ${inputNames[0]}")
                                onResult(indention, ETripleComponentTypeExt.INTEGER)
                            },
                            generateByteArrayWrapper = { indention, inputNames, outputName, _, imports, target, globalVariables, valPrefix, onResult ->
                                imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
                                imports.add("lupos.shared.inline.dynamicArray.ByteArrayWrapperExt")
                                globalVariables.add("${valPrefix(outputName, EVariablePlaceholderExt.ByteArrayWrapper)} = ByteArrayWrapper()")
                                target.appendLine("${indention}ByteArrayWrapperExt.copyInto(${inputNames[0]},$outputName)")
                                onResult(indention, ETripleComponentTypeExt.INTEGER)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                                imports.add("com.ionspin.kotlin.bignum.decimal.RoundingMode")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Decimal)} = ${inputNames[0]}.roundToDigitPositionAfterDecimalPoint(0, RoundingMode.ROUND_HALF_AWAY_FROM_ZERO)")
                                onResult(indention, ETripleComponentTypeExt.DECIMAL)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("kotlin.math.roundToInt")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${inputNames[0]}.roundToInt().toDouble()")
                                onResult(indention, ETripleComponentTypeExt.DOUBLE)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("kotlin.math.roundToInt")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${inputNames[0]}.roundToInt().toDouble()")
                                onResult(indention, ETripleComponentTypeExt.FLOAT)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "FLOOR",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Integer)} = ${inputNames[0]}")
                                onResult(indention, ETripleComponentTypeExt.INTEGER)
                            },
                            generateByteArrayWrapper = { indention, inputNames, outputName, _, imports, target, globalVariables, valPrefix, onResult ->
                                imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
                                imports.add("lupos.shared.inline.dynamicArray.ByteArrayWrapperExt")
                                globalVariables.add("${valPrefix(outputName, EVariablePlaceholderExt.ByteArrayWrapper)} = ByteArrayWrapper()")
                                target.appendLine("${indention}ByteArrayWrapperExt.copyInto(${inputNames[0]},$outputName)")
                                onResult(indention, ETripleComponentTypeExt.INTEGER)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Decimal)} = ${inputNames[0]}.floor()")
                                onResult(indention, ETripleComponentTypeExt.DECIMAL)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("kotlin.math.floor")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Double)} = floor(${inputNames[0]})")
                                onResult(indention, ETripleComponentTypeExt.DOUBLE)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("kotlin.math.floor")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Double)} = floor(${inputNames[0]})")
                                onResult(indention, ETripleComponentTypeExt.FLOAT)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "CEIL",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Integer)} = ${inputNames[0]}")
                                onResult(indention, ETripleComponentTypeExt.INTEGER)
                            },
                            generateByteArrayWrapper = { indention, inputNames, outputName, _, imports, target, globalVariables, valPrefix, onResult ->
                                imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
                                imports.add("lupos.shared.inline.dynamicArray.ByteArrayWrapperExt")
                                globalVariables.add("${valPrefix(outputName, EVariablePlaceholderExt.ByteArrayWrapper)} = ByteArrayWrapper()")
                                target.appendLine("${indention}ByteArrayWrapperExt.copyInto(${inputNames[0]},$outputName)")
                                onResult(indention, ETripleComponentTypeExt.INTEGER)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Decimal)} = ${inputNames[0]}.ceil()")
                                onResult(indention, ETripleComponentTypeExt.DECIMAL)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("kotlin.math.ceil")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ceil(${inputNames[0]})")
                                onResult(indention, ETripleComponentTypeExt.DOUBLE)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("kotlin.math.ceil")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ceil(${inputNames[0]})")
                                onResult(indention, ETripleComponentTypeExt.FLOAT)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
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
                    ).setOther(generateInstantiatedTrue2, generateByteArrayWrapperTrue2).build(),
                generateInstantiatedOther = generateInstantiatedTrue,
                generateIDOther = generateIDTrue,
                generateByteArrayWrapperOther = generateByteArrayWrapperTrue,
            ),
        )
        operators.add(
            MyOperator(
                name = "STRLEN",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Integer)} = BigInteger(${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}.length)")
                                onResult(indention, ETripleComponentTypeExt.INTEGER)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Integer)} = BigInteger(${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}.length)")
                                onResult(indention, ETripleComponentTypeExt.INTEGER)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Integer)} = BigInteger(${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}.length)")
                                onResult(indention, ETripleComponentTypeExt.INTEGER)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "LCASE",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}.lowercase()")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}.lowercase()")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_type)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_type)}")
                                onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}.lowercase()")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_lang)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_lang)}")
                                onResult(indention, ETripleComponentTypeExt.STRING_LANG)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
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
                            arrayOf(ETripleComponentTypeExt.DATE_TIME),
                        ),
                        generateInstantiated = { indention, _, outputName, _, imports, target, _, valPrefix, onResult ->
                            imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                            target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = \"\"")
                            onResult(indention, ETripleComponentTypeExt.STRING)
                        },
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_lang)}")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "STR",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.IRI),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Iri)}")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Integer)}.toString()")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Decimal)}.toString()")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)}.toString()")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)}.toString()")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.BOOLEAN),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Boolean)}.toString()")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.DateTime_typed_content)}")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
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
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_type)} = \"http://www.w3.org/2001/XMLSchema#string\"")
                                onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_type)} = \"http://www.w3.org/2001/XMLSchema#string\"")
                                onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_type)} = \"http://www.w3.org/2001/XMLSchema#string\"")
                                onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Integer)}.toString()")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_type)} = \"http://www.w3.org/2001/XMLSchema#string\"")
                                onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Decimal)}.toString()")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_type)} = \"http://www.w3.org/2001/XMLSchema#string\"")
                                onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)}.toString()")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_type)} = \"http://www.w3.org/2001/XMLSchema#string\"")
                                onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)}.toString()")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_type)} = \"http://www.w3.org/2001/XMLSchema#string\"")
                                onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.BOOLEAN),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Boolean)}.toString()")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_type)} = \"http://www.w3.org/2001/XMLSchema#string\"")
                                onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.DateTime_typed_content)}")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_type)} = \"http://www.w3.org/2001/XMLSchema#string\"")
                                onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "UCASE",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}.toUpperCase()")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}.toUpperCase()")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_type)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_type)}")
                                onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}.toUpperCase()")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_lang)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_lang)}")
                                onResult(indention, ETripleComponentTypeExt.STRING_LANG)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
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
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)}")
                                onResult(indention, ETripleComponentTypeExt.DOUBLE)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)}")
                                onResult(indention, ETripleComponentTypeExt.DOUBLE)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Decimal)}.doubleValue()")
                                onResult(indention, ETripleComponentTypeExt.DOUBLE)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Integer)}.doubleValue()")
                                onResult(indention, ETripleComponentTypeExt.DOUBLE)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.BOOLEAN),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Double)} = if (${suffixNames(inputNames[0], EVariablePlaceholderExt.Boolean)}) {")
                                target.appendLine("$indention    1.0")
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    0.0")
                                target.appendLine("$indention}")
                                onResult(indention, ETripleComponentTypeExt.DOUBLE)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}try {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}.toDouble()")
                                onResult("$indention    ", ETripleComponentTypeExt.DOUBLE)
                                target.appendLine("$indention} catch (e: Throwable) {")
                                onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                                target.appendLine("$indention}")
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}try {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}.toDouble()")
                                onResult("$indention    ", ETripleComponentTypeExt.DOUBLE)
                                target.appendLine("$indention} catch (e: Throwable) {")
                                onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                                target.appendLine("$indention}")
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}try {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} =${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}.toDouble()")
                                onResult("$indention    ", ETripleComponentTypeExt.DOUBLE)
                                target.appendLine("$indention} catch (e: Throwable) {")
                                onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                                target.appendLine("$indention}")
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
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
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)}")
                                onResult(indention, ETripleComponentTypeExt.FLOAT)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)}")
                                onResult(indention, ETripleComponentTypeExt.FLOAT)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Decimal)}.doubleValue()")
                                onResult(indention, ETripleComponentTypeExt.FLOAT)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Integer)}.doubleValue()")
                                onResult(indention, ETripleComponentTypeExt.FLOAT)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.BOOLEAN),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Double)} = if (${suffixNames(inputNames[0], EVariablePlaceholderExt.Boolean)}) {")
                                target.appendLine("$indention    1.0")
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    0.0")
                                target.appendLine("$indention}")
                                onResult(indention, ETripleComponentTypeExt.FLOAT)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}try {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}.toDouble()")
                                onResult("$indention    ", ETripleComponentTypeExt.FLOAT)
                                target.appendLine("$indention} catch (e: Throwable) {")
                                onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                                target.appendLine("$indention}")
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}try {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}.toDouble()")
                                onResult("$indention    ", ETripleComponentTypeExt.FLOAT)
                                target.appendLine("$indention} catch (e: Throwable) {")
                                onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                                target.appendLine("$indention}")
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}try {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Double)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}.toDouble()")
                                onResult("$indention    ", ETripleComponentTypeExt.FLOAT)
                                target.appendLine("$indention} catch (e: Throwable) {")
                                onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                                target.appendLine("$indention}")
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "DAY",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Integer)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.DateTime_day)}")
                                onResult(indention, ETripleComponentTypeExt.INTEGER)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "MONTH",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Integer)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.DateTime_month)}")
                                onResult(indention, ETripleComponentTypeExt.INTEGER)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "HOURS",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Integer)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.DateTime_hours)}")
                                onResult(indention, ETripleComponentTypeExt.INTEGER)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "MINUTES",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Integer)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.DateTime_minutes)}")
                                onResult(indention, ETripleComponentTypeExt.INTEGER)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "YEAR",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.integer.BigInteger")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Integer)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.DateTime_year)}")
                                onResult(indention, ETripleComponentTypeExt.INTEGER)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "SECONDS",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Decimal)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.DateTime_seconds)}")
                                onResult(indention, ETripleComponentTypeExt.DECIMAL)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "TZ",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.DateTime_tz)}")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "TIMEZONE",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.DateTime_timezone)}")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "STRDT",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.IRI),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_type)} = ${suffixNames(inputNames[1], EVariablePlaceholderExt.Iri)}")
                                onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "STRLANG",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_lang)} = ${suffixNames(inputNames[1], EVariablePlaceholderExt.String_content)}")
                                onResult(indention, ETripleComponentTypeExt.STRING_LANG)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "LANGMATCHES",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Boolean)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)} == ${suffixNames(inputNames[1], EVariablePlaceholderExt.String_content)}")
                                onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        // TODO OPTIMIZE WITH TYPE CONVERSION
        operators.add(
            MyOperator(
                name = "STRAFTER",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}.indexOf(${inputNames[1]})")
                                target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}.substring(${prefix}_idx + ${inputNames[1]}.length, ${inputNames[0]}.length)")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING)
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = \"\"")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING)
                                target.appendLine("$indention}")
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}.indexOf(${inputNames[1]}_content)")
                                target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}.substring(${prefix}_idx + ${inputNames[1]}_content.length, ${inputNames[0]}.length)")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING)
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = \"\"")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING)
                                target.appendLine("$indention}")
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}.indexOf(${inputNames[1]}_content)")
                                target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}.substring(${prefix}_idx + ${inputNames[1]}_content.length, ${inputNames[0]}.length)")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING)
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = \"\"")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING)
                                target.appendLine("$indention}")
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]})")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_lang)} = ${inputNames[0]}_lang")
                                target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}_content.substring(${prefix}_idx + ${inputNames[1]}.length, ${inputNames[0]}_content.length)")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_LANG)
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = \"\"")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_LANG)
                                target.appendLine("$indention}")
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]}_content)")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_lang)} = ${inputNames[0]}_lang")
                                target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}_content.substring(${prefix}_idx + ${inputNames[1]}_content.length, ${inputNames[0]}_content.length)")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_LANG)
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = \"\"")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_LANG)
                                target.appendLine("$indention}")
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]}_content)")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_lang)} = ${inputNames[0]}_lang")
                                target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}_content.substring(${prefix}_idx + ${inputNames[1]}_content.length, ${inputNames[0]}_content.length)")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_LANG)
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = \"\"")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_LANG)
                                target.appendLine("$indention}")
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]})")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_type)} = ${inputNames[0]}_type")
                                target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}_content.substring(${prefix}_idx + ${inputNames[1]}.length, ${inputNames[0]}_content.length)")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_TYPED)
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = \"\"")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_TYPED)
                                target.appendLine("$indention}")
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]}_content)")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_type)} = ${inputNames[0]}_type")
                                target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}_content.substring(${prefix}_idx + ${inputNames[1]}_content.length, ${inputNames[0]}_content.length)")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_TYPED)
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = \"\"")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_TYPED)
                                target.appendLine("$indention}")
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]}_content)")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_type)} = ${inputNames[0]}_type")
                                target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}_content.substring(${prefix}_idx + ${inputNames[1]}_content.length, ${inputNames[0]}_content.length)")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_TYPED)
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = \"\"")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_TYPED)
                                target.appendLine("$indention}")
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        // TODO OPTIMIZE WITH TYPE CONVERSION
        operators.add(
            MyOperator(
                name = "STRBEFORE",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}.indexOf(${inputNames[1]})")
                                target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}.substring(0, ${prefix}_idx)")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING)
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = \"\"")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING)
                                target.appendLine("$indention}")
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}.indexOf(${inputNames[1]}_content)")
                                target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}.substring(0, ${prefix}_idx)")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING)
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = \"\"")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING)
                                target.appendLine("$indention}")
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}.indexOf(${inputNames[1]}_content)")
                                target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}.substring(0, ${prefix}_idx)")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING)
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = \"\"")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING)
                                target.appendLine("$indention}")
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]})")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_lang)} = ${inputNames[0]}_lang")
                                target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}_content.substring(0, ${prefix}_idx)")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_LANG)
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = \"\"")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_LANG)
                                target.appendLine("$indention}")
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]}_content)")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_lang)} = ${inputNames[0]}_lang")
                                target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}_content.substring(0, ${prefix}_idx)")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_LANG)
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = \"\"")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_LANG)
                                target.appendLine("$indention}")
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]}_content)")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_lang)} = ${inputNames[0]}_lang")
                                target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}_content.substring(0, ${prefix}_idx)")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_LANG)
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = \"\"")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_LANG)
                                target.appendLine("$indention}")
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}val${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]})")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_type)} = ${inputNames[0]}_type")
                                target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}_content.substring(0, ${prefix}_idx)")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_TYPED)
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention   ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = \"\"")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_TYPED)
                                target.appendLine("$indention}")
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]}_content)")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_type)} = ${inputNames[0]}_type")
                                target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}_content.substring(0, ${prefix}_idx)")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_TYPED)
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = \"\"")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_TYPED)
                                target.appendLine("$indention}")
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, prefix, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}val ${prefix}_idx: Int = ${inputNames[0]}_content.indexOf(${inputNames[1]}_content)")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_type)} = ${inputNames[0]}_type")
                                target.appendLine("${indention}if (${prefix}_idx >= 0) {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}_content.substring(0, ${prefix}_idx)")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_TYPED)
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = \"\"")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_TYPED)
                                target.appendLine("$indention}")
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        // TODO OPTIMIZE WITH TYPE CONVERSION
        operators.add(
            MyOperator(
                name = "STRENDS",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Boolean)} = ${inputNames[0]}.endsWith(${inputNames[1]})")
                                onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Boolean)} = ${inputNames[0]}_content.endsWith(${inputNames[1]})")
                                onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Boolean)} = ${inputNames[0]}_content.endsWith(${inputNames[1]})")
                                onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        // TODO OPTIMIZE WITH TYPE CONVERSION
        operators.add(
            MyOperator(
                name = "STRSTARTS",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Boolean)} = ${inputNames[0]}.startsWith(${inputNames[1]})")
                                onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Boolean)} = ${inputNames[0]}_content.startsWith(${inputNames[1]})")
                                onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Boolean)} = ${inputNames[0]}_content.startsWith(${inputNames[1]})")
                                onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        // TODO OPTIMIZE WITH TYPE CONVERSION
        operators.add(
            MyOperator(
                name = "CONTAINS",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Boolean)} = ${inputNames[0]}.contains(${inputNames[1]})")
                                onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Boolean)} = ${inputNames[0]}_content.contains(${inputNames[1]})")
                                onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Boolean)} = ${inputNames[0]}_content.contains(${inputNames[1]})")
                                onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        // TODO OPTIMIZE WITH TYPE CONVERSION
        operators.add(
            MyOperator(
                name = "CONCAT",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]} + ${inputNames[1]}")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]} + ${inputNames[1]}_content")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}_content + ${inputNames[1]}")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING, ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]} + ${inputNames[1]}_content")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}_content + ${inputNames[1]}")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}_content + ${inputNames[1]}_content")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}_content + ${inputNames[1]}_content")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}if (${inputNames[0]}_lang == ${inputNames[1]}_lang) {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}_content + ${inputNames[1]}_content")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_lang)} = ${inputNames[0]}_lang")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_LANG)
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}_content + ${inputNames[1]}_content")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING)
                                target.appendLine("$indention}")
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED, ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}if (${inputNames[0]}_type == \"http://www.w3.org/2001/XMLSchema#string\" && ${inputNames[1]}_type == \"http://www.w3.org/2001/XMLSchema#string\") {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}_content + ${inputNames[1]}_content")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_type)} = \"http://www.w3.org/2001/XMLSchema#string\"")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING_TYPED)
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}_content + ${inputNames[1]}_content")
                                onResult("$indention    ", ETripleComponentTypeExt.STRING)
                                target.appendLine("$indention}")
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "Not",
                type = OperatorType.Basic,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.BOOLEAN),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Boolean)} = !${suffixNames(inputNames[0], EVariablePlaceholderExt.Boolean)}")
                                onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "And",
                type = OperatorType.Basic,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.BOOLEAN),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Boolean)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Boolean)} && ${suffixNames(inputNames[1], EVariablePlaceholderExt.Boolean)}")
                                onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.ERROR),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}if (${suffixNames(inputNames[0], EVariablePlaceholderExt.Boolean)}) {")
                                onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Boolean)} = false")
                                onResult("$indention    ", ETripleComponentTypeExt.BOOLEAN)
                                target.appendLine("$indention}")
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.BOOLEAN),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}if (${suffixNames(inputNames[1], EVariablePlaceholderExt.Boolean)}) {")
                                onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Boolean)} = false")
                                onResult("$indention    ", ETripleComponentTypeExt.BOOLEAN)
                                target.appendLine("$indention}")
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "Or",
                type = OperatorType.Basic,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.BOOLEAN),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Boolean)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Boolean)} || ${suffixNames(inputNames[1], EVariablePlaceholderExt.Boolean)}")
                                onResult(indention, ETripleComponentTypeExt.BOOLEAN)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.ERROR),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}if (${suffixNames(inputNames[0], EVariablePlaceholderExt.Boolean)}) {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Boolean)} = true")
                                onResult("$indention    ", ETripleComponentTypeExt.BOOLEAN)
                                target.appendLine("$indention} else {")
                                onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                                target.appendLine("$indention}")
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.BOOLEAN),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}if (${suffixNames(inputNames[1], EVariablePlaceholderExt.Boolean)}) {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Boolean)} = true")
                                onResult("$indention    ", ETripleComponentTypeExt.BOOLEAN)
                                target.appendLine("$indention} else {")
                                onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                                target.appendLine("$indention}")
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "MD5",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("lupos.shared.Crypto_MD5")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = Crypto_MD5.md5(${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)})")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("lupos.shared.Crypto_MD5")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = Crypto_MD5.md5(${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)})")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("lupos.shared.Crypto_MD5")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = Crypto_MD5.md5(${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)})")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "SHA1",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("lupos.shared.Crypto")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = Crypto.sha1(${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)})")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("lupos.shared.Crypto")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = Crypto.sha1(${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)})")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("lupos.shared.Crypto")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = Crypto.sha1(${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)})")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "SHA256",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("lupos.shared.Crypto")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = Crypto.sha256(${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)})")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("lupos.shared.Crypto")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = Crypto.sha256(${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)})")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                imports.add("lupos.shared.Crypto")
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = Crypto.sha256(${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)})")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
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
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.UNDEF),
                            generateInstantiated = generateInstantiatedError2,
                            generateByteArrayWrapper = generateByteArrayWrapperError2,
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.IRI),
                            generateInstantiated = generateInstantiatedTrue2,
                            generateByteArrayWrapper = generateByteArrayWrapperTrue2,
                        ),
                    ).setOther(generateInstantiatedFalse2, generateByteArrayWrapperFalse2).build(),
                generateInstantiatedOther = generateInstantiatedFalse,
                generateIDOther = generateIDFalse,
                generateByteArrayWrapperOther = generateByteArrayWrapperFalse,
            ),
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
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.UNDEF),
                            generateInstantiated = generateInstantiatedError2,
                            generateByteArrayWrapper = generateByteArrayWrapperError2,
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.IRI),
                            generateInstantiated = generateInstantiatedFalse2,
                            generateByteArrayWrapper = generateByteArrayWrapperFalse2,
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.BLANK_NODE),
                            generateInstantiated = generateInstantiatedFalse2,
                            generateByteArrayWrapper = generateByteArrayWrapperFalse2,
                        ),
                    ).setOther(generateInstantiatedTrue2, generateByteArrayWrapperTrue2).build(),
                generateInstantiatedOther = generateInstantiatedTrue,
                generateIDOther = generateIDTrue,
                generateByteArrayWrapperOther = generateByteArrayWrapperTrue,
            ),
        )
        operators.add(
            MyOperator(
                name = "BNODE1",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.ERROR),
                            generateInstantiated = { indention, _, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Blank_Node)} = query.getDictionary().createNewBNode(\"ERROR_ERROR\")")
                                onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.UNDEF),
                            generateInstantiated = { indention, _, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Blank_Node)} = query.getDictionary().createNewBNode(\"UNDEF_UNDEF\")")
                                onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.BLANK_NODE),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Blank_Node)} = query.getDictionary().createNewBNode(\"BLANK_NODE_\${${suffixNames(inputNames[0], EVariablePlaceholderExt.Blank_Node)}}\")")
                                onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.BOOLEAN),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Blank_Node)} = query.getDictionary().createNewBNode(\"BOOLEAN_\${${suffixNames(inputNames[0], EVariablePlaceholderExt.Boolean)}}\")")
                                onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Blank_Node)} = query.getDictionary().createNewBNode(\"DECIMAL_\${${suffixNames(inputNames[0], EVariablePlaceholderExt.Decimal)}}\")")
                                onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Blank_Node)} = query.getDictionary().createNewBNode(\"DOUBLE_\${${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)}}\")")
                                onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Blank_Node)} = query.getDictionary().createNewBNode(\"FLOAT_\${${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)}}\")")
                                onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Blank_Node)} = query.getDictionary().createNewBNode(\"INTEGER_\${${suffixNames(inputNames[0], EVariablePlaceholderExt.Integer)}}\")")
                                onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.IRI),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Blank_Node)} = query.getDictionary().createNewBNode(\"IRI_\${${suffixNames(inputNames[0], EVariablePlaceholderExt.Iri)}}\")")
                                onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Blank_Node)} = query.getDictionary().createNewBNode(\"STRING_\${${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}}\")")
                                onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Blank_Node)} = query.getDictionary().createNewBNode(\"STRING_LANG_\${${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}_\${${suffixNames(inputNames[0], EVariablePlaceholderExt.String_lang)}\")")
                                onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Blank_Node)} = query.getDictionary().createNewBNode(\"STRING_TYPED_\${${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}_\${${suffixNames(inputNames[0], EVariablePlaceholderExt.String_type)}\")")
                                onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
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
                    ).setOther(generateInstantiatedFalse2, generateByteArrayWrapperFalse2).build(),
                generateInstantiatedOther = generateInstantiatedFalse,
                generateIDOther = generateIDFalse,
                generateByteArrayWrapperOther = generateByteArrayWrapperFalse,
            ),
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
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                            generateInstantiated = generateInstantiatedString("http://www.w3.org/2001/XMLSchema#integer"),
                            generateByteArrayWrapper = generateByteArrayWrapperString("http://www.w3.org/2001/XMLSchema#integer"),
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                            generateInstantiated = generateInstantiatedString("http://www.w3.org/2001/XMLSchema#double"),
                            generateByteArrayWrapper = generateByteArrayWrapperString("http://www.w3.org/2001/XMLSchema#double"),
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                            generateInstantiated = generateInstantiatedString("http://www.w3.org/2001/XMLSchema#decimal"),
                            generateByteArrayWrapper = generateByteArrayWrapperString("http://www.w3.org/2001/XMLSchema#decimal"),
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                            generateInstantiated = generateInstantiatedString("http://www.w3.org/2001/XMLSchema#float"),
                            generateByteArrayWrapper = generateByteArrayWrapperString("http://www.w3.org/2001/XMLSchema#float"),
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                            generateInstantiated = generateInstantiatedString("http://www.w3.org/2001/XMLSchema#string"),
                            generateByteArrayWrapper = generateByteArrayWrapperString("http://www.w3.org/2001/XMLSchema#string"),
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = generateInstantiatedString("http://www.w3.org/1999/02/22-rdf-syntax-ns#langString"),
                            generateByteArrayWrapper = generateByteArrayWrapperString("http://www.w3.org/1999/02/22-rdf-syntax-ns#langString"),
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                            generateInstantiated = generateInstantiatedString("http://www.w3.org/2001/XMLSchema#dateTime"),
                            generateByteArrayWrapper = generateByteArrayWrapperString("http://www.w3.org/2001/XMLSchema#dateTime"),
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.String_content)} = ${inputNames[0]}_type")
                                onResult(indention, ETripleComponentTypeExt.STRING)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
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
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${valPrefix(outputName, EVariablePlaceholderExt.Iri)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Iri)}")
                                onResult(indention, ETripleComponentTypeExt.IRI)
                            },
                            generateByteArrayWrapper = { indention, inputNames, outputName, _, imports, target, globalVariables, valPrefix, onResult ->
                                imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
                                imports.add("lupos.shared.inline.dynamicArray.ByteArrayWrapperExt")
                                globalVariables.add("${valPrefix(outputName, EVariablePlaceholderExt.ByteArrayWrapper)} = ByteArrayWrapper()")
                                target.appendLine("${indention}ByteArrayWrapperExt.copyInto(${inputNames[0]},$outputName)")
                                onResult(indention, ETripleComponentTypeExt.IRI)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}${indention}${valPrefix(outputName, EVariablePlaceholderExt.Iri)} = if (prefix.length > 0 && !prefix.endsWith('/')) {")
                                target.appendLine("$indention    \"\$prefix/\$${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}\"")
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    \"\$prefix\$${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}\"")
                                target.appendLine("$indention}")
                                onResult(indention, ETripleComponentTypeExt.IRI)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}if (${inputNames[0]}_type == \"http://www.w3.org/2001/XMLSchema#string\") {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Iri)} = if (prefix.length > 0 && !prefix.endsWith('/')) {")
                                target.appendLine("$indention        \"\$prefix/\$${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}\"")
                                target.appendLine("$indention    } else {")
                                target.appendLine("$indention        \"\$prefix\$${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}\"")
                                target.appendLine("$indention    }")
                                onResult("$indention    ", ETripleComponentTypeExt.IRI)
                                target.appendLine("$indention} else {")
                                onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                                target.appendLine("$indention}")
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
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
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("$indention ${valPrefix(outputName, EVariablePlaceholderExt.Iri)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Iri)}")
                                onResult(indention, ETripleComponentTypeExt.IRI)
                            },
                            generateByteArrayWrapper = { indention, inputNames, outputName, _, imports, target, globalVariables, valPrefix, onResult ->
                                imports.add("lupos.shared.dynamicArray.ByteArrayWrapper")
                                imports.add("lupos.shared.inline.dynamicArray.ByteArrayWrapperExt")
                                globalVariables.add("${valPrefix(outputName, EVariablePlaceholderExt.ByteArrayWrapper)} = ByteArrayWrapper()")
                                target.appendLine("${indention}ByteArrayWrapperExt.copyInto(${suffixNames(inputNames[0], EVariablePlaceholderExt.Iri)},$outputName)")
                                onResult(indention, ETripleComponentTypeExt.IRI)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("$indention ${valPrefix(outputName, EVariablePlaceholderExt.Iri)} = if (prefix.length > 0 && !prefix.endsWith('/')) {")
                                target.appendLine("$indention    \"\$prefix/\$${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}\"")
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    \"\$prefix\$${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}\"")
                                target.appendLine("$indention}")
                                onResult(indention, ETripleComponentTypeExt.IRI)
                            },
                        ),
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}if (${inputNames[0]}_type == \"http://www.w3.org/2001/XMLSchema#string\") {")
                                target.appendLine("$indention     ${valPrefix(outputName, EVariablePlaceholderExt.Iri)} = if (prefix.length > 0 && !prefix.endsWith('/')) {")
                                target.appendLine("$indention        \"\$prefix/\$${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}\"")
                                target.appendLine("$indention    } else {")
                                target.appendLine("$indention        \"\$prefix\$${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}\"")
                                target.appendLine("$indention    }")
                                onResult("$indention    ", ETripleComponentTypeExt.IRI)
                                target.appendLine("$indention} else {")
                                onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                                target.appendLine("$indention}")
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "Division",
                type = OperatorType.Basic,
                implementations = MyOperatorPartFactory()
                    .addNumericBinaryOperatorStub("/")
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.INTEGER),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}if (${suffixNames(inputNames[1], EVariablePlaceholderExt.Integer)} == BigInteger.ZERO) {")
                                imports.add("com.ionspin.kotlin.bignum.decimal.BigDecimal")
                                onResult("$indention    ", ETripleComponentTypeExt.ERROR)
                                target.appendLine("$indention} else {")
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Decimal)} = BigDecimal.fromBigInteger(${suffixNames(inputNames[0], EVariablePlaceholderExt.Integer)}) / BigDecimal.fromBigInteger(${suffixNames(inputNames[1], EVariablePlaceholderExt.Integer)})")
                                onResult("$indention    ", ETripleComponentTypeExt.DECIMAL)
                                target.appendLine("$indention}")
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "Addition",
                type = OperatorType.Basic,
                implementations = MyOperatorPartFactory()
                    .addNumericBinaryOperatorStub("+")
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.INTEGER),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Integer)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Integer)} + ${suffixNames(inputNames[1], EVariablePlaceholderExt.Integer)}")
                                onResult("$indention    ", ETripleComponentTypeExt.INTEGER)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "Subtraction",
                type = OperatorType.Basic,
                implementations =
                MyOperatorPartFactory()
                    .addNumericBinaryOperatorStub("-")
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.INTEGER),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Integer)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Integer)} - ${suffixNames(inputNames[1], EVariablePlaceholderExt.Integer)}")
                                onResult("$indention    ", ETripleComponentTypeExt.INTEGER)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "Multiplication",
                type = OperatorType.Basic,
                implementations = MyOperatorPartFactory()
                    .addNumericBinaryOperatorStub("*")
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.INTEGER),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("$indention    ${valPrefix(outputName, EVariablePlaceholderExt.Integer)} = ${suffixNames(inputNames[0], EVariablePlaceholderExt.Integer)} * ${suffixNames(inputNames[1], EVariablePlaceholderExt.Integer)}")
                                onResult("$indention    ", ETripleComponentTypeExt.INTEGER)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )

        operators.add(
            MyOperator(
                name = "ToByteArrayWrapper",
                type = OperatorType.BuildInCall,
                implementations = MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}DictionaryHelper.stringToByteArray(${suffixNames(outputName, EVariablePlaceholderExt.ByteArrayWrapper)}, ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)})")
                                onResult(indention, ETripleComponentTypeExt._BYTEARRAYWRAPPER)
                            },
                        ),
                    ).add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.BLANK_NODE),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}DictionaryHelper.bnodeToByteArray(${suffixNames(outputName, EVariablePlaceholderExt.ByteArrayWrapper)}, ${suffixNames(inputNames[0], EVariablePlaceholderExt.Blank_Node)})")
                                onResult(indention, ETripleComponentTypeExt._BYTEARRAYWRAPPER)
                            },
                        ),
                    ).add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}DictionaryHelper.langToByteArray(${suffixNames(outputName, EVariablePlaceholderExt.ByteArrayWrapper)}, ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}, ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_lang)})")
                                onResult(indention, ETripleComponentTypeExt._BYTEARRAYWRAPPER)
                            },
                        ),
                    ).add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}DictionaryHelper.typedToByteArray(${suffixNames(outputName, EVariablePlaceholderExt.ByteArrayWrapper)}, ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_content)}, ${suffixNames(inputNames[0], EVariablePlaceholderExt.String_type)})")
                                onResult(indention, ETripleComponentTypeExt.STRING_TYPED)
                            },
                        ),
                    ).add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.IRI),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}DictionaryHelper.iriToByteArray(${suffixNames(outputName, EVariablePlaceholderExt.ByteArrayWrapper)}, ${suffixNames(inputNames[0], EVariablePlaceholderExt.Iri)})")
                                onResult(indention, ETripleComponentTypeExt._BYTEARRAYWRAPPER)
                            },
                        ),
                    ).add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}DictionaryHelper.floatToByteArray(${suffixNames(outputName, EVariablePlaceholderExt.ByteArrayWrapper)}, ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)})")
                                onResult(indention, ETripleComponentTypeExt._BYTEARRAYWRAPPER)
                            },
                        ),
                    ).add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}DictionaryHelper.doubleToByteArray(${suffixNames(outputName, EVariablePlaceholderExt.ByteArrayWrapper)}, ${suffixNames(inputNames[0], EVariablePlaceholderExt.Double)})")
                                onResult(indention, ETripleComponentTypeExt._BYTEARRAYWRAPPER)
                            },
                        ),
                    ).add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}DictionaryHelper.integerToByteArray(${suffixNames(outputName, EVariablePlaceholderExt.ByteArrayWrapper)}, ${suffixNames(inputNames[0], EVariablePlaceholderExt.Integer)})")
                                onResult(indention, ETripleComponentTypeExt._BYTEARRAYWRAPPER)
                            },
                        ),
                    ).add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.UNDEF),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}DictionaryHelper.undefToByteArray(${suffixNames(outputName, EVariablePlaceholderExt.ByteArrayWrapper)})")
                                onResult(indention, ETripleComponentTypeExt._BYTEARRAYWRAPPER)
                            },
                        ),
                    ).add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.ERROR),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}DictionaryHelper.errorToByteArray(${suffixNames(outputName, EVariablePlaceholderExt.ByteArrayWrapper)})")
                                onResult(indention, ETripleComponentTypeExt._BYTEARRAYWRAPPER)
                            },
                        ),
                    ).add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.BOOLEAN),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}DictionaryHelper.booleanToByteArray(${suffixNames(outputName, EVariablePlaceholderExt.ByteArrayWrapper)}, ${suffixNames(inputNames[0], EVariablePlaceholderExt.Boolean)})")
                                onResult(indention, ETripleComponentTypeExt._BYTEARRAYWRAPPER)
                            },
                        ),
                    ).add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DECIMAL),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}DictionaryHelper.decimalToByteArray(${suffixNames(outputName, EVariablePlaceholderExt.ByteArrayWrapper)}, ${suffixNames(inputNames[0], EVariablePlaceholderExt.Decimal)})")
                                onResult(indention, ETripleComponentTypeExt._BYTEARRAYWRAPPER)
                            },
                        ),
                    ).add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DATE_TIME),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("${indention}DictionaryHelper.dateTimeToByteArray(${suffixNames(outputName, EVariablePlaceholderExt.ByteArrayWrapper)}, ${suffixNames(inputNames[0], EVariablePlaceholderExt.DateTime_typed_content)})")
                                onResult(indention, ETripleComponentTypeExt._BYTEARRAYWRAPPER)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
        operators.add(
            MyOperator(
                name = "ToDictionaryID",
                type = OperatorType.BuildInCall,
                implementations =
                MyOperatorPartFactory()
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt._BYTEARRAYWRAPPER),
                            generateInstantiated = { indention, inputNames, outputName, _, imports, target, _, valPrefix, onResult ->
                                target.appendLine("$indention $outputName = query.getDictionary().createValue(${suffixNames(inputNames[0], EVariablePlaceholderExt.ByteArrayWrapper)})")
                                onResult("$indention    ", ETripleComponentTypeExt._DICTIONARYID)
                            },
                        ),
                    ).setOther(generateInstantiatedError2, generateByteArrayWrapperError2).build(),
                generateInstantiatedOther = generateInstantiatedError,
                generateIDOther = generateIDError,
                generateByteArrayWrapperOther = generateByteArrayWrapperError,
            ),
        )
    }
}
