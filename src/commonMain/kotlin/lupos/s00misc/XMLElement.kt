package lupos.s00misc

import kotlin.jvm.JvmField
import kotlin.math.abs
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

class XMLElement {
    // https://regex101.com
    companion object {
        val XMLHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
        fun parseFromAny(data: String, filename: String): List<XMLElement>? {
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
    }

    @JvmField
    val attributes = mutableMapOf<String, String>()
    @JvmField
    var content: String = ""
    @JvmField
    val childs = mutableListOf<XMLElement>()
    @JvmField
    val tag: String

    constructor(tag: String) {
        this.tag = decodeText(tag)
    }

    operator fun get(key: String): XMLElement? {
        childs.forEach {
            if (it.tag == key)
                return it
        }
        return null
    }

    override fun equals(other: Any?) = other is XMLElement && myEqualsUnclean(other)
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
//-->> avoid bugs in JENA
        if (tag == "results") {
            if (childs.count() == 0 && other.childs.count() == 1 && other.childs[0].childs.count() == 0 && other.childs[0].tag == "result")
                childs.add(XMLElement("result"))
            if (childs.count() == 1 && other.childs.count() == 0 && childs[0].childs.count() == 0 && childs[0].tag == "result")
                other.childs.add(XMLElement("result"))
        }
//<<-- avoid bugs in JENA
        if (childs.count() != other.childs.count())
            return false
        if (tag != "sparql") {
//-->> avoid bugs in JENA
            if (attributes["datatype"] == "http://www.w3.org/2001/XMLSchema#string" && other.attributes["datatype"] == null)
                other.attributes["datatype"] = "http://www.w3.org/2001/XMLSchema#string"
            if (other.attributes["datatype"] == "http://www.w3.org/2001/XMLSchema#string" && attributes["datatype"] == null)
                attributes["datatype"] = "http://www.w3.org/2001/XMLSchema#string"
//<<-- avoid bugs in JENA
            if (attributes != other.attributes)
                return false
        }
        val c1 = content.replace("""^\s*$""".toRegex(), "")
        val c2 = other.content.replace("""^\s*$""".toRegex(), "")
//-->> avoid bugs in JENA
        if (attributes["datatype"] == "http://www.w3.org/2001/XMLSchema#integer") {
            if (c1.toInt() != c2.toInt())
                return false
//<<-- avoid bugs in JENA
        } else if (attributes["datatype"] == "http://www.w3.org/2001/XMLSchema#decimal" || attributes["datatype"] == "http://www.w3.org/2001/XMLSchema#double") {
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
        attributes[decodeText(name)] = decodeText(value)
        return this
    }

    fun addContent(content: String): XMLElement {
        if (!childs.isEmpty())
            throw Exception("either content or subchilds must be empty")
        this.content += decodeText(content)
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

    fun encodeText(text: String): String {
        return text.replace("&", "&amp;").replace(">", "&gt;").replace("<", "&lt;").replace("'", "&apos;").replace("\"", "&quot;")
    }

    fun decodeText(text: String): String {
        return text.replace("&quot;", "\"").replace("&apos;", "'").replace("&lt;", "<").replace("&gt;", ">").replace("&amp;", "&")
    }

    override fun toString(): String {
        val c = content.replace("^\\s*$".toRegex(), "")
        var res = "<${encodeText(tag)}"
        for ((k, v) in attributes)
            res += " ${encodeText(k)}=\"${encodeText(v)}\""
        if (c.isEmpty() && childs.isEmpty()) {
            res += "/>"
        } else {
            res += ">"
            for (c in childs)
                res += c.toString()
            res += "${encodeText(c)}</${encodeText(tag)}>"
        }
        return res
    }

    fun toPrettyString(indention: String): StringBuilder {
        val c = content.replace("^\\s*$".toRegex(), "")
        var res = StringBuilder("${indention}<${encodeText(tag)}")
        for ((k, v) in attributes)
            res.append(" ${encodeText(k)}=\"${encodeText(v)}\"")
        if (c.isEmpty() && childs.isEmpty()) {
            res.append("/>\n")
        } else {
            if (c.isEmpty()) {
                res.append(">\n")
                for (c in childs)
                    res.append(c.toPrettyString(indention + " "))
                res.append("${indention}</${encodeText(tag)}>\n")
            } else {
                res.append(">${c}</${encodeText(tag)}>\n")
            }
        }
        return res
    }

    fun toPrettyString() = Trace.trace<String>({ "XMLElement.toPrettyString" }, { toPrettyString("").toString() })
}
