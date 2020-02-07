package lupos.s09physicalOperators.singleinput

import lupos.s00misc.classNameToString
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.ASTGraph
import lupos.s02buildSyntaxTree.sparql1_1.ASTIri
import lupos.s02buildSyntaxTree.sparql1_1.ASTNode
import lupos.s02buildSyntaxTree.sparql1_1.ASTTriple
import lupos.s02buildSyntaxTree.sparql1_1.ASTVar
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s05tripleStore.PersistentStore
import lupos.s09physicalOperators.*
import lupos.s09physicalOperators.noinput.POPExpression
import lupos.s09physicalOperators.POPBaseNullableIterator
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPBindUndefined
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPFilterExact
import lupos.s09physicalOperators.singleinput.POPGroup
import lupos.s09physicalOperators.singleinput.POPMakeBooleanResult


class POPModify : POPBase {
    override val dictionary: ResultSetDictionary
    val transactionID: Long
    val iri: String?
    val insert: List<ASTNode>
    val delete: List<ASTNode>
    val pstore: PersistentStore
    override val children: Array<OPBase> = arrayOf(OPNothing())
    private val resultSetNew: ResultSet
    private val resultSetOld: ResultSet
    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    constructor(dictionary: ResultSetDictionary, transactionID: Long, iri: String?, insert: List<ASTNode>, delete: List<ASTNode>, pstore: PersistentStore, child: OPBase) : super() {
        this.dictionary = dictionary
        this.transactionID = transactionID
        this.iri = iri
        this.insert = insert
        this.delete = delete
        this.pstore = pstore
        children[0] = child
        resultSetNew = ResultSet(dictionary)
        resultSetOld = children[0].getResultSet()
        require(resultSetOld.dictionary == dictionary || (!(this.children[0] is POPBase)))
    }

    override fun hasNext(): Boolean {
        try {
            Trace.start("POPModify.hasNext")
            return children[0].hasNext()
        } finally {
            Trace.stop("POPModify.hasNext")
        }
    }

    fun evaluateRow(node: ASTNode, row: ResultRow): String? {
        return POPExpression(dictionary, node).evaluate(resultSetOld, row)
    }

    override fun next(): ResultRow {
        try {
            Trace.start("POPModify.next")
            val row = children[0].next()
            for (i in insert) {
                when (i) {
                    is ASTTriple -> {
                        val store = pstore.getDefaultGraph()
                        val data = listOf<String?>(evaluateRow(i.children[0], row), evaluateRow(i.children[1], row), evaluateRow(i.children[2], row))
                        store.addData(transactionID, data)
                    }
                    is ASTGraph -> {
                        val store = if (i.iriOrVar is ASTIri) {
                            pstore.getNamedGraph(i.iriOrVar.iri)
                        } else {
                            pstore.getNamedGraph(resultSetOld.getValue(row[resultSetOld.createVariable((i.iriOrVar as ASTVar).name)])!!)
                        }
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
            }
            for (i in delete) {
                when (i) {
                    is ASTTriple -> {
                        val store = pstore.getDefaultGraph()
                        val data = listOf<String?>(evaluateRow(i.children[0], row), evaluateRow(i.children[1], row), evaluateRow(i.children[2], row))
                        store.deleteData(transactionID, data)
                    }
                    is ASTGraph -> {
                        val store = if (i.iriOrVar is ASTIri) {
                            pstore.getNamedGraph(i.iriOrVar.iri)
                        } else {
                            pstore.getNamedGraph(resultSetOld.getValue(row[resultSetOld.createVariable((i.iriOrVar as ASTVar).name)])!!)
                        }
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
            }
            return resultSetNew.createResultRow()
        } finally {
            Trace.stop("POPModify.next")
        }
    }

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
