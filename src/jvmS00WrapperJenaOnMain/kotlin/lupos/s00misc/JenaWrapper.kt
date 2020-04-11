import java.io.*
import org.apache.jena.query.*
import org.apache.jena.rdf.model.*
import org.apache.jena.riot.*
import org.apache.jena.update.*
import org.apache.jena.util.*

object JenaWrapper {
    var dataset = DatasetFactory.createTxnMem()
    fun dropAll() {
        execQuery("DROP SILENT ALL")
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
                res = "" + qexec.execAsk()
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

    fun loadFromFile(fileName: String) {
        val updateString = "load <file://${fileName}>"
        JenaWrapper.updateQuery(updateString)
    }

    fun execQueryFile(fileName: String) {
        val queryString = File(fileName).readText()
        JenaWrapper.execQuery(queryString)
    }
}
