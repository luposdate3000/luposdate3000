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
            _posAbsolute = value
        }
    var posIndex = 0
    var posIndexLocal = 0
    var posBackup = 0
    var sizeAbsolute = 0
    var sizeIndex = 0
    val data = Array<CompressedElement>(capacity) { CompressedElement(0, undefValue) }
    override fun toString(): String {
Coverage.funStart(708)
        val res = StringBuilder()
Coverage.statementStart(709)
        res.append("$capacity $posAbsolute $posIndex $posIndexLocal $posBackup $sizeAbsolute $sizeIndex\n")
Coverage.statementStart(710)
        for (i in 0 until capacity) {
Coverage.forLoopStart(711)
            res.append("${data[i].value}(${data[i].count})")
Coverage.statementStart(712)
            if (i == posIndex) {
Coverage.ifStart(713)
                res.append("-")
Coverage.statementStart(714)
            }
Coverage.statementStart(715)
            if (i == sizeIndex) {
Coverage.ifStart(716)
                res.append("+")
Coverage.statementStart(717)
            }
Coverage.statementStart(718)
            res.append(",")
Coverage.statementStart(719)
        }
Coverage.statementStart(720)
        return res.toString()
    }
    fun skipPos(count: Int) {
Coverage.funStart(721)
        require(posAbsolute + count <= sizeAbsolute)
Coverage.statementStart(722)
        posAbsolute += count
Coverage.statementStart(723)
        if (count > 0) {
Coverage.ifStart(724)
            var i = count
Coverage.statementStart(725)
            while (true) {
Coverage.whileLoopStart(726)
                val c = data[posIndex].count - posIndexLocal
Coverage.statementStart(727)
                if (c < i) {
Coverage.ifStart(728)
                    internalNextElement()
Coverage.statementStart(729)
                    i -= c
Coverage.statementStart(730)
                } else {
Coverage.ifStart(731)
                    posIndexLocal += i
Coverage.statementStart(732)
                    break
                }
Coverage.statementStart(733)
            }
Coverage.statementStart(734)
        } else {
Coverage.ifStart(735)
            var i = -count
Coverage.statementStart(736)
            while (true) {
Coverage.whileLoopStart(737)
                val c = posIndexLocal
Coverage.statementStart(738)
                if (c < i) {
Coverage.ifStart(739)
                    posIndex--
Coverage.statementStart(740)
                    posIndexLocal = data[posIndex].count
Coverage.statementStart(741)
                    i -= c
Coverage.statementStart(742)
                } else {
Coverage.ifStart(743)
                    posIndexLocal -= i
Coverage.statementStart(744)
                    break
                }
Coverage.statementStart(745)
            }
Coverage.statementStart(746)
        }
Coverage.statementStart(747)
    }
    fun skipSize(count: Int) {
Coverage.funStart(748)
        require(posAbsolute <= sizeAbsolute + count)
Coverage.statementStart(749)
        sizeAbsolute += count
Coverage.statementStart(750)
        if (count >= 0) {
Coverage.ifStart(751)
            data[sizeIndex].count += count
Coverage.statementStart(752)
        } else {
Coverage.ifStart(753)
            var i = -count
Coverage.statementStart(754)
            while (true) {
Coverage.whileLoopStart(755)
                val c = data[sizeIndex].count
Coverage.statementStart(756)
                if (sizeIndex == 0) {
Coverage.ifStart(757)
                    data[sizeIndex].count -= i
Coverage.statementStart(758)
                    break
                } else if (c < i) {
Coverage.ifStart(759)
                    sizeIndex--
Coverage.statementStart(760)
                    i -= c
Coverage.statementStart(761)
                } else if (c == i) {
Coverage.ifStart(762)
                    sizeIndex--
Coverage.statementStart(763)
                    break
                } else {
Coverage.ifStart(764)
                    data[sizeIndex].count -= i
Coverage.statementStart(765)
                    break
                }
Coverage.statementStart(766)
            }
Coverage.statementStart(767)
        }
Coverage.statementStart(768)
    }
    fun backupPosition() {
Coverage.funStart(769)
        posBackup = posAbsolute
Coverage.statementStart(770)
    }
    fun restorePosition() {
Coverage.funStart(771)
        require(posBackup <= sizeAbsolute)
Coverage.statementStart(772)
        posAbsolute = 0
Coverage.statementStart(773)
        posIndex = 0
Coverage.statementStart(774)
        posIndexLocal = 0
Coverage.statementStart(775)
        skipPos(posBackup)
Coverage.statementStart(776)
    }
    fun current(): Value {
Coverage.funStart(777)
        internalSafeNextElement()
Coverage.statementStart(778)
        return data[posIndex].value
    }
    override fun next(): Value {
Coverage.funStart(779)
        internalSafeNextElement()
Coverage.statementStart(780)
        posIndexLocal++
Coverage.statementStart(781)
        posAbsolute++
Coverage.statementStart(782)
        return data[posIndex].value
    }
    override fun hasNext() = sizeAbsolute > posAbsolute
    fun availableWrite() = capacity - sizeIndex - 1
    fun availableRead() = sizeAbsolute - posAbsolute
    fun canAppend() = availableWrite() > 0
    fun append(value: Value, count: Int = 1) {
Coverage.funStart(783)
        require(sizeIndex < capacity - 1 && count > 0)
Coverage.statementStart(784)
        if (sizeAbsolute == 0) {
Coverage.ifStart(785)
            data[sizeIndex].count = count
Coverage.statementStart(786)
            data[sizeIndex].value = value
Coverage.statementStart(787)
        } else if (data[sizeIndex].value == value) {
Coverage.ifStart(788)
            data[sizeIndex].count += count
Coverage.statementStart(789)
        } else {
Coverage.ifStart(790)
            sizeIndex++
Coverage.statementStart(791)
            data[sizeIndex].count = count
Coverage.statementStart(792)
            data[sizeIndex].value = value
Coverage.statementStart(793)
        }
Coverage.statementStart(794)
        sizeAbsolute += count
Coverage.statementStart(795)
    }
    fun sameElements(): Int {
Coverage.funStart(796)
        internalSafeNextElement()
Coverage.statementStart(797)
require(posIndex<=sizeIndex)
Coverage.statementStart(798)
        return data[posIndex].count - posIndexLocal
    }
    fun internalNextElement() {
Coverage.funStart(799)
        posIndex++
Coverage.statementStart(800)
        posIndexLocal = 0
Coverage.statementStart(801)
    }
    fun internalSafeNextElement() {
Coverage.funStart(802)
        if (posIndexLocal == data[posIndex].count && posIndex < sizeIndex) {
Coverage.ifStart(803)
            internalNextElement()
Coverage.statementStart(804)
        }
Coverage.statementStart(805)
    }
    fun copy(from: ResultVektor, count: Int) {
Coverage.funStart(806)
        require(count > 0)
Coverage.statementStart(807)
        var i = count
Coverage.statementStart(808)
        from.internalSafeNextElement()
Coverage.statementStart(809)
        while (true) {
Coverage.whileLoopStart(810)
            val c = from.data[from.posIndex].count - from.posIndexLocal
Coverage.statementStart(811)
            if (c < i) {
Coverage.ifStart(812)
                append(from.data[from.posIndex].value, c)
Coverage.statementStart(813)
                from.internalNextElement()
Coverage.statementStart(814)
                i -= c
Coverage.statementStart(815)
            } else {
Coverage.ifStart(816)
                append(from.data[from.posIndex].value, i)
Coverage.statementStart(817)
                from.posIndexLocal += i
Coverage.statementStart(818)
                break
            }
Coverage.statementStart(819)
        }
Coverage.statementStart(820)
        from.posAbsolute += count
Coverage.statementStart(821)
    }
    fun insertSorted(value: Value, first: Int = posAbsolute, last: Int = sizeAbsolute, comparator: Comparator<Value>, count: Int): Pair<Int, Int> {
Coverage.funStart(822)
        if (sizeAbsolute == 0) {
Coverage.ifStart(823)
            append(value, count)
Coverage.statementStart(824)
            return Pair(0, count)
        }
Coverage.statementStart(825)
        require(availableWrite() >= 2)
Coverage.statementStart(826)
        require(count > 0)
Coverage.statementStart(827)
        posAbsolute = 0
Coverage.statementStart(828)
        posIndex = 0
Coverage.statementStart(829)
        posIndexLocal = 0
Coverage.statementStart(830)
        sizeAbsolute += count
Coverage.statementStart(831)
        var index = 0
Coverage.statementStart(832)
        var indexLocal = 0
Coverage.statementStart(833)
        var idx = first
Coverage.statementStart(834)
        var absoluteindex = 0
Coverage.statementStart(835)
        while (idx > 0) {
Coverage.whileLoopStart(836)
            val c = data[index].count
Coverage.statementStart(837)
            if (c == idx) {
Coverage.ifStart(838)
                indexLocal = 0
Coverage.statementStart(839)
                absoluteindex = first
Coverage.statementStart(840)
                index++
Coverage.statementStart(841)
                break
            } else if (c > idx) {
Coverage.ifStart(842)
                indexLocal = idx
Coverage.statementStart(843)
                break
            } else {
Coverage.ifStart(844)
                absoluteindex += data[index].count
Coverage.statementStart(845)
                idx -= c
Coverage.statementStart(846)
                index++
Coverage.statementStart(847)
            }
Coverage.statementStart(848)
        }
Coverage.statementStart(849)
        idx = last - first + 1
Coverage.statementStart(850)
        var currentidx = first
Coverage.statementStart(851)
        while (true) {
Coverage.whileLoopStart(852)
            if (data[index].value == value) {
Coverage.ifStart(853)
                data[index].count += count
Coverage.statementStart(854)
                return Pair(absoluteindex, data[index].count)
            } else if (absoluteindex == last) {
Coverage.ifStart(855)
                var j = sizeIndex
Coverage.statementStart(856)
                while (j >= index) {
Coverage.whileLoopStart(857)
                    data[j + 1].count = data[j].count
Coverage.statementStart(858)
                    data[j + 1].value = data[j].value
Coverage.statementStart(859)
                    j--
Coverage.statementStart(860)
                }
Coverage.statementStart(861)
                data[index].value = value
Coverage.statementStart(862)
                data[index].count = count
Coverage.statementStart(863)
                sizeIndex++
Coverage.statementStart(864)
                return Pair(last, count)
            } else if (absoluteindex + data[index].count > last && comparator.compare(data[index].value, value) < 0) {
Coverage.ifStart(865)
                var j = sizeIndex
Coverage.statementStart(866)
                while (j >= index) {
Coverage.whileLoopStart(867)
                    data[j + 2].count = data[j].count
Coverage.statementStart(868)
                    data[j + 2].value = data[j].value
Coverage.statementStart(869)
                    j--
Coverage.statementStart(870)
                }
Coverage.statementStart(871)
                data[index + 1].value = value
Coverage.statementStart(872)
                data[index + 1].count = count
Coverage.statementStart(873)
                data[index].count = last - absoluteindex
Coverage.statementStart(874)
                data[index + 2].count -= last - absoluteindex
Coverage.statementStart(875)
                sizeIndex += 2
Coverage.statementStart(876)
                return Pair(last, count)
            } else if (comparator.compare(data[index].value, value) < 0) {
Coverage.ifStart(877)
                val c = data[index].count - indexLocal
Coverage.statementStart(878)
                currentidx += c
Coverage.statementStart(879)
                idx -= c
Coverage.statementStart(880)
                indexLocal = 0
Coverage.statementStart(881)
                absoluteindex += data[index].count
Coverage.statementStart(882)
                index++
Coverage.statementStart(883)
            } else if (indexLocal != 0) {
Coverage.ifStart(884)
                var j = sizeIndex
Coverage.statementStart(885)
                while (j >= index) {
Coverage.whileLoopStart(886)
                    data[j + 2].count = data[j].count
Coverage.statementStart(887)
                    data[j + 2].value = data[j].value
Coverage.statementStart(888)
                    j--
Coverage.statementStart(889)
                }
Coverage.statementStart(890)
                data[index + 1].value = value
Coverage.statementStart(891)
                data[index + 1].count = count
Coverage.statementStart(892)
                data[index].count = indexLocal
Coverage.statementStart(893)
                data[index + 2].count -= indexLocal
Coverage.statementStart(894)
                sizeIndex += 2
Coverage.statementStart(895)
                absoluteindex += data[index].count
Coverage.statementStart(896)
                return Pair(absoluteindex, count)
            } else {
Coverage.ifStart(897)
                var j = sizeIndex
Coverage.statementStart(898)
                while (j >= index) {
Coverage.whileLoopStart(899)
                    data[j + 1].count = data[j].count
Coverage.statementStart(900)
                    data[j + 1].value = data[j].value
Coverage.statementStart(901)
                    j--
Coverage.statementStart(902)
                }
Coverage.statementStart(903)
                data[index].value = value
Coverage.statementStart(904)
                data[index].count = count
Coverage.statementStart(905)
                sizeIndex++
Coverage.statementStart(906)
                return Pair(absoluteindex, count)
            }
Coverage.statementStart(907)
        }
Coverage.statementStart(908)
        throw Exception("unreachable")
    }
}
