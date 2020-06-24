package lupos.s09physicalOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPJoinCartesianProduct(query: Query, projectedVariables: List<String>, childA: OPBase, childB: OPBase, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinCartesianProductID, "POPJoinCartesianProduct", arrayOf(childA, childB), ESortPriority.JOIN) {
    override fun toSparql(): String {
        if (optional) {
            return "OPTIONAL{" + children[0].toSparql() + children[1].toSparql() + "}"
        }
        return children[0].toSparql() + children[1].toSparql()
    }

    override fun equals(other: Any?) = other is POPJoinCartesianProduct && optional == other.optional && children[0] == other.children[0] && children[1] == other.children[1]
    override suspend fun evaluate(): IteratorBundle {
        val columns = LOPJoin.getColumns(children[0].getProvidedVariableNames(), children[1].getProvidedVariableNames())
        require(columns[0].size == 0)
        val childA = children[0].evaluate()
        val childB = children[1].evaluate()
        val columnsINAO = mutableListOf<ColumnIterator>()//only in childA
        val columnsINBO = mutableListOf<ColumnIterator>()//only in childB
        val outO = Array(2) { mutableListOf<ColumnIteratorChildIterator>() }//only in one of the childs
        val outIterators = mutableListOf<ColumnIteratorChildIterator>()
        val outMap = mutableMapOf<String, ColumnIterator>()
        var res: IteratorBundle?
        var t: ColumnIteratorChildIterator
        for (name in columns[1]) {
            t = ColumnIteratorChildIterator()
            outIterators.add(t)
            outMap[name] = ColumnIteratorDebug(uuid, name, t)
            outO[0].add(t)
            columnsINAO.add(childA.columns[name]!!)
        }
        for (name in columns[2]) {
            t = ColumnIteratorChildIterator()
            outIterators.add(t)
            outMap[name] = ColumnIteratorDebug(uuid, name, t)
            outO[1].add(t)
            columnsINBO.add(childB.columns[name]!!)
        }
        var count: Int
        if (columnsINAO.size == 0 && columnsINBO.size == 0) {
            res = IteratorBundle(childA.count * childB.count)
        } else if (columnsINAO.size == 0) {
            if (childA.count > 0) {
                for (columnIndex in 0 until columnsINBO.size) {
                    outO[1][columnIndex].childs.add(ColumnIteratorRepeatIterator(childA.count, columnsINBO[columnIndex]))
                }
            }
            res = IteratorBundle(outMap)
        } else if (columnsINBO.size == 0) {
            if (childB.count > 0) {
                for (columnIndex in 0 until columnsINAO.size) {
                    outO[0][columnIndex].childs.add(ColumnIteratorRepeatIterator(childB.count, columnsINAO[columnIndex]))
                }
            }
            res = IteratorBundle(outMap)
        } else {
            val data = Array(columnsINBO.size) { MyListValue() }
            loopC@ while (true) {
                for (columnIndex in 0 until columnsINBO.size) {
                    val value = columnsINBO[columnIndex].next()
                    if (value == null) {
                        break@loopC
                    }
                    data[columnIndex].add(value)
                }
            }
            count = data[0].size
            if (count == 0) {
                if (optional) {
                    for (iterator in outIterators) {
                        iterator.close = {
                            iterator._close()
                            for (variable in children[0].getProvidedVariableNames()) {
                                childA.columns[variable]!!.close()
                            }
                            for (variable in children[1].getProvidedVariableNames()) {
                                childB.columns[variable]!!.close()
                            }
                        }
                        iterator.onNoMoreElements = {
                            var done = false
                            for (columnIndex in 0 until columnsINAO.size) {
                                val value = columnsINAO[columnIndex].next()
                                if (value == null) {
                                    SanityCheck.check { columnIndex == 0 }
                                    done = true
                                    break
                                }
                                outO[0][columnIndex].childs.add(ColumnIteratorRepeatValue(1, value))
                            }
                            if (!done) {
                                for (columnIndex in 0 until columnsINBO.size) {
                                    outO[1][columnIndex].childs.add(ColumnIteratorRepeatValue(1, ResultSetDictionary.undefValue))
                                }
                            }
                        }
                    }
                }
            } else {
                for (iterator in outIterators) {
                    iterator.close = {
                        iterator._close()
                        for (variable in children[0].getProvidedVariableNames()) {
                            childA.columns[variable]!!.close()
                        }
                        for (variable in children[1].getProvidedVariableNames()) {
                            childB.columns[variable]!!.close()
                        }
                    }
                    iterator.onNoMoreElements = {
                        var done = false
                        for (columnIndex in 0 until columnsINAO.size) {
                            val value = columnsINAO[columnIndex].next()
                            if (value == null) {
                                SanityCheck.check { columnIndex == 0 }
                                done = true
                                break
                            }
                            outO[0][columnIndex].childs.add(ColumnIteratorRepeatValue(count, value))
                        }
                        if (!done) {
                            for (columnIndex in 0 until columnsINBO.size) {
                                outO[1][columnIndex].childs.add(ColumnIteratorMultiValue(data[columnIndex]))
                            }
                        }
                    }
                }
            }
            res = IteratorBundle(outMap)
        }
        return res
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP() = POPJoinCartesianProduct(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
}
