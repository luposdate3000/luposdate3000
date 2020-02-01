package lupos.s00misc

fun XMLElement.Companion.parseFromJson(json: String): List<XMLElement>? {
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
                        val node = XMLElement("boolean").addContent(token2.value)
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
