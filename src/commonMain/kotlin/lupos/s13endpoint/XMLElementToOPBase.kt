package lupos.s13endpoint

import lupos.s13endpoint.Endpoint

import lupos.s07physicalOperators.multiinput.POPJoinNestedLoop
import lupos.s07physicalOperators.multiinput.POPUnion
import lupos.s07physicalOperators.singleinput.POPBind
import lupos.s07physicalOperators.singleinput.POPTemporaryStore
import lupos.s03buildOperatorGraph.data.LOPVariable
import lupos.s03buildOperatorGraph.OPBase
import lupos.s03buildOperatorGraph.toASTNode
import lupos.s08tripleStore.TripleStore
import lupos.s00misc.XMLElement

import lupos.s07physicalOperators.singleinput.POPSort
import lupos.s07physicalOperators.singleinput.POPRename
import lupos.s07physicalOperators.singleinput.POPProjection
import lupos.s07physicalOperators.singleinput.POPMakeBooleanResult
import lupos.s07physicalOperators.singleinput.POPGroup
import lupos.s07physicalOperators.singleinput.POPFilter
import lupos.s07physicalOperators.singleinput.POPFilterExact
import lupos.s07physicalOperators.singleinput.POPBindUndefined
import lupos.s07physicalOperators.singleinput.modifiers.POPOffset
import lupos.s07physicalOperators.singleinput.modifiers.POPLimit
import lupos.s07physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s07physicalOperators.POPValues
import lupos.s07physicalOperators.POPExpression
import lupos.s07physicalOperators.POPEmptyRow
import lupos.s07physicalOperators.POPBase
import lupos.s07physicalOperators.multiinput.POPJoinHashMap

fun createLOPVariable(mapping: MutableMap<String, String>, name: String): LOPVariable {
    val n = mapping[name]
    if (n != null)
        return LOPVariable(n)
    return LOPVariable(name)
}

fun XMLElement.Companion.convertToOPBase(node: XMLElement, store: TripleStore, mapping: MutableMap<String, String> = mutableMapOf<String, String>()): OPBase {
    return when (node.tag) {
        "POPSort" -> {
            val child = convertToOPBase(node["child"]!!.childs.first()!!, store, mapping) as POPBase
            POPSort(createLOPVariable(mapping, node.attributes["by"]!!), node.attributes["order"] == "ASC", child)
        }
        "POPRename" -> {
            val child = convertToOPBase(node.childs.first()!!, store, mapping) as POPBase
            POPRename(createLOPVariable(mapping, node.attributes["nameTo"]!!), createLOPVariable(mapping, node.attributes["nameFrom"]!!), child)
        }
        "POPProjection" -> {
            val child = convertToOPBase(node["child"]!!.childs.first()!!, store, mapping) as POPBase
            val variables = mutableListOf<LOPVariable>()
            node["variables"]!!.childs.forEach {
                variables.add(createLOPVariable(mapping, it.attributes["name"]!!))
            }
            return POPProjection(variables, child)
        }
        "POPMakeBooleanResult" -> POPMakeBooleanResult(convertToOPBase(node["child"]!!.childs.first()!!, store, mapping) as POPBase)
        "POPGroup" -> {
            val child = convertToOPBase(node["child"]!!.childs.first()!!, store, mapping) as POPBase
            val by = mutableListOf<LOPVariable>()
            var bindings: POPBase = POPEmptyRow()
            node["by"]!!.childs!!.forEach {
                by.add(createLOPVariable(mapping, it.attributes["name"]!!))
            }
            node["bindings"]!!.childs.forEach {
                bindings = POPBind(createLOPVariable(mapping, it.attributes["name"]!!), POPExpression.fromXMLElement(it.childs.first()), bindings)
            }
            if (bindings is POPEmptyRow)
                return POPGroup(by, null, child)
            return POPGroup(by, bindings as POPBind, child)
        }
        "POPFilter" -> POPFilter(POPExpression.fromXMLElement(node["filter"]!!.childs.first()), convertToOPBase(node["child"]!!.childs.first()!!, store, mapping) as POPBase)
        "POPFilterExact" -> {
            val child = convertToOPBase(node["child"]!!.childs.first()!!, store, mapping) as POPBase
            POPFilterExact(createLOPVariable(mapping, node.attributes["name"]!!), node.attributes["value"]!!, child)
        }
        "POPBindUndefined" -> {
            val child = convertToOPBase(node["child"]!!.childs.first()!!, store, mapping) as POPBase
            POPBindUndefined(createLOPVariable(mapping, node.attributes["name"]!!), child)
        }
        "POPBind" -> {
            val child = convertToOPBase(node["child"]!!.childs.first()!!, store, mapping) as POPBase
            POPBind(createLOPVariable(mapping, node.attributes["name"]!!), convertToOPBase(node["expression"]!!.childs.first()!!, store, mapping) as POPExpression, child)
        }
        "POPOffset" -> POPOffset(node.attributes["offset"]!!.toInt(), convertToOPBase(node["child"]!!.childs.first(), store, mapping) as POPBase)
        "POPLimit" -> POPLimit(node.attributes["limit"]!!.toInt(), convertToOPBase(node["child"]!!.childs.first()!!, store, mapping) as POPBase)
        "POPDistinct" -> POPDistinct(convertToOPBase(node["child"]!!.childs.first()!!, store, mapping) as POPBase)
        "POPValues" -> {
            val vars = mutableListOf<String>()
            val vals = mutableListOf<MutableMap<String, String>>()
            node["variables"]!!.childs!!.forEach {
                vars.add(it.attributes["name"]!!)
            }
            println("XX $node")
            node["bindings"]!!.childs!!.forEach {
                println("YY $it")
                val exp = mutableMapOf<String, String>()
                it.childs!!.forEach {
                    println("ZZ $it")
                    exp[it.attributes["name"]!!] = it.attributes["content"]!!
                }
                vals.add(exp)
            }
            return POPValues(vars, vals)
        }
        "POPEmptyRow" -> POPEmptyRow()
        "POPUnion" -> POPUnion(convertToOPBase(node["childA"]!!.childs.first()!!, store, mapping) as POPBase, convertToOPBase(node["childB"]!!.childs.first()!!, store, mapping) as POPBase)
        "POPJoinNestedLoop" -> POPJoinNestedLoop(convertToOPBase(node["childA"]!!.childs.first()!!, store, mapping) as POPBase, convertToOPBase(node["childB"]!!.childs.first()!!, store, mapping) as POPBase, node.attributes["optional"]!!.toBoolean())
        "POPJoinHashMap" -> POPJoinHashMap(convertToOPBase(node["childA"]!!.childs.first()!!, store, mapping) as POPBase, convertToOPBase(node["childB"]!!.childs.first()!!, store, mapping) as POPBase, node.attributes["optional"]!!.toBoolean())
        "POPTemporaryStore" -> POPTemporaryStore(convertToOPBase(node["child"]!!.childs.first()!!, store, mapping) as POPBase)
        "POPExpression" -> POPExpression(XMLElement.toASTNode(node.childs.first()!!))
        "TripleStoreIterator" -> {
            val res = store.getIterator()
            val olduuid = node.attributes["uuid"]
            mapping["#s" + olduuid] = "#s${res.uuid}"
            mapping["#p" + olduuid] = "#p${res.uuid}"
            mapping["#o" + olduuid] = "#o${res.uuid}"
            return res
        }
        else -> throw Exception("XMLElement.Companion.convertToOPBase unknown :: ${node.tag}")
    }
}
