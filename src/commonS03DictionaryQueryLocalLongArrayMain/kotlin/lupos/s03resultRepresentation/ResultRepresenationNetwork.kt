package lupos.s03resultRepresentation

import lupos.s00misc.*
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.*
import lupos.s09physicalOperators.POPBase

object ResultRepresenationNetwork {
    fun toNetworkPackage(query: POPBase): ByteArray {
        val res = DynamicByteArray()
        val resultSet = query.getResultSet()
        val variableNames = resultSet.getVariableNames().toTypedArray()
        val variablesCount = variableNames.size
        val variables = arrayOfNulls<Variable>(variablesCount)
        res.appendInt(variablesCount)
        var i = 0
        for (n in variableNames) {
            res.appendString(n)
            variables[i] = resultSet.createVariable(n)
            i++
        }
        var posResultLen = 0
        var latestDictionaryMax: Value? = null
        var currentRowCounter = 0
        if (query.hasNext()) {
            val resultRow = query.next()
            var newDictionaryMax = latestDictionaryMax
            for (v in variables) {
                if (newDictionaryMax == null || resultRow[v!!] > newDictionaryMax) {
                    newDictionaryMax = resultRow[v!!]
                }
            }
            res.appendLong(newDictionaryMax!! + 1)
            for (v in 0 until newDictionaryMax!! + 1) {
                res.appendString(resultSet.getValue(v)!!)
            }
            latestDictionaryMax = newDictionaryMax
            currentRowCounter = 0
            posResultLen = res.appendSpace(4)
            for (v in variables) {
                res.appendLong(resultRow[v!!])
            }
            currentRowCounter++
        } else {
            res.appendLong(0L)
            posResultLen = res.appendSpace(4)
        }
        while (query.hasNext()) {
            val resultRow = query.next()
            var newDictionaryMax = latestDictionaryMax!!
            for (v in variables) {
                if (resultRow[v!!] > newDictionaryMax)
                    newDictionaryMax = resultRow[v!!]
            }
            if (newDictionaryMax != latestDictionaryMax) {
                res.setInt(currentRowCounter, posResultLen)
                res.appendLong(newDictionaryMax - latestDictionaryMax)
                for (v in latestDictionaryMax + 1 until newDictionaryMax + 1) {
                    res.appendString(resultSet.getValue(v)!!)
                }
                currentRowCounter = 0
                posResultLen = res.appendSpace(4)
                latestDictionaryMax = newDictionaryMax
            }
            for (v in variables) {
                res.appendLong(resultRow[v!!])
            }
            currentRowCounter++
        }
        res.setInt(currentRowCounter, posResultLen)
        return res.finish()
    }

    fun fromNetworkPackage(dictionary: ResultSetDictionary, data: ByteArray): POPBase {
        val d = DynamicByteArray(data)
        return POPImportFromNetworkPackage(dictionary, d)
    }
}

class POPImportFromNetworkPackage : POPBase {
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf()
    private val resultSet: ResultSet
    val variableMap = mutableListOf<Value>()
    var rowsUntilNextDictionary = 0
    val data: DynamicByteArray
    val variables = mutableListOf<Variable>()
    override fun toXMLElement(): XMLElement {
        return XMLElement("POPImportFromNetworkPackage")
    }

    constructor(dictionary: ResultSetDictionary, data: DynamicByteArray) {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        this.data = data
        val variablesCount = data.getNextInt()
        for (i in 0 until variablesCount) {
            val name = data.getNextString()
            variables.add(resultSet.createVariable(name))
        }
        readDict()
    }

    override fun getProvidedVariableNames(): List<String> {
        return resultSet.getVariableNames().toList()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getResultSet(): ResultSet {
        return resultSet
    }

    override fun hasNext(): Boolean {
        return data.hasAvailable()
    }

    fun readDict() {
        if (rowsUntilNextDictionary == 0) {
            val dictEntryCount = data.getNextLong()
            for (i in 0 until dictEntryCount) {
                val s = data.getNextString()
                variableMap.add(dictionary.createValue(s))
            }
            rowsUntilNextDictionary = data.getNextInt()
        }
    }

    override fun next(): ResultRow {
        val row = resultSet.createResultRow()
        readDict()
        rowsUntilNextDictionary--
        for (v in variables) {
            val i = data.getNextLong().toInt()
            row[v] = variableMap[i]
        }
        return row
    }
}

fun myRequire(a: Any, b: Any) {
    println("require ($a == $b) >> ${a == b}")
    require(a == b)
}

fun dynamicByteArraySelfTest() {
    val a1 = DynamicByteArray()
    a1.appendInt(35)
    a1.appendLong(35L)
    a1.appendByte(245.toByte())
    a1.appendString("abc")
    a1.rewind()
    myRequire(a1.getNextInt(), 35)
    myRequire(a1.getNextLong(), 35L)
    myRequire(a1.getNextByte(), 245.toByte())
    myRequire(a1.getNextString(), "abc")
    val a2 = DynamicByteArray()
    a2.appendInt(35)
    a2.appendLong(Long.MAX_VALUE - 1L)
    a2.appendByte(245.toByte())
    a2.appendString("abc")
    a2.appendInt(35)
    a2.appendLong(35L)
    a2.appendByte(245.toByte())
    a2.appendString("abc")
    a2.rewind()
    myRequire(a2.getNextInt(), 35)
    myRequire(a2.getNextLong(), Long.MAX_VALUE - 1L)
    myRequire(a2.getNextByte(), 245.toByte())
    myRequire(a2.getNextString(), "abc")
    myRequire(a2.getNextInt(), 35)
    myRequire(a2.getNextLong(), 35L)
    myRequire(a2.getNextByte(), 245.toByte())
    myRequire(a2.getNextString(), "abc")
    println("dynamicByteArraySelfTest ok")
}
