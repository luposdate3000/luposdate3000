package lupos.s04logicalOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPFilter : LOPBase {
    override val classname = "LOPFilter"
    override val children: Array<OPBase> = arrayOf(OPNothing(), OPNothing())
    override fun childrenToVerifyCount() = 1

    constructor(filter: AOPBase) : super() {
        children[1] = filter
    }

    constructor(filter: AOPBase, child: OPBase) : this(filter) {
        children[0] = child
    }

    override fun getProvidedVariableNames():List<String> {
val res= children[0].getProvidedVariableNames().distinct()
println("($classname)($uuid)getProvidedVariableNames $res")
return res
}

    override fun getRequiredVariableNames():List<String>{
val res=children[1].getRequiredVariableNamesRecoursive()
println("($classname)($uuid)getRequiredVariableNames $res")
return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPFilter)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }
}
