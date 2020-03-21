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
Coverage.funStart(119)







        val res = StringBuilder()
        res.append("$capacity $posAbsolute $posIndex $posIndexLocal $posBackup $sizeAbsolute $sizeIndex\n")
        for (i in 0 until capacity) {
Coverage.forLoopStart(120)







            res.append("${data[i].value}(${data[i].count})")
            if (i == posIndex) {
Coverage.ifStart(121)







                res.append("-")
            }
            if (i == sizeIndex) {
Coverage.ifStart(122)







                res.append("+")
            }
            res.append(",")
        }
        return res.toString()
    }

    fun skipPos(count: Int) {
Coverage.funStart(123)







        require(posAbsolute + count <= sizeAbsolute)
        posAbsolute += count
        if (count > 0) {
Coverage.ifStart(124)







            var i = count
            while (true) {
Coverage.whileLoopStart(125)







                val c = data[posIndex].count - posIndexLocal
                if (c < i) {
Coverage.ifStart(126)







                    internalNextElement()
                    i -= c
                } else {
Coverage.ifStart(127)







                    posIndexLocal += i
                    break
                }
            }
        } else {
Coverage.ifStart(128)







            var i = -count
            while (true) {
Coverage.whileLoopStart(129)







                val c = posIndexLocal
                if (c < i) {
Coverage.ifStart(130)







                    posIndex--
                    posIndexLocal = data[posIndex].count
                    i -= c
                } else {
Coverage.ifStart(131)







                    posIndexLocal -= i
                    break
                }
            }
        }
    }

    fun skipSize(count: Int) {
Coverage.funStart(132)







        require(posAbsolute <= sizeAbsolute + count)
        sizeAbsolute += count
        if (count >= 0) {
Coverage.ifStart(133)







            data[sizeIndex].count += count
        } else {
Coverage.ifStart(134)







            var i = -count
            while (true) {
Coverage.whileLoopStart(135)







                val c = data[sizeIndex].count
                if (c < i) {
Coverage.ifStart(136)







                    sizeIndex--
                    i -= c
                } else {
Coverage.ifStart(137)







                    if (sizeIndex == 0 && c == i) {
Coverage.ifStart(138)







                        data[0].count = 0
                    } else if (c == i) {
Coverage.ifStart(139)







                        sizeIndex--
                    } else {
Coverage.ifStart(140)







                        data[sizeIndex].count -= i
                    }
                    break
                }
            }
        }
    }

    fun backupPosition() {
Coverage.funStart(141)







        posBackup[0] = posAbsolute
        posBackup[1] = posIndex
        posBackup[2] = posIndexLocal
    }

    fun restorePosition() {
Coverage.funStart(142)







        posAbsolute = posBackup[0]
        posIndex = posBackup[1]
        posIndexLocal = posBackup[2]
    }

    fun current(): Value {
Coverage.funStart(143)







        internalSafeNextElement()
        return data[posIndex].value
    }

    override fun next(): Value {
Coverage.funStart(144)







        internalSafeNextElement()
        posIndexLocal++
        posAbsolute++
        return data[posIndex].value
    }

    override fun hasNext() = sizeAbsolute > posAbsolute
    fun availableWrite() = capacity - sizeIndex - 1
    fun availableRead() = sizeAbsolute - posAbsolute
    fun canAppend() = availableWrite() > 0

    fun append(value: Value, count: Int = 1) {
Coverage.funStart(145)







        require(sizeIndex < capacity - 1 && count > 0)
        if (sizeAbsolute == 0) {
Coverage.ifStart(146)







            data[sizeIndex].count = count
            data[sizeIndex].value = value
        } else if (data[sizeIndex].value == value) {
Coverage.ifStart(147)







            data[sizeIndex].count += count
        } else {
Coverage.ifStart(148)







            sizeIndex++
            data[sizeIndex].count = count
            data[sizeIndex].value = value
        }
        sizeAbsolute += count
    }

    fun sameElements(): Int {
Coverage.funStart(149)







        internalSafeNextElement()
        if (posIndex > sizeIndex) {
Coverage.ifStart(150)







            return 0
        }
        return data[posIndex].count - posIndexLocal
    }

    fun internalNextElement() {
Coverage.funStart(151)







        posIndex++
        posIndexLocal = 0
    }

    fun internalSafeNextElement() {
Coverage.funStart(152)







        if (posIndexLocal == data[posIndex].count && posIndex < sizeIndex) {
Coverage.ifStart(153)







            internalNextElement()
        }
    }

    fun copy(from: ResultVektor, count: Int) {
Coverage.funStart(154)







        require(count > 0)
        var i = count
        from.internalSafeNextElement()
        while (true) {
Coverage.whileLoopStart(155)







            val c = from.data[from.posIndex].count - from.posIndexLocal
            if (c < i) {
Coverage.ifStart(156)







                append(from.data[from.posIndex].value, c)
                from.internalNextElement()
                i -= c
            } else {
Coverage.ifStart(157)







                append(from.data[from.posIndex].value, i)
                from.posIndexLocal += i
                break
            }
        }
        from.posAbsolute += count
    }

    fun insertSorted(value: Value, first: Int = posAbsolute, last: Int = sizeAbsolute, comparator: Comparator<Value>, count: Int): Pair<Int, Int> {
Coverage.funStart(158)







        if (sizeAbsolute == 0) {
Coverage.ifStart(159)







            append(value, count)
            return Pair(0, count)
        }
        require(availableWrite() >= 2)
        require(count > 0)
        posAbsolute = 0
        posIndex = 0
        posIndexLocal = 0
        sizeAbsolute += count
        var firstIndex = 0
        var firstIndexLocal = 0
        var lastIndex = 0
        var lastIndexLocal = 0
        var idx = first
        var absoluteindex = 0
        while (idx > 0) {
Coverage.whileLoopStart(160)







            val c = data[firstIndex].count
            if (c == 0) {
Coverage.ifStart(161)







                firstIndexLocal = idx
                break
            } else if (c == idx) {
Coverage.ifStart(162)







                firstIndexLocal = 0
                absoluteindex = first
                firstIndex++
                break
            } else if (c > idx) {
Coverage.ifStart(163)







                firstIndexLocal = idx
                break
            } else {
Coverage.ifStart(164)







                absoluteindex += data[firstIndex].count
                idx -= c
                firstIndex++
            }
        }
        lastIndex = firstIndex
        lastIndexLocal = firstIndexLocal
        idx = last - first + 1//maximaler noch zu gehende index
        var currentidx = first
        while (true) {
Coverage.whileLoopStart(165)







            if (data[lastIndex].value == value) {
Coverage.ifStart(166)







                data[lastIndex].count += count
                return Pair(absoluteindex, data[lastIndex].count)
            } else if (absoluteindex == last) {
Coverage.ifStart(167)







                var j = sizeIndex
                while (j >= lastIndex) {
Coverage.whileLoopStart(168)







                    data[j + 1].count = data[j].count
                    data[j + 1].value = data[j].value
                    j--
                }
                data[lastIndex].value = value
                data[lastIndex].count = count
                sizeIndex++
                return Pair(last, count)
            } else if (absoluteindex + data[lastIndex].count > last && comparator.compare(data[lastIndex].value, value) < 0) {
Coverage.ifStart(169)







                var j = sizeIndex
                while (j >= lastIndex) {
Coverage.whileLoopStart(170)







                    data[j + 2].count = data[j].count
                    data[j + 2].value = data[j].value
                    j--
                }
                data[lastIndex + 1].value = value
                data[lastIndex + 1].count = count
                data[lastIndex].count = last - absoluteindex
                data[lastIndex + 2].count -= last - absoluteindex
                sizeIndex += 2
                return Pair(last, count)
            } else if (lastIndex > sizeIndex) {
Coverage.ifStart(171)







                data[lastIndex].value = value
                data[lastIndex].count = count
                sizeIndex++
                return Pair(absoluteindex, count)
            } else if (comparator.compare(data[lastIndex].value, value) < 0) {
Coverage.ifStart(172)







                val c = data[lastIndex].count - lastIndexLocal
                currentidx += c
                if (currentidx - last - 1 == data[lastIndex].count) {
Coverage.ifStart(173)







                    lastIndexLocal = idx
                    var j = sizeIndex
                    while (j >= lastIndex) {
Coverage.whileLoopStart(174)







                        data[j + 1].count = data[j].count
                        data[j + 1].value = data[j].value
                        j--
                    }
                    data[lastIndex].value = value
                    data[lastIndex].count = count
                    sizeIndex++
                    return Pair(absoluteindex, count)
                } else if (c > idx) {
Coverage.ifStart(175)







                    lastIndexLocal = idx
                    var j = sizeIndex
                    while (j >= lastIndex) {
Coverage.whileLoopStart(176)







                        data[j + 2].count = data[j].count
                        data[j + 2].value = data[j].value
                        j--
                    }
                    data[lastIndex + 1].value = value
                    data[lastIndex + 1].count = count
                    data[lastIndex].count -= currentidx - last - 1
                    data[lastIndex + 2].count = currentidx - last - 1
                    sizeIndex += 2
                    absoluteindex += data[lastIndex].count
                    return Pair(absoluteindex, count)
                } else {
Coverage.ifStart(177)







                    idx -= c
                    lastIndexLocal = 0
                    absoluteindex += data[lastIndex].count
                    lastIndex++
                }
            } else {
Coverage.ifStart(178)







                if (firstIndexLocal != 0) {
Coverage.ifStart(179)







                    var j = sizeIndex
                    while (j >= lastIndex) {
Coverage.whileLoopStart(180)







                        data[j + 2].count = data[j].count
                        data[j + 2].value = data[j].value
                        j--
                    }
                    data[lastIndex + 1].value = value
                    data[lastIndex + 1].count = count
                    data[lastIndex].count = firstIndexLocal
                    data[lastIndex + 2].count -= firstIndexLocal
                    sizeIndex += 2
                    absoluteindex += data[lastIndex].count
                    return Pair(absoluteindex, count)
                } else {
Coverage.ifStart(181)







                    var j = sizeIndex
                    while (j >= lastIndex) {
Coverage.whileLoopStart(182)







                        data[j + 1].count = data[j].count
                        data[j + 1].value = data[j].value
                        j--
                    }
                    data[lastIndex].value = value
                    data[lastIndex].count = count
                    sizeIndex++
                    return Pair(absoluteindex, count)
                }
            }
        }
        throw Exception("unreachable")
    }
}
