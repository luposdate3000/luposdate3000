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
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf()
    override val resultSet: ResultSet
    var sNew: Variable
    var pNew: Variable
    var oNew: Variable
    val store: TripleStoreLocal
    var index: EIndexPattern = EIndexPattern.SPO
    override fun getGraphName(): String {
        return store.name
    }

    override fun toXMLElement(): XMLElement {
        return XMLElement("TripleStoreIteratorLocal").addAttribute("uuid", "" + uuid).addAttribute("nameS", nameS).addAttribute("nameP", nameP).addAttribute("nameO", nameO).addAttribute("name", getGraphName())
    }

    override fun setMNameS(n: String) {
        sNew = resultSet.renameVariable(nameS, n)
        nameS = n
    }

    override fun setMNameP(n: String) {
        pNew = resultSet.renameVariable(nameP, n)
        nameP = n
    }

    override fun setMNameO(n: String) {
        oNew = resultSet.renameVariable(nameO, n)
        nameO = n
    }

    constructor(dictionary: ResultSetDictionary, store: TripleStoreLocal, index: EIndexPattern) {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        sNew = resultSet.createVariable(nameS)
        pNew = resultSet.createVariable(nameP)
        oNew = resultSet.createVariable(nameO)
        this.store = store
        this.index = index
    }

    constructor(dictionary: ResultSetDictionary, store: TripleStoreLocal) : this(dictionary, store, EIndexPattern.SPO)

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>(nameS, nameP, nameO)
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun evaluate() = Trace.trace<Unit>({ "TripleStoreIteratorLocal.evaluate" }, {
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
