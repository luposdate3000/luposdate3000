package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallDATATYPE(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallDATATYPEID, "AOPBuildInCallDATATYPE", arrayOf(child)) {
    override fun toSparql() = "DATATYPE(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallDATATYPE)
            return false
        return children[0] == other.children[0]
    }

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            when (a) {
                is ValueSimpleLiteral -> res = ValueIri("http://www.w3.org/2001/XMLSchema#string")
                is ValueLanguageTaggedLiteral -> res = ValueIri("http://www.w3.org/1999/02/22-rdf-syntax-ns#langString")
                is ValueTypedLiteral -> res = ValueIri(a.type_iri)
                is ValueBoolean -> res = ValueIri("http://www.w3.org/2001/XMLSchema#boolean")
                is ValueDateTime -> res = ValueIri("http://www.w3.org/2001/XMLSchema#dateTime")
                is ValueDecimal -> res = ValueIri("http://www.w3.org/2001/XMLSchema#decimal")
                is ValueDouble -> res = ValueIri("http://www.w3.org/2001/XMLSchema#double")
                is ValueInteger -> res = ValueIri("http://www.w3.org/2001/XMLSchema#integer")
            }
            res
        }
    }

    override fun cloneOP() = AOPBuildInCallDATATYPE(query, children[0].cloneOP() as AOPBase)
}
