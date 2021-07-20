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

import lupos.operator.arithmetik.AOPAggregationBase
import lupos.operator.arithmetik.AOPBase
import lupos.operator.arithmetik.generated.*
import lupos.operator.arithmetik.multiinput.*
import lupos.operator.arithmetik.noinput.*
import lupos.operator.arithmetik.singleinput.*
import lupos.operator.base.OPBase
import lupos.operator.base.OPBaseCompound
import lupos.operator.base.Query
import lupos.operator.base.noinput.OPEmptyRow
import lupos.operator.base.singleinput.LOPNOOP
import lupos.operator.logical.LOPBase
import lupos.operator.logical.multiinput.LOPJoin
import lupos.operator.logical.multiinput.LOPMinus
import lupos.operator.logical.multiinput.LOPUnion
import lupos.operator.logical.noinput.LOPGraphOperation
import lupos.operator.logical.noinput.LOPModifyData
import lupos.operator.logical.noinput.LOPTriple
import lupos.operator.logical.noinput.LOPValues
import lupos.operator.logical.singleinput.*
import lupos.operator.logical.singleinput.modifiers.*
import lupos.operator.physical.noinput.POPValues2
import lupos.parser.sparql1_1.*
import lupos.shared.*
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.File
import lupos.shared.operator.IOPBase
import kotlin.jvm.JvmField

public class OperatorGraphVisitor(@JvmField public val query: Query) : Visitor<IOPBase> {
    @JvmField
    public val queryExecutionStartTime: ByteArrayWrapper = ByteArrayWrapper()

    init {
        DictionaryHelper.dateTimeToByteArray(queryExecutionStartTime)
    }

    private fun createUnion(a: IOPBase, b: IOPBase): IOPBase {
        val pa = a.getProvidedVariableNames().toMutableSet()
        val pb = b.getProvidedVariableNames().toMutableSet()
        val pc = pa.intersect(pb)
        pa.removeAll(pc)
        pb.removeAll(pc)
        var a1 = a
        var b1 = b
        val buffer = ByteArrayWrapper()
        DictionaryHelper.undefToByteArray(buffer)
        for (x in pa) {
            b1 = LOPBind(query, AOPVariable(query, x), AOPConstant(query, buffer), b1)
        }
        for (x in pb) {
            a1 = LOPBind(query, AOPVariable(query, x), AOPConstant(query, buffer), a1)
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
        child = child.replaceVariableWithAnother("s", query.getUniqueVariableName())
        child = child.replaceVariableWithAnother("p", query.getUniqueVariableName())
        child = child.replaceVariableWithAnother("o", query.getUniqueVariableName())
        var res: IOPBase? = null
        for (v in child.getProvidedVariableNames()) {
            val tmp5 = child.cloneOP()
            val tmp1 = LOPProjection(query, mutableListOf(AOPVariable(query, "s")), tmp5.replaceVariableWithAnother(v, "s"))
            val tmp2 = LOPProjection(query, mutableListOf(AOPVariable(query, "p")), tmp5.replaceVariableWithAnother(v, "p"))
            val tmp3 = LOPProjection(query, mutableListOf(AOPVariable(query, "o")), tmp5.replaceVariableWithAnother(v, "o"))
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
                mychild = mychild.replaceVariableWithAnother(selected, tmp)
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
                    tmp.replaceVariableWithAnother(tmp2.first as String, names[name])
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
                    val data = POPValues2(query, MemoryTable.parseFromAny(File(query.getWorkingDirectory() + d.source_iri).readAsString(), d.source_iri, query)!!)
                    when (d) {
                        is ASTDefaultGraph -> {
                            datasets[TripleStoreManager.DEFAULT_GRAPH_NAME] = data
                        }
                        is ASTNamedGraph -> {
                            datasets["<" + d.source_iri + ">"] = data
                        }
                    }
                } catch (e: Throwable) {
                    e.printStackTrace()
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
                    val buffer = ByteArrayWrapper()
                    DictionaryHelper.sparqlToByteArray(buffer, k)
                    val t = LOPBind(node.query, AOPVariable(query, node.graph), AOPConstant(query, buffer), v)
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
                    TODO(tmp2.getClassname())
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
        val buffer = ByteArrayWrapper()
        DictionaryHelper.undefToByteArray(buffer)
        return AOPConstant(query, buffer)
    }

    override fun visit(node: ASTSimpleLiteral, childrenValues: List<IOPBase>): IOPBase {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.stringToByteArray(buffer, node.content)
        return AOPConstant(query, buffer)
    }

    override fun visit(node: ASTTypedLiteral, childrenValues: List<IOPBase>): IOPBase {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.typedToByteArray(buffer, node.content, node.type_iri)
        return AOPConstant(query, buffer)
    }

    override fun visit(node: ASTLanguageTaggedLiteral, childrenValues: List<IOPBase>): IOPBase {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.langToByteArray(buffer, node.content, node.language)
        return AOPConstant(query, buffer)
    }

    override fun visit(node: ASTBooleanLiteral, childrenValues: List<IOPBase>): IOPBase {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.booleanToByteArray(buffer, node.value)
        return AOPConstant(query, buffer)
    }

    override fun visit(node: ASTInteger, childrenValues: List<IOPBase>): IOPBase {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.integerToByteArray(buffer, node.value.toString())
        return AOPConstant(query, buffer)
    }

    override fun visit(node: ASTDouble, childrenValues: List<IOPBase>): IOPBase {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.doubleToByteArray(buffer, node.image)
        return AOPConstant(query, buffer)
    }

    override fun visit(node: ASTDecimal, childrenValues: List<IOPBase>): IOPBase {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.decimalToByteArray(buffer, node.image)
        return AOPConstant(query, buffer)
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
                TODO("ASTFunctionCall ${node.iri} ${node.distinct}")
            }
        }
    }

    override fun visit(node: ASTTriple, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:784"/*SOURCE_FILE_END*/ }, { childrenValues.size == 3 })
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
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:802"/*SOURCE_FILE_END*/ }, { childrenValues.size > 1 })
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
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:815"/*SOURCE_FILE_END*/ }, { childrenValues.size > 1 })
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
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:828"/*SOURCE_FILE_END*/ }, { childrenValues.size == 2 })
        return AOPEQ(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTNEQ, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:833"/*SOURCE_FILE_END*/ }, { childrenValues.size == 2 })
        return AOPNEQ(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTLEQ, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:838"/*SOURCE_FILE_END*/ }, { childrenValues.size == 2 })
        return AOPLEQ(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTGEQ, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:843"/*SOURCE_FILE_END*/ }, { childrenValues.size == 2 })
        return AOPGEQ(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTLT, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:848"/*SOURCE_FILE_END*/ }, { childrenValues.size == 2 })
        return AOPLT(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTGT, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:853"/*SOURCE_FILE_END*/ }, { childrenValues.size == 2 })
        return AOPGT(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTIn, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:858"/*SOURCE_FILE_END*/ }, { childrenValues.size == 2 })
        return AOPIn(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTNotIn, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:863"/*SOURCE_FILE_END*/ }, { childrenValues.size == 2 })
        return AOPNotIn(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
    }

    override fun visit(node: ASTAddition, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:868"/*SOURCE_FILE_END*/ }, { childrenValues.size > 1 })
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
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:881"/*SOURCE_FILE_END*/ }, { childrenValues.size > 1 })
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
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:895"/*SOURCE_FILE_END*/ }, { childrenValues.size > 1 })
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
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:908"/*SOURCE_FILE_END*/ }, { childrenValues.size > 1 })
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
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:922"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
        return AOPNot(query, childrenValues[0] as AOPBase)
    }

    override fun visit(node: ASTBase, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:927"/*SOURCE_FILE_END*/ }, { childrenValues.isEmpty() })
        return LOPPrefix(query, "", node.iri)
    }

    override fun visit(node: ASTPrefix, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:932"/*SOURCE_FILE_END*/ }, { childrenValues.isEmpty() })
        return LOPPrefix(query, node.name, node.iri)
    }

    override fun visit(node: ASTAs, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:937"/*SOURCE_FILE_END*/ }, { childrenValues.isEmpty() })
        val a = node.variable.visit(this) as AOPVariable
        val b = node.expression.visit(this) as AOPBase
        if (b.getRequiredVariableNamesRecoursive().contains(a.name)) {
            throw RecoursiveVariableDefinitionSyntaxException(a.name)
        }
        return LOPBind(query, a, b)
    }

    override fun visit(node: ASTBlankNode, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:947"/*SOURCE_FILE_END*/ }, { childrenValues.isEmpty() })
        return AOPVariable(query, query.getUniqueVariableName(node.name))
// blank nodes are used for dont care within the queries. the only place, where the bnode is required as a value is within the insert/delete-clauses. there it needs to be replaced
    }

    override fun visit(node: ASTBuiltInCall, childrenValues: List<IOPBase>): IOPBase {
        when (node.function) {
            BuiltInFunctionsExt.STR -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:955"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallSTR(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.LANG -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:959"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallLANG(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.LANGMATCHES -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:963"/*SOURCE_FILE_END*/ }, { childrenValues.size == 2 })
                return AOPBuildInCallLANGMATCHES(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctionsExt.DATATYPE -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:967"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallDATATYPE(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.BOUND -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:971"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallBOUND(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.IRI -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:975"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallIRI(query, childrenValues[0] as AOPBase, "")
            }
            BuiltInFunctionsExt.URI -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:979"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallURI(query, childrenValues[0] as AOPBase, "")
            }
            BuiltInFunctionsExt.BNODE -> {
                if (childrenValues.size == 1) {
                    return AOPBuildInCallBNODE1(query, childrenValues[0] as AOPBase)
                }
                return AOPBuildInCallBNODE0(query)
            }
            BuiltInFunctionsExt.ABS -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:989"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallABS(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.CEIL -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:993"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallCEIL(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.FLOOR -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:997"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallFLOOR(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.ROUND -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1001"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallROUND(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.CONCAT -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1005"/*SOURCE_FILE_END*/ }, { childrenValues.isNotEmpty() })
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
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1016"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallSTRLEN(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.UCASE -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1020"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallUCASE(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.LCASE -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1024"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallLCASE(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.CONTAINS -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1028"/*SOURCE_FILE_END*/ }, { childrenValues.size == 2 })
                return AOPBuildInCallCONTAINS(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctionsExt.STRSTARTS -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1032"/*SOURCE_FILE_END*/ }, { childrenValues.size == 2 })
                return AOPBuildInCallSTRSTARTS(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctionsExt.STRENDS -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1036"/*SOURCE_FILE_END*/ }, { childrenValues.size == 2 })
                return AOPBuildInCallSTRENDS(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctionsExt.YEAR -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1040"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallYEAR(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.MONTH -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1044"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallMONTH(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.DAY -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1048"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallDAY(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.HOURS -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1052"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallHOURS(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.MINUTES -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1056"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallMINUTES(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.SECONDS -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1060"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallSECONDS(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.TIMEZONE -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1064"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallTIMEZONE(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.TZ -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1068"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallTZ(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.NOW -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1072"/*SOURCE_FILE_END*/ }, { childrenValues.isEmpty() })
                return AOPConstant(query, queryExecutionStartTime)
            }
            BuiltInFunctionsExt.UUID -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1076"/*SOURCE_FILE_END*/ }, { childrenValues.isEmpty() })
                return AOPBuildInCallUUID(query)
            }
            BuiltInFunctionsExt.STRUUID -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1080"/*SOURCE_FILE_END*/ }, { childrenValues.isEmpty() })
                return AOPBuildInCallSTRUUID(query)
            }
            BuiltInFunctionsExt.MD5 -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1084"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallMD5(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.SHA1 -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1088"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallSHA1(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.SHA256 -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1092"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallSHA256(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.IF -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1096"/*SOURCE_FILE_END*/ }, { childrenValues.size == 3 })
                return AOPBuildInCallIF(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase, childrenValues[2] as AOPBase)
            }
            BuiltInFunctionsExt.STRLANG -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1100"/*SOURCE_FILE_END*/ }, { childrenValues.size == 2 })
                return AOPBuildInCallSTRLANG(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctionsExt.STRAFTER -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1104"/*SOURCE_FILE_END*/ }, { childrenValues.size == 2 })
                return AOPBuildInCallSTRAFTER(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctionsExt.STRBEFORE -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1108"/*SOURCE_FILE_END*/ }, { childrenValues.size == 2 })
                return AOPBuildInCallSTRBEFORE(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctionsExt.STRDT -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1112"/*SOURCE_FILE_END*/ }, { childrenValues.size == 2 })
                return AOPBuildInCallSTRDT(query, childrenValues[0] as AOPBase, childrenValues[1] as AOPBase)
            }
            BuiltInFunctionsExt.isLITERAL -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1116"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallIsLITERAL(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.isIRI -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1120"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
                return AOPBuildInCallIsIri(query, childrenValues[0] as AOPBase)
            }
            BuiltInFunctionsExt.isNUMERIC -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1124"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
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
                TODO("BuiltInFunctionsExt." + BuiltInFunctionsExt.names[node.function])
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
                TODO("AggregationExt.GROUP_CONCAT")
            }
            else -> {
                throw UnreachableException()
            }
        }
        /*Coverage Unreachable*/
    }

    override fun visit(node: ASTUnion, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1173"/*SOURCE_FILE_END*/ }, { childrenValues.size >= 2 })
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
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1188"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
        val child = childrenValues.first() as AOPBase
        if (containsAggregate(node.children.first())) {
            throw AggregateNotAllowedSyntaxException()
        }
        return LOPFilter(query, child)
    }

    override fun visit(node: ASTOrderCondition, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1197"/*SOURCE_FILE_END*/ }, { childrenValues.size == 1 })
        val tmp = childrenValues.first() as AOPBase
        if (tmp is AOPVariable) {
            return LOPSort(query, node.asc, tmp)
        }
        val v = AOPVariable(query, "#f${tmp.uuid}")
        return LOPSort(query, node.asc, v, LOPBind(query, v, tmp))
    }

    override fun visit(node: ASTVar, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1207"/*SOURCE_FILE_END*/ }, { childrenValues.isEmpty() })
        return AOPVariable(query, node.name)
    }

    override fun visit(node: ASTIri, childrenValues: List<IOPBase>): IOPBase {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.iriToByteArray(buffer, node.iri)
        return AOPConstant(query, buffer)
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
                TODO(node.getClassname())
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
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1338"/*SOURCE_FILE_END*/ }, { childrenValues.isEmpty() })
        val g1 = graphRefToEnum(node.fromGraph)
        val g2 = graphRefToEnum(node.toGraph)
        return LOPGraphOperation(query, EGraphOperationTypeExt.ADD, node.silent, g1.first, g1.second, g2.first, g2.second)
    }

    override fun visit(node: ASTMove, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1345"/*SOURCE_FILE_END*/ }, { childrenValues.isEmpty() })
        val g1 = graphRefToEnum(node.fromGraph)
        val g2 = graphRefToEnum(node.toGraph)
        return LOPGraphOperation(query, EGraphOperationTypeExt.MOVE, node.silent, g1.first, g1.second, g2.first, g2.second)
    }

    override fun visit(node: ASTCopy, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1352"/*SOURCE_FILE_END*/ }, { childrenValues.isEmpty() })
        val g1 = graphRefToEnum(node.fromGraph)
        val g2 = graphRefToEnum(node.toGraph)
        return LOPGraphOperation(query, EGraphOperationTypeExt.COPY, node.silent, g1.first, g1.second, g2.first, g2.second)
    }

    override fun visit(node: ASTClear, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1359"/*SOURCE_FILE_END*/ }, { childrenValues.isEmpty() })
        val g1 = graphRefToEnum(node.graphref)
        return LOPGraphOperation(query, EGraphOperationTypeExt.CLEAR, node.silent, g1.first, g1.second)
    }

    override fun visit(node: ASTDrop, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1365"/*SOURCE_FILE_END*/ }, { childrenValues.isEmpty() })
        val g1 = graphRefToEnum(node.graphref)
        return LOPGraphOperation(query, EGraphOperationTypeExt.DROP, node.silent, g1.first, g1.second)
    }

    override fun visit(node: ASTCreate, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1371"/*SOURCE_FILE_END*/ }, { childrenValues.isEmpty() })
        val g1 = graphRefToEnum(node.graphref)
        return LOPGraphOperation(query, EGraphOperationTypeExt.CREATE, node.silent, g1.first, g1.second)
    }

    private fun simpleAstToLiteralValue(node: ASTNode): AOPBase {
        val tmp = node.visit(this) as AOPBase
        if (tmp is AOPVariable) {
            val buffer = ByteArrayWrapper()
            DictionaryHelper.bnodeToByteArray(buffer, tmp.name)
            return AOPConstant(query, buffer)
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
                val buffer = ByteArrayWrapper()
                DictionaryHelper.bnodeToByteArray(buffer, node.name)
                return AOPConstant(node.query, buffer)
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
        TODO("ASTModify")
    }

    override fun visit(node: ASTDefaultGraph, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTDefaultGraph")
    }

    override fun visit(node: ASTNamedGraph, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTNamedGraph")
    }

    override fun visit(node: ASTGraphRef, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTGraphRef")
    }

    override fun visit(node: ASTIriGraphRef, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTIriGraphRef")
    }

    override fun visit(node: ASTNamedIriGraphRef, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTNamedIriGraphRef")
    }

    override fun visit(node: ASTDefaultGraphRef, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTDefaultGraphRef")
    }

    override fun visit(node: ASTNamedGraphRef, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTNamedGraphRef")
    }

    override fun visit(node: ASTAllGraphRef, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTAllGraphRef")
    }

    override fun visit(node: ASTGrapOperation, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTGrapOperation")
    }

    override fun visit(node: ASTUpdateGrapOperation, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTUpdateGrapOperation")
    }

    override fun visit(node: ASTPathAlternatives, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTPathAlternatives")
    }

    override fun visit(node: ASTPathSequence, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTPathSequence")
    }

    override fun visit(node: ASTPathInverse, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTPathInverse")
    }

    override fun visit(node: ASTPathArbitraryOccurrences, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTPathArbitraryOccurrences")
    }

    override fun visit(node: ASTPathOptionalOccurrence, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTPathOptionalOccurrence")
    }

    override fun visit(node: ASTPathArbitraryOccurrencesNotZero, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTPathArbitraryOccurrencesNotZero")
    }

    override fun visit(node: ASTPathNegatedPropertySet, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTPathNegatedPropertySet")
    }

    override fun visit(node: ASTGroupConcat, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTGroupConcat")
    }

    override fun visit(node: ASTDatasetClause, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTDatasetClause")
    }

    override fun visit(node: ASTQueryBaseClass, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTQueryBaseClass")
    }

    override fun visit(node: ASTRDFTerm, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTRDFTerm")
    }

    override fun visit(node: ASTPlus, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTPlus")
    }

    override fun visit(node: ASTMinus, childrenValues: List<IOPBase>): IOPBase {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt:1604"/*SOURCE_FILE_END*/ }, { childrenValues.size == 2 })
        return LOPMinus(query, childrenValues[0], childrenValues[1], listOf())
    }

    override fun visit(node: ASTNumericLiteral, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTNumericLiteral")
    }

    override fun visit(node: ASTLiteral, childrenValues: List<IOPBase>): IOPBase {
        TODO("ASTLiteral")
    }
}
