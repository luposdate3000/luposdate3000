package lupos.s05tripleStore
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EOperatorID
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase


open class TripleStoreIteratorLocal : POPTripleStoreIteratorBase {
    override val operatorID = EOperatorID.TripleStoreIteratorLocalID
    override val classname = "TripleStoreIteratorLocal"
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf()
    override val resultSet: ResultSet
    @JvmField
    val store: TripleStoreLocal
    @JvmField
    var index: EIndexPattern = EIndexPattern.SPO

    override fun getGraphName(): String {
        return store.name
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("uuid", "" + uuid).addAttribute("name", getGraphName()).addContent(XMLElement("sparam").addContent(sparam.toXMLElement())).addContent(XMLElement("pparam").addContent(pparam.toXMLElement())).addContent(XMLElement("oparam").addContent(oparam.toXMLElement()))

    constructor(resultSet: ResultSet, store: TripleStoreLocal, index: EIndexPattern = EIndexPattern.SPO) {
        this.dictionary = resultSet.dictionary
        this.resultSet = resultSet
        this.store = store
        this.index = index
    }

    override fun cloneOP() = TripleStoreIteratorLocal(resultSet, store, index)


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
                store.forEach(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), { sv, pv, ov ->
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
