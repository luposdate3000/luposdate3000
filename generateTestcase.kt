import lupos.s00misc.*
import lupos.*

fun main(args: Array<String>) {
    if (args.size < 1) {
        printUsage()
    } else {
        var mode = GenerateTestCaseMode.Single
        try {
            mode = GenerateTestCaseMode.valueOf(args[0])
        } catch (e: Throwable) {
            printUsage()
            return
        }
        when (mode) {
            GenerateTestCaseMode.Single -> {
                if (args.size < 6) {
                    printUsage()
                } else {
                    generateTestcase(args[1], args[2], args[3], args[4], args[5])
                }
            }
            GenerateTestCaseMode.TestSuite -> {
                SparqlTestSuite.prefixDirectory = args[1]
                val converter = SparqlTestSuiteConverter(args[1], args[2])
                converter.testMain()
            }
            else -> {
                printUsage()
            }
        }
    }
}

enum class GenerateTestCaseMode {
    Single,
    TestSuite
}

fun printUsage() {
    println("usage ./generateTestcase.kts Single query_input_file.n3 query_file.sparql query_output_file.srx output_folder query_name")
    println("usage ./generateTestcase.kts TestSuite resource_folder output_folder")
}

fun helper_clean_string(s: String): String {
    var res: String = s
    while (true) {
        val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res)
        if (match == null) {
            break
        }
        val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
        res = res.replace(match.value, replacement)
    }
    return res
}

class SparqlTestSuiteConverter(resource_folder: String, val output_folder: String) : SparqlTestSuite() {
    var counter = 0

    init {
        prefixDirectory = resource_folder
    }

    override fun parseSPARQLAndEvaluate(executeJena: Boolean, testName: String, expectedResult: Boolean, queryFile: String, inputDataFileName: String?, resultDataFileName: String?, services: List<Map<String, String>>?, inputDataGraph: MutableList<MutableMap<String, String>>, outputDataGraph: MutableList<MutableMap<String, String>>): Boolean {
        if (inputDataFileName == null) {
            return false
        }
        if (resultDataFileName == null) {
            return false
        }
        generateTestcase(inputDataFileName, queryFile, resultDataFileName, output_folder + "/${counter++}/", testName)
        return true
    }
}

fun generateTestcase(query_input_file: String, query_file: String, query_output_file: String, output_folder: String, query_name: String) {
    println("generateTestcase.kts $query_input_file $query_file $query_output_file $output_folder $query_name")
    java.io.File(output_folder).deleteRecursively()
    java.io.File(output_folder).mkdirs()
    java.io.File(output_folder + "/query.sparql").printWriter().use { out ->
        java.io.File(query_file).forEachLine { line ->
            if (line.length > 0) {
                out.println(line)
            }
        }
    }
    val dict = mutableMapOf<String, Int>()
    var dict_bnode_count = 0
    val outDictionary = java.io.DataOutputStream(java.io.BufferedOutputStream(java.io.FileOutputStream(output_folder + "/query.dictionary")))
    val outTriples = java.io.DataOutputStream(java.io.BufferedOutputStream(java.io.FileOutputStream(output_folder + "/query.triples")))
    val data = XMLElement.parseFromAny(java.io.File(query_input_file).readText(), query_input_file)!!
    val row = IntArray(3)
    for (node in data["results"]!!.childs) {
        for (i in 0 until 3) {
            row[i] = -1
        }
        for (v in node.childs) {
            val name = v.attributes["name"]
            val idx: Int
            when (name) {
                "s" -> idx = 0
                "p" -> idx = 1
                "o" -> idx = 2
                else -> throw Exception("unknown name '$name'")
            }
            val child = v.childs.first()
            val content = helper_clean_string(child.content)
            val datatype = child.attributes["datatype"]
            val lang = child.attributes["xml:lang"]
            if ((datatype != null) && (lang != null)) {
                throw Exception("overspecification")
            }
            val s: String
            when {
                child.tag == "uri" -> s = "<" + content + ">"
                child.tag == "literal" && datatype != null -> s = "\"" + content + "\"^^<" + datatype + ">"
                child.tag == "literal" && lang != null -> s = "\"" + content + "\"@" + lang
                child.tag == "bnode" -> s = "_:" + content
                else -> s = "\"" + content + "\""
            }
            val id = dict[s]
            if (id != null) {
                row[idx] = id
            } else {
                val id2 = dict.size
                row[idx] = id2
                dict[s] = id2
                val tmp: ByteArray
                if (s.startsWith("_:")) {
                    tmp = "_:b${dict_bnode_count++}\n".encodeToByteArray()
                } else {
                    tmp = "$s\n".encodeToByteArray()
                }
                outDictionary.write(tmp)
            }
        }
        for (i in 0 until 3) {
            outTriples.writeInt(row[i])
        }
    }
    outTriples.close()
    var target = XMLElement.parseFromAny(java.io.File(query_output_file).readText(), query_output_file)!!
    val variablesSet = mutableSetOf<String>()
    for (node in target["results"]!!.childs) {
        for (v in node.childs) {
            variablesSet.add(v.attributes["name"]!!)
        }
    }
    val variables = variablesSet.toTypedArray()
    val outStat = java.io.DataOutputStream(java.io.BufferedOutputStream(java.io.FileOutputStream(output_folder + "/query.stat")))
    val outResult = java.io.DataOutputStream(java.io.BufferedOutputStream(java.io.FileOutputStream(output_folder + "/query.result")))
    outStat.writeInt(variables.size)
    for (i in 0 until variables.size) {
        val tmp = variables[i].encodeToByteArray()
        outStat.writeInt(tmp.size)
        outStat.write(tmp)
    }
    val tmp2 = query_name.encodeToByteArray()
    outStat.writeInt(tmp2.size)
    outStat.write(tmp2)
    val row_out = IntArray(variables.size)
    for (node in target["results"]!!.childs) {
        for (i in 0 until variables.size) {
            row_out[i] = -1
        }
        for (v in node.childs) {
            val name = v.attributes["name"]
            val idx = variables.indexOf(name)
            if (idx < 0) {
                throw Exception("unknown name '$name'")
            }
            val child = v.childs.first()
            val content = helper_clean_string(child.content)
            val datatype = child.attributes["datatype"]
            val lang = child.attributes["xml:lang"]
            if ((datatype != null) && (lang != null)) {
                throw Exception("overspecification")
            }
            val s: String
            when {
                child.tag == "uri" -> s = "<" + content + ">"
                child.tag == "literal" && datatype != null -> s = "\"" + content + "\"^^<" + datatype + ">"
                child.tag == "literal" && lang != null -> s = "\"" + content + "\"@" + lang
                child.tag == "bnode" -> s = "_:" + content
                else -> s = "\"" + content + "\""
            }
            val id = dict[s]
            if (id != null) {
                row_out[idx] = id
            } else {
                val id2 = dict.size
                row_out[idx] = id2
                dict[s] = id2
                val tmp: ByteArray
                if (s.startsWith("_:")) {
                    tmp = "_:b${dict_bnode_count++}\n".encodeToByteArray()
                } else {
                    tmp = "$s\n".encodeToByteArray()
                }
                outDictionary.write(tmp)
            }
        }
        for (i in 0 until variables.size) {
            outResult.writeInt(row_out[i])
        }
    }
    outResult.close()
    outStat.close()
    outDictionary.close()
}
