package lupos.s03buildOperatorGraph
import lupos.s06resultRepresentation.ResultSetIterator

import lupos.s00misc.classNameToString
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.XMLElement
import lupos.s03buildOperatorGraph.LOPBase


abstract class OPBase : ResultSetIterator {

    open fun toString(indentation: String): String = "${indentation}${classNameToString(this)}\n"

    companion object {
        private val global_uuid = ThreadSafeUuid()
    }

    val uuid: Long = global_uuid.next()

    override fun toString(): String = toXMLElement().toPrettyString()
    abstract fun getRequiredVariableNames(): List<String>
    abstract fun getProvidedVariableNames(): List<String>
    abstract fun toXMLElement(): XMLElement

}
