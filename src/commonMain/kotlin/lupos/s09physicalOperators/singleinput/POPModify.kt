package lupos.s09physicalOperators.singleinput

import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowConsume
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore


class POPModify (override val dictionary: ResultSetDictionary,val transactionID: Long,val iri: String?,val insert: List<OPBase>,val delete: List<OPBase>, child: OPBase) : POPBase (){
    override val operatorID = EOperatorID.POPModifyID
    override val classname = "POPModify"
    override val resultSet= ResultSet(dictionary)
    override val children: Array<OPBase> = arrayOf(child)
    override fun equals(other: Any?): Boolean {
        if (other !is POPModify)
            return false
        if (dictionary !== other.dictionary)
            return false
        if (iri != other.iri)
            return false
        if (transactionID != other.transactionID)
            return false
        if (!insert.equals(other.insert))
            return false
        if (!delete.equals(other.delete))
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }
 override fun getProvidedVariableNames()=listOf<String>()

    override fun cloneOP() = POPModify(dictionary, transactionID, iri, insert, delete, children[0].cloneOP())

    fun evaluateRow(node: OPBase, row: ResultRow): AOPConstant {
        return (node as AOPBase).calculate(children[0].resultSet, row)
    }

    override fun evaluate() = Trace.trace<Unit>({ "POPModify.evaluate" }, {
        children[0].evaluate()
        CoroutinesHelper.run {
            try {
                for (row in children[0].channel) {
                    resultFlowConsume({ this@POPModify }, { children[0] }, { row })
                    for (i in insert) {
                        try {
                            when (i) {
                                is LOPTriple -> {
                                    val store = if (i.graph == null)
                                        DistributedTripleStore.getDefaultGraph()
                                    else {
                                        if (i.graphVar)
                                            DistributedTripleStore.getNamedGraph(children[0].resultSet.getValue(row[children[0].resultSet.createVariable(i.graph)])!!, true)
                                        else
                                            DistributedTripleStore.getNamedGraph(i.graph, true)
                                    }
                                    val data = mutableListOf<AOPConstant>()
                                    for (c in i.children)
                                        data.add(evaluateRow(c, row))
                                    store.addData(transactionID, data)
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} insert ${classNameToString(i)}")
                            }
                        } catch (e: Throwable) {
//ignore unbound variables
                        }
                    }
                    for (i in delete) {
                        try {
                            when (i) {
                                is LOPTriple -> {
                                    val store = if (i.graph == null)
                                        DistributedTripleStore.getDefaultGraph()
                                    else {
                                        if (i.graphVar)
                                            DistributedTripleStore.getNamedGraph(children[0].resultSet.getValue(row[children[0].resultSet.createVariable(i.graph)])!!, true)
                                        else
                                            DistributedTripleStore.getNamedGraph(i.graph, false)
                                    }
                                    val data = mutableListOf<AOPConstant>()
                                    for (c in i.children)
                                        data.add(evaluateRow(c, row))
                                    store.deleteData(transactionID, data)
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} insert ${classNameToString(i)}")
                            }
                        } catch (e: Throwable) {
//ignore unbound variables
                        }
                    }
                    channel.send(resultFlowProduce({ this@POPModify }, { resultSet.createResultRow() }))
                }
                channel.close()
                children[0].channel.close()
            } catch (e: Throwable) {
                channel.close(e)
                children[0].channel.close(e)
            }
        }
    })


}
