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
import lupos.operator.base.singleinput.LOPNOOP
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
import lupos.parser.sparql1_1.SparqlParser.*
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
    public val queryExecutionStartTime: ByteArrayWrapper = ByteArrayWrapper()
    private var counter = 0
    private val prefixMap = mutableMapOf("" to "")

    init {
        DictionaryHelper.dateTimeToByteArray(queryExecutionStartTime)
    }

    private fun initializeEnum(value: Int?): Int = value?.let { it } ?: -1
    private fun decodeIri(ns: String) = prefixMap[ns]!!
    private fun decodeIri(ns: String, p: String): String {
        val tmp = prefixMap[ns]
        if (tmp == null) {
            TODO("prefix '$ns' not registered - instead there are ${prefixMap.toList()}")
        }
        return tmp + p
    }

    private fun prefix(k: String, v: String) {
        prefixMap[k] = v.drop(1).dropLast(1)
    }

    private fun visit(node: ASTListOfInterfaceOfVarOrClassOfExpressionAndVar): MutableList<Pair<AOPBase?, String>> {
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
                is ASTClassOfExpressionAndVar -> extractName(visit("", false, v.variable0!!), v.variable1!!)
            }
        }
        return names
    }

    private fun visit(node: ASTSelectClause): Pair<Int, List<Pair<AOPBase?, String>>?> = initializeEnum(node.variable0) to when (val variables = node.variable1!!) {
        is ASTListOfInterfaceOfVarOrClassOfExpressionAndVar -> visit(variables)
        is ASTSelectClauseAll -> null
    }

    private fun visit(v0: ASTPrologue, v1: ASTClassOfInterfaceOfSelectQueryOrConstructQueryOrDescribeQueryOrAskQueryAndValuesClauseOptional): OPBaseCompound {
        visit(v0)
        val variableOrdering = mutableListOf<List<String>>()
        val classOfInterfaceOfSelectQueryOrConstructQueryOrDescribeQueryOrAskQueryAndValuesClauseOptional = v1.variable0!!
        val valuesClauseOptional = v1.variable1
        val valuesClause = valuesClauseOptional!!.variable0
        var child = when (classOfInterfaceOfSelectQueryOrConstructQueryOrDescribeQueryOrAskQueryAndValuesClauseOptional) {
            is ASTSelectQuery -> {
                val tmp = visit(classOfInterfaceOfSelectQueryOrConstructQueryOrDescribeQueryOrAskQueryAndValuesClauseOptional.variable0!!).second
                if (tmp != null) {
                    variableOrdering.add(tmp.map { it.second })
                }
                visit(classOfInterfaceOfSelectQueryOrConstructQueryOrDescribeQueryOrAskQueryAndValuesClauseOptional, valuesClause)
            }
            is ASTConstructQuery -> visit(classOfInterfaceOfSelectQueryOrConstructQueryOrDescribeQueryOrAskQueryAndValuesClauseOptional, valuesClause)
            is ASTDescribeQuery -> visit(classOfInterfaceOfSelectQueryOrConstructQueryOrDescribeQueryOrAskQueryAndValuesClauseOptional, valuesClause)
            is ASTAskQuery -> visit(classOfInterfaceOfSelectQueryOrConstructQueryOrDescribeQueryOrAskQueryAndValuesClauseOptional, valuesClause)
        }
if(variableOrdering.size==0){
variableOrdering.add(child.getProvidedVariableNames().filter{!it.contains("#")})
}
        return OPBaseCompound(query, arrayOf(child), variableOrdering)
    }

    private fun visit(v0: ASTPrologue, v1: ASTClassOfUpdate1AndClassOfPrologueAndUpdateOptional): OPBaseCompound {
        val prolog = visit(v0).toMutableList()
        val childs = mutableListOf<IOPBase>()
        childs.add(visit(v1.variable0!!))
        var v2: ASTClassOfPrologueAndUpdateOptional = v1.variable1!!
        while (v2.variable0 != null) {
            val v3: ASTClassOfPrologueAndUpdate = v2.variable0!!
            prolog.addAll(visit(v3.variable0!!))
            val v4: ASTUpdate = v3.variable1!!
            val v5: ASTClassOfUpdate1AndClassOfPrologueAndUpdateOptional = v4.variable0!!
            childs.add(visit(v5.variable0!!))
            v2 = v5.variable1!!
        }
        return OPBaseCompound(query, childs.toTypedArray(), listOf())
    }

    public fun visit(node: ASTSparqlDoc): OPBaseCompound {
        println("inputAST " + node)
        val res = when (val v1 = node.variable1!!) {
            is ASTClassOfInterfaceOfSelectQueryOrConstructQueryOrDescribeQueryOrAskQueryAndValuesClauseOptional -> visit(node.variable0!!, v1)
            is ASTClassOfUpdate1AndClassOfPrologueAndUpdateOptionalOptional -> visit(node.variable0!!, v1)
        }
        println(res)
        return res
    }

    private fun visit(graph: String, graphVar: Boolean, node: ASTGroupCondition): Pair<AOPBase?, AOPVariable> = when (node) {
        is ASTFunctionCall -> visit(graph, graphVar, node) to AOPVariable(query, "_ASTGroupCondition#${counter++}")
        is ASTVar -> null to visit(node)
        is ASTBuiltInCall -> visit(graph, graphVar, node) to AOPVariable(query, "_ASTGroupCondition#${counter++}")
        is ASTClassOfExpressionAndVarOptional -> visit(graph, graphVar, node)
    }

    private fun visit(node: ASTSelectQuery, valuesClause: ASTValuesClause?): IOPBase {
        val nodeSelectClause = node.variable0
        val nodeListOfDatasetClause = node.variable1!!
        val nodeWhereClause = node.variable2
        val nodeSolutionModifier = node.variable3
        if (nodeListOfDatasetClause.value.size > 0) {
            TODO("datasets not supported")
        }
        val whereClause = visit("", false, nodeWhereClause!!)
        var res = whereClause
        if (valuesClause != null) {
            res = LOPJoin(query, res, visit(valuesClause), false)
        }
        val (duplicateModifier, selectClause) = visit(nodeSelectClause!!)
        res = when (duplicateModifier) {
            ASTEnumOfDISTINCTAndREDUCED._UNDEFINED -> {
                res
            }
            ASTEnumOfDISTINCTAndREDUCED.DISTINCT -> LOPDistinct(query, res)
            ASTEnumOfDISTINCTAndREDUCED.REDUCED -> LOPReduced(query, res)
            else -> TODO("invalid value")
        }
        val solutionModifier = nodeSolutionModifier!!
        val groupClause = solutionModifier.variable0!!.variable0
        val havingClause = solutionModifier.variable1!!.variable0
        if (groupClause != null) {
            val bindings = selectClause.orEmpty().filter { it.first != null }.map { it.second to it.first!! }
            val groupingBy = visit("", false, groupClause.variable0!!)
            for (f in groupingBy.filter { it.first != null }) {
                res = LOPBind(query, f.second, f.first!!, res)
            }
            val bindingsForHaving: List<Pair<String, AOPBase>> = havingClause?.let { visit("", false, it).map { it2 -> "_ASTSelectQuery#${counter++}" to it2 } }.orEmpty()
            res = LOPGroup(query, groupingBy.map { it.second }, bindings + bindingsForHaving, res)
            for (x in bindingsForHaving) {
                res = LOPFilter(query, x.second, res)
            }
        } else if (havingClause != null) {
            val bindings = selectClause.orEmpty().filter { it.first != null }.map { it.second to it.first!! }
            val bindingsForHaving: List<Pair<String, AOPBase>> = visit("", false, havingClause).map { "_ASTSelectQuery#${counter++}" to it }
            res = LOPGroup(query, listOf(), bindings + bindingsForHaving, res)
            for (x in bindingsForHaving) {
                res = LOPFilter(query, x.second, res)
            }
        } else if (selectClause != null) {
            if (selectClause.map { it.first }.filterNotNull().map { it!!.containsAggregate() }.fold(false) { s, t -> s || t }) {
                val bindings = selectClause.orEmpty().filter { it.first != null }.map { it.second to it.first!! }
                res = LOPGroup(query, listOf(), bindings, res)
            } else {
                for (b in selectClause.filter { it.first != null }) {
                    res = LOPBind(query, AOPVariable(query, b.second), b.first!!, res)
                }
            }
        }
        val orderClause = solutionModifier.variable2!!.variable0
        if (orderClause != null) {
            res = visit("", false, orderClause, res)
        }
        val limitOffsetClause = solutionModifier.variable3!!.variable0
        if (limitOffsetClause != null) {
            res = visit(limitOffsetClause, res)
        }
        if (selectClause != null) {
            res = LOPProjection(query, selectClause.map { AOPVariable(query, it.second) }.toMutableList(), res)
        }
        return res
    }

    private fun visit(graph: String, graphVar: Boolean, node: ASTSubSelect): IOPBase {
        val nodeSelectClause = node.variable0
        val nodeWhereClause = node.variable1
        val nodeSolutionModifier = node.variable2
        val valuesClause = node.variable3!!.variable0
        val whereClause = visit(graph, graphVar, nodeWhereClause!!)
        var res = whereClause
        if (valuesClause != null) {
            res = LOPJoin(query, res, visit(valuesClause), false)
        }
        val (duplicateModifier, selectClause) = visit(nodeSelectClause!!)
        val unused = when (duplicateModifier) {
            ASTEnumOfDISTINCTAndREDUCED.DISTINCT -> res = LOPDistinct(query, res)
            ASTEnumOfDISTINCTAndREDUCED.REDUCED -> res = LOPReduced(query, res)
            else -> TODO("invalid value")
        }
        val solutionModifier = nodeSolutionModifier!!
        val groupClause = solutionModifier.variable0!!.variable0
        val havingClause = solutionModifier.variable1!!.variable0
        if (groupClause != null) {
            val bindings = selectClause.orEmpty().filter { it.first != null }.map { it.second to it.first!! }
            val groupingBy = visit(graph, graphVar, groupClause.variable0!!)
            for (f in groupingBy.filter { it.first != null }) {
                res = LOPBind(query, f.second, f.first!!, res)
            }
            val bindingsForHaving: List<Pair<String, AOPBase>> = havingClause?.let { visit(graph, graphVar, it).map { it2 -> "_ASTSelectQuery#${counter++}" to it2 } }.orEmpty()
            res = LOPGroup(query, groupingBy.map { it.second }, bindings + bindingsForHaving, res)
            for (x in bindingsForHaving) {
                res = LOPFilter(query, x.second, res)
            }
        } else if (havingClause != null) {
            val bindings = selectClause.orEmpty().filter { it.first != null }.map { it.second to it.first!! }
            val bindingsForHaving: List<Pair<String, AOPBase>> = visit(graph, graphVar, havingClause).map { "_ASTSelectQuery#${counter++}" to it }
            res = LOPGroup(query, listOf(), bindings + bindingsForHaving, res)
            for (x in bindingsForHaving) {
                res = LOPFilter(query, x.second, res)
            }
        } else if (selectClause != null) {
            if (selectClause.map { it.first }.filterNotNull().map { it!!.containsAggregate() }.fold(false) { s, t -> s || t }) {
                val bindings = selectClause.orEmpty().filter { it.first != null }.map { it.second to it.first!! }
                res = LOPGroup(query, listOf(), bindings, res)
            } else {
                for (b in selectClause.filter { it.first != null }) {
                    res = LOPBind(query, AOPVariable(query, b.second), b.first!!, res)
                }
            }
        }
        val orderClause = solutionModifier.variable2!!.variable0
        if (orderClause != null) {
            res = visit(graph, graphVar, orderClause, res)
        }
        val limitOffsetClause = solutionModifier.variable3!!.variable0
        if (limitOffsetClause != null) {
            res = visit(limitOffsetClause, res)
        }
        if (selectClause != null) {
            res = LOPProjection(query, selectClause.map { AOPVariable(query, it.second) }.toMutableList(), res)
        }
        return res
    }

    private fun visit(node: ASTAskQuery, valuesClause: ASTValuesClause?): LOPMakeBooleanResult {
        val nodeListOfDatasetClause = node.variable0!!
        val nodeWhereClause = node.variable1
        val nodeSolutionModifier = node.variable2
        if (nodeListOfDatasetClause.value.size > 0) {
            TODO("datasets not supported")
        }
        val whereClause = visit("", false, nodeWhereClause!!)
        var res = whereClause
        if (valuesClause != null) {
            res = LOPJoin(query, res, visit(valuesClause), false)
        }
        val solutionModifier = nodeSolutionModifier!!
        val groupClause = solutionModifier.variable0!!.variable0
        val havingClause = solutionModifier.variable1!!.variable0
        if (groupClause != null) {
            val groupingBy = visit("", false, groupClause.variable0!!)
            for (f in groupingBy.filter { it.first != null }) {
                res = LOPBind(query, f.second, f.first!!, res)
            }
            val bindingsForHaving: List<Pair<String, AOPBase>> = havingClause?.let { visit("", false, it).map { it2 -> "_ASTSelectQuery#${counter++}" to it2 } }.orEmpty()
            res = LOPGroup(query, groupingBy.map { it.second }, bindingsForHaving, res)
            for (x in bindingsForHaving) {
                res = LOPFilter(query, x.second, res)
            }
        } else if (havingClause != null) {
            val bindingsForHaving: List<Pair<String, AOPBase>> = visit("", false, havingClause).map { "_ASTSelectQuery#${counter++}" to it }
            res = LOPGroup(query, listOf(), bindingsForHaving, res)
            for (x in bindingsForHaving) {
                res = LOPFilter(query, x.second, res)
            }
        }
        val orderClause = solutionModifier.variable2!!.variable0
        if (orderClause != null) {
            res = visit("", false, orderClause, res)
        }
        val limitOffsetClause = solutionModifier.variable3!!.variable0
        if (limitOffsetClause != null) {
            res = visit(limitOffsetClause, res)
        }
        return LOPMakeBooleanResult(query, res)
    }

    private fun visit(graph: String, graphVar: Boolean, node: ASTOrderClause, tmp: IOPBase): IOPBase {
        var res = tmp
        val names = mutableListOf<Pair<Boolean, String>>()
        for (v in node.variable0!!.value) {
            val asc = initializeEnum(v.variable0) != ASTEnumOfASCAndDESC.DESC
            val tt = visit(graph, graphVar, v.variable1!!)
            if (tt is AOPVariable) {
                names.add(0,asc to tt.name)
            } else {
                val name = "#sort_${counter++}"
                names.add(0,asc to name)
                res = LOPBind(query, AOPVariable(query, name), tt, res)
            }
        }
        for (x in names) {
            res = LOPSort(query, x.first, AOPVariable(query, x.second), res)
        }
        return res
    }

    private fun visit(graph: String, graphVar: Boolean, node: ASTClassOfDISTINCTAndExpressionAndListOfExpression): Pair<Boolean, List<AOPBase>> {
        val res = mutableListOf<AOPBase>()
        res.add(visit(graph, graphVar, node.variable1!!))
        val v2 = node.variable2
        if (v2 != null) {
            for (v in v2.value) {
                res.add(visit(graph, graphVar, v))
            }
        }
        return node.DISTINCT to res
    }

    private fun visit(graph: String, graphVar: Boolean, node: ASTArgList) = when (node) {
        is ASTClassOfDISTINCTAndExpressionAndListOfExpression -> visit(graph, graphVar, node)
        is ASTNILParam -> false to listOf()
    }

    private fun visit(graph: String, graphVar: Boolean, node: ASTFunctionCall) = when (val iri = visit(node.variable0!!)) {
        "http://www.w3.org/2001/XMLSchema#double" -> {
            val t = visit(graph, graphVar, node.variable1!!)
            if (t.second.size != 1) {
                TODO("<http://www.w3.org/2001/XMLSchema#double> requires exactly 1 parameter")
            }
            AOPFunctionCallDouble(query, t.second[0])
        }
        "http://www.w3.org/2001/XMLSchema#float" -> {
            val t = visit(graph, graphVar, node.variable1!!)
            if (t.second.size != 1) {
                TODO("<http://www.w3.org/2001/XMLSchema#float> requires exactly 1 parameter")
            }
            AOPFunctionCallFloat(query, t.second[0])
        }
        "http://www.w3.org/2001/XMLSchema#string" -> {
            val t = visit(graph, graphVar, node.variable1!!)
            if (t.second.size != 1) {
                TODO("<http://www.w3.org/2001/XMLSchema#string> requires exactly 1 parameter")
            }
            AOPFunctionCallString(query, t.second[0])
        }
        else -> {
            TODO("ASTFunctionCall $iri $node")
        }
    }

    private fun visit(graph: String, graphVar: Boolean, node: ASTExpressionList) = when (node) {
        is ASTClassOfExpressionAndListOfExpression -> visit(graph, graphVar, node)
        is ASTNILParam -> listOf()
    }

    private fun visit(graph: String, graphVar: Boolean, node: ASTBuiltInCall) = when (node) {
        is ASTBuiltInCallSameTerm -> TODO("not implemented")
        is ASTBuiltInCallIsBlanc -> TODO("not implemented")
        is ASTBuiltInCallSHA384 -> TODO("not implemented")
        is ASTBuiltInCallSHA512 -> TODO("not implemented")
        is ASTRegexExpression -> TODO("not implemented")
        is ASTSubstringExpression -> TODO("not implemented")
        is ASTStrReplaceExpression -> TODO("not implemented")
        is ASTBuiltInCallRand -> TODO("not implemented")
        is ASTBuiltInCallIsUri -> TODO("not implemented")
        is ASTBuiltInCallEncodeForUri -> TODO("not implemented")
        is ASTBuiltInCallYear -> AOPBuildInCallYEAR(query, visit(graph, graphVar, node.variable0!!))
        is ASTBuiltInCallMonth -> AOPBuildInCallMONTH(query, visit(graph, graphVar, node.variable0!!))
        is ASTBuiltInCallHours -> AOPBuildInCallHOURS(query, visit(graph, graphVar, node.variable0!!))
        is ASTBuiltInCallNow -> AOPConstant(query, queryExecutionStartTime)
        is ASTBuiltInCallIsIri -> AOPBuildInCallIRI(query, visit(graph, graphVar, node.variable0!!), "")
        is ASTBuiltInCallIsLiteral -> AOPBuildInCallIsLITERAL(query, visit(graph, graphVar, node.variable0!!))
        is ASTBuiltInCallMD5 -> AOPBuildInCallMD5(query, visit(graph, graphVar, node.variable0!!))
        is ASTBuiltInCallUUID -> AOPBuildInCallUUID(query)
        is ASTBuiltInCallSTRUUID -> AOPBuildInCallSTRUUID(query)
        is ASTBuiltInCallSHA1 -> AOPBuildInCallSHA1(query, visit(graph, graphVar, node.variable0!!))
        is ASTBuiltInCallCoalesce -> AOPBuildInCallCOALESCE(query, visit(graph, graphVar, node.variable0!!))
        is ASTBuiltInCallIf -> AOPBuildInCallIF(query, visit(graph, graphVar, node.variable0!!), visit(graph, graphVar, node.variable1!!), visit(graph, graphVar, node.variable2!!))
        is ASTBuiltInCallStrLang -> AOPBuildInCallSTRLANG(query, visit(graph, graphVar, node.variable0!!), visit(graph, graphVar, node.variable1!!))
        is ASTBuiltInCallMinutes -> AOPBuildInCallMINUTES(query, visit(graph, graphVar, node.variable0!!))
        is ASTBuiltInCallSHA256 -> AOPBuildInCallSHA256(query, visit(graph, graphVar, node.variable0!!))
        is ASTBuiltInCallStrDt -> AOPBuildInCallSTRDT(query, visit(graph, graphVar, node.variable0!!), visit(graph, graphVar, node.variable1!!))
        is ASTBuiltInCallIsNumeric -> AOPBuildInCallIsNUMERIC(query, visit(graph, graphVar, node.variable0!!))
        is ASTBuiltInCallSeconds -> AOPBuildInCallSECONDS(query, visit(graph, graphVar, node.variable0!!))
        is ASTBuiltInCallTimezone -> AOPBuildInCallTIMEZONE(query, visit(graph, graphVar, node.variable0!!))
        is ASTBuiltInCallTz -> AOPBuildInCallTZ(query, visit(graph, graphVar, node.variable0!!))
        is ASTBuiltInCallStrLen -> AOPBuildInCallSTRLEN(query, visit(graph, graphVar, node.variable0!!))
        is ASTBuiltInCallUCase -> AOPBuildInCallUCASE(query, visit(graph, graphVar, node.variable0!!))
        is ASTBuiltInCallStrBefore -> AOPBuildInCallSTRBEFORE(query, visit(graph, graphVar, node.variable0!!), visit(graph, graphVar, node.variable1!!))
        is ASTBuiltInCallStrAfter -> AOPBuildInCallSTRAFTER(query, visit(graph, graphVar, node.variable0!!), visit(graph, graphVar, node.variable1!!))
        is ASTBuiltInCallContains -> AOPBuildInCallCONTAINS(query, visit(graph, graphVar, node.variable0!!), visit(graph, graphVar, node.variable1!!))
        is ASTBuiltInCallStrStarts -> AOPBuildInCallSTRSTARTS(query, visit(graph, graphVar, node.variable0!!), visit(graph, graphVar, node.variable1!!))
        is ASTBuiltInCallStrEnds -> AOPBuildInCallSTRENDS(query, visit(graph, graphVar, node.variable0!!), visit(graph, graphVar, node.variable1!!))
        is ASTBuiltInCallDay -> AOPBuildInCallDAY(query, visit(graph, graphVar, node.variable0!!))
        is ASTBuiltInCallStr -> AOPBuildInCallSTR(query, visit(graph, graphVar, node.variable0!!))
        is ASTBuiltInCallLang -> AOPBuildInCallLANG(query, visit(graph, graphVar, node.variable0!!))
        is ASTBuildInCallLangMatches -> AOPBuildInCallLANGMATCHES(query, visit(graph, graphVar, node.variable0!!), visit(graph, graphVar, node.variable1!!))
        is ASTBuiltInCallDataType -> AOPBuildInCallDATATYPE(query, visit(graph, graphVar, node.variable0!!))
        is ASTBuiltInCallBound -> AOPBuildInCallBOUND(query, visit(node.variable0!!))
        is ASTBuiltInCallIri -> AOPBuildInCallIRI(query, visit(graph, graphVar, node.variable0!!), "")
        is ASTBuiltInCallUri -> AOPBuildInCallURI(query, visit(graph, graphVar, node.variable0!!), "")
        is ASTBuiltInCallAbs -> AOPBuildInCallABS(query, visit(graph, graphVar, node.variable0!!))
        is ASTBuiltInCallCeil -> AOPBuildInCallCEIL(query, visit(graph, graphVar, node.variable0!!))
        is ASTBuiltInCallLCase -> AOPBuildInCallLCASE(query, visit(graph, graphVar, node.variable0!!))
        is ASTBuiltInCallFloor -> AOPBuildInCallFLOOR(query, visit(graph, graphVar, node.variable0!!))
        is ASTBuiltInCallRound -> AOPBuildInCallROUND(query, visit(graph, graphVar, node.variable0!!))
        is ASTExistsFunc -> AOPBuildInCallExists(query, visit(true,graph, graphVar, node.variable0!!))
        is ASTNotExistsFunc -> AOPBuildInCallNotExists(query, visit(true,graph, graphVar, node.variable0!!))
        is ASTAggregate -> visit(graph, graphVar, node)
        is ASTBuiltInCallConcat -> visit(graph, graphVar, node.variable0!!).reduceRight { s, t -> AOPBuildInCallCONCAT(query, s, t) }
        is ASTBuiltInCallBNode -> when (val v0 = node.variable0!!) {
            is ASTExpression -> AOPBuildInCallBNODE1(query, visit(graph, graphVar, v0))
            is ASTNILParam -> AOPBuildInCallBNODE0(query)
        }
    }

    private fun visit(graph: String, graphVar: Boolean, node: ASTAggregate) = when (node) {
        is ASTAggregateAvg -> AOPAggregationAVG(query, node.DISTINCT, visit(graph, graphVar, node.variable1!!))
        is ASTAggregateCount -> AOPAggregationCOUNT(query, node.DISTINCT, visit(graph, graphVar, node.variable1!!))
        is ASTAggregateSum -> AOPAggregationSUM(query, node.DISTINCT, visit(graph, graphVar, node.variable1!!))
        is ASTAggregateMin -> AOPAggregationMIN(query, node.DISTINCT, visit(graph, graphVar, node.variable1!!))
        is ASTAggregateMax -> AOPAggregationMAX(query, node.DISTINCT, visit(graph, graphVar, node.variable1!!))
        is ASTAggregateSample -> AOPAggregationSAMPLE(query, node.DISTINCT, visit(graph, graphVar, node.variable1!!))
        is ASTAggregateGroupConcat -> TODO("not implemented")
    }

    private fun visit(graph: String, graphVar: Boolean, node: ASTInterfaceOfAggregateCountAllOrExpression) = when (node) {
        is ASTAggregateCountAll -> null
        is ASTExpression -> visit(graph, graphVar, node)
    }

    private fun visit(graph: String, graphVar: Boolean, node: ASTConstraint) = when (node) {
        is ASTFunctionCall -> visit(graph, graphVar, node)
        is ASTBrackettedExpression -> visit(graph, graphVar, node.variable0!!)
        is ASTBuiltInCall -> visit(graph, graphVar, node)
    }

    private fun visit(graph: String, graphVar: Boolean, node: ASTInterfaceOfConstraintOrVar) = when (node) {
        is ASTConstraint -> visit(graph, graphVar, node)
        is ASTVar -> visit(node)
    }

    private fun visit(node: ASTVar) = when (node) {
        is ASTVar1 -> AOPVariable(query, node.VAR1!!.drop(1))
        is ASTVar2 -> AOPVariable(query, node.VAR2!!.drop(1))
    }

    private fun visit(node: ASTLimitOffsetClauses, tmp: IOPBase) = when (node) {
        is ASTClassOfLimitClauseAndOffsetClauseOptional -> visit(node.variable1!!, visit(node.variable0!!, tmp))
        is ASTClassOfOffsetClauseAndLimitClauseOptional -> visit(node.variable1!!, visit(node.variable0!!, tmp))
    }

    private fun visit(node: ASTInterfaceOfBaseDeclOrPrefixDecl) = when (node) {
        is ASTBaseDecl -> visit(node)
        is ASTPrefixDecl -> visit(node)
    }

    private fun visit(graph: String, graphVar: Boolean, node: ASTRelationalExpression) = when (val v1 = node.variable1!!.variable0) {
        is ASTRelationalExpressionEQ -> AOPEQ(query, visit(graph, graphVar, node.variable0!!), visit(graph, graphVar, v1.variable0!!))
        is ASTRelationalExpressionNEQ -> AOPNEQ(query, visit(graph, graphVar, node.variable0!!), visit(graph, graphVar, v1.variable0!!))
        is ASTRelationalExpressionLT -> AOPLT(query, visit(graph, graphVar, node.variable0!!), visit(graph, graphVar, v1.variable0!!))
        is ASTRelationalExpressionGT -> AOPGT(query, visit(graph, graphVar, node.variable0!!), visit(graph, graphVar, v1.variable0!!))
        is ASTRelationalExpressionLEQ -> AOPLEQ(query, visit(graph, graphVar, node.variable0!!), visit(graph, graphVar, v1.variable0!!))
        is ASTRelationalExpressionGEQ -> AOPGEQ(query, visit(graph, graphVar, node.variable0!!), visit(graph, graphVar, v1.variable0!!))
        is ASTRelationalExpressionIN -> AOPIn(query, visit(graph, graphVar, node.variable0!!), AOPSet(query, visit(graph, graphVar, v1.variable0!!)))
        is ASTRelationalExpressionNOTIN -> AOPNotIn(query, visit(graph, graphVar, node.variable0!!), AOPSet(query, visit(graph, graphVar, v1.variable0!!)))
        null -> visit(graph, graphVar, node.variable0!!)
    }

    private fun visit(graph: String, graphVar: Boolean, node: ASTAdditiveExpression) = node.variable1!!.value.fold(visit(graph, graphVar, node.variable0!!)) { res, v ->
        when (v) {
            is ASTAdditiveExpressionPLUS -> AOPAddition(query, res, visit(graph, graphVar, v.variable0!!))
            is ASTAdditiveExpressionMINUS -> AOPSubtraction(query, res, visit(graph, graphVar, v.variable0!!))
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
                        is ASTAdditiveExpressionMULTIPLY -> AOPMultiplication(query, s, visit(graph, graphVar, t.variable0!!))
                        is ASTAdditiveExpressionDIVIDE -> AOPDivision(query, s, visit(graph, graphVar, t.variable0!!))
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

    private fun visit(node: ASTNumericLiteralUnsigned) = when (node) {
        is ASTNumericLiteralUnsignedInteger -> visit(node)
        is ASTNumericLiteralUnsignedDecimal -> visit(node)
        is ASTNumericLiteralUnsignedDouble -> visit(node)
    }

    private fun visit(node: ASTNumericLiteralPositive) = when (node) {
        is ASTNumericLiteralPositiveInteger -> visit(node)
        is ASTNumericLiteralPositiveDecimal -> visit(node)
        is ASTNumericLiteralPositiveDouble -> visit(node)
    }

    private fun visit(node: ASTNumericLiteralNegative) = when (node) {
        is ASTNumericLiteralNegativeInteger -> visit(node)
        is ASTNumericLiteralNegativeDecimal -> visit(node)
        is ASTNumericLiteralNegativeDouble -> visit(node)
    }

    private fun visit(node: ASTNumericLiteralUnsignedInteger): AOPConstant {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.integerToByteArray(buffer, node.INTEGER!!)
        return AOPConstant(query, buffer)
    }

    private fun visit(node: ASTNumericLiteralUnsignedDecimal): AOPConstant {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.decimalToByteArray(buffer, node.DECIMAL!!)
        return AOPConstant(query, buffer)
    }

    private fun visit(node: ASTNumericLiteralUnsignedDouble): AOPConstant {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.doubleToByteArray(buffer, node.DOUBLE!!)
        return AOPConstant(query, buffer)
    }

    private fun visit(node: ASTNumericLiteralPositiveInteger): AOPConstant {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.integerToByteArray(buffer, node.INTEGER_POSITIVE!!)
        return AOPConstant(query, buffer)
    }

    private fun visit(node: ASTNumericLiteralPositiveDecimal): AOPConstant {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.decimalToByteArray(buffer, node.DECIMAL_POSITIVE!!)
        return AOPConstant(query, buffer)
    }

    private fun visit(node: ASTNumericLiteralPositiveDouble): AOPConstant {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.doubleToByteArray(buffer, node.DOUBLE_POSITIVE!!)
        return AOPConstant(query, buffer)
    }

    private fun visit(node: ASTNumericLiteralNegativeInteger): AOPConstant {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.integerToByteArray(buffer, node.INTEGER_NEGATIVE!!)
        return AOPConstant(query, buffer)
    }

    private fun visit(node: ASTNumericLiteralNegativeDecimal): AOPConstant {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.decimalToByteArray(buffer, node.DECIMAL_NEGATIVE!!)
        return AOPConstant(query, buffer)
    }

    private fun visit(node: ASTNumericLiteralNegativeDouble): AOPConstant {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.doubleToByteArray(buffer, node.DOUBLE_NEGATIVE!!)
        return AOPConstant(query, buffer)
    }

    private fun visit(graph: String, graphVar: Boolean, node: ASTMultiplicativeExpression) = node.variable1!!.value.fold(visit(graph, graphVar, node.variable0!!)) { s, t ->
        when (t) {
            is ASTMultiplicativeExpressionMULTIPLY -> AOPMultiplication(query, s, visit(graph, graphVar, t.variable0!!))
            is ASTMultiplicativeExpressionDIVIDE -> AOPDivision(query, s, visit(graph, graphVar, t.variable0!!))
        }
    }

    private fun visit(graph: String, graphVar: Boolean, node: ASTUnaryExpression) = when (node) {
        is ASTUnaryExpressionNOT -> AOPNot(query, visit(graph, graphVar, node.variable0!!))
        is ASTUnaryExpressionPLUS -> visit(graph, graphVar, node.variable0!!)
        is ASTUnaryExpressionMINUS -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.integerToByteArray(buffer, "0")
            AOPSubtraction(query, AOPConstant(query, buffer), visit(graph, graphVar, node.variable0!!))
        }
        is ASTPrimaryExpression -> visit(graph, graphVar, node)
    }

    private fun visit(graph: String, graphVar: Boolean, node: ASTPrimaryExpression) = when (node) {
        is ASTBrackettedExpression -> visit(graph, graphVar, node.variable0!!)
        is ASTBuiltInCall -> visit(graph, graphVar, node)
        is ASTiriOrFunction -> visit(graph, graphVar, node)
        is ASTRDFLiteral -> visit(node)
        is ASTNumericLiteral -> visit(node)
        is ASTBooleanLiteral -> visit(node)
        is ASTVar -> visit(node)
    }

    private fun visit(graph: String, graphVar: Boolean, node: ASTiriOrFunction) = if (node.variable1!!.variable0 != null) {
        val f = ASTFunctionCall()
        f.variable0 = node.variable0!!
        f.variable1 = node.variable1!!.variable0!!
        visit(graph, graphVar, f)
    } else {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.iriToByteArray(buffer, visit(node.variable0!!))
        AOPConstant(query, buffer)
    }

    private fun visit(node: ASTRDFLiteral) = when (val v1 = node.variable1!!.variable0) {
        null -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.stringToByteArray(buffer, visit(node.variable0!!))
            AOPConstant(query, buffer)
        }
        is ASTiri -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.typedToByteArray(buffer, visit(node.variable0!!), visit(v1))
            AOPConstant(query, buffer)
        }
        is ASTRDFLiteralLang -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.langToByteArray(buffer, visit(node.variable0!!), v1.LANGTAG!!.drop(1))
            AOPConstant(query, buffer)
        }
    }

    private fun visit(node: ASTiri): String = when (node) {
        is ASTiriRef -> node.IRIREF!!.drop(1).dropLast(1)
        is ASTPrefixedNameLN -> {
            val x = node.PNAME_LN!!.split(":")
            decodeIri(x[0], x[1])
        }
        is ASTPrefixedNameNS -> decodeIri(node.PNAME_NS!!.dropLast(1))
    }

    private fun visit(node: ASTString) = when (node) {
        is ASTString1 -> visit(node)
        is ASTString2 -> visit(node)
        is ASTString1long -> visit(node)
        is ASTString2long -> visit(node)
    }

    private fun visit(node: ASTBooleanLiteral) = when (node) {
        is ASTBooleanLiteralTrue -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.booleanToByteArray(buffer, true)
            AOPConstant(query, buffer)
        }
        is ASTBooleanLiteralFalse -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.booleanToByteArray(buffer, true)
            AOPConstant(query, buffer)
        }
    }

    private fun visit(node: ASTNumericLiteral) = when (node) {
        is ASTNumericLiteralUnsigned -> visit(node)
        is ASTNumericLiteralPositive -> visit(node)
        is ASTNumericLiteralNegative -> visit(node)
    }

    private fun visit(node: ASTLoad): LOPGraphOperation {
        val into = node.variable2!!.variable0
        return if (into != null) {
            LOPGraphOperation(query, EGraphOperationTypeExt.LOAD, node.SILENT, EGraphRefTypeExt.DefaultGraphRef, visit(node.variable1!!), EGraphRefTypeExt.IriGraphRef, visit(into.variable0!!))
        } else {
            LOPGraphOperation(query, EGraphOperationTypeExt.LOAD, node.SILENT, EGraphRefTypeExt.DefaultGraphRef, visit(node.variable1!!), EGraphRefTypeExt.DefaultGraphRef, TripleStoreManager.DEFAULT_GRAPH_NAME)
        }
    }

    private fun visit(node: ASTGraphOrDefault) = when (node) {
        is ASTClassOfGRAPHAndiri -> EGraphRefTypeExt.IriGraphRef to visit(node.variable1!!)
        is ASTGraphRefDefault -> EGraphRefTypeExt.DefaultGraphRef to null
    }

    private fun visit(node: ASTGraphRefAll) = when (node) {
        is ASTGraphRef -> EGraphRefTypeExt.IriGraphRef to visit(node.variable0!!)
        is ASTGraphRefDefault -> EGraphRefTypeExt.DefaultGraphRef to null
        is ASTGraphRefNamed -> EGraphRefTypeExt.NamedGraphRef to null
        is ASTGraphRefAll2 -> EGraphRefTypeExt.AllGraphRef to null
    }

    private fun visit(node: ASTUpdate1) = when (node) {
        is ASTLoad -> visit(node)
        is ASTClear -> visit(node)
        is ASTDrop -> visit(node)
        is ASTCreate -> visit(node)
        is ASTAdd -> visit(node)
        is ASTMove -> visit(node)
        is ASTCopy -> visit(node)
        is ASTInsertData -> visit(node)
        is ASTDeleteData -> visit(node)
        is ASTDeleteWhere -> visit(node)
        is ASTModify -> visit(node)
    }

    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTBlankNodePropertyList): Pair<AOPBase, List<LOPTriple>> {
val subject =if(blankNodeToVariable){
AOPVariable(query, "_ASTBlankNodePropertyList#${counter++}")
}else{
        val buffer = ByteArrayWrapper()
        DictionaryHelper.bnodeToByteArray(buffer, "_ASTBlankNodePropertyList#${counter++}")
        AOPConstant(query, buffer)
}
        return subject to visit(blankNodeToVariable,graph, graphVar, subject, node.variable0!!)
    }

    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTCollection): Pair<AOPBase, List<LOPTriple>> {
        val bufferNil = ByteArrayWrapper()
        val bufferFirst = ByteArrayWrapper()
        val bufferRest = ByteArrayWrapper()
        DictionaryHelper.iriToByteArray(bufferNil, "http://www.w3.org/1999/02/22-rdf-syntax-ns#nil")
        DictionaryHelper.iriToByteArray(bufferFirst, "http://www.w3.org/1999/02/22-rdf-syntax-ns#first")
        DictionaryHelper.iriToByteArray(bufferRest, "http://www.w3.org/1999/02/22-rdf-syntax-ns#rest")
        var first :AOPBase= AOPConstant(query, bufferNil)
        var current :AOPBase= AOPConstant(query, bufferNil)
        var f = true
        val res = mutableListOf<LOPTriple>()
        for (v in node.variable0!!.value) {
            val t = visit(blankNodeToVariable,v)
            res.addAll(t.second)
            if (f) {
                f = false
current=if(blankNodeToVariable){
AOPVariable(query, "_ASTCollection#${counter++}")
}else{
                val buffer = ByteArrayWrapper()
                DictionaryHelper.bnodeToByteArray(buffer, "_ASTCollection#${counter++}")
                 AOPConstant(query, buffer)
}
                first = current
            } else {
val next=if(blankNodeToVariable){
AOPVariable(query, "_ASTCollection#${counter++}")
}else{
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

    private fun visit(graph: String, graphVar: Boolean, node: ASTTriplesNode): Pair<AOPConstant, List<LOPTriple>> = when (node) {
        is ASTBlankNodePropertyList -> visit(graph, graphVar, node)
        is ASTCollection -> visit(graph, graphVar, node)
    }

    private fun visit(blankNodeToVariable:Boolean,node: ASTGraphNode): Pair<AOPBase, List<LOPTriple>> = when (node) {
        is ASTTriplesNode -> visit(blankNodeToVariable,node)
        is ASTVar1 -> AOPVariable(query, node.VAR1!!.drop(1)) to listOf()
        is ASTVar2 -> AOPVariable(query, node.VAR2!!.drop(1)) to listOf()
        is ASTGraphTerm -> visit(blankNodeToVariable,node) to listOf()
    }

    private fun visit(blankNodeToVariable:Boolean,node: ASTGraphTerm) = when (node) {
        is ASTRDFLiteral -> visit(node)
        is ASTNumericLiteral -> visit(node)
        is ASTBooleanLiteral -> visit(node)
        is ASTiri -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.iriToByteArray(buffer, visit(node))
            AOPConstant(query, buffer)
        }
        is ASTBlankNode -> visit(blankNodeToVariable,node)
        is ASTNILParam -> {
if(blankNodeToVariable){
AOPVariable(query,"_ASTGraphTerm#${counter++}")
}else{
            val buffer = ByteArrayWrapper()
            DictionaryHelper.bnodeToByteArray(buffer, "_ASTGraphTerm#${counter++}")
            AOPConstant(query, buffer)
      }
  }
    }

    private fun visit(blankNodeToVariable:Boolean,node: ASTBlankNode) = when (node) {
        is ASTBlankNodeLabel -> visit(blankNodeToVariable,node)
        is ASTBlankNodeANON -> visit(blankNodeToVariable,node)
    }

    private fun visit(blankNodeToVariable:Boolean,node: ASTBlankNodeLabel): AOPBase =if(blankNodeToVariable) {
AOPVariable(query, node.BLANK_NODE_LABEL!!+"#")
}else{
        val buffer = ByteArrayWrapper()
        DictionaryHelper.bnodeToByteArray(buffer, node.BLANK_NODE_LABEL!!)
         AOPConstant(query, buffer)
    }

    private fun visit(blankNodeToVariable:Boolean,unused: ASTBlankNodeANON): AOPBase =if(blankNodeToVariable) {
AOPVariable(query,"_ASTBlankNodeANON#${counter++}")
}else {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.bnodeToByteArray(buffer, "_ASTBlankNodeANON#${counter++}")
         AOPConstant(query, buffer)
    }

    private fun visit(blankNodeToVariable:Boolean,node: ASTVarOrTerm) = when (node) {
        is ASTGraphTerm -> visit(blankNodeToVariable,node)
        is ASTVar1 -> AOPVariable(query, node.VAR1!!.drop(1))
        is ASTVar2 -> AOPVariable(query, node.VAR2!!.drop(1))
    }

    private fun visit(node: ASTVerb): AOPBase = when (node) {
        is ASTRDFType -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.iriToByteArray(buffer, "http://www.w3.org/1999/02/22-rdf-syntax-ns#type")
            AOPConstant(query, buffer)
        }
        is ASTVar1 -> AOPVariable(query, node.VAR1!!.drop(1))
        is ASTVar2 -> AOPVariable(query, node.VAR2!!.drop(1))
        is ASTiri -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.iriToByteArray(buffer, visit(node))
            AOPConstant(query, buffer)
        }
    }

    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTClassOfTriplesNodeAndPropertyListOptional): List<LOPTriple> {
        val tmp = visit(graph, graphVar, node.variable0!!)
        return tmp.second + visit(blankNodeToVariable,graph, graphVar, tmp.first, node.variable1!!)
    }

    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, subject: AOPBase, predicate: AOPBase, node: ASTObjectList): List<LOPTriple> {
        val a = visit(blankNodeToVariable,node.variable0!!)
        val res = a.second.toMutableList()
        res.add(LOPTriple(query, subject, predicate, a.first, graph, graphVar))
        for (v in node.variable1!!.value) {
            val b = visit(blankNodeToVariable,v)
            res.addAll(b.second)
            res.add(LOPTriple(query, subject, predicate, b.first, graph, graphVar))
        }
        return res
    }

    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTTriplesSameSubject): List<LOPTriple> = when (node) {
        is ASTClassOfVarOrTermAndPropertyListNotEmpty -> visit(blankNodeToVariable,graph, graphVar, node)
        is ASTClassOfTriplesNodeAndPropertyListOptional -> visit(blankNodeToVariable,graph, graphVar, node)
    }

    private fun visit(blankNodeToVariable:Boolean,node: ASTQuadsNotTriples): List<LOPTriple> = when (val v0 = node.variable0!!) {
        is ASTVar1 -> visit(blankNodeToVariable,v0.VAR1!!.drop(1), true, node.variable1!!)
        is ASTVar2 -> visit(blankNodeToVariable,v0.VAR2!!.drop(1), true, node.variable1!!)
        is ASTiri -> visit(blankNodeToVariable,visit(v0), false, node.variable1!!)
    }

    private fun visit(node: ASTDeleteWhere): LOPModify {
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

        val tmp = visit(true,"", false, node.variable0!!).map { LOPTriple(query, bnodeToVariable(it.children[0]), bnodeToVariable(it.children[1]), bnodeToVariable(it.children[2]), it.graph, it.graphVar) }
        return LOPModify(query, mutableListOf(), tmp.toMutableList(), tmp.map { it as OPBase }.reduce { s, t -> LOPJoin(query, s, t, false) })
    }

    private fun visit(node: ASTModify): LOPModify {
        val graph = node.variable0!!.variable0?.let { visit(it) } ?: ""
        val usingClause = node.variable2!!
        if (usingClause.value.size > 0) {
            TODO("using not implemented")
        }
        return when (val v1 = node.variable1!!) {
            is ASTInsertClause -> LOPModify(query, visit(true,graph, false, v1.variable0!!).toMutableList(), mutableListOf(), visit(true,graph, false, node.variable3!!))
            is ASTClassOfDeleteClauseAndInsertClauseOptional -> LOPModify(
                query,
                visit(true,graph, false, v1.variable1!!).toMutableList(),
                visit(true,graph, false, v1.variable0!!).toMutableList(),
                visit(true,graph, false, node.variable3!!)
            )
        }
    }

    private fun visit(node: ASTDataBlockValue): AOPConstant = when (node) {
        is ASTUNDEF -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.undefToByteArray(buffer)
            AOPConstant(query, buffer)
        }
        is ASTRDFLiteral -> visit(node)
        is ASTNumericLiteral -> visit(node)
        is ASTBooleanLiteral -> visit(node)
        is ASTiri -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.iriToByteArray(buffer, visit(node))
            AOPConstant(query, buffer)
        }
    }

    private fun visit(node: ASTDataBlock) = when (node) {
        is ASTInlineDataOneVar -> visit(node)
        is ASTInlineDataFull -> visit(node)
    }

    private fun visit(node: ASTInlineDataFull): LOPValues = when (val v = node.variable0!!) {
        is ASTListOfVar -> LOPValues(query, visit(v), visit(node.variable1!!).map { AOPValue(query, it) })
        is ASTNILParam -> LOPValues(query, listOf(), visit(node.variable1!!).map { AOPValue(query, it) })
    }

    private fun visit(node: ASTInterfaceOfListOfDataBlockValueOrNILParam) = when (node) {
        is ASTNILParam -> listOf()
        is ASTListOfDataBlockValue -> visit(node)
    }

    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTInterfaceOfSubSelectOrGroupGraphPatternSub): IOPBase = when (node) {
        is ASTGroupGraphPatternSub -> visit(blankNodeToVariable,graph, graphVar, node)
        is ASTSubSelect -> visit(graph, graphVar, node)
    }

    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTTriplesSameSubjectPath) = when (node) {
        is ASTClassOfVarOrTermAndPropertyListPathNotEmpty -> visit(blankNodeToVariable,graph, graphVar, node)
        is ASTClassOfTriplesNodePathAndPropertyListPathOptional -> visit(blankNodeToVariable,graph, graphVar, node)
    }

    private fun visit(node: ASTInterfaceOfVerbPathOrVerbSimple): AOPBase = when (node) {
        is ASTVerbSimple -> visit(node)
        is ASTVerbPath -> visit(node)
    }

    private fun visit(node: ASTVerbPath) = visit(node.variable0!!)
    private fun visit(node: ASTPath) = visit(node.variable0!!)
    private fun visit(node: ASTPathAlternative): AOPBase {
        if (node.variable1!!.value.size > 0) {
            TODO("path not implemented")
        }
        return visit(node.variable0!!)
    }

    private fun visit(node: ASTPathSequence): AOPBase {
        if (node.variable1!!.value.size > 0) {
            TODO("path not implemented")
        }
        return visit(node.variable0!!)
    }

    private fun visit(node: ASTPathEltOrInverse): AOPBase {
        if (node.negated) {
            TODO("path not implemented")
        }
        return visit(node.variable1!!)
    }

    private fun visit(node: ASTPathElt): AOPBase {
        if (initializeEnum(node.variable1) != ASTEnumOfoptionalAndanyAndatLeastOne._UNDEFINED) {
            TODO("path not implemented")
        }
        return visit(node.variable0!!)
    }

    private fun visit(node: ASTPathPrimary): AOPBase = when (node) {
        is ASTiri -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.iriToByteArray(buffer, visit(node))
            AOPConstant(query, buffer)
        }
        is ASTRDFType -> {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.iriToByteArray(buffer, "http://www.w3.org/1999/02/22-rdf-syntax-ns#type")
            AOPConstant(query, buffer)
        }
        is ASTPath -> visit(node)
        is ASTPathNegatedPropertySet -> TODO("path not implemented")
    }

    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, subject: AOPBase, predicate: AOPBase, node: ASTObjectListPath): List<LOPTriple> {
        val a = visit(blankNodeToVariable,node.variable0!!)
        val res = a.second.toMutableList()
        res.add(LOPTriple(query, subject, predicate, a.first, graph, graphVar))
        for (v in node.variable1!!.value) {
            val b = visit(blankNodeToVariable,v)
            res.addAll(b.second)
            res.add(LOPTriple(query, subject, predicate, b.first, graph, graphVar))
        }
        return res
    }

    private fun visit(blankNodeToVariable:Boolean,node: ASTGraphNodePath): Pair<AOPBase, List<LOPTriple>> = when (node) {
        is ASTVar1 -> AOPVariable(query, node.VAR1!!.drop(1)) to listOf()
        is ASTVar2 -> AOPVariable(query, node.VAR2!!.drop(1)) to listOf()
        is ASTGraphTerm -> visit(blankNodeToVariable,node) to listOf()
        is ASTTriplesNodePath -> visit(blankNodeToVariable,node)
    }

    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTClassOfTriplesNodePathAndPropertyListPathOptional): List<LOPTriple> {
        val tmp = visit(blankNodeToVariable,graph, graphVar, node.variable0!!)
        return tmp.second + visit(blankNodeToVariable,graph, graphVar, tmp.first, node.variable1!!)
    }

    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTBlankNodePropertyListPath): Pair<AOPBase, List<LOPTriple>> {
val subject=if(blankNodeToVariable){
AOPVariable(query, "_ASTBlankNodePropertyList#${counter++}")
}else{
        val buffer = ByteArrayWrapper()
        DictionaryHelper.bnodeToByteArray(buffer, "_ASTBlankNodePropertyList#${counter++}")
         AOPConstant(query, buffer)
}
        return subject to visit(blankNodeToVariable,graph, graphVar, subject, node.variable0!!)
    }

    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTCollectionPath): Pair<AOPBase, List<LOPTriple>> {
        val bufferNil = ByteArrayWrapper()
        val bufferFirst = ByteArrayWrapper()
        val bufferRest = ByteArrayWrapper()
        DictionaryHelper.iriToByteArray(bufferNil, "http://www.w3.org/1999/02/22-rdf-syntax-ns#nil")
        DictionaryHelper.iriToByteArray(bufferFirst, "http://www.w3.org/1999/02/22-rdf-syntax-ns#first")
        DictionaryHelper.iriToByteArray(bufferRest, "http://www.w3.org/1999/02/22-rdf-syntax-ns#rest")
        var first :AOPBase= AOPConstant(query, bufferNil)
        var current :AOPBase= AOPConstant(query, bufferNil)
        var f = true
        val res = mutableListOf<LOPTriple>()
        for (v in node.variable0!!.value) {
            val t = visit(blankNodeToVariable,v)
            res.addAll(t.second)
            if (f) {
                f = false
                current=if(blankNodeToVariable){
AOPVariable(query,"_ASTCollection#${counter++}")
}else{
val buffer = ByteArrayWrapper()
                DictionaryHelper.bnodeToByteArray(buffer, "_ASTCollection#${counter++}")
                 AOPConstant(query, buffer)
}
                first = current
            } else {
val next=if(blankNodeToVariable){
AOPVariable(query,"_ASTCollection#${counter++}")
}else{
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

    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTTriplesNodePath) = when (node) {
        is ASTBlankNodePropertyListPath -> visit(blankNodeToVariable,graph, graphVar, node)
        is ASTCollectionPath -> visit(blankNodeToVariable,graph, graphVar, node)
    }

    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTGraphGraphPattern, child: IOPBase) = LOPJoin(
        query,
        child,
        when (val v0 = node.variable0!!) {
            is ASTVar1 -> visit(blankNodeToVariable,v0.VAR1!!.drop(1), true, node.variable1!!)
            is ASTVar2 -> visit(blankNodeToVariable,v0.VAR2!!.drop(1), true, node.variable1!!)
            is ASTiri -> visit(blankNodeToVariable,visit(v0), false, node.variable1!!)
        },
        false
    )

    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTGroupGraphPatternSub): IOPBase {
        var triplesBlockPrevious = visit(blankNodeToVariable,graph, graphVar, node.variable0!!) as List<IOPBase>
        val listNotTriples: MutableList<ASTClassOfGraphPatternNotTriplesAndpointAndTriplesBlockOptional> = node.variable1!!.value
        if (triplesBlockPrevious.size == 0) {
            val v = listNotTriples.removeAt(0)
            triplesBlockPrevious = visit(blankNodeToVariable,graph, graphVar, v.variable2!!) + visit(graph, graphVar, v.variable0!!)
        }
        return listNotTriples.fold(
            triplesBlockPrevious.reduce { s, t ->
                LOPJoin(query, s, t, false)
            },
            { u, v ->
                (visit(blankNodeToVariable,graph, graphVar, v.variable2!!) + visit(blankNodeToVariable,graph, graphVar, v.variable0!!, u)).reduce { s, t -> LOPJoin(query, s, t, false) }
            }
        )
    }
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTGraphPatternNotTriples, child: IOPBase): IOPBase = when (node) {
        is ASTOptionalGraphPattern -> visit(blankNodeToVariable,graph, graphVar, node, child)
        is ASTGroupOrUnionGraphPattern -> visit(blankNodeToVariable,graph, graphVar, node, child)
        is ASTMinusGraphPattern -> visit(blankNodeToVariable,graph, graphVar, node, child)
        is ASTGraphGraphPattern -> visit(blankNodeToVariable,graph, graphVar, node, child)
        is ASTServiceGraphPattern -> visit(graph, graphVar, node, child)
        is ASTFilter -> visit(blankNodeToVariable,graph, graphVar, node, child)
        is ASTBind -> visit(blankNodeToVariable,graph, graphVar, node, child)
        is ASTValuesClause -> visit(graph, graphVar, node, child)
    }
    private fun visit(graph: String, graphVar: Boolean, node: ASTGraphPatternNotTriples): IOPBase = when (node) {
        is ASTGroupOrUnionGraphPattern -> visit(graph, graphVar, node)
        else -> TODO()
    }
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, subject: AOPBase, node: ASTPropertyListOptional): List<LOPTriple> = node.variable0?.let { visit(blankNodeToVariable,graph, graphVar, subject, it) }
        ?: listOf()

    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTTriplesBlockOptional): List<LOPTriple> = node.variable0?.let { visit(blankNodeToVariable,graph, graphVar, it) }
        ?: listOf()

    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTTriplesBlockOptionalOptional): List<LOPTriple> = node.variable0?.let { visit(blankNodeToVariable,graph, graphVar, it) }
        ?: listOf()

    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTTriplesTemplateOptionalOptional): List<LOPTriple> = node.variable0?.let { visit(blankNodeToVariable,graph, graphVar, it) }
        ?: listOf()

    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTTriplesTemplateOptional): List<LOPTriple> = node.variable0?.let { visit(blankNodeToVariable,graph, graphVar, it) }
        ?: listOf()

    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, subject: AOPBase, node: ASTPropertyListPathOptional): List<LOPTriple> = node.variable0?.let { visit(blankNodeToVariable,graph, graphVar, subject, it) }
        ?: listOf()


    private fun visit(graph: String, graphVar: Boolean, node: ASTHavingCondition) = visit(graph, graphVar, node.variable0!!)
    private fun visit(graph: String, graphVar: Boolean, node: ASTListOfHavingCondition) = node.value.map { visit(graph, graphVar, it) }
    private fun visit(graph: String, graphVar: Boolean, node: ASTHavingClause) = visit(graph, graphVar, node.variable0!!)
    private fun visit(graph: String, graphVar: Boolean, node: ASTClassOfExpressionAndVarOptional): Pair<AOPBase, AOPVariable> = visit(graph, graphVar, node.variable0!!) to visit(node.variable1!!.variable0!!)
    private fun visit(graph: String, graphVar: Boolean, node: ASTListOfGroupCondition): List<Pair<AOPBase?, AOPVariable>> = node.value.map { visit(graph, graphVar, it) }
    private fun visit(node: ASTListOfVar): List<AOPVariable> = node.value.map { visit(it) }
    private fun visit(node: ASTListOfDataBlockValue): List<AOPConstant> = node.value.map { visit(it) }
    private fun visit(node: ASTListOfInterfaceOfListOfDataBlockValueOrNILParam): List<List<AOPConstant>> = node.value.map { visit(it) }
    private fun visit(node: ASTInlineDataOneVar) = LOPValues(query, listOf(visit(node.variable0!!)), visit(node.variable1!!).map { AOPValue(query, listOf(it)) })
    private fun visit(node: ASTValuesClause): IOPBase = visit(node.variable0!!)
    private fun visit(node: ASTClear) = LOPGraphOperation(query, EGraphOperationTypeExt.CLEAR, node.SILENT, visit(node.variable1!!))
    private fun visit(node: ASTDrop) = LOPGraphOperation(query, EGraphOperationTypeExt.DROP, node.SILENT, visit(node.variable1!!))
    private fun visit(node: ASTCreate) = LOPGraphOperation(query, EGraphOperationTypeExt.CREATE, node.SILENT, visit(node.variable1!!))
    private fun visit(node: ASTAdd) = LOPGraphOperation(query, EGraphOperationTypeExt.ADD, node.SILENT, visit(node.variable1!!), visit(node.variable2!!))
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTClassOfVarOrTermAndPropertyListNotEmpty): List<LOPTriple> = visit(blankNodeToVariable,graph, graphVar, visit(blankNodeToVariable,node.variable0!!), node.variable1!!)
    private fun visit(graph: String, graphVar: Boolean, node: ASTValuesClause, child: IOPBase) = visit(node.variable0!!)
    private fun visit(node: ASTMove) = LOPGraphOperation(query, EGraphOperationTypeExt.MOVE, node.SILENT, visit(node.variable1!!), visit(node.variable2!!))
    private fun visit(node: ASTCopy) = LOPGraphOperation(query, EGraphOperationTypeExt.COPY, node.SILENT, visit(node.variable1!!), visit(node.variable2!!))
    private fun visit(blankNodeToVariable:Boolean,node: ASTObject): Pair<AOPBase, List<LOPTriple>> = visit(blankNodeToVariable,node.variable0!!)
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTQuadPattern) = visit(blankNodeToVariable,graph, graphVar, node.variable0!!)
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, subject: AOPBase, node: ASTClassOfVerbAndObjectList): List<LOPTriple> = visit(blankNodeToVariable,graph, graphVar, subject, visit(node.variable0!!), node.variable1!!)
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, subject: AOPBase, node: ASTClassOfVerbAndObjectListOptional): List<LOPTriple> = visit(blankNodeToVariable,graph, graphVar, subject, node.variable0!!)
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, subject: AOPBase, node: ASTListOfClassOfVerbAndObjectListOptional): List<LOPTriple> = node.value.map { visit(blankNodeToVariable,graph, graphVar, subject, it) }.flatten()
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, subject: AOPBase, node: ASTPropertyListNotEmpty): List<LOPTriple> = visit(blankNodeToVariable,graph, graphVar, subject, visit(node.variable0!!), node.variable1!!) + visit(blankNodeToVariable,graph, graphVar, subject, node.variable2!!)
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTTriplesTemplate): List<LOPTriple> = visit(blankNodeToVariable,graph, graphVar, node.variable0!!) + visit(blankNodeToVariable,graph, graphVar, node.variable1!!)
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTClassOfQuadsNotTriplesAndpointAndTriplesTemplateOptional) = visit(blankNodeToVariable,node.variable0!!) + node.variable2!!.variable0?.let { visit(blankNodeToVariable,graph, graphVar, it) }.orEmpty()
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTListOfClassOfQuadsNotTriplesAndpointAndTriplesTemplateOptional): List<LOPTriple> = node.value.map { visit(blankNodeToVariable,graph, graphVar, it) }.flatten()
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTQuads): List<LOPTriple> = visit(blankNodeToVariable,graph, graphVar, node.variable0!!) + visit(blankNodeToVariable,graph, graphVar, node.variable1!!)
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTQuadData): List<LOPTriple> = visit(blankNodeToVariable,graph, graphVar, node.variable0!!)
    private fun visit(node: ASTInsertData) = LOPModifyData(query, EModifyTypeExt.INSERT, visit(false,"", false, node.variable0!!).toMutableList())
    private fun visit(node: ASTDeleteData) = LOPModifyData(query, EModifyTypeExt.DELETE, visit(false,"", false, node.variable0!!).toMutableList())
    private fun visit(node: ASTString1) = node.STRING_LITERAL1!!.drop(1).dropLast(1)
    private fun visit(node: ASTString2) = node.STRING_LITERAL2!!.drop(1).dropLast(1)
    private fun visit(node: ASTString1long) = node.STRING_LITERAL_LONG1!!.drop(3).dropLast(3)
    private fun visit(node: ASTString2long) = node.STRING_LITERAL_LONG2!!.drop(3).dropLast(3)
    private fun visit(graph: String, graphVar: Boolean, node: ASTNumericExpression) = visit(graph, graphVar, node.variable0!!)
    private fun visit(graph: String, graphVar: Boolean, node: ASTClassOfExpressionAndListOfExpression) = listOf(visit(graph, graphVar, node.variable0!!)) + node.variable1?.value.orEmpty().map { visit(graph, graphVar, it) }
    private fun visit(graph: String, graphVar: Boolean, node: ASTConditionalAndExpression) = (listOf(visit(graph, graphVar, node.variable0!!)) + visit(graph, graphVar, node.variable1!!)).reduce { s, t -> AOPAnd(query, s, t) }
    private fun visit(graph: String, graphVar: Boolean, node: ASTConditionalOrExpression) = (listOf(visit(graph, graphVar, node.variable0!!)) + visit(graph, graphVar, node.variable1!!)).reduce { s, t -> AOPOr(query, s, t) }
    private fun visit(graph: String, graphVar: Boolean, node: ASTWhereClause) = visit(true,graph, graphVar, node.variable1!!)
    private fun visit(graph: String, graphVar: Boolean, node: ASTExpression): AOPBase = visit(graph, graphVar, node.variable0!!)
    private fun visit(graph: String, graphVar: Boolean, node: ASTValueLogical) = visit(graph, graphVar, node.variable0!!)
    private fun visit(graph: String, graphVar: Boolean, node: ASTListOfValueLogical) = node.value.map { visit(graph, graphVar, it) }
    private fun visit(graph: String, graphVar: Boolean, node: ASTListOfConditionalAndExpression) = node.value.map { visit(graph, graphVar, it) }
    private fun visit(node: ASTPrologue) = node.value.map { visit(it) }
    private fun visit(node: ASTBaseDecl) = prefix("", node.IRIREF!!)
    private fun visit(node: ASTPrefixDecl) = prefix(node.PNAME_NS!!.dropLast(1), node.IRIREF!!)
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTGroupGraphPattern) = visit(blankNodeToVariable,graph, graphVar, node.variable0!!)
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTClassOfVarOrTermAndPropertyListPathNotEmpty): List<LOPTriple> = visit(blankNodeToVariable,graph, graphVar, visit(blankNodeToVariable,node.variable0!!), node.variable1!!)
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTInsertClauseOptional) = visit(blankNodeToVariable,graph, graphVar, node.variable0!!)
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTInsertClause) = visit(blankNodeToVariable,graph, graphVar, node.variable0!!)
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTDeleteClause) = visit(blankNodeToVariable,graph, graphVar, node.variable0!!)
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, subject: AOPBase, node: ASTPropertyListPathNotEmpty): List<LOPTriple> = visit(blankNodeToVariable,graph, graphVar, subject, visit(node.variable0!!), node.variable1!!) + visit(blankNodeToVariable,graph, graphVar, subject, node.variable2!!)
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, subject: AOPBase, node: ASTPropertyList): List<LOPTriple> = visit(blankNodeToVariable,graph, graphVar, subject, node.variable0!!)
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTTriplesBlock): List<LOPTriple> = visit(blankNodeToVariable,graph, graphVar, node.variable0!!) + visit(blankNodeToVariable,graph, graphVar, node.variable1!!)
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, subject: AOPBase, node: ASTListOfClassOfInterfaceOfVerbPathOrVerbSimpleAndObjectListOptional): List<LOPTriple> = node.value.map { visit(blankNodeToVariable,graph, graphVar, subject, it.variable0!!) }.flatten()
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, subject: AOPBase, node: ASTClassOfInterfaceOfVerbPathOrVerbSimpleAndObjectList) = visit(blankNodeToVariable,graph, graphVar, subject, visit(node.variable0!!), node.variable1!!)
    private fun visit(node: ASTVerbSimple) = visit(node.variable0!!)
    private fun visit(blankNodeToVariable:Boolean,node: ASTObjectPath): Pair<AOPBase, List<LOPTriple>> = visit(blankNodeToVariable,node.variable0!!)
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTOptionalGraphPattern, child: IOPBase) = LOPJoin(query, child, visit(blankNodeToVariable,graph, graphVar, node.variable0!!), true)
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTGroupOrUnionGraphPattern, child: IOPBase) = LOPJoin(query, child, (listOf(visit(blankNodeToVariable,graph, graphVar, node.variable0!!)) + node.variable1!!.value.map { visit(blankNodeToVariable,graph, graphVar, it) }).reduce { s, t -> LOPUnion(query, s, t) }, false)
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTGroupOrUnionGraphPattern) = (listOf(visit(blankNodeToVariable,graph, graphVar, node.variable0!!)) + node.variable1!!.value.map { visit(blankNodeToVariable,graph, graphVar, it) }).reduce { s, t -> LOPUnion(query, s, t) }
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTMinusGraphPattern, child: IOPBase) = LOPMinus(query, child, visit(blankNodeToVariable,graph, graphVar, node.variable0!!), mutableListOf())
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTFilter, child: IOPBase) = LOPFilter(query, visit(graph, graphVar, node.variable0!!), child)
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, node: ASTBind, child: IOPBase) = LOPBind(query, visit(node.variable1!!), visit(graph, graphVar, node.variable0!!), child)
    private fun visit(graph: String, graphVar: Boolean, node: ASTServiceGraphPattern, child: IOPBase): IOPBase = TODO("service not implemented")
    private fun visit(node: ASTConstructQuery, valuesClause: ASTValuesClause?): IOPBase = TODO("construct query not implemented")
    private fun visit(node: ASTDescribeQuery, valuesClause: ASTValuesClause?): IOPBase = TODO("describe query not implemented")
    private fun visit(blankNodeToVariable:Boolean,graph: String, graphVar: Boolean, subject: AOPBase, node: ASTPropertyListPath): List<LOPTriple> = visit(blankNodeToVariable,graph, graphVar, subject, node.variable0!!)
    private fun visit(node: ASTOffsetClauseOptional, tmp: IOPBase) = node.variable0?.let { visit(it, tmp) } ?: tmp
    private fun visit(node: ASTLimitClauseOptional, tmp: IOPBase) = node.variable0?.let { visit(it, tmp) } ?: tmp
    private fun visit(node: ASTOffsetClause, tmp: IOPBase) = LOPOffset(query, node.INTEGER!!.toInt(), tmp)
    private fun visit(node: ASTLimitClause, tmp: IOPBase) = LOPLimit(query, node.INTEGER!!.toInt(), tmp)
    private fun visit(v0: ASTPrologue, v1: ASTClassOfUpdate1AndClassOfPrologueAndUpdateOptionalOptional) = v1.variable0?.let{visit(v0, it)}?: OPBaseCompound(query,arrayOf(),mutableListOf())
}
