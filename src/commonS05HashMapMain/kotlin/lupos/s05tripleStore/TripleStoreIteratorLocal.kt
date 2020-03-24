package lupos.s05tripleStore

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

open class TripleStoreIteratorLocal(query: Query,
                                    resultSet: ResultSet,
                                    val store: TripleStoreLocal,
                                    var index: EIndexPattern = EIndexPattern.SPO,
                                    operatorID: EOperatorID = EOperatorID.TripleStoreIteratorLocalID,
                                    classname: String = "TripleStoreIteratorLocal"
) :
        POPTripleStoreIteratorBase(query,
                operatorID,
                classname,
                resultSet,
                arrayOf()) {
    override fun getGraphName() = store.name
    override fun toXMLElement() = super.toXMLElement().addAttribute("uuid", "" + uuid).addAttribute("name", getGraphName()).addContent(XMLElement("sparam").addContent(params[0].toXMLElement())).addContent(XMLElement("pparam").addContent(params[1].toXMLElement())).addContent(XMLElement("oparam").addContent(params[2].toXMLElement()))
    override fun cloneOP() = TripleStoreIteratorLocal(query, resultSet, store, index)
    override fun getProvidedVariableNames(): List<String> {
        val tmp = mutableListOf<String>()
        for (p in params)
            tmp.addAll(p.getRequiredVariableNames())
        return tmp.distinct()
    }

    override fun evaluate() = Trace.trace<ResultIterator>({ "TripleStoreIteratorLocal.evaluate" }, {
        //row based
        val newVariables = Array(3) { resultSet.createVariable((params[it] as AOPVariable).name) }
        val root = store.getData(null, EIndexPattern.SPO)
        val res = ResultIterator()
        if (root != null) {
            var current = root!!
            res.next = {
                val map = mutableMapOf(query.dictionary.undefValue to query.dictionary.undefValue)
                val outbuf = ResultChunk(resultSet)
                for (i in 0 until current.availableRead()) {
//TODO this can be improved - without the need to iterate over rows
                    val row = current.nextArr()
                    for (i in 0 until 3) {//translate global ids to query local ids
                        val x = map[row[i]]
                        if (x != null) {
                            row[i] = x
                        } else {
                            val y = query.dictionary.createValue(store.resultSet.dictionary.getValue(row[i]))
                            map[row[i]] = y
                            row[i] = y
                        }
                    }
                    outbuf.append(row)
                }
                current = current.next
                if (current == root)
                    res.close()
                resultFlowProduce({ this@TripleStoreIteratorLocal }, { outbuf })
            }
        }
        return res
    })
}
