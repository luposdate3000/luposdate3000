package lupos.s04logicalOperators

import lupos.s03resultRepresentation.*

class ResultIterator(
val next:()->ResultRow,
val close:()->Unit
)
