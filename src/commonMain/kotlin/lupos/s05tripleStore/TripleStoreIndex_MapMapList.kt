package lupos.s05tripleStore
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.File
import lupos.s00misc.MyMapIntGeneric
import lupos.s00misc.MyMapLongGeneric
import lupos.s00misc.MySetInt
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.Query
class TripleStoreIndex_MapMapList : TripleStoreIndex() {
    @JvmField
    val data = MyMapIntGeneric<MyMapIntGeneric<MySetInt>>()
    override fun printContents() {
Coverage.funStart(7190)
    }
    override fun safeToFile(filename: String) {
Coverage.funStart(7191)
        File(filename).dataOutputStream { out ->
Coverage.statementStart(7192)
            out.writeInt(data.size)
Coverage.statementStart(7193)
            val iterator0 = data.iterator()
Coverage.statementStart(7194)
            while (iterator0.hasNext()) {
Coverage.whileLoopStart(7195)
                out.writeInt(iterator0.next())
Coverage.statementStart(7196)
                val value0 = iterator0.value()
Coverage.statementStart(7197)
                out.writeInt(value0.size)
Coverage.statementStart(7198)
                val iterator1 = value0.iterator()
Coverage.statementStart(7199)
                while (iterator1.hasNext()) {
Coverage.whileLoopStart(7200)
                    out.writeInt(iterator1.next())
Coverage.statementStart(7201)
                    val value1 = iterator1.value()
Coverage.statementStart(7202)
                    out.writeInt(value1.size)
Coverage.statementStart(7203)
                    value1.forEach {
Coverage.forEachLoopStart(7204)
                        out.writeInt(it)
Coverage.statementStart(7205)
                    }
Coverage.statementStart(7206)
                }
Coverage.statementStart(7207)
            }
Coverage.statementStart(7208)
        }
Coverage.statementStart(7209)
    }
    override fun loadFromFile(filename: String) {
Coverage.funStart(7210)
        File(filename).dataInputStream { it ->
Coverage.statementStart(7211)
            val size0 = it.readInt()
Coverage.statementStart(7212)
            data.withFastInitializer { initData ->
Coverage.statementStart(7213)
                for (i0 in 0 until size0) {
Coverage.forLoopStart(7214)
                    val key0 = it.readInt()
Coverage.statementStart(7215)
                    initData.appendAssumeSorted(key0, MyMapIntGeneric<MySetInt>()).withFastInitializer { tmp0 ->
Coverage.statementStart(7216)
                        val size1 = it.readInt()
Coverage.statementStart(7217)
                        for (i1 in 0 until size1) {
Coverage.forLoopStart(7218)
                            val key1 = it.readInt()
Coverage.statementStart(7219)
                            val tmp1 = tmp0.appendAssumeSorted(key1, MySetInt())
Coverage.statementStart(7220)
                            val size2 = it.readInt()
Coverage.statementStart(7221)
                            for (i2 in 0 until size2) {
Coverage.forLoopStart(7222)
                                val key2 = it.readInt()
Coverage.statementStart(7223)
                                tmp1.appendAssumeSorted(key2)
Coverage.statementStart(7224)
                            }
Coverage.statementStart(7225)
                        }
Coverage.statementStart(7226)
                    }
Coverage.statementStart(7227)
                }
Coverage.statementStart(7228)
            }
Coverage.statementStart(7229)
        }
Coverage.statementStart(7230)
    }
    override fun getIterator(query: Query, filter: IntArray, projection: List<String>): IteratorBundle {
Coverage.funStart(7231)
        SanityCheck.check { filter.size >= 0 && filter.size <= 3 }
Coverage.statementStart(7232)
        SanityCheck.check { projection.size + filter.size == 3 }
Coverage.statementStart(7233)
        val columns = mutableMapOf<String, ColumnIterator>()
Coverage.statementStart(7234)
        for (sIndex in 0 until projection.size) {
Coverage.forLoopStart(7235)
            val s = projection[sIndex]
Coverage.statementStart(7236)
            if (s != "_") {
Coverage.ifStart(7237)
                columns[s] = ColumnIterator()
Coverage.statementStart(7238)
            }
Coverage.statementStart(7239)
        }
Coverage.statementStart(7240)
        var res = IteratorBundle(columns)
Coverage.statementStart(7241)
        if (filter.size > 0) {
Coverage.ifStart(7242)
            val tmp = data[filter[0]]
Coverage.statementStart(7243)
            if (tmp != null) {
Coverage.ifStart(7244)
                if (filter.size > 1) {
Coverage.ifStart(7245)
                    val tmp1 = tmp[filter[1]]
Coverage.statementStart(7246)
                    if (tmp1 != null) {
Coverage.ifStart(7247)
                        if (filter.size > 2) {
Coverage.ifStart(7248)
                            if (tmp1.contains(filter[2])) {
Coverage.ifStart(7249)
                                res = IteratorBundle(1)
Coverage.statementStart(7250)
                            } else {
Coverage.ifStart(7251)
                                res = IteratorBundle(0)
Coverage.statementStart(7252)
                            }
Coverage.statementStart(7253)
                        } else {
Coverage.ifStart(7254)
                            if (projection[0] == "_") {
Coverage.ifStart(7255)
                                res = IteratorBundle(tmp1.size)
Coverage.statementStart(7256)
                            } else {
Coverage.ifStart(7257)
                                columns[projection[0]] = ColumnIteratorDebug(-1, projection[0], ColumnIteratorMultiValue(tmp1.iterator()))
Coverage.statementStart(7258)
                            }
Coverage.statementStart(7259)
                        }
Coverage.statementStart(7260)
                    }
Coverage.statementStart(7261)
                } else {
Coverage.ifStart(7262)
                    val columnsArr = arrayOf(ColumnIteratorChildIterator(), ColumnIteratorChildIterator())
Coverage.statementStart(7263)
                    if (projection[0] != "_") {
Coverage.ifStart(7264)
                        columns[projection[0]] = ColumnIteratorDebug(-2, projection[0], columnsArr[0])
Coverage.statementStart(7265)
                    }
Coverage.statementStart(7266)
                    if (projection[1] != "_") {
Coverage.ifStart(7267)
                        columns[projection[1]] = ColumnIteratorDebug(-3, projection[1], columnsArr[1])
Coverage.statementStart(7268)
                    }
Coverage.statementStart(7269)
                    var iter = tmp.iterator()
Coverage.statementStart(7270)
                    for (iterator in columnsArr) {
Coverage.forLoopStart(7271)
                        iterator.onNoMoreElements = {
Coverage.statementStart(7272)
                            if (iter.hasNext()) {
Coverage.ifStart(7273)
                                val key = iter.next()
Coverage.statementStart(7274)
                                val value = iter.value()
Coverage.statementStart(7275)
                                if (projection[0] != "_") {
Coverage.ifStart(7276)
                                    columnsArr[0].childs.add(ColumnIteratorRepeatValue(value.size, key))
Coverage.statementStart(7277)
                                }
Coverage.statementStart(7278)
                                if (projection[1] != "_") {
Coverage.ifStart(7279)
                                    columnsArr[1].childs.add(ColumnIteratorMultiValue(value.iterator()))
Coverage.statementStart(7280)
                                }
Coverage.statementStart(7281)
                            }
Coverage.statementStart(7282)
                        }
Coverage.statementStart(7283)
                    }
Coverage.statementStart(7284)
                }
Coverage.statementStart(7285)
            }
Coverage.statementStart(7286)
        } else {
Coverage.ifStart(7287)
            val columnsArr = arrayOf(ColumnIteratorChildIterator(), ColumnIteratorChildIterator(), ColumnIteratorChildIterator())
Coverage.statementStart(7288)
            if (projection[0] != "_") {
Coverage.ifStart(7289)
                columns[projection[0]] = ColumnIteratorDebug(-4, projection[0], columnsArr[0])
Coverage.statementStart(7290)
            }
Coverage.statementStart(7291)
            if (projection[1] != "_") {
Coverage.ifStart(7292)
                columns[projection[1]] = ColumnIteratorDebug(-5, projection[1], columnsArr[1])
Coverage.statementStart(7293)
            }
Coverage.statementStart(7294)
            if (projection[2] != "_") {
Coverage.ifStart(7295)
                columns[projection[2]] = ColumnIteratorDebug(-6, projection[2], columnsArr[2])
Coverage.statementStart(7296)
            }
Coverage.statementStart(7297)
            var iter = data.iterator()
Coverage.statementStart(7298)
            if (iter.hasNext()) {
Coverage.ifStart(7299)
                var key1 = iter.next()
Coverage.statementStart(7300)
                var value1 = iter.value()
Coverage.statementStart(7301)
                var iter2 = value1.iterator()
Coverage.statementStart(7302)
                for (iterator in columnsArr) {
Coverage.forLoopStart(7303)
                    iterator.onNoMoreElements = {
Coverage.statementStart(7304)
                        while (true) {
Coverage.whileLoopStart(7305)
                            if (iter2.hasNext()) {
Coverage.ifStart(7306)
                                val key2 = iter2.next()
Coverage.statementStart(7307)
                                val value2 = iter2.value()
Coverage.statementStart(7308)
                                if (projection[0] != "_") {
Coverage.ifStart(7309)
                                    columnsArr[0].childs.add(ColumnIteratorRepeatValue(value2.size, key1))
Coverage.statementStart(7310)
                                }
Coverage.statementStart(7311)
                                if (projection[1] != "_") {
Coverage.ifStart(7312)
                                    columnsArr[1].childs.add(ColumnIteratorRepeatValue(value2.size, key2))
Coverage.statementStart(7313)
                                }
Coverage.statementStart(7314)
                                if (projection[2] != "_") {
Coverage.ifStart(7315)
                                    columnsArr[2].childs.add(ColumnIteratorMultiValue(value2.iterator()))
Coverage.statementStart(7316)
                                }
Coverage.statementStart(7317)
                                break
                            } else {
Coverage.statementStart(7318)
                                if (iter.hasNext()) {
Coverage.ifStart(7319)
                                    key1 = iter.next()
Coverage.statementStart(7320)
                                    value1 = iter.value()
Coverage.statementStart(7321)
                                    iter2 = value1.iterator()
Coverage.statementStart(7322)
                                } else {
Coverage.ifStart(7323)
                                    break
                                }
Coverage.statementStart(7324)
                            }
Coverage.statementStart(7325)
                        }
Coverage.statementStart(7326)
                    }
Coverage.statementStart(7327)
                }
Coverage.statementStart(7328)
            }
Coverage.statementStart(7329)
        }
Coverage.statementStart(7330)
        return res
    }
    override fun import(dataImport: IntArray, count: Int, order: IntArray) {
Coverage.funStart(7331)
        val inverseOrder = IntArray(3)
Coverage.statementStart(7332)
        for (i in 0 until 3) {
Coverage.forLoopStart(7333)
            inverseOrder[order[i]] = i
Coverage.statementStart(7334)
        }
Coverage.statementStart(7335)
        if (count > 0) {
Coverage.ifStart(7336)
            var lastS = dataImport[count + inverseOrder[0]]
Coverage.statementStart(7337)
            var tmpS = data.getOrCreate(lastS, { MyMapIntGeneric<MySetInt>() })
Coverage.statementStart(7338)
            var lastP = dataImport[count + inverseOrder[1]]
Coverage.statementStart(7339)
            var tmpP = tmpS.getOrCreate(lastP, { MySetInt() })
Coverage.statementStart(7340)
            tmpP.add(dataImport[count + inverseOrder[2]])
Coverage.statementStart(7341)
            for (i in 1 until count) {
Coverage.forLoopStart(7342)
                var s = dataImport[count + inverseOrder[0]]
Coverage.statementStart(7343)
                var p = dataImport[count + inverseOrder[1]]
Coverage.statementStart(7344)
                if (s != lastS) {
Coverage.ifStart(7345)
                    lastS = s
Coverage.statementStart(7346)
                    lastP = p
Coverage.statementStart(7347)
                    tmpS = data.getOrCreate(lastS, { MyMapIntGeneric<MySetInt>() })
Coverage.statementStart(7348)
                    tmpP = tmpS.getOrCreate(lastP, { MySetInt() })
Coverage.statementStart(7349)
                } else if (p != lastP) {
Coverage.ifStart(7350)
                    lastP = p
Coverage.statementStart(7351)
                    tmpP = tmpS.getOrCreate(lastP, { MySetInt() })
Coverage.statementStart(7352)
                }
Coverage.statementStart(7353)
                tmpP.add(dataImport[count + inverseOrder[2]])
Coverage.statementStart(7354)
            }
Coverage.statementStart(7355)
        }
Coverage.statementStart(7356)
    }
    fun import(dataImport: MyMapLongGeneric<MySetInt>) {
Coverage.funStart(7357)
        val iterator = dataImport.iterator()
Coverage.statementStart(7358)
        while (iterator.hasNext()) {
Coverage.whileLoopStart(7359)
            val key = iterator.next()
Coverage.statementStart(7360)
            val value = iterator.value()
Coverage.statementStart(7361)
            val s = (key shr 32).toInt()
Coverage.statementStart(7362)
            val p = key.toInt()
Coverage.statementStart(7363)
            val tmpS = data.getOrCreate(s, { MyMapIntGeneric<MySetInt>() })
Coverage.statementStart(7364)
            val tmpP = tmpS.getOrCreate(p, { MySetInt() })
Coverage.statementStart(7365)
            val oiterator = value.iterator()
Coverage.statementStart(7366)
            while (oiterator.hasNext()) {
Coverage.whileLoopStart(7367)
                tmpP.add(oiterator.next())
Coverage.statementStart(7368)
            }
Coverage.statementStart(7369)
        }
Coverage.statementStart(7370)
    }
    override fun insert(a: Value, b: Value, c: Value) {
Coverage.funStart(7371)
        val tmp = data[a]
Coverage.statementStart(7372)
        if (tmp == null) {
Coverage.ifStart(7373)
            data[a] = MyMapIntGeneric(b to MySetInt(c))
Coverage.statementStart(7374)
        } else {
Coverage.ifStart(7375)
            val tmp2 = tmp[b]
Coverage.statementStart(7376)
            if (tmp2 == null) {
Coverage.ifStart(7377)
                tmp[b] = MySetInt(c)
Coverage.statementStart(7378)
            } else {
Coverage.ifStart(7379)
                tmp2.add(c)
Coverage.statementStart(7380)
            }
Coverage.statementStart(7381)
        }
Coverage.statementStart(7382)
    }
    override fun remove(a: Value, b: Value, c: Value) {
Coverage.funStart(7383)
        val tmp = data[a]
Coverage.statementStart(7384)
        if (tmp != null) {
Coverage.ifStart(7385)
            val tmp2 = tmp[b]
Coverage.statementStart(7386)
            if (tmp2 != null) {
Coverage.ifStart(7387)
                tmp2.remove(c)
Coverage.statementStart(7388)
            }
Coverage.statementStart(7389)
        }
Coverage.statementStart(7390)
    }
    override fun clear() {
Coverage.funStart(7391)
        data.clear()
Coverage.statementStart(7392)
    }
}
