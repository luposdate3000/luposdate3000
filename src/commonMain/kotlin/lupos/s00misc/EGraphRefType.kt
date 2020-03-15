package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


enum class EGraphRefType {
    IriGraphRef,
    DefaultGraphRef,
    NamedGraphRef,
    AllGraphRef
}
