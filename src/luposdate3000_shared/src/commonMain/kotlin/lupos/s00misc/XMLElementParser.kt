package lupos.s00misc
interface XMLElementParser {
    operator fun invoke(data: String): XMLElement?
}
