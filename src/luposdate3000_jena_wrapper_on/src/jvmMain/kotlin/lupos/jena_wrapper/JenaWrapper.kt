/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.jena_wrapper
import lupos.shared.JenaBugException
import lupos.shared.myPrintStackTrace
import org.apache.jena.query.ARQ
import org.apache.jena.query.DatasetFactory
import org.apache.jena.query.QueryExecutionFactory
import org.apache.jena.query.QueryFactory
import org.apache.jena.query.ResultSetFormatter
import org.apache.jena.sparql.algebra.optimize.Optimize
import org.apache.jena.sparql.mgt.Explain
import org.apache.jena.update.UpdateAction
import java.io.ByteArrayOutputStream
import java.io.File

public actual object JenaWrapper {
    @JvmField
    internal var dataset = DatasetFactory.createTxnMem()!!
    public actual fun dropAll() {
        updateQuery("DROP SILENT ALL")
    }

    private fun checkExceptions(queryString: String) {
        if (queryString.contains("STRDT")) {
            throw JenaBugException("jena implementation is wrong in combination with STRDT when there are typed literals matching the new specified type")
        }
        if (queryString.contains("STRLANG")) {
            throw JenaBugException("jena implementation changes the language to uppercase, and is wrong, when there already are language tagged literals")
        }
    }

    private fun updateQuery(queryString: String) {
        checkExceptions(queryString)
        try {
            UpdateAction.parseExecute(queryString, dataset)
        } catch (e: Throwable) {
            e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_jena_wrapper_on/src/jvmMain/kotlin/lupos/jena_wrapper/JenaWrapper.kt:51"/*SOURCE_FILE_END*/)
        }
    }

    public actual fun execQuery(queryString: String): String {
        return execQuery(queryString, true)
    }

    private fun execQuery(queryString: String, logging: Boolean): String {
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
            when {
                query.isSelectType -> {
                    val results = qexec.execSelect()
                    val stream = ByteArrayOutputStream()
                    ResultSetFormatter.outputAsXML(stream, results)
                    res = String(stream.toByteArray())
                }
                query.isAskType -> {
                    res = "<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\"><head><boolean>${qexec.execAsk()}</boolean></head></sparql>"
                }
                query.isConstructType -> {
                    val resultModel = qexec.execConstruct()
                    val query2 = QueryFactory.create("select ?s ?p ?o where{?s ?p ?o}")
                    val qexec2 = QueryExecutionFactory.create(query2, resultModel)
                    val results = qexec2.execSelect()
                    val stream = ByteArrayOutputStream()
                    ResultSetFormatter.outputAsXML(stream, results)
                    res = String(stream.toByteArray())
                }
                query.isDescribeType -> {
                    val resultModel = qexec.execDescribe()
                    val query2 = QueryFactory.create("select ?s ?p ?o where{?s ?p ?o}")
                    val qexec2 = QueryExecutionFactory.create(query2, resultModel)
                    val results = qexec2.execSelect()
                    val stream = ByteArrayOutputStream()
                    ResultSetFormatter.outputAsXML(stream, results)
                    res = String(stream.toByteArray())
                }
            }
            if (logging) {
                val plan = QueryExecutionFactory.createPlan(query, dataset.asDatasetGraph(), null)
                val op = plan.op
                Optimize.optimize(op, qexec.context)
            }
        } catch (e: Throwable) {
            e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_jena_wrapper_on/src/jvmMain/kotlin/lupos/jena_wrapper/JenaWrapper.kt:106"/*SOURCE_FILE_END*/)
        }
        return res
    }

    public actual fun loadFromFile(fileNames: String, graph: String) {
        var graph2 = graph
        if (!graph2.startsWith("<")) {
            graph2 = "<$graph2>"
        }
        val updateString = StringBuilder()
        for (fileName in fileNames.split(";")) {
            updateString.append("load <file://${File(fileName).absolutePath}> INTO GRAPH $graph2 ;")
        }
        updateQuery(updateString.toString())
    }

    public actual fun loadFromFile(fileNames: String) {
        val updateString = StringBuilder()
        for (fileName in fileNames.split(";")) {
            updateString.append("load <file://${File(fileName).absolutePath}> ;")
        }
        updateQuery(updateString.toString())
    }
}
