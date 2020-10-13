package lupos.s00misc

import lupos.SparqlTestSuite
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s04logicalOperators.Query
import lupos.s16network.ServerCommunicationSend
import lupos.s15tripleStoreDistributed.DistributedTripleStore

enum class BinaryTestCaseOutputMode {
    SELECT_QUERY_RESULT,
    SELECT_QUERY_RESULT_COUNT,
    ASK_QUERY_RESULT,
    MODIFY_RESULT
}

enum class BinaryTestCaseGenerateTestCaseMode {
    Single,
    TestSuite
}

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

object BinaryTestCase {
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

fun executeAllTestCase(folder:String="resources/binary/"){
File(folder).walk{it->
executeTestCase(it)
}
}
    fun executeTestCase(query_folder: String): Boolean {
        val targetStat = java.io.DataInputStream(java.io.BufferedInputStream(java.io.FileInputStream(query_folder + "/query.stat")))
        val mode_id = targetStat.readInt()
        val mode = BinaryTestCaseOutputMode.values()[mode_id]
        val variables = mutableListOf<String>()
        var target_result_count = 0
        when (mode) {
            BinaryTestCaseOutputMode.SELECT_QUERY_RESULT, BinaryTestCaseOutputMode.SELECT_QUERY_RESULT_COUNT, BinaryTestCaseOutputMode.MODIFY_RESULT -> {
                val variablesSize = targetStat.readInt()
                for (i in 0 until variablesSize) {
                    val len = targetStat.readInt()
                    val buf = ByteArray(len)
                    val read = targetStat.read(buf, 0, len)
                    if (read < len) {
                        throw Exception("not enough data available")
                    }
                    variables.add(buf.decodeToString())
                }
                target_result_count = targetStat.readInt()
            }
        }
        val len = targetStat.readInt()
        val buf = ByteArray(len)
        val read = targetStat.read(buf, 0, len)
        if (read < len) {
            throw Exception("not enough data available")
        }
        val query_name = buf.decodeToString()
        val dictionarySize = targetStat.readInt()
        val target_input_count = targetStat.readInt()
        targetStat.close()
        val targetDictionary = java.io.DataInputStream(java.io.BufferedInputStream(java.io.FileInputStream(query_folder + "/query.dictionary")))
        val targetDict = mutableMapOf<String, Int>()
        val mapping_target_to_live = IntArray(dictionarySize)
        val mapping_live_to_target = mutableMapOf<Int, Int>(ResultSetDictionary.undefValue to -1, ResultSetDictionary.errorValue to -1, ResultSetDictionary.nullValue to -1)
        for (i in 0 until dictionarySize) {
            val len = targetDictionary.readInt()
            val buf = ByteArray(len)
            val read = targetDictionary.read(buf, 0, len)
            if (read < len) {
                throw Exception("not enough data available")
            }
            val s = buf.decodeToString()
            targetDict[s] = i
            val tmp = nodeGlobalDictionary.createValue(s)
            mapping_target_to_live[i] = tmp
            mapping_live_to_target[tmp] = i
        }
        targetDictionary.close()
        val targetTriples = java.io.DataInputStream(java.io.BufferedInputStream(java.io.FileInputStream(query_folder + "/query.triples")))
        val query1 = Query()
        ServerCommunicationSend.graphClearAll(query1)
        query1.commit()
        val query2 = Query()
        var store = DistributedTripleStore.getDefaultGraph(query2)
        store.bulkImport { bulk ->
            for (i in 0 until target_input_count) {
                val s = targetTriples.readInt()
                val p = targetTriples.readInt()
                val o = targetTriples.readInt()
                bulk.insert(mapping_target_to_live[s], mapping_target_to_live[p], mapping_target_to_live[o])
            }
        }
//TODO verify data is loaded correctly
        val targetResult = java.io.DataInputStream(java.io.BufferedInputStream(java.io.FileInputStream(query_folder + "/query.result")))
//TODO exwcute query and compare result
        targetTriples.close()
        targetResult.close()
return true
    }

    fun generateTestcase(query_input_file: String, query_file: String, query_output_file: String, output_folder: String, query_name: String, output_mode_tmp: BinaryTestCaseOutputMode): Boolean {
        println("generateTestcase.kts $query_input_file $query_file $query_output_file $output_folder $query_name $output_mode_tmp")
        try {
            var output_mode = output_mode_tmp
            java.io.File(output_folder).deleteRecursively()
            java.io.File(output_folder).mkdirs()
            var containsOrderBy = false
            java.io.File(output_folder + "/query.sparql").printWriter().use { out ->
                java.io.File(query_file).forEachLine { line ->
                    if (line.length > 0) {
                        if (line.toLowerCase().contains("order")) {
                            containsOrderBy = true
                        }
                        out.println(line)
                    }
                }
            }
            val dict = mutableMapOf<String, Int>()
            var dict_bnode_count = 0
            val outDictionary = java.io.DataOutputStream(java.io.BufferedOutputStream(java.io.FileOutputStream(output_folder + "/query.dictionary")))
            val outTriples = java.io.DataOutputStream(java.io.BufferedOutputStream(java.io.FileOutputStream(output_folder + "/query.triples")))
            val data = XMLElement.parseFromAny(java.io.File(query_input_file).readText(), query_input_file)!!
            var input_counter = 0
            for (node in data["results"]!!.childs) {
                input_counter++
                val row = IntArray(3) { -1 }
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
                            tmp = "_:b${dict_bnode_count++}".encodeToByteArray()
                        } else {
                            tmp = s.encodeToByteArray()
                        }
                        outDictionary.writeInt(tmp.size)
                        outDictionary.write(tmp)
                    }
                }
                for (i in 0 until 3) {
                    outTriples.writeInt(row[i])
                }
            }
            outTriples.close()
            var target = XMLElement.parseFromAny(java.io.File(query_output_file).readText(), query_output_file)!!
            if (target["results"] == null && target["boolean"] != null) {
                output_mode = BinaryTestCaseOutputMode.ASK_QUERY_RESULT
            }
            val outStat = java.io.DataOutputStream(java.io.BufferedOutputStream(java.io.FileOutputStream(output_folder + "/query.stat")))
            val outResult = java.io.DataOutputStream(java.io.BufferedOutputStream(java.io.FileOutputStream(output_folder + "/query.result")))
            var resultCounter = 0
            when (output_mode) {
                BinaryTestCaseOutputMode.SELECT_QUERY_RESULT, BinaryTestCaseOutputMode.SELECT_QUERY_RESULT_COUNT, BinaryTestCaseOutputMode.MODIFY_RESULT -> {
                    val variablesSet = mutableSetOf<String>()
                    for (node in target["results"]!!.childs) {
                        for (v in node.childs) {
                            variablesSet.add(v.attributes["name"]!!)
                        }
                    }
                    val variables = variablesSet.toTypedArray()
                    if (variables.size == 0) {
                        output_mode = BinaryTestCaseOutputMode.SELECT_QUERY_RESULT_COUNT
                    }
                    outStat.writeInt(output_mode.ordinal)
                    outStat.writeInt(variables.size)
                    for (i in 0 until variables.size) {
                        val tmp = variables[i].encodeToByteArray()
                        outStat.writeInt(tmp.size)
                        outStat.write(tmp)
                    }
                    var allRows = mutableListOf<IntArray>()
                    for (node in target["results"]!!.childs) {
                        val row_out = IntArray(variables.size) { -1 }
                        resultCounter++
                        for (v in node.childs) {
                            val name = v.attributes["name"]
                            val idx = variables.indexOf(name)
                            if (idx < 0) {
                                throw Exception("unknown name '$name'")
                            }
                            if (v.childs.size > 0) {
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
                                        tmp = "_:b${dict_bnode_count++}".encodeToByteArray()
                                    } else {
                                        tmp = "$s".encodeToByteArray()
                                    }
                                    outDictionary.writeInt(tmp.size)
                                    outDictionary.write(tmp)
                                }
                            }
                        }
                        if (containsOrderBy) {
                            for (i in 0 until variables.size) {
                                outResult.writeInt(row_out[i])
                            }
                        } else {
                            allRows.add(row_out)
                        }
                    }
                    outStat.writeInt(resultCounter)
                    if (!containsOrderBy) {
                        allRows.sortWith(object : Comparator<IntArray> {
                            override fun compare(p1: IntArray, p2: IntArray): Int {
                                for (i in 0 until variables.size) {
                                    val tmp = p1[i].compareTo(p2[i])
                                    if (tmp != 0) {
                                        return tmp
                                    }
                                }
                                return 0
                            }
                        })
                        for (row2 in allRows) {
                            for (i in 0 until variables.size) {
                                outResult.writeInt(row2[i])
                            }
                        }
                    }
                }
                BinaryTestCaseOutputMode.ASK_QUERY_RESULT -> {
                    outStat.writeInt(output_mode.ordinal)
                    if (target["boolean"]!!.content.toLowerCase() == "true") {
                        outResult.writeInt(1)
                    } else {
                        outResult.writeInt(0)
                    }
                }
                else -> {
                    throw Exception("not implemented")
                }
            }
            val tmp2 = query_name.encodeToByteArray()
            outStat.writeInt(tmp2.size)
            outStat.write(tmp2)
            outStat.writeInt(dict.size)
            outStat.writeInt(input_counter)
            outResult.close()
            outStat.close()
            outDictionary.close()
            return true
        } catch (e: UnknownDataFile) {
            java.io.File(output_folder).deleteRecursively()
            return false
        }
    }
}
