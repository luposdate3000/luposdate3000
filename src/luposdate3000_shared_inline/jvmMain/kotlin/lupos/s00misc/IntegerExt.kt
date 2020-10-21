package lupos.s00misc

internal actual object IntegerExt {
    inline actual fun numberOfLeadingZeros(value: Int): Int {
        return Integer.numberOfLeadingZeros(value)
    }
}
