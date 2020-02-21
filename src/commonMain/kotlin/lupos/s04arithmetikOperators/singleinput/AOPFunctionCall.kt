package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPFunctionCall(var iri: String, var distinct: Boolean, args: List<OPBase>) : AOPBase() {
    override val children: Array<OPBase> = Array(args.size) { args[it] }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPFunctionCall")
        res.addAttribute("iri", iri)
        res.addAttribute("distinct", "" + distinct)
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPFunctionCall)
            return false
        if (iri != other.iri)
            return false
        if (distinct != other.distinct)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        TODO("not implemented")
    }
}
