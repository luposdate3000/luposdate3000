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
package lupos.s09physicalOperators.multiinput

import lupos.dictionary.DictionaryExt
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s05tripleStore.tripleStoreManager
import lupos.s09physicalOperators.POPBase
import kotlin.jvm.JvmField

public class POPJoinWithStoreExists public constructor(query: IQuery, projectedVariables: List<String>, childA: IOPBase, @JvmField public val childB: LOPTriple, @JvmField public val optional: Boolean) : POPBase(query, projectedVariables, EOperatorIDExt.POPJoinWithStoreExistsID, "POPJoinWithStoreExists", arrayOf(childA), ESortPriorityExt.SAME_AS_CHILD) {
    override fun getPartitionCount(variable: String): Int = children[0].getPartitionCount(variable)
    override fun toSparql(): String {
        if (optional) {
            return "OPTIONAL{" + children[0].toSparql() + childB.toSparql() + "}"
        }
        return children[0].toSparql() + childB.toSparql()
    }

    override fun equals(other: Any?): Boolean = other is POPJoinWithStoreExists && optional == other.optional && children[0] == other.children[0]
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        SanityCheck.check { !optional }
        SanityCheck.check { !childB.graphVar }
        SanityCheck.check { projectedVariables.isEmpty() }
        val childAv = children[0].evaluate(parent)
        val iteratorsHelper = mutableListOf<ColumnIterator>()
        val params = Array(3) { childB.children[it] as IAOPBase }
        var res = IteratorBundle(0)
        val mappingHelper = mutableListOf<Int>()
        for (i in 0 until 3) {
            val p = params[i]
            if (p is AOPVariable && p.name != "_") {
                mappingHelper.add(i)
                iteratorsHelper.add(childAv.columns[p.name]!!)
                params[i] = AOPConstant(query, 0)
            }
        }
        val index = LOPTriple.getIndex(params.map { it }.toTypedArray(), listOf())
        var done = false
        val iterators = iteratorsHelper.toTypedArray()
        val mapping = IntArray(mappingHelper.size) { mappingHelper[it] }
        SanityCheck.check { mapping.isNotEmpty() }
        for (i in mapping.indices) {
            val tmp = iterators[i].next()
            if (tmp == DictionaryExt.nullValue) {
                done = true
                for (element in iterators) {
                    element.close()
                }
                SanityCheck.check { i == 0 }
                break
            } else {
                params[mapping[i]] = AOPConstant(query, tmp)
            }
        }
        if (!done) {
            val distributedStore = tripleStoreManager.getGraph(childB.graph)
            var iteratorB = distributedStore.getIterator(query, params, index).evaluate(parent)
            res = object : IteratorBundle(0) {
                override /*suspend*/ fun hasNext2(): Boolean {
                    val t = iteratorB.hasNext2()
                    loop@ while (!t && !done) {
                        for (i in mapping.indices) {
                            val tmp = iterators[i].next()
                            if (tmp == DictionaryExt.nullValue) {
                                for (element in iterators) {
                                    element.close()
                                }
                                done = true
                                SanityCheck.check { i == 0 }
                                break@loop
                            } else {
                                params[mapping[i]] = AOPConstant(query, tmp)
                            }
                        }
                        if (!done) {
                            iteratorB = distributedStore.getIterator(query, params, index).evaluate(parent)
                        }
                    }
                    return t
                }

                override /*suspend*/ fun hasNext2Close() {
                    for (element in iterators) {
                        element.close()
                    }
                }
            }
        }
        return res
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement {
        val res = super.toXMLElement(partial).addAttribute("optional", "" + optional)
        res["children"]!!.addContent(childB.toXMLElement(partial))
        return res
    }

    override fun cloneOP(): IOPBase = POPJoinWithStoreExists(query, projectedVariables, children[0].cloneOP(), childB.cloneOP() as LOPTriple, optional)
}
