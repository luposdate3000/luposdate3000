package lupos.s09physicalOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPJoinMergeSingleColumn(query: Query, projectedVariables: List<String>, childA: OPBase, childB: OPBase, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinMergeSingleColumnID, "POPJoinMergeSingleColumn", arrayOf(childA, childB), ESortPriority.JOIN) {
    override fun toSparql(): String {
        if (optional) {
            return "OPTIONAL{" + children[0].toSparql() + children[1].toSparql() + "}"
        }
        return children[0].toSparql() + children[1].toSparql()
    }

    override fun equals(other: Any?) = other is POPJoinMergeSingleColumn && optional == other.optional && children[0] == other.children[0] && children[1] == other.children[1]
    override suspend fun evaluate(): IteratorBundle {
        SanityCheck.check { !optional }
        SanityCheck.check { projectedVariables.size == 1 }
        SanityCheck.check { children[0].getProvidedVariableNames().size == 1 }
        SanityCheck.check { children[0].getProvidedVariableNames()[0] == projectedVariables[0] }
        SanityCheck.check { children[1].getProvidedVariableNames().size == 1 }
        SanityCheck.check { children[1].getProvidedVariableNames()[0] == projectedVariables[0] }
        SanityCheck.println({ "$uuid open $classname" })
        val child = Array(2) { children[it].evaluate().columns[projectedVariables[0]]!! }
        val head = Array(2) { child[it].next() }
        val outMap = mutableMapOf<String, ColumnIterator>()
        val iterator = ColumnIterator()
        outMap[projectedVariables[0]] = ColumnIteratorDebug(uuid, projectedVariables[0], iterator)
        if (head[0] != null && head[1] != null) {
            val count = IntArray(2) { 0 }
            var counter = 0
            var value: Value? = head[0]
            iterator.close = {
                iterator._close()
                SanityCheck.println({ "$uuid close $classname" })
                child[0].close()
                child[1].close()
            }
            iterator.next = {
                if (counter == 0) {
                    var done = false
                    var change = true
                    loop@ while (change) {
                        change = false
                        for (i in 0 until 2) {
                            while (head[i]!! < head[1 - i]!!) {
                                change = true
                                head[i] = child[i].next()
                                if (head[i] == null) {
                                    iterator.close()
                                    value = null
                                    done = true
                                    break@loop
                                }
                            }
                        }
                    }
                    if (!done) {
                        value = head[0]
                        for (i in 0 until 2) {
                            count[i] = 0
                            while (head[i] == value) {
                                count[i]++
                                head[i] = child[i].next()
                            }
                        }
                        counter = count[0] * count[1]
                        if (head[0] == null || head[1] == null) {
                            if (counter == 0) {
                                iterator.close()
                            } else {
                                iterator.next = {
                                    if (counter == 0) {
                                        iterator.close()
                                        value = null
                                    } else {
                                        counter--
                                    }
                                    /*return*/ value
                                }
                            }
                        }
                    }
                }
                counter--
                /*return*/ value
            }
        } else {
            SanityCheck.println({ "$uuid close $classname" })
            child[0].close()
            child[1].close()
        }
        return IteratorBundle(outMap)
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP() = POPJoinMergeSingleColumn(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
}
