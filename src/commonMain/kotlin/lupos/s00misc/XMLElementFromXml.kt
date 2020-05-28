package lupos.s00misc
import lupos.s00misc.Coverage
fun XMLElement.Companion.parseFromXml(xml: String): XMLElement? {
Coverage.funStart(1454)
    return XMLElement.parseFromXmlHelper(xml)?.first()
}
fun XMLElement.Companion.parseFromXmlHelper(xml: String): List<XMLElement>? {
Coverage.funStart(1455)
    val x = xml.replace("\n", "").replace("\r", "")
Coverage.statementStart(1456)
    val res = mutableListOf<XMLElement>()
Coverage.statementStart(1457)
    var lastindex = 0
Coverage.statementStart(1458)
    """((<([a-zA-Z]+)([^>]*?)>(.*?)<\/\3>)|(<([a-zA-Z]+)([^>]*?)>)|(<\?.*?\?>)|(<!--.*?-->))?""".toRegex().findAll(x).forEach { child ->
Coverage.statementStart(1459)
        var value = child.value
Coverage.statementStart(1460)
        if (value.length > 0 && !value.startsWith("<?") && !value.startsWith("<!--") && child.range.start >= lastindex) {
Coverage.ifStart(1461)
            var nodeName = ""
Coverage.statementStart(1462)
            if (child.groups[3] != null) {
Coverage.ifStart(1463)
                nodeName = child.groups[3]!!.value
Coverage.statementStart(1464)
            }
Coverage.statementStart(1465)
            if (child.groups[7] != null) {
Coverage.ifStart(1466)
                nodeName = child.groups[7]!!.value
Coverage.statementStart(1467)
            }
Coverage.statementStart(1468)
            val childNode = XMLElement(nodeName)
Coverage.statementStart(1469)
            res.add(childNode)
Coverage.statementStart(1470)
            var nodeAttributes = ""
Coverage.statementStart(1471)
            if (child.groups[4] != null) {
Coverage.ifStart(1472)
                nodeAttributes = child.groups[4]!!.value
Coverage.statementStart(1473)
            }
Coverage.statementStart(1474)
            if (child.groups[8] != null) {
Coverage.ifStart(1475)
                nodeAttributes = child.groups[8]!!.value
Coverage.statementStart(1476)
            }
Coverage.statementStart(1477)
            """([^\s]*?)="(([^\\"]*(\\"|\\)*)*)"""".toRegex().findAll(nodeAttributes).forEach { attrMatch ->
Coverage.statementStart(1478)
                if (attrMatch.groups[1] != null && attrMatch.groups[2] != null) {
Coverage.ifStart(1479)
                    if (attrMatch.groups[1]!!.value == "xml:lang") {
Coverage.ifStart(1480)
                        childNode.addAttribute(attrMatch.groups[1]!!.value, attrMatch.groups[2]!!.value)
Coverage.statementStart(1481)
                    } else {
Coverage.ifStart(1482)
                        childNode.addAttribute(attrMatch.groups[1]!!.value, attrMatch.groups[2]!!.value)
Coverage.statementStart(1483)
                    }
Coverage.statementStart(1484)
                }
Coverage.statementStart(1485)
            }
Coverage.statementStart(1486)
            """([^\s]*?)='([^']*)'""".toRegex().findAll(nodeAttributes).forEach { attrMatch ->
Coverage.statementStart(1487)
                if (attrMatch.groups[1] != null && attrMatch.groups[2] != null) {
Coverage.ifStart(1488)
                    if (attrMatch.groups[1]!!.value == "xml:lang") {
Coverage.ifStart(1489)
                        childNode.addAttribute(attrMatch.groups[1]!!.value, attrMatch.groups[2]!!.value)
Coverage.statementStart(1490)
                    } else {
Coverage.ifStart(1491)
                        childNode.addAttribute(attrMatch.groups[1]!!.value, attrMatch.groups[2]!!.value)
Coverage.statementStart(1492)
                    }
Coverage.statementStart(1493)
                }
Coverage.statementStart(1494)
            }
Coverage.statementStart(1495)
            var content = ""
Coverage.statementStart(1496)
            if (child.groups[5] != null) {
Coverage.ifStart(1497)
                content = child.groups[5]!!.value
Coverage.statementStart(1498)
            }
Coverage.statementStart(1499)
            if (!child.value.endsWith("</${nodeName}>") && !child.value.endsWith("/>")) {
Coverage.ifStart(1500)
                val search = "</${nodeName}>"
Coverage.statementStart(1501)
                val idx2 = x.indexOf(search, child.range.last)
Coverage.statementStart(1502)
                content = x.substring(child.range.last, idx2 + search.length)
Coverage.statementStart(1503)
                lastindex = idx2
Coverage.statementStart(1504)
            }
Coverage.statementStart(1505)
            if (content != "") {
Coverage.ifStart(1506)
                val tmp = parseFromXmlHelper(content)
Coverage.statementStart(1507)
                if (tmp == null) {
Coverage.ifStart(1508)
                    childNode.addContentClean(content)
Coverage.statementStart(1509)
                } else {
Coverage.ifStart(1510)
                    childNode.addContent(tmp)
Coverage.statementStart(1511)
                }
Coverage.statementStart(1512)
            }
Coverage.statementStart(1513)
        }
Coverage.statementStart(1514)
    }
    if (res.isEmpty() && !xml.isEmpty()) {
Coverage.ifStart(1515)
        return null
    }
    return res
}
