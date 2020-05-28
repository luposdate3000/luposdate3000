package lupos.s00misc
import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
fun XMLElement.Companion.parseFromJson(json: String): XMLElement? {
Coverage.funStart(1278)
    val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
Coverage.statementStart(1279)
    val nodeHead = XMLElement("head")
Coverage.statementStart(1280)
    val nodeResults = XMLElement("results")
Coverage.statementStart(1281)
    nodeSparql.addContent(nodeHead)
Coverage.statementStart(1282)
    if (!json.contains("results")) {
Coverage.ifStart(1283)
        nodeSparql.addContent(XMLElement("boolean").addContent("" + (json.contains("true") && !json.contains("false"))))
Coverage.statementStart(1284)
        return nodeSparql
    }
Coverage.statementStart(1285)
    nodeSparql.addContent(nodeResults)
Coverage.statementStart(1286)
    var lastParent: XMLElement? = null
Coverage.statementStart(1287)
    var lastParentCounter = 0
Coverage.statementStart(1288)
    var opencounter = 0
Coverage.statementStart(1289)
    var idx = 0
Coverage.statementStart(1290)
    var nodeResult: XMLElement? = null
Coverage.statementStart(1291)
    var nodeBinding: XMLElement? = null
Coverage.statementStart(1292)
    val attributes = mutableMapOf<String, String>()
Coverage.statementStart(1293)
    val regexToken = """("([^"]*)")|[0-9]+ |\{|\}|\[|\]|,|:|true|false""".toRegex()
Coverage.statementStart(1294)
    var lasttokenbracket: Boolean
Coverage.statementStart(1295)
    var thistokenbracket = false
Coverage.statementStart(1296)
    while (idx < json.length) {
Coverage.whileLoopStart(1297)
        val token = regexToken.find(json, idx + 1)
Coverage.statementStart(1298)
        if (token == null) {
Coverage.ifStart(1299)
            return nodeSparql
        }
Coverage.statementStart(1300)
        idx = token.range.last
Coverage.statementStart(1301)
        lasttokenbracket = thistokenbracket
Coverage.statementStart(1302)
        thistokenbracket = false
Coverage.statementStart(1303)
        when (token.value) {
            "\"head\"" -> {
Coverage.whenCaseStart(1305)
                if (lastParent == null) {
Coverage.ifStart(1306)
                    lastParent = nodeHead
Coverage.statementStart(1307)
                    lastParentCounter = opencounter
Coverage.statementStart(1308)
                }
Coverage.statementStart(1309)
            }
            "\"results\"" -> {
Coverage.whenCaseStart(1310)
                if (lastParent == null) {
Coverage.ifStart(1311)
                    lastParent = nodeResults
Coverage.statementStart(1312)
                    lastParentCounter = opencounter
Coverage.statementStart(1313)
                    nodeBinding = null
Coverage.statementStart(1314)
                }
Coverage.statementStart(1315)
            }
            "\"vars\"", "\"bindings\"", ",", ":" -> {
Coverage.whenCaseStart(1316)
            }
            "{", "[" -> {
Coverage.whenCaseStart(1317)
                opencounter++
Coverage.statementStart(1318)
                thistokenbracket = true
Coverage.statementStart(1319)
            }
            "}", "]" -> {
Coverage.whenCaseStart(1320)
                opencounter--
Coverage.statementStart(1321)
                if (lasttokenbracket) {
Coverage.ifStart(1322)
                    if (lastParent != nodeHead) {
Coverage.ifStart(1323)
                        nodeResults.addContent(XMLElement("result"))
Coverage.statementStart(1324)
                    }
Coverage.statementStart(1325)
                } else {
Coverage.ifStart(1326)
                    if (nodeBinding != null) {
Coverage.ifStart(1327)
                        when (attributes["type"]) {
                            "uri", "bnode" -> {
Coverage.whenCaseStart(1329)
                                val node = XMLElement(attributes["type"]!!)
Coverage.statementStart(1330)
                                nodeBinding.addContent(node)
Coverage.statementStart(1331)
                                nodeBinding = node
Coverage.statementStart(1332)
                            }
                            "literal", "typed-literal" -> {
Coverage.whenCaseStart(1333)
                                val node = XMLElement("literal")
Coverage.statementStart(1334)
                                nodeBinding.addContent(node)
Coverage.statementStart(1335)
                                nodeBinding = node
Coverage.statementStart(1336)
                                if (attributes["datatype"] != null) {
Coverage.ifStart(1337)
                                    nodeBinding.addAttribute("datatype", attributes["datatype"]!!)
Coverage.statementStart(1338)
                                }
Coverage.statementStart(1339)
                                if (attributes["xml:lang"] != null) {
Coverage.ifStart(1340)
                                    nodeBinding.addAttribute("xml:lang", attributes["xml:lang"]!!)
Coverage.statementStart(1341)
                                }
Coverage.statementStart(1342)
                            }
                        }
Coverage.statementStart(1343)
                        if (attributes["value"] != null) {
Coverage.ifStart(1344)
                            nodeBinding.addContentClean(attributes["value"]!!)
Coverage.statementStart(1345)
                        }
Coverage.statementStart(1346)
                        attributes.clear()
Coverage.statementStart(1347)
                        nodeBinding = null
Coverage.statementStart(1348)
                    } else if (nodeResult != null) {
Coverage.ifStart(1349)
                        nodeResult = null
Coverage.statementStart(1350)
                    }
Coverage.statementStart(1351)
                    if (opencounter == lastParentCounter) {
Coverage.ifStart(1352)
                        lastParent = null
Coverage.statementStart(1353)
                    }
Coverage.statementStart(1354)
                }
Coverage.statementStart(1355)
            }
            else -> {
Coverage.whenCaseStart(1356)
                var flag = false
Coverage.statementStart(1357)
                if (lastParent != null) {
Coverage.ifStart(1358)
                    if (lastParent == nodeHead) {
Coverage.ifStart(1359)
                        nodeHead.addContent(XMLElement("variable").addAttribute("name", token.value.substring(1, token.value.length - 1)))
Coverage.statementStart(1360)
                        flag = true
Coverage.statementStart(1361)
                    } else {
Coverage.ifStart(1362)
                        if (nodeResult == null) {
Coverage.ifStart(1363)
                            nodeResult = XMLElement("result")
Coverage.statementStart(1364)
                            nodeResults.addContent(nodeResult)
Coverage.statementStart(1365)
                        }
Coverage.statementStart(1366)
                        if (nodeBinding == null) {
Coverage.ifStart(1367)
                            nodeBinding = XMLElement("binding").addAttribute("name", token.value.substring(1, token.value.length - 1))
Coverage.statementStart(1368)
                            nodeResult.addContent(nodeBinding)
Coverage.statementStart(1369)
                            flag = true
Coverage.statementStart(1370)
                        }
Coverage.statementStart(1371)
                    }
Coverage.statementStart(1372)
                } else {
Coverage.ifStart(1373)
                    if (token.value == "\"boolean\"") {
Coverage.ifStart(1374)
                        val token3 = regexToken.find(json, idx + 1)
Coverage.statementStart(1375)
                        if (token3 == null) {
Coverage.ifStart(1376)
                            return nodeSparql
                        }
Coverage.statementStart(1377)
                        SanityCheck.checkEQ({ token3.value }, { ":" })
Coverage.statementStart(1378)
                        idx = token3.range.last
Coverage.statementStart(1379)
                        val token2 = regexToken.find(json, idx + 1)
Coverage.statementStart(1380)
                        if (token2 == null) {
Coverage.ifStart(1381)
                            return nodeSparql
                        }
Coverage.statementStart(1382)
                        val nodeSparql2 = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
Coverage.statementStart(1383)
                        val node = XMLElement("boolean").addContentClean(token2.value)
Coverage.statementStart(1384)
                        nodeSparql2.addContent(nodeHead)
Coverage.statementStart(1385)
                        nodeSparql2.addContent(node)
Coverage.statementStart(1386)
                        return nodeSparql2
                    }
Coverage.statementStart(1387)
                }
Coverage.statementStart(1388)
                if (!flag && nodeBinding != null) {
Coverage.ifStart(1389)
                    val token3 = regexToken.find(json, idx + 1)
Coverage.statementStart(1390)
                    if (token3 == null) {
Coverage.ifStart(1391)
                        return nodeSparql
                    }
Coverage.statementStart(1392)
                    SanityCheck.checkEQ({ token3.value }, { ":" })
Coverage.statementStart(1393)
                    idx = token3.range.last
Coverage.statementStart(1394)
                    val token2 = regexToken.find(json, idx + 1)
Coverage.statementStart(1395)
                    if (token2 == null) {
Coverage.ifStart(1396)
                        return nodeSparql
                    }
Coverage.statementStart(1397)
                    idx = token2.range.last
Coverage.statementStart(1398)
                    if (token2.value.startsWith("\"") && token2.value.endsWith("\"")) {
Coverage.ifStart(1399)
                        attributes[token.value.substring(1, token.value.length - 1)] = token2.value.substring(1, token2.value.length - 1)
Coverage.statementStart(1400)
                    } else {
Coverage.ifStart(1401)
                        attributes[token.value.substring(1, token.value.length - 1)] = token2.value
Coverage.statementStart(1402)
                    }
Coverage.statementStart(1403)
                }
Coverage.statementStart(1404)
            }
        }
Coverage.statementStart(1405)
    }
Coverage.statementStart(1406)
    return nodeSparql
}
