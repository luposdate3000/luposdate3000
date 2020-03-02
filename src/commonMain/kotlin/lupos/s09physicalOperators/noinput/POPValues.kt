package lupos.s09physicalOperators.noinput

import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPValues : POPBase {
    override val operatorID = EOperatorID.POPValuesID
    override val classname = "POPValues"
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf()
    val variables = mutableListOf<Variable>()
    val stringVars = mutableListOf<String>()
    val data = mutableListOf<Map<Variable, Value>>()

override fun toSparql():String{
var res="VALUES("
for(v in stringVars)
res+=AOPVariable(v).toSparql()+" "
res+=") {"
for (m in data){
res+="("
for(v in variables){
val s=m[v]
if(s==null)
res+="UNDEF "
else
res+=resultSet.getValue(s)+" "
}
res+=")"
}
res+="}"
return res
}

    override fun equals(other: Any?): Boolean {
        if (other !is POPValues)
            return false
        if (dictionary !== other.dictionary)
            return false
        if (!data.equals(other.data))
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    override fun cloneOP() = POPValues(dictionary, variables.map { resultSet.getVariable(it) }, data.map {
        val res = mutableMapOf<String, String?>()
        for ((k, v) in (it as Map<Variable, Value>))
            res[resultSet.getVariable(k as Variable) as String] = resultSet.getValue(v as Value) as String?
        res
    })

    constructor(dictionary: ResultSetDictionary, v: List<String>, d: List<Map<String, String?>>) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        v.forEach {
            stringVars.add(it)
            variables.add(resultSet.createVariable(it))
        }
        d.forEach {
            val entry = mutableMapOf<Variable, Value>()
            data.add(entry)
            for ((k, v) in it) {
                if (v == null)
                    resultSet.dictionary.undefValue
                else
                    entry[resultSet.createVariable(k)] = resultSet.createValue(v)
            }
        }
    }

    constructor(dictionary: ResultSetDictionary, values: LOPValues) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        val rr = resultSet.createResultRow()
        for (name in values.variables) {
            stringVars.add(name.name)
            variables.add(resultSet.createVariable(name.name))
        }
        for (v in values.children) {
            require(v is AOPValue)
            val it = v.children.iterator()
            val entry = mutableMapOf<Variable, Value>()
            data.add(entry)
            for (v2 in variables) {
                entry[v2] = resultSet.createValue((it.next() as AOPConstant).valueToString())
            }
        }
    }

    override fun getProvidedVariableNames() = stringVars.distinct()

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf()
    }

    override fun evaluate() = Trace.trace<Channel<ResultRow>>({ "POPValues.evaluate" }, {
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                for (rsOld in data) {
                    var rsNew = resultSet.createResultRow()
                    for ((variable, value) in rsOld)
                        rsNew[variable] = value
                    channel.send(resultFlowProduce({ this@POPValues }, { rsNew }))
                }
                channel.close()
            } catch (e: Throwable) {
                channel.close(e)
            }
        }
        return channel
    })

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPValues")
        val xmlvariables = XMLElement("variables")
        res.addContent(xmlvariables)
        val bindings = XMLElement("bindings")
        res.addContent(bindings)
        for (v in variables)
            xmlvariables.addContent(XMLElement("variable").addAttribute("name", resultSet.getVariable(v)))
        for (d in data) {
            val b = XMLElement("binding")
            bindings.addContent(b)
            for ((k, v) in d) {
                val value = resultSet.getValue(v)
                if (value != null)
                    b.addContent(XMLElement("value").addAttribute("name", resultSet.getVariable(k)).addAttribute("content", value))
            }
        }
        return res
    }
}
