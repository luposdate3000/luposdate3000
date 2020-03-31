package lupos.s04logicalOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LOPServiceIRI(query: Query, @JvmField val name: String, @JvmField val silent: Boolean, child: OPBase) : LOPBase(query, EOperatorID.LOPServiceIRIID, "LOPServiceIRI", arrayOf(child)) {
    override fun toXMLElement() = super.toXMLElement().addAttribute("name", name).addAttribute("silent", "" + silent)
    override fun equals(other: Any?): Boolean {
        if (other !is LOPServiceIRI) {
            return false
        }
        if (name != other.name) {
            return false
        }
        if (silent != other.silent) {
            return false
        }
        for (i in children.indices) {
            if (children[i] != other.children[i]) {
                return false
            }
        }
        return true
    }

    override fun cloneOP() = LOPServiceIRI(query, name, silent, children[0].cloneOP())
}
