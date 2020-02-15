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
GlobalLogger.log(ELoggerType.BINARY_ENCODING,{"write variablecount $variablesCount"})
        res.appendInt(variablesCount)
        var i = 0
        for (n in variableNames) {
GlobalLogger.log(ELoggerType.BINARY_ENCODING,{"write variablename $n"})
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
                if (newDictionaryMax == null || ((!resultSet.isUndefValue(resultRow,v!!)) && resultRow[v!!] > newDictionaryMax)) {
                    newDictionaryMax = resultRow[v!!]
                }
            }
GlobalLogger.log(ELoggerType.BINARY_ENCODING,{"write dictlen a ${newDictionaryMax!! + 1}"})
            res.appendInt(newDictionaryMax!! + 1)
            for (v in 0 until newDictionaryMax!! + 1) {
GlobalLogger.log(ELoggerType.BINARY_ENCODING,{"write dictentry ${resultSet.getValue(v)!!}"})
                res.appendString(resultSet.getValue(v)!!)
            }
            latestDictionaryMax = newDictionaryMax
            currentRowCounter = 0
GlobalLogger.log(ELoggerType.BINARY_ENCODING,{"space triplecount"})
            posResultLen = res.appendSpace(4)
            for (v in variables) {
GlobalLogger.log(ELoggerType.BINARY_ENCODING,{"write triple ${resultSet.getValue(resultRow[v!!])}"})
                res.appendInt(resultRow[v!!])
            }
            currentRowCounter++
        while (query.hasNext()) {
            val resultRow = query.next()
            var newDictionaryMax = latestDictionaryMax!!
            for (v in variables) {
                if ((!resultSet.isUndefValue(resultRow,v!!)) && resultRow[v!!] > newDictionaryMax)
                    newDictionaryMax = resultRow[v!!]
            }
            if (newDictionaryMax != latestDictionaryMax) {
GlobalLogger.log(ELoggerType.BINARY_ENCODING,{"override triplecount $currentRowCounter"})
                res.setInt(currentRowCounter, posResultLen)
GlobalLogger.log(ELoggerType.BINARY_ENCODING,{"write dictlen c ${newDictionaryMax!! - latestDictionaryMax!!}"})
                res.appendInt(newDictionaryMax - latestDictionaryMax)
                for (v in latestDictionaryMax + 1 until newDictionaryMax + 1) {
GlobalLogger.log(ELoggerType.BINARY_ENCODING,{"write dictentry ${resultSet.getValue(v)!!}"})
                    res.appendString(resultSet.getValue(v)!!)
                }
                currentRowCounter = 0
GlobalLogger.log(ELoggerType.BINARY_ENCODING,{"space triplecount"})
                posResultLen = res.appendSpace(4)
                latestDictionaryMax = newDictionaryMax
            }
            for (v in variables) {
GlobalLogger.log(ELoggerType.BINARY_ENCODING,{"write triple ${resultSet.getValue(resultRow[v!!])}"})
                res.appendInt(resultRow[v!!])
            }
            currentRowCounter++
        }
GlobalLogger.log(ELoggerType.BINARY_ENCODING,{"override triplecount $currentRowCounter"})
        res.setInt(currentRowCounter, posResultLen)
        }
GlobalLogger.log(ELoggerType.BINARY_ENCODING,{"write dictlen d 0"})
	res.appendInt(0)
        return res.finish()
    }

    fun fromNetworkPackage(dictionary: ResultSetDictionary, data: ByteArray): POPBase {
        val d = DynamicByteArray(data)
        return POPImportFromNetworkPackage(dictionary, d)
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
GlobalLogger.log(ELoggerType.BINARY_ENCODING,{"read variablecount $variablesCount"})
            for (i in 0 until variablesCount) {
                val name = data.getNextString()
GlobalLogger.log(ELoggerType.BINARY_ENCODING,{"read variablename $name"})
                variables.add(resultSet.createVariable(name))
            }
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
            if (rowsUntilNextDictionary == 0) {
                val dictEntryCount = data.getNextInt()
GlobalLogger.log(ELoggerType.BINARY_ENCODING,{"read dictlen $dictEntryCount"})
		if(dictEntryCount==0)
			return false
                for (i in 0 until dictEntryCount) {
                    val s = data.getNextString()
GlobalLogger.log(ELoggerType.BINARY_ENCODING,{"read dictentry $s"})
                    variableMap.add(dictionary.createValue(s))
                }
                rowsUntilNextDictionary = data.getNextInt()
GlobalLogger.log(ELoggerType.BINARY_ENCODING,{"read triplecount $rowsUntilNextDictionary"})
            }
	    return true
        }

        override fun next(): ResultRow {
            val row = resultSet.createResultRow()
            rowsUntilNextDictionary--
            for (v in variables) {
                val l = data.getNextInt()
val i=if(l>variableMap.size)
	l
else
variableMap[l]
GlobalLogger.log(ELoggerType.BINARY_ENCODING,{"read triple ${dictionary.getValue(i)}"})
                row[v] = i
            }
            return row
        }
    }

}
