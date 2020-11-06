package lupos.s00misc

class XMLElementFromXML() : XMLElementParser {
    override operator fun invoke(data: String): XMLElement? {
        return parseFromXmlHelper(data)?.first()
    }

    internal fun parseFromXmlHelper(xml: String): List<XMLElement>? {
        val x = xml.replace("\n", "").replace("\r", "")
        val res = mutableListOf<XMLElement>()
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
                res.add(childNode)
                var nodeAttributes = ""
                if (child.groups[4] != null) {
                    nodeAttributes = child.groups[4]!!.value
                }
                if (child.groups[8] != null) {
                    nodeAttributes = child.groups[8]!!.value
                }
                """([^\s]*?)="(([^\\"]*(\\"|\\)*)*)"""".toRegex().findAll(nodeAttributes).forEach { attrMatch ->
                    if (attrMatch.groups[1] != null && attrMatch.groups[2] != null) {
                        childNode.addAttribute(attrMatch.groups[1]!!.value, attrMatch.groups[2]!!.value)
                    }
                }
                """([^\s]*?)='([^']*)'""".toRegex().findAll(nodeAttributes).forEach { attrMatch ->
                    if (attrMatch.groups[1] != null && attrMatch.groups[2] != null) {
                        childNode.addAttribute(attrMatch.groups[1]!!.value, attrMatch.groups[2]!!.value)
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
                    val tmp = parseFromXmlHelper(content)
                    if (tmp == null) {
                        childNode.addContentClean(content)
                    } else {
                        childNode.addContent(tmp)
                    }
                }
            }
        }
        if (res.isEmpty() && !xml.isEmpty()) {
            return null
        }
        return res
    }
}
