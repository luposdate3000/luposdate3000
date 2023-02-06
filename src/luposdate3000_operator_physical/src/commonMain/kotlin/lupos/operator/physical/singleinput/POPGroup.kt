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
package lupos.operator.physical.singleinput

import lupos.operator.arithmetik.AOPBase
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.arithmetik.singleinput.AOPAggregationCOUNT
import lupos.operator.base.IPOPLimit
import lupos.operator.base.noinput.OPEmptyRow
import lupos.operator.physical.POPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.GroupByColumnMissing
import lupos.shared.GroupByDuplicateColumnException
import lupos.shared.IQuery
import lupos.shared.OperationCanNotBeLocalException
import lupos.shared.Partition
import lupos.shared.PartitionHelper
import lupos.shared.SanityCheck
import lupos.shared.SortHelper
import lupos.shared.VariableNotDefinedSyntaxException
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

// TODO refactor such that the optimizer may choose which strategy to use
public class POPGroup : POPBase {
    override fun getPossibleSortPriorities(): List<List<SortHelper>> {
        /*possibilities for_ next operator*/
        val res = mutableListOf<List<SortHelper>>()
        val provided = by.map { it.name }
        for (x in children[0].getPossibleSortPriorities()) {
            val tmp = mutableListOf<SortHelper>()
            for (v in x) {
                if (provided.contains(v.variableName)) {
                    tmp.add(v)
                } else {
                    break
                }
            }
            addToPrefixFreeList(tmp, res)
        }
        return res
    }

    override fun getPartitionCount(variable: String): Int {
        if (SanityCheck.enabled) { if (!(children[0].getPartitionCount(variable) == 1)) { throw Exception("SanityCheck failed") } }
        return 1
    }

    @JvmField
    public var by: List<AOPVariable>

    @JvmField
    public var bindings: MutableList<Pair<String, AOPBase>> = mutableListOf()
    override fun toSparql(): String {
        var res = children[0].toSparql()
        res += " GROUP BY "
        for (b in by) {
            res += b.toSparql() + " "
        }
        for ((k, v) in bindings) {
            res += "(" + v.toSparql() + " AS " + AOPVariable(query, k).toSparql() + ")"
        }
        return res
    }

    override fun cloneOP(): POPGroup {
        return if (bindings.size > 0) {
            var tmpBindings = POPBind(query, listOf(), AOPVariable(query, bindings[0].first), bindings[0].second, OPEmptyRow(query))
            for (bp in 1 until bindings.size) {
                tmpBindings = POPBind(query, listOf(), AOPVariable(query, bindings[bp].first), bindings[bp].second, tmpBindings)
            }
            POPGroup(query, projectedVariables, by, tmpBindings, children[0].cloneOP())
        } else {
            POPGroup(query, projectedVariables, by, null, children[0].cloneOP())
        }
    }

    public constructor(
        query: IQuery,
        projectedVariables: List<String>,
        by: List<AOPVariable>,
        bindings: POPBind?,
        child: IOPBase
    ) : super(query, projectedVariables, EOperatorIDExt.POPGroupID, "POPGroup", arrayOf(child), ESortPriorityExt.GROUP) {
        this.by = by
        var tmpBind: IOPBase? = bindings
        while (tmpBind != null && tmpBind is POPBind) {
            this.bindings.add(Pair(tmpBind.name.name, tmpBind.children[1] as AOPBase))
            tmpBind = tmpBind.children[0]
        }
        this.bindings = this.bindings.asReversed()
    }

    public constructor(query: IQuery, projectedVariables: List<String>, by: List<AOPVariable>, bindings: List<Pair<String, AOPBase>>, child: IOPBase) : super(query, projectedVariables, EOperatorIDExt.POPGroupID, "POPGroup", arrayOf(child), ESortPriorityExt.GROUP) {
        this.by = by
        this.bindings = bindings.toMutableList()
    }

    override fun equals(other: Any?): Boolean = other is POPGroup && by == other.by && children[0] == other.children[0] && bindings == other.bindings
    override fun getProvidedVariableNamesInternal(): List<String> = (MutableList(by.size) { by[it].name } + MutableList(bindings.size) { bindings[it].first }).distinct()
    override fun getRequiredVariableNames(): List<String> {
        val res = MutableList(by.size) { by[it].name }
        for (b in bindings) {
            res.addAll(b.second.getRequiredVariableNamesRecoursive())
        }
        return res.distinct()
    }

    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {
        children[0].syntaxVerifyAllVariableExists(additionalProvided, autocorrect)
        if (SanityCheck.enabled) { if (!(additionalProvided.isEmpty())) { throw Exception("SanityCheck failed") } }
        val localProvide = additionalProvided + children[0].getProvidedVariableNames()
        val localRequire = mutableListOf<String>()
        for (v in by) {
            localRequire.add(v.name)
        }
        for (b in bindings) {
            localRequire += b.second.getRequiredVariableNamesRecoursive()
        }
        if (!localProvide.containsAll(localRequire)) {
            if (autocorrect) {
                for (name in localRequire) {
                    var found = false
                    for (prov in localProvide) {
                        if (prov == name) {
                            found = true
                            break
                        }
                    }
                    if (!found) {
                        for (b in by) {
                            if (b.name == name) {
                                throw GroupByColumnMissing(name)
                            }
                        }
                        for (b in bindings.indices) {
                            bindings[b] = Pair(bindings[b].first, bindings[b].second.replaceVariableWithUndef(name, true) as AOPBase)
                        }
                    }
                }
            } else {
                val tmp = localRequire.toMutableSet()
                tmp.removeAll(localProvide)
                if (tmp.size == 1) {
                    throw VariableNotDefinedSyntaxException(classname, tmp.first())
                } else {
                    throw VariableNotDefinedSyntaxException(classname, tmp.toString())
                }
            }
        }
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: PartitionHelper): XMLElement {
        val res = super.toXMLElement(partial, partition)
        val byxml = XMLElement("by")
        res.addContent(byxml)
        for (b in by) {
            byxml.addContent(XMLElement("variable").addAttribute("name", b.name))
        }
        val xmlbindings = XMLElement("bindings")
        res.addContent(xmlbindings)
        for ((first, second) in bindings) {
            xmlbindings.addContent(XMLElement("binding").addAttribute("name", first).addContent(second.toXMLElement(partial, partition)))
        }
        return res
    }

    override fun usesDictionary(): Boolean {
        return true
    }

    public fun canUseSortedInput(): Boolean {
        val keyColumnNames = by.map { it.name }.toTypedArray()
        val valueColumnNames = mutableListOf<String>()
        for (name in children[0].getProvidedVariableNames()) {
            if (!keyColumnNames.contains(name)) {
                valueColumnNames.add(name)
            }
        }
        val tmpSortPriority = children[0].getMySortPriority().map { it.variableName }
        if ((!children[0].getProvidedVariableNames().containsAll(keyColumnNames.toMutableList())) || (tmpSortPriority.size < keyColumnNames.size)) {
            return false
        } else {
            for (element in keyColumnNames) {
                if (!tmpSortPriority.contains(element)) {
                    return false
                }
            }
        }
        return true
    }

    public fun isCountOnly(): Boolean {
        val keyColumnNames = by.map { it.name }
        val localVariables = children[0].getProvidedVariableNames()
        val valueColumnNames = mutableListOf<String>()
        for (name in localVariables) {
            if (!keyColumnNames.contains(name)) {
                valueColumnNames.add(name)
            }
        }
        return bindings.size == 1 && bindings.first().second is AOPAggregationCOUNT &&
// simplicity ->
            valueColumnNames.size == 0
// <- simplicity
    }

    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val keyColumnNames = by.map { it.name }.toTypedArray()
        if (keyColumnNames.size != keyColumnNames.distinct().size) {
            throw GroupByDuplicateColumnException()
        }
        if (by.isEmpty()) {
            return EvalGroupWithoutKeyColumn(
                children[0].evaluate(parent),
                bindings,
                projectedVariables,
            )
        } else if (canUseSortedInput()) {
            return EvalGroupSorted(
                children[0].evaluate(parent),
                bindings,
                projectedVariables,
                by.map { it.name }.toTypedArray(),
            )
        } else if (isCountOnly()) {
            if (by.size == 0) {
                return EvalGroupCount0(
                    children[0].evaluate(parent),
                    bindings.first().first,
                    query.getDictionary(),
                )
            } else if (by.size == 1) {
                return EvalGroupCount1(
                    children[0].evaluate(parent),
                    bindings.first().first,
                    by[0].name,
                    query.getDictionary(),
                )
            }
        }
        return EvalGroup(
            children[0].evaluate(parent),
            bindings,
            by.map { it.name }.toTypedArray(),
        )
    }

    override fun toLocalOperatorGraph(parent: Partition, onFoundLimit: (IPOPLimit) -> Unit, onFoundSort: () -> Unit): POPBase? = throw OperationCanNotBeLocalException()
}
