package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.EPOPDebugMode
import lupos.s00misc.ESortPriority
import lupos.s00misc.ITERATOR_DEBUG_MODE
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIterator
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPDebug(query: IQuery, projectedVariables: List<String>, child: IOPBase) : POPBase(query, projectedVariables, EOperatorID.POPDebugID, "POPDebug", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun getPartitionCount(variable: String): Int = getChildren()[0].getPartitionCount(variable)
    override fun equals(other: Any?): Boolean = other is POPDebug && getChildren()[0] == other.getChildren()[0]
    override fun cloneOP(): IOPBase = POPDebug(query, projectedVariables, getChildren()[0].cloneOP())
    override fun getRequiredVariableNames(): List<String> = listOf<String>()
    override fun getProvidedVariableNames(): List<String> = getChildren()[0].getProvidedVariableNames()
    override fun getProvidedVariableNamesInternal(): List<String> = (getChildren()[0] as POPBase).getProvidedVariableNamesInternal()
    override fun toSparql(): String = getChildren()[0].toSparql()
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val child = getChildren()[0].evaluate(parent)
        when (ITERATOR_DEBUG_MODE) {
            EPOPDebugMode.NONE -> {
                return child
            }
            EPOPDebugMode.DEBUG1 -> {
                val target = getChildren()[0].getProvidedVariableNames()
                SanityCheck.println({ "POPDebug-child-mode ... $uuid ${getChildren()[0].getUUID()}" })
                if (child.hasColumnMode()) {
                    try {
                        child.columns
                    } catch (e: Throwable) {
                        SanityCheck.println { "debugchildclassname::" + getChildren()[0].getClassname() }
                        throw e
                    }
                    val columnMode = mutableListOf<String>()
                    for ((k, v) in child.columns) {
                        columnMode.add(k)
                    }
                    SanityCheck.check { columnMode.containsAll(target) }
                    SanityCheck.check { target.containsAll(columnMode) }
                } else if (child.hasRowMode()) {
                    val rowMode = child.rows.columns.toMutableList()
                    SanityCheck.check { rowMode.containsAll(target) }
                    SanityCheck.check { target.containsAll(rowMode) }
                }
                return child
            }
            EPOPDebugMode.DEBUG2 -> {
                val target = getChildren()[0].getProvidedVariableNames()
                SanityCheck.println({ "POPDebug-child-mode ... $uuid ${getChildren()[0].getUUID()}" })
                if (child.hasColumnMode()) {
                    try {
                        child.columns
                    } catch (e: Throwable) {
                        SanityCheck.println { "debugchildclassname2::" + getChildren()[0].getClassname() }
                        throw e
                    }
                    val outMap = mutableMapOf<String, ColumnIterator>()
                    val columnMode = mutableListOf<String>()
                    for ((k, v) in child.columns) {
                        columnMode.add(k)
                        var counter = 0
                        SanityCheck.println({ "$uuid $k opened" })
                        val iterator = object : ColumnIterator() {
                            @JvmField
                            var label = 1
                            override /*suspend*/ fun next(): Int {
                                if (label != 0) {
                                    SanityCheck.println({ "$uuid $k next call" })
                                    val res = v.next()
                                    if (res == ResultSetDictionaryExt.nullValue) {
                                        SanityCheck.println({ "$uuid $k next return closed $counter ${parent.data} ResultSetDictionaryExt.nullValue" })
                                    } else {
                                        counter++
                                        SanityCheck.println({ "$uuid $k next return $counter ${parent.data} ${res.toString(16)}" })
                                    }
                                    return res
                                } else {
                                    return ResultSetDictionaryExt.nullValue
                                }
                            }

                            override /*suspend*/ fun nextSIP(minValue: Int, result: IntArray) {
                                if (label != 0) {
                                    SanityCheck.println({ "$uuid $k next call minValue SIP" })
                                    v.nextSIP(minValue, result)
                                    val res = result[1]
                                    if (res == ResultSetDictionaryExt.nullValue) {
                                        SanityCheck.println({ "$uuid $k next return closed $counter ${parent.data} ResultSetDictionaryExt.nullValue" })
                                    } else {
                                        counter++
                                        SanityCheck.println({ "$uuid $k next return $counter ${parent.data} ${res.toString(16)}" })
                                    }
                                } else {
                                    result[0] = 0
                                    result[1] = ResultSetDictionaryExt.nullValue
                                }
                            }

                            override /*suspend*/ fun skipSIP(skipCount: Int): Int {
                                if (label != 0) {
                                    SanityCheck.println({ "$uuid $k next call skip SIP" })
                                    val res = v.skipSIP(skipCount)
                                    if (res == ResultSetDictionaryExt.nullValue) {
                                        SanityCheck.println({ "$uuid $k next return closed $counter ${parent.data} ResultSetDictionaryExt.nullValue" })
                                    } else {
                                        counter++
                                        SanityCheck.println({ "$uuid $k next return $counter ${parent.data} ${res.toString(16)}" })
                                    }
                                    return res
                                } else {
                                    return ResultSetDictionaryExt.nullValue
                                }
                            }

                            override /*suspend*/ fun close() {
                                if (label != 0) {
                                    label = 0
                                    SanityCheck.println({ "$uuid $k closed $counter ${parent.data}" })
                                    v.close()
                                }
                            }
                        }
                        outMap[k] = iterator
                    }
                    SanityCheck.check { columnMode.containsAll(target) }
                    SanityCheck.check({ target.containsAll(columnMode) }, { "$uuid $target $columnMode" })
                    return IteratorBundle(outMap)
                } else if (child.hasRowMode()) {
                    val rowMode = child.rows.columns.toMutableList()
                    SanityCheck.check { rowMode.containsAll(target) }
                    SanityCheck.check { target.containsAll(rowMode) }
                    val iterator = RowIterator()
                    var counter = 0
                    iterator.columns = child.rows.columns
                    iterator.next = {
                        SanityCheck.println({ "$uuid next call" })
                        val res = child.rows.next()
                        iterator.buf = child.rows.buf
                        if (res < 0) {
                            SanityCheck.println({ "$uuid next return closed $counter ${parent.data} ResultSetDictionaryExt.nullValue" })
                        } else {
                            counter++
                            SanityCheck.println({ "$uuid next return $counter ${parent.data} ${iterator.buf.map { it.toString(16) }}" })
                        }
                        /*return*/ res
                    }
                    iterator.close = {
                        SanityCheck.println({ "$uuid closed $counter ${parent.data}" })
                        child.rows.close()
                        iterator._close()
                    }
                    return IteratorBundle(iterator)
                } else {
                    return child
                }
            }
        }
    }
}
