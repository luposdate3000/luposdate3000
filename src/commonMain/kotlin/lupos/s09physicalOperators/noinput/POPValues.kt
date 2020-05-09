package lupos.s09physicalOperators.noinput
import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.DynamicByteArray
import lupos.s00misc.EBenchmark
import lupos.s00misc.EGraphRefType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.File
import lupos.s00misc.JenaWrapper
import lupos.s00misc.MemoryStatistics
import lupos.s00misc.MyListDouble
import lupos.s00misc.MyListGeneric
import lupos.s00misc.MyListInt
import lupos.s00misc.MyMapDoubleInt
import lupos.s00misc.MyMapIntGeneric
import lupos.s00misc.MyMapIntInt
import lupos.s00misc.MyMapLongGeneric
import lupos.s00misc.MyMapLongInt
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.MyMapStringIntPatriciaTrieDouble
import lupos.s00misc.MySetInt
import lupos.s00misc.OperatorGraphToLatex
import lupos.s00misc.parseFromXml
import lupos.s00misc.SanityCheck
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallSTRUUID
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallUUID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorAggregate
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorDistinct
import lupos.s04logicalOperators.iterator.ColumnIteratorMergeSort
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.ColumnIteratorQueue
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.iterator.ColumnIteratorStore1
import lupos.s04logicalOperators.iterator.ColumnIteratorStore2a
import lupos.s04logicalOperators.iterator.ColumnIteratorStore2b
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3a
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3b
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3c
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase




open class POPValues : POPBase {
    @JvmField
    val variables: List<String>
    @JvmField
    val data: Map<String, MyListValue>

    override fun toSparql(): String {
        var res = "VALUES("
        for (v in variables) {
            res += v + " "
        }
        res += ") {"
        var columns = Array(variables.size) { data[variables[it]] }
        for (i in 0 until columns[0]!!.size) {
            res += "("
            for (v in 0 until variables.size) {
                if (columns[v]!![i] == ResultSetDictionary.undefValue) {
                    res += "UNDEF "
                } else {
                    res += query.dictionary.getValue(columns[v]!![i]).valueToString() + " "
                }
            }
            res += ")"
        }
        res += "}"
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is POPValues) {
            return false
        }
        if (variables.size != other.variables.size) {
            return false
        }
        for (v in variables) {
            if (!other.variables.contains(v)) {
                return false
            }
            if (data[v]!!.size != other.data[v]!!.size) {
                return false
            }
            var columns1 = Array(variables.size) { data[variables[it]] }
            var columns2 = Array(variables.size) { other.data[variables[it]] }
            for (vIndex in 0 until variables.size) {
                for (i in 0 until columns1[0]!!.size) {
                    if (columns1[vIndex]!![i] != columns2[vIndex]!![i]) {
                        return false
                    }
                }
            }
        }
        return true
    }

    override fun cloneOP() = POPValues(query, projectedVariables, variables, data)

    constructor(query: Query, projectedVariables: List<String>, v: List<String>, d: MutableList<List<String?>>) : super(query, projectedVariables, EOperatorID.POPValuesID, "POPValues", arrayOf(), ESortPriority.PREVENT_ANY) {
        variables = v
        var columns = Array(variables.size) { MyListValue() }
        data = mutableMapOf<String, MyListValue>()
        for (variableIndex in 0 until variables.size) {
            data[variables[variableIndex]] = columns[variableIndex]
        }
        d.forEach {
            for (variableIndex in 0 until variables.size) {
                columns[variableIndex].add(query.dictionary.createValue(it[variableIndex]))
            }
        }
    }

    constructor(query: Query, projectedVariables: List<String>, v: List<String>, d: Map<String, MyListValue>) : super(query, projectedVariables, EOperatorID.POPValuesID, "POPValues", arrayOf(), ESortPriority.PREVENT_ANY) {
        variables = v
        data = d
    }

    constructor(query: Query, projectedVariables: List<String>, values: LOPValues) : super(query, projectedVariables, EOperatorID.POPValuesID, "POPValues", arrayOf(), ESortPriority.PREVENT_ANY) {
        val tmpVariables = mutableListOf<String>()
        for (name in values.variables) {
            tmpVariables.add(name.name)
        }
        variables = tmpVariables
        var columns = Array(variables.size) { MyListValue() }
        data = mutableMapOf<String, MyListValue>()
        for (variableIndex in 0 until variables.size) {
            data[variables[variableIndex]] = columns[variableIndex]
        }
        for (v in values.children) {
            SanityCheck.check({ v is AOPValue })
            val it = v.children.iterator()
            for (variableIndex in 0 until variables.size) {
                columns[variableIndex].add((it.next() as AOPConstant).value)
            }
        }
    }

    override fun getProvidedVariableNamesInternal() = variables.distinct()
    override fun getRequiredVariableNames() = mutableListOf<String>()
    override suspend fun evaluate(): ColumnIteratorRow {
        val outMap = mutableMapOf<String, ColumnIterator>()
        for (name in variables) {
            val tmp = ColumnIteratorMultiValue(data[name]!!)
            tmp.close = {
                tmp._close()
                for (variable in variables) {
                    outMap[variable]!!.close()
                }
            }
            outMap[name] = ColumnIteratorDebug(uuid, name, tmp)
        }
        return ColumnIteratorRow(outMap)
    }

    override fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        val xmlvariables = XMLElement("variables")
        res.addContent(xmlvariables)
        val bindings = XMLElement("bindings")
        res.addContent(bindings)
        for (variable in variables) {
            xmlvariables.addContent(XMLElement("variable").addAttribute("name", variable))
        }
        var columns = Array(variables.size) { data[variables[it]] }
        if (columns.size > 0) {
            for (i in 0 until columns[0]!!.size) {
                val b = XMLElement("binding")
                bindings.addContent(b)
                for (variableIndex in 0 until variables.size) {
                    val value = query.dictionary.getValue(columns[variableIndex]!![i]).valueToString()
                    if (value != null) {
                        b.addContent(XMLElement("value").addAttribute("name", variables[variableIndex]).addAttribute("content", value))
                    } else {
                        b.addContent(XMLElement("value").addAttribute("name", variables[variableIndex]))
                    }
                }
            }
        }
        return res
    }
}
