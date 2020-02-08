package lupos.s06buildOperatorGraph
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.ModifyDataType

import lupos.s00misc.classNameToString
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
import lupos.s02buildSyntaxTree.sparql1_1.Visitor
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.GraphOperationType
import lupos.s04logicalOperators.noinput.LOPExpression
import lupos.s04logicalOperators.noinput.LOPGraphOperation
import lupos.s04logicalOperators.noinput.LOPModifyData
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.noinput.LOPVariable
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


class OperatorGraphVisitor : Visitor<OPBase> {
    override fun visit(node: ASTNode, childrenValues: List<OPBase>): OPBase = LOPNOOP()

    fun mergeLOPBind(a: LOPBind, b: LOPBind): LOPBind {
        val aName = a.name.name
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

    override fun visit(node: ASTAskQuery, childrenValues: List<OPBase>): OPBase {
        return LOPMakeBooleanResult(visitSelectBase(node, arrayOf<ASTNode>(), false, false))
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
        if (distinct) {
            result.getLatestChild().setChild(LOPDistinct())
        }
        val projection = LOPProjection()
        result.getLatestChild().setChild(projection)
        if (select.size > 0) {
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
                    projection.variables.add(LOPVariable(s))
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
        val array = Array<ASTNode>(template.size) { itr.next() }
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
                else -> throw UnsupportedOperationException("${classNameToString(this)} template ${classNameToString(t)}")
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
                    val expression = h.visit(this) as LOPExpression
                    val tmpVar = LOPVariable("#f${expression.uuid}")
                    val tmpBind = LOPBind(tmpVar, expression)
                    if (bind != null)
                        bind = mergeLOPBind(bind, tmpBind)
                    else
                        bind = tmpBind
                    result.getLatestChild().setChild(LOPFilter(LOPExpression(ASTVar(tmpVar.name))))
                }
                result.getLatestChild().setChild(LOPGroup(mutableListOf<LOPVariable>(), bind, LOPNOOP()))
            } else {
                if (bindIsAggregate) {
                    result.getLatestChild().setChild(LOPGroup(mutableListOf<LOPVariable>(), bind, LOPNOOP()))
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
                tmp2 = tmp2.children[0]
            }
            when (tmp2) {
                is LOPMinus -> {
                    if (members.containsKey(GroupMember.GMLOPMinus))
                        (members[GroupMember.GMLOPMinus])!!.getLatestChild().setChild(tmp2)
                    else
                        members[GroupMember.GMLOPMinus] = tmp2
                }
                is LOPFilter -> {
                    if (members.containsKey(GroupMember.GMLOPFilter))
                        (members[GroupMember.GMLOPFilter])!!.getLatestChild().setChild(tmp2)
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
                        members[GroupMember.GMLOPOptional] = LOPJoin(members[GroupMember.GMLOPOptional]!!, tmp2.children[0], true)
                    } else {
                        members[GroupMember.GMLOPOptional] = tmp2.children[0]
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
                is LOPServiceIRI -> {
                    if (members.containsKey(GroupMember.GMLOPDataSource)) {
                        members[GroupMember.GMLOPDataSource] = LOPJoin(members[GroupMember.GMLOPDataSource]!!, tmp2, true)
                    } else {
                        members[GroupMember.GMLOPDataSource] = tmp2
                    }
                }
                is LOPServiceVAR -> {
                    if (members.containsKey(GroupMember.GMLOPDataSource)) {
                        tmp2.children[0] = members[GroupMember.GMLOPDataSource]!!
                        members[GroupMember.GMLOPDataSource] = tmp2
                    } else {
                        members[GroupMember.GMLOPDataSource] = tmp2
                    }
                }
                else ->
                    throw UnsupportedOperationException("${classNameToString(this)} GroupMember ${classNameToString(tmp2)}")
            }
        }
        if (members.containsKey(GroupMember.GMLOPMinus)) {
            result = members[GroupMember.GMLOPMinus]
        }
        if (members.containsKey(GroupMember.GMLOPFilter)) {
            if (result == null)
                result = members[GroupMember.GMLOPFilter]
            else
                (result).getLatestChild().setChild(members[GroupMember.GMLOPFilter]!!)
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
                (result).getLatestChild().setChild(firstJoin)
        }
        return result!!
    }

    fun insertLOPBind(a: OPBase, b: LOPBind): OPBase {
        if (a is LOPJoin) {
            val requiredVariables = b.expression.getRequiredVariableNames()
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
                require(query is LOPNOOP)
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
            realQuery = realQuery.children[0]
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
        return LOPTriple(childrenValues[0], childrenValues[1], childrenValues[2], "")
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

    override fun visit(node: ASTBlankNode, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        return LOPVariable("#" + node.name)
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

    override fun visit(node: ASTService, childrenValues: List<OPBase>): OPBase {
        when {
            node.iriOrVar is ASTIri -> return LOPServiceIRI(node.iriOrVar.iri, node.silent, parseGroup(node.children))
            node.iriOrVar is ASTVar -> return LOPServiceVAR(node.iriOrVar.name, node.silent, parseGroup(node.children))
            else -> throw UnsupportedOperationException("${classNameToString(this)} Service ${classNameToString(node)} ${classNameToString(node.iriOrVar)}")
        }
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

    fun setGraphNameForAllTriples(node: OPBase, name: ASTNode): OPBase {
        val iri = when (name) {
            is ASTIri -> name.iri
            is ASTIriGraphRef -> name.iri
            else -> throw UnsupportedOperationException("${classNameToString(this)} setGraphNameForAllTriples 1 ${classNameToString(node)} ${classNameToString(name)}")
        }
        when (node) {
            is OPNothing -> return node
            is LOPTriple -> return LOPTriple(node.s, node.p, node.o, iri)
            is LOPFilter -> node.children[0] = setGraphNameForAllTriples(node.children[0], name)
            is LOPJoin -> return LOPJoin(setGraphNameForAllTriples(node.children[0], name), setGraphNameForAllTriples(node.children[1], name), node.optional)
            else -> throw UnsupportedOperationException("${classNameToString(this)} setGraphNameForAllTriples 2 ${classNameToString(node)}")
        }
        return node
    }

    override fun visit(node: ASTGraph, childrenValues: List<OPBase>): OPBase {
//val iriOrVar: ASTNode, constraint: Array<ASTNode>
        var res: OPBase = OPNothing()
        for (c in childrenValues) {
            val tmp = setGraphNameForAllTriples(c, node.iriOrVar)
            if (res is OPNothing)
                res = tmp
            else
                res = LOPJoin(res, tmp, false)
        }
        return res
    }

    override fun visit(node: ASTAdd, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        val res = LOPGraphOperation()
        res.action = GraphOperationType.ADD
        res.silent = node.silent
        res.graphref1 = node.fromGraph
        res.graphref2 = node.toGraph
        return res
    }

    override fun visit(node: ASTMove, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        val res = LOPGraphOperation()
        res.action = GraphOperationType.MOVE
        res.silent = node.silent
        res.graphref1 = node.fromGraph
        res.graphref2 = node.toGraph
        return res
    }

    override fun visit(node: ASTCopy, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        val res = LOPGraphOperation()
        res.action = GraphOperationType.COPY
        res.silent = node.silent
        res.graphref1 = node.fromGraph
        res.graphref2 = node.toGraph
        return res
    }

    override fun visit(node: ASTClear, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        val res = LOPGraphOperation()
        res.action = GraphOperationType.CLEAR
        res.silent = node.silent
        res.graphref1 = node.graphref
        return res
    }

    override fun visit(node: ASTDrop, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        val res = LOPGraphOperation()
        res.action = GraphOperationType.DROP
        res.silent = node.silent
        res.graphref1 = node.graphref
        return res
    }

    override fun visit(node: ASTCreate, childrenValues: List<OPBase>): OPBase {
        require(childrenValues.isEmpty())
        val res = LOPGraphOperation()
        res.action = GraphOperationType.CREATE
        res.silent = node.silent
        res.graphref1 = node.graphref
        return res
    }

    fun simpleAstToStringValue(node: ASTNode): Pair<String, Boolean> {
        when (node) {
            is ASTIri -> return Pair("<" + node.iri + ">", true)
            is ASTInteger -> return Pair("" + node.value + "^^<http://www.w3.org/2001/XMLSchema#integer>", true)
            is ASTDecimal -> return Pair("" + node.image + "^^<http://www.w3.org/2001/XMLSchema#decimal>", true)
            is ASTSimpleLiteral -> return Pair(node.content, true)
            is ASTBlankNode -> return Pair("_:" + node.name, true)
            is ASTVar -> return Pair(node.name, false)
            else -> throw UnsupportedOperationException("${classNameToString(this)} simpleAstToStringValue ${classNameToString(node)}")
        }
    }

    fun modifyDataHelper(children: Array<ASTNode>, modify: LOPModifyData) {
        for (c in children) {
            when {
                c is ASTTriple -> {
                    modify.data.add(mutableListOf(simpleAstToStringValue(c.children[0]), simpleAstToStringValue(c.children[1]), simpleAstToStringValue(c.children[2]), Pair("", true)))
                }
                c is ASTGraph -> {
                    for (c2 in c.children) {
                        when {
                            c2 is ASTTriple -> {
                                modify.data.add(mutableListOf(simpleAstToStringValue(c2.children[0]), simpleAstToStringValue(c2.children[1]), simpleAstToStringValue(c2.children[2]), Pair((c.iriOrVar as ASTIri).iri, true)))
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
        val res = LOPModifyData(ModifyDataType.DELETE)
        modifyDataHelper(node.children, res)
        return res
    }

    override fun visit(node: ASTDeleteWhere, childrenValues: List<OPBase>): OPBase {
        val res = LOPModifyData(ModifyDataType.DELETE)
        modifyDataHelper(node.children, res)
        return res
    }

    override fun visit(node: ASTInsertData, childrenValues: List<OPBase>): OPBase {
        val res = LOPModifyData(ModifyDataType.INSERT)
        modifyDataHelper(node.children, res)
        return res
    }

    override fun visit(node: ASTModifyWithWhere, childrenValues: List<OPBase>): OPBase {
        require(node.iri == null)
        val child: OPBase = if (node.using.isEmpty()) {
            parseGroup(node.children)
        } else {
            var tmp: OPBase? = null
            for (c in node.using) {
                val tmp2 = setGraphNameForAllTriples(parseGroup(node.children), c)
                if (tmp == null)
                    tmp = tmp2
                else
                    tmp = LOPUnion(tmp, tmp2)
            }
            tmp!!
        }
        val res = LOPModify(child)
        for (e in node.insert)
            res.insert.add(e)
        for (e in node.delete)
            res.delete.add(e)
        return res
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
}
