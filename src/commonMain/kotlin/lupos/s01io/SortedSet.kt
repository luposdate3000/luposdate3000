package lupos.s01io

import lupos.s00misc.*


class SortedSet<T>(comparator: Comparator<T>, arrayAllocator: (Int) -> Array<T>) : SortedArrayBase<T>(comparator, arrayAllocator, ::SortedDistinctDataPageAllocator) {
}
