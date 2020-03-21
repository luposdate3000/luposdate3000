package lupos.s03resultRepresentation
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw
class ResultVektor(undefValue: Value) : Iterator<Value> {
    companion object {
        var capacity = 5
    }
    var posAbsolute = 0
    var posIndex = 0
    var posIndexLocal = 0
    var posBackup = Array(3) { 0 }
    var sizeAbsolute = 0
    var sizeIndex = 0
    val data = Array<CompressedElement>(capacity) { CompressedElement(0, undefValue) }
    override fun toString(): String {
Coverage.funStart(682)
        val res = StringBuilder()
Coverage.statementStart(683)
        res.append("$capacity $posAbsolute $posIndex $posIndexLocal $posBackup $sizeAbsolute $sizeIndex\n")
Coverage.statementStart(684)
        for (i in 0 until capacity) {
Coverage.forLoopStart(685)
            res.append("${data[i].value}(${data[i].count})")
Coverage.statementStart(686)
            if (i == posIndex) {
Coverage.ifStart(687)
                res.append("-")
Coverage.statementStart(688)
            }
Coverage.statementStart(689)
            if (i == sizeIndex) {
Coverage.ifStart(690)
                res.append("+")
Coverage.statementStart(691)
            }
Coverage.statementStart(692)
            res.append(",")
Coverage.statementStart(693)
        }
Coverage.statementStart(694)
        return res.toString()
    }
    fun skipPos(count: Int) {
Coverage.funStart(695)
        require(posAbsolute + count <= sizeAbsolute)
Coverage.statementStart(696)
        posAbsolute += count
Coverage.statementStart(697)
        if (count > 0) {
Coverage.ifStart(698)
            var i = count
Coverage.statementStart(699)
            while (true) {
Coverage.whileLoopStart(700)
                val c = data[posIndex].count - posIndexLocal
Coverage.statementStart(701)
                if (c < i) {
Coverage.ifStart(702)
                    internalNextElement()
Coverage.statementStart(703)
                    i -= c
Coverage.statementStart(704)
                } else {
Coverage.ifStart(705)
                    posIndexLocal += i
Coverage.statementStart(706)
                    break
                }
Coverage.statementStart(707)
            }
Coverage.statementStart(708)
        } else {
Coverage.ifStart(709)
            var i = -count
Coverage.statementStart(710)
            while (true) {
Coverage.whileLoopStart(711)
                val c = posIndexLocal
Coverage.statementStart(712)
                if (c < i) {
Coverage.ifStart(713)
                    posIndex--
Coverage.statementStart(714)
                    posIndexLocal = data[posIndex].count
Coverage.statementStart(715)
                    i -= c
Coverage.statementStart(716)
                } else {
Coverage.ifStart(717)
                    posIndexLocal -= i
Coverage.statementStart(718)
                    break
                }
Coverage.statementStart(719)
            }
Coverage.statementStart(720)
        }
Coverage.statementStart(721)
    }
    fun skipSize(count: Int) {
Coverage.funStart(722)
        require(posAbsolute <= sizeAbsolute + count)
Coverage.statementStart(723)
        sizeAbsolute += count
Coverage.statementStart(724)
        if (count >= 0) {
Coverage.ifStart(725)
            data[sizeIndex].count += count
Coverage.statementStart(726)
        } else {
Coverage.ifStart(727)
            var i = -count
Coverage.statementStart(728)
            while (true) {
Coverage.whileLoopStart(729)
                val c = data[sizeIndex].count
Coverage.statementStart(730)
                if (sizeIndex == 0) {
Coverage.ifStart(731)
                    data[sizeIndex].count -= i
Coverage.statementStart(732)
                    break
                } else if (c < i) {
Coverage.ifStart(733)
                    sizeIndex--
Coverage.statementStart(734)
                    i -= c
Coverage.statementStart(735)
                } else if (c == i) {
Coverage.ifStart(736)
                    sizeIndex--
Coverage.statementStart(737)
                    break
                } else {
Coverage.ifStart(738)
                    data[sizeIndex].count -= i
Coverage.statementStart(739)
                    break
                }
Coverage.statementStart(740)
            }
Coverage.statementStart(741)
        }
Coverage.statementStart(742)
    }
    fun backupPosition() {
Coverage.funStart(743)
        posBackup[0] = posAbsolute
Coverage.statementStart(744)
        posBackup[1] = posIndex
Coverage.statementStart(745)
        posBackup[2] = posIndexLocal
Coverage.statementStart(746)
    }
    fun restorePosition() {
Coverage.funStart(747)
        posAbsolute = posBackup[0]
Coverage.statementStart(748)
        posIndex = posBackup[1]
Coverage.statementStart(749)
        posIndexLocal = posBackup[2]
Coverage.statementStart(750)
    }
    fun current(): Value {
Coverage.funStart(751)
        internalSafeNextElement()
Coverage.statementStart(752)
        return data[posIndex].value
    }
    override fun next(): Value {
Coverage.funStart(753)
        internalSafeNextElement()
Coverage.statementStart(754)
        posIndexLocal++
Coverage.statementStart(755)
        posAbsolute++
Coverage.statementStart(756)
        return data[posIndex].value
    }
    override fun hasNext() = sizeAbsolute > posAbsolute
    fun availableWrite() = capacity - sizeIndex - 1
    fun availableRead() = sizeAbsolute - posAbsolute
    fun canAppend() = availableWrite() > 0
    fun append(value: Value, count: Int = 1) {
Coverage.funStart(757)
        require(sizeIndex < capacity - 1 && count > 0)
Coverage.statementStart(758)
        if (sizeAbsolute == 0) {
Coverage.ifStart(759)
            data[sizeIndex].count = count
Coverage.statementStart(760)
            data[sizeIndex].value = value
Coverage.statementStart(761)
        } else if (data[sizeIndex].value == value) {
Coverage.ifStart(762)
            data[sizeIndex].count += count
Coverage.statementStart(763)
        } else {
Coverage.ifStart(764)
            sizeIndex++
Coverage.statementStart(765)
            data[sizeIndex].count = count
Coverage.statementStart(766)
            data[sizeIndex].value = value
Coverage.statementStart(767)
        }
Coverage.statementStart(768)
        sizeAbsolute += count
Coverage.statementStart(769)
    }
    fun sameElements(): Int {
Coverage.funStart(770)
        internalSafeNextElement()
Coverage.statementStart(771)
        if (posIndex > sizeIndex) {
Coverage.ifStart(772)
            return 0
        }
Coverage.statementStart(773)
        return data[posIndex].count - posIndexLocal
    }
    fun internalNextElement() {
Coverage.funStart(774)
        posIndex++
Coverage.statementStart(775)
        posIndexLocal = 0
Coverage.statementStart(776)
    }
    fun internalSafeNextElement() {
Coverage.funStart(777)
        if (posIndexLocal == data[posIndex].count && posIndex < sizeIndex) {
Coverage.ifStart(778)
            internalNextElement()
Coverage.statementStart(779)
        }
Coverage.statementStart(780)
    }
    fun copy(from: ResultVektor, count: Int) {
Coverage.funStart(781)
        require(count > 0)
Coverage.statementStart(782)
        var i = count
Coverage.statementStart(783)
        from.internalSafeNextElement()
Coverage.statementStart(784)
        while (true) {
Coverage.whileLoopStart(785)
            val c = from.data[from.posIndex].count - from.posIndexLocal
Coverage.statementStart(786)
            if (c < i) {
Coverage.ifStart(787)
                append(from.data[from.posIndex].value, c)
Coverage.statementStart(788)
                from.internalNextElement()
Coverage.statementStart(789)
                i -= c
Coverage.statementStart(790)
            } else {
Coverage.ifStart(791)
                append(from.data[from.posIndex].value, i)
Coverage.statementStart(792)
                from.posIndexLocal += i
Coverage.statementStart(793)
                break
            }
Coverage.statementStart(794)
        }
Coverage.statementStart(795)
        from.posAbsolute += count
Coverage.statementStart(796)
    }
    fun insertSorted(value: Value, first: Int = posAbsolute, last: Int = sizeAbsolute, comparator: Comparator<Value>, count: Int): Pair<Int, Int> {
Coverage.funStart(797)
        if (sizeAbsolute == 0) {
Coverage.ifStart(798)
            append(value, count)
Coverage.statementStart(799)
            return Pair(0, count)
        }
Coverage.statementStart(800)
        require(availableWrite() >= 2)
Coverage.statementStart(801)
        require(count > 0)
Coverage.statementStart(802)
        posAbsolute = 0
Coverage.statementStart(803)
        posIndex = 0
Coverage.statementStart(804)
        posIndexLocal = 0
Coverage.statementStart(805)
        sizeAbsolute += count
Coverage.statementStart(806)
        var firstIndex = 0
Coverage.statementStart(807)
        var firstIndexLocal = 0
Coverage.statementStart(808)
        var lastIndex = 0
Coverage.statementStart(809)
        var lastIndexLocal = 0
Coverage.statementStart(810)
        var idx = first
Coverage.statementStart(811)
        var absoluteindex = 0
Coverage.statementStart(812)
        while (idx > 0) {
Coverage.whileLoopStart(813)
            val c = data[firstIndex].count
Coverage.statementStart(814)
            if (c == 0) {
Coverage.ifStart(815)
                firstIndexLocal = idx
Coverage.statementStart(816)
                break
            } else if (c == idx) {
Coverage.ifStart(817)
                firstIndexLocal = 0
Coverage.statementStart(818)
                absoluteindex = first
Coverage.statementStart(819)
                firstIndex++
Coverage.statementStart(820)
                break
            } else if (c > idx) {
Coverage.ifStart(821)
                firstIndexLocal = idx
Coverage.statementStart(822)
                break
            } else {
Coverage.ifStart(823)
                absoluteindex += data[firstIndex].count
Coverage.statementStart(824)
                idx -= c
Coverage.statementStart(825)
                firstIndex++
Coverage.statementStart(826)
            }
Coverage.statementStart(827)
        }
Coverage.statementStart(828)
        lastIndex = firstIndex
Coverage.statementStart(829)
        lastIndexLocal = firstIndexLocal
Coverage.statementStart(830)
        idx = last - first + 1//maximaler noch zu gehende index
Coverage.statementStart(831)
        var currentidx = first
Coverage.statementStart(832)
        while (true) {
Coverage.whileLoopStart(833)
            if (data[lastIndex].value == value) {
Coverage.ifStart(834)
                data[lastIndex].count += count
Coverage.statementStart(835)
                return Pair(absoluteindex, data[lastIndex].count)
            } else if (absoluteindex == last) {
Coverage.ifStart(836)
                var j = sizeIndex
Coverage.statementStart(837)
                while (j >= lastIndex) {
Coverage.whileLoopStart(838)
                    data[j + 1].count = data[j].count
Coverage.statementStart(839)
                    data[j + 1].value = data[j].value
Coverage.statementStart(840)
                    j--
Coverage.statementStart(841)
                }
Coverage.statementStart(842)
                data[lastIndex].value = value
Coverage.statementStart(843)
                data[lastIndex].count = count
Coverage.statementStart(844)
                sizeIndex++
Coverage.statementStart(845)
                return Pair(last, count)
            } else if (absoluteindex + data[lastIndex].count > last && comparator.compare(data[lastIndex].value, value) < 0) {
Coverage.ifStart(846)
                var j = sizeIndex
Coverage.statementStart(847)
                while (j >= lastIndex) {
Coverage.whileLoopStart(848)
                    data[j + 2].count = data[j].count
Coverage.statementStart(849)
                    data[j + 2].value = data[j].value
Coverage.statementStart(850)
                    j--
Coverage.statementStart(851)
                }
Coverage.statementStart(852)
                data[lastIndex + 1].value = value
Coverage.statementStart(853)
                data[lastIndex + 1].count = count
Coverage.statementStart(854)
                data[lastIndex].count = last - absoluteindex
Coverage.statementStart(855)
                data[lastIndex + 2].count -= last - absoluteindex
Coverage.statementStart(856)
                sizeIndex += 2
Coverage.statementStart(857)
                return Pair(last, count)
            } else if (lastIndex > sizeIndex) {
Coverage.ifStart(858)
                data[lastIndex].value = value
Coverage.statementStart(859)
                data[lastIndex].count = count
Coverage.statementStart(860)
                sizeIndex++
Coverage.statementStart(861)
                return Pair(absoluteindex, count)
            } else if (comparator.compare(data[lastIndex].value, value) < 0) {
Coverage.ifStart(862)
                val c = data[lastIndex].count - lastIndexLocal
Coverage.statementStart(863)
                currentidx += c
Coverage.statementStart(864)
                if (currentidx - last - 1 == data[lastIndex].count) {
Coverage.ifStart(865)
                    lastIndexLocal = idx
Coverage.statementStart(866)
                    var j = sizeIndex
Coverage.statementStart(867)
                    while (j >= lastIndex) {
Coverage.whileLoopStart(868)
                        data[j + 1].count = data[j].count
Coverage.statementStart(869)
                        data[j + 1].value = data[j].value
Coverage.statementStart(870)
                        j--
Coverage.statementStart(871)
                    }
Coverage.statementStart(872)
                    data[lastIndex].value = value
Coverage.statementStart(873)
                    data[lastIndex].count = count
Coverage.statementStart(874)
                    sizeIndex++
Coverage.statementStart(875)
                    return Pair(absoluteindex, count)
                } else if (c > idx) {
Coverage.ifStart(876)
                    lastIndexLocal = idx
Coverage.statementStart(877)
                    var j = sizeIndex
Coverage.statementStart(878)
                    while (j >= lastIndex) {
Coverage.whileLoopStart(879)
                        data[j + 2].count = data[j].count
Coverage.statementStart(880)
                        data[j + 2].value = data[j].value
Coverage.statementStart(881)
                        j--
Coverage.statementStart(882)
                    }
Coverage.statementStart(883)
                    data[lastIndex + 1].value = value
Coverage.statementStart(884)
                    data[lastIndex + 1].count = count
Coverage.statementStart(885)
                    data[lastIndex].count -= currentidx - last - 1
Coverage.statementStart(886)
                    data[lastIndex + 2].count = currentidx - last - 1
Coverage.statementStart(887)
                    sizeIndex += 2
Coverage.statementStart(888)
                    absoluteindex += data[lastIndex].count
Coverage.statementStart(889)
                    return Pair(absoluteindex, count)
                } else {
Coverage.ifStart(890)
                    idx -= c
Coverage.statementStart(891)
                    lastIndexLocal = 0
Coverage.statementStart(892)
                    absoluteindex += data[lastIndex].count
Coverage.statementStart(893)
                    lastIndex++
Coverage.statementStart(894)
                }
Coverage.statementStart(895)
            } else {
Coverage.ifStart(896)
                if (firstIndexLocal != 0) {
Coverage.ifStart(897)
                    var j = sizeIndex
Coverage.statementStart(898)
                    while (j >= lastIndex) {
Coverage.whileLoopStart(899)
                        data[j + 2].count = data[j].count
Coverage.statementStart(900)
                        data[j + 2].value = data[j].value
Coverage.statementStart(901)
                        j--
Coverage.statementStart(902)
                    }
Coverage.statementStart(903)
                    data[lastIndex + 1].value = value
Coverage.statementStart(904)
                    data[lastIndex + 1].count = count
Coverage.statementStart(905)
                    data[lastIndex].count = firstIndexLocal
Coverage.statementStart(906)
                    data[lastIndex + 2].count -= firstIndexLocal
Coverage.statementStart(907)
                    sizeIndex += 2
Coverage.statementStart(908)
                    absoluteindex += data[lastIndex].count
Coverage.statementStart(909)
                    return Pair(absoluteindex, count)
                } else {
Coverage.ifStart(910)
                    var j = sizeIndex
Coverage.statementStart(911)
                    while (j >= lastIndex) {
Coverage.whileLoopStart(912)
                        data[j + 1].count = data[j].count
Coverage.statementStart(913)
                        data[j + 1].value = data[j].value
Coverage.statementStart(914)
                        j--
Coverage.statementStart(915)
                    }
Coverage.statementStart(916)
                    data[lastIndex].value = value
Coverage.statementStart(917)
                    data[lastIndex].count = count
Coverage.statementStart(918)
                    sizeIndex++
Coverage.statementStart(919)
                    return Pair(absoluteindex, count)
                }
Coverage.statementStart(920)
            }
Coverage.statementStart(921)
        }
Coverage.statementStart(922)
        throw Exception("unreachable")
    }
}
