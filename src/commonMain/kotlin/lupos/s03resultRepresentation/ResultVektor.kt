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
                    if (sizeIndex == 0 && c == i)
                        data[0].count = 0
                    else if (c == i)
                        sizeIndex--
                    else
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
println("d0 $count")
        require(sizeIndex < capacity - 1 &&count>0)
if(sizeAbsolute==0){
println("d1")
            data[sizeIndex].count = count
            data[sizeIndex].value = value
}else        if (data[sizeIndex].value == value){
println("d2")
            data[sizeIndex].count += count
}        else {
println("d3")
            sizeIndex++
            data[sizeIndex].count = count
            data[sizeIndex].value = value
        }
        sizeAbsolute += count
println("d4 $sizeAbsolute")
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
println("b1")
if(sizeAbsolute==0){
append(value,count)
return Pair(0,count)
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
        while (true) {
println("b2")
            val c = data[firstIndex].count
            if (c >= idx) {
println("b3")
                if (c == idx && data[firstIndex].value != value&&c!=0) {
println("b4")
                    absoluteindex += data[firstIndex].count
                    firstIndex++
                } else {
println("b5")
                    firstIndexLocal = idx
                }
                break
            } else {
println("b6")
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
println("b7 $absoluteindex $last")
            if (absoluteindex == last) {
println("b8")
                var j = sizeIndex
                while (j >= lastIndex) {
println("b9")
                    data[j + 1].count = data[j].count
                    data[j + 1].value = data[j].value
                    j--
                }
                data[lastIndex ].value = value
                data[lastIndex ].count = count
                sizeIndex++
                return Pair(last, count)
            } else if (absoluteindex + data[lastIndex].count > last) {
println("b10")
                var j = sizeIndex
                while (j >= lastIndex) {
println("b11")
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
println("b12")
                data[lastIndex].value = value
                data[lastIndex].count = count
                sizeIndex++
                return Pair(absoluteindex, count)
            } else if (data[lastIndex].value == value) {
println("b13")
                data[lastIndex].count += count
                return Pair(absoluteindex, data[lastIndex].count)
            } else if (comparator.compare(data[lastIndex].value, value) < 0) {
println("b14")
                val c = data[lastIndex].count - lastIndexLocal
                currentidx += c
                if (currentidx - last - 1 == data[lastIndex].count) {
println("b15")
                    lastIndexLocal = idx
                    var j = sizeIndex
                    while (j >= lastIndex) {
println("b16")
                        data[j + 1].count = data[j].count
                        data[j + 1].value = data[j].value
                        j--
                    }
                    data[lastIndex].value = value
                    data[lastIndex].count = count
                    sizeIndex++
                    return Pair(absoluteindex, count)
                } else if (c > idx) {
println("b17")
                    lastIndexLocal = idx
                    var j = sizeIndex
                    while (j >= lastIndex) {
println("b18")
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
println("b19")
                    idx -= c
                    lastIndexLocal = 0
                    absoluteindex += data[lastIndex].count
                    lastIndex++
                }
            } else {
println("b20")
                if (firstIndexLocal != 0) {
println("b21")
                    var j = sizeIndex
                    while (j >= lastIndex) {
println("b22")
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
println("b23")
                    var j = sizeIndex
                    while (j >= lastIndex) {
println("b24")
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
