package lupos.s2buildOperatorGraph.singleinput

import lupos.s2buildOperatorGraph.OPBase

class LOPSort(val asc: Boolean, var by: OPBase) : LOPSingleInputBase() {
    constructor(asc: Boolean, by: OPBase, child: OPBase) : this(asc, by) {
        this.child = child
    }

    override fun toString(indentation: String): String {
	var res = "${indentation}${this::class.simpleName}\n${indentation}\torder: "
	if(asc)
		res+="'ASC'"
	else
		res+="'DESC'"
	res+="\n${indentation}\tby:\n" + by.toString("${indentation}\t\t") + "${indentation}\tchild:\n" + child.toString("${indentation}\t\t")
	return res
    }
}
