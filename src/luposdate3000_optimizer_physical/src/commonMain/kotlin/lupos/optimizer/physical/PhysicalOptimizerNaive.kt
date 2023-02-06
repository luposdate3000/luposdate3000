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
package lupos.optimizer.physical
import lupos.operator.arithmetik.AOPBase
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.OPBaseCompound
import lupos.operator.base.Query
import lupos.operator.base.noinput.OPEmptyRow
import lupos.operator.logical.multiinput.LOPJoin
import lupos.operator.logical.multiinput.LOPMinus
import lupos.operator.logical.multiinput.LOPUnion
import lupos.operator.logical.noinput.LOPGraphOperation
import lupos.operator.logical.noinput.LOPModifyData
import lupos.operator.logical.noinput.LOPTriple
import lupos.operator.logical.noinput.LOPValues
import lupos.operator.logical.singleinput.LOPBind
import lupos.operator.logical.singleinput.LOPFilter
import lupos.operator.logical.singleinput.LOPGroup
import lupos.operator.logical.singleinput.LOPMakeBooleanResult
import lupos.operator.logical.singleinput.LOPModify
import lupos.operator.logical.singleinput.LOPProjection
import lupos.operator.logical.singleinput.LOPSort
import lupos.operator.logical.singleinput.modifiers.LOPLimit
import lupos.operator.logical.singleinput.modifiers.LOPOffset
import lupos.operator.logical.singleinput.modifiers.LOPReduced
import lupos.operator.logical.singleinput.modifiers.LOPSortAny
import lupos.operator.physical.POPBase
import lupos.operator.physical.multiinput.POPJoinHashMap
import lupos.operator.physical.multiinput.POPMinus
import lupos.operator.physical.multiinput.POPUnion
import lupos.operator.physical.noinput.POPEmptyRow
import lupos.operator.physical.noinput.POPGraphOperation
import lupos.operator.physical.noinput.POPModifyData
import lupos.operator.physical.noinput.POPNothing
import lupos.operator.physical.noinput.POPValues
import lupos.operator.physical.singleinput.POPBind
import lupos.operator.physical.singleinput.POPFilter
import lupos.operator.physical.singleinput.POPGroup
import lupos.operator.physical.singleinput.POPMakeBooleanResult
import lupos.operator.physical.singleinput.POPModify
import lupos.operator.physical.singleinput.POPProjection
import lupos.operator.physical.singleinput.POPSort
import lupos.operator.physical.singleinput.modifiers.POPLimit
import lupos.operator.physical.singleinput.modifiers.POPOffset
import lupos.operator.physical.singleinput.modifiers.POPReduced
import lupos.optimizer.logical.EOptimizerIDExt
import lupos.optimizer.logical.OptimizerBase
import lupos.shared.EGraphOperationTypeExt
import lupos.shared.EGraphRefTypeExt
import lupos.shared.EIndexPatternExt
import lupos.shared.SanityCheck
import lupos.shared.TripleStoreManager
import lupos.shared.myPrintStackTrace
import lupos.shared.operator.IOPBase

public class PhysicalOptimizerNaive(query: Query) : OptimizerBase(query, EOptimizerIDExt.PhysicalOptimizerNaiveID, "PhysicalOptimizerNaive") {
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        var change = true
        val projectedVariables: List<String>
        try {
            projectedVariables = when {
                parent is LOPProjection -> {
                    parent.getProvidedVariableNames()
                }
                parent is POPProjection -> {
                    parent.getProvidedVariableNamesInternal()
                }
                node is POPBase -> {
                    node.getProvidedVariableNamesInternal()
                }
                else -> {
                    node.getProvidedVariableNames()
                }
            }
            when (node) {
                is OPBaseCompound -> {
                    val cc = node.getChildren()
                    if (cc.filterIsInstance<OPBaseCompound>().isNotEmpty()) {
                        val childs = mutableListOf<IOPBase>()
                        val columns = mutableListOf<List<String>>()
                        for (i in 0 until cc.size) {
                            val c = cc[i]
                            if (c is OPBaseCompound) {
                                val c3 = c.getChildren()
                                for (j in 0 until c3.size) {
                                    val c4 = c3[j]
                                    childs.add(c4)
                                    columns.add(c.columnProjectionOrder[j])
                                }
                            } else {
                                childs.add(c)
                                columns.add(node.columnProjectionOrder[i])
                            }
                        }
                        res = OPBaseCompound(query, childs.toTypedArray(), columns)
                    } else {
                        change = false
                    }
                }
                is LOPSortAny -> {
                    val child = node.getChildren()[0]
                    val v1 = node.possibleSortOrder
                    val v2 = child.getMySortPriority()
                    var flag = v1.size == v2.size
                    var i = 0
                    while (flag && i < v1.size) {
                        if (v1[i].variableName != v2[i].variableName || v1[i].sortType != v2[i].sortType) {
                            flag = false
                        }
                        i++
                    }
                    res = if (flag) {
                        child
                    } else {
                        POPSort(query, projectedVariables, arrayOf(), true, child)
                    }
                }
                is LOPGraphOperation -> {
                           if(SanityCheck.enabled){if(!(        parent is OPBaseCompound    )){throw Exception("SanityCheck failed")}}
                    val manager = query.getInstance().tripleStoreManager!!
                    fun createCopy(sourceName: String, targetName: String): POPBase {
                        try {
                            manager.createGraph(query, sourceName) // TODO this is very bad, because it is an modification during query optimisation phase
                        } catch (e: Throwable) {
                            e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_physical/src/commonMain/kotlin/lupos/optimizer/physical/PhysicalOptimizerNaive.kt:139"/*SOURCE_FILE_END*/)
                        }
                        return POPModify(
                            query,
                            listOf(),
                            listOf(LOPTriple(query, AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o"), targetName, false)),
                            listOf(),
                            manager.getGraph(sourceName)
                                .getIterator(
                                    query,
                                    arrayOf(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o")),
                                    EIndexPatternExt.SPO
                                )
                        )
                    }
                    when (node.action) {
                        EGraphOperationTypeExt.COPY -> {
                            when (node.graph1type) {
                                EGraphRefTypeExt.DefaultGraphRef -> {
                                    when (node.graph2type) {
                                        EGraphRefTypeExt.DefaultGraphRef -> {
                                            res = POPNothing(query, listOf())
                                        }
                                        EGraphRefTypeExt.IriGraphRef -> {
                                            res = OPBaseCompound(
                                                query,
                                                arrayOf(
                                                    POPGraphOperation(query, projectedVariables, node.silent, node.graph2type, node.graph2iri, node.graph2type, node.graph2iri, EGraphOperationTypeExt.CLEAR),
                                                    createCopy(TripleStoreManager.DEFAULT_GRAPH_NAME, node.graph2iri!!),
                                                ),
                                                listOf(listOf(), listOf())
                                            )
                                        }
                                        else -> {
                                            SanityCheck.checkUnreachable()
                                        }
                                    }
                                }
                                EGraphRefTypeExt.IriGraphRef -> {
                                    when (node.graph2type) {
                                        EGraphRefTypeExt.DefaultGraphRef -> {
                                            res = OPBaseCompound(
                                                query,
                                                arrayOf(
                                                    POPGraphOperation(query, projectedVariables, node.silent, node.graph2type, node.graph2iri, node.graph2type, node.graph2iri, EGraphOperationTypeExt.CLEAR),
                                                    createCopy(node.graph1iri!!, TripleStoreManager.DEFAULT_GRAPH_NAME),
                                                ),
                                                listOf(listOf(), listOf())
                                            )
                                        }
                                        EGraphRefTypeExt.IriGraphRef -> {
                                            res = if (node.graph1iri != node.graph2iri) {
                                                OPBaseCompound(
                                                    query,
                                                    arrayOf(
                                                        POPGraphOperation(query, projectedVariables, node.silent, node.graph2type, node.graph2iri, node.graph2type, node.graph2iri, EGraphOperationTypeExt.CLEAR),
                                                        createCopy(node.graph1iri!!, node.graph2iri!!),
                                                    ),
                                                    listOf(listOf(), listOf())
                                                )
                                            } else {
                                                POPNothing(query, listOf())
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
                            when (node.graph1type) {
                                EGraphRefTypeExt.DefaultGraphRef -> {
                                    when (node.graph2type) {
                                        EGraphRefTypeExt.DefaultGraphRef -> {
                                            res = POPNothing(query, listOf())
                                        }
                                        EGraphRefTypeExt.IriGraphRef -> {
                                            res = OPBaseCompound(
                                                query,
                                                arrayOf(
                                                    POPGraphOperation(query, projectedVariables, node.silent, node.graph2type, node.graph2iri, node.graph2type, node.graph2iri, EGraphOperationTypeExt.CLEAR),
                                                    createCopy(TripleStoreManager.DEFAULT_GRAPH_NAME, node.graph2iri!!),
                                                    POPGraphOperation(query, projectedVariables, node.silent, node.graph1type, node.graph1iri, node.graph1type, node.graph1iri, EGraphOperationTypeExt.CLEAR),
                                                ),
                                                listOf(listOf(), listOf(), listOf())
                                            )
                                        }
                                        else -> {
                                            SanityCheck.checkUnreachable()
                                        }
                                    }
                                }
                                EGraphRefTypeExt.IriGraphRef -> {
                                    when (node.graph2type) {
                                        EGraphRefTypeExt.DefaultGraphRef -> {
                                            res = OPBaseCompound(
                                                query,
                                                arrayOf(
                                                    POPGraphOperation(query, projectedVariables, node.silent, node.graph2type, node.graph2iri, node.graph2type, node.graph2iri, EGraphOperationTypeExt.CLEAR),
                                                    createCopy(node.graph1iri!!, TripleStoreManager.DEFAULT_GRAPH_NAME),
                                                    POPGraphOperation(query, projectedVariables, node.silent, node.graph1type, node.graph1iri, node.graph1type, node.graph1iri, EGraphOperationTypeExt.CLEAR),
                                                ),
                                                listOf(listOf(), listOf(), listOf())
                                            )
                                        }
                                        EGraphRefTypeExt.IriGraphRef -> {
                                            res = if (node.graph1iri != node.graph2iri) {
                                                OPBaseCompound(
                                                    query,
                                                    arrayOf(
                                                        POPGraphOperation(query, projectedVariables, node.silent, node.graph2type, node.graph2iri, node.graph2type, node.graph2iri, EGraphOperationTypeExt.CLEAR),
                                                        createCopy(node.graph1iri!!, node.graph2iri!!),
                                                        POPGraphOperation(query, projectedVariables, node.silent, node.graph1type, node.graph1iri, node.graph1type, node.graph1iri, EGraphOperationTypeExt.CLEAR),
                                                    ),
                                                    listOf(listOf(), listOf(), listOf())
                                                )
                                            } else {
                                                POPNothing(query, listOf())
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
                            when (node.graph1type) {
                                EGraphRefTypeExt.DefaultGraphRef -> {
                                    when (node.graph2type) {
                                        EGraphRefTypeExt.DefaultGraphRef -> {
                                            res = POPNothing(query, listOf())
                                        }
                                        EGraphRefTypeExt.IriGraphRef -> {
                                            res = OPBaseCompound(
                                                query,
                                                arrayOf(
                                                    POPGraphOperation(query, projectedVariables, true, node.graph2type, node.graph2iri, node.graph2type, node.graph2iri, EGraphOperationTypeExt.CREATE),
                                                    createCopy(TripleStoreManager.DEFAULT_GRAPH_NAME, node.graph2iri!!),
                                                ),
                                                listOf(listOf(), listOf())
                                            )
                                        }
                                        else -> {
                                            SanityCheck.checkUnreachable()
                                        }
                                    }
                                }
                                EGraphRefTypeExt.IriGraphRef -> {
                                    when (node.graph2type) {
                                        EGraphRefTypeExt.DefaultGraphRef -> {
                                            res = OPBaseCompound(
                                                query,
                                                arrayOf(
                                                    POPGraphOperation(query, projectedVariables, true, node.graph2type, node.graph2iri, node.graph2type, node.graph2iri, EGraphOperationTypeExt.CREATE),
                                                    createCopy(node.graph1iri!!, TripleStoreManager.DEFAULT_GRAPH_NAME),
                                                ),
                                                listOf(listOf(), listOf())
                                            )
                                        }
                                        EGraphRefTypeExt.IriGraphRef -> {
                                            res = if (node.graph1iri != node.graph2iri) {
                                                OPBaseCompound(
                                                    query,
                                                    arrayOf(
                                                        POPGraphOperation(query, projectedVariables, true, node.graph2type, node.graph2iri, node.graph2type, node.graph2iri, EGraphOperationTypeExt.CREATE),
                                                        createCopy(node.graph1iri!!, node.graph2iri!!),
                                                    ),
                                                    listOf(listOf(), listOf())
                                                )
                                            } else {
                                                POPNothing(query, listOf())
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
                        else -> {
                            res = POPGraphOperation(query, projectedVariables, node.silent, node.graph1type, node.graph1iri, node.graph2type, node.graph2iri, node.action)
                            res.sortPriorities = node.sortPriorities
                            res.mySortPriority = node.mySortPriority
                            res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                        }
                    }
                }
                is LOPModify -> {
                    res = POPModify(query, projectedVariables, node.insert, node.delete, node.getChildren()[0])
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPModifyData -> {
                    res = POPModifyData(query, projectedVariables, node.type, node.data)
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPProjection -> {
                    res = POPProjection(query, projectedVariables, node.getChildren()[0])
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPMakeBooleanResult -> {
                    res = if (node.getChildren()[0] is LOPProjection || node.getChildren()[0] is POPProjection) {
                        POPMakeBooleanResult(query, projectedVariables, node.getChildren()[0].getChildren()[0])
                    } else {
                        POPMakeBooleanResult(query, projectedVariables, node.getChildren()[0])
                    }
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPValues -> {
                    res = POPValues(query, projectedVariables, node)
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPLimit -> {
                    res = POPLimit(query, projectedVariables, node.limit, node.getChildren()[0])
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPReduced -> {
                    res = POPReduced(query, projectedVariables, node.getChildren()[0])
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPOffset -> {
                    res = POPOffset(query, projectedVariables, node.offset, node.getChildren()[0])
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPGroup -> {
                    res = POPGroup(query, projectedVariables, node.by, node.bindings, node.getChildren()[0])
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPUnion -> {
                    val countA = node.getChildren()[0].getChildrenCountRecoursive()
                    val countB = node.getChildren()[1].getChildrenCountRecoursive()
                    res = if (countA < countB) {
                        POPUnion(query, projectedVariables, node.getChildren()[0], node.getChildren()[1])
                    } else {
                        POPUnion(query, projectedVariables, node.getChildren()[1], node.getChildren()[0])
                    }
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPMinus -> {
                    res = POPMinus(query, projectedVariables, node.getChildren()[0], node.getChildren()[1])
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPSort -> {
                    if (parent !is LOPSort) {
                        val sortBy = mutableListOf<AOPVariable>()
                        sortBy.add(node.by)
                        var child = node.getChildren()[0]
                        while (child is LOPSort) {
                            sortBy.add(child.by)
                            child = child.getChildren()[0]
                        }
                        res = POPSort(query, projectedVariables, sortBy.toTypedArray(), node.asc, child)
                        res.sortPriorities = node.sortPriorities
                        res.mySortPriority = node.mySortPriority
                        res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                    } else {
                        change = false
                    }
                }
                is LOPFilter -> {
                    res = POPFilter(query, projectedVariables, node.getChildren()[1] as AOPBase, node.getChildren()[0])
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPBind -> {
                    res = POPBind(query, projectedVariables, node.name, node.getChildren()[1] as AOPBase, node.getChildren()[0])
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is LOPJoin -> {
                    res = POPJoinHashMap(query, projectedVariables, node.getChildren()[0], node.getChildren()[1], node.optional)
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                is OPEmptyRow -> {
                    res = POPEmptyRow(query, projectedVariables)
                    res.sortPriorities = node.sortPriorities
                    res.mySortPriority = node.mySortPriority
                    res.sortPrioritiesInitialized = node.sortPrioritiesInitialized
                }
                else -> {
                    change = false
                }
            }
        } finally {
            if (change) {
                onChange()
            }
        }
        return res
    }
}
