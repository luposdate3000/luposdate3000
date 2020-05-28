package lupos.s09physicalOperators.multiinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
class POPJoinHashMap(query: Query, projectedVariables: List<String>, childA: OPBase, childB: OPBase, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinHashMapID, "POPJoinHashMap", arrayOf(childA, childB), ESortPriority.JOIN) {
    override fun toSparql(): String {
Coverage.funStart(10112)
        if (optional) {
Coverage.ifStart(10113)
            return "OPTIONAL{" + children[0].toSparql() + children[1].toSparql() + "}"
        }
Coverage.statementStart(10114)
        return children[0].toSparql() + children[1].toSparql()
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(10115)
        if (other !is POPJoinHashMap) {
Coverage.ifStart(10116)
            return false
        }
Coverage.statementStart(10117)
        if (optional != other.optional) {
Coverage.ifStart(10118)
            return false
        }
Coverage.statementStart(10119)
        for (i in children.indices) {
Coverage.forLoopStart(10120)
            if (!children[i].equals(other.children[i])) {
Coverage.ifStart(10121)
                return false
            }
Coverage.statementStart(10122)
        }
Coverage.statementStart(10123)
        return true
    }
    class MapKey(@JvmField val data: Array<Value>) {
        override fun hashCode(): Int {
Coverage.funStart(10124)
            var res = 0
Coverage.statementStart(10125)
            for (i in 0 until data.size) {
Coverage.forLoopStart(10126)
                res += data[i].hashCode()
Coverage.statementStart(10127)
            }
Coverage.statementStart(10128)
            return res
        }
        override fun equals(other: Any?): Boolean {
Coverage.funStart(10129)
            SanityCheck.check { other is MapKey }
Coverage.statementStart(10130)
            for (i in 0 until data.size) {
Coverage.forLoopStart(10131)
                if (data[i] != (other as MapKey).data[i]) {
Coverage.ifStart(10132)
                    return false
                }
Coverage.statementStart(10133)
            }
Coverage.statementStart(10134)
            return true
        }
        fun equalsFuzzy(other: Any?): Boolean {
Coverage.funStart(10135)
            SanityCheck.check { other is MapKey }
Coverage.statementStart(10136)
            for (i in 0 until data.size) {
Coverage.forLoopStart(10137)
                if (data[i] != ResultSetDictionary.undefValue && (other as MapKey).data[i] != ResultSetDictionary.undefValue && data[i] != other.data[i]) {
Coverage.ifStart(10138)
                    return false
                }
Coverage.statementStart(10139)
            }
Coverage.statementStart(10140)
            return true
        }
    }
    class MapRow(columns: Int) {
        val columns = Array(columns) { MyListValue() }
        var count = 0
    }
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(10141)
//--- obtain child columns
Coverage.statementStart(10142)
        val columns = LOPJoin.getColumns(children[0].getProvidedVariableNames(), children[1].getProvidedVariableNames())
Coverage.statementStart(10143)
        require(columns[0].size != 0)
Coverage.statementStart(10144)
        val childA = children[0].evaluate()
Coverage.statementStart(10145)
        val childB = children[1].evaluate()
Coverage.statementStart(10146)
        val columnsINAO = mutableListOf<ColumnIterator>()//only in childA
Coverage.statementStart(10147)
        val columnsINBO = mutableListOf<ColumnIterator>()//only in childB
Coverage.statementStart(10148)
        val columnsINAJ = mutableListOf<ColumnIterator>()//join columnA
Coverage.statementStart(10149)
        val columnsINBJ = mutableListOf<ColumnIterator>()//join columnB
Coverage.statementStart(10150)
        val outO = Array(2) { mutableListOf<ColumnIteratorChildIterator>() }//only in one of the childs
Coverage.statementStart(10151)
        val outJ = mutableListOf<ColumnIteratorChildIterator>()
Coverage.statementStart(10152)
        val outIterators = mutableListOf<ColumnIteratorChildIterator>()
Coverage.statementStart(10153)
        val outMap = mutableMapOf<String, ColumnIterator>()
Coverage.statementStart(10154)
        var res: IteratorBundle?
Coverage.statementStart(10155)
        val tmp = mutableListOf<String>()
Coverage.statementStart(10156)
        var t: ColumnIteratorChildIterator
Coverage.statementStart(10157)
        tmp.addAll(children[1].getProvidedVariableNames())
Coverage.statementStart(10158)
        for (name in children[0].getProvidedVariableNames()) {
Coverage.forLoopStart(10159)
            if (tmp.contains(name)) {
Coverage.ifStart(10160)
                columnsINAJ.add(0, childA.columns[name]!!)
Coverage.statementStart(10161)
                columnsINBJ.add(0, childB.columns[name]!!)
Coverage.statementStart(10162)
                t = ColumnIteratorChildIterator()
Coverage.statementStart(10163)
                if (projectedVariables.contains(name)) {
Coverage.ifStart(10164)
                    outMap[name] = ColumnIteratorDebug(uuid, name, t)
Coverage.statementStart(10165)
                    outIterators.add(0, t)
Coverage.statementStart(10166)
                    outJ.add(0, t)
Coverage.statementStart(10167)
                }
Coverage.statementStart(10168)
                tmp.remove(name)
Coverage.statementStart(10169)
            } else {
Coverage.ifStart(10170)
                t = ColumnIteratorChildIterator()
Coverage.statementStart(10171)
                outIterators.add(t)
Coverage.statementStart(10172)
                outMap[name] = ColumnIteratorDebug(uuid, name, t)
Coverage.statementStart(10173)
                outO[0].add(t)
Coverage.statementStart(10174)
                columnsINAO.add(childA.columns[name]!!)
Coverage.statementStart(10175)
            }
Coverage.statementStart(10176)
        }
Coverage.statementStart(10177)
        for (name in tmp) {
Coverage.forLoopStart(10178)
            t = ColumnIteratorChildIterator()
Coverage.statementStart(10179)
            outIterators.add(t)
Coverage.statementStart(10180)
            outMap[name] = ColumnIteratorDebug(uuid, name, t)
Coverage.statementStart(10181)
            outO[1].add(t)
Coverage.statementStart(10182)
            columnsINBO.add(childB.columns[name]!!)
Coverage.statementStart(10183)
        }
Coverage.statementStart(10184)
        var emptyColumnsWithJoin = outO[0].size == 0 && outO[1].size == 0 && outJ.size == 0 && columnsINAJ.size != 0
Coverage.statementStart(10185)
        if (emptyColumnsWithJoin) {
Coverage.ifStart(10186)
            t = ColumnIteratorChildIterator()
Coverage.statementStart(10187)
            outJ.add(t)
Coverage.statementStart(10188)
            outIterators.add(t)
Coverage.statementStart(10189)
        }
Coverage.statementStart(10190)
        val mapWithoutUndef = mutableMapOf<MapKey, MapRow>()
Coverage.statementStart(10191)
        val mapWithUndef = mutableMapOf<MapKey, MapRow>()
Coverage.statementStart(10192)
        var currentKey: Array<Value>? = null
Coverage.statementStart(10193)
        var nextKey: Array<Value>?
Coverage.statementStart(10194)
        var map: MutableMap<MapKey, MapRow> = mapWithUndef
Coverage.statementStart(10195)
        var nextMap: MutableMap<MapKey, MapRow>
Coverage.statementStart(10196)
        var key: MapKey
Coverage.statementStart(10197)
        var oldArr: MapRow?
Coverage.statementStart(10198)
        var count: Int
Coverage.statementStart(10199)
        var countA: Int
Coverage.statementStart(10200)
        var countB: Int
Coverage.statementStart(10201)
        require(columnsINAJ.size > 0)
Coverage.statementStart(10202)
//--- insert second child into hash table
Coverage.statementStart(10203)
        while (true) {
Coverage.whileLoopStart(10204)
            if (currentKey != null) {
Coverage.ifStart(10205)
                count = 1
Coverage.statementStart(10206)
            } else {
Coverage.ifStart(10207)
                count = 0
Coverage.statementStart(10208)
            }
Coverage.statementStart(10209)
            loopB@ while (true) {
Coverage.whileLoopStart(10210)
                nextKey = Array(columnsINBJ.size) { ResultSetDictionary.undefValue }
Coverage.statementStart(10211)
                nextMap = mapWithoutUndef
Coverage.statementStart(10212)
                for (columnIndex in 0 until columnsINBJ.size) {
Coverage.forLoopStart(10213)
                    val value = columnsINBJ[columnIndex].next()
Coverage.statementStart(10214)
                    if (value == null) {
Coverage.ifStart(10215)
                        nextKey = null
Coverage.statementStart(10216)
                        break@loopB
                    }
Coverage.statementStart(10217)
                    nextKey!![columnIndex] = value
Coverage.statementStart(10218)
                    if (value == ResultSetDictionary.undefValue) {
Coverage.ifStart(10219)
                        nextMap = mapWithUndef
Coverage.statementStart(10220)
                    }
Coverage.statementStart(10221)
                }
Coverage.statementStart(10222)
                if (currentKey == null) {
Coverage.ifStart(10223)
                    currentKey = nextKey
Coverage.statementStart(10224)
                    map = nextMap
Coverage.statementStart(10225)
                } else if (nextKey == null || MapKey(nextKey) != MapKey(currentKey)) {
Coverage.ifStart(10226)
                    break
                }
Coverage.statementStart(10227)
                count++
Coverage.statementStart(10228)
            }
Coverage.statementStart(10229)
            if (currentKey == null) {
Coverage.ifStart(10230)
                break
            }
Coverage.statementStart(10231)
            key = MapKey(currentKey)
Coverage.statementStart(10232)
            oldArr = map[key]
Coverage.statementStart(10233)
            if (oldArr == null) {
Coverage.ifStart(10234)
                oldArr = MapRow(columnsINBO.size)
Coverage.statementStart(10235)
                map[key] = oldArr
Coverage.statementStart(10236)
            }
Coverage.statementStart(10237)
            oldArr.count += count
Coverage.statementStart(10238)
            for (columnIndex in 0 until columnsINBO.size) {
Coverage.forLoopStart(10239)
//TODO dont use kotlin lists here, use pages instead
Coverage.statementStart(10240)
                for (j in 0 until count) {
Coverage.forLoopStart(10241)
                    oldArr.columns[columnIndex].add(columnsINBO[columnIndex].next()!!)
Coverage.statementStart(10242)
                }
Coverage.statementStart(10243)
            }
Coverage.statementStart(10244)
            currentKey = nextKey
Coverage.statementStart(10245)
            map = nextMap
Coverage.statementStart(10246)
        }
Coverage.statementStart(10247)
//--- iterate first child
Coverage.statementStart(10248)
//--- calculate next "cartesian product"
Coverage.statementStart(10249)
        for (iterator in outIterators) {
Coverage.forLoopStart(10250)
//this is just function pointer assignment. this loop does not calculate anything
Coverage.statementStart(10251)
            iterator.close = {
Coverage.statementStart(10252)
                iterator._close()
Coverage.statementStart(10253)
                for (variable in children[0].getProvidedVariableNames()) {
Coverage.forLoopStart(10254)
                    childA.columns[variable]!!.close()
Coverage.statementStart(10255)
                }
Coverage.statementStart(10256)
                for (variable in children[1].getProvidedVariableNames()) {
Coverage.forLoopStart(10257)
                    childB.columns[variable]!!.close()
Coverage.statementStart(10258)
                }
Coverage.statementStart(10259)
            }
Coverage.statementStart(10260)
            iterator.onNoMoreElements = {
Coverage.statementStart(10261)
                var done = false
Coverage.statementStart(10262)
                while (!done) {
Coverage.whileLoopStart(10263)
                    if (currentKey == null) {
Coverage.ifStart(10264)
                        countA = 0
Coverage.statementStart(10265)
                    } else {
Coverage.ifStart(10266)
                        countA = 1
Coverage.statementStart(10267)
                    }
Coverage.statementStart(10268)
                    loopA@ while (true) {
Coverage.whileLoopStart(10269)
                        nextKey = Array(columnsINAJ.size) { ResultSetDictionary.undefValue }
Coverage.statementStart(10270)
                        nextMap = mapWithoutUndef
Coverage.statementStart(10271)
                        for (columnIndex in 0 until columnsINAJ.size) {
Coverage.forLoopStart(10272)
                            val value = columnsINAJ[columnIndex].next()
Coverage.statementStart(10273)
                            if (value == null) {
Coverage.ifStart(10274)
                                SanityCheck.check { columnIndex == 0 }
Coverage.statementStart(10275)
                                nextKey = null
Coverage.statementStart(10276)
                                break@loopA
                            }
Coverage.statementStart(10277)
                            nextKey!![columnIndex] = value
Coverage.statementStart(10278)
                            if (value == ResultSetDictionary.undefValue) {
Coverage.ifStart(10279)
                                nextMap = mapWithUndef
Coverage.statementStart(10280)
                            }
Coverage.statementStart(10281)
                        }
Coverage.statementStart(10282)
                        if (currentKey == null) {
Coverage.ifStart(10283)
                            map = nextMap
Coverage.statementStart(10284)
                            currentKey = nextKey
Coverage.statementStart(10285)
                        } else if (nextKey == null || MapKey(nextKey!!) != MapKey(currentKey!!)) {
Coverage.ifStart(10286)
                            break
                        }
Coverage.statementStart(10287)
                        countA++
Coverage.statementStart(10288)
                    }
Coverage.statementStart(10289)
                    if (currentKey == null) {
Coverage.ifStart(10290)
                        done = true
Coverage.statementStart(10291)
                        iterator.close()
Coverage.statementStart(10292)
                    } else {
Coverage.ifStart(10293)
                        key = MapKey(currentKey!!)
Coverage.statementStart(10294)
                        var others = mutableListOf<Pair<MapKey, MapRow>>()
Coverage.statementStart(10295)
//search for_join-partners
Coverage.statementStart(10296)
                        if (map == mapWithoutUndef) {
Coverage.ifStart(10297)
                            val tmp2 = mapWithoutUndef[key]
Coverage.statementStart(10298)
                            if (tmp2 != null) {
Coverage.ifStart(10299)
                                others.add(Pair(key, tmp2))
Coverage.statementStart(10300)
                            }
Coverage.statementStart(10301)
                            for ((k, v) in mapWithUndef) {
Coverage.forLoopStart(10302)
                                if (k.equalsFuzzy(key)) {
Coverage.ifStart(10303)
                                    others.add(Pair(k, v))
Coverage.statementStart(10304)
                                }
Coverage.statementStart(10305)
                            }
Coverage.statementStart(10306)
                        } else {
Coverage.ifStart(10307)
                            for ((k, v) in mapWithoutUndef) {
Coverage.forLoopStart(10308)
                                if (k.equalsFuzzy(key)) {
Coverage.ifStart(10309)
                                    others.add(Pair(k, v))
Coverage.statementStart(10310)
                                }
Coverage.statementStart(10311)
                            }
Coverage.statementStart(10312)
                            for ((k, v) in mapWithUndef) {
Coverage.forLoopStart(10313)
                                if (k.equalsFuzzy(key)) {
Coverage.ifStart(10314)
                                    others.add(Pair(k, v))
Coverage.statementStart(10315)
                                }
Coverage.statementStart(10316)
                            }
Coverage.statementStart(10317)
                        }
Coverage.statementStart(10318)
                        val dataOA = Array(columnsINAO.size) { MyListValue() }
Coverage.statementStart(10319)
                        for (columnIndex in 0 until columnsINAO.size) {
Coverage.forLoopStart(10320)
                            for (i in 0 until countA) {
Coverage.forLoopStart(10321)
                                dataOA[columnIndex].add(columnsINAO[columnIndex].next()!!)
Coverage.statementStart(10322)
                            }
Coverage.statementStart(10323)
                        }
Coverage.statementStart(10324)
                        if (others.size == 0) {
Coverage.ifStart(10325)
                            if (optional) {
Coverage.ifStart(10326)
//optional clause without match
Coverage.statementStart(10327)
                                done = true
Coverage.statementStart(10328)
                                countB = 1
Coverage.statementStart(10329)
                                val dataJ: Array<Value?> = Array(outJ.size) { currentKey!![it] }
Coverage.statementStart(10330)
                                val dataO: Array<Array<MyListValue>> = arrayOf(dataOA, Array(outO[1].size) { MyListValue(ResultSetDictionary.undefValue) })
Coverage.statementStart(10331)
                                POPJoin.crossProduct(dataO, dataJ, outO, outJ, countA, countB)
Coverage.statementStart(10332)
                            }
Coverage.statementStart(10333)
                        } else {
Coverage.ifStart(10334)
                            done = true
Coverage.statementStart(10335)
                            for (otherIndex in 0 until others.size) {
Coverage.forLoopStart(10336)
                                countB = others[otherIndex].second.count
Coverage.statementStart(10337)
                                val dataJ: Array<Value?> = Array(outJ.size) {
Coverage.statementStart(10338)
                                    var res2: Value?
Coverage.statementStart(10339)
                                    if (currentKey!![it] != ResultSetDictionary.undefValue) {
Coverage.ifStart(10340)
                                        res2 = currentKey!![it]
Coverage.statementStart(10341)
                                    } else {
Coverage.ifStart(10342)
                                        res2 = others[otherIndex].first.data[it]
Coverage.statementStart(10343)
                                    }
Coverage.statementStart(10344)
/*return*/res2
                                }
Coverage.statementStart(10345)
                                val dataO: Array<Array<MyListValue>> = arrayOf(dataOA, others[otherIndex].second.columns)
Coverage.statementStart(10346)
                                POPJoin.crossProduct(dataO, dataJ, outO, outJ, countA, countB)
Coverage.statementStart(10347)
                            }
Coverage.statementStart(10348)
                        }
Coverage.statementStart(10349)
                        map = nextMap
Coverage.statementStart(10350)
                        currentKey = nextKey
Coverage.statementStart(10351)
                    }
Coverage.statementStart(10352)
                }
Coverage.statementStart(10353)
            }
Coverage.statementStart(10354)
        }
Coverage.statementStart(10355)
        if (outMap.size > 0) {
Coverage.ifStart(10356)
            res = IteratorBundle(outMap)
Coverage.statementStart(10357)
        } else {
Coverage.ifStart(10358)
            res = IteratorBundle(0)
Coverage.statementStart(10359)
        }
Coverage.statementStart(10360)
        if (emptyColumnsWithJoin) {
Coverage.ifStart(10361)
            res.hasNext = {
Coverage.statementStart(10362)
                /*return*/outJ[0].next() != null
            }
Coverage.statementStart(10363)
        }
Coverage.statementStart(10364)
        return res
    }
    override fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP() = POPJoinHashMap(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
}
