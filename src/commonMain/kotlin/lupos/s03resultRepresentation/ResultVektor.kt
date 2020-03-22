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
Coverage.funStart(624)
        val res = StringBuilder()
Coverage.statementStart(625)
        res.append("$capacity $posAbsolute $posIndex $posIndexLocal $posBackup $sizeAbsolute $sizeIndex\n")
Coverage.statementStart(626)
        for (i in 0 until capacity) {
Coverage.forLoopStart(627)
            res.append("${data[i].value}(${data[i].count})")
Coverage.statementStart(628)
            if (i == posIndex) {
Coverage.ifStart(629)
                res.append("-")
Coverage.statementStart(630)
            }
Coverage.statementStart(631)
            if (i == sizeIndex) {
Coverage.ifStart(632)
                res.append("+")
Coverage.statementStart(633)
            }
Coverage.statementStart(634)
            res.append(",")
Coverage.statementStart(635)
        }
Coverage.statementStart(636)
        return res.toString()
    }
    fun skipPos(count: Int) {
Coverage.funStart(637)
        require(posAbsolute + count <= sizeAbsolute)
Coverage.statementStart(638)
        posAbsolute += count
Coverage.statementStart(639)
        if (count > 0) {
Coverage.ifStart(640)
            var i = count
Coverage.statementStart(641)
            while (true) {
Coverage.whileLoopStart(642)
                val c = data[posIndex].count - posIndexLocal
Coverage.statementStart(643)
                if (c < i) {
Coverage.ifStart(644)
                    internalNextElement()
Coverage.statementStart(645)
                    i -= c
Coverage.statementStart(646)
                } else {
Coverage.ifStart(647)
                    posIndexLocal += i
Coverage.statementStart(648)
                    break
                }
Coverage.statementStart(649)
            }
Coverage.statementStart(650)
        } else {
Coverage.ifStart(651)
            var i = -count
Coverage.statementStart(652)
            while (true) {
Coverage.whileLoopStart(653)
                val c = posIndexLocal
Coverage.statementStart(654)
                if (c < i) {
Coverage.ifStart(655)
                    posIndex--
Coverage.statementStart(656)
                    posIndexLocal = data[posIndex].count
Coverage.statementStart(657)
                    i -= c
Coverage.statementStart(658)
                } else {
Coverage.ifStart(659)
                    posIndexLocal -= i
Coverage.statementStart(660)
                    break
                }
Coverage.statementStart(661)
            }
Coverage.statementStart(662)
        }
Coverage.statementStart(663)
    }
    fun skipSize(count: Int) {
Coverage.funStart(664)
        require(posAbsolute <= sizeAbsolute + count)
Coverage.statementStart(665)
        sizeAbsolute += count
Coverage.statementStart(666)
        if (count >= 0) {
Coverage.ifStart(667)
            data[sizeIndex].count += count
Coverage.statementStart(668)
        } else {
Coverage.ifStart(669)
            var i = -count
Coverage.statementStart(670)
            while (true) {
Coverage.whileLoopStart(671)
                val c = data[sizeIndex].count
Coverage.statementStart(672)
                if (sizeIndex == 0) {
Coverage.ifStart(673)
                    data[sizeIndex].count -= i
Coverage.statementStart(674)
                    break
                } else if (c < i) {
Coverage.ifStart(675)
                    sizeIndex--
Coverage.statementStart(676)
                    i -= c
Coverage.statementStart(677)
                } else if (c == i) {
Coverage.ifStart(678)
                    sizeIndex--
Coverage.statementStart(679)
                    break
                } else {
Coverage.ifStart(680)
                    data[sizeIndex].count -= i
Coverage.statementStart(681)
                    break
                }
Coverage.statementStart(682)
            }
Coverage.statementStart(683)
        }
Coverage.statementStart(684)
    }
    fun backupPosition() {
Coverage.funStart(685)
        posBackup = posAbsolute
Coverage.statementStart(686)
    }
    fun restorePosition() {
Coverage.funStart(687)
        require(posBackup <= sizeAbsolute)
Coverage.statementStart(688)
        posAbsolute = 0
Coverage.statementStart(689)
        posIndex = 0
Coverage.statementStart(690)
        posIndexLocal = 0
Coverage.statementStart(691)
        skipPos(posBackup)
Coverage.statementStart(692)
    }
    fun current(): Value {
Coverage.funStart(693)
        internalSafeNextElement()
Coverage.statementStart(694)
        return data[posIndex].value
    }
    override fun next(): Value {
Coverage.funStart(695)
        internalSafeNextElement()
Coverage.statementStart(696)
        posIndexLocal++
Coverage.statementStart(697)
        posAbsolute++
Coverage.statementStart(698)
        return data[posIndex].value
    }
    override fun hasNext() = sizeAbsolute > posAbsolute
    fun availableWrite() = capacity - sizeIndex - 1
    fun availableRead() = sizeAbsolute - posAbsolute
    fun canAppend() = availableWrite() > 0
    fun append(value: Value, count: Int = 1) {
Coverage.funStart(699)
        require(sizeIndex < capacity - 1 && count > 0)
Coverage.statementStart(700)
        if (sizeAbsolute == 0) {
Coverage.ifStart(701)
            data[sizeIndex].count = count
Coverage.statementStart(702)
            data[sizeIndex].value = value
Coverage.statementStart(703)
        } else if (data[sizeIndex].value == value) {
Coverage.ifStart(704)
            data[sizeIndex].count += count
Coverage.statementStart(705)
        } else {
Coverage.ifStart(706)
            sizeIndex++
Coverage.statementStart(707)
            data[sizeIndex].count = count
Coverage.statementStart(708)
            data[sizeIndex].value = value
Coverage.statementStart(709)
        }
Coverage.statementStart(710)
        sizeAbsolute += count
Coverage.statementStart(711)
    }
    fun sameElements(): Int {
Coverage.funStart(712)
        internalSafeNextElement()
Coverage.statementStart(713)
        require(posIndex <= sizeIndex)
Coverage.statementStart(714)
        return data[posIndex].count - posIndexLocal
    }
    fun internalNextElement() {
Coverage.funStart(715)
        posIndex++
Coverage.statementStart(716)
        posIndexLocal = 0
Coverage.statementStart(717)
    }
    fun internalSafeNextElement() {
Coverage.funStart(718)
        if (posIndexLocal == data[posIndex].count && posIndex < sizeIndex) {
Coverage.ifStart(719)
            internalNextElement()
Coverage.statementStart(720)
        }
Coverage.statementStart(721)
    }
    fun copy(from: ResultVektor, count: Int) {
Coverage.funStart(722)
        require(count > 0)
Coverage.statementStart(723)
        var i = count
Coverage.statementStart(724)
        from.internalSafeNextElement()
Coverage.statementStart(725)
        while (true) {
Coverage.whileLoopStart(726)
            val c = from.data[from.posIndex].count - from.posIndexLocal
Coverage.statementStart(727)
            if (c < i) {
Coverage.ifStart(728)
                append(from.data[from.posIndex].value, c)
Coverage.statementStart(729)
                from.internalNextElement()
Coverage.statementStart(730)
                i -= c
Coverage.statementStart(731)
            } else {
Coverage.ifStart(732)
                append(from.data[from.posIndex].value, i)
Coverage.statementStart(733)
                from.posIndexLocal += i
Coverage.statementStart(734)
                break
            }
Coverage.statementStart(735)
        }
Coverage.statementStart(736)
        from.posAbsolute += count
Coverage.statementStart(737)
    }
    fun insertSorted(value: Value, first: Int = posAbsolute, last: Int = sizeAbsolute, comparator: Comparator<Value>, count: Int): Pair<Int, Int> {
Coverage.funStart(738)
        if (sizeAbsolute == 0) {
Coverage.ifStart(739)
            append(value, count)
Coverage.statementStart(740)
            return Pair(0, count)
        }
Coverage.statementStart(741)
        require(availableWrite() >= 2)
Coverage.statementStart(742)
        require(count > 0)
Coverage.statementStart(743)
        posAbsolute = 0
Coverage.statementStart(744)
        posIndex = 0
Coverage.statementStart(745)
        posIndexLocal = 0
Coverage.statementStart(746)
        sizeAbsolute += count
Coverage.statementStart(747)
        var index = 0
Coverage.statementStart(748)
        var indexLocal = 0
Coverage.statementStart(749)
        var idx = first
Coverage.statementStart(750)
        var absoluteindex = 0
Coverage.statementStart(751)
        while (idx > 0) {
Coverage.whileLoopStart(752)
            val c = data[index].count
Coverage.statementStart(753)
            if (c == idx) {
Coverage.ifStart(754)
                indexLocal = 0
Coverage.statementStart(755)
                absoluteindex = first
Coverage.statementStart(756)
                index++
Coverage.statementStart(757)
                break
            } else if (c > idx) {
Coverage.ifStart(758)
                indexLocal = idx
Coverage.statementStart(759)
                break
            } else {
Coverage.ifStart(760)
                absoluteindex += data[index].count
Coverage.statementStart(761)
                idx -= c
Coverage.statementStart(762)
                index++
Coverage.statementStart(763)
            }
Coverage.statementStart(764)
        }
Coverage.statementStart(765)
        idx = last - first + 1
Coverage.statementStart(766)
        var currentidx = first
Coverage.statementStart(767)
        while (true) {
Coverage.whileLoopStart(768)
            if (data[index].value == value) {
Coverage.ifStart(769)
                data[index].count += count
Coverage.statementStart(770)
                return Pair(absoluteindex, data[index].count)
            } else if (absoluteindex == last) {
Coverage.ifStart(771)
                var j = sizeIndex
Coverage.statementStart(772)
                while (j >= index) {
Coverage.whileLoopStart(773)
                    data[j + 1].count = data[j].count
Coverage.statementStart(774)
                    data[j + 1].value = data[j].value
Coverage.statementStart(775)
                    j--
Coverage.statementStart(776)
                }
Coverage.statementStart(777)
                data[index].value = value
Coverage.statementStart(778)
                data[index].count = count
Coverage.statementStart(779)
                sizeIndex++
Coverage.statementStart(780)
                return Pair(last, count)
            } else if (absoluteindex + data[index].count > last && comparator.compare(data[index].value, value) < 0) {
Coverage.ifStart(781)
                var j = sizeIndex
Coverage.statementStart(782)
                while (j >= index) {
Coverage.whileLoopStart(783)
                    data[j + 2].count = data[j].count
Coverage.statementStart(784)
                    data[j + 2].value = data[j].value
Coverage.statementStart(785)
                    j--
Coverage.statementStart(786)
                }
Coverage.statementStart(787)
                data[index + 1].value = value
Coverage.statementStart(788)
                data[index + 1].count = count
Coverage.statementStart(789)
                data[index].count = last - absoluteindex
Coverage.statementStart(790)
                data[index + 2].count -= last - absoluteindex
Coverage.statementStart(791)
                sizeIndex += 2
Coverage.statementStart(792)
                return Pair(last, count)
            } else if (comparator.compare(data[index].value, value) < 0) {
Coverage.ifStart(793)
                val c = data[index].count - indexLocal
Coverage.statementStart(794)
                currentidx += c
Coverage.statementStart(795)
                idx -= c
Coverage.statementStart(796)
                indexLocal = 0
Coverage.statementStart(797)
                absoluteindex += data[index].count
Coverage.statementStart(798)
                index++
Coverage.statementStart(799)
            } else if (indexLocal != 0) {
Coverage.ifStart(800)
                var j = sizeIndex
Coverage.statementStart(801)
                while (j >= index) {
Coverage.whileLoopStart(802)
                    data[j + 2].count = data[j].count
Coverage.statementStart(803)
                    data[j + 2].value = data[j].value
Coverage.statementStart(804)
                    j--
Coverage.statementStart(805)
                }
Coverage.statementStart(806)
                data[index + 1].value = value
Coverage.statementStart(807)
                data[index + 1].count = count
Coverage.statementStart(808)
                data[index].count = indexLocal
Coverage.statementStart(809)
                data[index + 2].count -= indexLocal
Coverage.statementStart(810)
                sizeIndex += 2
Coverage.statementStart(811)
                absoluteindex += data[index].count
Coverage.statementStart(812)
                return Pair(absoluteindex, count)
            } else {
Coverage.ifStart(813)
                var j = sizeIndex
Coverage.statementStart(814)
                while (j >= index) {
Coverage.whileLoopStart(815)
                    data[j + 1].count = data[j].count
Coverage.statementStart(816)
                    data[j + 1].value = data[j].value
Coverage.statementStart(817)
                    j--
Coverage.statementStart(818)
                }
Coverage.statementStart(819)
                data[index].value = value
Coverage.statementStart(820)
                data[index].count = count
Coverage.statementStart(821)
                sizeIndex++
Coverage.statementStart(822)
                return Pair(absoluteindex, count)
            }
Coverage.statementStart(823)
        }
/*Coverage Unreachable*/
    }
}
