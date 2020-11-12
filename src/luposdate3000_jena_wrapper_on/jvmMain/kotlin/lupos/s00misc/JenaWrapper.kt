package lupos.s00misc

import java.io.ByteArrayOutputStream
import java.io.File
import lupos.s00misc.JenaBugException
import org.apache.jena.query.ARQ
import org.apache.jena.query.DatasetFactory
import org.apache.jena.query.QueryExecutionFactory
import org.apache.jena.query.QueryFactory
import org.apache.jena.query.ResultSetFormatter
import org.apache.jena.sparql.algebra.optimize.Optimize
import org.apache.jena.sparql.mgt.Explain
import org.apache.jena.update.UpdateAction

object JenaWrapper {
    var dataset = DatasetFactory.createTxnMem()
    fun dropAll() {
        updateQuery("DROP SILENT ALL")
    }

    fun checkExceptions(queryString: String) {
        if (queryString.contains("STRDT")) {
            throw JenaBugException("jena implementation is wrong in combination with STRDT when there are typed literals matching the new specified type")
        }
        if (queryString.contains("STRLANG")) {
            throw JenaBugException("jena implementation changes the language to uppercase, and is wrong, when there already are language tagged literals")
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

    fun execQuery(queryString: String, logging: Boolean = true): String {
        if (logging) {
            checkExceptions(queryString)
        }
        var res = ""
        try {
            val query = QueryFactory.create(queryString)
            val qexec = QueryExecutionFactory.create(query, dataset)
            if (logging) {
                qexec.context.set(ARQ.symLogExec, true)
                qexec.context.set(ARQ.symLogExec, Explain.InfoLevel.FINE)
            }
            if (query.isSelectType) {
                val results = qexec.execSelect()
                val stream = ByteArrayOutputStream()
                ResultSetFormatter.outputAsXML(stream, results)
                res = String(stream.toByteArray())
            } else if (query.isAskType) {
                res = "<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\"><head><boolean>${qexec.execAsk()}</boolean></head></sparql>"
            } else if (query.isConstructType) {
                val resultModel = qexec.execConstruct()
                val query2 = QueryFactory.create("select ?s ?p ?o where{?s ?p ?o}")
                val qexec2 = QueryExecutionFactory.create(query2, resultModel)
                val results = qexec2.execSelect()
                val stream = ByteArrayOutputStream()
                ResultSetFormatter.outputAsXML(stream, results)
                res = String(stream.toByteArray())
            } else if (query.isDescribeType) {
                val resultModel = qexec.execDescribe()
                val query2 = QueryFactory.create("select ?s ?p ?o where{?s ?p ?o}")
                val qexec2 = QueryExecutionFactory.create(query2, resultModel)
                val results = qexec2.execSelect()
                val stream = ByteArrayOutputStream()
                ResultSetFormatter.outputAsXML(stream, results)
                res = String(stream.toByteArray())
            } else if (query.isJsonType) {
            } else if (query.isConstructQuad) {
            }
            if (logging) {
                val plan = QueryExecutionFactory.createPlan(query, dataset.asDatasetGraph(), null)
                val op = plan.op
                val op2 = Optimize.optimize(op, qexec.context)
                //println({ op2 })
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
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
        val queryString = File(fileName).readText()
        JenaWrapper.execQuery(queryString)
    }
}
