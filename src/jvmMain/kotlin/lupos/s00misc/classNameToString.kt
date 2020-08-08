package lupos.s00misc


fun classNameToString(c: Any): String {
    val res = c::class.simpleName
    if (res == null) {
        return ""
    }
    return res
}
