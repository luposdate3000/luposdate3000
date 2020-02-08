package lupos.s14endpoint
import lupos.s12p2p.DistributedTripleStore
import lupos.s12p2p.globalStore
import lupos.s03resultRepresentation.ResultSetDictionary

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.toASTNode
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
import lupos.s09physicalOperators.multiinput.POPJoinNestedLoop
import lupos.s09physicalOperators.multiinput.POPUnion
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPExpression
import lupos.s09physicalOperators.noinput.POPValues
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.modifiers.POPLimit
import lupos.s09physicalOperators.singleinput.modifiers.POPOffset
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPBindUndefined
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPFilterExact
import lupos.s09physicalOperators.singleinput.POPGroup
import lupos.s09physicalOperators.singleinput.POPMakeBooleanResult
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s09physicalOperators.singleinput.POPRename
import lupos.s09physicalOperators.singleinput.POPSort
import lupos.s09physicalOperators.singleinput.POPTemporaryStore
import lupos.s12p2p.POPServiceIRI
import lupos.s14endpoint.Endpoint


inline fun createLOPVariable(mapping: MutableMap<String, String>, name: String): LOPVariable {
    val n = mapping[name]
    if (n != null)
        return LOPVariable(n)
    return LOPVariable(name)
}

fun XMLElement.Companion.convertToOPBase(dictionary: ResultSetDictionary, transactionID: Long, node: XMLElement, store: DistributedTripleStore = globalStore, mapping: MutableMap<String, String> = mutableMapOf<String, String>()): OPBase {
    return when (node.tag) {
        "POPSort" -> {
            val child = convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], store, mapping)
            POPSort(dictionary, createLOPVariable(mapping, node.attributes["by"]!!), node.attributes["order"] == "ASC", child)
        }
        "POPRename" -> {
            val child = convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], store, mapping)
            POPRename(dictionary, createLOPVariable(mapping, node.attributes["nameTo"]!!), createLOPVariable(mapping, node.attributes["nameFrom"]!!), child)
        }
        "POPProjection" -> {
            val child = convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], store, mapping)
            val variables = mutableListOf<LOPVariable>()
            node["variables"]!!.childs.forEach {
                variables.add(createLOPVariable(mapping, it.attributes["name"]!!))
            }
            return POPProjection(dictionary, variables, child)
        }
        "POPMakeBooleanResult" -> POPMakeBooleanResult(dictionary, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], store, mapping))
        "POPGroup" -> {
            val child = convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], store, mapping)
            val by = mutableListOf<LOPVariable>()
            var bindings: POPBase = POPEmptyRow(dictionary)
            node["by"]!!.childs.forEach {
                by.add(createLOPVariable(mapping, it.attributes["name"]!!))
            }
            node["bindings"]!!.childs.forEach {
                bindings = POPBind(dictionary, createLOPVariable(mapping, it.attributes["name"]!!), POPExpression.fromXMLElement(dictionary, it.childs[0]), bindings)
            }
            if (bindings is POPEmptyRow)
                return POPGroup(dictionary, by, null, child)
            return POPGroup(dictionary, by, bindings as POPBind, child)
        }
        "POPFilter" -> POPFilter(dictionary, POPExpression.fromXMLElement(dictionary, node["filter"]!!.childs[0]), convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], store, mapping))
        "POPFilterExact" -> {
            val child = convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], store, mapping)
            POPFilterExact(dictionary, createLOPVariable(mapping, node.attributes["name"]!!), node.attributes["value"]!!, child)
        }
        "POPBindUndefined" -> {
            val child = convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], store, mapping)
            POPBindUndefined(dictionary, createLOPVariable(mapping, node.attributes["name"]!!), child)
        }
        "POPBind" -> {
            val child = convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], store, mapping)
            POPBind(dictionary, createLOPVariable(mapping, node.attributes["name"]!!), convertToOPBase(dictionary, transactionID, node["expression"]!!.childs[0], store, mapping) as POPExpression, child)
        }
        "POPOffset" -> POPOffset(dictionary, node.attributes["offset"]!!.toInt(), convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], store, mapping))
        "POPLimit" -> POPLimit(dictionary, node.attributes["limit"]!!.toInt(), convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], store, mapping))
        "POPDistinct" -> POPDistinct(dictionary, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], store, mapping))
        "POPValues" -> {
            val vars = mutableListOf<String>()
            val vals = mutableListOf<MutableMap<String, String>>()
            node["variables"]!!.childs.forEach {
                vars.add(it.attributes["name"]!!)
            }
            node["bindings"]!!.childs.forEach {
                val exp = mutableMapOf<String, String>()
                it.childs.forEach {
                    exp[it.attributes["name"]!!] = it.attributes["content"]!!
                }
                vals.add(exp)
            }
            return POPValues(dictionary, vars, vals)
        }
        "POPEmptyRow" -> POPEmptyRow(dictionary)
        "POPUnion" -> POPUnion(dictionary, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], store, mapping), convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], store, mapping))
        "POPJoinNestedLoop" -> POPJoinNestedLoop(dictionary, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], store, mapping), convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], store, mapping), node.attributes["optional"]!!.toBoolean())
        "POPJoinHashMap" -> POPJoinHashMap(dictionary, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], store, mapping), convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], store, mapping), node.attributes["optional"]!!.toBoolean())
        "POPTemporaryStore" -> POPTemporaryStore(dictionary, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], store, mapping))
        "POPExpression" -> POPExpression(dictionary, XMLElement.toASTNode(node.childs[0]))
        "TripleStoreIterator" -> {
            val res = store.getNamedGraph(node.attributes["name"]!!).getIterator(dictionary)
            val olduuid = node.attributes["uuid"]
            mapping["#s" + olduuid] = "#s${res.uuid}"
            mapping["#p" + olduuid] = "#p${res.uuid}"
            mapping["#o" + olduuid] = "#o${res.uuid}"
            return res
        }
        "POPServiceIRI" -> return POPServiceIRI(dictionary, transactionID, node.attributes["name"]!!, node.attributes["silent"]!!.toBoolean(), convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], store, mapping))
        else -> throw Exception("XMLElement.Companion.convertToOPBase unknown :: ${node.tag}")
    }
}
