/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.s09physicalOperators.noinput

import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EGraphOperationTypeExt
import lupos.s00misc.EGraphRefType
import lupos.s00misc.EGraphRefTypeExt
import lupos.s00misc.EIndexPatternExt
import lupos.s00misc.EModifyTypeExt
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.EvaluationException
import lupos.s00misc.File
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.UnreachableException
import lupos.s00misc.XMLElement
import lupos.s00misc.parseFromAny
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s05tripleStore.PersistentStoreLocalExt
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.IDistributedGraph
import lupos.s15tripleStoreDistributed.distributedTripleStore
import kotlin.jvm.JvmField

public class POPGraphOperation public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField public val silent: Boolean,
    @JvmField public var graph1type: EGraphRefType = EGraphRefTypeExt.DefaultGraphRef,
    @JvmField public var graph1iri: String? = null,
    @JvmField public var graph2type: EGraphRefType = EGraphRefTypeExt.DefaultGraphRef,
    @JvmField public var graph2iri: String? = null,
    @JvmField public val action: EGraphOperationType
) : POPBase(query, projectedVariables, EOperatorIDExt.POPGraphOperationID, "POPGraphOperation", arrayOf(), ESortPriorityExt.PREVENT_ANY) {
    override fun getPartitionCount(variable: String): Int = 1
    override fun toSparqlQuery(): String = toSparql()
    override fun toSparql(): String {
        var res = ""
        if (action == EGraphOperationTypeExt.LOAD) {
            res += "LOAD " + graph1iri!!
            res += if (silent) {
                " SILENT "
            } else {
                " "
            }
            if (graph2type == EGraphRefTypeExt.IriGraphRef) {
                res += "INTO GRAPH $graph2iri"
            }
        } else {
            when (action) {
                EGraphOperationTypeExt.LOAD -> {
                    res += "LOAD"
                }
                EGraphOperationTypeExt.CLEAR -> {
                    res += "CLEAR"
                }
                EGraphOperationTypeExt.DROP -> {
                    res += "DROP"
                }
                EGraphOperationTypeExt.CREATE -> {
                    res += "CREATE"
                }
                EGraphOperationTypeExt.COPY -> {
                    res += "COPY"
                }
                EGraphOperationTypeExt.MOVE -> {
                    res += "MOVE"
                }
                EGraphOperationTypeExt.ADD -> {
                    res += "ADD"
                }
            }
            res += if (silent) {
                " SILENT "
            } else {
                " "
            }
            res += when (graph1type) {
                EGraphRefTypeExt.AllGraphRef -> {
                    "ALL"
                }
                EGraphRefTypeExt.DefaultGraphRef -> {
                    "DEFAULT"
                }
                EGraphRefTypeExt.NamedGraphRef -> {
                    "NAMED"
                }
                EGraphRefTypeExt.IriGraphRef -> {
                    "GRAPH <" + graph1iri!! + ">"
                }
                else -> {
                    throw UnreachableException()
                }
            }
            if (action == EGraphOperationTypeExt.COPY || action == EGraphOperationTypeExt.MOVE || action == EGraphOperationTypeExt.ADD) {
                res += " TO "
                res += when (graph2type) {
                    EGraphRefTypeExt.AllGraphRef -> {
                        "ALL"
                    }
                    EGraphRefTypeExt.DefaultGraphRef -> {
                        "DEFAULT"
                    }
                    EGraphRefTypeExt.NamedGraphRef -> {
                        "NAMED"
                    }
                    EGraphRefTypeExt.IriGraphRef -> {
                        "GRAPH <" + graph2iri!! + ">"
                    }
                    else -> {
                        throw UnreachableException()
                    }
                }
            }
        }
        return res
    }

    override fun equals(other: Any?): Boolean = other is POPGraphOperation && silent == other.silent && graph1iri == other.graph1iri && graph1type == other.graph1type && graph2iri == other.graph2iri && graph2type == other.graph2type && action == other.action
    override fun cloneOP(): IOPBase = POPGraphOperation(query, projectedVariables, silent, graph1type, graph1iri, graph2type, graph2iri, action)
    /*suspend*/ private fun copyData(source: IDistributedGraph, target: IDistributedGraph, parent: Partition) {
        val row = source.getIterator(EIndexPatternExt.SPO, Partition()).evaluate(parent)
        val iterator = arrayOf(row.columns["s"]!!, row.columns["p"]!!, row.columns["o"]!!)
        target.modify(iterator, EModifyTypeExt.INSERT)
    }

    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        try {
            when (action) {
                EGraphOperationTypeExt.CLEAR -> {
                    when (graph1type) {
                        EGraphRefTypeExt.AllGraphRef -> {
                            for (name in distributedTripleStore.getGraphNames(true)) {
                                distributedTripleStore.clearGraph(query, name)
                            }
                        }
                        EGraphRefTypeExt.DefaultGraphRef -> {
                            distributedTripleStore.clearGraph(query, PersistentStoreLocalExt.defaultGraphName)
                        }
                        EGraphRefTypeExt.IriGraphRef -> {
                            distributedTripleStore.clearGraph(query, graph1iri!!)
                        }
                        EGraphRefTypeExt.NamedGraphRef -> {
                            for (name in distributedTripleStore.getGraphNames()) {
                                distributedTripleStore.clearGraph(query, name)
                            }
                        }
                    }
                }
                EGraphOperationTypeExt.DROP -> {
                    when (graph1type) {
                        EGraphRefTypeExt.AllGraphRef -> {
                            distributedTripleStore.clearGraph(query, PersistentStoreLocalExt.defaultGraphName)
                            for (name in distributedTripleStore.getGraphNames(false)) {
                                distributedTripleStore.dropGraph(query, name)
                            }
                        }
                        EGraphRefTypeExt.DefaultGraphRef -> {
                            distributedTripleStore.clearGraph(query, PersistentStoreLocalExt.defaultGraphName)
                        }
                        EGraphRefTypeExt.IriGraphRef -> {
                            distributedTripleStore.dropGraph(query, graph1iri!!)
                        }
                        EGraphRefTypeExt.NamedGraphRef -> {
                            for (name in distributedTripleStore.getGraphNames(false)) {
                                distributedTripleStore.dropGraph(query, name)
                            }
                        }
                    }
                }
                EGraphOperationTypeExt.CREATE -> {
                    when (graph1type) {
                        EGraphRefTypeExt.IriGraphRef -> {
                            distributedTripleStore.createGraph(query, graph1iri!!)
                        }
                        else -> {
                            SanityCheck.checkUnreachable()
                        }
                    }
                }
                EGraphOperationTypeExt.LOAD -> {
                    val fileName = query.getWorkingDirectory() + graph1iri
                    val target: IDistributedGraph = if (graph2type == EGraphRefTypeExt.DefaultGraphRef) {
                        distributedTripleStore.getDefaultGraph(query)
                    } else {
                        distributedTripleStore.getNamedGraph(query, graph2iri!!)
                    }
                    val xml = XMLElement.parseFromAny(File(fileName).readAsString(), fileName)!!
                    val d = POPValuesImportXML(query, listOf("s", "p", "o"), xml)
                    val row = d.evaluate(parent)
                    val iterator = arrayOf(row.columns["s"]!!, row.columns["p"]!!, row.columns["o"]!!)
                    target.modify(iterator, EModifyTypeExt.INSERT)
                }
                EGraphOperationTypeExt.COPY -> {
                    when (graph1type) {
                        EGraphRefTypeExt.DefaultGraphRef -> {
                            when (graph2type) {
                                EGraphRefTypeExt.DefaultGraphRef -> {
                                }
                                EGraphRefTypeExt.IriGraphRef -> {
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
                        EGraphRefTypeExt.IriGraphRef -> {
                            when (graph2type) {
                                EGraphRefTypeExt.DefaultGraphRef -> {
                                    val source = distributedTripleStore.getNamedGraph(query, graph1iri!!)
                                    val target = distributedTripleStore.getDefaultGraph(query)
                                    distributedTripleStore.clearGraph(query, PersistentStoreLocalExt.defaultGraphName)
                                    copyData(source, target, parent)
                                }
                                EGraphRefTypeExt.IriGraphRef -> {
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
                EGraphOperationTypeExt.MOVE -> {
                    when (graph1type) {
                        EGraphRefTypeExt.DefaultGraphRef -> {
                            when (graph2type) {
                                EGraphRefTypeExt.DefaultGraphRef -> {
                                }
                                EGraphRefTypeExt.IriGraphRef -> {
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
                        EGraphRefTypeExt.IriGraphRef -> {
                            when (graph2type) {
                                EGraphRefTypeExt.DefaultGraphRef -> {
                                    val source = distributedTripleStore.getNamedGraph(query, graph1iri!!)
                                    val target = distributedTripleStore.getDefaultGraph(query)
                                    distributedTripleStore.clearGraph(query, PersistentStoreLocalExt.defaultGraphName)
                                    copyData(source, target, parent)
                                    distributedTripleStore.clearGraph(query, graph1iri!!)
                                }
                                EGraphRefTypeExt.IriGraphRef -> {
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
                EGraphOperationTypeExt.ADD -> {
                    when (graph1type) {
                        EGraphRefTypeExt.DefaultGraphRef -> {
                            when (graph2type) {
                                EGraphRefTypeExt.DefaultGraphRef -> {
                                }
                                EGraphRefTypeExt.IriGraphRef -> {
                                    val source = distributedTripleStore.getDefaultGraph(query)
                                    val target = distributedTripleStore.getNamedGraph(query, graph2iri!!)
                                    copyData(source, target, parent)
                                }
                                else -> {
                                    SanityCheck.checkUnreachable()
                                }
                            }
                        }
                        EGraphRefTypeExt.IriGraphRef -> {
                            when (graph2type) {
                                EGraphRefTypeExt.DefaultGraphRef -> {
                                    val source = distributedTripleStore.getNamedGraph(query, graph1iri!!)
                                    val target = distributedTripleStore.getDefaultGraph(query)
                                    copyData(source, target, parent)
                                }
                                EGraphRefTypeExt.IriGraphRef -> {
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
