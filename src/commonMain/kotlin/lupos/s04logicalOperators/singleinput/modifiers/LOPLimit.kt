package lupos.s04logicalOperators.singleinput.modifiers
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase



class LOPLimit(val limit: Int) : LOPBase() {
    override val operatorID = EOperatorID.LOPLimitID
    override val classname = "LOPLimit"
    override val children: Array<OPBase> = arrayOf(OPNothing())

    constructor(limit: Int, child: OPBase) : this(limit) {
        this.children[0] = child
    }


    override fun toXMLElement() = super.toXMLElement().addAttribute("limit", "" + limit)

    override fun equals(other: Any?): Boolean {
        if (other !is LOPLimit)
            return false
        if (limit != other.limit)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }
}
