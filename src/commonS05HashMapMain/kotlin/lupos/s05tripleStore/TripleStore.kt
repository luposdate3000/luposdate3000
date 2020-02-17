package lupos.s05tripleStore

import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.GlobalLogger
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.OPBase
import lupos.s05tripleStore.POPTripleStoreIteratorBase


class TripleStoreIteratorLocalFilter : TripleStoreIteratorLocal {
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

    constructor(dictionary: ResultSetDictionary, store: TripleStoreLocal, index: EIndexPattern) : super(dictionary, store, index)

    fun setSFilterV(s: String) {
        sFilter = store.resultSet.createValue(s)
    }

    fun setPFilterV(p: String) {
        pFilter = store.resultSet.createValue(p)
    }

    fun setOFilterV(o: String) {
        oFilter = store.resultSet.createValue(o)
    }

    override fun evaluate() = Trace.trace<Unit>({ "TripleStoreIteratorLocalFilter.evaluate" }, {
        CoroutinesHelper.run {
try{
            for (value in iterator) {
                val result = resultSet.createResultRow()
                if (sFilter != null) {
                    if (value[sOld] != sFilter)
                        continue
                } else
                    result[sNew] = resultSet.createValue(store.resultSet.getValue(value[sOld]))
                if (pFilter != null) {
                    if (value[pOld] != pFilter)
                        continue
                } else
                    result[pNew] = resultSet.createValue(store.resultSet.getValue(value[pOld]))
                if (oFilter != null) {
                    if (value[oOld] != oFilter)
                        continue
                } else
                    result[oNew] = resultSet.createValue(store.resultSet.getValue(value[oOld]))
                channel.send(result)
            }
            channel.close()
}catch(e:Throwable){
            channel.close(e)
}
        }
    })
}

open class TripleStoreIteratorLocal : POPTripleStoreIteratorBase {
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf()
    override val resultSet: ResultSet
    val iterator: Iterator<ResultRow>
    var sNew: Variable
    var pNew: Variable
    var oNew: Variable
    val sOld: Variable
    val pOld: Variable
    val oOld: Variable
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
        iterator = store.tripleStore[index.ordinal].iterator()
        sOld = store.resultSet.createVariable("s")
        pOld = store.resultSet.createVariable("p")
        oOld = store.resultSet.createVariable("o")
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
try{
            for (value in iterator) {
                val result = resultSet.createResultRow()
                result[sNew] = resultSet.createValue(store.resultSet.getValue(value[sOld]))
                result[pNew] = resultSet.createValue(store.resultSet.getValue(value[pOld]))
                result[oNew] = resultSet.createValue(store.resultSet.getValue(value[oOld]))
                channel.send(result)
            }
            channel.close()
}catch(e:Throwable){
            channel.close(e)
}
        }
    })
}

class TripleStoreLocal {
    val resultSet = ResultSet(ResultSetDictionary())
    val s = resultSet.createVariable("s")
    val p = resultSet.createVariable("p")
    val o = resultSet.createVariable("o")
    val tripleStore = Array(EIndexPattern.values().size) { it -> mutableSetOf<ResultRow>() }
    val name: String

    val pendingModifications = Array(EIndexPattern.values().size) { it -> mutableMapOf<Long, MutableSet<Pair<EModifyType, ResultRow>>>() }

    fun modifyData(transactionID: Long, vals: Value, valp: Value, valo: Value, action: EModifyType, idx: EIndexPattern) = Trace.trace({ "TripleStoreLocal.modifyData" }, {
        var tmp = pendingModifications[idx.ordinal][transactionID]
        if (tmp == null) {
            tmp = mutableSetOf<Pair<EModifyType, ResultRow>>()
            pendingModifications[idx.ordinal][transactionID] = tmp
        }
        val r = resultSet.createResultRow()
        r[s] = vals
        r[p] = valp
        r[o] = valo
        tmp.add(Pair(action, r))
    })

    constructor(name: String) {
        this.name = name
    }

    fun clear() = Trace.trace({ "TripleStoreLocal.clear" }, {
        tripleStore.forEach() {
            it.clear()
        }
    })

    fun commit2(transactionID: Long) = Trace.trace({ "TripleStoreLocal.commit2" }, {
        EIndexPattern.values().forEach {
            val tmp = pendingModifications[it.ordinal][transactionID]
            if (tmp != null) {
                for (m in tmp) {
                    when (m.first) {
                        EModifyType.INSERT -> tripleStore[it.ordinal].add(m.second)
                        EModifyType.DELETE -> tripleStore[it.ordinal].remove(m.second)
                    }
                }
                pendingModifications[it.ordinal].remove(transactionID)
            }
        }
    })

    fun addData(transactionID: Long, ss: String, ps: String, os: String, idx: EIndexPattern) = Trace.trace({ "TripleStoreLocal.addData" }, {
        val vals = resultSet.createValue(ss)
        val valp = resultSet.createValue(ps)
        val valo = resultSet.createValue(os)
        modifyData(transactionID, vals, valp, valo, EModifyType.INSERT, idx)
    })

    fun deleteDataVar(transactionID: Long, ss: String, ps: String, os: String, sv: Boolean, pv: Boolean, ov: Boolean, idx: EIndexPattern) = Trace.trace({ "TripleStoreLocal.deleteDataVar" }, {
        val vals: Value = resultSet.createValue(ss)
        val valp: Value = resultSet.createValue(ps)
        val valo: Value = resultSet.createValue(os)
        var tmp = 0
        val row = resultSet.createResultRow()
        if (sv) {
            tmp++
            row[s] = vals
        }
        if (pv) {
            tmp++
            row[p] = valp
        }
        if (ov) {
            tmp++
            row[o] = valo
        }
        when (tmp) {
            3 -> modifyData(transactionID, vals, valp, valo, EModifyType.DELETE, idx)
            2 -> {
                if (!ov) {
                    val iterator = tripleStore[idx.ordinal].iterator()
                    while (iterator.hasNext()) {
                        val r = iterator.next()
                        if (r[s] == vals && r[p] == valp)
                            modifyData(transactionID, r[s], r[p], r[o], EModifyType.DELETE, idx)
                    }
                } else if (!pv) {
                    val iterator = tripleStore[idx.ordinal].iterator()
                    while (iterator.hasNext()) {
                        val r = iterator.next()
                        if (r[s] == vals && r[o] == valo)
                            modifyData(transactionID, r[s], r[p], r[o], EModifyType.DELETE, idx)
                    }
                } else {
                    val iterator = tripleStore[idx.ordinal].iterator()
                    while (iterator.hasNext()) {
                        val r = iterator.next()
                        if (r[p] == valp && r[o] == valo)
                            modifyData(transactionID, r[s], r[p], r[o], EModifyType.DELETE, idx)
                    }
                }
            }
            1 -> {
                if (ov) {
                    val iterator = tripleStore[idx.ordinal].iterator()
                    while (iterator.hasNext()) {
                        val r = iterator.next()
                        if (r[o] == valo)
                            modifyData(transactionID, r[s], r[p], r[o], EModifyType.DELETE, idx)
                    }
                } else if (pv) {
                    val iterator = tripleStore[idx.ordinal].iterator()
                    while (iterator.hasNext()) {
                        val r = iterator.next()
                        if (r[p] == valp)
                            modifyData(transactionID, r[s], r[p], r[o], EModifyType.DELETE, idx)
                    }
                } else {
                    val iterator = tripleStore[idx.ordinal].iterator()
                    while (iterator.hasNext()) {
                        val r = iterator.next()
                        if (r[s] == vals)
                            modifyData(transactionID, r[s], r[p], r[o], EModifyType.DELETE, idx)
                    }
                }
            }
            else -> {
                throw Exception("deleteDataVar $tmp")
            }
        }
    })

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, index: EIndexPattern): POPTripleStoreIteratorBase = Trace.trace({ "TripleStoreLocal.getIterator a" }, {
        return TripleStoreIteratorLocal(dictionary, this, index)
    }) as POPTripleStoreIteratorBase

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, s: String, p: String, o: String, index: EIndexPattern): POPTripleStoreIteratorBase = Trace.trace({ "TripleStoreLocal.getIterator b" }, {
        val res = TripleStoreIteratorLocal(dictionary, this, index)
        res.setMNameS(s)
        res.setMNameP(p)
        res.setMNameO(o)
        return res
    })

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, s: String, p: String, o: String, sv: Boolean, pv: Boolean, ov: Boolean, index: EIndexPattern): POPTripleStoreIteratorBase = Trace.trace({ "TripleStoreLocal.getIterator c" }, {
        GlobalLogger.log(ELoggerType.DEBUG, { "local get iterator :: $s $p $o $sv $pv $ov" })
        val res = TripleStoreIteratorLocalFilter(dictionary, this, index)
        if (sv)
            res.setSFilterV(s)
        else
            res.setMNameS(s)
        if (pv)
            res.setPFilterV(p)
        else
            res.setMNameP(p)
        if (ov)
            res.setOFilterV(o)
        else
            res.setMNameO(o)
        return res
    })
}
