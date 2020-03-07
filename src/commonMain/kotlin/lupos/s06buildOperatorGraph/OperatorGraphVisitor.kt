package lupos.s06buildOperatorGraph
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.classNameToString
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EGroupMember
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
import lupos.s04arithmetikOperators.noinput.AOPBoolean
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallSTRUUID
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallUUID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPDateTime
import lupos.s04arithmetikOperators.noinput.AOPDecimal
import lupos.s04arithmetikOperators.noinput.AOPDouble
import lupos.s04arithmetikOperators.noinput.AOPInteger
import lupos.s04arithmetikOperators.noinput.AOPIri
import lupos.s04arithmetikOperators.noinput.AOPLanguageTaggedLiteral
import lupos.s04arithmetikOperators.noinput.AOPSimpleLiteral
import lupos.s04arithmetikOperators.noinput.AOPTypedLiteral
import lupos.s04arithmetikOperators.noinput.AOPUndef
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
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
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPModify
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.LOPOptional
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPRename
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


class OperatorGraphVisitor : Visitor<OPBase> {
    @JvmField
    val queryExecutionStartTime = AOPDateTime() /*required for BuildInCall.NOW */

    override fun visit(node: ASTNode, childrenValues: List<OPBase>): OPBase = LOPNOOP()

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
        return LOPMakeBooleanResult(visitSelectBase(node, arrayOf(), false, false))
    }

    override fun visit(node: ASTSubSelectQuery, childrenValues: List<OPBase>): OPBase {
        if (node.existsValues()) {
            throw UnsupportedOperationException("${classNameToString(this)} Values ${classNameToString(node)}")
        }
        return LOPSubGroup(visit(node as ASTSelectQuery, childrenValues))
    }

    override fun visit(node: ASTSelectQuery, childrenValues: List<OPBase>): OPBase {
        return visitSelectBase(node, node.select, node.distinct, node.reduced)
    }

    fun visitSelectBase(node: ASTQueryBaseClass, select: Array<ASTNode>, distinct: Boolean, reduced: Boolean): OPBase {
        val result = LOPNOOP()
        var bind: LOPBind? = null
        var bindIsAggregate = false
        if (distinct)
            result.getLatestChild().setChild(LOPDistinct())
        val projection = LOPProjection()
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
                        projection.variables.add(AOPVariable(sel.name))
                    }
                    is ASTAs -> {
                        if (allNamesSelect.contains(sel.variable.name))
                            throw Exception("projection must not contain same variable as bind and selection ${sel.variable.name}")
                        allNamesBind.add(sel.variable.name)
                        val v = AOPVariable(sel.variable.name)
                        projection.variables.add(v)
                        val tmp3 = sel.expression.visit(this) as AOPBase
                        if (tmp3.getRequiredVariableNamesRecoursive().contains(v.name))
                            throw Exception("variable must not be recoursively defined $v")
                        val tmp2 = LOPBind(v, tmp3)
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
                    projection.variables.add(AOPVariable(s))
            }
        }
        return LOPSubGroup(result)
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
                if (s is AOPVariable)
                    tmp = LOPRename(AOPVariable("s"), AOPVariable(s.name), tmp)
                else
                    tmp = LOPBind(AOPVariable("s"), s, tmp)
                if (p is AOPVariable)
                    tmp = LOPRename(AOPVariable("p"), AOPVariable(p.name), tmp)
                else
                    tmp = LOPBind(AOPVariable("p"), p, tmp)
                if (o is AOPVariable)
                    tmp = LOPRename(AOPVariable("o"), AOPVariable(o.name), tmp)
                else
                    tmp = LOPBind(AOPVariable("o"), o, tmp)
            } else
                throw UnsupportedOperationException("${classNameToString(this)} template ${classNameToString(t)}")
            tmp = LOPProjection(mutableListOf(AOPVariable("s"), AOPVariable("p"), AOPVariable("o")), tmp)
            if (result == null)
                result = tmp
            else
                result = LOPUnion(result, tmp)
        }
        if (result == null)
            return LOPNOOP()
        return LOPDistinct(result)
    }

    fun visitQueryBase(node: ASTQueryBaseClass, bindp: LOPBind?, bindIsAggregate: Boolean, reduced: Boolean): OPBase {
        var bind = bindp
        val result = LOPNOOP()
        if (node.existsLimit()) {
            result.getLatestChild().setChild(LOPLimit(node.limit))
        }
        if (node.existsOffset()) {
            result.getLatestChild().setChild(LOPOffset(node.offset))
        }
        if (reduced) {
            result.getLatestChild().setChild(LOPReduced())
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
                    val tmpVar = AOPVariable("#f${expression.uuid}")
                    val tmpBind = LOPBind(tmpVar, expression)
                    if (bind != null)
                        bind = mergeLOPBind(bind, tmpBind)
                    else
                        bind = tmpBind
                    result.getLatestChild().setChild(LOPFilter(AOPVariable(tmpVar.name)))
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
                        val v = AOPVariable(b.variable.name)
                        variables.add(v)
                        val tmp = b.expression.visit(this) as AOPBase
                        if (tmp.getRequiredVariableNamesRecoursive().contains(v.name))
                            throw Exception("variable must not be recoursively defined $v")
                        val tmp2 = LOPBind(v, tmp)
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
                result.getLatestChild().setChild(LOPGroup(variables, bind, LOPNOOP()))
            else
                result.getLatestChild().setChild(LOPGroup(variables, bind, child))
        } else {
            if (node.existsHaving()) {
                for (h in node.having) {
                    val expression = h.visit(this) as AOPBase
                    val tmpVar = AOPVariable("#f${expression.uuid}")
                    val tmpBind = LOPBind(tmpVar, expression)
                    if (bind != null)
                        bind = mergeLOPBind(bind, tmpBind)
                    else
                        bind = tmpBind
                    result.getLatestChild().setChild(LOPFilter(AOPVariable(tmpVar.name)))
                }
                result.getLatestChild().setChild(LOPGroup(mutableListOf(), bind, LOPNOOP()))
            } else {
                if (bindIsAggregate) {
                    result.getLatestChild().setChild(LOPGroup(mutableListOf(), bind, LOPNOOP()))
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
            return LOPNOOP()
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
                        members[EGroupMember.GMLOPDataSource] = LOPJoin(members[EGroupMember.GMLOPDataSource]!!, tmp2, false)
                    else
                        members[EGroupMember.GMLOPDataSource] = tmp2
                }
                is LOPBind -> {
                    bind.add(tmp2)
                }
                is LOPTriple -> {
                    if (members.containsKey(EGroupMember.GMLOPDataSource)) {
                        members[EGroupMember.GMLOPDataSource] = LOPJoin(members[EGroupMember.GMLOPDataSource]!!, tmp2, false)
                    } else {
                        members[EGroupMember.GMLOPDataSource] = tmp2
                    }
                }
                is LOPUnion -> {
                    if (members.containsKey(EGroupMember.GMLOPDataSource)) {
                        members[EGroupMember.GMLOPDataSource] = LOPJoin(members[EGroupMember.GMLOPDataSource]!!, tmp2, false)
                    } else {
                        members[EGroupMember.GMLOPDataSource] = tmp2
                    }
                }
                is LOPValues -> {
                    if (members.containsKey(EGroupMember.GMLOPDataSource)) {
                        members[EGroupMember.GMLOPDataSource] = LOPJoin(members[EGroupMember.GMLOPDataSource]!!, tmp2, false)
                    } else {
                        members[EGroupMember.GMLOPDataSource] = tmp2
                    }
                }
                is LOPOptional -> {
                    if (members.containsKey(EGroupMember.GMLOPOptional)) {
                        members[EGroupMember.GMLOPOptional] = LOPJoin(members[EGroupMember.GMLOPOptional]!!, tmp2.children[0], true)
                    } else {
                        members[EGroupMember.GMLOPOptional] = tmp2.children[0]
                    }
                }
                is LOPJoin -> {
                    if (members.containsKey(EGroupMember.GMLOPDataSource)) {
                        members[EGroupMember.GMLOPDataSource] = LOPJoin(members[EGroupMember.GMLOPDataSource]!!, tmp2, true)
                    } else {
                        members[EGroupMember.GMLOPDataSource] = tmp2
                    }
                }
                is LOPSubGroup -> {
                    if (members.containsKey(EGroupMember.GMLOPDataSource)) {
                        members[EGroupMember.GMLOPDataSource] = LOPJoin(members[EGroupMember.GMLOPDataSource]!!, tmp2, false)
                    } else {
                        members[EGroupMember.GMLOPDataSource] = tmp2
                    }
                }
                is LOPServiceIRI -> {
                    if (members.containsKey(EGroupMember.GMLOPDataSource)) {
                        members[EGroupMember.GMLOPDataSource] = LOPJoin(members[EGroupMember.GMLOPDataSource]!!, tmp2, true)
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
                firstJoin = LOPOptional(members[EGroupMember.GMLOPOptional]!!)
            else
                firstJoin = LOPJoin(firstJoin, members[EGroupMember.GMLOPOptional]!!, true)
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
                    return LOPJoin(a.children[0], insertLOPBind(a.children[1], b), a.optional)
                return a
            }
        }
        b.getLatestChild().setChild(a)
        return b
    }

    override fun visit(node: ASTQuery, childrenValues: List<OPBase>): OPBase {
        if (childrenValues.isEmpty()) {
            return LOPNOOP() // empty query
        }
        var query: OPBase = LOPNOOP()
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
                    values = LOPJoin(values, q, false)
                }
            } else {
                if (lastQuery == null) {
                    lastQuery = q
                } else {
                    query = LOPJoin(query, joinValuesAndQuery(values, lastQuery), false)
                    values = null
                    lastQuery = q
                }
            }
        }
        if (query is LOPNOOP) {
            if (lastQuery != null)
                query = joinValuesAndQuery(values, lastQuery)
        } else {
            query = LOPJoin(query, joinValuesAndQuery(values, lastQuery!!), false)
        }
        if (prefix != null) {
            prefix.getLatestChild().setChild(query)
            return prefix
        }
        return query
    }

    private fun joinValuesAndQuery(values: OPBase?, query: OPBase): OPBase {
        if (values == null)
            return query
        if (query !is LOPProjection)
            return LOPJoin(values, query, false)
        var latestProjection = query
        var realQuery = query
        while (realQuery is LOPProjection) {
            latestProjection = realQuery
            realQuery = realQuery.children[0]
        }
        (latestProjection as LOPProjection).setChild(LOPJoin(values, realQuery, false))
        return query
    }

    override fun visit(node: ASTUndef, childrenValues: List<OPBase>): OPBase {
        return AOPUndef()
    }

    override fun visit(node: ASTSimpleLiteral, childrenValues: List<OPBase>): OPBase {
        return AOPSimpleLiteral(node.delimiter, node.content)
    }

    override fun visit(node: ASTTypedLiteral, childrenValues: List<OPBase>): OPBase {
        return AOPVariable.calculate(node.delimiter + node.content + node.delimiter + "^^<" + node.type_iri + ">")
    }

    override fun visit(node: ASTLanguageTaggedLiteral, childrenValues: List<OPBase>): OPBase {
        return AOPLanguageTaggedLiteral(node.delimiter, node.content, node.language)
    }

    override fun visit(node: ASTBooleanLiteral, childrenValues: List<OPBase>): OPBase {
        return AOPBoolean(node.value)
    }

    override fun visit(node: ASTInteger, childrenValues: List<OPBase>): OPBase {
        return AOPInteger(node.value)
    }

    override fun visit(node: ASTDouble, childrenValues: List<OPBase>): OPBase {
        return AOPDouble(node.toDouble())
    }

    override fun visit(node: ASTDecimal, childrenValues: List<OPBase>): OPBase {
        return AOPDecimal(node.toDouble())
    }

    override fun visit(node: ASTFunctionCall, childrenValues: List<OPBase>): OPBase {
        throw Exception("not implemented")
    }

    override fun visit(node: ASTTriple, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size == 3)
        return LOPTriple(childrenValues[0] as AOPBase, childrenValues[1] as AOPBase, childrenValues[2] as AOPBase, PersistentStoreLocal.defaultGraphName, false)
    }

    override fun visit(node: ASTOptional, childrenValues: List<OPBase>): OPBase {
        return LOPOptional(parseGroup(node.children))
    }

    override fun visit(node: ASTSet, childrenValues: List<OPBase>): OPBase {
        val tmp = List(childrenValues.size) { childrenValues[it] as AOPBase }
        return AOPSet(tmp)
    }

    override fun visit(node: ASTOr, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size > 1)
        var res: AOPBase? = null
        for (v in childrenValues) {
            if (res == null)
                res = v as AOPBase
            else
                res = AOPOr(v as AOPBase, res)
        }
        return res!!
    }

    override fun visit(node: ASTAnd, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size > 1)
        var res: AOPBase? = null
        for (v in childrenValues) {
            if (res == null)
                res = v as AOPBase
            else
                res = AOPAnd(v as AOPBase, res)
        }
        return res!!
    }

    override fun visit(node: ASTEQ, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size == 2)
        return AOPEQ(childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTNEQ, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size == 2)
        return AOPNEQ(childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTLEQ, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size == 2)
        return AOPLEQ(childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTGEQ, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size == 2)
        return AOPGEQ(childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTLT, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size == 2)
        return AOPLT(childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTGT, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size == 2)
        return AOPGT(childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTIn, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size == 2)
        return AOPIn(childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTNotIn, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size == 2)
        return AOPNotIn(childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTAddition, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size > 1)
        var res: AOPBase? = null
        for (v in childrenValues) {
            if (res == null)
                res = v as AOPBase
            else
                res = AOPAddition(v as AOPBase, res)
        }
        return res!!
    }

    override fun visit(node: ASTSubtraction, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size > 1)
        var res: AOPBase? = null
        for (v in childrenValues) {
            if (res == null)
                res = v as AOPBase
            else
                res = AOPSubtraction(v as AOPBase, res)
        }
        return res!!
    }

    override fun visit(node: ASTMultiplication, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size > 1)
        var res: AOPBase? = null
        for (v in childrenValues) {
            if (res == null)
                res = v as AOPBase
            else
                res = AOPMultiplication(v as AOPBase, res)
        }
        return res!!
    }

    override fun visit(node: ASTDivision, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size > 1)
        var res: AOPBase? = null
        for (v in childrenValues) {
            if (res == null)
                res = v as AOPBase
            else
                res = AOPDivision(v as AOPBase, res)
        }
        return res!!
    }

    override fun visit(node: ASTNot, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size == 1)
        return AOPNot(childrenValues[0] as AOPBase)
    }

    override fun visit(node: ASTBase, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        return LOPPrefix("", node.iri)
    }

    override fun visit(node: ASTPrefix, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        return LOPPrefix(node.name, node.iri)
    }

    override fun visit(node: ASTAs, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        val a = node.variable.visit(this) as AOPVariable
        val b = node.expression.visit(this) as AOPBase
        if (b.getRequiredVariableNamesRecoursive().contains(a.name))
            throw Exception("variable must not be recousively defined $a")
        return LOPBind(a, b)
    }

    override fun visit(node: ASTBlankNode, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        return AOPVariable("#" + node.name)
    }

    override fun visit(node: ASTBuiltInCall, childrenValues: List<OPBase>): OPBase {
        when (node.function) {
            BuiltInFunctions.STR -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallSTR(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.LANG -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallLANG(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.LANGMATCHES -> {
                require(childrenValues.size == 2)
                return AOPBuildInCallLANGMATCHES(childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctions.DATATYPE -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallDATATYPE(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.BOUND -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallBOUND(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.IRI -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallIRI(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.URI -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallURI(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.BNODE -> {
                if (childrenValues.size == 1)
                    return AOPBuildInCallBNODE1(childrenValues[0] as AOPBase)
                return AOPBuildInCallBNODE0()
            }
            BuiltInFunctions.ABS -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallABS(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.CEIL -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallCEIL(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.FLOOR -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallFLOOR(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.ROUND -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallROUND(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.CONCAT -> {
                require(childrenValues.size > 0)
                var res = childrenValues[0] as AOPBase
                for (i in 1 until childrenValues.size)
                    res = AOPBuildInCallCONCAT(res, childrenValues[i] as AOPBase)
                return res
            }
            BuiltInFunctions.STRLEN -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallSTRLEN(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.UCASE -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallUCASE(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.LCASE -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallLCASE(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.CONTAINS -> {
                require(childrenValues.size == 2)
                return AOPBuildInCallCONTAINS(childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctions.STRSTARTS -> {
                require(childrenValues.size == 2)
                return AOPBuildInCallSTRSTARTS(childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctions.STRENDS -> {
                require(childrenValues.size == 2)
                return AOPBuildInCallSTRENDS(childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctions.YEAR -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallYEAR(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.MONTH -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallMONTH(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.DAY -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallDAY(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.HOURS -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallHOURS(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.MINUTES -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallMINUTES(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.SECONDS -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallSECONDS(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.TIMEZONE -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallTIMEZONE(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.TZ -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallTZ(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.NOW -> {
                require(childrenValues.size == 0)
                return queryExecutionStartTime
            }
            BuiltInFunctions.UUID -> {
                require(childrenValues.size == 0)
                return AOPBuildInCallUUID()
            }
            BuiltInFunctions.STRUUID -> {
                return AOPBuildInCallSTRUUID()
            }
            BuiltInFunctions.MD5 -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallMD5(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.SHA1 -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallSHA1(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.SHA256 -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallSHA256(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.IF -> {
                require(childrenValues.size == 3)
                return AOPBuildInCallIF(childrenValues[0] as AOPBase, childrenValues[1] as AOPBase, childrenValues[2] as AOPBase)
            }
            BuiltInFunctions.STRLANG -> {
                require(childrenValues.size == 2)
                return AOPBuildInCallSTRLANG(childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctions.STRDT -> {
                require(childrenValues.size == 2)
                return AOPBuildInCallSTRDT(childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctions.isLITERAL -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallIsLITERAL(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.isIRI -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallIsIri(childrenValues[0] as AOPBase)
            }
            BuiltInFunctions.isNUMERIC -> {
                require(childrenValues.size == 1)
                return AOPBuildInCallIsNUMERIC(childrenValues[0] as AOPBase)
            }
            else -> throw UnsupportedOperationException("${classNameToString(this)} ${node.function}")
        }
    }

    override fun visit(node: ASTAggregation, childrenValues: List<OPBase>): OPBase {
        when (node.type) {
            Aggregation.COUNT -> return AOPAggregationCOUNT(node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            Aggregation.MIN -> return AOPAggregationMIN(node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            Aggregation.MAX -> return AOPAggregationMAX(node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            Aggregation.SAMPLE -> return AOPAggregationSAMPLE(node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            Aggregation.AVG -> return AOPAggregationAVG(node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            Aggregation.SUM -> return AOPAggregationSUM(node.distinct, Array(childrenValues.size) { childrenValues[it] as AOPBase })
            else -> throw UnsupportedOperationException("${classNameToString(this)} ${node.type}")
        }
    }

    override fun visit(node: ASTUnion, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size == 2)
        return LOPUnion(childrenValues[0], childrenValues[1])
    }

    override fun visit(node: ASTFilter, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size == 1)
        return LOPFilter(childrenValues.first() as AOPBase)
    }

    override fun visit(node: ASTOrderCondition, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size == 1)
        val tmp = childrenValues.first() as AOPBase
        if (tmp is AOPVariable)
            return LOPSort(node.asc, tmp)
        val v = AOPVariable("#f${tmp.uuid}")
        return LOPSort(node.asc, v, LOPBind(v, tmp))
    }

    override fun visit(node: ASTVar, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        return AOPVariable(node.name)
    }

    override fun visit(node: ASTIri, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        return AOPIri(node.iri)
    }

    override fun visit(node: ASTGroup, childrenValues: List<OPBase>): OPBase {
        return LOPSubGroup(parseGroup(node.children))
    }

    override fun visit(node: ASTService, childrenValues: List<OPBase>): OPBase {
        when {
            node.iriOrVar is ASTIri -> return LOPServiceIRI(node.iriOrVar.iri, node.silent, parseGroup(node.children))
            node.iriOrVar is ASTVar -> return LOPServiceVAR(node.iriOrVar.name, node.silent, parseGroup(node.children))
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
        return LOPValues(variables, values)
    }

    override fun visit(node: ASTValue, childrenValues: List<OPBase>): OPBase {
        val tmp = List(childrenValues.size) { childrenValues[it] as AOPConstant }
        return AOPValue(tmp)
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
                    return LOPTriple(node.children[0] as AOPBase, node.children[1] as AOPBase, node.children[2] as AOPBase, iri, false)
                else
                    return node
            }
            is LOPFilter -> node.children[0] = setGraphNameForAllTriples(node.children[0], name, optional)
            is LOPJoin -> return LOPJoin(setGraphNameForAllTriples(node.children[0], name, optional), setGraphNameForAllTriples(node.children[1], name, optional), node.optional)
            else -> throw UnsupportedOperationException("${classNameToString(this)} setGraphNameForAllTriples 2 ${classNameToString(node)} $optional")
        }
        return node
    }

    override fun visit(node: ASTGraph, childrenValues: List<OPBase>): OPBase {
        var res: OPBase = OPNothing()
        for (c in childrenValues) {
            val tmp = setGraphNameForAllTriples(c, node.iriOrVar, false)
            if (res is OPNothing)
                res = tmp
            else
                res = LOPJoin(res, tmp, false)
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
        require(childrenValues.isEmpty())
        val g1 = graphRefToEnum(node.fromGraph)
        val g2 = graphRefToEnum(node.toGraph)
        val res = LOPGraphOperation(EGraphOperationType.ADD, node.silent, g1.first, g1.second, g2.first, g2.second)
        return res
    }

    override fun visit(node: ASTMove, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        val g1 = graphRefToEnum(node.fromGraph)
        val g2 = graphRefToEnum(node.toGraph)
        val res = LOPGraphOperation(EGraphOperationType.MOVE, node.silent, g1.first, g1.second, g2.first, g2.second)
        return res
    }

    override fun visit(node: ASTCopy, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        val g1 = graphRefToEnum(node.fromGraph)
        val g2 = graphRefToEnum(node.toGraph)
        val res = LOPGraphOperation(EGraphOperationType.COPY, node.silent, g1.first, g1.second, g2.first, g2.second)
        return res
    }

    override fun visit(node: ASTClear, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        val g1 = graphRefToEnum(node.graphref)
        val res = LOPGraphOperation(EGraphOperationType.CLEAR, node.silent, g1.first, g1.second)
        return res
    }

    override fun visit(node: ASTDrop, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        val g1 = graphRefToEnum(node.graphref)
        val res = LOPGraphOperation(EGraphOperationType.DROP, node.silent, g1.first, g1.second)
        return res
    }

    override fun visit(node: ASTCreate, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        val g1 = graphRefToEnum(node.graphref)
        val res = LOPGraphOperation(EGraphOperationType.CREATE, node.silent, g1.first, g1.second)
        return res
    }

    fun simpleAstToStringValue(node: ASTNode): AOPBase {
        return node.visit(this) as AOPBase
    }

    fun modifyDataHelper(children: Array<ASTNode>, modify: LOPModifyData) {
        for (c in children) {
            when {
                c is ASTTriple -> {
                    modify.data.add(LOPTriple(simpleAstToStringValue(c.children[0]), simpleAstToStringValue(c.children[1]), simpleAstToStringValue(c.children[2]), PersistentStoreLocal.defaultGraphName, false))
                }
                c is ASTGraph -> {
                    for (c2 in c.children) {
                        when {
                            c2 is ASTTriple -> {
                                modify.data.add(LOPTriple(simpleAstToStringValue(c2.children[0]), simpleAstToStringValue(c2.children[1]), simpleAstToStringValue(c2.children[2]), (c.iriOrVar as ASTIri).iri, true))
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
        val res = LOPModifyData(EModifyType.DELETE)
        modifyDataHelper(node.children, res)
        return res
    }

    override fun visit(node: ASTDeleteWhere, childrenValues: List<OPBase>): OPBase {
        val res = LOPModifyData(EModifyType.DELETE)
        modifyDataHelper(node.children, res)
        return res
    }

    override fun visit(node: ASTInsertData, childrenValues: List<OPBase>): OPBase {
        val res = LOPModifyData(EModifyType.INSERT)
        modifyDataHelper(node.children, res)
        return res
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
                    tmp = LOPUnion(tmp, tmp2)
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
            val res = LOPModify(insert, delete, setGraphNameForAllTriples(child, ASTIri(iri), true))
            return res
        } else {
            for (e in node.insert)
                insert.add(e.visit(this) as LOPTriple)
            for (e in node.delete)
                delete.add(e.visit(this) as LOPTriple)
            val res = LOPModify(insert, delete, child)
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
