package lupos.s00misc
import kotlin.jvm.JvmField
import kotlin.math.abs
import lupos.s00misc.Coverage
class XMLElement {
    // https://regex101.com
    companion object {
        val XMLHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
        fun parseFromAny(data: String, filename: String): XMLElement? {
Coverage.funStart(1516)
            when {
                filename.endsWith(".srx") -> {
Coverage.whenCaseStart(1518)
                    return XMLElement.parseFromXml(data)
                }
                filename.endsWith(".tsv") -> {
Coverage.whenCaseStart(1519)
                    return XMLElement.parseFromTsv(data)
                }
                filename.endsWith(".csv") -> {
Coverage.whenCaseStart(1520)
                    return XMLElement.parseFromCsv(data)
                }
                filename.endsWith(".ttl") -> {
Coverage.whenCaseStart(1521)
                    return XMLElement.parseFromTtl(data)
                }
                filename.endsWith(".nt") -> {
Coverage.whenCaseStart(1522)
                    return XMLElement.parseFromTtl(data)
                }
                filename.endsWith(".n3") -> {
Coverage.whenCaseStart(1523)
                    return XMLElement.parseFromTtl(data)
                }
                filename.endsWith(".srj") -> {
Coverage.whenCaseStart(1524)
                    return XMLElement.parseFromJson(data)
                }
                else -> {
Coverage.whenCaseStart(1525)
                    throw Exception("data parser :: file type '${filename}' unknown")
                }
            }
Coverage.statementStart(1526)
        }
        fun parseBindingFromString(nodeResult: XMLElement, value: String?, name: String) {
Coverage.funStart(1527)
            val nodeBinding = XMLElement("binding").addAttribute("name", name)
Coverage.statementStart(1528)
            if (value != null && value != "") {
Coverage.ifStart(1529)
                if (value.startsWith("\"") && !value.endsWith("\"")) {
Coverage.ifStart(1530)
                    val idx = value.lastIndexOf("\"^^<")
Coverage.statementStart(1531)
                    if (idx >= 0) {
Coverage.ifStart(1532)
                        val data = value.substring(1, idx)
Coverage.statementStart(1533)
                        val type = value.substring(idx + 4, value.length - 1)
Coverage.statementStart(1534)
                        nodeBinding.addContent(XMLElement("literal").addContentClean(data).addAttribute("datatype", type))
Coverage.statementStart(1535)
                    } else {
Coverage.ifStart(1536)
                        val idx2 = value.lastIndexOf("\"@")
Coverage.statementStart(1537)
                        if (idx2 >= 0) {
Coverage.ifStart(1538)
                            val data = value.substring(1, idx2)
Coverage.statementStart(1539)
                            val lang = value.substring(idx2 + 2, value.length)
Coverage.statementStart(1540)
                            nodeBinding.addContent(XMLElement("literal").addContentClean(data).addAttribute("xml:lang", lang))
Coverage.statementStart(1541)
                        } else {
Coverage.ifStart(1542)
                            nodeBinding.addContent(XMLElement("literal").addContentClean(value))
Coverage.statementStart(1543)
                        }
Coverage.statementStart(1544)
                    }
Coverage.statementStart(1545)
                } else if (value.startsWith("<") && value.endsWith(">")) {
Coverage.ifStart(1546)
                    nodeBinding.addContent(XMLElement("uri").addContentClean(value.substring(1, value.length - 1)))
Coverage.statementStart(1547)
                } else if (value.startsWith("_:")) {
Coverage.ifStart(1548)
                    nodeBinding.addContent(XMLElement("bnode").addContentClean(value.substring(2, value.length)))
Coverage.statementStart(1549)
                } else if (value.startsWith("\"") && value.endsWith("\"")) {
Coverage.ifStart(1550)
                    nodeBinding.addContent(XMLElement("literal").addContentClean(value.substring(1, value.length - 1)))
Coverage.statementStart(1551)
                } else {
Coverage.ifStart(1552)
                    val literal = XMLElement("literal").addContentClean(value)
Coverage.statementStart(1553)
                    if ("""[0-9]+""".toRegex().matches(value)) {
Coverage.ifStart(1554)
                        literal.addAttribute("datatype", "http://www.w3.org/2001/XMLSchema#integer")
Coverage.statementStart(1555)
                    }
Coverage.statementStart(1556)
                    if ("""[0-9]*\.[0-9]+""".toRegex().matches(value)) {
Coverage.ifStart(1557)
                        literal.addAttribute("datatype", "http://www.w3.org/2001/XMLSchema#decimal")
Coverage.statementStart(1558)
                    }
Coverage.statementStart(1559)
                    if ("""[0-9]*\.[0-9]+[eE][0-9]+""".toRegex().matches(value)) {
Coverage.ifStart(1560)
                        literal.addAttribute("datatype", "http://www.w3.org/2001/XMLSchema#double")
Coverage.statementStart(1561)
                    }
Coverage.statementStart(1562)
                    nodeBinding.addContent(literal)
Coverage.statementStart(1563)
                }
Coverage.statementStart(1564)
                nodeResult.addContent(nodeBinding)
Coverage.statementStart(1565)
            }
Coverage.statementStart(1566)
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
Coverage.funStart(1567)
        childs.forEach {
Coverage.forEachLoopStart(1568)
            if (it.tag == key) {
Coverage.ifStart(1569)
                return it
            }
Coverage.statementStart(1570)
        }
Coverage.statementStart(1571)
        return null
    }
    override fun equals(other: Any?) = other is XMLElement && myEqualsUnclean(other, true, true, true)
    fun myEquals(other: XMLElement?): Boolean {
Coverage.funStart(1572)
        if (other == null) {
Coverage.ifStart(1573)
            return false
        }
Coverage.statementStart(1574)
        if (tag != other.tag) {
Coverage.ifStart(1575)
            return false
        }
Coverage.statementStart(1576)
        if (tag == "bnode") {
Coverage.ifStart(1577)
            return true
        }
Coverage.statementStart(1578)
        val c1 = content.replace("""^\s*$""".toRegex(), "")
Coverage.statementStart(1579)
        val c2 = other.content.replace("""^\s*$""".toRegex(), "")
Coverage.statementStart(1580)
        if (c1 != c2) {
Coverage.ifStart(1581)
            return false
        }
Coverage.statementStart(1582)
        if (childs.count() != other.childs.count()) {
Coverage.ifStart(1583)
            return false
        }
Coverage.statementStart(1584)
        if (attributes != other.attributes) {
Coverage.ifStart(1585)
            return false
        }
Coverage.statementStart(1586)
        var i = 0
Coverage.statementStart(1587)
        for (c in childs) {
Coverage.forLoopStart(1588)
            var d = other.childs[i]
Coverage.statementStart(1589)
            if (!c.myEquals(d)) {
Coverage.ifStart(1590)
                return false
            }
Coverage.statementStart(1591)
            i++
Coverage.statementStart(1592)
        }
Coverage.statementStart(1593)
        return true
    }
    fun myEqualsUnclean(other: XMLElement?, fixStringType: Boolean, fixNumbers: Boolean, fixSortOrder: Boolean): Boolean {
Coverage.funStart(1594)
        if (other == null) {
Coverage.ifStart(1595)
            return false
        }
Coverage.statementStart(1596)
        if (tag != other.tag) {
Coverage.ifStart(1597)
            return false
        }
Coverage.statementStart(1598)
        if (tag == "bnode") {
Coverage.ifStart(1599)
            return true
        }
Coverage.statementStart(1600)
        if (tag == "results") {
Coverage.ifStart(1601)
            if (childs.count() == 0 && other.childs.count() == 1 && other.childs[0].childs.count() == 0 && other.childs[0].tag == "result") {
Coverage.ifStart(1602)
                childs.add(XMLElement("result"))
Coverage.statementStart(1603)
            }
Coverage.statementStart(1604)
            if (childs.count() == 1 && other.childs.count() == 0 && childs[0].childs.count() == 0 && childs[0].tag == "result") {
Coverage.ifStart(1605)
                other.childs.add(XMLElement("result"))
Coverage.statementStart(1606)
            }
Coverage.statementStart(1607)
        }
Coverage.statementStart(1608)
//<<-- avoid bugs in JENA
Coverage.statementStart(1609)
        if (childs.count() != other.childs.count()) {
Coverage.ifStart(1610)
            return false
        }
Coverage.statementStart(1611)
        if (tag != "sparql") {
Coverage.ifStart(1612)
            if (attributes["datatype"] == "http://www.w3.org/2001/XMLSchema#string" && other.attributes["datatype"] == null && fixStringType) {
Coverage.ifStart(1613)
                other.attributes["datatype"] = "http://www.w3.org/2001/XMLSchema#string"
Coverage.statementStart(1614)
            }
Coverage.statementStart(1615)
            if (other.attributes["datatype"] == "http://www.w3.org/2001/XMLSchema#string" && attributes["datatype"] == null && fixStringType) {
Coverage.ifStart(1616)
                attributes["datatype"] = "http://www.w3.org/2001/XMLSchema#string"
Coverage.statementStart(1617)
            }
Coverage.statementStart(1618)
//<<-- avoid bugs in JENA
Coverage.statementStart(1619)
            if (attributes != other.attributes) {
Coverage.ifStart(1620)
                return false
            }
Coverage.statementStart(1621)
        }
Coverage.statementStart(1622)
        val c1 = content.replace("""^\s*$""".toRegex(), "")
Coverage.statementStart(1623)
        val c2 = other.content.replace("""^\s*$""".toRegex(), "")
Coverage.statementStart(1624)
        if (attributes["datatype"] == "http://www.w3.org/2001/XMLSchema#integer" && fixNumbers) {
Coverage.ifStart(1625)
            if (c1.toInt() != c2.toInt()) {
Coverage.ifStart(1626)
                return false
            }
Coverage.statementStart(1627)
//<<-- avoid bugs in JENA
Coverage.statementStart(1628)
        } else if ((attributes["datatype"] == "http://www.w3.org/2001/XMLSchema#decimal" || attributes["datatype"] == "http://www.w3.org/2001/XMLSchema#double") && fixNumbers) {
Coverage.ifStart(1629)
            val a = c1.toDouble()
Coverage.statementStart(1630)
            val b = c2.toDouble()
Coverage.statementStart(1631)
            if (abs(a - b) > 0.00001) {
Coverage.ifStart(1632)
                return false
            }
Coverage.statementStart(1633)
        } else if (c1 != c2) {
Coverage.ifStart(1634)
            return false
        }
Coverage.statementStart(1635)
        if (fixSortOrder) {
Coverage.ifStart(1636)
            for (c in childs) {
Coverage.forLoopStart(1637)
                var found = false
Coverage.statementStart(1638)
                for (d in other.childs) {
Coverage.forLoopStart(1639)
                    if (c.myEqualsUnclean(d, fixStringType, fixNumbers, fixSortOrder)) {
Coverage.ifStart(1640)
                        found = true
Coverage.statementStart(1641)
                        break
                    }
Coverage.statementStart(1642)
                }
Coverage.statementStart(1643)
                if (!found) {
Coverage.ifStart(1644)
                    return false
                }
Coverage.statementStart(1645)
            }
Coverage.statementStart(1646)
        } else {
Coverage.ifStart(1647)
            var i = 0
Coverage.statementStart(1648)
            for (c in childs) {
Coverage.forLoopStart(1649)
                var d = other.childs[i]
Coverage.statementStart(1650)
                if (!c.myEquals(d)) {
Coverage.ifStart(1651)
                    return false
                }
Coverage.statementStart(1652)
                i++
Coverage.statementStart(1653)
            }
Coverage.statementStart(1654)
        }
Coverage.statementStart(1655)
        return true
    }
    fun addAttribute(name: String, value: String): XMLElement {
Coverage.funStart(1656)
        attributes[decodeText(name)] = decodeText(value)
Coverage.statementStart(1657)
        return this
    }
    fun addContentClean(s: String): XMLElement {
Coverage.funStart(1658)
        var res: String = s
Coverage.statementStart(1659)
        while (true) {
Coverage.whileLoopStart(1660)
            val match = "\\\\u[0-9a-fA-f]{4}".toRegex().find(res)
Coverage.statementStart(1661)
            if (match == null) {
Coverage.ifStart(1662)
                break
            }
Coverage.statementStart(1663)
            val replacement = match.value.substring(2, 6).toInt(16).toChar() + ""
Coverage.statementStart(1664)
            res = res.replace(match.value, replacement)
Coverage.statementStart(1665)
        }
Coverage.statementStart(1666)
        addContent(res)
Coverage.statementStart(1667)
        return this
    }
    fun addContent(content: String): XMLElement {
Coverage.funStart(1668)
        if (!childs.isEmpty()) {
Coverage.ifStart(1669)
            throw Exception("either content or subchilds must be empty")
        }
Coverage.statementStart(1670)
        this.content += decodeText(content)
Coverage.statementStart(1671)
        return this
    }
    fun addContent(childs: Collection<XMLElement>): XMLElement {
Coverage.funStart(1672)
        if (!content.isEmpty()) {
Coverage.ifStart(1673)
            throw Exception("either content or subchilds must be empty")
        }
Coverage.statementStart(1674)
        this.childs.addAll(childs)
Coverage.statementStart(1675)
        return this
    }
    fun addContent(child: XMLElement): XMLElement {
Coverage.funStart(1676)
        if (!content.isEmpty()) {
Coverage.ifStart(1677)
            throw Exception("either content or subchilds must be empty")
        }
Coverage.statementStart(1678)
        childs.add(child)
Coverage.statementStart(1679)
        return this
    }
    fun addContent(childs: Collection<String>, childTag: String): XMLElement {
Coverage.funStart(1680)
        for (c in childs) {
Coverage.forLoopStart(1681)
            addContent(XMLElement(childTag).addContent(c).toString())
Coverage.statementStart(1682)
        }
Coverage.statementStart(1683)
        return this
    }
    fun encodeText(text: String): String {
Coverage.funStart(1684)
        return text.replace("&", "&amp;").replace(">", "&gt;").replace("<", "&lt;").replace("'", "&apos;").replace("\"", "&quot;")
    }
    fun decodeText(text: String): String {
Coverage.funStart(1685)
        return text.replace("&quot;", "\"").replace("&apos;", "'").replace("&lt;", "<").replace("&gt;", ">").replace("&amp;", "&")
    }
    override fun toString(): String {
Coverage.funStart(1686)
        val c = content.replace("^\\s*$".toRegex(), "")
Coverage.statementStart(1687)
        var res = "<${encodeText(tag)}"
Coverage.statementStart(1688)
        for ((k, v) in attributes) {
Coverage.forLoopStart(1689)
            res += " ${encodeText(k)}=\"${encodeText(v)}\""
Coverage.statementStart(1690)
        }
Coverage.statementStart(1691)
        if (c.isEmpty() && childs.isEmpty()) {
Coverage.ifStart(1692)
            res += "/>"
Coverage.statementStart(1693)
        } else {
Coverage.ifStart(1694)
            res += ">"
Coverage.statementStart(1695)
            for (child in childs) {
Coverage.forLoopStart(1696)
                res += child.toString()
Coverage.statementStart(1697)
            }
Coverage.statementStart(1698)
            res += "${encodeText(c)}</${encodeText(tag)}>"
Coverage.statementStart(1699)
        }
Coverage.statementStart(1700)
        return res
    }
    fun toPrettyString(indention: String): StringBuilder {
Coverage.funStart(1701)
        val c = content.replace("^\\s*$".toRegex(), "")
Coverage.statementStart(1702)
        var res = StringBuilder("${indention}<${encodeText(tag)}")
Coverage.statementStart(1703)
        for ((k, v) in attributes) {
Coverage.forLoopStart(1704)
            res.append(" ${encodeText(k)}=\"${encodeText(v)}\"")
Coverage.statementStart(1705)
        }
Coverage.statementStart(1706)
        if (c.isEmpty() && childs.isEmpty()) {
Coverage.ifStart(1707)
            res.append("/>\n")
Coverage.statementStart(1708)
        } else {
Coverage.ifStart(1709)
            if (c.isEmpty()) {
Coverage.ifStart(1710)
                res.append(">\n")
Coverage.statementStart(1711)
                for (child in childs) {
Coverage.forLoopStart(1712)
                    res.append(child.toPrettyString(indention + " "))
Coverage.statementStart(1713)
                }
Coverage.statementStart(1714)
                res.append("${indention}</${encodeText(tag)}>\n")
Coverage.statementStart(1715)
            } else {
Coverage.ifStart(1716)
                res.append(">${c}</${encodeText(tag)}>\n")
Coverage.statementStart(1717)
            }
Coverage.statementStart(1718)
        }
Coverage.statementStart(1719)
        return res
    }
    fun toPrettyString() = toPrettyString("").toString()
}
