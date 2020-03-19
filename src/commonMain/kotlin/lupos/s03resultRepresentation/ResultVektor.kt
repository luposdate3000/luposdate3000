package lupos.s03resultRepresentation

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
        val res = StringBuilder()
        res.append("$capacity $posAbsolute $posIndex $posIndexLocal $posBackup $sizeAbsolute $sizeIndex\n")
        for (i in 0 until capacity) {
            res.append("${data[i].value}(${data[i].count})")
            if (i == posIndex)
                res.append("-")
            if (i == sizeIndex)
                res.append("+")
            res.append(",")
        }
        return res.toString()
    }

    fun skipPos(count: Int) {
        posAbsolute += count
        if (count >= 0) {
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
        sizeAbsolute += count
        if (count >= 0)
            data[sizeIndex].count += count
        else {
            var i = -count
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
        internalSafeNextElement()
        if (posIndex > sizeIndex)
            return 0
        return data[posIndex].count - posIndexLocal
    }

    fun internalNextElement() {
        posIndex++
        posIndexLocal = 0
    }

    fun internalSafeNextElement() {
        if (posIndexLocal == data[posIndex].count && posIndex < sizeIndex)
            internalNextElement()
    }

    fun copy(from: ResultVektor, count: Int) {
        var i = count
        if (count > 0)
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

    fun insertSorted(value: Value, first: Int = posAbsolute, last: Int = sizeAbsolute + 1, comparator: Comparator<Value>, count: Int): Int {
        sizeAbsolute += count
        var firstIndex = 0
        var firstIndexLocal = 0
        var lastIndex = 0
        var lastIndexLocal = 0
        var idx = first
println("a")
        while (true) {
println("b")
            val c = data[firstIndex].count
            if (c >= idx) {
println("c $idx")
                firstIndexLocal = idx
                break
            } else {
println("d")
                idx -= c
                firstIndex++
            }
        }
        lastIndex = firstIndex
        lastIndexLocal = firstIndexLocal
        idx = last-first //maximaler noch zu gehende index
        var currentidx = first//aktuelle absolute position
        while (true) {
println("e $currentidx")
if(lastIndex>sizeIndex){
println("p")
data[lastIndex].value=value
data[lastIndex].count = count
sizeIndex++
return currentidx - firstIndexLocal
   }else         if (data[lastIndex].value == value) {
println("f")
                data[lastIndex].count += count
                return currentidx - firstIndexLocal
            } else if (comparator.compare(data[lastIndex].value, value) < 0) {
//hinzufügen jetzt oder später
println("g $lastIndex ${data[lastIndex].value} $value")
                val c = data[lastIndex].count - lastIndexLocal
                currentidx += c
                if (c > idx) {
//das letzte element geht weiter als mein bereich - split
println("h $c $idx ${data[lastIndex].count} $lastIndexLocal")
                    lastIndexLocal = idx
                    var j = sizeIndex
                    while (j >= lastIndex) {
println("i")
                        data[j+2].count = data[j ].count
                        data[j+2].value = data[j ].value
                        j--
                    }
                    data[lastIndex+1].value = value
                    data[lastIndex+1].count = count
data[lastIndex].count = firstIndexLocal+idx
                    data[lastIndex + 2].count -= firstIndexLocal-idx
                    sizeIndex+=2
                    return currentidx - firstIndexLocal
                } else {
println("j $c $idx ${data[lastIndex].count} $lastIndexLocal")
                    idx -= c
                    lastIndexLocal = 0
                    lastIndex++
                }
            } else {
println("m")
                if (firstIndex == lastIndex) {
println("n")
                    var j = sizeIndex
                    while (j >= lastIndex) {
println("k")
                        data[j + 2].count = data[j].count
                        data[j + 2].value = data[j].value
                        j--
                    }
                    data[lastIndex + 1].value = value
                    data[lastIndex + 1].count = count
                    data[lastIndex].count = firstIndexLocal
                    data[lastIndex + 2].count -= firstIndexLocal
                    sizeIndex += 2
                    return currentidx - firstIndexLocal
                } else {
println("o")
                    var j = sizeIndex
                    while (j > lastIndex) {
println("l")
                        data[j].count = data[j - 1].count
                        data[j].value = data[j - 1].value
                        j--
                    }
                    data[lastIndex].value = value
                    data[lastIndex].count = count
                    sizeIndex++
                    return currentidx - firstIndexLocal
                }
            }
        }
        throw Exception("unreachable")
    }
}
