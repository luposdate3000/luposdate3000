package lupos.s00misc


data class SortHelper(val variableName: String, val sortType: ESortType) {
    override fun toString() = "$variableName.$sortType"
}
