package lupos.s00misc

import kotlin.jvm.JvmField
import kotlin.math.abs

class XMLElement {
    // https://regex101.com
    companion object {
@JvmField val parseFromAnyRegistered=mutableMapOf<String,XMLElementParser>()
        fun parseBindingFromString(nodeResult: XMLElement, value: String?, name: String) {
            val nodeBinding = XMLElement("binding").addAttribute("name", name)
            if (value != null && value != "") {
                if (value.startsWith("\"") && !value.endsWith("\"")) {
                    val idx = value.lastIndexOf("\"^^<")
                    if (idx >= 0) {
                        val data = value.substring(1, idx)
                        val type = value.substring(idx + 4, value.length - 1)
                        nodeBinding.addContent(XMLElement("literal").addContentClean(data).addAttribute("datatype", type))
                    } else {
                        val idx2 = value.lastIndexOf("\"@")
                        if (idx2 >= 0) {
                            val data = value.substring(1, idx2)
                            val lang = value.substring(idx2 + 2, value.length)
                            nodeBinding.addContent(XMLElement("literal").addContentClean(data).addAttribute("xml:lang", lang))
                        } else {
                            nodeBinding.addContent(XMLElement("literal").addContentClean(value))
                        }
                    }
                } else if (value.startsWith("<") && value.endsWith(">")) {
                    nodeBinding.addContent(XMLElement("uri").addContentClean(value.substring(1, value.length - 1)))
                } else if (value.startsWith("_:")) {
                    nodeBinding.addContent(XMLElement("bnode").addContentClean(value.substring(2, value.length)))
                } else if (value.startsWith("\"") && value.endsWith("\"")) {
                    nodeBinding.addContent(XMLElement("literal").addContentClean(value.substring(1, value.length - 1)))
                } else {
                    val literal = XMLElement("literal").addContentClean(value)
                    if ("""[0-9]+""".toRegex().matches(value)) {
                        literal.addAttribute("datatype", "http://www.w3.org/2001/XMLSchema#integer")
                    }
                    if ("""[0-9]*\.[0-9]+""".toRegex().matches(value)) {
                        literal.addAttribute("datatype", "http://www.w3.org/2001/XMLSchema#decimal")
                    }
                    if ("""[0-9]*\.[0-9]+[eE][0-9]+""".toRegex().matches(value)) {
                        literal.addAttribute("datatype", "http://www.w3.org/2001/XMLSchema#double")
                    }
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
            if (it.tag == key) {
                return it
            }
        }
        return null
    }

    override fun equals(other: Any?) = other is XMLElement && myEqualsUnclean(other, true, true, true)
    fun myEquals(other: XMLElement?): Boolean {
        if (other == null) {
            return false
        }
        if (tag != other.tag) {
            return false
        }
        if (tag == "bnode") {
            return true
        }
        val c1 = content.replace("""^\s*$""".toRegex(), "")
        val c2 = other.content.replace("""^\s*$""".toRegex(), "")
        if (c1 != c2) {
            return false
        }
        if (childs.count() != other.childs.count()) {
            return false
        }
        if (attributes != other.attributes) {
            return false
        }
        var i = 0
        for (c in childs) {
            var d = other.childs[i]
            if (!c.myEquals(d)) {
                return false
            }
            i++
        }
        return true
    }

    fun myEqualsUnclean(other: XMLElement?, fixStringType: Boolean, fixNumbers: Boolean, fixSortOrder: Boolean): Boolean {
        if (other == null) {
            return false
        }
        if (tag != other.tag) {
            return false
        }
        if (tag == "bnode") {
            return true
        }
// avoid bugs in JENA -->>
        if (tag == "results") {
            if (childs.count() == 0 && other.childs.count() == 1 && other.childs[0].childs.count() == 0 && other.childs[0].tag == "result") {
                childs.add(XMLElement("result"))
            }
            if (childs.count() == 1 && other.childs.count() == 0 && childs[0].childs.count() == 0 && childs[0].tag == "result") {
                other.childs.add(XMLElement("result"))
            }
        }
//<<-- avoid bugs in JENA
        if (childs.count() != other.childs.count()) {
            return false
        }
        if (tag != "sparql") {
// avoid bugs in JENA -->>
            if (attributes["datatype"] == "http://www.w3.org/2001/XMLSchema#string" && other.attributes["datatype"] == null && fixStringType) {
                other.attributes["datatype"] = "http://www.w3.org/2001/XMLSchema#string"
            }
            if (other.attributes["datatype"] == "http://www.w3.org/2001/XMLSchema#string" && attributes["datatype"] == null && fixStringType) {
                attributes["datatype"] = "http://www.w3.org/2001/XMLSchema#string"
            }
//<<-- avoid bugs in JENA
// avoid inconsistencies in W3C -->>
            if (attributes["xml:lang"] != null) {
                attributes["xml:lang"] = attributes["xml:lang"]!!.toLowerCase()
            }
            if (other.attributes["xml:lang"] != null) {
                other.attributes["xml:lang"] = other.attributes["xml:lang"]!!.toLowerCase()
            }
//<<-- avoid inconsistencies in W3C
            if (attributes != other.attributes) {
                return false
            }
        }
        val c1 = content.replace("""^\s*$""".toRegex(), "")
        val c2 = other.content.replace("""^\s*$""".toRegex(), "")
        if (attributes["datatype"] == "http://www.w3.org/2001/XMLSchema#integer" && fixNumbers) {
            if (c1 != c2) {
                return false
            }
        } else if ((attributes["datatype"] == "http://www.w3.org/2001/XMLSchema#decimal" || attributes["datatype"] == "http://www.w3.org/2001/XMLSchema#double" || attributes["datatype"] == "http://www.w3.org/2001/XMLSchema#float") && fixNumbers) {
            val a = c1.toDouble()
            val b = c2.toDouble()
            if (abs(a - b) > 0.00001) {
                return false
            }
        } else if (c1 != c2) {
            return false
        }
        if (fixSortOrder) {
            var biginput = childs.size > 10 || other.childs.size > 10
            var myMap = mutableMapOf<String, MutableList<XMLElement>>()
            var otherMap = mutableMapOf<String, MutableList<XMLElement>>()
            var change = true
            if (biginput) {
                if (childs.size > 10000 && childs.size == other.childs.size) {
                    return true
                }
                var n = 0
                for (c in childs) {
                    //SanityCheck.println({ "myEqualsUnclean - loop A ${n} ${childs.size}" })
                    var s = c.toString()
                    if (myMap[s] == null) {
                        myMap[s] = mutableListOf(c)
                    } else {
                        myMap[s]!!.add(c)
                    }
                    n++
                }
                n = 0
                for (c in other.childs) {
                    //SanityCheck.println({ "myEqualsUnclean - loop B ${n} ${other.childs.size}" })
                    var s = c.toString()
                    if (otherMap[s] == null) {
                        otherMap[s] = mutableListOf(c)
                    } else {
                        otherMap[s]!!.add(c)
                    }
                    n++
                }
                while (change) {
                    change = false
                    //SanityCheck.println({ "myEqualsUnclean - loop C ${myMap.size} ${otherMap.size}" })
                    for ((k, v) in myMap) {
                        val w = otherMap[k]
                        if (w != null) {
                            change = true
                            if (w.size == v.size) {
                                myMap.remove(k)
                                otherMap.remove(k)
                            } else if (w.size < v.size) {
                                for (i in 0 until w.size) {
                                    v.removeAt(0)
                                }
                                otherMap.remove(k)
                            } else if (v.size < w.size) {
                                for (i in 0 until v.size) {
                                    w.removeAt(0)
                                }
                                myMap.remove(k)
                            }
                            break
                        }
                    }
                }
            }
            var myRemaining = mutableListOf<XMLElement>()
            var otherRemaining = mutableListOf<XMLElement>()
            if (biginput) {
                for ((k, v) in myMap) {
                    myRemaining.addAll(v)
                }
                for ((k, v) in otherMap) {
                    otherRemaining.addAll(v)
                }
            } else {
                myRemaining.addAll(childs)
                otherRemaining.addAll(other.childs)
            }
            change = true
            while (change) {
                change = false
                //SanityCheck.println({ "myEqualsUnclean - loop D ${myRemaining.size} ${otherRemaining.size}" })
                var i = 0
                loop@ for (c in myRemaining) {
                    var j = 0
                    for (d in otherRemaining) {
                        if (c.myEqualsUnclean(d, fixStringType, fixNumbers, fixSortOrder)) {
                            myRemaining.removeAt(i)
                            otherRemaining.removeAt(j)
                            change = true
                            break@loop
                        }
                        j++
                    }
                    return false
                }
            }
        } else {
            var i = 0
            for (c in childs) {
                var d = other.childs[i]
                if (!c.myEquals(d)) {
                    return false
                }
                i++
            }
        }
        return true
    }

    fun addAttribute(name: String, value: String): XMLElement {
        attributes[decodeText(name)] = decodeText(value)
        return this
    }

    fun addContentClean(s: String): XMLElement {
        var res: String = s
        while (true) {
            val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res)
            if (match == null) {
                break
            }
            val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
            res = res.replace(match.value, replacement)
        }
        addContent(res)
        return this
    }

    fun addContent(content: String): XMLElement {
        SanityCheck.check { childs.isEmpty() }
        this.content += decodeText(content)
        return this
    }

    fun addContent(childs: Collection<XMLElement>): XMLElement {
        SanityCheck.check { content.isEmpty() }
        this.childs.addAll(childs)
        return this
    }

    fun addContent(child: XMLElement): XMLElement {
        SanityCheck.check { content.isEmpty() }
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
        return text.replace("&", "&amp;").replace(">", "&gt;").replace("<", "&lt;").replace("'", "&apos;").replace("\"", "&quot;").replace("\\n", "&#x0A;")
    }

    fun decodeText(text: String): String {
        return text.replace("&quot;", "\"").replace("&apos;", "'").replace("&lt;", "<").replace("&gt;", ">").replace("&amp;", "&").replace("&#x0A;", "\\n")
    }

    override fun toString(): String {
        val c = content.replace("^\\s*$".toRegex(), "")
        var res = "<${encodeText(tag)}"
        for ((k, v) in attributes) {
            res += " ${encodeText(k)}=\"${encodeText(v)}\""
        }
        if (c.isEmpty() && childs.isEmpty()) {
            res += "/>"
        } else {
            res += ">"
            for (child in childs) {
                res += child.toString()
            }
            res += "${encodeText(c)}</${encodeText(tag)}>"
        }
        return res
    }

    fun toPrettyString(indention: String): StringBuilder {
        val c = content.replace("^\\s*$".toRegex(), "")
        var res = StringBuilder("${indention}<${encodeText(tag)}")
        for ((k, v) in attributes) {
            res.append(" ${encodeText(k)}=\"${encodeText(v)}\"")
        }
        if (c.isEmpty() && childs.isEmpty()) {
            res.append("/>\n")
        } else {
            if (c.isEmpty()) {
                res.append(">\n")
                for (child in childs) {
                    res.append(child.toPrettyString(indention + " "))
                }
                res.append("${indention}</${encodeText(tag)}>\n")
            } else {
                res.append(">${c}</${encodeText(tag)}>\n")
            }
        }
        return res
    }

    fun toPrettyString() = toPrettyString("").toString()
}
