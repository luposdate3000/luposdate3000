package lupos.s00misc
public data class SortHelper public constructor(val variableName: String, val sortType: ESortType) {
    override fun toString(): String = "$variableName.$sortType"
}
