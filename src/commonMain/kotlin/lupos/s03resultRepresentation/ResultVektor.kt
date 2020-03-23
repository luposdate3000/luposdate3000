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
Coverage.funStart(625)
        val res = StringBuilder()
Coverage.statementStart(626)
        res.append("$capacity $posAbsolute $posIndex $posIndexLocal $posBackup $sizeAbsolute $sizeIndex\n")
Coverage.statementStart(627)
        for (i in 0 until capacity) {
Coverage.forLoopStart(628)
            res.append("${data[i].value}(${data[i].count})")
Coverage.statementStart(629)
            if (i == posIndex) {
Coverage.ifStart(630)
                res.append("-")
Coverage.statementStart(631)
            }
Coverage.statementStart(632)
            if (i == sizeIndex) {
Coverage.ifStart(633)
                res.append("+")
Coverage.statementStart(634)
            }
Coverage.statementStart(635)
            res.append(",")
Coverage.statementStart(636)
        }
Coverage.statementStart(637)
        return res.toString()
    }
    fun skipPos(count: Int) {
Coverage.funStart(638)
        require(posAbsolute + count <= sizeAbsolute)
Coverage.statementStart(639)
        posAbsolute += count
Coverage.statementStart(640)
        if (count > 0) {
Coverage.ifStart(641)
            var i = count
Coverage.statementStart(642)
            while (true) {
Coverage.whileLoopStart(643)
                val c = data[posIndex].count - posIndexLocal
Coverage.statementStart(644)
                if (c < i) {
Coverage.ifStart(645)
                    internalNextElement()
Coverage.statementStart(646)
                    i -= c
Coverage.statementStart(647)
                } else {
Coverage.ifStart(648)
                    posIndexLocal += i
Coverage.statementStart(649)
                    break
                }
Coverage.statementStart(650)
            }
Coverage.statementStart(651)
        } else {
Coverage.ifStart(652)
            var i = -count
Coverage.statementStart(653)
            while (true) {
Coverage.whileLoopStart(654)
                val c = posIndexLocal
Coverage.statementStart(655)
                if (c < i) {
Coverage.ifStart(656)
                    posIndex--
Coverage.statementStart(657)
                    posIndexLocal = data[posIndex].count
Coverage.statementStart(658)
                    i -= c
Coverage.statementStart(659)
                } else {
Coverage.ifStart(660)
                    posIndexLocal -= i
Coverage.statementStart(661)
                    break
                }
Coverage.statementStart(662)
            }
Coverage.statementStart(663)
        }
Coverage.statementStart(664)
    }
    fun skipSize(count: Int) {
Coverage.funStart(665)
        require(posAbsolute <= sizeAbsolute + count)
Coverage.statementStart(666)
        sizeAbsolute += count
Coverage.statementStart(667)
        if (count >= 0) {
Coverage.ifStart(668)
            data[sizeIndex].count += count
Coverage.statementStart(669)
        } else {
Coverage.ifStart(670)
            var i = -count
Coverage.statementStart(671)
            while (true) {
Coverage.whileLoopStart(672)
                val c = data[sizeIndex].count
Coverage.statementStart(673)
                if (sizeIndex == 0) {
Coverage.ifStart(674)
                    data[sizeIndex].count -= i
Coverage.statementStart(675)
                    break
                } else if (c < i) {
Coverage.ifStart(676)
                    sizeIndex--
Coverage.statementStart(677)
                    i -= c
Coverage.statementStart(678)
                } else if (c == i) {
Coverage.ifStart(679)
                    sizeIndex--
Coverage.statementStart(680)
                    break
                } else {
Coverage.ifStart(681)
                    data[sizeIndex].count -= i
Coverage.statementStart(682)
                    break
                }
Coverage.statementStart(683)
            }
Coverage.statementStart(684)
        }
Coverage.statementStart(685)
    }
    fun backupPosition() {
Coverage.funStart(686)
        posBackup = posAbsolute
Coverage.statementStart(687)
    }
    fun restorePosition() {
Coverage.funStart(688)
        require(posBackup <= sizeAbsolute)
Coverage.statementStart(689)
        posAbsolute = 0
Coverage.statementStart(690)
        posIndex = 0
Coverage.statementStart(691)
        posIndexLocal = 0
Coverage.statementStart(692)
        skipPos(posBackup)
Coverage.statementStart(693)
    }
    fun current(): Value {
Coverage.funStart(694)
        internalSafeNextElement()
Coverage.statementStart(695)
        return data[posIndex].value
    }
    override fun next(): Value {
Coverage.funStart(696)
        internalSafeNextElement()
Coverage.statementStart(697)
        posIndexLocal++
Coverage.statementStart(698)
        posAbsolute++
Coverage.statementStart(699)
        return data[posIndex].value
    }
    override fun hasNext() = sizeAbsolute > posAbsolute
    fun availableWrite() = capacity - sizeIndex - 1
    fun availableRead() = sizeAbsolute - posAbsolute
    fun canAppend() = availableWrite() > 0
    fun append(value: Value, count: Int = 1) {
Coverage.funStart(700)
        require(sizeIndex < capacity - 1 && count > 0)
Coverage.statementStart(701)
        if (sizeAbsolute == 0) {
Coverage.ifStart(702)
            data[sizeIndex].count = count
Coverage.statementStart(703)
            data[sizeIndex].value = value
Coverage.statementStart(704)
        } else if (data[sizeIndex].value == value) {
Coverage.ifStart(705)
            data[sizeIndex].count += count
Coverage.statementStart(706)
        } else {
Coverage.ifStart(707)
            sizeIndex++
Coverage.statementStart(708)
            data[sizeIndex].count = count
Coverage.statementStart(709)
            data[sizeIndex].value = value
Coverage.statementStart(710)
        }
Coverage.statementStart(711)
        sizeAbsolute += count
Coverage.statementStart(712)
    }
    fun sameElements(): Int {
Coverage.funStart(713)
        internalSafeNextElement()
Coverage.statementStart(714)
        require(posIndex <= sizeIndex)
Coverage.statementStart(715)
        return data[posIndex].count - posIndexLocal
    }
    fun internalNextElement() {
Coverage.funStart(716)
        posIndex++
Coverage.statementStart(717)
        posIndexLocal = 0
Coverage.statementStart(718)
    }
    fun internalSafeNextElement() {
Coverage.funStart(719)
        if (posIndexLocal == data[posIndex].count && posIndex < sizeIndex) {
Coverage.ifStart(720)
            internalNextElement()
Coverage.statementStart(721)
        }
Coverage.statementStart(722)
    }
    fun copy(from: ResultVektor, count: Int) {
Coverage.funStart(723)
        require(count > 0)
Coverage.statementStart(724)
        var i = count
Coverage.statementStart(725)
        from.internalSafeNextElement()
Coverage.statementStart(726)
        while (true) {
Coverage.whileLoopStart(727)
            val c = from.data[from.posIndex].count - from.posIndexLocal
Coverage.statementStart(728)
            if (c < i) {
Coverage.ifStart(729)
                append(from.data[from.posIndex].value, c)
Coverage.statementStart(730)
                from.internalNextElement()
Coverage.statementStart(731)
                i -= c
Coverage.statementStart(732)
            } else {
Coverage.ifStart(733)
                append(from.data[from.posIndex].value, i)
Coverage.statementStart(734)
                from.posIndexLocal += i
Coverage.statementStart(735)
                break
            }
Coverage.statementStart(736)
        }
Coverage.statementStart(737)
        from.posAbsolute += count
Coverage.statementStart(738)
    }
    fun insertSorted(value: Value, first: Int = posAbsolute, last: Int = sizeAbsolute, comparator: Comparator<Value>, count: Int): Pair<Int, Int> {
Coverage.funStart(739)
        if (sizeAbsolute == 0) {
Coverage.ifStart(740)
            append(value, count)
Coverage.statementStart(741)
            return Pair(0, count)
        }
Coverage.statementStart(742)
        require(availableWrite() >= 2)
Coverage.statementStart(743)
        require(count > 0)
Coverage.statementStart(744)
        posAbsolute = 0
Coverage.statementStart(745)
        posIndex = 0
Coverage.statementStart(746)
        posIndexLocal = 0
Coverage.statementStart(747)
        sizeAbsolute += count
Coverage.statementStart(748)
        var index = 0
Coverage.statementStart(749)
        var indexLocal = 0
Coverage.statementStart(750)
        var idx = first
Coverage.statementStart(751)
        var absoluteindex = 0
Coverage.statementStart(752)
        while (idx > 0) {
Coverage.whileLoopStart(753)
            val c = data[index].count
Coverage.statementStart(754)
            if (c == idx) {
Coverage.ifStart(755)
                indexLocal = 0
Coverage.statementStart(756)
                absoluteindex = first
Coverage.statementStart(757)
                index++
Coverage.statementStart(758)
                break
            } else if (c > idx) {
Coverage.ifStart(759)
                indexLocal = idx
Coverage.statementStart(760)
                break
            } else {
Coverage.ifStart(761)
                absoluteindex += data[index].count
Coverage.statementStart(762)
                idx -= c
Coverage.statementStart(763)
                index++
Coverage.statementStart(764)
            }
Coverage.statementStart(765)
        }
Coverage.statementStart(766)
        idx = last - first + 1
Coverage.statementStart(767)
        var currentidx = first
Coverage.statementStart(768)
        while (true) {
Coverage.whileLoopStart(769)
            if (data[index].value == value) {
Coverage.ifStart(770)
                data[index].count += count
Coverage.statementStart(771)
                return Pair(absoluteindex, data[index].count)
            } else if (absoluteindex == last) {
Coverage.ifStart(772)
                var j = sizeIndex
Coverage.statementStart(773)
                while (j >= index) {
Coverage.whileLoopStart(774)
                    data[j + 1].count = data[j].count
Coverage.statementStart(775)
                    data[j + 1].value = data[j].value
Coverage.statementStart(776)
                    j--
Coverage.statementStart(777)
                }
Coverage.statementStart(778)
                data[index].value = value
Coverage.statementStart(779)
                data[index].count = count
Coverage.statementStart(780)
                sizeIndex++
Coverage.statementStart(781)
                return Pair(last, count)
            } else if (absoluteindex + data[index].count > last && comparator.compare(data[index].value, value) < 0) {
Coverage.ifStart(782)
                var j = sizeIndex
Coverage.statementStart(783)
                while (j >= index) {
Coverage.whileLoopStart(784)
                    data[j + 2].count = data[j].count
Coverage.statementStart(785)
                    data[j + 2].value = data[j].value
Coverage.statementStart(786)
                    j--
Coverage.statementStart(787)
                }
Coverage.statementStart(788)
                data[index + 1].value = value
Coverage.statementStart(789)
                data[index + 1].count = count
Coverage.statementStart(790)
                data[index].count = last - absoluteindex
Coverage.statementStart(791)
                data[index + 2].count -= last - absoluteindex
Coverage.statementStart(792)
                sizeIndex += 2
Coverage.statementStart(793)
                return Pair(last, count)
            } else if (comparator.compare(data[index].value, value) < 0) {
Coverage.ifStart(794)
                val c = data[index].count - indexLocal
Coverage.statementStart(795)
                currentidx += c
Coverage.statementStart(796)
                idx -= c
Coverage.statementStart(797)
                indexLocal = 0
Coverage.statementStart(798)
                absoluteindex += data[index].count
Coverage.statementStart(799)
                index++
Coverage.statementStart(800)
            } else if (indexLocal != 0) {
Coverage.ifStart(801)
                var j = sizeIndex
Coverage.statementStart(802)
                while (j >= index) {
Coverage.whileLoopStart(803)
                    data[j + 2].count = data[j].count
Coverage.statementStart(804)
                    data[j + 2].value = data[j].value
Coverage.statementStart(805)
                    j--
Coverage.statementStart(806)
                }
Coverage.statementStart(807)
                data[index + 1].value = value
Coverage.statementStart(808)
                data[index + 1].count = count
Coverage.statementStart(809)
                data[index].count = indexLocal
Coverage.statementStart(810)
                data[index + 2].count -= indexLocal
Coverage.statementStart(811)
                sizeIndex += 2
Coverage.statementStart(812)
                absoluteindex += data[index].count
Coverage.statementStart(813)
                return Pair(absoluteindex, count)
            } else {
Coverage.ifStart(814)
                var j = sizeIndex
Coverage.statementStart(815)
                while (j >= index) {
Coverage.whileLoopStart(816)
                    data[j + 1].count = data[j].count
Coverage.statementStart(817)
                    data[j + 1].value = data[j].value
Coverage.statementStart(818)
                    j--
Coverage.statementStart(819)
                }
Coverage.statementStart(820)
                data[index].value = value
Coverage.statementStart(821)
                data[index].count = count
Coverage.statementStart(822)
                sizeIndex++
Coverage.statementStart(823)
                return Pair(absoluteindex, count)
            }
Coverage.statementStart(824)
        }
/*Coverage Unreachable*/
    }
}
