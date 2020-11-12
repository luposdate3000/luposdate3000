package lupos.s09physicalOperators.noinput

import lupos.s00misc.*
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s05tripleStore.PersistentStoreLocalExt
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.IDistributedGraph
import lupos.s15tripleStoreDistributed.distributedTripleStore
import kotlin.jvm.JvmField

class POPGraphOperation(query: IQuery,
                        projectedVariables: List<String>,
                        @JvmField val silent: Boolean,
                        @JvmField var graph1type: EGraphRefType = EGraphRefType.DefaultGraphRef,
                        @JvmField var graph1iri: String? = null,
                        @JvmField var graph2type: EGraphRefType = EGraphRefType.DefaultGraphRef,
                        @JvmField var graph2iri: String? = null,
                        @JvmField val action: EGraphOperationType) : POPBase(query, projectedVariables, EOperatorID.POPGraphOperationID, "POPGraphOperation", arrayOf(), ESortPriority.PREVENT_ANY) {
    override fun getPartitionCount(variable: String): Int = 1
    override fun toSparqlQuery() = toSparql()
    override fun toSparql(): String {
        var res = ""
        if (action == EGraphOperationType.LOAD) {
            res += "LOAD " + graph1iri!!
            res += if (silent) {
                " SILENT "
            } else {
                " "
            }
            if (graph2type == EGraphRefType.IriGraphRef) {
                res += "INTO GRAPH $graph2iri"
            }
        } else {
            when (action) {
                EGraphOperationType.LOAD -> {
                    res += "LOAD"
                }
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
            res += if (silent) {
                " SILENT "
            } else {
                " "
            }
            res += when (graph1type) {
                EGraphRefType.AllGraphRef -> {
                    "ALL"
                }
                EGraphRefType.DefaultGraphRef -> {
                    "DEFAULT"
                }
                EGraphRefType.NamedGraphRef -> {
                    "NAMED"
                }
                EGraphRefType.IriGraphRef -> {
                    "GRAPH <" + graph1iri!! + ">"
                }
            }
            if (action == EGraphOperationType.COPY || action == EGraphOperationType.MOVE || action == EGraphOperationType.ADD) {
                res += " TO "
                res += when (graph2type) {
                    EGraphRefType.AllGraphRef -> {
                        "ALL"
                    }
                    EGraphRefType.DefaultGraphRef -> {
                        "DEFAULT"
                    }
                    EGraphRefType.NamedGraphRef -> {
                        "NAMED"
                    }
                    EGraphRefType.IriGraphRef -> {
                        "GRAPH <" + graph2iri!! + ">"
                    }
                }
            }
        }
        return res
    }

    override fun equals(other: Any?) = other is POPGraphOperation && silent == other.silent && graph1iri == other.graph1iri && graph1type == other.graph1type && graph2iri == other.graph2iri && graph2type == other.graph2type && action == other.action
    override fun cloneOP(): IOPBase = POPGraphOperation(query, projectedVariables, silent, graph1type, graph1iri, graph2type, graph2iri, action)
    /*suspend*/ private fun copyData(source: IDistributedGraph, target: IDistributedGraph, parent: Partition) {
        val row = source.getIterator(EIndexPattern.SPO, Partition()).evaluate(parent)
        val iterator = arrayOf(row.columns["s"]!!, row.columns["p"]!!, row.columns["o"]!!)
        target.modify(iterator, EModifyType.INSERT)
    }

    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        try {
            when (action) {
                EGraphOperationType.CLEAR -> {
                    when (graph1type) {
                        EGraphRefType.AllGraphRef -> {
                            for (name in distributedTripleStore.getGraphNames(true)) {
                                distributedTripleStore.clearGraph(query, name)
                            }
                        }
                        EGraphRefType.DefaultGraphRef -> {
                            distributedTripleStore.clearGraph(query, PersistentStoreLocalExt.defaultGraphName)
                        }
                        EGraphRefType.IriGraphRef -> {
                            distributedTripleStore.clearGraph(query, graph1iri!!)
                        }
                        EGraphRefType.NamedGraphRef -> {
                            for (name in distributedTripleStore.getGraphNames()) {
                                distributedTripleStore.clearGraph(query, name)
                            }
                        }
                    }
                }
                EGraphOperationType.DROP -> {
                    when (graph1type) {
                        EGraphRefType.AllGraphRef -> {
                            distributedTripleStore.clearGraph(query, PersistentStoreLocalExt.defaultGraphName)
                            for (name in distributedTripleStore.getGraphNames(false)) {
                                distributedTripleStore.dropGraph(query, name)
                            }
                        }
                        EGraphRefType.DefaultGraphRef -> {
                            distributedTripleStore.clearGraph(query, PersistentStoreLocalExt.defaultGraphName)
                        }
                        EGraphRefType.IriGraphRef -> {
                            distributedTripleStore.dropGraph(query, graph1iri!!)
                        }
                        EGraphRefType.NamedGraphRef -> {
                            for (name in distributedTripleStore.getGraphNames(false)) {
                                distributedTripleStore.dropGraph(query, name)
                            }
                        }
                    }
                }
                EGraphOperationType.CREATE -> {
                    when (graph1type) {
                        EGraphRefType.IriGraphRef -> {
                            distributedTripleStore.createGraph(query, graph1iri!!)
                        }
                        else -> {
                            SanityCheck.checkUnreachable()
                        }
                    }
                }
                EGraphOperationType.LOAD -> {
                    val fileName = query.getWorkingDirectory() + graph1iri
                    val target: IDistributedGraph = if (graph2type == EGraphRefType.DefaultGraphRef) {
                        distributedTripleStore.getDefaultGraph(query)
                    } else {
                        distributedTripleStore.getNamedGraph(query, graph2iri!!)
                    }
                    val xml = XMLElement.parseFromAny(File(fileName).readAsString(), fileName)!!
                    val d = POPValuesImportXML(query, listOf("s", "p", "o"), xml)
                    val row = d.evaluate(parent)
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
                                    val source = distributedTripleStore.getDefaultGraph(query)
                                    val target = distributedTripleStore.getNamedGraph(query, graph2iri!!)
                                    distributedTripleStore.clearGraph(query, graph2iri!!)
                                    copyData(source, target, parent)
                                }
                                else -> {
                                    SanityCheck.checkUnreachable()
                                }
                            }
                        }
                        EGraphRefType.IriGraphRef -> {
                            when (graph2type) {
                                EGraphRefType.DefaultGraphRef -> {
                                    val source = distributedTripleStore.getNamedGraph(query, graph1iri!!)
                                    val target = distributedTripleStore.getDefaultGraph(query)
                                    distributedTripleStore.clearGraph(query, PersistentStoreLocalExt.defaultGraphName)
                                    copyData(source, target, parent)
                                }
                                EGraphRefType.IriGraphRef -> {
                                    if (graph1iri != graph2iri) {
                                        val source = distributedTripleStore.getNamedGraph(query, graph1iri!!)
                                        val target = distributedTripleStore.getNamedGraph(query, graph2iri!!)
                                        distributedTripleStore.clearGraph(query, graph2iri!!)
                                        copyData(source, target, parent)
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
                                    val source = distributedTripleStore.getDefaultGraph(query)
                                    val target = distributedTripleStore.getNamedGraph(query, graph2iri!!)
                                    distributedTripleStore.clearGraph(query, graph2iri!!)
                                    copyData(source, target, parent)
                                    distributedTripleStore.clearGraph(query, PersistentStoreLocalExt.defaultGraphName)
                                }
                                else -> {
                                    SanityCheck.checkUnreachable()
                                }
                            }
                        }
                        EGraphRefType.IriGraphRef -> {
                            when (graph2type) {
                                EGraphRefType.DefaultGraphRef -> {
                                    val source = distributedTripleStore.getNamedGraph(query, graph1iri!!)
                                    val target = distributedTripleStore.getDefaultGraph(query)
                                    distributedTripleStore.clearGraph(query, PersistentStoreLocalExt.defaultGraphName)
                                    copyData(source, target, parent)
                                    distributedTripleStore.clearGraph(query, graph1iri!!)
                                }
                                EGraphRefType.IriGraphRef -> {
                                    if (graph1iri != graph2iri) {
                                        val source = distributedTripleStore.getNamedGraph(query, graph1iri!!)
                                        val target = distributedTripleStore.getNamedGraph(query, graph2iri!!)
                                        distributedTripleStore.clearGraph(query, graph2iri!!)
                                        copyData(source, target, parent)
                                        distributedTripleStore.clearGraph(query, graph1iri!!)
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
                                    val source = distributedTripleStore.getDefaultGraph(query)
                                    val target = distributedTripleStore.getNamedGraph(query, graph2iri!!)
                                    copyData(source, target, parent)
                                }
                                else -> {
                                    SanityCheck.checkUnreachable()
                                }
                            }
                        }
                        EGraphRefType.IriGraphRef -> {
                            when (graph2type) {
                                EGraphRefType.DefaultGraphRef -> {
                                    val source = distributedTripleStore.getNamedGraph(query, graph1iri!!)
                                    val target = distributedTripleStore.getDefaultGraph(query)
                                    copyData(source, target, parent)
                                }
                                EGraphRefType.IriGraphRef -> {
                                    if (graph1iri != graph2iri) {
                                        val source = distributedTripleStore.getNamedGraph(query, graph1iri!!)
                                        val target = distributedTripleStore.getNamedGraph(query, graph2iri!!)
                                        copyData(source, target, parent)
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
            SanityCheck.println { "TODO exception 7" }
            e.printStackTrace()
            if (!silent) {
                throw e
            }
        }
        return IteratorBundle(1)
    }
}
