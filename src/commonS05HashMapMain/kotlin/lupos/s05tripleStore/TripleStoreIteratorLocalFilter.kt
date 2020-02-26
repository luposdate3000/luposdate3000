package lupos.s05tripleStore
import lupos.s00misc.EOperatorID

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


class TripleStoreIteratorLocalFilter : TripleStoreIteratorLocal {
    override val operatorID=EOperatorID.TripleStoreIteratorLocalFilterID
    override val classname = "TripleStoreIteratorLocalFilter"
    var sFilter: Value? = null
    var pFilter: Value? = null
    var oFilter: Value? = null

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("TripleStoreIteratorLocalFilter").addAttribute("uuid", "" + uuid).addAttribute("name", getGraphName())
        if (sFilter == null)
            res.addAttribute("nameS", nameS)
        else
            res.addAttribute("filterS", store.resultSet.getValue(sFilter!!)!!)
        if (pFilter == null)
            res.addAttribute("nameP", nameP)
        else
            res.addAttribute("filterP", store.resultSet.getValue(pFilter!!)!!)
        if (oFilter == null)
            res.addAttribute("nameO", nameO)
        else
            res.addAttribute("filterO", store.resultSet.getValue(oFilter!!)!!)
        return res
    }

    constructor(resultSet: ResultSet, store: TripleStoreLocal, index: EIndexPattern) : super(resultSet, store, index) {

    }

    fun setSFilterV(s: String) {
        sFilter = store.resultSet.createValue(s)
    }

    fun setPFilterV(p: String) {
        pFilter = store.resultSet.createValue(p)
    }

    fun setOFilterV(o: String) {
        oFilter = store.resultSet.createValue(o)
    }

    override fun getProvidedVariableNames(): List<String> {
        val tmp = mutableListOf<String>()
        if (sFilter == null)
            tmp += nameS
        if (pFilter == null)
            tmp += nameP
        if (oFilter == null)
            tmp += nameO
        return tmp.distinct()
    }

    override fun evaluate() = Trace.trace<Unit>({ "TripleStoreIteratorLocalFilter.evaluate" }, {
        val sNew: Variable?
        val pNew: Variable?
        val oNew: Variable?
        if (sFilter == null)
            sNew = resultSet.createVariable(nameS)
        else
            sNew = null
        if (pFilter == null)
            pNew = resultSet.createVariable(nameP)
        else
            pNew = null
        if (oFilter == null)
            oNew = resultSet.createVariable(nameO)
        else
            oNew = null
        CoroutinesHelper.run {
            try {
                store.forEach(sFilter, pFilter, oFilter, { sv, pv, ov ->
                    val result = resultSet.createResultRow()
                    if (sNew != null)
                        result[sNew] = resultSet.createValue(store.resultSet.getValue(sv))
                    if (pNew != null)
                        result[pNew] = resultSet.createValue(store.resultSet.getValue(pv))
                    if (oNew != null)
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
