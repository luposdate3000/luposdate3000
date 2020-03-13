package lupos.s01io

class MyDataPage<T>(val comparator: Comparator<T>, val factory: (Int) -> Array<T>) {
    companion object {
        val capacity = 4
    }

    val data = factory(capacity)
    var size = 0
    var sortuntil = 0
    var prev = this
    var next = this
    override fun toString(): String {
        var res = StringBuilder("")
        res.append("(")
        if (size > 0) {
            res.append(data[0])
            for (i in 1 until size)
                res.append("," + data[i])
        }
        res.append(")")
        return res.toString()
    }

    fun append(value: T) {
        if (size < capacity) {
            data[size] = value
            if (size == 0)
                sortuntil = 1
            size++
        } else {
            val res = MyDataPage(comparator, factory)
            res.data[0] = value
            res.size = 1
            res.sortuntil = 1
            res.next = next
            next = res
            res.prev = this
            res.next.prev = res
        }
    }

    fun sort(a: Array<T>, b: Array<T>): Array<T> {
        var aIdx = 0
        var bIdx = 0
        var res = factory(a.size + b.size)
        for (i in 0 until res.size) {
            if (aIdx == a.size)
                res[i] = b[bIdx++]
            else if (bIdx == b.size)
                res[i] = a[aIdx++]
            else if (comparator.compare(a[aIdx], b[bIdx]) > 0)
                res[i] = b[bIdx++]
            else
                res[i] = a[aIdx++]
        }
        return res
    }

    fun sort(first: Int, last: Int): Array<T> {
        if (first == last) {
            var res = factory(1)
            res[0] = data[first]
            return res
        }
        val middle = (first + last) / 2
        return sort(sort(first, middle), sort(middle + 1, last))
    }

    fun sort() {
        if (size > 1 && sortuntil < size) {
            val tmp = sort(0, size - 1)
            for (i in 0 until size)
                data[i] = tmp[i]
            sortuntil = size
        }
    }
}

class SortedArray<T>(val comparator: Comparator<T>, val factory: (Int) -> Array<T>) {
    var data = MyDataPage<T>(comparator, factory)
    var size = 0
    var sortuntil = 0
    override fun toString(): String {
        var res = StringBuilder("")
        var tmp = data
        res.append("<")
        while (tmp != data.prev) {
            res.append(tmp.toString())
            tmp = tmp.next
        }
        res.append(tmp.toString())
        res.append(">")
        return res.toString()
    }

    fun add(value: T) {
        size++
        data.prev.append(value)
    }

    fun forEachUnordered(action: (T) -> Unit) {
        var tmp = data
        for (i in 0 until tmp.size)
            action(tmp.data[i])
        while (tmp != data.prev) {
            tmp = tmp.next
            for (i in 0 until tmp.size)
                action(tmp.data[i])
        }
    }

    fun forEach(action: (T) -> Unit) {
        sort()
        var tmp = data
        for (i in 0 until tmp.size)
            action(tmp.data[i])
        while (tmp != data.prev) {
            tmp = tmp.next
            for (i in 0 until tmp.size)
                action(tmp.data[i])
        }
    }

    fun sort(a: MyDataPage<T>, b: MyDataPage<T>, aCount: Int, bCount: Int): MyDataPage<T> {
        var res = MyDataPage<T>(comparator, factory)
	var aCounter=0
	var bCounter=0
        var aIdx = 0
        var bIdx = 0
        var aPage = a
        var bPage = b
if(comparator.compare(a.prev.data[a.prev.size-1],b.data[0])<=0){
println("${a.prev.data[a.prev.size-1]} vs ${b.data[0]}")
var s=""
var tmp=a
for(i in 0 until aCount){
s+="$tmp "
tmp=tmp.next
}
require (tmp===a)
s+="and "
tmp=b
for(i in 0 until bCount){
s+="$tmp "
tmp=tmp.next
}
require (tmp===b)
println(s)
a.prev.next=b
b.prev.next=a
a.prev=b.prev
b.prev=a.prev
return a
}
        while (aCounter < aCount && bCounter < bCount) {
            when {
                aIdx == aPage.size -> {
                    aIdx = 0
                    aCounter++
                    aPage = aPage.next
                }
                bIdx == bPage.size -> {
                    bIdx = 0
                    bCounter++
                    bPage = bPage.next
                }
                comparator.compare(aPage.data[aIdx], bPage.data[bIdx]) > 0 ->
                    res.prev.append(bPage.data[bIdx++])
                else ->
                    res.prev.append(aPage.data[aIdx++])
            }
        }
        if (aCounter == aCount) {
            while (bIdx < bPage.size)
                res.prev.append(bPage.data[bIdx++])
            bCounter++
            bPage = bPage.next
            while (bCounter < bCount) {
                bIdx = 0
                while (bIdx < bPage.size)
                    res.prev.append(bPage.data[bIdx++])
                bPage = bPage.next
                bCounter++
            }
        } else if (bCounter == bCount) {
            while (aIdx < aPage.size)
                res.prev.append(aPage.data[aIdx++])
            aCounter++
            aPage = aPage.next
            while (aCounter < aCount) {
                aIdx = 0
                while (aIdx < aPage.size)
                    res.prev.append(aPage.data[aIdx++])
                aPage = aPage.next
                aCounter++
            }
        }
        return res
    }

    fun sort(first: MyDataPage<T>, last: MyDataPage<T>, count: Int): MyDataPage<T> {
        if (count == 1)
            return first
        if (count == 2){
		first.next=first
		first.prev=first
		last.next=last
		last.prev=last
            return sort(first, last, 1, 1)
	}
        val half = count / 2
        val half2 = count - half
        var middle = first
        for (i in 1 until half)
            middle = middle.next
val middle2=middle.next
middle.next=first
first.prev=middle
middle2.prev=last
last.next=middle2
        return sort(sort(first, middle, half), sort(middle2, last, half2), half, half2)
    }

    fun sort() {
        if (sortuntil < size) {
            var pageCount = 1
            var tmp = data
            tmp.sort()
            while (tmp != data.prev) {
                tmp = tmp.next
                tmp.sort()
                pageCount++
            }
            if (pageCount > 1) {
                data = sort(data, data.prev, pageCount)
                tmp = data
                while (tmp != data.prev) {
                    tmp.sortuntil = tmp.size
                    tmp = tmp.next
                }
            }
            sortuntil = size
        }
    }
}
