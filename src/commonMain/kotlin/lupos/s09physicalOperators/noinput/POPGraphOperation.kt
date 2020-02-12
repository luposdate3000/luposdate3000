package lupos.s09physicalOperators.noinput

import lupos.s00misc.classNameToString
import lupos.s00misc.*
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
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore


class POPGraphOperation(override val dictionary: ResultSetDictionary, val transactionID: Long, val silent: Boolean, val graphref1: ASTGraphRef, val graphref2: ASTGraphRef?, val action: EGraphOperationType) : POPBase() {
    override val children: Array<OPBase> = arrayOf()
    private val resultSetNew = ResultSet(dictionary)

    private var first = true

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun hasNext(): Boolean =            Trace.trace("POPGraphOperation.hasNext"){
            return first
    }as Boolean

    inline fun i2s(iri: ASTIriGraphRef): String {
        return iri.iri
    }

    override fun next(): ResultRow =            Trace.trace("POPGraphOperation.next"){
try{
            first = false
            when (graphref1) {
                is ASTAllGraphRef -> {
                    when (action) {
                        EGraphOperationType.CLEAR -> {
                            for (s in DistributedTripleStore.getGraphNames(true)) {
                                DistributedTripleStore.clearGraph(s)
                            }
                        }
                        EGraphOperationType.DROP -> {
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
                        EGraphOperationType.CLEAR -> DistributedTripleStore.clearGraph(DistributedTripleStore.localStore.defaultGraphName)
                        EGraphOperationType.DROP -> DistributedTripleStore.clearGraph(DistributedTripleStore.localStore.defaultGraphName)
                        EGraphOperationType.COPY -> {
                            when (graphref2) {
                                is ASTIriGraphRef -> {
                                    try {
                                        DistributedTripleStore.clearGraph(i2s(graphref2))
                                    } catch (e: Throwable) {
                                    }
                                    DistributedTripleStore.getNamedGraph(i2s(graphref2), true).addData(transactionID, DistributedTripleStore.getDefaultGraph().getIterator(transactionID, dictionary, "s", "p", "o"))
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                            }
                        }
                        EGraphOperationType.MOVE -> {
                            when (graphref2) {
                                is ASTIriGraphRef -> {
                                    try {
                                        DistributedTripleStore.clearGraph(i2s(graphref2))
                                    } catch (e: Throwable) {
                                    }
                                    DistributedTripleStore.getNamedGraph(i2s(graphref2), true).addData(transactionID, DistributedTripleStore.getDefaultGraph().getIterator(transactionID, dictionary, "s", "p", "o"))
                                    DistributedTripleStore.clearGraph(DistributedTripleStore.localStore.defaultGraphName)
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                            }
                        }
                        EGraphOperationType.ADD -> {
                            when (graphref2) {
                                is ASTIriGraphRef -> {
                                    DistributedTripleStore.getNamedGraph(i2s(graphref2), true).addData(transactionID, DistributedTripleStore.getDefaultGraph().getIterator(transactionID, dictionary, "s", "p", "o"))
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                            }
                        }
                        else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                    }
                }
                is ASTIriGraphRef -> {
                    when (action) {
                        EGraphOperationType.CREATE -> DistributedTripleStore.createGraph(i2s(graphref1))
                        EGraphOperationType.CLEAR -> {
                            try {
                                DistributedTripleStore.clearGraph(i2s(graphref1))
                            } catch (e: Throwable) {
                            }
                        }
                        EGraphOperationType.DROP -> DistributedTripleStore.dropGraph(i2s(graphref1))
                        EGraphOperationType.COPY -> {
                            when (graphref2) {
                                is ASTIriGraphRef -> {
                                    if (i2s(graphref2) != i2s(graphref1)) {
                                        try {
                                            DistributedTripleStore.clearGraph(i2s(graphref2))
                                        } catch (e: Throwable) {
                                        }
                                        DistributedTripleStore.getNamedGraph(i2s(graphref2), true).addData(transactionID, DistributedTripleStore.getNamedGraph(i2s(graphref1)).getIterator(transactionID, dictionary, "s", "p", "o"))
                                    }
                                }
                                is ASTDefaultGraphRef -> {
                                    DistributedTripleStore.clearGraph(DistributedTripleStore.localStore.defaultGraphName)
                                    DistributedTripleStore.getDefaultGraph().addData(transactionID, DistributedTripleStore.getNamedGraph(i2s(graphref1)).getIterator(transactionID, dictionary, "s", "p", "o"))
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                            }
                        }
                        EGraphOperationType.MOVE -> {
                            when (graphref2) {
                                is ASTIriGraphRef -> {
                                    if (i2s(graphref2) != i2s(graphref1)) {
                                        try {
                                            DistributedTripleStore.clearGraph(i2s(graphref2))
                                        } catch (e: Throwable) {
                                        }
                                        DistributedTripleStore.getNamedGraph(i2s(graphref2), true).addData(transactionID, DistributedTripleStore.getNamedGraph(i2s(graphref1)).getIterator(transactionID, dictionary, "s", "p", "o"))
                                        DistributedTripleStore.dropGraph(i2s(graphref1))
                                    }
                                }
                                is ASTDefaultGraphRef -> {
                                    DistributedTripleStore.clearGraph(DistributedTripleStore.localStore.defaultGraphName)
                                    DistributedTripleStore.getDefaultGraph().addData(transactionID, DistributedTripleStore.getNamedGraph(i2s(graphref1)).getIterator(transactionID, dictionary, "s", "p", "o"))
                                    DistributedTripleStore.dropGraph(i2s(graphref1))
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                            }
                        }
                        EGraphOperationType.ADD -> {
                            when (graphref2) {
                                is ASTIriGraphRef -> {
                                    if (i2s(graphref2) != i2s(graphref1))
                                        DistributedTripleStore.getNamedGraph(i2s(graphref2), true).addData(transactionID, DistributedTripleStore.getNamedGraph(i2s(graphref1)).getIterator(transactionID, dictionary, "s", "p", "o"))
                                }
                                is ASTDefaultGraphRef -> {
                                    DistributedTripleStore.getDefaultGraph().addData(transactionID, DistributedTripleStore.getNamedGraph(i2s(graphref1)).getIterator(transactionID, dictionary, "s", "p", "o"))
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
                            EGraphOperationType.CREATE -> DistributedTripleStore.createGraph(name)
                            EGraphOperationType.CLEAR -> {
                                try {
                                    DistributedTripleStore.clearGraph(name)
                                } catch (e: Throwable) {
                                }
                            }
                            EGraphOperationType.DROP -> DistributedTripleStore.dropGraph(name)
                            EGraphOperationType.COPY -> {
                                when (graphref2) {
                                    else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                                }
                            }
                            EGraphOperationType.ADD -> {
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
}
    }as ResultRow

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
