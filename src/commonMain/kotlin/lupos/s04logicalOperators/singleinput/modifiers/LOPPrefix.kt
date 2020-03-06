package lupos.s04logicalOperators.singleinput.modifiers

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPPrefix(@JvmField val name: String, @JvmField val iri: String, child: OPBase = OPNothing()) : LOPBase() {
    override val operatorID = EOperatorID.LOPPrefixID
    override val classname = "LOPPrefix"
    override val children: Array<OPBase> = arrayOf(child)

    override fun toXMLElement() = super.toXMLElement().addAttribute("name", name).addAttribute("iri", iri)

    override fun equals(other: Any?): Boolean {
        if (other !is LOPPrefix)
            return false
        if (name != other.name)
            return false
        if (iri != other.iri)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun cloneOP() = LOPPrefix(name, iri, children[0].cloneOP())
}
