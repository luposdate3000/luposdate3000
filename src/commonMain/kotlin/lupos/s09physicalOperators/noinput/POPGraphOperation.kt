package lupos.s09physicalOperators.noinput
import lupos.s00misc.Coverage
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EGraphRefType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedGraph
import lupos.s15tripleStoreDistributed.DistributedTripleStore
class POPGraphOperation(query: Query,
                        projectedVariables: List<String>,
                        val silent: Boolean,
                        var graph1type: EGraphRefType = EGraphRefType.DefaultGraphRef,
                        var graph1iri: String? = null,
                        var graph2type: EGraphRefType = EGraphRefType.DefaultGraphRef,
                        var graph2iri: String? = null,
                        val action: EGraphOperationType) : POPBase(query, projectedVariables, EOperatorID.POPGraphOperationID, "POPGraphOperation", arrayOf(), ESortPriority.PREVENT_ANY) {
    override fun toSparqlQuery() = toSparql()
    override fun toSparql(): String {
Coverage.funStart(11018)
        var res = ""
Coverage.statementStart(11019)
        when (action) {
            EGraphOperationType.CLEAR -> {
Coverage.whenCaseStart(11021)
                res += "CLEAR"
Coverage.statementStart(11022)
            }
            EGraphOperationType.DROP -> {
Coverage.whenCaseStart(11023)
                res += "DROP"
Coverage.statementStart(11024)
            }
            EGraphOperationType.CREATE -> {
Coverage.whenCaseStart(11025)
                res += "CREATE"
Coverage.statementStart(11026)
            }
            EGraphOperationType.COPY -> {
Coverage.whenCaseStart(11027)
                res += "COPY"
Coverage.statementStart(11028)
            }
            EGraphOperationType.MOVE -> {
Coverage.whenCaseStart(11029)
                res += "MOVE"
Coverage.statementStart(11030)
            }
            EGraphOperationType.ADD -> {
Coverage.whenCaseStart(11031)
                res += "ADD"
Coverage.statementStart(11032)
            }
        }
Coverage.statementStart(11033)
        if (silent) {
Coverage.ifStart(11034)
            res += " SILENT "
Coverage.statementStart(11035)
        } else {
Coverage.ifStart(11036)
            res += " "
Coverage.statementStart(11037)
        }
Coverage.statementStart(11038)
        when (graph1type) {
            EGraphRefType.AllGraphRef -> {
Coverage.whenCaseStart(11040)
                res += "ALL"
Coverage.statementStart(11041)
            }
            EGraphRefType.DefaultGraphRef -> {
Coverage.whenCaseStart(11042)
                res += "DEFAULT"
Coverage.statementStart(11043)
            }
            EGraphRefType.NamedGraphRef -> {
Coverage.whenCaseStart(11044)
                res += "NAMED"
Coverage.statementStart(11045)
            }
            EGraphRefType.IriGraphRef -> {
Coverage.whenCaseStart(11046)
                res += "GRAPH <" + graph1iri!! + ">"
Coverage.statementStart(11047)
            }
        }
Coverage.statementStart(11048)
        if (action == EGraphOperationType.COPY || action == EGraphOperationType.MOVE || action == EGraphOperationType.ADD) {
Coverage.ifStart(11049)
            res += " TO "
Coverage.statementStart(11050)
            when (graph2type) {
                EGraphRefType.AllGraphRef -> {
Coverage.whenCaseStart(11052)
                    res += "ALL"
Coverage.statementStart(11053)
                }
                EGraphRefType.DefaultGraphRef -> {
Coverage.whenCaseStart(11054)
                    res += "DEFAULT"
Coverage.statementStart(11055)
                }
                EGraphRefType.NamedGraphRef -> {
Coverage.whenCaseStart(11056)
                    res += "NAMED"
Coverage.statementStart(11057)
                }
                EGraphRefType.IriGraphRef -> {
Coverage.whenCaseStart(11058)
                    res += "GRAPH <" + graph2iri!! + ">"
Coverage.statementStart(11059)
                }
            }
Coverage.statementStart(11060)
        }
Coverage.statementStart(11061)
        return res
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(11062)
        if (other !is POPGraphOperation) {
Coverage.ifStart(11063)
            return false
        }
Coverage.statementStart(11064)
        if (silent != other.silent) {
Coverage.ifStart(11065)
            return false
        }
Coverage.statementStart(11066)
        if (graph1iri != other.graph1iri) {
Coverage.ifStart(11067)
            return false
        }
Coverage.statementStart(11068)
        if (graph1type != other.graph1type) {
Coverage.ifStart(11069)
            return false
        }
Coverage.statementStart(11070)
        if (graph2iri != other.graph2iri) {
Coverage.ifStart(11071)
            return false
        }
Coverage.statementStart(11072)
        if (graph2type != other.graph2type) {
Coverage.ifStart(11073)
            return false
        }
Coverage.statementStart(11074)
        if (action != other.action) {
Coverage.ifStart(11075)
            return false
        }
Coverage.statementStart(11076)
        for (i in children.indices) {
Coverage.forLoopStart(11077)
            if (!children[i].equals(other.children[i])) {
Coverage.ifStart(11078)
                return false
            }
Coverage.statementStart(11079)
        }
Coverage.statementStart(11080)
        return true
    }
    override fun cloneOP() = POPGraphOperation(query, projectedVariables, silent, graph1type, graph1iri, graph2type, graph2iri, action)
    suspend fun copyData(source: DistributedGraph, target: DistributedGraph) {
Coverage.funStart(11081)
        val row = source.getIterator(EIndexPattern.SPO).evaluate()
Coverage.statementStart(11082)
        val iterator = arrayOf(row.columns["s"]!!, row.columns["p"]!!, row.columns["o"]!!)
Coverage.statementStart(11083)
        target.modify(iterator, EModifyType.INSERT)
Coverage.statementStart(11084)
    }
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(11085)
        try {
Coverage.statementStart(11086)
            when (action) {
                EGraphOperationType.CLEAR -> {
Coverage.whenCaseStart(11088)
                    when (graph1type) {
                        EGraphRefType.AllGraphRef -> {
Coverage.whenCaseStart(11090)
                            for (name in DistributedTripleStore.getGraphNames(true)) {
Coverage.forLoopStart(11091)
                                DistributedTripleStore.clearGraph(query, name)
Coverage.statementStart(11092)
                            }
Coverage.statementStart(11093)
                        }
                        EGraphRefType.DefaultGraphRef -> {
Coverage.whenCaseStart(11094)
                            DistributedTripleStore.clearGraph(query, PersistentStoreLocal.defaultGraphName)
Coverage.statementStart(11095)
                        }
                        EGraphRefType.IriGraphRef -> {
Coverage.whenCaseStart(11096)
                            DistributedTripleStore.clearGraph(query, graph1iri!!)
Coverage.statementStart(11097)
                        }
                        EGraphRefType.NamedGraphRef -> {
Coverage.whenCaseStart(11098)
                            for (name in DistributedTripleStore.getGraphNames()) {
Coverage.forLoopStart(11099)
                                DistributedTripleStore.clearGraph(query, name)
Coverage.statementStart(11100)
                            }
Coverage.statementStart(11101)
                        }
                    }
Coverage.statementStart(11102)
                }
                EGraphOperationType.DROP -> {
Coverage.whenCaseStart(11103)
                    when (graph1type) {
                        EGraphRefType.AllGraphRef -> {
Coverage.whenCaseStart(11105)
                            DistributedTripleStore.clearGraph(query, PersistentStoreLocal.defaultGraphName)
Coverage.statementStart(11106)
                            for (name in DistributedTripleStore.getGraphNames(false)) {
Coverage.forLoopStart(11107)
                                DistributedTripleStore.dropGraph(query, name)
Coverage.statementStart(11108)
                            }
Coverage.statementStart(11109)
                        }
                        EGraphRefType.DefaultGraphRef -> {
Coverage.whenCaseStart(11110)
                            DistributedTripleStore.clearGraph(query, PersistentStoreLocal.defaultGraphName)
Coverage.statementStart(11111)
                        }
                        EGraphRefType.IriGraphRef -> {
Coverage.whenCaseStart(11112)
                            DistributedTripleStore.dropGraph(query, graph1iri!!)
Coverage.statementStart(11113)
                        }
                        EGraphRefType.NamedGraphRef -> {
Coverage.whenCaseStart(11114)
                            for (name in DistributedTripleStore.getGraphNames(false)) {
Coverage.forLoopStart(11115)
                                DistributedTripleStore.dropGraph(query, name)
Coverage.statementStart(11116)
                            }
Coverage.statementStart(11117)
                        }
                    }
Coverage.statementStart(11118)
                }
                EGraphOperationType.CREATE -> {
Coverage.whenCaseStart(11119)
                    when (graph1type) {
                        EGraphRefType.IriGraphRef -> {
Coverage.whenCaseStart(11121)
                            DistributedTripleStore.createGraph(query, graph1iri!!)
Coverage.statementStart(11122)
                        }
                        else -> {
Coverage.whenCaseStart(11123)
                            SanityCheck.check { false }
Coverage.statementStart(11124)
                        }
                    }
Coverage.statementStart(11125)
                }
                EGraphOperationType.COPY -> {
Coverage.whenCaseStart(11126)
                    when (graph1type) {
                        EGraphRefType.DefaultGraphRef -> {
Coverage.whenCaseStart(11128)
                            when (graph2type) {
                                EGraphRefType.DefaultGraphRef -> {
Coverage.whenCaseStart(11130)
                                }
                                EGraphRefType.IriGraphRef -> {
Coverage.whenCaseStart(11131)
                                    val source = DistributedTripleStore.getDefaultGraph(query)
Coverage.statementStart(11132)
                                    val target = DistributedTripleStore.getNamedGraph(query, graph2iri!!)
Coverage.statementStart(11133)
                                    DistributedTripleStore.clearGraph(query, graph2iri!!)
Coverage.statementStart(11134)
                                    copyData(source, target)
Coverage.statementStart(11135)
                                }
                                else -> {
Coverage.whenCaseStart(11136)
                                    SanityCheck.check { false }
Coverage.statementStart(11137)
                                }
                            }
Coverage.statementStart(11138)
                        }
                        EGraphRefType.IriGraphRef -> {
Coverage.whenCaseStart(11139)
                            when (graph2type) {
                                EGraphRefType.DefaultGraphRef -> {
Coverage.whenCaseStart(11141)
                                    val source = DistributedTripleStore.getNamedGraph(query, graph1iri!!)
Coverage.statementStart(11142)
                                    val target = DistributedTripleStore.getDefaultGraph(query)
Coverage.statementStart(11143)
                                    DistributedTripleStore.clearGraph(query, PersistentStoreLocal.defaultGraphName)
Coverage.statementStart(11144)
                                    copyData(source, target)
Coverage.statementStart(11145)
                                }
                                EGraphRefType.IriGraphRef -> {
Coverage.whenCaseStart(11146)
                                    if (graph1iri != graph2iri) {
Coverage.ifStart(11147)
                                        val source = DistributedTripleStore.getNamedGraph(query, graph1iri!!)
Coverage.statementStart(11148)
                                        val target = DistributedTripleStore.getNamedGraph(query, graph2iri!!)
Coverage.statementStart(11149)
                                        DistributedTripleStore.clearGraph(query, graph2iri!!)
Coverage.statementStart(11150)
                                        copyData(source, target)
Coverage.statementStart(11151)
                                    }
Coverage.statementStart(11152)
                                }
                                else -> {
Coverage.whenCaseStart(11153)
                                    SanityCheck.check { false }
Coverage.statementStart(11154)
                                }
                            }
Coverage.statementStart(11155)
                        }
                        else -> {
Coverage.whenCaseStart(11156)
                            SanityCheck.check { false }
Coverage.statementStart(11157)
                        }
                    }
Coverage.statementStart(11158)
                }
                EGraphOperationType.MOVE -> {
Coverage.whenCaseStart(11159)
                    when (graph1type) {
                        EGraphRefType.DefaultGraphRef -> {
Coverage.whenCaseStart(11161)
                            when (graph2type) {
                                EGraphRefType.DefaultGraphRef -> {
Coverage.whenCaseStart(11163)
                                }
                                EGraphRefType.IriGraphRef -> {
Coverage.whenCaseStart(11164)
                                    val source = DistributedTripleStore.getDefaultGraph(query)
Coverage.statementStart(11165)
                                    val target = DistributedTripleStore.getNamedGraph(query, graph2iri!!)
Coverage.statementStart(11166)
                                    DistributedTripleStore.clearGraph(query, graph2iri!!)
Coverage.statementStart(11167)
                                    copyData(source, target)
Coverage.statementStart(11168)
                                    DistributedTripleStore.clearGraph(query, PersistentStoreLocal.defaultGraphName)
Coverage.statementStart(11169)
                                }
                                else -> {
Coverage.whenCaseStart(11170)
                                    SanityCheck.check { false }
Coverage.statementStart(11171)
                                }
                            }
Coverage.statementStart(11172)
                        }
                        EGraphRefType.IriGraphRef -> {
Coverage.whenCaseStart(11173)
                            when (graph2type) {
                                EGraphRefType.DefaultGraphRef -> {
Coverage.whenCaseStart(11175)
                                    val source = DistributedTripleStore.getNamedGraph(query, graph1iri!!)
Coverage.statementStart(11176)
                                    val target = DistributedTripleStore.getDefaultGraph(query)
Coverage.statementStart(11177)
                                    DistributedTripleStore.clearGraph(query, PersistentStoreLocal.defaultGraphName)
Coverage.statementStart(11178)
                                    copyData(source, target)
Coverage.statementStart(11179)
                                    DistributedTripleStore.clearGraph(query, graph1iri!!)
Coverage.statementStart(11180)
                                }
                                EGraphRefType.IriGraphRef -> {
Coverage.whenCaseStart(11181)
                                    if (graph1iri != graph2iri) {
Coverage.ifStart(11182)
                                        val source = DistributedTripleStore.getNamedGraph(query, graph1iri!!)
Coverage.statementStart(11183)
                                        val target = DistributedTripleStore.getNamedGraph(query, graph2iri!!)
Coverage.statementStart(11184)
                                        DistributedTripleStore.clearGraph(query, graph2iri!!)
Coverage.statementStart(11185)
                                        copyData(source, target)
Coverage.statementStart(11186)
                                        DistributedTripleStore.clearGraph(query, graph1iri!!)
Coverage.statementStart(11187)
                                    }
Coverage.statementStart(11188)
                                }
                                else -> {
Coverage.whenCaseStart(11189)
                                    SanityCheck.check { false }
Coverage.statementStart(11190)
                                }
                            }
Coverage.statementStart(11191)
                        }
                        else -> {
Coverage.whenCaseStart(11192)
                            SanityCheck.check { false }
Coverage.statementStart(11193)
                        }
                    }
Coverage.statementStart(11194)
                }
                EGraphOperationType.ADD -> {
Coverage.whenCaseStart(11195)
                    when (graph1type) {
                        EGraphRefType.DefaultGraphRef -> {
Coverage.whenCaseStart(11197)
                            when (graph2type) {
                                EGraphRefType.DefaultGraphRef -> {
Coverage.whenCaseStart(11199)
                                }
                                EGraphRefType.IriGraphRef -> {
Coverage.whenCaseStart(11200)
                                    val source = DistributedTripleStore.getDefaultGraph(query)
Coverage.statementStart(11201)
                                    val target = DistributedTripleStore.getNamedGraph(query, graph2iri!!)
Coverage.statementStart(11202)
                                    copyData(source, target)
Coverage.statementStart(11203)
                                }
                                else -> {
Coverage.whenCaseStart(11204)
                                    SanityCheck.check { false }
Coverage.statementStart(11205)
                                }
                            }
Coverage.statementStart(11206)
                        }
                        EGraphRefType.IriGraphRef -> {
Coverage.whenCaseStart(11207)
                            when (graph2type) {
                                EGraphRefType.DefaultGraphRef -> {
Coverage.whenCaseStart(11209)
                                    val source = DistributedTripleStore.getNamedGraph(query, graph1iri!!)
Coverage.statementStart(11210)
                                    val target = DistributedTripleStore.getDefaultGraph(query)
Coverage.statementStart(11211)
                                    copyData(source, target)
Coverage.statementStart(11212)
                                }
                                EGraphRefType.IriGraphRef -> {
Coverage.whenCaseStart(11213)
                                    if (graph1iri != graph2iri) {
Coverage.ifStart(11214)
                                        val source = DistributedTripleStore.getNamedGraph(query, graph1iri!!)
Coverage.statementStart(11215)
                                        val target = DistributedTripleStore.getNamedGraph(query, graph2iri!!)
Coverage.statementStart(11216)
                                        copyData(source, target)
Coverage.statementStart(11217)
                                    }
Coverage.statementStart(11218)
                                }
                                else -> {
Coverage.whenCaseStart(11219)
                                    SanityCheck.check { false }
Coverage.statementStart(11220)
                                }
                            }
Coverage.statementStart(11221)
                        }
                        else -> {
Coverage.whenCaseStart(11222)
                            SanityCheck.check { false }
Coverage.statementStart(11223)
                        }
                    }
Coverage.statementStart(11224)
                }
            }
Coverage.statementStart(11225)
        } catch (e: Throwable) {
Coverage.statementStart(11226)
            if (!silent) {
Coverage.ifStart(11227)
                throw e
            }
Coverage.statementStart(11228)
        }
Coverage.statementStart(11229)
        return IteratorBundle(1)
    }
}
