package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query


class AOPBuildInCallDATATYPE(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallDATATYPEID, "AOPBuildInCallDATATYPE", arrayOf(child)) {

    override fun toSparql() = "DATATYPE(" + children[0].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallDATATYPE)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): ValueDefinition {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        when (a) {
            is ValueSimpleLiteral -> return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueIri("http://www.w3.org/2001/XMLSchema#string")
            })
            is ValueLanguageTaggedLiteral -> return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueIri("http://www.w3.org/1999/02/22-rdf-syntax-ns#langString")
            })
            is ValueTypedLiteral -> return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueIri(a.type_iri)
            })
            is ValueBoolean -> return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueIri("http://www.w3.org/2001/XMLSchema#boolean")
            })
            is ValueDateTime -> return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueIri("http://www.w3.org/2001/XMLSchema#dateTime")
            })
            is ValueDecimal -> return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueIri("http://www.w3.org/2001/XMLSchema#decimal")
            })
            is ValueDouble -> return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueIri("http://www.w3.org/2001/XMLSchema#double")
            })
            is ValueInteger -> return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueIri("http://www.w3.org/2001/XMLSchema#integer")
            })
        }
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPBuiltInCall DATATYPE only works with typed string input")
        })
    }

    override fun cloneOP() = AOPBuildInCallDATATYPE(query, children[0].cloneOP() as AOPBase)
}
