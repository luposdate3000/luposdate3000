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
package lupos.operator.logical.singleinput

import lupos.operator.arithmetik.AOPBase
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.noinput.OPEmptyRow
import lupos.operator.logical.LOPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.GroupByColumnMissing
import lupos.shared.IQuery
import lupos.shared.PartitionHelper
import lupos.shared.SanityCheck
import lupos.shared.SortHelper
import lupos.shared.VariableNotDefinedSyntaxException
import lupos.shared.XMLElement
import lupos.shared.operator.HistogramResult
import lupos.shared.operator.IOPBase
import kotlin.jvm.JvmField

public class LOPGroup public constructor(query: IQuery, @JvmField public var by: List<AOPVariable>) : LOPBase(query, EOperatorIDExt.LOPGroupID, "LOPGroup", arrayOf(OPEmptyRow(query)), ESortPriorityExt.GROUP) {
    override fun childrenToVerifyCount(): Int = 1

    @JvmField
    public var bindings: MutableList<Pair<String, AOPBase>> = mutableListOf()
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

    public constructor(query: IQuery, by: List<AOPVariable>, bindings: List<Pair<String, AOPBase>>, child: IOPBase) : this(query, by) {
        this.bindings = bindings.toMutableList()
        children[0] = child
    }

    public constructor(query: IQuery, by: List<AOPVariable>, bindings: IOPBase?, child: IOPBase) : this(query, by) {
        var b = bindings
        while (b != null) {
            if (b is LOPBind) {
                this.bindings.add(Pair(b.name.name, b.children[1] as AOPBase))
                b = b.children[0]
            } else {
if(SanityCheck.enabled){if(!( b is OPEmptyRow )){throw Exception("SanityCheck failed")}}
                break
            }
        }
        this.bindings = this.bindings.asReversed()
        children[0] = child
    }

    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {
        children[0].syntaxVerifyAllVariableExists(additionalProvided, autocorrect)
if(SanityCheck.enabled){if(!( additionalProvided.isEmpty() )){throw Exception("SanityCheck failed")}}
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

    override fun getProvidedVariableNames(): List<String> {
        return (bindings.map { it.first } + Array(by.size) { by[it].name }).distinct()
    }

    override fun getRequiredVariableNames(): List<String> {
        val res = mutableListOf<String>()
        for (b in bindings) {
            res.addAll(b.second.getRequiredVariableNamesRecoursive())
        }
        for (b in by) {
            res.addAll(b.getRequiredVariableNames())
        }
        return res.distinct()
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: PartitionHelper): XMLElement {
        val res = super.toXMLElement(partial, partition)
        val byxml = XMLElement("LocalBy")
        res.addContent(byxml)
        for (b in by) {
            byxml.addContent(XMLElement("LocalVariable").addAttribute("name", b.name))
        }
        val bindingsxml = XMLElement("LocalBindings")
        res.addContent(bindingsxml)
        for ((first, second) in bindings) {
            bindingsxml.addContent(XMLElement("Binding").addAttribute("name", first).addContent(second.toXMLElement(partial, partition)))
        }
        return res
    }

    override fun equals(other: Any?): Boolean = other is LOPGroup && children[0] == other.children[0] && by == other.by && bindings == other.bindings
    override fun cloneOP(): IOPBase = LOPGroup(query, by, bindings.map { it }, children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        val res = HistogramResult()
        for (v in getProvidedVariableNames()) {
            res.values[v] = 1
        }
        res.count = 1
        return res
    }
}
