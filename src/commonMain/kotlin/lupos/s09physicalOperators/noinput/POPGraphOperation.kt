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
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.GraphOperationType
import lupos.s04logicalOperators.OPBase
import lupos.s05tripleStore.PersistentStore
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPExpression
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.POPBaseNullableIterator


class POPGraphOperation(val transactionID: Long, val silent: Boolean, val graphref1: ASTGraphRef, val graphref2: ASTGraphRef?, val action: GraphOperationType, val pstore: PersistentStore) : POPBase() {
    private val resultSetNew = ResultSet()

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

    override fun next(): ResultRow {
        try {
            Trace.start("POPGraphOperation.next")
            first = false
            when (graphref1) {
                is ASTAllGraphRef -> {
                    when (action) {
                        GraphOperationType.CLEAR -> pstore.clearGraphAll()
                        GraphOperationType.DROP -> pstore.dropGraphAll()
                        else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                    }
                }
                is ASTDefaultGraphRef -> {
                    when (action) {
                        GraphOperationType.CLEAR -> pstore.getDefaultGraph().truncate()
                        GraphOperationType.DROP -> pstore.getDefaultGraph().truncate()
                        GraphOperationType.COPY -> {
                            when (graphref2) {
                                is ASTIriGraphRef -> {
                                    pstore.getNamedGraph(graphref2.iri).truncate()
                                    pstore.getNamedGraph(graphref2.iri).addData(transactionID, pstore.getDefaultGraph().getIterator())
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                            }
                        }
                        GraphOperationType.MOVE -> {
                            when (graphref2) {
                                is ASTIriGraphRef -> {
                                    pstore.getNamedGraph(graphref2.iri).truncate()
                                    pstore.getNamedGraph(graphref2.iri).addData(transactionID, pstore.getDefaultGraph().getIterator())
                                    pstore.getDefaultGraph().truncate()
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                            }
                        }
                        GraphOperationType.ADD -> {
                            when (graphref2) {
                                is ASTIriGraphRef -> {
                                    pstore.getNamedGraph(graphref2.iri).addData(transactionID, pstore.getDefaultGraph().getIterator())
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                            }
                        }
                        else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                    }
                }
                is ASTIriGraphRef -> {
                    when (action) {
                        GraphOperationType.CREATE -> pstore.createGraph(graphref1.iri)
                        GraphOperationType.CLEAR -> pstore.getNamedGraph(graphref1.iri).truncate()
                        GraphOperationType.DROP -> pstore.dropGraph(graphref1.iri)
                        GraphOperationType.COPY -> {
                            when (graphref2) {
                                is ASTIriGraphRef -> {
                                    pstore.getNamedGraph(graphref2.iri).truncate()
                                    pstore.getNamedGraph(graphref2.iri).addData(transactionID, pstore.getNamedGraph(graphref1.iri).getIterator())
                                }
                                is ASTDefaultGraphRef -> {
                                    pstore.getDefaultGraph().truncate()
                                    pstore.getDefaultGraph().addData(transactionID, pstore.getNamedGraph(graphref1.iri).getIterator())
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                            }
                        }
                        GraphOperationType.MOVE -> {
                            when (graphref2) {
                                is ASTIriGraphRef -> {
                                    pstore.getNamedGraph(graphref2.iri).truncate()
                                    pstore.getNamedGraph(graphref2.iri).addData(transactionID, pstore.getNamedGraph(graphref1.iri).getIterator())
                                    pstore.dropGraph(graphref1.iri)
                                }
                                is ASTDefaultGraphRef -> {
                                    pstore.getDefaultGraph().truncate()
                                    pstore.getDefaultGraph().addData(transactionID, pstore.getNamedGraph(graphref1.iri).getIterator())
                                    pstore.dropGraph(graphref1.iri)
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                            }
                        }
                        GraphOperationType.ADD -> {
                            when (graphref2) {
                                is ASTIriGraphRef -> {
                                    pstore.getNamedGraph(graphref2.iri).addData(transactionID, pstore.getNamedGraph(graphref1.iri).getIterator())
                                }
                                is ASTDefaultGraphRef -> {
                                    pstore.getDefaultGraph().addData(transactionID, pstore.getNamedGraph(graphref1.iri).getIterator())
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                            }
                        }
                        else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                    }
                }
                is ASTNamedGraphRef -> {
                    pstore.getGraphNames().forEach { name ->
                        when (action) {
                            GraphOperationType.CREATE -> pstore.createGraph(name)
                            GraphOperationType.CLEAR -> pstore.getNamedGraph(name).truncate()
                            GraphOperationType.DROP -> pstore.dropGraph(name)
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
