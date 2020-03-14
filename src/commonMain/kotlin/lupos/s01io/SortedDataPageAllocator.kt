package lupos.s01io

import lupos.s00misc.*


fun <T> SortedDataPageAllocator(comparator: Comparator<T>, arrayAllocator: (Int) -> Array<T>): SortedDataPage<T> {
    return SortedDataPage<T>(comparator, arrayAllocator, ::SortedDataPageAllocator)
}
