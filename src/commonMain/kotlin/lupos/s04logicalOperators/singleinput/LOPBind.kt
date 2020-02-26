package lupos.s04logicalOperators.singleinput

import lupos.s00misc.EOperatorID
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.noinput.OPNothing


class LOPBind : LOPBase {
    override val operatorID = EOperatorID.LOPBindID
    override val classname = "LOPBind"
    override val children: Array<OPBase> = arrayOf(OPNothing(), OPNothing())
    val name: AOPVariable

    constructor(name: AOPVariable, expression: OPBase) : super() {
        this.name = name
        children[1] = expression
    }

    constructor(name: AOPVariable, expression: OPBase, child: OPBase) : this(name, expression) {
        children[0] = child
    }

    override fun childrenToVerifyCount(): Int = 1

    override fun getProvidedVariableNames() = (children[0].getProvidedVariableNames() + name.name).distinct().toMutableList()

    override fun toXMLElement() = super.toXMLElement().addAttribute("name", name.name)

    override fun equals(other: Any?): Boolean {
        if (other !is LOPBind)
            return false
        if (!name.equals(other.name))
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }
}
