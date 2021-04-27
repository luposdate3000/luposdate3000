object LinkTypes {
    var sortedLinkTypes: Array<LinkType> = emptyArray()
        set(value) {
            field = value
            field.sortByDescending { it.dataRateInKbps }
        }

    fun getLinkTypeByIndex(index: Int)
            = sortedLinkTypes[index]

    private fun getIndexByLinkType(linkType: LinkType)
            = sortedLinkTypes.indexOfFirst { linkType.name == it.name}

    fun getSortedLinkTypeIndices(list: List<LinkType>): IntArray {
        val result = IntArray(list.size)
        for((index, linkType) in list.withIndex()) {
            result[index] = getIndexByLinkType(linkType)
        }
        return result.sortedArray()
    }
}