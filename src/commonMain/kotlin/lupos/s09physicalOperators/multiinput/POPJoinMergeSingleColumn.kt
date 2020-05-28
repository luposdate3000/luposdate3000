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
Coverage.funStart(10682)
        if (optional) {
Coverage.ifStart(10683)
            return "OPTIONAL{" + children[0].toSparql() + children[1].toSparql() + "}"
        }
Coverage.statementStart(10684)
        return children[0].toSparql() + children[1].toSparql()
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(10685)
        if (other !is POPJoinMergeSingleColumn) {
Coverage.ifStart(10686)
            return false
        }
Coverage.statementStart(10687)
        if (optional != other.optional) {
Coverage.ifStart(10688)
            return false
        }
Coverage.statementStart(10689)
        for (i in children.indices) {
Coverage.forLoopStart(10690)
            if (!children[i].equals(other.children[i])) {
Coverage.ifStart(10691)
                return false
            }
Coverage.statementStart(10692)
        }
Coverage.statementStart(10693)
        return true
    }
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(10694)
        SanityCheck.check { !optional }
Coverage.statementStart(10695)
        SanityCheck.check { projectedVariables.size == 1 }
Coverage.statementStart(10696)
        SanityCheck.check { children[0].getProvidedVariableNames().size == 1 }
Coverage.statementStart(10697)
        SanityCheck.check { children[0].getProvidedVariableNames()[0] == projectedVariables[0] }
Coverage.statementStart(10698)
        SanityCheck.check { children[1].getProvidedVariableNames().size == 1 }
Coverage.statementStart(10699)
        SanityCheck.check { children[1].getProvidedVariableNames()[0] == projectedVariables[0] }
Coverage.statementStart(10700)
        val child = Array(2) { children[it].evaluate().columns[projectedVariables[0]]!! }
Coverage.statementStart(10701)
        val head = Array(2) { child[it].next() }
Coverage.statementStart(10702)
        val outMap = mutableMapOf<String, ColumnIterator>()
Coverage.statementStart(10703)
        val iterator = ColumnIterator()
Coverage.statementStart(10704)
        outMap[projectedVariables[0]] = ColumnIteratorDebug(uuid, projectedVariables[0], iterator)
Coverage.statementStart(10705)
        if (head[0] != null && head[1] != null) {
Coverage.ifStart(10706)
            val count = IntArray(2) { 0 }
Coverage.statementStart(10707)
            var counter = 0
Coverage.statementStart(10708)
            var value: Value? = head[0]
Coverage.statementStart(10709)
            iterator.next = {
Coverage.statementStart(10710)
                if (counter == 0) {
Coverage.ifStart(10711)
                    var done = false
Coverage.statementStart(10712)
                    var change = true
Coverage.statementStart(10713)
                    loop@ while (change) {
Coverage.whileLoopStart(10714)
                        change = false
Coverage.statementStart(10715)
                        for (i in 0 until 2) {
Coverage.forLoopStart(10716)
                            while (head[i]!! < head[1 - i]!!) {
Coverage.whileLoopStart(10717)
                                change = true
Coverage.statementStart(10718)
                                head[i] = child[i].next()
Coverage.statementStart(10719)
                                if (head[i] == null) {
Coverage.ifStart(10720)
                                    iterator.close()
Coverage.statementStart(10721)
                                    value = null
Coverage.statementStart(10722)
                                    done = true
Coverage.statementStart(10723)
                                    break@loop
                                }
Coverage.statementStart(10724)
                            }
Coverage.statementStart(10725)
                        }
Coverage.statementStart(10726)
                    }
Coverage.statementStart(10727)
                    if (!done) {
Coverage.ifStart(10728)
                        value = head[0]
Coverage.statementStart(10729)
                        for (i in 0 until 2) {
Coverage.forLoopStart(10730)
                            count[i] = 0
Coverage.statementStart(10731)
                            while (head[i] == value) {
Coverage.whileLoopStart(10732)
                                count[i]++
Coverage.statementStart(10733)
                                head[i] = child[i].next()
Coverage.statementStart(10734)
                            }
Coverage.statementStart(10735)
                        }
Coverage.statementStart(10736)
                        counter = count[0] * count[1]
Coverage.statementStart(10737)
                        if (head[0] == null || head[1] == null) {
Coverage.ifStart(10738)
                            if (counter == 0) {
Coverage.ifStart(10739)
                                iterator.close()
Coverage.statementStart(10740)
                            } else {
Coverage.ifStart(10741)
                                iterator.next = {
Coverage.statementStart(10742)
                                    if (counter == 0) {
Coverage.ifStart(10743)
                                        iterator.close()
Coverage.statementStart(10744)
                                        value = null
Coverage.statementStart(10745)
                                    } else {
Coverage.ifStart(10746)
                                        counter--
Coverage.statementStart(10747)
                                    }
Coverage.statementStart(10748)
                                    /*return*/ value
                                }
Coverage.statementStart(10749)
                            }
Coverage.statementStart(10750)
                        }
Coverage.statementStart(10751)
                    }
Coverage.statementStart(10752)
                }
Coverage.statementStart(10753)
                counter--
Coverage.statementStart(10754)
                /*return*/ value
            }
Coverage.statementStart(10755)
        }
Coverage.statementStart(10756)
        return IteratorBundle(outMap)
    }
    override fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP() = POPJoinMergeSingleColumn(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
}
