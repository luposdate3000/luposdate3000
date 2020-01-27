package lupos.s00misc
import lupos.s02buildSyntaxTree.turtle.TurtleScanner
import lupos.s02buildSyntaxTree.turtle.TurtleParser
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import kotlin.math.abs


class XMLElement(val tag: String) {
    // https://regex101.com
    companion object {
        val XMLHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
        fun parseFromAny(data: String, filename: String): List<XMLElement>? {
            println(filename)
            when {
                filename.endsWith(".srx") -> return XMLElement.parseFromXml(data)
                filename.endsWith(".tsv") -> return XMLElement.parseFromTsv(data)
                filename.endsWith(".ttl") -> return XMLElement.parseFromTtl(data)
                filename.endsWith(".nt") -> return XMLElement.parseFromTtl(data)
                filename.endsWith(".n3") -> return XMLElement.parseFromTtl(data)
                filename.endsWith(".srj") -> return XMLElement.parseFromJson(data)
                else -> {
                    throw Exception("data parser :: file type '${filename}' unknown")
                }
            }
        }

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
                    """([^\s]*?)='([^']*)'""".toRegex().findAll(nodeAttributes).forEach() { attrMatch ->
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

        fun parseFromTtl(ttl: String): List<XMLElement>? {
            val res = mutableListOf<XMLElement>()
            val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
            res.add(nodeSparql)
            val nodeHead = XMLElement("head")
            val nodeResults = XMLElement("results")
            nodeSparql.addContent(nodeHead)
            nodeSparql.addContent(nodeResults)
            nodeHead.addContent(XMLElement("variable").addAttribute("name", "s"))
            nodeHead.addContent(XMLElement("variable").addAttribute("name", "p"))
            nodeHead.addContent(XMLElement("variable").addAttribute("name", "o"))
            val ltit = LookAheadTokenIterator(TurtleScanner(LexerCharIterator(ttl)), 3)
            val parser = TurtleParser({ triple ->
                val nodeResult = XMLElement("result")
                nodeResults.addContent(nodeResult)
                parseBindingFromString(nodeResult, triple.s.toN3String(), "s")
                parseBindingFromString(nodeResult, triple.p.toN3String(), "p")
                parseBindingFromString(nodeResult, triple.o.toN3String(), "o")
            }, ltit).turtleDoc()
            return res
        }

        fun parseBindingFromString(nodeResult: XMLElement, value: String?, name: String) {
            val nodeBinding = XMLElement("binding").addAttribute("name", name)
            if (value != null && value != "") {
                if (value.startsWith("\"") && !value.endsWith("\"")) {
                    val idx = value.lastIndexOf("\"^^<")
                    if (idx >= 0) {
                        val data = value.substring(1, idx)
                        val type = value.substring(idx + 4, value.length - 1)
                        nodeBinding.addContent(XMLElement("literal").addContent(data).addAttribute("datatype", type))
                    } else {
                        val idx2 = value.lastIndexOf("\"@")
                        if (idx2 >= 0) {
                            val data = value.substring(1, idx2)
                            val lang = value.substring(idx2 + 2, value.length)
                            nodeBinding.addContent(XMLElement("literal").addContent(data).addAttribute("xml:lang", lang))
                        } else {
                            nodeBinding.addContent(XMLElement("literal").addContent(value))
                        }
                    }
                } else if (value.startsWith("<") && value.endsWith(">"))
                    nodeBinding.addContent(XMLElement("uri").addContent(value.substring(1, value.length - 1)))
                else if (value.startsWith("_:"))
                    nodeBinding.addContent(XMLElement("bnode").addContent(value.substring(2, value.length)))
                else if (value.startsWith("\"") && value.endsWith("\""))
                    nodeBinding.addContent(XMLElement("literal").addContent(value.substring(1, value.length - 1)))
                else {
                    val literal = XMLElement("literal").addContent(value)
                    if ("""[0-9]+""".toRegex().matches(value))
                        literal.addAttribute("datatype", "http://www.w3.org/2001/XMLSchema#integer")
                    if ("""[0-9]*\.[0-9]+""".toRegex().matches(value))
                        literal.addAttribute("datatype", "http://www.w3.org/2001/XMLSchema#decimal")
                    if ("""[0-9]*\.[0-9]+[eE][0-9]+""".toRegex().matches(value))
                        literal.addAttribute("datatype", "http://www.w3.org/2001/XMLSchema#double")
                    nodeBinding.addContent(literal)
                }
                nodeResult.addContent(nodeBinding)
            }
        }

        fun parseFromJson(json: String): List<XMLElement>? {
            val res = mutableListOf<XMLElement>()
            val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
            res.add(nodeSparql)
            val nodeHead = XMLElement("head")
            val nodeResults = XMLElement("results")
            nodeSparql.addContent(nodeHead)
            nodeSparql.addContent(nodeResults)
            var lastParent: XMLElement? = null
            var lastParentCounter = 0
            var opencounter = 0
            var idx = 0
            var nodeResult: XMLElement? = null
            var nodeBinding: XMLElement? = null
            val attributes = mutableMapOf<String, String>()
            val regexToken = """("([^"]*)")|[0-9]+ |\{|\}|\[|\]|,|:|true|false""".toRegex()
            while (idx < json.length) {
                val token = regexToken.find(json, idx + 1)
                if (token == null)
                    return res
                idx = token.range.last
                when (token.value) {
                    "\"head\"" -> {
                        if (lastParent == null) {
                            lastParent = nodeHead
                            lastParentCounter = opencounter
                        }
                    }
                    "\"results\"" -> {
                        if (lastParent == null) {
                            lastParent = nodeResults
                            lastParentCounter = opencounter
                            nodeBinding = null
                        }
                    }
                    "\"vars\"", "\"bindings\"", ",", ":" -> {
                    }
                    "{", "[" -> opencounter++
                    "}", "]" -> {
                        opencounter--
                        if (nodeBinding != null) {
                            when (attributes["type"]) {
                                "uri", "bnode" -> {
                                    val node = XMLElement(attributes["type"]!!)
                                    nodeBinding.addContent(node)
                                    nodeBinding = node
                                }
                                "literal", "typed-literal" -> {
                                    val node = XMLElement("literal")
                                    nodeBinding.addContent(node)
                                    nodeBinding = node
                                    if (attributes["datatype"] != null)
                                        nodeBinding.addAttribute("datatype", attributes["datatype"]!!)
                                }
                            }
                            if (attributes["value"] != null) {
                                nodeBinding.addContent(attributes["value"]!!)
                            }
                            attributes.clear()
                            nodeBinding = null
                        } else if (nodeResult != null)
                            nodeResult = null
                        if (opencounter == lastParentCounter)
                            lastParent = null
                    }
                    else -> {
                        var flag = false
                        if (lastParent != null) {
                            if (lastParent == nodeHead) {
                                nodeHead.addContent(XMLElement("variable").addAttribute("name", token.value.substring(1, token.value.length - 1)))
                                flag = true
                            } else {
                                if (nodeResult == null) {
                                    nodeResult = XMLElement("result")
                                    nodeResults.addContent(nodeResult)
                                }
                                if (nodeBinding == null) {
                                    nodeBinding = XMLElement("binding").addAttribute("name", token.value.substring(1, token.value.length - 1))
                                    nodeResult.addContent(nodeBinding)
                                    flag = true
                                }
                            }
                        } else {
                            if (token.value == "\"boolean\"") {
                                val token3 = regexToken.find(json, idx + 1)
                                if (token3 == null)
                                    return res
                                require(token3.value == ":")
                                idx = token3.range.last
                                val token2 = regexToken.find(json, idx + 1)
                                if (token2 == null)
                                    return res
                                idx = token2.range.last
                                val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
                                res.clear()
                                res.add(nodeSparql)
                                val node = XMLElement("boolean").addContent(token2.value).addAttribute("datatype", "http://www.w3.org/2001/XMLSchema#boolean")
                                nodeSparql.addContent(nodeHead)
                                nodeSparql.addContent(node)
                                return res
                            }
                        }
                        if (!flag) {
                            if (nodeBinding != null) {
                                val token3 = regexToken.find(json, idx + 1)
                                if (token3 == null)
                                    return res
                                require(token3.value == ":")
                                idx = token3.range.last
                                val token2 = regexToken.find(json, idx + 1)
                                if (token2 == null)
                                    return res
                                idx = token2.range.last
                                if (token2.value.startsWith("\"") && token2.value.endsWith("\""))
                                    attributes[token.value.substring(1, token.value.length - 1)] = token2.value.substring(1, token2.value.length - 1)
                                else
                                    attributes[token.value.substring(1, token.value.length - 1)] = token2.value
                            }
                        }
                    }
                }
            }
            return res
        }

        fun parseFromTsv(tsv: String): List<XMLElement>? {
            val res = mutableListOf<XMLElement>()
            val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
            res.add(nodeSparql)
            val nodeHead = XMLElement("head")
            val nodeResults = XMLElement("results")
            nodeSparql.addContent(nodeHead)
            nodeSparql.addContent(nodeResults)
            val lines = tsv.lines()
            val variables = mutableListOf<Pair<String, Int>>()
            var i = 0
            for (variableName in lines.first().split("\t")) {
                nodeHead.addContent(XMLElement("variable").addAttribute("name", variableName.substring(1, variableName.length)))
                variables.add(Pair(variableName.substring(1, variableName.length), i))
                i++
            }
            var firstLine = true
            for (line in lines) {
                if (firstLine) {
                    firstLine = false
                    continue
                }
                if (line.isEmpty())
                    continue
                val nodeResult = XMLElement("result")
                nodeResults.addContent(nodeResult)
                val values = line.split("\t")
                for (variable in variables) {
                    if (values.size > variable.second) {
                        parseBindingFromString(nodeResult, values[variable.second], variable.first)
                    }
                }
            }
            return res
        }
    }

    val attributes = mutableMapOf<String, String>()
    var content: String = ""
    val childs = mutableListOf<XMLElement>()

    fun myEquals(other: XMLElement?): Boolean {
        if (other == null)
            return false
        if (tag != other.tag)
            return false
        if (tag == "bnode")
            return true
        val c1 = content.replace("""^\s*$""".toRegex(), "")
        val c2 = other.content.replace("""^\s*$""".toRegex(), "")
        if (c1 != c2)
            return false
        if (childs.count() != other.childs.count())
            return false
        if (attributes != other.attributes)
            return false
        var i = 0
        for (c in childs) {
            var d = other.childs[i]
            if (!c.myEquals(d))
                return false
            i++
        }
        return true
    }

    fun myEqualsUnclean(other: XMLElement?): Boolean {
        if (other == null)
            return false
        if (tag != other.tag)
            return false
        if (tag == "bnode")
            return true
        if (childs.count() != other.childs.count())
            return false
        if (tag != "sparql" && attributes != other.attributes)
            return false
        val c1 = content.replace("""^\s*$""".toRegex(), "")
        val c2 = other.content.replace("""^\s*$""".toRegex(), "")
        if (attributes["datatype"] == "http://www.w3.org/2001/XMLSchema#decimal" || attributes["datatype"] == "http://www.w3.org/2001/XMLSchema#double") {
            val a = c1.toDouble()
            val b = c2.toDouble()
            if (abs(a - b) > 0.00001)
                return false
        } else if (c1 != c2)
            return false
        for (c in childs) {
            var found = false
            for (d in other.childs) {
                if (c.myEqualsUnclean(d)) {
                    found = true
                    break
                }
            }
            if (!found)
                return false
        }
        return true
    }

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
        val c = content.replace("^\\s*$".toRegex(), "")
        var res = "<${tag}"
        for ((k, v) in attributes)
            res += " ${k}=\"${v}\""
        if (c.isEmpty() && childs.isEmpty()) {
            res += "/>"
        } else {
            res += ">"
            for (c in childs)
                res += c.toString()
            res += "${c}</${tag}>"
        }
        return res
    }

    fun toPrettyString(indention: String = ""): String {
        val c = content.replace("^\\s*$".toRegex(), "")
        var res = "${indention}<${tag}"
        for ((k, v) in attributes)
            res += " ${k}=\"${v}\""
        if (c.isEmpty() && childs.isEmpty()) {
            res += "/>\n"
        } else {
            if (c.isEmpty()) {
                res += ">\n"
                for (c in childs)
                    res += c.toPrettyString(indention + "\t")
                res += "${indention}</${tag}>\n"
            } else {
                res += ">${c}</${tag}>\n"
            }
        }
        return res
    }
}


