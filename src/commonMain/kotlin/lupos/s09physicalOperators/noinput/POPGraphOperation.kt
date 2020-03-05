package lupos.s09physicalOperators.noinput

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
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s05tripleStore.*
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore


class POPGraphOperation(override val dictionary: ResultSetDictionary,
                        val transactionID: Long,
                        val silent: Boolean,
                        var graph1type: EGraphRefType = EGraphRefType.DefaultGraphRef,
                        var graph1iri: String? = null,
                        var graph2type: EGraphRefType = EGraphRefType.DefaultGraphRef,
                        var graph2iri: String? = null,
                        val action: EGraphOperationType) : POPBase() {
    override val operatorID = EOperatorID.POPGraphOperationID
    override val classname = "POPGraphOperation"
    override val children: Array<OPBase> = arrayOf()
    override val resultSet = ResultSet(dictionary)

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
        }
        if (action == EGraphOperationType.COPY || action == EGraphOperationType.MOVE || action == EGraphOperationType.ADD) {
            res += " TO "
            when (graph2type) {
                EGraphRefType.AllGraphRef -> res += "ALL"
                EGraphRefType.DefaultGraphRef -> res += "DEFAULT"
                EGraphRefType.NamedGraphRef -> res += "NAMED"
                EGraphRefType.IriGraphRef -> res += "GRAPH <" + graph2iri!! + ">"
            }
        }
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is POPGraphOperation)
            return false
        if (dictionary !== other.dictionary)
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

    override fun cloneOP() = POPGraphOperation(dictionary, transactionID, silent, graph1type, graph1iri, graph2type, graph2iri, action)

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
                                    DistributedTripleStore.clearGraph(s)
                                }
                            }
                            EGraphOperationType.DROP -> {
                                DistributedTripleStore.clearGraph(PersistentStoreLocal.defaultGraphName)
                                for (s in DistributedTripleStore.getGraphNames(false)) {
                                    DistributedTripleStore.dropGraph(s)
                                }
                            }
                            else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${graph1type} ${graph2type} $action")
                        }
                    }
                    EGraphRefType.DefaultGraphRef -> {
                        when (action) {
                            EGraphOperationType.CLEAR -> DistributedTripleStore.clearGraph(PersistentStoreLocal.defaultGraphName)
                            EGraphOperationType.DROP -> DistributedTripleStore.clearGraph(PersistentStoreLocal.defaultGraphName)
                            EGraphOperationType.COPY -> {
                                when (graph2type) {
                                    EGraphRefType.IriGraphRef -> {
                                        try {
                                            DistributedTripleStore.clearGraph(graph2iri!!)
                                        } catch (e: Throwable) {
                                        }
                                        DistributedTripleStore.getNamedGraph(graph2iri!!, true).addData(transactionID, DistributedTripleStore.getDefaultGraph().getIterator(transactionID, dictionary, AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), EIndexPattern.SPO))
                                    }
                                    else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${graph1type} ${graph2type} $action")
                                }
                            }
                            EGraphOperationType.MOVE -> {
                                when (graph2type) {
                                    EGraphRefType.IriGraphRef -> {
                                        try {
                                            DistributedTripleStore.clearGraph(graph2iri!!)
                                        } catch (e: Throwable) {
                                        }
                                        DistributedTripleStore.getNamedGraph(graph2iri!!, true).addData(transactionID, DistributedTripleStore.getDefaultGraph().getIterator(transactionID, dictionary, AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), EIndexPattern.SPO))
                                        DistributedTripleStore.clearGraph(PersistentStoreLocal.defaultGraphName)
                                    }
                                    else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${graph1type} ${graph2type} $action")
                                }
                            }
                            EGraphOperationType.ADD -> {
                                when (graph2type) {
                                    EGraphRefType.IriGraphRef -> {
                                        DistributedTripleStore.getNamedGraph(graph2iri!!, true).addData(transactionID, DistributedTripleStore.getDefaultGraph().getIterator(transactionID, dictionary, AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), EIndexPattern.SPO))
                                    }
                                    else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${graph1type} ${graph2type} $action")
                                }
                            }
                            else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${graph1type} ${graph2type} $action")
                        }
                    }
                    EGraphRefType.IriGraphRef -> {
                        when (action) {
                            EGraphOperationType.CREATE -> DistributedTripleStore.createGraph(graph1iri!!)
                            EGraphOperationType.CLEAR -> {
                                try {
                                    DistributedTripleStore.clearGraph(graph1iri!!)
                                } catch (e: Throwable) {
                                }
                            }
                            EGraphOperationType.DROP -> DistributedTripleStore.dropGraph(graph1iri!!)
                            EGraphOperationType.COPY -> {
                                when (graph2type) {
                                    EGraphRefType.IriGraphRef -> {
                                        if (graph2iri!! != graph1iri!!) {
                                            try {
                                                DistributedTripleStore.clearGraph(graph2iri!!)
                                            } catch (e: Throwable) {
                                            }
                                            DistributedTripleStore.getNamedGraph(graph2iri!!, true).addData(transactionID, DistributedTripleStore.getNamedGraph(graph1iri!!).getIterator(transactionID, dictionary, AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), EIndexPattern.SPO))
                                        }
                                    }
                                    EGraphRefType.DefaultGraphRef -> {
                                        DistributedTripleStore.clearGraph(PersistentStoreLocal.defaultGraphName)
                                        DistributedTripleStore.getDefaultGraph().addData(transactionID, DistributedTripleStore.getNamedGraph(graph1iri!!).getIterator(transactionID, dictionary, AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), EIndexPattern.SPO))
                                    }
                                    else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${graph1type} ${graph2type} $action")
                                }
                            }
                            EGraphOperationType.MOVE -> {
                                when (graph2type) {
                                    EGraphRefType.IriGraphRef -> {
                                        if (graph2iri!! != graph1iri!!) {
                                            try {
                                                DistributedTripleStore.clearGraph(graph2iri!!)
                                            } catch (e: Throwable) {
                                            }
                                            DistributedTripleStore.getNamedGraph(graph2iri!!, true).addData(transactionID, DistributedTripleStore.getNamedGraph(graph1iri!!).getIterator(transactionID, dictionary, AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), EIndexPattern.SPO))
                                            DistributedTripleStore.dropGraph(graph1iri!!)
                                        }
                                    }
                                    EGraphRefType.DefaultGraphRef -> {
                                        DistributedTripleStore.clearGraph(PersistentStoreLocal.defaultGraphName)
                                        DistributedTripleStore.getDefaultGraph().addData(transactionID, DistributedTripleStore.getNamedGraph(graph1iri!!).getIterator(transactionID, dictionary, AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), EIndexPattern.SPO))
                                        DistributedTripleStore.dropGraph(graph1iri!!)
                                    }
                                    else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${graph1type} ${graph2type} $action")
                                }
                            }
                            EGraphOperationType.ADD -> {
                                when (graph2type) {
                                    EGraphRefType.IriGraphRef -> {
                                        if (graph2iri!! != graph1iri!!)
                                            DistributedTripleStore.getNamedGraph(graph2iri!!, true).addData(transactionID, DistributedTripleStore.getNamedGraph(graph1iri!!).getIterator(transactionID, dictionary, AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), EIndexPattern.SPO))
                                    }
                                    EGraphRefType.DefaultGraphRef -> {
                                        DistributedTripleStore.getDefaultGraph().addData(transactionID, DistributedTripleStore.getNamedGraph(graph1iri!!).getIterator(transactionID, dictionary, AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), EIndexPattern.SPO))
                                    }
                                    else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${graph1type} ${graph2type} $action")
                                }
                            }
                            else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${graph1type} ${graph2type} $action")
                        }
                    }
                    EGraphRefType.NamedGraphRef -> {
                        DistributedTripleStore.getGraphNames().forEach { name ->
                            when (action) {
                                EGraphOperationType.CREATE -> DistributedTripleStore.createGraph(name)
                                EGraphOperationType.CLEAR -> {
                                    try {
                                        DistributedTripleStore.clearGraph(name)
                                    } catch (e: Throwable) {
                                    }
                                }
                                EGraphOperationType.DROP -> DistributedTripleStore.dropGraph(name)
                                EGraphOperationType.COPY -> {
                                    when (graph2type) {
                                        else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${graph1type} ${graph2type} $action")
                                    }
                                }
                                EGraphOperationType.ADD -> {
                                    when (graph2type) {
                                        else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${graph1type} ${graph2type} $action")
                                    }
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${graph1type} ${graph2type} $action")
                            }
                        }
                    }
                    else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${graph1type} ${graph2type}")
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
