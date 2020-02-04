package lupos.s07physicalOperators

import lupos.s00misc.*
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s03buildOperatorGraph.data.*
import lupos.s03buildOperatorGraph.OPBase
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.ResultSet
import lupos.s06resultRepresentation.Variable
import lupos.s07physicalOperators.POPBase
import lupos.s07physicalOperators.POPBaseNullableIterator
import lupos.s08tripleStore.*


class POPGraphOperation(val transactionID: Long, val silent: Boolean, val graphref: ASTGraphRef, val action: GraphOperationType, val pstore: PersistentStore) : POPBase() {
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
            when (graphref) {
                is ASTAllGraphRef -> {
                    when (action) {
                        GraphOperationType.CREATE -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref)} CREATE")
                        GraphOperationType.CLEAR -> pstore.clearGraphAll()
                        GraphOperationType.DROP -> pstore.dropGraphAll()
                    }
                }
                is ASTDefaultGraphRef -> {
                    when (action) {
                        GraphOperationType.CREATE -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref)} CREATE")
                        GraphOperationType.CLEAR -> pstore.getDefaultGraph().truncate()
                        GraphOperationType.DROP -> pstore.getDefaultGraph().truncate()
                    }
                }
                is ASTIriGraphRef -> {
                    when (action) {
                        GraphOperationType.CREATE -> pstore.createGraph(graphref.iri)
                        GraphOperationType.CLEAR -> pstore.getNamedGraph(graphref.iri).truncate()
                        GraphOperationType.DROP -> pstore.dropGraph(graphref.iri)
                    }
                }
                is ASTNamedGraphRef -> {
                    pstore.getGraphNames().forEach { name ->
                        when (action) {
                            GraphOperationType.CREATE -> pstore.createGraph(name)
                            GraphOperationType.CLEAR -> pstore.getNamedGraph(name).truncate()
                            GraphOperationType.DROP -> pstore.dropGraph(name)
                        }
                    }
                }
                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref)}")
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
