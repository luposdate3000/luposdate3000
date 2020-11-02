package lupos.s09physicalOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIteratorEmpty
import lupos.s04logicalOperators.iterator.ColumnIteratorEmpty
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPJoinCartesianProduct(query: IQuery, projectedVariables: List<String>, childA: IOPBase, childB: IOPBase, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinCartesianProductID, "POPJoinCartesianProduct", arrayOf(childA, childB), ESortPriority.JOIN) {
    override fun getPartitionCount(variable: String): Int {
        if (children[0].getProvidedVariableNames().contains(variable)) {
            if (children[1].getProvidedVariableNames().contains(variable)) {
                SanityCheck.check { children[0].getPartitionCount(variable) == children[1].getPartitionCount(variable) }
                return children[0].getPartitionCount(variable)
            } else {
                return children[0].getPartitionCount(variable)
            }
        } else {
            if (children[1].getProvidedVariableNames().contains(variable)) {
                return children[1].getPartitionCount(variable)
            } else {
                throw Exception("unknown variable $variable")
            }
        }
    }

    override fun toSparql(): String {
        if (optional) {
            return "OPTIONAL{" + children[0].toSparql() + children[1].toSparql() + "}"
        }
        return children[0].toSparql() + children[1].toSparql()
    }

    override fun equals(other: Any?) = other is POPJoinCartesianProduct && optional == other.optional && children[0] == other.children[0] && children[1] == other.children[1]
    override suspend fun evaluate(parent: Partition): IteratorBundle {
        val columns = LOPJoin.getColumns(children[0].getProvidedVariableNames(), children[1].getProvidedVariableNames())
        SanityCheck {
            for (v in children[0].getProvidedVariableNames()) {
                getPartitionCount(v)
            }
            for (v in children[1].getProvidedVariableNames()) {
                getPartitionCount(v)
            }
        }
        SanityCheck.check { columns[0].size == 0 }
        SanityCheck.println({ "POPJoinCartesianProductXXX$uuid open A $classname" })
        val childA = children[0].evaluate(parent)
        SanityCheck.println({ "POPJoinCartesianProductXXX$uuid open B $classname" })
        val childB = children[1].evaluate(parent)
        val columnsINAO = mutableListOf<ColumnIterator>()//only in childA
        val columnsINBO = mutableListOf<ColumnIterator>()//only in childB
        val outO = Array(2) { mutableListOf<ColumnIteratorChildIterator>() }//only in one of the childs
        val outMap = mutableMapOf<String, ColumnIterator>()
        var res: IteratorBundle?
        var t: ColumnIteratorChildIterator
        for (name in columns[1]) {
            columnsINAO.add(childA.columns[name]!!)
        }
        for (name in columns[2]) {
            columnsINBO.add(childB.columns[name]!!)
        }
        var count: Int
        if (columnsINAO.size == 0 && columnsINBO.size == 0) {
            SanityCheck.println({ "POPJoinCartesianProductXXX$uuid mode A" })
            try {
                res = IteratorBundle(childA.count() * childB.count())
            } catch (e: Throwable) {
                SanityCheck.println("exception from ${getUUID()} ${children[0].getUUID()} ${children[1].getUUID()}")
                throw e
            }
        } else if (columnsINAO.size == 0) {
            SanityCheck.println({ "POPJoinCartesianProductXXX$uuid mode B" })
            SanityCheck.println({ "POPJoinCartesianProductXXX$uuid closecount A $classname" })
            if (childA.count() > 0) {
                for (columnIndex in 0 until columnsINBO.size) {
                    outMap[columns[2][columnIndex]] = ColumnIteratorRepeatIterator(childA.count(), columnsINBO[columnIndex])
                }
            } else {
                SanityCheck.println { "POPJoinCartesianProductXXX$uuid close B $classname" }
                for ((k, v) in childB.columns) {
                    v.close()
                }
                for (columnIndex in 0 until columnsINBO.size) {
                    outMap[columns[2][columnIndex]] = ColumnIteratorEmpty()
                }
            }
            res = IteratorBundle(outMap)
        } else if (columnsINBO.size == 0) {
            SanityCheck.println({ "POPJoinCartesianProductXXX$uuid mode C" })
            SanityCheck.println({ "POPJoinCartesianProductXXX$uuid closecount B $classname" })
            if (childB.count() > 0) {
                for (columnIndex in 0 until columnsINAO.size) {
                    outMap[columns[1][columnIndex]] = ColumnIteratorRepeatIterator(childB.count(), columnsINAO[columnIndex])
                }
            } else {
                SanityCheck.println { "POPJoinCartesianProductXXX$uuid close A $classname" }
                for ((k, v) in childA.columns) {
                    v.close()
                }
                for (columnIndex in 0 until columnsINAO.size) {
                    outMap[columns[2][columnIndex]] = ColumnIteratorEmpty()
                }
            }
            res = IteratorBundle(outMap)
        } else {
            SanityCheck.println({ "POPJoinCartesianProductXXX$uuid mode D" })
            val data = Array(columnsINBO.size) { mutableListOf<Int>() }
            loopC@ while (true) {
                for (columnIndex in 0 until columnsINBO.size) {
                    val value = columnsINBO[columnIndex].next()
                    if (value == ResultSetDictionaryExt.nullValue) {
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
                    for (i in 0 until columns[1].size + columns[2].size) {
                        val iterator = object : ColumnIteratorChildIterator() {
                            @JvmField
                            val childA0 = childA
                            override suspend fun close() {
                                __close()
                            }

                            suspend inline fun __close() {
                                if (label != 0) {
                                    _close()
                                    SanityCheck.println({ "POPJoinCartesianProductXXX$uuid close A $classname" })
                                    for ((k, v) in childA0.columns) {
                                        v.close()
                                    }
                                }
                            }

                            override suspend fun next(): Int {
                                return next_helper({
                                    var done = false
                                    for (columnIndex in 0 until columnsINAO.size) {
                                        val value = columnsINAO[columnIndex].next()
                                        if (value == ResultSetDictionaryExt.nullValue) {
                                            SanityCheck.check { columnIndex == 0 }
                                            done = true
                                            SanityCheck.println({ "POPJoinCartesianProductXXX$uuid close A $classname" })
                                            for ((k, v) in childA0.columns) {
                                                v.close()
                                            }
                                            break
                                        }
                                        outO[0][columnIndex].addChild(ColumnIteratorRepeatValue(1, value))
                                    }
                                    if (!done) {
                                        for (columnIndex in 0 until columnsINBO.size) {
                                            outO[1][columnIndex].addChild(ColumnIteratorRepeatValue(1, ResultSetDictionaryExt.undefValue))
                                        }
                                    }
                                }, { __close() })
                            }
                        }
                        if (i < columns[1].size) {
                            outO[0].add(iterator)
                            outMap[columns[1][i]] = iterator
                        } else {
                            outO[1].add(iterator)
                            outMap[columns[2][i - columns[1].size]] = iterator
                        }
                    }
                } else {
                    SanityCheck.println { "POPJoinCartesianProductXXX$uuid close A $classname" }
                    for ((k, v) in childA.columns) {
                        v.close()
                    }
                    for (i in 0 until columns[1].size + columns[2].size) {
                        val iterator = ColumnIteratorChildIteratorEmpty()
                        if (i < columns[1].size) {
                            outO[0].add(iterator)
                            outMap[columns[1][i]] = iterator
                        } else {
                            outO[1].add(iterator)
                            outMap[columns[2][i - columns[1].size]] = iterator
                        }
                    }
                }
            } else {
                SanityCheck.println({ "POPJoinCartesianProductXXX$uuid mode G" })
                for (i in 0 until columns[1].size + columns[2].size) {
                    val iterator = object : ColumnIteratorChildIterator() {
                        @JvmField
                        val childA0 = childA
                        override suspend fun close() {
                            __close()
                        }

                        suspend inline fun __close() {
                            if (label != 0) {
                                _close()
                                SanityCheck.println({ "POPJoinCartesianProductXXX$uuid close A $classname" })
                                for ((k, v) in childA0.columns) {
                                    v.close()
                                }
                            }
                        }

                        override suspend fun next(): Int {
                            return next_helper({
                                var done = false
                                for (columnIndex in 0 until columnsINAO.size) {
                                    val value = columnsINAO[columnIndex].next()
                                    if (value == ResultSetDictionaryExt.nullValue) {
                                        SanityCheck.check { columnIndex == 0 }
                                        done = true
                                        SanityCheck.println({ "POPJoinCartesianProductXXX$uuid close A $classname" })
                                        for ((k, v) in childA0.columns) {
                                            v.close()
                                        }
                                        break
                                    }
                                    outO[0][columnIndex].addChild(ColumnIteratorRepeatValue(count, value))
                                }
                                if (!done) {
                                    for (columnIndex in 0 until columnsINBO.size) {
                                        outO[1][columnIndex].addChild(ColumnIteratorMultiValue(data[columnIndex]))
                                    }
                                }
                            }, { __close() })
                        }
                    }
                    if (i < columns[1].size) {
                        outO[0].add(iterator)
                        outMap[columns[1][i]] = iterator
                    } else {
                        outO[1].add(iterator)
                        outMap[columns[2][i - columns[1].size]] = iterator
                    }
                }
            }
            res = IteratorBundle(outMap)
        }
        return res
    }

    override suspend fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP(): IOPBase = POPJoinCartesianProduct(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
}
