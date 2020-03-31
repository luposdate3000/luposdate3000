package lupos.s09physicalOperators.singleinput.modifiers

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.SanityCheck
import lupos.s00misc.Trace
import lupos.s01io.*
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPDistinct(query: Query, child: OPBase) : POPBase(query, EOperatorID.POPDistinctID, "POPDistinct", arrayOf(child)) {
    override fun equals(other: Any?) = other is POPDistinct && children[0] == other.children[0]
    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT ")) {
            return "{SELECT DISTINCT " + sparql.substring("{SELECT ".length, sparql.length)
        }
        return "{SELECT DISTINCT * {" + sparql + "}}"
    }

    override fun cloneOP() = POPDistinct(query, children[0].cloneOP())
    override suspend fun evaluate(): ColumnIteratorRow {
        val variables = getProvidedVariableNames()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val child = children[0].evaluate()
        for (variable in variables) {
            outMap[variable] = ColumnIteratorDistinct(child.columns[variable]!!)
        }
        return ColumnIteratorRow(outMap)
    }
}
