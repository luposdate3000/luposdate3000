package lupos.modulename
internal actual object IntegerExt {
    internal actual inline fun numberOfLeadingZeros(value: Int): Int {
        var i = 31
        while (i >= 0) {
            if (value and (1 shl i) != 0) {
                return 31 - i
            }
            i--
        }
        return 32
    }
}
