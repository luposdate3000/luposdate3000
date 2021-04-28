package lupos.scripting

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
                                imports.add("lupos.shared_inline.ByteArrayWrapperExt")
                                globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
                                target.appendLine("${indention}ByteArrayWrapperExt.copyInto(${inputNames[0]},$outputName)")
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
                                imports.add("lupos.shared_inline.ByteArrayWrapperExt")
                                globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
                                target.appendLine("${indention}ByteArrayWrapperExt.copyInto(${inputNames[0]},$outputName)")
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
                                imports.add("lupos.shared_inline.ByteArrayWrapperExt")
                                globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
                                target.appendLine("${indention}ByteArrayWrapperExt.copyInto(${inputNames[0]},$outputName)")
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
                                target.appendLine("${indention}val $outputName: Int = query.getDictionary().createNewBNode(\"DECIMAL_\${${inputNames[0]}}\")")
                                onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                            },
                        )
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.DOUBLE),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                                target.appendLine("${indention}val $outputName: Int = query.getDictionary().createNewBNode(\"DOUBLE_\${${inputNames[0]}}\")")
                                onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                            },
                        )
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.FLOAT),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                                target.appendLine("${indention}val $outputName: Int = query.getDictionary().createNewBNode(\"FLOAT_\${${inputNames[0]}}\")")
                                onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                            },
                        )
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.INTEGER),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                                target.appendLine("${indention}val $outputName: Int = query.getDictionary().createNewBNode(\"INTEGER_\${${inputNames[0]}}\")")
                                onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                            },
                        )
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.IRI),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                                target.appendLine("${indention}val $outputName: Int = query.getDictionary().createNewBNode(\"IRI_\${${inputNames[0]}}\")")
                                onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                            },
                        )
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                                target.appendLine("${indention}val $outputName: Int = query.getDictionary().createNewBNode(\"STRING_\${${inputNames[0]}}\")")
                                onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                            },
                        )
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_LANG),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                                target.appendLine("${indention}val $outputName: Int = query.getDictionary().createNewBNode(\"STRING_LANG_\${${inputNames[0]}_content}_\${${inputNames[0]}_lang}\")")
                                onResult(indention, ETripleComponentTypeExt.BLANK_NODE)
                            },
                        )
                    )
                    .add(
                        MyOperatorPart(
                            childrenTypes = arrayOf(ETripleComponentTypeExt.STRING_TYPED),
                            generateInstantiated = { indention, inputNames, outputName, _, _, target, _, onResult ->
                                target.appendLine("${indention}val $outputName: Int = query.getDictionary().createNewBNode(\"STRING_TYPED_\${${inputNames[0]}_content}_\${${inputNames[0]}_type}\")")
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
                                imports.add("lupos.shared_inline.ByteArrayWrapperExt")
                                globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
                                target.appendLine("${indention}ByteArrayWrapperExt.copyInto(${inputNames[0]},$outputName)")
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
                                imports.add("lupos.shared_inline.ByteArrayWrapperExt")
                                globalVariables.add("val $outputName: ByteArrayWrapper = ByteArrayWrapper()")
                                target.appendLine("${indention}ByteArrayWrapperExt.copyInto(${inputNames[0]},$outputName)")
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
    }
}
