package lupos.s2buildOperatorGraph

import lupos.misc.XMLElement


abstract class OPBase {

    open fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n"

    companion object {
        var global_uuid = 0
    }

    val uuid = global_uuid++

    override fun toString(): String = toXMLElement().toPrettyString()
    abstract fun getRequiredVariableNames(): List<String>
    abstract fun getProvidedVariableNames(): List<String>
    abstract fun toXMLElement(): XMLElement
}
