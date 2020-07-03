package lupos.s09physicalOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EGraphRefType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.EvaluationException
import lupos.s00misc.File
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedGraph
import lupos.s15tripleStoreDistributed.DistributedTripleStore

class POPGraphOperation(query: Query,
                        projectedVariables: List<String>,
                        @JvmField val silent: Boolean,
                        @JvmField var graph1type: EGraphRefType = EGraphRefType.DefaultGraphRef,
                        @JvmField var graph1iri: String? = null,
                        @JvmField var graph2type: EGraphRefType = EGraphRefType.DefaultGraphRef,
                        @JvmField var graph2iri: String? = null,
                        @JvmField val action: EGraphOperationType) : POPBase(query, projectedVariables, EOperatorID.POPGraphOperationID, "POPGraphOperation", arrayOf(), ESortPriority.PREVENT_ANY) {
    override fun toSparqlQuery() = toSparql()
    override fun toSparql(): String {
        var res = ""
        if (action == EGraphOperationType.LOAD) {
            res += "LOAD " + graph1iri!!
            if (silent) {
                res += " SILENT "
            } else {
                res += " "
            }
            if (graph2type == EGraphRefType.IriGraphRef) {
                res += "INTO GRAPH " + graph2iri
            }
        } else {
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
        }
        return res
    }

    override fun equals(other: Any?) = other is POPGraphOperation && silent == other.silent && graph1iri == other.graph1iri && graph1type == other.graph1type && graph2iri == other.graph2iri && graph2type == other.graph2type && action == other.action
    override fun cloneOP() = POPGraphOperation(query, projectedVariables, silent, graph1type, graph1iri, graph2type, graph2iri, action)
    suspend fun copyData(source: DistributedGraph, target: DistributedGraph) {
        val row = source.getIterator(EIndexPattern.SPO).evaluate()
        val iterator = arrayOf(row.columns["s"]!!, row.columns["p"]!!, row.columns["o"]!!)
        target.modify(iterator, EModifyType.INSERT)
    }

    override suspend fun evaluate(): IteratorBundle {
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
                            SanityCheck.checkUnreachable()
                        }
                    }
                }
                EGraphOperationType.LOAD -> {
                    var fileName = query.workingDirectory + graph1iri
                    val target: DistributedGraph
                    if (graph2type == EGraphRefType.DefaultGraphRef) {
                        target = DistributedTripleStore.getDefaultGraph(query)
                    } else {
                        target = DistributedTripleStore.getNamedGraph(query, graph2iri!!)
                    }
                    val xml = XMLElement.parseFromAny(File(fileName).readAsString(), fileName)!!
                    val d = POPValuesImportXML(query, listOf("s", "p", "o"), xml)
                    val row = d.evaluate()
                    val iterator = arrayOf(row.columns["s"]!!, row.columns["p"]!!, row.columns["o"]!!)
                    target.modify(iterator, EModifyType.INSERT)
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
                                    SanityCheck.checkUnreachable()
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
                                    SanityCheck.checkUnreachable()
                                }
                            }
                        }
                        else -> {
                            SanityCheck.checkUnreachable()
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
                                    SanityCheck.checkUnreachable()
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
                                    SanityCheck.checkUnreachable()
                                }
                            }
                        }
                        else -> {
                            SanityCheck.checkUnreachable()
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
                                    SanityCheck.checkUnreachable()
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
                                    SanityCheck.checkUnreachable()
                                }
                            }
                        }
                        else -> {
                            SanityCheck.checkUnreachable()
                        }
                    }
                }
            }
        } catch (e: EvaluationException) {
            if (!silent) {
                throw e
            }
        } catch (e: Throwable) {
           SanityCheck.println("TODO exception 7")
            e.printStackTrace()
            if (!silent) {
                throw e
            }
        }
        return IteratorBundle(1)
    }
}
