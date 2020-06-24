package lupos.s04logicalOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.GroupByColumnMissing
import lupos.s00misc.SanityCheck
import lupos.s00misc.VariableNotDefinedSyntaxException
import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LOPGroup(query: Query, @JvmField var by: List<AOPVariable>) : LOPBase(query, EOperatorID.LOPGroupID, "LOPGroup", arrayOf(OPEmptyRow(query)), ESortPriority.PREVENT_ANY) {
    override fun childrenToVerifyCount() = 1
    var bindings = mutableListOf<Pair<String, AOPBase>>()

    constructor(query: Query, by: List<AOPVariable>, bindings: List<Pair<String, AOPBase>>, child: OPBase) : this(query, by) {
        this.bindings = bindings.toMutableList()
        children[0] = child
    }

    constructor(query: Query, by: List<AOPVariable>, bindings: OPBase?, child: OPBase) : this(query, by) {
        var b = bindings
        while (b != null) {
            if (b is LOPBind) {
                this.bindings.add(Pair(b.name.name, b.children[1] as AOPBase))
                b = b.children[0]
            } else {
                SanityCheck.check { b is OPEmptyRow }
                break
            }
        }
        this.bindings = this.bindings.asReversed()
        children[0] = child
    }

    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {
        children[0].syntaxVerifyAllVariableExists(additionalProvided, autocorrect)
        SanityCheck.check({ additionalProvided.isEmpty() })
        val localProvide = additionalProvided + children[0].getProvidedVariableNames()
        val localRequire = mutableListOf<String>()
        for (v in by) {
            localRequire.add(v.name)
        }
        for (b in bindings) {
            localRequire += b.second.getRequiredVariableNamesRecoursive()
        }
        if (!localProvide.containsAll(localRequire)) {
            if (autocorrect) {
                for (name in localRequire) {
                    var found = false
                    for (prov in localProvide) {
                        if (prov == name) {
                            found = true
                            break
                        }
                    }
                    if (!found) {
                        for (b in by) {
                            if (b.name == name) {
                                throw GroupByColumnMissing(name)
                            }
                        }
                        for (b in bindings.indices) {
                            bindings[b] = Pair(bindings[b].first, replaceVariableWithUndef(bindings[b].second, name) as AOPBase)
                        }
                    }
                }
            } else {
                var tmp = localRequire.toMutableSet()
                tmp.removeAll(localProvide)
                if (tmp.size == 1) {
                    throw VariableNotDefinedSyntaxException(classname, tmp.first())
                } else {
                    throw VariableNotDefinedSyntaxException(classname, tmp.toString())
                }
            }
        }
    }

    override fun getProvidedVariableNames(): List<String> {
        return (bindings.map { it.first } + Array(by.size) { by[it].name }).distinct()
    }

    override fun getRequiredVariableNames(): List<String> {
        val res = mutableListOf<String>()
        for (b in bindings) {
            res.addAll(b.second.getRequiredVariableNamesRecoursive())
        }
        for (b in by) {
            res.addAll(b.getRequiredVariableNames())
        }
        return res.distinct()
    }

    override fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        val byxml = XMLElement("LocalBy")
        res.addContent(byxml)
        for (b in by) {
            byxml.addContent(XMLElement("LocalVariable").addAttribute("name", b.name))
        }
        val bindingsxml = XMLElement("LocalBindings")
        res.addContent(bindingsxml)
        for (b in bindings) {
            bindingsxml.addContent(XMLElement("Binding").addAttribute("name", b.first).addContent(b.second.toXMLElement()))
        }
        return res
    }

    override fun equals(other: Any?) = other is LOPGroup && children[0] == other.children[0] && by == other.by && bindings == other.bindings
    override fun cloneOP() = LOPGroup(query, by, bindings.map { it }, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
        var res = HistogramResult()
        for (v in getProvidedVariableNames()) {
            res.values[v] = 1
        }
        res.count = 1
        return res
    }
}
