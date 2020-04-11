import java.io.*
import org.apache.jena.query.*
import org.apache.jena.rdf.model.*
import org.apache.jena.riot.*
import org.apache.jena.update.*
import org.apache.jena.util.*

object JenaWrapper {
    var dataset = DatasetFactory.createTxnMem()
    fun dropAll() {
    }

    fun updateQuery(queryString: String) {
    }

    fun execQuery(queryString: String): String {
        var res = ""
        return res
    }

    fun loadFromFile(fileName: String) {
    }

    fun execQueryFile(fileName: String) {
    }
}
