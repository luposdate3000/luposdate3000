package lupos.s03resultRepresentation

import lupos.s00misc.*
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.*
import lupos.s09physicalOperators.POPBase


@UseExperimental(kotlin.ExperimentalStdlibApi::class)
class DynamicByteArray {
    var data: ByteArray
    val maxlen: Int

    constructor() {
        data = ByteArray(100)
        maxlen = Int.MAX_VALUE
println("init $maxlen")
    }

    constructor(data: ByteArray) {
        this.data = data
        maxlen = getInt(0)
println("init $maxlen")
    }

    fun finish(): ByteArray {
println("finish $pos")
        setInt(pos, 0)
println("finish ${getInt(0)}")
        return data
    }

    var pos = 4
    fun setInt(i: Int, p: Int) {
println("DynamicByteArray.setInt $p $i")
        data.set(p, i.toByte())
        data.set(p + 1, (i ushr 8).toByte())
        data.set(p + 2, (i ushr 16).toByte())
        data.set(p + 3, (i ushr 24).toByte())
    }

    fun appendInt(i: Int) {
        if (pos + 4 >= data.size)
            data += ByteArray(data.size)
        setInt(i, pos)
        pos += 4
    }

    fun getInt(p: Int): Int {
        val res = ((0xFF and data[p].toInt())) or
                ((0xFF and data[p + 1].toInt()) shl 8) or
                ((0xFF and data[p + 2].toInt()) shl 16) or
                ((0xFF and data[p + 3].toInt()) shl 24)
println("DynamicByteArray.getInt $p $res")
        return res
    }

    fun getNextInt(): Int {
        val res = getInt(pos)
        pos += 4
        return res
    }

    fun setLong(i: Long, p: Int) {
println("DynamicByteArray.setLong $p $i")
        data.set(p, i.toByte())
        data.set(p + 1, (i ushr 8).toByte())
        data.set(p + 2, (i ushr 16).toByte())
        data.set(p + 3, (i ushr 24).toByte())
        data.set(p + 4, (i ushr 32).toByte())
        data.set(p + 5, (i ushr 40).toByte())
        data.set(p + 6, (i ushr 48).toByte())
        data.set(p + 7, (i ushr 56).toByte())
    }

    fun appendLong(i: Long) {
        if (pos + 4 >= data.size)
            data += ByteArray(data.size)
        setLong(i, pos)
        pos += 8
    }

    fun getLong(p: Int): Long {
        val res = ((0xFF and data[p].toInt()).toLong()) or
                ((0xFF and data[p + 1].toInt()).toLong() shl 8) or
                ((0xFF and data[p + 2].toInt()).toLong() shl 16) or
                ((0xFF and data[p + 3].toInt()).toLong() shl 24) or
                ((0xFF and data[p + 4].toInt()).toLong() shl 32) or
                ((0xFF and data[p + 5].toInt()).toLong() shl 40) or
                ((0xFF and data[p + 6].toInt()).toLong() shl 48) or
                ((0xFF and data[p + 7].toInt()).toLong() shl 56)
println("DynamicByteArray.getLong $p $res")
        return res
    }

    fun getNextLong(): Long {
        val res = getLong(pos)
        pos += 8
        return res
    }

    fun setByte(b: Byte, p: Int) {
println("DynamicByteArray.setByte $p $b")
        data.set(p, b)
    }

    fun appendByte(b: Byte) {
        if (pos + 1 >= data.size)
            data += ByteArray(data.size)
        setByte(b, pos)
        pos++
    }

    fun getByte(p: Int): Byte {
println("DynamicByteArray.getByte $p ${data[p]}")
        return data[p]
    }

    fun getNextByte(): Byte {
        val res = getByte(pos)
        pos += 1
        return res
    }

    fun appendString(s: String) {
println("DynamicByteArray.setString $pos $s")
        val tmp = s.encodeToByteArray()
        appendInt(tmp.size)
        while (pos + tmp.size >= data.size)
            data += ByteArray(data.size)
        for (b in tmp) {
            data.set(pos, b)
            pos++
        }
    }

    fun getString(p: Int): String {
val p=pos
        val l = getInt(p)
        val res= data.decodeToString(p + 4, pos + 5, true)
println("DynamicByteArray.getString $p $res")
return res
    }

    fun getNextString(): String {
        val l = getNextInt()
        val res = data.decodeToString(pos, pos + l, true)
        pos += l
        return res
    }

    fun rewind() {
        pos = 4
    }

    fun appendSpace(l: Int): Int {
        val res = pos
        pos += l
        return res
    }

    fun getPosition(): Int {
        return pos
    }

    fun hasAvailable(): Boolean {
println("hasAvailable :: $maxlen $pos")
        return maxlen > pos
    }
}

object ResultRepresenationNetwork {
    fun toNetworkPackage(query: POPBase): ByteArray {
        println("ResultRepresenationNetwork.toNetworkPackage")
        val res = DynamicByteArray()
        val resultSet = query.getResultSet()
        val variableNames = resultSet.getVariableNames().toTypedArray()
        val variablesCount = variableNames.size
        val variables = arrayOfNulls<Variable>(variablesCount)
println("write variablesCount $variablesCount")
        res.appendInt(variablesCount)
        var i = 0
        for (n in variableNames) {
println("write variableNames $n")
            res.appendString(n)
            variables[i] == resultSet.createVariable(n)
            i++
        }
        var posResultLen = 0
        var latestDictionaryMax: Value? = null
        var currentRowCounter = 0
        if (query.hasNext()) {
            val resultRow = query.next()
            var newDictionaryMax = latestDictionaryMax
            for (v in variables) {
                if (newDictionaryMax == null || resultRow[v!!] > newDictionaryMax)
                    newDictionaryMax = resultRow[v!!]
            }
println("write dictlen ${latestDictionaryMax!! + 1}")
            res.appendLong(latestDictionaryMax!! + 1)
            for (v in latestDictionaryMax!! + 1 until newDictionaryMax!!) {
println("write dictentry ${resultSet.getValue(v)!!}")
                res.appendString(resultSet.getValue(v)!!)
            }
            currentRowCounter = 0
println("space for resultcount")
            posResultLen = res.appendSpace(4)
            for (v in variables) {
println("write triplevalue ${resultRow[v!!]}")
                res.appendLong(resultRow[v!!])
            }
            currentRowCounter++
        }else{
		println("write dictlen 0")
            res.appendLong(0L)
	    println("space for resultcount")
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
println("override resultcount $currentRowCounter")
                res.setInt(currentRowCounter, posResultLen)
println("write dictlen ${newDictionaryMax - latestDictionaryMax}")
                res.appendLong(newDictionaryMax - latestDictionaryMax)
                for (v in latestDictionaryMax + 1 until newDictionaryMax) {
println("write dictentry ${resultSet.getValue(v)!!}")
                    res.appendString(resultSet.getValue(v)!!)
                }
                currentRowCounter = 0
println("space for resultcount")
                posResultLen = res.appendSpace(4)
            }
            for (v in variables) {
println("write triplevalue ${resultRow[v!!]}")
                res.appendLong(resultRow[v!!])
            }
            currentRowCounter++
        }
println("override resultcount ${currentRowCounter}")
        res.setInt(currentRowCounter, posResultLen)
        return res.finish()
    }

    fun fromNetworkPackage(dictionary: ResultSetDictionary, data: ByteArray): POPBase {
        println("ResultRepresenationNetwork.fromNetworkPackage")
        val d = DynamicByteArray(data)
println("verify :: ${d.maxlen}")
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
println("read variablesCount $variablesCount")
        for (i in 0 until variablesCount) {
            val name = data.getNextString()
println("read variableNames $name")
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

fun readDict(){
        if (rowsUntilNextDictionary == 0) {
            val dictEntryCount = data.getNextLong()
println("read dictlen $dictEntryCount")
            for (i in 0 until dictEntryCount) {
val s=data.getNextString()
                variableMap.add(dictionary.createValue(s))
println("read dictentry $s")
            }
            rowsUntilNextDictionary = data.getNextInt()
println("read resultcount $rowsUntilNextDictionary")
        }
}

    override fun next(): ResultRow {
        val row = resultSet.createResultRow()
readDict()
        rowsUntilNextDictionary--
        for (v in variables) {
val i=data.getNextLong().toInt()
            row[v] = variableMap[i]
println("read triplevalue $i")
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
