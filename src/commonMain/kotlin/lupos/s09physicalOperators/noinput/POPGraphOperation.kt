package lupos.s09physicalOperators.noinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowProduce
import lupos.s02buildSyntaxTree.sparql1_1.ASTAllGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTDefaultGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTIriGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTNamedGraphRef
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.*
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore


class POPGraphOperation(query: Query,
                        val silent: Boolean,
                        var graph1type: EGraphRefType = EGraphRefType.DefaultGraphRef,
                        var graph1iri: String? = null,
                        var graph2type: EGraphRefType = EGraphRefType.DefaultGraphRef,
                        var graph2iri: String? = null,
                        val action: EGraphOperationType) : POPBase(query, EOperatorID.POPGraphOperationID, "POPGraphOperation", ResultSet(query.dictionary), arrayOf()) {

    override fun toSparqlQuery() = toSparql()
    override fun toSparql(): String {
        var res = ""
        when (action) {
            EGraphOperationType.CLEAR -> res += "CLEAR"
            EGraphOperationType.DROP -> res += "DROP"
            EGraphOperationType.CREATE -> res += "CREATE"
            EGraphOperationType.COPY -> res += "COPY"
            EGraphOperationType.MOVE -> res += "MOVE"
            EGraphOperationType.ADD -> res += "ADD"
            else -> require(false)
        }
        if (silent)
            res += " SILENT "
        else
            res += " "
        when (graph1type) {
            EGraphRefType.AllGraphRef -> res += "ALL"
            EGraphRefType.DefaultGraphRef -> res += "DEFAULT"
            EGraphRefType.NamedGraphRef -> res += "NAMED"
            EGraphRefType.IriGraphRef -> res += "GRAPH <" + graph1iri!! + ">"
            else -> require(false)
        }
        if (action == EGraphOperationType.COPY || action == EGraphOperationType.MOVE || action == EGraphOperationType.ADD) {
            res += " TO "
            when (graph2type) {
                EGraphRefType.AllGraphRef -> res += "ALL"
                EGraphRefType.DefaultGraphRef -> res += "DEFAULT"
                EGraphRefType.NamedGraphRef -> res += "NAMED"
                EGraphRefType.IriGraphRef -> res += "GRAPH <" + graph2iri!! + ">"
                else -> require(false)
            }
        }
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is POPGraphOperation)
            return false
        if (silent != other.silent)
            return false
        if (graph1iri != other.graph1iri)
            return false
        if (graph1type != other.graph1type)
            return false
        if (graph2iri != other.graph2iri)
            return false
        if (graph2type != other.graph2type)
            return false
        if (action != other.action)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    override fun cloneOP() = POPGraphOperation(query, silent, graph1type, graph1iri, graph2type, graph2iri, action)

    fun i2s(iri: ASTIriGraphRef): String {
        return iri.iri
    }

    override fun evaluate() = Trace.trace<Channel<ResultRow>>({ "POPGraphOperation.evaluate" }, {
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                when (graph1type) {
                    EGraphRefType.AllGraphRef -> {
                        when (action) {
                            EGraphOperationType.CLEAR -> {
                                for (s in DistributedTripleStore.getGraphNames(true)) {
                                    DistributedTripleStore.clearGraph(query, s)
                                }
                            }
                            EGraphOperationType.DROP -> {
                                DistributedTripleStore.clearGraph(query, PersistentStoreLocal.defaultGraphName)
                                for (s in DistributedTripleStore.getGraphNames(false)) {
                                    DistributedTripleStore.dropGraph(query, s)
                                }
                            }
                            else -> require(false)
                        }
                    }
                    EGraphRefType.DefaultGraphRef -> {
                        when (action) {
                            EGraphOperationType.CLEAR -> DistributedTripleStore.clearGraph(query, PersistentStoreLocal.defaultGraphName)
                            EGraphOperationType.DROP -> DistributedTripleStore.clearGraph(query, PersistentStoreLocal.defaultGraphName)
                            EGraphOperationType.COPY -> {
                                when (graph2type) {
                                    EGraphRefType.DefaultGraphRef -> {/*noop*/
                                    }
                                    EGraphRefType.IriGraphRef -> {
                                        try {
                                            DistributedTripleStore.clearGraph(query, graph2iri!!)
                                        } catch (e: Throwable) {
                                        }
                                        DistributedTripleStore.getNamedGraph(query, graph2iri!!, true).addData(DistributedTripleStore.getDefaultGraph(query).getIterator(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o"), EIndexPattern.SPO))
                                    }
                                    else -> require(false)
                                }
                            }
                            EGraphOperationType.MOVE -> {
                                when (graph2type) {
                                    EGraphRefType.DefaultGraphRef -> {/*noop*/
                                    }
                                    EGraphRefType.IriGraphRef -> {
                                        try {
                                            DistributedTripleStore.clearGraph(query, graph2iri!!)
                                        } catch (e: Throwable) {
                                        }
                                        DistributedTripleStore.getNamedGraph(query, graph2iri!!, true).addData(DistributedTripleStore.getDefaultGraph(query).getIterator(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o"), EIndexPattern.SPO))
                                        DistributedTripleStore.clearGraph(query, PersistentStoreLocal.defaultGraphName)
                                    }
                                    else -> require(false)
                                }
                            }
                            EGraphOperationType.ADD -> {
                                when (graph2type) {
                                    EGraphRefType.DefaultGraphRef -> {/*noop*/
                                    }
                                    EGraphRefType.IriGraphRef -> {
                                        DistributedTripleStore.getNamedGraph(query, graph2iri!!, true).addData(DistributedTripleStore.getDefaultGraph(query).getIterator(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o"), EIndexPattern.SPO))
                                    }
                                    else -> require(false)
                                }
                            }
                            else -> require(false)
                        }
                    }
                    EGraphRefType.IriGraphRef -> {
                        when (action) {
                            EGraphOperationType.CREATE -> DistributedTripleStore.createGraph(query, graph1iri!!)
                            EGraphOperationType.CLEAR -> {
                                try {
                                    DistributedTripleStore.clearGraph(query, graph1iri!!)
                                } catch (e: Throwable) {
                                }
                            }
                            EGraphOperationType.DROP -> DistributedTripleStore.dropGraph(query, graph1iri!!)
                            EGraphOperationType.COPY -> {
                                when (graph2type) {
                                    EGraphRefType.IriGraphRef -> {
                                        if (graph2iri!! != graph1iri!!) {
                                            try {
                                                DistributedTripleStore.clearGraph(query, graph2iri!!)
                                            } catch (e: Throwable) {
                                            }
                                            DistributedTripleStore.getNamedGraph(query, graph2iri!!, true).addData(DistributedTripleStore.getNamedGraph(query, graph1iri!!).getIterator(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o"), EIndexPattern.SPO))
                                        }
                                    }
                                    EGraphRefType.DefaultGraphRef -> {
                                        DistributedTripleStore.clearGraph(query, PersistentStoreLocal.defaultGraphName)
                                        DistributedTripleStore.getDefaultGraph(query).addData(DistributedTripleStore.getNamedGraph(query, graph1iri!!).getIterator(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o"), EIndexPattern.SPO))
                                    }
                                    else -> require(false)
                                }
                            }
                            EGraphOperationType.MOVE -> {
                                when (graph2type) {
                                    EGraphRefType.IriGraphRef -> {
                                        if (graph2iri!! != graph1iri!!) {
                                            try {
                                                DistributedTripleStore.clearGraph(query, graph2iri!!)
                                            } catch (e: Throwable) {
                                            }
                                            DistributedTripleStore.getNamedGraph(query, graph2iri!!, true).addData(DistributedTripleStore.getNamedGraph(query, graph1iri!!).getIterator(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o"), EIndexPattern.SPO))
                                            DistributedTripleStore.dropGraph(query, graph1iri!!)
                                        }
                                    }
                                    EGraphRefType.DefaultGraphRef -> {
                                        DistributedTripleStore.clearGraph(query, PersistentStoreLocal.defaultGraphName)
                                        DistributedTripleStore.getDefaultGraph(query).addData(DistributedTripleStore.getNamedGraph(query, graph1iri!!).getIterator(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o"), EIndexPattern.SPO))
                                        DistributedTripleStore.dropGraph(query, graph1iri!!)
                                    }
                                    else -> require(false)
                                }
                            }
                            EGraphOperationType.ADD -> {
                                when (graph2type) {
                                    EGraphRefType.IriGraphRef -> {
                                        if (graph2iri!! != graph1iri!!)
                                            DistributedTripleStore.getNamedGraph(query, graph2iri!!, true).addData(DistributedTripleStore.getNamedGraph(query, graph1iri!!).getIterator(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o"), EIndexPattern.SPO))
                                    }
                                    EGraphRefType.DefaultGraphRef -> {
                                        DistributedTripleStore.getDefaultGraph(query).addData(DistributedTripleStore.getNamedGraph(query, graph1iri!!).getIterator(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o"), EIndexPattern.SPO))
                                    }
                                    else -> require(false)
                                }
                            }
                            else -> require(false)
                        }
                    }
                    EGraphRefType.NamedGraphRef -> {
                        DistributedTripleStore.getGraphNames().forEach { name ->
                            when (action) {
                                EGraphOperationType.CLEAR -> {
                                    try {
                                        DistributedTripleStore.clearGraph(query, name)
                                    } catch (e: Throwable) {
                                    }
                                }
                                EGraphOperationType.DROP -> DistributedTripleStore.dropGraph(query, name)
                                else -> require(false)
                            }
                        }
                    }
                    else -> require(false)
                }
            } catch (e: Throwable) {
                if (!silent) {
                    channel.close(e)
                }
            }
            try {
                channel.send(resultFlowProduce({ this@POPGraphOperation }, { resultSet.createResultRow() }))
                channel.close()
            } catch (e: Throwable) {
                channel.close(e)
            }
        }
        return channel
    })

}
