package lupos.s04logicalOperators.singleinput
import lupos.s00misc.EOperatorID

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPServiceVAR : LOPBase {
    override val operatorID=EOperatorID.LOPServiceVARID
    override val classname = "LOPServiceVAR"
    override val children: Array<OPBase> = arrayOf(OPNothing(), OPNothing())
    val name: String
    val silent: Boolean

    constructor(name: String, silent: Boolean, constraint: OPBase) : super() {
        this.name = name
        this.silent = silent
        children[1] = constraint
    }

    constructor(name: String, silent: Boolean, constraint: OPBase, child: OPBase) : this(name, silent, constraint) {
        this.children[0] = child
    }


    override fun toXMLElement() = super.toXMLElement().addAttribute("name", name).addAttribute("silent", "" + silent).addContent(XMLElement("constraint").addContent(children[1].toXMLElement()))

    override fun equals(other: Any?): Boolean {
        if (other !is LOPServiceVAR)
            return false
        if (name != other.name)
            return false
        if (silent != other.silent)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }
}
