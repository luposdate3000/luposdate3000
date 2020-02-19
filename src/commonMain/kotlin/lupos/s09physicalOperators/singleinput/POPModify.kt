package lupos.s09physicalOperators.singleinput

import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.ASTGraph
import lupos.s02buildSyntaxTree.sparql1_1.ASTIri
import lupos.s02buildSyntaxTree.sparql1_1.ASTNode
import lupos.s02buildSyntaxTree.sparql1_1.ASTTriple
import lupos.s02buildSyntaxTree.sparql1_1.ASTVar
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.noinput.POPExpression
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore


class POPModify : POPBase {
    override val resultSet: ResultSet
    override val children: Array<OPBase> = arrayOf(OPNothing())
    override val dictionary: ResultSetDictionary
    val transactionID: Long
    val iri: String?
    val insert: List<ASTNode>
    val delete: List<ASTNode>
    override fun equals(other: Any?): Boolean {
        if (other !is POPModify)
            return false
        if (dictionary !== other.dictionary)
            return false
        if (iri != other.iri)
            return false
        if (transactionID != other.transactionID)
            return false
        if (!insert.equals(other.insert))
            return false
        if (!delete.equals(other.delete))
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    constructor(dictionary: ResultSetDictionary, transactionID: Long, iri: String?, insert: List<ASTNode>, delete: List<ASTNode>, child: OPBase) : super() {
        this.dictionary = dictionary
        this.transactionID = transactionID
        this.iri = iri
        this.insert = insert
        this.delete = delete
        children[0] = child
        resultSet = ResultSet(dictionary)
        require(children[0].resultSet.dictionary == dictionary || (!(this.children[0] is POPBase)))
    }

    fun evaluateRow(node: ASTNode, row: ResultRow): String {
        return POPExpression(dictionary, node).evaluate(children[0].resultSet, row)!!
    }

    override fun evaluate() = Trace.trace<Unit>({ "POPModify.evaluate" }, {
        children[0].evaluate()
        CoroutinesHelper.run {
            try {
                for (row in children[0].channel) {
                    for (i in insert) {
                        try {
                            when (i) {
                                is ASTTriple -> {
                                    val store = DistributedTripleStore.getDefaultGraph()
                                    val data = listOf<String?>(evaluateRow(i.children[0], row), evaluateRow(i.children[1], row), evaluateRow(i.children[2], row))
                                    store.addData(transactionID, data)
                                }
                                is ASTGraph -> {
                                    val name = if (i.iriOrVar is ASTIri)
                                        i.iriOrVar.iri
                                    else
                                        children[0].resultSet.getValue(row[children[0].resultSet.createVariable((i.iriOrVar as ASTVar).name)])!!
                                    val store = DistributedTripleStore.getNamedGraph(name, true)
                                    for (c in i.children) {
                                        when (c) {
                                            is ASTTriple -> {
                                                val data = listOf<String?>(evaluateRow(c.children[0], row), evaluateRow(c.children[1], row), evaluateRow(c.children[2], row))
                                                store.addData(transactionID, data)
                                            }
                                            else -> throw UnsupportedOperationException("${classNameToString(this)} insertGraph ${classNameToString(i)}")
                                        }
                                    }
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} insert ${classNameToString(i)}")
                            }
                        } catch (e: Throwable) {
//ignore unbound variables
                        }
                    }
                    for (i in delete) {
                        try {
                            when (i) {
                                is ASTTriple -> {
                                    val store = DistributedTripleStore.getDefaultGraph()
                                    val data = listOf<String?>(evaluateRow(i.children[0], row), evaluateRow(i.children[1], row), evaluateRow(i.children[2], row))
                                    store.deleteData(transactionID, data)
                                }
                                is ASTGraph -> {
                                    val name = if (i.iriOrVar is ASTIri)
                                        i.iriOrVar.iri
                                    else
                                        children[0].resultSet.getValue(row[children[0].resultSet.createVariable((i.iriOrVar as ASTVar).name)])!!
                                    val store = DistributedTripleStore.getNamedGraph(name, false)
                                    for (c in i.children) {
                                        when (c) {
                                            is ASTTriple -> {
                                                val data = listOf<String?>(evaluateRow(c.children[0], row), evaluateRow(c.children[1], row), evaluateRow(c.children[2], row))
                                                store.deleteData(transactionID, data)
                                            }
                                            else -> throw UnsupportedOperationException("${classNameToString(this)} insertGraph ${classNameToString(i)}")
                                        }
                                    }
                                }
                                else -> throw UnsupportedOperationException("${classNameToString(this)} insert ${classNameToString(i)}")
                            }
                        } catch (e: Throwable) {
//ignore unbound variables
                        }
                    }
                    channel.send(resultSet.createResultRow())
                }
                channel.close()
                children[0].channel.close()
            } catch (e: Throwable) {
                channel.close(e)
                children[0].channel.close(e)
            }
        }
    })

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPModify")
        return res
    }
}
