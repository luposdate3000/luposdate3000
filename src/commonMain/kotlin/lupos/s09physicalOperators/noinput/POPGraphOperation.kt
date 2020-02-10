package lupos.s09physicalOperators.noinput

import lupos.s00misc.classNameToString
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.ASTAllGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTDefaultGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTIriGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTNamedGraphRef
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.GraphOperationType
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore


class POPGraphOperation(override val dictionary: ResultSetDictionary, val transactionID: Long, val silent: Boolean, val graphref1: ASTGraphRef, val graphref2: ASTGraphRef?, val action: GraphOperationType) : POPBase() {
    override val children: Array<OPBase> = arrayOf()
    private val resultSetNew = ResultSet(dictionary)

    private var first = true

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun hasNext(): Boolean {
        try {
            Trace.start("POPGraphOperation.hasNext")
            return first
        } finally {
            Trace.stop("POPGraphOperation.hasNext")
        }
    }

    inline fun i2s(iri: ASTIriGraphRef): String {
        return iri.iri
    }

    override fun next(): ResultRow {
        try {
            Trace.start("POPGraphOperation.next")
            first = false
            when (graphref1) {
                is ASTAllGraphRef -> {
                    when (action) {
                        GraphOperationType.CLEAR -> {
                            for (s in DistributedTripleStore.getGraphNames(true)) {
                                DistributedTripleStore.clearGraph(s)
                            }
                        }
                        GraphOperationType.DROP -> {
                            DistributedTripleStore.clearGraph(DistributedTripleStore.localStore.defaultGraphName)
                            for (s in DistributedTripleStore.getGraphNames(false)) {
                                DistributedTripleStore.dropGraph(s)
                            }
                        }
                        else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                    }
                }
                is ASTDefaultGraphRef -> {
                    when (action) {
                        GraphOperationType.CLEAR -> DistributedTripleStore.getDefaultGraph().clear()
                        GraphOperationType.DROP -> DistributedTripleStore.getDefaultGraph().clear()
                        GraphOperationType.COPY -> {
                            when (graphref2) {
                                is ASTIriGraphRef -> {
                                    DistributedTripleStore.getNamedGraph(i2s(graphref2), true).clear()
                                    DistributedTripleStore.getNamedGraph(i2s(graphref2), true).addData(transactionID, DistributedTripleStore.getDefaultGraph().getIterator(dictionary, "s", "p", "o"))
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                            }
                        }
                        GraphOperationType.MOVE -> {
                            when (graphref2) {
                                is ASTIriGraphRef -> {
                                    DistributedTripleStore.getNamedGraph(i2s(graphref2), true).clear()
                                    DistributedTripleStore.getNamedGraph(i2s(graphref2), true).addData(transactionID, DistributedTripleStore.getDefaultGraph().getIterator(dictionary, "s", "p", "o"))
                                    DistributedTripleStore.getDefaultGraph().clear()
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                            }
                        }
                        GraphOperationType.ADD -> {
                            when (graphref2) {
                                is ASTIriGraphRef -> {
                                    DistributedTripleStore.getNamedGraph(i2s(graphref2), true).addData(transactionID, DistributedTripleStore.getDefaultGraph().getIterator(dictionary, "s", "p", "o"))
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                            }
                        }
                        else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                    }
                }
                is ASTIriGraphRef -> {
                    when (action) {
                        GraphOperationType.CREATE -> DistributedTripleStore.createGraph(i2s(graphref1))
                        GraphOperationType.CLEAR -> DistributedTripleStore.getNamedGraph(i2s(graphref1), true).clear()
                        GraphOperationType.DROP -> DistributedTripleStore.dropGraph(i2s(graphref1))
                        GraphOperationType.COPY -> {
                            when (graphref2) {
                                is ASTIriGraphRef -> {
                                    if (i2s(graphref2) != i2s(graphref1)) {
                                        DistributedTripleStore.getNamedGraph(i2s(graphref2), true).clear()
                                        DistributedTripleStore.getNamedGraph(i2s(graphref2), true).addData(transactionID, DistributedTripleStore.getNamedGraph(i2s(graphref1)).getIterator(dictionary, "s", "p", "o"))
                                    }
                                }
                                is ASTDefaultGraphRef -> {
                                    DistributedTripleStore.getDefaultGraph().clear()
                                    DistributedTripleStore.getDefaultGraph().addData(transactionID, DistributedTripleStore.getNamedGraph(i2s(graphref1)).getIterator(dictionary, "s", "p", "o"))
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                            }
                        }
                        GraphOperationType.MOVE -> {
                            when (graphref2) {
                                is ASTIriGraphRef -> {
                                    if (i2s(graphref2) != i2s(graphref1)) {
                                        DistributedTripleStore.getNamedGraph(i2s(graphref2), true).clear()
                                        DistributedTripleStore.getNamedGraph(i2s(graphref2), true).addData(transactionID, DistributedTripleStore.getNamedGraph(i2s(graphref1)).getIterator(dictionary, "s", "p", "o"))
                                        DistributedTripleStore.dropGraph(i2s(graphref1))
                                    }
                                }
                                is ASTDefaultGraphRef -> {
                                    DistributedTripleStore.getDefaultGraph().clear()
                                    DistributedTripleStore.getDefaultGraph().addData(transactionID, DistributedTripleStore.getNamedGraph(i2s(graphref1)).getIterator(dictionary, "s", "p", "o"))
                                    DistributedTripleStore.dropGraph(i2s(graphref1))
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                            }
                        }
                        GraphOperationType.ADD -> {
                            when (graphref2) {
                                is ASTIriGraphRef -> {
                                    if (i2s(graphref2) != i2s(graphref1))
                                        DistributedTripleStore.getNamedGraph(i2s(graphref2), true).addData(transactionID, DistributedTripleStore.getNamedGraph(i2s(graphref1)).getIterator(dictionary, "s", "p", "o"))
                                }
                                is ASTDefaultGraphRef -> {
                                    DistributedTripleStore.getDefaultGraph().addData(transactionID, DistributedTripleStore.getNamedGraph(i2s(graphref1)).getIterator(dictionary, "s", "p", "o"))
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                            }
                        }
                        else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                    }
                }
                is ASTNamedGraphRef -> {
                    DistributedTripleStore.getGraphNames().forEach { name ->
                        when (action) {
                            GraphOperationType.CREATE -> DistributedTripleStore.createGraph(name)
                            GraphOperationType.CLEAR -> DistributedTripleStore.getNamedGraph(name, true).clear()
                            GraphOperationType.DROP -> DistributedTripleStore.dropGraph(name)
                            GraphOperationType.COPY -> {
                                when (graphref2) {
                                    else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                                }
                            }
                            GraphOperationType.ADD -> {
                                when (graphref2) {
                                    else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                                }
                            }
                            else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                        }
                    }
                }
                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)}")
            }
            return resultSetNew.createResultRow()
        } catch (e: Throwable) {
            if (!silent)
                throw e
            return resultSetNew.createResultRow()
        } finally {
            Trace.stop("POPGraphOperation.next")
        }
    }

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPGraphOperation")
        return res
    }
}
