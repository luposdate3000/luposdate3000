package lupos.s04logicalOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LOPJoin(query: Query, first: OPBase, second: OPBase, @JvmField val optional: Boolean) : LOPBase(query, EOperatorID.LOPJoinID, "LOPJoin", arrayOf(first, second), ESortPriority.JOIN) {
    override fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun equals(other: Any?): Boolean {
        if (other !is LOPJoin) {
            return false
        }
        if (optional != other.optional) {
            return false
        }
        for (i in children.indices) {
            if (children[i] != other.children[i]) {
                return false
            }
        }
        return true
    }

    override fun cloneOP() = LOPJoin(query, children[0].cloneOP(), children[1].cloneOP(), optional)

    companion object {
        fun getColumns(columnsA: List<String>, columnsB: List<String>): Array<MutableList<String>/*0:Join,1:AOnly,2:BOnly*/> {
            val res = Array(3) { mutableListOf<String>() }
            res[2].addAll(columnsB)
            for (name in columnsA) {
                if (res[2].contains(name)) {
                    res[0].add(name)
                    res[2].remove(name)
                } else {
                    res[1].add(name)
                }
            }
            return res
        }
    }
}
