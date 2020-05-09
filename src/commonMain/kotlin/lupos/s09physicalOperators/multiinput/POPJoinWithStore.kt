package lupos.s09physicalOperators.multiinput
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
import lupos.s01io.*
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
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallSTRUUID
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallUUID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
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
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore




class POPJoinWithStore(query: Query, projectedVariables: List<String>, childA: OPBase, val childB: LOPTriple, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinWithStoreID, "POPJoinWithStore", arrayOf(childA), ESortPriority.SAME_AS_CHILD) {
    override fun toSparql(): String {
        if (optional) {
            return "OPTIONAL{" + children[0].toSparql() + childB.toSparql() + "}"
        }
        return children[0].toSparql() + childB.toSparql()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is POPJoinWithStore) {
            return false
        }
        if (optional != other.optional) {
            return false
        }
        for (i in children.indices) {
            if (!children[i].equals(other.children[i])) {
                return false
            }
        }
        return true
    }

    override suspend fun evaluate(): ColumnIteratorRow {
        SanityCheck.check { !optional }
        SanityCheck.check { !childB.graphVar }
        val childAv = children[0].evaluate()
        val childA = children[0]
        val columnsINAO = mutableListOf<ColumnIterator>()
        val columnsINAJ = mutableListOf<ColumnIterator>()
        val columnsOUTAO = mutableListOf<ColumnIteratorQueue>()
        val columnsOUTAJ = mutableListOf<ColumnIteratorQueue>()
        val columnsOUTB = mutableListOf<ColumnIteratorQueue>()
        val columnsOUT = mutableListOf<ColumnIteratorQueue>()
        val variablINBO = mutableListOf<String>()
        val indicesINBJ = MyListInt()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val tmp = mutableListOf<String>()
        tmp.addAll(childB.getProvidedVariableNames())
        for (name in childA.getProvidedVariableNames()) {
            val it = ColumnIteratorQueue()
            if (tmp.contains(name)) {
                for (i in 0 until 3) {
                    val cc = childB.children[i]
                    if (cc is AOPVariable && cc.name == name) {
                        indicesINBJ.add(i)
                        break
                    }
                }
                tmp.remove(name)
                if (projectedVariables.contains(name)) {
                    columnsOUT.add(it)
                    columnsINAJ.add(0, childAv.columns[name]!!)
                    columnsOUTAJ.add(0, it)
                } else {
                    columnsINAJ.add(childAv.columns[name]!!)
                }
            } else {
                columnsOUT.add(it)
                columnsOUTAO.add(0, it)
                columnsINAO.add(0, childAv.columns[name]!!)
            }
            outMap[name] = it
        }
        for (name in tmp) {
            variablINBO.add(name)
            val it = ColumnIteratorQueue()
            columnsOUTB.add(it)
            columnsOUT.add(it)
            outMap[name] = it
        }
        SanityCheck.check { variablINBO.size > 0 }
        val distributedStore = DistributedTripleStore.getNamedGraph(query, childB.graph)
        val valuesAO = Array<Value?>(columnsINAO.size) { null }
        val valuesAJ = Array<Value?>(columnsINAJ.size) { null }
        var count = 0
        val params = Array<AOPBase>(3) {
            if (childB.children[it] is AOPConstant) {
                count++
            }
/*return*/ childB.children[it] as AOPBase
        }
        for (i in 0 until indicesINBJ.size) {
            SanityCheck.check { params[indicesINBJ[i]] is AOPVariable }
            params[indicesINBJ[i]] = AOPConstant(query, ResultSetDictionary.undefValue2)
            count++
        }
        var index: EIndexPattern
        SanityCheck.check { count > 0 }
        SanityCheck.check { count < 3 }
        if (count == 1) {
            if (params[0] is AOPConstant) {
                if (childB.mySortPriority.size == 0 || (params[1] as AOPVariable).name == childB.mySortPriority[0]) {
                    index = EIndexPattern.S_PO
                } else {
                    index = EIndexPattern.S_OP
                }
            } else if (params[1] is AOPConstant) {
                if (childB.mySortPriority.size == 0 || (params[0] as AOPVariable).name == childB.mySortPriority[0]) {
                    index = EIndexPattern.P_SO
                } else {
                    index = EIndexPattern.P_OS
                }
            } else {
                SanityCheck.check { params[2] is AOPConstant }
                if (childB.mySortPriority.size == 0 || (params[0] as AOPVariable).name == childB.mySortPriority[0]) {
                    index = EIndexPattern.O_SP
                } else {
                    index = EIndexPattern.O_PS
                }
            }
        } else {
            SanityCheck.check { count == 2 }
            if (params[0] is AOPVariable) {
                index = EIndexPattern.PO_S
            } else if (params[1] is AOPVariable) {
                index = EIndexPattern.SO_P
            } else {
                SanityCheck.check { params[2] is AOPVariable }
                index = EIndexPattern.SP_O
            }
        }
        println("choosen index for POPJoinWithStore $index")
        SanityCheck.check { indicesINBJ.size > 0 }
        SanityCheck.check { valuesAJ.size == indicesINBJ.size }
        val columnsInB = Array(variablINBO.size) { ColumnIterator() }
        for (i in 0 until columnsINAO.size) {
            valuesAO[i] = columnsINAO[i].next()
        }
        for (i in 0 until columnsINAJ.size) {
            valuesAJ[i] = columnsINAJ[i].next()
        }
        if (valuesAJ[0] != null) {
            println("POPJoinWithStore valuesAO.nexx : ${valuesAO.map { "$it = ${query.dictionary.getValue(it!!).toSparql()}" }}")
            println("POPJoinWithStore valuesAJ.nexx : ${valuesAJ.map { "$it = ${query.dictionary.getValue(it!!).toSparql()}" }}")
//there is at least one value in A
            for (i in 0 until indicesINBJ.size) {
                params[indicesINBJ[i]] = AOPConstant(query, valuesAJ[i]!!)
            }
            println("used params : ${params.map { it.toSparql() }}")
            var columnsInBRoot = distributedStore.getIterator(params, index).evaluate()
            for (i in 0 until variablINBO.size) {
                columnsInB[i] = columnsInBRoot.columns[variablINBO[i]]!!
            }
            for (column in columnsOUT) {
                column.onEmptyQueue = {
                    loopA@ while (true) {
                        println("POPJoinWithStore onEmptyQueue loop")
                        var done = true
                        loopB@ for (i in 0 until variablINBO.size) {
                            val value = columnsInB[i].next()
                            if (value == null) {
                                SanityCheck.check { i == 0 }
                                done = false
                                break@loopB
                            } else {
                                columnsOUTB[i].queue.add(value)
                                println("POPJoinWithStore columnsOUTB[$i] append : ${value}")
                            }
                        }
                        if (done) {
                            for (i in 0 until columnsOUTAO.size) {
                                columnsOUTAO[i].queue.add(valuesAO[i]!!)
                                println("POPJoinWithStore columnsOUTAO[$i] append : ${valuesAO[i]}")
                            }
                            for (i in 0 until columnsOUTAJ.size) {
                                columnsOUTAJ[i].queue.add(valuesAJ[i]!!)
                                println("POPJoinWithStore columnsOUTAJ[$i] append : ${valuesAJ[i]}")
                            }
                            break@loopA
                        } else {
                            for (i in 0 until columnsINAO.size) {
                                valuesAO[i] = columnsINAO[i].next()
                            }
                            for (i in 0 until columnsINAJ.size) {
                                valuesAJ[i] = columnsINAJ[i].next()
                            }
                            if (valuesAJ[0] != null) {
                                println("POPJoinWithStore valuesAO.nexx : ${valuesAO.map { "$it = ${query.dictionary.getValue(it!!).toSparql()}" }}")
                                println("POPJoinWithStore valuesAJ.nexx : ${valuesAJ.map { "$it = ${query.dictionary.getValue(it!!).toSparql()}" }}")
                                for (i in 0 until indicesINBJ.size) {
                                    params[indicesINBJ[i]] = AOPConstant(query, valuesAJ[i]!!)
                                }
                                println("used paramm : ${params.map { it.toSparql() }}")
                                columnsInBRoot = distributedStore.getIterator(params, index).evaluate()
                                for (i in 0 until variablINBO.size) {
                                    columnsInB[i] = columnsInBRoot!!.columns[variablINBO[i]]!!
                                }
                            } else {
                                break@loopA
                            }
                        }
                    }
                }
            }
        }
        return ColumnIteratorRow(outMap)
    }

    override fun toXMLElement(): XMLElement {
        val res = super.toXMLElement().addAttribute("optional", "" + optional)
        res["children"]!!.addContent(childB.toXMLElement())
        return res
    }

    override fun cloneOP() = POPJoinWithStore(query, projectedVariables, children[0].cloneOP(), childB.cloneOP(), optional)
}
