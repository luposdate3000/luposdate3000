package lupos.s03resultRepresentation
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s00misc.Coverage
class ResultVektor(undefValue: Value) : Iterator<Value> {
    companion object {
        var capacity = 6
    }
    var posAbsolute = 0
    var posIndex = 0
    var posIndexLocal = 0
    var posBackup = Array(3) { 0 }
    var sizeAbsolute = 0
    var sizeIndex = 0
    val data = Array<CompressedElement>(capacity) { CompressedElement(0, undefValue) }
    override fun toString(): String {
//Coverage.funStart(675)
        val res = StringBuilder()
//Coverage.statementStart(676)
        res.append("$capacity $posAbsolute $posIndex $posIndexLocal $posBackup $sizeAbsolute $sizeIndex\n")
//Coverage.statementStart(677)
        for (i in 0 until capacity) {
//Coverage.forLoopStart(678)
            res.append("${data[i].value}(${data[i].count})")
//Coverage.statementStart(679)
            if (i == posIndex) {
//Coverage.ifStart(680)
                res.append("-")
//Coverage.statementStart(681)
            }
//Coverage.statementStart(682)
            if (i == sizeIndex) {
//Coverage.ifStart(683)
                res.append("+")
//Coverage.statementStart(684)
            }
//Coverage.statementStart(685)
            res.append(",")
//Coverage.statementStart(686)
        }
//Coverage.statementStart(687)
        return res.toString()
    }
    fun skipPos(count: Int) {
Coverage.funStart(688)
println("$posAbsolute $count $sizeAbsolute")
        require(posAbsolute + count <= sizeAbsolute)
Coverage.statementStart(689)
        posAbsolute += count
Coverage.statementStart(690)
        if (count > 0) {
Coverage.ifStart(691)
            var i = count
Coverage.statementStart(692)
            while (true) {
Coverage.whileLoopStart(693)
                val c = data[posIndex].count - posIndexLocal
Coverage.statementStart(694)
                if (c < i) {
Coverage.ifStart(695)
                    internalNextElement()
Coverage.statementStart(696)
                    i -= c
Coverage.statementStart(697)
                } else {
Coverage.ifStart(698)
                    posIndexLocal += i
Coverage.statementStart(699)
                    break
                }
Coverage.statementStart(700)
            }
Coverage.statementStart(701)
        } else {
Coverage.ifStart(702)
            var i = -count
Coverage.statementStart(703)
            while (true) {
Coverage.whileLoopStart(704)
                val c = posIndexLocal
Coverage.statementStart(705)
                if (c < i) {
Coverage.ifStart(706)
                    posIndex--
Coverage.statementStart(707)
                    posIndexLocal = data[posIndex].count
Coverage.statementStart(708)
                    i -= c
Coverage.statementStart(709)
                } else {
Coverage.ifStart(710)
                    posIndexLocal -= i
Coverage.statementStart(711)
                    break
                }
Coverage.statementStart(712)
            }
Coverage.statementStart(713)
        }
Coverage.statementStart(714)
    }
    fun skipSize(count: Int) {
Coverage.funStart(715)
        require(posAbsolute <= sizeAbsolute + count)
Coverage.statementStart(716)
        sizeAbsolute += count
Coverage.statementStart(717)
        if (count >= 0) {
Coverage.ifStart(718)
            data[sizeIndex].count += count
Coverage.statementStart(719)
        } else {
Coverage.ifStart(720)
            var i = -count
Coverage.statementStart(721)
            while (true) {
Coverage.whileLoopStart(722)
                val c = data[sizeIndex].count
Coverage.statementStart(723)
                if (sizeIndex == 0) {
Coverage.ifStart(724)
                    data[sizeIndex].count -= i
Coverage.statementStart(725)
                    break
                } else if (c < i) {
Coverage.ifStart(726)
                    sizeIndex--
Coverage.statementStart(727)
                    i -= c
Coverage.statementStart(728)
                } else if (c == i) {
Coverage.ifStart(729)
                    sizeIndex--
Coverage.statementStart(730)
                    break
                } else {
Coverage.ifStart(731)
                    data[sizeIndex].count -= i
Coverage.statementStart(732)
                    break
                }
Coverage.statementStart(733)
            }
Coverage.statementStart(734)
        }
Coverage.statementStart(735)
    }
    fun backupPosition() {
Coverage.funStart(736)
        posBackup[0] = posAbsolute
Coverage.statementStart(737)
        posBackup[1] = posIndex
Coverage.statementStart(738)
        posBackup[2] = posIndexLocal
Coverage.statementStart(739)
    }
    fun restorePosition() {
Coverage.funStart(740)
require(posBackup[0]<=sizeAbsolute)
        posAbsolute = posBackup[0]
Coverage.statementStart(741)
        posIndex = posBackup[1]
Coverage.statementStart(742)
        posIndexLocal = posBackup[2]
Coverage.statementStart(743)
    }
    fun current(): Value {
Coverage.funStart(744)
        internalSafeNextElement()
Coverage.statementStart(745)
        return data[posIndex].value
    }
    override fun next(): Value {
Coverage.funStart(746)
        internalSafeNextElement()
Coverage.statementStart(747)
        posIndexLocal++
Coverage.statementStart(748)
        posAbsolute++
Coverage.statementStart(749)
        return data[posIndex].value
    }
    override fun hasNext() = sizeAbsolute > posAbsolute
    fun availableWrite() = capacity - sizeIndex - 1
    fun availableRead() = sizeAbsolute - posAbsolute
    fun canAppend() = availableWrite() > 0
    fun append(value: Value, count: Int = 1) {
Coverage.funStart(750)
        require(sizeIndex < capacity - 1 && count > 0)
Coverage.statementStart(751)
        if (sizeAbsolute == 0) {
Coverage.ifStart(752)
            data[sizeIndex].count = count
Coverage.statementStart(753)
            data[sizeIndex].value = value
Coverage.statementStart(754)
        } else if (data[sizeIndex].value == value) {
Coverage.ifStart(755)
            data[sizeIndex].count += count
Coverage.statementStart(756)
        } else {
Coverage.ifStart(757)
            sizeIndex++
Coverage.statementStart(758)
            data[sizeIndex].count = count
Coverage.statementStart(759)
            data[sizeIndex].value = value
Coverage.statementStart(760)
        }
Coverage.statementStart(761)
        sizeAbsolute += count
Coverage.statementStart(762)
    }
    fun sameElements(): Int {
Coverage.funStart(763)
        internalSafeNextElement()
Coverage.statementStart(764)
        if (posIndex > sizeIndex) {
Coverage.ifStart(765)
            return 0
        }
Coverage.statementStart(766)
        return data[posIndex].count - posIndexLocal
    }
    fun internalNextElement() {
Coverage.funStart(767)
        posIndex++
Coverage.statementStart(768)
        posIndexLocal = 0
Coverage.statementStart(769)
    }
    fun internalSafeNextElement() {
Coverage.funStart(770)
        if (posIndexLocal == data[posIndex].count && posIndex < sizeIndex) {
Coverage.ifStart(771)
            internalNextElement()
Coverage.statementStart(772)
        }
Coverage.statementStart(773)
    }
    fun copy(from: ResultVektor, count: Int) {
Coverage.funStart(774)
        require(count > 0)
Coverage.statementStart(775)
        var i = count
Coverage.statementStart(776)
        from.internalSafeNextElement()
Coverage.statementStart(777)
        while (true) {
Coverage.whileLoopStart(778)
            val c = from.data[from.posIndex].count - from.posIndexLocal
Coverage.statementStart(779)
            if (c < i) {
Coverage.ifStart(780)
                append(from.data[from.posIndex].value, c)
Coverage.statementStart(781)
                from.internalNextElement()
Coverage.statementStart(782)
                i -= c
Coverage.statementStart(783)
            } else {
Coverage.ifStart(784)
                append(from.data[from.posIndex].value, i)
Coverage.statementStart(785)
                from.posIndexLocal += i
Coverage.statementStart(786)
                break
            }
Coverage.statementStart(787)
        }
Coverage.statementStart(788)
        from.posAbsolute += count
Coverage.statementStart(789)
    }
    fun insertSorted(value: Value, first: Int = posAbsolute, last: Int = sizeAbsolute, comparator: Comparator<Value>, count: Int): Pair<Int, Int> {
Coverage.funStart(790)
        if (sizeAbsolute == 0) {
Coverage.ifStart(791)
            append(value, count)
Coverage.statementStart(792)
            return Pair(0, count)
        }
Coverage.statementStart(793)
        require(availableWrite() >= 2)
Coverage.statementStart(794)
        require(count > 0)
Coverage.statementStart(795)
        posAbsolute = 0
Coverage.statementStart(796)
        posIndex = 0
Coverage.statementStart(797)
        posIndexLocal = 0
Coverage.statementStart(798)
        sizeAbsolute += count
Coverage.statementStart(799)
        var firstIndex = 0
Coverage.statementStart(800)
        var firstIndexLocal = 0
Coverage.statementStart(801)
        var lastIndex = 0
Coverage.statementStart(802)
        var lastIndexLocal = 0
Coverage.statementStart(803)
        var idx = first
Coverage.statementStart(804)
        var absoluteindex = 0
Coverage.statementStart(805)
        while (idx > 0) {
Coverage.whileLoopStart(806)
            val c = data[firstIndex].count
Coverage.statementStart(807)
            if (c == idx) {
Coverage.ifStart(808)
                firstIndexLocal = 0
Coverage.statementStart(809)
                absoluteindex = first
Coverage.statementStart(810)
                firstIndex++
Coverage.statementStart(811)
                break
            } else if (c > idx) {
Coverage.ifStart(812)
                firstIndexLocal = idx
Coverage.statementStart(813)
                break
            } else {
Coverage.ifStart(814)
                absoluteindex += data[firstIndex].count
Coverage.statementStart(815)
                idx -= c
Coverage.statementStart(816)
                firstIndex++
Coverage.statementStart(817)
            }
Coverage.statementStart(818)
        }
Coverage.statementStart(819)
        lastIndex = firstIndex
Coverage.statementStart(820)
        lastIndexLocal = firstIndexLocal
Coverage.statementStart(821)
        idx = last - first + 1//maximaler noch zu gehende index
Coverage.statementStart(822)
        var currentidx = first
Coverage.statementStart(823)
        while (true) {
Coverage.whileLoopStart(824)
            if (data[lastIndex].value == value) {
Coverage.ifStart(825)
                data[lastIndex].count += count
Coverage.statementStart(826)
                return Pair(absoluteindex, data[lastIndex].count)
            } else if (absoluteindex == last) {
Coverage.ifStart(827)
                var j = sizeIndex
Coverage.statementStart(828)
                while (j >= lastIndex) {
Coverage.whileLoopStart(829)
                    data[j + 1].count = data[j].count
Coverage.statementStart(830)
                    data[j + 1].value = data[j].value
Coverage.statementStart(831)
                    j--
Coverage.statementStart(832)
                }
Coverage.statementStart(833)
                data[lastIndex].value = value
Coverage.statementStart(834)
                data[lastIndex].count = count
Coverage.statementStart(835)
                sizeIndex++
Coverage.statementStart(836)
                return Pair(last, count)
            } else if (absoluteindex + data[lastIndex].count > last && comparator.compare(data[lastIndex].value, value) < 0) {
Coverage.ifStart(837)
                var j = sizeIndex
Coverage.statementStart(838)
                while (j >= lastIndex) {
Coverage.whileLoopStart(839)
                    data[j + 2].count = data[j].count
Coverage.statementStart(840)
                    data[j + 2].value = data[j].value
Coverage.statementStart(841)
                    j--
Coverage.statementStart(842)
                }
Coverage.statementStart(843)
                data[lastIndex + 1].value = value
Coverage.statementStart(844)
                data[lastIndex + 1].count = count
Coverage.statementStart(845)
                data[lastIndex].count = last - absoluteindex
Coverage.statementStart(846)
                data[lastIndex + 2].count -= last - absoluteindex
Coverage.statementStart(847)
                sizeIndex += 2
Coverage.statementStart(848)
                return Pair(last, count)
            } else if (comparator.compare(data[lastIndex].value, value) < 0) {
Coverage.ifStart(849)
                val c = data[lastIndex].count - lastIndexLocal
Coverage.statementStart(850)
                currentidx += c
Coverage.statementStart(851)
                    idx -= c
Coverage.statementStart(852)
                    lastIndexLocal = 0
Coverage.statementStart(853)
                    absoluteindex += data[lastIndex].count
Coverage.statementStart(854)
                    lastIndex++
Coverage.statementStart(855)
            } else {
Coverage.ifStart(856)
                if (firstIndexLocal != 0) {
Coverage.ifStart(857)
                    var j = sizeIndex
Coverage.statementStart(858)
                    while (j >= lastIndex) {
Coverage.whileLoopStart(859)
                        data[j + 2].count = data[j].count
Coverage.statementStart(860)
                        data[j + 2].value = data[j].value
Coverage.statementStart(861)
                        j--
Coverage.statementStart(862)
                    }
Coverage.statementStart(863)
                    data[lastIndex + 1].value = value
Coverage.statementStart(864)
                    data[lastIndex + 1].count = count
Coverage.statementStart(865)
                    data[lastIndex].count = firstIndexLocal
Coverage.statementStart(866)
                    data[lastIndex + 2].count -= firstIndexLocal
Coverage.statementStart(867)
                    sizeIndex += 2
Coverage.statementStart(868)
                    absoluteindex += data[lastIndex].count
Coverage.statementStart(869)
                    return Pair(absoluteindex, count)
                } else {
Coverage.ifStart(870)
                    var j = sizeIndex
Coverage.statementStart(871)
                    while (j >= lastIndex) {
Coverage.whileLoopStart(872)
                        data[j + 1].count = data[j].count
Coverage.statementStart(873)
                        data[j + 1].value = data[j].value
Coverage.statementStart(874)
                        j--
Coverage.statementStart(875)
                    }
Coverage.statementStart(876)
                    data[lastIndex].value = value
Coverage.statementStart(877)
                    data[lastIndex].count = count
Coverage.statementStart(878)
                    sizeIndex++
Coverage.statementStart(879)
                    return Pair(absoluteindex, count)
                }
Coverage.statementStart(880)
            }
Coverage.statementStart(881)
        }
Coverage.statementStart(882)
        throw Exception("unreachable")
    }
}
