package lupos.s00misc
import java.io.*
import lupos.s00misc.Coverage
import org.apache.jena.query.*
import org.apache.jena.rdf.model.*
import org.apache.jena.riot.*
import org.apache.jena.sparql.algebra.optimize.*
import org.apache.jena.sparql.mgt.*
import org.apache.jena.update.*
import org.apache.jena.util.*
object JenaWrapper {
    var dataset = DatasetFactory.createTxnMem()
    fun dropAll() {
Coverage.funStart(16430)
        updateQuery("DROP SILENT ALL")
Coverage.statementStart(16431)
    }
    fun updateQuery(queryString: String) {
Coverage.funStart(16432)
        try {
Coverage.statementStart(16433)
            UpdateAction.parseExecute(queryString, dataset)
Coverage.statementStart(16434)
        } catch (e: Throwable) {
Coverage.statementStart(16435)
            e.printStackTrace()
Coverage.statementStart(16436)
        }
Coverage.statementStart(16437)
    }
    fun execQuery(queryString: String): String {
Coverage.funStart(16438)
        println("Jena optimized query >>")
Coverage.statementStart(16439)
        var res = ""
Coverage.statementStart(16440)
        try {
Coverage.statementStart(16441)
            val query = QueryFactory.create(queryString)
Coverage.statementStart(16442)
            val qexec = QueryExecutionFactory.create(query, dataset)
Coverage.statementStart(16443)
            qexec.getContext().set(ARQ.symLogExec, true)
Coverage.statementStart(16444)
            qexec.getContext().set(ARQ.symLogExec, Explain.InfoLevel.FINE)
Coverage.statementStart(16445)
            if (query.isSelectType()) {
Coverage.ifStart(16446)
                val results = qexec.execSelect()
Coverage.statementStart(16447)
                val stream = ByteArrayOutputStream()
Coverage.statementStart(16448)
                ResultSetFormatter.outputAsXML(stream, results)
Coverage.statementStart(16449)
                res = String(stream.toByteArray())
Coverage.statementStart(16450)
            } else if (query.isAskType()) {
Coverage.ifStart(16451)
                val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#").addContent(XMLElement("head")).addContent(XMLElement("boolean").addContent("" + qexec.execAsk()))
Coverage.statementStart(16452)
                res = nodeSparql.toPrettyString()
Coverage.statementStart(16453)
            } else if (query.isConstructType()) {
Coverage.ifStart(16454)
                val resultModel = qexec.execConstruct()
Coverage.statementStart(16455)
                qexec.close()
Coverage.statementStart(16456)
            } else if (query.isDescribeType()) {
Coverage.ifStart(16457)
                val resultModel = qexec.execDescribe()
Coverage.statementStart(16458)
                qexec.close()
Coverage.statementStart(16459)
            } else if (query.isJsonType()) {
Coverage.ifStart(16460)
            } else if (query.isConstructQuad()) {
Coverage.ifStart(16461)
            }
Coverage.statementStart(16462)
            println("------")
Coverage.statementStart(16463)
            val plan = QueryExecutionFactory.createPlan(query, dataset.asDatasetGraph(), null)
Coverage.statementStart(16464)
            val op = plan.getOp()
Coverage.statementStart(16465)
            val op2 = Optimize.optimize(op, qexec.getContext())
Coverage.statementStart(16466)
            println(op2)
Coverage.statementStart(16467)
        } catch (e: Throwable) {
Coverage.statementStart(16468)
            e.printStackTrace()
Coverage.statementStart(16469)
        }
Coverage.statementStart(16470)
        println("Jena optimized query <<")
Coverage.statementStart(16471)
        return res
    }
    fun loadFromFile(fileNames: String, graph: String) {
Coverage.funStart(16472)
        val updateString = StringBuilder()
Coverage.statementStart(16473)
        for (fileName in fileNames.split(";")) {
Coverage.forLoopStart(16474)
            updateString.append("load <file://${fileName}> INTO GRAPH $graph ;")
Coverage.statementStart(16475)
        }
Coverage.statementStart(16476)
        JenaWrapper.updateQuery(updateString.toString())
Coverage.statementStart(16477)
    }
    fun loadFromFile(fileNames: String) {
Coverage.funStart(16478)
        val updateString = StringBuilder()
Coverage.statementStart(16479)
        for (fileName in fileNames.split(";")) {
Coverage.forLoopStart(16480)
            updateString.append("load <file://${fileName}> ;")
Coverage.statementStart(16481)
        }
Coverage.statementStart(16482)
        JenaWrapper.updateQuery(updateString.toString())
Coverage.statementStart(16483)
    }
    fun execQueryFile(fileName: String) {
Coverage.funStart(16484)
        val queryString = java.io.File(fileName).readText()
Coverage.statementStart(16485)
        JenaWrapper.execQuery(queryString)
Coverage.statementStart(16486)
    }
}
