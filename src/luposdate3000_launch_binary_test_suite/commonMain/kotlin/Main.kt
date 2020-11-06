import lupos.s00misc.BinaryTestCase
import lupos.s00misc.BinaryTestCaseOutputMode
import lupos.s00misc.Parallel
import lupos.s00misc.XMLElement
import lupos.s00misc.XMLElementFromCsv
import lupos.s00misc.XMLElementFromJson
import lupos.s00misc.XMLElementFromN3
import lupos.s00misc.XMLElementFromTsv
import lupos.s00misc.XMLElementFromXML
import lupos.s15tripleStoreDistributed.distributedTripleStore
import lupos.s15tripleStoreDistributed.DistributedTripleStore

internal fun main(args: Array<String>): Unit {
    distributedTripleStore = DistributedTripleStore()
    XMLElement.parseFromAnyRegistered["n3"] = XMLElementFromN3()
    XMLElement.parseFromAnyRegistered["ttl"] = XMLElementFromN3()
    XMLElement.parseFromAnyRegistered["srx"] = XMLElementFromXML()
    XMLElement.parseFromAnyRegistered["srj"] = XMLElementFromJson()
    XMLElement.parseFromAnyRegistered["csv"] = XMLElementFromCsv()
    XMLElement.parseFromAnyRegistered["tsv"] = XMLElementFromTsv()
    Parallel.runBlocking {
        if (args.size > 0 && args[0] == "--generate") {
            if (args.size < 7) {
                println("usage xyz.jar --generate 'query_input_file' 'query_file' 'query_output_file' 'output_folder' 'query_name' [SELECT_QUERY_RESULT|MODIFY_RESULT]")
            } else {
                BinaryTestCase.generateTestcase(args[1], args[2], args[3], args[4], args[5], BinaryTestCaseOutputMode.valueOf(args[6]))
            }
        } else if (args.size == 1) {
            BinaryTestCase.executeAllTestCase(args[0])
        } else if (args.size > 0) {
            BinaryTestCase.executeTestCase(args[0] + "/" + args[1])
        } else {
            BinaryTestCase.executeAllTestCase()
        }
    }
}
