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
        updateQuery("DROP SILENT ALL")
    }

    fun checkExceptions(queryString: String) {
        if (queryString.contains("STRDT")) {
            throw Exception("jena implementation is wrong in combination with STRDT when there are typed literals matching the new specified type")
        }
        if (queryString.contains("STRLANG")) {
            throw Exception("jena implementation changes the language to uppercase, and is wrong, when there already are language tagged literals")
        }
    }

    fun updateQuery(queryString: String) {
        checkExceptions(queryString)
        try {
            UpdateAction.parseExecute(queryString, dataset)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    fun execQuery(queryString: String): String {
        checkExceptions(queryString)
        println("Jena optimized query >>")
        var res = ""
        try {
            val query = QueryFactory.create(queryString)
            val qexec = QueryExecutionFactory.create(query, dataset)
            qexec.getContext().set(ARQ.symLogExec, true)
            qexec.getContext().set(ARQ.symLogExec, Explain.InfoLevel.FINE)
            if (query.isSelectType()) {
                val results = qexec.execSelect()
                val stream = ByteArrayOutputStream()
                ResultSetFormatter.outputAsXML(stream, results)
                res = String(stream.toByteArray())
            } else if (query.isAskType()) {
                val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#").addContent(XMLElement("head")).addContent(XMLElement("boolean").addContent("" + qexec.execAsk()))
                res = nodeSparql.toPrettyString()
            } else if (query.isConstructType()) {
                val resultModel = qexec.execConstruct()
val query2 = QueryFactory.create("select ?s ?p ?o where{?s ?p ?o}")
val qexec2 = QueryExecutionFactory.create(query2, resultModel)
val results = qexec2.execSelect()
                val stream = ByteArrayOutputStream()
                ResultSetFormatter.outputAsXML(stream, results)
                res = String(stream.toByteArray())
            } else if (query.isDescribeType()) {
                val resultModel = qexec.execDescribe()
val query2 = QueryFactory.create("select ?s ?p ?o where{?s ?p ?o}")
val qexec2 = QueryExecutionFactory.create(query2, resultModel)
val results = qexec2.execSelect()
                val stream = ByteArrayOutputStream()
                ResultSetFormatter.outputAsXML(stream, results)
                res = String(stream.toByteArray())
            } else if (query.isJsonType()) {
            } else if (query.isConstructQuad()) {
            }
            println("------")
            val plan = QueryExecutionFactory.createPlan(query, dataset.asDatasetGraph(), null)
            val op = plan.getOp()
            val op2 = Optimize.optimize(op, qexec.getContext())
            println(op2)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        println("Jena optimized query <<")
        return res
    }

    fun loadFromFile(fileNames: String, graph: String) {
        var graph2 = graph
        if (!graph2.startsWith("<")) {
            graph2 = "<" + graph2 + ">"
        }
        val updateString = StringBuilder()
        for (fileName in fileNames.split(";")) {
            updateString.append("load <file://${fileName}> INTO GRAPH $graph2 ;")
        }
        println("jena-load-from-file-string ${updateString.toString()}")
        JenaWrapper.updateQuery(updateString.toString())
    }

    fun loadFromFile(fileNames: String) {
        val updateString = StringBuilder()
        for (fileName in fileNames.split(";")) {
            updateString.append("load <file://${fileName}> ;")
        }
        JenaWrapper.updateQuery(updateString.toString())
    }

    fun execQueryFile(fileName: String) {
        val queryString = java.io.File(fileName).readText()
        JenaWrapper.execQuery(queryString)
    }
}
