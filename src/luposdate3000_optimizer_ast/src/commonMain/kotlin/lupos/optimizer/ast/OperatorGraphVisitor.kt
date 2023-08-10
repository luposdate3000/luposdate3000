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
package lupos.optimizer.ast

import lupos.operator.arithmetik.AOPBase
import lupos.operator.arithmetik.generated.AOPAddition
import lupos.operator.arithmetik.generated.AOPAnd
import lupos.operator.arithmetik.generated.AOPBuildInCallABS
import lupos.operator.arithmetik.generated.AOPBuildInCallBNODE1
import lupos.operator.arithmetik.generated.AOPBuildInCallBOUND
import lupos.operator.arithmetik.generated.AOPBuildInCallCEIL
import lupos.operator.arithmetik.generated.AOPBuildInCallCONCAT
import lupos.operator.arithmetik.generated.AOPBuildInCallCONTAINS
import lupos.operator.arithmetik.generated.AOPBuildInCallDATATYPE
import lupos.operator.arithmetik.generated.AOPBuildInCallDAY
import lupos.operator.arithmetik.generated.AOPBuildInCallFLOOR
import lupos.operator.arithmetik.generated.AOPBuildInCallHOURS
import lupos.operator.arithmetik.generated.AOPBuildInCallIRI
import lupos.operator.arithmetik.generated.AOPBuildInCallIsLITERAL
import lupos.operator.arithmetik.generated.AOPBuildInCallIsNUMERIC
import lupos.operator.arithmetik.generated.AOPBuildInCallLANG
import lupos.operator.arithmetik.generated.AOPBuildInCallLANGMATCHES
import lupos.operator.arithmetik.generated.AOPBuildInCallLCASE
import lupos.operator.arithmetik.generated.AOPBuildInCallMD5
import lupos.operator.arithmetik.generated.AOPBuildInCallMINUTES
import lupos.operator.arithmetik.generated.AOPBuildInCallMONTH
import lupos.operator.arithmetik.generated.AOPBuildInCallROUND
import lupos.operator.arithmetik.generated.AOPBuildInCallSECONDS
import lupos.operator.arithmetik.generated.AOPBuildInCallSHA1
import lupos.operator.arithmetik.generated.AOPBuildInCallSHA256
import lupos.operator.arithmetik.generated.AOPBuildInCallSTR
import lupos.operator.arithmetik.generated.AOPBuildInCallSTRAFTER
import lupos.operator.arithmetik.generated.AOPBuildInCallSTRBEFORE
import lupos.operator.arithmetik.generated.AOPBuildInCallSTRDT
import lupos.operator.arithmetik.generated.AOPBuildInCallSTRENDS
import lupos.operator.arithmetik.generated.AOPBuildInCallSTRLANG
import lupos.operator.arithmetik.generated.AOPBuildInCallSTRLEN
import lupos.operator.arithmetik.generated.AOPBuildInCallSTRSTARTS
import lupos.operator.arithmetik.generated.AOPBuildInCallTIMEZONE
import lupos.operator.arithmetik.generated.AOPBuildInCallTZ
import lupos.operator.arithmetik.generated.AOPBuildInCallUCASE
import lupos.operator.arithmetik.generated.AOPBuildInCallURI
import lupos.operator.arithmetik.generated.AOPBuildInCallYEAR
import lupos.operator.arithmetik.generated.AOPDivision
import lupos.operator.arithmetik.generated.AOPFunctionCallDouble
import lupos.operator.arithmetik.generated.AOPFunctionCallFloat
import lupos.operator.arithmetik.generated.AOPFunctionCallString
import lupos.operator.arithmetik.generated.AOPMultiplication
import lupos.operator.arithmetik.generated.AOPNot
import lupos.operator.arithmetik.generated.AOPOr
import lupos.operator.arithmetik.generated.AOPSubtraction
import lupos.operator.arithmetik.multiinput.AOPBuildInCallCOALESCE
import lupos.operator.arithmetik.multiinput.AOPBuildInCallIF
import lupos.operator.arithmetik.multiinput.AOPEQ
import lupos.operator.arithmetik.multiinput.AOPGEQ
import lupos.operator.arithmetik.multiinput.AOPGT
import lupos.operator.arithmetik.multiinput.AOPIn
import lupos.operator.arithmetik.multiinput.AOPLEQ
import lupos.operator.arithmetik.multiinput.AOPLT
import lupos.operator.arithmetik.multiinput.AOPNEQ
import lupos.operator.arithmetik.multiinput.AOPNotIn
import lupos.operator.arithmetik.multiinput.AOPSet
import lupos.operator.arithmetik.noinput.AOPBuildInCallBNODE0
import lupos.operator.arithmetik.noinput.AOPBuildInCallSTRUUID
import lupos.operator.arithmetik.noinput.AOPBuildInCallUUID
import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPValue
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.arithmetik.singleinput.AOPAggregationAVG
import lupos.operator.arithmetik.singleinput.AOPAggregationCOUNT
import lupos.operator.arithmetik.singleinput.AOPAggregationMAX
import lupos.operator.arithmetik.singleinput.AOPAggregationMIN
import lupos.operator.arithmetik.singleinput.AOPAggregationSAMPLE
import lupos.operator.arithmetik.singleinput.AOPAggregationSUM
import lupos.operator.arithmetik.singleinput.AOPBuildInCallExists
import lupos.operator.arithmetik.singleinput.AOPBuildInCallNotExists
import lupos.operator.base.OPBase
import lupos.operator.base.OPBaseCompound
import lupos.operator.base.Query
import lupos.operator.base.noinput.OPEmptyRow
import lupos.operator.logical.multiinput.LOPJoin
import lupos.operator.logical.multiinput.LOPMinus
import lupos.operator.logical.multiinput.LOPUnion
import lupos.operator.logical.noinput.LOPGraphOperation
import lupos.operator.logical.noinput.LOPModifyData
import lupos.operator.logical.noinput.LOPTriple
import lupos.operator.logical.noinput.LOPValues
import lupos.operator.logical.singleinput.LOPBind
import lupos.operator.logical.singleinput.LOPFilter
import lupos.operator.logical.singleinput.LOPGroup
import lupos.operator.logical.singleinput.LOPMakeBooleanResult
import lupos.operator.logical.singleinput.LOPModify
import lupos.operator.logical.singleinput.LOPProjection
import lupos.operator.logical.singleinput.LOPSort
import lupos.operator.logical.singleinput.modifiers.LOPDistinct
import lupos.operator.logical.singleinput.modifiers.LOPLimit
import lupos.operator.logical.singleinput.modifiers.LOPOffset
import lupos.operator.logical.singleinput.modifiers.LOPReduced
import lupos.parser.sparql.ASTAdd
import lupos.parser.sparql.ASTAdditiveExpression
import lupos.parser.sparql.ASTAdditiveExpressionDIVIDE
import lupos.parser.sparql.ASTAdditiveExpressionMINUS
import lupos.parser.sparql.ASTAdditiveExpressionMULTIPLY
import lupos.parser.sparql.ASTAdditiveExpressionPLUS
import lupos.parser.sparql.ASTAggregate
import lupos.parser.sparql.ASTAggregateAvg
import lupos.parser.sparql.ASTAggregateCount
import lupos.parser.sparql.ASTAggregateCountAll
import lupos.parser.sparql.ASTAggregateGroupConcat
import lupos.parser.sparql.ASTAggregateMax
import lupos.parser.sparql.ASTAggregateMin
import lupos.parser.sparql.ASTAggregateSample
import lupos.parser.sparql.ASTAggregateSum
import lupos.parser.sparql.ASTArgList
import lupos.parser.sparql.ASTAskQuery
import lupos.parser.sparql.ASTBaseDecl
import lupos.parser.sparql.ASTBind
import lupos.parser.sparql.ASTBlankNode
import lupos.parser.sparql.ASTBlankNodeANON
import lupos.parser.sparql.ASTBlankNodeLabel
import lupos.parser.sparql.ASTBlankNodePropertyList
import lupos.parser.sparql.ASTBlankNodePropertyListPath
import lupos.parser.sparql.ASTBooleanLiteral
import lupos.parser.sparql.ASTBooleanLiteralFalse
import lupos.parser.sparql.ASTBooleanLiteralTrue
import lupos.parser.sparql.ASTBrackettedExpression
import lupos.parser.sparql.ASTBuildInCallLangMatches
import lupos.parser.sparql.ASTBuiltInCall
import lupos.parser.sparql.ASTBuiltInCallAbs
import lupos.parser.sparql.ASTBuiltInCallBNode
import lupos.parser.sparql.ASTBuiltInCallBound
import lupos.parser.sparql.ASTBuiltInCallCeil
import lupos.parser.sparql.ASTBuiltInCallCoalesce
import lupos.parser.sparql.ASTBuiltInCallConcat
import lupos.parser.sparql.ASTBuiltInCallContains
import lupos.parser.sparql.ASTBuiltInCallDataType
import lupos.parser.sparql.ASTBuiltInCallDay
import lupos.parser.sparql.ASTBuiltInCallEncodeForUri
import lupos.parser.sparql.ASTBuiltInCallFloor
import lupos.parser.sparql.ASTBuiltInCallHours
import lupos.parser.sparql.ASTBuiltInCallIf
import lupos.parser.sparql.ASTBuiltInCallIri
import lupos.parser.sparql.ASTBuiltInCallIsBlanc
import lupos.parser.sparql.ASTBuiltInCallIsIri
import lupos.parser.sparql.ASTBuiltInCallIsLiteral
import lupos.parser.sparql.ASTBuiltInCallIsNumeric
import lupos.parser.sparql.ASTBuiltInCallIsUri
import lupos.parser.sparql.ASTBuiltInCallLCase
import lupos.parser.sparql.ASTBuiltInCallLang
import lupos.parser.sparql.ASTBuiltInCallMD5
import lupos.parser.sparql.ASTBuiltInCallMinutes
import lupos.parser.sparql.ASTBuiltInCallMonth
import lupos.parser.sparql.ASTBuiltInCallNow
import lupos.parser.sparql.ASTBuiltInCallRand
import lupos.parser.sparql.ASTBuiltInCallRound
import lupos.parser.sparql.ASTBuiltInCallSHA1
import lupos.parser.sparql.ASTBuiltInCallSHA256
import lupos.parser.sparql.ASTBuiltInCallSHA384
import lupos.parser.sparql.ASTBuiltInCallSHA512
import lupos.parser.sparql.ASTBuiltInCallSTRUUID
import lupos.parser.sparql.ASTBuiltInCallSameTerm
import lupos.parser.sparql.ASTBuiltInCallSeconds
import lupos.parser.sparql.ASTBuiltInCallStr
import lupos.parser.sparql.ASTBuiltInCallStrAfter
import lupos.parser.sparql.ASTBuiltInCallStrBefore
import lupos.parser.sparql.ASTBuiltInCallStrDt
import lupos.parser.sparql.ASTBuiltInCallStrEnds
import lupos.parser.sparql.ASTBuiltInCallStrLang
import lupos.parser.sparql.ASTBuiltInCallStrLen
import lupos.parser.sparql.ASTBuiltInCallStrStarts
import lupos.parser.sparql.ASTBuiltInCallTimezone
import lupos.parser.sparql.ASTBuiltInCallTz
import lupos.parser.sparql.ASTBuiltInCallUCase
import lupos.parser.sparql.ASTBuiltInCallUUID
import lupos.parser.sparql.ASTBuiltInCallUri
import lupos.parser.sparql.ASTBuiltInCallYear
import lupos.parser.sparql.ASTClassOfConstructTemplateAndListOfDatasetClauseAndWhereClauseAndSolutionModifier
import lupos.parser.sparql.ASTClassOfDISTINCTAndExpressionAndListOfExpression
import lupos.parser.sparql.ASTClassOfDeleteClauseAndInsertClauseOptional
import lupos.parser.sparql.ASTClassOfExpressionAndListOfExpression
import lupos.parser.sparql.ASTClassOfExpressionAndVar
import lupos.parser.sparql.ASTClassOfExpressionAndVarOptional
import lupos.parser.sparql.ASTClassOfGRAPHAndiri
import lupos.parser.sparql.ASTClassOfGraphPatternNotTriplesAndpointAndTriplesBlockOptional
import lupos.parser.sparql.ASTClassOfInterfaceOfNumericLiteralPositiveOrNumericLiteralNegativeAndListOfInterfaceOfAdditiveExpressionMULTIPLYOrAdditiveExpressionDIVIDE
import lupos.parser.sparql.ASTClassOfInterfaceOfSelectQueryOrConstructQueryOrDescribeQueryOrAskQueryAndValuesClauseOptional
import lupos.parser.sparql.ASTClassOfInterfaceOfVerbPathOrVerbSimpleAndObjectList
import lupos.parser.sparql.ASTClassOfLimitClauseAndOffsetClauseOptional
import lupos.parser.sparql.ASTClassOfListOfDatasetClauseAndGroupGraphPatternAndSolutionModifier
import lupos.parser.sparql.ASTClassOfOffsetClauseAndLimitClauseOptional
import lupos.parser.sparql.ASTClassOfPrologueAndUpdate
import lupos.parser.sparql.ASTClassOfPrologueAndUpdateOptional
import lupos.parser.sparql.ASTClassOfQuadsNotTriplesAndpointAndTriplesTemplateOptional
import lupos.parser.sparql.ASTClassOfTriplesNodeAndPropertyListOptional
import lupos.parser.sparql.ASTClassOfTriplesNodePathAndPropertyListPathOptional
import lupos.parser.sparql.ASTClassOfUpdate1AndClassOfPrologueAndUpdateOptional
import lupos.parser.sparql.ASTClassOfUpdate1AndClassOfPrologueAndUpdateOptionalOptional
import lupos.parser.sparql.ASTClassOfVarOrTermAndPropertyListNotEmpty
import lupos.parser.sparql.ASTClassOfVarOrTermAndPropertyListPathNotEmpty
import lupos.parser.sparql.ASTClassOfVerbAndObjectList
import lupos.parser.sparql.ASTClassOfVerbAndObjectListOptional
import lupos.parser.sparql.ASTClear
import lupos.parser.sparql.ASTCollection
import lupos.parser.sparql.ASTCollectionPath
import lupos.parser.sparql.ASTConditionalAndExpression
import lupos.parser.sparql.ASTConditionalOrExpression
import lupos.parser.sparql.ASTConstraint
import lupos.parser.sparql.ASTConstructQuery
import lupos.parser.sparql.ASTConstructTemplate
import lupos.parser.sparql.ASTConstructTriples
import lupos.parser.sparql.ASTConstructTriplesOptional
import lupos.parser.sparql.ASTConstructTriplesOptionalOptional
import lupos.parser.sparql.ASTCopy
import lupos.parser.sparql.ASTCreate
import lupos.parser.sparql.ASTDataBlock
import lupos.parser.sparql.ASTDataBlockValue
import lupos.parser.sparql.ASTDeleteClause
import lupos.parser.sparql.ASTDeleteData
import lupos.parser.sparql.ASTDeleteWhere
import lupos.parser.sparql.ASTDescribeQuery
import lupos.parser.sparql.ASTDrop
import lupos.parser.sparql.ASTEnumOfASCAndDESC
import lupos.parser.sparql.ASTEnumOfDISTINCTAndREDUCED
import lupos.parser.sparql.ASTEnumOfoptionalAndanyAndatLeastOne
import lupos.parser.sparql.ASTExistsFunc
import lupos.parser.sparql.ASTExpression
import lupos.parser.sparql.ASTExpressionList
import lupos.parser.sparql.ASTFilter
import lupos.parser.sparql.ASTFunctionCall
import lupos.parser.sparql.ASTGraphGraphPattern
import lupos.parser.sparql.ASTGraphNode
import lupos.parser.sparql.ASTGraphNodePath
import lupos.parser.sparql.ASTGraphOrDefault
import lupos.parser.sparql.ASTGraphPatternNotTriples
import lupos.parser.sparql.ASTGraphRef
import lupos.parser.sparql.ASTGraphRefAll
import lupos.parser.sparql.ASTGraphRefAll2
import lupos.parser.sparql.ASTGraphRefDefault
import lupos.parser.sparql.ASTGraphRefNamed
import lupos.parser.sparql.ASTGraphTerm
import lupos.parser.sparql.ASTGroupCondition
import lupos.parser.sparql.ASTGroupGraphPattern
import lupos.parser.sparql.ASTGroupGraphPatternSub
import lupos.parser.sparql.ASTGroupOrUnionGraphPattern
import lupos.parser.sparql.ASTHavingClause
import lupos.parser.sparql.ASTHavingCondition
import lupos.parser.sparql.ASTInlineDataFull
import lupos.parser.sparql.ASTInlineDataOneVar
import lupos.parser.sparql.ASTInsertClause
import lupos.parser.sparql.ASTInsertClauseOptional
import lupos.parser.sparql.ASTInsertData
import lupos.parser.sparql.ASTInterfaceOfAggregateCountAllOrExpression
import lupos.parser.sparql.ASTInterfaceOfBaseDeclOrPrefixDecl
import lupos.parser.sparql.ASTInterfaceOfConstraintOrVar
import lupos.parser.sparql.ASTInterfaceOfListOfDataBlockValueOrNILParam
import lupos.parser.sparql.ASTInterfaceOfSubSelectOrGroupGraphPatternSub
import lupos.parser.sparql.ASTInterfaceOfVerbPathOrVerbSimple
import lupos.parser.sparql.ASTLimitClause
import lupos.parser.sparql.ASTLimitClauseOptional
import lupos.parser.sparql.ASTLimitOffsetClauses
import lupos.parser.sparql.ASTListOfClassOfInterfaceOfVerbPathOrVerbSimpleAndObjectListOptional
import lupos.parser.sparql.ASTListOfClassOfQuadsNotTriplesAndpointAndTriplesTemplateOptional
import lupos.parser.sparql.ASTListOfClassOfVerbAndObjectListOptional
import lupos.parser.sparql.ASTListOfConditionalAndExpression
import lupos.parser.sparql.ASTListOfDataBlockValue
import lupos.parser.sparql.ASTListOfDatasetClause
import lupos.parser.sparql.ASTListOfGroupCondition
import lupos.parser.sparql.ASTListOfHavingCondition
import lupos.parser.sparql.ASTListOfInterfaceOfListOfDataBlockValueOrNILParam
import lupos.parser.sparql.ASTListOfInterfaceOfVarOrClassOfExpressionAndVar
import lupos.parser.sparql.ASTListOfUsingClause
import lupos.parser.sparql.ASTListOfValueLogical
import lupos.parser.sparql.ASTListOfVar
import lupos.parser.sparql.ASTLoad
import lupos.parser.sparql.ASTMinusGraphPattern
import lupos.parser.sparql.ASTModify
import lupos.parser.sparql.ASTMove
import lupos.parser.sparql.ASTMultiplicativeExpression
import lupos.parser.sparql.ASTMultiplicativeExpressionDIVIDE
import lupos.parser.sparql.ASTMultiplicativeExpressionMULTIPLY
import lupos.parser.sparql.ASTNILParam
import lupos.parser.sparql.ASTNotExistsFunc
import lupos.parser.sparql.ASTNumericExpression
import lupos.parser.sparql.ASTNumericLiteral
import lupos.parser.sparql.ASTNumericLiteralNegative
import lupos.parser.sparql.ASTNumericLiteralNegativeDecimal
import lupos.parser.sparql.ASTNumericLiteralNegativeDouble
import lupos.parser.sparql.ASTNumericLiteralNegativeInteger
import lupos.parser.sparql.ASTNumericLiteralPositive
import lupos.parser.sparql.ASTNumericLiteralPositiveDecimal
import lupos.parser.sparql.ASTNumericLiteralPositiveDouble
import lupos.parser.sparql.ASTNumericLiteralPositiveInteger
import lupos.parser.sparql.ASTNumericLiteralUnsigned
import lupos.parser.sparql.ASTNumericLiteralUnsignedDecimal
import lupos.parser.sparql.ASTNumericLiteralUnsignedDouble
import lupos.parser.sparql.ASTNumericLiteralUnsignedInteger
import lupos.parser.sparql.ASTObject
import lupos.parser.sparql.ASTObjectList
import lupos.parser.sparql.ASTObjectListPath
import lupos.parser.sparql.ASTObjectPath
import lupos.parser.sparql.ASTOffsetClause
import lupos.parser.sparql.ASTOffsetClauseOptional
import lupos.parser.sparql.ASTOptionalGraphPattern
import lupos.parser.sparql.ASTOrderClause
import lupos.parser.sparql.ASTPath
import lupos.parser.sparql.ASTPathAlternative
import lupos.parser.sparql.ASTPathElt
import lupos.parser.sparql.ASTPathEltOrInverse
import lupos.parser.sparql.ASTPathNegatedPropertySet
import lupos.parser.sparql.ASTPathPrimary
import lupos.parser.sparql.ASTPathSequence
import lupos.parser.sparql.ASTPrefixDecl
import lupos.parser.sparql.ASTPrefixedNameLN
import lupos.parser.sparql.ASTPrefixedNameNS
import lupos.parser.sparql.ASTPrimaryExpression
import lupos.parser.sparql.ASTPrologue
import lupos.parser.sparql.ASTPropertyList
import lupos.parser.sparql.ASTPropertyListNotEmpty
import lupos.parser.sparql.ASTPropertyListOptional
import lupos.parser.sparql.ASTPropertyListPath
import lupos.parser.sparql.ASTPropertyListPathNotEmpty
import lupos.parser.sparql.ASTPropertyListPathOptional
import lupos.parser.sparql.ASTQuadData
import lupos.parser.sparql.ASTQuadPattern
import lupos.parser.sparql.ASTQuads
import lupos.parser.sparql.ASTQuadsNotTriples
import lupos.parser.sparql.ASTRDFLiteral
import lupos.parser.sparql.ASTRDFLiteralLang
import lupos.parser.sparql.ASTRDFType
import lupos.parser.sparql.ASTRegexExpression
import lupos.parser.sparql.ASTRelationalExpression
import lupos.parser.sparql.ASTRelationalExpressionEQ
import lupos.parser.sparql.ASTRelationalExpressionGEQ
import lupos.parser.sparql.ASTRelationalExpressionGT
import lupos.parser.sparql.ASTRelationalExpressionIN
import lupos.parser.sparql.ASTRelationalExpressionLEQ
import lupos.parser.sparql.ASTRelationalExpressionLT
import lupos.parser.sparql.ASTRelationalExpressionNEQ
import lupos.parser.sparql.ASTRelationalExpressionNOTIN
import lupos.parser.sparql.ASTSelectClause
import lupos.parser.sparql.ASTSelectClauseAll
import lupos.parser.sparql.ASTSelectQuery
import lupos.parser.sparql.ASTServiceGraphPattern
import lupos.parser.sparql.ASTSolutionModifier
import lupos.parser.sparql.ASTSparqlDoc
import lupos.parser.sparql.ASTStrReplaceExpression
import lupos.parser.sparql.ASTString
import lupos.parser.sparql.ASTString1
import lupos.parser.sparql.ASTString1long
import lupos.parser.sparql.ASTString2
import lupos.parser.sparql.ASTString2long
import lupos.parser.sparql.ASTSubSelect
import lupos.parser.sparql.ASTSubstringExpression
import lupos.parser.sparql.ASTTriplesBlock
import lupos.parser.sparql.ASTTriplesBlockOptional
import lupos.parser.sparql.ASTTriplesBlockOptionalOptional
import lupos.parser.sparql.ASTTriplesNode
import lupos.parser.sparql.ASTTriplesNodePath
import lupos.parser.sparql.ASTTriplesSameSubject
import lupos.parser.sparql.ASTTriplesSameSubjectPath
import lupos.parser.sparql.ASTTriplesTemplate
import lupos.parser.sparql.ASTTriplesTemplateOptional
import lupos.parser.sparql.ASTTriplesTemplateOptionalOptional
import lupos.parser.sparql.ASTUNDEF
import lupos.parser.sparql.ASTUnaryExpression
import lupos.parser.sparql.ASTUnaryExpressionMINUS
import lupos.parser.sparql.ASTUnaryExpressionNOT
import lupos.parser.sparql.ASTUnaryExpressionPLUS
import lupos.parser.sparql.ASTUpdate
import lupos.parser.sparql.ASTUpdate1
import lupos.parser.sparql.ASTValueLogical
import lupos.parser.sparql.ASTValuesClause
import lupos.parser.sparql.ASTVar
import lupos.parser.sparql.ASTVar1
import lupos.parser.sparql.ASTVar2
import lupos.parser.sparql.ASTVarOrTerm
import lupos.parser.sparql.ASTVerb
import lupos.parser.sparql.ASTVerbPath
import lupos.parser.sparql.ASTVerbSimple
import lupos.parser.sparql.ASTWhereClause
import lupos.parser.sparql.ASTiri
import lupos.parser.sparql.ASTiriOrFunction
import lupos.parser.sparql.ASTiriRef
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.EGraphOperationTypeExt
import lupos.shared.EGraphRefTypeExt
import lupos.shared.EModifyTypeExt
import lupos.shared.TripleStoreManager
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.IOPBase

public class OperatorGraphVisitor(public val query: Query) {
    private val queryExecutionStartTime: ByteArrayWrapper = ByteArrayWrapper()
    private var counter = 0
    private val prefixMap = mutableMapOf("" to "")

    init {
        DictionaryHelper.dateTimeToByteArray(queryExecutionStartTime)
    }

    private fun initializeEnum(value: Int?): Int = value ?: 0
    private fun decodeIri(ns: String) = prefixMap[ns]!!
    private fun decodeIri(ns: String, p: String): String {
        val tmp = prefixMap[ns] ?: TODO("prefix '$ns' not registered - instead there are ${prefixMap.toList()}")
        return tmp + p
    }

    private fun prefix(k: String, v: String) {
        prefixMap[k] = v.drop(1).dropLast(1)
    }

    private fun createBind(name: AOPVariable, expression: AOPBase, child: IOPBase): LOPBind {
        if (child.getProvidedVariableNames().contains(name.name)) {
            TODO("bound variable already exist")
        }
        return LOPBind(query, name, expression, child)
    }

    private fun visit000(node: ASTListOfInterfaceOfVarOrClassOfExpressionAndVar): MutableList<Pair<AOPBase?, String>> {
        val names = mutableListOf<Pair<AOPBase?, String>>()
        fun extractName(b: AOPBase?, n: ASTVar) {
            val unused = when (n) {
                is ASTVar1 -> names.add(b to n.VAR1!!.drop(1))
                is ASTVar2 -> names.add(b to n.VAR2!!.drop(1))
            }
        }
        for (v in node.value) {
            val unused = when (v) {
                is ASTVar -> extractName(null, v)
                is ASTClassOfExpressionAndVar -> extractName(visit033("", false, false,v.variable0!!), v.variable1!!)
            }
        }
        return names
    }

    private fun visit172(node: ASTSelectClause): Pair<Int, List<Pair<AOPBase?, String>>?> = initializeEnum(node.variable0) to when (val variables = node.variable1!!) {
        is ASTListOfInterfaceOfVarOrClassOfExpressionAndVar -> visit000(variables)
        is ASTSelectClauseAll -> null
    }

    private fun visit171(v0: ASTPrologue, v1: ASTClassOfInterfaceOfSelectQueryOrConstructQueryOrDescribeQueryOrAskQueryAndValuesClauseOptional): OPBaseCompound {
        visit029(v0)
        val variableOrdering = mutableListOf<List<String>>()
        val classOfInterfaceOfSelectQueryOrConstructQueryOrDescribeQueryOrAskQueryAndValuesClauseOptional = v1.variable0!!
        val valuesClauseOptional = v1.variable1
        val valuesClause = valuesClauseOptional!!.variable0
        val child = when (classOfInterfaceOfSelectQueryOrConstructQueryOrDescribeQueryOrAskQueryAndValuesClauseOptional) {
            is ASTSelectQuery -> {
                val tmp = visit172(classOfInterfaceOfSelectQueryOrConstructQueryOrDescribeQueryOrAskQueryAndValuesClauseOptional.variable0!!).second
                if (tmp != null) {
                    variableOrdering.add(tmp.map { it.second })
                }
                visit167(classOfInterfaceOfSelectQueryOrConstructQueryOrDescribeQueryOrAskQueryAndValuesClauseOptional, valuesClause)
            }
            is ASTConstructQuery -> visit165(classOfInterfaceOfSelectQueryOrConstructQueryOrDescribeQueryOrAskQueryAndValuesClauseOptional, valuesClause)
            is ASTDescribeQuery -> visit008(classOfInterfaceOfSelectQueryOrConstructQueryOrDescribeQueryOrAskQueryAndValuesClauseOptional, valuesClause)
            is ASTAskQuery -> visit164(classOfInterfaceOfSelectQueryOrConstructQueryOrDescribeQueryOrAskQueryAndValuesClauseOptional, valuesClause)
        }
        if (variableOrdering.isEmpty()) {
            variableOrdering.add(child.getProvidedVariableNames().filter { !it.contains("#") })
        }
        return OPBaseCompound(query, arrayOf(child), variableOrdering)
    }

    private fun visit170(v0: ASTPrologue, v1: ASTClassOfUpdate1AndClassOfPrologueAndUpdateOptional): OPBaseCompound {
        val prolog = visit029(v0).toMutableList()
        val childs = mutableListOf<IOPBase>()
        childs.add(visit124(v1.variable0!!))
        var v2: ASTClassOfPrologueAndUpdateOptional = v1.variable1!!
        while (v2.variable0 != null) {
            val v3: ASTClassOfPrologueAndUpdate = v2.variable0!!
            prolog.addAll(visit029(v3.variable0!!))
            val v4: ASTUpdate = v3.variable1!!
            val v5: ASTClassOfUpdate1AndClassOfPrologueAndUpdateOptional = v4.variable0!!
            childs.add(visit124(v5.variable0!!))
            v2 = v5.variable1!!
        }
        return OPBaseCompound(query, childs.toTypedArray(), listOf())
    }

    public fun visit(node: ASTSparqlDoc): OPBaseCompound {
//         println("inputAST " + node)
 lupos.parser.sparql.SparqlParser(lupos.shared.inline.MyStringStream("")).printASTSparqlDoc(node)
// println()
        val res = when (val v1 = node.variable1!!) {
            is ASTClassOfInterfaceOfSelectQueryOrConstructQueryOrDescribeQueryOrAskQueryAndValuesClauseOptional -> visit171(node.variable0!!, v1)
            is ASTClassOfUpdate1AndClassOfPrologueAndUpdateOptionalOptional -> visit076(node.variable0!!, v1)
        }
        //      println(res)
        return res
    }

    private fun visit168(graph: String, graphVar: Boolean, graphOverride:Boolean,node: ASTGroupCondition): Pair<AOPBase?, AOPVariable> = when (node) {
        is ASTFunctionCall -> visit160(graph, graphVar,graphOverride,node) to AOPVariable(query, "_ASTGroupCondition#${counter++}")
        is ASTVar -> null to visit153(node)
        is ASTBuiltInCall -> visit158(graph, graphVar,graphOverride,node) to AOPVariable(query, "_ASTGroupCondition#${counter++}")
        is ASTClassOfExpressionAndVarOptional -> visit070(graph, graphVar,graphOverride,node)
    }

    private fun visit167(node: ASTSelectQuery, valuesClause: ASTValuesClause?): IOPBase {
        val nodeSelectClause = node.variable0
        val nodeListOfDatasetClause = node.variable1!!
        val nodeWhereClause = node.variable2
        val nodeSolutionModifier = node.variable3
        if (nodeListOfDatasetClause.value.size > 0) {
            TODO("datasets not supported")
        }
        val whereClause = visit034("", false,false, nodeWhereClause!!)
        var res = whereClause
        if (valuesClause != null) {
            res = LOPJoin(query, res, visit064(valuesClause), false)
        }
        val (duplicateModifier, selectClause) = visit172(nodeSelectClause!!)
        val solutionModifier = nodeSolutionModifier!!
        val groupClause = solutionModifier.variable0!!.variable0
        val havingClause = solutionModifier.variable1!!.variable0
        if (groupClause != null) {
            val bindings = selectClause.orEmpty().filter { it.first != null }.map { it.second to it.first!! }
            val groupingBy = visit069("", false,false, groupClause.variable0!!)
            for ((first, second) in groupingBy.filter { it.first != null }) {
                res = createBind(second, first!!, res)
            }
            val bindingsForHaving: List<Pair<String, AOPBase>> = havingClause?.let { visit071("", false,false, it).map { it2 -> "_ASTSelectQuery#${counter++}" to it2 } }.orEmpty()
            res = LOPGroup(query, groupingBy.map { it.second }, bindings + bindingsForHaving, res)
            for ((first) in bindingsForHaving) {
                res = LOPFilter(query, AOPVariable(query, first), res)
            }
        } else if (havingClause != null) {
            val bindings = selectClause.orEmpty().filter { it.first != null }.map { it.second to it.first!! }
            val bindingsForHaving: List<Pair<String, AOPBase>> = visit071("", false,false, havingClause).map { "_ASTSelectQuery#${counter++}" to it }
            res = LOPGroup(query, listOf(), bindings + bindingsForHaving, res)
            for ((first) in bindingsForHaving) {
                res = LOPFilter(query, AOPVariable(query, first), res)
            }
        } else if (selectClause != null) {
            if (selectClause.mapNotNull { it.first }.map { it.containsAggregate() }.fold(false) { s, t -> s || t }) {
                val bindings = selectClause.orEmpty().filter { it.first != null }.map { it.second to it.first!! }
                res = LOPGroup(query, listOf(), bindings, res)
            } else {
                for ((first, second) in selectClause.filter { it.first != null }) {
                    res = createBind(AOPVariable(query, second), first!!, res)
                }
            }
        }
        val orderClause = solutionModifier.variable2!!.variable0
        if (orderClause != null) {
            res = visit163("", false,false, orderClause, res)
        }
        val limitOffsetClause = solutionModifier.variable3!!.variable0
        if (limitOffsetClause != null) {
            res = visit152(limitOffsetClause, res)
        }
        if (selectClause != null) {
            res = LOPProjection(query, selectClause.map { AOPVariable(query, it.second) }.toMutableList(), res)
        }
        res = when (duplicateModifier) {
            ASTEnumOfDISTINCTAndREDUCED._UNDEFINED -> res
            ASTEnumOfDISTINCTAndREDUCED.ASTEnumOfDISTINCTAndREDUCEDDISTINCT -> LOPDistinct(query, res)
            ASTEnumOfDISTINCTAndREDUCED.ASTEnumOfDISTINCTAndREDUCEDREDUCED -> LOPReduced(query, res)
            else -> TODO("invalid value")
        }
        return res
    }

    private fun visit166(graph: String, graphVar: Boolean,graphOverride:Boolean, node: ASTSubSelect): IOPBase {
        val nodeSelectClause = node.variable0
        val nodeWhereClause = node.variable1
        val nodeSolutionModifier = node.variable2
        val valuesClause = node.variable3!!.variable0
        val whereClause = visit034(graph, graphVar,graphOverride, nodeWhereClause!!)
        var res = whereClause
        if (valuesClause != null) {
            res = LOPJoin(query, res, visit064(valuesClause), false)
        }
        val (duplicateModifier, selectClause) = visit172(nodeSelectClause!!)
        val solutionModifier = nodeSolutionModifier!!
        val groupClause = solutionModifier.variable0!!.variable0
        val havingClause = solutionModifier.variable1!!.variable0
        if (groupClause != null) {
            val bindings = selectClause.orEmpty().filter { it.first != null }.map { it.second to it.first!! }
            val groupingBy = visit069(graph, graphVar,graphOverride,groupClause.variable0!!)
            for ((first, second) in groupingBy.filter { it.first != null }) {
                res = createBind(second, first!!, res)
            }
            val bindingsForHaving: List<Pair<String, AOPBase>> = havingClause?.let { visit071(graph, graphVar,graphOverride, it).map { it2 -> "_ASTSelectQuery#${counter++}" to it2 } }.orEmpty()
            res = LOPGroup(query, groupingBy.map { it.second }, bindings + bindingsForHaving, res)
            for ((first) in bindingsForHaving) {
                res = LOPFilter(query, AOPVariable(query, first), res)
            }
        } else if (havingClause != null) {
            val bindings = selectClause.orEmpty().filter { it.first != null }.map { it.second to it.first!! }
            val bindingsForHaving: List<Pair<String, AOPBase>> = visit071(graph, graphVar,graphOverride, havingClause).map { "_ASTSelectQuery#${counter++}" to it }
            res = LOPGroup(query, listOf(), bindings + bindingsForHaving, res)
            for ((first) in bindingsForHaving) {
                res = LOPFilter(query, AOPVariable(query, first), res)
            }
        } else if (selectClause != null) {
            if (selectClause.mapNotNull { it.first }.map { it.containsAggregate() }.fold(false) { s, t -> s || t }) {
                val bindings = selectClause.orEmpty().filter { it.first != null }.map { it.second to it.first!! }
                res = LOPGroup(query, listOf(), bindings, res)
            } else {
                for ((first, second) in selectClause.filter { it.first != null }) {
                    res = createBind(AOPVariable(query, second), first!!, res)
                }
            }
        }
        val orderClause = solutionModifier.variable2!!.variable0
        if (orderClause != null) {
            res = visit163(graph, graphVar,graphOverride, orderClause, res)
        }
        val limitOffsetClause = solutionModifier.variable3!!.variable0
        if (limitOffsetClause != null) {
            res = visit152(limitOffsetClause, res)
        }
        if (selectClause != null) {
            res = LOPProjection(query, selectClause.map { AOPVariable(query, it.second) }.toMutableList(), res)
        }
        res = when (duplicateModifier) {
            ASTEnumOfDISTINCTAndREDUCED._UNDEFINED -> res
            ASTEnumOfDISTINCTAndREDUCED.ASTEnumOfDISTINCTAndREDUCEDDISTINCT -> LOPDistinct(query, res)
            ASTEnumOfDISTINCTAndREDUCED.ASTEnumOfDISTINCTAndREDUCEDREDUCED -> LOPReduced(query, res)
            else -> TODO("invalid value")
        }
        return res
    }

    private fun visit165(node: ASTConstructQuery, valuesClause: ASTValuesClause?): IOPBase {
        val constructTriples = mutableListOf<LOPTriple>()
        var datasetClauses: ASTListOfDatasetClause? = null
        var solutionModifier: ASTSolutionModifier? = null
        var dataSource: IOPBase = when (val tmp = node.variable0!!) {
            is ASTClassOfConstructTemplateAndListOfDatasetClauseAndWhereClauseAndSolutionModifier -> {
                constructTriples.addAll(visit001(tmp.variable0!!))
                datasetClauses = tmp.variable1!!
                solutionModifier = tmp.variable3!!
                visit026(true, "", false,false, tmp.variable2!!.variable1!!)
            }
            is ASTClassOfListOfDatasetClauseAndGroupGraphPatternAndSolutionModifier -> {
                datasetClauses = tmp.variable0!!
                solutionModifier = tmp.variable2!!
                val tt = visit026(true, "", false,false, tmp.variable1!!)
                fun extractTriplesFromSimpleJoins(node2: IOPBase): List<LOPTriple> {
                    return when (node2) {
                        is LOPTriple -> listOf(node2)
                        is LOPJoin -> {
                            if (node2.optional) {
                                TODO("optional is not allowed in short construct query form")
                            }
                            extractTriplesFromSimpleJoins(node2.children[0]) + extractTriplesFromSimpleJoins(node2.children[1])
                        }
                        else -> TODO("this operator (${node2.getClassname()}) is not allowed in short construct query form")
                    }
                }
                constructTriples.addAll(extractTriplesFromSimpleJoins(tt))
                tt
            }
        }
        val labels = arrayOf("s", "p", "o")
        if (constructTriples.isEmpty()) {
            return LOPProjection(query, labels.map { AOPVariable(query, it) }.toMutableList(), OPEmptyRow(query))
        }
        if (datasetClauses.value.size > 0) {
            TODO("datasets not supported")
        }
        if (valuesClause != null) {
            dataSource = LOPJoin(query, dataSource, visit064(valuesClause), false)
        }
        val groupClause = solutionModifier.variable0!!.variable0
        val havingClause = solutionModifier.variable1!!.variable0
        if (groupClause != null) {
            TODO("group clause not supported for construct")
        } else if (havingClause != null) {
            TODO("having clause not supported for construct")
        }
        val orderClause = solutionModifier.variable2!!.variable0
        if (orderClause != null) {
            dataSource = visit163("", false,false, orderClause, dataSource)
        }
        val limitOffsetClause = solutionModifier.variable3!!.variable0
        if (limitOffsetClause != null) {
            dataSource = visit152(limitOffsetClause, dataSource)
        }
        val returning = LOPDistinct(
            query,
            LOPProjection(
                query,
                labels.map { AOPVariable(query, it) }.toMutableList(),
                constructTriples.map {
                    val constants = mutableListOf<Pair<String, AOPConstant>>()
                    val variables = mutableListOf<Pair<String, AOPVariable>>()
                    for (i in 0 until 3) {
                        if (it.children[i] is AOPConstant) {
                            constants.add(labels[i] to it.children[i] as AOPConstant)
                        } else {
                            variables.add(labels[i] to it.children[i] as AOPVariable)
                        }
                    }
                    val cc = if (constants.size > 0) {
                        LOPValues(query, constants.map { (first) -> AOPVariable(query, first) }, listOf(AOPValue(query, constants.map { it2 -> it2.second })))
                    } else {
                        null
                    }
                    val vv = if (variables.size > 0) {
                        var x: IOPBase = dataSource.cloneOP()
                        x = LOPProjection(query, variables.map { it2 -> it2.second }.toMutableList(), x)
                        x = LOPDistinct(query, x)
                        for ((first, second) in variables) {
                            x = createBind(AOPVariable(query, "construct#$first"), second, x)
                        }
                        x = LOPProjection(query, variables.map { (first) -> AOPVariable(query, "construct#$first") }.toMutableList(), x)
                        for ((first) in variables) {
                            x = createBind(AOPVariable(query, first), AOPVariable(query, "construct#$first"), x)
                        }
                        x
                    } else {
                        null
                    }
                    if (vv == null) {
                        cc!!
                    } else if (cc == null) {
                        vv
                    } else {
                        LOPJoin(query, cc, vv, false)
                    }
                }.reduce { s, t -> LOPUnion(query, s, t) }
            )
        )
        println("yyy $returning")
        return returning
    }

    private fun visit164(node: ASTAskQuery, valuesClause: ASTValuesClause?): LOPMakeBooleanResult {
        val nodeListOfDatasetClause = node.variable0!!
        val nodeWhereClause = node.variable1
        val nodeSolutionModifier = node.variable2
        if (nodeListOfDatasetClause.value.size > 0) {
            TODO("datasets not supported")
        }
        val whereClause = visit034("", false,false, nodeWhereClause!!)
        var res = whereClause
        if (valuesClause != null) {
            res = LOPJoin(query, res, visit064(valuesClause), false)
        }
        val solutionModifier = nodeSolutionModifier!!
        val groupClause = solutionModifier.variable0!!.variable0
        val havingClause = solutionModifier.variable1!!.variable0
        if (groupClause != null) {
            val groupingBy = visit069("", false,false, groupClause.variable0!!)
            for ((first, second) in groupingBy.filter { it.first != null }) {
                res = createBind(second, first!!, res)
            }
            val bindingsForHaving: List<Pair<String, AOPBase>> = havingClause?.let { visit071("", false,false, it).map { it2 -> "_ASTSelectQuery#${counter++}" to it2 } }.orEmpty()
            res = LOPGroup(query, groupingBy.map { it.second }, bindingsForHaving, res)
            for ((first) in bindingsForHaving) {
                res = LOPFilter(query, AOPVariable(query, first), res)
            }
        } else if (havingClause != null) {
            val bindingsForHaving: List<Pair<String, AOPBase>> = visit071("", false,false, havingClause).map { "_ASTSelectQuery#${counter++}" to it }
            res = LOPGroup(query, listOf(), bindingsForHaving, res)
            for ((first) in bindingsForHaving) {
                res = LOPFilter(query, AOPVariable(query, first), res)
            }
        }
        val orderClause = solutionModifier.variable2!!.variable0
        if (orderClause != null) {
            res = visit163("", false,false, orderClause, res)
        }
        val limitOffsetClause = solutionModifier.variable3!!.variable0
        if (limitOffsetClause != null) {
            res = visit152(limitOffsetClause, res)
        }
        return LOPMakeBooleanResult(query, res)
    }

    private fun visit163(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTOrderClause, tmp: IOPBase): IOPBase {
        var res = tmp
        val names = mutableListOf<Pair<Boolean, String>>()
        for (v in node.variable0!!.value) {
            val asc = initializeEnum(v.variable0) != ASTEnumOfASCAndDESC.ASTEnumOfASCAndDESCDESC
            val tt = visit154(graph, graphVar,graphOverride, v.variable1!!)
            if (tt is AOPVariable) {
                names.add(0, asc to tt.name)
            } else {
                val name = "#sort_${counter++}"
                names.add(0, asc to name)
                res = createBind(AOPVariable(query, name), tt, res)
            }
        }
        for ((first, second) in names) {
            res = LOPSort(query, first, AOPVariable(query, second), res)
        }
        return res
    }

    private fun visit162(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTClassOfDISTINCTAndExpressionAndListOfExpression): Pair<Boolean, List<AOPBase>> {
        val res = mutableListOf<AOPBase>()
        res.add(visit033(graph, graphVar, graphOverride,node.variable1!!))
        val v2 = node.variable2
        if (v2 != null) {
            for (v in v2.value) {
                res.add(visit033(graph, graphVar,graphOverride, v))
            }
        }
        return node.DISTINCT to res
    }

    private fun visit161(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTArgList) = when (node) {
        is ASTClassOfDISTINCTAndExpressionAndListOfExpression -> visit162(graph, graphVar,graphOverride,node)
        is ASTNILParam -> false to listOf()
    }

    private fun visit160(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTFunctionCall) = when (val iri = visit131(node.variable0!!)) {
        "http://www.w3.org/2001/XMLSchema#double" -> {
            val t = visit161(graph, graphVar, graphOverride,node.variable1!!)
            if (t.second.size != 1) {
                TODO("<http://www.w3.org/2001/XMLSchema#double> requires exactly 1 parameter")
            }
            AOPFunctionCallDouble(query, t.second[0])
        }
        "http://www.w3.org/2001/XMLSchema#float" -> {
            val t = visit161(graph, graphVar, graphOverride,node.variable1!!)
            if (t.second.size != 1) {
                TODO("<http://www.w3.org/2001/XMLSchema#float> requires exactly 1 parameter")
            }
            AOPFunctionCallFloat(query, t.second[0])
        }
        "http://www.w3.org/2001/XMLSchema#string" -> {
            val t = visit161(graph, graphVar, graphOverride,node.variable1!!)
            if (t.second.size != 1) {
                TODO("<http://www.w3.org/2001/XMLSchema#string> requires exactly 1 parameter")
            }
            AOPFunctionCallString(query, t.second[0])
        }
        else -> {
            TODO("ASTFunctionCall $iri ${node.variable0}")
        }
    }

    private fun visit159(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTExpressionList) = when (node) {
        is ASTClassOfExpressionAndListOfExpression -> visit037(graph, graphVar, graphOverride,node)
        is ASTNILParam -> listOf()
    }

    private fun visit158(graph: String, graphVar: Boolean, graphOverride:Boolean,node: ASTBuiltInCall) = when (node) {
        is ASTBuiltInCallSameTerm -> TODO("OperatorGraphVisitor not implemented")
        is ASTBuiltInCallIsBlanc -> TODO("OperatorGraphVisitor not implemented")
        is ASTBuiltInCallSHA384 -> TODO("OperatorGraphVisitor not implemented")
        is ASTBuiltInCallSHA512 -> TODO("OperatorGraphVisitor not implemented")
        is ASTRegexExpression -> TODO("OperatorGraphVisitor not implemented")
        is ASTSubstringExpression -> TODO("OperatorGraphVisitor not implemented")
        is ASTStrReplaceExpression -> TODO("OperatorGraphVisitor not implemented")
        is ASTBuiltInCallRand -> TODO("OperatorGraphVisitor not implemented")
        is ASTBuiltInCallIsUri -> TODO("OperatorGraphVisitor not implemented")
        is ASTBuiltInCallEncodeForUri -> TODO("OperatorGraphVisitor not implemented")
        is ASTBuiltInCallYear -> AOPBuildInCallYEAR(query, visit033(graph, graphVar, graphOverride,node.variable0!!))
        is ASTBuiltInCallMonth -> AOPBuildInCallMONTH(query, visit033(graph, graphVar, graphOverride,node.variable0!!))
        is ASTBuiltInCallHours -> AOPBuildInCallHOURS(query, visit033(graph, graphVar, graphOverride,node.variable0!!))
        is ASTBuiltInCallNow -> AOPConstant(query, queryExecutionStartTime)
        is ASTBuiltInCallIsIri -> AOPBuildInCallIRI(query, visit033(graph, graphVar,graphOverride,node.variable0!!), "")
        is ASTBuiltInCallIsLiteral -> AOPBuildInCallIsLITERAL(query, visit033(graph, graphVar,graphOverride,node.variable0!!))
        is ASTBuiltInCallMD5 -> AOPBuildInCallMD5(query, visit033(graph, graphVar,graphOverride,node.variable0!!))
        is ASTBuiltInCallUUID -> AOPBuildInCallUUID(query)
        is ASTBuiltInCallSTRUUID -> AOPBuildInCallSTRUUID(query)
        is ASTBuiltInCallSHA1 -> AOPBuildInCallSHA1(query, visit033(graph, graphVar,graphOverride,node.variable0!!))
        is ASTBuiltInCallCoalesce -> AOPBuildInCallCOALESCE(query, visit159(graph, graphVar,graphOverride,node.variable0!!))
        is ASTBuiltInCallIf -> AOPBuildInCallIF(query, visit033(graph, graphVar,graphOverride,node.variable0!!), visit033(graph, graphVar,graphOverride,node.variable1!!), visit033(graph, graphVar,graphOverride,node.variable2!!))
        is ASTBuiltInCallStrLang -> AOPBuildInCallSTRLANG(query, visit033(graph, graphVar,graphOverride,node.variable0!!), visit033(graph, graphVar,graphOverride,node.variable1!!))
        is ASTBuiltInCallMinutes -> AOPBuildInCallMINUTES(query, visit033(graph, graphVar,graphOverride,node.variable0!!))
        is ASTBuiltInCallSHA256 -> AOPBuildInCallSHA256(query, visit033(graph, graphVar,graphOverride,node.variable0!!))
        is ASTBuiltInCallStrDt -> AOPBuildInCallSTRDT(query, visit033(graph, graphVar,graphOverride,node.variable0!!), visit033(graph, graphVar,graphOverride,node.variable1!!))
        is ASTBuiltInCallIsNumeric -> AOPBuildInCallIsNUMERIC(query, visit033(graph, graphVar,graphOverride,node.variable0!!))
        is ASTBuiltInCallSeconds -> AOPBuildInCallSECONDS(query, visit033(graph, graphVar,graphOverride,node.variable0!!))
        is ASTBuiltInCallTimezone -> AOPBuildInCallTIMEZONE(query, visit033(graph, graphVar,graphOverride,node.variable0!!))
        is ASTBuiltInCallTz -> AOPBuildInCallTZ(query, visit033(graph, graphVar,graphOverride,node.variable0!!))
        is ASTBuiltInCallStrLen -> AOPBuildInCallSTRLEN(query, visit033(graph, graphVar,graphOverride,node.variable0!!))
        is ASTBuiltInCallUCase -> AOPBuildInCallUCASE(query, visit033(graph, graphVar,graphOverride,node.variable0!!))
        is ASTBuiltInCallStrBefore -> AOPBuildInCallSTRBEFORE(query, visit033(graph, graphVar,graphOverride,node.variable0!!), visit033(graph, graphVar,graphOverride,node.variable1!!))
        is ASTBuiltInCallStrAfter -> AOPBuildInCallSTRAFTER(query, visit033(graph, graphVar,graphOverride,node.variable0!!), visit033(graph, graphVar,graphOverride,node.variable1!!))
        is ASTBuiltInCallContains -> AOPBuildInCallCONTAINS(query, visit033(graph, graphVar,graphOverride,node.variable0!!), visit033(graph, graphVar,graphOverride,node.variable1!!))
        is ASTBuiltInCallStrStarts -> AOPBuildInCallSTRSTARTS(query, visit033(graph, graphVar,graphOverride,node.variable0!!), visit033(graph, graphVar,graphOverride,node.variable1!!))
        is ASTBuiltInCallStrEnds -> AOPBuildInCallSTRENDS(query, visit033(graph, graphVar,graphOverride,node.variable0!!), visit033(graph, graphVar,graphOverride,node.variable1!!))
        is ASTBuiltInCallDay -> AOPBuildInCallDAY(query, visit033(graph, graphVar,graphOverride,node.variable0!!))
        is ASTBuiltInCallStr -> AOPBuildInCallSTR(query, visit033(graph, graphVar,graphOverride,node.variable0!!))
        is ASTBuiltInCallLang -> AOPBuildInCallLANG(query, visit033(graph, graphVar,graphOverride,node.variable0!!))
        is ASTBuildInCallLangMatches -> AOPBuildInCallLANGMATCHES(query, visit033(graph, graphVar,graphOverride,node.variable0!!), visit033(graph, graphVar,graphOverride,node.variable1!!))
        is ASTBuiltInCallDataType -> AOPBuildInCallDATATYPE(query, visit033(graph, graphVar,graphOverride,node.variable0!!))
        is ASTBuiltInCallBound -> AOPBuildInCallBOUND(query, visit153(node.variable0!!))
        is ASTBuiltInCallIri -> AOPBuildInCallIRI(query, visit033(graph, graphVar,graphOverride,node.variable0!!), "")
        is ASTBuiltInCallUri -> AOPBuildInCallURI(query, visit033(graph, graphVar,graphOverride,node.variable0!!), "")
        is ASTBuiltInCallAbs -> AOPBuildInCallABS(query, visit033(graph, graphVar,graphOverride,node.variable0!!))
        is ASTBuiltInCallCeil -> AOPBuildInCallCEIL(query, visit033(graph, graphVar,graphOverride,node.variable0!!))
        is ASTBuiltInCallLCase -> AOPBuildInCallLCASE(query, visit033(graph, graphVar,graphOverride,node.variable0!!))
        is ASTBuiltInCallFloor -> AOPBuildInCallFLOOR(query, visit033(graph, graphVar,graphOverride,node.variable0!!))
        is ASTBuiltInCallRound -> AOPBuildInCallROUND(query, visit033(graph, graphVar,graphOverride,node.variable0!!))
        is ASTExistsFunc -> AOPBuildInCallExists(query, visit026(true, graph, graphVar,graphOverride, node.variable0!!))
        is ASTNotExistsFunc -> AOPBuildInCallNotExists(query, visit026(true, graph,graphOverride, graphVar, node.variable0!!))
        is ASTAggregate -> visit157(graph, graphVar,graphOverride,node)
        is ASTBuiltInCallConcat -> visit159(graph, graphVar,graphOverride,node.variable0!!).reduceRight { s, t -> AOPBuildInCallCONCAT(query, s, t) }
        is ASTBuiltInCallBNode -> when (val v0 = node.variable0!!) {
            is ASTExpression -> AOPBuildInCallBNODE1(query, visit033(graph, graphVar,graphOverride, v0))
            is ASTNILParam -> AOPBuildInCallBNODE0(query)
        }
    }

    private fun visit157(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTAggregate) = when (node) {
        is ASTAggregateAvg -> AOPAggregationAVG(query, node.DISTINCT, visit033(graph, graphVar,graphOverride,node.variable1!!))
        is ASTAggregateCount -> AOPAggregationCOUNT(query, node.DISTINCT, visit156(graph, graphVar,graphOverride,node.variable1!!))
        is ASTAggregateSum -> AOPAggregationSUM(query, node.DISTINCT, visit033(graph, graphVar,graphOverride,node.variable1!!))
        is ASTAggregateMin -> AOPAggregationMIN(query, node.DISTINCT, visit033(graph, graphVar,graphOverride,node.variable1!!))
        is ASTAggregateMax -> AOPAggregationMAX(query, node.DISTINCT, visit033(graph, graphVar,graphOverride,node.variable1!!))
        is ASTAggregateSample -> AOPAggregationSAMPLE(query, node.DISTINCT, visit033(graph, graphVar,graphOverride,node.variable1!!))
        is ASTAggregateGroupConcat -> TODO("OperatorGraphVisitor not implemented")
    }

    private fun visit156(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTInterfaceOfAggregateCountAllOrExpression) = when (node) {
        is ASTAggregateCountAll -> null
        is ASTExpression -> visit033(graph, graphVar,graphOverride,node)
    }

    private fun visit155(graph: String, graphVar: Boolean, graphOverride:Boolean,node: ASTConstraint) = when (node) {
        is ASTFunctionCall -> visit160(graph, graphVar,graphOverride,node)
        is ASTBrackettedExpression -> visit033(graph, graphVar,graphOverride,node.variable0!!)
        is ASTBuiltInCall -> visit158(graph, graphVar,graphOverride, node)
    }

    private fun visit154(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTInterfaceOfConstraintOrVar) = when (node) {
        is ASTConstraint -> visit155(graph, graphVar,graphOverride,node)
        is ASTVar -> visit153(node)
    }

    private fun visit153(node: ASTVar) = when (node) {
        is ASTVar1 -> AOPVariable(query, node.VAR1!!.drop(1))
        is ASTVar2 -> AOPVariable(query, node.VAR2!!.drop(1))
    }

    private fun visit152(node: ASTLimitOffsetClauses, tmp: IOPBase) = when (node) {
        is ASTClassOfLimitClauseAndOffsetClauseOptional -> visit006(node.variable1!!, visit003(node.variable0!!, tmp))
        is ASTClassOfOffsetClauseAndLimitClauseOptional -> visit005(node.variable1!!, visit004(node.variable0!!, tmp))
    }

    private fun visit151(node: ASTInterfaceOfBaseDeclOrPrefixDecl) = when (node) {
        is ASTBaseDecl -> visit028(node)
        is ASTPrefixDecl -> visit027(node)
    }

    private fun visit150(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTRelationalExpression) = when (val v1 = node.variable1!!.variable0) {
        is ASTRelationalExpressionEQ -> AOPEQ(query, visit038(graph, graphVar,graphOverride,node.variable0!!), visit038(graph, graphVar,graphOverride, v1.variable0!!))
        is ASTRelationalExpressionNEQ -> AOPNEQ(query, visit038(graph, graphVar,graphOverride,node.variable0!!), visit038(graph, graphVar,graphOverride, v1.variable0!!))
        is ASTRelationalExpressionLT -> AOPLT(query, visit038(graph, graphVar,graphOverride,node.variable0!!), visit038(graph, graphVar,graphOverride, v1.variable0!!))
        is ASTRelationalExpressionGT -> AOPGT(query, visit038(graph, graphVar,graphOverride,node.variable0!!), visit038(graph, graphVar,graphOverride, v1.variable0!!))
        is ASTRelationalExpressionLEQ -> AOPLEQ(query, visit038(graph, graphVar,graphOverride,node.variable0!!), visit038(graph, graphVar,graphOverride, v1.variable0!!))
        is ASTRelationalExpressionGEQ -> AOPGEQ(query, visit038(graph, graphVar,graphOverride,node.variable0!!), visit038(graph, graphVar,graphOverride, v1.variable0!!))
        is ASTRelationalExpressionIN -> AOPIn(query, visit038(graph, graphVar,graphOverride,node.variable0!!), AOPSet(query, visit159(graph, graphVar,graphOverride, v1.variable0!!)))
        is ASTRelationalExpressionNOTIN -> AOPNotIn(query, visit038(graph, graphVar,graphOverride,node.variable0!!), AOPSet(query, visit159(graph, graphVar,graphOverride, v1.variable0!!)))
        null -> visit038(graph, graphVar,graphOverride,node.variable0!!)
    }

    private fun visit149(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTAdditiveExpression) = node.variable1!!.value.fold(visit136(graph, graphVar,graphOverride,node.variable0!!)) { res, v ->
        when (v) {
            is ASTAdditiveExpressionPLUS -> AOPAddition(query, res, visit136(graph, graphVar,graphOverride, v.variable0!!))
            is ASTAdditiveExpressionMINUS -> AOPSubtraction(query, res, visit136(graph, graphVar,graphOverride, v.variable0!!))
            is ASTClassOfInterfaceOfNumericLiteralPositiveOrNumericLiteralNegativeAndListOfInterfaceOfAdditiveExpressionMULTIPLYOrAdditiveExpressionDIVIDE -> {
                val buffer = ByteArrayWrapper()
                val isAddition = when (val v10 = v.variable0!!) {
                    is ASTNumericLiteralPositiveInteger -> {
                        DictionaryHelper.integerToByteArray(buffer, v10.INTEGER_POSITIVE!!.drop(1))
                        true
                    }
                    is ASTNumericLiteralPositiveDecimal -> {
                        DictionaryHelper.decimalToByteArray(buffer, v10.DECIMAL_POSITIVE!!.drop(1))
                        true
                    }
                    is ASTNumericLiteralPositiveDouble -> {
                        DictionaryHelper.doubleToByteArray(buffer, v10.DOUBLE_POSITIVE!!.drop(1))
                        true
                    }
                    is ASTNumericLiteralNegativeInteger -> {
                        DictionaryHelper.integerToByteArray(buffer, v10.INTEGER_NEGATIVE!!.drop(1))
                        false
                    }
                    is ASTNumericLiteralNegativeDecimal -> {
                        DictionaryHelper.decimalToByteArray(buffer, v10.DECIMAL_NEGATIVE!!.drop(1))
                        false
                    }
                    is ASTNumericLiteralNegativeDouble -> {
                        DictionaryHelper.doubleToByteArray(buffer, v10.DOUBLE_NEGATIVE!!.drop(1))
                        false
                    }
                }
                val op = v.variable1!!.value.fold(AOPConstant(query, buffer) as AOPBase) { s, t ->
                    when (t) {
                        is ASTAdditiveExpressionMULTIPLY -> AOPMultiplication(query, s, visit135(graph, graphVar,graphOverride, t.variable0!!))
                        is ASTAdditiveExpressionDIVIDE -> AOPDivision(query, s, visit135(graph, graphVar,graphOverride, t.variable0!!))
                    }
                }
                if (isAddition) {
                    AOPAddition(query, res, op)
                } else {
                    AOPSubtraction(query, res, op)
                }
            }
        }
    }

    private fun visit148(node: ASTNumericLiteralUnsigned) = when (node) {
        is ASTNumericLiteralUnsignedInteger -> visit145(node)
        is ASTNumericLiteralUnsignedDecimal -> visit144(node)
        is ASTNumericLiteralUnsignedDouble -> visit143(node)
    }

    private fun visit147(node: ASTNumericLiteralPositive) = when (node) {
        is ASTNumericLiteralPositiveInteger -> visit142(node)
        is ASTNumericLiteralPositiveDecimal -> visit141(node)
        is ASTNumericLiteralPositiveDouble -> visit140(node)
    }

    private fun visit146(node: ASTNumericLiteralNegative) = when (node) {
        is ASTNumericLiteralNegativeInteger -> visit139(node)
        is ASTNumericLiteralNegativeDecimal -> visit138(node)
        is ASTNumericLiteralNegativeDouble -> visit137(node)
    }

    private fun visit145(node: ASTNumericLiteralUnsignedInteger): AOPConstant {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.integerToByteArray(buffer, node.INTEGER!!)
        return AOPConstant(query, buffer)
    }

    private fun visit144(node: ASTNumericLiteralUnsignedDecimal): AOPConstant {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.decimalToByteArray(buffer, node.DECIMAL!!)
        return AOPConstant(query, buffer)
    }

    private fun visit143(node: ASTNumericLiteralUnsignedDouble): AOPConstant {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.doubleToByteArray(buffer, node.DOUBLE!!)
        return AOPConstant(query, buffer)
    }

    private fun visit142(node: ASTNumericLiteralPositiveInteger): AOPConstant {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.integerToByteArray(buffer, node.INTEGER_POSITIVE!!)
        return AOPConstant(query, buffer)
    }

    private fun visit141(node: ASTNumericLiteralPositiveDecimal): AOPConstant {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.decimalToByteArray(buffer, node.DECIMAL_POSITIVE!!)
        return AOPConstant(query, buffer)
    }

    private fun visit140(node: ASTNumericLiteralPositiveDouble): AOPConstant {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.doubleToByteArray(buffer, node.DOUBLE_POSITIVE!!)
        return AOPConstant(query, buffer)
    }

    private fun visit139(node: ASTNumericLiteralNegativeInteger): AOPConstant {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.integerToByteArray(buffer, node.INTEGER_NEGATIVE!!)
        return AOPConstant(query, buffer)
    }

    private fun visit138(node: ASTNumericLiteralNegativeDecimal): AOPConstant {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.decimalToByteArray(buffer, node.DECIMAL_NEGATIVE!!)
        return AOPConstant(query, buffer)
    }

    private fun visit137(node: ASTNumericLiteralNegativeDouble): AOPConstant {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.doubleToByteArray(buffer, node.DOUBLE_NEGATIVE!!)
        return AOPConstant(query, buffer)
    }

    private fun visit136(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTMultiplicativeExpression) = node.variable1!!.value.fold(visit135(graph, graphVar,graphOverride,node.variable0!!)) { s, t ->
        when (t) {
            is ASTMultiplicativeExpressionMULTIPLY -> AOPMultiplication(query, s, visit135(graph, graphVar,graphOverride, t.variable0!!))
            is ASTMultiplicativeExpressionDIVIDE -> AOPDivision(query, s, visit135(graph, graphVar,graphOverride, t.variable0!!))
        }
    }

    private fun visit135(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTUnaryExpression) = when (node) {
        is ASTUnaryExpressionNOT -> AOPNot(query, visit134(graph, graphVar,graphOverride,node.variable0!!))
        is ASTUnaryExpressionPLUS -> visit134(graph, graphVar,graphOverride,node.variable0!!)
        is ASTUnaryExpressionMINUS -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.integerToByteArray(buffer, "0")
            AOPSubtraction(query, AOPConstant(query, buffer), visit134(graph, graphVar,graphOverride,node.variable0!!))
        }
        is ASTPrimaryExpression -> visit134(graph, graphVar,graphOverride,node)
    }

    private fun visit134(graph: String, graphVar: Boolean, graphOverride:Boolean,node: ASTPrimaryExpression) = when (node) {
        is ASTBrackettedExpression -> visit033(graph, graphVar,graphOverride,node.variable0!!)
        is ASTBuiltInCall -> visit158(graph, graphVar,graphOverride,node)
        is ASTiriOrFunction -> visit133(graph, graphVar,graphOverride,node)
        is ASTRDFLiteral -> visit132(node)
        is ASTNumericLiteral -> visit128(node)
        is ASTBooleanLiteral -> visit129(node)
        is ASTVar -> visit153(node)
    }

    private fun visit133(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTiriOrFunction) = if (node.variable1!!.variable0 != null) {
        val f = ASTFunctionCall()
        f.variable0 = node.variable0!!
        f.variable1 = node.variable1!!.variable0!!
        visit160(graph, graphVar,graphOverride, f)
    } else {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.iriToByteArray(buffer, visit131(node.variable0!!))
        AOPConstant(query, buffer)
    }

    private fun visit132(node: ASTRDFLiteral) = when (val v1 = node.variable1!!.variable0) {
        null -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.stringToByteArray(buffer, visit130(node.variable0!!))
            AOPConstant(query, buffer)
        }
        is ASTiri -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.typedToByteArray(buffer, visit130(node.variable0!!), visit131(v1))
            AOPConstant(query, buffer)
        }
        is ASTRDFLiteralLang -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.langToByteArray(buffer, visit130(node.variable0!!), v1.LANGTAG!!.drop(1))
            AOPConstant(query, buffer)
        }
    }

    private fun visit131(node: ASTiri): String = when (node) {
        is ASTiriRef -> node.IRIREF!!.drop(1).dropLast(1)
        is ASTPrefixedNameLN -> {
            val x = node.PNAME_LN!!.split(":")
            decodeIri(x[0], x[1])
        }
        is ASTPrefixedNameNS -> decodeIri(node.PNAME_NS!!.dropLast(1))
    }

    private fun visit130(node: ASTString) = when (node) {
        is ASTString1 -> visit042(node)
        is ASTString2 -> visit041(node)
        is ASTString1long -> visit040(node)
        is ASTString2long -> visit039(node)
    }

    private fun visit129(node: ASTBooleanLiteral) = when (node) {
        is ASTBooleanLiteralTrue -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.booleanToByteArray(buffer, true)
            AOPConstant(query, buffer)
        }
        is ASTBooleanLiteralFalse -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.booleanToByteArray(buffer, false)
            AOPConstant(query, buffer)
        }
    }

    private fun visit128(node: ASTNumericLiteral) = when (node) {
        is ASTNumericLiteralUnsigned -> visit148(node)
        is ASTNumericLiteralPositive -> visit147(node)
        is ASTNumericLiteralNegative -> visit146(node)
    }

    private fun visit127(node: ASTLoad): LOPGraphOperation {
        val into = node.variable2!!.variable0
        return if (into != null) {
            LOPGraphOperation(query, EGraphOperationTypeExt.LOAD, node.SILENT, EGraphRefTypeExt.DefaultGraphRef, visit131(node.variable1!!), EGraphRefTypeExt.IriGraphRef, visit131(into.variable0!!))
        } else {
            LOPGraphOperation(query, EGraphOperationTypeExt.LOAD, node.SILENT, EGraphRefTypeExt.DefaultGraphRef, visit131(node.variable1!!), EGraphRefTypeExt.DefaultGraphRef, TripleStoreManager.DEFAULT_GRAPH_NAME)
        }
    }

    private fun visit126(node: ASTGraphOrDefault) = when (node) {
        is ASTClassOfGRAPHAndiri -> EGraphRefTypeExt.IriGraphRef to visit131(node.variable1!!)
        is ASTGraphRefDefault -> EGraphRefTypeExt.DefaultGraphRef to null
    }

    private fun visit125(node: ASTGraphRefAll) = when (node) {
        is ASTGraphRef -> EGraphRefTypeExt.IriGraphRef to visit131(node.variable0!!)
        is ASTGraphRefDefault -> EGraphRefTypeExt.DefaultGraphRef to null
        is ASTGraphRefNamed -> EGraphRefTypeExt.NamedGraphRef to null
        is ASTGraphRefAll2 -> EGraphRefTypeExt.AllGraphRef to null
    }

    private fun visit124(node: ASTUpdate1) = when (node) {
        is ASTLoad -> visit127(node)
        is ASTClear -> visit063(node)
        is ASTDrop -> visit062(node)
        is ASTCreate -> visit061(node)
        is ASTAdd -> visit060(node)
        is ASTMove -> visit057(node)
        is ASTCopy -> visit056(node)
        is ASTInsertData -> visit044(node)
        is ASTDeleteData -> visit043(node)
        is ASTDeleteWhere -> visit109(node)
        is ASTModify -> visit108(node)
    }

    private fun visit123(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTBlankNodePropertyList): Pair<AOPBase, List<LOPTriple>> {
        val subject = if (blankNodeToVariable) {
            AOPVariable(query, "_ASTBlankNodePropertyList#${counter++}")
        } else {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.bnodeToByteArray(buffer, "_ASTBlankNodePropertyList#${counter++}")
            AOPConstant(query, buffer)
        }
        return subject to visit050(blankNodeToVariable, graph, graphVar,graphOverride, subject, node.variable0!!)
    }

    private fun visit122(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTCollection): Pair<AOPBase, List<LOPTriple>> {
        val bufferNil = ByteArrayWrapper()
        val bufferFirst = ByteArrayWrapper()
        val bufferRest = ByteArrayWrapper()
        DictionaryHelper.iriToByteArray(bufferNil, "http://www.w3.org/1999/02/22-rdf-syntax-ns#nil")
        DictionaryHelper.iriToByteArray(bufferFirst, "http://www.w3.org/1999/02/22-rdf-syntax-ns#first")
        DictionaryHelper.iriToByteArray(bufferRest, "http://www.w3.org/1999/02/22-rdf-syntax-ns#rest")
        var first: AOPBase = AOPConstant(query, bufferNil)
        var current: AOPBase = AOPConstant(query, bufferNil)
        var f = true
        val res = mutableListOf<LOPTriple>()
        for (v in node.variable0!!.value) {
            val t = visit120(blankNodeToVariable, graph, graphVar,graphOverride, v)
            res.addAll(t.second)
            if (f) {
                f = false
                current = if (blankNodeToVariable) {
                    AOPVariable(query, "_ASTCollection#${counter++}")
                } else {
                    val buffer = ByteArrayWrapper()
                    DictionaryHelper.bnodeToByteArray(buffer, "_ASTCollection#${counter++}")
                    AOPConstant(query, buffer)
                }
                first = current
            } else {
                val next = if (blankNodeToVariable) {
                    AOPVariable(query, "_ASTCollection#${counter++}")
                } else {
                    val buffer = ByteArrayWrapper()
                    DictionaryHelper.bnodeToByteArray(buffer, "_ASTCollection#${counter++}")
                    AOPConstant(query, buffer)
                }
                res.add(LOPTriple(query, current, AOPConstant(query, bufferRest), next, graph, graphVar))
                current = next
            }
            res.add(LOPTriple(query, current, AOPConstant(query, bufferFirst), t.first, graph, graphVar))
        }
        if (!f) {
            res.add(LOPTriple(query, current, AOPConstant(query, bufferRest), AOPConstant(query, bufferNil), graph, graphVar))
        }
        return first to res
    }

    private fun visit121(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTTriplesNode): Pair<AOPBase, List<LOPTriple>> = when (node) {
        is ASTBlankNodePropertyList -> visit123(blankNodeToVariable, graph, graphVar,graphOverride,node)
        is ASTCollection -> visit122(blankNodeToVariable, graph, graphVar,graphOverride,node)
    }

    private fun visit120(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTGraphNode): Pair<AOPBase, List<LOPTriple>> = when (node) {
        is ASTTriplesNode -> visit121(blankNodeToVariable, graph, graphVar,graphOverride,node)
        is ASTVar1 -> AOPVariable(query, node.VAR1!!.drop(1)) to listOf()
        is ASTVar2 -> AOPVariable(query, node.VAR2!!.drop(1)) to listOf()
        is ASTGraphTerm -> visit119(blankNodeToVariable, node) to listOf()
    }

    private fun visit119(blankNodeToVariable: Boolean, node: ASTGraphTerm) = when (node) {
        is ASTRDFLiteral -> visit132(node)
        is ASTNumericLiteral -> visit128(node)
        is ASTBooleanLiteral -> visit129(node)
        is ASTiri -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.iriToByteArray(buffer, visit131(node))
            AOPConstant(query, buffer)
        }
        is ASTBlankNode -> visit118(blankNodeToVariable, node)
        is ASTNILParam -> {
            if (blankNodeToVariable) {
                AOPVariable(query, "_ASTGraphTerm#${counter++}")
            } else {
                val buffer = ByteArrayWrapper()
                DictionaryHelper.bnodeToByteArray(buffer, "_ASTGraphTerm#${counter++}")
                AOPConstant(query, buffer)
            }
        }
    }

    private fun visit118(blankNodeToVariable: Boolean, node: ASTBlankNode) = when (node) {
        is ASTBlankNodeLabel -> visit117(blankNodeToVariable, node)
        is ASTBlankNodeANON -> visit116(blankNodeToVariable, node)
    }

    private fun visit117(blankNodeToVariable: Boolean, node: ASTBlankNodeLabel): AOPBase = if (blankNodeToVariable) {
        AOPVariable(query, node.BLANK_NODE_LABEL!! + "#")
    } else {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.bnodeToByteArray(buffer, node.BLANK_NODE_LABEL!!)
        AOPConstant(query, buffer)
    }

    private fun visit116(blankNodeToVariable: Boolean, unused: ASTBlankNodeANON): AOPBase = if (blankNodeToVariable) {
        AOPVariable(query, "_ASTBlankNodeANON#${counter++}")
    } else {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.bnodeToByteArray(buffer, "_ASTBlankNodeANON#${counter++}")
        AOPConstant(query, buffer)
    }

    private fun visit115(blankNodeToVariable: Boolean, node: ASTVarOrTerm) = when (node) {
        is ASTGraphTerm -> visit119(blankNodeToVariable, node)
        is ASTVar1 -> AOPVariable(query, node.VAR1!!.drop(1))
        is ASTVar2 -> AOPVariable(query, node.VAR2!!.drop(1))
    }

    private fun visit114(node: ASTVerb): AOPBase = when (node) {
        is ASTRDFType -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.iriToByteArray(buffer, "http://www.w3.org/1999/02/22-rdf-syntax-ns#type")
            AOPConstant(query, buffer)
        }
        is ASTVar1 -> AOPVariable(query, node.VAR1!!.drop(1))
        is ASTVar2 -> AOPVariable(query, node.VAR2!!.drop(1))
        is ASTiri -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.iriToByteArray(buffer, visit131(node))
            AOPConstant(query, buffer)
        }
    }

    private fun visit113(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTClassOfTriplesNodeAndPropertyListOptional): List<LOPTriple> {
        val tmp = visit121(blankNodeToVariable, graph, graphVar,graphOverride,node.variable0!!)
        return tmp.second + visit083(blankNodeToVariable, graph, graphVar,graphOverride, tmp.first, node.variable1!!)
    }

    private fun visit112(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, subject: AOPBase, predicate: AOPBase, node: ASTObjectList): List<LOPTriple> {
        val a = visit055(blankNodeToVariable, graph, graphVar,graphOverride,node.variable0!!)
        val res = a.second.toMutableList()
        res.add(LOPTriple(query, subject, predicate, a.first, graph, graphVar))
        for (v in node.variable1!!.value) {
            val b = visit055(blankNodeToVariable, graph, graphVar,graphOverride, v)
            res.addAll(b.second)
            res.add(LOPTriple(query, subject, predicate, b.first, graph, graphVar))
        }
        return res
    }

    private fun visit111(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTTriplesSameSubject): List<LOPTriple> = when (node) {
        is ASTClassOfVarOrTermAndPropertyListNotEmpty -> visit059(blankNodeToVariable, graph, graphVar,graphOverride,node)
        is ASTClassOfTriplesNodeAndPropertyListOptional -> visit113(blankNodeToVariable, graph, graphVar,graphOverride,node)
    }

    private fun visit110(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean,node: ASTQuadsNotTriples): List<LOPTriple> = if(graphOverride){
visit079(blankNodeToVariable, graph,graphVar,graphOverride, node.variable1!!)
}else{when (val v0 = node.variable0!!) {
        is ASTVar1 -> visit079(blankNodeToVariable, v0.VAR1!!.drop(1), true,graphOverride, node.variable1!!)
        is ASTVar2 -> visit079(blankNodeToVariable, v0.VAR2!!.drop(1), true,graphOverride, node.variable1!!)
        is ASTiri -> visit079(blankNodeToVariable, visit131(v0), false,graphOverride, node.variable1!!)
    }
}

    private fun visit109(node: ASTDeleteWhere): LOPModify {
        val mapping = mutableMapOf<DictionaryValueType, AOPVariable>()
        fun bnodeToVariable(v: IOPBase): AOPBase {
            return if (v is AOPConstant) {
                val vv = v.getValue()
                if (vv and DictionaryValueHelper.flagNoBNode == DictionaryValueHelper.flagNoBNode) {
                    v
                } else {
                    var t = mapping[vv]
                    if (t == null) {
                        t = AOPVariable(query, "_ASTDeleteWhere${counter++}")
                        mapping[vv] = t
                    }
                    t
                }
            } else {
                v as AOPBase
            }
        }

        val tmp = visit054("",false,false, node.variable0!!).map { LOPTriple(query, bnodeToVariable(it.children[0]), bnodeToVariable(it.children[1]), bnodeToVariable(it.children[2]), it.graph, it.graphVar) }
        return LOPModify(query, mutableListOf(), tmp.toMutableList(), tmp.map { it as OPBase }.reduce { s, t -> LOPJoin(query, s, t, false) })
    }

    private fun visit108(node: ASTModify): LOPModify {
        val graph = node.variable0!!.variable0?.let { visit131(it) } ?: ""
        val usingClause: ASTListOfUsingClause = node.variable2!!
        val theData: IOPBase = if (usingClause.value.size > 0) {
            usingClause.value.map { visit026(true, visit131(it.variable0!!), false,true, node.variable3!!) }.reduce { s, t -> LOPUnion(query, s, t) } 
        } else {
            visit026(true, graph, false,false, node.variable3!!)
        }
        return when (val v1 = node.variable1!!) {
            is ASTInsertClause -> LOPModify(query, visit054(graph,false,false, v1.variable0!!).toMutableList(), mutableListOf(), theData)
            is ASTClassOfDeleteClauseAndInsertClauseOptional -> LOPModify(
                query,
                visit077(graph, false,false,v1.variable1!!).toMutableList(),
                visit023(graph, false,false,v1.variable0!!).toMutableList(),
                theData
            )
        }
    }

    private fun visit107(node: ASTDataBlockValue): AOPConstant = when (node) {
        is ASTUNDEF -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.undefToByteArray(buffer)
            AOPConstant(query, buffer)
        }
        is ASTRDFLiteral -> visit132(node)
        is ASTNumericLiteral -> visit128(node)
        is ASTBooleanLiteral -> visit129(node)
        is ASTiri -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.iriToByteArray(buffer, visit131(node))
            AOPConstant(query, buffer)
        }
    }

    private fun visit106(node: ASTDataBlock) = when (node) {
        is ASTInlineDataOneVar -> visit065(node)
        is ASTInlineDataFull -> visit105(node)
    }

    private fun visit105(node: ASTInlineDataFull): LOPValues = when (val v = node.variable0!!) {
        is ASTListOfVar -> LOPValues(query, visit068(v), visit066(node.variable1!!).map { AOPValue(query, it) })
        is ASTNILParam -> LOPValues(query, listOf(), visit066(node.variable1!!).map { AOPValue(query, it) })
    }

    private fun visit104(node: ASTInterfaceOfListOfDataBlockValueOrNILParam) = when (node) {
        is ASTNILParam -> listOf()
        is ASTListOfDataBlockValue -> visit067(node)
    }

    private fun visit103(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean,node: ASTInterfaceOfSubSelectOrGroupGraphPatternSub): IOPBase = when (node) {
        is ASTGroupGraphPatternSub -> visit086(blankNodeToVariable, graph, graphVar,graphOverride, node)
        is ASTSubSelect -> visit166(graph, graphVar,graphOverride,node)
    }
    private fun visit103b(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean,node: ASTInterfaceOfSubSelectOrGroupGraphPatternSub, child: IOPBase): IOPBase = when (node) {
        is ASTGroupGraphPatternSub -> visit086b(blankNodeToVariable, graph, graphVar,graphOverride,node, child)
        is ASTSubSelect -> LOPJoin(query, child, visit166(graph, graphVar,graphOverride,node), false)
    }

    private fun visit102(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTTriplesSameSubjectPath) = when (node) {
        is ASTClassOfVarOrTermAndPropertyListPathNotEmpty -> visit025(blankNodeToVariable, graph, graphVar,graphOverride,node)
        is ASTClassOfTriplesNodePathAndPropertyListPathOptional -> visit090(blankNodeToVariable, graph, graphVar,graphOverride,node)
    }

    private fun visit101(node: ASTInterfaceOfVerbPathOrVerbSimple): AOPBase = when (node) {
        is ASTVerbSimple -> visit017(node)
        is ASTVerbPath -> visit100(node)
    }

    private fun visit100(node: ASTVerbPath) = visit099(node.variable0!!)
    private fun visit099(node: ASTPath) = visit098(node.variable0!!)
    private fun visit098(node: ASTPathAlternative): AOPBase {
        if (node.variable1!!.value.size > 0) {
            TODO("path not implemented")
        }
        return visit097(node.variable0!!)
    }

    private fun visit097(node: ASTPathSequence): AOPBase {
        if (node.variable1!!.value.size > 0) {
            TODO("path not implemented")
        }
        return visit096(node.variable0!!)
    }

    private fun visit096(node: ASTPathEltOrInverse): AOPBase {
        if (node.negated) {
            TODO("path not implemented")
        }
        return visit095(node.variable1!!)
    }

    private fun visit095(node: ASTPathElt): AOPBase {
        if (initializeEnum(node.variable1) != ASTEnumOfoptionalAndanyAndatLeastOne._UNDEFINED) {
            TODO("path not implemented")
        }
        return visit094(node.variable0!!)
    }

    private fun visit094(node: ASTPathPrimary): AOPBase = when (node) {
        is ASTiri -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.iriToByteArray(buffer, visit131(node))
            AOPConstant(query, buffer)
        }
        is ASTRDFType -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.iriToByteArray(buffer, "http://www.w3.org/1999/02/22-rdf-syntax-ns#type")
            AOPConstant(query, buffer)
        }
        is ASTPath -> visit099(node)
        is ASTPathNegatedPropertySet -> TODO("path not implemented")
    }

    private fun visit093(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, subject: AOPBase, predicate: AOPBase, node: ASTObjectListPath): List<LOPTriple> {
        val a = visit016(blankNodeToVariable, graph, graphVar,graphOverride,node.variable0!!)
        val res = a.second.toMutableList()
        res.add(LOPTriple(query, subject, predicate, a.first, graph, graphVar))
        for (v in node.variable1!!.value) {
            val b = visit016(blankNodeToVariable, graph, graphVar,graphOverride, v)
            res.addAll(b.second)
            res.add(LOPTriple(query, subject, predicate, b.first, graph, graphVar))
        }
        return res
    }

    private fun visit092(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTGraphNodePath): Pair<AOPBase, List<LOPTriple>> = when (node) {
        is ASTVar1 -> AOPVariable(query, node.VAR1!!.drop(1)) to listOf()
        is ASTVar2 -> AOPVariable(query, node.VAR2!!.drop(1)) to listOf()
        is ASTGraphTerm -> visit119(blankNodeToVariable, node) to listOf()
        is ASTTriplesNodePath -> visit091(blankNodeToVariable, graph, graphVar,graphOverride,node)
    }

    private fun visit091(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTTriplesNodePath) = when (node) {
        is ASTBlankNodePropertyListPath -> visit089(blankNodeToVariable, graph, graphVar,graphOverride,node)
        is ASTCollectionPath -> visit088(blankNodeToVariable, graph, graphVar,graphOverride,node)
    }

    private fun visit090(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTClassOfTriplesNodePathAndPropertyListPathOptional): List<LOPTriple> {
        val tmp = visit091(blankNodeToVariable, graph, graphVar,graphOverride,node.variable0!!)
        return tmp.second + visit078(blankNodeToVariable, graph, graphVar,graphOverride, tmp.first, node.variable1!!)
    }

    private fun visit089(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTBlankNodePropertyListPath): Pair<AOPBase, List<LOPTriple>> {
        val subject = if (blankNodeToVariable) {
            AOPVariable(query, "_ASTBlankNodePropertyList#${counter++}")
        } else {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.bnodeToByteArray(buffer, "_ASTBlankNodePropertyList#${counter++}")
            AOPConstant(query, buffer)
        }
        return subject to visit022(blankNodeToVariable, graph, graphVar,graphOverride, subject, node.variable0!!)
    }

    private fun visit088(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTCollectionPath): Pair<AOPBase, List<LOPTriple>> {
        val bufferNil = ByteArrayWrapper()
        val bufferFirst = ByteArrayWrapper()
        val bufferRest = ByteArrayWrapper()
        DictionaryHelper.iriToByteArray(bufferNil, "http://www.w3.org/1999/02/22-rdf-syntax-ns#nil")
        DictionaryHelper.iriToByteArray(bufferFirst, "http://www.w3.org/1999/02/22-rdf-syntax-ns#first")
        DictionaryHelper.iriToByteArray(bufferRest, "http://www.w3.org/1999/02/22-rdf-syntax-ns#rest")
        var first: AOPBase = AOPConstant(query, bufferNil)
        var current: AOPBase = AOPConstant(query, bufferNil)
        var f = true
        val res = mutableListOf<LOPTriple>()
        for (v in node.variable0!!.value) {
            val t = visit092(blankNodeToVariable, graph, graphVar,graphOverride, v)
            res.addAll(t.second)
            if (f) {
                f = false
                current = if (blankNodeToVariable) {
                    AOPVariable(query, "_ASTCollection#${counter++}")
                } else {
                    val buffer = ByteArrayWrapper()
                    DictionaryHelper.bnodeToByteArray(buffer, "_ASTCollection#${counter++}")
                    AOPConstant(query, buffer)
                }
                first = current
            } else {
                val next = if (blankNodeToVariable) {
                    AOPVariable(query, "_ASTCollection#${counter++}")
                } else {
                    val buffer = ByteArrayWrapper()
                    DictionaryHelper.bnodeToByteArray(buffer, "_ASTCollection#${counter++}")
                    AOPConstant(query, buffer)
                }
                res.add(LOPTriple(query, current, AOPConstant(query, bufferRest), next, graph, graphVar))
                current = next
            }
            res.add(LOPTriple(query, current, AOPConstant(query, bufferFirst), t.first, graph, graphVar))
        }
        if (!f) {
            res.add(LOPTriple(query, current, AOPConstant(query, bufferRest), AOPConstant(query, bufferNil), graph, graphVar))
        }
        return first to res
    }

    private fun visit087(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean,graphOverride:Boolean,node: ASTGraphGraphPattern, child: IOPBase ) = LOPJoin(
        query,
        child,
if(graphOverride){
visit026(blankNodeToVariable, graph, graphVar,true, node.variable1!!)
}else{
        when (val v0 = node.variable0!!) {
            is ASTVar1 -> visit026(blankNodeToVariable, v0.VAR1!!.drop(1), true,false, node.variable1!!)
            is ASTVar2 -> visit026(blankNodeToVariable, v0.VAR2!!.drop(1), true,false, node.variable1!!)
            is ASTiri -> visit026(blankNodeToVariable, visit131(v0), false,false, node.variable1!!)
        }},
        false
    )

    private fun visit086b(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean,graphOverride:Boolean, node: ASTGroupGraphPatternSub, child: IOPBase): IOPBase {
        var triplesBlockPrevious = (visit082(blankNodeToVariable, graph, graphVar,graphOverride,node.variable0!!) as List<IOPBase>) + listOf(child)
        val listNotTriples: MutableList<ASTClassOfGraphPatternNotTriplesAndpointAndTriplesBlockOptional> = node.variable1!!.value
        val allFilter = mutableListOf<ASTFilter>() // filters are always applied at the end of a ASTGroupGraphPatternSub
        var res = listNotTriples.fold(
            triplesBlockPrevious.reduce { s, t ->
                LOPJoin(query, s, t, false)
            }
        ) { u, v ->
            val tmp = visit085(blankNodeToVariable, graph, graphVar,graphOverride,v.variable0!!, u)
            allFilter.addAll(tmp.first)
            (visit082(blankNodeToVariable, graph, graphVar,graphOverride, v.variable2!!) + tmp.second).reduce { s, t -> LOPJoin(query, s, t, false) }
        }
        for (f in allFilter) {
            res = LOPFilter(query, visit155(graph, graphVar,graphOverride, f.variable0!!), res)
        }
        return res
    }

    private fun visit086(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean,graphOverride:Boolean, node: ASTGroupGraphPatternSub): IOPBase {
        var triplesBlockPrevious = visit082(blankNodeToVariable, graph, graphVar,graphOverride,node.variable0!!) as List<IOPBase>
        val listNotTriples: MutableList<ASTClassOfGraphPatternNotTriplesAndpointAndTriplesBlockOptional> = node.variable1!!.value
        val allFilter = mutableListOf<ASTFilter>() // filters are always applied at the end of a ASTGroupGraphPatternSub
        if (triplesBlockPrevious.isEmpty()) {
            if (listNotTriples.isEmpty()) {
                return OPEmptyRow(query)
            }
            val v = listNotTriples.removeAt(0)
            val tmp = visit084(blankNodeToVariable, graph, graphVar,graphOverride, v.variable0!!)
            allFilter.addAll(tmp.first)
            triplesBlockPrevious = visit082(blankNodeToVariable, graph, graphVar,graphOverride, v.variable2!!) + tmp.second
        }
        var res = listNotTriples.fold(
            triplesBlockPrevious.reduce { s, t ->
                LOPJoin(query, s, t, false)
            }
        ) { u, v ->
            val tmp = visit085(blankNodeToVariable, graph, graphVar,graphOverride, v.variable0!!, u)
            allFilter.addAll(tmp.first)
            (visit082(blankNodeToVariable, graph, graphVar,graphOverride, v.variable2!!) + tmp.second).reduce { s, t -> LOPJoin(query, s, t, false) }
        }
        for (f in allFilter) {
            res = LOPFilter(query, visit155(graph, graphVar,graphOverride, f.variable0!!), res)
        }
        return res
    }

    private fun visit085(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean,graphOverride:Boolean, node: ASTGraphPatternNotTriples, child: IOPBase): Pair<List<ASTFilter>, IOPBase> = when (node) {
        is ASTOptionalGraphPattern -> listOf<ASTFilter>() to visit015(blankNodeToVariable, graph, graphVar,graphOverride,node, child)
        is ASTGroupOrUnionGraphPattern -> listOf<ASTFilter>() to visit014(blankNodeToVariable, graph, graphVar,graphOverride,node, child)
        is ASTMinusGraphPattern -> listOf<ASTFilter>() to visit012(blankNodeToVariable, graph, graphVar,graphOverride,node, child)
        is ASTGraphGraphPattern -> listOf<ASTFilter>() to visit087(blankNodeToVariable, graph, graphVar,graphOverride, node, child)
        is ASTServiceGraphPattern -> listOf<ASTFilter>() to visit009(graph, graphVar,graphOverride,node, child)
        is ASTFilter -> visit011(graph, graphVar,graphOverride,node, child)
        is ASTBind -> listOf<ASTFilter>() to visit010(graph, graphVar,graphOverride,node, child)
        is ASTValuesClause -> listOf<ASTFilter>() to visit058(node)
    }

    private fun visit084(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean,graphOverride:Boolean, node: ASTGraphPatternNotTriples): Pair<List<ASTFilter>, IOPBase> = when (node) {
        is ASTGroupOrUnionGraphPattern -> listOf<ASTFilter>() to visit013(blankNodeToVariable, graph, graphVar,graphOverride,node)
        is ASTFilter -> visit011(graph, graphVar,graphOverride,node, OPEmptyRow(query))
        is ASTValuesClause -> listOf<ASTFilter>() to visit064(node)
        is ASTBind -> listOf<ASTFilter>() to visit010(graph, graphVar,graphOverride,node, OPEmptyRow(query))
        is ASTGraphGraphPattern -> listOf<ASTFilter>() to visit087(blankNodeToVariable, graph, graphVar,graphOverride,node, OPEmptyRow(query))
        else -> TODO(node.toString())
    }

    private fun visit083(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, subject: AOPBase, node: ASTPropertyListOptional): List<LOPTriple> = node.variable0?.let { visit021(blankNodeToVariable, graph, graphVar,graphOverride, subject, it) }
        ?: listOf()

    private fun visit082(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTTriplesBlockOptional): List<LOPTriple> = node.variable0?.let { visit020(blankNodeToVariable, graph, graphVar,graphOverride, it) }
        ?: listOf()

    private fun visit081(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTTriplesBlockOptionalOptional): List<LOPTriple> = node.variable0?.let { visit082(blankNodeToVariable, graph, graphVar,graphOverride, it) }
        ?: listOf()

    private fun visit080(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTTriplesTemplateOptionalOptional): List<LOPTriple> = node.variable0?.let { visit079(blankNodeToVariable, graph, graphVar,graphOverride, it) }
        ?: listOf()

    private fun visit079(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTTriplesTemplateOptional): List<LOPTriple> = node.variable0?.let { visit049(blankNodeToVariable, graph, graphVar,graphOverride, it) }
        ?: listOf()

    private fun visit078(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, subject: AOPBase, node: ASTPropertyListPathOptional): List<LOPTriple> = node.variable0?.let { visit007(blankNodeToVariable, graph, graphVar,graphOverride, subject, it) }
        ?: listOf()

    private fun visit077(graph: String,graphVar: Boolean, graphOverride: Boolean,  node: ASTInsertClauseOptional): List<LOPTriple> = node.variable0?.let { visit024(graph,graphVar,graphOverride, it) }
        ?: listOf()

    private fun visit076(v0: ASTPrologue, v1: ASTClassOfUpdate1AndClassOfPrologueAndUpdateOptionalOptional) = v1.variable0?.let { visit170(v0, it) }
        ?: OPBaseCompound(query, arrayOf(), mutableListOf())

    private fun visit075(node: ASTConstructTriplesOptionalOptional): List<LOPTriple> = node.variable0?.let { visit074(it) }
        ?: listOf()

    private fun visit074(node: ASTConstructTriplesOptional): List<LOPTriple> = node.variable0?.let { visit002(it) }
        ?: listOf()

    private fun visit073(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTHavingCondition) = visit155(graph, graphVar,graphOverride,node.variable0!!)
    private fun visit072(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTListOfHavingCondition) = node.value.map { visit073(graph, graphVar,graphOverride, it) }
    private fun visit071(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTHavingClause) = visit072(graph, graphVar,graphOverride,node.variable0!!)
    private fun visit070(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTClassOfExpressionAndVarOptional): Pair<AOPBase, AOPVariable> = visit033(graph, graphVar,graphOverride,node.variable0!!) to visit153(node.variable1!!.variable0!!)
    private fun visit069(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTListOfGroupCondition): List<Pair<AOPBase?, AOPVariable>> = node.value.map { visit168(graph, graphVar,graphOverride, it) }
    private fun visit068(node: ASTListOfVar): List<AOPVariable> = node.value.map { visit153(it) }
    private fun visit067(node: ASTListOfDataBlockValue): List<AOPConstant> = node.value.map { visit107(it) }
    private fun visit066(node: ASTListOfInterfaceOfListOfDataBlockValueOrNILParam): List<List<AOPConstant>> = node.value.map { visit104(it) }
    private fun visit065(node: ASTInlineDataOneVar) = LOPValues(query, listOf(visit153(node.variable0!!)), visit067(node.variable1!!).map { AOPValue(query, listOf(it)) })
    private fun visit064(node: ASTValuesClause): IOPBase = visit106(node.variable0!!)
    private fun visit063(node: ASTClear) = LOPGraphOperation(query, EGraphOperationTypeExt.CLEAR, node.SILENT, visit125(node.variable1!!))
    private fun visit062(node: ASTDrop) = LOPGraphOperation(query, EGraphOperationTypeExt.DROP, node.SILENT, visit125(node.variable1!!))
    private fun visit061(node: ASTCreate) = LOPGraphOperation(query, EGraphOperationTypeExt.CREATE, node.SILENT, visit125(node.variable1!!))
    private fun visit060(node: ASTAdd) = LOPGraphOperation(query, EGraphOperationTypeExt.ADD, node.SILENT, visit126(node.variable1!!), visit126(node.variable2!!))
    private fun visit059(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTClassOfVarOrTermAndPropertyListNotEmpty): List<LOPTriple> = visit050(blankNodeToVariable, graph, graphVar,graphOverride, visit115(blankNodeToVariable, node.variable0!!), node.variable1!!)
    private fun visit058(node: ASTValuesClause) = visit106(node.variable0!!)
    private fun visit057(node: ASTMove) = LOPGraphOperation(query, EGraphOperationTypeExt.MOVE, node.SILENT, visit126(node.variable1!!), visit126(node.variable2!!))
    private fun visit056(node: ASTCopy) = LOPGraphOperation(query, EGraphOperationTypeExt.COPY, node.SILENT, visit126(node.variable1!!), visit126(node.variable2!!))
    private fun visit055(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTObject): Pair<AOPBase, List<LOPTriple>> = visit120(blankNodeToVariable, graph, graphVar,graphOverride,node.variable0!!)
    private fun visit054(graph: String, graphVar: Boolean,graphOverride:Boolean,  node: ASTQuadPattern) = visit046(true, graph,graphVar,graphOverride, node.variable0!!)
    private fun visit053(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, subject: AOPBase, node: ASTClassOfVerbAndObjectList): List<LOPTriple> = visit112(blankNodeToVariable, graph, graphVar,graphOverride, subject, visit114(node.variable0!!), node.variable1!!)
    private fun visit052(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, subject: AOPBase, node: ASTClassOfVerbAndObjectListOptional): List<LOPTriple> = visit053(blankNodeToVariable, graph, graphVar,graphOverride, subject, node.variable0!!)
    private fun visit051(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, subject: AOPBase, node: ASTListOfClassOfVerbAndObjectListOptional): List<LOPTriple> = node.value.map { visit052(blankNodeToVariable, graph, graphVar,graphOverride, subject, it) }.flatten()
    private fun visit050(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, subject: AOPBase, node: ASTPropertyListNotEmpty): List<LOPTriple> = visit112(blankNodeToVariable, graph, graphVar,graphOverride, subject, visit114(node.variable0!!), node.variable1!!) + visit051(blankNodeToVariable, graph, graphVar,graphOverride, subject, node.variable2!!)
    private fun visit049(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTTriplesTemplate): List<LOPTriple> = visit111(blankNodeToVariable, graph, graphVar,graphOverride,node.variable0!!) + visit080(blankNodeToVariable, graph, graphVar,graphOverride,node.variable1!!)


    private fun visit048(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean,graphOverride:Boolean,node: ASTClassOfQuadsNotTriplesAndpointAndTriplesTemplateOptional) = visit110(blankNodeToVariable,graph,graphVar, graphOverride,node.variable0!!) + node.variable2!!.variable0?.let { visit049(blankNodeToVariable, graph, false, graphOverride,it) }.orEmpty()

    private fun visit047(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean,graphOverride:Boolean,  node: ASTListOfClassOfQuadsNotTriplesAndpointAndTriplesTemplateOptional): List<LOPTriple> = node.value.map { visit048(blankNodeToVariable, graph,graphVar, graphOverride,it) }.flatten()
    private fun visit046(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean,graphOverride:Boolean,  node: ASTQuads): List<LOPTriple> = visit079(blankNodeToVariable, graph, false,graphOverride, node.variable0!!) + visit047(blankNodeToVariable, graph,graphVar,graphOverride, node.variable1!!)
    private fun visit045(node: ASTQuadData): List<LOPTriple> = visit046(false, "", false,false,node.variable0!!)
    private fun visit044(node: ASTInsertData) = LOPModifyData(query, EModifyTypeExt.INSERT, visit045(node.variable0!!).toMutableList())
    private fun visit043(node: ASTDeleteData) = LOPModifyData(query, EModifyTypeExt.DELETE, visit045(node.variable0!!).toMutableList())
    private fun visit042(node: ASTString1) = node.STRING_LITERAL1!!.drop(1).dropLast(1)
    private fun visit041(node: ASTString2) = node.STRING_LITERAL2!!.drop(1).dropLast(1)
    private fun visit040(node: ASTString1long) = node.STRING_LITERAL_LONG1!!.drop(3).dropLast(3)
    private fun visit039(node: ASTString2long) = node.STRING_LITERAL_LONG2!!.drop(3).dropLast(3)
    private fun visit038(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTNumericExpression) = visit149(graph, graphVar,graphOverride,node.variable0!!)
    private fun visit037(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTClassOfExpressionAndListOfExpression) = listOf(visit033(graph, graphVar,graphOverride,node.variable0!!)) + node.variable1?.value.orEmpty().map { visit033(graph, graphVar,graphOverride, it) }
    private fun visit036(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTConditionalAndExpression) = (listOf(visit032(graph, graphVar,graphOverride,node.variable0!!)) + visit031(graph, graphVar,graphOverride,node.variable1!!)).reduce { s, t -> AOPAnd(query, s, t) }
    private fun visit035(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTConditionalOrExpression) = (listOf(visit036(graph, graphVar,graphOverride,node.variable0!!)) + visit030(graph, graphVar,graphOverride,node.variable1!!)).reduce { s, t -> AOPOr(query, s, t) }
    private fun visit034(graph: String, graphVar: Boolean, graphOverride:Boolean,node: ASTWhereClause) = visit026(true, graph, graphVar,graphOverride,node.variable1!!)
    private fun visit033(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTExpression): AOPBase = visit035(graph, graphVar,graphOverride,node.variable0!!)
    private fun visit032(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTValueLogical) = visit150(graph, graphVar,graphOverride,node.variable0!!)
    private fun visit031(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTListOfValueLogical) = node.value.map { visit032(graph, graphVar,graphOverride, it) }
    private fun visit030(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTListOfConditionalAndExpression) = node.value.map { visit036(graph, graphVar,graphOverride, it) }
    private fun visit029(node: ASTPrologue) = node.value.map { visit151(it) }
    private fun visit028(node: ASTBaseDecl) = prefix("", node.IRIREF!!)
    private fun visit027(node: ASTPrefixDecl) = prefix(node.PNAME_NS!!.dropLast(1), node.IRIREF!!)


    private fun visit026(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean,node: ASTGroupGraphPattern) = visit103(blankNodeToVariable, graph, graphVar,graphOverride,node.variable0!!)


    private fun visit026b(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean,node: ASTGroupGraphPattern, child: IOPBase) = visit103b(blankNodeToVariable, graph, graphVar,graphOverride, node.variable0!!, child)
    private fun visit025(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTClassOfVarOrTermAndPropertyListPathNotEmpty): List<LOPTriple> = visit022(blankNodeToVariable, graph, graphVar,graphOverride, visit115(blankNodeToVariable, node.variable0!!), node.variable1!!)
    private fun visit024(graph: String, graphVar: Boolean,graphOverride:Boolean,  node: ASTInsertClause) = visit054(graph, graphVar,graphOverride, node.variable0!!)
    private fun visit023(graph: String, graphVar: Boolean,graphOverride:Boolean,  node: ASTDeleteClause) = visit054(graph, graphVar,graphOverride, node.variable0!!)
    private fun visit022(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, subject: AOPBase, node: ASTPropertyListPathNotEmpty): List<LOPTriple> = visit093(blankNodeToVariable, graph, graphVar,graphOverride, subject, visit101(node.variable0!!), node.variable1!!) + visit019(blankNodeToVariable, graph, graphVar,graphOverride, subject, node.variable2!!)
    private fun visit021(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, subject: AOPBase, node: ASTPropertyList): List<LOPTriple> = visit050(blankNodeToVariable, graph, graphVar,graphOverride, subject, node.variable0!!)
    private fun visit020(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTTriplesBlock): List<LOPTriple> = visit102(blankNodeToVariable, graph, graphVar,graphOverride,node.variable0!!) + visit081(blankNodeToVariable, graph, graphVar,graphOverride,node.variable1!!)
    private fun visit019(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, subject: AOPBase, node: ASTListOfClassOfInterfaceOfVerbPathOrVerbSimpleAndObjectListOptional): List<LOPTriple> = node.value.map { visit018(blankNodeToVariable, graph, graphVar,graphOverride, subject, it.variable0!!) }.flatten()
    private fun visit018(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, subject: AOPBase, node: ASTClassOfInterfaceOfVerbPathOrVerbSimpleAndObjectList) = visit112(blankNodeToVariable, graph, graphVar,graphOverride, subject, visit101(node.variable0!!), node.variable1!!)
    private fun visit017(node: ASTVerbSimple) = visit153(node.variable0!!)
    private fun visit016(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTObjectPath): Pair<AOPBase, List<LOPTriple>> = visit092(blankNodeToVariable, graph, graphVar,graphOverride,node.variable0!!)
    private fun visit015(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean,graphOverride:Boolean, node: ASTOptionalGraphPattern, child: IOPBase) = LOPJoin(query, child, visit026(blankNodeToVariable, graph, graphVar,graphOverride, node.variable0!!), true)
    private fun visit014(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTGroupOrUnionGraphPattern, child: IOPBase) = LOPJoin(query, child, (listOf(visit026(blankNodeToVariable, graph, graphVar,graphOverride,node.variable0!!)) + node.variable1!!.value.map { visit026(blankNodeToVariable, graph, graphVar,graphOverride, it) }).reduce { s, t -> LOPUnion(query, s, t) }, false)

// private fun visit014(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTGroupOrUnionGraphPattern, child: IOPBase) =        (            listOf            (visit026b                (                    blankNodeToVariable, graph, graphVar,graphOverride,node.variable0!!,child)            ) + node.variable1!!.value.map            {visit026b                (                    blankNodeToVariable, graph, graphVar,graphOverride, it,child                )            }            ).reduce    {        s, t ->                LOPUnion        (            query, s, t        )    }
    private fun visit013(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTGroupOrUnionGraphPattern) = (listOf(visit026(blankNodeToVariable, graph, graphVar,graphOverride,node.variable0!!)) + node.variable1!!.value.map { visit026(blankNodeToVariable, graph, graphVar,graphOverride, it) }).reduce { s, t -> LOPUnion(query, s, t) }
    private fun visit012(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTMinusGraphPattern, child: IOPBase) = LOPMinus(query, child, visit026(blankNodeToVariable, graph, graphVar,graphOverride,node.variable0!!), mutableListOf())
    private fun visit011(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTFilter, child: IOPBase) = listOf(node) to child
    private fun visit010(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTBind, child: IOPBase) = createBind(visit153(node.variable1!!), visit033(graph, graphVar,graphOverride,node.variable0!!), child)
    private fun visit009(graph: String, graphVar: Boolean, graphOverride:Boolean, node: ASTServiceGraphPattern, child: IOPBase): IOPBase = TODO("service not implemented")
    private fun visit008(node: ASTDescribeQuery, valuesClause: ASTValuesClause?): IOPBase = TODO("describe query not implemented")
    private fun visit007(blankNodeToVariable: Boolean, graph: String, graphVar: Boolean, graphOverride:Boolean, subject: AOPBase, node: ASTPropertyListPath): List<LOPTriple> = visit022(blankNodeToVariable, graph, graphVar,graphOverride, subject, node.variable0!!)
    private fun visit006(node: ASTOffsetClauseOptional, tmp: IOPBase) = node.variable0?.let { visit004(it, tmp) } ?: tmp
    private fun visit005(node: ASTLimitClauseOptional, tmp: IOPBase) = node.variable0?.let { visit003(it, tmp) } ?: tmp
    private fun visit004(node: ASTOffsetClause, tmp: IOPBase) = LOPOffset(query, node.INTEGER!!.toInt(), tmp)
    private fun visit003(node: ASTLimitClause, tmp: IOPBase) = LOPLimit(query, node.INTEGER!!.toInt(), tmp)
    private fun visit002(node: ASTConstructTriples): List<LOPTriple> = visit111(true, "", false,false, node.variable0!!) + visit075(node.variable1!!)
    private fun visit001(node: ASTConstructTemplate): List<LOPTriple> = visit074(node.variable0!!)
}
