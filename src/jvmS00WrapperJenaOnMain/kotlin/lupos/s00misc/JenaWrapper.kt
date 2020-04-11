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
            val qexec = QueryExecutionFactory.create(queryString, dataset)
            val results = qexec.execSelect()
            val stream = ByteArrayOutputStream()
            ResultSetFormatter.outputAsXML(stream, results)
            res = String(stream.toByteArray())
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
