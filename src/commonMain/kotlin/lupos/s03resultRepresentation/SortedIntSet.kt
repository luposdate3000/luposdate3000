package lupos.s03resultRepresentation

class SortedIntSet() {
    var data = mutableListOf<Int>()
    var size: Int = 0
        get() = data.size

    constructor(value: Int) : this() {
        data.add(value)
    }

    inline fun contains(value: Int): Boolean {
        if (data.size == 0) {
            return false
        } else if (data.size == 1) {
            return data[0] == value
        } else {
            var l = 0
            var r = data.size - 1
            while (r - l > 1) {
                var m = (r + l) / 2
                if (data[m] < value) {
                    l = m
                } else if (data[m] > value) {
                    r = m
                } else {
                    return true
                }
            }
            return data[l] == value || data[r] == value
        }
    }

    inline fun add(value: Int) {
        if (data.size == 0) {
            data.add(value)
        } else if (data.size == 1) {
            if (data[0] < value)
                data.add(value)
            else if (data[0] > value)
                data.add(0, value)
        } else {
            var l = 0
            var r = data.size - 1
            while (r - l > 1) {
                var m = (r + l) / 2
                if (data[m] < value) {
                    l = m
                } else if (data[m] > value) {
                    r = m
                } else {
                    return
                }
            }
            if (data[r] < value) {
                data.add(r + 1, value)
            } else if (data[l] > value) {
                data.add(l, value)
            } else if (data[l] < value) {
                data.add(l + 1, value)
            }
        }
    }

    inline fun remove(value: Int) {
        if (data.size == 1) {
            if (data[0] == value) {
                data.removeAt(0)
            }
        } else if (data.size > 1) {
            var l = 0
            var r = data.size - 1
            while (r - l > 1) {
                var m = (r + l) / 2
                if (data[m] < value) {
                    l = m
                } else if (data[m] > value) {
                    r = m
                } else {
                    data.removeAt(m)
                    return
                }
            }
            if (data[l] == value)
                data.removeAt(l)
            else if (data[r] == value)
                data.removeAt(r)
        }
    }

    inline fun toList(): List<Int> {
        return data
    }
}
