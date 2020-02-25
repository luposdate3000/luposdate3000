package lupos.s05tripleStore

import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.GlobalLogger
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.OPBase
import lupos.s05tripleStore.POPTripleStoreIteratorBase


open class TripleStoreIteratorLocal : POPTripleStoreIteratorBase {
    override val classname = "TripleStoreIteratorLocal"
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf()
    override val resultSet: ResultSet
    val store: TripleStoreLocal
    var index: EIndexPattern = EIndexPattern.SPO
    override fun getGraphName(): String {
        return store.name
    }

    override fun toXMLElement(): XMLElement {
        return XMLElement("TripleStoreIteratorLocal").addAttribute("uuid", "" + uuid).addAttribute("nameS", nameS).addAttribute("nameP", nameP).addAttribute("nameO", nameO).addAttribute("name", getGraphName())
    }

    override fun setMNameS(n: String) {
        nameS = n
    }

    override fun setMNameP(n: String) {
        nameP = n
    }

    override fun setMNameO(n: String) {
        nameO = n
    }

    constructor(resultSet: ResultSet, store: TripleStoreLocal, index: EIndexPattern) {
        this.dictionary = resultSet.dictionary
        this.resultSet = resultSet
        this.store = store
        this.index = index
    }

    constructor(resultSet: ResultSet, store: TripleStoreLocal) : this(resultSet, store, EIndexPattern.SPO)

    override fun getProvidedVariableNames() = mutableListOf(nameS, nameP, nameO).distinct()

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun evaluate() = Trace.trace<Unit>({ "TripleStoreIteratorLocal.evaluate" }, {
        val sNew = resultSet.createVariable(nameS)
        val pNew = resultSet.createVariable(nameP)
        val oNew = resultSet.createVariable(nameO)
        CoroutinesHelper.run {
            try {
                store.forEach(null, null, null, { sv, pv, ov ->
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
    })
}
