package lupos.s09physicalOperators.singleinput
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s03resultRepresentation.ResultSetDictionary

import lupos.s00misc.kotlinStacktrace
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.noinput.POPExpression
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.POPBaseNullableIterator
import lupos.s09physicalOperators.singleinput.POPBind


class POPGroup : POPBaseNullableIterator {
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    private var data: MutableList<ResultRow>? = null
    private val resultSetOld: ResultSet
    private val resultSetNew: ResultSet
    private var iterator: Iterator<ResultRow>? = null
    var by: List<LOPVariable>
    var bindings = mutableListOf<Pair<Variable, POPExpression>>()

    constructor(dictionary: ResultSetDictionary, by: List<LOPVariable>, bindings: POPBind?, child: OPBase) : super() {
        this.dictionary = dictionary
        resultSetNew = ResultSet(dictionary)
        children[0] = child
        this.by = by
        resultSetOld = children[0].getResultSet()
        require(resultSetOld.dictionary == dictionary || (!(this.children[0] is POPBase)))
        var tmpBind: OPBase? = bindings
        while (tmpBind != null && tmpBind is POPBind) {
            this.bindings.add(Pair(resultSetNew.createVariable(tmpBind.name.name), tmpBind.expression))
            resultSetNew.createVariable(tmpBind.name.name)
            tmpBind = tmpBind.children[0]
        }
        this.bindings = this.bindings.asReversed()
        for (v in by)
            resultSetNew.createVariable(v.name)
    }

    override fun getProvidedVariableNames(): List<String> {
        val tmp = mutableListOf<String>()
        for (v in by)
            tmp.add(v.name)
        for (v in bindings)
            tmp.add(resultSetNew.getVariable(v.first))
        return tmp
    }

    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {
        require(additionalProvided.isEmpty())
        val localProvide = additionalProvided + children[0].getProvidedVariableNames()
        val localRequire = mutableListOf<String>()
        for (v in by)
            localRequire.add(v.name)
        for (b in bindings)
            localRequire += b.second.getRequiredVariableNames()
        for (c in children)
            c.syntaxVerifyAllVariableExists(localProvide, autocorrect)
        val res = localProvide.containsAll(localRequire)
        if (!res) {
            if (autocorrect) {
                syntaxVerifyAllVariableExistsAutocorrect()
            } else {
                throw Exception("undefined Variable")
            }
        }
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun nnext(): ResultRow? {
        try {
            Trace.start("POPGroup.nnext")
            if (data == null) {
                val tmpMutableMap = mutableMapOf<String, MutableList<ResultRow>>()
                val variables = mutableListOf<Pair<Variable, Variable>>()
                for (v in by)
                    variables.add(Pair(resultSetNew.createVariable(v.name), resultSetOld.createVariable(v.name)))
                while (children[0].hasNext()) {
                    val rsOld = children[0].next()
                    var key: String = "|"
                    for (variable in variables)
                        key = key + resultSetOld.getValue(rsOld[variable.second]) + "|"
                    var tmp = tmpMutableMap[key]
                    if (tmp == null) {
                        tmp = mutableListOf<ResultRow>()
                        tmpMutableMap[key] = tmp
                    }
                    tmp.add(rsOld)
                }
                data = mutableListOf<ResultRow>()
                if (tmpMutableMap.keys.size == 0) {
                    val rsNew = resultSetNew.createResultRow()
                    for (b in variables)
                        resultSetNew.setUndefValue(rsNew, b.first)
                    for (b in bindings)
                        resultSetNew.setUndefValue(rsNew, b.first)
                    data!!.add(rsNew)
                }
                for (k in tmpMutableMap.keys) {
                    val rsOld = tmpMutableMap[k]!!.first()
                    val rsNew = resultSetNew.createResultRow()
                    for (variable in variables)
                        rsNew[variable.first] = rsOld[variable.second]
                    for (b in bindings) {
                        try {
                            val value = b.second.evaluate(resultSetOld, tmpMutableMap[k]!!)
                            if (value == null)
                                resultSetNew.setUndefValue(rsNew, b.first)
                            else
                                rsNew[b.first] = resultSetNew.createValue(value)
                        } catch (e: Throwable) {
                            resultSetNew.setUndefValue(rsNew, b.first)
                            print("silent :: ")
                            e.kotlinStacktrace()
                        }
                    }
                    data!!.add(rsNew)
                }
                reset()
            }
            if (iterator == null || !iterator!!.hasNext())
                return null
            return iterator!!.next()
        } finally {
            Trace.stop("POPGroup.nnext")
        }
    }

    fun reset() {
        iterator = data!!.listIterator()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPGroup")
        val byxml = XMLElement("by")
        res.addContent(byxml)
        for (b in by)
            byxml.addContent(XMLElement("variable").addAttribute("name", b.name))
        val xmlbindings = XMLElement("bindings")
        res.addContent(xmlbindings)
        for (b in bindings) {
            xmlbindings.addContent(XMLElement("binding").addAttribute("name", resultSetNew.getVariable(b.first)).addContent(b.second.toXMLElement()))
        }
        res.addContent(childrenToXML())
        return res
    }
}
