package lupos.s04logicalOperators

import kotlinx.coroutines.channels.Channel
import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.ASTUndef
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.singleinput.LOPBind


abstract class OPBase {
    abstract val operatorID: EOperatorID
    abstract val classname: String
    val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
    abstract val resultSet: ResultSet
    abstract val children: Array<OPBase>

    open fun applyPrefix(prefix: String, iri: String) {
        for (c in children)
            c.applyPrefix(prefix, iri)
    }

    open fun childrenToVerifyCount(): Int = children.size

    open fun updateChildren(i: Int, child: OPBase) {
        require(i < children.size)
        children[i] = child
    }

    abstract fun evaluate()

    open fun toString(indentation: String): String = "${indentation}${classNameToString(this)}\n"

    companion object {
        private val global_uuid = ThreadSafeUuid()
    }

    val uuid: Long = global_uuid.next()

    override fun toString(): String = toXMLElement().toPrettyString()

    fun getRequiredVariableNamesRecoursive(): List<String> {
        var res = getRequiredVariableNames()
        for (c in children)
            res += c.getRequiredVariableNamesRecoursive()
        return res.distinct()
    }

    open fun getRequiredVariableNames(): List<String> {
        val res = mutableListOf<String>()
        println("($classname)($uuid)getRequiredVariableNames $res")
        return res
    }

    open fun getProvidedVariableNames(): List<String> {
        val res = mutableListOf<String>()
        for (c in children)
            res.addAll(c.getProvidedVariableNames())
        println("($classname)($uuid)getProvidedVariableNames ${res.distinct()}")
        return res.distinct()
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
                children[0] = LOPBind(AOPVariable(req), AOPUndef(), children[0])
            }
        }
    }

    open fun syntaxVerifyAllVariableExists(additionalProvided: List<String> = listOf<String>(), autocorrect: Boolean = false) {
        for (i in 0 until childrenToVerifyCount())
            children[i].syntaxVerifyAllVariableExists(additionalProvided, autocorrect)
        val res = (additionalProvided + getProvidedVariableNames()).containsAll(getRequiredVariableNames())
        if (!res) {
            if (autocorrect) {
                syntaxVerifyAllVariableExistsAutocorrect()
            } else {
                println(this.toXMLElement().toPrettyString())
                throw Exception("${classNameToString(this)} undefined Variable")
            }
        }
    }

    fun setChild(child: OPBase): OPBase {
        require(children.size > 0)
        this.children[0] = child
        return child
    }

    fun getLatestChild(): OPBase {
        if (children.size > 0 && children[0].children.size > 0)
            return children[0].getLatestChild()
        return this
    }
}
