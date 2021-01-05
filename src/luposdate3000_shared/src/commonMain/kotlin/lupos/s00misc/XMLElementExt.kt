package lupos.s00misc
public fun XMLElement.Companion.parseFromAny(data: String, filename: String): XMLElement? {
    val ext = filename.substring(filename.lastIndexOf(".") + 1)
    val parser = parseFromAnyRegistered[ext]
    if (parser == null) {
        throw UnknownDataFileException("$filename ($ext)")
    } else {
        return parser(data)
    }
}
