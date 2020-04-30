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
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.iterator.*
import lupos.s09physicalOperators.POPBase

class POPJoinMergeSingleColumn(query: Query, projectedVariables: List<String>, childA: OPBase, childB: OPBase, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinMergeSingleColumnID, "POPJoinMergeSingleColumn", arrayOf(childA, childB), ESortPriority.JOIN) {
    override fun toSparql(): String {
        if (optional) {
            return "OPTIONAL{" + children[0].toSparql() + children[1].toSparql() + "}"
        }
        return children[0].toSparql() + children[1].toSparql()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is POPJoinMergeSingleColumn) {
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
        SanityCheck.check { !optional }
        SanityCheck.check { projectedVariables.size == 1 }
        SanityCheck.check { children[0].getProvidedVariableNames().size == 1 }
        SanityCheck.check { children[0].getProvidedVariableNames()[0] == projectedVariables[0] }
        SanityCheck.check { children[1].getProvidedVariableNames().size == 1 }
        SanityCheck.check { children[1].getProvidedVariableNames()[0] == projectedVariables[0] }
        val child = Array(2) { children[it].evaluate().columns[projectedVariables[0]]!! }
        val head = Array(2) { child[it].next() }
        val outMap = mutableMapOf<String, ColumnIterator>()
        val iterator = ColumnIterator()
        outMap[projectedVariables[0]] = ColumnIteratorDebug(uuid, projectedVariables[0], iterator)
        if (head[0] != null && head[1] != null) {
            val count = IntArray(2) { 0 }
            var counter = 0
            var value: Value? = head[0]
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
        }
        return ColumnIteratorRow(outMap)
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP() = POPJoinMergeSingleColumn(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
}
