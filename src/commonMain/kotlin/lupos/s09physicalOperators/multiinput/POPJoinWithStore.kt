package lupos.s09physicalOperators.multiinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s01io.*
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.noinput.*
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore

class POPJoinWithStore(query: Query, projectedVariables: List<String>, childA: OPBase, val childB: LOPTriple, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinWithStoreID, "POPJoinWithStore", arrayOf(childA), ESortPriority.SAME_AS_CHILD) {
    override fun toSparql(): String {
        if (optional) {
            return "OPTIONAL{" + children[0].toSparql() + childB.toSparql() + "}"
        }
        return children[0].toSparql() + childB.toSparql()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is POPJoinWithStore) {
            return false
        }
        if (optional != other.optional) {
            return false
        }
        for (i in children.indices) {
            if (!children[i].equals(other.children[i])) {
                return false
            }
        }
        return true
    }

    override suspend fun evaluate(): ColumnIteratorRow {
        require(!optional, { "POPJoinWithStore optional" })
        require(!childB.graphVar)
        val childAv = children[0].evaluate()
        val childA = children[0]
        val columnsINAO = mutableListOf<ColumnIterator>()
        val columnsINAJ = mutableListOf<ColumnIterator>()
        val columnsOUTAO = mutableListOf<ColumnIteratorQueue>()
        val columnsOUTAJ = mutableListOf<ColumnIteratorQueue>()
        val columnsOUTB = mutableListOf<ColumnIteratorQueue>()
        val columnsOUT = mutableListOf<ColumnIteratorQueue>()
        val variablINBO = mutableListOf<String>()
        val variablINBJ = mutableListOf<String>()
        val indicesINBJ = mutableListOf<Int>()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val tmp = mutableListOf<String>()
        tmp.addAll(childB.getProvidedVariableNames())
        for (name in childA.getProvidedVariableNames()) {
            val it = ColumnIteratorQueue()
            if (tmp.contains(name)) {
                variablINBJ.add(name)
                for (i in 0 until 3) {
                    val cc = childB.children[i]
                    if (cc is AOPVariable && cc.name == name) {
                        indicesINBJ.add(i)
                        break
                    }
                }
                tmp.remove(name)
                if (projectedVariables.contains(name)) {
                    columnsOUT.add(it)
                    columnsINAJ.add(0, childAv.columns[name]!!)
                    columnsOUTAJ.add(0, it)
                } else {
                    columnsINAJ.add(childAv.columns[name]!!)
                }
            } else {
                if (projectedVariables.contains(name)) {
                    columnsOUT.add(it)
                    columnsOUTAO.add(0, it)
                    columnsINAO.add(0, childAv.columns[name]!!)
                } else {
                    columnsINAO.add(childAv.columns[name]!!)
                }
            }
            outMap[name] = it
        }
        for (name in tmp) {
            variablINBO.add(name)
            val it = ColumnIteratorQueue()
            columnsOUTB.add(it)
            columnsOUT.add(it)
            outMap[name] = it
        }
        require(variablINBO.size > 0)
        val distributedStore = DistributedTripleStore.getNamedGraph(query, childB.graph)
        val valuesAO = Array<Value?>(columnsINAO.size) { null }
        val valuesAJ = Array<Value?>(columnsINAJ.size) { null }
        var count = 0
        val params = Array<AOPBase>(3) {
            if (childB.children[it] is AOPConstant) {
                count++
            }
/*return*/ childB.children[it] as AOPBase
        }
        for (i in 0 until indicesINBJ.size) {
            require(params[indicesINBJ[i]] is AOPVariable)
            params[indicesINBJ[i]] = AOPConstant(query, ResultSetDictionary.undefValue2)
            count++
        }
        var index: EIndexPattern
        require(count > 0, { "POPJoinWithStore do not bind all 3 variables" })
        require(count < 3, { "POPJoinWithStore no cartesian product" })
        if (count == 1) {
            if (params[0] is AOPConstant) {
                index = EIndexPattern.S
            } else if (params[1] is AOPConstant) {
                index = EIndexPattern.P
            } else {
                require(params[2] is AOPConstant)
                index = EIndexPattern.O
            }
        } else {
            require(count == 2)
            if (params[0] is AOPVariable) {
                index = EIndexPattern.PO
            } else if (params[1] is AOPVariable) {
                index = EIndexPattern.SO
            } else {
                require(params[2] is AOPVariable)
                index = EIndexPattern.SP
            }
        }
        require(indicesINBJ.size > 0)
        var columnsInBRoot: ColumnIteratorRow? = null
        val columnsInB = Array(variablINBO.size) { ColumnIterator() }
        for (i in 0 until columnsINAO.size) {
            valuesAO[i] = columnsINAO[i].next()
        }
        for (i in 0 until columnsINAJ.size) {
            valuesAJ[i] = columnsINAJ[i].next()
        }
        if ((valuesAO.size > 0 && valuesAO[0] != null) || (valuesAJ.size > 0 && valuesAJ[0] != null)) {
            for (i in 0 until indicesINBJ.size) {
                params[indicesINBJ[i]] = AOPConstant(query, query.dictionary.getValue(valuesAJ[i]!!))
            }
            columnsInBRoot = distributedStore.getIterator(params, index).evaluate()
            for (i in 0 until variablINBO.size) {
                columnsInB[i] = columnsInBRoot.columns[variablINBO[i]]!!
            }
            for (column in columnsOUT) {
                column.onEmptyQueue = {
                    loopA@ while (true) {
                        var done = true
                        if (variablINBO.size == 0) {
                            done = columnsInBRoot!!.hasNext()
                        } else {
                            loopB@ for (i in 0 until variablINBO.size) {
                                val value = columnsInB[i].next()
                                if (value == null) {
                                    require(i == 0)
                                    done = false
                                    break@loopB
                                }
                                columnsOUTB[i].queue.add(value)
                            }
                        }
                        if (done) {
                            for (i in 0 until columnsOUTAO.size) {
                                columnsOUTAO[i].queue.add(valuesAO[i]!!)
                            }
                            for (i in 0 until columnsOUTAJ.size) {
                                columnsOUTAJ[i].queue.add(valuesAJ[i]!!)
                            }
                            break@loopA
                        } else {
                            for (i in 0 until columnsINAO.size) {
                                valuesAO[i] = columnsINAO[i].next()
                            }
                            for (i in 0 until columnsINAJ.size) {
                                valuesAJ[i] = columnsINAJ[i].next()
                            }
                            if ((valuesAO.size > 0 && valuesAO[0] != null) || (valuesAJ.size > 0 && valuesAJ[0] != null)) {
                                for (i in 0 until indicesINBJ.size) {
                                    params[indicesINBJ[i]] = AOPConstant(query, query.dictionary.getValue(valuesAJ[i]!!))
                                }
                                columnsInBRoot = distributedStore.getIterator(params, index).evaluate()
                                for (i in 0 until variablINBO.size) {
                                    columnsInB[i] = columnsInBRoot!!.columns[variablINBO[i]]!!
                                }
                            } else {
                                break@loopA
                            }
                        }
                    }
                }
            }
        }
        return ColumnIteratorRow(outMap)
    }

    override fun toXMLElement(): XMLElement {
        val res = super.toXMLElement().addAttribute("optional", "" + optional)
        res["children"]!!.addContent(childB.toXMLElement())
        return res
    }

    override fun cloneOP() = POPJoinWithStore(query, projectedVariables, children[0].cloneOP(), childB.cloneOP(), optional)
}
