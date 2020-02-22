package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallDATATYPE(child: AOPBase) : AOPBase() {
    override val children: Array<OPBase> = arrayOf(child)

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPBuildInCallDATATYPE")
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallDATATYPE)
            return false
        return children[0].equals(other.children[0])
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        when (a) {
            is AOPTypedLiteral -> return addMicroTest(this, resultRow, resultSet, AOPIri(a.type_iri))
            is AOPBoolean -> return addMicroTest(this, resultRow, resultSet, AOPIri("http://www.w3.org/2001/XMLSchema#boolean"))
            is AOPDateTime -> return addMicroTest(this, resultRow, resultSet, AOPIri("http://www.w3.org/2001/XMLSchema#dateTime"))
            is AOPDecimal -> return addMicroTest(this, resultRow, resultSet, AOPIri("http://www.w3.org/2001/XMLSchema#decimal"))
            is AOPDouble -> return addMicroTest(this, resultRow, resultSet, AOPIri("http://www.w3.org/2001/XMLSchema#double"))
            is AOPInteger -> return addMicroTest(this, resultRow, resultSet, AOPIri("http://www.w3.org/2001/XMLSchema#integer"))
        }
        throw addMicroTest(this, resultRow, resultSet, Exception("AOPBuiltInCall DATATYPE only works with typed string input"))
    }
}
