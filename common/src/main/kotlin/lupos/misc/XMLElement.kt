package lupos.misc

import kotlin.math.*

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
                nodeHead.addContent(XMLElement("variable").addAttribute("name", variableName))
                variables.add(Pair(variableName, i))
                i++
            }
            var firstLine = true
            for (line in lines) {
                if (firstLine) {
                    firstLine = false
                    continue
                }
                val nodeResult = XMLElement("result")
                nodeResults.addContent(nodeResult)
                val values = line.split("\t")
                for (variable in variables) {
                    val nodeBinding = XMLElement("binding").addAttribute("name", variable.first)
                    if (values.size > variable.second) {
                        val value = values[variable.second]
                        if (value != null && value != "") {
                            if (value.startsWith("\"") && !value.endsWith("\"")) {
                                println("value:" + value)
                                val idx = value.lastIndexOf("\"^^<")
                                println("idx:" + idx)
                                if (idx >= 0) {
                                    val data = value.substring(1, idx)
                                    val type = value.substring(idx + 4, value.length - 1)
                                    nodeBinding.addContent(XMLElement("literal").addContent(data).addAttribute("datatype", type))
                                } else {
                                    val idx2 = value.lastIndexOf("\"@")
                                    println("idx2:" + idx2)
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
                            else
                                nodeBinding.addContent(XMLElement("literal").addContent(value))
                            nodeResult.addContent(nodeBinding)
                        }
                    }
                }
            }
            if (res.isEmpty() && !tsv.isEmpty())
                return null
            return res
        }
    }

    val attributes = mutableMapOf<String, String>()
    var content: String = ""
    val childs = mutableListOf<XMLElement>()

    fun myEquals(other: XMLElement?): Boolean {
        if (other == null) {
            println("aa $this $other");return false
        }
        if (tag != other.tag) {
            println("ba $this $other");return false
        }
        val c1 = content.replace("""^\s*$""".toRegex(), "")
        val c2 = other.content.replace("""^\s*$""".toRegex(), "")
        if (c1 != c2) {
            println("ca $this $other");return false
        }
        if (childs.count() != other.childs.count()) {
            println("da $this $other");return false
        }
        if (attributes != other.attributes) {
            println("ea $this $other");return false
        }
        var i = 0
        for (c in childs) {
            var d = other.childs[i]
            if (!c.myEquals(d)) {
                println("fa $this $other");return false
            }
            i++
        }
        println("g");
        return true
    }

    fun myEqualsUnclean(other: XMLElement?): Boolean {
        if (other == null) {
            println("ab $this $other");return false
        }
        if (tag != other.tag) {
            println("bb $this $other");return false
        }
        if (childs.count() != other.childs.count()) {
            println("cb $this $other");return false
        }
        if (tag != "sparql" && attributes != other.attributes) {
            println("db $this $other");return false
        }
        val c1 = content.replace("""^\s*$""".toRegex(), "")
        val c2 = other.content.replace("""^\s*$""".toRegex(), "")
        if (attributes["datatype"] == "http://www.w3.org/2001/XMLSchema#decimal" || attributes["datatype"] == "http://www.w3.org/2001/XMLSchema#double") {
            val a = c1.toDouble()
            val b = c2.toDouble()
            if (abs(a - b) > 0.00001) {
                println("eb $this $other");return false
            }
        } else if (c1 != c2) {
            println("fb $this $other");return false
        }
        for (c in childs) {
            var found = false
            for (d in other.childs) {
                if (c.myEqualsUnclean(d)) {
                    found = true
                    break
                }
            }
            if (!found) {
                println("gb $this $other");return false
            }
        }
        println("h");
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


