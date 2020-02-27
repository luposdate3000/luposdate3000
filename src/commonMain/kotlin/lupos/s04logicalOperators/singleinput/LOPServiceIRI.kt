package lupos.s04logicalOperators.singleinput
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase



class LOPServiceIRI(val name: String, val silent: Boolean, child: OPBase) : LOPBase() {
    override val operatorID = EOperatorID.LOPServiceIRIID
    override val classname = "LOPServiceIRI"
    override val children: Array<OPBase> = arrayOf(child)

    override fun toXMLElement() = super.toXMLElement().addAttribute("name", name).addAttribute("silent", "" + silent)

    override fun equals(other: Any?): Boolean {
        if (other !is LOPServiceIRI)
            return false
        if (name != other.name)
            return false
        if (silent != other.silent)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }
}
