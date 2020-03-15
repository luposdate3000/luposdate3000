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
        val newVariables = Array(3) { resultSet.createVariable((params[it] as AOPVariable).name) }
        val variables = arrayOf<AOPBase>(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o"))
        val channel = Channel<ResultChunk>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            Trace.trace({ "TripleStoreIteratorLocal.next" }, {
                var outbuf = ResultChunk(resultSet)
                try {
                    store.forEach(variables, { it ->
                        val row = resultSet.createResultRow()
                        for (i in 0 until 3)
                            resultSet.setValue(row, newVariables[i]!!, store.resultSet.getValueObject(it[i]))
                        if (!outbuf.canAppend()) {
                            channel.send(resultFlowProduce({ this@TripleStoreIteratorLocal }, { outbuf }))
                            outbuf = ResultChunk(resultSet)
                        }
                        outbuf.append(row)
                    }, index)
                    channel.send(resultFlowProduce({ this@TripleStoreIteratorLocal }, { outbuf }))
                    channel.close()
                } catch (e: Throwable) {
                    channel.close(e)
                }
            })
        }
        return ResultIterator(next = {
            channel.receive()
        }, close = {
            channel.close()
        })
    })
}
