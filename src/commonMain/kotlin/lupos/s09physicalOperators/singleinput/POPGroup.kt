package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.ELoggerType
import lupos.s00misc.EOperatorID
import lupos.s00misc.GlobalLogger
import lupos.s00misc.SanityCheck
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.POPBind

class POPGroup : POPBase {
    @JvmField
    var by: List<AOPVariable>
    @JvmField
    var bindings = mutableListOf<Pair<String, AOPBase>>()

    override fun toSparql(): String {
        var res = children[0].toSparql()
        res += " GROUP BY "
        for (b in by) {
            res += b.toSparql() + " "
        }
        for ((k, v) in bindings) {
            res += "(" + v.toSparql() + " AS " + AOPVariable(query, k).toSparql() + ")"
        }
        return res
    }

    override fun cloneOP(): POPGroup {
        if (bindings.size > 0) {
            var tmpBindings = POPBind(query, AOPVariable(query, bindings[0].first), bindings[0].second, OPNothing(query))
            for (bp in 1 until bindings.size) {
                tmpBindings = POPBind(query, AOPVariable(query, bindings[0].first), bindings[0].second, tmpBindings)
            }
            return POPGroup(query, by, tmpBindings, children[0].cloneOP())
        } else {
            return POPGroup(query, by, null, children[0].cloneOP())
        }
    }

    constructor(query: Query, by: List<AOPVariable>, bindings: POPBind?, child: OPBase) : super(query, EOperatorID.POPGroupID, "POPGroup", arrayOf(child)) {
        this.by = by
        var tmpBind: OPBase? = bindings
        while (tmpBind != null && tmpBind is POPBind) {
            this.bindings.add(Pair(tmpBind.name.name, tmpBind.children[1] as AOPBase))
            tmpBind = tmpBind.children[0]
        }
        this.bindings = this.bindings.asReversed()
    }

    override fun equals(other: Any?): Boolean = other is POPGroup && by.equals(other.by) && bindings.equals(other.bindings) && children[0] == other.children[0]
    override fun getProvidedVariableNames() = (MutableList(by.size) { by[it].name } + MutableList(bindings.size) { bindings[it].first }).distinct()
    override fun getRequiredVariableNames(): List<String> {
        var res = MutableList(by.size) { by[it].name }
        for (b in bindings) {
            res.addAll(b.second.getRequiredVariableNamesRecoursive())
        }
        return res.distinct()
    }

    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {
        SanityCheck.check({ additionalProvided.isEmpty() })
        val localProvide = additionalProvided + children[0].getProvidedVariableNames()
        val localRequire = mutableListOf<String>()
        for (v in by) {
            localRequire.add(v.name)
        }
        for (b in bindings) {
            localRequire += b.second.getRequiredVariableNames()
        }
        for (c in children) {
            c.syntaxVerifyAllVariableExists(localProvide, autocorrect)
        }
        val res = localProvide.containsAll(localRequire)
        if (!res) {
            if (autocorrect) {
                syntaxVerifyAllVariableExistsAutocorrect()
            } else {
                throw Exception("$classname undefined Variable")
            }
        }
    }

    fun getAggregations(node: OPBase): MutableList<AOPAggregationBase> {
        var res = mutableListOf<AOPAggregationBase>()
        for (n in node.children) {
            res.addAll(getAggregations(n))
        }
        if (node is AOPAggregationBase) {
            res.add(node)
        }
        return res
    }

    class MapKey(@JvmField val data: Array<Value>) {
        override fun hashCode(): Int {
            var res = 0
            for (i in 0 until data.size) {
                res += data[i].hashCode()
            }
            return res
        }

        override fun equals(other: Any?): Boolean {
            require(other is MapKey)
            for (i in 0 until data.size) {
                if (data[i] != other.data[i]) {
                    return false
                }
            }
            return true
        }

        fun equalsFuzzy(other: Any?): Boolean {
            require(other is MapKey)
            for (i in 0 until data.size) {
                if (data[i] != ResultSetDictionary.undefValue && other.data[i] != ResultSetDictionary.undefValue && data[i] != other.data[i]) {
                    return false
                }
            }
            return true
        }
    }

    class MapRow(val iterators: ColumnIteratorRow, val aggregates: Array<ColumnIteratorAggregate>, val columns: Array<ColumnIteratorQueue>)

    override suspend fun evaluate(): ColumnIteratorRow {
        val localVariables = children[0].getProvidedVariableNames()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val child = children[0].evaluate()
        val aggregations = mutableListOf<AOPAggregationBase>()
        for (b in bindings) {
            aggregations.addAll(getAggregations(b.second))
        }
        val keyColumnNames = Array(by.size) { by[it].name }
        val keyColumns: Array<ColumnIterator> = Array(keyColumnNames.size) { child.columns[keyColumnNames[it]]!! }
        val valueColumnNames = mutableListOf<String>()
        for (name in localVariables) {
            if (!keyColumnNames.contains(name)) {
                valueColumnNames.add(name)
            }
        }
        val valueColumns = Array(valueColumnNames.size) { child.columns[valueColumnNames[it]]!! }
        if (keyColumnNames.size == 0) {
            val localMap = mutableMapOf<String, ColumnIterator>()
            val localColumns = Array(valueColumnNames.size) { ColumnIteratorQueue() }
            for (columnIndex in 0 until valueColumnNames.size) {
                localMap[valueColumnNames[columnIndex]] = localColumns[columnIndex]
            }
            val row = ColumnIteratorRow(localMap)
            val localAggregations = Array(aggregations.size) {
                val tmp = aggregations[it].createIterator(row)
                localMap["#" + aggregations[it].uuid] = tmp
                /*return*/tmp
            }
            val localRow = MapRow(row, localAggregations, localColumns)
            loop2@ while (true) {
                for (columnIndex in 0 until valueColumnNames.size) {
                    val value = valueColumns[columnIndex].next()
                    if (value == null) {
                        require(columnIndex == 0)
                        break@loop2
                    }
                    localRow.columns[columnIndex].tmp = value
                }
                for (aggregate in localRow.aggregates) {
                    aggregate.evaluate()
                }
            }
            for (columnIndex in 0 until bindings.size) {
                val value = query.dictionary.createValue(bindings[columnIndex].second.evaluate(localRow.iterators)())
                outMap[bindings[columnIndex].first] = ColumnIteratorRepeatValue(1, value)
            }
        } else {
            val map = mutableMapOf<MapKey, MapRow>()
            loop@ while (true) {
                val currentKey = Array(keyColumnNames.size) { ResultSetDictionary.undefValue }
                for (columnIndex in 0 until keyColumnNames.size) {
                    val value = keyColumns[columnIndex].next()
                    if (value == null) {
                        require(columnIndex == 0)
                        break@loop
                    }
                    currentKey[columnIndex] = value
                }
                val key = MapKey(currentKey)
                var localRow = map[key]
                if (localRow == null) {
                    val localMap = mutableMapOf<String, ColumnIterator>()
                    val localColumns = Array(valueColumnNames.size) { ColumnIteratorQueue() }
                    for (columnIndex in 0 until keyColumnNames.size) {
                        val tmp = ColumnIteratorQueue()
                        tmp.tmp = currentKey[columnIndex]
                        localMap[keyColumnNames[columnIndex]] = tmp
                    }
                    for (columnIndex in 0 until valueColumnNames.size) {
                        localMap[valueColumnNames[columnIndex]] = localColumns[columnIndex]
                    }
                    val row = ColumnIteratorRow(localMap)
                    val localAggregations = Array(aggregations.size) {
                        val tmp = aggregations[it].createIterator(row)
                        localMap["#" + aggregations[it].uuid] = tmp
                        /*return*/tmp
                    }
                    localRow = MapRow(row, localAggregations, localColumns)
                    map[key] = localRow
                }
                for (columnIndex in 0 until valueColumnNames.size) {
                    localRow.columns[columnIndex].tmp = valueColumns[columnIndex].next()
                }
                for (aggregate in localRow.aggregates) {
                    aggregate.evaluate()
                }
            }
            val outKeys = Array(keyColumnNames.size) { mutableListOf<Value>() }
            val outValues = Array(bindings.size) { mutableListOf<Value>() }
            for ((k, v) in map) {
                for (columnIndex in 0 until keyColumnNames.size) {
                    outKeys[columnIndex].add(k.data[columnIndex])
                }
                for (columnIndex in 0 until bindings.size) {
                    outValues[columnIndex].add(query.dictionary.createValue(bindings[columnIndex].second.evaluate(v.iterators)()))
                }
            }
            for (columnIndex in 0 until keyColumnNames.size) {
                outMap[keyColumnNames[columnIndex]] = ColumnIteratorMultiValue(outKeys[columnIndex])
            }
            for (columnIndex in 0 until bindings.size) {
                outMap[bindings[columnIndex].first] = ColumnIteratorMultiValue(outValues[columnIndex])
            }
        }
        return ColumnIteratorRow(outMap)
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPGroup")
        res.addAttribute("uuid", "" + uuid)
        val byxml = XMLElement("by")
        res.addContent(byxml)
        for (b in by) {
            byxml.addContent(XMLElement("variable").addAttribute("name", b.name))
        }
        val xmlbindings = XMLElement("bindings")
        res.addContent(xmlbindings)
        for (b in bindings) {
            xmlbindings.addContent(XMLElement("binding").addAttribute("name", b.first).addContent(b.second.toXMLElement()))
        }
        res.addContent(childrenToXML())
        return res
    }
}
