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
        Coverage.funStart(644)
        val res = StringBuilder()
        Coverage.statementStart(645)
        res.append("$capacity $posAbsolute $posIndex $posIndexLocal $posBackup $sizeAbsolute $sizeIndex\n")
        Coverage.statementStart(646)
        for (i in 0 until capacity) {
            Coverage.forLoopStart(647)
            res.append("${data[i].value}(${data[i].count})")
            Coverage.statementStart(648)
            if (i == posIndex) {
                Coverage.ifStart(649)
                res.append("-")
                Coverage.statementStart(650)
            }
            Coverage.statementStart(651)
            if (i == sizeIndex) {
                Coverage.ifStart(652)
                res.append("+")
                Coverage.statementStart(653)
            }
            Coverage.statementStart(654)
            res.append(",")
            Coverage.statementStart(655)
        }
        Coverage.statementStart(656)
        return res.toString()
    }

    fun skipPos(count: Int) {
        Coverage.funStart(657)
        require(posAbsolute + count <= sizeAbsolute)
        Coverage.statementStart(658)
        posAbsolute += count
        Coverage.statementStart(659)
        if (count > 0) {
            Coverage.ifStart(660)
            var i = count
            Coverage.statementStart(661)
            while (true) {
                Coverage.whileLoopStart(662)
                val c = data[posIndex].count - posIndexLocal
                Coverage.statementStart(663)
                if (c < i) {
                    Coverage.ifStart(664)
                    internalNextElement()
                    Coverage.statementStart(665)
                    i -= c
                    Coverage.statementStart(666)
                } else {
                    Coverage.ifStart(667)
                    posIndexLocal += i
                    Coverage.statementStart(668)
                    break
                }
                Coverage.statementStart(669)
            }
            Coverage.statementStart(670)
        } else {
            Coverage.ifStart(671)
            var i = -count
            Coverage.statementStart(672)
            while (true) {
                Coverage.whileLoopStart(673)
                val c = posIndexLocal
                Coverage.statementStart(674)
                if (c < i) {
                    Coverage.ifStart(675)
                    posIndex--
                    Coverage.statementStart(676)
                    posIndexLocal = data[posIndex].count
                    Coverage.statementStart(677)
                    i -= c
                    Coverage.statementStart(678)
                } else {
                    Coverage.ifStart(679)
                    posIndexLocal -= i
                    Coverage.statementStart(680)
                    break
                }
                Coverage.statementStart(681)
            }
            Coverage.statementStart(682)
        }
        Coverage.statementStart(683)
    }

    fun skipSize(count: Int) {
        Coverage.funStart(684)
        require(posAbsolute <= sizeAbsolute + count)
        Coverage.statementStart(685)
        sizeAbsolute += count
        Coverage.statementStart(686)
        if (count >= 0) {
            Coverage.ifStart(687)
            data[sizeIndex].count += count
            Coverage.statementStart(688)
        } else {
            Coverage.ifStart(689)
            var i = -count
            Coverage.statementStart(690)
            while (true) {
                Coverage.whileLoopStart(691)
                val c = data[sizeIndex].count
                Coverage.statementStart(692)
                if (sizeIndex == 0) {
                    Coverage.ifStart(693)
                    data[sizeIndex].count -= i
                    Coverage.statementStart(694)
                    break
                } else if (c < i) {
                    Coverage.ifStart(695)
                    sizeIndex--
                    Coverage.statementStart(696)
                    i -= c
                    Coverage.statementStart(697)
                } else if (c == i) {
                    Coverage.ifStart(698)
                    sizeIndex--
                    Coverage.statementStart(699)
                    break
                } else {
                    Coverage.ifStart(700)
                    data[sizeIndex].count -= i
                    Coverage.statementStart(701)
                    break
                }
                Coverage.statementStart(702)
            }
            Coverage.statementStart(703)
        }
        Coverage.statementStart(704)
    }

    fun backupPosition() {
        Coverage.funStart(705)
        posBackup = posAbsolute
        Coverage.statementStart(706)
    }

    fun restorePosition() {
        Coverage.funStart(707)
        require(posBackup <= sizeAbsolute)
        Coverage.statementStart(708)
        posAbsolute = 0
        Coverage.statementStart(709)
        posIndex = 0
        Coverage.statementStart(710)
        posIndexLocal = 0
        Coverage.statementStart(711)
        skipPos(posBackup)
        Coverage.statementStart(712)
    }

    fun current(): Value {
        Coverage.funStart(713)
        internalSafeNextElement()
        Coverage.statementStart(714)
        return data[posIndex].value
    }

    override fun next(): Value {
        Coverage.funStart(715)
        internalSafeNextElement()
        Coverage.statementStart(716)
        posIndexLocal++
        Coverage.statementStart(717)
        posAbsolute++
        Coverage.statementStart(718)
        return data[posIndex].value
    }

    override fun hasNext() = sizeAbsolute > posAbsolute
    fun availableWrite() = capacity - sizeIndex - 1
    fun availableRead() = sizeAbsolute - posAbsolute
    fun canAppend() = availableWrite() > 0
    fun append(value: Value, count: Int = 1) {
        Coverage.funStart(719)
        require(sizeIndex < capacity - 1 && count > 0)
        Coverage.statementStart(720)
        if (sizeAbsolute == 0) {
            Coverage.ifStart(721)
            data[sizeIndex].count = count
            Coverage.statementStart(722)
            data[sizeIndex].value = value
            Coverage.statementStart(723)
        } else if (data[sizeIndex].value == value) {
            Coverage.ifStart(724)
            data[sizeIndex].count += count
            Coverage.statementStart(725)
        } else {
            Coverage.ifStart(726)
            sizeIndex++
            Coverage.statementStart(727)
            data[sizeIndex].count = count
            Coverage.statementStart(728)
            data[sizeIndex].value = value
            Coverage.statementStart(729)
        }
        Coverage.statementStart(730)
        sizeAbsolute += count
        Coverage.statementStart(731)
    }

    fun sameElements(): Int {
        Coverage.funStart(732)
        internalSafeNextElement()
        Coverage.statementStart(733)
        require(posIndex <= sizeIndex)
        Coverage.statementStart(734)
        return data[posIndex].count - posIndexLocal
    }

    fun internalNextElement() {
        Coverage.funStart(735)
        posIndex++
        Coverage.statementStart(736)
        posIndexLocal = 0
        Coverage.statementStart(737)
    }

    fun internalSafeNextElement() {
        Coverage.funStart(738)
        if (posIndexLocal == data[posIndex].count && posIndex < sizeIndex) {
            Coverage.ifStart(739)
            internalNextElement()
            Coverage.statementStart(740)
        }
        Coverage.statementStart(741)
    }

    fun copy(from: ResultVektor, count: Int) {
        Coverage.funStart(742)
        require(count > 0)
        Coverage.statementStart(743)
        var i = count
        Coverage.statementStart(744)
        from.internalSafeNextElement()
        Coverage.statementStart(745)
        while (true) {
            Coverage.whileLoopStart(746)
            val c = from.data[from.posIndex].count - from.posIndexLocal
            Coverage.statementStart(747)
            if (c < i) {
                Coverage.ifStart(748)
                append(from.data[from.posIndex].value, c)
                Coverage.statementStart(749)
                from.internalNextElement()
                Coverage.statementStart(750)
                i -= c
                Coverage.statementStart(751)
            } else {
                Coverage.ifStart(752)
                append(from.data[from.posIndex].value, i)
                Coverage.statementStart(753)
                from.posIndexLocal += i
                Coverage.statementStart(754)
                break
            }
            Coverage.statementStart(755)
        }
        Coverage.statementStart(756)
        from.posAbsolute += count
        Coverage.statementStart(757)
    }

    fun insertSorted(value: Value, first: Int = posAbsolute, last: Int = sizeAbsolute, comparator: Comparator<Value>, count: Int): Pair<Int, Int> {
        Coverage.funStart(758)
        if (sizeAbsolute == 0) {
            Coverage.ifStart(759)
            append(value, count)
            Coverage.statementStart(760)
            return Pair(0, count)
        }
        Coverage.statementStart(761)
        require(availableWrite() >= 2)
        Coverage.statementStart(762)
        require(count > 0)
        Coverage.statementStart(763)
        posAbsolute = 0
        Coverage.statementStart(764)
        posIndex = 0
        Coverage.statementStart(765)
        posIndexLocal = 0
        Coverage.statementStart(766)
        sizeAbsolute += count
        Coverage.statementStart(767)
        var index = 0
        Coverage.statementStart(768)
        var indexLocal = 0
        Coverage.statementStart(769)
        var idx = first
        Coverage.statementStart(770)
        var absoluteindex = 0
        Coverage.statementStart(771)
        while (idx > 0) {
            Coverage.whileLoopStart(772)
            val c = data[index].count
            Coverage.statementStart(773)
            if (c == idx) {
                Coverage.ifStart(774)
                indexLocal = 0
                Coverage.statementStart(775)
                absoluteindex = first
                Coverage.statementStart(776)
                index++
                Coverage.statementStart(777)
                break
            } else if (c > idx) {
                Coverage.ifStart(778)
                indexLocal = idx
                Coverage.statementStart(779)
                break
            } else {
                Coverage.ifStart(780)
                absoluteindex += data[index].count
                Coverage.statementStart(781)
                idx -= c
                Coverage.statementStart(782)
                index++
                Coverage.statementStart(783)
            }
            Coverage.statementStart(784)
        }
        Coverage.statementStart(785)
        idx = last - first + 1
        Coverage.statementStart(786)
        var currentidx = first
        Coverage.statementStart(787)
        while (true) {
            Coverage.whileLoopStart(788)
            if (data[index].value == value) {
                Coverage.ifStart(789)
                data[index].count += count
                Coverage.statementStart(790)
                return Pair(absoluteindex, data[index].count)
            } else if (absoluteindex == last) {
                Coverage.ifStart(791)
                var j = sizeIndex
                Coverage.statementStart(792)
                while (j >= index) {
                    Coverage.whileLoopStart(793)
                    data[j + 1].count = data[j].count
                    Coverage.statementStart(794)
                    data[j + 1].value = data[j].value
                    Coverage.statementStart(795)
                    j--
                    Coverage.statementStart(796)
                }
                Coverage.statementStart(797)
                data[index].value = value
                Coverage.statementStart(798)
                data[index].count = count
                Coverage.statementStart(799)
                sizeIndex++
                Coverage.statementStart(800)
                return Pair(last, count)
            } else if (absoluteindex + data[index].count > last && comparator.compare(data[index].value, value) < 0) {
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
                data[index].count = last - absoluteindex
                Coverage.statementStart(810)
                data[index + 2].count -= last - absoluteindex
                Coverage.statementStart(811)
                sizeIndex += 2
                Coverage.statementStart(812)
                return Pair(last, count)
            } else if (comparator.compare(data[index].value, value) < 0) {
                Coverage.ifStart(813)
                val c = data[index].count - indexLocal
                Coverage.statementStart(814)
                currentidx += c
                Coverage.statementStart(815)
                idx -= c
                Coverage.statementStart(816)
                indexLocal = 0
                Coverage.statementStart(817)
                absoluteindex += data[index].count
                Coverage.statementStart(818)
                index++
                Coverage.statementStart(819)
            } else if (indexLocal != 0) {
                Coverage.ifStart(820)
                var j = sizeIndex
                Coverage.statementStart(821)
                while (j >= index) {
                    Coverage.whileLoopStart(822)
                    data[j + 2].count = data[j].count
                    Coverage.statementStart(823)
                    data[j + 2].value = data[j].value
                    Coverage.statementStart(824)
                    j--
                    Coverage.statementStart(825)
                }
                Coverage.statementStart(826)
                data[index + 1].value = value
                Coverage.statementStart(827)
                data[index + 1].count = count
                Coverage.statementStart(828)
                data[index].count = indexLocal
                Coverage.statementStart(829)
                data[index + 2].count -= indexLocal
                Coverage.statementStart(830)
                sizeIndex += 2
                Coverage.statementStart(831)
                absoluteindex += data[index].count
                Coverage.statementStart(832)
                return Pair(absoluteindex, count)
            } else {
                Coverage.ifStart(833)
                var j = sizeIndex
                Coverage.statementStart(834)
                while (j >= index) {
                    Coverage.whileLoopStart(835)
                    data[j + 1].count = data[j].count
                    Coverage.statementStart(836)
                    data[j + 1].value = data[j].value
                    Coverage.statementStart(837)
                    j--
                    Coverage.statementStart(838)
                }
                Coverage.statementStart(839)
                data[index].value = value
                Coverage.statementStart(840)
                data[index].count = count
                Coverage.statementStart(841)
                sizeIndex++
                Coverage.statementStart(842)
                return Pair(absoluteindex, count)
            }
            Coverage.statementStart(843)
        }
/*Coverage Unreachable*/
        Coverage.statementStart(845)
    }
}
