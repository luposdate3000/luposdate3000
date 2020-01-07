package lupos.s2buildOperatorGraph.singleinput.modifiers

import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.singleinput.LOPSingleInputBase

class LOPPrefix(val name: String, val iri: String) : LOPSingleInputBase() {
    constructor(name: String, iri: String, child: OPBase) : this(name, iri) {
        this.child = child
    }

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName} '${name}' '${iri}'\n" + child.toString("${indentation}\t")
}