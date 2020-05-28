package lupos.s04logicalOperators.singleinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
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
    constructor(query: Query, by: List<AOPVariable>, child: OPBase) : this(query, by) {
        children[0] = child
    }
    constructor(query: Query, by: List<AOPVariable>, bindings: List<Pair<String, AOPBase>>, child: OPBase) : this(query, by) {
        this.bindings = bindings.toMutableList()
        children[0] = child
    }
    constructor(query: Query, by: List<AOPVariable>, bindings: OPBase?, child: OPBase) : this(query, by) {
        var b = bindings
        while (b != null) {
Coverage.whileLoopStart(5093)
            if (b is LOPBind) {
Coverage.ifStart(5094)
                this.bindings.add(Pair(b.name.name, b.children[1] as AOPBase))
                b = b.children[0]
            } else {
Coverage.ifStart(5095)
                SanityCheck.check { b is OPEmptyRow }
                break
            }
        }
        this.bindings = this.bindings.asReversed()
        children[0] = child
    }
    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {
Coverage.funStart(5096)
        children[0].syntaxVerifyAllVariableExists(additionalProvided, autocorrect)
Coverage.statementStart(5097)
        SanityCheck.check({ additionalProvided.isEmpty() })
Coverage.statementStart(5098)
        val localProvide = additionalProvided + children[0].getProvidedVariableNames()
Coverage.statementStart(5099)
        val localRequire = mutableListOf<String>()
Coverage.statementStart(5100)
        for (v in by) {
Coverage.forLoopStart(5101)
            localRequire.add(v.name)
Coverage.statementStart(5102)
        }
Coverage.statementStart(5103)
        for (b in bindings) {
Coverage.forLoopStart(5104)
            localRequire += b.second.getRequiredVariableNamesRecoursive()
Coverage.statementStart(5105)
        }
Coverage.statementStart(5106)
        if (!localProvide.containsAll(localRequire)) {
Coverage.ifStart(5107)
            if (autocorrect) {
Coverage.ifStart(5108)
                for (name in localRequire) {
Coverage.forLoopStart(5109)
                    var found = false
Coverage.statementStart(5110)
                    for (prov in localProvide) {
Coverage.forLoopStart(5111)
                        if (prov == name) {
Coverage.ifStart(5112)
                            found = true
Coverage.statementStart(5113)
                            break
                        }
Coverage.statementStart(5114)
                    }
Coverage.statementStart(5115)
                    if (!found) {
Coverage.ifStart(5116)
                        for (b in by) {
Coverage.forLoopStart(5117)
                            if (b.name == name) {
Coverage.ifStart(5118)
                                throw Exception("undefined GROUP BY column >$name<")
                            }
Coverage.statementStart(5119)
                        }
Coverage.statementStart(5120)
                        for (b in bindings.indices) {
Coverage.forLoopStart(5121)
                            bindings[b] = Pair(bindings[b].first, replaceVariableWithUndef(bindings[b].second, name) as AOPBase)
Coverage.statementStart(5122)
                        }
Coverage.statementStart(5123)
                    }
Coverage.statementStart(5124)
                }
Coverage.statementStart(5125)
            } else {
Coverage.ifStart(5126)
                throw Exception("$classname undefined Variable")
            }
Coverage.statementStart(5127)
        }
Coverage.statementStart(5128)
    }
    override fun getProvidedVariableNames(): List<String> {
Coverage.funStart(5129)
        return (bindings.map { it.first } + Array(by.size) { by[it].name }).distinct()
    }
    override fun getRequiredVariableNames(): List<String> {
Coverage.funStart(5130)
        val res = mutableListOf<String>()
Coverage.statementStart(5131)
        for (b in bindings) {
Coverage.forLoopStart(5132)
            res.addAll(b.second.getRequiredVariableNamesRecoursive())
Coverage.statementStart(5133)
        }
Coverage.statementStart(5134)
        for (b in by) {
Coverage.forLoopStart(5135)
            res.addAll(b.getRequiredVariableNames())
Coverage.statementStart(5136)
        }
Coverage.statementStart(5137)
        return res.distinct()
    }
    override fun toXMLElement(): XMLElement {
Coverage.funStart(5138)
        val res = super.toXMLElement()
Coverage.statementStart(5139)
        val byxml = XMLElement("LocalBy")
Coverage.statementStart(5140)
        res.addContent(byxml)
Coverage.statementStart(5141)
        for (b in by) {
Coverage.forLoopStart(5142)
            byxml.addContent(XMLElement("LocalVariable").addAttribute("name", b.name))
Coverage.statementStart(5143)
        }
Coverage.statementStart(5144)
        val bindingsxml = XMLElement("LocalBindings")
Coverage.statementStart(5145)
        res.addContent(bindingsxml)
Coverage.statementStart(5146)
        for (b in bindings) {
Coverage.forLoopStart(5147)
            bindingsxml.addContent(XMLElement("Binding").addAttribute("name", b.first).addContent(b.second.toXMLElement()))
Coverage.statementStart(5148)
        }
Coverage.statementStart(5149)
        return res
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(5150)
        if (other !is LOPGroup) {
Coverage.ifStart(5151)
            return false
        }
Coverage.statementStart(5152)
        for (i in children.indices) {
Coverage.forLoopStart(5153)
            if (children[i] != other.children[i]) {
Coverage.ifStart(5154)
                return false
            }
Coverage.statementStart(5155)
        }
Coverage.statementStart(5156)
        for (i in by.indices) {
Coverage.forLoopStart(5157)
            if (by[i] != other.by[i]) {
Coverage.ifStart(5158)
                return false
            }
Coverage.statementStart(5159)
        }
Coverage.statementStart(5160)
        for (i in bindings.indices) {
Coverage.forLoopStart(5161)
            if (bindings[i] != other.bindings[i]) {
Coverage.ifStart(5162)
                return false
            }
Coverage.statementStart(5163)
        }
Coverage.statementStart(5164)
        return true
    }
    override fun cloneOP() = LOPGroup(query, by, bindings.map { it }, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(5165)
        var res = HistogramResult()
Coverage.statementStart(5166)
        for (v in getProvidedVariableNames()) {
Coverage.forLoopStart(5167)
            res.values[v] = 1
Coverage.statementStart(5168)
        }
Coverage.statementStart(5169)
        res.count = 1
Coverage.statementStart(5170)
        return res
    }
}
