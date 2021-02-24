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

import lupos.s00misc.AggregateNotAllowedSyntaxException
import lupos.s00misc.DatasetImportFailedException
import lupos.s00misc.EGraphOperationTypeExt
import lupos.s00misc.EGraphRefType
import lupos.s00misc.EGraphRefTypeExt
import lupos.s00misc.EModifyTypeExt
import lupos.s00misc.File
import lupos.s00misc.GroupByClauseNotUsedException
import lupos.s00misc.MyBigDecimal
import lupos.s00misc.MyBigInteger
import lupos.s00misc.ProjectionDoubleDefinitionOfVariableSyntaxException
import lupos.s00misc.RecoursiveVariableDefinitionSyntaxException
import lupos.s00misc.SanityCheck
import lupos.s00misc.SparqlFeatureNotImplementedException
import lupos.s00misc.UnreachableException
import lupos.s00misc.XMLElement
import lupos.s00misc.parseFromAny
import lupos.s02buildSyntaxTree.sparql1_1.ASTAdd
import lupos.s02buildSyntaxTree.sparql1_1.ASTAddition
import lupos.s02buildSyntaxTree.sparql1_1.ASTAggregation
import lupos.s02buildSyntaxTree.sparql1_1.ASTAllGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTAnd
import lupos.s02buildSyntaxTree.sparql1_1.ASTAs
import lupos.s02buildSyntaxTree.sparql1_1.ASTAskQuery
import lupos.s02buildSyntaxTree.sparql1_1.ASTBase
import lupos.s02buildSyntaxTree.sparql1_1.ASTBlankNode
import lupos.s02buildSyntaxTree.sparql1_1.ASTBooleanLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTBuiltInCall
import lupos.s02buildSyntaxTree.sparql1_1.ASTClear
import lupos.s02buildSyntaxTree.sparql1_1.ASTConstructQuery
import lupos.s02buildSyntaxTree.sparql1_1.ASTCopy
import lupos.s02buildSyntaxTree.sparql1_1.ASTCreate
import lupos.s02buildSyntaxTree.sparql1_1.ASTDatasetClause
import lupos.s02buildSyntaxTree.sparql1_1.ASTDecimal
import lupos.s02buildSyntaxTree.sparql1_1.ASTDefaultGraph
import lupos.s02buildSyntaxTree.sparql1_1.ASTDefaultGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTDeleteData
import lupos.s02buildSyntaxTree.sparql1_1.ASTDeleteWhere
import lupos.s02buildSyntaxTree.sparql1_1.ASTDescribeQuery
import lupos.s02buildSyntaxTree.sparql1_1.ASTDivision
import lupos.s02buildSyntaxTree.sparql1_1.ASTDouble
import lupos.s02buildSyntaxTree.sparql1_1.ASTDrop
import lupos.s02buildSyntaxTree.sparql1_1.ASTEQ
import lupos.s02buildSyntaxTree.sparql1_1.ASTFilter
import lupos.s02buildSyntaxTree.sparql1_1.ASTFunctionCall
import lupos.s02buildSyntaxTree.sparql1_1.ASTGEQ
import lupos.s02buildSyntaxTree.sparql1_1.ASTGT
import lupos.s02buildSyntaxTree.sparql1_1.ASTGrapOperation
import lupos.s02buildSyntaxTree.sparql1_1.ASTGraph
import lupos.s02buildSyntaxTree.sparql1_1.ASTGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTGroup
import lupos.s02buildSyntaxTree.sparql1_1.ASTGroupConcat
import lupos.s02buildSyntaxTree.sparql1_1.ASTIn
import lupos.s02buildSyntaxTree.sparql1_1.ASTInsertData
import lupos.s02buildSyntaxTree.sparql1_1.ASTInteger
import lupos.s02buildSyntaxTree.sparql1_1.ASTIri
import lupos.s02buildSyntaxTree.sparql1_1.ASTIriGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTLEQ
import lupos.s02buildSyntaxTree.sparql1_1.ASTLT
import lupos.s02buildSyntaxTree.sparql1_1.ASTLanguageTaggedLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTLoad
import lupos.s02buildSyntaxTree.sparql1_1.ASTMinus
import lupos.s02buildSyntaxTree.sparql1_1.ASTMinusGroup
import lupos.s02buildSyntaxTree.sparql1_1.ASTModify
import lupos.s02buildSyntaxTree.sparql1_1.ASTModifyWithWhere
import lupos.s02buildSyntaxTree.sparql1_1.ASTMove
import lupos.s02buildSyntaxTree.sparql1_1.ASTMultiplication
import lupos.s02buildSyntaxTree.sparql1_1.ASTNEQ
import lupos.s02buildSyntaxTree.sparql1_1.ASTNamedGraph
import lupos.s02buildSyntaxTree.sparql1_1.ASTNamedGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTNamedIriGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTNode
import lupos.s02buildSyntaxTree.sparql1_1.ASTNot
import lupos.s02buildSyntaxTree.sparql1_1.ASTNotIn
import lupos.s02buildSyntaxTree.sparql1_1.ASTNumericLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTOptional
import lupos.s02buildSyntaxTree.sparql1_1.ASTOr
import lupos.s02buildSyntaxTree.sparql1_1.ASTOrderCondition
import lupos.s02buildSyntaxTree.sparql1_1.ASTPathAlternatives
import lupos.s02buildSyntaxTree.sparql1_1.ASTPathArbitraryOccurrences
import lupos.s02buildSyntaxTree.sparql1_1.ASTPathArbitraryOccurrencesNotZero
import lupos.s02buildSyntaxTree.sparql1_1.ASTPathInverse
import lupos.s02buildSyntaxTree.sparql1_1.ASTPathNegatedPropertySet
import lupos.s02buildSyntaxTree.sparql1_1.ASTPathOptionalOccurrence
import lupos.s02buildSyntaxTree.sparql1_1.ASTPathSequence
import lupos.s02buildSyntaxTree.sparql1_1.ASTPlus
import lupos.s02buildSyntaxTree.sparql1_1.ASTPrefix
import lupos.s02buildSyntaxTree.sparql1_1.ASTQuery
import lupos.s02buildSyntaxTree.sparql1_1.ASTQueryBaseClass
import lupos.s02buildSyntaxTree.sparql1_1.ASTRDFTerm
import lupos.s02buildSyntaxTree.sparql1_1.ASTSelectQuery
import lupos.s02buildSyntaxTree.sparql1_1.ASTService
import lupos.s02buildSyntaxTree.sparql1_1.ASTSet
import lupos.s02buildSyntaxTree.sparql1_1.ASTSimpleLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTSubSelectQuery
import lupos.s02buildSyntaxTree.sparql1_1.ASTSubtraction
import lupos.s02buildSyntaxTree.sparql1_1.ASTTriple
import lupos.s02buildSyntaxTree.sparql1_1.ASTTypedLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTUndef
import lupos.s02buildSyntaxTree.sparql1_1.ASTUnion
import lupos.s02buildSyntaxTree.sparql1_1.ASTUpdateGrapOperation
import lupos.s02buildSyntaxTree.sparql1_1.ASTValue
import lupos.s02buildSyntaxTree.sparql1_1.ASTValues
import lupos.s02buildSyntaxTree.sparql1_1.ASTVar
import lupos.s02buildSyntaxTree.sparql1_1.AggregationExt
import lupos.s02buildSyntaxTree.sparql1_1.BuiltInFunctionsExt
import lupos.s02buildSyntaxTree.sparql1_1.Visitor
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPAggregationBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPAddition
import lupos.s04arithmetikOperators.multiinput.AOPAnd
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCOALESCE
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCONCAT
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCONTAINS
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallIF
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallLANGMATCHES
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRAFTER
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRBEFORE
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRDT
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRENDS
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRLANG
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRSTARTS
import lupos.s04arithmetikOperators.multiinput.AOPDivision
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.multiinput.AOPGEQ
import lupos.s04arithmetikOperators.multiinput.AOPGT
import lupos.s04arithmetikOperators.multiinput.AOPIn
import lupos.s04arithmetikOperators.multiinput.AOPLEQ
import lupos.s04arithmetikOperators.multiinput.AOPLT
import lupos.s04arithmetikOperators.multiinput.AOPMultiplication
import lupos.s04arithmetikOperators.multiinput.AOPNEQ
import lupos.s04arithmetikOperators.multiinput.AOPNotIn
import lupos.s04arithmetikOperators.multiinput.AOPOr
import lupos.s04arithmetikOperators.multiinput.AOPSet
import lupos.s04arithmetikOperators.multiinput.AOPSubtraction
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallSTRUUID
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallUUID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04arithmetikOperators.singleinput.AOPAggregationAVG
import lupos.s04arithmetikOperators.singleinput.AOPAggregationCOUNT
import lupos.s04arithmetikOperators.singleinput.AOPAggregationMAX
import lupos.s04arithmetikOperators.singleinput.AOPAggregationMIN
import lupos.s04arithmetikOperators.singleinput.AOPAggregationSAMPLE
import lupos.s04arithmetikOperators.singleinput.AOPAggregationSUM
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallABS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallBNODE1
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallBOUND
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallCEIL
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallDATATYPE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallDAY
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallExists
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallFLOOR
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallHOURS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIRI
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIsIri
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIsLITERAL
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIsNUMERIC
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallLANG
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallLCASE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMD5
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMINUTES
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMONTH
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallNotExists
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallROUND
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSECONDS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSHA1
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSHA256
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSTR
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSTRLEN
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallTIMEZONE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallTZ
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallUCASE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallURI
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallYEAR
import lupos.s04arithmetikOperators.singleinput.AOPFunctionCallDouble
import lupos.s04arithmetikOperators.singleinput.AOPFunctionCallFloat
import lupos.s04arithmetikOperators.singleinput.AOPFunctionCallString
import lupos.s04arithmetikOperators.singleinput.AOPNot
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.LOPGraphOperation
import lupos.s04logicalOperators.noinput.LOPModifyData
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPModify
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.LOPOptional
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPServiceIRI
import lupos.s04logicalOperators.singleinput.LOPServiceVAR
import lupos.s04logicalOperators.singleinput.LOPSort
import lupos.s04logicalOperators.singleinput.LOPSubGroup
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s04logicalOperators.singleinput.modifiers.LOPReduced
import lupos.s05tripleStore.TripleStoreManager
import lupos.s09physicalOperators.noinput.POPValuesImportXML
import kotlin.jvm.JvmField

public class OperatorGraphVisitor(@JvmField public val query: Query) : Visitor<IOPBase> {
    @JvmField
    public val queryExecutionStartTime: ValueDateTime = ValueDateTime()
    private fun createUnion(a: IOPBase, b: IOPBase): IOPBase {
        val pa = a.getProvidedVariableNames().toMutableSet()
        val pb = b.getProvidedVariableNames().toMutableSet()
        val pc = pa.intersect(pb)
        pa.removeAll(pc)
        pb.removeAll(pc)
        var a1 = a
        var b1 = b
        for (x in pa) {
            b1 = LOPBind(query, AOPVariable(query, x), AOPConstant(query, ValueUndef()), b1)
        }
        for (x in pb) {
            a1 = LOPBind(query, AOPVariable(query, x), AOPConstant(query, ValueUndef()), a1)
        }
        return LOPUnion(query, a1, b1)
    }

    /*queryExecutionStartTime required for_ BuildInCall.NOW */
    override fun visit(node: ASTNode, childrenValues: List<IOPBase>): IOPBase = LOPNOOP(query)
    private fun mergeLOPBind(a: LOPBind, b: LOPBind): LOPBind {
        val aName = a.name.name
        return if (b.getChildren()[1].getRequiredVariableNames().contains(aName)) {
            b.getLatestChild().setChild(a)
            b
        } else {
            a.getLatestChild().setChild(b)
            a
        }
        /*Coverage Unreachable*/
    }

    private fun containsAggregate(node: ASTNode): Boolean {
        if (node is ASTAggregation) {
            return true
        }
        for (c in node.children) {
            if (containsAggregate(c)) {
                return true
            }
        }
        return false
    }

    private fun containsAggregate(node: IOPBase): Boolean {
        if (node is AOPAggregationBase) {
            return true
        }
        for (c in node.getChildren()) {
            if (containsAggregate(c)) {
                return true
            }
        }
        return false
    }

    override fun visit(node: ASTAskQuery, childrenValues: List<IOPBase>): IOPBase {
        return LOPMakeBooleanResult(query, visitSelectBase(node, arrayOf(), distinct = false, false))
    }

    override fun visit(node: ASTSubSelectQuery, childrenValues: List<IOPBase>): IOPBase {
        val res = LOPSubGroup(query, visit(node as ASTSelectQuery, childrenValues))
        if (node.existsValues()) {
            return LOPJoin(query, node.values!!.visit(this), res, false)
        }
        return res
    }

    override fun visit(node: ASTSelectQuery, childrenValues: List<IOPBase>): IOPBase {
        return visitSelectBase(node, node.select, node.distinct, node.reduced)
    }

    private fun visitSelectBase(node: ASTQueryBaseClass, select: Array<ASTNode>, distinct: Boolean, reduced: Boolean): IOPBase {
        val result = LOPNOOP(query)
        var bind: LOPBind? = null
        var bindIsAggregate = false
        val projection = LOPProjection(query)
        result.getLatestChild().setChild(projection)
        val allNamesSelect = mutableSetOf<String>()
        val allNamesBind = mutableSetOf<String>()
        if (select.isNotEmpty()) {
            for (sel in select) {
                when (sel) {
                    is ASTVar -> {
                        if (allNamesBind.contains(sel.name)) {
                            throw ProjectionDoubleDefinitionOfVariableSyntaxException(sel.name)
                        }
                        allNamesSelect.add(sel.name)
                        projection.variables.add(AOPVariable(query, sel.name))
                    }
                    is ASTAs -> {
                        if (allNamesSelect.contains(sel.variable.name)) {
                            throw ProjectionDoubleDefinitionOfVariableSyntaxException(sel.variable.name)
                        }
                        allNamesBind.add(sel.variable.name)
                        val v = AOPVariable(query, sel.variable.name)
                        projection.variables.add(v)
                        val tmp3 = sel.expression.visit(this) as AOPBase
                        if (tmp3.getRequiredVariableNamesRecoursive().contains(v.name)) {
                            throw RecoursiveVariableDefinitionSyntaxException(v.name)
                        }
                        val tmp2 = LOPBind(query, v, tmp3)
                        bindIsAggregate = bindIsAggregate || containsAggregate(sel.expression)
                        bind = if (bind != null) {
                            mergeLOPBind(bind, tmp2)
                        } else {
                            tmp2
                        }
                    }
                    else -> {
                        SanityCheck.checkUnreachable()
                    }
                }
            }
        }
        val childNode = visitQueryBase(node, bind, bindIsAggregate, reduced, distinct)
        result.getLatestChild().setChild(childNode)
        if (select.isEmpty()) {
            for (s in childNode.getProvidedVariableNames()) {
                if (!query.isGeneratedVariableName(s)) {
                    projection.variables.add(AOPVariable(query, s))
                }
            }
        }
        return LOPSubGroup(query, result)
    }

    override fun visit(node: ASTDescribeQuery, childrenValues: List<IOPBase>): IOPBase {
        var child = visitSelectBase(node, node.select, false, false)
        child = child.replaceVariableWithAnother(child, "s", query.getUniqueVariableName())
        child = child.replaceVariableWithAnother(child, "p", query.getUniqueVariableName())
        child = child.replaceVariableWithAnother(child, "o", query.getUniqueVariableName())
        var res: IOPBase? = null
        for (v in child.getProvidedVariableNames()) {
            val tmp5 = child.cloneOP()
            val tmp1 = LOPProjection(query, mutableListOf(AOPVariable(query, "s")), tmp5.replaceVariableWithAnother(tmp5, v, "s"))
            val tmp2 = LOPProjection(query, mutableListOf(AOPVariable(query, "p")), tmp5.replaceVariableWithAnother(tmp5, v, "p"))
            val tmp3 = LOPProjection(query, mutableListOf(AOPVariable(query, "o")), tmp5.replaceVariableWithAnother(tmp5, v, "o"))
            val tmp4 = LOPUnion(
                query,
                LOPUnion(
                    query,
                    LOPJoin(query, tmp1, LOPTriple(query, AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o"), "", false), false),
                    LOPJoin(query, tmp2, LOPTriple(query, AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o"), "", false), false)
                ),
                LOPJoin(query, tmp3, LOPTriple(query, AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o"), "", false), false)
            )
            res = if (res == null) {
                tmp4
            } else {
                LOPUnion(query, res, tmp4)
            }
        }
        if (res == null) {
            return LOPNOOP(query)
        }
        return LOPDistinct(query, res)
    }

    override fun visit(node: ASTConstructQuery, childrenValues: List<IOPBase>): IOPBase {
        val child = visitQueryBase(node, null, false, false, false)
        return visitConstructBase(child, node.template)
    }

    private fun visitConstructBase(child: IOPBase, template: Array<ASTNode>): IOPBase {
        val names = listOf("s", "p", "o")
        val templates = mutableListOf<Pair<Any, Boolean>>() // name, isVariable
        for (t in template) {
            val templateLocal = t.visit(this) as LOPTriple
            for (i in 0 until 3) {
                val tmp1 = templateLocal.getChildren()[i]
                if (tmp1 is AOPVariable) {
                    templates.add(Pair(tmp1.name, true))
                } else {
                    templates.add(Pair(tmp1 as AOPConstant, false))
                }
            }
        }
        var mychild: IOPBase = child
        val provided = mychild.getProvidedVariableNames()
        for (selected in names) {
            if (provided.contains(selected)) {
                val tmp = query.getUniqueVariableName()
                mychild = mychild.replaceVariableWithAnother(mychild, selected, tmp)
                for (i in 0 until templates.size) {
                    if (templates[i].second && templates[i].first == selected) {
                        templates[i] = Pair(tmp, true)
                    }
                }
            }
        }
        var res: IOPBase? = null
        for (i in 0 until templates.size / 3) {
            var tmp = mychild.cloneOP()
            for (name in 0 until 3) {
                val tmp2 = templates[i * 3 + name]
                tmp = if (tmp2.second) {
                    tmp.replaceVariableWithAnother(tmp, tmp2.first as String, names[name])
                } else {
                    LOPBind(query, AOPVariable(query, names[name]), tmp2.first as AOPConstant, tmp)
                }
            }
            tmp = LOPProjection(query, names.map { AOPVariable(query, it) }.toMutableList(), tmp)
            res = if (res == null) {
                tmp
            } else {
                LOPUnion(query, res, tmp)
            }
        }
        if (res == null) {
            return LOPNOOP(query)
        }
        return LOPProjection(query, mutableListOf(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o")), LOPDistinct(query, res))
    }

    private fun refineLopGroup(g: LOPGroup): LOPBase {
        val bindingsInside = mutableListOf<Pair<String, AOPBase>>()
        val bindingsOutside = mutableListOf<Pair<String, AOPBase>>()
        for (b in g.bindings) {
            if (containsAggregate(b.second)) {
                bindingsInside.add(b)
            } else {
                bindingsOutside.add(b)
            }
        }
        var res: LOPBase = LOPGroup(query, g.by, bindingsInside, g.getChildren()[0])
        for ((first, second) in bindingsOutside) {
            res = LOPBind(query, AOPVariable(query, first), second, res)
        }
        return res
    }

    private fun visitQueryBase(node: ASTQueryBaseClass, bindp: LOPBind?, bindIsAggregate: Boolean, reduced: Boolean, distinct: Boolean): IOPBase {
        var bind = bindp
        val result = LOPNOOP(query)
        if (node.existsLimit()) {
            result.getLatestChild().setChild(LOPLimit(query, node.limit))
        }
        if (node.existsOffset()) {
            result.getLatestChild().setChild(LOPOffset(query, node.offset))
        }
        if (distinct) {
            result.getLatestChild().setChild(LOPDistinct(query))
        } else if (reduced) {
            result.getLatestChild().setChild(LOPReduced(query))
        }
        if (node.existsOrderBy()) {
            for (order in node.orderBy) {
                result.getLatestChild().setChild(order.visit(this) as LOPSort)
            }
        }
        if (node.existsGroupBy()) {
            if (node.existsHaving()) {
                for (h in node.having) {
                    val expression = h.visit(this) as AOPBase
                    val tmpVar = AOPVariable(query, query.getUniqueVariableName())
                    val tmpBind = LOPBind(query, tmpVar, expression)
                    bind = if (bind != null) {
                        mergeLOPBind(bind, tmpBind)
                    } else {
                        tmpBind
                    }
                    result.getLatestChild().setChild(LOPFilter(query, AOPVariable(query, tmpVar.name)))
                }
            }
            val variables = mutableListOf<AOPVariable>()
            var child: LOPBind? = null
            for (b in node.groupBy) {
                when (b) {
                    is ASTVar -> {
                        variables.add(b.visit(this) as AOPVariable)
                    }
                    is ASTAs -> {
                        val v = AOPVariable(query, b.variable.name)
                        variables.add(v)
                        val tmp = b.expression.visit(this) as AOPBase
                        if (tmp.getRequiredVariableNamesRecoursive().contains(v.name)) {
                            throw RecoursiveVariableDefinitionSyntaxException(v.name)
                        }
                        val tmp2 = LOPBind(query, v, tmp)
                        child = if (child != null) {
                            mergeLOPBind(child, tmp2)
                        } else {
                            tmp2
                        }
                    }
                    else -> {
                        throw GroupByClauseNotUsedException()
                    }
                }
            }
            if (child == null) {
                result.getLatestChild().setChild(refineLopGroup(LOPGroup(query, variables, bind, LOPNOOP(query))))
            } else {
                result.getLatestChild().setChild(refineLopGroup(LOPGroup(query, variables, bind, child)))
            }
        } else {
            if (node.existsHaving()) {
                for (h in node.having) {
                    val expression = h.visit(this) as AOPBase
                    val tmpVar = AOPVariable(query, query.getUniqueVariableName())
                    val tmpBind = LOPBind(query, tmpVar, expression)
                    bind = if (bind != null) {
                        mergeLOPBind(bind, tmpBind)
                    } else {
                        tmpBind
                    }
                    result.getLatestChild().setChild(LOPFilter(query, AOPVariable(query, tmpVar.name)))
                }
                result.getLatestChild().setChild(refineLopGroup(LOPGroup(query, mutableListOf(), bind, LOPNOOP(query))))
            } else {
                if (bindIsAggregate) {
                    result.getLatestChild().setChild(refineLopGroup(LOPGroup(query, mutableListOf(), bind, LOPNOOP(query))))
                } else {
                    if (bind != null) {
                        result.getLatestChild().setChild(bind)
                    }
                }
            }
        }
        if (node.where.isNotEmpty()) {
            result.getLatestChild().setChild(parseGroup(node.where))
        }
        if (node.existsDatasets()) {
            val datasets = mutableMapOf<String, IOPBase>()
            for (d in node.datasets) {
                try {
                    val data = POPValuesImportXML(query, listOf("s", "p", "o"), XMLElement.parseFromAny(File(query.getWorkingDirectory() + d.source_iri).readAsString(), d.source_iri)!!)
                    when (d) {
                        is ASTDefaultGraph -> {
                            datasets[TripleStoreManager.DEFAULT_GRAPH_NAME] = data
                        }
                        is ASTNamedGraph -> {
                            datasets["<" + d.source_iri + ">"] = data
                        }
                    }
                } catch (e: Throwable) {
                    throw DatasetImportFailedException(query.getWorkingDirectory() + d.source_iri)
                }
            }
            return applyDatasets(result, datasets)
        }
        return result
    }

    private fun applyDatasets(node: IOPBase, datasets: MutableMap<String, IOPBase>): IOPBase {
        if (node is LOPTriple) {
            return if (node.graphVar) {
                var tmp: IOPBase? = null
                for ((k, v) in datasets) {
                    val t = LOPBind(node.query, AOPVariable(query, node.graph), AOPConstant(query, ValueDefinition(k)), v)
                    tmp = if (tmp == null) {
                        t
                    } else {
                        createUnion(tmp, t)
                    }
                }
                tmp!!
            } else {
                datasets[node.graph]!!
            }
        } else {
            for (i in node.getChildren().indices) {
                node.getChildren()[i] = applyDatasets(node.getChildren()[i], datasets)
            }
            return node
        }
    }

    private fun parseGroup(nodes: Array<ASTNode>): IOPBase {
        if (nodes.isEmpty()) {
            return LOPNOOP(query)
        }
        var result: IOPBase? = null
        val bind = mutableListOf<LOPBind>()
        val members = mutableMapOf<EGroupMember, IOPBase>()
        for (n in nodes) {
            var tmp2 = n.visit(this)
            while (tmp2 is LOPNOOP) {
                tmp2 = tmp2.getChildren()[0]
            }
            when (tmp2) {
                is LOPMinus -> {
                    if (members.containsKey(EGroupMemberExt.GMLOPMinus)) {
                        (members[EGroupMemberExt.GMLOPMinus])!!.getLatestChild().setChild(tmp2)
                    } else {
                        members[EGroupMemberExt.GMLOPMinus] = tmp2
                    }
                }
                is LOPFilter -> {
                    if (members.containsKey(EGroupMemberExt.GMLOPFilter)) {
                        (members[EGroupMemberExt.GMLOPFilter])!!.getLatestChild().setChild(tmp2)
                    } else {
                        members[EGroupMemberExt.GMLOPFilter] = tmp2
                    }
                }
                is LOPProjection -> {
                    if (members.containsKey(EGroupMemberExt.GMLOPDataSource)) {
                        members[EGroupMemberExt.GMLOPDataSource] = LOPJoin(query, members[EGroupMemberExt.GMLOPDataSource]!!, tmp2, false)
                    } else {
                        members[EGroupMemberExt.GMLOPDataSource] = tmp2
                    }
                }
                is LOPBind -> {
                    bind.add(tmp2)
                }
                is LOPTriple -> {
                    if (members.containsKey(EGroupMemberExt.GMLOPDataSource)) {
                        members[EGroupMemberExt.GMLOPDataSource] = LOPJoin(query, members[EGroupMemberExt.GMLOPDataSource]!!, tmp2, false)
                    } else {
                        members[EGroupMemberExt.GMLOPDataSource] = tmp2
                    }
                }
                is LOPUnion -> {
                    if (members.containsKey(EGroupMemberExt.GMLOPDataSource)) {
                        members[EGroupMemberExt.GMLOPDataSource] = LOPJoin(query, members[EGroupMemberExt.GMLOPDataSource]!!, tmp2, false)
                    } else {
                        members[EGroupMemberExt.GMLOPDataSource] = tmp2
                    }
                }
                is LOPValues -> {
                    if (members.containsKey(EGroupMemberExt.GMLOPDataSource)) {
                        members[EGroupMemberExt.GMLOPDataSource] = LOPJoin(query, members[EGroupMemberExt.GMLOPDataSource]!!, tmp2, false)
                    } else {
                        members[EGroupMemberExt.GMLOPDataSource] = tmp2
                    }
                }
                is LOPOptional -> {
                    var optionalRoot: IOPBase = tmp2.getChildren()[0]
                    while (optionalRoot is LOPFilter) {
                        val child = optionalRoot.getChildren()[0]
                        optionalRoot.getChildren()[0] = LOPNOOP(query)
                        if (members.containsKey(EGroupMemberExt.GMLOPFilter)) {
                            (members[EGroupMemberExt.GMLOPFilter])!!.getLatestChild().setChild(optionalRoot)
                        } else {
                            members[EGroupMemberExt.GMLOPFilter] = optionalRoot
                        }
                        optionalRoot = child
                    }
                    if (members.containsKey(EGroupMemberExt.GMLOPOptional)) {
                        members[EGroupMemberExt.GMLOPOptional] = LOPJoin(query, members[EGroupMemberExt.GMLOPOptional]!!, optionalRoot, true)
                    } else {
                        members[EGroupMemberExt.GMLOPOptional] = optionalRoot
                    }
                }
                is LOPJoin -> {
                    if (members.containsKey(EGroupMemberExt.GMLOPDataSource)) {
                        members[EGroupMemberExt.GMLOPDataSource] = LOPJoin(query, members[EGroupMemberExt.GMLOPDataSource]!!, tmp2, true)
                    } else {
                        members[EGroupMemberExt.GMLOPDataSource] = tmp2
                    }
                }
                is LOPSubGroup -> {
                    if (members.containsKey(EGroupMemberExt.GMLOPDataSource)) {
                        members[EGroupMemberExt.GMLOPDataSource] = LOPJoin(query, members[EGroupMemberExt.GMLOPDataSource]!!, tmp2, false)
                    } else {
                        members[EGroupMemberExt.GMLOPDataSource] = tmp2
                    }
                }
                is LOPServiceIRI -> {
                    if (members.containsKey(EGroupMemberExt.GMLOPDataSource)) {
                        members[EGroupMemberExt.GMLOPDataSource] = LOPJoin(query, members[EGroupMemberExt.GMLOPDataSource]!!, tmp2, true)
                    } else {
                        members[EGroupMemberExt.GMLOPDataSource] = tmp2
                    }
                }
                is LOPServiceVAR -> {
                    if (members.containsKey(EGroupMemberExt.GMLOPDataSource)) {
                        tmp2.getChildren()[0] = members[EGroupMemberExt.GMLOPDataSource]!!
                        members[EGroupMemberExt.GMLOPDataSource] = tmp2
                    } else {
                        members[EGroupMemberExt.GMLOPDataSource] = tmp2
                    }
                }
                else -> {
                    throw SparqlFeatureNotImplementedException(tmp2.getClassname())
                }
            }
        }
        if (members.containsKey(EGroupMemberExt.GMLOPFilter)) {
            if (result == null) {
                result = members[EGroupMemberExt.GMLOPFilter]
            } else {
                (result).getLatestChild().setChild(members[EGroupMemberExt.GMLOPFilter]!!)
            }
        }
        var firstJoin: IOPBase? = null
        if (members.containsKey(EGroupMemberExt.GMLOPDataSource)) {
            firstJoin = members[EGroupMemberExt.GMLOPDataSource]
        }
        if (members.containsKey(EGroupMemberExt.GMLOPOptional)) {
            firstJoin = if (firstJoin == null) {
                LOPOptional(query, members[EGroupMemberExt.GMLOPOptional]!!)
            } else {
                LOPJoin(query, firstJoin, members[EGroupMemberExt.GMLOPOptional]!!, true)
            }
        }
        if (firstJoin == null) {
            var bb: LOPBind? = null
            for (b in bind) {
                bb = if (bb == null) {
                    b
                } else {
                    mergeLOPBind(bb, b)
                }
            }
            firstJoin = bb
        } else {
            for (b in bind) {
                firstJoin = insertLOPBind(firstJoin!!, b)
            }
        }
        if (firstJoin != null) {
            if (result == null) {
                result = firstJoin
            } else {
                (result).getLatestChild().setChild(firstJoin)
            }
        }
        if (members.containsKey(EGroupMemberExt.GMLOPMinus)) {
            var tmp = members[EGroupMemberExt.GMLOPMinus]!!
/*
tmp.getLatestChild().setChild(result!!)
return tmp
*/
            while (tmp is LOPMinus) {
                val a = result!!
                val b = LOPJoin(query, result.cloneOP(), tmp.getChildren()[1], false)
                val t = a.getProvidedVariableNames().toMutableList()
                result = LOPMinus(query, a, b, t)
                tmp = tmp.getChildren()[0]
            }
        }
        return result!!
    }

    private fun insertLOPBind(a: IOPBase, b: LOPBind): IOPBase {
        if (a is LOPJoin) {
            val requiredVariables = b.getChildren()[1].getRequiredVariableNames()
            val providedLeft = a.getChildren()[0].getProvidedVariableNames()
            var leftOk = true
            for (v in requiredVariables) {
                if (!providedLeft.contains(v)) {
                    leftOk = false
                    break
                }
            }
            val providedRight = a.getChildren()[1].getProvidedVariableNames()
            var rightOk = true
            for (v in requiredVariables) {
                if (!providedRight.contains(v)) {
                    rightOk = false
                    break
                }
            }
            if (leftOk != rightOk) {
                if (leftOk) {
                    a.getChildren()[0] = insertLOPBind(a.getChildren()[0], b)
                } else {
                    return LOPJoin(query, a.getChildren()[0], insertLOPBind(a.getChildren()[1], b), a.optional)
                }
                return a
            }
        }
        b.getLatestChild().setChild(a)
        return b
    }

    override fun visit(node: ASTQuery, childrenValues: List<IOPBase>): IOPBase {
        if (childrenValues.isEmpty()) {
            return LOPNOOP(query) // empty query
        }
        val childs = mutableListOf<IOPBase>()
        var prefix: LOPPrefix? = null
        /*prefix is applied at the end*/
        for (q in childrenValues) {
            if (q is LOPPrefix) {
                if (prefix == null) {
                    prefix = q
                } else {
                    prefix.getLatestChild().setChild(q)
                }
            } else if (q is LOPValues) {
                if (childs.size > 0) {
                    childs[childs.size - 1] = joinValuesAndQuery(q, childs[childs.size - 1])
                } else {
                    childs.add(q)
                }
            } else {
                childs.add(q)
            }
        }
        if (prefix != null) {
            for (i in 0 until childs.size) {
                val tmp = prefix.cloneOP()
                tmp.getLatestChild().setChild(childs[i])
                childs[i] = tmp
            }
        }
        val columnProjectionOrder = mutableListOf<List<String>>()
        for (i in 0 until childs.size) {
            var x = childs[i]
            val list = mutableListOf<String>()
            while (x is LOPPrefix || x is LOPSubGroup || x is LOPNOOP) {
                x = x.getChildren()[0]
            }
            if (x is LOPProjection) {
                list.addAll(x.variables.map { it.name })
            } else {
            }
            columnProjectionOrder.add(list)
        }
        var res: IOPBase = OPBaseCompound(query, childs.toTypedArray(), columnProjectionOrder)
        res = preventTriplesWithMultipleInstancesOfTheSameVariable(res)
        return res
    }

    private fun preventTriplesWithMultipleInstancesOfTheSameVariable(node: IOPBase): IOPBase {
        if (node is LOPTriple) {
            val variables = mutableSetOf<String>()
            for (i in node.getChildren().indices) {
                val c = node.getChildren()[i]
                if (c is AOPVariable) {
                    if (variables.contains(c.name)) {
                        val newVariable = AOPVariable(node.query, query.getUniqueVariableName())
                        node.updateChildren(i, newVariable)
                        val tmp = LOPFilter(node.query, AOPEQ(query, newVariable, c), node)
                        return preventTriplesWithMultipleInstancesOfTheSameVariable(tmp)
                    } else {
                        variables.add(c.name)
                    }
                }
            }
        } else {
            for (i in node.getChildren().indices) {
                node.updateChildren(i, preventTriplesWithMultipleInstancesOfTheSameVariable(node.getChildren()[i]))
            }
        }
        return node
    }

    private fun joinValuesAndQuery(values: IOPBase?, opbase: IOPBase): IOPBase {
        if (values == null) {
            return opbase
        }
        if (opbase !is LOPProjection) {
            return LOPJoin(query, values, opbase, false)
        }
        var latestProjection = opbase
        var realQuery = opbase
        while (realQuery is LOPProjection) {
            latestProjection = realQuery
            realQuery = realQuery.getChildren()[0]
        }
        (latestProjection as LOPProjection).setChild(LOPJoin(query, values, realQuery, false))
        return opbase
    }

    override fun visit(node: ASTUndef, childrenValues: List<IOPBase>): IOPBase {
        return AOPConstant(query, ValueUndef())
    }

    override fun visit(node: ASTSimpleLiteral, childrenValues: List<IOPBase>): IOPBase {
        return AOPConstant(query, ValueSimpleLiteral(node.delimiter, node.content))
    }

    override fun visit(node: ASTTypedLiteral, childrenValues: List<IOPBase>): IOPBase {
        return AOPConstant(query, ValueDefinition(node.delimiter + node.content + node.delimiter + "^^<" + node.type_iri + ">"))
    }

    override fun visit(node: ASTLanguageTaggedLiteral, childrenValues: List<IOPBase>): IOPBase {
        return AOPConstant(query, ValueLanguageTaggedLiteral(node.delimiter, node.content, node.language))
    }

    override fun visit(node: ASTBooleanLiteral, childrenValues: List<IOPBase>): IOPBase {
        return AOPConstant(query, ValueBoolean(node.value))
    }

    override fun visit(node: ASTInteger, childrenValues: List<IOPBase>): IOPBase {
        return AOPConstant(query, ValueInteger(MyBigInteger(node.value)))
    }

    override fun visit(node: ASTDouble, childrenValues: List<IOPBase>): IOPBase {
        return AOPConstant(query, ValueDouble(node.toDouble()))
    }

    override fun visit(node: ASTDecimal, childrenValues: List<IOPBase>): IOPBase {
        return AOPConstant(query, ValueDecimal(MyBigDecimal(node.toDouble())))
    }

    override fun visit(node: ASTFunctionCall, childrenValues: List<IOPBase>): IOPBase {
        return when (node.iri) {
            "http://www.w3.org/2001/XMLSchema#double" -> {
                AOPFunctionCallDouble(query, childrenValues[0] as AOPBase)
            }
            "http://www.w3.org/2001/XMLSchema#float" -> {
                AOPFunctionCallFloat(query, childrenValues[0] as AOPBase)
            }
            "http://www.w3.org/2001/XMLSchema#string" -> {
                AOPFunctionCallString(query, childrenValues[0] as AOPBase)
            }
            else -> {
                throw SparqlFeatureNotImplementedException("ASTFunctionCall ${node.iri} ${node.distinct}")
            }
        }
    }

    override fun visit(node: ASTTriple, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.size == 3 }
        return LOPTriple(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase, childrenValues[2] as AOPBase, TripleStoreManager.DEFAULT_GRAPH_NAME, false)
    }

    override fun visit(node: ASTMinusGroup, childrenValues: List<IOPBase>): IOPBase {
        return LOPMinus(query, LOPNOOP(query), parseGroup(node.children), listOf())
    }

    override fun visit(node: ASTOptional, childrenValues: List<IOPBase>): IOPBase {
        return LOPOptional(query, LOPSubGroup(query, parseGroup(node.children)))
    }

    override fun visit(node: ASTSet, childrenValues: List<IOPBase>): IOPBase {
        val tmp = List(childrenValues.size) { childrenValues[it] as AOPBase }
        return AOPSet(query, tmp)
    }

    override fun visit(node: ASTOr, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.size > 1 }
        var res: AOPBase? = null
        for (v in childrenValues) {
            res = if (res == null) {
                v as AOPBase
            } else {
                AOPOr(query, v as AOPBase, res)
            }
        }
        return res!!
    }

    override fun visit(node: ASTAnd, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.size > 1 }
        var res: AOPBase? = null
        for (v in childrenValues) {
            res = if (res == null) {
                v as AOPBase
            } else {
                AOPAnd(query, v as AOPBase, res)
            }
        }
        return res!!
    }

    override fun visit(node: ASTEQ, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.size == 2 }
        return AOPEQ(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTNEQ, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.size == 2 }
        return AOPNEQ(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTLEQ, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.size == 2 }
        return AOPLEQ(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTGEQ, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.size == 2 }
        return AOPGEQ(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTLT, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.size == 2 }
        return AOPLT(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTGT, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.size == 2 }
        return AOPGT(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTIn, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.size == 2 }
        return AOPIn(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTNotIn, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.size == 2 }
        return AOPNotIn(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTAddition, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.size > 1 }
        var res: AOPBase? = null
        for (v in childrenValues) {
            res = if (res == null) {
                v as AOPBase
            } else {
                AOPAddition(query, v as AOPBase, res)
            }
        }
        return res!!
    }

    override fun visit(node: ASTSubtraction, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.size > 1 }
        var res: AOPBase? = null
        for (i in childrenValues.indices) {
            val v = childrenValues[childrenValues.size - 1 - i]
            res = if (res == null) {
                v as AOPBase
            } else {
                AOPSubtraction(query, v as AOPBase, res)
            }
        }
        return res!!
    }

    override fun visit(node: ASTMultiplication, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.size > 1 }
        var res: AOPBase? = null
        for (v in childrenValues) {
            res = if (res == null) {
                v as AOPBase
            } else {
                AOPMultiplication(query, v as AOPBase, res)
            }
        }
        return res!!
    }

    override fun visit(node: ASTDivision, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.size > 1 }
        var res: AOPBase? = null
        for (i in childrenValues.indices) {
            val v = childrenValues[childrenValues.size - 1 - i]
            res = if (res == null) {
                v as AOPBase
            } else {
                AOPDivision(query, v as AOPBase, res)
            }
        }
        return res!!
    }

    override fun visit(node: ASTNot, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.size == 1 }
        return AOPNot(query, childrenValues[0] as AOPBase)
    }

    override fun visit(node: ASTBase, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.isEmpty() }
        return LOPPrefix(query, "", node.iri)
    }

    override fun visit(node: ASTPrefix, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.isEmpty() }
        return LOPPrefix(query, node.name, node.iri)
    }

    override fun visit(node: ASTAs, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.isEmpty() }
        val a = node.variable.visit(this) as AOPVariable
        val b = node.expression.visit(this) as AOPBase
        if (b.getRequiredVariableNamesRecoursive().contains(a.name)) {
            throw RecoursiveVariableDefinitionSyntaxException(a.name)
        }
        return LOPBind(query, a, b)
    }

    override fun visit(node: ASTBlankNode, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.isEmpty() }
        return AOPVariable(query, query.getUniqueVariableName(node.name))
// blank nodes are used for dont care within the queries. the only place, where the bnode is required as a value is within the insert/delete-clauses. there it needs to be replaced
    }

    override fun visit(node: ASTBuiltInCall, childrenValues: List<IOPBase>): IOPBase {
        when (node.function) {
            BuiltInFunctionsExt.STR -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallSTR(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.LANG -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallLANG(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.LANGMATCHES -> {
                SanityCheck.check { childrenValues.size == 2 }
                return AOPBuildInCallLANGMATCHES(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctionsExt.DATATYPE -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallDATATYPE(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.BOUND -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallBOUND(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.IRI -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallIRI(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.URI -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallURI(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.BNODE -> {
                if (childrenValues.size == 1) {
                    return AOPBuildInCallBNODE1(query, childrenValues[0] as AOPBase)
                }
                return AOPBuildInCallBNODE0(query)
            }
            BuiltInFunctionsExt.ABS -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallABS(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.CEIL -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallCEIL(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.FLOOR -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallFLOOR(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.ROUND -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallROUND(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.CONCAT -> {
                SanityCheck.check { childrenValues.isNotEmpty() }
                var res = childrenValues[0] as AOPBase
                for (i in 1 until childrenValues.size) {
                    res = AOPBuildInCallCONCAT(query, res, childrenValues[i] as AOPBase)
                }
                return res
            }
            BuiltInFunctionsExt.COALESCE -> {
                return AOPBuildInCallCOALESCE(query, childrenValues.map { it as AOPBase })
            }
            BuiltInFunctionsExt.STRLEN -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallSTRLEN(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.UCASE -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallUCASE(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.LCASE -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallLCASE(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.CONTAINS -> {
                SanityCheck.check { childrenValues.size == 2 }
                return AOPBuildInCallCONTAINS(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctionsExt.STRSTARTS -> {
                SanityCheck.check { childrenValues.size == 2 }
                return AOPBuildInCallSTRSTARTS(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctionsExt.STRENDS -> {
                SanityCheck.check { childrenValues.size == 2 }
                return AOPBuildInCallSTRENDS(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctionsExt.YEAR -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallYEAR(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.MONTH -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallMONTH(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.DAY -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallDAY(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.HOURS -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallHOURS(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.MINUTES -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallMINUTES(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.SECONDS -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallSECONDS(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.TIMEZONE -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallTIMEZONE(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.TZ -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallTZ(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.NOW -> {
                SanityCheck.check { childrenValues.isEmpty() }
                return AOPConstant(query, queryExecutionStartTime)
            }
            BuiltInFunctionsExt.UUID -> {
                SanityCheck.check { childrenValues.isEmpty() }
                return AOPBuildInCallUUID(query)
            }
            BuiltInFunctionsExt.STRUUID -> {
                SanityCheck.check { childrenValues.isEmpty() }
                return AOPBuildInCallSTRUUID(query)
            }
            BuiltInFunctionsExt.MD5 -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallMD5(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.SHA1 -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallSHA1(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.SHA256 -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallSHA256(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.IF -> {
                SanityCheck.check { childrenValues.size == 3 }
                return AOPBuildInCallIF(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase, childrenValues[2] as AOPBase)
            }
            BuiltInFunctionsExt.STRLANG -> {
                SanityCheck.check { childrenValues.size == 2 }
                return AOPBuildInCallSTRLANG(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctionsExt.STRAFTER -> {
                SanityCheck.check { childrenValues.size == 2 }
                return AOPBuildInCallSTRAFTER(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctionsExt.STRBEFORE -> {
                SanityCheck.check { childrenValues.size == 2 }
                return AOPBuildInCallSTRBEFORE(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctionsExt.STRDT -> {
                SanityCheck.check { childrenValues.size == 2 }
                return AOPBuildInCallSTRDT(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctionsExt.isLITERAL -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallIsLITERAL(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.isIRI -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallIsIri(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.isNUMERIC -> {
                SanityCheck.check { childrenValues.size == 1 }
                return AOPBuildInCallIsNUMERIC(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.NotExists -> {
                query.dontCheckVariableExistence = true
                return AOPBuildInCallNotExists(query, parseGroup(node.children))
            }
            BuiltInFunctionsExt.Exists -> {
                query.dontCheckVariableExistence = true
                return AOPBuildInCallExists(query, parseGroup(node.children))
            }
            else -> {
                throw SparqlFeatureNotImplementedException("BuiltInFunctionsExt." + node.function.toString())
            }
        }
        /*Coverage Unreachable*/
    }

    override fun visit(node: ASTAggregation, childrenValues: List<IOPBase>): IOPBase {
        when (node.type) {
            AggregationExt.COUNT -> {
                return AOPAggregationCOUNT(query, node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            }
            AggregationExt.MIN -> {
                return AOPAggregationMIN(query, node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            }
            AggregationExt.MAX -> {
                return AOPAggregationMAX(query, node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            }
            AggregationExt.SAMPLE -> {
                return AOPAggregationSAMPLE(query, node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            }
            AggregationExt.AVG -> {
                return AOPAggregationAVG(query, node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            }
            AggregationExt.SUM -> {
                return AOPAggregationSUM(query, node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            }
            AggregationExt.GROUP_CONCAT -> {
                throw SparqlFeatureNotImplementedException("AggregationExt.GROUP_CONCAT")
            }
            else -> {
                throw UnreachableException()
            }
        }
        /*Coverage Unreachable*/
    }

    override fun visit(node: ASTUnion, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.size >= 2 }
        val tmplist = mutableListOf<IOPBase>()
        for (v in childrenValues) {
            tmplist.add(v)
        }
        while (tmplist.size > 1) {
            val a = tmplist.removeAt(0)
            val b = tmplist.removeAt(0)
            val c = createUnion(a, b)
            tmplist.add(c)
        }
        return tmplist[0]
    }

    override fun visit(node: ASTFilter, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.size == 1 }
        val child = childrenValues.first() as AOPBase
        if (containsAggregate(node.children.first())) {
            throw AggregateNotAllowedSyntaxException()
        }
        return LOPFilter(query, child)
    }

    override fun visit(node: ASTOrderCondition, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.size == 1 }
        val tmp = childrenValues.first() as AOPBase
        if (tmp is AOPVariable) {
            return LOPSort(query, node.asc, tmp)
        }
        val v = AOPVariable(query, "#f${tmp.uuid}")
        return LOPSort(query, node.asc, v, LOPBind(query, v, tmp))
    }

    override fun visit(node: ASTVar, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.isEmpty() }
        return AOPVariable(query, node.name)
    }

    override fun visit(node: ASTIri, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.isEmpty() }
        return AOPConstant(query, ValueIri(node.iri))
    }

    override fun visit(node: ASTGroup, childrenValues: List<IOPBase>): IOPBase {
        return LOPSubGroup(query, parseGroup(node.children))
    }

    override fun visit(node: ASTService, childrenValues: List<IOPBase>): IOPBase {
        when (val iriOrVar = node.iriOrVar) {
            is ASTIri -> {
                return LOPServiceIRI(query, iriOrVar.iri, node.silent, parseGroup(node.children))
            }
            is ASTVar -> {
                return LOPServiceVAR(query, iriOrVar.name, node.silent, parseGroup(node.children))
            }
            else -> {
                SanityCheck.checkUnreachable()
            }
        }
        /*Coverage Unreachable*/
    }

    override fun visit(node: ASTValues, childrenValues: List<IOPBase>): IOPBase {
        val variables = mutableListOf<AOPVariable>()
        val values = mutableListOf<AOPValue>()
        for (v in node.variables) {
            variables.add(v.visit(this) as AOPVariable)
        }
        for (v in node.children) {
            values.add(v.visit(this) as AOPValue)
        }
        return LOPValues(query, variables, values)
    }

    override fun visit(node: ASTValue, childrenValues: List<IOPBase>): IOPBase {
        val tmp = List(childrenValues.size) { childrenValues[it] as AOPConstant }
        return AOPValue(query, tmp)
    }

    private fun setGraphNameForAllTriples(node: IOPBase, name: ASTNode, optional: Boolean): IOPBase {
        var iriIsVariable = false
        val iri = when (name) {
            is ASTIri -> {
                name.iri
            }
            is ASTNamedIriGraphRef -> {
                name.iri
            }
            is ASTIriGraphRef -> {
                name.iri
            }
            is ASTVar -> {
                iriIsVariable = true
                name.name
            }
            else -> {
                SanityCheck.checkUnreachable()
            }
        }
        when (node) {
            is OPEmptyRow -> {
                return node
            }
            is LOPTriple -> {
                return if (!optional || node.graph == TripleStoreManager.DEFAULT_GRAPH_NAME) {
                    LOPTriple(query, node.getChildren()[0] as AOPBase, node.getChildren()[1] as AOPBase, node.getChildren()[2] as AOPBase, iri, iriIsVariable)
                } else {
                    node
                }
            }
            is LOPFilter -> {
                node.getChildren()[0] = setGraphNameForAllTriples(node.getChildren()[0], name, optional)
            }
            is LOPJoin -> {
                return LOPJoin(query, setGraphNameForAllTriples(node.getChildren()[0], name, optional), setGraphNameForAllTriples(node.getChildren()[1], name, optional), node.optional)
            }
            is LOPUnion -> {
                return createUnion(setGraphNameForAllTriples(node.getChildren()[0], name, optional), setGraphNameForAllTriples(node.getChildren()[1], name, optional))
            }
            else -> {
                throw SparqlFeatureNotImplementedException(node.getClassname())
            }
        }
        return node
    }

    override fun visit(node: ASTGraph, childrenValues: List<IOPBase>): IOPBase {
        var res: IOPBase = OPEmptyRow(query)
        for (c in childrenValues) {
            val tmp = setGraphNameForAllTriples(c, node.iriOrVar, false)
            res = if (res is OPEmptyRow) {
                tmp
            } else {
                LOPJoin(query, res, tmp, false)
            }
        }
        return res
    }

    private fun graphRefToEnum(ref: ASTGraphRef): Pair<EGraphRefType, String?> {
        when (ref) {
            is ASTIriGraphRef -> {
                return Pair(EGraphRefTypeExt.IriGraphRef, ref.iri)
            }
            is ASTNamedIriGraphRef -> {
                return Pair(EGraphRefTypeExt.IriGraphRef, ref.iri)
            }
            is ASTDefaultGraphRef -> {
                return Pair(EGraphRefTypeExt.DefaultGraphRef, null)
            }
            is ASTNamedGraphRef -> {
                return Pair(EGraphRefTypeExt.NamedGraphRef, null)
            }
            is ASTAllGraphRef -> {
                return Pair(EGraphRefTypeExt.AllGraphRef, null)
            }
            else -> {
                SanityCheck.checkUnreachable()
            }
        }
        /*Coverage Unreachable*/
    }

    override fun visit(node: ASTAdd, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.isEmpty() }
        val g1 = graphRefToEnum(node.fromGraph)
        val g2 = graphRefToEnum(node.toGraph)
        return LOPGraphOperation(query, EGraphOperationTypeExt.ADD, node.silent, g1.first, g1.second, g2.first, g2.second)
    }

    override fun visit(node: ASTMove, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.isEmpty() }
        val g1 = graphRefToEnum(node.fromGraph)
        val g2 = graphRefToEnum(node.toGraph)
        return LOPGraphOperation(query, EGraphOperationTypeExt.MOVE, node.silent, g1.first, g1.second, g2.first, g2.second)
    }

    override fun visit(node: ASTCopy, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.isEmpty() }
        val g1 = graphRefToEnum(node.fromGraph)
        val g2 = graphRefToEnum(node.toGraph)
        return LOPGraphOperation(query, EGraphOperationTypeExt.COPY, node.silent, g1.first, g1.second, g2.first, g2.second)
    }

    override fun visit(node: ASTClear, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.isEmpty() }
        val g1 = graphRefToEnum(node.graphref)
        return LOPGraphOperation(query, EGraphOperationTypeExt.CLEAR, node.silent, g1.first, g1.second)
    }

    override fun visit(node: ASTDrop, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.isEmpty() }
        val g1 = graphRefToEnum(node.graphref)
        return LOPGraphOperation(query, EGraphOperationTypeExt.DROP, node.silent, g1.first, g1.second)
    }

    override fun visit(node: ASTCreate, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.isEmpty() }
        val g1 = graphRefToEnum(node.graphref)
        return LOPGraphOperation(query, EGraphOperationTypeExt.CREATE, node.silent, g1.first, g1.second)
    }

    private fun simpleAstToLiteralValue(node: ASTNode): AOPBase {
        val tmp = node.visit(this) as AOPBase
        if (tmp is AOPVariable) {
            return AOPConstant(query, ValueBnode(tmp.name))
        }
        return tmp
    }

    private fun modifyDataHelper(children: Array<ASTNode>, modify: LOPModifyData) {
        for (c in children) {
            when (c) {
                is ASTTriple -> {
                    modify.data.add(LOPTriple(query, simpleAstToLiteralValue(c.children[0]), simpleAstToLiteralValue(c.children[1]), simpleAstToLiteralValue(c.children[2]), TripleStoreManager.DEFAULT_GRAPH_NAME, false))
                }
                is ASTGraph -> {
                    for (c2 in c.children) {
                        when (c2) {
                            is ASTTriple -> {
                                val nameOrVar = c.iriOrVar
                                if (nameOrVar is ASTIri) {
                                    modify.data.add(LOPTriple(query, simpleAstToLiteralValue(c2.children[0]), simpleAstToLiteralValue(c2.children[1]), simpleAstToLiteralValue(c2.children[2]), nameOrVar.iri, false))
                                } else if (nameOrVar is ASTVar) {
                                    modify.data.add(LOPTriple(query, simpleAstToLiteralValue(c2.children[0]), simpleAstToLiteralValue(c2.children[1]), simpleAstToLiteralValue(c2.children[2]), nameOrVar.name, true))
                                }
                            }
                            else -> {
                                SanityCheck.checkUnreachable()
                            }
                        }
                    }
                }
                else -> {
                    SanityCheck.checkUnreachable()
                }
            }
        }
    }

    override fun visit(node: ASTDeleteData, childrenValues: List<IOPBase>): IOPBase {
        val res = LOPModifyData(query, EModifyTypeExt.DELETE)
        modifyDataHelper(node.children, res)
        return res
    }

    override fun visit(node: ASTDeleteWhere, childrenValues: List<IOPBase>): IOPBase {
        return visit(ASTModifyWithWhere(null, node.children, arrayOf(), arrayOf(), node.children), listOf<OPBase>())
    }

    override fun visit(node: ASTInsertData, childrenValues: List<IOPBase>): IOPBase {
        val res = LOPModifyData(query, EModifyTypeExt.INSERT)
        modifyDataHelper(node.children, res)
        return res
    }

    private fun joinToList(node: IOPBase): List<IOPBase> {
        if (node is LOPJoin) {
            val res = mutableListOf<IOPBase>()
            res.addAll(joinToList(node.getChildren()[0]))
            res.addAll(joinToList(node.getChildren()[1]))
            return res
        }
        return listOf(node)
    }

    private fun variableToBNode(node: IOPBase, providedVariables: List<String>): IOPBase {
        if (node is AOPVariable) {
            if (!providedVariables.contains(node.name)) {
                return AOPConstant(node.query, ValueBnode(node.name))
            }
        } else {
            for (i in node.getChildren().indices) {
                node.updateChildren(i, variableToBNode(node.getChildren()[i], providedVariables))
            }
        }
        return node
    }

    override fun visit(node: ASTModifyWithWhere, childrenValues: List<IOPBase>): IOPBase {
        val child: IOPBase = if (node.using.isEmpty()) {
            parseGroup(node.children)
        } else {
            var tmp: IOPBase? = null
            for (c in node.using) {
                val t = parseGroup(node.children)
                val tmp2 = setGraphNameForAllTriples(t, c, false)
                tmp = if (tmp == null) {
                    tmp2
                } else {
                    createUnion(tmp, tmp2)
                }
            }
            tmp!!
        }
        val providedVariables = child.getProvidedVariableNames()
        val iri = node.iri
        val insert: MutableList<LOPTriple> = mutableListOf()
        val delete: MutableList<LOPTriple> = mutableListOf()
        if (iri != null) {
            for (e in node.insert) {
                insert.add(variableToBNode(setGraphNameForAllTriples(e.visit(this), ASTIri(iri), true), providedVariables) as LOPTriple)
            }
            for (e in node.delete) {
                delete.add(variableToBNode(setGraphNameForAllTriples(e.visit(this), ASTIri(iri), true), providedVariables) as LOPTriple)
            }
            return LOPModify(query, insert, delete, setGraphNameForAllTriples(child, ASTIri(iri), true))
        } else {
            for (e in node.insert) {
                for (tmp in joinToList(e.visit(this))) {
                    insert.add(variableToBNode(tmp, providedVariables) as LOPTriple)
                }
            }
            for (e in node.delete) {
                for (tmp in joinToList(e.visit(this))) {
                    delete.add(variableToBNode(tmp, providedVariables) as LOPTriple)
                }
            }
            return LOPModify(query, insert, delete, child)
        }
        /*Coverage Unreachable*/
    }

    override fun visit(node: ASTLoad, childrenValues: List<IOPBase>): IOPBase {
        val tmp = node.into
        return if (tmp != null) {
            val g2 = graphRefToEnum(tmp)
            LOPGraphOperation(query, EGraphOperationTypeExt.LOAD, node.silent, EGraphRefTypeExt.DefaultGraphRef, node.iri, g2.first, g2.second)
        } else {
            LOPGraphOperation(query, EGraphOperationTypeExt.LOAD, node.silent, EGraphRefTypeExt.DefaultGraphRef, node.iri, EGraphRefTypeExt.DefaultGraphRef, TripleStoreManager.DEFAULT_GRAPH_NAME)
        }
    }

    override fun visit(node: ASTModify, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTModify")
    }

    override fun visit(node: ASTDefaultGraph, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTDefaultGraph")
    }

    override fun visit(node: ASTNamedGraph, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTNamedGraph")
    }

    override fun visit(node: ASTGraphRef, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTGraphRef")
    }

    override fun visit(node: ASTIriGraphRef, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTIriGraphRef")
    }

    override fun visit(node: ASTNamedIriGraphRef, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTNamedIriGraphRef")
    }

    override fun visit(node: ASTDefaultGraphRef, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTDefaultGraphRef")
    }

    override fun visit(node: ASTNamedGraphRef, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTNamedGraphRef")
    }

    override fun visit(node: ASTAllGraphRef, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTAllGraphRef")
    }

    override fun visit(node: ASTGrapOperation, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTGrapOperation")
    }

    override fun visit(node: ASTUpdateGrapOperation, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTUpdateGrapOperation")
    }

    override fun visit(node: ASTPathAlternatives, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTPathAlternatives")
    }

    override fun visit(node: ASTPathSequence, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTPathSequence")
    }

    override fun visit(node: ASTPathInverse, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTPathInverse")
    }

    override fun visit(node: ASTPathArbitraryOccurrences, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTPathArbitraryOccurrences")
    }

    override fun visit(node: ASTPathOptionalOccurrence, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTPathOptionalOccurrence")
    }

    override fun visit(node: ASTPathArbitraryOccurrencesNotZero, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTPathArbitraryOccurrencesNotZero")
    }

    override fun visit(node: ASTPathNegatedPropertySet, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTPathNegatedPropertySet")
    }

    override fun visit(node: ASTGroupConcat, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTGroupConcat")
    }

    override fun visit(node: ASTDatasetClause, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTDatasetClause")
    }

    override fun visit(node: ASTQueryBaseClass, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTQueryBaseClass")
    }

    override fun visit(node: ASTRDFTerm, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTRDFTerm")
    }

    override fun visit(node: ASTPlus, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTPlus")
    }

    override fun visit(node: ASTMinus, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check { childrenValues.size == 2 }
        return LOPMinus(query, childrenValues[0], childrenValues[1], listOf())
    }

    override fun visit(node: ASTNumericLiteral, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTNumericLiteral")
    }

    override fun visit(node: ASTLiteral, childrenValues: List<IOPBase>): IOPBase {
        throw SparqlFeatureNotImplementedException("ASTLiteral")
    }
}
