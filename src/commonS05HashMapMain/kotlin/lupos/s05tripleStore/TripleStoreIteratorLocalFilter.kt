package lupos.s05tripleStore

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*


import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

class TripleStoreIteratorLocalFilter(query: Query, resultSet: ResultSet, val store: TripleStoreLocal, var index: EIndexPattern) : POPTripleStoreIteratorBase(query, EOperatorID.TripleStoreIteratorLocalID, "TripleStoreIteratorLocal", resultSet, arrayOf()) {
    override fun getGraphName() = store.name
    override fun toXMLElement() = super.toXMLElement().addAttribute("uuid", "" + uuid).addAttribute("name", getGraphName()).addContent(XMLElement("sparam").addContent(params[0].toXMLElement())).addContent(XMLElement("pparam").addContent(params[1].toXMLElement())).addContent(XMLElement("oparam").addContent(params[2].toXMLElement()))
    override fun cloneOP() = TripleStoreIteratorLocalFilter(query, resultSet, store, index)
    override fun getProvidedVariableNames(): List<String> {
        val tmp = mutableListOf<String>()
        for (p in params)
            tmp.addAll(p.getRequiredVariableNames())
        return tmp.distinct()
    }

    override fun evaluate() = Trace.trace<ResultIterator>({ "TripleStoreIteratorLocal.evaluate" }, {
        println("TripleStoreIteratorLocalFilter evaluate")
        //row based
        val newVariables = mutableListOf<Variable>()
        var idxHelper = 0
        val key = mutableListOf<Value>()
        var columns = 0
        for (i in 0 until 3)
            if (params[i] is AOPVariable) {
                columns++
                newVariables.add(resultSet.createVariable((params[i] as AOPVariable).name))
            } else {
                key.add(store.resultSet.dictionary.createValue((params[i] as AOPConstant).value))
                if (i == 0) idxHelper += 1
                if (i == 1) idxHelper += 2
                if (i == 2) idxHelper += 4
            }
        println("suggested Index $index $idxHelper")
        require(idxHelper < 7)
        val idx = when (idxHelper) {
            1 -> EIndexPattern.S
            2 -> EIndexPattern.P
            3 -> EIndexPattern.SP
            4 -> EIndexPattern.O
            5 -> EIndexPattern.SO
            6 -> EIndexPattern.PO
            else -> EIndexPattern.SPO
        }
        println("suggested Index $index $idx")
        val root = store.getData(key.toTypedArray(), idx)
        val res = ResultIterator()
        println("TripleStoreIteratorLocalFilter evaluate ? $idxHelper")
        if (root != null) {
            var current = root!!
            println("TripleStoreIteratorLocalFilter evaluate found something")
            res.next = {
                println("xxx?")
                try {
                    store.resultSet.createVariable("s")
                    store.resultSet.createVariable("p")
                    store.resultSet.createVariable("o")
                    println("filter.next $current")
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
                println("iterator-next")
                val map = mutableMapOf(ResultSetDictionary.undefValue to ResultSetDictionary.undefValue)
                val outbuf = ResultChunk(resultSet)
                for (i in 0 until columns) {
                    val columnIn = current.data[i]
                    val columnOut = outbuf.data[newVariables[i].toInt()]
                    for (j in columnIn.posIndex until columnIn.sizeIndex + 1) {
                        val x = columnIn.data[j].value
                        if (map[x] == null) {
                            map[x] = query.dictionary.createValue(store.resultSet.dictionary.getValue(x))
                        }
                        columnOut.append(map[x]!!, columnIn.data[j].count)
                    }
                }
                current = current.next
                if (current == root)
                    res.close()
                println("filter.next = ${current == root} $outbuf")
                resultFlowProduce({ this@TripleStoreIteratorLocalFilter }, { outbuf })
            }
        }
        return res
    })
}
