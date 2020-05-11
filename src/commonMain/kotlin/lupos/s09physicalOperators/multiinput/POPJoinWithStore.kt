package lupos.s09physicalOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.ESortType
import lupos.s00misc.MyListInt
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorQueue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
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

    override suspend fun evaluate(): IteratorBundle {
        SanityCheck.check { !optional }
        SanityCheck.check { !childB.graphVar }
        val childAv = children[0].evaluate()
        val childA = children[0]
        val columnsINAO = mutableListOf<ColumnIterator>()
        val columnsINAJ = mutableListOf<ColumnIterator>()
        val columnsOUTAO = mutableListOf<ColumnIteratorQueue>()
        val columnsOUTAJ = mutableListOf<ColumnIteratorQueue>()
        val columnsOUTB = mutableListOf<ColumnIteratorQueue>()
        val columnsOUT = mutableListOf<ColumnIteratorQueue>()
        val variablINBO = mutableListOf<String>()
        val indicesINBJ = MyListInt()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val tmp = mutableListOf<String>()
        tmp.addAll(childB.getProvidedVariableNames())
        for (name in childA.getProvidedVariableNames()) {
            val it = ColumnIteratorQueue()
            if (tmp.contains(name)) {
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
                columnsOUT.add(it)
                columnsOUTAO.add(0, it)
                columnsINAO.add(0, childAv.columns[name]!!)
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
        SanityCheck.check { variablINBO.size > 0 }
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
            SanityCheck.check { params[indicesINBJ[i]] is AOPVariable }
            params[indicesINBJ[i]] = AOPConstant(query, ResultSetDictionary.undefValue2)
            count++
        }
        var index: EIndexPattern
        SanityCheck.check { count > 0 }
        SanityCheck.check { count < 3 }
        SanityCheck {
            for (i in 0 until childB.mySortPriority.size) {
                SanityCheck.check { childB.mySortPriority[i].sortType == ESortType.FAST }
            }
        }
        if (count == 1) {
            if (params[0] is AOPConstant) {
                if (childB.mySortPriority.size == 0 || (params[1] as AOPVariable).name == childB.mySortPriority[0].variableName) {
                    index = EIndexPattern.S_PO
                } else {
                    index = EIndexPattern.S_OP
                }
            } else if (params[1] is AOPConstant) {
                if (childB.mySortPriority.size == 0 || (params[0] as AOPVariable).name == childB.mySortPriority[0].variableName) {
                    index = EIndexPattern.P_SO
                } else {
                    index = EIndexPattern.P_OS
                }
            } else {
                SanityCheck.check { params[2] is AOPConstant }
                if (childB.mySortPriority.size == 0 || (params[0] as AOPVariable).name == childB.mySortPriority[0].variableName) {
                    index = EIndexPattern.O_SP
                } else {
                    index = EIndexPattern.O_PS
                }
            }
        } else {
            SanityCheck.check { count == 2 }
            if (params[0] is AOPVariable) {
                index = EIndexPattern.PO_S
            } else if (params[1] is AOPVariable) {
                index = EIndexPattern.SO_P
            } else {
                SanityCheck.check { params[2] is AOPVariable }
                index = EIndexPattern.SP_O
            }
        }
        SanityCheck.check { indicesINBJ.size > 0 }
        SanityCheck.check { valuesAJ.size == indicesINBJ.size }
        val columnsInB = Array(variablINBO.size) { ColumnIterator() }
        for (i in 0 until columnsINAO.size) {
            valuesAO[i] = columnsINAO[i].next()
        }
        for (i in 0 until columnsINAJ.size) {
            valuesAJ[i] = columnsINAJ[i].next()
        }
        if (valuesAJ[0] != null) {
//there is at least one value in A
            for (i in 0 until indicesINBJ.size) {
                params[indicesINBJ[i]] = AOPConstant(query, valuesAJ[i]!!)
            }
            var columnsInBRoot = distributedStore.getIterator(params, index).evaluate()
            for (i in 0 until variablINBO.size) {
                columnsInB[i] = columnsInBRoot.columns[variablINBO[i]]!!
            }
            for (column in columnsOUT) {
                column.onEmptyQueue = {
                    loopA@ while (true) {
                        var done = true
                        loopB@ for (i in 0 until variablINBO.size) {
                            val value = columnsInB[i].next()
                            if (value == null) {
                                SanityCheck.check { i == 0 }
                                done = false
                                break@loopB
                            } else {
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
                            if (valuesAJ[0] != null) {
                                for (i in 0 until indicesINBJ.size) {
                                    params[indicesINBJ[i]] = AOPConstant(query, valuesAJ[i]!!)
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
        return IteratorBundle(outMap)
    }

    override fun toXMLElement(): XMLElement {
        val res = super.toXMLElement().addAttribute("optional", "" + optional)
        res["children"]!!.addContent(childB.toXMLElement())
        return res
    }

    override fun cloneOP() = POPJoinWithStore(query, projectedVariables, children[0].cloneOP(), childB.cloneOP(), optional)
}
