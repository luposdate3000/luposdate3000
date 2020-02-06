package lupos.s09physicalOperators.noinput
import lupos.s03resultRepresentation.*

import lupos.s00misc.classNameToString
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s02buildSyntaxTree.sparql1_1.ASTAllGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTDefaultGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTIriGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTNamedGraphRef
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.noinput.GraphOperationType
import lupos.s04logicalOperators.OPBase
import lupos.s05tripleStore.PersistentStore
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPExpression
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.POPBaseNullableIterator


class POPGraphOperation(override val dictionary:ResultSetDictionary,val transactionID: Long, val silent: Boolean, val graphref1: ASTGraphRef, val graphref2: ASTGraphRef?, val action: GraphOperationType, val pstore: PersistentStore) : POPBase() {
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

    fun i2s(iri: ASTIriGraphRef): String {
        return iri.iri
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
                                    pstore.getNamedGraph(i2s(graphref2), true).truncate()
                                    pstore.getNamedGraph(i2s(graphref2), true).addData(transactionID, pstore.getDefaultGraph().getIterator(dictionary,"s", "p", "o"))
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                            }
                        }
                        GraphOperationType.MOVE -> {
                            when (graphref2) {
                                is ASTIriGraphRef -> {
                                    pstore.getNamedGraph(i2s(graphref2), true).truncate()
                                    pstore.getNamedGraph(i2s(graphref2), true).addData(transactionID, pstore.getDefaultGraph().getIterator(dictionary,"s", "p", "o"))
                                    pstore.getDefaultGraph().truncate()
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                            }
                        }
                        GraphOperationType.ADD -> {
                            when (graphref2) {
                                is ASTIriGraphRef -> {
                                    pstore.getNamedGraph(i2s(graphref2), true).addData(transactionID, pstore.getDefaultGraph().getIterator(dictionary,"s", "p", "o"))
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                            }
                        }
                        else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                    }
                }
                is ASTIriGraphRef -> {
                    when (action) {
                        GraphOperationType.CREATE -> pstore.createGraph(i2s(graphref1))
                        GraphOperationType.CLEAR -> pstore.getNamedGraph(i2s(graphref1), true).truncate()
                        GraphOperationType.DROP -> pstore.dropGraph(i2s(graphref1))
                        GraphOperationType.COPY -> {
                            when (graphref2) {
                                is ASTIriGraphRef -> {
                                    if (i2s(graphref2) != i2s(graphref1)) {
                                        pstore.getNamedGraph(i2s(graphref2), true).truncate()
                                        pstore.getNamedGraph(i2s(graphref2), true).addData(transactionID, pstore.getNamedGraph(i2s(graphref1)).getIterator(dictionary,"s", "p", "o"))
                                    }
                                }
                                is ASTDefaultGraphRef -> {
                                    pstore.getDefaultGraph().truncate()
                                    pstore.getDefaultGraph().addData(transactionID, pstore.getNamedGraph(i2s(graphref1)).getIterator(dictionary,"s", "p", "o"))
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                            }
                        }
                        GraphOperationType.MOVE -> {
                            when (graphref2) {
                                is ASTIriGraphRef -> {
                                    if (i2s(graphref2) != i2s(graphref1)) {
                                        pstore.getNamedGraph(i2s(graphref2), true).truncate()
                                        pstore.getNamedGraph(i2s(graphref2), true).addData(transactionID, pstore.getNamedGraph(i2s(graphref1)).getIterator(dictionary,"s", "p", "o"))
                                        pstore.dropGraph(i2s(graphref1))
                                    }
                                }
                                is ASTDefaultGraphRef -> {
                                    pstore.getDefaultGraph().truncate()
                                    pstore.getDefaultGraph().addData(transactionID, pstore.getNamedGraph(i2s(graphref1)).getIterator(dictionary,"s", "p", "o"))
                                    pstore.dropGraph(i2s(graphref1))
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                            }
                        }
                        GraphOperationType.ADD -> {
                            when (graphref2) {
                                is ASTIriGraphRef -> {
                                    if (i2s(graphref2) != i2s(graphref1))
                                        pstore.getNamedGraph(i2s(graphref2), true).addData(transactionID, pstore.getNamedGraph(i2s(graphref1)).getIterator(dictionary,"s", "p", "o"))
                                }
                                is ASTDefaultGraphRef -> {
                                    pstore.getDefaultGraph().addData(transactionID, pstore.getNamedGraph(i2s(graphref1)).getIterator(dictionary,"s", "p", "o"))
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
                            GraphOperationType.CLEAR -> pstore.getNamedGraph(name, true).truncate()
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
