package lupos.s09physicalOperators.noinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EOperatorID
import lupos.s00misc.SanityCheck
import lupos.s02buildSyntaxTree.sparql1_1.ASTAllGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTDefaultGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTIriGraphRef
import lupos.s02buildSyntaxTree.sparql1_1.ASTNamedGraphRef
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.*
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.*

class POPGraphOperation(query: Query,
                        projectedVariables: List<String>,
                        val silent: Boolean,
                        var graph1type: EGraphRefType = EGraphRefType.DefaultGraphRef,
                        var graph1iri: String? = null,
                        var graph2type: EGraphRefType = EGraphRefType.DefaultGraphRef,
                        var graph2iri: String? = null,
                        val action: EGraphOperationType) : POPBase(query, projectedVariables, EOperatorID.POPGraphOperationID, "POPGraphOperation", arrayOf(),ESortPriority.PREVENT_ANY) {
    override fun toSparqlQuery() = toSparql()
    override fun toSparql(): String {
        var res = ""
        when (action) {
            EGraphOperationType.CLEAR -> {
                res += "CLEAR"
            }
            EGraphOperationType.DROP -> {
                res += "DROP"
            }
            EGraphOperationType.CREATE -> {
                res += "CREATE"
            }
            EGraphOperationType.COPY -> {
                res += "COPY"
            }
            EGraphOperationType.MOVE -> {
                res += "MOVE"
            }
            EGraphOperationType.ADD -> {
                res += "ADD"
            }
        }
        if (silent) {
            res += " SILENT "
        } else {
            res += " "
        }
        when (graph1type) {
            EGraphRefType.AllGraphRef -> {
                res += "ALL"
            }
            EGraphRefType.DefaultGraphRef -> {
                res += "DEFAULT"
            }
            EGraphRefType.NamedGraphRef -> {
                res += "NAMED"
            }
            EGraphRefType.IriGraphRef -> {
                res += "GRAPH <" + graph1iri!! + ">"
            }
        }
        if (action == EGraphOperationType.COPY || action == EGraphOperationType.MOVE || action == EGraphOperationType.ADD) {
            res += " TO "
            when (graph2type) {
                EGraphRefType.AllGraphRef -> {
                    res += "ALL"
                }
                EGraphRefType.DefaultGraphRef -> {
                    res += "DEFAULT"
                }
                EGraphRefType.NamedGraphRef -> {
                    res += "NAMED"
                }
                EGraphRefType.IriGraphRef -> {
                    res += "GRAPH <" + graph2iri!! + ">"
                }
            }
        }
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is POPGraphOperation) {
            return false
        }
        if (silent != other.silent) {
            return false
        }
        if (graph1iri != other.graph1iri) {
            return false
        }
        if (graph1type != other.graph1type) {
            return false
        }
        if (graph2iri != other.graph2iri) {
            return false
        }
        if (graph2type != other.graph2type) {
            return false
        }
        if (action != other.action) {
            return false
        }
        for (i in children.indices) {
            if (!children[i].equals(other.children[i])) {
                return false
            }
        }
        return true
    }

    override fun cloneOP() = POPGraphOperation(query, projectedVariables, silent, graph1type, graph1iri, graph2type, graph2iri, action)
    suspend fun copyData(source: DistributedGraph, target: DistributedGraph) {
        val row = source.getIterator(EIndexPattern.SPO).evaluate()
        val iterator = arrayOf(row.columns["s"]!!, row.columns["p"]!!, row.columns["o"]!!)
        target.modify(iterator, EModifyType.INSERT)
    }

    override suspend fun evaluate(): ColumnIteratorRow {
        try {
            when (action) {
                EGraphOperationType.CLEAR -> {
                    when (graph1type) {
                        EGraphRefType.AllGraphRef -> {
                            for (name in DistributedTripleStore.getGraphNames(true)) {
                                DistributedTripleStore.clearGraph(query, name)
                            }
                        }
                        EGraphRefType.DefaultGraphRef -> {
                            DistributedTripleStore.clearGraph(query, PersistentStoreLocal.defaultGraphName)
                        }
                        EGraphRefType.IriGraphRef -> {
                            DistributedTripleStore.clearGraph(query, graph1iri!!)
                        }
                        EGraphRefType.NamedGraphRef -> {
                            for (name in DistributedTripleStore.getGraphNames()) {
                                DistributedTripleStore.clearGraph(query, name)
                            }
                        }
                    }
                }
                EGraphOperationType.DROP -> {
                    when (graph1type) {
                        EGraphRefType.AllGraphRef -> {
                            DistributedTripleStore.clearGraph(query, PersistentStoreLocal.defaultGraphName)
                            for (name in DistributedTripleStore.getGraphNames(false)) {
                                DistributedTripleStore.dropGraph(query, name)
                            }
                        }
                        EGraphRefType.DefaultGraphRef -> {
                            DistributedTripleStore.clearGraph(query, PersistentStoreLocal.defaultGraphName)
                        }
                        EGraphRefType.IriGraphRef -> {
                            DistributedTripleStore.dropGraph(query, graph1iri!!)
                        }
                        EGraphRefType.NamedGraphRef -> {
                            for (name in DistributedTripleStore.getGraphNames(false)) {
                                DistributedTripleStore.dropGraph(query, name)
                            }
                        }
                    }
                }
                EGraphOperationType.CREATE -> {
                    when (graph1type) {
                        EGraphRefType.IriGraphRef -> {
                            DistributedTripleStore.createGraph(query, graph1iri!!)
                        }
                        else -> {
                            require(false)
                        }
                    }
                }
                EGraphOperationType.COPY -> {
                    when (graph1type) {
                        EGraphRefType.DefaultGraphRef -> {
                            when (graph2type) {
                                EGraphRefType.DefaultGraphRef -> {
                                }
                                EGraphRefType.IriGraphRef -> {
                                    val source = DistributedTripleStore.getDefaultGraph(query)
                                    val target = DistributedTripleStore.getNamedGraph(query, graph2iri!!)
                                    DistributedTripleStore.clearGraph(query, graph2iri!!)
                                    copyData(source, target)
                                }
                                else -> {
                                    require(false)
                                }
                            }
                        }
                        EGraphRefType.IriGraphRef -> {
                            when (graph2type) {
                                EGraphRefType.DefaultGraphRef -> {
                                    val source = DistributedTripleStore.getNamedGraph(query, graph1iri!!)
                                    val target = DistributedTripleStore.getDefaultGraph(query)
                                    DistributedTripleStore.clearGraph(query, PersistentStoreLocal.defaultGraphName)
                                    copyData(source, target)
                                }
                                EGraphRefType.IriGraphRef -> {
                                    if (graph1iri != graph2iri) {
                                        val source = DistributedTripleStore.getNamedGraph(query, graph1iri!!)
                                        val target = DistributedTripleStore.getNamedGraph(query, graph2iri!!)
                                        DistributedTripleStore.clearGraph(query, graph2iri!!)
                                        copyData(source, target)
                                    }
                                }
                                else -> {
                                    require(false)
                                }
                            }
                        }
                        else -> {
                            require(false)
                        }
                    }
                }
                EGraphOperationType.MOVE -> {
                    when (graph1type) {
                        EGraphRefType.DefaultGraphRef -> {
                            when (graph2type) {
                                EGraphRefType.DefaultGraphRef -> {
                                }
                                EGraphRefType.IriGraphRef -> {
                                    val source = DistributedTripleStore.getDefaultGraph(query)
                                    val target = DistributedTripleStore.getNamedGraph(query, graph2iri!!)
                                    DistributedTripleStore.clearGraph(query, graph2iri!!)
                                    copyData(source, target)
                                    DistributedTripleStore.clearGraph(query, PersistentStoreLocal.defaultGraphName)
                                }
                                else -> {
                                    require(false)
                                }
                            }
                        }
                        EGraphRefType.IriGraphRef -> {
                            when (graph2type) {
                                EGraphRefType.DefaultGraphRef -> {
                                    val source = DistributedTripleStore.getNamedGraph(query, graph1iri!!)
                                    val target = DistributedTripleStore.getDefaultGraph(query)
                                    DistributedTripleStore.clearGraph(query, PersistentStoreLocal.defaultGraphName)
                                    copyData(source, target)
                                    DistributedTripleStore.clearGraph(query, graph1iri!!)
                                }
                                EGraphRefType.IriGraphRef -> {
                                    if (graph1iri != graph2iri) {
                                        val source = DistributedTripleStore.getNamedGraph(query, graph1iri!!)
                                        val target = DistributedTripleStore.getNamedGraph(query, graph2iri!!)
                                        DistributedTripleStore.clearGraph(query, graph2iri!!)
                                        copyData(source, target)
                                        DistributedTripleStore.clearGraph(query, graph1iri!!)
                                    }
                                }
                                else -> {
                                    require(false)
                                }
                            }
                        }
                        else -> {
                            require(false)
                        }
                    }
                }
                EGraphOperationType.ADD -> {
                    when (graph1type) {
                        EGraphRefType.DefaultGraphRef -> {
                            when (graph2type) {
                                EGraphRefType.DefaultGraphRef -> {
                                }
                                EGraphRefType.IriGraphRef -> {
                                    val source = DistributedTripleStore.getDefaultGraph(query)
                                    val target = DistributedTripleStore.getNamedGraph(query, graph2iri!!)
                                    copyData(source, target)
                                }
                                else -> {
                                    require(false)
                                }
                            }
                        }
                        EGraphRefType.IriGraphRef -> {
                            when (graph2type) {
                                EGraphRefType.DefaultGraphRef -> {
                                    val source = DistributedTripleStore.getNamedGraph(query, graph1iri!!)
                                    val target = DistributedTripleStore.getDefaultGraph(query)
                                    copyData(source, target)
                                }
                                EGraphRefType.IriGraphRef -> {
                                    if (graph1iri != graph2iri) {
                                        val source = DistributedTripleStore.getNamedGraph(query, graph1iri!!)
                                        val target = DistributedTripleStore.getNamedGraph(query, graph2iri!!)
                                        copyData(source, target)
                                    }
                                }
                                else -> {
                                    require(false)
                                }
                            }
                        }
                        else -> {
                            require(false)
                        }
                    }
                }
            }
        } catch (e: Throwable) {
            if (!silent) {
                throw e
            }
        }
        val res = ColumnIteratorRow(mutableMapOf<String, ColumnIterator>())
        res.count = 1
        return res
    }
}
