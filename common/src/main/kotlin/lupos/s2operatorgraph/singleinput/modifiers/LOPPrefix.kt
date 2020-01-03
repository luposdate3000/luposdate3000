package lupos.s2operatorgraph.singleinput.modifiers

import lupos.s2operatorgraph.OPBase
import lupos.s2operatorgraph.singleinput.LOPSingleInputBase

class LOPPrefix(val name: String, val iri: String) : LOPSingleInputBase() {
    constructor(name: String, iri: String, child: OPBase) : this(name, iri) {
        this.child = child
    }

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName} '${name}' '${iri}'\n" + child.toString("${indentation}\t")
}