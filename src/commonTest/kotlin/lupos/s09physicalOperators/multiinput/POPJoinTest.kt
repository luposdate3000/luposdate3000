package lupos.s09physicalOperators.multiinput
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
import lupos.s04arithmetikOperators.multiinput.AOPAnd
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.multiinput.AOPGEQ
import lupos.s04arithmetikOperators.multiinput.AOPGT
import lupos.s04arithmetikOperators.multiinput.AOPOr
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
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.noinput.*




object POPJoinTest {
    val MAX_VARIABLES = 4
    val MAX_COUNT = 10
    val MAX_VALUE = 10
    val verbose = false
    fun log(value: Any?) {
        if (verbose) {
            println(value)
        }
    }

    fun removeDuplicates(variables: List<String>, data: MutableMap<String, MyListValue>, keys: List<String>) {
        println("withduplicates = $data")
        var count = 0
        if (variables.size > 0) {
            count = data[variables[0]]!!.size
        }
        for (i in count - 1 downTo 0) {
            duplicates@ for (j in count - 1 downTo i + 1) {
                for (variable in keys) {
                    if (data[variable]!![i] != data[variable]!![j]) {
                        continue@duplicates
                    }
                }
                for (variable in variables) {
                    data[variable]!!.removeAt(j)
                }
                count--
            }
        }
        println("withoutduplicates = $data")
    }

    suspend fun invoke(random: TestRandom) {
/*
test does not include
 - Undefined values
 - optional
 - rows without join partner
 - rows with multiple join partners
*/
        try {
            while (true) {
                val query = Query()
//---generate the result of the merge
                val variableSize = random.nextInt(MAX_VARIABLES - 1) + 1
                var count = random.nextInt(MAX_COUNT)
                val data = mutableMapOf<String, MyListValue>()
                val variables = List(variableSize) {
                    data["v$it"] = MyListValue()
/*return*/"v$it"
                }
                for (i in 0 until count) {
                    for (variable in variables) {
                        data[variable]!!.add(query.dictionary.createValue(ValueInteger(random.nextInt(MAX_VALUE))))
                    }
                }
//---calculate which columns should be joines
                val variablesJ = mutableListOf<String>()
                val variablesAO = mutableListOf<String>()
                val variablesBO = mutableListOf<String>()
                val variablesA = mutableListOf<String>()
                val variablesB = mutableListOf<String>()
                val dataA = mutableMapOf<String, MyListValue>()
                val dataB = mutableMapOf<String, MyListValue>()
                for (variable in variables) {
                    when (random.nextInt(3)) {
                        0 -> {
                            variablesA.add(variable)
                            variablesAO.add(variable)
                            dataA[variable] = MyListValue()
                        }
                        1 -> {
                            variablesB.add(variable)
                            variablesBO.add(variable)
                            dataB[variable] = MyListValue()
                        }
                        else -> {
                            variablesA.add(variable)
                            variablesB.add(variable)
                            variablesJ.add(variable)
                            dataA[variable] = MyListValue()
                            dataB[variable] = MyListValue()
                        }
                    }
                }
                if (variablesA.size == 0 || variablesB.size == 0) {
                    continue
                }
//---eliminate duplicate results to simplify testing
                removeDuplicates(variables, data, variablesJ)
                count = data[variables[0]]!!.size
//---reverse calculate the data for_ the colunmns
                for (i in 0 until count) {
                    for (variable in variablesJ) {
                        dataA[variable]!!.add(data[variable]!![i])
                        dataB[variable]!!.add(data[variable]!![i])
                    }
                    for (variable in variablesAO) {
                        dataA[variable]!!.add(data[variable]!![i])
                    }
                    for (variable in variablesBO) {
                        dataB[variable]!!.add(data[variable]!![i])
                    }
                }
//---eliminate duplicates in the source data
                removeDuplicates(variablesA, dataA, variablesA)
                removeDuplicates(variablesB, dataB, variablesB)
                if (variablesJ.size == 0) {
//---cartesian product
                    data.clear()
                    var countA = 0
                    var countB = 0
                    for (variable in variablesAO) {
                        data[variable] = MyListValue()
                        countA = dataA[variable]!!.size
                    }
                    for (variable in variablesBO) {
                        data[variable] = MyListValue()
                        countB = dataB[variable]!!.size
                    }
                    for (i in 0 until countA) {
                        for (j in 0 until countB) {
                            for (variable in variablesAO) {
                                data[variable]!!.add(dataA[variable]!![i])
                            }
                            for (variable in variablesBO) {
                                data[variable]!!.add(dataB[variable]!![j])
                            }
                        }
                    }
                }
                count = data[variables[0]]!!.size
//---finally calculate the join and check the result
                val valuesA = POPValues(query, variablesA, dataA)
                val valuesB = POPValues(query, variablesB, dataB)
                val iterator = POPJoinHashMap(query, valuesA, valuesB, false).evaluate()
                println(variablesA)
                println(variablesB)
                println(data)
                println(dataA)
                println(dataB)
                val dataRetrieveIterators = Array(variables.size) { iterator.columns[variables[it]] }
                val dataRetrieved = Array(variables.size) { MyListValue() }
                loop@ while (true) {
                    for (variableIndex in 0 until variables.size) {
                        val value = dataRetrieveIterators[variableIndex]!!.next()
                        if (value == null) {
                            require(variableIndex == 0)
                            break@loop
                        }
                        dataRetrieved[variableIndex].add(value)
                    }
                }
                println(dataRetrieved.map { "${it}\n" })
                for (i in 0 until count) {
                    var counter = 0
                    next@ for (j in dataRetrieved[0].size - 1 downTo 0) {
                        for (variableIndex in 0 until variables.size) {
                            if (dataRetrieved[variableIndex][j] != data[variables[variableIndex]]!![i]) {
                                continue@next
                            }
                        }
                        for (variableIndex in 0 until variables.size) {
                            dataRetrieved[variableIndex].removeAt(j)
                        }
                        counter++
                    }
                    println(dataRetrieved.map { "${it}\n" })
                    println("$i $count $counter")
                    require(counter == 1)
                }
                require(dataRetrieved[0].size == 0)
            }
        } catch (e: NoMoreRandomException) {
        }
    }
}
