package lupos.p2p

import com.soywiz.korio.net.http.createHttpClient
import com.soywiz.korio.net.http.createHttpServer
import com.soywiz.korio.net.http.Http
import com.soywiz.korio.net.http.HttpServer
import lupos.misc.kotlinStacktrace

class XMLElement(val tag: String) {
    // https://regex101.com
    companion object {
        val XMLHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"

        fun parseFromXml(xml: String): List<XMLElement>? {
            val x = xml.replace("\n", "").replace("\r", "")
            val res = mutableListOf<XMLElement>()
            var lastindex = 0
            """((<([a-zA-Z]+)([^>]*?)>(.*?)<\/\3>)|(<([a-zA-Z]+)([^>]*?)>)|(<\?.*?\?>)|(<!--.*?-->))?""".toRegex().findAll(x).forEach() { child ->
                var value = child.value
                if (value.length > 0 && !value.startsWith("<?") && !value.startsWith("<!--") && child.range.start >= lastindex) {

                    var nodeName = ""
                    if (child.groups[3] != null)
                        nodeName = child.groups[3]!!.value
                    if (child.groups[7] != null)
                        nodeName = child.groups[7]!!.value
                    val childNode = XMLElement(nodeName)
                    res.add(childNode)

                    var nodeAttributes = ""
                    if (child.groups[4] != null)
                        nodeAttributes = child.groups[4]!!.value
                    if (child.groups[8] != null)
                        nodeAttributes = child.groups[8]!!.value

                    """([^\s]*?)="(([^\\"]*(\\"|\\)*)*)"""".toRegex().findAll(nodeAttributes).forEach() { attrMatch ->
                        if (attrMatch.groups[1] != null && attrMatch.groups[2] != null)
                            childNode.addAttribute(attrMatch.groups[1]!!.value, attrMatch.groups[2]!!.value)
                    }
                    var content = ""
                    if (child.groups[5] != null)
                        content = child.groups[5]!!.value
                    if (!child.value.endsWith("</${nodeName}>") && !child.value.endsWith("/>")) {
                        val search = "</${nodeName}>"
                        val idx2 = x.indexOf(search, child.range.last)
                        content = x.substring(child.range.last, idx2 + search.length)
                        lastindex = idx2
                    }
                    if (content != "") {
                        val tmp = parseFromXml(content)
                        if (tmp == null)
                            childNode.addContent(content)
                        else
                            childNode.addContent(tmp)
                    }
                }
            }
            if (res.isEmpty() && !xml.isEmpty())
                return null
            return res
        }
    }

    val attributes = mutableMapOf<String, String>()
    var content: String = ""
    val childs = mutableListOf<XMLElement>()
    fun addAttribute(name: String, value: String): XMLElement {
        attributes[name] = value
        return this
    }

    fun addContent(content: String): XMLElement {
        if (!childs.isEmpty())
            throw Exception("either content or subchilds must be empty")
        this.content += content
        return this
    }

    fun addContent(childs: Collection<XMLElement>): XMLElement {
        if (!content.isEmpty())
            throw Exception("either content or subchilds must be empty")
        this.childs.addAll(childs)
        return this
    }

    fun addContent(child: XMLElement): XMLElement {
        if (!content.isEmpty())
            throw Exception("either content or subchilds must be empty")
        childs.add(child)
        return this
    }

    fun addContent(childs: Collection<String>, childTag: String): XMLElement {
        for (c in childs) {
            addContent(XMLElement(childTag).addContent(c).toString())
        }
        return this
    }

    override fun toString(): String {
        var res = "<${tag}"
        for ((k, v) in attributes) {
            res += " ${k}=\"${v}\""
        }
        if (content.isEmpty() && childs.isEmpty()) {
            res += "/>"
        } else {
            res += ">"
            for (c in childs) {
                res += c.toString()
            }
            res += "${content}</${tag}>"
        }
        return res
    }

    fun toPrettyString(indention: String = ""): String {
        var res = "${indention}<${tag}"
        for ((k, v) in attributes) {
            res += " ${k}=\"${v}\""
        }
        if (content.isEmpty() && childs.isEmpty()) {
            res += "/>\n"
        } else {
            if (content.isEmpty()) {
                res += ">\n"
                for (c in childs) {
                    res += c.toPrettyString(indention + "\t")
                }
                res += "${indention}</${tag}>\n"
            } else {
                res += ">${content}</${tag}>\n"
            }
        }
        return res
    }

}

object P2P {
    val REQUEST_PEERS_LIST = arrayOf("/peers/list")
    val REQUEST_PEERS_JOIN = arrayOf("/peers/join", "hostname")
    var hostname = "localhost"
    var port = 8080
    val knownClients = mutableSetOf<String>()
    var server: HttpServer? = null
    val client = createHttpClient()
    var fullname = hostname + ":" + port

    suspend fun process_peers_list(): String {
        return XMLElement("servers").addContent(knownClients, "server").toPrettyString()
    }

    suspend fun process_peers_join(hostname: String?): String {
        if (hostname != null && hostname != "localhost")
            knownClients.add(hostname)
        return ""
    }

    suspend fun myRequestHandler(request: HttpServer.Request) {
        println("listen::Request")
        val params = request.getParams
        println(params)
        println(request.path)
        println(request.method)
        request.replaceHeader("Connection", "close")
        request.replaceHeader("Content-Type", "text/html")
        var response = XMLElement.XMLHeader
        try {
            when (request.path) {
                REQUEST_PEERS_LIST[0] -> response = response + process_peers_list()
                REQUEST_PEERS_JOIN[0] -> response = response + process_peers_join(params[REQUEST_PEERS_JOIN[1]]?.first())
                else -> request.setStatus(404)
            }
        } catch (e: Throwable) {
            e.kotlinStacktrace()
            response = e.toString()
            request.setStatus(404)
        }
        request.end(response)
    }

    suspend fun start(bootstrap: String?) {
        fullname = hostname + ":" + port
        knownClients.add(fullname)
        if (bootstrap != null && bootstrap != "$hostname:$port") {
            var response = client.request(Http.Method.GET, "http://${bootstrap}${REQUEST_PEERS_LIST[0]}")
            var responseString = response.readAllString()
            XMLElement.parseFromXml(responseString)?.forEach() { root ->
                if (root.tag == "servers") {
                    root.childs.forEach() { server ->
                        if (server.tag == "server") {
                            knownClients.add(server.content)
                            client.request(Http.Method.GET, "http://${server.content}${REQUEST_PEERS_JOIN[0]}?${REQUEST_PEERS_JOIN[1]}=${fullname}")
                        }
                    }
                }
            }
        }
        server = createHttpServer().listen(port, hostname, ::myRequestHandler)
    }
}
