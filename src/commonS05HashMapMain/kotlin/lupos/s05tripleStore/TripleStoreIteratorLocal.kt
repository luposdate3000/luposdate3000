package lupos.s05tripleStore

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EOperatorID
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query


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

    override fun toXMLElement() = super.toXMLElement().addAttribute("uuid", "" + uuid).addAttribute("name", getGraphName()).addContent(XMLElement("sparam").addContent(sparam.toXMLElement())).addContent(XMLElement("pparam").addContent(pparam.toXMLElement())).addContent(XMLElement("oparam").addContent(oparam.toXMLElement()))

    override fun cloneOP() = TripleStoreIteratorLocal(query, resultSet, store, index)

    override fun getProvidedVariableNames(): List<String> {
        val tmp = mutableListOf<String>()
        tmp.addAll(sparam.getRequiredVariableNames())
        tmp.addAll(pparam.getRequiredVariableNames())
        tmp.addAll(oparam.getRequiredVariableNames())
        return tmp.distinct()
    }

    override fun evaluate() = Trace.trace<Channel<ResultRow>>({ "TripleStoreIteratorLocal.evaluate" }, {
        val sNew = resultSet.createVariable((sparam as AOPVariable).name)
        val pNew = resultSet.createVariable((pparam as AOPVariable).name)
        val oNew = resultSet.createVariable((oparam as AOPVariable).name)
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                store.forEach(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o"), { sv, pv, ov ->
                    val result = resultSet.createResultRow()
                    result[sNew] = resultSet.createValue(store.resultSet.getValue(sv))
                    result[pNew] = resultSet.createValue(store.resultSet.getValue(pv))
                    result[oNew] = resultSet.createValue(store.resultSet.getValue(ov))
                    channel.send(result)
                }, index)
                channel.close()
            } catch (e: Throwable) {
                channel.close(e)
            }
        }
        return channel
    })
}
