package lupos.s09physicalOperators.noinput

import com.benasher44.uuid.uuid4
import com.soywiz.krypto.md5
import com.soywiz.krypto.sha1
import com.soywiz.krypto.sha256
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToInt
import lupos.s00misc.classNameToString
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.singleinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase


@UseExperimental(kotlin.ExperimentalStdlibApi::class)
class POPExpression : LOPBase {
    val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())

    override fun equals(other: Any?): Boolean {
        if (other !is POPExpression)
            return false
        if (dictionary !== other.dictionary)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    constructor(dictionary: ResultSetDictionary, child: AOPBase) {
        this.dictionary = dictionary
        this.children[0] = child
    }

    fun evaluateBoolean(resultSet: ResultSet, resultRow: ResultRow): Boolean {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPBoolean)
            return a.value
        throw Exception("POPExpression evaluateBoolean only works with boolean result")
    }

    fun evaluate(resultSet: ResultSet, resultRow: ResultRow): String? {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        return a.valueToString()
    }

    fun setAggregationMode(node: OPBase, mode: Boolean, count: Int) {
        for (n in node.children)
            setAggregationMode(n, mode, count)
        if (node is AOPAggregation) {
            node.count = count
            node.collectMode = mode
            node.a = null
        }
    }

    fun evaluate(resultSet: ResultSet, resultRows: List<ResultRow>): String? {
        setAggregationMode(children[0], true, resultRows.count())
        for (resultRow in resultRows)
            (children[0] as AOPBase).calculate(resultSet, resultRow)
        setAggregationMode(children[0], false, resultRows.count())
        val a = (children[0] as AOPBase).calculate(resultSet, resultSet.createResultRow())
        return a.valueToString()
    }

    fun getAllVariablesInChildren(node: OPBase): List<String> {
        val res = mutableListOf<String>()
        if (node is AOPVariable)
            res.add(node.name)
        for (c in node.children)
            res.addAll(getAllVariablesInChildren(c))
        return res
    }

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return getAllVariablesInChildren(children[0])
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPExpression")
        res.addContent(childrenToXML())
        return res
    }
}
