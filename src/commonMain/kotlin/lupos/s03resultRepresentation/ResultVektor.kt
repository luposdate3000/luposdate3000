package lupos.s03resultRepresentation

import lupos.s00misc.*
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw

class ResultVektor(undefValue: Value) : Iterator<Value> {
    companion object {
        var capacity = 6
    }

    var _posAbsolute = 0
    val uuid = ThreadSafeUuid().next()
    var posAbsolute: Int
        get():Int {
            return _posAbsolute
        }
        set(value) {
            println("set $uuid $value")
            _posAbsolute = value
        }
    var posIndex = 0
    var posIndexLocal = 0
    var posBackup = 0
    var sizeAbsolute = 0
    var sizeIndex = 0
    val data = Array<CompressedElement>(capacity) { CompressedElement(0, undefValue) }
    override fun toString(): String {
        Coverage.funStart(793)
//                                                                                 
        Coverage.statementStart(794)
        val res = StringBuilder()
        Coverage.statementStart(795)
//                                                                                 
        Coverage.statementStart(796)
        res.append("$capacity $posAbsolute $posIndex $posIndexLocal $posBackup $sizeAbsolute $sizeIndex\n")
        Coverage.statementStart(797)
//                                                                                 
        Coverage.statementStart(798)
        for (i in 0 until capacity) {
            Coverage.forLoopStart(799)
//                                                                                 
            Coverage.statementStart(800)
            res.append("${data[i].value}(${data[i].count})")
            Coverage.statementStart(801)
//                                                                                 
            Coverage.statementStart(802)
            if (i == posIndex) {
                Coverage.ifStart(803)
//                                                                                 
                Coverage.statementStart(804)
                res.append("-")
                Coverage.statementStart(805)
//                                                                                 
                Coverage.statementStart(806)
            }
            Coverage.statementStart(807)
//                                                                                 
            Coverage.statementStart(808)
            if (i == sizeIndex) {
                Coverage.ifStart(809)
//                                                                                 
                Coverage.statementStart(810)
                res.append("+")
                Coverage.statementStart(811)
//                                                                                 
                Coverage.statementStart(812)
            }
            Coverage.statementStart(813)
//                                                                                 
            Coverage.statementStart(814)
            res.append(",")
            Coverage.statementStart(815)
//                                                                                 
            Coverage.statementStart(816)
        }
        Coverage.statementStart(817)
//                                                                                 
        Coverage.statementStart(818)
        return res.toString()
    }

    fun skipPos(count: Int) {
        Coverage.funStart(819)
        println("$posAbsolute $count $sizeAbsolute")
        Coverage.statementStart(820)
        require(posAbsolute + count <= sizeAbsolute)
        Coverage.statementStart(821)
        posAbsolute += count
        Coverage.statementStart(822)
        if (count > 0) {
            Coverage.ifStart(823)
            var i = count
            Coverage.statementStart(824)
            while (true) {
                Coverage.whileLoopStart(825)
                val c = data[posIndex].count - posIndexLocal
                Coverage.statementStart(826)
                if (c < i) {
                    Coverage.ifStart(827)
                    internalNextElement()
                    Coverage.statementStart(828)
                    i -= c
                    Coverage.statementStart(829)
                } else {
                    Coverage.ifStart(830)
                    posIndexLocal += i
                    Coverage.statementStart(831)
                    break
                }
                Coverage.statementStart(832)
            }
            Coverage.statementStart(833)
        } else {
            Coverage.ifStart(834)
            println(this)
            Coverage.statementStart(835)
            var i = -count
            Coverage.statementStart(836)
            while (true) {
                Coverage.whileLoopStart(837)
                val c = posIndexLocal
                Coverage.statementStart(838)
                println("c $c $i")
                Coverage.statementStart(839)
                if (c < i) {
                    Coverage.ifStart(840)
                    posIndex--
                    Coverage.statementStart(841)
                    posIndexLocal = data[posIndex].count
                    Coverage.statementStart(842)
                    i -= c
                    Coverage.statementStart(843)
                } else {
                    Coverage.ifStart(844)
                    posIndexLocal -= i
                    Coverage.statementStart(845)
                    break
                }
                Coverage.statementStart(846)
            }
            Coverage.statementStart(847)
        }
        Coverage.statementStart(848)
    }

    fun skipSize(count: Int) {
        Coverage.funStart(849)
        require(posAbsolute <= sizeAbsolute + count)
        Coverage.statementStart(850)
        sizeAbsolute += count
        Coverage.statementStart(851)
        if (count >= 0) {
            Coverage.ifStart(852)
            data[sizeIndex].count += count
            Coverage.statementStart(853)
        } else {
            Coverage.ifStart(854)
            var i = -count
            Coverage.statementStart(855)
            while (true) {
                Coverage.whileLoopStart(856)
                val c = data[sizeIndex].count
                Coverage.statementStart(857)
                if (sizeIndex == 0) {
                    Coverage.ifStart(858)
                    data[sizeIndex].count -= i
                    Coverage.statementStart(859)
                    break
                } else if (c < i) {
                    Coverage.ifStart(860)
                    sizeIndex--
                    Coverage.statementStart(861)
                    i -= c
                    Coverage.statementStart(862)
                } else if (c == i) {
                    Coverage.ifStart(863)
                    sizeIndex--
                    Coverage.statementStart(864)
                    break
                } else {
                    Coverage.ifStart(865)
                    data[sizeIndex].count -= i
                    Coverage.statementStart(866)
                    break
                }
                Coverage.statementStart(867)
            }
            Coverage.statementStart(868)
        }
        Coverage.statementStart(869)
    }

    fun backupPosition() {
        Coverage.funStart(870)
        posBackup = posAbsolute
        Coverage.statementStart(871)
    }

    fun restorePosition() {
        Coverage.funStart(872)
        require(posBackup <= sizeAbsolute)
        Coverage.statementStart(873)
        posAbsolute = 0
        Coverage.statementStart(874)
        posIndex = 0
        Coverage.statementStart(875)
        posIndexLocal = 0
        Coverage.statementStart(876)
        skipPos(posBackup)
        Coverage.statementStart(877)
    }

    fun current(): Value {
        Coverage.funStart(878)
        internalSafeNextElement()
        Coverage.statementStart(879)
        return data[posIndex].value
    }

    override fun next(): Value {
        Coverage.funStart(880)
        internalSafeNextElement()
        Coverage.statementStart(881)
        posIndexLocal++
        Coverage.statementStart(882)
        posAbsolute++
        Coverage.statementStart(883)
        return data[posIndex].value
    }

    override fun hasNext() = sizeAbsolute > posAbsolute
    fun availableWrite() = capacity - sizeIndex - 1
    fun availableRead() = sizeAbsolute - posAbsolute
    fun canAppend() = availableWrite() > 0
    fun append(value: Value, count: Int = 1) {
        Coverage.funStart(884)
        require(sizeIndex < capacity - 1 && count > 0)
        Coverage.statementStart(885)
        if (sizeAbsolute == 0) {
            Coverage.ifStart(886)
            data[sizeIndex].count = count
            Coverage.statementStart(887)
            data[sizeIndex].value = value
            Coverage.statementStart(888)
        } else if (data[sizeIndex].value == value) {
            Coverage.ifStart(889)
            data[sizeIndex].count += count
            Coverage.statementStart(890)
        } else {
            Coverage.ifStart(891)
            sizeIndex++
            Coverage.statementStart(892)
            data[sizeIndex].count = count
            Coverage.statementStart(893)
            data[sizeIndex].value = value
            Coverage.statementStart(894)
        }
        Coverage.statementStart(895)
        sizeAbsolute += count
        Coverage.statementStart(896)
    }

    fun sameElements(): Int {
        Coverage.funStart(897)
        internalSafeNextElement()
        Coverage.statementStart(898)
        if (posIndex > sizeIndex) {
            Coverage.ifStart(899)
            return 0
        }
        Coverage.statementStart(900)
        return data[posIndex].count - posIndexLocal
    }

    fun internalNextElement() {
        Coverage.funStart(901)
        posIndex++
        Coverage.statementStart(902)
        posIndexLocal = 0
        Coverage.statementStart(903)
    }

    fun internalSafeNextElement() {
        Coverage.funStart(904)
        if (posIndexLocal == data[posIndex].count && posIndex < sizeIndex) {
            Coverage.ifStart(905)
            internalNextElement()
            Coverage.statementStart(906)
        }
        Coverage.statementStart(907)
    }

    fun copy(from: ResultVektor, count: Int) {
        Coverage.funStart(908)
        require(count > 0)
        Coverage.statementStart(909)
        var i = count
        Coverage.statementStart(910)
        from.internalSafeNextElement()
        Coverage.statementStart(911)
        while (true) {
            Coverage.whileLoopStart(912)
            val c = from.data[from.posIndex].count - from.posIndexLocal
            Coverage.statementStart(913)
            if (c < i) {
                Coverage.ifStart(914)
                append(from.data[from.posIndex].value, c)
                Coverage.statementStart(915)
                from.internalNextElement()
                Coverage.statementStart(916)
                i -= c
                Coverage.statementStart(917)
            } else {
                Coverage.ifStart(918)
                append(from.data[from.posIndex].value, i)
                Coverage.statementStart(919)
                from.posIndexLocal += i
                Coverage.statementStart(920)
                break
            }
            Coverage.statementStart(921)
        }
        Coverage.statementStart(922)
        from.posAbsolute += count
        Coverage.statementStart(923)
    }

    fun insertSorted(value: Value, first: Int = posAbsolute, last: Int = sizeAbsolute, comparator: Comparator<Value>, count: Int): Pair<Int, Int> {
        Coverage.funStart(924)
        println(this)
        Coverage.statementStart(925)
        if (sizeAbsolute == 0) {
            Coverage.ifStart(926)
            append(value, count)
            Coverage.statementStart(927)
            return Pair(0, count)
        }
        Coverage.statementStart(928)
        require(availableWrite() >= 2)
        Coverage.statementStart(929)
        require(count > 0)
        Coverage.statementStart(930)
        posAbsolute = 0
        Coverage.statementStart(931)
        posIndex = 0
        Coverage.statementStart(932)
        posIndexLocal = 0
        Coverage.statementStart(933)
        sizeAbsolute += count
        Coverage.statementStart(934)
        var index = 0
        Coverage.statementStart(935)
        var indexLocal = 0
        Coverage.statementStart(936)
        var idx = first
        Coverage.statementStart(937)
        var absoluteindex = 0
        Coverage.statementStart(938)
        while (idx > 0) {
            Coverage.whileLoopStart(939)
            val c = data[index].count
            Coverage.statementStart(940)
            if (c == idx) {
                Coverage.ifStart(941)
                indexLocal = 0
                Coverage.statementStart(942)
                absoluteindex = first
                Coverage.statementStart(943)
                index++
                Coverage.statementStart(944)
                break
            } else if (c > idx) {
                Coverage.ifStart(945)
                indexLocal = idx
                Coverage.statementStart(946)
                break
            } else {
                Coverage.ifStart(947)
                absoluteindex += data[index].count
                Coverage.statementStart(948)
                idx -= c
                Coverage.statementStart(949)
                index++
                Coverage.statementStart(950)
            }
            Coverage.statementStart(951)
        }
        Coverage.statementStart(952)
        idx = last - first + 1//maximaler noch zu gehende index
        Coverage.statementStart(953)
        var currentidx = first
        Coverage.statementStart(954)
        while (true) {
            Coverage.whileLoopStart(955)
            println("x $index ${data[index].value} $value $absoluteindex ${data[index].count} $indexLocal $first $last")
            Coverage.statementStart(956)
            if (data[index].value == value) {
                Coverage.ifStart(957)
                data[index].count += count
                Coverage.statementStart(958)
                return Pair(absoluteindex, data[index].count)
            } else if (absoluteindex == last) {
                Coverage.ifStart(959)
                var j = sizeIndex
                Coverage.statementStart(960)
                while (j >= index) {
                    Coverage.whileLoopStart(961)
                    data[j + 1].count = data[j].count
                    Coverage.statementStart(962)
                    data[j + 1].value = data[j].value
                    Coverage.statementStart(963)
                    j--
                    Coverage.statementStart(964)
                }
                Coverage.statementStart(965)
                data[index].value = value
                Coverage.statementStart(966)
                data[index].count = count
                Coverage.statementStart(967)
                sizeIndex++
                Coverage.statementStart(968)
                return Pair(last, count)
            } else if (absoluteindex + data[index].count > last && comparator.compare(data[index].value, value) < 0) {
                Coverage.ifStart(969)
                var j = sizeIndex
                Coverage.statementStart(970)
                while (j >= index) {
                    Coverage.whileLoopStart(971)
                    data[j + 2].count = data[j].count
                    Coverage.statementStart(972)
                    data[j + 2].value = data[j].value
                    Coverage.statementStart(973)
                    j--
                    Coverage.statementStart(974)
                }
                Coverage.statementStart(975)
                data[index + 1].value = value
                Coverage.statementStart(976)
                data[index + 1].count = count
                Coverage.statementStart(977)
                data[index].count = last - absoluteindex
                Coverage.statementStart(978)
                data[index + 2].count -= last - absoluteindex
                Coverage.statementStart(979)
                sizeIndex += 2
                Coverage.statementStart(980)
                return Pair(last, count)
            } else if (comparator.compare(data[index].value, value) < 0) {
                Coverage.ifStart(981)
                val c = data[index].count - indexLocal
                Coverage.statementStart(982)
                currentidx += c
                Coverage.statementStart(983)
                idx -= c
                Coverage.statementStart(984)
                indexLocal = 0
                Coverage.statementStart(985)
                absoluteindex += data[index].count
                Coverage.statementStart(986)
                index++
                Coverage.statementStart(987)
            } else if (indexLocal != 0) {
                Coverage.ifStart(988)
                var j = sizeIndex
                Coverage.statementStart(989)
                while (j >= index) {
                    Coverage.whileLoopStart(990)
                    data[j + 2].count = data[j].count
                    Coverage.statementStart(991)
                    data[j + 2].value = data[j].value
                    Coverage.statementStart(992)
                    j--
                    Coverage.statementStart(993)
                }
                Coverage.statementStart(994)
                data[index + 1].value = value
                Coverage.statementStart(995)
                data[index + 1].count = count
                Coverage.statementStart(996)
                data[index].count = indexLocal
                Coverage.statementStart(997)
                data[index + 2].count -= indexLocal
                Coverage.statementStart(998)
                sizeIndex += 2
                Coverage.statementStart(999)
                absoluteindex += data[index].count
                Coverage.statementStart(1000)
                return Pair(absoluteindex, count)
            } else {
                Coverage.ifStart(1001)
                var j = sizeIndex
                Coverage.statementStart(1002)
                while (j >= index) {
                    Coverage.whileLoopStart(1003)
                    data[j + 1].count = data[j].count
                    Coverage.statementStart(1004)
                    data[j + 1].value = data[j].value
                    Coverage.statementStart(1005)
                    j--
                    Coverage.statementStart(1006)
                }
                Coverage.statementStart(1007)
                data[index].value = value
                Coverage.statementStart(1008)
                data[index].count = count
                Coverage.statementStart(1009)
                sizeIndex++
                Coverage.statementStart(1010)
                return Pair(absoluteindex, count)
            }
            Coverage.statementStart(1011)
        }
        Coverage.statementStart(1012)
        throw Exception("unreachable")
    }
}
