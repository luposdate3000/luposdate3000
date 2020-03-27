package lupos.s04logicalOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query


class LOPGroup(query: Query, @JvmField var by: List<AOPVariable>) : LOPBase(query, EOperatorID.LOPGroupID, "LOPGroup", arrayOf(OPNothing(query), OPNothing(query))) {
    override fun childrenToVerifyCount() = 1

    constructor(query: Query, by: List<AOPVariable>, child: OPBase) : this(query, by) {
        children[0] = child
    }

    constructor(query: Query, by: List<AOPVariable>, bindings: OPBase?, child: OPBase) : this(query, by) {
        if (bindings != null)
            children[1] = bindings
        children[0] = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return (children[1].getProvidedVariableNames() + Array(by.size) { by[it].name }).distinct()
    }

    override fun getRequiredVariableNames(): List<String> {
        val res = mutableListOf<String>()
        res.addAll(children[1].getRequiredVariableNamesRecoursive())
        for (b in by)
            res.addAll(b.getRequiredVariableNames())
        return res.distinct()
    }

    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {
        SanityCheck.check({ additionalProvided.isEmpty() })
        val localProvide = additionalProvided + children[0].getProvidedVariableNames()
        val localRequire = mutableListOf<String>()
        for (v in by)
            localRequire.add(v.name)
        localRequire += children[1].getRequiredVariableNames()
        children[0].syntaxVerifyAllVariableExists(localProvide, autocorrect)
        val res = localProvide.containsAll(localRequire)
        if (!res) {
            if (autocorrect) {
                syntaxVerifyAllVariableExistsAutocorrect()
            } else {
                throw Exception("$classname undefined Variable")
            }
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPGroup")
        val byxml = XMLElement("LocalBy")
        res.addContent(byxml)
        for (b in by)
            byxml.addContent(XMLElement("LocalVariable").addAttribute("name", b.name))
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPGroup)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun cloneOP() = LOPGroup(query, by, children[1].cloneOP(), children[0].cloneOP())
}
