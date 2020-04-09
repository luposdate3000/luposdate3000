package lupos.s09physicalOperators.noinput
import lupos.s00misc.ESortPriority

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPEmptyRow(query: Query, projectedVariables: List<String>) : POPBase(query, projectedVariables, EOperatorID.POPEmptyRowID, "POPEmptyRow", arrayOf(),ESortPriority.PREVENT_ANY) {
    override fun cloneOP() = POPEmptyRow(query, projectedVariables)
    override fun toSparql() = "{}"
    override fun equals(other: Any?) = other is POPEmptyRow
    override suspend fun evaluate(): ColumnIteratorRow {
        val outMap = mutableMapOf<String, ColumnIterator>()
        val res = ColumnIteratorRow(outMap)
        res.count = 1
        return res
    }
}
