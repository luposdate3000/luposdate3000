package lupos.s2operatorgraph.singleinput

import lupos.s2operatorgraph.OPBase

class LOPSort(val asc: Boolean, var by: OPBase) : LOPSingleInputBase() {
    constructor(asc: Boolean, by: OPBase, child: OPBase) : this(asc, by) {
        this.child = child
    }

    override fun toString(indentation: String): String {
        return when {
            asc -> "${indentation}${this::class.simpleName} 'ASC' '${by}'\n" + child.toString("${indentation}\t")
            else -> "${indentation}${this::class.simpleName} 'DESC' '${by}'\n" + child.toString("${indentation}\t")
        }
    }
}