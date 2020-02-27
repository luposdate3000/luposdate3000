package lupos.s04logicalOperators.singleinput.modifiers
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase



class LOPDistinct() : LOPBase() {
    override val operatorID = EOperatorID.LOPDistinctID
    override val classname = "LOPDistinct"
    override val children: Array<OPBase> = arrayOf(OPNothing())

    constructor(child: OPBase) : this() {
        this.children[0] = child
    }


    override fun equals(other: Any?): Boolean {
        if (other !is LOPDistinct)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }
}
