package lupos.s01io

import lupos.s00misc.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw


fun <T> SortedDistinctDataPageAllocator(comparator: Comparator<T>, arrayAllocator: (Int) -> Array<T>): SortedDistinctDataPage<T> {
    return SortedDistinctDataPage<T>(comparator, arrayAllocator, ::SortedDistinctDataPageAllocator)
}
