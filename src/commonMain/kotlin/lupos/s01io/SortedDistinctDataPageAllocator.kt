package lupos.s01io

import lupos.s00misc.*


fun <T> SortedDistinctDataPageAllocator(comparator: Comparator<T>, arrayAllocator: (Int) -> Array<T>): SortedDistinctDataPage<T> {
    return SortedDistinctDataPage<T>(comparator, arrayAllocator, ::SortedDistinctDataPageAllocator)
}
