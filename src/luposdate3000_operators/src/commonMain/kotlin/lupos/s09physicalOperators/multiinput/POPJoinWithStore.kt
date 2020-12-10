package lupos.s09physicalOperators.multiinput
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.ESortType
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorEmpty
import lupos.s04logicalOperators.iterator.ColumnIteratorQueue
import lupos.s04logicalOperators.iterator.ColumnIteratorQueueEmpty
import lupos.s04logicalOperators.iterator.ColumnIteratorQueueExt
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.distributedTripleStore
import kotlin.jvm.JvmField
class POPJoinWithStore(query: IQuery, projectedVariables: List<String>, childA: IOPBase, val childB: LOPTriple, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinWithStoreID, "POPJoinWithStore", arrayOf(childA), ESortPriority.SAME_AS_CHILD) {
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
        val columnsTmp = LOPJoin.getColumns(childA.getProvidedVariableNames(), childB.getProvidedVariableNames())
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
            val j = index.tripleIndicees[i]
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
        val distributedStore = distributedTripleStore.getNamedGraph(query, childB.graph)
        val valuesAO = IntArray(columnsINAO.size) { ResultSetDictionaryExt.nullValue }
        val valuesAJ = IntArray(columnsINAJ.size) { ResultSetDictionaryExt.nullValue }
        var count = 0
        val params = Array<IAOPBase>(3) {
            if (childB.children[it] is AOPConstant) {
                count++
            }
            childB.children[it] as AOPBase
        }
        for (i in 0 until indicesINBJ.size) {
            SanityCheck.check { params[indicesINBJ[i]] is AOPVariable }
            params[indicesINBJ[i]] = AOPConstant(query, ResultSetDictionaryExt.undefValue2)
            count++
        }
        SanityCheck {
            SanityCheck.check { count > 0 }
            SanityCheck.check { count < 3 }
            for (i in 0 until childB.mySortPriority.size) {
                SanityCheck.check { childB.mySortPriority[i].sortType == ESortType.FAST }
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
        val theuuid = uuid
        if (valuesAJ[0] != ResultSetDictionaryExt.nullValue) {
// there is at least one value in A
            for (i in 0 until indicesINBJ.size) {
                params[indicesINBJ[i]] = AOPConstant(query, valuesAJ[i])
            }
            SanityCheck.println { "POPJoinWithStoreXXXopening store for join with store A $theuuid" }
            var columnsInBRoot = distributedStore.getIterator(params, index, Partition()).evaluate(parent)
            for (i in 0 until variablINBO.size) {
                columnsInB[i] = columnsInBRoot.columns[variablINBO[i]]!!
            }
            SanityCheck.println { "POPJoinWithStoreXXXopened ${columnsInBRoot.columns.size} columns for store, and saved ${variablINBO.size} of these" }
            for ((first, second) in columnsOUT) {
                val column = object : ColumnIteratorQueue() {
                    override /*suspend*/ fun close() {
                        __close()
                    }
                    /*suspend*/ inline fun __close() {
                        if (label != 0) {
                            ColumnIteratorQueueExt._close(this)
                            SanityCheck.println { "POPJoinWithStoreXXXclosing store for join with store A $theuuid" }
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
                                        if (value == ResultSetDictionaryExt.nullValue) {
                                            SanityCheck.println { "POPJoinWithStoreXXXclosing store for join with store B $theuuid" }
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
                                        if (valuesAJ[0] != ResultSetDictionaryExt.nullValue) {
                                            for (i in 0 until indicesINBJ.size) {
                                                params[indicesINBJ[i]] = AOPConstant(query, valuesAJ[i])
                                            }
                                            SanityCheck.println { "POPJoinWithStoreXXXopening store for join with store B $theuuid" }
                                            columnsInBRoot = distributedStore.getIterator(params, index, Partition()).evaluate(parent)
                                            for (i in 0 until variablINBO.size) {
                                                columnsInB[i] = columnsInBRoot.columns[variablINBO[i]]!!
                                            }
                                        } else {
                                            SanityCheck.println { "POPJoinWithStoreXXXclosing store for join with store C $theuuid" }
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
    override /*suspend*/ fun toXMLElement(): XMLElement {
        val res = super.toXMLElement().addAttribute("optional", "" + optional)
        res["children"]!!.addContent(childB.toXMLElement())
        return res
    }
    override fun cloneOP(): IOPBase = POPJoinWithStore(query, projectedVariables, children[0].cloneOP(), childB.cloneOP() as LOPTriple, optional)
}
