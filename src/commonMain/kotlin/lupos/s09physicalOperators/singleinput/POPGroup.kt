package lupos.s09physicalOperators.singleinput
import lupos.s00misc.kotlinStacktrace
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.LOPExpression
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPExpression
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.POPBaseNullableIterator
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPBindUndefined
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPFilterExact
import lupos.s09physicalOperators.singleinput.POPSingleInputBaseNullableIterator



class POPGroup : POPSingleInputBaseNullableIterator {
    private var data: MutableList<ResultRow>? = null
    private val resultSetOld: ResultSet
    private val resultSetNew = ResultSet()
    private var iterator: Iterator<ResultRow>? = null
    var by: List<LOPVariable>
    var bindings = mutableListOf<Pair<Variable, POPExpression>>()

    constructor(by: List<LOPVariable>, bindings: POPBind?, child: OPBase) : super(child) {
        this.by = by
        resultSetOld = child.getResultSet()
        var tmpBind: OPBase? = bindings
        while (tmpBind != null && tmpBind is POPBind) {
            this.bindings.add(Pair(resultSetNew.createVariable(tmpBind.name.name), tmpBind.expression))
            resultSetNew.createVariable(tmpBind.name.name)
            tmpBind = tmpBind.child
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
            tmp.addAll(v.second.getProvidedVariableNames())
        return tmp
    }

    override fun getRequiredVariableNames(): List<String> {
        val tmp = mutableListOf<String>()
        for (v in by)
            tmp.add(v.name)
        for (v in bindings)
            tmp.addAll(v.second.getRequiredVariableNames())
        return tmp
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
                while (child.hasNext()) {
                    val rsOld = child.next()
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
                        rsNew[b.first] = resultSetNew.createValue(resultSetNew.getUndefValue())
                    for (b in bindings)
                        rsNew[b.first] = resultSetNew.createValue(resultSetNew.getUndefValue())
                    data!!.add(rsNew)
                }
                for (k in tmpMutableMap.keys) {
                    val rsOld = tmpMutableMap[k]!!.first()
                    val rsNew = resultSetNew.createResultRow()
                    for (variable in variables)
                        rsNew[variable.first] = resultSetNew.createValue(resultSetOld.getValue(rsOld[variable.second]))
                    for (b in bindings) {
                        try {
                            rsNew[b.first] = resultSetNew.createValue(b.second.evaluate(resultSetOld, tmpMutableMap[k]!!))
                        } catch (e: Throwable) {
                            rsNew[b.first] = resultSetNew.createValue(resultSetNew.getUndefValue())
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
        res.addContent(XMLElement("child").addContent(child.toXMLElement()))
        return res
    }
}
