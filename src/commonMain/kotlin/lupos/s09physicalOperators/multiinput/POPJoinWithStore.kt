package lupos.s09physicalOperators.multiinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.ESortType
import lupos.s00misc.MyListInt
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorQueue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore
class POPJoinWithStore(query: Query, projectedVariables: List<String>, childA: OPBase, val childB: LOPTriple, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinWithStoreID, "POPJoinWithStore", arrayOf(childA), ESortPriority.SAME_AS_CHILD) {
    override fun toSparql(): String {
Coverage.funStart(10824)
        if (optional) {
Coverage.ifStart(10825)
            return "OPTIONAL{" + children[0].toSparql() + childB.toSparql() + "}"
        }
Coverage.statementStart(10826)
        return children[0].toSparql() + childB.toSparql()
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(10827)
        if (other !is POPJoinWithStore) {
Coverage.ifStart(10828)
            return false
        }
Coverage.statementStart(10829)
        if (optional != other.optional) {
Coverage.ifStart(10830)
            return false
        }
Coverage.statementStart(10831)
        for (i in children.indices) {
Coverage.forLoopStart(10832)
            if (!children[i].equals(other.children[i])) {
Coverage.ifStart(10833)
                return false
            }
Coverage.statementStart(10834)
        }
Coverage.statementStart(10835)
        return true
    }
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(10836)
        SanityCheck.check { !optional }
Coverage.statementStart(10837)
        SanityCheck.check { !childB.graphVar }
Coverage.statementStart(10838)
        val childAv = children[0].evaluate()
Coverage.statementStart(10839)
        val childA = children[0]
Coverage.statementStart(10840)
        val columnsINAO = mutableListOf<ColumnIterator>()
Coverage.statementStart(10841)
        val columnsINAJ = mutableListOf<ColumnIterator>()
Coverage.statementStart(10842)
        val columnsOUTAO = mutableListOf<ColumnIteratorQueue>()
Coverage.statementStart(10843)
        val columnsOUTAJ = mutableListOf<ColumnIteratorQueue>()
Coverage.statementStart(10844)
        val columnsOUTB = mutableListOf<ColumnIteratorQueue>()
Coverage.statementStart(10845)
        val columnsOUT = mutableListOf<ColumnIteratorQueue>()
Coverage.statementStart(10846)
        val variablINBO = mutableListOf<String>()
Coverage.statementStart(10847)
        val indicesINBJ = MyListInt()
Coverage.statementStart(10848)
        val outMap = mutableMapOf<String, ColumnIterator>()
Coverage.statementStart(10849)
        val tmp2 = mutableListOf<String>()
Coverage.statementStart(10850)
        tmp2.addAll(childA.getProvidedVariableNames())
Coverage.statementStart(10851)
        val columnsTmp = LOPJoin.getColumns(childA.getProvidedVariableNames(), childB.getProvidedVariableNames())
Coverage.statementStart(10852)
        var localSortPriority = mutableListOf<String>()
Coverage.statementStart(10853)
        localSortPriority.addAll(childB.mySortPriority.map { it.variableName })
Coverage.statementStart(10854)
        val paramsHelper = Array<OPBase>(3) {
Coverage.statementStart(10855)
            var tmp = childB.children[it] as AOPBase
Coverage.statementStart(10856)
            if (tmp is AOPVariable && columnsTmp[0].contains(tmp.name)) {
Coverage.ifStart(10857)
                localSortPriority.remove(tmp.name)
Coverage.statementStart(10858)
                tmp = AOPConstant(query, 0)
Coverage.statementStart(10859)
            }
Coverage.statementStart(10860)
/*return*/ tmp
        }
Coverage.statementStart(10861)
        val index = LOPTriple.getIndex(paramsHelper, localSortPriority)
Coverage.statementStart(10862)
        for (i in 0 until 3) {
Coverage.forLoopStart(10863)
            val j = index.tripleIndicees[i]
Coverage.statementStart(10864)
            val t = childB.children[j]
Coverage.statementStart(10865)
            if (t is AOPVariable) {
Coverage.ifStart(10866)
                val name = t.name
Coverage.statementStart(10867)
                if (columnsTmp[0].contains(name)) {
Coverage.ifStart(10868)
                    SanityCheck.check { name != "_" }
Coverage.statementStart(10869)
                    val it = ColumnIteratorQueue()
Coverage.statementStart(10870)
                    for (k in 0 until 3) {
Coverage.forLoopStart(10871)
                        val cc = childB.children[k]
Coverage.statementStart(10872)
                        if (cc is AOPVariable && cc.name == name) {
Coverage.ifStart(10873)
                            indicesINBJ.add(k)
Coverage.statementStart(10874)
                            break
                        }
Coverage.statementStart(10875)
                    }
Coverage.statementStart(10876)
                    tmp2.remove(name)
Coverage.statementStart(10877)
                    if (projectedVariables.contains(name)) {
Coverage.ifStart(10878)
                        columnsOUT.add(it)
Coverage.statementStart(10879)
                        columnsINAJ.add(0, childAv.columns[name]!!)
Coverage.statementStart(10880)
                        columnsOUTAJ.add(0, it)
Coverage.statementStart(10881)
                        outMap[name] = it
Coverage.statementStart(10882)
                    } else {
Coverage.ifStart(10883)
                        columnsINAJ.add(childAv.columns[name]!!)
Coverage.statementStart(10884)
                    }
Coverage.statementStart(10885)
                } else {
Coverage.ifStart(10886)
                    SanityCheck.check { columnsTmp[2].contains(name) || name == "_" }
Coverage.statementStart(10887)
                    if (name != "_") {
Coverage.ifStart(10888)
                        variablINBO.add(name)
Coverage.statementStart(10889)
                        val it = ColumnIteratorQueue()
Coverage.statementStart(10890)
                        columnsOUTB.add(it)
Coverage.statementStart(10891)
                        columnsOUT.add(it)
Coverage.statementStart(10892)
                        outMap[name] = it
Coverage.statementStart(10893)
                    }
Coverage.statementStart(10894)
                }
Coverage.statementStart(10895)
            }
Coverage.statementStart(10896)
        }
Coverage.statementStart(10897)
        for (name in tmp2) {
Coverage.forLoopStart(10898)
            SanityCheck.check { columnsTmp[1].contains(name) || name == "_" }
Coverage.statementStart(10899)
            if (name != "_") {
Coverage.ifStart(10900)
                val it = ColumnIteratorQueue()
Coverage.statementStart(10901)
                columnsOUT.add(it)
Coverage.statementStart(10902)
                columnsOUTAO.add(0, it)
Coverage.statementStart(10903)
                columnsINAO.add(0, childAv.columns[name]!!)
Coverage.statementStart(10904)
                outMap[name] = it
Coverage.statementStart(10905)
            }
Coverage.statementStart(10906)
        }
Coverage.statementStart(10907)
        SanityCheck.check { variablINBO.size > 0 }
Coverage.statementStart(10908)
        val distributedStore = DistributedTripleStore.getNamedGraph(query, childB.graph)
Coverage.statementStart(10909)
        val valuesAO = Array<Value?>(columnsINAO.size) { null }
Coverage.statementStart(10910)
        val valuesAJ = Array<Value?>(columnsINAJ.size) { null }
Coverage.statementStart(10911)
        var count = 0
Coverage.statementStart(10912)
        val params = Array<AOPBase>(3) {
Coverage.statementStart(10913)
            if (childB.children[it] is AOPConstant) {
Coverage.ifStart(10914)
                count++
Coverage.statementStart(10915)
            }
Coverage.statementStart(10916)
/*return*/ childB.children[it] as AOPBase
        }
Coverage.statementStart(10917)
        for (i in 0 until indicesINBJ.size) {
Coverage.forLoopStart(10918)
            SanityCheck.check { params[indicesINBJ[i]] is AOPVariable }
Coverage.statementStart(10919)
            params[indicesINBJ[i]] = AOPConstant(query, ResultSetDictionary.undefValue2)
Coverage.statementStart(10920)
            count++
Coverage.statementStart(10921)
        }
Coverage.statementStart(10922)
        SanityCheck {
Coverage.statementStart(10923)
            SanityCheck.check { count > 0 }
Coverage.statementStart(10924)
            SanityCheck.check { count < 3 }
Coverage.statementStart(10925)
            for (i in 0 until childB.mySortPriority.size) {
Coverage.forLoopStart(10926)
                SanityCheck.check { childB.mySortPriority[i].sortType == ESortType.FAST }
Coverage.statementStart(10927)
            }
Coverage.statementStart(10928)
            SanityCheck.check { indicesINBJ.size > 0 }
Coverage.statementStart(10929)
            SanityCheck.check { valuesAJ.size == indicesINBJ.size }
Coverage.statementStart(10930)
        }
Coverage.statementStart(10931)
        val columnsInB = Array(variablINBO.size) { ColumnIterator() }
Coverage.statementStart(10932)
        for (i in 0 until columnsINAO.size) {
Coverage.forLoopStart(10933)
            valuesAO[i] = columnsINAO[i].next()
Coverage.statementStart(10934)
        }
Coverage.statementStart(10935)
        for (i in 0 until columnsINAJ.size) {
Coverage.forLoopStart(10936)
            valuesAJ[i] = columnsINAJ[i].next()
Coverage.statementStart(10937)
        }
Coverage.statementStart(10938)
        if (valuesAJ[0] != null) {
Coverage.ifStart(10939)
//there is at least one value in A
Coverage.statementStart(10940)
            for (i in 0 until indicesINBJ.size) {
Coverage.forLoopStart(10941)
                params[indicesINBJ[i]] = AOPConstant(query, valuesAJ[i]!!)
Coverage.statementStart(10942)
            }
Coverage.statementStart(10943)
            var columnsInBRoot = distributedStore.getIterator(params, index).evaluate()
Coverage.statementStart(10944)
            for (i in 0 until variablINBO.size) {
Coverage.forLoopStart(10945)
                columnsInB[i] = columnsInBRoot.columns[variablINBO[i]]!!
Coverage.statementStart(10946)
            }
Coverage.statementStart(10947)
            for (column in columnsOUT) {
Coverage.forLoopStart(10948)
                column.onEmptyQueue = {
Coverage.statementStart(10949)
                    loopA@ while (true) {
Coverage.whileLoopStart(10950)
                        var done = true
Coverage.statementStart(10951)
                        loopB@ for (i in 0 until variablINBO.size) {
Coverage.forLoopStart(10952)
                            val value = columnsInB[i].next()
Coverage.statementStart(10953)
                            if (value == null) {
Coverage.ifStart(10954)
                                SanityCheck.check { i == 0 }
Coverage.statementStart(10955)
                                done = false
Coverage.statementStart(10956)
                                break@loopB
                            } else {
Coverage.statementStart(10957)
                                columnsOUTB[i].queue.add(value)
Coverage.statementStart(10958)
                            }
Coverage.statementStart(10959)
                        }
Coverage.statementStart(10960)
                        if (done) {
Coverage.ifStart(10961)
                            for (i in 0 until columnsOUTAO.size) {
Coverage.forLoopStart(10962)
                                columnsOUTAO[i].queue.add(valuesAO[i]!!)
Coverage.statementStart(10963)
                            }
Coverage.statementStart(10964)
                            for (i in 0 until columnsOUTAJ.size) {
Coverage.forLoopStart(10965)
                                columnsOUTAJ[i].queue.add(valuesAJ[i]!!)
Coverage.statementStart(10966)
                            }
Coverage.statementStart(10967)
                            break@loopA
                        } else {
Coverage.statementStart(10968)
                            for (i in 0 until columnsINAO.size) {
Coverage.forLoopStart(10969)
                                valuesAO[i] = columnsINAO[i].next()
Coverage.statementStart(10970)
                            }
Coverage.statementStart(10971)
                            for (i in 0 until columnsINAJ.size) {
Coverage.forLoopStart(10972)
                                valuesAJ[i] = columnsINAJ[i].next()
Coverage.statementStart(10973)
                            }
Coverage.statementStart(10974)
                            if (valuesAJ[0] != null) {
Coverage.ifStart(10975)
                                for (i in 0 until indicesINBJ.size) {
Coverage.forLoopStart(10976)
                                    params[indicesINBJ[i]] = AOPConstant(query, valuesAJ[i]!!)
Coverage.statementStart(10977)
                                }
Coverage.statementStart(10978)
                                columnsInBRoot = distributedStore.getIterator(params, index).evaluate()
Coverage.statementStart(10979)
                                for (i in 0 until variablINBO.size) {
Coverage.forLoopStart(10980)
                                    columnsInB[i] = columnsInBRoot.columns[variablINBO[i]]!!
Coverage.statementStart(10981)
                                }
Coverage.statementStart(10982)
                            } else {
Coverage.ifStart(10983)
                                break@loopA
                            }
Coverage.statementStart(10984)
                        }
Coverage.statementStart(10985)
                    }
Coverage.statementStart(10986)
                }
Coverage.statementStart(10987)
            }
Coverage.statementStart(10988)
        }
Coverage.statementStart(10989)
        return IteratorBundle(outMap)
    }
    override fun toXMLElement(): XMLElement {
Coverage.funStart(10990)
        val res = super.toXMLElement().addAttribute("optional", "" + optional)
Coverage.statementStart(10991)
        res["children"]!!.addContent(childB.toXMLElement())
Coverage.statementStart(10992)
        return res
    }
    override fun cloneOP() = POPJoinWithStore(query, projectedVariables, children[0].cloneOP(), childB.cloneOP(), optional)
}
