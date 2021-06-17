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
package lupos.operator.physical.multiinput

import lupos.operator.arithmetik.AOPBase
import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.iterator.ColumnIteratorQueueEmpty
import lupos.operator.base.multiinput.LOPJoin_Helper
import lupos.operator.logical.noinput.LOPTriple
import lupos.operator.physical.POPBase
import lupos.shared.EIndexPatternHelper
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.ESortTypeExt
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.dictionary.DictionaryExt
import lupos.shared.inline.ColumnIteratorQueueExt
import lupos.shared.operator.IAOPBase
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.ColumnIteratorEmpty
import lupos.shared.operator.iterator.ColumnIteratorQueue
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

public class POPJoinWithStore public constructor(query: IQuery, projectedVariables: List<String>, childA: IOPBase, @JvmField public val childB: LOPTriple, @JvmField public val optional: Boolean) : POPBase(query, projectedVariables, EOperatorIDExt.POPJoinWithStoreID, "POPJoinWithStore", arrayOf(childA), ESortPriorityExt.SAME_AS_CHILD) {
    override fun getPartitionCount(variable: String): Int = children[0].getPartitionCount(variable)
    override fun toSparql(): String {
        if (optional) {
            return "OPTIONAL{" + children[0].toSparql() + childB.toSparql() + "}"
        }
        return children[0].toSparql() + childB.toSparql()
    }

    override fun equals(other: Any?): Boolean = other is POPJoinWithStore && optional == other.optional && children[0] == other.children[0]
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        SanityCheck.check { !optional }
        SanityCheck.check { !childB.graphVar }
        val childAv = children[0].evaluate(parent)
        val childA = children[0]
        val columnsINAO = mutableListOf<ColumnIterator>()
        val columnsINAJ = mutableListOf<ColumnIterator>()
        val columnsOUTAO = mutableListOf<ColumnIteratorQueue>()
        val columnsOUTAJ = mutableListOf<ColumnIteratorQueue>()
        val columnsOUTB = mutableListOf<ColumnIteratorQueue>()
        val columnsOUT = mutableListOf<Pair<String, Int>>() // key_in_outMap | AO,AJ,B
        val variablINBO = mutableListOf<String>()
        val indicesINBJ = mutableListOf<Int>()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val tmp2 = mutableListOf<String>()
        tmp2.addAll(childA.getProvidedVariableNames())
        val columnsTmp = LOPJoin_Helper.getColumns(childA.getProvidedVariableNames(), childB.getProvidedVariableNames())
        val localSortPriority = mutableListOf<String>()
        localSortPriority.addAll(childB.mySortPriority.map { it.variableName })
        val paramsHelper = Array<IOPBase>(3) {
            var tmp = childB.children[it] as AOPBase
            if (tmp is AOPVariable && columnsTmp[0].contains(tmp.name)) {
                localSortPriority.remove(tmp.name)
                tmp = AOPConstant(query, 0)
            }
            tmp
        }
        val index = LOPTriple.getIndex(paramsHelper, localSortPriority)
        for (i in 0 until 3) {
            val j = EIndexPatternHelper.tripleIndicees[index][i]
            val t = childB.children[j]
            if (t is AOPVariable) {
                val name = t.name
                if (columnsTmp[0].contains(name)) {
                    SanityCheck.check { name != "_" }
                    for (k in 0 until 3) {
                        val cc = childB.children[k]
                        if (cc is AOPVariable && cc.name == name) {
                            indicesINBJ.add(k)
                            break
                        }
                    }
                    tmp2.remove(name)
                    if (projectedVariables.contains(name)) {
                        columnsINAJ.add(0, childAv.columns[name]!!)
                        columnsOUT.add(Pair(name, 1))
                    } else {
                        columnsINAJ.add(childAv.columns[name]!!)
                    }
                } else {
                    SanityCheck.check { columnsTmp[2].contains(name) || name == "_" }
                    if (name != "_") {
                        variablINBO.add(name)
                        columnsOUT.add(Pair(name, 2))
                    }
                }
            }
        }
        for (name in tmp2) {
            SanityCheck.check { columnsTmp[1].contains(name) || name == "_" }
            if (name != "_") {
                columnsOUT.add(Pair(name, 0))
                columnsINAO.add(0, childAv.columns[name]!!)
            }
        }
        SanityCheck.check { variablINBO.size > 0 }
        val distributedStore = query.getInstance().tripleStoreManager!!.getGraph(childB.graph)
        val valuesAO = IntArray(columnsINAO.size) { DictionaryExt.nullValue }
        val valuesAJ = IntArray(columnsINAJ.size) { DictionaryExt.nullValue }
        var count = 0
        val params = Array<IAOPBase>(3) {
            if (childB.children[it] is AOPConstant) {
                count++
            }
            childB.children[it] as AOPBase
        }
        for (i in 0 until indicesINBJ.size) {
            SanityCheck.check { params[indicesINBJ[i]] is AOPVariable }
            params[indicesINBJ[i]] = AOPConstant(query, DictionaryExt.undefValue2)
            count++
        }
        SanityCheck {
            SanityCheck.check { count > 0 }
            SanityCheck.check { count < 3 }
            for (i in 0 until childB.mySortPriority.size) {
                SanityCheck.check { childB.mySortPriority[i].sortType == ESortTypeExt.FAST }
            }
            SanityCheck.check { indicesINBJ.size > 0 }
            SanityCheck.check { valuesAJ.size == indicesINBJ.size }
        }
        val columnsInB = Array<ColumnIterator>(variablINBO.size) { ColumnIteratorEmpty() }
        for (i in 0 until columnsINAO.size) {
            valuesAO[i] = columnsINAO[i].next()
        }
        for (i in 0 until columnsINAJ.size) {
            valuesAJ[i] = columnsINAJ[i].next()
        }
        if (valuesAJ[0] != DictionaryExt.nullValue) {
// there is at least one value in A
            for (i in 0 until indicesINBJ.size) {
                params[indicesINBJ[i]] = AOPConstant(query, valuesAJ[i])
            }
            var columnsInBRoot = distributedStore.getIterator(query, params, index).evaluate(parent)
            for (i in 0 until variablINBO.size) {
                columnsInB[i] = columnsInBRoot.columns[variablINBO[i]]!!
            }
            for ((first, second) in columnsOUT) {
                val column = object : ColumnIteratorQueue() {
                    override /*suspend*/ fun close() {
                        __close()
                    }

                    @Suppress("NOTHING_TO_INLINE")
                    /*suspend*/ inline fun __close() {
                        if (label != 0) {
                            ColumnIteratorQueueExt._close(this)
                            for (element in columnsInB) {
                                element.close()
                            }
                            for (closeIndex in 0 until columnsINAO.size) {
                                columnsINAO[closeIndex].close()
                            }
                            for (closeIndex in 0 until columnsINAJ.size) {
                                columnsINAJ[closeIndex].close()
                            }
                        }
                    }

                    override /*suspend*/ fun next(): Int {
                        return ColumnIteratorQueueExt.nextHelper(
                            this,
                            {
                                loopA@ while (true) {
                                    var done = true
                                    loopB@ for (i in 0 until variablINBO.size) {
                                        val value = columnsInB[i].next()
                                        if (value == DictionaryExt.nullValue) {
                                            for (element in columnsInB) {
                                                element.close()
                                            }
                                            SanityCheck.check { i == 0 }
                                            done = false
                                            break@loopB
                                        } else {
                                            columnsOUTB[i].queue.add(value)
                                        }
                                    }
                                    if (done) {
                                        for (i in 0 until columnsOUTAO.size) {
                                            columnsOUTAO[i].queue.add(valuesAO[i])
                                        }
                                        for (i in 0 until columnsOUTAJ.size) {
                                            columnsOUTAJ[i].queue.add(valuesAJ[i])
                                        }
                                        break@loopA
                                    } else {
                                        for (i in 0 until columnsINAO.size) {
                                            valuesAO[i] = columnsINAO[i].next()
                                        }
                                        for (i in 0 until columnsINAJ.size) {
                                            valuesAJ[i] = columnsINAJ[i].next()
                                        }
                                        if (valuesAJ[0] != DictionaryExt.nullValue) {
                                            for (i in 0 until indicesINBJ.size) {
                                                params[indicesINBJ[i]] = AOPConstant(query, valuesAJ[i])
                                            }
                                            columnsInBRoot = distributedStore.getIterator(query, params, index).evaluate(parent)
                                            for (i in 0 until variablINBO.size) {
                                                columnsInB[i] = columnsInBRoot.columns[variablINBO[i]]!!
                                            }
                                        } else {
                                            for (element in columnsInB) {
                                                element.close()
                                            }
                                            for (closeIndex in 0 until columnsINAO.size) {
                                                columnsINAO[closeIndex].close()
                                            }
                                            for (closeIndex in 0 until columnsINAJ.size) {
                                                columnsINAJ[closeIndex].close()
                                            }
                                            break@loopA
                                        }
                                    }
                                }
                            },
                            { __close() }
                        )
                    }
                }
                outMap[first] = column
                when (second) {
                    0 -> {
                        columnsOUTAO.add(column)
                    }
                    1 -> {
                        columnsOUTAJ.add(column)
                    }
                    2 -> {
                        columnsOUTB.add(column)
                    }
                }
            }
        } else {
            for ((first) in columnsOUT) {
                outMap[first] = ColumnIteratorQueueEmpty()
            }
        }
        return IteratorBundle(outMap)
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement {
        val res = super.toXMLElement(partial).addAttribute("optional", "" + optional)
        res["children"]!!.addContent(childB.toXMLElement(partial))
        return res
    }

    override fun cloneOP(): IOPBase = POPJoinWithStore(query, projectedVariables, children[0].cloneOP(), childB.cloneOP() as LOPTriple, optional)
}
