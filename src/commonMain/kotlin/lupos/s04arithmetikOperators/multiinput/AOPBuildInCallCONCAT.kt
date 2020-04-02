package lupos.s04arithmetikOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallCONCAT(query: Query, child: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallCONCATID, "AOPBuildInCallCONCAT", arrayOf(child, childB)) {
    override fun toSparql() = "CONCAT(" + children[0].toSparql() + ", " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallCONCAT) {
            return false
        }
        for (i in children.indices) {
            if (children[i] != other.children[i]) {
                return false
            }
        }
        return true
    }

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            val b = childB()
            if (a is ValueLanguageTaggedLiteral && b is ValueLanguageTaggedLiteral && a.language == b.language) {
                res = ValueLanguageTaggedLiteral(a.delimiter, a.content + b.content, a.language)
            } else if (a is ValueTypedLiteral && b is ValueTypedLiteral && a.type_iri == "http://www.w3.org/2001/XMLSchema#string" && a.type_iri == b.type_iri) {
                res = ValueTypedLiteral(a.delimiter, a.content + b.content, a.type_iri)
            } else if (a is ValueStringBase && b is ValueStringBase) {
                res = ValueSimpleLiteral(a.delimiter, a.content + b.content)
            }
/*return*/res
        }
    }

    override fun cloneOP() = AOPBuildInCallCONCAT(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
