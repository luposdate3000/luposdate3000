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
Coverage.funStart(645)
        val res = StringBuilder()
Coverage.statementStart(646)
        res.append("$capacity $posAbsolute $posIndex $posIndexLocal $posBackup $sizeAbsolute $sizeIndex\n")
Coverage.statementStart(647)
        for (i in 0 until capacity) {
Coverage.forLoopStart(648)
            res.append("${data[i].value}(${data[i].count})")
Coverage.statementStart(649)
            if (i == posIndex) {
Coverage.ifStart(650)
                res.append("-")
Coverage.statementStart(651)
            }
Coverage.statementStart(652)
            if (i == sizeIndex) {
Coverage.ifStart(653)
                res.append("+")
Coverage.statementStart(654)
            }
Coverage.statementStart(655)
            res.append(",")
Coverage.statementStart(656)
        }
Coverage.statementStart(657)
        return res.toString()
    }
    fun skipPos(count: Int) {
Coverage.funStart(658)
        require(posAbsolute + count <= sizeAbsolute)
Coverage.statementStart(659)
        posAbsolute += count
Coverage.statementStart(660)
        if (count > 0) {
Coverage.ifStart(661)
            var i = count
Coverage.statementStart(662)
            while (true) {
Coverage.whileLoopStart(663)
                val c = data[posIndex].count - posIndexLocal
Coverage.statementStart(664)
                if (c < i) {
Coverage.ifStart(665)
                    internalNextElement()
Coverage.statementStart(666)
                    i -= c
Coverage.statementStart(667)
                } else {
Coverage.ifStart(668)
                    posIndexLocal += i
Coverage.statementStart(669)
                    break
                }
Coverage.statementStart(670)
            }
Coverage.statementStart(671)
        } else {
Coverage.ifStart(672)
            var i = -count
Coverage.statementStart(673)
            while (true) {
Coverage.whileLoopStart(674)
                val c = posIndexLocal
Coverage.statementStart(675)
                if (c < i) {
Coverage.ifStart(676)
                    posIndex--
Coverage.statementStart(677)
                    posIndexLocal = data[posIndex].count
Coverage.statementStart(678)
                    i -= c
Coverage.statementStart(679)
                } else {
Coverage.ifStart(680)
                    posIndexLocal -= i
Coverage.statementStart(681)
                    break
                }
Coverage.statementStart(682)
            }
Coverage.statementStart(683)
        }
Coverage.statementStart(684)
    }
    fun skipSize(count: Int) {
Coverage.funStart(685)
        require(posAbsolute <= sizeAbsolute + count)
Coverage.statementStart(686)
        sizeAbsolute += count
Coverage.statementStart(687)
        if (count >= 0) {
Coverage.ifStart(688)
            data[sizeIndex].count += count
Coverage.statementStart(689)
        } else {
Coverage.ifStart(690)
            var i = -count
Coverage.statementStart(691)
            while (true) {
Coverage.whileLoopStart(692)
                val c = data[sizeIndex].count
Coverage.statementStart(693)
                if (sizeIndex == 0) {
Coverage.ifStart(694)
                    data[sizeIndex].count -= i
Coverage.statementStart(695)
                    break
                } else if (c < i) {
Coverage.ifStart(696)
                    sizeIndex--
Coverage.statementStart(697)
                    i -= c
Coverage.statementStart(698)
                } else if (c == i) {
Coverage.ifStart(699)
                    sizeIndex--
Coverage.statementStart(700)
                    break
                } else {
Coverage.ifStart(701)
                    data[sizeIndex].count -= i
Coverage.statementStart(702)
                    break
                }
Coverage.statementStart(703)
            }
Coverage.statementStart(704)
        }
Coverage.statementStart(705)
    }
    fun backupPosition() {
Coverage.funStart(706)
        posBackup = posAbsolute
Coverage.statementStart(707)
    }
    fun restorePosition() {
Coverage.funStart(708)
        require(posBackup <= sizeAbsolute)
Coverage.statementStart(709)
        posAbsolute = 0
Coverage.statementStart(710)
        posIndex = 0
Coverage.statementStart(711)
        posIndexLocal = 0
Coverage.statementStart(712)
        skipPos(posBackup)
Coverage.statementStart(713)
    }
    fun current(): Value {
Coverage.funStart(714)
        internalSafeNextElement()
Coverage.statementStart(715)
        return data[posIndex].value
    }
    override fun next(): Value {
Coverage.funStart(716)
        internalSafeNextElement()
Coverage.statementStart(717)
        posIndexLocal++
Coverage.statementStart(718)
        posAbsolute++
Coverage.statementStart(719)
        return data[posIndex].value
    }
    override fun hasNext() = sizeAbsolute > posAbsolute
    fun availableWrite() = capacity - sizeIndex - 1
    fun availableRead() = sizeAbsolute - posAbsolute
    fun canAppend() = availableWrite() > 0
    fun append(value: Value, count: Int = 1) {
Coverage.funStart(720)
        require(sizeIndex < capacity - 1 && count > 0)
Coverage.statementStart(721)
        if (sizeAbsolute == 0) {
Coverage.ifStart(722)
            data[sizeIndex].count = count
Coverage.statementStart(723)
            data[sizeIndex].value = value
Coverage.statementStart(724)
        } else if (data[sizeIndex].value == value) {
Coverage.ifStart(725)
            data[sizeIndex].count += count
Coverage.statementStart(726)
        } else {
Coverage.ifStart(727)
            sizeIndex++
Coverage.statementStart(728)
            data[sizeIndex].count = count
Coverage.statementStart(729)
            data[sizeIndex].value = value
Coverage.statementStart(730)
        }
Coverage.statementStart(731)
        sizeAbsolute += count
Coverage.statementStart(732)
    }
    fun sameElements(): Int {
Coverage.funStart(733)
        internalSafeNextElement()
Coverage.statementStart(734)
        require(posIndex <= sizeIndex)
Coverage.statementStart(735)
        return data[posIndex].count - posIndexLocal
    }
    fun internalNextElement() {
Coverage.funStart(736)
        posIndex++
Coverage.statementStart(737)
        posIndexLocal = 0
Coverage.statementStart(738)
    }
    fun internalSafeNextElement() {
Coverage.funStart(739)
        if (posIndexLocal == data[posIndex].count && posIndex < sizeIndex) {
Coverage.ifStart(740)
            internalNextElement()
Coverage.statementStart(741)
        }
Coverage.statementStart(742)
    }
    fun copy(from: ResultVektor, count: Int) {
Coverage.funStart(743)
        require(count > 0)
Coverage.statementStart(744)
        var i = count
Coverage.statementStart(745)
        from.internalSafeNextElement()
Coverage.statementStart(746)
        while (true) {
Coverage.whileLoopStart(747)
            val c = from.data[from.posIndex].count - from.posIndexLocal
Coverage.statementStart(748)
            if (c < i) {
Coverage.ifStart(749)
                append(from.data[from.posIndex].value, c)
Coverage.statementStart(750)
                from.internalNextElement()
Coverage.statementStart(751)
                i -= c
Coverage.statementStart(752)
            } else {
Coverage.ifStart(753)
                append(from.data[from.posIndex].value, i)
Coverage.statementStart(754)
                from.posIndexLocal += i
Coverage.statementStart(755)
                break
            }
Coverage.statementStart(756)
        }
Coverage.statementStart(757)
        from.posAbsolute += count
Coverage.statementStart(758)
    }
    fun insertSorted(value: Value, first: Int = posAbsolute, last: Int = sizeAbsolute, comparator: Comparator<Value>, count: Int): Pair<Int, Int> {
Coverage.funStart(759)
        if (sizeAbsolute == 0) {
Coverage.ifStart(760)
            append(value, count)
Coverage.statementStart(761)
            return Pair(0, count)
        }
Coverage.statementStart(762)
        require(availableWrite() >= 2)
Coverage.statementStart(763)
        require(count > 0)
Coverage.statementStart(764)
        posAbsolute = 0
Coverage.statementStart(765)
        posIndex = 0
Coverage.statementStart(766)
        posIndexLocal = 0
Coverage.statementStart(767)
        sizeAbsolute += count
Coverage.statementStart(768)
        var index = 0
Coverage.statementStart(769)
        var indexLocal = 0
Coverage.statementStart(770)
        var idx = first
Coverage.statementStart(771)
        var absoluteindex = 0
Coverage.statementStart(772)
        while (idx > 0) {
Coverage.whileLoopStart(773)
            val c = data[index].count
Coverage.statementStart(774)
            if (c == idx) {
Coverage.ifStart(775)
                indexLocal = 0
Coverage.statementStart(776)
                absoluteindex = first
Coverage.statementStart(777)
                index++
Coverage.statementStart(778)
                break
            } else if (c > idx) {
Coverage.ifStart(779)
                indexLocal = idx
Coverage.statementStart(780)
                break
            } else {
Coverage.ifStart(781)
                absoluteindex += data[index].count
Coverage.statementStart(782)
                idx -= c
Coverage.statementStart(783)
                index++
Coverage.statementStart(784)
            }
Coverage.statementStart(785)
        }
Coverage.statementStart(786)
        idx = last - first + 1
Coverage.statementStart(787)
        var currentidx = first
Coverage.statementStart(788)
        while (true) {
Coverage.whileLoopStart(789)
            if (data[index].value == value) {
Coverage.ifStart(790)
                data[index].count += count
Coverage.statementStart(791)
                return Pair(absoluteindex, data[index].count)
            } else if (absoluteindex == last) {
Coverage.ifStart(792)
                var j = sizeIndex
Coverage.statementStart(793)
                while (j >= index) {
Coverage.whileLoopStart(794)
                    data[j + 1].count = data[j].count
Coverage.statementStart(795)
                    data[j + 1].value = data[j].value
Coverage.statementStart(796)
                    j--
Coverage.statementStart(797)
                }
Coverage.statementStart(798)
                data[index].value = value
Coverage.statementStart(799)
                data[index].count = count
Coverage.statementStart(800)
                sizeIndex++
Coverage.statementStart(801)
                return Pair(last, count)
            } else if (absoluteindex + data[index].count > last && comparator.compare(data[index].value, value) < 0) {
Coverage.ifStart(802)
                var j = sizeIndex
Coverage.statementStart(803)
                while (j >= index) {
Coverage.whileLoopStart(804)
                    data[j + 2].count = data[j].count
Coverage.statementStart(805)
                    data[j + 2].value = data[j].value
Coverage.statementStart(806)
                    j--
Coverage.statementStart(807)
                }
Coverage.statementStart(808)
                data[index + 1].value = value
Coverage.statementStart(809)
                data[index + 1].count = count
Coverage.statementStart(810)
                data[index].count = last - absoluteindex
Coverage.statementStart(811)
                data[index + 2].count -= last - absoluteindex
Coverage.statementStart(812)
                sizeIndex += 2
Coverage.statementStart(813)
                return Pair(last, count)
            } else if (comparator.compare(data[index].value, value) < 0) {
Coverage.ifStart(814)
                val c = data[index].count - indexLocal
Coverage.statementStart(815)
                currentidx += c
Coverage.statementStart(816)
                idx -= c
Coverage.statementStart(817)
                indexLocal = 0
Coverage.statementStart(818)
                absoluteindex += data[index].count
Coverage.statementStart(819)
                index++
Coverage.statementStart(820)
            } else if (indexLocal != 0) {
Coverage.ifStart(821)
                var j = sizeIndex
Coverage.statementStart(822)
                while (j >= index) {
Coverage.whileLoopStart(823)
                    data[j + 2].count = data[j].count
Coverage.statementStart(824)
                    data[j + 2].value = data[j].value
Coverage.statementStart(825)
                    j--
Coverage.statementStart(826)
                }
Coverage.statementStart(827)
                data[index + 1].value = value
Coverage.statementStart(828)
                data[index + 1].count = count
Coverage.statementStart(829)
                data[index].count = indexLocal
Coverage.statementStart(830)
                data[index + 2].count -= indexLocal
Coverage.statementStart(831)
                sizeIndex += 2
Coverage.statementStart(832)
                absoluteindex += data[index].count
Coverage.statementStart(833)
                return Pair(absoluteindex, count)
            } else {
Coverage.ifStart(834)
                var j = sizeIndex
Coverage.statementStart(835)
                while (j >= index) {
Coverage.whileLoopStart(836)
                    data[j + 1].count = data[j].count
Coverage.statementStart(837)
                    data[j + 1].value = data[j].value
Coverage.statementStart(838)
                    j--
Coverage.statementStart(839)
                }
Coverage.statementStart(840)
                data[index].value = value
Coverage.statementStart(841)
                data[index].count = count
Coverage.statementStart(842)
                sizeIndex++
Coverage.statementStart(843)
                return Pair(absoluteindex, count)
            }
Coverage.statementStart(844)
        }
Coverage.statementStart(845)
        throw Exception("unreachable")
    }
}
