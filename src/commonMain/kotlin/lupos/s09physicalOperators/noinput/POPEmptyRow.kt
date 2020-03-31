package lupos.s09physicalOperators.noinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.Trace
import lupos.s03resultRepresentation.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPEmptyRow(query: Query) : POPBase(query, EOperatorID.POPEmptyRowID, "POPEmptyRow", arrayOf()) {
    override fun cloneOP() = POPEmptyRow(query)
    override fun toSparql() = "{}"
    override fun equals(other: Any?) = other is POPEmptyRow
    override suspend fun evaluate(): ColumnIteratorRow {
        val outMap = mutableMapOf<String, ColumnIterator>()
        val res = ColumnIteratorRow(outMap)
        res.count = 1
        return res
    }
}
