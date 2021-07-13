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
package lupos.operator.physical.noinput
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.iterator.ColumnIteratorMultiValue3
import lupos.operator.physical.POPBase
import lupos.shared.DictionaryValueHelper
import lupos.shared.EGraphOperationType
import lupos.shared.EGraphOperationTypeExt
import lupos.shared.EGraphRefType
import lupos.shared.EGraphRefTypeExt
import lupos.shared.EIndexPatternExt
import lupos.shared.EModifyTypeExt
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.EvaluationException
import lupos.shared.IQuery
import lupos.shared.ITripleStoreDescription
import lupos.shared.MemoryTable
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.TripleStoreManager
import lupos.shared.UnreachableException
import lupos.shared.inline.File
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

public class POPGraphOperation public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField public val silent: Boolean,
    @JvmField public var graph1type: EGraphRefType,
    @JvmField public var graph1iri: String?,
    @JvmField public var graph2type: EGraphRefType,
    @JvmField public var graph2iri: String?,
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
            if (action == EGraphOperationTypeExt.MOVE || action == EGraphOperationTypeExt.ADD) {
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

    /*suspend*/ private fun copyData(source: ITripleStoreDescription, target: ITripleStoreDescription, parent: Partition) {
        val row = source.getIterator(query, arrayOf(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o")), EIndexPatternExt.SPO).evaluate(parent)
        val iterator = arrayOf(row.columns["s"]!!, row.columns["p"]!!, row.columns["o"]!!)
        val cache = target.modify_create_cache(query, EModifyTypeExt.INSERT)
        while (true) {
            val s = iterator[0].next()
            val p = iterator[1].next()
            val o = iterator[2].next()
            if (s == DictionaryValueHelper.nullValue) {
                break
            }
            cache.writeRow(s, p, o, query)
        }
        cache.close()
    }

    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        try {
            val manager = query.getInstance().tripleStoreManager!!
            when (action) {
                EGraphOperationTypeExt.CLEAR -> {
                    when (graph1type) {
                        EGraphRefTypeExt.AllGraphRef -> {
                            for (name in manager.getGraphNames(true)) {
                                manager.clearGraph(query, name)
                            }
                        }
                        EGraphRefTypeExt.DefaultGraphRef -> {
                            manager.clearGraph(query, TripleStoreManager.DEFAULT_GRAPH_NAME)
                        }
                        EGraphRefTypeExt.IriGraphRef -> {
                            manager.clearGraph(query, graph1iri!!)
                        }
                        EGraphRefTypeExt.NamedGraphRef -> {
                            for (name in manager.getGraphNames()) {
                                manager.clearGraph(query, name)
                            }
                        }
                    }
                }
                EGraphOperationTypeExt.DROP -> {
                    when (graph1type) {
                        EGraphRefTypeExt.AllGraphRef -> {
                            manager.clearGraph(query, TripleStoreManager.DEFAULT_GRAPH_NAME)
                            for (name in manager.getGraphNames(false)) {
                                manager.dropGraph(query, name)
                            }
                        }
                        EGraphRefTypeExt.DefaultGraphRef -> {
                            manager.clearGraph(query, TripleStoreManager.DEFAULT_GRAPH_NAME)
                        }
                        EGraphRefTypeExt.IriGraphRef -> {
                            manager.dropGraph(query, graph1iri!!)
                        }
                        EGraphRefTypeExt.NamedGraphRef -> {
                            for (name in manager.getGraphNames(false)) {
                                manager.dropGraph(query, name)
                            }
                        }
                    }
                }
                EGraphOperationTypeExt.CREATE -> {
                    when (graph1type) {
                        EGraphRefTypeExt.IriGraphRef -> {
                            manager.createGraph(query, graph1iri!!)
                        }
                        else -> {
                            SanityCheck.checkUnreachable()
                        }
                    }
                }
                EGraphOperationTypeExt.LOAD -> {
                    val fileName = query.getWorkingDirectory() + graph1iri
                    val target: ITripleStoreDescription = if (graph2type == EGraphRefTypeExt.DefaultGraphRef) {
                        manager.getDefaultGraph()
                    } else {
                        manager.getGraph(graph2iri!!)
                    }
                    val table = MemoryTable.parseFromAny(File(fileName).readAsString(), fileName, query)!!
                    val sa = table.column("s")!!
                    val pa = table.column("p")!!
                    val oa = table.column("o")!!
                    val iterator = arrayOf<ColumnIterator>(
                        ColumnIteratorMultiValue3(sa, sa.size),
                        ColumnIteratorMultiValue3(pa, pa.size),
                        ColumnIteratorMultiValue3(oa, oa.size),
                    )
                    val cache = target.modify_create_cache(query, EModifyTypeExt.INSERT)
                    while (true) {
                        val s = iterator[0].next()
                        val p = iterator[1].next()
                        val o = iterator[2].next()
                        if (s == DictionaryValueHelper.nullValue) {
                            break
                        }
                        cache.writeRow(s, p, o, query)
                    }
                    cache.close()
                }
                EGraphOperationTypeExt.MOVE -> {
                    when (graph1type) {
                        EGraphRefTypeExt.DefaultGraphRef -> {
                            when (graph2type) {
                                EGraphRefTypeExt.DefaultGraphRef -> {
                                }
                                EGraphRefTypeExt.IriGraphRef -> {
                                    val source = manager.getDefaultGraph()
                                    val target = manager.getGraph(graph2iri!!)
                                    manager.clearGraph(query, graph2iri!!)
                                    copyData(source, target, parent)
                                    manager.clearGraph(query, TripleStoreManager.DEFAULT_GRAPH_NAME)
                                }
                                else -> {
                                    SanityCheck.checkUnreachable()
                                }
                            }
                        }
                        EGraphRefTypeExt.IriGraphRef -> {
                            when (graph2type) {
                                EGraphRefTypeExt.DefaultGraphRef -> {
                                    val source = manager.getGraph(graph1iri!!)
                                    val target = manager.getDefaultGraph()
                                    manager.clearGraph(query, TripleStoreManager.DEFAULT_GRAPH_NAME)
                                    copyData(source, target, parent)
                                    manager.clearGraph(query, graph1iri!!)
                                }
                                EGraphRefTypeExt.IriGraphRef -> {
                                    if (graph1iri != graph2iri) {
                                        val source = manager.getGraph(graph1iri!!)
                                        val target = manager.getGraph(graph2iri!!)
                                        manager.clearGraph(query, graph2iri!!)
                                        copyData(source, target, parent)
                                        manager.clearGraph(query, graph1iri!!)
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
                                    val source = manager.getDefaultGraph()
                                    if (!manager.getGraphNames().contains(graph2iri!!)) {
                                        manager.createGraph(query, graph2iri!!)
                                    }
                                    val target = manager.getGraph(graph2iri!!)
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
                                    val source = manager.getGraph(graph1iri!!)
                                    val target = manager.getDefaultGraph()
                                    copyData(source, target, parent)
                                }
                                EGraphRefTypeExt.IriGraphRef -> {
                                    if (graph1iri != graph2iri) {
                                        val source = manager.getGraph(graph1iri!!)
                                        if (!manager.getGraphNames().contains(graph2iri!!)) {
                                            manager.createGraph(query, graph2iri!!)
                                        }
                                        val target = manager.getGraph(graph2iri!!)
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
            e.printStackTrace()
            if (!silent) {
                throw e
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            if (!silent) {
                throw e
            }
        }
        return IteratorBundle(1)
    }
    public override fun usesDictionary(): Boolean {
        var res = super.usesDictionary()
        SanityCheck(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/noinput/POPGraphOperation.kt:345"/*SOURCE_FILE_END*/ },
            {
                res = true
            }
        )
        return res
    }
}
