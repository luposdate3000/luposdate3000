package lupos.s06buildOperatorGraph

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.classNameToString
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EGroupMember
import lupos.s00misc.SanityCheck
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
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
import lupos.s02buildSyntaxTree.sparql1_1.ASTGraph
import lupos.s02buildSyntaxTree.sparql1_1.ASTGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTGrapOperation
import lupos.s02buildSyntaxTree.sparql1_1.ASTGroup
import lupos.s02buildSyntaxTree.sparql1_1.ASTGroupConcat
import lupos.s02buildSyntaxTree.sparql1_1.ASTGT
import lupos.s02buildSyntaxTree.sparql1_1.ASTIn
import lupos.s02buildSyntaxTree.sparql1_1.ASTInsertData
import lupos.s02buildSyntaxTree.sparql1_1.ASTInteger
import lupos.s02buildSyntaxTree.sparql1_1.ASTIri
import lupos.s02buildSyntaxTree.sparql1_1.ASTIriGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTLanguageTaggedLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTLEQ
import lupos.s02buildSyntaxTree.sparql1_1.ASTLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTLoad
import lupos.s02buildSyntaxTree.sparql1_1.ASTLT
import lupos.s02buildSyntaxTree.sparql1_1.ASTMinus
import lupos.s02buildSyntaxTree.sparql1_1.ASTMinusGroup
import lupos.s02buildSyntaxTree.sparql1_1.ASTModify
import lupos.s02buildSyntaxTree.sparql1_1.ASTModifyWithWhere
import lupos.s02buildSyntaxTree.sparql1_1.ASTMove
import lupos.s02buildSyntaxTree.sparql1_1.ASTMultiplication
import lupos.s02buildSyntaxTree.sparql1_1.ASTNamedGraph
import lupos.s02buildSyntaxTree.sparql1_1.ASTNamedGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTNamedIriGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTNEQ
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
import lupos.s02buildSyntaxTree.sparql1_1.BuiltInFunctions
import lupos.s02buildSyntaxTree.sparql1_1.Visitor
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPAddition
import lupos.s04arithmetikOperators.multiinput.AOPAnd
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCONCAT
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCONTAINS
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallIF
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallLANGMATCHES
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
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04arithmetikOperators.singleinput.*
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallABS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallBNODE1
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallBOUND
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallCEIL
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallDATATYPE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallDAY
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallFLOOR
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallHOURS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIsIri
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIsLITERAL
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIsNUMERIC
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallLANG
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallLCASE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMD5
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMINUTES
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMONTH
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
import lupos.s04arithmetikOperators.singleinput.AOPNot
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.LOPGraphOperation
import lupos.s04logicalOperators.noinput.LOPModifyData
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
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
import lupos.s05tripleStore.*

class OperatorGraphVisitor(val query: Query) : Visitor<OPBase> {
    @JvmField
    val queryExecutionStartTime = ValueDateTime() /*required for BuildInCall.NOW */

    override fun visit(node: ASTNode, childrenValues: List<OPBase>): OPBase = LOPNOOP(query)
    fun mergeLOPBind(a: LOPBind, b: LOPBind): LOPBind {
        val aName = a.name.name
        if (b.children[1].getRequiredVariableNames().contains(aName)) {
            b.getLatestChild().setChild(a)
            return b
        } else {
            a.getLatestChild().setChild(b)
            return a
        }
    }

    fun containsAggregate(node: ASTNode): Boolean {
        if (node is ASTAggregation)
            return true
        for (c in node.children)
            if (containsAggregate(c))
                return true
        return false
    }

    override fun visit(node: ASTAskQuery, childrenValues: List<OPBase>): OPBase {
        return LOPMakeBooleanResult(query, visitSelectBase(node, arrayOf(), false, false))
    }

    override fun visit(node: ASTSubSelectQuery, childrenValues: List<OPBase>): OPBase {
        val res = LOPSubGroup(query, visit(node as ASTSelectQuery, childrenValues))
        if (node.existsValues())
            return LOPJoin(query, node.values!!.visit(this)!!, res!!, false)
        return res
    }

    override fun visit(node: ASTSelectQuery, childrenValues: List<OPBase>): OPBase {
        return visitSelectBase(node, node.select, node.distinct, node.reduced)
    }

    fun visitSelectBase(node: ASTQueryBaseClass, select: Array<ASTNode>, distinct: Boolean, reduced: Boolean): OPBase {
        val result = LOPNOOP(query)
        var bind: LOPBind? = null
        var bindIsAggregate = false
        if (distinct)
            result.getLatestChild().setChild(LOPDistinct(query))
        val projection = LOPProjection(query)
        result.getLatestChild().setChild(projection)
        val allNamesSelect = mutableSetOf<String>()
        val allNamesBind = mutableSetOf<String>()
        if (select.size > 0) {
            for (sel in select) {
                when (sel) {
                    is ASTVar -> {
                        if (allNamesBind.contains(sel.name))
                            throw Exception("projection must not contain same variable as bind and selection ${sel.name}")
                        allNamesSelect.add(sel.name)
                        projection.variables.add(AOPVariable(query, sel.name))
                    }
                    is ASTAs -> {
                        if (allNamesSelect.contains(sel.variable.name))
                            throw Exception("projection must not contain same variable as bind and selection ${sel.variable.name}")
                        allNamesBind.add(sel.variable.name)
                        val v = AOPVariable(query, sel.variable.name)
                        projection.variables.add(v)
                        val tmp3 = sel.expression.visit(this) as AOPBase
                        if (tmp3.getRequiredVariableNamesRecoursive().contains(v.name))
                            throw Exception("variable must not be recoursively defined $v")
                        val tmp2 = LOPBind(query, v, tmp3)
                        bindIsAggregate = bindIsAggregate || containsAggregate(sel.expression)
                        if (bind != null)
                            bind = mergeLOPBind(bind, tmp2)
                        else
                            bind = tmp2
                    }
                    else -> {
                        throw UnsupportedOperationException("${classNameToString(this)} Select-Parameter ${classNameToString(node)}")
                    }
                }
            }
        }
        val childNode = visitQueryBase(node, bind, bindIsAggregate, reduced)
        result.getLatestChild().setChild(childNode)
        if (select.size == 0) {
            for (s in childNode.getProvidedVariableNames()) {
                if (!s.startsWith("#"))
                    projection.variables.add(AOPVariable(query, s))
            }
        }
        return LOPSubGroup(query, result)
    }

    override fun visit(node: ASTDescribeQuery, childrenValues: List<OPBase>): OPBase {
        val child = visitSelectBase(node, node.select, false, false)
        val template = mutableListOf<ASTNode>()
        for (v in child.getProvidedVariableNames()) {
            template.add(ASTTriple(ASTVar("s"), ASTVar("p"), ASTVar(v)))
            template.add(ASTTriple(ASTVar("s"), ASTVar(v), ASTVar("o")))
            template.add(ASTTriple(ASTVar(v), ASTVar("p"), ASTVar("o")))
        }
        val itr = template.iterator()
        val array = Array(template.size) { itr.next() }
        return visitConstructBase(child, array)
    }

    override fun visit(node: ASTConstructQuery, childrenValues: List<OPBase>): OPBase {
        val child = visitQueryBase(node, null, false, false)
        return visitConstructBase(child, node.template)
    }

    fun visitConstructBase(child: OPBase, template: Array<ASTNode>): OPBase {
        var result: OPBase? = null
        for (t in template) {
            val template = t.visit(this)
            var tmp: OPBase = child
            if (template is LOPTriple) {
                val s = template.children[0] as AOPBase
                val p = template.children[1] as AOPBase
                val o = template.children[2] as AOPBase
                tmp = LOPBind(query, AOPVariable(query, "s"), s, tmp)
                tmp = LOPBind(query, AOPVariable(query, "p"), p, tmp)
                tmp = LOPBind(query, AOPVariable(query, "o"), o, tmp)
            } else
                throw UnsupportedOperationException("${classNameToString(this)} template ${classNameToString(t)}")
            tmp = LOPProjection(query, mutableListOf(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o")), tmp)
            if (result == null)
                result = tmp
            else
                result = LOPUnion(query, result, tmp)
        }
        if (result == null)
            return LOPNOOP(query)
        return LOPDistinct(query, result)
    }

    fun visitQueryBase(node: ASTQueryBaseClass, bindp: LOPBind?, bindIsAggregate: Boolean, reduced: Boolean): OPBase {
        var bind = bindp
        val result = LOPNOOP(query)
        if (node.existsLimit()) {
            result.getLatestChild().setChild(LOPLimit(query, node.limit))
        }
        if (node.existsOffset()) {
            result.getLatestChild().setChild(LOPOffset(query, node.offset))
        }
        if (reduced) {
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
                    val tmpVar = AOPVariable(query, "#f${expression.uuid}")
                    val tmpBind = LOPBind(query, tmpVar, expression)
                    if (bind != null)
                        bind = mergeLOPBind(bind, tmpBind)
                    else
                        bind = tmpBind
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
                        if (tmp.getRequiredVariableNamesRecoursive().contains(v.name))
                            throw Exception("variable must not be recoursively defined $v")
                        val tmp2 = LOPBind(query, v, tmp)
                        if (child != null)
                            child = mergeLOPBind(child, tmp2)
                        else
                            child = tmp2
                    }
                    else -> {
                        throw UnsupportedOperationException("${classNameToString(this)} Group-Parameter ${classNameToString(node)}")
                    }
                }
            }
            if (child == null)
                result.getLatestChild().setChild(LOPGroup(query, variables, bind, LOPNOOP(query)))
            else
                result.getLatestChild().setChild(LOPGroup(query, variables, bind, child))
        } else {
            if (node.existsHaving()) {
                for (h in node.having) {
                    val expression = h.visit(this) as AOPBase
                    val tmpVar = AOPVariable(query, "#f${expression.uuid}")
                    val tmpBind = LOPBind(query, tmpVar, expression)
                    if (bind != null)
                        bind = mergeLOPBind(bind, tmpBind)
                    else
                        bind = tmpBind
                    result.getLatestChild().setChild(LOPFilter(query, AOPVariable(query, tmpVar.name)))
                }
                result.getLatestChild().setChild(LOPGroup(query, mutableListOf(), bind, LOPNOOP(query)))
            } else {
                if (bindIsAggregate) {
                    result.getLatestChild().setChild(LOPGroup(query, mutableListOf(), bind, LOPNOOP(query)))
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
            // var datasets: Array<ASTDatasetClause> = arrayOf<ASTDatasetClause>();
            TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
        }
        return result
    }

    private fun parseGroup(nodes: Array<ASTNode>): OPBase {
        if (nodes.isEmpty()) {
            return LOPNOOP(query)
        }
        var result: OPBase? = null
        val bind = mutableListOf<LOPBind>()
        var members = mutableMapOf<EGroupMember, OPBase>()
        for (n in nodes) {
            var tmp2 = n.visit(this)
            while (tmp2 is LOPNOOP) {
                tmp2 = tmp2.children[0]
            }
            when (tmp2) {
/*                is LOPMinus -> {
                    if (members.containsKey(EGroupMember.GMLOPMinus))
                        (members[EGroupMember.GMLOPMinus])!!.getLatestChild().setChild(tmp2)
                    else
                        members[EGroupMember.GMLOPMinus] = tmp2
                }*/
                is LOPFilter -> {
                    if (members.containsKey(EGroupMember.GMLOPFilter))
                        (members[EGroupMember.GMLOPFilter])!!.getLatestChild().setChild(tmp2)
                    else
                        members[EGroupMember.GMLOPFilter] = tmp2
                }
                is LOPProjection -> {
                    if (members.containsKey(EGroupMember.GMLOPDataSource))
                        members[EGroupMember.GMLOPDataSource] = LOPJoin(query, members[EGroupMember.GMLOPDataSource]!!, tmp2, false)
                    else
                        members[EGroupMember.GMLOPDataSource] = tmp2
                }
                is LOPBind -> {
                    bind.add(tmp2)
                }
                is LOPTriple -> {
                    if (members.containsKey(EGroupMember.GMLOPDataSource)) {
                        members[EGroupMember.GMLOPDataSource] = LOPJoin(query, members[EGroupMember.GMLOPDataSource]!!, tmp2, false)
                    } else {
                        members[EGroupMember.GMLOPDataSource] = tmp2
                    }
                }
                is LOPUnion -> {
                    if (members.containsKey(EGroupMember.GMLOPDataSource)) {
                        members[EGroupMember.GMLOPDataSource] = LOPJoin(query, members[EGroupMember.GMLOPDataSource]!!, tmp2, false)
                    } else {
                        members[EGroupMember.GMLOPDataSource] = tmp2
                    }
                }
                is LOPValues -> {
                    if (members.containsKey(EGroupMember.GMLOPDataSource)) {
                        members[EGroupMember.GMLOPDataSource] = LOPJoin(query, members[EGroupMember.GMLOPDataSource]!!, tmp2, false)
                    } else {
                        members[EGroupMember.GMLOPDataSource] = tmp2
                    }
                }
                is LOPOptional -> {
                    if (members.containsKey(EGroupMember.GMLOPOptional)) {
                        members[EGroupMember.GMLOPOptional] = LOPJoin(query, members[EGroupMember.GMLOPOptional]!!, tmp2.children[0], true)
                    } else {
                        members[EGroupMember.GMLOPOptional] = tmp2.children[0]
                    }
                }
                is LOPJoin -> {
                    if (members.containsKey(EGroupMember.GMLOPDataSource)) {
                        members[EGroupMember.GMLOPDataSource] = LOPJoin(query, members[EGroupMember.GMLOPDataSource]!!, tmp2, true)
                    } else {
                        members[EGroupMember.GMLOPDataSource] = tmp2
                    }
                }
                is LOPSubGroup -> {
                    if (members.containsKey(EGroupMember.GMLOPDataSource)) {
                        members[EGroupMember.GMLOPDataSource] = LOPJoin(query, members[EGroupMember.GMLOPDataSource]!!, tmp2, false)
                    } else {
                        members[EGroupMember.GMLOPDataSource] = tmp2
                    }
                }
                is LOPServiceIRI -> {
                    if (members.containsKey(EGroupMember.GMLOPDataSource)) {
                        members[EGroupMember.GMLOPDataSource] = LOPJoin(query, members[EGroupMember.GMLOPDataSource]!!, tmp2, true)
                    } else {
                        members[EGroupMember.GMLOPDataSource] = tmp2
                    }
                }
                is LOPServiceVAR -> {
                    if (members.containsKey(EGroupMember.GMLOPDataSource)) {
                        tmp2.children[0] = members[EGroupMember.GMLOPDataSource]!!
                        members[EGroupMember.GMLOPDataSource] = tmp2
                    } else {
                        members[EGroupMember.GMLOPDataSource] = tmp2
                    }
                }
                else ->
                    throw UnsupportedOperationException("${classNameToString(this)} EGroupMember ${classNameToString(tmp2)}")
            }
        }
/*        if (members.containsKey(EGroupMember.GMLOPMinus)) {
            result = members[EGroupMember.GMLOPMinus]
        }
*/
        if (members.containsKey(EGroupMember.GMLOPFilter)) {
            if (result == null)
                result = members[EGroupMember.GMLOPFilter]
            else
                (result).getLatestChild().setChild(members[EGroupMember.GMLOPFilter]!!)
        }
        var firstJoin: OPBase? = null
        if (members.containsKey(EGroupMember.GMLOPDataSource)) {
            firstJoin = members[EGroupMember.GMLOPDataSource]
        }
        if (members.containsKey(EGroupMember.GMLOPOptional)) {
            if (firstJoin == null)
                firstJoin = LOPOptional(query, members[EGroupMember.GMLOPOptional]!!)
            else
                firstJoin = LOPJoin(query, firstJoin, members[EGroupMember.GMLOPOptional]!!, true)
        }
        if (firstJoin == null) {
            var bb: LOPBind? = null
            for (b in bind) {
                if (bb == null)
                    bb = b
                else
                    bb = mergeLOPBind(bb, b)
            }
            firstJoin = bb
        } else {
            for (b in bind) {
                firstJoin = insertLOPBind(firstJoin!!, b)
            }
        }
        if (firstJoin != null) {
            if (result == null)
                result = firstJoin
            else
                (result).getLatestChild().setChild(firstJoin)
        }
        return result!!
    }

    fun insertLOPBind(a: OPBase, b: LOPBind): OPBase {
        if (a is LOPJoin) {
            val requiredVariables = b.children[1].getRequiredVariableNames()
            val providedLeft = a.children[0].getProvidedVariableNames()
            var leftOk = true
            for (v in requiredVariables) {
                if (!providedLeft.contains(v)) {
                    leftOk = false
                    break
                }
            }
            val providedRight = a.children[1].getProvidedVariableNames()
            var rightOk = true
            for (v in requiredVariables) {
                if (!providedRight.contains(v)) {
                    rightOk = false
                    break
                }
            }
            if (leftOk != rightOk) {
                if (leftOk)
                    a.children[0] = insertLOPBind(a.children[0], b)
                else
                    return LOPJoin(query, a.children[0], insertLOPBind(a.children[1], b), a.optional)
                return a
            }
        }
        b.getLatestChild().setChild(a)
        return b
    }

    override fun visit(node: ASTQuery, childrenValues: List<OPBase>): OPBase {
        if (childrenValues.isEmpty()) {
            return LOPNOOP(query) // empty query
        }
        var opbase: OPBase = LOPNOOP(query)
        var prefix: LOPPrefix? = null
        var values: OPBase? = null
        var lastQuery: OPBase? = null
        for (q in childrenValues) {
            if (q is LOPPrefix) {
                if (prefix == null)
                    prefix = q
                else
                    prefix.getLatestChild().setChild(q)
            } else if (q is LOPValues) {
                if (values == null) {
                    values = q
                } else {
                    values = LOPJoin(query, values, q, false)
                }
            } else {
                if (lastQuery == null) {
                    lastQuery = q
                } else {
                    opbase = LOPJoin(query, opbase, joinValuesAndQuery(values, lastQuery), false)
                    values = null
                    lastQuery = q
                }
            }
        }
        if (opbase is LOPNOOP) {
            if (lastQuery != null)
                opbase = joinValuesAndQuery(values, lastQuery)
        } else {
            opbase = LOPJoin(query, opbase, joinValuesAndQuery(values, lastQuery!!), false)
        }
        if (prefix != null) {
            prefix.getLatestChild().setChild(opbase)
            return prefix
        }
        return opbase
    }

    private fun joinValuesAndQuery(values: OPBase?, opbase: OPBase): OPBase {
        if (values == null)
            return opbase
        if (opbase !is LOPProjection)
            return LOPJoin(query, values, opbase, false)
        var latestProjection = opbase
        var realQuery = opbase
        while (realQuery is LOPProjection) {
            latestProjection = realQuery
            realQuery = realQuery.children[0]
        }
        (latestProjection as LOPProjection).setChild(LOPJoin(query, values, realQuery, false))
        return opbase
    }

    override fun visit(node: ASTUndef, childrenValues: List<OPBase>): OPBase {
        return AOPConstant(query, ValueUndef())
    }

    override fun visit(node: ASTSimpleLiteral, childrenValues: List<OPBase>): OPBase {
        return AOPConstant(query, ValueSimpleLiteral(node.delimiter, node.content))
    }

    override fun visit(node: ASTTypedLiteral, childrenValues: List<OPBase>): OPBase {
        return AOPConstant(query, ValueDefinition(node.delimiter + node.content + node.delimiter + "^^<" + node.type_iri + ">"))
    }

    override fun visit(node: ASTLanguageTaggedLiteral, childrenValues: List<OPBase>): OPBase {
        return AOPConstant(query, ValueLanguageTaggedLiteral(node.delimiter, node.content, node.language))
    }

    override fun visit(node: ASTBooleanLiteral, childrenValues: List<OPBase>): OPBase {
        return AOPConstant(query, ValueBoolean(node.value))
    }

    override fun visit(node: ASTInteger, childrenValues: List<OPBase>): OPBase {
        return AOPConstant(query, ValueInteger(node.value))
    }

    override fun visit(node: ASTDouble, childrenValues: List<OPBase>): OPBase {
        return AOPConstant(query, ValueDouble(node.toDouble()))
    }

    override fun visit(node: ASTDecimal, childrenValues: List<OPBase>): OPBase {
        return AOPConstant(query, ValueDecimal(node.toDouble()))
    }

    override fun visit(node: ASTFunctionCall, childrenValues: List<OPBase>): OPBase {
        throw Exception("not implemented")
    }

    override fun visit(node: ASTTriple, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 3 })
        return LOPTriple(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase, childrenValues[2] as AOPBase, PersistentStoreLocal.defaultGraphName, false)
    }

    override fun visit(node: ASTOptional, childrenValues: List<OPBase>): OPBase {
        return LOPOptional(query, parseGroup(node.children))
    }

    override fun visit(node: ASTSet, childrenValues: List<OPBase>): OPBase {
        val tmp = List(childrenValues.size) { childrenValues[it] as AOPBase }
        return AOPSet(query, tmp)
    }

    override fun visit(node: ASTOr, childrenValues: List<OPBase>): OPBase {
        SanityCheck.check({ childrenValues.size > 1 })
        var res: AOPBase? = null
        for (v in childrenValues) {
            if (res == null)
                res = v as AOPBase
            else
                res = AOPOr(query, v as AOPBase, res)
        }
        return res!!
    }

    override fun visit(node: ASTAnd, childrenValues: List<OPBase>): OPBase {
        SanityCheck.check({ childrenValues.size > 1 })
        var res: AOPBase? = null
        for (v in childrenValues) {
            if (res == null)
                res = v as AOPBase
            else
                res = AOPAnd(query, v as AOPBase, res)
        }
        return res!!
    }

    override fun visit(node: ASTEQ, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 2 })
        return AOPEQ(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTNEQ, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 2 })
        return AOPNEQ(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTLEQ, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 2 })
        return AOPLEQ(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTGEQ, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 2 })
        return AOPGEQ(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTLT, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 2 })
        return AOPLT(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTGT, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 2 })
        return AOPGT(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTIn, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 2 })
        return AOPIn(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTNotIn, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 2 })
        return AOPNotIn(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTAddition, childrenValues: List<OPBase>): OPBase {
        SanityCheck.check({ childrenValues.size > 1 })
        var res: AOPBase? = null
        for (v in childrenValues) {
            if (res == null)
                res = v as AOPBase
            else
                res = AOPAddition(query, v as AOPBase, res)
        }
        return res!!
    }

    override fun visit(node: ASTSubtraction, childrenValues: List<OPBase>): OPBase {
        SanityCheck.check({ childrenValues.size > 1 })
        var res: AOPBase? = null
        for (v in childrenValues) {
            if (res == null)
                res = v as AOPBase
            else
                res = AOPSubtraction(query, v as AOPBase, res)
        }
        return res!!
    }

    override fun visit(node: ASTMultiplication, childrenValues: List<OPBase>): OPBase {
        SanityCheck.check({ childrenValues.size > 1 })
        var res: AOPBase? = null
        for (v in childrenValues) {
            if (res == null)
                res = v as AOPBase
            else
                res = AOPMultiplication(query, v as AOPBase, res)
        }
        return res!!
    }

    override fun visit(node: ASTDivision, childrenValues: List<OPBase>): OPBase {
        SanityCheck.check({ childrenValues.size > 1 })
        var res: AOPBase? = null
        for (v in childrenValues) {
            if (res == null)
                res = v as AOPBase
            else
                res = AOPDivision(query, v as AOPBase, res)
        }
        return res!!
    }

    override fun visit(node: ASTNot, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 1 })
        return AOPNot(query, childrenValues[0] as AOPBase)
    }

    override fun visit(node: ASTBase, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
        return LOPPrefix(query, "", node.iri)
    }

    override fun visit(node: ASTPrefix, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
        return LOPPrefix(query, node.name, node.iri)
    }

    override fun visit(node: ASTAs, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
        val a = node.variable.visit(this) as AOPVariable
        val b = node.expression.visit(this) as AOPBase
        if (b.getRequiredVariableNamesRecoursive().contains(a.name))
            throw Exception("variable must not be recousively defined $a")
        return LOPBind(query, a, b)
    }

    override fun visit(node: ASTBlankNode, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
        return AOPVariable(query, "#" + node.name)
    }

    override fun visit(node: ASTBuiltInCall, childrenValues: List<OPBase>): OPBase {
        when (node.function) {
            BuiltInFunctions.STR -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallSTR(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.LANG -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallLANG(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.LANGMATCHES -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 2 })
                return AOPBuildInCallLANGMATCHES(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctions.DATATYPE -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallDATATYPE(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.BOUND -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallBOUND(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.IRI -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallIRI(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.URI -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallURI(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.BNODE -> {
                if (childrenValues.size == 1)
                    return AOPBuildInCallBNODE1(query, childrenValues[0] as AOPBase)
                return AOPBuildInCallBNODE0(query)
            }
            BuiltInFunctions.ABS -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallABS(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.CEIL -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallCEIL(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.FLOOR -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallFLOOR(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.ROUND -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallROUND(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.CONCAT -> {
                SanityCheck.check({ childrenValues.size > 0 })
                var res = childrenValues[0] as AOPBase
                for (i in 1 until childrenValues.size)
                    res = AOPBuildInCallCONCAT(query, res, childrenValues[i] as AOPBase)
                return res
            }
            BuiltInFunctions.STRLEN -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallSTRLEN(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.UCASE -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallUCASE(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.LCASE -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallLCASE(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.CONTAINS -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 2 })
                return AOPBuildInCallCONTAINS(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctions.STRSTARTS -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 2 })
                return AOPBuildInCallSTRSTARTS(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctions.STRENDS -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 2 })
                return AOPBuildInCallSTRENDS(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctions.YEAR -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallYEAR(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.MONTH -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallMONTH(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.DAY -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallDAY(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.HOURS -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallHOURS(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.MINUTES -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallMINUTES(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.SECONDS -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallSECONDS(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.TIMEZONE -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallTIMEZONE(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.TZ -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallTZ(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.NOW -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 0 })
                return AOPConstant(query, queryExecutionStartTime)
            }
            BuiltInFunctions.UUID -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 0 })
                return AOPBuildInCallUUID(query)
            }
            BuiltInFunctions.STRUUID -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 0 })
                return AOPBuildInCallSTRUUID(query)
            }
            BuiltInFunctions.MD5 -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallMD5(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.SHA1 -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallSHA1(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.SHA256 -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallSHA256(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.IF -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 3 })
                return AOPBuildInCallIF(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase, childrenValues[2] as AOPBase)
            }
            BuiltInFunctions.STRLANG -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 2 })
                return AOPBuildInCallSTRLANG(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctions.STRDT -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 2 })
                return AOPBuildInCallSTRDT(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctions.isLITERAL -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallIsLITERAL(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.isIRI -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallIsIri(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.isNUMERIC -> {
                SanityCheck.checkEQ({ childrenValues.size }, { 1 })
                return AOPBuildInCallIsNUMERIC(query, childrenValues[0] as AOPBase)
            }
            else -> throw UnsupportedOperationException("${classNameToString(this)} ${node.function}")
        }
    }

    override fun visit(node: ASTAggregation, childrenValues: List<OPBase>): OPBase {
        when (node.type) {
            Aggregation.COUNT -> return AOPAggregationCOUNT(query, node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            Aggregation.MIN -> return AOPAggregationMIN(query, node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            Aggregation.MAX -> return AOPAggregationMAX(query, node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            Aggregation.SAMPLE -> return AOPAggregationSAMPLE(query, node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            Aggregation.AVG -> return AOPAggregationAVG(query, node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            Aggregation.SUM -> return AOPAggregationSUM(query, node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            else -> SanityCheck.checkUnreachable()
        }
    }

    override fun visit(node: ASTUnion, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 2 })
        return LOPUnion(query, childrenValues[0], childrenValues[1])
    }

    override fun visit(node: ASTFilter, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 1 })
        val child = childrenValues.first() as AOPBase
        if (containsAggregate(node.children.first()))
            throw Exception("Aggregate not allowed here")
        return LOPFilter(query, child)
    }

    override fun visit(node: ASTOrderCondition, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 1 })
        val tmp = childrenValues.first() as AOPBase
        if (tmp is AOPVariable)
            return LOPSort(query, node.asc, tmp)
        val v = AOPVariable(query, "#f${tmp.uuid}")
        return LOPSort(query, node.asc, v, LOPBind(query, v, tmp))
    }

    override fun visit(node: ASTVar, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
        return AOPVariable(query, node.name)
    }

    override fun visit(node: ASTIri, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
        return AOPConstant(query, ValueIri(node.iri))
    }

    override fun visit(node: ASTGroup, childrenValues: List<OPBase>): OPBase {
        return LOPSubGroup(query, parseGroup(node.children))
    }

    override fun visit(node: ASTService, childrenValues: List<OPBase>): OPBase {
        when {
            node.iriOrVar is ASTIri -> return LOPServiceIRI(query, node.iriOrVar.iri, node.silent, parseGroup(node.children))
            node.iriOrVar is ASTVar -> return LOPServiceVAR(query, node.iriOrVar.name, node.silent, parseGroup(node.children))
            else -> throw UnsupportedOperationException("${classNameToString(this)} Service ${classNameToString(node)} ${classNameToString(node.iriOrVar)}")
        }
    }

    override fun visit(node: ASTValues, childrenValues: List<OPBase>): OPBase {
        val variables = mutableListOf<AOPVariable>()
        val values = mutableListOf<AOPValue>()
        for (v in node.variables)
            variables.add(v.visit(this) as AOPVariable)
        for (v in node.children)
            values.add(v.visit(this) as AOPValue)
        return LOPValues(query, variables, values)
    }

    override fun visit(node: ASTValue, childrenValues: List<OPBase>): OPBase {
        val tmp = List(childrenValues.size) { childrenValues[it] as AOPConstant }
        return AOPValue(query, tmp)
    }

    fun setGraphNameForAllTriples(node: OPBase, name: ASTNode, optional: Boolean): OPBase {
        val iri = when (name) {
            is ASTIri -> name.iri
            is ASTIriGraphRef -> name.iri
            else -> throw UnsupportedOperationException("${classNameToString(this)} setGraphNameForAllTriples 1 ${classNameToString(node)} ${classNameToString(name)} $optional")
        }
        when (node) {
            is OPNothing -> return node
            is LOPTriple -> {
                if (!optional || node.graph == PersistentStoreLocal.defaultGraphName)
                    return LOPTriple(query, node.children[0] as AOPBase, node.children[1] as AOPBase, node.children[2] as AOPBase, iri, false)
                else
                    return node
            }
            is LOPFilter -> node.children[0] = setGraphNameForAllTriples(node.children[0], name, optional)
            is LOPJoin -> return LOPJoin(query, setGraphNameForAllTriples(node.children[0], name, optional), setGraphNameForAllTriples(node.children[1], name, optional), node.optional)
            else -> throw UnsupportedOperationException("${classNameToString(this)} setGraphNameForAllTriples 2 ${classNameToString(node)} $optional")
        }
        return node
    }

    override fun visit(node: ASTGraph, childrenValues: List<OPBase>): OPBase {
        var res: OPBase = OPNothing(query)
        for (c in childrenValues) {
            val tmp = setGraphNameForAllTriples(c, node.iriOrVar, false)
            if (res is OPNothing)
                res = tmp
            else
                res = LOPJoin(query, res, tmp, false)
        }
        return res
    }

    fun graphRefToEnum(ref: ASTGraphRef): Pair<EGraphRefType, String?> {
        if (ref is ASTIriGraphRef)
            return Pair(EGraphRefType.IriGraphRef, ref.iri)
        if (ref is ASTNamedIriGraphRef)
            return Pair(EGraphRefType.IriGraphRef, ref.iri)
        if (ref is ASTDefaultGraphRef)
            return Pair(EGraphRefType.DefaultGraphRef, null)
        if (ref is ASTNamedGraphRef)
            return Pair(EGraphRefType.NamedGraphRef, null)
        if (ref is ASTAllGraphRef)
            return Pair(EGraphRefType.AllGraphRef, null)
        throw Exception("not reachable")
    }

    override fun visit(node: ASTAdd, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
        val g1 = graphRefToEnum(node.fromGraph)
        val g2 = graphRefToEnum(node.toGraph)
        val res = LOPGraphOperation(query, EGraphOperationType.ADD, node.silent, g1.first, g1.second, g2.first, g2.second)
        return res
    }

    override fun visit(node: ASTMove, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
        val g1 = graphRefToEnum(node.fromGraph)
        val g2 = graphRefToEnum(node.toGraph)
        val res = LOPGraphOperation(query, EGraphOperationType.MOVE, node.silent, g1.first, g1.second, g2.first, g2.second)
        return res
    }

    override fun visit(node: ASTCopy, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
        val g1 = graphRefToEnum(node.fromGraph)
        val g2 = graphRefToEnum(node.toGraph)
        val res = LOPGraphOperation(query, EGraphOperationType.COPY, node.silent, g1.first, g1.second, g2.first, g2.second)
        return res
    }

    override fun visit(node: ASTClear, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
        val g1 = graphRefToEnum(node.graphref)
        val res = LOPGraphOperation(query, EGraphOperationType.CLEAR, node.silent, g1.first, g1.second)
        return res
    }

    override fun visit(node: ASTDrop, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
        val g1 = graphRefToEnum(node.graphref)
        val res = LOPGraphOperation(query, EGraphOperationType.DROP, node.silent, g1.first, g1.second)
        return res
    }

    override fun visit(node: ASTCreate, childrenValues: List<OPBase>): OPBase {
        SanityCheck.checkEQ({ childrenValues.size }, { 0 })
        val g1 = graphRefToEnum(node.graphref)
        val res = LOPGraphOperation(query, EGraphOperationType.CREATE, node.silent, g1.first, g1.second)
        return res
    }

    fun simpleAstToStringValue(node: ASTNode): AOPBase {
        return node.visit(this) as AOPBase
    }

    fun modifyDataHelper(children: Array<ASTNode>, modify: LOPModifyData) {
        for (c in children) {
            when {
                c is ASTTriple -> {
                    modify.data.add(LOPTriple(query, simpleAstToStringValue(c.children[0]), simpleAstToStringValue(c.children[1]), simpleAstToStringValue(c.children[2]), PersistentStoreLocal.defaultGraphName, false))
                }
                c is ASTGraph -> {
                    for (c2 in c.children) {
                        when {
                            c2 is ASTTriple -> {
                                modify.data.add(LOPTriple(query, simpleAstToStringValue(c2.children[0]), simpleAstToStringValue(c2.children[1]), simpleAstToStringValue(c2.children[2]), (c.iriOrVar as ASTIri).iri, true))
                            }
                            else -> throw UnsupportedOperationException("${classNameToString(this)} modifyDataHelper ${classNameToString(c2)}")
                        }
                    }
                }
                else -> throw UnsupportedOperationException("${classNameToString(this)} modifyDataHelper ${classNameToString(c)}")
            }
        }
    }

    override fun visit(node: ASTDeleteData, childrenValues: List<OPBase>): OPBase {
        val res = LOPModifyData(query, EModifyType.DELETE)
        modifyDataHelper(node.children, res)
        return res
    }

    override fun visit(node: ASTDeleteWhere, childrenValues: List<OPBase>): OPBase {
        return visit(ASTModifyWithWhere(null, node.children, arrayOf<ASTNode>(), arrayOf<ASTGraphRef>(), node.children), listOf<OPBase>())
    }

    override fun visit(node: ASTInsertData, childrenValues: List<OPBase>): OPBase {
        val res = LOPModifyData(query, EModifyType.INSERT)
        modifyDataHelper(node.children, res)
        return res
    }

    fun joinToList(node: OPBase): List<OPBase> {
        if (node is LOPJoin) {
            val res = mutableListOf<OPBase>()
            res.addAll(joinToList(node.children[0]))
            res.addAll(joinToList(node.children[1]))
            return res
        }
        return listOf(node)
    }

    override fun visit(node: ASTModifyWithWhere, childrenValues: List<OPBase>): OPBase {
        val child: OPBase = if (node.using.isEmpty()) {
            parseGroup(node.children)
        } else {
            var tmp: OPBase? = null
            for (c in node.using) {
                val tmp2 = setGraphNameForAllTriples(parseGroup(node.children), c, false)
                if (tmp == null)
                    tmp = tmp2
                else
                    tmp = LOPUnion(query, tmp, tmp2)
            }
            tmp!!
        }
        val iri = node.iri
        val insert: MutableList<LOPTriple> = mutableListOf<LOPTriple>()
        val delete: MutableList<LOPTriple> = mutableListOf<LOPTriple>()
        if (iri != null) {
            for (e in node.insert)
                insert.add(setGraphNameForAllTriples(e.visit(this), ASTIri(iri), true) as LOPTriple)
            for (e in node.delete)
                delete.add(setGraphNameForAllTriples(e.visit(this), ASTIri(iri), true) as LOPTriple)
            val res = LOPModify(query, insert, delete, setGraphNameForAllTriples(child, ASTIri(iri), true))
            return res
        } else {
            for (e in node.insert) {
                for (tmp in joinToList(e.visit(this))) {
                    insert.add(tmp as LOPTriple)
                }
            }
            for (e in node.delete) {
                for (tmp in joinToList(e.visit(this))) {
                    delete.add(tmp as LOPTriple)
                }
            }
            val res = LOPModify(query, insert, delete, child)
            return res
        }
    }

    override fun visit(node: ASTMinusGroup, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${classNameToString(this)} minus ${classNameToString(node)}")
    }

    override fun visit(node: ASTLoad, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${classNameToString(this)} Graph ${classNameToString(node)}")
    }

    override fun visit(node: ASTModify, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${classNameToString(this)} Update ${classNameToString(node)}")
    }

    override fun visit(node: ASTDefaultGraph, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${classNameToString(this)} Graph ${classNameToString(node)}")
    }

    override fun visit(node: ASTNamedGraph, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${classNameToString(this)} Graph ${classNameToString(node)}")
    }

    override fun visit(node: ASTGraphRef, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${classNameToString(this)} Graph ${classNameToString(node)}")
    }

    override fun visit(node: ASTIriGraphRef, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${classNameToString(this)} Graph ${classNameToString(node)}")
    }

    override fun visit(node: ASTNamedIriGraphRef, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${classNameToString(this)} Graph ${classNameToString(node)}")
    }

    override fun visit(node: ASTDefaultGraphRef, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${classNameToString(this)} Graph ${classNameToString(node)}")
    }

    override fun visit(node: ASTNamedGraphRef, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${classNameToString(this)} Graph ${classNameToString(node)}")
    }

    override fun visit(node: ASTAllGraphRef, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${classNameToString(this)} Graph ${classNameToString(node)}")
    }

    override fun visit(node: ASTGrapOperation, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${classNameToString(this)} Graph ${classNameToString(node)}")
    }

    override fun visit(node: ASTUpdateGrapOperation, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${classNameToString(this)} Graph ${classNameToString(node)}")
    }

    override fun visit(node: ASTPathAlternatives, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${classNameToString(this)} Path ${classNameToString(node)}")
    }

    override fun visit(node: ASTPathSequence, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${classNameToString(this)} Path ${classNameToString(node)}")
    }

    override fun visit(node: ASTPathInverse, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${classNameToString(this)} Path ${classNameToString(node)}")
    }

    override fun visit(node: ASTPathArbitraryOccurrences, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${classNameToString(this)} Path ${classNameToString(node)}")
    }

    override fun visit(node: ASTPathOptionalOccurrence, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${classNameToString(this)} Path ${classNameToString(node)}")
    }

    override fun visit(node: ASTPathArbitraryOccurrencesNotZero, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${classNameToString(this)} Path ${classNameToString(node)}")
    }

    override fun visit(node: ASTPathNegatedPropertySet, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${classNameToString(this)} Path ${classNameToString(node)}")
    }

    override fun visit(node: ASTGroupConcat, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${classNameToString(this)} Group ${classNameToString(node)}")
    }

    override fun visit(node: ASTDatasetClause, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${classNameToString(this)} Query Type ${classNameToString(node)}")
    }

    override fun visit(node: ASTQueryBaseClass, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTRDFTerm, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTPlus, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTMinus, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTNumericLiteral, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(node: ASTLiteral, childrenValues: List<OPBase>): OPBase {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}
