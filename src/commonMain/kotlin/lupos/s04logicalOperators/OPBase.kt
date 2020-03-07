package lupos.s04logicalOperators
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.noinput.AOPUndef
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.singleinput.LOPBind


abstract class OPBase (val query:Query,val operatorID: EOperatorID,val classname: String, val resultSet: ResultSet,val children: Array<OPBase>){

    abstract fun evaluate(): Channel<ResultRow>
    abstract fun cloneOP(): OPBase

    open fun applyPrefix(prefix: String, iri: String) {
        for (c in children)
            c.applyPrefix(prefix, iri)
    }

    open fun childrenToVerifyCount(): Int = children.size

    open fun updateChildren(i: Int, child: OPBase) {
        require(i < children.size)
        children[i] = child
    }

    open fun toString(indentation: String): String = "${indentation}${classNameToString(this)}\n"

    companion object {
        private val global_uuid = ThreadSafeUuid()
    }

    @JvmField
    val uuid: Long = global_uuid.next()

    override fun toString(): String = toXMLElement().toPrettyString()

    fun getRequiredVariableNamesRecoursive(): List<String> {
        val res = getRequiredVariableNames().toMutableList()
        for (c in children)
            res += c.getRequiredVariableNamesRecoursive()
        return res.distinct()
    }

    open fun getRequiredVariableNames(): List<String> {
        return mutableListOf()
    }

    open fun getProvidedVariableNames(): List<String> {
        val res = mutableListOf<String>()
        for (c in children)
            res.addAll(c.getProvidedVariableNames())
        return res.distinct()
    }

    open fun toSparqlQuery(): String {
        return "SELECT * WHERE{" + toSparql() + "}"
    }

    open fun toSparql(): String {
        throw Exception("not implemented $classname.toSparql()")
    }

    open fun toXMLElement(): XMLElement {
        val res = XMLElement(classname)
        res.addAttribute("uuid", "" + uuid)
        res.addContent(childrenToXML())
        return res
    }

    fun childrenToXML(): XMLElement {
        val res = XMLElement("children")
        for (c in children)
            res.addContent(c.toXMLElement())
        return res
    }

    fun syntaxVerifyAllVariableExistsAutocorrect() {
        for (req in getRequiredVariableNames()) {
            var found = false
            for (prov in getProvidedVariableNames()) {
                if (prov == req) {
                    found = true
                    break
                }
            }
            if (!found) {
                children[0] = LOPBind(query,AOPVariable(query,req), AOPUndef(query), children[0])
            }
        }
    }

    open fun syntaxVerifyAllVariableExists(additionalProvided: List<String> = listOf(), autocorrect: Boolean = false) {
        for (i in 0 until childrenToVerifyCount())
            children[i].syntaxVerifyAllVariableExists(additionalProvided, autocorrect)
        val res = (additionalProvided + getProvidedVariableNames()).containsAll(getRequiredVariableNames())
        if (!res) {
            if (autocorrect) {
                syntaxVerifyAllVariableExistsAutocorrect()
            } else {
                throw Exception("${classNameToString(this)} undefined Variable")
            }
        }
    }

    fun setChild(child: OPBase): OPBase {
        require(children.isNotEmpty())
        this.children[0] = child
        return child
    }

    fun getLatestChild(): OPBase {
        if (children.isNotEmpty() && children[0].children.isNotEmpty())
            return children[0].getLatestChild()
        return this
    }
}
