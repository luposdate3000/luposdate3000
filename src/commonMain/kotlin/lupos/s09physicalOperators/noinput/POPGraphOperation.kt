package lupos.s09physicalOperators.noinput

import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
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
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore


class POPGraphOperation : POPBase {
    override val children: Array<OPBase> = arrayOf()
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    val transactionID: Long
    val silent: Boolean
    val graphref1: ASTGraphRef
    val graphref2: ASTGraphRef?
    val action: EGraphOperationType

    override fun equals(other: Any?): Boolean {
        if (other !is POPGraphOperation)
            return false
        if (dictionary !== other.dictionary)
            return false
        if (silent != other.silent)
            return false
        if (graphref1.equals(other.graphref1))
            return false
        if ((graphref2 == null && other.graphref2 == null) || graphref2!!.equals(other.graphref2!!))
            return false
        if (action != other.action)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    constructor(dictionary: ResultSetDictionary, transactionID: Long, silent: Boolean, graphref1: ASTGraphRef, graphref2: ASTGraphRef?, action: EGraphOperationType) : super() {
        this.dictionary = dictionary
        this.transactionID = transactionID
        this.silent = silent
        this.graphref1 = graphref1
        this.graphref2 = graphref2
        this.action = action
        this.resultSet = ResultSet(dictionary)
    }


    fun i2s(iri: ASTIriGraphRef): String {
        return iri.iri
    }

    override fun evaluate() = Trace.trace<Unit>({ "POPGraphOperation.evaluate" }, {
        CoroutinesHelper.run {
            try {
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
                                        DistributedTripleStore.getNamedGraph(i2s(graphref2), true).addData(transactionID, DistributedTripleStore.getDefaultGraph().getIterator(transactionID, dictionary, "s", "p", "o", EIndexPattern.SPO))
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
                                        DistributedTripleStore.getNamedGraph(i2s(graphref2), true).addData(transactionID, DistributedTripleStore.getDefaultGraph().getIterator(transactionID, dictionary, "s", "p", "o", EIndexPattern.SPO))
                                        DistributedTripleStore.clearGraph(DistributedTripleStore.localStore.defaultGraphName)
                                    }
                                    else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                                }
                            }
                            EGraphOperationType.ADD -> {
                                when (graphref2) {
                                    is ASTIriGraphRef -> {
                                        DistributedTripleStore.getNamedGraph(i2s(graphref2), true).addData(transactionID, DistributedTripleStore.getDefaultGraph().getIterator(transactionID, dictionary, "s", "p", "o", EIndexPattern.SPO))
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
                                            DistributedTripleStore.getNamedGraph(i2s(graphref2), true).addData(transactionID, DistributedTripleStore.getNamedGraph(i2s(graphref1)).getIterator(transactionID, dictionary, "s", "p", "o", EIndexPattern.SPO))
                                        }
                                    }
                                    is ASTDefaultGraphRef -> {
                                        DistributedTripleStore.clearGraph(DistributedTripleStore.localStore.defaultGraphName)
                                        DistributedTripleStore.getDefaultGraph().addData(transactionID, DistributedTripleStore.getNamedGraph(i2s(graphref1)).getIterator(transactionID, dictionary, "s", "p", "o", EIndexPattern.SPO))
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
                                            DistributedTripleStore.getNamedGraph(i2s(graphref2), true).addData(transactionID, DistributedTripleStore.getNamedGraph(i2s(graphref1)).getIterator(transactionID, dictionary, "s", "p", "o", EIndexPattern.SPO))
                                            DistributedTripleStore.dropGraph(i2s(graphref1))
                                        }
                                    }
                                    is ASTDefaultGraphRef -> {
                                        DistributedTripleStore.clearGraph(DistributedTripleStore.localStore.defaultGraphName)
                                        DistributedTripleStore.getDefaultGraph().addData(transactionID, DistributedTripleStore.getNamedGraph(i2s(graphref1)).getIterator(transactionID, dictionary, "s", "p", "o", EIndexPattern.SPO))
                                        DistributedTripleStore.dropGraph(i2s(graphref1))
                                    }
                                    else -> throw UnsupportedOperationException("${classNameToString(this)} graphref ${classNameToString(graphref1)} ${classNameToString(graphref2!!)} $action")
                                }
                            }
                            EGraphOperationType.ADD -> {
                                when (graphref2) {
                                    is ASTIriGraphRef -> {
                                        if (i2s(graphref2) != i2s(graphref1))
                                            DistributedTripleStore.getNamedGraph(i2s(graphref2), true).addData(transactionID, DistributedTripleStore.getNamedGraph(i2s(graphref1)).getIterator(transactionID, dictionary, "s", "p", "o", EIndexPattern.SPO))
                                    }
                                    is ASTDefaultGraphRef -> {
                                        DistributedTripleStore.getDefaultGraph().addData(transactionID, DistributedTripleStore.getNamedGraph(i2s(graphref1)).getIterator(transactionID, dictionary, "s", "p", "o", EIndexPattern.SPO))
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
            } catch (e: Throwable) {
                if (!silent)
                    throw e
            }
            try {
                channel.send(resultFlowProduce({ this@POPGraphOperation }, { resultSet.createResultRow() }))
                channel.close()
            } catch (e: Throwable) {
                channel.close(e)
            }
        }
    })

    override fun getProvidedVariableNames() = mutableListOf<String>()

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPGraphOperation")
        res.addAttribute("uuid", "" + uuid)
        return res
    }
}
