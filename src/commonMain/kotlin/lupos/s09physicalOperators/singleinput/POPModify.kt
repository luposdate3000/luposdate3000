package lupos.s07physicalOperators.singleinput
import lupos.s00misc.classNameToString
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.ASTGraph
import lupos.s02buildSyntaxTree.sparql1_1.ASTIri
import lupos.s02buildSyntaxTree.sparql1_1.ASTNode
import lupos.s02buildSyntaxTree.sparql1_1.ASTTriple
import lupos.s02buildSyntaxTree.sparql1_1.ASTVar
import lupos.s04logicalOperators.OPBase
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.ResultSet
import lupos.s06resultRepresentation.Variable
import lupos.s07physicalOperators.POPBaseNullableIterator
import lupos.s07physicalOperators.POPExpression
import lupos.s08tripleStore.PersistentStore



class POPModify(val transactionID: Long, val iri: String?, val insert: List<ASTNode>, val delete: List<ASTNode>, val pstore: PersistentStore, child: OPBase) : POPSingleInputBase(child) {
    private val resultSetNew = ResultSet()
    private val resultSetOld = child.getResultSet()

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun hasNext(): Boolean {
        try {
            Trace.start("POPModify.hasNext")
            return child.hasNext()
        } finally {
            Trace.stop("POPModify.hasNext")
        }
    }

    fun evaluateRow(node: ASTNode, row: ResultRow): String {
        return POPExpression(node).evaluate(resultSetOld, row)
    }

    override fun next(): ResultRow {
        try {
            Trace.start("POPModify.next")
            val row = child.next()
            for (i in insert) {
                when (i) {
                    is ASTTriple -> {
                        val store = pstore.getDefaultGraph()
                        val data = listOf<String>(evaluateRow(i.children[0], row), evaluateRow(i.children[1], row), evaluateRow(i.children[2], row))
                        store.addData(transactionID, data)
                    }
                    is ASTGraph -> {
                        val store = if (i.iriOrVar is ASTIri) {
                            pstore.getNamedGraph(i.iriOrVar.iri)
                        } else {
                            pstore.getNamedGraph(row[resultSetOld.createVariable((i.iriOrVar as ASTVar).name)])
                        }
                        for (c in i.children) {
                            when (c) {
                                is ASTTriple -> {
                                    val data = listOf<String>(evaluateRow(c.children[0], row), evaluateRow(c.children[1], row), evaluateRow(c.children[2], row))
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
                        val data = listOf<String>(evaluateRow(i.children[0], row), evaluateRow(i.children[1], row), evaluateRow(i.children[2], row))
                        store.deleteData(transactionID, data)
                    }
                    is ASTGraph -> {
                        val store = if (i.iriOrVar is ASTIri) {
                            pstore.getNamedGraph(i.iriOrVar.iri)
                        } else {
                            pstore.getNamedGraph(row[resultSetOld.createVariable((i.iriOrVar as ASTVar).name)])
                        }
                        for (c in i.children) {
                            when (c) {
                                is ASTTriple -> {
                                    val data = listOf<String>(evaluateRow(c.children[0], row), evaluateRow(c.children[1], row), evaluateRow(c.children[2], row))
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
