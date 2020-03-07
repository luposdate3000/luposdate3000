package lupos.s14endpoint
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EOperatorID
import lupos.s00misc.GlobalLogger
import lupos.s00misc.parseFromXml
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.rdf.Dictionary
import lupos.s02buildSyntaxTree.rdf.ID_Triple
import lupos.s02buildSyntaxTree.sparql1_1.SPARQLParser
import lupos.s02buildSyntaxTree.sparql1_1.TokenIteratorSPARQLParser
import lupos.s02buildSyntaxTree.turtle.TurtleParserWithDictionary
import lupos.s02buildSyntaxTree.turtle.TurtleScanner
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04logicalOperators.OPBase
import lupos.s06buildOperatorGraph.OperatorGraphVisitor
import lupos.s08logicalOptimisation.LogicalOptimizer
import lupos.s09physicalOperators.noinput.POPImportFromXml
import lupos.s09physicalOperators.POPBase
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.QueryResultToXML
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s15tripleStoreDistributed.DistributedTripleStore


class TripleInsertIterator : POPBase {
    @JvmField
    var result: ResultRow

    override fun equals(other: Any?): Boolean {
        if (other !is TripleInsertIterator)
            return false
        if (result != other.result)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    fun cleanString(s: String): String = Trace.trace({ "TripleInsertIterator.cleanString" }, {
        var res = s
        while (true) {
            val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res)
            if (match == null)
                break
            val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
            res = res.replace(match.value, replacement)
        }
        return res
    })

    override fun cloneOP() = throw Exception("not implemented")

    constructor(query:Query, triple: ID_Triple) :super(query,EOperatorID.TripleInsertIteratorID,"TripleInsertIterator",ResultSet(query.dictionary), arrayOf()){
        result = resultSet.createResultRow()
        result[resultSet.createVariable("s")] = resultSet.createValue(cleanString(Dictionary[triple.s]!!.toN3String()))
        result[resultSet.createVariable("p")] = resultSet.createValue(cleanString(Dictionary[triple.p]!!.toN3String()))
        result[resultSet.createVariable("o")] = resultSet.createValue(cleanString(Dictionary[triple.o]!!.toN3String()))
    }

    override fun evaluate() = Trace.trace<Channel<ResultRow>>({ "TripleInsertIterator.evaluate" }, {
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                channel.send(result)
                channel.close()
            } catch (e: Throwable) {
                channel.close(e)
            }
        }
        return channel
    })
}
