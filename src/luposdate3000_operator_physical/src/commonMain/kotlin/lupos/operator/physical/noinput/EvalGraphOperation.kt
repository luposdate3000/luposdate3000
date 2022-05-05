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
import lupos.operator.base.iterator.ColumnIteratorMultiValue3
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.EGraphOperationType
import lupos.shared.EGraphOperationTypeExt
import lupos.shared.EGraphRefType
import lupos.shared.EGraphRefTypeExt
import lupos.shared.EModifyTypeExt
import lupos.shared.EvaluationException
import lupos.shared.IQuery
import lupos.shared.ITripleStoreDescription
import lupos.shared.MemoryTable
import lupos.shared.TripleStoreManager
import lupos.shared.inline.File
import lupos.shared.myPrintStackTrace
import lupos.shared.myPrintStackTraceAndThrowAgain
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle

public object EvalGraphOperation {
    private class EvalLocalClass(
        val silent: Boolean,
        val graph1type: EGraphRefType,
        val graph1iri: String?,
        val graph2type: EGraphRefType,
        val graph2iri: String?,
        val action: EGraphOperationType,
        val query: IQuery,
    ) : ColumnIterator() {
        private var __first = true
        override fun close() {
            __first = false
        }

        override fun next(): DictionaryValueType {
            if (!__first) {
                return DictionaryValueHelper.nullValue
            }
            // println("EvalGraphOperation $silent $graph1type $graph1iri $graph2type $graph2iri $action .... ${EGraphRefTypeExt.names[graph1type]} ${EGraphRefTypeExt.names[graph2type]} ${EGraphOperationTypeExt.names[action]}")
            __first = false
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
                                TODO("EvalGraphOperation b $graph1type")
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
                        val cache = target.modify_create_cache(query, EModifyTypeExt.INSERT, -1, false)
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
                    else -> {
                        TODO("EvalGraphOperation c $action")
                    }
                }
            } catch (e: EvaluationException) {
                if (!silent) {
                    e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/noinput/EvalGraphOperation.kt:145"/*SOURCE_FILE_END*/)
                } else {
                    e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/noinput/EvalGraphOperation.kt:147"/*SOURCE_FILE_END*/)
                }
            } catch (e: Throwable) {
                if (!silent) {
                    e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/noinput/EvalGraphOperation.kt:151"/*SOURCE_FILE_END*/)
                } else {
                    e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/noinput/EvalGraphOperation.kt:153"/*SOURCE_FILE_END*/)
                }
            }
            return DictionaryValueHelper.booleanTrueValue
        }
    }

    public operator fun invoke(
        silent: Boolean,
        graph1type: EGraphRefType,
        graph1iri: String?,
        graph2type: EGraphRefType,
        graph2iri: String?,
        action: EGraphOperationType,
        query: IQuery,
    ): IteratorBundle {
        return IteratorBundle(
            mapOf(
                "?success" to EvalLocalClass(silent, graph1type, graph1iri, graph2type, graph2iri, action, query)
            )
        )
    }
}
