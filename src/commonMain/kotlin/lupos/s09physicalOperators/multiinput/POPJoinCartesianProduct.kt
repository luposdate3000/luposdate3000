package lupos.s09physicalOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.FuncColumnIteratorClose
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
    override fun evaluate(parent: Partition): IteratorBundle {
        val columns = LOPJoin.getColumns(children[0].getProvidedVariableNames(), children[1].getProvidedVariableNames())
        require(columns[0].size == 0)
        SanityCheck.println({ "POPJoinCartesianProductXXX$uuid open A $classname" })
        val childA = children[0].evaluate(parent)
        SanityCheck.println({ "POPJoinCartesianProductXXX$uuid open B $classname" })
        val childB = children[1].evaluate(parent)
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
            outMap[name] = t
            outO[0].add(t)
            columnsINAO.add(childA.columns[name]!!)
        }
        for (name in columns[2]) {
            t = ColumnIteratorChildIterator()
            outIterators.add(t)
            outMap[name] = t
            outO[1].add(t)
            columnsINBO.add(childB.columns[name]!!)
        }
        var count: Int
        if (columnsINAO.size == 0 && columnsINBO.size == 0) {
            SanityCheck.println({ "POPJoinCartesianProductXXX$uuid mode A" })
            try {
                res = IteratorBundle(childA.count * childB.count)
            } catch (e: Throwable) {
                println("exception from $uuid ${childA.mode} ${childB.mode} ${children[0].uuid} ${children[1].uuid}")
                throw e
            }
        } else if (columnsINAO.size == 0) {
            SanityCheck.println({ "POPJoinCartesianProductXXX$uuid mode B" })
            SanityCheck.println({ "POPJoinCartesianProductXXX$uuid closecount A $classname" })
            if (childA.count > 0) {
                for (columnIndex in 0 until columnsINBO.size) {
                    outO[1][columnIndex].childs.add(ColumnIteratorRepeatIterator(childA.count, columnsINBO[columnIndex]))
//dont assign close, because every column in pass through
                }
            } else {
                SanityCheck.println { "POPJoinCartesianProductXXX$uuid close B $classname" }
                for ((k, v) in childB.columns) {
                    v.close()
                }
            }
            res = IteratorBundle(outMap)
        } else if (columnsINBO.size == 0) {
            SanityCheck.println({ "POPJoinCartesianProductXXX$uuid mode C" })
            SanityCheck.println({ "POPJoinCartesianProductXXX$uuid closecount B $classname" })
            if (childB.count > 0) {
                for (columnIndex in 0 until columnsINAO.size) {
                    outO[0][columnIndex].childs.add(ColumnIteratorRepeatIterator(childB.count, columnsINAO[columnIndex]))
//dont assign close, because every column in pass through
                }
            } else {
                SanityCheck.println { "POPJoinCartesianProductXXX$uuid close A $classname" }
                for ((k, v) in childA.columns) {
                    v.close()
                }
            }
            res = IteratorBundle(outMap)
        } else {
            SanityCheck.println({ "POPJoinCartesianProductXXX$uuid mode D" })
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
            SanityCheck.println({ "POPJoinCartesianProductXXX$uuid close B $classname" })
            for ((k, v) in childB.columns) {
                v.close()
            }
            count = data[0].size
            if (count == 0) {
                SanityCheck.println({ "POPJoinCartesianProductXXX$uuid mode E" })
                if (optional) {
                    SanityCheck.println({ "POPJoinCartesianProductXXX$uuid mode F" })
                    for (iterator in outIterators) {
                        iterator.close = object : FuncColumnIteratorClose("POPJoinCartesinProduct.close") {
                            override fun invoke() {
                                iterator._close()
                                SanityCheck.println({ "POPJoinCartesianProductXXX$uuid close A $classname" })
                                for ((k, v) in childA.columns) {
                                    v.close()
                                }
                            }
                        }
                        iterator.onNoMoreElements = {
                            var done = false
                            for (columnIndex in 0 until columnsINAO.size) {
                                val value = columnsINAO[columnIndex].next()
                                if (value == null) {
                                    SanityCheck.check { columnIndex == 0 }
                                    done = true
                                    SanityCheck.println({ "POPJoinCartesianProductXXX$uuid close A $classname" })
                                    for ((k, v) in childA.columns) {
                                        v.close()
                                    }
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
                } else {
                    SanityCheck.println { "POPJoinCartesianProductXXX$uuid close A $classname" }
                    for ((k, v) in childA.columns) {
                        v.close()
                    }
                }
            } else {
                SanityCheck.println({ "POPJoinCartesianProductXXX$uuid mode G" })
                for (iterator in outIterators) {
                    iterator.close = object : FuncColumnIteratorClose("POPJoinCartesinProduct.close") {
                        override fun invoke() {
                            SanityCheck.println({ "POPJoinCartesianProductXXX$uuid close A $classname" })
                            iterator._close()
                            for ((k, v) in childA.columns) {
                                v.close()
                            }
                        }
                    }
                    iterator.onNoMoreElements = {
                        var done = false
                        for (columnIndex in 0 until columnsINAO.size) {
                            val value = columnsINAO[columnIndex].next()
                            if (value == null) {
                                SanityCheck.check { columnIndex == 0 }
                                done = true
                                SanityCheck.println({ "POPJoinCartesianProductXXX$uuid close A $classname" })
                                for ((k, v) in childA.columns) {
                                    v.close()
                                }
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
