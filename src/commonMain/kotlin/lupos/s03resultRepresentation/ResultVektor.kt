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
        val res = StringBuilder()
        res.append("$capacity $posAbsolute $posIndex $posIndexLocal $posBackup $sizeAbsolute $sizeIndex\n")
        for (i in 0 until capacity) {
            res.append("${data[i].value}(${data[i].count})")
            if (i == posIndex) {
                res.append("-")
            }
            if (i == sizeIndex) {
                res.append("+")
            }
            res.append(",")
        }
        return res.toString()
    }
    fun skipPos(count: Int) {
        require(posAbsolute + count <= sizeAbsolute)
        posAbsolute += count
        if (count > 0) {
            var i = count
            while (true) {
                val c = data[posIndex].count - posIndexLocal
                if (c < i) {
                    internalNextElement()
                    i -= c
                } else {
                    posIndexLocal += i
                    break
                }
            }
        } else {
            var i = -count
            while (true) {
                val c = posIndexLocal
                if (c < i) {
                    posIndex--
                    posIndexLocal = data[posIndex].count
                    i -= c
                } else {
                    posIndexLocal -= i
                    break
                }
            }
        }
    }
    fun skipSize(count: Int) {
        require(posAbsolute <= sizeAbsolute + count)
        sizeAbsolute += count
        if (count >= 0) {
            data[sizeIndex].count += count
        } else {
            var i = -count
            while (true) {
                val c = data[sizeIndex].count
                if (sizeIndex == 0) {
                    data[sizeIndex].count -= i
                    break
                } else if (c < i) {
                    sizeIndex--
                    i -= c
                } else if (c == i) {
                    sizeIndex--
                    break
                } else {
                    data[sizeIndex].count -= i
                    break
                }
            }
        }
    }
    fun backupPosition() {
        posBackup = posAbsolute
    }
    fun restorePosition() {
        require(posBackup <= sizeAbsolute)
        posAbsolute = 0
        posIndex = 0
        posIndexLocal = 0
        skipPos(posBackup)
    }
    fun current(): Value {
        internalSafeNextElement()
        return data[posIndex].value
    }
    override fun next(): Value {
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
        require(sizeIndex < capacity - 1 && count > 0)
        if (sizeAbsolute == 0) {
            data[sizeIndex].count = count
            data[sizeIndex].value = value
        } else if (data[sizeIndex].value == value) {
            data[sizeIndex].count += count
        } else {
            sizeIndex++
            data[sizeIndex].count = count
            data[sizeIndex].value = value
        }
        sizeAbsolute += count
    }
    fun sameElements(): Int {
        internalSafeNextElement()
        require(posIndex <= sizeIndex)
        return data[posIndex].count - posIndexLocal
    }
    fun internalNextElement() {
        posIndex++
        posIndexLocal = 0
    }
    fun internalSafeNextElement() {
        if (posIndexLocal == data[posIndex].count && posIndex < sizeIndex) {
            internalNextElement()
        }
    }
    fun copy(from: ResultVektor, count: Int) {
        require(count > 0)
        var i = count
        from.internalSafeNextElement()
        while (true) {
            val c = from.data[from.posIndex].count - from.posIndexLocal
            if (c < i) {
                append(from.data[from.posIndex].value, c)
                from.internalNextElement()
                i -= c
            } else {
                append(from.data[from.posIndex].value, i)
                from.posIndexLocal += i
                break
            }
        }
        from.posAbsolute += count
    }
    fun insertSorted(value: Value, first: Int = posAbsolute, last: Int = sizeAbsolute, comparator: Comparator<Value>, count: Int): Pair<Int, Int> {
        if (sizeAbsolute == 0) {
            append(value, count)
            return Pair(0, count)
        }
        require(availableWrite() >= 2)
        require(count > 0)
        posAbsolute = 0
        posIndex = 0
        posIndexLocal = 0
        sizeAbsolute += count
        var index = 0
        var indexLocal = 0
        var idx = first
        var absoluteindex = 0
        while (idx > 0) {
            val c = data[index].count
            if (c == idx) {
                indexLocal = 0
                absoluteindex = first
                index++
                break
            } else if (c > idx) {
                indexLocal = idx
                break
            } else {
                absoluteindex += data[index].count
                idx -= c
                index++
            }
        }
        idx = last - first + 1
        var currentidx = first
        while (true) {
            if (data[index].value == value) {
                data[index].count += count
                return Pair(absoluteindex, data[index].count)
            } else if (absoluteindex == last) {
                var j = sizeIndex
                while (j >= index) {
                    data[j + 1].count = data[j].count
                    data[j + 1].value = data[j].value
                    j--
                }
                data[index].value = value
                data[index].count = count
                sizeIndex++
                return Pair(last, count)
            } else if (absoluteindex + data[index].count > last && comparator.compare(data[index].value, value) < 0) {
                var j = sizeIndex
                while (j >= index) {
                    data[j + 2].count = data[j].count
                    data[j + 2].value = data[j].value
                    j--
                }
                data[index + 1].value = value
                data[index + 1].count = count
                data[index].count = last - absoluteindex
                data[index + 2].count -= last - absoluteindex
                sizeIndex += 2
                return Pair(last, count)
            } else if (comparator.compare(data[index].value, value) < 0) {
                val c = data[index].count - indexLocal
                currentidx += c
                idx -= c
                indexLocal = 0
                absoluteindex += data[index].count
                index++
            } else if (indexLocal != 0) {
                var j = sizeIndex
                while (j >= index) {
                    data[j + 2].count = data[j].count
                    data[j + 2].value = data[j].value
                    j--
                }
                data[index + 1].value = value
                data[index + 1].count = count
                data[index].count = indexLocal
                data[index + 2].count -= indexLocal
                sizeIndex += 2
                absoluteindex += data[index].count
                return Pair(absoluteindex, count)
            } else {
                var j = sizeIndex
                while (j >= index) {
                    data[j + 1].count = data[j].count
                    data[j + 1].value = data[j].value
                    j--
                }
                data[index].value = value
                data[index].count = count
                sizeIndex++
                return Pair(absoluteindex, count)
            }
        }
/*Coverage Unreachable*/
    }
}
