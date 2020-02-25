package lupos.s04logicalOperators.singleinput.modifiers

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPPrefix(val name: String, val iri: String) : LOPBase() {
    override val classname = "LOPPrefix"
    override val children: Array<OPBase> = arrayOf(OPNothing())

    constructor(name: String, iri: String, child: OPBase) : this(name, iri) {
        this.children[0] = child
    }


    override fun toXMLElement() = super.toXMLElement().addAttribute("name", name).addAttribute("iri", iri)

    override fun equals(other: Any?): Boolean {
        if (other !is LOPPrefix)
            return false
        if (name != other.name)
            return false
        if (iri != other.iri)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }
}
