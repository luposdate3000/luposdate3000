package lupos

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import io.ktor.http.content.*
import io.ktor.utils.io.*
import io.ktor.utils.io.charsets.*
import io.ktor.utils.io.core.*
import kotlin.native.concurrent.*
import kotlin.random.*
import kotlinx.coroutines.*
import lupos.s00misc.*


class ExceptionJenaBug(message: String) : Exception(message)

class JenaRequest {
    var containsStringDatatypeQueries = false
    fun finalize() {
        client.close()
    }

    val client = HttpClient() {
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
    }

    constructor() {
        var message: String? = null
        CoroutinesHelper.runBlock {
            try {
                message = client.post<String> {
                    url(URLBuilder(URLProtocol.HTTP, "localhost", 3030, encodedPath = "/$/datasets").build())
                    contentType(ContentType.Application.FormUrlEncoded.withCharset(Charsets.UTF_8))
                    body = "dbName=sp2b&dbType=mem"
                }
            } catch (e: Throwable) {
                if (e.message != "Client request(http://localhost:3030/$/datasets) invalid: 409 Name already registered /sp2b") {
                    throw e
                }
            }
        }
        requestUpdate("DROP SILENT ALL")
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
        if (query.contains("<http://www.w3.org/2001/XMLSchema#string>"))
            containsStringDatatypeQueries = true
        if (containsStringDatatypeQueries)
            throw ExceptionJenaBug("queryWithStringDatatype")
        var message: String? = null
        CoroutinesHelper.runBlock {
            message = client.post<String> {
                url(URLBuilder(URLProtocol.HTTP, "localhost", 3030, encodedPath = "/sp2b/update").build())
                contentType(ContentType.Application.FormUrlEncoded.withCharset(Charsets.UTF_8))
                body = listOf("update" to query).formUrlEncode()
            }
        }
        return XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#").addContent(XMLElement("head")).addContent(XMLElement("results").addContent(XMLElement("result")))
    }

    fun requestQuery(query: String): XMLElement {
        if (query.contains("CONSTRUCT"))
            throw ExceptionJenaBug("queryWithConstruct")
        if (query.contains("<http://www.w3.org/2001/XMLSchema#string>"))
            containsStringDatatypeQueries = true
        if (containsStringDatatypeQueries)
            throw ExceptionJenaBug("queryWithStringDatatype")
        var message: String? = null
        CoroutinesHelper.runBlock {
            message = client.post<String> {
                url(URLBuilder(URLProtocol.HTTP, "localhost", 3030, encodedPath = "/sp2b/query").build())
                contentType(ContentType.Application.FormUrlEncoded.withCharset(Charsets.UTF_8))
                body = listOf("query" to query).formUrlEncode()
            }
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
