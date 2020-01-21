package lupos.s2buildOperatorGraph

import lupos.s1buildSyntaxTree.sparql1_1.ASTAdd
import lupos.s1buildSyntaxTree.sparql1_1.ASTAddition
import lupos.s1buildSyntaxTree.sparql1_1.ASTAggregation
import lupos.s1buildSyntaxTree.sparql1_1.ASTAllGraphRef
import lupos.s1buildSyntaxTree.sparql1_1.ASTAnd
import lupos.s1buildSyntaxTree.sparql1_1.ASTAs
import lupos.s1buildSyntaxTree.sparql1_1.ASTAskQuery
import lupos.s1buildSyntaxTree.sparql1_1.ASTBase
import lupos.s1buildSyntaxTree.sparql1_1.ASTBlankNode
import lupos.s1buildSyntaxTree.sparql1_1.ASTBooleanLiteral
import lupos.s1buildSyntaxTree.sparql1_1.ASTBuiltInCall
import lupos.s1buildSyntaxTree.sparql1_1.ASTClear
import lupos.s1buildSyntaxTree.sparql1_1.ASTConstructQuery
import lupos.s1buildSyntaxTree.sparql1_1.ASTCopy
import lupos.s1buildSyntaxTree.sparql1_1.ASTCreate
import lupos.s1buildSyntaxTree.sparql1_1.ASTDatasetClause
import lupos.s1buildSyntaxTree.sparql1_1.ASTDecimal
import lupos.s1buildSyntaxTree.sparql1_1.ASTDefaultGraph
import lupos.s1buildSyntaxTree.sparql1_1.ASTDefaultGraphRef
import lupos.s1buildSyntaxTree.sparql1_1.ASTDeleteData
import lupos.s1buildSyntaxTree.sparql1_1.ASTDeleteWhere
import lupos.s1buildSyntaxTree.sparql1_1.ASTDescribeQuery
import lupos.s1buildSyntaxTree.sparql1_1.ASTDivision
import lupos.s1buildSyntaxTree.sparql1_1.ASTDouble
import lupos.s1buildSyntaxTree.sparql1_1.ASTDrop
import lupos.s1buildSyntaxTree.sparql1_1.ASTEQ
import lupos.s1buildSyntaxTree.sparql1_1.ASTFilter
import lupos.s1buildSyntaxTree.sparql1_1.ASTFunctionCall
import lupos.s1buildSyntaxTree.sparql1_1.ASTGEQ
import lupos.s1buildSyntaxTree.sparql1_1.ASTGraph
import lupos.s1buildSyntaxTree.sparql1_1.ASTGraphRef
import lupos.s1buildSyntaxTree.sparql1_1.ASTGrapOperation
import lupos.s1buildSyntaxTree.sparql1_1.ASTGroup
import lupos.s1buildSyntaxTree.sparql1_1.ASTGroupConcat
import lupos.s1buildSyntaxTree.sparql1_1.ASTGT
import lupos.s1buildSyntaxTree.sparql1_1.ASTIn
import lupos.s1buildSyntaxTree.sparql1_1.ASTInsertData
import lupos.s1buildSyntaxTree.sparql1_1.ASTInteger
import lupos.s1buildSyntaxTree.sparql1_1.ASTIri
import lupos.s1buildSyntaxTree.sparql1_1.ASTIriGraphRef
import lupos.s1buildSyntaxTree.sparql1_1.ASTLanguageTaggedLiteral
import lupos.s1buildSyntaxTree.sparql1_1.ASTLEQ
import lupos.s1buildSyntaxTree.sparql1_1.ASTLiteral
import lupos.s1buildSyntaxTree.sparql1_1.ASTLoad
import lupos.s1buildSyntaxTree.sparql1_1.ASTLT
import lupos.s1buildSyntaxTree.sparql1_1.ASTMinus
import lupos.s1buildSyntaxTree.sparql1_1.ASTMinusGroup
import lupos.s1buildSyntaxTree.sparql1_1.ASTModify
import lupos.s1buildSyntaxTree.sparql1_1.ASTModifyWithWhere
import lupos.s1buildSyntaxTree.sparql1_1.ASTMove
import lupos.s1buildSyntaxTree.sparql1_1.ASTMultiplication
import lupos.s1buildSyntaxTree.sparql1_1.ASTNamedGraph
import lupos.s1buildSyntaxTree.sparql1_1.ASTNamedGraphRef
import lupos.s1buildSyntaxTree.sparql1_1.ASTNamedIriGraphRef
import lupos.s1buildSyntaxTree.sparql1_1.ASTNEQ
import lupos.s1buildSyntaxTree.sparql1_1.ASTNode
import lupos.s1buildSyntaxTree.sparql1_1.*
import lupos.s1buildSyntaxTree.sparql1_1.ASTNotIn
import lupos.s1buildSyntaxTree.sparql1_1.ASTNumericLiteral
import lupos.s1buildSyntaxTree.sparql1_1.ASTOptional
import lupos.s1buildSyntaxTree.sparql1_1.ASTOr
import lupos.s1buildSyntaxTree.sparql1_1.ASTOrderCondition
import lupos.s1buildSyntaxTree.sparql1_1.ASTPathAlternatives
import lupos.s1buildSyntaxTree.sparql1_1.ASTPathArbitraryOccurrences
import lupos.s1buildSyntaxTree.sparql1_1.ASTPathArbitraryOccurrencesNotZero
import lupos.s1buildSyntaxTree.sparql1_1.ASTPathInverse
import lupos.s1buildSyntaxTree.sparql1_1.ASTPathNegatedPropertySet
import lupos.s1buildSyntaxTree.sparql1_1.ASTPathOptionalOccurrence
import lupos.s1buildSyntaxTree.sparql1_1.ASTPathSequence
import lupos.s1buildSyntaxTree.sparql1_1.ASTPlus
import lupos.s1buildSyntaxTree.sparql1_1.ASTPrefix
import lupos.s1buildSyntaxTree.sparql1_1.ASTQuery
import lupos.s1buildSyntaxTree.sparql1_1.ASTQueryBaseClass
import lupos.s1buildSyntaxTree.sparql1_1.ASTRDFTerm
import lupos.s1buildSyntaxTree.sparql1_1.ASTSelectQuery
import lupos.s1buildSyntaxTree.sparql1_1.ASTService
import lupos.s1buildSyntaxTree.sparql1_1.ASTSet
import lupos.s1buildSyntaxTree.sparql1_1.ASTSimpleLiteral
import lupos.s1buildSyntaxTree.sparql1_1.ASTSubSelectQuery
import lupos.s1buildSyntaxTree.sparql1_1.ASTSubtraction
import lupos.s1buildSyntaxTree.sparql1_1.ASTTriple
import lupos.s1buildSyntaxTree.sparql1_1.ASTTypedLiteral
import lupos.s1buildSyntaxTree.sparql1_1.ASTUndef
import lupos.s1buildSyntaxTree.sparql1_1.ASTUnion
import lupos.s1buildSyntaxTree.sparql1_1.ASTUpdateGrapOperation
import lupos.s1buildSyntaxTree.sparql1_1.ASTValue
import lupos.s1buildSyntaxTree.sparql1_1.ASTValues
import lupos.s1buildSyntaxTree.sparql1_1.ASTVar
import lupos.s1buildSyntaxTree.sparql1_1.Visitor
import lupos.s2buildOperatorGraph.data.LOPExpression
import lupos.s2buildOperatorGraph.data.LOPTriple
import lupos.s2buildOperatorGraph.data.LOPValues
import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s2buildOperatorGraph.multiinput.LOPJoin
import lupos.s2buildOperatorGraph.multiinput.LOPMinus
import lupos.s2buildOperatorGraph.multiinput.LOPUnion
import lupos.s2buildOperatorGraph.singleinput.LOPBind
import lupos.s2buildOperatorGraph.singleinput.LOPFilter
import lupos.s2buildOperatorGraph.singleinput.LOPGroup
import lupos.s2buildOperatorGraph.singleinput.LOPNOOP
import lupos.s2buildOperatorGraph.singleinput.LOPOptional
import lupos.s2buildOperatorGraph.singleinput.LOPProjection
import lupos.s2buildOperatorGraph.singleinput.LOPSingleInputBase
import lupos.s2buildOperatorGraph.singleinput.LOPSort
import lupos.s2buildOperatorGraph.singleinput.*
import lupos.s2buildOperatorGraph.singleinput.modifiers.LOPDistinct
import lupos.s2buildOperatorGraph.singleinput.modifiers.LOPLimit
import lupos.s2buildOperatorGraph.singleinput.modifiers.LOPOffset
import lupos.s2buildOperatorGraph.singleinput.modifiers.LOPPrefix
import lupos.s2buildOperatorGraph.singleinput.modifiers.LOPReduced

class OperatorGraphVisitor : Visitor<OPBase> {
    override fun visit(node: ASTNode, childrenValues: List<OPBase>): OPBase = LOPNOOP()

    fun mergeLOPBind(a: LOPBind, b: LOPBind): LOPBind {
        val aName = ((a!! as LOPBind).name as LOPVariable).name
        if (b.expression.getRequiredVariableNames().contains(aName)) {
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

    override fun visit(node: ASTSubSelectQuery, childrenValues: List<OPBase>): OPBase {
        if (node.existsValues()) {
            throw UnsupportedOperationException("${this::class.simpleName} Values ${node::class.simpleName}")
        }
        return LOPSubGroup(visit(node as ASTSelectQuery, childrenValues))
    }

    override fun visit(node: ASTSelectQuery, childrenValues: List<OPBase>): OPBase {
	return visitSelectBase(node,node.select,node.distinct,node.reduced)
    }

    fun visitSelectBase(node: ASTQueryBaseClass,select: Array<ASTNode>, distinct: Boolean, reduced: Boolean):OPBase{
        val result = LOPNOOP()
        var bind: LOPBind? = null
        var bindIsAggregate = false
        if (select.size>0) {
            val projection = LOPProjection()
            result.getLatestChild().setChild(projection)
            for (sel in select) {
                when (sel) {
                    is ASTVar -> {
                        projection.variables.add(LOPVariable(sel.name))
                    }
                    is ASTAs -> {
                        val v = LOPVariable(sel.variable.name)
                        projection.variables.add(v)
                        val tmp2 = LOPBind(v, sel.expression.visit(this))
                        bindIsAggregate = bindIsAggregate || containsAggregate(sel.expression)
                        if (bind != null)
                            bind = mergeLOPBind(bind, tmp2)
                        else
                            bind = tmp2
                    }
                    else -> {
                        throw UnsupportedOperationException("${this::class.simpleName} Select-Parameter ${node::class.simpleName}")
                    }
                }
            }
        }
        result.getLatestChild().setChild(visitQueryBase(node, bind, bindIsAggregate, distinct, reduced))
        return LOPSubGroup(result)
	}

    override fun visit(node: ASTDescribeQuery, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Query Type ${node::class.simpleName}")
    }

    override fun visit(node: ASTConstructQuery, childrenValues: List<OPBase>): OPBase {
        var result: OPBase? = null
        val child = visitQueryBase(node, null, false, false, false)
        for (t in node.template) {
            val template = t.visit(this)
            var tmp: OPBase = LOPProjection()
            for (v in template.getProvidedVariableNames())
                (tmp as LOPProjection).variables.add(LOPVariable(v))
            (tmp as LOPProjection).setChild(LOPJoin(child, template, false))
            when {
                t is ASTTriple -> {
                    val s = t.children[0]
                    val p = t.children[1]
                    val o = t.children[2]
                    if (s is ASTVar)
                        tmp = LOPRename(LOPVariable("s"), LOPVariable(s.name), tmp)
                    else
                        tmp = LOPBind(LOPVariable("s"), s.visit(this), tmp)
                    if (p is ASTVar)
                        tmp = LOPRename(LOPVariable("p"), LOPVariable(p.name), tmp)
                    else
                        tmp = LOPBind(LOPVariable("p"), p.visit(this), tmp)
                    if (o is ASTVar)
                        tmp = LOPRename(LOPVariable("o"), LOPVariable(o.name), tmp)
                    else
                        tmp = LOPBind(LOPVariable("o"), o.visit(this), tmp)
                }
                else -> throw UnsupportedOperationException("${this::class.simpleName} template ${t::class.simpleName}")
            }
            if (result == null)
                result = tmp
            else
                result = LOPUnion(result, tmp)
        }
        if (result == null)
            return LOPNOOP()
        return LOPDistinct(result)
    }

    fun visitQueryBase(node: ASTQueryBaseClass, bindp: LOPBind?, bindIsAggregate: Boolean, distinct: Boolean, reduced: Boolean): OPBase {
        var bind = bindp
        val result = LOPNOOP()
        if (node.existsLimit()) {
            result.getLatestChild().setChild(LOPLimit(node.limit))
        }
        if (node.existsOffset()) {
            result.getLatestChild().setChild(LOPOffset(node.offset))
        }
        if (distinct) {
            result.getLatestChild().setChild(LOPDistinct())
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
                    val expression = h.visit(this) as LOPExpression
                    val tmpVar = LOPVariable("#f${expression.uuid}")
                    val tmpBind = LOPBind(tmpVar, expression)
                    if (bind != null)
                        bind = mergeLOPBind(bind, tmpBind)
                    else
                        bind = tmpBind
                    result.getLatestChild().setChild(LOPFilter(LOPExpression(ASTVar(tmpVar.name))))
                }
            }
            val variables = mutableListOf<LOPVariable>()
            var child: LOPBind? = null
            for (b in node.groupBy) {
                when (b) {
                    is ASTVar -> {
                        variables.add(b.visit(this) as LOPVariable)
                    }
                    is ASTAs -> {
                        val v = LOPVariable(b.variable.name)
                        variables.add(v)
                        val tmp2 = LOPBind(v, b.expression.visit(this))
                        if (child != null)
                            child = mergeLOPBind(child, tmp2)
                        else
                            child = tmp2
                    }
                    else -> {
                        throw UnsupportedOperationException("${this::class.simpleName} Group-Parameter ${node::class.simpleName}")
                    }
                }
            }
            if (child == null)
                result.getLatestChild().setChild(LOPGroup(variables, bind as LOPBind?, LOPNOOP()))
            else
                result.getLatestChild().setChild(LOPGroup(variables, bind as LOPBind?, child))
        } else {
            if (node.existsHaving()) {
                for (h in node.having) {
                    val expression = h.visit(this) as LOPExpression
                    val tmpVar = LOPVariable("#f${expression.uuid}")
                    val tmpBind = LOPBind(tmpVar, expression)
                    if (bind != null)
                        bind = mergeLOPBind(bind, tmpBind)
                    else
                        bind = tmpBind
                    result.getLatestChild().setChild(LOPFilter(LOPExpression(ASTVar(tmpVar.name))))
                }
                result.getLatestChild().setChild(LOPGroup(mutableListOf<LOPVariable>(), bind as LOPBind?, LOPNOOP()))
            } else {
                if (bindIsAggregate) {
                    result.getLatestChild().setChild(LOPGroup(mutableListOf<LOPVariable>(), bind as LOPBind?, LOPNOOP()))
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

    enum class GroupMember {
        GMLOPFilter, GMLOPMinus, GMLOPDataSource, GMLOPOptional
    }

    private fun parseGroup(nodes: Array<ASTNode>): OPBase {
        if (nodes.isEmpty()) {
            return LOPNOOP()
        }
        var result: OPBase? = null
        val bind = mutableListOf<LOPBind>()
        var members = mutableMapOf<GroupMember, OPBase>()
        for (n in nodes) {
            var tmp2 = n.visit(this)
            while (tmp2 is LOPNOOP) {
                tmp2 = tmp2.child
            }
            when (tmp2) {
                is LOPMinus -> {
                    if (members.containsKey(GroupMember.GMLOPMinus))
                        (members[GroupMember.GMLOPMinus] as LOPSingleInputBase).getLatestChild().setChild(tmp2)
                    else
                        members[GroupMember.GMLOPMinus] = tmp2
                }
                is LOPFilter -> {
                    if (members.containsKey(GroupMember.GMLOPFilter))
                        (members[GroupMember.GMLOPFilter] as LOPSingleInputBase).getLatestChild().setChild(tmp2)
                    else
                        members[GroupMember.GMLOPFilter] = tmp2
                }
                is LOPProjection -> {
                    if (members.containsKey(GroupMember.GMLOPDataSource))
                        members[GroupMember.GMLOPDataSource] = LOPJoin(members[GroupMember.GMLOPDataSource]!!, tmp2, false)
                    else
                        members[GroupMember.GMLOPDataSource] = tmp2
                }
                is LOPBind -> {
                    bind.add(tmp2)
                }
                is LOPTriple -> {
                    if (members.containsKey(GroupMember.GMLOPDataSource)) {
                        members[GroupMember.GMLOPDataSource] = LOPJoin(members[GroupMember.GMLOPDataSource]!!, tmp2, false)
                    } else {
                        members[GroupMember.GMLOPDataSource] = tmp2
                    }
                }
                is LOPUnion -> {
                    if (members.containsKey(GroupMember.GMLOPDataSource)) {
                        members[GroupMember.GMLOPDataSource] = LOPJoin(members[GroupMember.GMLOPDataSource]!!, tmp2, false)
                    } else {
                        members[GroupMember.GMLOPDataSource] = tmp2
                    }
                }
                is LOPValues -> {
                    if (members.containsKey(GroupMember.GMLOPDataSource)) {
                        members[GroupMember.GMLOPDataSource] = LOPJoin(members[GroupMember.GMLOPDataSource]!!, tmp2, false)
                    } else {
                        members[GroupMember.GMLOPDataSource] = tmp2
                    }
                }
                is LOPOptional -> {
                    if (members.containsKey(GroupMember.GMLOPOptional)) {
                        members[GroupMember.GMLOPOptional] = LOPJoin(members[GroupMember.GMLOPOptional]!!, tmp2.child, true)
                    } else {
                        members[GroupMember.GMLOPOptional] = tmp2.child
                    }
                }
                is LOPJoin -> {
                    if (members.containsKey(GroupMember.GMLOPDataSource)) {
                        members[GroupMember.GMLOPDataSource] = LOPJoin(members[GroupMember.GMLOPDataSource]!!, tmp2, true)
                    } else {
                        members[GroupMember.GMLOPDataSource] = tmp2
                    }
                }
                is LOPSubGroup -> {
                    if (members.containsKey(GroupMember.GMLOPDataSource)) {
                        members[GroupMember.GMLOPDataSource] = LOPJoin(members[GroupMember.GMLOPDataSource]!!, tmp2, false)
                    } else {
                        members[GroupMember.GMLOPDataSource] = tmp2
                    }
                }
                else ->
                    throw UnsupportedOperationException("${this::class.simpleName} GroupMember ${tmp2::class.simpleName}")
            }
        }
        if (members.containsKey(GroupMember.GMLOPMinus)) {
            result = members[GroupMember.GMLOPMinus]
        }
        if (members.containsKey(GroupMember.GMLOPFilter)) {
            if (result == null)
                result = members[GroupMember.GMLOPFilter]
            else
                (result as LOPSingleInputBase).getLatestChild().setChild(members[GroupMember.GMLOPFilter]!!)
        }
        var firstJoin: OPBase? = null
        if (members.containsKey(GroupMember.GMLOPDataSource)) {
            firstJoin = members[GroupMember.GMLOPDataSource]
        }
        if (members.containsKey(GroupMember.GMLOPOptional)) {
            if (firstJoin == null)
                firstJoin = LOPOptional(members[GroupMember.GMLOPOptional]!!)
            else
                firstJoin = LOPJoin(firstJoin, members[GroupMember.GMLOPOptional]!!, true)
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
                (result as LOPSingleInputBase).getLatestChild().setChild(firstJoin)
        }
        return result!!
    }

    fun insertLOPBind(a: OPBase, b: LOPBind): OPBase {
        if (a is LOPJoin) {
            val requiredVariables = b.expression.getRequiredVariableNames()
            val providedLeft = a.child.getProvidedVariableNames()
            var leftOk = true
            for (v in requiredVariables) {
                if (!providedLeft.contains(v)) {
                    leftOk = false
                    break
                }
            }
            val providedRight = a.second.getProvidedVariableNames()
            var rightOk = true
            for (v in requiredVariables) {
                if (!providedRight.contains(v)) {
                    rightOk = false
                    break
                }
            }
            if (leftOk != rightOk) {
                if (leftOk)
                    a.child = insertLOPBind(a.child, b)
                else
                    return LOPJoin(a.child, insertLOPBind(a.second, b), a.optional)
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
                query = q
            }
        }
        if (values != null && prefix != null) {
            prefix.getLatestChild().setChild(joinValuesAndQuery(values, query))
            return prefix
        } else if (values != null) {
            return joinValuesAndQuery(values, query)
        } else if (prefix != null) {
            prefix.getLatestChild().setChild(query)
            return prefix
        }
        return query
    }

    private fun joinValuesAndQuery(values: OPBase, query: OPBase): OPBase {
        if (query !is LOPProjection)
            return LOPJoin(values, query, false)
        var latestProjection = query
        var realQuery = query
        while (realQuery is LOPProjection) {
            latestProjection = realQuery
            realQuery = realQuery.child
        }
        (latestProjection as LOPProjection).setChild(LOPJoin(values, realQuery, false))
        return query
    }

    override fun visit(node: ASTUndef, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTSimpleLiteral, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTTypedLiteral, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTLanguageTaggedLiteral, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTBooleanLiteral, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTNumericLiteral, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTInteger, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTDouble, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTDecimal, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTFunctionCall, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTTriple, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size == 3)
        return LOPTriple(childrenValues[0], childrenValues[1], childrenValues[2])
    }

    override fun visit(node: ASTOptional, childrenValues: List<OPBase>): OPBase {
        return LOPOptional(parseGroup(node.children))
    }

    override fun visit(node: ASTSet, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTOr, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTAnd, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTEQ, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTNEQ, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTLEQ, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTGEQ, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTLT, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTGT, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTIn, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTNotIn, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTAddition, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTSubtraction, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTMultiplication, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTDivision, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTNot, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
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
        return LOPBind(node.variable.visit(this) as LOPVariable, node.expression.visit(this))
    }

    override fun visit(node: ASTBuiltInCall, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTAggregation, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTMinusGroup, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isNotEmpty())
        return LOPMinus(LOPNOOP(), parseGroup(node.children))
    }

    override fun visit(node: ASTUnion, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size == 2)
        return LOPUnion(childrenValues[0], childrenValues[1])
    }

    override fun visit(node: ASTFilter, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size == 1)
        return LOPFilter(childrenValues.first() as LOPExpression)
    }

    override fun visit(node: ASTOrderCondition, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.size == 1)
        return LOPSort(node.asc, childrenValues.first())
    }

    override fun visit(node: ASTVar, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        return LOPVariable(node.name)
    }

    override fun visit(node: ASTLiteral, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        return LOPExpression(node)
    }

    override fun visit(node: ASTIri, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        return LOPExpression(node)
    }

    override fun visit(node: ASTGroup, childrenValues: List<OPBase>): OPBase {
        return LOPSubGroup(parseGroup(node.children))
    }

    override fun visit(node: ASTValues, childrenValues: List<OPBase>): OPBase {
        if (node.variables.isEmpty()) {
            return LOPNOOP()
        }
        val variables = mutableListOf<LOPVariable>()
        val values = mutableListOf<LOPExpression>()
        for (v in node.variables) {
            variables.add(v.visit(this) as LOPVariable)
        }
        for (v in node.children) {
            values.add(LOPExpression(v))
        }
        return LOPValues(variables, values)
    }

    override fun visit(node: ASTValue, childrenValues: List<OPBase>): OPBase {
        return LOPExpression(node)
    }

    override fun visit(node: ASTAdd, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Graph ${node::class.simpleName}")
    }

    override fun visit(node: ASTMove, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Graph ${node::class.simpleName}")
    }

    override fun visit(node: ASTCopy, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Graph ${node::class.simpleName}")
    }

    override fun visit(node: ASTGraph, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Graph ${node::class.simpleName}")
    }

    override fun visit(node: ASTDefaultGraph, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Graph ${node::class.simpleName}")
    }

    override fun visit(node: ASTNamedGraph, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Graph ${node::class.simpleName}")
    }

    override fun visit(node: ASTGraphRef, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Graph ${node::class.simpleName}")
    }

    override fun visit(node: ASTIriGraphRef, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Graph ${node::class.simpleName}")
    }

    override fun visit(node: ASTNamedIriGraphRef, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Graph ${node::class.simpleName}")
    }

    override fun visit(node: ASTDefaultGraphRef, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Graph ${node::class.simpleName}")
    }

    override fun visit(node: ASTNamedGraphRef, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Graph ${node::class.simpleName}")
    }

    override fun visit(node: ASTAllGraphRef, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Graph ${node::class.simpleName}")
    }

    override fun visit(node: ASTGrapOperation, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Graph ${node::class.simpleName}")
    }

    override fun visit(node: ASTUpdateGrapOperation, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Graph ${node::class.simpleName}")
    }

    override fun visit(node: ASTClear, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Graph ${node::class.simpleName}")
    }

    override fun visit(node: ASTLoad, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Graph ${node::class.simpleName}")
    }

    override fun visit(node: ASTDrop, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Graph ${node::class.simpleName}")
    }

    override fun visit(node: ASTCreate, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Graph ${node::class.simpleName}")
    }

    override fun visit(node: ASTModify, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Update ${node::class.simpleName}")
    }

    override fun visit(node: ASTDeleteData, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Update ${node::class.simpleName}")
    }

    override fun visit(node: ASTDeleteWhere, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Update ${node::class.simpleName}")
    }

    override fun visit(node: ASTInsertData, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Update ${node::class.simpleName}")
    }

    override fun visit(node: ASTModifyWithWhere, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Update ${node::class.simpleName}")
    }

    override fun visit(node: ASTPathAlternatives, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Path ${node::class.simpleName}")
    }

    override fun visit(node: ASTPathSequence, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Path ${node::class.simpleName}")
    }

    override fun visit(node: ASTPathInverse, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Path ${node::class.simpleName}")
    }

    override fun visit(node: ASTPathArbitraryOccurrences, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Path ${node::class.simpleName}")
    }

    override fun visit(node: ASTPathOptionalOccurrence, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Path ${node::class.simpleName}")
    }

    override fun visit(node: ASTPathArbitraryOccurrencesNotZero, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Path ${node::class.simpleName}")
    }

    override fun visit(node: ASTPathNegatedPropertySet, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Path ${node::class.simpleName}")
    }

    override fun visit(node: ASTGroupConcat, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Group ${node::class.simpleName}")
    }

    override fun visit(node: ASTBlankNode, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Blank Node ${node::class.simpleName}")
    }

    override fun visit(node: ASTDatasetClause, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Query Type ${node::class.simpleName}")
    }

    override fun visit(node: ASTAskQuery, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Query Type ${node::class.simpleName}")
    }

    override fun visit(node: ASTService, childrenValues: List<OPBase>): OPBase {
        throw UnsupportedOperationException("${this::class.simpleName} Service ${node::class.simpleName}")
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
}
