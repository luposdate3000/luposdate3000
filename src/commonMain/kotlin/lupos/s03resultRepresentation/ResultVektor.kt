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
Coverage.funStart(635)
        val res = StringBuilder()
Coverage.statementStart(636)
        res.append("$capacity $posAbsolute $posIndex $posIndexLocal $posBackup $sizeAbsolute $sizeIndex\n")
Coverage.statementStart(637)
        for (i in 0 until capacity) {
Coverage.forLoopStart(638)
            res.append("${data[i].value}(${data[i].count})")
Coverage.statementStart(639)
            if (i == posIndex) {
Coverage.ifStart(640)
                res.append("-")
Coverage.statementStart(641)
            }
Coverage.statementStart(642)
            if (i == sizeIndex) {
Coverage.ifStart(643)
                res.append("+")
Coverage.statementStart(644)
            }
Coverage.statementStart(645)
            res.append(",")
Coverage.statementStart(646)
        }
Coverage.statementStart(647)
        return res.toString()
    }
    fun skipPos(count: Int) {
Coverage.funStart(648)
        require(posAbsolute + count <= sizeAbsolute)
Coverage.statementStart(649)
        posAbsolute += count
Coverage.statementStart(650)
        if (count > 0) {
Coverage.ifStart(651)
            var i = count
Coverage.statementStart(652)
            while (true) {
Coverage.whileLoopStart(653)
                val c = data[posIndex].count - posIndexLocal
Coverage.statementStart(654)
                if (c < i) {
Coverage.ifStart(655)
                    internalNextElement()
Coverage.statementStart(656)
                    i -= c
Coverage.statementStart(657)
                } else {
Coverage.ifStart(658)
                    posIndexLocal += i
Coverage.statementStart(659)
                    break
                }
Coverage.statementStart(660)
            }
Coverage.statementStart(661)
        } else {
Coverage.ifStart(662)
            var i = -count
Coverage.statementStart(663)
            while (true) {
Coverage.whileLoopStart(664)
                val c = posIndexLocal
Coverage.statementStart(665)
                if (c < i) {
Coverage.ifStart(666)
                    posIndex--
Coverage.statementStart(667)
                    posIndexLocal = data[posIndex].count
Coverage.statementStart(668)
                    i -= c
Coverage.statementStart(669)
                } else {
Coverage.ifStart(670)
                    posIndexLocal -= i
Coverage.statementStart(671)
                    break
                }
Coverage.statementStart(672)
            }
Coverage.statementStart(673)
        }
Coverage.statementStart(674)
    }
    fun skipSize(count: Int) {
Coverage.funStart(675)
        require(posAbsolute <= sizeAbsolute + count)
Coverage.statementStart(676)
        sizeAbsolute += count
Coverage.statementStart(677)
        if (count >= 0) {
Coverage.ifStart(678)
            data[sizeIndex].count += count
Coverage.statementStart(679)
        } else {
Coverage.ifStart(680)
            var i = -count
Coverage.statementStart(681)
            while (true) {
Coverage.whileLoopStart(682)
                val c = data[sizeIndex].count
Coverage.statementStart(683)
                if (sizeIndex == 0) {
Coverage.ifStart(684)
                    data[sizeIndex].count -= i
Coverage.statementStart(685)
                    break
                } else if (c < i) {
Coverage.ifStart(686)
                    sizeIndex--
Coverage.statementStart(687)
                    i -= c
Coverage.statementStart(688)
                } else if (c == i) {
Coverage.ifStart(689)
                    sizeIndex--
Coverage.statementStart(690)
                    break
                } else {
Coverage.ifStart(691)
                    data[sizeIndex].count -= i
Coverage.statementStart(692)
                    break
                }
Coverage.statementStart(693)
            }
Coverage.statementStart(694)
        }
Coverage.statementStart(695)
    }
    fun backupPosition() {
Coverage.funStart(696)
        posBackup = posAbsolute
Coverage.statementStart(697)
    }
    fun restorePosition() {
Coverage.funStart(698)
        require(posBackup <= sizeAbsolute)
Coverage.statementStart(699)
        posAbsolute = 0
Coverage.statementStart(700)
        posIndex = 0
Coverage.statementStart(701)
        posIndexLocal = 0
Coverage.statementStart(702)
        skipPos(posBackup)
Coverage.statementStart(703)
    }
    fun current(): Value {
Coverage.funStart(704)
        internalSafeNextElement()
Coverage.statementStart(705)
        return data[posIndex].value
    }
    override fun next(): Value {
Coverage.funStart(706)
        internalSafeNextElement()
Coverage.statementStart(707)
        posIndexLocal++
Coverage.statementStart(708)
        posAbsolute++
Coverage.statementStart(709)
        return data[posIndex].value
    }
    override fun hasNext() = sizeAbsolute > posAbsolute
    fun availableWrite() = capacity - sizeIndex - 1
    fun availableRead() = sizeAbsolute - posAbsolute
    fun canAppend() = availableWrite() > 0
    fun append(value: Value, count: Int = 1) {
Coverage.funStart(710)
        require(sizeIndex < capacity - 1 && count > 0)
Coverage.statementStart(711)
        if (sizeAbsolute == 0) {
Coverage.ifStart(712)
            data[sizeIndex].count = count
Coverage.statementStart(713)
            data[sizeIndex].value = value
Coverage.statementStart(714)
        } else if (data[sizeIndex].value == value) {
Coverage.ifStart(715)
            data[sizeIndex].count += count
Coverage.statementStart(716)
        } else {
Coverage.ifStart(717)
            sizeIndex++
Coverage.statementStart(718)
            data[sizeIndex].count = count
Coverage.statementStart(719)
            data[sizeIndex].value = value
Coverage.statementStart(720)
        }
Coverage.statementStart(721)
        sizeAbsolute += count
Coverage.statementStart(722)
    }
    fun sameElements(): Int {
Coverage.funStart(723)
        internalSafeNextElement()
Coverage.statementStart(724)
        require(posIndex <= sizeIndex)
Coverage.statementStart(725)
        return data[posIndex].count - posIndexLocal
    }
    fun internalNextElement() {
Coverage.funStart(726)
        posIndex++
Coverage.statementStart(727)
        posIndexLocal = 0
Coverage.statementStart(728)
    }
    fun internalSafeNextElement() {
Coverage.funStart(729)
        if (posIndexLocal == data[posIndex].count && posIndex < sizeIndex) {
Coverage.ifStart(730)
            internalNextElement()
Coverage.statementStart(731)
        }
Coverage.statementStart(732)
    }
    fun copy(from: ResultVektor, count: Int) {
Coverage.funStart(733)
        require(count > 0)
Coverage.statementStart(734)
        var i = count
Coverage.statementStart(735)
        from.internalSafeNextElement()
Coverage.statementStart(736)
        while (true) {
Coverage.whileLoopStart(737)
            val c = from.data[from.posIndex].count - from.posIndexLocal
Coverage.statementStart(738)
            if (c < i) {
Coverage.ifStart(739)
                append(from.data[from.posIndex].value, c)
Coverage.statementStart(740)
                from.internalNextElement()
Coverage.statementStart(741)
                i -= c
Coverage.statementStart(742)
            } else {
Coverage.ifStart(743)
                append(from.data[from.posIndex].value, i)
Coverage.statementStart(744)
                from.posIndexLocal += i
Coverage.statementStart(745)
                break
            }
Coverage.statementStart(746)
        }
Coverage.statementStart(747)
        from.posAbsolute += count
Coverage.statementStart(748)
    }
    fun insertSorted(value: Value, first: Int = posAbsolute, last: Int = sizeAbsolute, comparator: Comparator<Value>, count: Int): Pair<Int, Int> {
Coverage.funStart(749)
        if (sizeAbsolute == 0) {
Coverage.ifStart(750)
            append(value, count)
Coverage.statementStart(751)
            return Pair(0, count)
        }
Coverage.statementStart(752)
        require(availableWrite() >= 2)
Coverage.statementStart(753)
        require(count > 0)
Coverage.statementStart(754)
        posAbsolute = 0
Coverage.statementStart(755)
        posIndex = 0
Coverage.statementStart(756)
        posIndexLocal = 0
Coverage.statementStart(757)
        sizeAbsolute += count
Coverage.statementStart(758)
        var index = 0
Coverage.statementStart(759)
        var indexLocal = 0
Coverage.statementStart(760)
        var idx = first
Coverage.statementStart(761)
        var absoluteindex = 0
Coverage.statementStart(762)
        while (idx > 0) {
Coverage.whileLoopStart(763)
            val c = data[index].count
Coverage.statementStart(764)
            if (c == idx) {
Coverage.ifStart(765)
                indexLocal = 0
Coverage.statementStart(766)
                absoluteindex = first
Coverage.statementStart(767)
                index++
Coverage.statementStart(768)
                break
            } else if (c > idx) {
Coverage.ifStart(769)
                indexLocal = idx
Coverage.statementStart(770)
                break
            } else {
Coverage.ifStart(771)
                absoluteindex += data[index].count
Coverage.statementStart(772)
                idx -= c
Coverage.statementStart(773)
                index++
Coverage.statementStart(774)
            }
Coverage.statementStart(775)
        }
Coverage.statementStart(776)
        idx = last - first + 1
Coverage.statementStart(777)
        var currentidx = first
Coverage.statementStart(778)
        while (true) {
Coverage.whileLoopStart(779)
            if (data[index].value == value) {
Coverage.ifStart(780)
                data[index].count += count
Coverage.statementStart(781)
                return Pair(absoluteindex, data[index].count)
            } else if (absoluteindex == last) {
Coverage.ifStart(782)
                var j = sizeIndex
Coverage.statementStart(783)
                while (j >= index) {
Coverage.whileLoopStart(784)
                    data[j + 1].count = data[j].count
Coverage.statementStart(785)
                    data[j + 1].value = data[j].value
Coverage.statementStart(786)
                    j--
Coverage.statementStart(787)
                }
Coverage.statementStart(788)
                data[index].value = value
Coverage.statementStart(789)
                data[index].count = count
Coverage.statementStart(790)
                sizeIndex++
Coverage.statementStart(791)
                return Pair(last, count)
            } else if (absoluteindex + data[index].count > last && comparator.compare(data[index].value, value) < 0) {
Coverage.ifStart(792)
                var j = sizeIndex
Coverage.statementStart(793)
                while (j >= index) {
Coverage.whileLoopStart(794)
                    data[j + 2].count = data[j].count
Coverage.statementStart(795)
                    data[j + 2].value = data[j].value
Coverage.statementStart(796)
                    j--
Coverage.statementStart(797)
                }
Coverage.statementStart(798)
                data[index + 1].value = value
Coverage.statementStart(799)
                data[index + 1].count = count
Coverage.statementStart(800)
                data[index].count = last - absoluteindex
Coverage.statementStart(801)
                data[index + 2].count -= last - absoluteindex
Coverage.statementStart(802)
                sizeIndex += 2
Coverage.statementStart(803)
                return Pair(last, count)
            } else if (comparator.compare(data[index].value, value) < 0) {
Coverage.ifStart(804)
                val c = data[index].count - indexLocal
Coverage.statementStart(805)
                currentidx += c
Coverage.statementStart(806)
                idx -= c
Coverage.statementStart(807)
                indexLocal = 0
Coverage.statementStart(808)
                absoluteindex += data[index].count
Coverage.statementStart(809)
                index++
Coverage.statementStart(810)
            } else if (indexLocal != 0) {
Coverage.ifStart(811)
                var j = sizeIndex
Coverage.statementStart(812)
                while (j >= index) {
Coverage.whileLoopStart(813)
                    data[j + 2].count = data[j].count
Coverage.statementStart(814)
                    data[j + 2].value = data[j].value
Coverage.statementStart(815)
                    j--
Coverage.statementStart(816)
                }
Coverage.statementStart(817)
                data[index + 1].value = value
Coverage.statementStart(818)
                data[index + 1].count = count
Coverage.statementStart(819)
                data[index].count = indexLocal
Coverage.statementStart(820)
                data[index + 2].count -= indexLocal
Coverage.statementStart(821)
                sizeIndex += 2
Coverage.statementStart(822)
                absoluteindex += data[index].count
Coverage.statementStart(823)
                return Pair(absoluteindex, count)
            } else {
Coverage.ifStart(824)
                var j = sizeIndex
Coverage.statementStart(825)
                while (j >= index) {
Coverage.whileLoopStart(826)
                    data[j + 1].count = data[j].count
Coverage.statementStart(827)
                    data[j + 1].value = data[j].value
Coverage.statementStart(828)
                    j--
Coverage.statementStart(829)
                }
Coverage.statementStart(830)
                data[index].value = value
Coverage.statementStart(831)
                data[index].count = count
Coverage.statementStart(832)
                sizeIndex++
Coverage.statementStart(833)
                return Pair(absoluteindex, count)
            }
Coverage.statementStart(834)
        }
/*Coverage Unreachable*/
    }
}
