package lupos.s2buildOperatorGraph

import lupos.misc.XMLElement
import lupos.misc.*

abstract class OPBase {

    open fun toString(indentation: String): String = "${indentation}${classNameToString(this)}\n"

companion object {
private         val global_uuid = ThreadSafeUuid()
    }

    val uuid :Long = global_uuid.next()

    override fun toString(): String = toXMLElement().toPrettyString()
    abstract fun getRequiredVariableNames(): List<String>
    abstract fun getProvidedVariableNames(): List<String>
    abstract fun toXMLElement(): XMLElement
}
