package lupos.s03resultRepresentation

import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw


class ResultVektor(undefValue: Value) : Iterator<Value> {
    companion object {
        val capacity = 5
    }

    var posAbsolute = 0
    var posIndex = 0
    var posIndexLocal = 0
    var posBackup = Array(3) { 0 }
    var sizeAbsolute = 0
    var sizeIndex = 0

    val data = Array<CompressedElement>(capacity) { CompressedElement(0, undefValue) }

    fun skipPos(count: Int) {
        posAbsolute += count
        if (count >= 0) {
            var i = count
            while (true) {
                val c = data[posIndex].count - posIndexLocal
                if (c < i) {
                    nextElement()
                    i -= c
                } else {
                    posIndexLocal += i
                    break
                }
            }
        } else {
            require(false)
        }
    }

    fun skipSize(count: Int) {
        sizeAbsolute += count
        if (count >= 0)
            data[sizeIndex].count += count
        else {
            var i = count
            while (true) {
                val c = data[sizeIndex].count
                if (c < i) {
                    sizeIndex--
                    i -= c
                } else {
                    data[sizeIndex].count -= i
                    break
                }
            }
        }
    }

    fun backupPosition() {
        posBackup[0] = posAbsolute
        posBackup[1] = posIndex
        posBackup[2] = posIndexLocal
    }

    fun restorePosition() {
        posAbsolute = posBackup[0]
        posIndex = posBackup[1]
        posIndexLocal = posBackup[2]
    }

    fun current(): Value = data[posIndex].value
    override fun next(): Value {
        safeNextElement()
        posIndexLocal++
        posAbsolute++
        return data[posIndex].value
    }

    override fun hasNext() = sizeAbsolute > posAbsolute
    fun availableWrite() = capacity - sizeIndex - 1
    fun availableRead() = sizeAbsolute - posAbsolute
    fun canAppend() = availableWrite() > 0

    fun append(value: Value, count: Int = 1) {
        if (data[sizeIndex].value == value)
            data[sizeIndex].count += count
        else {
            if (sizeAbsolute > 0)
                sizeIndex++
            data[sizeIndex].count = count
            data[sizeIndex].value = value
        }
        sizeAbsolute += count
    }

    fun sameElements(): Int {
        safeNextElement()
        if (posIndex > sizeIndex)
            return 0
        return data[posIndex].count - posIndexLocal
    }

    fun nextElement() {
        posIndex++
        posIndexLocal = 0
    }

    fun safeNextElement() {
        if (posIndexLocal == data[posIndex].count)
            nextElement()
    }

    fun copy(from: ResultVektor, count: Int) {
        var i = count
        if (count > 0)
            from.safeNextElement()
        while (true) {
            val c = from.data[from.posIndex].count - from.posIndexLocal
            if (c < i) {
                append(from.data[from.posIndex].value, c)
                from.nextElement()
                i -= c
            } else {
                append(from.data[from.posIndex].value, i)
                from.posIndexLocal += i
                break
            }
        }
        from.posAbsolute += count
    }

    fun copy(from: Value, count: Int) {
        append(from, count)
    }

    fun insertSorted(value: Value, first: Int = posIndex, last: Int = sizeIndex + 1, comparator: Comparator<Value>, count: Int): Int {
        sizeAbsolute += count
        for (i in first until last)
            if (data[i].value == value) {
                data[i].count++
                return i
            }
        for (i in first until last)
            if (comparator.compare(data[i].value, value) > 0) {
                var j = sizeIndex
                while (j > i) {
                    data[j] = data[j - 1]
                    j--
                }
                data[i].value = value
                data[i].count = count
                return i
            }
        if (sizeAbsolute > count)
            sizeIndex++
        val i = sizeIndex
        data[i].value = value
        data[i].count = count
        return i
    }
}
