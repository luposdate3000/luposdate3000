package lupos.s04logicalOperators

import lupos.s00misc.classNameToString
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.ASTUndef
import lupos.s03resultRepresentation.ResultSetIterator
import lupos.s04logicalOperators.noinput.LOPExpression
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.singleinput.LOPBind


abstract class OPBase : ResultSetIterator {

    abstract val children: Array<OPBase>

    open fun toString(indentation: String): String = "${indentation}${classNameToString(this)}\n"

    companion object {
        private val global_uuid = ThreadSafeUuid()
    }

    val uuid: Long = global_uuid.next()

    override fun toString(): String = toXMLElement().toPrettyString()
    abstract fun getRequiredVariableNames(): List<String>
    abstract fun getProvidedVariableNames(): List<String>
    abstract fun toXMLElement(): XMLElement
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
                children[0] = LOPBind(LOPVariable(req), LOPExpression(ASTUndef()), children[0])
            }
        }
    }

    open fun syntaxVerifyAllVariableExists(additionalProvided: List<String> = listOf<String>(), autocorrect: Boolean = false) {
        for (c in children)
            c.syntaxVerifyAllVariableExists(additionalProvided, autocorrect)
        val res = (additionalProvided + getProvidedVariableNames()).containsAll(getRequiredVariableNames())
        if (!res) {
            if (autocorrect) {
                syntaxVerifyAllVariableExistsAutocorrect()
            } else {
                throw Exception("undefined Variable")
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
