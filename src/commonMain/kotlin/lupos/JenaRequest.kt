package lupos

import kotlin.native.concurrent.*
import kotlin.random.*
import kotlinx.coroutines.*
import lupos.s00misc.*
import lupos.s14endpoint.*

class ExceptionJenaBug(message: String) : Exception(message)

class JenaRequest {
    var containsStringDatatypeQueries = false
    fun finalize() {
    }


    constructor() {
        var message: String? = null
println("a")
        CoroutinesHelper.runBlock {
println("b")
            try {
		message=EndpointClientImpl.requestPostString("http://localhost:3030/$/datasets","dbName=sp2b&dbType=mem")
println("c")
            } catch (e: Throwable) {
println("f")
            }
println("g")
        }
println("d")
        requestUpdate("DROP SILENT ALL")
println("e")
    }


    fun insertDataIntoGraph(graph: String?, data: XMLElement) {
        var query = "INSERT DATA{\n"
        if (graph != null)
            query += "GRAPH <$graph> {"
        if (data.tag != "sparql")
            throw Exception("can only parse sparql xml into an iterator")
        for (v in data["head"]!!.childs)
            for (node in data["results"]!!.childs) {
                val result = mutableMapOf<String, String>()
                for (v in node.childs) {
                    val name = v.attributes["name"]!!
                    val child = v.childs.first()
                    val content = child.content
                    val value = when {
                        child.tag == "uri" -> "<" + content + ">"
                        child.tag == "literal" && child.attributes["datatype"] != null -> "\"" + content + "\"^^<" + child.attributes["datatype"] + ">"
                        child.tag == "literal" && child.attributes["xml:lang"] != null -> "\"" + content + "\"@" + child.attributes["xml:lang"]
                        child.tag == "bnode" -> "_:" + content
                        else -> "\"" + content + "\""
                    }
                    result[name] = value
                }
                query += result["s"] + " " + result["p"] + " " + result["o"] + ".\n"
            }
        if (graph != null)
            query += "}\n"
        query += "}"
        requestUpdate(query)
    }

    fun requestUpdate(query: String): XMLElement {
        if (query.contains("UUID"))
            throw ExceptionJenaBug("uuid will never match")
        if (query.contains("<http://www.w3.org/2001/XMLSchema#string>"))
            containsStringDatatypeQueries = true
        if (containsStringDatatypeQueries)
            throw ExceptionJenaBug("queryWithStringDatatype")
        var message: String? = null
        CoroutinesHelper.runBlock {
	message=EndpointClientImpl.requestPostString("http://localhost:3030/sp2b/update",EndpointClientImpl.encodeParam("update",query))
        }
        return XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#").addContent(XMLElement("head")).addContent(XMLElement("results").addContent(XMLElement("result")))
    }

    fun requestQuery(query: String): XMLElement {
        if (query.contains("UUID"))
            throw ExceptionJenaBug("uuid will never match")
        if (query.contains("CONSTRUCT"))
            throw ExceptionJenaBug("queryWithConstruct")
        if (query.contains("<http://www.w3.org/2001/XMLSchema#string>"))
            containsStringDatatypeQueries = true
        if (containsStringDatatypeQueries)
            throw ExceptionJenaBug("queryWithStringDatatype")
        var message: String? = null
        CoroutinesHelper.runBlock {
message=EndpointClientImpl.requestPostString("http://localhost:3030/sp2b/query",EndpointClientImpl.encodeParam("query",query))
        }
        println(message)
        return XMLElement.parseFromJson(message!!)!!.first()
    }

    fun requestAny(query: String): XMLElement {
        val tmp = query.toLowerCase()
        if (tmp.contains("add"))
            return requestUpdate(query)
        return requestQuery(query)
    }

}
