package lupos.s09physicalOperators.noinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
open class POPValues : POPBase {
    @JvmField
    val variables: List<String>
    @JvmField
    val data: Map<String, MyListValue>
    @JvmField
    val rows: Int
    override fun toSparql(): String {
Coverage.funStart(11295)
        var res = "VALUES("
Coverage.statementStart(11296)
        for (v in variables) {
Coverage.forLoopStart(11297)
            res += v + " "
Coverage.statementStart(11298)
        }
Coverage.statementStart(11299)
        res += ") {"
Coverage.statementStart(11300)
        var columns = Array(variables.size) { data[variables[it]] }
Coverage.statementStart(11301)
        if (columns.size > 0) {
Coverage.ifStart(11302)
            for (i in 0 until columns[0]!!.size) {
Coverage.forLoopStart(11303)
                res += "("
Coverage.statementStart(11304)
                for (v in 0 until variables.size) {
Coverage.forLoopStart(11305)
                    if (columns[v]!![i] == ResultSetDictionary.undefValue) {
Coverage.ifStart(11306)
                        res += "UNDEF "
Coverage.statementStart(11307)
                    } else {
Coverage.ifStart(11308)
                        res += query.dictionary.getValue(columns[v]!![i]).valueToString() + " "
Coverage.statementStart(11309)
                    }
Coverage.statementStart(11310)
                }
Coverage.statementStart(11311)
                res += ")"
Coverage.statementStart(11312)
            }
Coverage.statementStart(11313)
        }
Coverage.statementStart(11314)
        res += "}"
Coverage.statementStart(11315)
        return res
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(11316)
        if (other !is POPValues) {
Coverage.ifStart(11317)
            return false
        }
Coverage.statementStart(11318)
        if (rows != other.rows) {
Coverage.ifStart(11319)
            return false
        }
Coverage.statementStart(11320)
        if (rows == -1) {
Coverage.ifStart(11321)
            if (variables.size != other.variables.size) {
Coverage.ifStart(11322)
                return false
            }
Coverage.statementStart(11323)
            for (v in variables) {
Coverage.forLoopStart(11324)
                if (!other.variables.contains(v)) {
Coverage.ifStart(11325)
                    return false
                }
Coverage.statementStart(11326)
                if (data[v]!!.size != other.data[v]!!.size) {
Coverage.ifStart(11327)
                    return false
                }
Coverage.statementStart(11328)
                var columns1 = Array(variables.size) { data[variables[it]] }
Coverage.statementStart(11329)
                var columns2 = Array(variables.size) { other.data[variables[it]] }
Coverage.statementStart(11330)
                for (vIndex in 0 until variables.size) {
Coverage.forLoopStart(11331)
                    for (i in 0 until columns1[0]!!.size) {
Coverage.forLoopStart(11332)
                        if (columns1[vIndex]!![i] != columns2[vIndex]!![i]) {
Coverage.ifStart(11333)
                            return false
                        }
Coverage.statementStart(11334)
                    }
Coverage.statementStart(11335)
                }
Coverage.statementStart(11336)
            }
Coverage.statementStart(11337)
        }
Coverage.statementStart(11338)
        return true
    }
    override fun cloneOP(): POPValues {
Coverage.funStart(11339)
        if (rows != -1) {
Coverage.ifStart(11340)
            return POPValues(query, rows)
        } else {
Coverage.statementStart(11341)
            return POPValues(query, projectedVariables, variables, data)
        }
Coverage.statementStart(11342)
    }
    constructor(query: Query, count: Int) : super(query, listOf<String>(), EOperatorID.POPValuesID, "POPValues", arrayOf(), ESortPriority.PREVENT_ANY) {
        variables = listOf<String>()
        data = mapOf<String, MyListValue>()
        rows = count
    }
    constructor(query: Query, projectedVariables: List<String>, v: List<String>, d: MutableList<List<String?>>) : super(query, projectedVariables, EOperatorID.POPValuesID, "POPValues", arrayOf(), ESortPriority.PREVENT_ANY) {
        variables = v
        var columns = Array(variables.size) { MyListValue() }
        data = mutableMapOf<String, MyListValue>()
        if (projectedVariables.size == 0) {
Coverage.ifStart(11343)
            rows = d.size
        } else {
Coverage.ifStart(11344)
            for (variableIndex in 0 until variables.size) {
Coverage.forLoopStart(11345)
                data[variables[variableIndex]] = columns[variableIndex]
            }
            d.forEach {
Coverage.forEachLoopStart(11346)
                for (variableIndex in 0 until variables.size) {
Coverage.forLoopStart(11347)
                    columns[variableIndex].add(query.dictionary.createValue(it[variableIndex]))
                }
            }
            rows = -1
        }
    }
    constructor(query: Query, projectedVariables: List<String>, v: List<String>, d: Map<String, MyListValue>) : super(query, projectedVariables, EOperatorID.POPValuesID, "POPValues", arrayOf(), ESortPriority.PREVENT_ANY) {
        variables = v
        data = d
        rows = -1
    }
    constructor(query: Query, projectedVariables: List<String>, values: LOPValues) : super(query, projectedVariables, EOperatorID.POPValuesID, "POPValues", arrayOf(), ESortPriority.PREVENT_ANY) {
        if (projectedVariables.size == 0) {
Coverage.ifStart(11348)
            variables = listOf<String>()
            data = mapOf<String, MyListValue>()
            rows = values.children.size
        } else {
Coverage.ifStart(11349)
            val tmpVariables = mutableListOf<String>()
            for (name in values.variables) {
Coverage.forLoopStart(11350)
                tmpVariables.add(name.name)
            }
            variables = tmpVariables
            var columns = Array(variables.size) { MyListValue() }
            data = mutableMapOf<String, MyListValue>()
            for (variableIndex in 0 until variables.size) {
Coverage.forLoopStart(11351)
                data[variables[variableIndex]] = columns[variableIndex]
            }
            for (v in values.children) {
Coverage.forLoopStart(11352)
                SanityCheck.check({ v is AOPValue })
                val it = v.children.iterator()
                for (variableIndex in 0 until variables.size) {
Coverage.forLoopStart(11353)
                    columns[variableIndex].add((it.next() as AOPConstant).value)
                }
            }
            rows = -1
        }
    }
    override fun getProvidedVariableNamesInternal() = variables.distinct()
    override fun getRequiredVariableNames() = mutableListOf<String>()
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(11354)
        if (rows == -1) {
Coverage.ifStart(11355)
            val outMap = mutableMapOf<String, ColumnIterator>()
Coverage.statementStart(11356)
            for (name in variables) {
Coverage.forLoopStart(11357)
                val tmp = ColumnIteratorMultiValue(data[name]!!)
Coverage.statementStart(11358)
                tmp.close = {
Coverage.statementStart(11359)
                    tmp._close()
Coverage.statementStart(11360)
                    for (variable in variables) {
Coverage.forLoopStart(11361)
                        outMap[variable]!!.close()
Coverage.statementStart(11362)
                    }
Coverage.statementStart(11363)
                }
Coverage.statementStart(11364)
                outMap[name] = ColumnIteratorDebug(uuid, name, tmp)
Coverage.statementStart(11365)
            }
Coverage.statementStart(11366)
            return IteratorBundle(outMap)
        } else {
Coverage.statementStart(11367)
            return IteratorBundle(rows)
        }
Coverage.statementStart(11368)
    }
    override fun toXMLElement(): XMLElement {
Coverage.funStart(11369)
        val res = super.toXMLElement()
Coverage.statementStart(11370)
        val xmlvariables = XMLElement("variables")
Coverage.statementStart(11371)
        res.addAttribute("rows", "" + rows)
Coverage.statementStart(11372)
        res.addContent(xmlvariables)
Coverage.statementStart(11373)
        val bindings = XMLElement("bindings")
Coverage.statementStart(11374)
        res.addContent(bindings)
Coverage.statementStart(11375)
        for (variable in variables) {
Coverage.forLoopStart(11376)
            xmlvariables.addContent(XMLElement("variable").addAttribute("name", variable))
Coverage.statementStart(11377)
        }
Coverage.statementStart(11378)
        var columns = Array(variables.size) { data[variables[it]] }
Coverage.statementStart(11379)
        if (columns.size > 0) {
Coverage.ifStart(11380)
            for (i in 0 until columns[0]!!.size) {
Coverage.forLoopStart(11381)
                val b = XMLElement("binding")
Coverage.statementStart(11382)
                bindings.addContent(b)
Coverage.statementStart(11383)
                for (variableIndex in 0 until variables.size) {
Coverage.forLoopStart(11384)
                    val value = query.dictionary.getValue(columns[variableIndex]!![i]).valueToString()
Coverage.statementStart(11385)
                    if (value != null) {
Coverage.ifStart(11386)
                        b.addContent(XMLElement("value").addAttribute("name", variables[variableIndex]).addAttribute("content", value))
Coverage.statementStart(11387)
                    } else {
Coverage.ifStart(11388)
                        b.addContent(XMLElement("value").addAttribute("name", variables[variableIndex]))
Coverage.statementStart(11389)
                    }
Coverage.statementStart(11390)
                }
Coverage.statementStart(11391)
            }
Coverage.statementStart(11392)
        }
Coverage.statementStart(11393)
        return res
    }
}
