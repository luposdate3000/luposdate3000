package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s04logicalOperators.Query

fun XMLElement.Companion.parseFromXml(xml: String): XMLElement? {
    var res: XMLElement? = null
    val x = xml.replace("\n", "").replace("\r", "")
    var lastindex = 0
    """((<([a-zA-Z]+)([^>]*?)>(.*?)<\/\3>)|(<([a-zA-Z]+)([^>]*?)>)|(<\?.*?\?>)|(<!--.*?-->))?""".toRegex().findAll(x).forEach { child ->
        var value = child.value
        if (value.length > 0 && !value.startsWith("<?") && !value.startsWith("<!--") && child.range.start >= lastindex) {
            var nodeName = ""
            if (child.groups[3] != null) {
                nodeName = child.groups[3]!!.value
            }
            if (child.groups[7] != null) {
                nodeName = child.groups[7]!!.value
            }
            val childNode = XMLElement(nodeName)
            res = childNode
            var nodeAttributes = ""
            if (child.groups[4] != null) {
                nodeAttributes = child.groups[4]!!.value
            }
            if (child.groups[8] != null) {
                nodeAttributes = child.groups[8]!!.value
            }
            """([^\s]*?)="(([^\\"]*(\\"|\\)*)*)"""".toRegex().findAll(nodeAttributes).forEach { attrMatch ->
                if (attrMatch.groups[1] != null && attrMatch.groups[2] != null) {
                    if (attrMatch.groups[1]!!.value == "xml:lang") {
                        childNode.addAttribute(attrMatch.groups[1]!!.value, attrMatch.groups[2]!!.value.toLowerCase())
                    } else {
                        childNode.addAttribute(attrMatch.groups[1]!!.value, attrMatch.groups[2]!!.value)
                    }
                }
            }
            """([^\s]*?)='([^']*)'""".toRegex().findAll(nodeAttributes).forEach { attrMatch ->
                if (attrMatch.groups[1] != null && attrMatch.groups[2] != null) {
                    if (attrMatch.groups[1]!!.value == "xml:lang") {
                        childNode.addAttribute(attrMatch.groups[1]!!.value, attrMatch.groups[2]!!.value.toLowerCase())
                    } else {
                        childNode.addAttribute(attrMatch.groups[1]!!.value, attrMatch.groups[2]!!.value)
                    }
                }
            }
            var content = ""
            if (child.groups[5] != null) {
                content = child.groups[5]!!.value
            }
            if (!child.value.endsWith("</${nodeName}>") && !child.value.endsWith("/>")) {
                val search = "</${nodeName}>"
                val idx2 = x.indexOf(search, child.range.last)
                content = x.substring(child.range.last, idx2 + search.length)
                lastindex = idx2
            }
            if (content != "") {
                val tmp = parseFromXml(content)
                if (tmp == null) {
                    childNode.addContent(content)
                } else {
                    childNode.addContent(tmp)
                }
            }
        }
    }
    return res
}
