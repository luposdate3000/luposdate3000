package lupos.s00misc

import lupos.s00misc.File
import lupos.s02buildSyntaxTree.sparql1_1.parseSPARQL
import lupos.SparqlTestSuite

class SparqlTestSuiteConverter(resource_folder: String, val output_folder: String) : SparqlTestSuite() {
    var counter = 0
    var lastFile: String = ""

    init {
        prefixDirectory = resource_folder + "/"
    }

    override fun parseSPARQLAndEvaluate(//
            executeJena: Boolean,
            testName: String,//
            expectedResult: Boolean,//
            queryFile: String,//
            inputDataFileName: String?,//
            resultDataFileName: String?,//
            services: List<Map<String, String>>?,//
            inputDataGraph: MutableList<MutableMap<String, String>>,//
            outputDataGraph: MutableList<MutableMap<String, String>>//
    ): Boolean {
        if (services != null && services.size > 0) {
            return false
        }
        if (inputDataGraph.size > 0) {
            return false
        }
        if (inputDataFileName == null) {
            return false
        }
        var inputFile = inputDataFileName
        if (inputFile == "#keep-data#") {
            inputFile = lastFile
        }
        lastFile = inputFile
        var outputFile = resultDataFileName
        var mode = BinaryTestCaseOutputMode.SELECT_QUERY_RESULT
        if (inputDataFileName == null) {
            return false
        }
        if (outputFile == null) {
            if (outputDataGraph.size == 1 && outputDataGraph[0]["name"] == "") {
                mode = BinaryTestCaseOutputMode.MODIFY_RESULT
                outputFile = outputDataGraph[0]["filename"]
            } else {
                return false
            }
        } else {
            if (outputDataGraph.size > 0) {
                return false
            }
        }
        var tmp = BinaryTestCase.generateTestcase(inputFile, queryFile, outputFile!!, output_folder + "/${counter++}/", testName, mode)
        if (!tmp) {
            counter--
        }
        return true
    }
}
