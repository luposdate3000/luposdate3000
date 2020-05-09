package lupos.s00misc

import java.io.*
import lupos.s00misc.Coverage
import org.apache.jena.query.*
import org.apache.jena.rdf.model.*
import org.apache.jena.riot.*
import org.apache.jena.update.*
import org.apache.jena.util.*

object JenaWrapper {
    var dataset = DatasetFactory.createTxnMem()
    fun dropAll() {
        updateQuery("DROP SILENT ALL")
    }

    fun updateQuery(queryString: String) {
        try {
            UpdateAction.parseExecute(queryString, dataset)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    fun execQuery(queryString: String): String {
        var res = ""
        try {
            val query = QueryFactory.create(queryString)
            val qexec = QueryExecutionFactory.create(query, dataset)
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
                qexec.close()
            } else if (query.isDescribeType()) {
                val resultModel = qexec.execDescribe()
                qexec.close()
            } else if (query.isJsonType()) {
            } else if (query.isConstructQuad()) {
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return res
    }

    fun loadFromFile(fileNames: String, graph: String) {
        val updateString = StringBuilder()
        for (fileName in fileNames.split(";")) {
            updateString.append("load <file://${fileName}> INTO GRAPH $graph ;")
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
        val queryString = java.io.File(fileName).readText()
        JenaWrapper.execQuery(queryString)
    }
}
