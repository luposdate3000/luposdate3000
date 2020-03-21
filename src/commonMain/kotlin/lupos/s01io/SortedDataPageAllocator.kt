package lupos.s01io

import lupos.s00misc.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw

fun <T> SortedDataPageAllocator(comparator: Comparator<T>, arrayAllocator: (Int) -> Array<T>): SortedDataPage<T> {
    return SortedDataPage<T>(comparator, arrayAllocator, ::SortedDataPageAllocator)
}
