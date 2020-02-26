package lupos.s03resultRepresentation

import lupos.s00misc.*
import lupos.s00misc.ELoggerType
import lupos.s00misc.EOperatorID
import lupos.s00misc.GlobalLogger
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


object ResultRepresenationNetwork {
    fun toNetworkPackage(query: POPBase): ByteArray {
        query.evaluate()
        val res = DynamicByteArray()
        val variableNames = query.getProvidedVariableNames().toTypedArray()
        val variablesCount = variableNames.size
        val variables = arrayOfNulls<Variable>(variablesCount)
        GlobalLogger.log(ELoggerType.BINARY_ENCODING, { "write variablecount $variablesCount" })
        res.appendInt(variablesCount)
        var i = 0
        for (n in variableNames) {
            GlobalLogger.log(ELoggerType.BINARY_ENCODING, { "write variablename $n" })
            res.appendString(n)
            variables[i] = query.resultSet.createVariable(n)
            i++
        }
        var posResultLen = 0
        var latestDictionaryMax: Value? = null
        var currentRowCounter = 0
        CoroutinesHelper.runBlock {
            var firstDict = true
            for (resultRow in query.channel) {
                var newDictionaryMax = latestDictionaryMax
                for (v in variables)
                    if ((!query.resultSet.isUndefValue(resultRow, v!!)) && (newDictionaryMax == null || resultRow[v!!] > newDictionaryMax!!))
                        newDictionaryMax = resultRow[v]
                if (newDictionaryMax == null || newDictionaryMax != latestDictionaryMax) {
                    if (!firstDict) {
                        GlobalLogger.log(ELoggerType.BINARY_ENCODING, { "override triplecount a $currentRowCounter" })
                        res.setInt(currentRowCounter, posResultLen)
                    }
                    if (newDictionaryMax == null) {
                        GlobalLogger.log(ELoggerType.BINARY_ENCODING, { "write dictlen a 0" })
                        res.appendInt(0)
                    } else if (latestDictionaryMax == null) {
                        GlobalLogger.log(ELoggerType.BINARY_ENCODING, { "write dictlen b ${newDictionaryMax!! + 1}" })
                        res.appendInt(newDictionaryMax!! + 1)
                        for (v in 0 until newDictionaryMax!! + 1) {
                            GlobalLogger.log(ELoggerType.BINARY_ENCODING, { "write dictentry ${query.resultSet.getValue(v)!!}" })
                            res.appendString(query.resultSet.getValue(v)!!)
                        }
                    } else {
                        GlobalLogger.log(ELoggerType.BINARY_ENCODING, { "write dictlen c ${newDictionaryMax!! - latestDictionaryMax!!}" })
                        res.appendInt(newDictionaryMax!! - latestDictionaryMax!!)
                        for (v in latestDictionaryMax!! + 1 until newDictionaryMax!! + 1) {
                            GlobalLogger.log(ELoggerType.BINARY_ENCODING, { "write dictentry ${query.resultSet.getValue(v)!!}" })
                            res.appendString(query.resultSet.getValue(v)!!)
                        }
                    }
                    currentRowCounter = 0
                    GlobalLogger.log(ELoggerType.BINARY_ENCODING, { "space triplecount" })
                    posResultLen = res.appendSpace(4)
                    firstDict = false
                    latestDictionaryMax = newDictionaryMax
                }
                for (v in variables) {
                    GlobalLogger.log(ELoggerType.BINARY_ENCODING, { "write triple ${query.resultSet.getValue(resultRow[v!!])}" })
                    res.appendInt(resultRow[v!!])
                }
                currentRowCounter++
            }
            if (!firstDict) {
                GlobalLogger.log(ELoggerType.BINARY_ENCODING, { "override triplecount b $currentRowCounter" })
                res.setInt(currentRowCounter, posResultLen)
            }
        }
        GlobalLogger.log(ELoggerType.BINARY_ENCODING, { "write dictlen d 0" })
        res.appendInt(0)
        return res.finish()
    }

    fun fromNetworkPackage(resultSet: ResultSet, data: ByteArray): POPBase {
        val d = DynamicByteArray(data)
        return POPImportFromNetworkPackage(resultSet, d)
    }

    class POPImportFromNetworkPackage : POPBase {
        override val classname = "POPImportFromNetworkPackage"
        override val dictionary: ResultSetDictionary
        override val children: Array<OPBase> = arrayOf()
        override val resultSet: ResultSet
        val variableMap = mutableListOf<Value>()
        var rowsUntilNextDictionary = 0
        val data: DynamicByteArray
        val variables = mutableListOf<Variable>()

        override fun equals(other: Any?): Boolean {
            if (other !is POPImportFromNetworkPackage)
                return false
            if (dictionary !== other.dictionary)
                return false
            for (i in children.indices) {
                if (!children[i].equals(other.children[i]))
                    return false
            }
            return true
        }

        constructor(resultSet: ResultSet, data: DynamicByteArray) {
            this.dictionary = resultSet.dictionary
            this.resultSet = resultSet
            this.data = data
            val variablesCount = data.getNextInt()
            GlobalLogger.log(ELoggerType.BINARY_ENCODING, { "read variablecount $variablesCount" })
            for (i in 0 until variablesCount) {
                val name = data.getNextString()
                GlobalLogger.log(ELoggerType.BINARY_ENCODING, { "read variablename $name" })
                variables.add(resultSet.createVariable(name))
            }
        }

        override fun getProvidedVariableNames() = resultSet.getVariableNames().toList().distinct()

        override fun evaluate() = Trace.trace<Unit>({ "POPImportFromNetworkPackage.evaluate" }, {
            CoroutinesHelper.runBlock {
                try {
                    while (true) {
                        if (rowsUntilNextDictionary == 0) {
                            val dictEntryCount = data.getNextInt()
                            GlobalLogger.log(ELoggerType.BINARY_ENCODING, { "read dictlen $dictEntryCount" })
                            if (dictEntryCount == 0)
                                break
                            for (i in 0 until dictEntryCount) {
                                val s = data.getNextString()
                                GlobalLogger.log(ELoggerType.BINARY_ENCODING, { "read dictentry $s" })
                                variableMap.add(dictionary.createValue(s))
                            }
                            rowsUntilNextDictionary = data.getNextInt()
                            GlobalLogger.log(ELoggerType.BINARY_ENCODING, { "read triplecount $rowsUntilNextDictionary" })
                        }
                        val row = resultSet.createResultRow()
                        rowsUntilNextDictionary--
                        for (v in variables) {
                            val l = data.getNextInt()
                            val i = if (l > variableMap.size)
                                l
                            else
                                variableMap[l]
                            GlobalLogger.log(ELoggerType.BINARY_ENCODING, { "read triple ${dictionary.getValue(i)}" })
                            row[v] = i
                        }
                        channel.send(row)
                    }
                    channel.close()
                } catch (e: Throwable) {
                    channel.close(e)
                }
            }
        })
    }
}
